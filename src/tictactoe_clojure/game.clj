(ns tictactoe-clojure.game
  (:require [tictactoe-clojure.board :as board])	
  (:require [tictactoe-clojure.console :as console])	
  (:require [tictactoe-clojure.rules :as rules]))

(defn- to-int
  [string]
    (Integer. (re-find #"[0-9]*" string)))

(defn take-move
	[board]
    (let [user-move (to-int (console/prompt "Enter your position"))]
      (if (rules/valid-move? user-move board)
	      (board/move board user-move "X")
        ("Invalid Move"))))

(defn start-game
  [board]
    (console/display rules/instructions)
    (console/show-board board))
