/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DesempenhoDao;
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
import model.Desempenho;



/**
 *
 * @author Alexsandro
 */
@WebServlet(name = "RedirectDesempenho", urlPatterns = {"/redirectDesempenho"})
public class RedirectDesempenho extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private final DesempenhoDao dao;

    public RedirectDesempenho() {
        dao = new DesempenhoDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession();
        int discplinaID = (int) sessao.getAttribute("codDisciplina");
        Desempenho notas = new Desempenho();
        
        if (sessao.getAttribute("dados") != null) {
            Desempenho dados = (Desempenho) sessao.getAttribute("dados");
            sessao.removeAttribute("dados");
            sessao.removeAttribute("codDisciplina");

            request.setAttribute("dados", dados);
            try {
                dao.updateDesempenho(dados,discplinaID);
                RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/jsp/registro/notasSucesso.jsp");
                    view.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(RedirectDesempenho.class.getName()).log(Level.SEVERE, null, ex);
            }
                     
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registro/notasErro.jsp");
            dispatcher.forward(request, response);
        }
    }
}