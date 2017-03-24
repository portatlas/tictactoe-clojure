(ns tictactoe-clojure.core
 (:require [tictactoe-clojure.console :as console])
 (:require [tictactoe-clojure.rules :as rules])
 (:require [tictactoe-clojure.board :as board])
 (:require [tictactoe-clojure.game :as game]))

(defn -main
  []
    (console/display rules/instructions)
    (game/play-game board/new-board "X"))
