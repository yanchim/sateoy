(ns sateoy.websocket
  (:require-macros
   [cljs.core.async.macros :as asyncm :refer (go go-loop)])
  (:require
   [cljs.core.async :as async :refer (<! >! put! chan)]
   [taoensso.sente  :as sente :refer (cb-success?)]))

;;;; Logging config

(defonce min-log-level (atom nil))

(defn- set-min-log-level! [level]
  (sente/set-min-log-level! level) ; Min log level for internal Sente namespaces
  (reset! min-log-level     level))

(set-min-log-level! #_:trace :debug #_:info #_:warn)

;;;; Define our Sente channel socket (chsk) client

(let [{:keys [chsk ch-recv send-fn state]}
      (sente/make-channel-socket-client!
       "/chat"
       "for-chat"
       {:port 3000 :type :auto})]

  (def chsk       chsk)
  (def ch-chsk    ch-recv) ; ChannelSocket's receive channel
  (def chsk-send! send-fn) ; ChannelSocket's send API fn
  (def chsk-state state)   ; Watchable, read-only atom
  )
