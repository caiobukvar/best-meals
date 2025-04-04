# Eureka Server

Este repositório contém a configuração de um **Eureka Server** para registro e descoberta de serviços em uma arquitetura distribuída.

## Tecnologias Utilizadas
- **Java**
- **Spring Boot** (Spring Cloud Netflix Eureka)
- **Maven**

## Configuração e Instalação

### Clonando o Repositório
```sh
git clone https://github.com/caiobukvar/eureka-server.git
cd eureka-server
```

### Executando o Servidor

1. Certifique-se de que possui o **Java 17+** instalado.
2. Execute o comando:
```sh
mvn spring-boot:run
```
3. O Eureka Server estará disponível em:
   - **`http://localhost:8761/`**

## Configuração do Application Properties
O arquivo `src/main/resources/application.properties` contém as configurações do servidor:
```properties
spring.application.name=eureka-server
server.port=8761
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
eureka.server.wait-time-in-ms-when-sync-empty=0
```
Isso configura o Eureka como **servidor** (e não cliente), tornando-o o ponto central de registro.

## Registrando um Serviço no Eureka
Para conectar um serviço ao Eureka, adicione as seguintes configurações no `application.properties` do serviço:
```properties
spring.application.name=nome-do-servico
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
```
Isso permitirá que o serviço se registre automaticamente no Eureka Server.

## Monitoramento
Ao acessar **`http://localhost:8761/`**, você pode visualizar todos os serviços registrados e suas instâncias ativas.

## Contribuição
Pull Requests são bem-vindos! Caso tenha alguma sugestão ou problema, abra uma issue.

---
🚀 **Criado e mantido por [Caio Bukvar](https://github.com/caiobukvar).**
