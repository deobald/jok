(defproject
  jukebox "1.0.0-SNAPSHOT"
  :description "Jokbox"
  :repositories {"local" ~(str (.toURI (java.io.File. "maven_repository")))}
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [org.jaudiotagger/jaudiotagger "2.0.1"]
                 [compojure "1.1.0"]
                 [hiccup "1.0.0"]
                 [ring/ring-jetty-adapter "1.1.0"]

                 ;; [org.clojars.ghoseb/cron4j "2.2.1"]
                 ;; [fleetdb "0.3.1"]
                 ;; [corroborate "0.2.0"]
                 ;; [ring-json-params "0.1.3"]
                 ;; [ring-cors "0.0.2-SNAPSHOT"]
                 ;; [clj-json "0.4.3"]
                 ;; [org.mindrot/jbcrypt "0.3m"]
                 ;; [jaad "0.8.3"]
                 ;; [jl "1.0.1"]
                 ;; [jogg "0.0.7"]
                 ;; [jorbis "0.0.15"]
                 ;; [mp3spi "1.9.5"]
                 ;; [tritonus_share "0.3.6"]
                 ;; [vorbisspi "1.0.3"]
                 ;; [fs "1.1.2"]
                 ]
  :dev-dependencies [[lein-ring "0.7.1"]
                     ;; [speclj "1.5.2"]
                     ;; [speclj-growl "1.0.0-SNAPSHOT"]
                     ]
  :main jok.core
  :ring {:handler jok.core/app}
  ;;  :test-path "spec/"
  )
