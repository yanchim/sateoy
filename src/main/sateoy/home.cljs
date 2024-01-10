(ns sateoy.home
  (:require
   ["react" :as react]
   [reagent.core :as r]
   [reagent.dom.client :as rdomc]
   [shadow.css :refer [css]]))

(defonce root
  (delay (rdomc/create-root (js/document.getElementById "app"))))

(defn ^:dev/after-load start []
  (js/console.log "start"))

(defn init []
  (js/console.log "init")
  (start))

(defn ^:dev/before-load stop []
  (js/console.log "stop"))

(defn App []
  [:> react/Fragment
   [:header
    [:h1 {:class (css :py-12 :text-center :font-bold)}
     "答应我，不爱国的请划走！"]]])

(rdomc/render @root [:> react/StrictMode {} [App]])
