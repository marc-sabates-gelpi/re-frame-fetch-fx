(defproject    superstructor/re-frame-fetch-fx "lein-git-inject/version"
  :description "A re-frame effects handler for fetching resources (including across the network)."
  :url         "https://github.com/superstructor/re-frame-fetch-fx.git"
  :license     {:name "MIT"}

  :dependencies [[org.clojure/clojure       "1.10.1"   :scope "provided"]
                 [org.clojure/clojurescript "1.10.520" :scope "provided"
                  :exclusions [com.google.javascript/closure-compiler-unshaded
                               org.clojure/google-closure-library]]
                 [thheller/shadow-cljs      "2.8.76"   :scope "provided"]
                 [re-frame                  "0.10.9"   :scope "provided"]

                 [cljs-bean                 "1.5.0"]]

  :profiles {:dev {:dependencies [[binaryage/devtools "0.9.10"]]}}

  :plugins      [[day8/lein-git-inject "0.0.2"]
                 [lein-shadow          "0.1.6"]
                 [lein-shell           "0.5.0"]]

  :middleware   [leiningen.git-inject/middleware]

  :jvm-opts ["-Xmx2g"]

  :source-paths   ["src"]
  :test-paths     ["test"]
  :resource-paths ["run/resources"]

  :shadow-cljs {:nrepl  {:port 8777}

                :builds {:browser-test
                         {:target    :browser-test
                          :ns-regexp "-test$"
                          :test-dir  "resources/public/js/test"}

                         :karma-test
                         {:target    :karma
                          :ns-regexp "-test$"
                          :output-to "target/karma-test.js"}}}

  :aliases {"test-auto"  ["with-profile" "dev" "do"
                          ["clean"]
                          ["shadow" "watch" "browser-test"]]
            "karma-once" ["do"
                          ["clean"]
                          ["shadow" "compile" "karma-test"]
                          ["shell" "karma" "start" "--single-run" "--reporters" "junit,dots"]]}

  :deploy-repositories [["clojars" {:sign-releases false
                                    :url           "https://clojars.org/repo"
                                    :username      :env/CLOJARS_USERNAME
                                    :password      :env/CLOJARS_PASSWORD}]]

  :release-tasks [["deploy" "clojars"]]

  :clean-targets [:target-path
                  "shadow-cljs.edn"
                  "package.json"
                  "package-lock.json"
                  "resources/public/js/test"])




