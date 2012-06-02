(ns jok.views.yellow
  (:require [jok.views.layou :as layou]
            [jok.models.yellow :as model]))

(defn- song-row [song]
  [:tr
   [:td (:title song)]
   [:td (:artist song)]
   [:td (:album song)]])

(defn- song-table [songs] 
  [:table
   [:tr
    [:th "song"]
    [:th "artist"]
    [:th "album"]]
   (map song-row songs)])

(defn index [request songs]
  (layou/t request "yellow" (song-table songs)))
