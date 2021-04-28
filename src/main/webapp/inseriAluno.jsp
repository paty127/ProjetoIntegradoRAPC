<%-- 
    Document   : inseriAluno
    Created on : 28 de abr de 2021, 04:39:10
    Author     : Alex
--%>
<%@page import = "model.Aluno"%>
<%@page import = "dao.AlunoDAO2"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            try {
                Aluno aluno = new Aluno();
                AlunoDAO2 alu = new AlunoDAO2();
                if (request.getParameter("nome").equals("")){
                    out.print("<script>");
                    out.print("alert('Você não preencheu o campo Nome');");
                    out.print("</script>");
                }else if(request.getParameter("dataNascimento").equals("")){
                    out.print("<script>");
                    out.print("alert('Você não preencheu o campo dataNascimento');");
                    out.print("</script>");
                            
                }else if(request.getParameter("NomePai").equals("")){
                    out.print("<script>");
                    out.print("alert('Você não preencheu o campo NomePai');");
                    out.print("</script>");
                }else if(request.getParameter("NomeMae").equals("")){
                    out.print("<script>");
                    out.print("alert('Você não preencheu o campo NomeMae');");
                    out.print("</script>");
                }else if(request.getParameter("email").equals("")){
                    out.print("<script>");
                    out.print("alert('Você não preencheu o campo email');");
                    out.print("</script>");
                }else if(request.getParameter("CelularAluno").equals("")){
                    out.print("<script>");
                    out.print("alert('Você não preencheu o campo CelularAluno');");
                    out.print("</script>");
                }else if(request.getParameter("celularPai").equals("")){
                    out.print("<script>");
                    out.print("alert('Você não preencheu o campo celularPai');");
                    out.print("</script>");
                }else if(request.getParameter("celularMae").equals("")){
                    out.print("<script>");
                    out.print("alert('Você não preencheu o campo celularMae');");
                    out.print("</script>");
                }else if(request.getParameter("genero").equals("")){
                    out.print("<script>");
                    out.print("alert('Você não preencheu o campo genero');");
                    out.print("</script>");
                }else{
                    aluno.setNome(request.getParameter("nome"));
                    aluno.setNome(request.getParameter("dataNascimento"));
                    aluno.setNome(request.getParameter("NomePai"));
                    aluno.setNome(request.getParameter("NomeMae"));
                    aluno.setNome(request.getParameter("email"));
                    aluno.setNome(request.getParameter("CelularAluno"));
                    aluno.setNome(request.getParameter("celularPai"));
                    aluno.setNome(request.getParameter("celularMae"));
                    aluno.setNome(request.getParameter("genero"));
                    alu.inserir(aluno);
                    out.print("<script>");
                    out.print("alert('Dados inseridos com sucesso!');");
                    out.print("</script>");
                    response.sendRedirect("CadastroAluno.jsp");
                }                    
                } catch (Exception erro) {
                    throw new RuntimeException("Erro ao cadastrar o aluno" +erro);
                }
        %>
    </body>
</html>
