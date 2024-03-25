DELETE from restaurantes.tb_reserva;
DELETE from restaurantes.tb_avaliacao;
DELETE from restaurantes.tb_restaurante;
DELETE from restaurantes.tb_tipocozinha;
DELETE from restaurantes.tb_cliente;

INSERT INTO restaurantes.tb_tipocozinha (id_tipo_cozinha, ds_tipo) VALUES (1, "LANCHES");
INSERT INTO restaurantes.tb_tipocozinha (id_tipo_cozinha, ds_tipo) VALUES (2, "PIZZAS");
INSERT INTO restaurantes.tb_tipocozinha (id_tipo_cozinha, ds_tipo) VALUES (3, "MASSAS");
INSERT INTO restaurantes.tb_tipocozinha (id_tipo_cozinha, ds_tipo) VALUES (4, "BIFES");
INSERT INTO restaurantes.tb_tipocozinha (id_tipo_cozinha, ds_tipo) VALUES (5, "PRATO FEITO");

INSERT INTO restaurantes.tb_restaurante
    (id_Restaurante, ds_enderec, hr_final,
     hr_inicio, nm_restaurante, id_tipo_cozinha,
     nr_mesas, mesas_disponiveis)
VALUES (1, "AVENIDA UM", "06:00",
        "22:00", "RESTAURANTE UM", 1, 30, 20);

INSERT INTO restaurantes.tb_cliente
    (id_cliente, email, nome, telefone)
values (1,"EMAIL UM", "CLIENTE UM", 213213);

INSERT restaurantes.tb_avaliacao
    (id_avaliacao, dt_avaliacao, ds_descricao, nr_nota, id_cliente, id_restaurante)
values
   (1, "2024-03-20", "Gostei do lugar.", 8, 1,1);

INSERT restaurantes.tb_avaliacao
   (id_avaliacao, dt_avaliacao, ds_descricao, nr_nota, id_cliente, id_restaurante)
values
    (2, "2024-03-20", "Não gostei muito.", 3, 1,1);

INSERT restaurantes.tb_avaliacao
    (id_avaliacao, dt_avaliacao, ds_descricao, nr_nota, id_cliente, id_restaurante)
values
    (3, "2024-03-20", "Achei otimo.", 10, 1,1);

--Inserts para a tabela Reserva--
INSERT INTO restaurantes.tb_restaurante
    (id_Restaurante, ds_enderec, hr_final,
     hr_inicio, nm_restaurante, id_tipo_cozinha,
     nr_mesas, mesas_disponiveis)
VALUES (2, "AVENIDA UM", "06:00",
        "22:00", "RESTAURANTE UM", 1, 8, 8);

INSERT INTO restaurantes.tb_cliente
    (id_cliente, email, nome, telefone)
values (2,"EMAIL DOIS", "CLIENTE DOIS", 213213);
INSERT INTO restaurantes.tb_cliente
    (id_cliente, email, nome, telefone)
values (3,"EMAIL TRES", "CLIENTE TRES", 213213);
INSERT INTO restaurantes.tb_cliente
    (id_cliente, email, nome, telefone)
values (4,"EMAIL QUATRO", "CLIENTE QUATRO", 213213);
INSERT INTO restaurantes.tb_cliente
    (id_cliente, email, nome, telefone)
values (5,"EMAIL CINCO", "CLIENTE CINCO", 213213);
INSERT INTO restaurantes.tb_cliente
    (id_cliente, email, nome, telefone)
values (6,"EMAIL SEIS", "CLIENTE SEIS", 213213);

INSERT INTO restaurantes.tb_reserva (id_reserva, data_reserva, hr_final, hr_inicio, numero_pessoas, cliente_id_cliente, restaurante_id_restaurante)
VALUES (1, "2024-03-20", "21", "20", 8, 1, 2);
INSERT INTO restaurantes.tb_reserva (id_reserva, data_reserva, hr_final, hr_inicio, numero_pessoas, cliente_id_cliente, restaurante_id_restaurante)
VALUES (2, "2024-03-20", "21", "20", 14, 2, 2);
INSERT INTO restaurantes.tb_reserva (id_reserva, data_reserva, hr_final, hr_inicio, numero_pessoas, cliente_id_cliente, restaurante_id_restaurante)
VALUES (3, "2024-03-20", "21", "20", 8, 3, 2);
INSERT INTO restaurantes.tb_reserva (id_reserva, data_reserva, hr_final, hr_inicio, numero_pessoas, cliente_id_cliente, restaurante_id_restaurante)
VALUES (4, "2024-03-20", "21", "20", 3, 4, 2);
--INSERT INTO restaurantes.reserva (id_reserva, data_reserva, hr_final, hr_inicio, numero_pessoas, cliente_id_cliente, restaurante_id_restaurante)
--VALUES (5, "2024-03-20", "21", "20", 8, 5, 2);
--INSERT I5TO restaurantes.reserva (id_reserva, data_reserva, hr_final, hr_inicio, numero_pessoas, cliente_id_cliente, restaurante_id_restaurante)
--VALUES (6, "2024-03-20", "21", "20", 8, 6, 2);