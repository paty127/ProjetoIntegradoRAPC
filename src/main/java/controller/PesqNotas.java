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


@WebServlet(name = "PesqNotas", urlPatterns = {"/pesqNotas"})
public class PesqNotas extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final DisciplinaDao daoDisc;
    private final TurmaDao daoT;
    private final AlunoDao daoA;
    private final DesempenhoDao daoDesp;
    Desempenho desempenho = new Desempenho();
    

        public PesqNotas() {
            daoT = new TurmaDao();
            daoDisc = new DisciplinaDao();
            daoA = new AlunoDao();
            daoDesp = new DesempenhoDao();
        }
        
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        
        if (request.getParameter("codTurma").equals("")) {
            codTurma = 0;
        } else {
              codTurma = Integer.parseInt(request.getParameter("codTurma"));
            //codTurma = request.setAttribute("dados", dados);
            //codTurma.setNumero(Integer.parseInt(request.getParameter("codDisciplina")));
        }
        if (request.getParameter("codDisciplina").equals("")) {
            codDisciplina = 0;
        } else {
            codDisciplina = Integer.parseInt(request.getParameter("codDisciplina"));
            //codTurma.setNumero(Integer.parseInt(request.getParameter("codDisciplina")));
        }


        
        // VALIDAÇÕES
        if (codTurma == 0) {
            temErro = true;
            request.setAttribute("erroTurma", "Turma não informada.");
        }
        if (codDisciplina == 0) {
            temErro = true;
            request.setAttribute("erroDisciplina", "Disciplina não informada.");
        }
         

        Desempenho dados = new Desempenho(codTurma,codDisciplina);
        request.setAttribute("dados", dados);
        
        if (temErro) {
            request.getRequestDispatcher("/WEB-INF/jsp/registro/notasValidacao.jsp").forward(request, response);
        } else {
            HttpSession sessao = request.getSession();
            sessao.setAttribute("dados", dados);
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
