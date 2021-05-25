(ns api_validacao.http
  (:require [cheshire.core :as json]))

(defn json-http
  [body status]
  {
   :status  status
   :body    (if (string? body)
              body
              (json/generate-string body true))
   :headers {"Content-Type" "application/json"}
   })


(defn json-http-ok
  [body]
  (println "HTTP OK")
  (json-http body 200))
