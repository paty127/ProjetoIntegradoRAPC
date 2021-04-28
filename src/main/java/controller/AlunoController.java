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

@WebServlet(name = "AlunoController", urlPatterns = {"/cadastrar-aluno"})
public class AlunoController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/user.jsp";
    private static String LIST_USER = "/listUser.jsp";
    private AlunoDao dao;

    public AlunoController() {
        super();
        dao = new AlunoDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int CodAluno = Integer.parseInt(request.getParameter("CodAluno"));
            try {
                dao.deletarAluno(CodAluno);
            } catch (SQLException ex) {
                Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            forward = LIST_USER;
            try {
                request.setAttribute("Aluno", dao.getAllUsers());
            } catch (SQLException ex) {
                Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int CodAluno = Integer.parseInt(request.getParameter("CodAluno"));
            Aluno user;
            try {
                user = dao.getUserById(CodAluno);
            } catch (SQLException ex) {
                Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("Aluno", CodAluno);
        } else if (action.equalsIgnoreCase("listUser")){
            forward = LIST_USER;
            try {
                request.setAttribute("aluno", dao.getAllUsers());
            } catch (SQLException ex) {
                Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Aluno user = new Aluno();
        user.setNome(request.getParameter("nome"));
        try {
            Date dob=null;
            String teste = request.getParameter("dob");
            System.out.println(teste);
            if(request.getParameter("dob")!=null){
                dob = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dob"));
            }
            else{
                dob = null;
            }

            user.setDataNasc(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setEmail(request.getParameter("email"));
        String userid = request.getParameter("userid");
        if(userid == null || userid.isEmpty())
        {
            dao.addUser(user);
        }
        else
        {
            user.setUserid(Integer.parseInt(userid));
            dao.updateUser(user);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
        request.setAttribute("users", dao.getAllUsers());
        view.forward(request, response);
    }
}