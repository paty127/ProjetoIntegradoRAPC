package model;

import java.util.Date;

/**
 *
 * @author Carlos Pavão <carlos.henrique93@msn.com>
 */
public class Professor {
    private int codProfessor;
    private String nome;
    private String sexo;
    private Date dataNasc;
    private String rg;
    private String cpf;
    private String celular;
    private String email;
    private String disciplina1;
    private String disciplina2;
    private String senha;
    private String senha_repetida;
    private String rua;
    private int    numero;
    private String bairro;
    private String cep;

    public Professor() {}

    public Professor(int codProfessor, String nome, String sexo, Date dataNasc,
        String rg, String cpf, String celular, String email,
        String disciplina1, String disciplina2, String senha,
        String senha_repetida, String rua, int numero,
        String bairro, String cep) {
        this.codProfessor = codProfessor;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNasc = dataNasc;
        this.rg = rg;
        this.cpf = cpf;
        this.celular = celular;
        this.email = email;
        this.disciplina1 = disciplina1;
        this.disciplina2 = disciplina2;
        this.senha = senha;
        this.senha_repetida = senha_repetida;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisciplina1() {
        return disciplina1;
    }

    public void setDisciplina1(String disciplina1) {
        this.disciplina1 = disciplina1;
    }

    public String getDisciplina2() {
        return disciplina2;
    }

    public void setDisciplina2(String disciplina2) {
        this.disciplina2 = disciplina2;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha_repetida() {
        return senha_repetida;
    }

    public void setSenha_repetida(String senha_repetida) {
        this.senha_repetida = senha_repetida;
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
