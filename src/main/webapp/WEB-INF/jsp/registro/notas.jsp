<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

             <c:forEach items="${disciplinas}" var="disciplina">
                <select name="disciplina">
                    <option><c:out value="${disciplina.nome}" /></option>
                    <option></option>
                </select>
            </c:forEach>
            <a href="selecaoTurma">Pesquisar</a>
        
    </body>
</html>
