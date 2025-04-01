## 🧩 Projeto: Sistema do Processo Seletivo Pleno

**Nome:** Rafael Ribeiro Estrela
**Inscrição:** ?

---

## ✨ Tecnologias Utilizadas

- **Spring Boot**
- **Java 21**
- **MinIO** `:latest`
- **PostgreSQL** `:latest`

---

## 🛠️ Como Executar
Atentar antes de subir, limpar/ deletar imagens de outros containers para não da erro ou conflitos
   ```bash
   docker system prune 
   ```
1. Execute o seguinte comando no terminal dentro dessa pasta:

   ```bash
   docker compose -f "docker-compose.yml" up -d --build
   ```

   Este comando iniciará os seguintes containers:
   - MinIO
   - PostgreSQL
   - Aplicação Spring Boot

---

## 🧩 Arquitetura do Projeto

A arquitetura MVC foi utiliza devido a sua simplicidade de implementação e que se encaixa bem para o desafio.

---

## 🧪 Como Testar

### ✅ Acesso à API via Swagger

1. Acesse a interface Swagger:

   ```shell
   http://localhost:8080/swagger-ui/index.html#/
   ```

2. **Autenticação**
   - Execute o serviço **POST** `/auth/login` na seção **AUTENTICAÇÃO**.
   - Use as credenciais:
     - **Usuário:** `admin`
     - **Senha:** `12345`
   - Copie o `accessToken` e insira na opção **Authorize** (canto superior direito).
   - O token é válido por 5 minutos.
   - Para renovar o token, utilize o serviço `/auth/refresh-token` informando o `refreshToken` e cole o novo token na opção **Authorize**.

---

## 🔄 Funcionalidades (CRUDs)

### 🏢 CRUD de Unidade

- **POST** `/unidade/`: Cria uma nova unidade.
- **GET** `/unidade/paginado/all`: Lista todas as unidades paginadas.
- **GET** `/unidade/{unidadeId}`: Busca uma unidade específica.
- **PUT** `/unidade/{unidadeId}`: Atualiza uma unidade.
- **DELETE** `/unidade/{unidadeId}`: Exclui uma unidade.

---

### 👨‍💼 CRUD de Servidor Efetivo

- **POST** `/servidorEfetivo/`: Cria um novo servidor efetivo.
  - Necessário informar a pessoa completa.
  - Não é necessário informar lista completa de endereços, apenas os IDs.
- **GET** `/servidorEfetivo/paginado/all`: Lista servidores efetivos paginados.
- **GET** `/servidorEfetivo/{pessoaId}`: Busca servidor efetivo por ID.
- **PUT** `/servidorEfetivo/{pessoaId}`: Atualiza servidor efetivo.
- **POST** `/servidorEfetivo/uploadFoto/{pessoaId}`: Adiciona uma ou mais fotos ao servidor.
- **DELETE** `/servidorEfetivo/{pessoaId}`: Exclui o servidor efetivo.
- **GET** `/servidorEfetivo/enderecoFuncional?nome=...`: Busca endereço funcional por parte do nome.
- **GET** `/servidorEfetivo/lotadosUnidade/{unidadeId}`: Lista servidores efetivos lotados em determinada unidade.

---

### ⏳ CRUD de Servidor Temporário

- **POST** `/servidorTemporario/`: Cria um novo servidor temporário.
  - Semelhante ao servidor efetivo.
- **GET** `/servidorTemporario/paginado/all`: Lista servidores temporários paginados.
- **GET** `/servidorTemporario/{unidadeId}`: Busca servidor temporário por unidade.
- **PUT** `/servidorTemporario/{pessoaId}`: Atualiza servidor temporário.
- **POST** `/servidorTemporario/uploadFoto/{pessoaId}`: (Sim, também funciona para temporários).
- **DELETE** `/servidorTemporario/{pessoaId}`: Exclui o servidor temporário.

---

### 🧷 CRUD de Lotação

- **POST** `/lotacao/`: Cria uma nova lotação.
  - Basta passar os IDs da pessoa e unidade, não os objetos completos.
- **GET** `/lotacao/paginado/all`: Lista todas as lotações paginadas.
- **GET** `/lotacao/{unidadeId}`: Busca uma lotação específica.
- **PUT** `/lotacao/{lotacaoId}`: Atualiza uma lotação.
- **DELETE** `/lotacao/{lotacaoId}`: Exclui uma lotação.

