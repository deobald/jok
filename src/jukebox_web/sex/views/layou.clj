(ns jukebox-web.sex.views.layou
  (:use [hiccup.core :only [html]]
        [hiccup.page-helpers :only [html5
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
    [:script {:src "/js/sex.js"}]
    (include-css "/css/sex.css")]

   [:body {:data-accept "mp3|m4a|mp4|mpeg"
           :class color}
    (html
     (flash request)
     (html content))]))
