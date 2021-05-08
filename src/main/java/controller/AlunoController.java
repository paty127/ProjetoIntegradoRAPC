package controller;

import dao.AlunoDao;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Aluno;

@WebServlet(name = "Alunos", urlPatterns = {"/AlunoController"})
public class AlunoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/adicionarEditarAluno.jsp";
    private static String LIST_ALUNO = "/listarAluno.jsp";
    private AlunoDao dao;

    public AlunoController() {
        super();
        dao = new AlunoDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String forward = "";
        //Pegar o parametro de Action 
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int codAluno = Integer.parseInt(request.getParameter("codAluno"));
            try {
                dao.deletarAluno(codAluno);
            } catch (SQLException ex) {
                Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            forward = LIST_ALUNO;
            try {
                request.setAttribute("alunos", dao.getAllAlunos());
            } catch (SQLException ex) {
                Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int codAluno = Integer.parseInt(request.getParameter("codAluno"));
            try {
                //Passar o codigo de aluno para o metodo getAlunoByID
                Aluno aluno = dao.getAlunoById(codAluno);
                request.setAttribute("aluno", aluno);

            } catch (SQLException ex) {
                Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("ListAluno")) {
            forward = LIST_ALUNO;
            try {
                //Criando um atributo chamado Alunos e inserindo a lista que veio do metodo getAllAlunos
                request.setAttribute("alunos", dao.getAllAlunos());
            } catch (SQLException ex) {
                Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        //Redireciona  para Forward
        view.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        int numero;
        int codAluno;
        boolean temErro = false;
        Aluno aluno = new Aluno();

        LocalDate dataNascimento = null;
        if (!request.getParameter("dataNascimento").equals("")) {
            dataNascimento = LocalDate.parse(request.getParameter("dataNascimento"));
        } else {
            dataNascimento = null;
            request.setAttribute("erroData", "Data não informada.");
        }
        aluno.setDataNasc(dataNascimento);
        if (request.getParameter("numero").equals("")) {
            numero = 0;
        } else {
            numero = Integer.parseInt(request.getParameter("numero"));
            aluno.setNumero(Integer.parseInt(request.getParameter("numero")));
        }

        String nome = (request.getParameter("nome"));
        String sexo = (request.getParameter("sexo"));
        String email = (request.getParameter("email"));
        String celular = (request.getParameter("celular"));
        String nomePai = (request.getParameter("pai"));
        String celularPai = (request.getParameter("celularPai"));
        String nomeMae = (request.getParameter("mae"));
        String celularMae = (request.getParameter("celularMae"));
        String rua = (request.getParameter("rua"));
        String bairro = (request.getParameter("bairro"));
        String cep = (request.getParameter("cep"));
        if (request.getParameter("codAluno").equals("")) {
            codAluno = 0;
        } else {
            codAluno = Integer.parseInt(request.getParameter("codAluno"));
        }
        aluno.setNome(request.getParameter("nome"));
        aluno.setNomePai(request.getParameter("pai"));
        aluno.setNomeMae(request.getParameter("mae"));
        aluno.setCelular(request.getParameter("celular"));
        aluno.setSexo(request.getParameter("sexo"));
        aluno.setCelularPai(request.getParameter("celularPai"));
        aluno.setCelularMae(request.getParameter("celularMae"));
        aluno.setEmail(request.getParameter("email"));
        aluno.setRua(request.getParameter("rua"));
        aluno.setBairro(request.getParameter("bairro"));
        aluno.setCep(request.getParameter("cep"));

        // VALIDAÇÕES
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
        if (email.equals("")) {
            temErro = true;
            request.setAttribute("erroEmail", "E-mail não informado.");
        }
        if (nomeMae.equals("")) {
            temErro = true;
            request.setAttribute("erroNomeMae", "Nome da mãe não informado.");
        }
        if (celularMae.equals("")) {
            temErro = true;
            request.setAttribute("erroCelularMae", "Celular da mãe não informado.");
        }
        if (nomePai.equals("")) {
            temErro = true;
            request.setAttribute("erroNomePai", "Nome do pai não informado.");
        }
        if (celularPai.equals("")) {
            temErro = true;
            request.setAttribute("erroCelularPai", "Celular do pai não informado.");
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

        Aluno dados = new Aluno(codAluno, nome, dataNascimento, sexo, nomePai, nomeMae,
                celular, celularPai, celularMae, email, rua, numero, bairro, cep);

        request.setAttribute("dados", dados);

        if (temErro) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/validacaoAluno.jsp");
            dispatcher.forward(request, response);
        } else {
            if (codAluno == 0) {
                try {
                    dao.adicionarAluno(dados);
                    RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
                } catch (SQLException ex) {
                    Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                aluno.setCodAluno(codAluno);
                try {
                    dao.updateAluno(aluno);
                } catch (SQLException ex) {
                    Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
            try {
                request.setAttribute("alunos", dao.getAllAlunos());
            } catch (SQLException ex) {
                Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            view.forward(request, response);
        }
    }
}
