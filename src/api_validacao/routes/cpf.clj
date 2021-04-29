(ns api-validacao.routes.cpf)

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

(defn generate-cpf!
  [body]
  (let [nine-digits (repeatedly 10 #(rand-int 10))
        cpf (conj nine-digits (first-digit nine-digits))
        cpf (conj cpf (second-digit cpf))]
    (reduce str cpf)))


(def routes
  ["/cpf"
   {:swagger {:tags ["cpf"]}}
   ["/generate" {:get {:summary    "Generate X's valids CPFs"
                       :parameters {:query {:with_dots boolean?}}
                       :responses  {200 {:body {:cpf     string?
                                                :message string?}}}
                       :handler    (fn [{{:keys [query]} :parameters}]
                                     (generate-cpf! query))}}]
   ])


(comment (generate-cpf {:teste "teste"}))
