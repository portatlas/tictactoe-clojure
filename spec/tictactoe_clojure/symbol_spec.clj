(ns tictactoe-clojure.symbol-spec
  (:require [speclj.core :refer :all]
            [tictactoe-clojure.symbol :refer :all]))

(describe "tictactoe-clojure.symbol"
  (describe "X"
    (it "returns X as a string"
      (should= "X" X)))
  (describe "O"
    (it "returns O as a string"
      (should= "O" O))))