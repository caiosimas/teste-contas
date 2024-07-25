CREATE TABLE IF NOT EXISTS conta (
    id UUID NOT NULL,
    data_vencimento TIMESTAMP,
    data_pagamento TIMESTAMP,
    descricao VARCHAR(500),
    situacao_conta SMALLINT,
    valor numeric(10, 2),
    userID UUID,

    CONSTRAINT pk_conta PRIMARY KEY (id)
);
