package m03.uf4.objects;

public class Encanteri {
    // Atributos
    private String nom;
    private int nivellNecessari;
    private int dany;

    // Constructores
    public Encanteri(String nom, int nivellNecessari, int dany) {
        this.nom = nom;
        this.nivellNecessari = nivellNecessari;
        this.dany = dany;
    }
    
    // Getters y Setters
    public int getNivellNecessari() {
        return nivellNecessari;
    }

    public int getDany() {
        return dany;
    }

    // Funcionalidades
    @Override
    public String toString() {
        return nom + "(Nivell necessari: " + nivellNecessari + ", dany: " + dany + ")";
    }
}
