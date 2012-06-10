(ns jok.models.pink
  (:require [jok.music-player :as player]))

(defn new-queue []
  [])

(def queue (atom (new-queue)))

(defn enqueue! [song]
  (swap! queue #(conj % song)))

(defn dequeue! []
  (swap! queue #(vec (rest %))))

(defn current-song []
  (first @queue))

(defn play []
  (player/play (:file (current-song))))

(defn play-next []
  (dequeue!)
  (player/play-song (:file (current-song))))

(defn pause []
  (player/pause))

(player/on-finished play-next)
