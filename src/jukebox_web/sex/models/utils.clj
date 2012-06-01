(ns jukebox-web.sex.models.utils
  (:import [java.io File]
           [java.util UUID])
  (:require [clojure.string :as string]
            [clojure.java.io :as io]))

(defn extension [filename]
  (last (string/split (str filename) #"\.")))

(defn file-path [& parts]
  (string/join File/separator parts))

(defn uuid-file [dir ext]
  (io/as-file (file-path dir (str (UUID/randomUUID) "." ext))))

(defn strip-slashes [s]
  (string/replace s #"/" " "))

(defn mkdir-p [path]
  (.mkdirs (io/as-file path)))

(defn mv [from to]
  (.renameTo (io/as-file from) (io/as-file to)))
