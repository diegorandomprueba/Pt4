package m03.uf4.objects;

public class Aprenent extends Mag {

    // Constructor
    public Aprenent(String nom, String cognom, Data dataNaixement, int nivell) {
        super(nom, cognom, dataNaixement, nivell);
    }

    // Implementación del método aprendreEncanteri
    @Override
    public boolean aprendreEncanteri(Encanteri e) {
        // Verificar si el nivel del aprendiz es suficiente para aprender el encanteri
        if (this.getNivellCombat() >= e.getNivellNecessari()) {
            // Buscar espacio en el array de encanteris
            for (int i = 0; i < encanteris.length; i++) {
                if (encanteris[i] == null) {
                    encanteris[i] = e;
                    return true; // Encanteri aprendido con éxito
                }
            }

            // Si no hay espacio, buscar el encanteri con el menor daño para reemplazarlo si el nuevo hace más daño
            int indexMenorDany = -1;
            int menorDany = Integer.MAX_VALUE;

            for (int i = 0; i < encanteris.length; i++) {
                if (encanteris[i] != null && encanteris[i].getDany() < menorDany) {
                    menorDany = encanteris[i].getDany();
                    indexMenorDany = i;
                }
            }

            // Reemplazar si el nuevo encanteri hace más daño que el de menor daño
            if (indexMenorDany != -1 && e.getDany() > menorDany) {
                encanteris[indexMenorDany] = e;
                return true; // Encanteri reemplazado con éxito
            }
        }

        return false; // No se ha podido aprender el encanteri
    }
}
