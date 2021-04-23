create database ProjetoRAPC;

USE ProjetoRAPC;

/* Banco Logico: */
CREATE TABLE Aluno (
    cod_aluno integer not null auto_increment PRIMARY KEY,
    nome varchar(50),
    data_de_nascimento date,
    sexo varchar(2),
    pai varchar(30),
    mae varchar(30),
    celular varchar(15),
    telefone_pai varchar(15),
    telefone_mae varchar(15),
    email varchar(40)
);
CREATE TABLE Turma (
    cod_turma integer not null auto_increment PRIMARY KEY,
    serie varchar(3),
    horario varchar(50),
    turno varchar(5),
    fk_cod_aluno integer,
	foreign key (fk_cod_aluno) references Aluno(cod_aluno)
);

CREATE TABLE Professor (
    cod_professor integer not null auto_increment PRIMARY KEY,
    nome varchar(50),
    data_de_nascimento date,
    sexo varchar(2),
    celular varchar(15),
    telefone varchar(13),
    cpf varchar(11),
    rg varchar(15),
    email varchar(40)
);



CREATE TABLE Disciplinas (
    cod_disciplina integer not null auto_increment PRIMARY KEY,
    nome varchar(30),
    cargahoraria integer
);

CREATE TABLE Funcionario (
    cod_funcionario integer not null auto_increment PRIMARY KEY,
    Nome varchar(30),
    Sobrenome varchar(30),
    Data_de_Nascimento date,
    Sexo varchar(2),
    Celular varchar(13),
    Telefone varchar(10),
    CPF varchar(11),
    RG varchar(14),    
    fk_cod_professor integer,
    FOREIGN KEY (fk_cod_professor) references Professor(cod_professor)
);


CREATE TABLE Endereco (
    cod_endereco integer not null auto_increment PRIMARY KEY,
    rua varchar(30),
    numero integer,
    bairro varchar(30),
    cep varchar(9),
    fk_cod_professor integer,
    fk_cod_funcionario integer,
    fk_Aluno_cod_aluno integer,
    FOREIGN KEY (fk_cod_professor) REFERENCES Professor (cod_professor),
    FOREIGN KEY (fk_cod_funcionario) REFERENCES Funcionario (cod_funcionario),
	FOREIGN KEY (fk_Aluno_cod_aluno) REFERENCES Aluno (cod_aluno)
);
CREATE TABLE Ministrante (
    codigo int not null auto_increment PRIMARY KEY,
    fk_cod_professor int,
    fk_cod_disciplina int,
    FOREIGN KEY (fk_cod_professor) REFERENCES Professor (cod_professor),
    FOREIGN KEY (fk_cod_disciplina) REFERENCES Disciplinas (cod_disciplina)
);

CREATE TABLE Grade (
    codigo_grade integer not null auto_increment PRIMARY KEY,
    fk_cod_turma integer,
    fk_cod_disciplina integer,
    FOREIGN KEY (fk_cod_turma) REFERENCES Turma(cod_turma),
    FOREIGN KEY (fk_cod_disciplina) REFERENCES Disciplinas(cod_disciplina)
);

CREATE TABLE Desempenho (
    Codigo int PRIMARY KEY,
    notas decimal,
    frequencia int,
    fk_cod_disciplina int,
    fk_cod_aluno int,
    FOREIGN KEY (fk_cod_disciplina) REFERENCES Disciplinas (cod_disciplina),
	FOREIGN KEY (fk_cod_aluno) REFERENCES Aluno (cod_aluno)
);