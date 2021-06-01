package model;

/**
 *
 * @author Carlos Pavão <carlos.henrique93@msn.com>
 */
public class Frequencia {
    int ID_frequencia;
    int aula_assistida;
    int presente;
    int ausente;
    int CodDisciplina;
    int CodAluno;

    public Frequencia() {
    }

    public Frequencia(int ID_frequencia, int aula_assistida, int presente, int ausente, int CodDisciplina, int CodAluno) {
        this.ID_frequencia = ID_frequencia;
        this.aula_assistida = aula_assistida;
        this.presente = presente;
        this.ausente = ausente;
        this.CodDisciplina = CodDisciplina;
        this.CodAluno = CodAluno;
    }

    public int getID_frequencia() {
        return ID_frequencia;
    }

    public void setID_frequencia(int ID_frequencia) {
        this.ID_frequencia = ID_frequencia;
    }

    public int getAula_assistida() {
        return aula_assistida;
    }

    public void setAula_assistida(int aula_assistida) {
        this.aula_assistida = aula_assistida;
    }

    public int getPresente() {
        return presente;
    }

    public void setPresente(int presente) {
        this.presente = presente;
    }

    public int getAusente() {
        return ausente;
    }

    public void setAusente(int ausente) {
        this.ausente = ausente;
    }

    public int getCodDisciplina() {
        return CodDisciplina;
    }

    public void setCodDisciplina(int CodDisciplina) {
        this.CodDisciplina = CodDisciplina;
    }

    public int getCodAluno() {
        return CodAluno;
    }

    public void setCodAluno(int CodAluno) {
        this.CodAluno = CodAluno;
    }
    
    
}
