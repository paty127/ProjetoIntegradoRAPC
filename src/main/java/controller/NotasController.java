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
import model.Desempenho;
import model.Turma;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "notasController", urlPatterns = {"/notasController","/selectionDisc","/insertNota"})
public class NotasController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final DisciplinaDao daoDisc;
    private final TurmaDao daoT;
    private final AlunoDao daoA;
    private final DesempenhoDao daoDesp;
    Desempenho desempenho = new Desempenho();
    

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
                request.setAttribute("alunos", daoT.listarAlunosDaTurma(codTurma));
            } catch (SQLException ex) {
                Logger.getLogger(NotasController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Redirecionar(request,response);
        }else if(action.equals("/insertNota")){
            inserirDesempenho(request,response);
            Redirecionar(request,response);
        }else{
            response.sendRedirect("home.jsp");
        }
    }

    private void inserirDesempenho(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int qtdeTurma = 0;
        int codTurma = Integer.parseInt(request.getParameter("codTurma"));

        try {
             qtdeTurma = daoT.SelecionarDesempenho(codTurma).getQte();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
        if (qtdeTurma == 0){
            qtdeTurma = 20;
        }else{
            qtdeTurma = 20 - qtdeTurma;
        }

        for (int idx = 0; idx< qtdeTurma; idx++){
            String codAlunoStr = request.getParameter("codAluno["+idx+"]");
            String nota1Str = request.getParameter("nota1["+idx+"]");
            String nota2Str = request.getParameter("nota2["+idx+"]");
            String nota3Str = request.getParameter("nota3["+idx+"]");
            String nota4Str = request.getParameter("nota4["+idx+"]");

            desempenho.setCodDisciplina(Integer.parseInt(request.getParameter("codDisciplina")));
            if (codAlunoStr.equals(null)){
                desempenho.setCodAluno(0);
            }else {
                desempenho.setCodDisciplina(Integer.parseInt(codAlunoStr));
            }
            if( nota1Str.equals(null)){
                desempenho.setNota1(0);
            }else {
                desempenho.setNota1(Integer.parseInt(nota1Str));
            }
            if( nota2Str.equals(null)){
                desempenho.setNota2(0);
            }else {
                desempenho.setNota2(Integer.parseInt(nota2Str));
            }
            if( nota3Str.equals(null)){
                desempenho.setNota3(0);
            }else {
                desempenho.setNota3(Integer.parseInt(nota2Str));
            }
            if( nota4Str.equals(null)){
                desempenho.setNota4(0);
            }else {
                desempenho.setNota4(Integer.parseInt(nota2Str));
            }

            daoDesp.adicionarDesempenho(desempenho);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        int codTurma = Integer.parseInt(request.getParameter("codTurma"));
        Desempenho desempenho = new Desempenho();
        Turma turma = new Turma();
        double nota1;
        double nota2;
        double nota3;
        double nota4;
        int codAluno;
        int qtdeTurma = 0;

        try {
            ListaAlunos(request,response,codTurma);
        } catch (SQLException ex) {
            Logger.getLogger(NotasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Redirecionar(request,response);

        int codDisciplina = Integer.parseInt(request.getParameter("codDisciplina"));

        inserirDesempenho(request,response);
            
    }



    protected void Redirecionar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Direcionando para tela JSP.
        request.getRequestDispatcher("/WEB-INF/jsp/registro/notas.jsp").forward(request, response);
    }

    protected void ListaAlunos(HttpServletRequest request, HttpServletResponse response,int codTurma)
            throws ServletException, IOException, SQLException {
        //Criando um atributo chamado Alunos e inserindo a lista que veio do metodo getAllAlunos
        for(int i =0; i<=daoT.listarAlunosDaTurma(codTurma).toArray().length; i++) {
            
        }
        Redirecionar(request,response);
    }

    protected void ListaDesempenho(HttpServletRequest request, HttpServletResponse response,int codTurma,int codDisciplina)
            throws ServletException, IOException, SQLException {

            request.setAttribute("desempenho", daoDesp.DesempenhoDisciplinaTurma(codTurma,codDisciplina));
    }
    
}
