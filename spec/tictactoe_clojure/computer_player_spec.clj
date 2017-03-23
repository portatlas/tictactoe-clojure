(ns tictactoe-clojure.computer-player-spec
  (:require [speclj.core :refer :all]
            [tictactoe-clojure.board :as board]
            [tictactoe-clojure.computer-player :refer :all]))

(describe "tictactoe-clojure.computer-player"
  (context "3x3 Board"
    (with two-move-board ["O" 2 3 "X" 5 6 7 8 9])
    (with x-1-move-from-win ["O" 2 3 "X" "X" 6 7 8 9])
    (with o-1-move-from-win ["O" "O" 3 4 5 "X" 7 8 "X"])

    (describe "#optimal-move"
      (it "returns the index of the move with the neutral score if no winning or blocking position available"
        (should= 5 (optimal-move "O" ["X" 2 3 4 5 6 7 8 9] 1)))
      (it "returns the index of a move that blocks the opponent from winning"
        (should= 6 (optimal-move "O" @x-1-move-from-win 1)))
      (it "returns the index of the move that makes a winning move"
        (should= 3 (optimal-move "O" @o-1-move-from-win 5)))))

  (context "4x4 Board"
    (with empty-board [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16])

    (describe "#optimal-move"
      (it "returns the index of the move on a preferred position"
        (should= 1 (optimal-move "X" @empty-board 16)))))

  (context "5x5 Board"
    (with empty-board [1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25])
    (with o-close-to-win-board ["O" "O" "O" 4 5 "X" 7 8 9 10 11 12 "O" 14 15 16 17 18 19 20 "X" 22 23 24 25])

    (describe "#optimal-move"
      (it "returns the index of the move on a preferred position"
        (should= 1 (optimal-move "X" @empty-board 25)))
      (it "returns the index of the move that blocks opponent that is close to winning"
        (should= 4 (optimal-move "X" @o-close-to-win-board 25))))))
