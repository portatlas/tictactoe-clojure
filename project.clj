(defproject tictactoe-clojure "0.1.0-SNAPSHOT"
  :description "Tic Tac Toe Game written in Clojure"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0-RC2"]
                 [org.clojure/test.check "0.9.0"]
                 [org.clojure/math.numeric-tower "0.0.4"]]
  :main tictactoe-clojure.core
  :profiles {:dev {:dependencies [[speclj "3.3.1"]]}}
  :plugins [[speclj "3.3.1"]]
  :test-paths ["spec"])
