
package controller;

import dao.AlunoDao;
import dao.DesempenhoDao;
import dao.DisciplinaDao;
import dao.FrequenciaDao;
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
import model.Frequencia;



@WebServlet(name = "FrequenciaController", urlPatterns = {"/registrarFrequencia","/selecao"})
public class FrequenciaController extends HttpServlet {
    
    private static final long serialVersionUID = 102831973239L;
    private static final String LIST_FREQ = "/listarFreq";
    private final DisciplinaDao daoDisc;
    private final TurmaDao daoT;
    private final AlunoDao daoA;
    private final FrequenciaDao daoF;
    Frequencia desempenho = new Frequencia();

    public FrequenciaController() {
        daoDisc = new DisciplinaDao();
        daoT = new TurmaDao();
        daoA = new AlunoDao();
        daoF = new FrequenciaDao();
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        boolean temErro = false;
        String forward = "";
        String action = request.getServletPath();

        if (action.equals("/registrarFrequencia")) {
            
            try {
                request.setAttribute("turmas", daoT.getAllTurmas());
                request.setAttribute("Disciplinas", daoDisc.getAllDisciplinas());
            } catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }          
            Redirecionar(request,response);
        }
        if (action.equals("/selecao")) {
            
            try {
                request.setAttribute("turmas", daoT.getAllTurmas());
                request.setAttribute("Disciplinas", daoDisc.getAllDisciplinas());
            } catch (SQLException ex) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
            }          
            Redirecionar(request,response);
        }
        if (action.equalsIgnoreCase("ListFrequencia")) {
            forward = LIST_FREQ;
            try {
                request.setAttribute("Freq", daoF.getAllFrequencia());
            } catch (SQLException ex) {
                Logger.getLogger(FrequenciaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
        

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
    }
    
    protected void Redirecionar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Direcionando para tela JSP.
        request.getRequestDispatcher("/WEB-INF/jsp/registro/presenca.jsp").forward(request, response);
    }
}
