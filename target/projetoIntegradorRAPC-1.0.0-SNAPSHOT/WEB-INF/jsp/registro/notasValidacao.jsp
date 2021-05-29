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
        <title>Registrar Nota</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/erro.css" />
    </head>
    <body>
        <div class="container">
            <h1>Registrar Notas</h1>
            
                <form name="frmPesqInicial" action="selectionDisc">
                    <label for="Turma">Turma</label>
                    <select class="custom-select mr-sm-11" name="codTurma"
                            id="inputTurma" >

                        <!-- Carregando o select do Banco -->
                        <option value="" />Selecione</option>
                        <c:forEach items="${turmas}" var="turma">
                            <option value="${turma.turmaID}" />${turma.serie}</option>
                        </c:forEach>
                    </select>
                    <c:if test="${not empty erroTurma}">
                        <span class="msg-erro"><c:out value="${erroTurma}" /></span>
                    </c:if>
                    <br>

                    <!-- Select de Disciplina   -->

                    <label for="Disciplina">Disciplina</label><br/>
                    <select class="custom-select mr-sm-1" name="codDisciplina"
                            id="inputDisciplina">
                        <!-- Carregando o select do Banco -->
                        <option value="">Selecione</option>
                        <c:forEach items="${Disciplinas}" var="d">
                            <option value="${d.disciplinaID}" />${d.nome}</option>
                        </c:forEach>
                    </select>
                    <c:if test="${not empty erroDisciplina}">
                        <span class="msg-erro"><c:out value="${erroDisciplina}" /></span>
                    </c:if>
                    <br/>
                    <input role="button" aria-pressed="true" type="submit" value="Pesquisar" />
                </form>
            
                <form method="POST"  name="frmNotas" action="${request.contextPath}notasController" var="idx">
                <table border="1">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Nome</th>
                            <th scope="col">Nota 1</th>
                            <th scope="col">Nota 2</th>
                            <th scope="col">Nota 3</th>
                            <th scope="col">Nota 4</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!<!-- atributo Alunos vem da classe AlunoControlles na linha 65  -->
                        <c:forEach items="${alunos}" var="alunos">

                            <tr>

                                <!-- Código do Aluno -->
                                <th scope="row">

                                    <label class="form-control" name="codAluno">
                                        <c:out value="${alunos.codAluno}" />
                                    </label>

                                </th>



                                <!-- Nome do Aluno -->

                                <td>

                                    <c:out value="${alunos.nome}" />

                                </td>

                                <!-- Nota 1 -->

                                <td>
                                    <input class="form-control"  id="nota1" type="text" name="nota1"
                                           onkeypress="$(this).mask('00.0')"placeholder="00.0">
                                </td>

                                <!-- Nota 2 -->

                                <td>
                                    <input class="form-control"  id="nota2" type="text" name="nota2"
                                           onkeypress="$(this).mask('00.0')"placeholder="00.0">
                                </td>

                                <!-- Nota 3 -->

                                <td>
                                    <input class="form-control"  id="nota3" type="text" name="nota3"
                                           onkeypress="$(this).mask('00.0')"placeholder="00.0">
                                </td>

                                <!-- Nota 4 -->

                                <td>
                                    <input class="form-control"  id="nota4" type="text" name="nota4"
                                           onkeypress="$(this).mask('00.0')"placeholder="00.0">
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <button type="submit">Enviar</button>
            </form>
        </form>

    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>
    <script type="text/javascript">
                                               $("#nota1, #nota2,#nota3,#nota4").mask("00.0");
    </script>
</body>
</html>
