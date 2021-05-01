package model;


import java.util.Date;

/**
 *
 * @author Carlos Pavão <carlos.henrique93@msn.com>
 */
public class Aluno {
    
private int codAluno;
private String nome;
private Date dataNasc;
private String sexo;
private String nomePai;
private String nomeMae;
private String Celular;
private String CelularPai;
private String CelularMae;
private String email;
    
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
        return Celular;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    public String getCelularPai() {
        return CelularPai;
    }

    public void setCelularPai(String CelularPai) {
        this.CelularPai = CelularPai;
    }

    public String getCelularMae() {
        return CelularMae;
    }

    public void setCelularMae(String CelularMae) {
        this.CelularMae = CelularMae;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    



}
