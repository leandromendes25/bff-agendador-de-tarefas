# 🚀 Sistema de Microserviços - Agendador de Tarefas

Projeto desenvolvido com o objetivo de estudar e aplicar conceitos de **arquitetura de microserviços**, utilizando **Java, Spring Boot e boas práticas de desenvolvimento back-end e de front utilizando Angular Js.**.

O sistema é composto por múltiplos serviços independentes que se comunicam entre si, cada um responsável por um contexto específico do domínio.

---

## 🧱 Arquitetura

O projeto segue arquitetura de microserviços, onde cada serviço possui:

- Camada Controller
- Camada Service
- Camada Repository
- Banco de dados próprio
- Comunicação via REST utilizando OpenFeign

Arquitetura geral:

[ Client / Frontend ]
|
v
[ BFF ]
|
| Auth/User Service |
| Task Scheduler Service |
| Email Service |
[ Databases ]


---

## 🧩 Microserviços

### 🔐 User Service (Autenticação)

Responsável por:

- Cadastro de usuários
- Login
- Geração e validação de JWT

Repositório:  
👉 https://github.com/leandromendes25/usuario

---

### 🗓️ Task Scheduler Service (Agendador de Tarefas)

Responsável por:

- Criação de tarefas
- Associação da tarefa ao usuário autenticado
- Consumo de token JWT recebido do User Service
- Comunicação com User Service via OpenFeign

Repositório:  
👉 https://github.com/leandromendes25/agendador-de-tarefas

---

### 📧 Email Service

Responsável por:

- Envio de e-mails
- Recebimento de solicitações de envio

Repositório:  
👉 https://github.com/leandromendes25/notificacao

---

### 🧭 BFF (Backend For Frontend)

Responsável por:

- Centralizar requisições do frontend
- Encaminhar chamadas para os microserviços corretos
- Simplificar consumo da API pelo cliente

Este repositório
---

### Frontend

Responsável por:

- Fazer a comunicação entre o backend e o front
- Ser amigável com o usuário final

Repositório:
👉 https://github.com/leandromendes25/agendador-tarefas-front
## 🔁 Comunicação Entre Serviços

A comunicação entre microserviços é realizada via **OpenFeign**, permitindo chamadas REST entre serviços de forma simples e desacoplada.

Exemplo:

```java
@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UserClient {

    @PostMapping("/login")
    String login(@RequestBody LoginDTORequest loginDTOrequest);
}
```
🔐 Segurança

Autenticação baseada em JWT

Token gerado no User Service

Token enviado no header Authorization

Serviços validam token antes de executar ações

🐳 Docker e Orquestração

Todos os microserviços possuem Dockerfile e são orquestrados através de um docker-compose.yml central.

📁 Estrutura Recomendada

microservices-project
├─ user-service
├─ task-scheduler-service
├─ email-service
├─ bff-service
└─ docker-compose.yml

⚠️ Importante: Todos os projetos devem estar na mesma pasta para que o docker-compose consiga construir as imagens corretamente.

▶️ Como Executar

Pré-requisitos:
+ Java 21
+ Docker
+ Docker Compose
+ Angular Js 21

Passos:

```
git clone git@github.com:leandromendes25/bff-agendador-de-tarefas.git
git clone git@github.com:leandromendes25/agendador-de-tarefas.git
git clone git@github.com:leandromendes25/usuario.git
git clone git@github.com:leandromendes25/notificacao.git
git clone git@github.com:leandromendes25/agendador-tarefas-front.git
cd bff-agendador-de-tarefas
docker-compose up -d

em outra aba do terminal
cd agendador-tarefas-front
npm install
ng serve
```
🔧 Tecnologias Utilizadas
+ Java
+ Spring Boot
+ Spring Security
+ JWT
+ Spring Data JPA
+ PostgreSQL
+ MongoDB
+ OpenFeign
+ Docker
+ Docker Compose
+ Maven
+ Gradle
+ Git
+ Node.js
+ Angular

🎯 Objetivo do Projeto

Projeto criado com foco em aprendizado prático de:

Arquitetura de microserviços

Segurança em aplicações distribuídas

Comunicação entre serviços

Organização de código

Boas práticas

Teste sua aplicação pelo Swagger
http://localhost:8083/swagger-ui/index.html