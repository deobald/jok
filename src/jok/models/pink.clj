(ns jok.models.pink
  (:require [jok.music-player :as player]))

(defn new-queue []
  (clojure.lang.PersistentQueue/EMPTY))

(def queue (atom (new-queue)))

(defn enqueue! [song]
  (swap! queue #(conj % song)))

(defn current-song []
  (peek @queue))

(defn play []
  (player/play (:file (current-song))))

(defn pause []
 (player/pause))