ALTER TABLE "main"."categoria" ALTER COLUMN "usuario_id"
DROP NOT NULL,
ADD COLUMN IF NOT EXISTS "tipo" VARCHAR (50),
ADD CONSTRAINT "titulo_com_tipo_repetido" UNIQUE ("titulo", "tipo", "usuario_id");
