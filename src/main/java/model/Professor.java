package model;

import java.util.Date;

/**
 *
 * @author Carlos Pavão <carlos.henrique93@msn.com>
 */
public class Professor {
    private int codProfessor;
    private String nome;
    private Date dataNasc;
    private String sexo;
    private String celular;
    private String telefone;
    private String cpf;
    private String rg;
    private String email;
    private String rua;
    private int    numero;
    private String bairro;
    private String cep;

    public Professor() {
    }
    
    
    
    public Professor(int codProfessor, String nome, Date dataNasc, String sexo, String celular, String telefone, String cpf, String rg, String email, String rua, int numero, String bairro, String cep) {
        this.codProfessor = codProfessor;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
        this.celular = celular;
        this.telefone = telefone;
        this.cpf = cpf;
        this.rg = rg;
        this.email = email;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
    }
  

    public int getCodProfessor() {
        return codProfessor;
    }

    public void setCodProfessor(int codProfessor) {
        this.codProfessor = codProfessor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

   
    
    
}
