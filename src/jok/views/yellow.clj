(ns jok.views.yellow
  (:require [hiccup.form :as form]
            [hiccup.util :as hutil]
            [jok.files :as jfiles]
            [jok.views.layou :as layou]
            [jok.models.yellow :as model])
  (:use [hiccup.core :only [html]]))

(defn- search-bar []
  [:input#search {:type "text"}])

(defn- upload-button []
  [:form {:action "/white/upload-multiple" :method "post" :enctype "multipart/form-data"}
   [:input {:name "file" :type "file" :size "20" :multiple "multiple"}]
   [:input {:type "submit" :name "submit" :value "submit"}]])

(defn- top-bar []
  [:div
   [:div.search {:colspan 4 :style "float: left;"}
    (search-bar)]
   [:div.upload
    (upload-button)]])

(defn- song-header-row []
  [:tr
   [:th.song "song"]
   [:th.artist "artist"]
   [:th.album "album"]
   [:th.enqueue "actions"]
   [:th.download "actions"]])

(defn- song-row [song]
  [:tr.song-row
   [:td.song (:title song)]
   [:td.artist (:artist song)]
   [:td.album (:album song)]
   [:td.enqueue
    [:div.enqueue-button
     (form/form-to [:post "/yellow/enqueue"]
                   (form/hidden-field "file" (:file song))
                   (form/submit-button "+"))]]
   [:td.download
    [:div.download-link
     [:a {:href (hutil/url "/music"
                           (jfiles/url-encode-parts (:relative-path song)))} "⇣"]]]])

(defn- song-table [songs]
  [:table#songs
   ;;   (song-header-row)
   (map song-row songs)])

(defn page [songs]
  (html (top-bar)
        (song-table songs)))
