package model;

/**
 *
 * @author Carlos Pavão <carlos.henrique93@msn.com>
 */
public class Desempenho {
    private int cod_aluno;
    private String nome;
    private double nota1;
    private double nota2;
    private double nota3;
    private double nota4;
    private int codDisciplina;
    private int codAluno;

    public Desempenho() {
    }
    public Desempenho(int codTurma, int codAluno) {
        this.codDisciplina = codTurma;
        this.codAluno = codAluno;
    }

    public Desempenho(int cod_aluno, String nome, double nota1, double nota2,
            double nota3, double nota4, int codDisciplina, int codAluno) {
        this.cod_aluno = cod_aluno;
        this.nome = nome;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.nota4 = nota4;
        this.codDisciplina = codDisciplina;
        this.codAluno = codAluno;
    }

    public Desempenho(double nota1, double nota2, double nota3, double nota4,
            int codDisciplina) {
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.nota4 = nota4;
        this.codDisciplina = codDisciplina;
    }
    

    public int getCod_aluno() {
        return cod_aluno;
    }

    public void setCod_aluno(int cod_aluno) {
        this.cod_aluno = cod_aluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getNota3() {
        return nota3;
    }

    public void setNota3(double nota3) {
        this.nota3 = nota3;
    }

    public double getNota4() {
        return nota4;
    }

    public void setNota4(double nota4) {
        this.nota4 = nota4;
    }

    public int getCodDisciplina() {
        return codDisciplina;
    }

    public void setCodDisciplina(int codDisciplina) {
        this.codDisciplina = codDisciplina;
    }

    public int getCodAluno() {
        return codAluno;
    }

    public void setCodAluno(int codAluno) {
        this.codAluno = codAluno;
    }
}
