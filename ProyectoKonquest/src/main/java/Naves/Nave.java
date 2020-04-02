/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Naves;

/**
 *
 * @author sergi
 */
public class Nave {
    private int espacioNave;
    private int costoProduccion;
    private double velocidadNave;
    private int codigoNave;
    public Nave(int codigoNave, int espacioNave, int costo, double velocidad){
    this.codigoNave = codigoNave;
    this.espacioNave = espacioNave;
    this.costoProduccion = costo;
    this.velocidadNave = velocidad;
    }
    public int getEspacioNaves(){
    return this.espacioNave;
    }
    public int getCostoProduccion(){
    return this.costoProduccion;
    }
    public double getVelocidadNave(){
    return this.velocidadNave;
    }
    public int getCodigoNave(){
    return this.codigoNave;
    }
    public void setEspacioNave(int espacioNave) {
        this.espacioNave = espacioNave;
    }

    public void setCostoProduccion(int costoProduccion) {
        this.costoProduccion = costoProduccion;
    }

    public void setVelocidadNave(double velocidadNave) {
        this.velocidadNave = velocidadNave;
    }

    public void setCodigoNave(int codigoNave) {
        this.codigoNave = codigoNave;
    }
    
}
