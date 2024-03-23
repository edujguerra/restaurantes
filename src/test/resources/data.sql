DELETE from linguagens.tb_avaliacao;
DELETE from linguagens.tb_restaurante;
DELETE from linguagens.tb_tipocozinha;
DELETE from linguagens.tb_cliente;

INSERT INTO linguagens.tb_tipocozinha (id_tipo_cozinha, ds_tipo) VALUES (1, "LANCHES");
INSERT INTO linguagens.tb_tipocozinha (id_tipo_cozinha, ds_tipo) VALUES (2, "PIZZAS");
INSERT INTO linguagens.tb_tipocozinha (id_tipo_cozinha, ds_tipo) VALUES (3, "MASSAS");
INSERT INTO linguagens.tb_tipocozinha (id_tipo_cozinha, ds_tipo) VALUES (4, "BIFES");
INSERT INTO linguagens.tb_tipocozinha (id_tipo_cozinha, ds_tipo) VALUES (5, "PRATO FEITO");

INSERT INTO linguagens.tb_restaurante
    (id_Restaurante, ds_enderec, hr_final,
     hr_inicio, nm_restaurante, id_tipo_cozinha,
     nr_mesas, mesas_disponiveis)
VALUES (1, "AVENIDA UM", "06:00",
        "22:00", "RESTAURANTE UM", 1, 30, 20);

INSERT INTO linguagens.tb_cliente
    (id_cliente, email, nome, telefone)
values (1,"EMAIL UM", "CLIENTE UM", 213213);

INSERT linguagens.tb_avaliacao
    (dt_avaliacao, ds_descricao, nr_nota, id_cliente, id_restaurante)
values
    ("2024-03-20", "Gostei do lugar.", 8, 1,1);

INSERT linguagens.tb_avaliacao
(dt_avaliacao, ds_descricao, nr_nota, id_cliente, id_restaurante)
values
    ("2024-03-20", "Não gostei muito.", 3, 1,1);

INSERT linguagens.tb_avaliacao
(dt_avaliacao, ds_descricao, nr_nota, id_cliente, id_restaurante)
values
    ("2024-03-20", "Achei otimo.", 10, 1,1);