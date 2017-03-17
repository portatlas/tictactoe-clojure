(ns tictactoe-clojure.game-setup-spec
  (:require [speclj.core :refer :all]
            [tictactoe-clojure.game-setup :refer :all]
            [tictactoe-clojure.console :as console]))

(describe "tictactoe-clojure.game-setup"
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
      (should= [#tictactoe_clojure.player.Human{:piece "X"} 
                #tictactoe_clojure.player.Human{:piece "O"}]
                (matchup-opponents ["Human" "Human"])))
    (it "returns a vector with a Human Player and Computer Player Class"
      (should= [#tictactoe_clojure.player.Human{:piece "X"} 
                #tictactoe_clojure.player.Computer{:piece "O"}]
                (matchup-opponents ["Human" "Computer"])))))
