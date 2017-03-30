(ns tictactoe-clojure.player-spec
  (:require [speclj.core :refer :all]
            [tictactoe-clojure.human-player :as human-player]
            [tictactoe-clojure.computer-player :as computer-player]
            [tictactoe-clojure.player :refer :all]
            [tictactoe-clojure.symbol :refer :all]))

(describe "tictactoe-clojure.player"
  (with empty-board [1 2 3 4 5 6 7 8 9])
  (with x-one-move-from-win [X X 3 O X 6 7 O O])
  (with human-x (create-player "Human" X))
  (with computer-o (create-player "Computer" O))

  (describe "Human"
    (it "has an associated piece to place on the board"
      (should= X (:piece @human-x)))
    (it "has an method to move"
      (with-redefs [human-player/human-move (fn [_] 1)]
        (should= 1 (move @human-x @empty-board)))))

  (describe "Computer"
    (it "has an associated piece to place on the board"
      (should= O (:piece @computer-o)))
    (it "has an method to move"
      (with-redefs [(computer-player/optimal-move  X @o-one-move-from-win)]
        (should= 3 (move @computer-o @x-one-move-from-win)))))

  (describe "create-player"
    (it "returns an instance of a Human player"
      (should= @human-x  (create-player "Human" X)))
    (it "returns an instance of a Computer player"
      (should= @computer-o  (create-player "Computer" O)))))
