/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AlunoDao;
import dao.ProfessorDao;
import java.io.IOException;
import java.sql.SQLException;
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
import model.Professor;

/**
 *
 * @author Alexsandro
 */
@WebServlet(name = "RedirectProfessor", urlPatterns = {"/redirectProfessor"})
public class RedirectProfessor extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private static final String INSERT_OR_EDIT = "/cadastroProfessor";
    private static final String LIST_ALUNO = "/listarProfessor";
    private final ProfessorDao dao;

    public RedirectProfessor() {
        dao = new ProfessorDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        int numero;
        int codProfessor = (int) sessao.getAttribute("codProfessor");
        Professor professor = new Professor();
        
        if (sessao.getAttribute("dados") != null) {
            Professor dados = (Professor) sessao.getAttribute("dados");
            sessao.removeAttribute("dados");

            request.setAttribute("dados", dados);
            
            if (codProfessor == 0) {
                try {
                    dao.adicionarProfessor(dados);
                    RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/jsp/aluno/alunoSucesso.jsp");
                    view.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                professor.setCodProfessor(codProfessor);
                try {
                    dao.updateProfessor(dados);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/aluno/alunoSucessoEd.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            RequestDispatcher view = request.getRequestDispatcher(LIST_ALUNO);
            
            try {
                request.setAttribute("alunos", dao.getAllProfessor());
            } catch (SQLException ex) {
                Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/aluno/alunoErro.jsp");
            dispatcher.forward(request, response);
        }
    }
}