(ns jukebox-web.sex.controllers.white
  (:require [jukebox-web.sex.views.white :as view]
            [jukebox-web.sex.models.white :as model]))

(defn index [request]
  (view/index request))

(defn upload [request]
  (let [{:keys [tempfile filename]}
        (-> request :params :file)]
    (model/save-file tempfile filename))
  "upload complete")

