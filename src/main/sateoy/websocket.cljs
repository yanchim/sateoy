(ns sateoy.websocket)

(defonce channel (atom nil))

(defn connect! [url receive-handler]
  (if-let [chan (js/WebSocket. url)]
    (do
      (.log js/console "Connected!")
      (set! (.-onmessage chan) #(->> % .-data receive-handler))
      (reset! channel chan))
    (throw (ex-info "WebSocket connection failed." {:url url}))))

(defn send-message! [msg]
  (if-let [chan @channel]
    (.send chan (pr-str msg))
    (throw (ex-info "Couldn't send message, channel isn't open!"
                    {:message msg}))))

(defn disconnect! []
  (.close @channel)
  (reset! channel nil))
