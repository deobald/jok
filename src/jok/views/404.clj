(ns jok.views.404
  (:use [hiccup.page :only [html5 include-css]]))

(defn lost []
  (html5
   [:head
    (include-css "css/jok.css")]
   [:body {:class "blue"}
    [:center
     [:h1
      "how did you even get here?"]]]))
