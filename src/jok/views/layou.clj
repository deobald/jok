(ns jok.views.layou
  (:use [hiccup.core :only [html]]
        [hiccup.page :only [html5
                            include-css]]))

(defn- flash [request]
  (when (-> request :flash :success)
    (html [:div {:class "alert-message success"}
           (-> request :flash :success)])))

(defn t [request color left-pane right-pane & [content header]]
  (html5
   [:head
    [:title "jok"]
    [:script {:src "/js/v/jquery-1.6.4.min.js"}]
    [:script {:src "/js/v/underscore-min.js"}]
    [:script {:src "/js/jok.js"}]
    (html header)
    (include-css "/css/jok.css")]

   [:body {:data-accept "mp3|m4a|mp4|mpeg"}
    [:div.page
     [:div {:class (str left-pane " sidebar")} "&nbsp;"]
     [:div {:class (str color " main")}
      (html
       (flash request)
       (html content))]
     [:div {:class (str right-pane " sidebar")} "&nbsp;"]]]))
