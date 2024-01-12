(ns sateoy.core
  (:require
   ["react" :as react]
   [reagent.dom.client :as rdomc]
   [sateoy.views :as views]))

(defonce root
  (delay (rdomc/create-root (js/document.getElementById "app"))))

(defn ^:dev/after-load start []
  (js/console.log "start"))

(defn init []
  (js/console.log "init")
  (start))

(defn ^:dev/before-load stop []
  (js/console.log "stop"))

(rdomc/render @root [:> react/StrictMode {} [views/core]])
