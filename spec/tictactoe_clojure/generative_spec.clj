(ns tictactoe-clojure.generative-spec
  (:require [speclj.core :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [tictactoe-clojure.board :as board]
            [tictactoe-clojure.computer-player :as computer-player]
            [tictactoe-clojure.game-setup :as game-setup]
            [tictactoe-clojure.rules :as rules]
            [tictactoe-clojure.game :as game]
            [tictactoe-clojure.symbol :refer :all]))

(defn- move-x-times
  [board times-to-moves max-spaces]
    (loop [board board
           move 0]
      (if (= times-to-moves move)
        board
        (recur
          (board/move board (first (take 1 (gen/sample (gen/choose 1 max-spaces)))) (rules/whose-turn board))
          (inc move)))))

(describe "Generative Tests"
  (context "Computer Player never loses in a"
    (with vs-type ["Computer" "Computer"])
    (with match (game-setup/matchup-opponents @vs-type))

    (it "3x3 board"
      (should-contain "Its a draw"
        (with-out-str (game/play-game @match (board/new-board 3)))))
    (it "4x4 board"
      (should-contain "Its a draw"
        (with-out-str (game/play-game @match (board/new-board 4)))))
    (it "5x5 board"
      (should-contain "Its a draw"
        (with-out-str (game/play-game @match (board/new-board 5))))))

  (context "Computer Player makes a move in 10 secs or less when 13 slots are available in a"
    (with board4x4-with-13-spaces (move-x-times (board/new-board 4) 3 16))
    (with board5x5-with13-spaces (move-x-times (board/new-board 5) 12 25))

    (it "4x4 Board"
        (should= true (<= (with-out-str (time (computer-player/optimal-move O @board4x4-with-13-spaces)) 10000))))
    (it "5x5 Board"
        (should= true (<= (with-out-str (time (computer-player/optimal-move O @board5x5-with13-spaces)) 10000))))))
