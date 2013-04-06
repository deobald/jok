(ns jok.views.layou
  (:use [hiccup.core :only [html]]
        [hiccup.page :only [html5
                            include-css]]))

(defn- flash [request]
  (when (-> request :flash :success)
    (html [:div {:class "alert-message success"}
           (-> request :flash :success)])))

(defn ty [request & [content header]]
  (html5
   [:head
    [:title "jok"]
    [:script {:src "/js/v/swipe.js"}]
    [:script {:src "/js/jok.js"}]
    (include-css "/css/reset.css")
    (include-css "/css/jok.css")
    (include-css "/css/swipe.css")
    (html header)]

   [:body {:data-accept "mp3|m4a|mp4|mpeg"
           :onload "jok_core.ready();"}
    (html
     (flash request)
     (html content))]))
