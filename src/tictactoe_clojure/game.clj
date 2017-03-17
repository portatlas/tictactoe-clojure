(ns tictactoe-clojure.game
  (:require [tictactoe-clojure.player :as player])  
  (:require [tictactoe-clojure.board :as board])  
  (:require [tictactoe-clojure.console :as console])  
  (:require [tictactoe-clojure.rules :as rules]))

(defn play-game 
  [players board]
    (loop [board board
           current-player (first players)]
      (console/display (str "Player " (:piece current-player) " move\n"))    
      (console/display (board/stringify-board-to-grid board))
        (if (rules/game-over? board)
          (console/display (rules/game-result board))
          (recur
            (board/move board (player/move current-player board) (:piece current-player))
            (rules/switch-player players current-player)))))
