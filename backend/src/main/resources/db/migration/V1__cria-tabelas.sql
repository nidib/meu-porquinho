BEGIN;

CREATE SCHEMA IF NOT EXISTS "main";

CREATE TABLE "main"."frequencia" (
	"id" UUID DEFAULT gen_random_uuid(),
	"nome" CHARACTER VARYING(255) NOT NULL,
	"grandeza" INTEGER NOT NULL UNIQUE,
	"criado_em" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"atualizado_em" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

	PRIMARY KEY ("id")
);

CREATE TABLE "main"."prioridade" (
	"id" UUID DEFAULT gen_random_uuid(),
	"nome" CHARACTER VARYING(255) NOT NULL,
	"grandeza" INTEGER NOT NULL UNIQUE,
	"criado_em" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"atualizado_em" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

	PRIMARY KEY ("id")
);

CREATE TABLE "main"."perfil" (
	"id" UUID DEFAULT gen_random_uuid(),
	"nome_completo" CHARACTER VARYING(255),
	"data_de_nascimento" DATE,
	"apelido" CHARACTER VARYING(50),
	"criado_em" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"atualizado_em" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

	CONSTRAINT "data_de_nascimento_nao_pode_ser_no_futuro" CHECK ("data_de_nascimento" < CURRENT_DATE),

	PRIMARY KEY ("id")
);

CREATE TABLE "main"."usuario" (
	"id" UUID DEFAULT gen_random_uuid(),
	"login" CHARACTER VARYING(50) UNIQUE NOT NULL,
	"email" CHARACTER VARYING(255) UNIQUE NOT NULL,
	"senha" CHARACTER VARYING(255) NOT NULL,
	"perfil_id" UUID NOT NULL UNIQUE,
	"criado_em" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"atualizado_em" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

	PRIMARY KEY ("id"),
	FOREIGN KEY ("perfil_id") REFERENCES "main"."perfil" ("id") ON DELETE CASCADE
);

CREATE TABLE "main"."categoria" (
	"id" UUID DEFAULT gen_random_uuid(),
	"nome" CHARACTER VARYING(255) NOT NULL,
	"tipo" CHARACTER VARYING(50) NOT NULL,
	"usuario_id" UUID,
	"criado_em" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"atualizado_em" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

 	CONSTRAINT "nome_com_tipo_devem_ser_unicos_para_um_usuario" UNIQUE ("nome", "tipo", "usuario_id"),
 	CONSTRAINT "tipo_enum" CHECK ("tipo" IN ('GASTO', 'RECEITA')),

	PRIMARY KEY ("id"),
	FOREIGN KEY ("usuario_id") REFERENCES "main"."usuario" ("id") ON DELETE CASCADE
);

CREATE TABLE "main"."conta_bancaria" (
	"id" UUID DEFAULT gen_random_uuid(),
	"titulo" CHARACTER VARYING(255) NOT NULL,
	"saldo" BIGINT NOT NULL DEFAULT 0,
	"dia_do_vencimento_da_fatura" INTEGER NOT NULL,
	"usuario_id" UUID NOT NULL,
	"criado_em" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"atualizado_em" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

	CONSTRAINT "vencimento_da_fatura_deve_ser_um_dia_comum_para_todos_os_meses" CHECK ("dia_do_vencimento_da_fatura" BETWEEN 1 AND 28),
	CONSTRAINT "titulo_da_conta_deve_ser_unica_para_um_usuario" UNIQUE ("titulo", "usuario_id"),

	PRIMARY KEY ("id"),
	FOREIGN KEY ("usuario_id") REFERENCES "main"."usuario" ("id") ON DELETE CASCADE
);

CREATE TABLE "main"."movimentacao" (
	"id" UUID DEFAULT gen_random_uuid(),
	"nome" VARCHAR(50) NOT NULL,
	"valor" BIGINT NOT NULL,
	"data" DATE NOT NULL,
	"data_de_conclusao" DATE,
	"conta_bancaria_id" UUID NOT NULL,
	"categoria_id" UUID NOT NULL,
	"frequencia_id" UUID,
	"prioridade_id" UUID,
	"criado_em" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"atualizado_em" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

	CONSTRAINT "valor_nao_pode_ser_zero" CHECK ("valor" <> 0),
	CONSTRAINT "frequencia_eh_obrigatoria_quando_data_nao_for_informada" CHECK (("data_de_conclusao" IS NULL) OR ("frequencia_id" IS NOT NULL)),

	PRIMARY KEY ("id"),
	FOREIGN KEY ("conta_bancaria_id") REFERENCES "main"."conta_bancaria" ("id") ON DELETE CASCADE,
	FOREIGN KEY ("categoria_id") REFERENCES "main"."categoria" ("id") ON DELETE CASCADE,
	FOREIGN KEY ("frequencia_id") REFERENCES "main"."frequencia" ("id") ON DELETE SET NULL,
	FOREIGN KEY ("prioridade_id") REFERENCES "main"."prioridade" ("id") ON DELETE SET NULL
);

COMMIT;
