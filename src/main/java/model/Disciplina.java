package model;

/**
 *
 * @author Carlos Pavão <carlos.henrique93@msn.com>
 */
public class Disciplina {
    private int disciplinaID;
    private String nome;
    private int cargahoraria;

    public Disciplina() {
    }

    public Disciplina(int disciplinaID, String nome, int cargahoraria) {
        this.disciplinaID = disciplinaID;
        this.nome = nome;
        this.cargahoraria = cargahoraria;
    }

    public int getDisciplinaID() {
        return disciplinaID;
    }

    public void setDisciplinaID(int disciplinaID) {
        this.disciplinaID = disciplinaID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargahoraria() {
        return cargahoraria;
    }

    public void setCargahoraria(int cargahoraria) {
        this.cargahoraria = cargahoraria;
    }
}
