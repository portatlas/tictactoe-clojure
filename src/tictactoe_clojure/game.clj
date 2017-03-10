(ns tictactoe-clojure.game
  (:require [tictactoe-clojure.board :as board])	
  (:require [tictactoe-clojure.human-player :as human-player])
  (:require [tictactoe-clojure.computer-player :as computer-player])
  (:require [tictactoe-clojure.console :as console])	
  (:require [tictactoe-clojure.rules :as rules]))

(defn take-two-moves
  [board current-turn]
    (let [board board]
      (console/display (str current-turn "'s turn"))
      (if (= current-turn "O")
        (board/move board (human-player/human-move board) "O") 
        (board/move board (computer-player/optimal-move board 9) "X"))))

(defn show-result
  [played-board]
    (if (rules/draw? played-board)
      (console/display "Its a draw")
      (console/display (str (rules/winner played-board) " wins!"))))

(defn play-game 
  [board]
    (loop [board board player-sequence player-sequence]
      (console/display (board/stringify-board-to-grid board))
        (if (rules/game-over? board)
          (show-result board)
          (recur
            (take-two-moves board turn)
            (rules/whose-turn turn )))))
