CREATE TABLE pacientes(
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(180) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    cpf VARCHAR(11),
    telefone VARCHAR(20),
    PRIMARY KEY(id)
);