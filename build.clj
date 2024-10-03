(ns build
  (:require [clojure.tools.build.api :as b]))

(def build-folder "target")
(def jar-contents (str build-folder "/classes"))

(def app-name 'cf.clj/test)
(def version "1")
(def basis (b/create-basis {:project "deps.edn"}))
(def uberjar-file-name (format "%s/%s-%s-standalone.jar" build-folder (name app-name) version))

(defn clean [_]
  (b/delete {:path build-folder})
  (println (format "Build folder \"%s\" removed" build-folder)))

(defn uberjar [_]
  (clean nil)

  (b/copy-dir {:src-dirs ["resources"]
               :target-dir jar-contents})

  (b/compile-clj {:basis basis
                  :src-dirs ["src"]
                  :class-dir jar-contents})

  (b/uber {:class-dir jar-contents
           :uber-file uberjar-file-name
           :basis basis
           :main 'cf.clj.main})

  (println (format "Uberjar created: \"%s\"" uberjar-file-name)))
