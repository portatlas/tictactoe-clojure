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
      (it "moves to the spot with the neutral score if no winning or blocking position are available"
        (should= 5 (optimal-move "O" ["X" 2 3 4 5 6 7 8 9] 1)))
      (it "blocks a move that would allow the opponent to win"
        (should= 6 (optimal-move "O" @x-1-move-from-win 1)))
      (it "makes a winning move"
        (should= 3 (optimal-move "O" @o-1-move-from-win 5))))))
