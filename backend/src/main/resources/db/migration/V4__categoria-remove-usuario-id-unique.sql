ALTER TABLE "main"."categoria" ALTER COLUMN "usuario_id"
DROP NOT NULL,
ADD COLUMN IF NOT EXISTS "tipo" VARCHAR (50),
ADD CONSTRAINT "titulo_repetido_para_o_usuario_id" UNIQUE ("titulo", "usuario_id");
