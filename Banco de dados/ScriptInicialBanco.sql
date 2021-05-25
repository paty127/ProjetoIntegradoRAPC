create database ProjetoRAPC;
/*
CREATE USER 'CyberSchool'@'localhost' IDENTIFIED BY 'CyberSchool2021';
GRANT ALL PRIVILEGES ON ProjetoRAPC. * TO 'CyberSchool'@'localhost';
FLUSH PRIVILEGES;
*/
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
CREATE TABLE Adm (
    cod_adm integer not null auto_increment PRIMARY KEY,
    nome varchar(30),
    sexo varchar(9),
    data_nascimento date,
    rg varchar(13),
    cpf varchar(16),
    celular varchar(15),
    email varchar(40),
    senha varchar(60) not null,
    senha_repetida varchar(60) not null,
	perfil varchar(14),
    fk_endereco integer,
    FOREIGN KEY (fk_endereco) REFERENCES endereco (id_endereco)
);
CREATE TABLE Professor (
    cod_professor integer not null auto_increment PRIMARY KEY,
    nome varchar(50),
    sexo varchar(9),
    data_nascimento date,
    rg varchar(13),
    cpf varchar(16),
    celular varchar(15),  
    email varchar(40),
	senha varchar(60) not null,
    senha_repetida varchar(60) not null,
    perfil varchar(9),
    fk_endereco integer,
    FOREIGN KEY (fk_endereco) REFERENCES endereco (id_endereco)
);
CREATE TABLE Turma (
    cod_turma integer not null auto_increment PRIMARY KEY,
    serie varchar(3),
    fk_cod_aluno integer,
	foreign key (fk_cod_aluno) references Aluno(cod_aluno)
);

select * from Turma;

SELECT turma.fk_cod_aluno
FROM Turma
INNER JOIN aluno
ON turma.fk_cod_aluno=aluno.cod_aluno,aluno.nome;
CREATE TABLE Disciplinas (
    disciplinaID integer not null auto_increment PRIMARY KEY,
    nome varchar(30),
    cargahoraria integer
);
CREATE TABLE Ministrante (
    fk_cod_professor integer,
    fk_disciplinaID integer,
    FOREIGN KEY (fk_cod_professor) REFERENCES Professor (cod_professor),
    FOREIGN KEY (fk_disciplinaID) REFERENCES Disciplinas (disciplinaID)
);

CREATE TABLE Grade (
    fk_cod_turma integer,
    fk_disciplinaID integer,
    FOREIGN KEY (fk_cod_turma) REFERENCES turma (cod_turma),
    FOREIGN KEY (fk_disciplinaID) REFERENCES disciplinas (disciplinaID)
);

CREATE TABLE Desempenho (
	desempenhoID integer not null auto_increment PRIMARY KEY,
    nota1 decimal,
    nota2 decimal,
    nota3 decimal,
    nota4 decimal,
    frequencia int,
    fk_disciplinaID int,
    fk_cod_aluno int,
    FOREIGN KEY (fk_disciplinaID) REFERENCES Disciplinas (disciplinaID),
    FOREIGN KEY (fk_cod_aluno) REFERENCES Aluno (cod_aluno)
);
   
insert into turma(serie)values
				 ('1ºA'),('1ºB'),
                 ('2ºA'),('2ºB'),
                 ('3ºA'),('3ºB'),
                 ('4ºA'),('4ºB'),
				 ('5ºA'),('5ºB');
        
insert into Disciplinas(nome,cargahoraria)values
						("Português",80),
                        ("Matemática",80),
                        ("História",80),
                        ("Geografia",80),
                        ("Ciências",40),
                        ("Fisíca",120),
                        ("Artes",80),
                        ("Inglês",80);   
                        
insert into grade(fk_cod_turma,fk_disciplinaID)values
	/*1ºA*/(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),
    /*1ºB*/(2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),
	/*2ºA*/(3,1),(3,2),(3,3),(3,4),(3,5),(3,6),(3,7),
	/*2ºB*/(4,1),(4,2),(4,3),(4,4),(4,5),(4,6),(4,7),
	/*3ºA*/(5,1),(5,2),(5,3),(5,4),(5,5),(5,6),(5,7),
	/*3ºB*/(6,1),(6,2),(6,3),(6,4),(6,5),(6,6),(6,7),
	/*4ºA*/(7,1),(7,2),(7,3),(7,4),(7,5),(7,6),(7,7),
	/*4ºB*/(8,1),(8,2),(8,3),(8,4),(8,5),(8,6),(8,7),
	/*5ºA*/(9,1),(9,2),(9,3),(9,4),(9,5),(9,6),(9,7),
	/*5ºB*/(10,1),(10,2),(10,3),(10,4),(10,5),(10,6),(10,7);
					

delimiter $$
create procedure novo_aluno (
	nome varchar(50),cpf varchar(15),data_nascimento date,sexo varchar(9),celular varchar(15),email varchar(40),
    pai varchar(30),telefone_pai varchar(15),mae varchar(30),telefone_mae varchar(15),
    rua varchar(30),numero integer,complemento varchar (30),bairro varchar(30),cep varchar(9))
    begin
    insert into endereco (rua,numero,complemento,bairro,cep) values (rua,numero,complemento,bairro,cep);
    insert into aluno(nome,cpf,data_nascimento,sexo,celular,email,pai,telefone_pai,mae,telefone_mae,fk_endereco)values(nome,cpf,data_nascimento,sexo,celular,email,pai,telefone_pai,mae,telefone_mae,@@identity);
    end $$

 /* Exemplo de criação do usuário com a procedure
call novo_aluno ('Fulano','111.1111.111-11','1988/08/14','Masculino','1196291-0587','fulano@msn.com','Pai', '11962910587','Mãe','11962910587','Avenida Circular', 113,'Apto52 Bloco6','Jardim Raposo','05547-025');

Exemplo de consula:
select *
FROM aluno as A
JOIN endereco as E on A.fk_endereco = E.id_endereco;
*/
   
create procedure novo_adm (
nome varchar(50),sexo varchar(9),data_nascimento date,
rg varchar(13),cpf varchar(16),celular varchar(15),  
email varchar(40),senha varchar(60), senha_repetida varchar(60),perfil varchar(14),
rua varchar(30),numero integer,complemento varchar(30),bairro varchar(30),cep varchar(9))
    begin
	insert into Endereco(rua,numero,complemento,bairro,cep)values(rua,numero,complemento,bairro,cep);
    insert into Adm(nome,sexo,data_nascimento,rg,cpf,celular,email,perfil,senha,senha_repetida,fk_endereco)values(nome,sexo,data_nascimento,rg,cpf,celular,email,perfil,senha,senha_repetida,@@identity);
    end $$
 /*Exemplo de criação do usuário com a procedure
call novo_adm ('Fulano','Masculino','1988/08/14','11.111.111-1','111.1111.111-11','1196291-0587','fulano@msn.com','12345678','12345678','administrativo','Avenida Circular', 113,'Apto52 Bloco6','Jardim Raposo','05547-025');

Exemplo de consula:
select *
FROM Adm as A
JOIN endereco as E on A.fk_endereco = E.id_endereco;
*/   
create procedure novo_professor (
nome varchar(50),sexo varchar(9),data_nascimento date,
rg varchar(13),cpf varchar(16),celular varchar(15),  
email varchar(40),senha varchar(60), senha_repetida varchar(60),perfil varchar(9),
rua varchar(30),numero integer,complemento varchar(30),bairro varchar(30),cep varchar(9),fk_disciplinaID integer,fk_disciplina2ID integer)
    begin
	insert into Endereco(rua,numero,complemento,bairro,cep)values(rua,numero,complemento,bairro,cep);
    insert into Professor(nome,sexo,data_nascimento,rg,cpf,celular,email,perfil,senha,senha_repetida,fk_endereco)values(nome,sexo,data_nascimento,rg,cpf,celular,email,perfil,senha,senha_repetida,@@identity);
    insert into ministrante(fk_cod_professor, fk_disciplinaID)values(@@identity, fk_disciplinaID);
    insert into ministrante(fk_cod_professor, fk_disciplinaID)values(@@identity, fk_disciplina2ID);
    end $$

/*Exemplo de criação do Professor com a procedure
call novo_professor ('Fulano','Masculino','1988/08/14','11.111.111-1','111.1111.111-11','1196291-0587','fulano@msn.com','12345678','12345678','Professor','Avenida Circular', 113,'Apto52 Bloco6','Jardim Raposo','05547-025',5,2,5,4);

Exemplo de consula:
select *
FROM professor as A
JOIN endereco as E on A.fk_endereco = E.id_endereco;
*/




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
select *
FROM professor as A
JOIN endereco as E on A.fk_endereco = E.id_endereco;

*/