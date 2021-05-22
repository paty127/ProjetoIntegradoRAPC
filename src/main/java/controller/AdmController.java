package controller;

import dao.AdmDao;
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

import model.Adm;


@WebServlet(name = "adms", urlPatterns = {"/admController"})
public class AdmController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String INSERT_Adm = "/cadastroAdm";
    private static final String EDIT = "/editarAdm";
    private static final String GERAR_RELATORIO = "/PDF";
    
    private static final String LIST_ALUNO = "/listarAdm";
    
    private final AdmDao dao;

    public AdmController() {
        dao = new AdmDao();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

         String forward = "";
        //Pegar o parametro de Action 
        String action = request.getParameter("action");

            if (action.equalsIgnoreCase("edit")) {
            
            int codAdm = Integer.parseInt(request.getParameter("codAdm"));
            //Passar o codigo de adm para o metodo getAdmByID
            try {
                request.setAttribute("adm",  dao.getAdmById(codAdm));
                RequestDispatcher dispatcher = request.getRequestDispatcher("/editarAdm");
                    dispatcher.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(AdmController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //request.setAttribute("adm", adm);
        } else if (action.equalsIgnoreCase("ListAdm")) {
            try {
                //Criando um atributo chamado Adms e inserindo a lista que veio do metodo getAllAdms
                request.setAttribute("adms", dao.getAllAdm());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/listarAdm");
                    dispatcher.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(AdmController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroAdm");
                    dispatcher.forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        int numero;
        int codAdm;
        boolean temErro = false;
        String action = request.getParameter("action");
        Adm adm = new Adm();

        LocalDate dataNascimento = null;
        if (!request.getParameter("dataNascimento").equals("")) {
            dataNascimento = LocalDate.parse(request.getParameter("dataNascimento"));
        } else {
            dataNascimento = null;
            request.setAttribute("erroData", "Data não informada.");
        }
        adm.setDataNasc(dataNascimento);
        if (request.getParameter("numero").equals("")) {
            numero = 0;
        } else {
            numero = Integer.parseInt(request.getParameter("numero"));
            adm.setNumero(Integer.parseInt(request.getParameter("numero")));
        }

        String nome = (request.getParameter("nome"));
        String sexo = (request.getParameter("sexo"));
        String rg = (request.getParameter("rg"));
        String cpf = (request.getParameter("cpf"));
        String celular = (request.getParameter("celular"));
        String email = (request.getParameter("email"));
        String senha = (request.getParameter("senha"));
        String senha_repetida = (request.getParameter("senhaRepetida"));
        String perfil = (request.getParameter("perfil"));
        String rua = (request.getParameter("rua"));
        String complemento = (request.getParameter("complemento"));
        String bairro = (request.getParameter("bairro"));
        String cep = (request.getParameter("cep"));
        if (request.getParameter("codAdm").equals("")) {
            codAdm = 0;
        } else {
            codAdm = Integer.parseInt(request.getParameter("codAdm"));
        }
        adm.setNome(request.getParameter("nome"));
        adm.setSexo(request.getParameter("sexo"));
        adm.setRg(request.getParameter("rg"));
        adm.setCpf(request.getParameter("cpf"));
        adm.setCelular(request.getParameter("celular"));
        adm.setEmail(request.getParameter("email"));
        adm.setSenha(request.getParameter("senha"));
        adm.setSenha_repetida(request.getParameter("senhaRepetida"));
        adm.setPerfil(request.getParameter("perfil"));
        adm.setRua(request.getParameter("rua"));
        adm.setComplemento(request.getParameter("complemento"));
        adm.setBairro(request.getParameter("bairro"));
        adm.setCep(request.getParameter("cep"));

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

        Adm dados = new Adm(codAdm,nome,sexo,dataNascimento,
               rg,cpf,celular,email,perfil,senha,
               senha_repetida,rua,numero,complemento,bairro,cep);

        request.setAttribute("dados", dados);

        if (temErro) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adm/validacaoAdm.jsp");
            dispatcher.forward(request, response);
        } 
        else {
            HttpSession sessao = request.getSession();
            sessao.setAttribute("dados", dados);
            sessao.setAttribute("codAdm", codAdm);
            response.sendRedirect(request.getContextPath() + "/redirectAdm");
        }
    }
}