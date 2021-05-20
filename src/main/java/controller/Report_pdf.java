/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alexsandro
 */
@WebServlet(name = "Report_pdf", urlPatterns = {"/listaAluno.pdf"})
public class Report_pdf extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
         gerarRelatorio(request, response);
    }

    private void gerarRelatorio(HttpServletRequest request, HttpServletResponse response) {
        Document documento = new Document();
        try {
            //Tipo de conteudo
            response.setContentType("apllication/pdf");
            //Nome do documento
            response.addHeader("Content-Desposition", "inline;filename=" + "matriculados.pdf");
            //Criar o documento
            PdfWriter.getInstance(documento, response.getOutputStream());
            //Abrir documento
            documento.open();
            documento.add(new Paragraph("Lista de Alunos Maticulados"));
            documento.close();
        } catch (Exception e) {
            System.out.println(e);
            documento.close();
        }
    }
}