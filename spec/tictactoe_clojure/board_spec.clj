(ns tictactoe-clojure.board-spec
  (:require [speclj.core :refer :all]
            [tictactoe-clojure.board :refer :all]))

(describe "new board"
  (it "is a vector with a range from 0 to 8"
    (should= new-board [0 1 2 3 4 5 6 7 8] )))
