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
            
        <form name="frmContato" action="selectionDisc">
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
                <select class="" name="codDisciplina"
                    id="inputDisciplina"> 
                    <!-- Carregando o select do Banco -->
                    <option value="" />Selecione</option>
                    <c:forEach items="${Disciplinas}" var="d">
                    <option value="${d.disciplinaID}" />${d.nome}</option>
                    </c:forEach>
                </select>
                <input role="button" aria-pressed="true" type="submit" value="Pesquisar" />
        </form>    
            
            
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
                            <th scope="row">
                                
                                <c:out value="${aluno.codAluno}" />
                                
                            </th>
                                
                            

                            <!-- Nome do Aluno -->

                            <td>
                                
                                <c:out value="${aluno.nome}" />
                                
                            </td>
                    </c:forEach>
                            <!-- Nota 1 -->
                    <c:forEach items="${desempenho}" var="nota">        
                            <td>
                                 <c:out value="${nota.nota1}" />
                            </td>

                            <!-- Nota 2 -->

                            <td>
                                <c:out value="${nota.nota2}" />
                            </td>

                            <!-- Nota 3 -->

                            <td>
                                <c:out value="${nota.nota2}" />
                            </td>

                            <!-- Nota 4 -->

                            <td>
                                <c:out value="${nota.nota2}" />
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
          
    </body>
</html>
