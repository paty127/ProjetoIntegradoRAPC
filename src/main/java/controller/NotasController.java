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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "notasController", urlPatterns = {"/notasController","/selectionDisc","/registrar-frequencia"})
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

        boolean temErro = false;

        String action = request.getServletPath();

        if (action.equals("/notasController")) {
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
            request.getRequestDispatcher("/WEB-INF/jsp/registro/notas.jsp").forward(request, response);

        }
        else if (action.equals("/selectionDisc")) {
            int codTurma;
            int codDisciplina;

            if (request.getParameter("codTurma").equals("")) {
                codTurma = 0;
                temErro = true;
                request.setAttribute("erroTurma", "Turma não informada.");
            } else {
                codTurma = Integer.parseInt(request.getParameter("codTurma"));
            }
            if (request.getParameter("codDisciplina").equals("")) {
                codDisciplina = 0;
                temErro = true;
                request.setAttribute("erroDisciplina", "Disciplina não informada.");

            } else {
                codDisciplina = Integer.parseInt(request.getParameter("codDisciplina"));
            }
            if (temErro && codTurma != 0) {
                
                try {
                    request.setAttribute("turmaD", daoT.recuperaListaTurmaDifer(codTurma));
                    request.setAttribute("turmaR", daoT.recuperaTurma(codTurma));
                    request.setAttribute("Disciplinas", daoDisc.getAllDisciplinas());
                    request.setAttribute("listaTurma", daoDesp.desempenhoPorTurma(codTurma));
                    
            }   catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
                request.getRequestDispatcher("/WEB-INF/jsp/registro/notasValidacao1.jsp").forward(request, response);
            }
             else if (temErro && codDisciplina != 0) {
                
                try {
                    request.setAttribute("turmas", daoT.getAllTurmas());
                    request.setAttribute("disciplinaR", daoDisc.recuperaDisci(codDisciplina));
                    request.setAttribute("discplinaD", daoDisc.recuperaListaDisciDifer(codDisciplina));

            }   catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
                request.getRequestDispatcher("/WEB-INF/jsp/registro/notasValidacao2.jsp").forward(request, response);
            }
             else if (codTurma == 0 && codDisciplina == 0) {
                
                try {
                    request.setAttribute("turmas", daoT.getAllTurmas());
                    request.setAttribute("Disciplinas", daoDisc.getAllDisciplinas());  

            }   catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
                request.getRequestDispatcher("/WEB-INF/jsp/registro/notasValidacao.jsp").forward(request, response);
            } else {
                try {
                    request.setAttribute("turmaD", daoT.recuperaListaTurmaDifer(codTurma));
                    request.setAttribute("turmaR", daoT.recuperaTurma(codTurma));
                    request.setAttribute("listaTurmaDisciplina", daoDesp.desempenhoPorTurmaDisciplina(codTurma, codDisciplina));
                    request.setAttribute("disciplinaR", daoDisc.recuperaDisci(codDisciplina));
                    request.setAttribute("discplinaD", daoDisc.recuperaListaDisciDifer(codDisciplina));
                } catch (SQLException ex) {
                    Logger.getLogger(NotasController.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.setAttribute("codTurma", codTurma);
                request.setAttribute("codDisciplina", codDisciplina);
                request.getRequestDispatcher("/WEB-INF/jsp/registro/notas2.jsp").forward(request, response);
            }
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
                desempenho.setCod_aluno(0);
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

            //daoDesp.adicionarDesempenho(desempenho);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        int codTurma;
        int codDisciplina;
        double nota1;
        double nota2;
        double nota3;
        double nota4;
        
        boolean temErro = false;
        
        // VALIDAÇÕES
        if (request.getParameter("turmaCod") == null || request.getParameter("turmaCod").equals("")  ) {
            codTurma = 0;
            temErro = true;
            request.setAttribute("erroTurma", "Turma não informada.");
        } else {
            
            codTurma = Integer.parseInt(request.getParameter("turmaCod"));
            
        }
        if (request.getParameter("DisciplinaCod") == null || request.getParameter("DisciplinaCod").equals("")){
            codDisciplina = 0;
            temErro = true;
            request.setAttribute("erroDisciplina", "Disciplina não informada.");
        } else {

            codDisciplina = Integer.parseInt(request.getParameter("DisciplinaCod"));

        }
        if (request.getParameter("nota1") == null || request.getParameter("nota1").equals("")) {
            nota1 = 0.0;
            temErro = true;
            request.setAttribute("erroTurma", "Turma não informada.");
            request.setAttribute("erroDisciplina", "Disciplina não informada.");
        } else {
            nota1 = Double.parseDouble(request.getParameter("nota1"));
            
        }
        if (request.getParameter("nota2") == null || request.getParameter("nota2").equals("")) {
            nota2 = 0.0;
        } else {
            nota2 = Double.parseDouble(request.getParameter("nota2"));
            
        }
        if (request.getParameter("nota3") == null || request.getParameter("nota3").equals("")) {
            nota3 = 0.0;
        } else {
            nota3 = Double.parseDouble(request.getParameter("nota3"));
            
        }
        if (request.getParameter("nota4") == null || request.getParameter("nota4").equals("")) {
            nota4 = 0.0;
        } else {
            nota4 = Double.parseDouble(request.getParameter("nota4"));
            
        }

        Desempenho dados = new Desempenho(nota1,nota2,nota3,nota4);
        request.setAttribute("dados", dados);
        request.setAttribute("disciplinaID", codDisciplina);
        
        
            if (temErro && codTurma != 0) {
                
                try {
                    request.setAttribute("turmaD", daoT.recuperaListaTurmaDifer(codTurma));
                    request.setAttribute("turmaR", daoT.recuperaTurma(codTurma));
                    request.setAttribute("Disciplinas", daoDisc.getAllDisciplinas());                   
            }   catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
                request.getRequestDispatcher("/WEB-INF/jsp/registro/notasValidacao1.jsp").forward(request, response);
            }
             else if (temErro && codDisciplina != 0) {
                
                try {
                    request.setAttribute("turmas", daoT.getAllTurmas());
                    request.setAttribute("disciplinaR", daoDisc.recuperaDisci(codDisciplina));
                    request.setAttribute("discplinaD", daoDisc.recuperaListaDisciDifer(codDisciplina));

            }   catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
                request.getRequestDispatcher("/WEB-INF/jsp/registro/notasValidacao2.jsp").forward(request, response);
            }
             else if (codTurma == 0 && codDisciplina == 0) {
                
                try {
                    request.setAttribute("turmas", daoT.getAllTurmas());
                    request.setAttribute("Disciplinas", daoDisc.getAllDisciplinas());  

            }   catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
                request.getRequestDispatcher("/WEB-INF/jsp/registro/notasValidacao.jsp").forward(request, response);
            } else {
            HttpSession sessao = request.getSession();
            sessao.setAttribute("dados", dados);
            sessao.setAttribute("disciplinaID", codDisciplina);
            response.sendRedirect(request.getContextPath() + "/redirectDesempenho");
        }
        
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
