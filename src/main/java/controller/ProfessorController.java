package controller;

import dao.ProfessorDao;
import java.io.IOException;
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
import model.Professor;

@WebServlet(name = "Professores", urlPatterns = {"/ProfessorController"})
public class ProfessorController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/adicionarEditarProfessor.jsp";
    private static String LIST_PROFESSOR = "/listarProfessor.jsp";
    private ProfessorDao dao;

    public ProfessorController() {
        super();
        dao = new ProfessorDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String forward = "";
        //Pegar o parametro de Action 
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
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
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int codProfessor = Integer.parseInt(request.getParameter("codProfessor"));
            try {
                //Passar o codigo de aluno para o metodo getAlunoByID
                Professor professor = dao.getProfessorById(codProfessor);
                request.setAttribute("professor", professor);

            } catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("ListProfessor")) {
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

        int numero;
        int codProfessor;
        boolean temErro = false;
        Professor professor = new Professor();

        Date dataNascimento = null;
        try {
            if (!request.getParameter("dataNascimento").equals("")) {
                dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dataNascimento"));
            } else {
                dataNascimento = null;
            }

            professor.setDataNasc(dataNascimento);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (request.getParameter("numero").equals("")) {
            numero = 0;
        } else {
            numero = Integer.parseInt(request.getParameter("numero"));
            professor.setNumero(Integer.parseInt(request.getParameter("numero")));
        }
        String nome = (request.getParameter("nome"));
        String sexo = (request.getParameter("sexo"));
        String email = (request.getParameter("email"));
        String celular = (request.getParameter("celular"));
        String telefone = (request.getParameter("telefone"));
        String cpf = (request.getParameter("cpf"));
        String rg = (request.getParameter("rg"));
        String rua = (request.getParameter("rua"));
        String bairro = (request.getParameter("bairro"));
        String cep = (request.getParameter("cep"));

        if (request.getParameter("codProfessor").equals("")) {
            codProfessor = 0;
        } else {
            codProfessor = Integer.parseInt(request.getParameter("codProfessor"));
        }
        professor.setNome(request.getParameter("nome"));
        professor.setSexo(request.getParameter("sexo"));
        professor.setCelular(request.getParameter("celular"));
        professor.setTelefone(request.getParameter("telefone"));
        professor.setCpf(request.getParameter("cpf"));
        professor.setRg(request.getParameter("rg"));
        professor.setEmail(request.getParameter("email"));
        professor.setRua(request.getParameter("rua"));
        professor.setBairro(request.getParameter("bairro"));
        professor.setCep(request.getParameter("cep"));

        //validações
        if (nome.equals("")) {
            temErro = true;
            request.setAttribute("erroNome", "Nome não informado.");
        }
        if (dataNascimento == null) {
            temErro = true;
            request.setAttribute("erroData", "Data não informada.");
        }
        if (sexo.equals("")) {
            temErro = true;
            request.setAttribute("erroSexo", "Gênero não informado.");
        }
        if (celular.equals("")) {
            temErro = true;
            request.setAttribute("erroCelular", "Celular não informado.");
        }
        if (telefone.equals("")) {
            temErro = true;
            request.setAttribute("erroTelefone", "Telefone não informado.");
        }
        if (email.equals("")) {
            temErro = true;
            request.setAttribute("erroEmail", "E-mail não informado.");
        }
        if (cpf.equals("")) {
            temErro = true;
            request.setAttribute("erroCPF", "Cpf não informado.");
        }
        if (rg.equals("")) {
            temErro = true;
            request.setAttribute("erroRG", "RG não informado.");
        }
        if (rua.equals("")) {
            temErro = true;
            request.setAttribute("erroRua", "Logradouro não informado.");
        }
        if (numero == 0) {
            temErro = true;
            request.setAttribute("erroNumero", "Número não informado.");
        }
        if (bairro.equals("")) {
            temErro = true;
            request.setAttribute("erroBairro", "Bairro não informado.");
        }
        if (cep.equals("")) {
            temErro = true;
            request.setAttribute("erroCep", "Cep não informado.");
        }

        Professor dados = new Professor(codProfessor, nome, dataNascimento, sexo, celular, telefone, cpf, rg, email, rua, numero, bairro, cep);

        request.setAttribute("dados", dados);

        if (temErro) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/validacaoProfessor.jsp");
            dispatcher.forward(request, response);
        } else {
            if (codProfessor == 0) {
                try {
                    dao.adicionarProfessor(dados);
                    RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
                } catch (SQLException ex) {
                    Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                professor.setCodProfessor(codProfessor);
                try {
                    dao.updateProfessor(professor);
                } catch (SQLException ex) {
                    Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
            try {
                request.setAttribute("professores", dao.getAllProfessor());
            } catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            view.forward(request, response);
        }
    }
}



