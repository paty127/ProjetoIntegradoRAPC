create database ProjetoRAPC;

CREATE USER 'CyberSchool'@'localhost' IDENTIFIED BY 'CyberSchool2021';
GRANT ALL PRIVILEGES ON ProjetoRAPC. * TO 'CyberSchool'@'localhost';
FLUSH PRIVILEGES;

USE ProjetoRAPC;

/* Banco Logico: */
CREATE TABLE Endereco (
    id_endereco integer not null auto_increment PRIMARY KEY,
    rua varchar(30),
    numero integer,
    complemento varchar(30),
    bairro varchar(30),
    cep varchar(9)
);
CREATE TABLE Aluno (
    cod_aluno integer not null auto_increment PRIMARY KEY,
    nome varchar(50),
    cpf varchar(16),
    data_nascimento date,
    sexo varchar(9),
    celular varchar(15),
    email varchar(40),
    pai varchar(30),
    telefone_pai varchar(15),
    mae varchar(30),
    telefone_mae varchar(15),
    fk_endereco integer,
    FOREIGN KEY (fk_endereco) REFERENCES endereco (id_endereco)
);
delimiter $$
create procedure novo_aluno (
	nome varchar(50),cpf varchar(15),data_de_nascimento date,sexo varchar(2),celular varchar(15),email varchar(40),
    pai varchar(30),telefone_pai varchar(15),mae varchar(30),telefone_mae varchar(15),
    rua varchar(30),numero integer,complemento varchar (30),bairro varchar(30),cep varchar(9))
    begin
    insert into endereco (rua,numero,complemento,bairro,cep) values (rua,numero,complemento,bairro,cep);
    insert into aluno(nome,cpf,data_de_nascimento,sexo,celular,email,pai,telefone_pai,mae,telefone_mae,fk_endereco)values(nome,cpf,data_de_nascimento,sexo,celular,email,pai,telefone_pai,mae,telefone_mae,@@identity);
    end $$
    
/* Exemplo de criação do usuário com a procedure
call novo_aluno ('Fulano','111.1111.111-11','1988/08/14','M','1196291-0587','fulano@msn.com','Pai', '11962910587','Mãe','11962910587','Avenida Circular', 113,'Jardim Raposo','05547-025');

Exemplo de consula:
select *
FROM aluno as A
JOIN endereco as E on A.fk_endereco = E.id_endereco;

*/

CREATE TABLE Turma (
    cod_turma integer not null auto_increment PRIMARY KEY,
    serie varchar(3),
    horario varchar(50),
    turno varchar(5),
    fk_cod_aluno integer,
	foreign key (fk_cod_aluno) references Aluno(cod_aluno)
);

CREATE TABLE Funcionario (
    id_funcionario integer not null auto_increment PRIMARY KEY,
    nome varchar(30),
    sobrenome varchar(30),
    data_Nascimento date,
    sexo varchar(9),
    celular varchar(13),
    telefone varchar(10),
    cpf varchar(16),
    rg varchar(13),
    senha varchar(60) not null,
    senha_repetida varchar(60) not null,
    cargo varchar(10) not null,
    fk_endereco integer,
    FOREIGN KEY (fk_endereco) REFERENCES endereco (id_endereco)
);

CREATE TABLE Professor (
    cod_professor integer not null auto_increment PRIMARY KEY,
    nome varchar(50),
    data_nascimento date,
    sexo varchar(9),
    celular varchar(15),
    telefone varchar(13),
    cpf varchar(16),
    rg varchar(13),
    email varchar(40),
    fk_endereco integer,
    fk_funcionario integer,
    FOREIGN KEY (fk_endereco) REFERENCES endereco (id_endereco)
    FOREIGN KEY (fk_funcionario) references funcionario(id_funcionario)
);
delimiter $$
create procedure novo_professor (
	nome varchar(50),data_nascimento date,sexo varchar(9),celular varchar(15),telefone varchar(13),
    cpf varchar(16),rg varchar(13),email varchar(40),
    rua varchar(30),numero integer,complemento varchar(30),bairro varchar(30),cep varchar(9))
    begin
	insert into Endereco(rua,numero,complemento,bairro,cep)values(rua,numero,complemento,bairro,cep);
    insert into Professor(nome,data_nascimento,sexo,celular,telefone,cpf,rg,email,fk_endereco)values(nome,data_nascimento,sexo,celular,telefone,cpf,rg,email,@@identity);
    end $$
CREATE TABLE Disciplinas (
    cod_disciplina integer not null auto_increment PRIMARY KEY,
    nome varchar(30),
    cargahoraria integer
);
CREATE TABLE Ministrante (
    codigo int not null auto_increment PRIMARY KEY,
    fk_professor integer,
    fk_disciplina integer,
    /*FOREIGN KEY (fk_professor) REFERENCES Professor (cod_professor)*/
    FOREIGN KEY (fk_disciplina) REFERENCES Disciplinas (cod_disciplina)
);
CREATE TABLE Grade (
    codigo_grade integer not null auto_increment PRIMARY KEY,
    fk_cod_turma integer,
    fk_cod_disciplina integer,
    /*FOREIGN KEY (fk_cod_turma) REFERENCES Turma(cod_turma)*/
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
/* Exemplos de consultas
SELECT * from endereco;
SELECT * from aluno;

select *
FROM aluno as A
JOIN endereco as E on A.fk_endereco = E.id_endereco;

DELETE aluno,endereco FROM aluno INNER JOIN endereco ON aluno.fk_endereco = endereco.id_endereco WHERE cod_aluno = 2;

select * FROM aluno INNER JOIN endereco on aluno.fk_endereco = endereco.id_endereco WHERE cod_aluno = 2;

select * FROM aluno JOIN endereco ON aluno.fk_endereco = endereco.id_endereco WHERE cod_aluno = 4;

UPDATE FROM aluno set aluno.sexo = 'M' JOIN endereco ON aluno.fk_endereco = endereco.id_endereco WHERE cod_aluno = 4;

UPDATE aluno INNER JOIN endereco ON aluno.fk_endereco = endereco.id_endereco 
aluno.sexo = 'M' where cod_aluno = 4;

UPDATE aluno INNER JOIN endereco ON aluno.fk_endereco = id_endereco SET endereco.numero = 100 WHERE cod_aluno = 7;

*/
/*
use projetorapc;
drop database projetorapc;

select *
FROM aluno as A
JOIN endereco as E on A.fk_endereco = E.id_endereco;
*/