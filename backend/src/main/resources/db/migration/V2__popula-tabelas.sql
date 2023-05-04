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

INSERT INTO "main"."categoria" ("id", "tipo", "nome")
	VALUES
	('00cf0d2c-9e02-4c59-87d9-48c6127b39d4', 'RECEITA', 'Salário'),
	('20354896-44e7-46dc-bd90-8c152b42d799', 'RECEITA', 'Empréstimo'),
	('27d757be-aa2a-4485-a8b9-be3fd30144a7', 'RECEITA', 'Investimentos'),
	('3afc45f0-8b04-49ee-8b1b-fb03402db182', 'RECEITA', 'Outras'),
	('67384f8f-edde-4e41-98b4-e5b02e088bd9', 'GASTO', 'Alimentação'),
	('701486fb-2615-4b76-9bb4-ad113951dae7', 'GASTO', 'Assinaturas e serviços'),
	('87573bb1-8aa8-428f-86d8-2e27ccfb7213', 'GASTO', 'Bares e restaurantes'),
	('b0b1acfa-b5fd-4ac1-9060-8524051fdff4', 'GASTO', 'Casa'),
	('1dad1b5d-c25b-498c-8fee-54384f2817ea', 'GASTO', 'Compras'),
	('87f77adc-6140-4732-8b13-20f31806dabc', 'GASTO', 'Cuidados pessoais'),
	('0287630d-c8b6-4d3f-ae05-44c84d476ba6', 'GASTO', 'Cuidados pessoais'),
	('592720ae-8c05-4611-9887-9197f65d7d5c', 'GASTO', 'Dívidas e empréstimos'),
	('18c9b6d5-f6b4-4083-a5d1-e8a0a976a36b', 'GASTO', 'Educação'),
	('d0603845-e747-4164-8be7-ab6f94f6928a', 'GASTO', 'Família'),
	('d2c101cd-e92a-404d-8ad6-19a0b19ef72f', 'GASTO', 'Investimentos'),
	('aaf83833-4407-4357-8b80-5a2d159c070b', 'GASTO', 'Lazer'),
	('72cd2d19-e2a0-433b-82bd-3b900b7a77e0', 'GASTO', 'Mercado'),
	('7e99710d-3de2-4865-8243-1a160f520a06', 'GASTO', 'Pets'),
	('6b9f94a5-27f4-4078-a5a0-5a830b672d30', 'GASTO', 'Saúde'),
	('0aca8482-2775-4d30-bdff-b387d4719360', 'GASTO', 'Transporte'),
	('fed72230-869f-4fb1-9e48-d34d7bc3c0be', 'GASTO', 'Viagem'),
	('eeb2b110-ebb8-41a6-b6ce-9102fd13f145', 'GASTO', 'Outros');

COMMIT;
