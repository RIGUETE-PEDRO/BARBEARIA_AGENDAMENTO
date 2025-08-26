-- TIPOS DE USUÁRIO
INSERT INTO TIPO_USUARIO(ID,CARGO) VALUES(1,'Administrador');
INSERT INTO TIPO_USUARIO(ID,CARGO) VALUES(2,'Barbeiro');
INSERT INTO TIPO_USUARIO(ID,CARGO) VALUES(3,'Cliente');

-- BARBEARIAS
INSERT INTO BARBEARIA(NOME, ENDERECO, TELEFONE) VALUES
('Barbearia Central','Rua Principal, 100','27999998888'),
('Barbearia do Bairro','Av. Secundária, 50','27998887777');

-- USUÁRIOS (Admin, Barbeiros e Cliente)
INSERT INTO USUARIO(ID_TIPO, ID_BARBEARIA, NOME, EMAIL, SENHA, TELEFONE) VALUES
(1, NULL, 'Pedro Henrique Riguete', 'riguetepedro0@gmail.com', '$2a$10$Kvez0.NTEYKugaFrpV2/xOh7nEYcrH5M4EW/W6PW2YVRca1yT28QC', '28999534886'), -- Administrador
/*SENHA 12345678*/
(2, 1, 'Carlos Silva', 'carlos@central.com', '$2a$10$Kvez0.NTEYKugaFrpV2/xOh7nEYcrH5M4EW/W6PW2YVRca1yT28QC', '27999991111'), -- Barbeiro Barb Central
(2, 2, 'Marcos Souza', 'marcos@bairro.com', '$2a$10$Kvez0.NTEYKugaFrpV2/xOh7nEYcrH5M4EW/W6PW2YVRca1yT28QC', '27998882222'), -- Barbeiro Barb Bairro
(3, NULL, 'Ana Paula', 'ana@gmail.com', '$2a$10$Kvez0.NTEYKugaFrpV2/xOh7nEYcrH5M4EW/W6PW2YVRca1yT28QC', '27997776666'), -- Cliente
(3, NULL, 'João Pedro', 'joao@gmail.com', '$2a$10$Kvez0.NTEYKugaFrpV2/xOh7nEYcrH5M4EW/W6PW2YVRca1yT28QC', '27996665555'); -- Cliente

-- SERVIÇOS
INSERT INTO SERVICO(ID_BARBEARIA, NOME, DESCRICAO, PRECO, DURACAO_MINUTOS) VALUES
(1,'Corte Masculino','Corte de cabelo masculino padrão', 25.00, 30),
(1,'Barba','Aparar e modelar barba', 15.00, 20),
(2,'Corte Feminino','Corte de cabelo feminino', 35.00, 45),
(2,'Coloração','Coloração e tratamento capilar', 80.00, 90);

-- HORÁRIOS DE TRABALHO
INSERT INTO HORARIO_TRABALHO(ID_BARBEIRO, ID_BARBEARIA, DIA_SEMANA, HORA_INICIO, HORA_FIM) VALUES
(2, 1, 1, '09:00:00', '18:00:00'), -- Carlos Segunda
(2, 1, 2, '09:00:00', '18:00:00'), -- Carlos Terça
(3, 2, 1, '10:00:00', '19:00:00'), -- Marcos Segunda
(3, 2, 2, '10:00:00', '19:00:00'); -- Marcos Terça
