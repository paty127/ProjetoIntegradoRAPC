/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AlunoDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Aluno;

/**
 *
 * @author Alexsandro
 */
@WebServlet(name = "ResultadoAluno", urlPatterns = {"/resultado"})
public class ResultadoAluno extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/adicionarEditarAluno.jsp";
    private static String LIST_ALUNO = "/listarAluno.jsp";
    private AlunoDao dao;

    public ResultadoAluno() {
        super();
        dao = new AlunoDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        int numero;
        int codAluno = (int) sessao.getAttribute("codAluno");
        Aluno aluno = new Aluno();
        
        /*
        aluno.setNome(request.getParameter("nome"));
        aluno.setNomePai(request.getParameter("pai"));
        aluno.setNomeMae(request.getParameter("mae"));
        aluno.setCelular(request.getParameter("celular"));
        aluno.setSexo(request.getParameter("sexo"));
        aluno.setCelularPai(request.getParameter("celularPai"));
        aluno.setCelularMae(request.getParameter("celularMae"));
        aluno.setEmail(request.getParameter("email"));
        aluno.setRua(request.getParameter("rua"));
        aluno.setBairro(request.getParameter("bairro"));
        aluno.setCep(request.getParameter("cep"));
        */
        System.out.println(codAluno);
        if (sessao.getAttribute("dados") != null) {
            Aluno dados = (Aluno) sessao.getAttribute("dados");
            sessao.removeAttribute("dados");

            request.setAttribute("dados", dados);
            if (codAluno == 0) {
                try {
                    dao.adicionarAluno(dados);
                    RequestDispatcher view = request.getRequestDispatcher("/alunoSucesso.jsp");
                } catch (SQLException ex) {
                    Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                aluno.setCodAluno(codAluno);
                try {
                    dao.updateAluno(dados);
                    RequestDispatcher view = request.getRequestDispatcher("/alunoSucesso.jsp");
                } catch (SQLException ex) {
                    Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
            try {
                request.setAttribute("alunos", dao.getAllAlunos());
            } catch (SQLException ex) {
                Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/alunoSucesso.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/alunoErro.jsp");
            dispatcher.forward(request, response);
        }
    }
}