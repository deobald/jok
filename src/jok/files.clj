(ns jok.files
  (:require [ring.util.codec :as codec]
            [clojure.java.io :as io]
            [clojure.string :as s]))

(defn all-files [dir]
  (map #(.getAbsolutePath %) (file-seq (io/file dir))))

(defn all-music-files [dir]
  (filter #(.endsWith % "mp3") (all-files dir)))

(defn url-encode-parts [path]
  (->> (s/split path #"/")
       (map codec/url-encode)
       (s/join "/")))
