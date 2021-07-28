(ns api-validacao.controllers.cpf
  (:require [api_validacao.http :as http]
            [clojure.string :as string]))

(defn parse-int
  [char]
  (Integer/parseInt (str char)))

(defn first-digit
  [nine-digits-cpf]
  (let [remainder (->> nine-digits-cpf
                       (mapv * (range 10 1 -1))
                       (reduce +))
        remainder (mod remainder 11)]

    (if (or (= remainder 0) (= remainder 1))
      0
      (- 11 remainder))))

(defn second-digit
  [ten-digits-cpf]
  (let [remainder (->> ten-digits-cpf
                       (mapv * (range 11 1 -1))
                       (reduce +))
        remainder (mod remainder 11)]

    (if (or (= remainder 0) (= remainder 1))
      0
      (- 11 remainder))))


(defn mask-cpf
  "Receive an 11-digits CPF and returns the cpf with dots."
  [cpf]
  (str (subs cpf 0 3) "." (subs cpf 3 6) "." (subs cpf 6 9) "-" (subs cpf 9 11)))


(defn generate-cpf!
  []
  (let [nine-digits (repeatedly 9 #(rand-int 10))
        cpf (conj (vec nine-digits) (first-digit nine-digits))
        cpf (conj cpf (second-digit cpf))]
    (reduce str cpf)))


(defn handle-cpf-generate
  [body]
  (let [cpf (generate-cpf!)
        resp {:cpf (if (:with_dots body)
                     (mask-cpf cpf)
                     cpf)}]
    (http/json-http-ok resp)))

(defn remove-mask
  [cpf]
  (-> cpf
      (string/replace "." "")
      (string/replace "-" "")))

(defn cpf-is-valid?
  [cpf digit1 digit2]
  (if (= (count cpf) 11)
    (and (= digit1 (nth cpf 9))
         (= digit2 (nth cpf 10)))
    false))

(defn handle-validate-cpf
  [{:keys [cpf] :as body}]
  (let [cpf (remove-mask cpf)
        numbers (vec (map parse-int cpf))
        digit1 (first-digit (subvec numbers 0 10))
        digit2 (second-digit (subvec numbers 0 11))
        response (cpf-is-valid? numbers digit1 digit2)]
    (if response
      (http/json-http-ok {:cpf   cpf
                          :valid true})
      (http/json-http-ok {:cpf   cpf
                          :valid false}))))