/* Começo Artefatos Samuel */
CREATE OR REPLACE FUNCTION recupera_corridas_pendentes(cpfNumber text, mes integer, ano integer)
 	RETURNS TABLE (
      id_corrida BIGINT,
      valor_recebido REAL,
      valor_total  REAL,
      data_inicio  TIMESTAMP,
      data_fim     TIMESTAMP
 	)
  	LANGUAGE plpgsql AS
$func$
    BEGIN
       RETURN QUERY
       select * from recupera_corridas(cpfNumber, mes, ano) as c where c.data_fim is null; -- Utiliza recupera_corridas feito pelo Áquila
    END
$func$;

CREATE OR REPLACE VIEW veiculo_view AS
    SELECT
        v.chassi as chassi,
        v.cor as cor,
        v.placa as placa,
        v.ano as ano,
        v.modelo as modelo,
        v.marca as marca,
        v.max_ocupacao as max_ocupacao,
        g.cep as garagem_cep,
        g.num as garagem_num,
        g.num_vaga as garagem_num_vaga,
        p.id as proprietario_id
    FROM
        veiculo v, garagem g, proprietariopossuiveiculo p
    WHERE v.chassi = g.chassi AND p.chassi = v.chassi;
 
CREATE OR REPLACE FUNCTION recupera_veiculos(propietarioId int)
	RETURNS TABLE (
    	chassi VARCHAR(17),
        cor VARCHAR(255),
        placa VARCHAR(7),
        ano INT,
        modelo VARCHAR(50),
        marca VARCHAR(50),
        max_ocupacao INT,
        garagem_cep CHAR(8),
        garagem_num INT,
        garagem_num_vaga INT,
        proprietario_id INT
  	)
	LANGUAGE plpgsql AS
$func$
BEGIN
   RETURN QUERY
   select * from veiculo_view where veiculo_view.proprietario_id = propietarioId;                  
END
$func$;
    
CREATE OR REPLACE FUNCTION f_i_veiculo()
	RETURNS TRIGGER AS
	$func$
    BEGIN
    	INSERT INTO veiculo(chassi, cor, placa, ano, modelo, marca, max_ocupacao)
        	VALUES(NEW.chassi, NEW.cor, NEW.placa, NEW.ano, NEW.modelo, NEW.marca, NEW.max_ocupacao);
        INSERT INTO garagem(chassi, cep, num, num_vaga)
        	VALUES(NEW.chassi, NEW.garagem_cep, NEW.garagem_num, NEW.garagem_num_vaga);
        INSERT INTO proprietariopossuiveiculo(id, chassi)
        	VALUES(NEW.proprietario_id, NEW.chassi);
		RETURN NULL;
    END
    $func$ LANGUAGE plpgsql;
    
 CREATE OR REPLACE TRIGGER t_i_veiculo INSTEAD OF INSERT ON veiculo_view
 	FOR EACH ROW EXECUTE PROCEDURE f_i_veiculo();
    
CREATE OR REPLACE FUNCTION f_u_veiculo()
	RETURNS TRIGGER AS
	$func$
    BEGIN
    	UPDATE veiculo
        	SET cor = NEW.cor, placa = NEW.placa, ano = NEW.ano, modelo = NEW.modelo,
           	marca = NEW.marca, max_ocupacao = NEW.max_ocupacao
		WHERE veiculo.chassi = NEW.chassi;
        UPDATE garagem
        	SET cep = NEW.garagem_cep, num = NEW.garagem_num, num_vaga = NEW.garagem_num_vaga
		WHERE garagem.chassi = NEW.chassi;
        RETURN NULL;
    END
    $func$ LANGUAGE plpgsql;
    
 CREATE OR REPLACE TRIGGER t_u_veiculo INSTEAD OF UPDATE ON veiculo_view
 	FOR EACH ROW EXECUTE PROCEDURE f_u_veiculo();
    
    
CREATE OR REPLACE FUNCTION f_d_veiculo()
	RETURNS TRIGGER AS
	$func$
    BEGIN
    	DELETE FROM garagem WHERE garagem.chassi = OLD.chassi;
        DELETE FROM veiculo WHERE veiculo.chassi = OLD.chassi;
        DELETE FROM proprietariopossuiveiculo WHERE proprietariopossuiveiculo.chassi = OLD.chassi;
        RETURN NULL;
    END
    $func$ LANGUAGE plpgsql;
    
 CREATE OR REPLACE TRIGGER t_d_veiculo INSTEAD OF DELETE ON veiculo_view
 	FOR EACH ROW EXECUTE PROCEDURE f_d_veiculo();
        
/* Fim Artefatos Samuel */

/* Começo Artefatos Gabriele */
CREATE OR REPLACE VIEW admin_resumo_de_corridas_view AS
    SELECT
        id AS id,
        cpf AS motorista_cpf,
        chassi AS veiculo_chassi,
        valor AS valor_total,
        inicia_as AS data_inicio
    FROM
        corrida;

/* Fim Artefatos Gabriele */

/* Começo Artefatos Áquila */
CREATE VIEW Pessoa_login AS SELECT email, senha FROM Pessoa;

CREATE OR REPLACE FUNCTION foo(a TEXT, b TEXT) 
RETURNS BOOLEAN 
LANGUAGE sql AS
'SELECT (senha = $2) FROM Pessoa_login WHERE email = $1;
RETURN FOUND;';

CREATE FUNCTION check_password(uname TEXT, pass TEXT)
RETURNS BOOLEAN AS'
DECLARE passed BOOLEAN;
BEGIN
        SELECT  (senha = $2) INTO passed
        FROM    Pessoa_login
        WHERE   email = $1;

        RETURN passed;
END;
'
LANGUAGE plpgsql;

CREATE VIEW corridas_view AS SELECT id, cpf,valor, inicia_as, termina_as FROM Corrida;

CREATE OR REPLACE FUNCTION recupera_corridas(cpfNumber text, mes integer, ano integer)
  RETURNS TABLE (id_corrida BIGINT
	       , valor_recebido REAL
	       , valor_total  REAL
	       , data_inicio  TIMESTAMP
	       , data_fim     TIMESTAMP)
  LANGUAGE plpgsql AS
$func$
BEGIN
   RETURN QUERY
   select id::BIGINT as id_corrida,(valor*0.6)::REAL as valor_recebido, valor as valor_total, inicia_as as data_inicio, termina_as as data_fim from corridas_view where cpf = $1 and EXTRACT(MONTH FROM inicia_as) = $2 and EXTRACT(YEAR FROM inicia_as)= $3;                  
END
$func$;

CREATE OR REPLACE FUNCTION recupera_lucro_total(cpfNumber text, mes integer, ano integer)
  RETURNS REAL
  LANGUAGE plpgsql AS
$func$
DECLARE 
  cur corrida%ROWTYPE;
  total REAL;
BEGIN
   total := 0.0;
   FOR cur in 
	SELECT * FROM corrida WHERE cpf =$1 AND EXTRACT(MONTH FROM inicia_as) = $2 AND EXTRACT(YEAR FROM inicia_as)= $3
   LOOP
	total := total + cur.valor * 0.6;
   	END LOOP;
   RETURN total;
END;
$func$;

CREATE OR REPLACE FUNCTION recupera_corridas_totais(cpfNumber text, mes integer, ano integer)
  RETURNS BIGINT
  LANGUAGE plpgsql AS
$func$
DECLARE 
  cur corrida%ROWTYPE;
  total BIGINT;
BEGIN
   total := 0;
   FOR cur in 
	SELECT * FROM corrida WHERE cpf =$1 and EXTRACT(MONTH FROM inicia_as) = $2 and EXTRACT(YEAR FROM inicia_as)= $3
   LOOP
	total := total + 1;
   	END LOOP;
   RETURN total;
END;
$func$;

/* Fim Artefatos Áquila */