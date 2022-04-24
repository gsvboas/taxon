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