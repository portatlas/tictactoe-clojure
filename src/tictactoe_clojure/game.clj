(ns tictactoe-clojure.game
  (:require [tictactoe-clojure.player :as player])  
  (:require [tictactoe-clojure.board :as board])  
  (:require [tictactoe-clojure.console :as console])  
  (:require [tictactoe-clojure.stringify-msg :as stringify-msg])  
  (:require [tictactoe-clojure.rules :as rules]))

(defn play-game 
  [players board]
    (loop [board board
           current-player (first players)]
      (console/display (stringify-msg/whose-turn current-player))  
      (console/display (stringify-msg/transform-to-grid board))
        (if (rules/game-over? board)
          (console/display (stringify-msg/game-result board))
          (recur
            (board/move board (player/move current-player board) (:piece current-player))
            (rules/switch-player players current-player)))))
