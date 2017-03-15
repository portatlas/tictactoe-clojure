(ns tictactoe-clojure.game-spec
  (:require [speclj.core :refer :all]
            [tictactoe-clojure.human-player :as human-player]
            [tictactoe-clojure.computer-player :as computer-player]
            [tictactoe-clojure.console :as console]
            [tictactoe-clojure.board :as board]
            [tictactoe-clojure.game :refer :all]))

(describe "tictactoe-clojure.game"
  (with new-board [1 2 3 4 5 6 7 8 9])
  (with gametype (defn human-vs-computer [board current-turn]
                   (console/display (str current-turn "'s turn"))
                   (if (= current-turn "X")
                     (board/move board (human-player/human-move board) "X") 
                     (do
                       (console/display "Computer's move:")
                       (board/move board (computer-player/optimal-move "O" board 9) "O")))))
  
  (describe "#play-game"
    (it "loops through a game where the human enters 1, 2, 4 and loses to the computer"
      (with-redefs [human-player/human-move (fn [_] (with-in-str "1\n2\n4\n"))
                    console/display (fn [_] "Player O wins")]
          (should= "Player O wins" (play-game @new-board "X" @gametype))))))
