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
      (should= "1"
        (with-in-str "1"
          (prompt "Enter position on the board")))))

  (describe "#show-board"
    (it "prints an empty board to the screen"
      (should= "1 | 2 | 3\n---------\n4 | 5 | 6\n---------\n7 | 8 | 9\n"
        (with-out-str
          (show-board [1 2 3 4 5 6 7 8 9]))))
    (it "prints an board with symbols to the screen"
      (should= "X | 2 | O\n---------\n4 | 5 | X\n---------\n7 | 8 | 9\n"
        (with-out-str
          (show-board ["X" 2 "O" 4 5 "X" 7 8 9]))))))
