(ns tictactoe-clojure.player
  (:require [tictactoe-clojure.human-player :as human-player])
  (:require [tictactoe-clojure.computer-player :as computer-player]))

(defprotocol Player
  (piece [this])
  (move [this board]))

(defrecord Human [piece]
  Player
  (piece [this] (:piece this))
  (move [this board]
    (human-player/human-move board)))

(defrecord Computer [piece]
  Player
  (piece [this] (:piece this))
  (move [this board]
    (computer-player/optimal-move (:piece this) board 9)))

(defn create-player [player piece]
  (cond 
    (= player "Human") (Human. piece)
    (= player "Computer") (Computer. piece)))