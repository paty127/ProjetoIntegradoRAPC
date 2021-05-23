package controller;

import dao.DisciplinaDao;
import dao.ProfessorDao;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

import model.Professor;


@WebServlet(name = "professores", urlPatterns = {"/professorController"})
public class ProfessorController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String INSERT_Professor = "/cadastroProfessor";
    private static final String EDIT = "/editarProfessor";

    
    private static final String LIST_ALUNO = "/listarProfessor";
    
    private ProfessorDao dao;

    private  DisciplinaDao disDAO;

    public ProfessorController() {
        dao = new ProfessorDao();
        disDAO = new DisciplinaDao();
    }




    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
         String forward = "";
        //Pegar o parametro de Action 
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int codProfessor = Integer.parseInt(request.getParameter("codProfessor"));
            try {
                dao.deletarProfessor(codProfessor);
                //RequestDispatcher delete = request.getRequestDispatcher("/professorDelete.jsp");
                //delete.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            forward = LIST_ALUNO;
            try {
                request.setAttribute("professores", dao.getAllProfessor());
            } catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("edit")) {
            forward = EDIT;
            int codProfessor = Integer.parseInt(request.getParameter("codProfessor"));
            try {
                //Passar o codigo de professor para o metodo getProfessorByID
                Professor professor = dao.getProfessorById(codProfessor);
                request.setAttribute("professor", professor);

            } catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("ListProfessor")) {
            forward = LIST_ALUNO;
            try {
                //Criando um atributo chamado Professors e inserindo a lista que veio do metodo getAllProfessors
                request.setAttribute("professores", dao.getAllProfessor());
            } catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            forward = INSERT_Professor;
            try {
                request.setAttribute("disciplinas", disDAO.getAllDisciplinas());
            } catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        int numero;
        int codProfessor;
        boolean temErro = false;
        Professor professor = new Professor();

        LocalDate dataNascimento = null;
        if (!request.getParameter("dataNascimento").equals("")) {
            dataNascimento = LocalDate.parse(request.getParameter("dataNascimento"));
        } else {
            dataNascimento = null;
            request.setAttribute("erroData", "Data não informada.");
        }
        professor.setDataNasc(dataNascimento);
        if (request.getParameter("numero").equals("")) {
            numero = 0;
        } else {
            numero = Integer.parseInt(request.getParameter("numero"));
            professor.setNumero(Integer.parseInt(request.getParameter("numero")));
        }

        String nome = (request.getParameter("nome"));
        String sexo = (request.getParameter("sexo"));
        String rg = (request.getParameter("rg"));
        String cpf = (request.getParameter("cpf"));
        String celular = (request.getParameter("celular"));
        String email = (request.getParameter("email"));
        String disciplina1 = (request.getParameter("disciplina1"));
        String disciplina2 = (request.getParameter("disciplina2"));
        String senha = (request.getParameter("senha"));
        String senha_repetida = (request.getParameter("senhaRepetida"));
        String perfil = (request.getParameter("perfil"));
        String rua = (request.getParameter("rua"));
        String complemento = (request.getParameter("complemento"));
        String bairro = (request.getParameter("bairro"));
        String cep = (request.getParameter("cep"));
        if (request.getParameter("codProfessor").equals("")) {
            codProfessor = 0;
        } else {
            codProfessor = Integer.parseInt(request.getParameter("codProfessor"));
        }
        professor.setNome(request.getParameter("nome"));
        professor.setSexo(request.getParameter("sexo"));
        professor.setRg(request.getParameter("rg"));
        professor.setCpf(request.getParameter("cpf"));
        professor.setCelular(request.getParameter("celular"));
        professor.setEmail(request.getParameter("email"));
        professor.setDisciplina1(request.getParameter("disciplina1"));
        professor.setDisciplina2(request.getParameter("disciplina2"));
        professor.setSenha(request.getParameter("senha"));
        professor.setSenha_repetida(request.getParameter("senhaRepetida"));
        professor.setPerfil(request.getParameter("perfil"));
        professor.setRua(request.getParameter("rua"));
        professor.setComplemento(request.getParameter("complemento"));
        professor.setBairro(request.getParameter("bairro"));
        professor.setCep(request.getParameter("cep"));

        // VALIDAÇÕES
        if (nome.equals("")) {
            temErro = true;
            request.setAttribute("erroNome", "Nome não informado.");
        }
        if (sexo.equals("")) {
            temErro = true;
            request.setAttribute("erroSexo", "Gênero não informado.");
        }
        if (dataNascimento == null) {
            temErro = true;
            request.setAttribute("erroData", "Data não informada.");
        }
        if (rg.equals("")) {
            temErro = true;
            request.setAttribute("erroRG", "RG não informado.");
        }
        if (cpf.equals("")) {
            temErro = true;
            request.setAttribute("erroCPF", "CPF não informado.");
        }      

        if (celular.equals("")) {
            temErro = true;
            request.setAttribute("erroCelular", "Celular não informado.");
        }
        if (email.equals("")) {
            temErro = true;
            request.setAttribute("erroEmail", "E-mail não informado.");
        }
        if (rua.equals("")) {
            temErro = true;
            request.setAttribute("erroRua", "Logradouro não informado.");
        }
        if (disciplina1.equals("")) {
            temErro = true;
            request.setAttribute("errodisciplina1", "Disciplina 1 não informada.");
        }
        if (disciplina2.equals("")) {
            temErro = true;
            request.setAttribute("errodisciplina2", "Disciplina 2 não informada.");
        }
        if (disciplina1.equals(disciplina2)) {
            temErro = true;
            request.setAttribute("erroDisciplinas", "As disciplinas não podem ser iguais.");
        }
        if (senha.equals("")) {
            temErro = true;
            request.setAttribute("erroSenha", "A senha não foi informada.");
        }
        if (senha_repetida.equals("")) {
            temErro = true;
            request.setAttribute("erroSenhaRepetida", "A senha repetida não foi informada.");
        }
        if (!(senha.equals(senha_repetida))) {
            temErro = true;
            request.setAttribute("erroSenhas", "A senha e a senha repetida não "
                    + "são iguais.");
        }
        if (rua.equals("")) {
            temErro = true;
            request.setAttribute("erroRua", "Logradouro não informado.");
        }
        if (numero == 0) {
            temErro = true;
            request.setAttribute("erroNumero", "Número não informado.");
        }/*
        if (complemento.equals("")) {
            temErro = true;
            request.setAttribute("erroComplemento", "Complemento não informado.");
        }*/
        if (bairro.equals("")) {
            temErro = true;
            request.setAttribute("erroBairro", "Bairro não informado.");
        }
        if (cep.equals("")) {
            temErro = true;
            request.setAttribute("erroCep", "Cep não informado.");
        }

        Professor dados = new Professor(codProfessor,nome,sexo,dataNascimento,
               rg,cpf,celular,email,disciplina1,disciplina2,perfil,senha,
               senha_repetida,rua,numero,complemento,bairro,cep);

        request.setAttribute("dados", dados);

        if (temErro) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/professor/validacaoProfessor.jsp");
            dispatcher.forward(request, response);
        } else {
            HttpSession sessao = request.getSession();
            sessao.setAttribute("dados", dados);
            sessao.setAttribute("codProfessor", codProfessor);
            response.sendRedirect(request.getContextPath() + "/redirectProfessor");
        }
    }
}