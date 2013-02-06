jok
===

office jukebox


hacking
-------

prerequisites:

1. install [leiningen](https://github.com/technomancy/leiningen)
2. install java
3. install gpg

running in dev mode:

1. `lein deps`
2. `lein ring server`
3. sanity-check "-Djna.library.path" in :jvm-opts of project.clj

old code:

* resources/public/css
* resources/public/js
* src/jukebox_player
* src/jukebox_web


running
-------

Try `lein ring war` and run it in a container for now. We need a self-supporting binary, though.


license
-------

Since we're using vlcj, we're stuck with GPL 3.0+. Sorry kids.
