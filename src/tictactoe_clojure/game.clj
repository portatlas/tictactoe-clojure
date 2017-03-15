(ns tictactoe-clojure.game
  (:require [tictactoe-clojure.game-setup :as game-setup])
  (:require [tictactoe-clojure.board :as board])  
  (:require [tictactoe-clojure.human-player :as human-player])
  (:require [tictactoe-clojure.computer-player :as computer-player])
  (:require [tictactoe-clojure.console :as console])  
  (:require [tictactoe-clojure.rules :as rules]))

(defn play-game 
  [board turn opponent-type]
    (loop [board board
           turn turn]
      (console/display (board/stringify-board-to-grid board))
        (if (rules/game-over? board)
          (console/display (rules/game-result board))
          (recur
            (opponent-type board turn)
            (rules/switch-turn turn)))))
