INSERT INTO
    pessoa(id, email, senha, nome, papel)
VALUES
    (1, 'admin@taxon.com.br', 'admin', 'Administrador', 'ADMIN')
ON CONFLICT DO NOTHING;

INSERT INTO
    pessoa(id, email, senha, nome, papel)
VALUES
    (2, 'conveniada@gmail.com', 'conveniada', 'Conveniada', 'CONV')
ON CONFLICT DO NOTHING;

INSERT INTO
    pessoa(id, email, senha, nome, papel)
VALUES
    (3, 'motorista@taxon.com.br', 'motorista', 'Motorista', 'MOT')
ON CONFLICT DO NOTHING;

INSERT INTO
    fisica(cpf, data_nasc, estado_civil, rg, id)
VALUES
    ('33377799955', '1975-01-01 00:00:00', 'casado', '110003336', 3)
ON CONFLICT DO NOTHING;

INSERT INTO
    motorista(cpf, renach, agencia_bancaria, conta_bancaria, banco)
VALUES
    ('33377799955', '000000000', '1234', '12345-10', 'BB')
ON CONFLICT DO NOTHING;

INSERT INTO
    veiculo(chassi, cor, placa, ano, modelo, max_ocupacao, marca)
VALUES
    ('12345678901234567', 'branco', 'AAA0000', '2022', 'Corolla', '5', 'Toyota')
ON CONFLICT DO NOTHING;

INSERT INTO
    motoristaacessaveiculo(cpf, chassi)
VALUES
    ('33377799955', '12345678901234567')
ON CONFLICT DO NOTHING;


INSERT INTO
    motoristahabilitado(cpf, chassi)
VALUES
    ('33377799955', '12345678901234567')
ON CONFLICT DO NOTHING;

INSERT INTO
    juridica(cnpj, setor, id)
VALUES
    ('09876543214321', 'transportes', 2)
ON CONFLICT DO NOTHING;

INSERT INTO
    conveniada(cnpj)
VALUES
    ('09876543214321')
ON CONFLICT DO NOTHING;

INSERT INTO
    agendamento(cnpj, efetuado_as)
VALUES
    ('09876543214321', '2022-04-24 14:35:00')
ON CONFLICT DO NOTHING;

INSERT INTO
    fatura(cnpj, data_criacao, situacao)
VALUES
    ('09876543214321', '2022-04-01 00:00:00', 'em aberto')
ON CONFLICT DO NOTHING;

INSERT INTO
    corrida(cpf,
            chassi,
            inicia_as,
            valor,
            agendamento_id,
            fatura_id)
VALUES
    (
        '33377799955',
        '12345678901234567',
        '2022-06-01 08:00:00',
        103.87,
        1,
        1
    )
ON CONFLICT DO NOTHING;
INSERT INTO
    corrida(cpf,
            chassi,
            inicia_as,
            valor,
            agendamento_id,
            fatura_id)
VALUES
    (
        '33377799955',
        '12345678901234567',
        '2022-06-01 09:00:00',
        23.41,
        1,
        1
    )
ON CONFLICT DO NOTHING;