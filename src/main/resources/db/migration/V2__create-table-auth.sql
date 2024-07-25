CREATE TABLE IF NOT EXISTS auth (
    id UUID NOT NULL,
    username VARCHAR(255),
    password VARCHAR(255),

    CONSTRAINT pk_auth PRIMARY KEY (id)
);