(ns tictactoe-clojure.core
 (:require [tictactoe-clojure.board :as board])
 (:require [tictactoe-clojure.game :as game]))

(defn -main
  []
    (game/play-game board/new-board "O"))
