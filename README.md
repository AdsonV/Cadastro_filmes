
# 🎬 projetomvc – Sistema de Cadastro e Análise de Filmes

Este é um projeto full stack desenvolvido para fins acadêmicos, com o objetivo de aplicar conceitos de **desenvolvimento web com Java, Spring Boot, Thymeleaf, API RESTful, MySQL** e **JavaScript com jQuery**.

A aplicação permite que usuários cadastrem, editem e visualizem filmes, além de realizar análises contendo **nota e texto**. O front-end consome os dados da API para exibir as informações dinamicamente.

---

## ⚙️ Tecnologias utilizadas

### 🖥️ Back-end
- Java 21
- Spring Boot 3.4.4
- Spring Web (MVC e REST)
- Spring Data JPA
- MySQL
- Maven

### 🌐 Front-end
- HTML5 + CSS3
- JavaScript (jQuery)
- Thymeleaf (em algumas páginas do back-end)
- AJAX para comunicação com a API REST

---

## 🧩 Estrutura do Projeto

### 📁 Back-end (`projetomvc`)
```
projetomvc/
├── src/main/java/com/example/projetomvc/
│   ├── controllers/
│   ├── models/
│   ├── repositories/
│   └── ProjetomvcApplication.java
├── src/main/resources/
│   ├── application.properties
│   └── templates/ (para Thymeleaf)
├── pom.xml
└── script.sql
```

### 📁 Front-end (`Senac3`)
```
Senac3/
├── paginas/
│   ├── cadastrar.html
│   ├── detalhar.html
│   └── listar.html
├── js/
│   ├── cadastrar.js
│   ├── detalhar.js
│   └── listar.js
├── css/
    └── (opcional: estilos customizados)
```

---

## 🚀 Funcionalidades

- [x] Cadastro de filmes com título, gênero, ano e nota
- [x] Listagem de filmes com suas análises
- [x] Edição e exclusão de filmes
- [x] Cadastro de análises com nota e texto
- [x] Integração via API RESTful com endpoints para filmes e análises
- [x] Consumo da API usando jQuery/AJAX
- [x] Interface amigável e responsiva com HTML e CSS

---

## 🔧 Como executar o projeto

### Banco de dados
1. Crie o banco de dados no MySQL com o script `script.sql` disponível no projeto.
2. Atualize o arquivo `application.properties` com as credenciais corretas:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nomedobanco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### Back-end
```bash
cd projetomvc
./mvnw spring-boot:run
```

### Front-end
Abra o arquivo `paginas/cadastrar.html` em seu navegador.

---
