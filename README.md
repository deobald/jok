= jok

office jukebox

== hacking

# install [leiningen](https://github.com/technomancy/leiningen)
## install java
## install gpg
# `lein deps`
# `lein ring server`
## sanity-check "-Djna.library.path" in :jvm-opts of project.clj

There is still old code in these directories:

* resources/public/css
* resources/public/js
* src/jukebox_player
* src/jukebox_web


== running

Try `lein ring war` and run it in a container for now. We need a self-supporting binary, though.


== license

Since we're using vlcj, we're stuck with GPL 3.0+. Sorry kids.
