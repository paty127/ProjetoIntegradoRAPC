package model;

/**
 *
 * @author Carlos Pavão <carlos.henrique93@msn.com>
 */
public class Turma {
    private int turmaID;
    private String serie;

    public Turma() {
    }

    public Turma(int turmaID, String serie) {
        this.turmaID = turmaID;
        this.serie = serie;
    }

    public int getTurmaID() {
        return turmaID;
    }

    public void setTurmaID(int turmaID) {
        this.turmaID = turmaID;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

   
}
