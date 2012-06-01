(ns jok.controllers.white
  (:require [jok.views.white :as view]
            [jok.models.white :as model]))

(defn index [request]
  (view/index request))

(defn upload [request]
  (let [{:keys [tempfile filename]}
        (-> request :params :file)]
    (model/save-file tempfile filename))
  "upload complete")

