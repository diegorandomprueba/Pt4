package m03.uf4.application;
import m03.uf4.objects.*;

public class ProvaApp {
    public static void main(String[] args) {
        // Crear algunos encanteris (hechos inventados)
        Encanteri encanteri1 = new Encanteri("Fuego", 10, 20);
        Encanteri encanteri2 = new Encanteri("Hielo", 15, 25);
        Encanteri encanteri3 = new Encanteri("Rayos", 12, 30);
        
        // Crear algunas fechas de nacimiento
        Data data1 = new Data(1, 1, 2000);
        Data data2 = new Data(15, 5, 1995);
        Data data3 = new Data(30, 12, 1980);
        
        // Crear aprendices
        Aprenent aprenent1 = new Aprenent("Carlos", "Pérez", data1, 5);
        Aprenent aprenent2 = new Aprenent("Ana", "García", data2, 10);
        Aprenent aprenent3 = new Aprenent("Luis", "Martínez", data3, 8);
               
        // Crear mentores
        Mentor mentor1 = new Mentor("José", "López", data2, 12);
        Mentor mentor2 = new Mentor("María", "Fernández", data3, 15);

        // Agregar aprendices a los mentores
        mentor1.afegirAprenent(aprenent1);
        mentor1.afegirAprenent(aprenent2);
        mentor2.afegirAprenent(aprenent3);

        // Aprender encanteris
        boolean resultado1 = mentor1.aprendreEncanteri(encanteri1);
        boolean resultado2 = mentor1.aprendreEncanteri(encanteri2);
        boolean resultado3 = aprenent1.aprendreEncanteri(encanteri3);

        // Mostrar resultados
        System.out.println("Resultado de aprender encanteri por el mentor 1: " + resultado1);
        System.out.println("Resultado de aprender encanteri por el mentor 1: " + resultado2);
        System.out.println("Resultado de aprender encanteri por el aprendiz 1: " + resultado3);

        // Mostrar información de los mentores y aprendices
        System.out.println(mentor1);
        System.out.println(mentor2);
        System.out.println(aprenent1);
        System.out.println(aprenent2);
        System.out.println(aprenent3);
        
        // Comprobar niveles de combate
        System.out.println("Nivel de combate del mentor 1: " + mentor1.getNivellCombat());
        System.out.println("Nivel de combate del aprendiz 1: " + aprenent1.getNivellCombat()); 
    }
}