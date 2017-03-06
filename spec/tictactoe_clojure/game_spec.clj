(ns tictactoe-clojure.game-spec
  (:require [speclj.core :refer :all]
    [tictactoe-clojure.console :as console]
    [tictactoe-clojure.game :refer :all]))

(describe "tictactoe-clojure.game"
  (with new-board [1 2 3 4 5 6 7 8 9])
  (with instructions "Two players take turns placing a 'X' and 'O' respectively on a 3 x 3 grid. The player who succeeds in placing three of their symbols in a horizontal, vertical or diagonal row wins.\n")
  (with board-displayed "1 | 2 | 3\n---------\n4 | 5 | 6\n---------\n7 | 8 | 9\n")

  (describe "#start-game"
  	(it "starts the game and displays the instructions with a new board"
  	  (should= (str @instructions @board-displayed)
  	  	(with-out-str
          (start-game @new-board)))))

  (describe "#take-move"
    (it "places the position given by user prompt onto the game board"
      (with-redefs [console/prompt (fn [_] "1")]
        (should= ["X" 2 3 4 5 6 7 8 9]
          (take-move @new-board))))))
