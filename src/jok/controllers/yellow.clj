(ns jok.controllers.yellow
  (:require [ring.util.response :as response]
            [jok.config :as config]
            [jok.views.yellow :as view]
            [jok.models.yellow :as model]
            [jok.models.pink :as pink]))

(defn index [request]
  (view/index request (model/all-songs config/MUSIC-DIR)))

(defn enqueue [request]
  (pink/enqueue! (model/to-song (-> request :form-params (get "file"))))
  (response/redirect "/yellow"))
