(ns api_validacao.http
  (:require [cheshire.core :as json]))

(def standard-error {:body {:message "Erro genérico"}
                     :status 500
                     :headers {"Content-Type" "application/json"}})


(defn json-http
  [body status]
  (try
    {:status  status
     :body    body
     }
    (catch Exception e
      standard-error)))


(defn json-http-ok
  [body]
  (json-http body 200))
