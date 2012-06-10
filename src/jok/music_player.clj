(ns jok.music-player
  (:require [jok.config :as config])
  (:import [uk.co.caprica.vlcj.player.headless DefaultHeadlessMediaPlayer]
           [uk.co.caprica.vlcj.player
            MediaPlayerFactory
            MediaPlayerEventAdapter]))

(def player (.newHeadlessMediaPlayer (MediaPlayerFactory.)))

(defn- song? []
  (.isPlayable player))

(defn- media-options []
  (if (config/stream?)
    (into-array [":sout=#rtp{dst=230.0.0.1,port=5555,mux=ts}"])
    (make-array String 0)))

(defn unpause []
  (.play player))

(defn play-song [song]
  (.playMedia player song (media-options)))

(defn play [song]
  (if (song?)
    (unpause)
    (play-song song)))

(defn pause []
  (.pause player))

(defn on-finished [callback]
  (.addMediaPlayerEventListener player
                                (proxy [MediaPlayerEventAdapter] []
                                  (finished [player]
                                    (callback)))))
