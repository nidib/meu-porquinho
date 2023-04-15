BEGIN;

INSERT INTO "main"."frequencia" ("id", "nome", "grandeza")
	VALUES
	('2ac06cde-755e-440f-90f7-4cbbeffa8e08', 'Diário', 0),
	('184b3424-83bd-4d5d-b8ae-e8e0bd602eb7', 'Semanal', 1),
	('129514c3-36a8-4643-8816-c3b50b36ef79', 'Quinzenal', 2),
	('cccfe55a-946e-429d-8da0-ef28189e66ee', 'Mensal', 3),
	('dc1cca96-761d-445c-ab32-2b367be9c165', 'Bimestral', 4),
	('4d1fd300-f3eb-4eba-bf97-6785eced426d', 'Trimestral', 5),
	('dc1712a9-4336-437b-ac4d-ba56b2fe0236', 'Semestral', 6),
	('a1103e4d-47b7-43fa-8847-9a1f2398d818', 'Anual', 7);

INSERT INTO "main"."prioridade" ("id", "nome", "grandeza")
	VALUES
	('04c9bf1a-2a88-4ba4-b35e-9671623bfb71', 'Baixa', 0),
	('5636d6e5-999a-4588-920c-7f42e80f63c8', 'Normal', 1),
	('92fef7c8-ab6e-4ed5-a7df-a17dfa45d9d0', 'Alta', 2),
	('0cb8d34f-1ce0-4019-9611-b8a0696f994e', 'Crítica', 3);

COMMIT;
