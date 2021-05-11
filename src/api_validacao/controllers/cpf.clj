(ns api-validacao.controllers.cpf)


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
  (let [nine-digits (repeatedly 10 #(rand-int 10))
        cpf (conj nine-digits (first-digit nine-digits))
        cpf (conj cpf (second-digit cpf))]
    (reduce str cpf)))


(defn handle-cpf-generate
  [body]
  (let [cpf (generate-cpf!)]
    (if (:with_dots body)
      {:body {:cpf (mask-cpf cpf)}}
      {:body {:cpf cpf}})))