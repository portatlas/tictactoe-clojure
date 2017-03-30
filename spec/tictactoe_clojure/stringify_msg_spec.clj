(ns tictactoe-clojure.stringify-msg-spec
  (:require [speclj.core :refer :all]
  	    [tictactoe-clojure.player :as player]
            [tictactoe-clojure.rules :as rules]
            [tictactoe-clojure.stringify-msg :refer :all]
            [tictactoe-clojure.symbol :refer :all]))

(describe "tictactoe-clojure.stringify-msg"
  (with empty-3x3-board [1 2 3 4 5 6 7 8 9])
  (with board-with-draw [X O X O O X X X O])
  (with x-win-1st-row-board [X X X O O 6 7 8 9])
  (with o-win-2nd-row-board [X 2 X O O O 7 8 9])
  (with empty-4x4-board [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16])
  (with empty-5x5-board [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25])
  (with human-x (player/create-player "Human" X))

  (describe "#invalid-input"
    (it "returns a string Invalid Input. Please try again."
      (should= "Invalid Input. Please try again." invalid-input)))
  
  (describe "#whose-turn"
    (it "returns a string Player move with the current player"
      (should= "Player X move\n" (whose-turn @human-x))))

  (describe "transform-to-grid"
    (it "converts a 3x3 board vector into a string"
      (should= " 1 | 2 | 3 \n-----------\n 4 | 5 | 6 \n-----------\n 7 | 8 | 9 "
        (transform-to-grid @empty-3x3-board)))
    (it "converts a 4x4 board vector into a string "
      (should= " 1  | 2  | 3  | 4  \n-------------------\n 5  | 6  | 7  | 8  \n-------------------\n 9  | 10 | 11 | 12 \n-------------------\n 13 | 14 | 15 | 16 "
        (transform-to-grid @empty-4x4-board)))
    (it "converts a 5x5 board vector into a string "
      (should= " 1  | 2  | 3  | 4  | 5  \n------------------------\n 6  | 7  | 8  | 9  | 10 \n------------------------\n 11 | 12 | 13 | 14 | 15 \n------------------------\n 16 | 17 | 18 | 19 | 20 \n------------------------\n 21 | 22 | 23 | 24 | 25 "
        (transform-to-grid @empty-5x5-board))))

  (describe "game-result"
    (it "returns Its a draw if the result is a draw"
      (should= "Its a draw" (game-result @board-with-draw)))
    (it "returns who the winner is if X won"
      (should= "Player X wins" (game-result @x-win-1st-row-board)))
    (it "returns who the winner is if O won"
      (should= "Player O wins" (game-result @o-win-2nd-row-board)))))
