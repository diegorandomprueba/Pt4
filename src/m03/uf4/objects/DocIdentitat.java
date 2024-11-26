package m03.uf4.objects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocIdentitat {
    private int numDni;
    private String lletraDni;

    // Constructores
    public DocIdentitat(int numDni, String lletraDni) {
        // Validacion letra
        if (lletraDni.length() == 1) {
            char let = lletraDni.charAt(0);
            if (Character.isLetter(let)) {
                this.lletraDni = lletraDni.toUpperCase();
            } else {
                System.err.println("Error: Ha introducido un caracter especial");
                this.lletraDni = "";
                this.numDni = -1;
            }
        } else {
            System.err.println("Error: Ha introducido mas de una letra al DNI");
            this.lletraDni = "";
            this.numDni = -1;
        }

        // Validacion numeros
        if (numDni < 0) {
            System.err.println("Error: El numero del DNI no puede ser negativo");
            this.lletraDni = "";
            this.numDni = -1;
        } else if (numDni > 99999999) {
            System.err.println("Error: El numero del DNI no puede tener mas de 8 digitos");
            this.lletraDni = "";
            this.numDni = -1;
        } else {
            this.numDni = numDni;
        }
    }

    public DocIdentitat(String dni) {
        // Validar patron
        String regex = "^[0-9]{8}[A-Za-z]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dni);

        if (!matcher.matches()) {
            System.err.println("Error: El DNI introducido no tiene el patron de un DNI");
            this.lletraDni = "";
            this.numDni = -1;
        } else {
            // Separar letra y numero
            int num = Integer.parseInt(dni.substring(0, dni.length() - 1));
            String letras = dni.substring(dni.length() - 1);

            // Validacion letras
            if (letras.length() == 1) {
                char let = letras.charAt(0);
                if (Character.isLetter(let)) {
                    this.lletraDni = letras.toUpperCase();
                } else {
                    System.err.println("Error: Ha introducido un caracter especial");
                    this.lletraDni = "";
                    this.numDni = -1;
                }
            } else {
                System.err.println("Error: Ha introducido mas de una letras al DNI");
                this.lletraDni = "";
                this.numDni = -1;
            }

            // Validacion numeros
            if (num < 0) {
                System.err.println("Error: El numero del DNI no puede ser negativo");
                this.lletraDni = "";
                this.numDni = -1;
            } else if (num > 99999999) {
                System.err.println("Error: El numero del DNI no puede tener mas de 8 digitos");
                this.lletraDni = "";
                this.numDni = -1;
            } else {
                this.numDni = num;
            }
        }

    }

    // Getters y Setters
    public int getNum() {
        return numDni;
    }

    public String getLletraDni() {
        return lletraDni;
    }

    // Funcionalidades
    public boolean isValid() {
        String caracteres = "TRWAGMYFPDXBNJZSQVHLCKE";

        int resto = numDni % 23;

        // Se crea una variable que almacena la letra correcta
        char letraCalculada = caracteres.charAt(resto);

        // Si la letra correcta coincide con la letra del numDni, retorna true, sino
        // retorna false
        return lletraDni.charAt(0) == letraCalculada;
    }

    @Override
    public String toString() {
        return String.format("%08d%s", numDni, lletraDni);
    }
}
