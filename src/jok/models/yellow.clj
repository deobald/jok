(ns jok.models.yellow
  (:require [jok.config :as config]
            [jok.files :as files]
            [jok.tagging :as tagging]))

(defn to-song [file-path]
  (assoc (tagging/track-metadata file-path)
    :file file-path
    :relative-path (clojure.string/replace file-path config/MUSIC-DIR "")))

(defn all-songs [dir]
  (map to-song (files/all-music-files dir)))
