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
public class PlanetaNombre {
    private String nombrePlaneta;
    private String DueñoPlaneta;
    private String tipoGuerreros;

    public PlanetaNombre(String nombrePlaneta, String DueñoPlaneta, String tipoGuerreros) {
        this.nombrePlaneta = nombrePlaneta;
        this.DueñoPlaneta = DueñoPlaneta;
        this.tipoGuerreros = tipoGuerreros;
    }

    public String getNombrePlaneta() {
        return nombrePlaneta;
    }

    public void setNombrePlaneta(String nombrePlaneta) {
        this.nombrePlaneta = nombrePlaneta;
    }

    public String getDueñoPlaneta() {
        return DueñoPlaneta;
    }

    public void setDueñoPlaneta(String DueñoPlaneta) {
        this.DueñoPlaneta = DueñoPlaneta;
    }

    public String getTipoGuerreros() {
        return tipoGuerreros;
    }

    public void setTipoGuerreros(String tipoGuerreros) {
        this.tipoGuerreros = tipoGuerreros;
    }
    
}
