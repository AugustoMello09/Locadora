INSERT INTO tb_estado (name) VALUES ('Acre');
INSERT INTO tb_estado (name) VALUES ('Alagoas');
INSERT INTO tb_estado (name) VALUES ('Amapá');
INSERT INTO tb_estado (name) VALUES ('Amazonas');
INSERT INTO tb_estado (name) VALUES ('Bahia');
INSERT INTO tb_estado (name) VALUES ('Ceará');
INSERT INTO tb_estado (name) VALUES ('Distrito Federal');
INSERT INTO tb_estado (name) VALUES ('Espírito Santo');
INSERT INTO tb_estado (name) VALUES ('Goiás');
INSERT INTO tb_estado (name) VALUES ('Maranhão');
INSERT INTO tb_estado (name) VALUES ('Mato Grosso');
INSERT INTO tb_estado (name) VALUES ('Mato Grosso do Sul');
INSERT INTO tb_estado (name) VALUES ('Minas Gerais');
INSERT INTO tb_estado (name) VALUES ('Pará');
INSERT INTO tb_estado (name) VALUES ('Paraíba');
INSERT INTO tb_estado (name) VALUES ('Paraná');
INSERT INTO tb_estado (name) VALUES ('Pernambuco');
INSERT INTO tb_estado (name) VALUES ('Piauí');
INSERT INTO tb_estado (name) VALUES ('Rio de Janeiro');
INSERT INTO tb_estado (name) VALUES ('Rio Grande do Norte');
INSERT INTO tb_estado (name) VALUES ('Rio Grande do Sul');
INSERT INTO tb_estado (name) VALUES ('Rondônia');
INSERT INTO tb_estado (name) VALUES ('Roraima');
INSERT INTO tb_estado (name) VALUES ('Santa Catarina');
INSERT INTO tb_estado (name) VALUES ('São Paulo');
INSERT INTO tb_estado (name) VALUES ('Sergipe');
INSERT INTO tb_estado (name) VALUES ('Tocantins');

INSERT INTO tb_cidade (name, estado_id) VALUES('Paraguaçu Paulista', 25);
INSERT INTO tb_cidade (name, estado_id) VALUES('Cidade de Deus', 19);

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user (name, email, cpf, password) VALUES ('usuario','usuario@gmail.com','cpfrandom', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (name, email, cpf, password) VALUES ('adm','adm@gmail.com','cpfrandom','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_endereco (cidade_id, user_id, logradouro, numero, complemento, bairro, cep) VALUES (1, 1, 'Avenida Matos', '105', 'Sala 800', 'Centro', '38777012');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);

INSERT INTO tb_categoria (nome_Categoria) VALUES('Ação');	
INSERT INTO tb_categoria (nome_Categoria) VALUES('Aventura');	
INSERT INTO tb_categoria (nome_Categoria) VALUES('Drama');
INSERT INTO tb_categoria (nome_Categoria) VALUES('Romance');
INSERT INTO tb_categoria (nome_Categoria) VALUES('Ficção científica');
INSERT INTO tb_categoria (nome_Categoria) VALUES('Terror');
INSERT INTO tb_categoria (nome_Categoria) VALUES('Animação');
INSERT INTO tb_categoria (nome_Categoria) VALUES('Fantasia');
INSERT INTO tb_categoria (nome_Categoria) VALUES('Suspense');
INSERT INTO tb_categoria (nome_Categoria) VALUES('Comédia');

INSERT INTO tb_estoque (quantidade, status) VALUES(15, 0);
INSERT INTO tb_estoque (quantidade, status) VALUES(15, 0);
INSERT INTO tb_estoque (quantidade, status) VALUES(15, 0);
INSERT INTO tb_estoque (quantidade, status) VALUES(15, 0);
INSERT INTO tb_estoque (quantidade, status) VALUES(15, 0);
INSERT INTO tb_estoque (quantidade, status) VALUES(15, 0);
INSERT INTO tb_estoque (quantidade, status) VALUES(15, 0);
INSERT INTO tb_estoque (quantidade, status) VALUES(15, 0);
INSERT INTO tb_estoque (quantidade, status) VALUES(15, 0);
INSERT INTO tb_estoque (quantidade, status) VALUES(15, 0);
INSERT INTO tb_estoque (quantidade, status) VALUES(15, 0);
INSERT INTO tb_estoque (quantidade, status) VALUES(15, 0);
INSERT INTO tb_estoque (quantidade, status) VALUES(15, 0);
INSERT INTO tb_estoque (quantidade, status) VALUES(15, 0);

INSERT INTO tb_filme (nome, descricao, diretor, valor_aluguel, categoria_id, estoque_id) VALUES('Oppenheimer', 'O físico J. Robert Oppenheimer trabalha com uma equipe de cientistas durante o Projeto Manhattan, levando ao desenvolvimento da bomba atômica.', 'Christopher Nolan', 19.00, 3, 1);
INSERT INTO tb_filme (nome, descricao, diretor, valor_aluguel, categoria_id, estoque_id) VALUES('O Iluminado', 'Um escritor em crise se muda com sua família para um hotel isolado, onde eventos sobrenaturais começam a ocorrer.', 'Stanley Kubrick', 16.50, 6, 2);
INSERT INTO tb_filme (nome, descricao, diretor, valor_aluguel, categoria_id, estoque_id) VALUES('De Volta para o Futuro', 'Um jovem viaja ao passado em um carro modificado e acidentalmente interfere no futuro, precisando consertar a linha do tempo.', 'Robert Zemeckis', 14.00, 8, 3);
INSERT INTO tb_filme (nome, descricao, diretor, valor_aluguel, categoria_id, estoque_id) VALUES('Jurassic Park', 'Um grupo de cientistas é convidado a visitar um parque temático habitado por dinossauros, mas a aventura se torna perigosa.', 'Steven Spielberg', 15.00, 7, 4);
INSERT INTO tb_filme (nome, descricao, diretor, valor_aluguel, categoria_id, estoque_id) VALUES('Meu Malvado Favorito', 'Um supervilão planeja roubar a lua, mas suas vidas mudam quando ele adota três órfãs.', 'Pierre Coffin, Chris Renaud', 13.50, 5, 5);
INSERT INTO tb_filme (nome, descricao, diretor, valor_aluguel, categoria_id, estoque_id) VALUES('Pânico', 'Um assassino mascarado ataca adolescentes de uma pequena cidade durante o aniversário de um assassinato ocorrido no passado.', 'Wes Craven', 17.00, 6, 6);
INSERT INTO tb_filme (nome, descricao, diretor, valor_aluguel, categoria_id, estoque_id) VALUES('E.T. - O Extraterrestre', 'Um garoto encontra e faz amizade com um extraterrestre perdido, tentando ajudá-lo a retornar ao seu planeta.', 'Steven Spielberg', 15.50, 7, 7);
INSERT INTO tb_filme (nome, descricao, diretor, valor_aluguel, categoria_id, estoque_id) VALUES('Star Wars: Episódio IV - Uma Nova Esperança', 'Uma aventura espacial épica onde um grupo de rebeldes tenta destruir uma estação espacial poderosa.', 'George Lucas', 18.50, 8, 8);
INSERT INTO tb_filme (nome, descricao, diretor, valor_aluguel, categoria_id, estoque_id) VALUES('Harry Potter e a Pedra Filosofal', 'Um jovem bruxo descobre seu destino ao ser convidado a estudar em uma escola de magia.', 'Chris Columbus', 16.50, 8, 9);
INSERT INTO tb_filme (nome, descricao, diretor, valor_aluguel, categoria_id, estoque_id) VALUES('Missão Impossível 7', 'Ethan Hunt e sua equipe precisam evitar um desastre global após uma missão dar errado.', 'Christopher McQuarrie', 17.50, 1, 10);
INSERT INTO tb_filme (nome, descricao, diretor, valor_aluguel, categoria_id, estoque_id) VALUES('O Senhor dos Anéis: A Sociedade do Anel', 'Um jovem hobbit é escolhido para embarcar em uma jornada para destruir um anel poderoso e impedir o retorno de um senhor das trevas.', 'Peter Jackson', 15.00, 2, 11);
INSERT INTO tb_filme (nome, descricao, diretor, valor_aluguel, categoria_id, estoque_id) VALUES('Interestelar', 'Um grupo de astronautas viaja pelo espaço em busca de um novo lar para a humanidade, enfrentando perigos e paradoxos temporais.', 'Christopher Nolan', 18.50, 5, 12);
INSERT INTO tb_filme (nome, descricao, diretor, valor_aluguel, categoria_id, estoque_id) VALUES('Orgulho e Preconceito', 'A história de amor e amadurecimento entre Elizabeth Bennet e Mr. Darcy na Inglaterra do século XIX.', 'Joe Wright', 14.00, 4, 13);
INSERT INTO tb_filme (nome, descricao, diretor, valor_aluguel, categoria_id, estoque_id) VALUES('Matrix', 'Um hacker descobre a verdade sobre a realidade e se junta a uma rebelião contra máquinas que dominam a humanidade.', 'Lana Wachowski', 16.00, 5, 14);

INSERT INTO tb_reserva_online (qtd_reservada, data_reserva, status_reserva, user_id, estoque_id) VALUES(2, '2021-12-20', 0, 1, 1);
INSERT INTO tb_reserva_online (qtd_reservada, data_reserva, status_reserva, user_id, estoque_id) VALUES(2, '2021-12-20', 0, 1, 1);
INSERT INTO tb_reserva_online (qtd_reservada, data_reserva, status_reserva, user_id, estoque_id) VALUES(3, '2022-01-05', 0, 2, 3);
INSERT INTO tb_reserva_online (qtd_reservada, data_reserva, status_reserva, user_id, estoque_id) VALUES(1, '2022-02-10', 0, 1, 5);
INSERT INTO tb_reserva_online (qtd_reservada, data_reserva, status_reserva, user_id, estoque_id) VALUES(5, '2022-03-15', 0, 1, 7);
INSERT INTO tb_reserva_online (qtd_reservada, data_reserva, status_reserva, user_id, estoque_id) VALUES(2, '2022-04-20', 0, 1, 9);
INSERT INTO tb_reserva_online (qtd_reservada, data_reserva, status_reserva, user_id, estoque_id) VALUES(4, '2022-05-25', 0, 1, 2);
INSERT INTO tb_reserva_online (qtd_reservada, data_reserva, status_reserva, user_id, estoque_id) VALUES(3, '2022-06-01', 0, 1, 4);
INSERT INTO tb_reserva_online (qtd_reservada, data_reserva, status_reserva, user_id, estoque_id) VALUES(1, '2022-07-10', 0, 1, 6);
INSERT INTO tb_reserva_online (qtd_reservada, data_reserva, status_reserva, user_id, estoque_id) VALUES(4, '2022-08-15', 0, 1, 8);
INSERT INTO tb_reserva_online (qtd_reservada, data_reserva, status_reserva, user_id, estoque_id) VALUES(2, '2022-09-20', 0, 2, 10);	

INSERT INTO tb_locacao (qtd, forma_pagamento, data_locacao, data_devolucao, data_max_devolucao, user_id, filme_id)  VALUES (1, 2, '2023-08-24', '2023-10-30', '2023-08-31', 2, 1);
INSERT INTO tb_locacao (qtd, forma_pagamento, data_locacao, data_devolucao, data_max_devolucao, user_id, filme_id) VALUES (2, 1, '2023-08-25', '2023-10-30', '2023-09-01', 1, 2);
INSERT INTO tb_locacao (qtd, forma_pagamento, data_locacao, data_devolucao, data_max_devolucao, user_id, filme_id) VALUES (1, 1, '2023-08-26', '2023-10-30', '2023-09-02', 2, 3);
INSERT INTO tb_locacao (qtd, forma_pagamento, data_locacao, data_devolucao, data_max_devolucao, user_id, filme_id) VALUES (3, 2, '2023-08-27', '2023-10-30', '2023-09-03', 2, 4);
INSERT INTO tb_locacao (qtd, forma_pagamento, data_locacao, data_devolucao, data_max_devolucao, user_id, filme_id) VALUES (1, 1, '2023-08-28', '2023-10-30', '2023-09-04', 1, 5);
INSERT INTO tb_locacao (qtd, forma_pagamento, data_locacao, data_devolucao, data_max_devolucao, user_id, filme_id) VALUES (2, 2, '2023-08-29', '2023-10-30', '2023-09-05', 1, 6);
INSERT INTO tb_locacao (qtd, forma_pagamento, data_locacao, data_devolucao, data_max_devolucao, user_id, filme_id) VALUES (1, 2, '2023-08-30', '2023-10-30', '2023-09-06', 2, 7);
INSERT INTO tb_locacao (qtd, forma_pagamento, data_locacao, data_devolucao, data_max_devolucao, user_id, filme_id) VALUES (3, 1, '2023-08-31', '2023-10-30', '2023-09-07', 1, 8);
INSERT INTO tb_locacao (qtd, forma_pagamento, data_locacao, data_devolucao, data_max_devolucao, user_id, filme_id) VALUES (1, 1, '2023-09-01', '2023-10-30', '2023-09-08', 1, 9);

INSERT INTO tb_locacao (qtd, forma_pagamento, data_locacao, data_max_devolucao, user_id, filme_id) VALUES (2, 2, '2023-09-02', '2023-09-09', 2, 10);

INSERT INTO tb_multa (valor, forma_pagamento,locacao_id) VALUES(100, 0, 1);
INSERT INTO tb_multa (valor, forma_pagamento,locacao_id) VALUES(100, 0, 2);
INSERT INTO tb_multa (valor, forma_pagamento,locacao_id) VALUES(100, 0, 3);
INSERT INTO tb_multa (valor, forma_pagamento,locacao_id) VALUES(100, 0, 4);
INSERT INTO tb_multa (valor, forma_pagamento,locacao_id) VALUES(100, 0, 5);
INSERT INTO tb_multa (valor, forma_pagamento,locacao_id) VALUES(100, 0, 6);
INSERT INTO tb_multa (valor, forma_pagamento,locacao_id) VALUES(100, 0, 7);
INSERT INTO tb_multa (valor, forma_pagamento,locacao_id) VALUES(100, 0, 8);
INSERT INTO tb_multa (valor, forma_pagamento,locacao_id) VALUES(100, 0, 9);

