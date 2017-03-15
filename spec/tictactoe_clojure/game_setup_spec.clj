(ns tictactoe-clojure.game-setup-spec
  (:require [speclj.core :refer :all]
  	        [tictactoe-clojure.console :as console]
            [tictactoe-clojure.game-setup :refer :all]))

(describe "tictactoe-clojure.game-setup"
  (describe "#opponent-type"
  	(it "prompts user to select opponent, if user select 1 return Human"
  	  (with-redefs [console/prompt (fn [_] "1")]
  	    (should= "Human" (opponent-type))))
    (it "prompts user to select opponent, if user select 2 return Computer"
  	  (with-redefs [console/prompt (fn [_] "2")]
  	    (should= "Computer" (opponent-type)))))

  (describe "#opponent-sequence"
    (it "prompts user to select sequence, if user select 1 return Human"
      (with-redefs [console/prompt (fn [_] "1")]
        (should= "Human" (opponent-sequence))))
    (it "prompts user to select opponent, if user select 2 return Computer"
      (with-redefs [console/prompt (fn [_] "2")]
        (should= "Computer" (opponent-sequence))))))
