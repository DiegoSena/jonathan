# Desafio técnico — API de Produtos e Pedidos

Este projeto é uma API em Spring Boot que já possui um CRUD de **Produtos**. O desafio tem duas partes.

## Parte 1 — Revisão do CRUD de Produtos

Revise o código existente como se fosse um Pull Request que vai para produção.

- Aponte os problemas que encontrar e **classifique por gravidade** (o que bloqueia o merge, o que é débito técnico, o que é sugestão).
- Para cada ponto, explique o impacto e como você resolveria.
- Você pode corrigir no código o que julgar mais importante, mas não precisa corrigir tudo. Priorize.

## Parte 2 — Criação de Pedidos

Implemente um endpoint para criação de pedidos.

### Requisição

O pedido contém:

- o **CPF** do cliente;
- os **itens** do pedido (produto e quantidade);
- os dados de pagamento (**número do cartão**).

O formato do JSON fica a seu critério.

### Fluxo

1. Verificar se os produtos existem.
2. Verificar se há estoque.
3. Realizar o pagamento na API de pagamento (abaixo).
4. Se tudo der certo, decrementar o estoque e salvar o pedido.

### API de pagamento

```
POST https://f739-200-152-100-218.ngrok-free.app/authorize
Content-Type: application/json

{ "cardNumber": "4111111111111111", "amount": 150 }
```

**Autorização aprovada — `200`**

```json
{
  "transactionId": "a1ca42c1-6130-4abc-93aa-aab2abb0f467",
  "cardNumber": "**** **** **** 1111",
  "amount": 150,
  "timestamp": "2026-10-21T14:29:15.899Z",
  "status": "approved",
  "code": "00",
  "authorizationCode": "465230"
}
```

**Autorização negada — `422`**

```json
{
  "transactionId": "7939dfd9-64a7-4745-aaf0-2d711534b3ca",
  "cardNumber": "**** **** **** 1111",
  "amount": 1500,
  "timestamp": "2026-10-21T14:29:16.030Z",
  "status": "declined",
  "code": "51",
  "message": "Transação negada: valor excede o limite de 1000"
}
```

**Requisição inválida — `400`**

```json
{ "status": "invalid_request", "message": "cardNumber deve ter entre 13 e 19 dígitos" }
```