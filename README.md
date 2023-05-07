# Cofrinho

Gerenciador financeiro

## Sumário

-   [Stack](#stack)
-   [Como rodar localmente?](#como-rodar-localmente)
    -   [Rodar direto](#rodar-direto)
    -   [Rodar via docker](#rodar-via-docker)

### Stack

-   Banco de dados: PostgreSQL
-   Backend: Java com Spring Boot | Gerenciado com Gradle
-   Frontend: TBD

### Como rodar localmente?

-   Clonar esse repositório
-   Configurar as variáveis de ambiente
    -   Na pasta `backend` duplicar o arquivo `.env-sample` e renomear para `.env`
    -   Esse arquivo contém informacões sensíveis e por isso nao será commitado
    -   Se for subir o ambiente com docker, ignorar o próximo passo, e deixar o conteúdo do arquivo duplicado igual ao sample
    -   Nele você deverá informar as variáveis do seu ambiente
        1. DB_URL: Url do seu banco postgres
            - Deverá conter `postgresql://host:porta/nome_da_base` (Garantir que a base exista antes de subir a aplicação)
        2. DB_USER: Username do seu banco
        3. DB_PASSWORD: Senha do seu banco
        4. JWT_KEY=: É uma chave anônima usada para criar e verificar o token de autenticação.
    -   Com as variáveis de ambiente definidas, escolher:
        -   [Rodar direto](#rodar-direto)
        -   [Rodar via docker](#rodar-via-docker)

#### Rodar direto

##### Backend

-   Esse projeto utiliza Java 17 com Gradle. Portanto, os mesmos deverão estar configurados em seu ambiente
-   Via linha de comando na raíz da pasta `backend` rodar:

```bash
./gradlew bootRun
```

-   Ambiente do backend deverá inicializar sem erros
-   Se nenhum erro for mostrado no terminal, testar a url `http://localhost:8080` que deverá retornar "ok"

#### Rodar via docker

-   Por enquando, rodando via docker, definições variáveis de ambiente não são suportadas. Portanto manter o exato conteúdo de `.env-sample` no seu arquivo `.env`
-   Feito isso, via linha de comando na raíz do projeto rodar:

```bash
docker-compose up
```

-   Esse comando irá:
    -   Levantar um banco postgres na porta 5433
    -   Levantar o backend na porta 8080
-   Ambiente do backend deverá inicializar sem erros
-   Se nenhum erro for mostrado no terminal, testar a url `http://localhost:8080` que deverá retornar "ok"
