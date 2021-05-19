/*
 * Desenvolvedores: 
 * ALEXSANDRO DA SILVA RAMOS
 * CARLOS HENRIQUE PAVAO INACIO
 * PATRICIA
 * FERREIRA DE SOUSA RENAN FERREIRA NOVAES
 * MATHEUS MARCHENA
*/

package model;


import java.time.LocalDate;

public class Aluno {
    
    private int codAluno;
    private String nome;
    private String cpf;
    private LocalDate dataNasc;
    private String sexo;
    private String nomePai;
    private String nomeMae;
    private String celular;
    private String celularPai;
    private String celularMae;
    private String email;
    private String rua;
    private int numero;
    private String complemento;
    private String bairro;
    private String cep;

    public Aluno() {
    }

    public Aluno(int codAluno, String nome, String cpf, LocalDate dataNasc,
            String sexo, String nomePai, String nomeMae, String celular,
            String celularPai, String celularMae, String email, String rua,
            int numero, String complemento, String bairro, String cep) {
        this.codAluno = codAluno;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.sexo = sexo;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
        this.celular = celular;
        this.celularPai = celularPai;
        this.celularMae = celularMae;
        this.email = email;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
    }

    public int getCodAluno() {
        return codAluno;
    }

    public void setCodAluno(int codAluno) {
        this.codAluno = codAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCelularPai() {
        return celularPai;
    }

    public void setCelularPai(String celularPai) {
        this.celularPai = celularPai;
    }

    public String getCelularMae() {
        return celularMae;
    }

    public void setCelularMae(String celularMae) {
        this.celularMae = celularMae;
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

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
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
