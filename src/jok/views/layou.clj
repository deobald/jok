(ns jok.views.layou
  (:use [hiccup.core :only [html]]
        [hiccup.page :only [html5
                            include-css]]))

(defn- flash [request]
  (when (-> request :flash :success)
    (html [:div {:class "alert-message success"}
           (-> request :flash :success)])))

;; TODO: kill this
(defn t [request color left-pane right-pane & [content header]]
  (html5
   [:head
    [:title "jok"]
    [:script {:src "/js/jok.js"}]
    (include-css "/css/reset.css")
    (include-css "/css/jok.css")
    (html header)]

   [:body {:data-accept "mp3|m4a|mp4|mpeg"
           :onload "slide.ready(); search.ready(); browser_repl.ready();"}
    [:div.page
     [:div {:class (str left-pane " sidebar")} "&nbsp;"]
     [:div {:class (str color " main")}
      (html
       (flash request)
       (html content))]
     [:div {:class (str right-pane " sidebar")} "&nbsp;"]]]))

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
           :onload "slide.ready(); search.ready(); browser_repl.ready();"}
    (html
     (flash request)
     (html content))]))
