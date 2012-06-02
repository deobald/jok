(ns jok.models.yellow
  (:require [jok.files :as files]
            [jok.tagging :as tagging]))

(defn all-songs [dir]
  (map tagging/track-metadata (files/all-music-files dir)))