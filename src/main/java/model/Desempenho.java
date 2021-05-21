package model;

/**
 *
 * @author Carlos Pavão <carlos.henrique93@msn.com>
 */
public class Desempenho {
    private int idDesempenho;
    private double notas;
    private int presenca;

    public Desempenho(int idDesempenho, double notas, int presenca) {
        this.idDesempenho = idDesempenho;
        this.notas = notas;
        this.presenca = presenca;
    }

    public int getIdDesempenho() {
        return idDesempenho;
    }

    public void setIdDesempenho(int idDesempenho) {
        this.idDesempenho = idDesempenho;
    }

    public double getNotas() {
        return notas;
    }

    public void setNotas(double notas) {
        this.notas = notas;
    }

    public int getPresenca() {
        return presenca;
    }

    public void setPresenca(int presenca) {
        this.presenca = presenca;
    }

    
}
