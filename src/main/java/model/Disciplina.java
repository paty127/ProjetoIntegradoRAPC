package model;

/**
 *
 * @author Carlos Pavão <carlos.henrique93@msn.com>
 */
public class Disciplina {
    private int diciplinaID;
    private String Nome;
    private int CargaHorario;

    public Disciplina(int diciplinaID, String Nome, int CargaHorario) {
        this.diciplinaID = diciplinaID;
        this.Nome = Nome;
        this.CargaHorario = CargaHorario;
    }

    public int getDiciplinaID() {
        return diciplinaID;
    }

    public void setDiciplinaID(int diciplinaID) {
        this.diciplinaID = diciplinaID;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public int getCargaHorario() {
        return CargaHorario;
    }

    public void setCargaHorario(int CargaHorario) {
        this.CargaHorario = CargaHorario;
    }
    

}
