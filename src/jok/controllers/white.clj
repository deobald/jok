(ns jok.controllers.white
  (:require [jok.config :as config]
            [jok.views.white :as view]
            [jok.models.white :as model]))

(defn index [request]
  (view/index request))

(defn upload [request]
  (let [{:keys [tempfile filename]}
        (-> request :params :file)]
    (model/save-file tempfile filename config/MUSIC-DIR))
  "upload complete")

