<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page buffer="8192kb" autoFlush="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Teste</h1>
                   
            <label for="Turma">Turma</label><br/> 
                <select class="custom-select mr-sm-2" name="codTurma"
                    id="inputTurma"> 

                    <!-- Carregando o select do Banco -->

                    <option value="" />Selecione</option>
                    <c:forEach items="${turmas}" var="turma">
                    <option value="${turma.turmaID}" />${turma.serie}</option>
                    </c:forEach>
                </select>
                <br>

                <!-- Select de Disciplina   -->

                <label for="Turma">Disciplina</label><br/> 
                <select class="custom-select mr-sm-2" name="Disciplina"
                    id="inputDisciplina"> 


                    <!-- Carregando o select do Banco -->
                    <option value="" />Selecione</option>
                    <c:forEach items="${Disciplinas}" var="d">
                    <option value="${d.disciplinaID}" />${d.nome}</option>
                    </c:forEach>
                </select>
            
            <input role="button" aria-pressed="true" type="submit" value="Pesquisar" />
            
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Nota 1</th>
                        <th>Nota 2</th>
                        <th>Nota 3</th>
                        <th>Nota 4</th>
                    </tr>
                </thead>
                <tbody>
                    <!<!-- atributo Alunos vem da classe AlunoControlles na linha 65  -->
                    <c:forEach items="${alunos}" var="aluno">
                        <tr>

                            <!-- Código do Aluno -->
                            <td>
                                <th scope="row"><c:out value="${aluno.codAluno}" /></th>
                            </td>

                            <!-- Nome do Aluno -->

                            <td>
                                <td><c:out value="${aluno.nome}" /></td>
                            </td>
                        </c:forEach>
                            <!-- Nota 1 -->

                            <td>
                                <input class="form-control" id="inputComplemento" type="text" 
                               name="complemento" value="<c:out value="${professor.complemento}" />">
                            </td>

                            <!-- Nota 2 -->

                            <td>
                                <input class="form-control" id="inputComplemento" type="text" 
                               name="complemento" value="<c:out value="${professor.complemento}" />">
                            </td>

                            <!-- Nota 3 -->

                            <td>
                                <input class="form-control" id="inputComplemento" type="text" 
                               name="complemento" value="<c:out value="${professor.complemento}" />">
                            </td>

                            <!-- Nota 4 -->

                            <td>
                                <input class="form-control" id="inputComplemento" type="text" 
                               name="complemento" value="<c:out value="${professor.complemento}" />">
                            </td>
                        </tr>
                    
                </tbody>
            </table> 
    </body>
</html>
