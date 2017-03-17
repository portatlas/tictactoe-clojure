(ns tictactoe-clojure.player-spec
  (:require [speclj.core :refer :all]
            [tictactoe-clojure.human-player :as human-player]
            [tictactoe-clojure.computer-player :as computer-player]
            [tictactoe-clojure.player :refer :all]))

(describe "tictactoe-clojure.player"
  (with empty-board [1 2 3 4 5 6 7 8 9])
  (with x-one-move-from-win ["X" "X" "O" "O" "X" 6 7 "O" "O"])

  (describe "Human"
    (it "has an associated piece to place on the board"
      (should= "X" (:piece #tictactoe_clojure.player.Human{:piece "X"})))
    (it "has an method to move"
      (with-redefs [human-player/human-move (fn [_] 1)]
        (should= 1 (move #tictactoe_clojure.player.Human{:piece "X"} @empty-board)))))

  (describe "Computer"
    (it "has an associated piece to place on the board"
      (should= "X" (:piece #tictactoe_clojure.player.Computer{:piece "X"})))
    (it "has an method to move"
      (with-redefs [(computer-player/optimal-move  "X" @x-one-move-from-win 1)]
        (should= 7 (move #tictactoe_clojure.player.Computer{:piece "X"} @x-one-move-from-win)))))

  (describe "create-player"
    (it "returns an instance of a Human player when instructed"
      (should= #tictactoe_clojure.player.Human{:piece "X"}  (create-player "Human" "X")))
    (it "returns an instance of a Computer player when instructed"
      (should= #tictactoe_clojure.player.Computer{:piece "O"}  (create-player "Computer" "O")))))
