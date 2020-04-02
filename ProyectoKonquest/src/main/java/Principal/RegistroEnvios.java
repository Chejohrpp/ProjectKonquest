package Principal;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chejohrpp
 */
public class RegistroEnvios {
    private String planetaOrigen;
    private int cantGurerros;
    private String tipoGuerreros;
    private String tipoNave;
    private String planetaDestino;
    private int turnollegada;
    private String nombreJugador;
    private boolean Entregado;

    public RegistroEnvios(String planetaOrigen, int cantGurerros, String tipoGuerreros, String tipoNave, String planetaDestino, int turnollegada, String nombreJugador, boolean Entregado) {
        this.planetaOrigen = planetaOrigen;
        this.cantGurerros = cantGurerros;
        this.tipoGuerreros = tipoGuerreros;
        this.tipoNave = tipoNave;
        this.planetaDestino = planetaDestino;
        this.turnollegada = turnollegada;
        this.nombreJugador = nombreJugador;
        this.Entregado = Entregado;
    }

    public boolean isEntregado() {
        return Entregado;
    }

    public void setEntregado(boolean Entregado) {
        this.Entregado = Entregado;
    }
    
    public String getPlanetaOrigen() {
        return planetaOrigen;
    }

    public void setPlanetaOrigen(String planetaOrigen) {
        this.planetaOrigen = planetaOrigen;
    }

    public int getCantGurerros() {
        return cantGurerros;
    }

    public void setCantGurerros(int cantGurerros) {
        this.cantGurerros = cantGurerros;
    }

    public String getTipoGuerreros() {
        return tipoGuerreros;
    }

    public void setTipoGuerreros(String tipoGuerreros) {
        this.tipoGuerreros = tipoGuerreros;
    }

    public String getTipoNave() {
        return tipoNave;
    }

    public void setTipoNave(String tipoNave) {
        this.tipoNave = tipoNave;
    }

    public String getPlanetaDestino() {
        return planetaDestino;
    }

    public void setPlanetaDestino(String planetaDestino) {
        this.planetaDestino = planetaDestino;
    }

    public int getTurnollegada() {
        return turnollegada;
    }

    public void setTurnollegada(int turnollegada) {
        this.turnollegada = turnollegada;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    @Override
    public String toString() {
        return "planeta de Origen: " + planetaOrigen + ", cantidad Guerreros: " + cantGurerros + ", Tipo de Guerreros: " + tipoGuerreros + ", Tipo de Nave: " + tipoNave + ", Planeta de Destino: " + planetaDestino + ", Turno de llegada: " + turnollegada+"\n"  ;
    }
    
    
}
