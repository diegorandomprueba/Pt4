package m03.uf4.objects;
import m03.uf4.objects.Data;

public abstract class Mag extends Persona {
    // Atributos
    private final int numEncanteri = 10;
    private int nivell;
    public Encanteri[] encanteris;

    // Constructores
    public Mag(String nom, String cognom, Data dataNaixement, int nivell) {
        super(nom, cognom, dataNaixement);
        this.nivell = nivell;
        this.encanteris = new Encanteri[numEncanteri];
    }

    // Getters y Setters
    public int getNivellCombat() {
        if (getEdat() < 18) {
            return getEdat();
        } else if (getEdat() >= 18 && getEdat() < 25) {
            return getEdat()+3;
        } else if (getEdat() >= 25 && getEdat() < 40) {
            return getEdat()+5;
        } else if (getEdat() >= 40 && getEdat() < 55) {
            return getEdat()+4;
        } else if (getEdat() >= 55 && getEdat() < 65) {
            return getEdat()+2;
        } else {
            return getEdat()-1;
        }
    }

    public void setNivell(int nivell) {
        if(nivell < 0) {
            this.nivell = 0;
        } else {
            this.nivell = nivell;
        }
    }

    public int getNumEncanteri() {
        return encanteris.length;
    }

    // Funcionalidades
    public abstract boolean aprendreEncanteri(Encanteri e);

    public void afegirExperiencia(Experiencia e) {
        if (e == Experiencia.MOLTA) {
            nivell += 2;
        } else if (e == Experiencia.MITJA) {
            nivell += 8;
        } else if (e == Experiencia.POCA) {
            nivell += 15;
        } else {
            System.err.println("Valor invalido!");
        }
    }


}
