(ns api-validacao.routes.cpf
  (:require [api-validacao.controllers.cpf :as cpf]
            [api-validacao.schemas :as spec]))

(def routes
  ["/cpf"
   {:swagger {:tags ["cpf"]}}
   ["/generate" {:get {:summary    "Generate one valid CPF"
                       :parameters {:query {:with_dots boolean?}}
                       :handler    (fn [{{:keys [query]} :parameters}]
                                     (cpf/handle-cpf-generate query))}}]

   ["/validate" {:post {:summary    "Verify if one CPF is valid"
                        :parameters {:body {:cpf string?}}
                        :handler    (fn [{{:keys [body]} :parameters}]
                                      (cpf/handle-validate-cpf body))
                        :responses {200 {:body ::spec/validate-cpf-response}
                                    500 {:body ::spec/validate-cpf-response}}}}]
   ])

