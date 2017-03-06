(ns tictactoe-clojure.core
 (:require [tictactoe-clojure.console :as console])	
 (:require [tictactoe-clojure.board :as board])
 (:require [tictactoe-clojure.game :as game]))

(defn -main
  []
    (game/start-game board/new-board)
    (console/display (game/take-move board/new-board)))
