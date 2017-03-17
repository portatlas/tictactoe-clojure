(ns tictactoe-clojure.board-spec
  (:require [speclj.core :refer :all]
            [tictactoe-clojure.board :refer :all]))

(describe "tictactoe-clojure.board"
  (with x-on-1-board ["X" 2 3 4 5 6 7 8 9])
  (with empty-board [1 2 3 4 5 6 7 8 9])

  (context "Board of any size"

    (describe "#valid-slots"
      (it "returns a vector of all the slots for a new board"
        (should= @empty-board (valid-slots (new-board 3))))
      (it "returns a vector of the last 8 spots when the first position is taken"
        (should= [2 3 4 5 6 7 8 9] (valid-slots ["X" 2 3 4 5 6 7 8 9])))
      (it "returns a vector of the first row when slots in the 2nd and 3rd row are taken"
        (should= [1 2 3] (valid-slots [1 2 3 "O" "X" "O" "X" "O" "O"])))
      (it "returns an empty vector if there are no valid slots"
        (should= [] (valid-slots ["X" "O" "X" "O" "O" "X" "X" "X" "O"]))))

    (describe "#move"
      (it "returns a board with the move applied to the first spot with the symbol X"
        (should= @x-on-1-board (move (new-board 3) 1 "X")))
      (it "returns a board with the move applied to the last spot with the symbol O"
        (should= [1 2 3 4 5 6 7 8 "O"] (move (new-board 3) 9 "O")))
      (it "returns a board with an X on 1 and an O on 2"
        (should= ["X" "O" 3 4 5 6 7 8 9] (move (move (new-board 3) 1 "X") 2 "O")))
      (it "returns the board with out the move applied if the move input is out of bounds"
        (should= [1 2 3 4 5 6 7 8 9] (move (new-board 3) 10 "O")))))

  (context "3x3 Board"
    (with empty-board [1 2 3 4 5 6 7 8 9])
    (with played-board ["X" "O" "O" "X" 5 6 7 8 9])
  
    (describe "#new-board"
      (it "returns a vector with a range from 1 to 9"
        (should= @empty-board (new-board 3))))

    (describe "#board-size"
      (it "returns 3 for a 3x3 board"
        (should= 3 (board-size @empty-board))))
  
    (describe "#board-rows"
      (it "should return the three rows of an empty board"
        (should= '((1 2 3)(4 5 6)(7 8 9)) (board-rows @empty-board 3)))
      (it "should return the three rows of the board with the corresponding symbols"
        (should= '(("X" "O" "O")("X" 5 6)(7 8 9)) (board-rows @played-board 3))))
  
    (describe "board-columns"
      (it "should return the three columns of an empty board"
        (should= '((1 4 7)(2 5 8)(3 6 9)) (board-columns @empty-board 3)))
      (it "should return the three columns of the board with the corresponding symbols"
        (should= '(("X" "X" 7)("O" 5 8)("O" 6 9)) (board-columns @played-board 3))))

    (describe "board-diagonals"
      (it "should return the two diagonals of an empty board"
        (should= '((1 5 9)(3 5 7)) (board-diagonals @empty-board 3)))
      (it "should return the two diagonals of a board with the corresponding symbols"
        (should= '(("X" 5 9)("O" 5 7)) (board-diagonals @played-board 3))))
  
    (describe "#stringify-board-to-grid"
      (it "converts a board vector into a string "
        (should= " 1 | 2 | 3 \n-----------\n 4 | 5 | 6 \n-----------\n 7 | 8 | 9 "
          (stringify-board-to-grid @empty-board)))))

  (context "4x4 Board"
    (with empty-board [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16])
    
    (describe "#new-board"
      (it "returns a vector with a range from 1 to 16"
        (should= @empty-board (new-board 4))))

    (describe "#board-rows"
      (it "should return the four rows of an empty board"
        (should= '((1 2 3 4)(5 6 7 8)(9 10 11 12)(13 14 15 16)) (board-rows (new-board 4) 4))))

    (describe "#board-columns"
      (it "should return the four columns of an empty board"
        (should= '((1 5 9 13)(2 6 10 14)(3 7 11 15)(4 8 12 16)) (board-columns (new-board 4) 4))))

    (describe "#board-diagonals"
      (it "should return the two diagonals of an empty board"
        (should= '((1 6 11 16)(4 7 10 13)) (board-diagonals (new-board 4) 4))))

    (describe "#stringify-board-to-grid"
      (it "converts a board vector into a string "
        (should= " 1  | 2  | 3  | 4  \n-------------------\n 5  | 6  | 7  | 8  \n-------------------\n 9  | 10 | 11 | 12 \n-------------------\n 13 | 14 | 15 | 16 "
          (stringify-board-to-grid @empty-board)))))

  (context "5x5 Board"
    (with empty-board [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25])

    (describe "#new-board"
      (it "returns a vector with a range from 1 to 25"
        (should= @empty-board (new-board 5))))

    (describe "#board-rows"
      (it "should return the five rows of an empty board"
        (should= '((1 2 3 4 5)(6 7 8 9 10)(11 12 13 14 15)(16 17 18 19 20)(21 22 23 24 25))
          (board-rows (new-board 5) 5))))

    (describe "#board-columns"
      (it "should return the five rows of an empty board"
        (should= '((1 6 11 16 21)(2 7 12 17 22)(3 8 13 18 23)(4 9 14 19 24)(5 10 15 20 25))
          (board-columns (new-board 5) 5))))    

    (describe "#board-diagonals"
      (it "should return the two diagonals of an empty board"
        (should= '((1 7 13 19 25)(5 9 13 17 21)) (board-diagonals (new-board 5) 5))))

    (describe "#stringify-board-to-grid"
      (it "converts a board vector into a string "
        (should= " 1  | 2  | 3  | 4  | 5  \n------------------------\n 6  | 7  | 8  | 9  | 10 \n------------------------\n 11 | 12 | 13 | 14 | 15 \n------------------------\n 16 | 17 | 18 | 19 | 20 \n------------------------\n 21 | 22 | 23 | 24 | 25 "
          (stringify-board-to-grid @empty-board))))))
