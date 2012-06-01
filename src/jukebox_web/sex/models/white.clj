(ns jukebox-web.sex.models.white
  (:require [clojure.string :as string]))

(defn save-file [tempfile ext]
  "oh, we probably uploaded that.")

;; (defn save-file [tempfile user ext]
;;   (let [file-with-ext (io/as-file (file-path *music-library* (str (UUID/randomUUID) "." ext)))]
;;     (io/copy tempfile file-with-ext)
;;     (rename-with-tags user file-with-ext)))

(defn extension [filename]
  (last (string/split (str filename) #"\.")))