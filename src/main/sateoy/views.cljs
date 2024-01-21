(ns sateoy.views
  (:require
   [cljs.reader :refer [read-string]]
   [goog.string :refer [format]]
   [sateoy.components :as component]
   [sateoy.state :as state]
   [sateoy.websocket :as ws]
   [shadow.css :refer [css]]))

(defn home []
  [:div {:class (css :text-center :h-screen)
         :style {:background-image "url('images/lian.jpg')"
                 :background-repeat "no-repeat"
                 :background-size "cover"
                 :background-attachment "fixed"}}
   [:header
    [:h1 {:class (css :py-12 :text-2xl)} "不爱国的请划走！"]]
   [:section
    [:p {:class (css :py-12)} "爱莲者尚未竣工，但现在你可以"]
    [:div {:class (css :grid :grid-cols-1 :gap-y-4 :text-sm :font-semibold :leading-6 :text-zinc-700)}
     [:a {:href "https://dict.baidu.com/shici/detail?pid=9b5dcacead424897ac2c9db8f1ad01fa"
          :class (css :mx-auto :px-2 :py-0.5 :inline-flex :items-center :gap-3 :rounded-lg
                      [:hover {:color "black" :background-color "grey"}])}
      (component/svg-doc)
      [:div "欣赏周敦颐著作《爱莲说》"]]
     [:a {:href "https://space.bilibili.com/1437582453"
          :class (css :mx-auto :px-2 :py-0.5 :inline-flex :items-center :gap-3 :rounded-lg
                      [:hover {:color "black" :background-color "grey"}])}
      (component/svg-bilibili)
      [:div "关注东雪莲喵，关注东雪莲谢谢喵"]]
     [:a {:href "#"
          :on-click #(reset! state/show-chat? true)
          :class (css :mx-auto :px-2 :py-0.5 :inline-flex :items-center :gap-3 :rounded-lg
                      [:hover {:color "black" :background-color "grey"}])}
      (component/svg-chat)
      [:div "对中国妄下论断"]]
     [:a {:href "https://github.com/yanchim/sateoy"
          :class (css :mx-auto :px-2 :py-0.5 :inline-flex :items-center :gap-3 :rounded-lg
                      [:hover {:color "black" :background-color "grey"}])}
      (component/svg-github)
      [:div "一起建设爱国者の日常"]]]]])

(defn send-message []
  (let [name (if (empty? @state/chat-name) "爱国者" @state/chat-name)
        timestamp (.now js/Date)]
    (ws/send-message! {:type "shout"
                       :name name
                       :msg @state/chat-msg
                       :inserted_at timestamp})
    (reset! state/chat-msg "")
    (.scrollTo js/window 0 (.. js/document -documentElement -scrollHeight))))

(defn click-handler []
  (send-message))

(defn keypress-handler [event]
  (when (#{"Enter" "NumpadEnter"} (.-code event))
    (send-message)))

(defn timestamp-date
  "Get TIMESTAMP's date."
  [timestamp]
  (let [date (js/Date. timestamp)
        year (.getFullYear date)
        month (inc (.getMonth date))
        day (.getDate date)]
    (format "%d/%02d/%02d" year month day)))

(defn timestamp-time
  "Get TIMESTAMP's date."
  [timestamp]
  (let [date (js/Date. timestamp)
        hour (.getHours date)
        minute (.getMinutes date)
        second (.getSeconds date)]
    (format "%02d:%02d:%02d" hour minute second)))

(defn chat-msg-list
  "Render chat page's message list."
  []
  [:div {:class (css :mt-16)}
   [:ul {:id "msg-list"}
    (for [[idx msg] (map-indexed vector @state/chat-msg-list)]
      ^{:key (str idx)}
      [:div {:class (css :flex :flex-row
                         :mx-2 :py-2
                         :border-b :border-slate-300
                         :text-red-800
                         [:hover {:background-color "#333"}])
             :style {:width "95%"}}
       [:div {:class (css :text-left :font-semibold :break-words)
              :style {:width "20%"}}
        (:name msg)
        [:div {:class (css :text-xs :mr-1)}
         [:span {:class (css :font-thin :mr-1)} (timestamp-date (:inserted_at msg))]
         [:span (timestamp-time (:inserted_at msg))]]]
       [:div {:class (css :flex :mx-1 :grow)
              :style {:width "60%"}}
        (:msg msg)]])]])

(defn chat []
  [:div {:class (css :min-h-screen :flex :flex-col)
         :style {:background-image "url('images/weiwei.jpg')"
                 :background-repeat "repeat-y"
                 :background-size "100% auto"}}
   [:header {:class (css :bg-red-800 :text-white
                         :w-full :h-16
                         :text-3xl :font-mono
                         :px-4 :flex :items-center :justify-between
                         :top-0 :fixed :z-10)}
    [:div {:class (css :flex-1 :text-left)}
     [:button
      {:class (css :p-2 :rounded-full [:hover {:background-color "#999"}])
       :on-click #(reset! state/show-chat? false)}
      [:svg {:xmlns "http://www.w3.org/2000/svg" :height "24" :width "24"
             :viewBox "0 -960 960 960"}
       [:path {:fill "white" :d "m313-440 224 224-57 56-320-320 320-320 57 56-224 224h487v80H313Z"}]]]]
    [:h1 {:class (css :text-center :flex-1)} "维为道来"]
    [:div {:class (css :flex-1)}]]
   (chat-msg-list)
   [:footer {:class (css :bg-transparent :p-2 :h-12 :bottom-0 :w-full :flex :justify-center :sticky :mt-auto)}
    [:div {:class (css :w-full :flex :items-center :text-gray-700)}
     [:input {:type "text" :id "name" :placeholder "昵称" :class (css :grow-0 :p-1.5)
              :value @state/chat-name :on-change #(reset! state/chat-name (.. % -target -value))}]
     [:input {:type "text" :id "msg" :placeholder "你不了解中国而妄下论断" :class (css :grow :mx-1 :px-2 :py-1.5)
              :value @state/chat-msg
              :on-change #(reset! state/chat-msg (.. % -target -value))
              :on-key-press (when-not (empty? @state/chat-msg) keypress-handler)}]
     [:button {:id "send"
               :on-click (when-not (empty? @state/chat-msg) click-handler)
               :class (css :text-white :bg-red-500 [:hover {:background-color "red"}] :bold :rounded :px-3 :py-1.5 :w-fit :transition-colors :duration-150)}
      "发送"]]]])

(defn handle-response [response]
  (.log js/console "server" response)
  (let [{:keys [type name msg inserted_at]} (read-string response)]
    (when (= type "shout")
      (swap! state/chat-msg-list conj {:msg msg
                                       :name name
                                       :inserted_at inserted_at}))))

(defn core []
  (if @state/show-chat?
    (do
      (ws/connect!
       (str "ws://" (. js/location -hostname) ":3000/chat")
       handle-response)
      [chat])
    [home]))
