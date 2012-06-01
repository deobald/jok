(ns jok.models.tagging
  (:import [java.util.logging Logger Level]
           [org.jaudiotagger.audio AudioFileIO]
           [org.jaudiotagger.tag FieldKey])
  (:require [clojure.java.io :as io]
            [jok.models.utils :as utils]))

(.setLevel (Logger/getLogger "org.jaudiotagger") Level/WARNING)

(defn- extract [file]
  (let [audio-file (AudioFileIO/read (io/as-file file))
        tags (.getTag audio-file)
        header (.getAudioHeader audio-file)]
    (conj {}
      [:artist (.getFirst tags FieldKey/ARTIST)]
      [:album (.getFirst tags FieldKey/ALBUM)]
      [:title (.getFirst tags FieldKey/TITLE)]
      [:duration (.getTrackLength header)])))

(defn correct-filename [file music-dir]
  (let [{:keys [artist album title]} (extract file)
        dir (utils/file-path music-dir
                             (utils/strip-slashes artist)
                             (utils/strip-slashes album))
        new-file (utils/file-path dir
                                  (str (utils/strip-slashes title)
                                       "."
                                       (utils/extension file)))]
    (utils/mkdir-p dir)
    (utils/mv file new-file)))
