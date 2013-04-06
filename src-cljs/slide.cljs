(ns slide
  (:require [goog.dom :as gdom]
            swipe 
            interop))

(defn- body-element []
  (first (gdom/getElementsByTagNameAndClass "body")))

(defn- get-saved-scroll []
  (.-otherScroll js/window))

(defn- set-saved-scroll [position]
  (set! (.-otherScroll js/window) position))

(defn- get-body-scroll []
  (.-scrollTop (body-element)))

(defn- set-body-scroll [position]
  (set! (.-scrollTop (body-element)) position))

(defn init []
  (set-saved-scroll 0))

(defn swap-scrolls [index element]
  (let [saved (get-saved-scroll)]
    (set-saved-scroll (get-body-scroll))
    (set-body-scroll saved)))

(defn ready []
  (let [slider (.getElementById js/document "slider")
        swipey (swipe.Swipe. slider (clj->js {:continuous false :callback swap-scrolls}))]
    (init)
    (set! (.-swipey js/window) swipey)
    (.setup swipey)))
