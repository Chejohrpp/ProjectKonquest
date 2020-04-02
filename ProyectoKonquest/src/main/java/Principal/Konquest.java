/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.util.Scanner;

/**
 *
 * @author sergi
 */
public class Konquest {
    public static void main(String []arg){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenidos al juego de Konquest");
        System.out.println("Diseñe el tamaño del tablero");
        System.out.println("Elija el numero de Filas");
        int y = scanner.nextInt();
        System.out.println("Elija el numero de Columnas");
        int x = scanner.nextInt();
        System.out.println("Elija la cantidad de Planetas neutrales");
        int cantidadPlanetasNeutrales = (scanner.nextInt());        
        String jugador = scanner.nextLine(); //Esta variable no se usa, si se quita el programa no lee el String jugador 1
         System.out.println("Cual es el nombre del Jugador 1");
        String jugador1 = scanner.nextLine();
         System.out.println("Cual es el nombre del Jugador 2");
        String jugador2 = scanner.nextLine();
        
        //Nos pasamos a Galaxia.java
        Galaxia galaxia = new Galaxia();
        galaxia.Started(x,y,cantidadPlanetasNeutrales,jugador1,jugador2);
    }
}
