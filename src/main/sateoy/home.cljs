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
  [:div {:class (css :text-center)}
   [:header
    [:h1 {:class (css :py-12 :text-2xl :font-bold)}
     "不爱国的请划走！"]]
   [:section
    [:p "爱莲者尚未竣工，但现在你可以"]
    [:div {:class (css :grid :grid-cols-1 :gap-y-4 :text-sm :font-semibold :leading-6 :text-zinc-700)}
     [:div
      [:a {:href "https://dict.baidu.com/shici/detail?pid=9b5dcacead424897ac2c9db8f1ad01fa"
           :class (css :group :-mx-2 :-my-0.5 :px-2 :py-0.5 :inline-flex :items-center :gap-3 :rounded-lg)}
       [:svg {:xmlns "http://www.w3.org/2000/svg" :viewBox "0 0 448 512" :class (css :w-6 :h-6)}
        [:path {:d "M96 0C43 0 0 43 0 96V416c0 53 43 96 96 96H384h32c17.7 0 32-14.3 32-32s-14.3-32-32-32V384c17.7 0 32-14.3 32-32V32c0-17.7-14.3-32-32-32H384 96zm0 384H352v64H96c-17.7 0-32-14.3-32-32s14.3-32 32-32zm32-240c0-8.8 7.2-16 16-16H336c8.8 0 16 7.2 16 16s-7.2 16-16 16H144c-8.8 0-16-7.2-16-16zm16 48H336c8.8 0 16 7.2 16 16s-7.2 16-16 16H144c-8.8 0-16-7.2-16-16s7.2-16 16-16z"}]]
       [:div "欣赏周敦颐著作《爱莲说》"]]]]]])

(rdomc/render @root [:> react/StrictMode {} [App]])
