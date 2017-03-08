(ns tictactoe-clojure.board-spec
  (:require [speclj.core :refer :all]
	  [tictactoe-clojure.board :refer :all]))

(describe "tictactoe-clojure.board"
  (with empty-board [0 1 2 3 4 5 6 7 8])
  (with x-on-0-board ["X" 1 2 3 4 5 6 7 8])
  (with played-board ["X" "O" "O" "X" 4 5 6 7 8])

  (describe "new-board"
    (it "returns a vector with a range from 0 to 8"
      (should= new-board @empty-board )))

  (describe "#board-rows"
    (it "should return the three rows of an empty board"
      (should= '((0 1 2)(3 4 5)(6 7 8)) (board-rows @empty-board)))
	  (it "should return the three rows of the board with the corresponding symbols"
	    (should= '(("X" "O" "O")("X" 4 5)(6 7 8)) (board-rows @played-board))))

  (describe "board-columns"
	  (it "should return the three columns of an empty board"
	    (should= '((0 3 6)(1 4 7)(2 5 8)) (board-columns @empty-board)))
    (it "should return the three columns of the board with the corresponding symbols"
      (should= '(("X" "X" 6)("O" 4 7)("O" 5 8)) (board-columns @played-board))))

  (describe "board-diagonals"
    (it "should return the two diagonals of an empty board"
      (should= '((0 4 8)(2 4 6)) (board-diagonals @empty-board)))
    (it "should return the two diagonals of a board with the corresponding symbols"
      (should= '(("X" 4 8)("O" 4 6)) (board-diagonals @played-board))))

  (describe "#valid-slots"
    (it "returns a vector of all the slots for a new board"
      (should= @empty-board (valid-slots new-board)))
    (it "returns a vector of the last 8 spots when the first position is taken"
      (should= [1 2 3 4 5 6 7 8] (valid-slots ["X" 1 2 3 4 5 6 7 8])))
    (it "returns a vector of the first row when slots in the 2nd and 3rd row are taken"
      (should= [0 1 2] (valid-slots [0 1 2 "O" "X" "O" "X" "O" "O"])))
    (it "returns an empty vector if there are no valid slots"
	    (should= [] (valid-slots ["X" "O" "X" "O" "O" "X" "X" "X" "O"]))))
  	
  (describe "#move"
    (it "returns a board with the move applied with the correct symbol"
      (should= @x-on-0-board (move new-board 0 "X")))))
