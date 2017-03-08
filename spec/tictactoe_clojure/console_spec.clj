(ns tictactoe-clojure.console-spec
  (:require [speclj.core :refer :all]
    [tictactoe-clojure.console :refer :all]))

(describe "tictactoe-clojure.console"
  (describe "#display"
    (it "print message to the screen"
      (should= "Welcome to Tic Tac Toe\n"
        (with-out-str
          (display "Welcome to Tic Tac Toe")))))

  (describe "#prompt"
    (it "prints the message to the screen and ask user for input"
      (should= "0"
        (with-in-str "0"
          (prompt "Enter position on the board")))))

  (describe "#show-board"
    (it "prints an empty board to the screen"
      (should= "0 | 1 | 2\n---------\n3 | 4 | 5\n---------\n6 | 7 | 8\n"
        (with-out-str
          (show-board [0 1 2 3 4 5 6 7 8]))))
    (it "prints an board with symbols to the screen"
      (should= "X | 1 | O\n---------\n3 | 4 | X\n---------\n6 | 7 | 8\n"
        (with-out-str
          (show-board ["X" 1 "O" 3 4 "X" 6 7 8]))))))
