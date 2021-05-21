<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css"
              href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="http://www.godtur.no/godtur/js/jquery-ui-1.8.18.custom.min.js"></script>
        <title>Editar  cadastro de Adm</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/erro.css" />
    </head>
    <body>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
        <script>
            $(function () {
                $('input[name=data_de_nascimento]').datepicker();
            });
        </script>
    <nav class="navbar navbar-dark bg-dark  navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="${pageContext.request.contextPath}"><img src="Imagens/logo.png" alt="some text" width=120 height=60>CyberSchool</a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#conteudoNavbarSuportado" aria-controls="conteudoNavbarSuportado" aria-expanded="false" aria-label="Alterna navegação">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="conteudoNavbarSuportado">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Listagem
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="professorController?action=ListAlunos">Alunos Matriculados</a>
                    <a class="dropdown-item" href="professorController?action=ListProfessor">Lista Professores</a>
                    <a class="dropdown-item" href="professorController?action=ListAdm">Lista de Adm's</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Cadastro
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/cadastroAluno">Cadastrar Aluno</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/cadastroProfessor">Cadastrar Professor</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/cadastroAdm">Cadastrar Adm</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Algo mais aqui</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#">Desativado</a>
            </li>
            </ul>
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder="Pesquisar" aria-label="Pesquisar">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Pesquisar</button>
            </form>
        </div>
    </nav>
    <div class="container">
        <h4>Dados do Adm</h4>
        <form method="POST" action='${request.contextPath}admController' name="frmAddUser">
            <div class="form-row">
                    <div class="form-group col-md-1">
                        <label>Matrícula</label>
                        <input type="text" class="form-control" readonly="readonly" name="codAdm"
                               value="<c:out value="${adm.codAdm}" />" >
                    </div>
                    <div class="form-group col-md-2" id="matricula">
                    <label for="inputPerfil">Perfil</label><br/> 
                    <select class="custom-select mr-sm-2" name="perfil"
                        id="inputPerfil" value="<c:out value="${adm.perfil}" />"> 
                        <option value="Adm">Adm</option>
                    </select>
                    </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-3">   
                    <!-- Nome -->
                    <label for="inputNome">Nome</label>
                    <input class="form-control"  id="inputNome" type="text" name="nome"
                           value="<c:out value="${adm.nome}" />" >
                </div>
                <div class="form-group col-md-2">
                    <!-- Gênero -->
                    <label for="inputGenero">Gênero</label><br/> 
                    <select class="custom-select mr-sm-2" name="sexo"
                        id="inputGenero" value="<c:out value="${adm.sexo}" />"> 
                        <option value="${adm.sexo}">${adm.sexo}</option>
                        <option value="Masculino">Masculino</option>
                        <option value="Feminino">Feminino</option>
                    </select>
                </div>
                <div class="form-group col-md-2">    
                    <!-- Data -->
                    <label for="inputData">Data de Nascimento</label> 
                    <input class="form-control"  id="inputData" type="date" 
                           maxlength="10" name="dataNascimento" 
                           value="<c:out value="${adm.dataNasc}" />" >
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-2">
                    <label for="inputRG">RG</label>
                    <input type="text" class="form-control" id="inputRG" name="rg"
                           value="<c:out value="${adm.rg}" />" 
                           placeholder="XXX.XXX.XXX-X"
                           onkeypress="$(this).mask('00.000.000-0');">
                </div>
                <div class="form-group col-md-2">
                    <label for="inputCPFAdm">CPF</label>
                    <input type="text" class="form-control" id="inputCPFAdm" name="cpf"
                           value="<c:out value="${adm.cpf}" />" 
                           placeholder="XXX.XXX.XXX-XX"
                           onkeypress="$(this).mask('000.000.000-00');">
                </div>
                <div class="form-group col-md-2">
                    <label for="inputCelularAdm">Celular</label>
                    <input class="form-control" id="inputCelularAdm"type="text" 
                           onkeypress="$(this).mask('(00) 00000-0000')""
                           name="celular" value="<c:out value="${adm.celular}" />" 
                           placeholder="(XX) XXXXX-XXXX">
                </div>
                <div class="form-group col-md-3">
                    <!-- E-mail -->
                    <label for="inputEmail">E-mail</label>
                    <input type="email" class="form-control" id="inputEmail"
                           name="email" value="<c:out value="${adm.email}" />" >
                </div>
            </div>
            <div class="form-row">

            </div>
            <h4>Senha de acesso</h4>    
            <div class="form-row">
                <div class="form-group col-md-3">   
                    <!-- Nome -->
                    <label for="senha">Senha</label>
                    <input class="form-control"  id="inputSenha" type="password" name="senha"
                           placeholder="XXXXXXXX" maxlength="8" value="<c:out value="${adm.senha}" />"> 
                </div>
                <div class="form-group col-md-2">
                    <label for="senhaRepetida">Repita a senha</label>
                    <input class="form-control" id="inputSenhaRepetida"type="password" 
                           name="senhaRepetida" maxlength="8"
                           placeholder="XXXXXXXX" value="<c:out value="${adm.senha_repetida}" />">
                </div>
            </div>
            <h4>Endereço</h4>
            <div class="form-row">
                <div class="form-group col-md-3">
                    <label for="inputLogradouro">Logradouro</label>
                    <input class="form-control" id="inputLogradouro" type="text" 
                           name="rua" value="<c:out value="${adm.rua}" />">
                </div>
                <div class="form-group col-md-1">
                    <label for="inputNumero">Nº</label>
                    <input class="form-control" id="inputNumero" type="text" 
                           name="numero" value="<c:out value="${adm.numero}" />">
                </div>
                <div class="form-group col-md-2">
                    <label for="inputComplemento">Complemento</label>
                    <input class="form-control" id="inputComplemento" type="text" 
                           name="complemento" value="<c:out value="${adm.complemento}" />">
                </div>
            </div>
                <div class="form-row">
                <div class="form-group col-md-2">
                    <label for="inputBairro">Bairro</label>
                    <input class="form-control" id="inputBairro" type="text" 
                           name="bairro" value="<c:out value="${adm.bairro}" />">
                </div>
                <div class="form-group col-md-2">
                    <label for="inputCEP">CEP</label>
                    <input class="form-control" id="inputCEP" type="text" name="cep"
                           onkeypress="$(this).mask('00000-000')"
                           placeholder="XXXXX-XXX"
                           value="<c:out value="${adm.cep}" />" >
                </div>
                </div>
            </div>
            <div class="container">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <input class="btn btn-primary btn-lg active" role="button" aria-pressed="true" type="submit" value="Enviar" />     
                    </div>
                </div>
            </div>
        </form>
    </div>
</body>
</html>