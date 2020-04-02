/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Planetas;

import java.util.Random;

/**
 *
 * @author sergi
 */
public class Fuego  extends Planeta {

    public Fuego(String nombre, double porcentajeMuertes, int cantidadDinero, int cantidadNaves, int cantidadGuerreros, int cantDineroTurno, int cantConstructores) {
        super(nombre, porcentajeMuertes, cantidadDinero, cantidadNaves, cantidadGuerreros, cantDineroTurno, cantConstructores);
    }

     @Override
     public int RandomCantDineroTurno(){
        Random random = new Random();
        return random.nextInt(71)+70;
    }
     @Override
        public int RandomCantGuerreroFinalizarTurno(){
        Random random = new Random();
        return random.nextInt(11)+(10);
    }
    @Override
    public String toString() {
        return "Fuego {" + "nombre=" + getNombre() + ", porcentajeMuertes=" + getPorcentajeMuertes() + ", cantidadDinero=" + getCantidadDinero() + ", cantidadNaves=" + getCantidadNaves() + ", cantidadGuerreros=" + getCantidadGuerreros() + ", cantDineroTurno=" + getCantDineroTurno() + '}';
    }
    
}
