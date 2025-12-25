# Spring Boot – Aplicação simples de contas e saldos

Esta é uma aplicação exemplo de consulta e persistência de contas e saldos.

---

## Bancos de Dados

| Banco                | Finalidade                             | Tipo |
|----------------------|----------------------------------------|------|
| **Account Database** | Criação e Consulta de contas           | H2 (em memória) |
| **Balance Database** | Criaçào e Consulta de saldos de contas | H2 (em memória) |

>Obs: Como os bancos são **in-memory**, os dados são perdidos ao reiniciar a aplicação.

---

### Endpoints da Aplicação
A aplicação possui dois endpoints principais para a consulta e persistencia de dados.

>Uma **collection do insomnia** está disponibilizada na raiz do projeto.

---
### GET - Consulta de contas por id de cliente

Endpoint responsável por consultar as contas, seus saldos e o valor total no banco de dados de saldos.

| Método | URL |
|--------|-----|
| `GET`  | `/accounts/{customerId}` |

#### Response Body

```json
[
	{
		"number": "56672",
		"type": "J",
		"balances": [
			{
				"description": "saldo disponível",
				"amount": "777.00"
			},
			{
				"description": "saldo bloqueado",
				"amount": "300.00"
			}
		],
		"totalAmount": "1077.00"
	}
]
```

### POST - Criação de conta

Endpoint responsável por persistir contas junto com seus saldos disponiveis e/ou bloqueados no banco de dados de contas.

| Método | URL |
|--------|-----|
| `POST` | `/accounts/{customerId}` |

#### Request Body
```json
{
	"number": "56672",
	"type": "J",
	"balances": [
		{
			"blocked": false,
			"amount": 777
		},
		{
			"blocked": true,
			"amount": 300
		}
	]
}
```