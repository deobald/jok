(ns jukebox-web.sex.views.layou
  (:use [hiccup.core :only [html]]
        [hiccup.page-helpers :only [html5
                                    include-css]]))

(defn- flash [request]
  (when (-> request :flash :success)
    (html [:div {:class "alert-message success"}
           (-> request :flash :success)])))

(defn- progress-bar []
  (html [:script#file-notification {:type "text/example" }
         [:li.uploading.alert-message.block-message
          [:p "{{ file.name }} {{ file.size }}mb"]
          [:div.progress-wrapper
           [:div.progress-bar]]]]))

(defn t [request & content]
  (html5
   [:head
    [:title "Jok"]
    [:script {:src "/js/sex.js"}]
    (include-css "/css/sex.css")]

   [:body {:data-accept "mp3|m4a|mp4|mpeg"}
    (html
     (flash request)
     (html content))
    (progress-bar)]))
