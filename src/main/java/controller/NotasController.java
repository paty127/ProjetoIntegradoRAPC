/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AlunoDao;
import dao.DesempenhoDao;
import dao.DisciplinaDao;
import dao.TurmaDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "notasController", urlPatterns = {"/notasController","/selectionDisc"})
public class NotasController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final DisciplinaDao daoDisc;
    private final TurmaDao daoT;
    private final AlunoDao daoA;
    private final DesempenhoDao daoDesp;
    

        public NotasController() {
            daoT = new TurmaDao();
            daoDisc = new DisciplinaDao();
            daoA = new AlunoDao();
            daoDesp = new DesempenhoDao();
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
                request.setAttribute("Disciplinas", daoDisc.getAllDisciplinas());
            } catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Redirecionar(request,response);
            
            
        }else if(action.equals("/selectionDisc")){
            int codTurma = Integer.parseInt(request.getParameter("codTurma"));
            int codDisciplina = Integer.parseInt(request.getParameter("codDisciplina"));

            try {
                ListaAlunos(request,response,codTurma);
            } catch (SQLException ex) {
                Logger.getLogger(NotasController.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                ListaDesempenho(request,response,codTurma,codDisciplina);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            Redirecionar(request,response);


        }else{
            response.sendRedirect("home.jsp");
        }
    }
    
    
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        int codTurma = Integer.parseInt(request.getParameter("codTurma"));
            
        try {
            ListaAlunos(request,response,codTurma);
        } catch (SQLException ex) {
            Logger.getLogger(NotasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Redirecionar(request,response);
            
    }

    protected void Redirecionar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Direcionando para tela JSP.
        request.getRequestDispatcher("/WEB-INF/jsp/registro/notas.jsp").forward(request, response);
    }

    protected void ListaAlunos(HttpServletRequest request, HttpServletResponse response,int codTurma)
            throws ServletException, IOException, SQLException {
        //Criando um atributo chamado Alunos e inserindo a lista que veio do metodo getAllAlunos
        request.setAttribute("alunos", daoT.listarAlunosDaTurma(codTurma));
    }

    protected void ListaDesempenho(HttpServletRequest request, HttpServletResponse response,int codTurma,int codDisciplina)
            throws ServletException, IOException, SQLException {

            request.setAttribute("desempenho", daoDesp.DesempenhoDisciplinaTurma(codTurma,codDisciplina));
    }
    
}