# car-management-api

## Car Management API
A Car Management API é um sistema de gestão de veículos projetado para facilitar o controle e manutenção de informações sobre carros. Este projeto fornece uma solução backend robusta, construída com Spring Boot, que permite operações CRUD (Criar, Ler, Atualizar, Deletar) através de uma interface RESTful.

## Funcionalidades
- Cadastro de Veículos: Permite adicionar novos carros ao sistema com informações como marca, modelo e ano.
- Consulta de Veículos: Os usuários podem visualizar todos os veículos cadastrados e suas especificações.
- Edição de Veículos: Facilita a atualização de informações de carros existentes.
- Remoção de Veículos: Possibilita a exclusão de veículos do sistema.

##Segurança
Utilizamos as melhores práticas de segurança, incluindo autenticação de usuários e proteção contra CSRF, para garantir que apenas usuários autorizados possam realizar operações na API.

##Tecnologias Utilizadas
- Spring Boot: Para a criação de um aplicativo autônomo que inclui um servidor embarcado.
- Spring Security: Para a segurança do aplicativo, incluindo autenticação e autorização.
- JPA / Hibernate: Para a persistência de dados e interação com o banco de dados.

Este projeto é ideal para quem procura uma plataforma confiável para gerenciar uma frota de veículos ou para desenvolvedores que desejam aprender e aplicar conceitos avançados de desenvolvimento web com Spring Framework.

### comandos

```bash
mvn clean install -U
```

```bash
mvnw spring-boot:run
```

### detalhes
- https://spring.io/guides/gs/securing-web
- https://github.com/spring-guides/gs-securing-web
