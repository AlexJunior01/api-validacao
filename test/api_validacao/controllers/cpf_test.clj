(ns api-validacao.controllers.cpf-test
  (:require [clojure.test :refer :all]
            [api-validacao.controllers.cpf :as cpf]))


(deftest generate-cpf
  (testing "generate cpf without dots"
    (let [payload {:with_dots false}
          {:keys [body] :as resp} (cpf/handle-cpf-generate payload)
          cpf (:cpf body)]
      (is (= (count cpf) 11))))


  (testing "generate cpf with dots"
    (let [payload {:with_dots true}
          {:keys [body] :as resp} (cpf/handle-cpf-generate payload)
          cpf (:cpf body)]
      (is (= (count cpf) 14)))))