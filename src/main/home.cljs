(ns home)

(defn ^:dev/after-load start []
  (js/console.log "start"))

(defn init []
  (js/console.log "init")
  (start))

(defn ^:dev/before-load stop []
  (js/console.log "stop"))
