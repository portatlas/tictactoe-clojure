(ns tictactoe-clojure.console)

(defn display [message]
	(println message))

(defn prompt [message]
 	(println message)
 	(read-line))
