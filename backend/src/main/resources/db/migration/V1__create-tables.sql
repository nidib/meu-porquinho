BEGIN;

CREATE SCHEMA IF NOT EXISTS "main";

CREATE TABLE IF NOT EXISTS "main"."frequency" (
	"id" UUID DEFAULT gen_random_uuid(),
	"name" CHARACTER VARYING(255) NOT NULL,
	"level" INTEGER NOT NULL UNIQUE,
	"created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"updated_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "main"."priority" (
	"id" UUID DEFAULT gen_random_uuid(),
	"name" CHARACTER VARYING(255) NOT NULL,
	"level" INTEGER NOT NULL UNIQUE,
	"created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"updated_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "main"."user" (
	"id" UUID DEFAULT gen_random_uuid(),
	"login" CHARACTER VARYING(50) NOT NULL,
	"password" CHARACTER VARYING(255) NOT NULL,
	"email" CHARACTER VARYING(255) NOT NULL,
	"created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"updated_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

	PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "main"."profile" (
	"id" UUID DEFAULT gen_random_uuid(),
	"full_name" CHARACTER VARYING(255),
	"birthday" DATE,
	"nickname" CHARACTER VARYING(50),
	"user_id" UUID NOT NULL UNIQUE,
	"created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"updated_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

	CONSTRAINT "birthday_cannot_be_future" CHECK ("birthday" < CURRENT_DATE),

	PRIMARY KEY ("id"),
	FOREIGN KEY ("user_id") REFERENCES "main"."user" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "main"."bank_account" (
	"id" UUID DEFAULT gen_random_uuid(),
	"title" CHARACTER VARYING(255) NOT NULL,
	"balance" BIGINT NOT NULL DEFAULT 0,
	"pay_day" INTEGER NOT NULL,
	"user_id" UUID NOT NULL,
	"created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"updated_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

	CONSTRAINT "pay_day_must_be_a_common_day_to_all_months" CHECK ("pay_day" BETWEEN 1 AND 28),

	PRIMARY KEY ("id"),
	FOREIGN KEY ("user_id") REFERENCES "main"."user" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "main"."category" (
	"id" UUID DEFAULT gen_random_uuid(),
	"title" CHARACTER VARYING(255) NOT NULL,
	"user_id" UUID NOT NULL,
	"created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"updated_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

	PRIMARY KEY ("id"),
	FOREIGN KEY ("user_id") REFERENCES "main"."user" ("id") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "main"."financial_movement" (
	"id" UUID DEFAULT gen_random_uuid(),
	"amount" BIGINT NOT NULL DEFAULT 0,
	"date" DATE NOT NULL,
	"end_date" DATE,
	"frequency_id" UUID,
	"bank_account_id" UUID NOT NULL,
	"created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"updated_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

	CONSTRAINT "amount_must_not_be_zero" CHECK ("amount" <> 0),
	CONSTRAINT "mandatory_frequency_when_end_date_is_defined" CHECK (("end_date" IS NULL) OR ("frequency_id" IS NOT NULL)),

	PRIMARY KEY ("id"),
	FOREIGN KEY ("bank_account_id") REFERENCES "main"."bank_account" ("id") ON DELETE CASCADE,
	FOREIGN KEY ("frequency_id") REFERENCES "main"."frequency" ("id") ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS "main"."income" (
	"id" UUID DEFAULT gen_random_uuid(),
	"description" CHARACTER VARYING(255),
	"category_id" UUID,
	"financial_movement_id" UUID NOT NULL,
	"created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"updated_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

	PRIMARY KEY ("id"),
	FOREIGN KEY ("financial_movement_id") REFERENCES "main"."financial_movement" ("id") ON DELETE CASCADE,
	FOREIGN KEY ("category_id") REFERENCES "main"."category" ("id") ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS "main"."expense" (
	"id" UUID DEFAULT gen_random_uuid(),
	"description" CHARACTER VARYING(255),
	"category_id" UUID,
	"priority_id" UUID,
	"financial_movement_id" UUID NOT NULL,
	"created_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"updated_at" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

	PRIMARY KEY ("id"),
	FOREIGN KEY ("financial_movement_id") REFERENCES "main"."financial_movement" ("id") ON DELETE CASCADE,
	FOREIGN KEY ("category_id") REFERENCES "main"."category" ("id") ON DELETE SET NULL,
	FOREIGN KEY ("priority_id") REFERENCES "main"."priority" ("id") ON DELETE SET NULL
);

COMMIT;