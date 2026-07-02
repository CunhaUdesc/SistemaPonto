/* ============================= FUNCIONÁRIO ============================= */
CREATE TABLE IF NOT EXISTS tbfuncionario (
    funcodigo 		  SERIAL NOT NULL
    funnome 		  VARCHAR(100) NOT NULL,
    funcpf 			  VARCHAR(11) NOT NULL UNIQUE,
    funtipo 		  VARCHAR(7) NOT NULL,
    fundatanascimento DATE,
    funcargahoraria   SMALLINT,
    funsalario		  DECIMAL(5,2),
    funvalorhora      DECIMAL(5,2),
    usucodigo         SMALLINT NOT NULL,
);

-- Comments
COMMENT ON COLUMN tbfuncionario.funcodigo 		IS 'Código sequencial que será o login do usuário';
COMMENT ON COLUMN tbfuncionario.funnome   		IS 'Nome do Funcionário';
COMMENT ON COLUMN tbfuncionario.funcpf    		IS 'CPF do Funcionário';
COMMENT ON COLUMN tbfuncionario.funtipo   		IS 'Tipo do Funcionário (Fixo ou Horista)';
COMMENT ON COLUMN tbfuncionario.fundatanascimento IS 'Data de Nascimento do Funcionário'
COMMENT ON COLUMN tbfuncionario.funcargahoraria IS 'Carga Horária Diária do Funcionário Fixo';
COMMENT ON COLUMN tbfuncionario.funsalario		IS 'Salário mensal do Funcionário Fixo';
COMMENT ON COLUMN tbfuncionario.funvalorhora    IS 'Valor da Hora do Funcionário Horista';
COMMENT ON COLUMN tbfuncionario.usucodigo       IS 'Código do usuário relacionado ao funcionário'

-- Keys
ALTER TABLE tbfuncionario ADD PRIMARY KEY (funcodigo);
ALTER TABLE tbfuncionario ADD CONSTRAINT fk_niveis        FOREIGN KEY (NFVCODIGO) REFERENCES tbnivelfuncionario(nvfcodigo);
ALTER TABLE tbfuncionario ADD CONSTRAINT fk_usuario       FOREIGN KEY (USUCODIGO) REFERENCES tbusuario(usucodigo)
ALTER TABLE tbfuncionario ADD CONSTRAINT fk_registroPonto FOREIGN KEY (REGCODIGO) REFERENCES tbregistroponto(regcodigo)

-- Checks
ALTER TABLE tbfuncionario ADD CONSTRAINT chk_tipo_funcionario CHECK (funtipo IN('FIXO', 'HORISTA'));

/* ============================= USUÁRIO ============================= */
CREATE TABLE IF NOT EXISTS tbusuario (
    usucodigo    SERIAL NOT NULL,
    usulogin 	 SMALLINT UNIQUE NOT NULL,
    ususenha 	 SMALLINT NOT NULL,
    usutipo 	 VARCHAR(4) NOT NULL,
    funcodigo    SMALLINT NOT NULL
);

-- Comments
COMMENT ON COLUMN tbusuario.usucodigo    IS 'Código do Usuário';
COMMENT ON COLUMN tbusuario.usulogin     IS 'Login do Usuário (Código do Funcionário)';
COMMENT ON COLUMN tbusuario.ususenha     IS 'Senha do Usuário';
COMMENT ON COLUMN tbusuario.usutipo      IS 'Tipo de Usuário (ADM ou FUNC)';

-- Keys
ALTER TABLE tbusuario ADD PRIMARY KEY (usucodigo);
ALTER TABLE tbusuario ADD CONSTRAINT fk_login FOREIGN KEY (usulogin) REFERENCES tbfuncionario(funcodigo);

-- Checks
ALTER TABLE tbusuario ALTER COLUMN usutipo SET DEFAULT 'FUNC';
ALTER TABLE tbusuario ADD CONSTRAINT chk_tipo_usuario CHECK (usutipo IN('ADM', 'FUNC'));
ALTER TABLE tbusuario ADD CONSTRAINT chk_senha CHECK (ususenha ~ '^[A-Za-z0-9]+$');

/* ============================= REGISTRO PONTO ============================= */
CREATE TABLE IF NOT EXISTS tbregistroponto (
    regcodigo         SERIAL NOT NULL,
    regdataregistro   DATE NOT NULL,
    regentrada        TIMESTAMP,
    regsaidaintervalo TIMESTAMP,
    regvoltaintervalo TIMESTAMP,
    regsaidafinal     TIMESTAMP,
    funcodigo 	      INTEGER NOT NULL
);


-- Comments
COMMENT ON COLUMN tbregistroponto.regcodigo       IS 'Sequência do registro';
COMMENT ON COLUMN tbregistroponto.regdataregistro IS 'Data de criação do registro';
COMMENT ON COLUMN tbregistroponto.regentrada	  IS 'Hora de entrada';

-- Keys
ALTER TABLE tbregistroponto ADD PRIMARY KEY (regcodigo);
ALTER TABLE tbregistroponto ADD CONSTRAINT fk_funcionario FOREIGN KEY (funcodigo) REFERENCES tbfuncionario (funcodigo);

-- Checks
ALTER TABLE tbregistroponto ALTER COLUMN regdataregistro SET DEFAULT CURRENT_DATE

/* ============================= NÍVEIS FUNCIONÁRIO ============================= */
CREATE TABLE tbniveisfuncionario (
    nvfcodigo 	 SMALLINT NOT NULL,
    nvfvalorhora DECIMAL(5,2) NOT NULL,
);

-- Comments
COMMENT ON COLUMN tbniveisfuncionario.nvfcodigo    IS 'Código do Nível';
COMMENT ON COLUMN tbniveisfuncionario.nvfvalorhora IS 'Valor da Hora do Nível';

-- Keys
ALTER TABLE tbniveisfuncionario ADD PRIMARY KEY (nvfcodigo);

-- Check
ALTER TABLE tbniveisfuncionario ADD CONSTRAINT chk_codigos CHECK (nvfcodigo IN (1, 2, 3, 4
ALTER TABLE tbniveisfuncionario ALTER COLUMN nvfvalorhora SET DEFAULT 0.0

/* ============================= ADD DADOS INICIAIS ============================= */

-- Administrador
INSERT INTO tbusuario (usulogin, ususenha, usutipo) VALUES (0, 'adm', 'ADM');

-- Níveis
INSERT INTO tbniveisfuncionario (nvfcodigo) VALUES ((1),(2),(3),(4));























