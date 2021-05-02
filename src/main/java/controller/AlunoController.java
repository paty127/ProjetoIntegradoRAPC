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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Aluno;

@WebServlet(name = "Alunos", urlPatterns = {"/AlunoController"})
public class AlunoController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/CadastroAluno.jsp";
    private static String LIST_USER = "/listarAluno.jsp";
    private AlunoDao dao;

    public AlunoController() {
        super();
        dao = new AlunoDao();
    }

    @Override
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        //Pegar o parametro de Action 
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int codAluno = Integer.parseInt(request.getParameter("codAluno"));
            try {
                dao.deletarAluno(codAluno);
            } catch (SQLException ex) {
                Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            forward = LIST_USER;
            try {
                request.setAttribute("alunos", dao.getAllAlunos());
            } catch (SQLException ex) {
                Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT; 
            int codAluno = Integer.parseInt(request.getParameter("codAluno"));
            try {
                //Passar o codigo de aluno para o metodo getAlunoByID
                Aluno aluno = dao.getAlunoById(codAluno);
                request.setAttribute("aluno",aluno );

            } catch (SQLException ex) {
                Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }      
        } else if (action.equalsIgnoreCase("ListAluno")){
            forward = LIST_USER;
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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        Aluno aluno = new Aluno();
        
        aluno.setNome(request.getParameter("nome"));
        
        try {
            Date data_de_nascimento = null;
            String teste = request.getParameter("data_de_nascimento");
            //System.out.println(teste);
            if(request.getParameter("data_de_nascimento")!=null){
                data_de_nascimento = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("data_de_nascimento"));
            }
            else{
                data_de_nascimento = null;
            }

            aluno.setDataNasc(data_de_nascimento);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        aluno.setNomePai(request.getParameter("pai"));
        aluno.setNomeMae(request.getParameter("mae"));
        aluno.setCelular(request.getParameter("celular"));
        aluno.setCelularPai(request.getParameter("celularPai"));
        aluno.setCelularMae(request.getParameter("celularMae"));
        aluno.setEmail(request.getParameter("email"));
        aluno.setSexo(request.getParameter("sexo"));
        
        String codAluno = request.getParameter("codAluno");
        
        if(codAluno == null || codAluno.isEmpty())
        {
            try {
                dao.adicionarAluno(aluno);
            } catch (SQLException ex) {
                Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            aluno.setCodAluno(Integer.parseInt(codAluno));
            try {
                dao.updateUser(aluno);
            } catch (SQLException ex) {
                Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
        try {
            request.setAttribute("alunos", dao.getAllAlunos());
        } catch (SQLException ex) {
            Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        view.forward(request, response);
    }
}