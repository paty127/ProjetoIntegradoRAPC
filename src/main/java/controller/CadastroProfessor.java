/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DisciplinaDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alexsandro
 */
@WebServlet(name = "CadastroProfessor", urlPatterns = {"/cadastroProfessor"})
public class CadastroProfessor extends HttpServlet {
    
        private  DisciplinaDao disDAO;

    public CadastroProfessor() {
        disDAO = new DisciplinaDao();
    }

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            request.setAttribute("disciplinas", disDAO.getAllDisciplinas());
            request.getRequestDispatcher("/WEB-INF/jsp/professor/cadastroProfessor.jsp")
            .forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}