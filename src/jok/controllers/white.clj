(ns jok.controllers.white
  (:require [jok.config :as config]
            [jok.views.white :as view]
            [jok.models.white :as model]))

(defn upload [request]
  (let [{:keys [tempfile filename]}
        (-> request :params :file)]
    (model/save-file tempfile filename config/MUSIC-DIR))
  "upload complete")

(defn upload-multiple [request]
  (let [files (-> request :multipart-params (get "file"))]
    (doseq [file files]
      (let [{:keys [tempfile filename]} file]
        (model/save-file tempfile filename config/MUSIC-DIR)))))
