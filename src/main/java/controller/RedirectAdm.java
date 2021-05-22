/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AlunoDao;
import dao.AdmDao;
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
import model.Adm;

/**
 *
 * @author Alexsandro
 */
@WebServlet(name = "RedirectAdm", urlPatterns = {"/redirectAdm"})
public class RedirectAdm extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private static final String INSERT_OR_EDIT = "/cadastroAdm";
    private static final String LIST_ALUNO = "/listarAdm";
    private final AdmDao dao;

    public RedirectAdm() {
        dao = new AdmDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        int numero;
        int codAdm = (int) sessao.getAttribute("codAdm");
        Adm adm = new Adm();
        String action = request.getParameter("action");
        
        if (sessao.getAttribute("dados") != null) {
            Adm dados = (Adm) sessao.getAttribute("dados");
            sessao.removeAttribute("dados");

            request.setAttribute("dados", dados);
            
            if (codAdm == 0) {
                try {
                    dao.adicionarAdm(dados);
                    RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/jsp/aluno/alunoSucesso.jsp");
                    view.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if(codAdm != 0){
            try {
                    dao.deletarAdm(codAdm);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adm/admSucessoExc.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
                } 
        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adm/admErro.jsp");
            dispatcher.forward(request, response);
        }
    }
}