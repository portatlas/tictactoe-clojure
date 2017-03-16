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
          (prompt (with-out-str "Enter position on the board")))))))
