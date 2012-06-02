(ns jok.views.layou
  (:use [hiccup.core :only [html]]
        [hiccup.page :only [html5
                            include-css]]))

(defn- flash [request]
  (when (-> request :flash :success)
    (html [:div {:class "alert-message success"}
           (-> request :flash :success)])))

(defn t [request color & content]
  (html5
   [:head
    [:title "jok"]
    [:script {:src "/js/v/jquery-1.6.4.min.js"}]
    [:script {:src "/js/v/underscore-min.js"}]
    [:script {:src "/js/jok.js"}]
    (include-css "/css/jok.css")]

   [:body {:data-accept "mp3|m4a|mp4|mpeg"
           :class color}
    [:div.page
     [:div.navigation
      [:ul
       [:li [:a {:href "/white"} "White"]]
       [:li [:a {:href "/yellow"} "Yellow"]]
       [:li [:a {:href "/pink"} "Pink"]]]]
     (html
      (flash request)
      (html content))]]))
