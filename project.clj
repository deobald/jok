(defproject
  jukebox "1.0.0-SNAPSHOT"
  :description "Jokbox"

  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.clojure/tools.cli "0.2.1"]
                 [org.jaudiotagger/jaudiotagger "2.0.1"]
                 [compojure "1.1.0"]
                 [hiccup "1.0.0"]
                 [ring/ring-jetty-adapter "1.1.0"]
                 [uk.co.caprica/vlcj "2.2.0"]]

  :plugins [[lein-ring "0.8.2"]
            [lein-cljsbuild "0.3.0"]]

  :main jok.core
  :ring {:handler jok.core/app}

  :cljsbuild {:builds
              ;; TODO: create dev/production builds
              [{:source-paths ["src-cljs"]
                :compiler {:output-to "resources/public/js/jok.js"
                           :optimizations :whitespace
                           :pretty-print true
                           :libs ["resources/public/js/v"]}}]
              :repl-listen-port
              9000
              :repl-launch-commands
              {"x" ["firefox" "-jsconsole" "http://localhost:3000"]}}

  ;; TODO: this is a huge sadness. fix in a cross-platform way.
  :jvm-opts ["-Djna.library.path=/Applications/VLC.app/Contents/MacOS/lib"])
