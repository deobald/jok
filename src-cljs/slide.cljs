(ns slide
  (:require [goog.dom :as gdom]
            swipe 
            interop))

(defn saved-scroll []
  (.-otherScroll js/window))

(defn init []
  (set! (.-bodyElement js/window) (first (gdom/getElementsByTagNameAndClass "body")))
  (set! (saved-scroll) 0))

(defn current-scroll []
  (-> js/window .-bodyElement .-scrollTop))

(defn save-scroll []
  (set! (saved-scroll) (current-scroll)))

(defn scroll-to [position]
  (set! (current-scroll) position))

(defn swap-scrolls [index element]
  (let [scroll-back-to (saved-scroll)]
    (save-scroll)
    (scroll-to scroll-back-to)))

(defn ready []
  (let [slider (.getElementById js/document "slider")
        swipey (swipe.Swipe. slider (clj->js {:continuous false :callback swap-scrolls}))]
    (init)
    (set! (.-swipey js/window) swipey)
    (.setup swipey)))
