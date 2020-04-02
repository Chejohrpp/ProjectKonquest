/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guerreros;

/**
 *
 * @author sergi
 */
public class Guerrero {
    private double factorMuerte;
    private int espacioOcupadoGuerrero;
    public Guerrero(double factormuerte, int espacioocupado){
        this.factorMuerte = factormuerte;
        this.espacioOcupadoGuerrero = espacioocupado;
    }
    public double getFatorMuerte(){
    return this.factorMuerte;
    }
    public int getEspacioOcupadoGuerrero(){
    return this.espacioOcupadoGuerrero;
    }
    public void setFactorMuerte(double factorMuerte) {
        this.factorMuerte = factorMuerte;
    }

    public void setEspacioOcupadoGuerrero(int espacioOcupadoGuerrero) {
        this.espacioOcupadoGuerrero = espacioOcupadoGuerrero;
    }
    
}
