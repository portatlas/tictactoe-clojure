(ns tictactoe-clojure.game-setup-spec
  (:require [speclj.core :refer :all]
            [tictactoe-clojure.game-setup :refer :all]
            [tictactoe-clojure.console :as console]
            [tictactoe-clojure.player :as player]))

(describe "tictactoe-clojure.game-setup"
  (with human-player-X (player/create-player "Human" "X"))
  (with human-player-O (player/create-player "Human" "O"))
  (with computer-player-O (player/create-player "Computer" "O"))

  (describe "#get-board-size"
    (it "returns 3 if the user selects a 3x3 board"
      (with-out-str (with-in-str "3"
        (should= 3 (get-board-size)))))
    (it "returns 4 if the user selects a 4x4 board"
      (with-out-str (with-in-str "4"
        (should= 4 (get-board-size))))))

  (describe "#opponent-type"
    (it "prompts user to select opponent, if user select 1 return a vector of Human Human"
      (with-redefs [console/prompt (fn [_] "1")]
        (should= ["Human" "Human"] (opponent-type))))
    (it "prompt user for who goes first, if user select 1 return a vector of Human Computer"
      (with-out-str (with-in-str "2\n1\n"
        (should= ["Human" "Computer"] (opponent-type)))))
    (it "prompts user for who goes first, if user select 2 return a vector of Computer Human"
      (with-out-str (with-in-str "2\n2\n"
        (should= ["Computer" "Human"] (opponent-type))))))

  (describe "#matchup-opponents"
    (it "returns a vector with a Human Player and Human Player Class"
      (should= [@human-player-X @human-player-O]
                (matchup-opponents ["Human" "Human"])))
    (it "returns a vector with a Human Player and Computer Player Class"
      (should= [@human-player-X @computer-player-O]
                (matchup-opponents ["Human" "Computer"])))))
