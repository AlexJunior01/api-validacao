(ns api-validacao.routes.cpf-test
  (:require [clojure.test :refer :all]
            [api-validacao.routes.cpf :as cpf]
            [api-validacao.web :refer [app]]
            [cheshire.core :as json]))
