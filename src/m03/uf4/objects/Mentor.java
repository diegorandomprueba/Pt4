package m03.uf4.objects;

import java.util.HashSet;
import java.util.Set;

public class Mentor extends Mag {
    // Atributos
    private String especialitat;
    private int n_aprenents = 5;
    public Mag[] aprenents = new Mag[n_aprenents];

    // Constructores
    public Mentor(String nom, String cognom, Data dataNaixement, int nivell) {
        super(nom, cognom, dataNaixement, nivell);
    }

    // Getters y Setters
    public int getOrdreMentor() {
        // Orden base
        int ordre = 1;

        // Variable para almacenar el orden jerárquico más alto de los aprendices
        int ordreMax = 0;

        // Recorremos los aprendices directos
        for (Mag aprenent : aprenents) {
            if (aprenent instanceof Mentor) {
                Mentor mentorAprenent = (Mentor) aprenent;
                // Llamamos recursivamente a getOrdreMentor() para obtener el nivel del aprendiz
                int ordreAprenent = mentorAprenent.getOrdreMentor();
                ordreMax = Math.max(ordreMax, ordreAprenent);
            }
        }

        // Si hay algún aprendiz que es mentor, incrementamos el orden
        if (ordreMax > 0) {
            ordre += ordreMax;
        }

        return ordre;
    }

    @Override
    public int getNivellCombat() {
        // Obtener el nivel base del mentor
        int nivellBase = super.getNivellCombat();

        // Sumar puntos por orden jerárquico
        int ordreJerarquic = getOrdreMentor(); // Método que devuelve el orden jerárquico del mentor
        int puntuacioJerarquica = ordreJerarquic * 5;

        // Usar un Set para contar aprendices únicos
        Set<Integer> idsAprenentsUnics = new HashSet<>();

        // Recorrer aprendices directos y contar aprendices indirectos
        for (Mag aprenent : aprenents) {
            if (aprenent != null) {
                // Añadir ID del aprendiz directo
                idsAprenentsUnics.add(aprenent.getIdPersona());

                // Si el aprendiz es un mentor, añadir sus aprendices
                if (aprenent instanceof Mentor) {
                    Mentor mentorAprenent = (Mentor) aprenent;
                    for (Mag indirectAprenent : mentorAprenent.aprenents) {
                        if (indirectAprenent != null) {
                            idsAprenentsUnics.add(indirectAprenent.getIdPersona());
                        }
                    }
                }
            }
        }

        // Calcular el nivel total
        int nivellTotal = nivellBase + puntuacioJerarquica + idsAprenentsUnics.size();

        return nivellTotal;
    }

    // Funcionalidades
    public void afegirAprenent(Mag mag) {
        if (this.getIdPersona() == mag.getIdPersona()) {
            System.err.println("Error: No puedes añadirte a ti mismo, como aprendiz.");
            return;
        } else {
            for (Mag a : aprenents) {
                if (a != null) {
                    if (mag.getIdPersona() == a.getIdPersona()) {
                        System.err.println("Error: No puedes introducir un aprendiz repetido.");
                        return;
                    }
                }
            }

            if (mag instanceof Mentor) {
                System.err.println("Error: No puedes añadir un mentor, como aprendiz.");
                return;
            } else {
                for (int i = 0; i < aprenents.length; i++) {
                    if (aprenents[i] == null) {
                        aprenents[i] = mag; // Añadimos "mag" al primer espacio nulo
                        System.out.println("Aprendiz " + mag.getNom() + " añadido correctamente.");
                        return;
                    }
                }

            }
        }
    }

    @Override
    public boolean aprendreEncanteri(Encanteri e) {
        // Verificar si el nivel del mentor es suficiente para aprender el encanteri
        if (this.getNivellCombat() >= e.getNivellNecessari()) {
            // Buscar espacio en el array de encanteris del mentor
            for (int i = 0; i < encanteris.length; i++) {
                if (encanteris[i] == null) {
                    encanteris[i] = e; // Añadir el encanteri al mentor
                    break; // Salir después de añadir
                }
            }

            // Ahora añadir el encanteri a los aprendices que cumplan con el nivel
            for (Mag aprenent : aprenents) {
                if (aprenent != null && aprenent.getNivellCombat() >= e.getNivellNecessari()) {
                    // Buscar espacio en el array de encanteris del aprendiz
                    for (int i = 0; i < aprenent.encanteris.length; i++) {
                        if (aprenent.encanteris[i] == null) {
                            aprenent.encanteris[i] = e; // Añadir el encanteri al aprendiz
                            break; // Salir después de añadir
                        }
                    }
                }
            }

            return true; // Encanteri aprendido con éxito por el mentor y añadidos a aprendices
        }

        return false; // No se ha podido aprender el encanteri
    }

    public boolean eliminarAprenent(int id) {
        boolean encontrado = false;

        // Buscar el objeto con el ID especificado
        for (int j = 0; j < aprenents.length; j++) {
            if (aprenents[j] != null && aprenents[j].getIdPersona() == id) {
                aprenents[j] = null; // Establecer a null
                encontrado = true; // Marcar que se encontró
                System.out.println("Mag eliminado de la lista d’aprenents.");
                break; // Salir del bucle una vez encontrado
            }
        }

        // Si no se encontró, mostrar mensaje de error
        if (!encontrado) {
            System.err.println("Error: No se pudo eliminar el Mag con ID " + id);
            return false; // Retornar false si no se encontró
        }

        // Reordenar el array después de la eliminación
        aprenents = reorderArray(aprenents);

        return true; // Retornar true si se eliminó exitosamente
    }

    public Mag[] reorderArray(Mag[] array) {
        // Crear un nuevo array que tendrá el tamaño original
        Mag[] newArray = new Mag[array.length];
        int index = 0;

        // Copiar elementos no nulos al nuevo array
        for (Mag element : array) {
            if (element != null) {
                newArray[index++] = element;
            }
        }

        // Rellenar el resto del nuevo array con null
        for (int i = index; i < newArray.length; i++) {
            newArray[i] = null;
        }

        return newArray;
    }
}