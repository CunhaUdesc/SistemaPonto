CREATE TABLE IF NOT EXISTS tbfuncionario (
    funcodigo 		  INTEGER PRIMARY KEY AUTOINCREMENT,
    funnome 		  VARCHAR(100) NOT NULL,
    funcpf 			  VARCHAR(11) NOT NULL UNIQUE,
    funtipo 		  VARCHAR(7) NOT NULL CHECK (funtipo IN('FIXO', 'HORISTA')),
    fundatanascimento DATE,
    funcargahoraria   SMALLINT DEFAULT 0.0,
    funsalario		  DECIMAL(5,2) DEFAULT 0.0,
    funvalorhora      DECIMAL(5,2) DEFAULT 0.0
);

CREATE TABLE IF NOT EXISTS tbusuario (
    usucodigo    INTEGER PRIMARY KEY AUTOINCREMENT,
    usulogin 	 SMALLINT UNIQUE NOT NULL,
    ususenha 	 VARCHAR  NOT NULL,
    usutipo 	 VARCHAR(4) NOT NULL DEFAULT 'FUNC' CHECK (usutipo IN('ADM', 'FUNC')),
    funcodigo    SMALLINT NOT NULL UNIQUE, FOREIGN KEY (funcodigo) REFERENCES tbfuncionario(funcodigo)
);

CREATE TABLE IF NOT EXISTS tbregistroponto (
    regcodigo         INTEGER PRIMARY KEY AUTOINCREMENT,
    regdataregistro   DATE NOT NULL DEFAULT CURRENT_DATE,
    regentrada        TIMESTAMP,
    regsaidaintervalo TIMESTAMP,
    regvoltaintervalo TIMESTAMP,
    regsaidafinal     TIMESTAMP,
    funcodigo 	      INTEGER NOT NULL, FOREIGN KEY (funcodigo) REFERENCES tbfuncionario(funcodigo)
);

INSERT INTO tbusuario (usulogin, ususenha, usutipo, funcodigo) VALUES (0, 'adm', 'ADM', 1);























