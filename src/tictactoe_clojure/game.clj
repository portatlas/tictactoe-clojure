(ns tictactoe-clojure.game
  (:require [tictactoe-clojure.board :as board])  
  (:require [tictactoe-clojure.human-player :as human-player])
  (:require [tictactoe-clojure.computer-player :as computer-player])
  (:require [tictactoe-clojure.console :as console])  
  (:require [tictactoe-clojure.rules :as rules]))

(defn- take-two-moves
  [board current-turn]
    (let [board board]
      (console/display (str current-turn "'s turn"))
      (if (= current-turn "X")
        (board/move board (human-player/human-move board) "X") 
        (do
          (console/display "Computer's move:")
          (board/move board (computer-player/optimal-move "O" board 9) "O")))))

(defn play-game 
  [board turn]
    (loop [board board
          turn turn]
      (console/display (board/stringify-board-to-grid board))
        (if (rules/game-over? board)
          (console/display (rules/game-result board))
          (recur
            (take-two-moves board turn)
            (rules/switch-turn turn)))))
