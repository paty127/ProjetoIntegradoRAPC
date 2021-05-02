<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link type="text/css"
    href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.js"></script>
<script type="text/javascript" src="http://www.godtur.no/godtur/js/jquery-ui-1.8.18.custom.min.js"></script>
<title>Add new user</title>
</head>
<body>
    <script>
        $(function() {
            $('input[name=data_de_nascimento]').datepicker();
        });
    </script>

    <form method="POST" action='${request.contextPath}AlunoController' name="frmAddUser">
        Codigo Aluno : <input type="text" readonly="readonly" name="codAluno"
            value="<c:out value="${aluno.codAluno}" />" /> <br /> 
        Nome : <input
            type="text" name="nome"
            value="<c:out value="${aluno.nome}" />" /> <br /> 
        Data : <input
            type="text" placeholder="dd/MM/yyyy" name="data_de_nascimento" data-date-format="dd/MM/yyyy"
            value="<fmt:formatDate pattern="dd/MM/yyyy" value="${aluno.dataNasc}" />" /> <br />
        Sexo : <input
            type="text" name="sexo"
            value="<c:out value="${aluno.sexo}" />" /> <br /> 
        Celular aluno : <input
            type="text" name="pai"
            value="<c:out value="${aluno.celular}" />" /> <br /> 
        Email : <input type="text" name="email"
            value="<c:out value="${aluno.email}" />" /> <br />
        Nome Mãe : <input
            type="text" name="mae"
            value="<c:out value="${aluno.nomeMae}" />" /> <br /> 
        Telefone Mae : <input
            type="text" name="celularMae"
            value="<c:out value="${aluno.celularMae}" />" /> <br /> 
        Nome Pai : <input
            type="text" name="pai"
            value="<c:out value="${aluno.nomePai}" />" /> <br /> 
        Telefone Pai : <input
            type="text" name="CelularPai"
            value="<c:out value="${aluno.celularPai}" />" /> <br />
        <input
            type="submit" value="Submit" />
    </form>
</body>
</html>