package model;

/**
 *
 * @author Carlos Pavão <carlos.henrique93@msn.com>
 */
public class Turma {
    
    private int turmaID;
    private String serie;
    private int qte;

    public Turma() {
    }

    public Turma(int turmaID, String serie, int qte) {
        this.turmaID = turmaID;
        this.serie = serie;
        this.qte = qte;
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

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
    
}
