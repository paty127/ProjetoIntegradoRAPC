<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1">
            <thead>
                <tr>
                    <th>Codigo do Aluno</th>
                    <th>Nome</th>
                    <th>Data de nascimento</th>
                    <th>sexo</th>
                    <th>Nome do Pai</th>
                    <th>Nome da Mãe</th>
                    <th>celular</th>
                    <th>telefone do pai</th>
                    <th>telefone da Mãe</th>
                    <th>E-mail</th>
                    <th colspan="2">Ação</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${alunos}" var="aluno">
                    <tr>
                        <td><c:out value="${aluno.codAluno}" /></td>
                        <td><c:out value="${aluno.nome}" /></td>
                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${aluno.dataNasc}" /></td>
                        <td><c:out value="${aluno.sexo}" /></td>
                        <td><c:out value="${aluno.nomePai}" /></td>
                        <td><c:out value="${aluno.nomeMae}" /></td>
                        <td><c:out value="${aluno.celular}" /></td>
                        <td><c:out value="${aluno.celularPai}" /></td>
                        <td><c:out value="${aluno.celularMae}" /></td>
                        <td><c:out value="${aluno.email}" /></td>
                        <td><a href="AlunoController?action=edit&CodAluno=<c:out value="${aluno.codAluno}" />">Editar </td>
                        <td><a href="AlunoController?action=delete&CodAluno=<c:out value="${aluno.codAluno}" />">deletar</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p><a href="AlunoController?action=insert">Add aluno</a></p>
    </body>
</html>
