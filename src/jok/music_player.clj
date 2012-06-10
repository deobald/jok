(ns jok.music-player
  (:require [jok.config :as config])
  (:import [uk.co.caprica.vlcj.player.headless DefaultHeadlessMediaPlayer]
           [uk.co.caprica.vlcj.player MediaPlayerFactory]))

(def player (.newHeadlessMediaPlayer (MediaPlayerFactory.)))

(defn media-options []
  (if (config/stream?)
    (into-array [":sout=#rtp{dst=230.0.0.1,port=5555,mux=ts}"])
    (make-array String 0)))

(defn play [song]
  (if (.isPlayable player)
    (.play player)
    (.playMedia player song (media-options))))

(defn pause []
  (.pause player))
