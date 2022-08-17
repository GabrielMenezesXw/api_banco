DROP DATABASE PROJETO_LP1;

CREATE DATABASE PROJETO_LP1;

USE PROJETO_LP1;

INSERT INTO TB_BANCO (nome, senha)
	VALUES	('Banrisul', 'Banrisul'),
			('Banco do Brasil ', 'Banco do Brasil'),
			('Sicredi', 'Sicredi'),
			('Unicred', 'Unicred'),
			('Banco Inter', 'Banco Inter');

INSERT INTO conta (cpf_titular,saldo,senha,status)
VALUES 
('01655489709', 5000, 'memes', 'ATIVA'),
('01616489772', 10000, 'patos','INATIVA'),
('02155489704', 2000, 'carros', 'ATIVA'),
('01623789712', 10000, 'senhas', 'ATIVA'),
('01697389752', 40000, 'passwords', 'ATIVA');

INSERT INTO TB_ENDERECO (bairro, cidade, logradouro, uf)
VALUES 
('Passo da areia', 'Porto Alegre' ,'Rua Vinte e Nove, 194', 20),
('Jardim são pedro', 'Porto Alegre',  'Rua Vinte e tres, 165', 20),
('Belém Velho', 'Porto Alegre',  'Rua Vinte e quatro, 220', 20),
('Cristal', 'Porto Alegre',  'Rua Vinte e cinco 3906', 20),
('São Bento', 'Porto Alegre',  'Rua Vinte e seis 4040', 20);

INSERT INTO tb_pessoa(cpf, data_nascimento, nome, sexo, endereco)
VALUES
('01655489709', '1996-07-20', 'Marques Marcos', 'MASCULINO',1),
('01616489772', '2002-09-01', 'Viktor Villiam', 'MASCULINO',2),
('02155489704', '1990-12-23', 'Carlos Eduardo', 'MASCULINO',3),
('01623789712', '1968-03-10', 'Vladimir Pinto', 'MASCULINO',4),
('01697389752', '1995-05-12', 'Johanna Baptista', 'FEMININO',5);
