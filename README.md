# 🍽️ Best Meals

**Best Meals** é uma aplicação baseada em microserviços que permite o cadastro, avaliação e listagem dos melhores pratos de restaurantes de uma cidade.  
Cada parte da aplicação é desacoplada e comunicada via HTTP, utilizando Eureka para descoberta de serviços e Spring Cloud Gateway para roteamento.

---

## 🧱 Arquitetura

A arquitetura do projeto segue o modelo de **microserviços**, com os seguintes módulos:

- `eureka-server`: Serviço de descoberta de microsserviços usando Spring Cloud Netflix Eureka.
- `api-gateway`: Gateway responsável por rotear requisições para os serviços corretos.
- `meal-service`: Serviço responsável pelo cadastro e consulta de pratos (meals).
- `meal-evaluation-service`: Serviço responsável por avaliar pratos com comentários e notas.
- `restaurant-service`: Serviço responsável pelo cadastro e listagem de restaurantes.
- `restaurant-evaluation-service`: Serviço responsável por avaliar restaurantes com comentários e notas.

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot 3.2+
- Spring Web
- Spring Data JPA
- Spring Cloud Netflix Eureka
- Spring Cloud Gateway
- PostgreSQL
- Swagger/OpenAPI 3
- Docker (em breve)
- Maven

---

## 🚀 Como Rodar o Projeto

> Pré-requisitos:
> - Java 17+
> - Maven
> - PostgreSQL em execução (ou alterar as configurações para outro banco)

### 1. Clone o repositório

```bash
git clone https://github.com/caiobukvar/best-meals.git
cd best-meals
