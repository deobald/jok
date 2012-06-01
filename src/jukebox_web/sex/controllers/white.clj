(ns jukebox-web.sex.controllers.white
  (:require [jukebox-web.sex.views.white :as view]
            [jukebox-web.sex.models.white :as model]))

(defn index [request]
  (view/index request))

(defn upload [request]
  (when-let [current-user (-> request :session :current-user)]
    (let [{:keys [tempfile filename]}
          (-> request :params :file)]
      (model/save-file tempfile
                       (model/extension filename)))
    "upload complete"))

