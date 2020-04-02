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
public class Agua extends Planeta {

    public Agua(String nombre, double porcentajeMuertes, int cantidadDinero, int cantidadNaves, int cantidadGuerreros, int cantDineroTurno, int cantConstructores) {
        super(nombre, porcentajeMuertes, cantidadDinero, cantidadNaves, cantidadGuerreros, cantDineroTurno, cantConstructores);
    }


    


    @Override
        public int RandomCantGuerreroFinalizarTurno(){
        Random random = new Random();
        return random.nextInt(12)+(12);
    }
    @Override
     public int RandomCantDineroTurno(){
    Random random = new Random();
    return random.nextInt(61)+60;
    }
    @Override
    public String toString() {
        return "Agua {" + "nombre=" + getNombre() + ", porcentajeMuertes=" + getPorcentajeMuertes() + ", cantidadDinero=" + getCantidadDinero() + ", cantidadNaves=" + getCantidadNaves() + ", cantidadGuerreros=" + getCantidadGuerreros() + ", cantDineroTurno=" + getCantDineroTurno() + '}';
    }
}
