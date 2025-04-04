Nome do participante: Rafael Ribeiro Estrela

Inscrição: 8369

Documentação:

# 🛠️ API – Desafio Seplag MT (Desenvolvedor Java Backend)

Este repositório contém a implementação da API desenvolvida para o desafio técnico do processo seletivo da **Secretaria de Estado de Planejamento e Gestão de Mato Grosso (SEPLAG-MT)** para o cargo de **Desenvolvedor Java Backend**.

---

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Framework** (Spring Boot, Spring Data)
- **PostgreSQL** – Banco de dados relacional
- **MinIO** – Armazenamento de arquivos (compatível com S3)
- **Docker & Docker Compose**
- Arquitetura **MVC**, com aplicação de **padrões de projeto** para melhor organização e manutenção

---

## 🐳 Preparando o Ambiente com Docker

Antes de subir os containers da aplicação, recomenda-se **limpar o ambiente Docker** para evitar conflitos com containers, volumes e redes antigas.

### 🔄 Limpeza do Docker (opcional, mas recomendada)

```bash
# Parar todos os containers em execução
docker stop $(docker ps -q)

# Remover todos os containers
docker rm $(docker ps -a -q)

# Remover todas as imagens
docker rmi -f $(docker images -q)

# Remover todos os volumes
docker volume rm $(docker volume ls -q)

# Remover todas as redes
docker network rm $(docker network ls -q)

# Limpeza completa e segura
docker system prune -a --volumes -f
```

---

## ▶️ Executando a Aplicação

### 1. Clone o repositório

```bash
git clone https://github.com/RafaelRibeiroEstrela/Java-desafio-processo-seletivo-seplag.git
cd seplag-api
```

### 2. Suba os containers com Docker Compose

Na pasta principal do projeto, execute o comando:

```bash
docker-compose up --build
```

O sistema irá subir os seguintes serviços:

- API Java Spring Boot
- Banco de dados PostgreSQL
- Servidor MinIO (acessível via browser)

---

## 🧪 Testando a API

A API expõe endpoints RESTful documentados via Swagger (ou Postman, se aplicável).

- Acesse a documentação:

```
http://localhost:8080/swagger-ui.html
```

- Utilize ferramentas como **Postman** ou **curl** para testar os endpoints.

---

## Autenticação e Segurança

A aplicação utilização autentação e autorização com Spring Security e Token JWT.

Para realizar login, acesse o url:

```
Método: POST
URL: http://localhost:8080/auth/login
Request: {
  "username":"admin",
  "password":"12345"
}
```

Para atualizar token, acesse o url:

```
Método: POST
URL: http://localhost:8080/auth/refresh-token
Request: {
   "token":"{token}" 
}
```

Para realizar logout, acesse a url:

```
Método: POST
URL: http://localhost:8080/auth/logout
Request: {
   "token":"{token}" 
}
```

## 📁 Upload de Arquivos

Os arquivos enviados são armazenados no **MinIO**, acessível via:

```
http://localhost:9000
```

- **Usuário:** user  
- **Senha:** 12345678
- **Bucket:** desafio-processo-seletivo-seplag-bucket

Para realizar upload de fotos:

```
http://localhost:8080/upload-fotos
```

Ao realizar o upload, será retornado um json com a URL para recuperar o arquivo.
Lembre-se que a URL irá expirar após 5 minutos.

---

## 🧩 Arquitetura & Padrões

- Separação clara entre camadas: **Controller**, **Service**, **Repository**
- Aplicação de princípios **SOLID**
- Uso de **DTOs** e mapeamentos com ModelMapper (se aplicável)
- Tratamento de exceções com `@ControllerAdvice`
- Logs com **SLF4J**

---

## ✅ Status

> ✅ Projeto concluído com sucesso  
> Pronto para avaliação técnica
