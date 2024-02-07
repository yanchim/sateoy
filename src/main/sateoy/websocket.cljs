(ns sateoy.websocket
  (:require-macros
   [cljs.core.async.macros :as asyncm :refer (go go-loop)])
  (:require
   [cljs.core.async :as async :refer (<! >! put! chan)]
   [cljs.reader :refer [read-string]]
   [sateoy.state :as state]
   [taoensso.sente  :as sente :refer (cb-success?)]))

;;;; Util for logging output to on-screen

(defn ->output!! [response]
  (.log js/console "server" response)
  ;; (let [{:keys [type name msg inserted_at]} (read-string response)]
  ;;   (when (= type "shout")
  ;;     (swap! state/chat-msg-list conj {:msg msg
  ;;                                      :name name
  ;;                                      :inserted_at inserted_at})))
  )

(defn ->output!
  ([] (->output!! "\n"))
  ([fmt & args]
   (let [msg (apply str fmt args)]
     (->output!! (str "\n- " msg)))))

;;;; Logging config

(defonce min-log-level (atom nil))

(defn- set-min-log-level! [level]
  (sente/set-min-log-level! level) ; Min log level for internal Sente namespaces
  (reset! min-log-level     level))

(set-min-log-level! #_:trace :debug #_:info #_:warn)

;;;; Define our Sente channel socket (chsk) client

(let [;; Serializtion format, must use same val for client + server:
      packer :edn                       ; Default packer, a good choice im most cases
      ;; (sente-transit/get-transit-packer) ; Needs Transit dep
      {:keys [chsk ch-recv send-fn state]}
      (sente/make-channel-socket-client!
       "/chat"                          ; Must match server Ring routing URL
       "for-chat"
       {:port 3000 :type :auto})]

  (def chsk       chsk)
  (def ch-chsk    ch-recv) ; ChannelSocket's receive channel
  (def chsk-send! send-fn) ; ChannelSocket's send API fn
  (def chsk-state state)   ; Watchable, read-only atom
  )

;;;; Sente event handlers

(defmulti -event-msg-handler
  "Multimethod to handle Sente `event-msg`s"
  :id                                   ; Dispatch on event-id
  )

(defn event-msg-handler
  "Wraps `-event-msg-handler` with logging, error catching, etc."
  [{:as ev-msg :keys [id ?data event]}]
  (-event-msg-handler ev-msg))

(defmethod -event-msg-handler
  :default ; Default/fallback case (no other matching handler)
  [{:as ev-msg :keys [event]}]
  (->output! "Unhandled event: %s" event))

(defmethod -event-msg-handler :chsk/state
  [{:as ev-msg :keys [?data]}]
  (let [[old-state-map new-state-map] (vector ?data)]
    (cond
      (:first-open? new-state-map) (->output! "Channel socket FIRST OPENED: "  new-state-map)
      (:opened?     new-state-map) (->output! "Channel socket OPENED: "        new-state-map)
      (:closed?     new-state-map) (->output! "Channel socket CLOSED: "        new-state-map)
      :else                        (->output! "Channel socket state changed: " new-state-map))))

(defmethod -event-msg-handler :chsk/recv
  [{:as ev-msg :keys [?data]}]
  (->output! "Push event from server: %s" ?data))

(defmethod -event-msg-handler :chsk/handshake
  [{:as ev-msg :keys [?data]}]
  (let [[?uid _ ?handshake-data first-handshake?] ?data]
    (->output! "Handshake: %s" ?data)))

(defmethod -event-msg-handler :chat/broadcast
  [{:as ev-msg :keys [?data]}]
  (->output! "receive boardcast")
  (let [{:keys [username msg timestamp]} ?data]
    (reset! state/chat-msg-list
            (conj @state/chat-msg-list {:username username :msg msg :timestamp timestamp}))))

;;;; Sente event router (our `event-msg-handler` loop)

(defonce router (atom nil))

(defn stop-router! [] (when-let [stop-fn @router] (stop-fn)))

(defn start-router! []
  (stop-router!)
  (reset! router
          (sente/start-client-chsk-router!
           ch-chsk event-msg-handler)))

;;;; Init stuff

(defn start! [] (start-router!))
(defonce start-once (start!))
