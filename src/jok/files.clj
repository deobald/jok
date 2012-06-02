(ns jok.files
  (:require [clojure.java.io :as io]))

(defn all-files [dir]
  (map #(.getAbsolutePath %) (file-seq (io/file dir))))

(defn all-music-files [dir]
  (filter #(.endsWith % "mp3") (all-files dir)))
