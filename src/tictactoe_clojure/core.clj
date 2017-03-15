(ns tictactoe-clojure.core
 (:require [tictactoe-clojure.console :as console])
 (:require [tictactoe-clojure.rules :as rules])
 (:require [tictactoe-clojure.human-player :as human-player])
 (:require [tictactoe-clojure.computer-player :as computer-player])
 (:require [tictactoe-clojure.board :as board])
 (:require [tictactoe-clojure.game-setup :as game-setup])
 (:require [tictactoe-clojure.game :as game]))

(defn -main
  []
    (console/display rules/instructions)
    (let [game-type (game-setup/opponent-type)]
      (cond 
        (= game-type "Human")
          (game/play-game board/new-board "X" 
            (fn [board current-turn]
              (console/display (str current-turn "'s turn"))
              (if (= current-turn "X")
                (board/move board (human-player/human-move board) "X") 
                (board/move board (human-player/human-move board) "O"))))
        (= game-type "Computer") 
          (game/play-game board/new-board "X" 
          	(fn [board current-turn]
                (console/display (str current-turn "'s turn"))
                (if (= current-turn "X")
                  (board/move board (human-player/human-move board) "X") 
                  (do
                    (console/display "Computer's move:")
                    (board/move board (computer-player/optimal-move "O" board 9) "O"))))))))
