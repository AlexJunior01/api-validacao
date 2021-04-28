(ns api-validacao.routes.cpf)

(defn generate-cpf
  [body]
  (if (:with_dots body)
    {:body {:cpf     "403.685.548-48"
            :message "CPF gerado com sucesso"}}
    {:body {:cpf     "40368554848"
            :message "CPF gerado com sucesso"}}))


(def routes
  ["/cpf"
   {:swagger {:tags ["cpf"]}}
   ["/generate" {:get {:summary    "Generate X's valids CPFs"
                       :parameters {:query {:with_dots boolean?}}
                       :responses  {200 {:body {:cpf     string?
                                                :message string?}}}
                       :handler    (fn [{{:keys [query]} :parameters}]
                                     (generate-cpf query))}}]
   ])
