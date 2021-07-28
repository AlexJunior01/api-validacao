(ns api-validacao.schemas
  (:require [clojure.spec.alpha :as s]))


(s/def ::message string?)

(s/def ::cpf string?)
(s/def ::valid boolean?)


(s/def ::validate-cpf-response
  (s/keys :req-un [::cpf
                   ::valid]
          :opt-un [::message]))

