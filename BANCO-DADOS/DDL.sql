-- ===================================================================
-- SCRIPT COMPLETO PARA BANCO DE DADOS DE BARBEARIA (FOCO EM AGENDAMENTO)
-- ===================================================================

-- Apaga e recria o banco para um ambiente limpo
DROP DATABASE IF EXISTS BARBEARIA;
CREATE DATABASE BARBEARIA;
USE BARBEARIA;

-- Cria um usuário específico para a aplicação, por segurança
DROP USER IF EXISTS 'barbearia'@'localhost';
CREATE USER 'barbearia'@'localhost' IDENTIFIED BY 'barbearia@1234';
GRANT SELECT, INSERT, UPDATE, DELETE ON BARBEARIA.* TO 'barbearia'@'localhost';

-- -------------------------------------------------------------------
-- TABELAS DE ESTRUTURA BÁSICA
-- -------------------------------------------------------------------

-- Tabela para definir os papéis (Cliente, Barbeiro, Admin)
CREATE TABLE TIPO_USUARIO (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NOME VARCHAR(50) NOT NULL UNIQUE
);

-- Tabela central de usuários
CREATE TABLE USUARIO (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    ID_TIPO INT NOT NULL,
    NOME VARCHAR(120) NOT NULL,
    EMAIL VARCHAR(100) NOT NULL UNIQUE,
    SENHA VARCHAR(255) NOT NULL,
    TELEFONE VARCHAR(15),
    DATA_CRIACAO TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    DATA_ATUALIZACAO TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (ID_TIPO) REFERENCES TIPO_USUARIO(ID)
);

-- Tabela com o catálogo de serviços oferecidos
CREATE TABLE SERVICO (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NOME VARCHAR(100) NOT NULL,
    DESCRICAO TEXT,
    PRECO DECIMAL(10,2) NOT NULL,
    DURACAO_MINUTOS INT NOT NULL, -- Essencial para calcular o fim do horário
    DATA_CRIACAO TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- -------------------------------------------------------------------
-- TABELAS DE CONTROLE DE DISPONIBILIDADE DO BARBEIRO
-- -------------------------------------------------------------------

-- Tabela para definir a jornada de trabalho padrão de cada barbeiro
CREATE TABLE HORARIO_TRABALHO (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    ID_BARBEIRO INT NOT NULL,
    DIA_SEMANA INT NOT NULL, -- 0=Domingo, 1=Segunda, ..., 6=Sábado
    HORA_INICIO TIME NOT NULL,
    HORA_FIM TIME NOT NULL,
    INICIO_INTERVALO TIME, -- Opcional, para horário de almoço
    FIM_INTERVALO TIME,
    FOREIGN KEY (ID_BARBEIRO) REFERENCES USUARIO(ID),
    UNIQUE(ID_BARBEIRO, DIA_SEMANA) -- Um barbeiro só tem um horário por dia da semana
);

-- Tabela para bloqueios específicos (férias, consultas médicas, etc.)
CREATE TABLE BLOQUEIO_AGENDA (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    ID_BARBEIRO INT NOT NULL,
    DATA_INICIO DATETIME NOT NULL,
    DATA_FIM DATETIME NOT NULL,
    MOTIVO VARCHAR(255),
    FOREIGN KEY (ID_BARBEIRO) REFERENCES USUARIO(ID)
);


-- -------------------------------------------------------------------
-- TABELAS PRINCIPAIS DE OPERAÇÃO (AGENDAMENTO E FEEDBACK)
-- -------------------------------------------------------------------

-- Tabela de agendamentos, agora com o campo STATUS
CREATE TABLE AGENDAMENTO (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    ID_CLIENTE INT NOT NULL,
    ID_BARBEIRO INT NOT NULL,
    ID_SERVICO INT NOT NULL,
    DATA_CORTE DATE NOT NULL,
    HORA_CORTE TIME NOT NULL,
    -- O Status é vital para gerenciar o fluxo do agendamento
    STATUS ENUM(
        'AGENDADO',
        'CONFIRMADO',
        'CONCLUIDO',
        'CANCELADO_CLIENTE',
        'CANCELADO_BARBEIRO',
        'NAO_COMPARECEU'
    ) NOT NULL DEFAULT 'AGENDADO',
    PRECO_COBRADO DECIMAL(10,2) NOT NULL,
    OBSERVACOES TEXT,
    DATA_CRIACAO TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ID_CLIENTE) REFERENCES USUARIO(ID),
    FOREIGN KEY (ID_BARBEIRO) REFERENCES USUARIO(ID),
    FOREIGN KEY (ID_SERVICO) REFERENCES SERVICO(ID),
    UNIQUE (ID_BARBEIRO, DATA_CORTE, HORA_CORTE) -- Evita agendamento duplo no mesmo horário
);

-- Tabela para que o cliente possa avaliar o serviço após a conclusão
CREATE TABLE AVALIACAO (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    ID_AGENDAMENTO INT NOT NULL UNIQUE, -- Uma avaliação por agendamento
    NOTA INT NOT NULL, -- de 1 a 5
    COMENTARIO TEXT,
    DATA_CRIACAO TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ID_AGENDAMENTO) REFERENCES AGENDAMENTO(ID)
);
