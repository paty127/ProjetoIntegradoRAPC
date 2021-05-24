/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AlunoDao;
import dao.DisciplinaDao;
import dao.TurmaDao;
import model.Disciplina;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet(name = "notasController", urlPatterns = {"/notasController","/selecaoTurma"})
public class NotasController extends HttpServlet {
    
    private final DisciplinaDao daoD;
    private final TurmaDao daoT;
    private final AlunoDao daoA;
    

        public NotasController() {
            daoT = new TurmaDao();
            daoD = new DisciplinaDao();
            daoA = new AlunoDao();
        }
        
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getServletPath();
        
        if(action.equals("/notasController")){
            //Captando informações do banco para o Select
            try {
                request.setAttribute("turmas", daoT.getAllTurmas());
            } catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                request.setAttribute("Disciplinas", daoD.getAllDisciplinas());
            } catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Redirecionar(request,response);
            double codTurma = Double.parseDouble("inputTurma");
            double codDisciplina = Double.parseDouble("inputDisciplina");
        }
    }
    
    
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
    }
    
    //Listar Alunos
    protected void Redirecionar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Direcionando para tela JSP.
        request.getRequestDispatcher("/WEB-INF/jsp/registro/notas.jsp").forward(request, response);
    }
    protected void ListaAlunos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
}
