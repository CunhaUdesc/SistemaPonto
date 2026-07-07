DROP TABLE IF EXISTS tbregistroponto;
DROP TABLE IF EXISTS tbusuario;
DROP TABLE IF EXISTS tbfuncionario;

CREATE TABLE IF NOT EXISTS tbfuncionario (
    funcodigo 		  INTEGER PRIMARY KEY AUTOINCREMENT,
    funnome 		  VARCHAR(100) NOT NULL,
    funcpf 			  VARCHAR(11) NOT NULL UNIQUE,
    fundatanascimento VARCHAR(10),
    funtipo 		  VARCHAR(7) NOT NULL,
    funcargahoraria   SMALLINT DEFAULT 0.0,
    funsalario		  DECIMAL(5,2) DEFAULT 0.0,
    funvalorhora      DECIMAL(5,2) DEFAULT 0.0
);

CREATE TABLE IF NOT EXISTS tbusuario (
    usucodigo    INTEGER PRIMARY KEY AUTOINCREMENT,
    usulogin 	 SMALLINT UNIQUE NOT NULL,
    ususenha 	 VARCHAR  NOT NULL,
    usutipo 	 VARCHAR(4) NOT NULL DEFAULT 'FUNC',
    funcodigo    SMALLINT NOT NULL UNIQUE, FOREIGN KEY (funcodigo) REFERENCES tbfuncionario(funcodigo) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tbregistroponto (
    regcodigo         INTEGER PRIMARY KEY AUTOINCREMENT,
    regdataregistro   DATE NOT NULL DEFAULT CURRENT_DATE,
    regentrada        TIMESTAMP,
    regsaidaintervalo TIMESTAMP,
    regvoltaintervalo TIMESTAMP,
    regsaidafinal     TIMESTAMP,
    funcodigo 	      INTEGER NOT NULL, FOREIGN KEY (funcodigo) REFERENCES tbfuncionario(funcodigo) ON DELETE CASCADE
);


INSERT INTO tbfuncionario (funnome, funcpf, fundatanascimento, funtipo, funvalorhora, funsalario, funcargahoraria) VALUES ('Administrador', '00000000001', '01/01/2020', 'HORISTA', '50', null, null);
INSERT INTO tbusuario (usulogin, ususenha, usutipo, funcodigo) VALUES (0, 'adm', 'ADM', 1);
INSERT INTO tbregistroponto (regdataregistro, regentrada, regsaidaintervalo, regvoltaintervalo, regsaidafinal, funcodigo) VALUES ('2025-12-25','2026-07-06 08:30:00', '2026-05-06 11:45:12', '2026-07-06 13:02:05', '2026-07-06 16:40:45', 1);

INSERT INTO tbregistroponto (regdataregistro, regentrada, regsaidaintervalo, regvoltaintervalo, regsaidafinal, funcodigo) VALUES ('2026-12-25','2026-07-07 00:30:00', '2026-07-07 00:45:12', '2026-07-07 01:02:05', '2026-07-07 01:40:45', 1)
