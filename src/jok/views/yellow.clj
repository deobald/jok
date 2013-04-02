(ns jok.views.yellow
  (:require [hiccup.form :as form]
            [hiccup.util :as hutil]
            [jok.files :as jfiles]
            [jok.views.layou :as layou]
            [jok.models.yellow :as model])
  (:use [hiccup.core :only [html]]))

(defn- search-bar []
  [:tr
   [:td {:colspan 4}
    [:input#search {:type "text"}]]])

(defn- song-row [song]
  [:tr.song-row
   [:td (:title song)]
   [:td (:artist song)]
   [:td (:album song)]
   [:td
    [:div.enqueue-button
     (form/form-to [:post "/yellow/enqueue"]
                   (form/hidden-field "file" (:file song))
                   (form/submit-button "+"))]
    [:div.download-link
     [:a {:href (hutil/url "/music"
                           (jfiles/url-encode-parts (:relative-path song)))} "⇣"]]]])

(defn- song-table [songs]
  [:table
   [:tr
    [:th "song"]
    [:th "artist"]
    [:th "album"]
    [:th "actions"]]
   (map song-row songs)])

(defn index [request songs]
  (layou/t request "yellow" "white" "pink"
           (html (search-bar)
                 (song-table songs))))
