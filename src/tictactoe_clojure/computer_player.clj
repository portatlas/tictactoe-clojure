(ns tictactoe-clojure.computer-player
  (:use clojure.set)
  (:require [tictactoe-clojure.console :as console])
  (:require [tictactoe-clojure.board :as board])
  (:require [tictactoe-clojure.rules :as rules]))

(def preferred-positions-on-4x4 #{1 4 13 16})
(def preferred-positions-on-5x5 #{1 3 5 11 13 21 25})
(def initial-alpha -100)
(def initial-beta 100)

(defn- assign-initial-score
  [is-max-player]
    (if is-max-player
      [0 -100]
      [0 100]))

(defn- winning-score
  [board depth max-player]
    (if (= max-player (rules/winner board))
      (+ 10 depth)
      (- -10 depth)))

(defn- gameover-move-score
  [board depth max-player]
    (if (rules/winner? board)
      [0 (winning-score board depth max-player)]
      [0 0]))

(defn- find-max-min
  [eval-comparator max-player best-position valued-position move-position]
    (if (eval-comparator (second best-position) (second valued-position))
      (vector move-position (second valued-position))
      best-position))

(defn- player-best-move-score
  [is-max-player best-position valued-position move-position]
    (if is-max-player
      (find-max-min < is-max-player best-position valued-position move-position)
      (find-max-min > is-max-player best-position valued-position move-position)))

(defn- recalculate-alpha 
  [is-max-player best-score alpha]
    (if is-max-player
      (max (second best-score) alpha)
      alpha))

(defn- recalculate-beta
  [is-max-player? move-score beta]
    (if (not is-max-player?)
      (min (second move-score) beta)
      beta))

(defn- prune?
  [alpha beta]
    (>= alpha beta))

(declare mem-minimax)

(defn- end-branch-traversal? 
  [valid-slots alpha beta]
    (or
      (prune? alpha beta)
      (= 0 (count valid-slots))))

(defn- move-on-preferred-position
  [board valued-moves]
  (first
    (clojure.set/intersection
      (set (board/valid-slots board))
      valued-moves)))

(defn- check-seq-for-dups [seq]
  (for [[id freq] (frequencies seq)
        :when (> freq 1)]
   id))

(defn- index-of-win-combo-with-potential-winner
  [board player]
  (.indexOf
    (map #(check-seq-for-dups %) (rules/winning-combinations board))
    (list (rules/switch-symbol player))))

(defn- board-with-potential-winner
  [board player]
  (let [sec-board-w-dup (index-of-win-combo-with-potential-winner board player)]
      (if (not= sec-board-w-dup -1)
        sec-board-w-dup
        nil)))

(defn- opponent-close-to-win?
  [board player]
  (if (not= nil (board-with-potential-winner board player))
    true
    false))

(defn- move-to-block-opponent
  [board player]
  (first
    (board/valid-slots
      (nth (rules/winning-combinations board) (board-with-potential-winner board player)))))

(defn- minimax
  [board depth is-max-player max-player alpha beta]
    (if (rules/game-over? board)
      (gameover-move-score board depth max-player)
      (do
        (loop [[first-free-slot & rest] (board/valid-slots board)
                best-position (assign-initial-score is-max-player)
                alpha alpha
                beta beta]
          (let [updated-board (board/move board first-free-slot (rules/whose-turn board))
                position (mem-minimax updated-board (dec depth) (not is-max-player) max-player alpha beta)
                latest-best-move-score (player-best-move-score is-max-player best-position position first-free-slot)
                new-alpha (recalculate-alpha is-max-player latest-best-move-score alpha)
                new-beta (recalculate-beta is-max-player latest-best-move-score beta)]
            (if (end-branch-traversal? rest new-alpha new-beta)
              latest-best-move-score
              (recur rest latest-best-move-score new-alpha new-beta)))))))

(def mem-minimax (memoize minimax))

(defn optimal-move
  [player board]
    (let[board-size (board/board-size board)
         use-preferred-position-4x4board? (and (= board-size 4) (> (count (board/valid-slots board)) 13))
         use-preferred-position-5x5board? (and (= board-size 5) (> (count (board/valid-slots board)) 20))
         opponent-about-to-win-5x5board? (and (= board-size 5) (>= (count (board/valid-slots board)) 14) (opponent-close-to-win? board player))]
      (cond
        use-preferred-position-4x4board?
          (move-on-preferred-position board preferred-positions-on-4x4)
        use-preferred-position-5x5board?
          (move-on-preferred-position board preferred-positions-on-5x5)
        opponent-about-to-win-5x5board?
          (move-to-block-opponent board player)  
        :else
          (do 
            (let [depth (count (board/valid-slots board))
                  is-max-player true
                  max-player (rules/whose-turn board)
                  best-move-score-pair (mem-minimax board depth is-max-player max-player initial-alpha initial-beta)]
              (first best-move-score-pair))))))
