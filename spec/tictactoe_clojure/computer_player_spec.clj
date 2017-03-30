(ns tictactoe-clojure.computer-player-spec
  (:require [speclj.core :refer :all]
            [tictactoe-clojure.board :as board]
            [tictactoe-clojure.computer-player :refer :all]
            [tictactoe-clojure.symbol :refer :all]))

(describe "tictactoe-clojure.computer-player"
  (context "3x3 Board"
    (describe "#optimal-move"
      (it "makes a winning move for X"
        (should= 1 (optimal-move X [1 X X O O 6 7 8 9])))
      (it "makes a blocking move for X"
        (should= 1 (optimal-move X [1 O O 4 X 6 X 8 X])))
      (it "makes a winning move for O"
        (should= 9 (optimal-move O [1 X X 4 X 6 O O 9])))
      (it "makes a blocking move for O"
        (should= 9 (optimal-move O [X O O O 5 X X X 9])))))

  (context "4x4 Board"
    (describe "#optimal-move"
      (it "makes a winning move for X"
        (should= 1 (optimal-move X [1 X X X O O O 8 9 10 11 12 13 14 15 16])))
      (it "makes a blocking move for X"
        (should= 1 (optimal-move X [1 O O O X 6 X 8 X 10 11 X 13 14 15 16])))
      (it "makes a winning move for O"
        (should= 6 (optimal-move O [1 X X 4 O 6 O O 9 10 11 X 13 14 15 16])))
      (it "makes a blocking move for O"
        (should= 5 (optimal-move O [X O O O 5 X X X 9 10 11 12 X 14 15 16])))))

  (context "5x5 Board"
    (describe "#optimal-move"
      (it "makes a winning move for X"
        (should= 1 (optimal-move X [1 X X X X O O O O 10 11 12 X 14 15 O 17 18 X 20 21 O 23 24 25])))
      (it "makes a blocking move for X"
        (should= 1 (optimal-move X [1 O O O X O X O X 10 11 X 13 O 15 16 X 18 19 20 21 22 23 24 25])))
      (it "makes a winning move for O"
        (should= 6 (optimal-move O [X X X 4 O 6 O O O O 11 X 13 14 15 X 17 18 X 20 21 X 23 24 25])))
      (it "makes a blocking move for O"
        (should= 10 (optimal-move O [X O O O 5 X X X X 10 11 12 X 14 15 16 17 O 19 20 O 22 O 24 25]))))))
