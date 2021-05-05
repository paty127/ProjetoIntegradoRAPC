<%-- 
    Document   : index
    Created on : 28 de abr. de 2021, 12:26:21
    Author     : Carlos Pavão
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!--Redireciona para pagina de listagem-->
        <a href="<jsp:forward page="AlunoController?action=ListAluno"/>">adicionar aluno</a>
    </body>
</html>
