(ns jok.music-player
  (:import [uk.co.caprica.vlcj.player.headless DefaultHeadlessMediaPlayer]
           [uk.co.caprica.vlcj.player MediaPlayerFactory]))

(def player (.newHeadlessMediaPlayer (MediaPlayerFactory.)))

(defn play
  ([]
     (.play player))
  ([song]
     (let [empty-varargs (make-array String 0)]
       (.playMedia player song empty-varargs))))

(defn pause []
  (.pause player))
