(ns jok.models.yellow
  (:require [jok.files :as files]
            [jok.tagging :as tagging]))

(defn- to-song [file-path]
  (assoc (tagging/track-metadata file-path)
    :file file-path))

(defn all-songs [dir]
  (map to-song (files/all-music-files dir)))
