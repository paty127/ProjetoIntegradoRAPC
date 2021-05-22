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
@WebServlet(name = "AdmDeleta", urlPatterns = {"/admDeleta"})
public class AdmDeleta extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String action = request.getParameter("action");
        int codAdm; 
        if (request.getParameter("remove").equals("")) {
            codAdm = 0;
        } else {
            codAdm = Integer.parseInt(request.getParameter("codAdm"));
        }
        
        if (action.equalsIgnoreCase("delete")) {

            HttpSession deleta = request.getSession();
            //sessao.setAttribute("dados", dados);
            deleta.setAttribute("codAdm", codAdm);
            response.sendRedirect(request.getContextPath() + "/redirectAdm");  
        } 
    }
}