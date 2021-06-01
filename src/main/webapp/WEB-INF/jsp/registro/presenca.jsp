<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page buffer="8192kb" autoFlush="true" %>
<!DOCTYPE html>
<html>
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css"
              href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="http://www.godtur.no/godtur/js/jquery-ui-1.8.18.custom.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>REGISTRO DE PRESENÇA</title>
    </head>
    <body>
        <div class="container">

            <h1>Registrar Presença</h1>

            <form  name="frmNotas" action="${request.contextPath}selecao" var="form1">
                
                <label for="Turma">Turma</label>
                <select class="custom-select mr-sm-11" name="codTurma"
                        id="inputTurma">

                    <!-- Carregando o select do Banco -->
                    <option value="" />Selecione</option>
                    <c:forEach items="${turmas}" var="turma">
                        <option value="${turma.turmaID}" />${turma.serie}</option>
                    </c:forEach>

                </select>
                
                <br>

                <label for="Disciplina">Disciplina</label><br/>
                <select class="custom-select mr-sm-1" name="codDisciplina"
                        id="inputDisciplina">
                    <!-- Carregando o select do Banco -->
                    <option value="" />Selecione</option>
                    <c:forEach items="${Disciplinas}" var="d">
                        <option value="${d.disciplinaID}" />${d.nome}</option>
                    </c:forEach>
                </select>
                <button type="submit" value="form1">Pesquisar</button>
            </form>

            <form method="POST"  name="frmNotas" action="${request.contextPath}FrequenciaController" var="form1">
                <table border="1">
                    <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Nome</th>
                        <th scope="col">Presente</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- atributo Alunos vem da classe AlunoControlles na linha 65  -->
                    <c:forEach items="${alunos}" var="alunos">
                        <tr>

                            <th scope="row">

                                <label class="form-control" name="codAluno[${idx}]">
                                    <c:out value="${alunos.codAluno}" />
                                </label>

                            </th>


                            <td>

                                <c:out value="${alunos.nome}" />

                            </td>

                            <!-- Nota 1 -->

                            <td>
                                <input type="radio" name="presenca" value="1"/>
                            </td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <button type="submit" value="principal">Enviar</button>
            </form>
        </div>
    </body>

</html>
