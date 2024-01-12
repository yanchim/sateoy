(ns sateoy.views
  (:require
   [shadow.css :refer [css]]
   [sateoy.state :as state]))

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
      [:svg {:xmlns "http://www.w3.org/2000/svg" :height "24" :width "24" :viewBox "0 -960 960 960"}
       [:path {:d "M320-240h320v-80H320v80Zm0-160h320v-80H320v80ZM240-80q-33 0-56.5-23.5T160-160v-640q0-33 23.5-56.5T240-880h320l240 240v480q0 33-23.5 56.5T720-80H240Zm280-520v-200H240v640h480v-440H520ZM240-800v200-200 640-640Z"}]]
      [:div "欣赏周敦颐著作《爱莲说》"]]
     [:a {:href "https://space.bilibili.com/1437582453"
          :class (css :mx-auto :px-2 :py-0.5 :inline-flex :items-center :gap-3 :rounded-lg
                      [:hover {:color "black" :background-color "grey"}])}
      [:svg {:xmlns "http://www.w3.org/2000/svg" :height "24" :width "24" :viewBox "0 0 512 512"}
       [:path {:d "M488.6 104.1C505.3 122.2 513 143.8 511.9 169.8V372.2C511.5 398.6 502.7 420.3 485.4 437.3C468.2 454.3 446.3 463.2 419.9 464H92.02C65.57 463.2 43.81 454.2 26.74 436.8C9.682 419.4 .7667 396.5 0 368.2V169.8C.7667 143.8 9.682 122.2 26.74 104.1C43.81 87.75 65.57 78.77 92.02 78H121.4L96.05 52.19C90.3 46.46 87.42 39.19 87.42 30.4C87.42 21.6 90.3 14.34 96.05 8.603C101.8 2.868 109.1 0 117.9 0C126.7 0 134 2.868 139.8 8.603L213.1 78H301.1L375.6 8.603C381.7 2.868 389.2 0 398 0C406.8 0 414.1 2.868 419.9 8.603C425.6 14.34 428.5 21.6 428.5 30.4C428.5 39.19 425.6 46.46 419.9 52.19L394.6 78L423.9 78C450.3 78.77 471.9 87.75 488.6 104.1H488.6zM449.8 173.8C449.4 164.2 446.1 156.4 439.1 150.3C433.9 144.2 425.1 140.9 416.4 140.5H96.05C86.46 140.9 78.6 144.2 72.47 150.3C66.33 156.4 63.07 164.2 62.69 173.8V368.2C62.69 377.4 65.95 385.2 72.47 391.7C78.99 398.2 86.85 401.5 96.05 401.5H416.4C425.6 401.5 433.4 398.2 439.7 391.7C446 385.2 449.4 377.4 449.8 368.2L449.8 173.8zM185.5 216.5C191.8 222.8 195.2 230.6 195.6 239.7V273C195.2 282.2 191.9 289.9 185.8 296.2C179.6 302.5 171.8 305.7 162.2 305.7C152.6 305.7 144.7 302.5 138.6 296.2C132.5 289.9 129.2 282.2 128.8 273V239.7C129.2 230.6 132.6 222.8 138.9 216.5C145.2 210.2 152.1 206.9 162.2 206.5C171.4 206.9 179.2 210.2 185.5 216.5H185.5zM377 216.5C383.3 222.8 386.7 230.6 387.1 239.7V273C386.7 282.2 383.4 289.9 377.3 296.2C371.2 302.5 363.3 305.7 353.7 305.7C344.1 305.7 336.3 302.5 330.1 296.2C323.1 289.9 320.7 282.2 320.4 273V239.7C320.7 230.6 324.1 222.8 330.4 216.5C336.7 210.2 344.5 206.9 353.7 206.5C362.9 206.9 370.7 210.2 377 216.5H377z"}]]
      [:div "关注东雪莲喵，关注东雪莲谢谢喵"]]
     [:a {:href "#"
          :on-click #(swap! state/chat assoc :show true)
          :class (css :mx-auto :px-2 :py-0.5 :inline-flex :items-center :gap-3 :rounded-lg
                      [:hover {:color "black" :background-color "grey"}])}
      [:svg {:xmlns "http://www.w3.org/2000/svg" :height "24" :width "24" :viewBox "0 0 640 512"}
       [:path {:d "M208 352c114.9 0 208-78.8 208-176S322.9 0 208 0S0 78.8 0 176c0 38.6 14.7 74.3 39.6 103.4c-3.5 9.4-8.7 17.7-14.2 24.7c-4.8 6.2-9.7 11-13.3 14.3c-1.8 1.6-3.3 2.9-4.3 3.7c-.5 .4-.9 .7-1.1 .8l-.2 .2 0 0 0 0C1 327.2-1.4 334.4 .8 340.9S9.1 352 16 352c21.8 0 43.8-5.6 62.1-12.5c9.2-3.5 17.8-7.4 25.3-11.4C134.1 343.3 169.8 352 208 352zM448 176c0 112.3-99.1 196.9-216.5 207C255.8 457.4 336.4 512 432 512c38.2 0 73.9-8.7 104.7-23.9c7.5 4 16 7.9 25.2 11.4c18.3 6.9 40.3 12.5 62.1 12.5c6.9 0 13.1-4.5 15.2-11.1c2.1-6.6-.2-13.8-5.8-17.9l0 0 0 0-.2-.2c-.2-.2-.6-.4-1.1-.8c-1-.8-2.5-2-4.3-3.7c-3.6-3.3-8.5-8.1-13.3-14.3c-5.5-7-10.7-15.4-14.2-24.7c24.9-29 39.6-64.7 39.6-103.4c0-92.8-84.9-168.9-192.6-175.5c.4 5.1 .6 10.3 .6 15.5z"}]]
      [:div "对中国妄下论断"]]
     [:a {:href "https://github.com/yanchim/sateoy"
          :class (css :mx-auto :px-2 :py-0.5 :inline-flex :items-center :gap-3 :rounded-lg
                      [:hover {:color "black" :background-color "grey"}])}
      [:svg {:xmlns "http://www.w3.org/2000/svg" :height "24" :width "24" :viewBox "0 0 512 512"}
       [:path {:d "M165.9 397.4c0 2-2.3 3.6-5.2 3.6-3.3.3-5.6-1.3-5.6-3.6 0-2 2.3-3.6 5.2-3.6 3-.3 5.6 1.3 5.6 3.6zm-31.1-4.5c-.7 2 1.3 4.3 4.3 4.9 2.6 1 5.6 0 6.2-2s-1.3-4.3-4.3-5.2c-2.6-.7-5.5.3-6.2 2.3zm44.2-1.7c-2.9.7-4.9 2.6-4.6 4.9.3 2 2.9 3.3 5.9 2.6 2.9-.7 4.9-2.6 4.6-4.6-.3-1.9-3-3.2-5.9-2.9zM244.8 8C106.1 8 0 113.3 0 252c0 110.9 69.8 205.8 169.5 239.2 12.8 2.3 17.3-5.6 17.3-12.1 0-6.2-.3-40.4-.3-61.4 0 0-70 15-84.7-29.8 0 0-11.4-29.1-27.8-36.6 0 0-22.9-15.7 1.6-15.4 0 0 24.9 2 38.6 25.8 21.9 38.6 58.6 27.5 72.9 20.9 2.3-16 8.8-27.1 16-33.7-55.9-6.2-112.3-14.3-112.3-110.5 0-27.5 7.6-41.3 23.6-58.9-2.6-6.5-11.1-33.3 2.6-67.9 20.9-6.5 69 27 69 27 20-5.6 41.5-8.5 62.8-8.5s42.8 2.9 62.8 8.5c0 0 48.1-33.6 69-27 13.7 34.7 5.2 61.4 2.6 67.9 16 17.7 25.8 31.5 25.8 58.9 0 96.5-58.9 104.2-114.8 110.5 9.2 7.9 17 22.9 17 46.4 0 33.7-.3 75.4-.3 83.6 0 6.5 4.6 14.4 17.3 12.1C428.2 457.8 496 362.9 496 252 496 113.3 383.5 8 244.8 8zM97.2 352.9c-1.3 1-1 3.3.7 5.2 1.6 1.6 3.9 2.3 5.2 1 1.3-1 1-3.3-.7-5.2-1.6-1.6-3.9-2.3-5.2-1zm-10.8-8.1c-.7 1.3.3 2.9 2.3 3.9 1.6 1 3.6.7 4.3-.7.7-1.3-.3-2.9-2.3-3.9-2-.6-3.6-.3-4.3.7zm32.4 35.6c-1.6 1.3-1 4.3 1.3 6.2 2.3 2.3 5.2 2.6 6.5 1 1.3-1.3.7-4.3-1.3-6.2-2.2-2.3-5.2-2.6-6.5-1zm-11.4-14.7c-1.6 1-1.6 3.6 0 5.9 1.6 2.3 4.3 3.3 5.6 2.3 1.6-1.3 1.6-3.9 0-6.2-1.4-2.3-4-3.3-5.6-2z"}]]
      [:div "一起建设爱国者の日常"]]]]])

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
       :on-click #(swap! state/chat assoc :show false)}
      [:svg {:xmlns "http://www.w3.org/2000/svg" :height "24" :width "24"
             :viewBox "0 -960 960 960"}
       [:path {:fill "white" :d "m313-440 224 224-57 56-320-320 320-320 57 56-224 224h487v80H313Z"}]]]]
    [:h1 {:class (css :text-center :flex-1)} "维为道来"]
    [:div {:class (css :flex-1)}]]
   [:div {:class (css :h-16)}
    [:ul {:id "msg-list"}]]
   [:footer {:class (css :bg-transparent :p-2 :h-12 :bottom-0 :w-full :flex :justify-center :sticky :mt-auto)}
    [:div {:class (css :w-full :flex :items-center :text-gray-700)}
     [:input {:type "text" :id "name" :placeholder "昵称" :class (css :grow-0 :p-1.5) :required true}]
     [:input {:type "text" :id "name" :placeholder "你不了解中国而妄下论断" :class (css :grow :mx-1 :px-2 :py-1.5)}]
     [:button {:id "send"
               :on-click #(.log js/console "send")
               :class (css :text-white :bg-red-500 [:hover {:background-color "red"}] :bold :rounded :px-3 :py-1.5 :w-fit :transition-colors :duration-150)}
      "发送"]]]])

(defn core []
  (if (:show @state/chat)
    (do
      (.log js/console "get message")
      [chat])
    [home]))
