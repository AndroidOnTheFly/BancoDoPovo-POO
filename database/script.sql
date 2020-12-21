CREATE TABLE cliente (
cpf VARCHAR(32),
nome VARCHAR(100) NOT NULL,
email VARCHAR(100) NOT NULL,
cidade VARCHAR(50) NOT NULL,
estado VARCHAR(50) NOT NULL,
nascimento VARCHAR(10) NOT NULL,
tipoConta VARCHAR(32) NOT NULL,
senha VARCHAR(50) NOT NULL,
CONSTRAINT cliente_pk PRIMARY KEY(cpf)
)
;
CREATE table contacorrente(
numConta VARCHAR(32) NOT NULL,
agencia VARCHAR(32) NOT NULL,
saldo  REAL NOT NULL, 
cpf VARCHAR(32),
CONSTRAINT contacorrente_pk PRIMARY KEY (numConta),
CONSTRAINT contacorrente_fk FOREIGN KEY (cpf) REFERENCES cliente(cpf)
)
;
CREATE TABLE contapoupanca(
numConta VARCHAR(32) NOT NULL,
agencia VARCHAR(32) NOT NULL,
saldo  REAL NOT NULL, 
cpf VARCHAR(32),
CONSTRAINT contapoupanca_pk PRIMARY KEY (numConta),
CONSTRAINT contapoupanca_fk FOREIGN KEY (cpf) REFERENCES cliente(cpf)
)
;

CREATE TABLE emprestimobasedomilagre(
valor REAL NOT NULL,
numConta VARCHAR(32),
CONSTRAINT emprestimobasedomilagre_fk FOREIGN KEY (numConta) REFERENCES contacorrente(numConta)
)
;
CREATE TABLE emprestimoperdicao(
valor REAL NOT NULL,
numConta VARCHAR(32),
CONSTRAINT emprestimoperdicao_fk FOREIGN KEY (numConta) REFERENCES contacorrente(numConta)
)
;
CREATE TABLE emprestimosalvaraperto(
valor REAL NOT NULL,
numConta VARCHAR(32),
CONSTRAINT emprestimosalvaraperto_fk FOREIGN KEY (numConta) REFERENCES contacorrente(numConta)
)
;
CREATE TABLE emprestimosalvarserasa(
valor REAL NOT NULL,
numConta VARCHAR(32),
CONSTRAINT salvarserasa_fk FOREIGN KEY (numConta) REFERENCES contacorrente(numConta)
)
;