CREATE TABLE medicos(
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(180) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    crm VARCHAR(6) NOT NULL UNIQUE,
    especialidade VARCHAR(100) NOT NULL,
    logradouro VARCHAR(100) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    complemento VARCHAR(100),
    numero VARCHAR(20),
    uf VARCHAR(2),
    cidade VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);