(ns tictactoe-clojure.board-spec
  (:require [speclj.core :refer :all]
            [tictactoe-clojure.board :refer :all]))

(describe "tictactoe-clojure.board"
  (with empty-board [1 2 3 4 5 6 7 8 9])
  (with x-on-1-board ["X" 2 3 4 5 6 7 8 9])
  (with played-board ["X" "O" "O" "X" 5 6 7 8 9])

  (describe "new-board"
    (it "returns a vector with a range from 1 to "
      (should= new-board @empty-board )))

  (describe "#board-rows"
    (it "should return the three rows of an empty board"
      (should= '((1 2 3)(4 5 6)(7 8 9)) (board-rows @empty-board)))
    (it "should return the three rows of the board with the corresponding symbols"
      (should= '(("X" "O" "O")("X" 5 6)(7 8 9)) (board-rows @played-board))))

  (describe "board-columns"
    (it "should return the three columns of an empty board"
      (should= '((1 4 7)(2 5 8)(3 6 9)) (board-columns @empty-board)))
    (it "should return the three columns of the board with the corresponding symbols"
      (should= '(("X" "X" 7)("O" 5 8)("O" 6 9)) (board-columns @played-board))))

  (describe "board-diagonals"
    (it "should return the two diagonals of an empty board"
      (should= '((1 5 9)(3 5 7)) (board-diagonals @empty-board)))
    (it "should return the two diagonals of a board with the corresponding symbols"
      (should= '(("X" 5 9)("O" 5 7)) (board-diagonals @played-board))))

  (describe "#valid-slots"
    (it "returns a vector of all the slots for a new board"
      (should= @empty-board (valid-slots new-board)))
    (it "returns a vector of the last 8 spots when the first position is taken"
      (should= [2 3 4 5 6 7 8 9] (valid-slots ["X" 2 3 4 5 6 7 8 9])))
    (it "returns a vector of the first row when slots in the 2nd and 3rd row are taken"
      (should= [1 2 3] (valid-slots [1 2 3 "O" "X" "O" "X" "O" "O"])))
    (it "returns an empty vector if there are no valid slots"
      (should= [] (valid-slots ["X" "O" "X" "O" "O" "X" "X" "X" "O"]))))
  	
  (describe "#move"
    (it "returns a board with the move applied to the first spot with the symbol X"
      (should= @x-on-1-board (move new-board 1 "X")))
    (it "returns a board with the move applied to the last spot with the symbol 0"
      (should= [1 2 3 4 5 6 7 8 "O"] (move new-board 9 "O")))
    (it "returns a board with an X on 1 and an O on 2"
      (should= ["X" "O" 3 4 5 6 7 8 9] (move (move new-board 1 "X") 2 "O"))))

  (describe "#stringify-board-to-grid"
    (it "converts a board vector into a string "
      (should= "1 | 2 | 3\n---------\n4 | 5 | 6\n---------\n7 | 8 | 9\n"
          (stringify-board-to-grid @empty-board)))))
