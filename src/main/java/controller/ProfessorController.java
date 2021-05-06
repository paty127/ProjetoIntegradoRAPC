/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProfessorDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Aluno;
import model.Professor;

/**
 *
 * @author Carlos Pavão
 */
@WebServlet(name = "Professores", urlPatterns = {"/ProfessorController"})
public class ProfessorController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/adicionarEditarProfessor.jsp";
    private static String LIST_PROFESSOR = "/listarProfessor.jsp";
    private ProfessorDao dao;
    
    public ProfessorController(){
        super();
        dao = new ProfessorDao();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String forward="";
        //Pegar o parametro de Action 
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int codProfessor = Integer.parseInt(request.getParameter("codProfessor"));
            try {
                dao.deletarProfessor(codProfessor);
            } catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            forward = LIST_PROFESSOR;
            try {
                request.setAttribute("Professores", dao.getAllProfessor());
            } catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT; 
            int codProfessor = Integer.parseInt(request.getParameter("codProfessor"));
            try {
                //Passar o codigo de aluno para o metodo getAlunoByID
                Professor professor = dao.getProfessorById(codProfessor);
                request.setAttribute("professor",professor);

            } catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }      
        } else if (action.equalsIgnoreCase("ListProfessor")){
            forward = LIST_PROFESSOR;
            try {
                //Criando um atributo chamado Alunos e inserindo a lista que veio do metodo getAllAlunos
                request.setAttribute("alunos", dao.getAllProfessor());
            } catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        //Redireciona  para Forward
        view.forward(request, response);
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        Professor professor = new Professor();
        
        professor.setNome(request.getParameter("nome"));
        
        try {
            Date data_de_nascimento = null;
            String teste = request.getParameter("data_de_nascimento");
            
            if(request.getParameter("data_de_nascimento")!=null){
                data_de_nascimento = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("data_de_nascimento"));
            }
            else{
                data_de_nascimento = null;
            }

            professor.setDataNasc(data_de_nascimento);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        professor.setSexo(request.getParameter("sexo"));
        professor.setCelular(request.getParameter("celular"));
        professor.setTelefone(request.getParameter("telefone"));
        professor.setCpf(request.getParameter("cpf"));
        professor.setRg(request.getParameter("rg"));
        professor.setEmail(request.getParameter("email"));
        professor.setRua(request.getParameter("rua"));
        professor.setNumero(Integer.parseInt(request.getParameter("numero")));
        professor.setBairro(request.getParameter("bairro"));
        professor.setCep(request.getParameter("cep"));
        
        String codProfessor = request.getParameter("codProfessor");
        
        if(codProfessor == null || codProfessor.isEmpty())
        {
            try {
                dao.adicionarProfessor(professor);
            } catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            professor.setCodProfessor(Integer.parseInt(codProfessor));
            try {
                dao.updateProfessor(professor);
            } catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_PROFESSOR);
        try {
            request.setAttribute("Professores", dao.getAllProfessor());
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        view.forward(request, response);
    }
}


