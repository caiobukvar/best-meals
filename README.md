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
```

### 2. Configure o banco de dados
Atualize os arquivos application.properties (ou application.yml) de cada serviço com os dados de conexão do seu banco:

```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/best_meals
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### 3. Inicie os serviços
A ordem sugerida de inicialização é:

1.  `eureka-server`
2.  `restaurant-service`
3.  `restaurant-evaluation-service`
4.  `meal-service`
5.  `meal-evaluation-service`
6.  `api-gateway`

Você pode rodar cada serviço com o comando:

```bash
cd nome-do-servico
mvn spring-boot:run
```

## 📚 Documentação da API
Cada serviço expõe sua documentação Swagger em:

- `restaurant-service`: http://localhost:8081/swagger-ui.html
- `restaurant-evaluation-service`: http://localhost:8082/swagger-ui.html
- `meal-service`: http://localhost:8083/swagger-ui.html
- `meal-evaluation-service`: http://localhost:8084/swagger-ui.html

## 🔁 Comunicação entre Serviços
A comunicação entre os serviços é feita via HTTP utilizando RestTemplate, com base nos nomes registrados no Eureka.

## 📦 Estrutura dos Diretórios

```pgsql
best-meals/
│
├── api-gateway/
├── eureka-server/
├── meal-service/
├── meal-evaluation-service/
├── restaurant-service/
├── restaurant-evaluation-service/
└── pom.xml
```

## ✍️ Autor
Desenvolvido por Caio Bukvar
