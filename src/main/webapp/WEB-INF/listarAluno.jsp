<%-- 
    Document   : listarAluno
    Created on : 28 de abr. de 2021, 09:59:22
    Author     : Carlos Pavão
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <thead>
            <tr>        
                <th>Codigo do Aluno</th>
                <th>Nome</th>
                <th>Data nascimento</th>
                <th>Sexo</th>
                <th>Nome do Pai</th>
                <th>Nome da Mãe</th>
                <th>Celular</th>
                <th>Celular do pai</th>
                <th>Celular da mãe</th>
                <th>E-mail</th>
                <th colspan="2">Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${aluno}" var="aluno">
                <tr>
                    <td><c:out value="${aluno.cod_aluno}" /></td>
                    <td><c:out value="${aluno.nome}" /></td>
                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${aluno.data_de_nascimento}" /></td>
                    <td><c:out value="${aluno.sexo}" /></td>
                    <td><c:out value="${aluno.pai}" /></td>
                    <td><c:out value="${aluno.mae}" /></td>
                    <td><c:out value="${aluno.celular}" /></td>
                    <td><c:out value="${aluno.telefone_pai}" /></td>
                    <td><c:out value="${aluno.telefone_mae}" /></td>
                    <td><c:out value="${aluno.email}" /></td>
                    <td><a href="UserController?action=edit&userId=<c:out value="${aluno.cod_aluno}"/>">Update</a></td>
                    <td><a href="UserController?action=delete&userId=<c:out value="${aluno.cod_aluno}"/>">Delete</a></td>
                </tr>
            </c:forEach>
    </body>
</html>
