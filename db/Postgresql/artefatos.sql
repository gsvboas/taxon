/* Começo Artefatos Gabriele */
CREATE OR REPLACE VIEW admin_detalhamento_de_corrida_view AS
    SELECT
        c.id as id,
        p.nome as motorista_nome,
        c.cpf as motorista_cpf,
        v.marca as veiculo_marca,
        v.modelo as veiculo_modelo,
        v.cor as veiculo_cor,
        v.placa as veiculo_placa,
        c.inicia_em as inicia_em,
        c.termina_em as termina_em,
        c.inicia_as as inicia_as,
        c.termina_as as termina_as,
        c.valor as valor_total

    FROM
        corrida as c
    JOIN
        fisica as f ON f.cpf = c.cpf
    JOIN
        pessoa as p ON p.id = f.id
    JOIN
        veiculo as v ON c.chassi = v.chassi;

CREATE OR REPLACE FUNCTION recupera_fatura_de_agendamento(agendamento_id integer)
    RETURNS integer AS $fatura_id$
        DECLARE
            id integer;
        BEGIN
            SELECT
                   f.id INTO id
            FROM
                 fatura as f
            JOIN
                agendamento as a ON f.cnpj = a.cnpj
            WHERE a.id = agendamento_id
            AND EXTRACT(MONTH from a.efetuado_as) = EXTRACT(MONTH from f.data_criacao);
            RETURN id;
        END
    $fatura_id$ LANGUAGE plpgsql;

CREATE OR REPLACE VIEW admin_criacao_de_corrida_view AS
    SELECT
        c.cpf AS motorista_cpf,
        c.chassi AS veiculo_chassi,
        c.inicia_as AS data_inicio,
        c.agendamento_id AS agendamento_id,
        c.fatura_id AS fatura_id
    FROM
         corrida AS c;

CREATE OR REPLACE FUNCTION f_i_corrida()
    RETURNS trigger AS $trigger$
        DECLARE
            fatura_id integer;
        BEGIN
            fatura_id = recupera_fatura_de_agendamento(NEW.agendamento_id);
            INSERT INTO corrida(
                cpf,
                chassi,
                inicia_as,
                agendamento_id,
                fatura_id
            ) VALUES (
                NEW.motorista_cpf,
                NEW.veiculo_chassi,
                NEW.data_inicio,
                NEW.agendamento_id,
                fatura_id
            );
            RETURN NULL;
        END
    $trigger$ LANGUAGE plpgsql;

CREATE TRIGGER t_i_corrida INSTEAD OF
    INSERT ON admin_criacao_de_corrida_view
    FOR EACH ROW
    EXECUTE FUNCTION f_i_corrida();

CREATE OR REPLACE VIEW admin_alocacao_de_corrida_view AS
SELECT
    c.id as agendamento_id,
    c.inicia_as as inicia_as,
    c.cpf as motorista_cpf,
    c.chassi as veiculo_chassi
FROM
    corrida AS c;

CREATE OR REPLACE FUNCTION alocar_corrida()
    RETURNS trigger AS $trigger$
        BEGIN
            INSERT INTO admin_criacao_de_corrida_view(
                motorista_cpf,
                veiculo_chassi,
                data_inicio,
                agendamento_id
            ) VALUES (
                NEW.motorista_cpf,
                NEW.veiculo_chassi,
                NEW.inicia_as,
                NEW.agendamento_id
            );
            RETURN NULL;
        END
    $trigger$ LANGUAGE plpgsql;

CREATE TRIGGER t_i_alocacao_de_corrida_view
    INSTEAD OF INSERT ON admin_alocacao_de_corrida_view
    FOR EACH ROW
    EXECUTE FUNCTION alocar_corrida();

CREATE OR REPLACE VIEW admin_criacao_de_agendamento_view AS
    SELECT
        a.cnpj as cnpj,
        a.efetuado_as as efetuado_as,
        c.inicia_as as inicia_as
    FROM agendamento AS a
    JOIN corrida AS c ON c.agendamento_id = a.id;

CREATE OR REPLACE VIEW admin_motoristas_habilitados_corrida AS
    SELECT
        mh.cpf as cpf,
        mh.chassi as chassi,
        c.inicia_as as horario_corrida
    FROM
        motoristahabilitado as mh
    JOIN
        corrida as c ON c.chassi = mh.chassi AND c.cpf = mh.cpf;

CREATE OR REPLACE FUNCTION f_ao_agendar()
    RETURNS trigger AS $trigger$
        DECLARE
            agendamento_id integer;
            motorista_cpf CHAR(11);
            veiculo_chassi CHAR(17);
        BEGIN
            INSERT INTO agendamento (cnpj, efetuado_as) VALUES (NEW.cnpj, NEW.efetuado_as);
            SELECT id INTO agendamento_id FROM agendamento WHERE cnpj = NEW.cnpj AND efetuado_as = NEW.efetuado_as;
            SELECT
                cpf, chassi INTO motorista_cpf, veiculo_chassi
            FROM admin_motoristas_habilitados_corrida
            ORDER BY random() LIMIT 1;


            INSERT INTO admin_alocacao_de_corrida_view(
                agendamento_id, inicia_as,
                motorista_cpf, veiculo_chassi
            ) VALUES (
                agendamento_id, NEW.inicia_as,
                motorista_cpf, veiculo_chassi
            );
            RETURN NULL;
        END
    $trigger$ LANGUAGE plpgsql;

CREATE TRIGGER t_ao_agendar
    INSTEAD OF INSERT ON admin_criacao_de_agendamento_view
    FOR EACH ROW
    EXECUTE FUNCTION f_ao_agendar();

CREATE OR REPLACE VIEW admin_resumo_de_agendamento_view AS
    SELECT
        a.cnpj AS cnpj,
        c.id AS corrida_id,
        p.nome AS nome_conveniada
    FROM
        agendamento AS a
    JOIN
        corrida AS c ON a.id = c.agendamento_id
    JOIN
        juridica j on a.cnpj = j.cnpj
    JOIN
        pessoa p on j.id = p.id;

CREATE OR REPLACE VIEW admin_resumo_de_corridas_view AS
    SELECT
        c.id AS id,
        p.nome AS conveniada_nome,
        c.cpf AS motorista_cpf,
        c.chassi AS veiculo_chassi,
        c.valor AS valor_total,
        c.inicia_as AS data_inicio,
        c.termina_as AS data_fim
    FROM
        corrida AS c
    JOIN
        agendamento AS a ON c.agendamento_id = a.id
    JOIN
        juridica AS j ON a.cnpj = j.cnpj
    JOIN
        pessoa AS p ON j.id = p.id;

CREATE OR REPLACE FUNCTION mesma_empresa(agendamento integer, fatura integer)
    RETURNS boolean as $result$
        DECLARE
            agendamento_cnpj char(14);
            fatura_cnpj char(14);
        BEGIN
            SELECT a.cnpj into agendamento_cnpj FROM agendamento AS a WHERE a.id = agendamento;
            SELECT f.cnpj into fatura_cnpj FROM fatura AS f WHERE f.id = fatura;
            RETURN agendamento_cnpj = fatura_cnpj;
        END
$result$ LANGUAGE plpgsql;
ALTER TABLE corrida
    ADD CONSTRAINT
        corrida_check
        CHECK(mesma_empresa(agendamento_id, fatura_id));

CREATE OR REPLACE FUNCTION f_ao_inserir_ou_atualizar_em_admin_resumo_de_corridas_view()
    RETURNS trigger AS $trig$
        BEGIN
            RAISE 'Favor atualizar diretamente nas tabelas';
        END
    $trig$ LANGUAGE plpgsql;

CREATE TRIGGER trig_ao_inserir_ou_atualizar_em_admin_resumo_de_corridas_view
    INSTEAD OF UPDATE ON admin_resumo_de_corridas_view
    FOR EACH ROW
    EXECUTE FUNCTION f_ao_inserir_ou_atualizar_em_admin_resumo_de_corridas_view();
/* Fim Artefatos Gabriele */
/* Começo Artefatos Áquila */

CREATE OR REPLACE VIEW Pessoa_login AS SELECT email, senha FROM Pessoa;

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
    m.cpf as motorista_cpf
FROM
    veiculo v, garagem g, motoristaacessaveiculo m
WHERE v.chassi = g.chassi AND m.chassi = v.chassi;

CREATE OR REPLACE FUNCTION recupera_veiculos(motoristaCpf text)
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
                      motorista_cpf CHAR(11)
                  )
    LANGUAGE plpgsql AS
$func$
BEGIN
    RETURN QUERY
        select * from veiculo_view where veiculo_view.motorista_cpf = motoristaCpf;
END
$func$;

CREATE OR REPLACE FUNCTION f_i_veiculo()
    RETURNS TRIGGER AS
$func$
BEGIN
    INSERT INTO veiculo(chassi, cor, placa, ano, modelo, marca, max_ocupacao)
    	VALUES(NEW.chassi, NEW.cor, NEW.placa, NEW.ano, NEW.modelo, NEW.marca, NEW.max_ocupacao)
        ON CONFLICT (chassi) DO UPDATE
        	SET cor = NEW.cor, placa = NEW.placa, ano = NEW.ano, modelo = NEW.modelo,
				marca = NEW.marca, max_ocupacao = NEW.max_ocupacao;
    INSERT INTO garagem(chassi, cep, num, num_vaga)
    	VALUES(NEW.chassi, NEW.garagem_cep, NEW.garagem_num, NEW.garagem_num_vaga)
        ON CONFLICT (chassi, cep, num) DO UPDATE
        	SET num_vaga = NEW.garagem_num_vaga;
    INSERT INTO motoristaacessaveiculo(cpf, chassi)
    	VALUES(NEW.motorista_cpf, NEW.chassi)
        ON CONFLICT (cpf, chassi) DO NOTHING;
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
    SET num_vaga = NEW.garagem_num_vaga
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
    DELETE FROM motoristaacessaveiculo WHERE motoristaacessaveiculo.chassi = OLD.chassi;
    DELETE FROM veiculo WHERE veiculo.chassi = OLD.chassi;
    RETURN NULL;
END
$func$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER t_d_veiculo INSTEAD OF DELETE ON veiculo_view
    FOR EACH ROW EXECUTE PROCEDURE f_d_veiculo();

/* Fim Artefatos Samuel */
