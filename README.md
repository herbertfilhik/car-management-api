# car-management-api

## Tópicos
- [Car Management API](#car-management-api)
- [Funcionalidades](#funcionalidades)
- [Segurança](#seguranca)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Comandos para baixar e executar o projeto](#comandos-para-baixar-e-executar-o-projeto)
- [Testando os EndPoints](#testando-os-endpoints)
- [Leitura para referencia](#leitura-para-referencia)

## Car Management API
A Car Management API é um sistema de gestão de veículos projetado para facilitar o controle e manutenção de informações sobre carros. Este projeto fornece uma solução backend robusta, construída com Spring Boot, que permite operações CRUD (Criar, Ler, Atualizar, Deletar) através de uma interface RESTful.

## Funcionalidades
- Cadastro de Veículos: Permite adicionar novos carros ao sistema com informações como marca, modelo e ano.
- Consulta de Veículos: Os usuários podem visualizar todos os veículos cadastrados e suas especificações.
- Edição de Veículos: Facilita a atualização de informações de carros existentes.
- Remoção de Veículos: Possibilita a exclusão de veículos do sistema.

## Seguranca
Utilizamos as melhores práticas de segurança, incluindo autenticação de usuários e proteção contra CSRF, para garantir que apenas usuários autorizados possam realizar operações na API.

## Tecnologias Utilizadas
- Spring Boot: Para a criação de um aplicativo autônomo que inclui um servidor embarcado.
- Spring Security: Para a segurança do aplicativo, incluindo autenticação e autorização.
- JPA / Hibernate: Para a persistência de dados e interação com o banco de dados.

Este projeto é ideal para quem procura uma plataforma confiável para gerenciar uma frota de veículos ou para desenvolvedores que desejam aprender e aplicar conceitos avançados de desenvolvimento web com Spring Framework.

### Comandos para baixar e executar o projeto

```bash
git clone https://github.com/herbertfilhik/car-management-api.git
```

```bash
mvn clean install -U
```

```bash
mvnw spring-boot:run
```

### Testando os EndPoints

#### Guia de Testes da Car Management API com Postman

Este guia assume que você já tenha a coleção do Postman para a Car Management API importada em seu ambiente do Postman.

##### Baixe a Collection

[Collection do Postman](https://github.com/herbertfilhik/car-management-api/blob/main/docs/car-management-api.postman_collection.json)

**Configuração Inicial**
- **Inicie a Aplicação:** Certifique-se de que a Car Management API está em execução em seu ambiente local ou em um servidor remoto.
- **Abra o Postman:** Inicie o Postman e selecione a coleção da Car Management API.
- **Configure o Ambiente:** Crie um novo ambiente no Postman para armazenar variáveis, como a URL base da API e tokens de autenticação.

**Autenticação**
- **Login:** Use o endpoint `/login` para autenticar. Você precisará inserir as credenciais de usuário (por exemplo, `username` e `password`) no corpo da requisição.
    - Após o login bem-sucedido, copie o valor do `JSESSIONID` retornado nos headers de resposta e o valor do token CSRF, se aplicável.
- **Configure o Ambiente:** Adicione o `JSESSIONID` e o token CSRF como variáveis no ambiente do Postman.

![Descrição da captura de tela](https://github.com/herbertfilhik/car-management-api/blob/main/docs/endpoint-01.png)

![Descrição da captura de tela](https://github.com/herbertfilhik/car-management-api/blob/main/docs/endpoint-02.png)

**Testando Endpoints**
- **Listar Veículos:** Acesse o endpoint `GET /cars` para listar todos os veículos cadastrados.
    - Certifique-se de adicionar o `JSESSIONID` e o token CSRF nos headers, se necessário.
- **Adicionar Veículo:** Use o endpoint `POST /cars` para criar um novo veículo.
    - No corpo da requisição, insira os detalhes do veículo, como `brand`, `model`, e `year`.
    - Inclua o `JSESSIONID` e o token CSRF nos headers.
- **Consultar Veículo por ID:** Com o endpoint `GET /cars/{id}`, você pode obter os detalhes de um veículo específico substituindo `{id}` pelo ID do veículo desejado.
    - Não esqueça de adicionar o `JSESSIONID` e o token CSRF nos headers.
- **Atualizar Veículo:** O endpoint `PUT /cars/{id}` permite atualizar as informações de um veículo existente.
    - Substitua `{id}` pelo ID do veículo e inclua os novos dados do veículo no corpo da requisição.
    - Adicione o `JSESSIONID` e o token CSRF nos headers.
- **Excluir Veículo:** Para remover um veículo, use o endpoint `DELETE /cars/{id}`, substituindo `{id}` pelo ID do veículo que deseja excluir.
    - Certifique-se de adicionar o `JSESSIONID` e o token CSRF nos headers.

![Descrição da captura de tela](https://github.com/herbertfilhik/car-management-api/blob/main/docs/endpoint-03.png)

![Descrição da captura de tela](https://github.com/herbertfilhik/car-management-api/blob/main/docs/endpoint-04.png)

![Descrição da captura de tela](https://github.com/herbertfilhik/car-management-api/blob/main/docs/endpoint-05.png)

![Descrição da captura de tela](https://github.com/herbertfilhik/car-management-api/blob/main/docs/endpoint-06.png)

**Dicas Adicionais**
- **Verifique as Respostas:** Para cada requisição, verifique o corpo da resposta e os códigos de status para garantir que a operação foi bem-sucedida.
- **Gerencie suas Variáveis:** Utilize as variáveis do ambiente do Postman para facilitar a manutenção e evitar a repetição de valores comuns, como URL base e tokens.

### Leitura para referencia
- https://spring.io/guides/gs/securing-web
- https://github.com/spring-guides/gs-securing-web

### Acesso ao Swagger
- http://localhost:9090/swagger-ui/index.html
