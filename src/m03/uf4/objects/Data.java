package m03.uf4.objects;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Data {
    // Atributos
    private int dia;
    private int mes;
    private int any;

    // Constructores
    public Data() {
        // Consigue fecha de sistema
        LocalDate data = LocalDate.now();

        // Asigna valores a los atributos
        this.dia = data.getDayOfMonth();
        this.mes = data.getMonthValue();
        this.any = data.getYear();
    }

    public Data(int dia, int mes, int any) {
        // Valida el año introducido
        if (any < 0) {
            System.err.println("Error: el año no puede ser inferior a 0");
            return;
        }

        // Valida el mes introducido
        if (mes < 1 || mes > 12) {
            System.err.println("Error: el mes no puede ser inferior a 1 o superior a 12");
            return;
        }

        // Valida el día introducido
        if (dia < 1 || dia > getMaxDiasDelMes(mes, any)) {
            System.err.println("Error: el día no es válido para el mes " + mes);
            return;
        }

        // Asignar valores al objeto
        this.dia = dia;
        this.mes = mes;
        this.any = any;
    }

    public Data(String data) {
        // Validar el formato de la fecha
        Pattern pattern = Pattern.compile("(\\d{2})/(\\d{2})/(\\d{4})");
        Matcher matcher = pattern.matcher(data);

        if (!matcher.matches()) {
            System.err.println("Error: el formato de la fecha debe ser dd/mm/aaaa");
            return;
        }

        // Extraer día, mes y año
        int dia = Integer.parseInt(matcher.group(1));
        int mes = Integer.parseInt(matcher.group(2));
        int any = Integer.parseInt(matcher.group(3));

        // Validar la fecha extraída
        if (!isDataValida(dia, mes, any)) {
            return; // Si la fecha no es válida, retorna
        }

        // Asignar valores al objeto
        this.dia = dia;
        this.mes = mes;
        this.any = any;
    }

    // Método para validar si la fecha es correcta
    public static boolean isDataValida(int dia, int mes, int any) {
        if (any < 0) {
            System.err.println("Error: el año no puede ser inferior a 0");
            return false;
        }

        if (mes < 1 || mes > 12) {
            System.err.println("Error: el mes no puede ser inferior a 1 o superior a 12");
            return false;
        }

        if (dia < 1 || dia > getMaxDiasDelMes(mes, any)) {
            System.err.println("Error: el día no es válido para el mes " + mes);
            return false;
        }

        return true;
    }

    // Método privado para validar la fecha
    /* La hago publica ya que en el ejercicio 4 pides que validemos la fecha de nacimiento de las personas*/
    public boolean isDataValida() {
        return isDataValida(this.dia, this.mes, this.any);
    }

    // Getters y Setters
    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        // Validar el día introducido
        if (dia < 1 || dia > getMaxDiasDelMes(mes, any)) {
            System.err.println("Error: el día " + dia + " no es válido para el mes " + mes);
        } else {
            this.dia = dia;
        }
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        // Validar el mes introducido
        if (mes < 1 || mes > 12) {
            System.err.println("Error: el mes no puede ser inferior a 1 o superior a 12");
        } else if (dia > getMaxDiasDelMes(mes, any)) {
            System.err.println("Error: el día " + dia + " no es válido para el mes " + mes);
        } else {
            this.mes = mes;
        }
    }

    public int getAny() {
        return any;
    }

    public void setAny(int any) {
        if (any < 0) {
            System.err.println("Error: el año no puede ser inferior a 0");
        } else {
            this.any = any;
        }
    }

    // Funcionalidades
    public void nextDay() {
        // Determinar el máximo número de días en el mes actual
        int maxDias = getMaxDiasDelMes(mes, any);

        // Verificar si es el último día del mes
        if (dia < maxDias) {
            dia++;
        } else {
            dia = 1; // Reiniciar el día
            if (mes == 12) {
                mes = 1; // Reiniciar el mes
                any++;
            } else {
                mes++;
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", dia, mes, any);
    }

    // Método para obtener el máximo número de días en un mes
    private static int getMaxDiasDelMes(int mes, int any) {
        switch (mes) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if ((any % 4 == 0 && any % 100 != 0) || (any % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 0; // Mes inválido
        }
    }

    // Metodo que transforma un objeto Data, en un objeto LocalDate
    public LocalDate toLocalDate() {
        if (any == 0 || mes == 0 || dia == 0 ) {
            System.err.println("Error: Los atributos del objeto Data, estan vacios.");
            return null;
        } else {
            return LocalDate.of(any, mes, dia);
        }
    }
}