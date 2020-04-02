/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Constructoress;

/**
 *
 * @author sergi
 */
public class Constructores {
    private int velocidadConstructor;
    private int precioCompraConstructores;
    private int precioVentaConstructores;
    private int codigoNaveConstruye;
    public Constructores(int velocidadConst, int preciocompra, int precioventa, int codigo){
        this.codigoNaveConstruye = codigo;
        this.precioCompraConstructores = preciocompra;
        this.precioVentaConstructores = precioventa;
        this.velocidadConstructor = velocidadConst;
    }
    public int getVelocidadConstructor(){
    return this.velocidadConstructor;
    }
    public int getPrecioCompraConstructor(){
    return this.precioCompraConstructores;
    }
    public int getprecioVentaConstructor(){
    return this.precioVentaConstructores;
    }
    public int getCodigoNave(){
    return this.codigoNaveConstruye;
    }
    public void setVelocidadConstructor(int velocidadConstructor) {
        this.velocidadConstructor = velocidadConstructor;
    }

    public void setPrecioCompraConstructores(int precioCompraConstructores) {
        this.precioCompraConstructores = precioCompraConstructores;
    }

    public void setPrecioVentaConstructores(int precioVentaConstructores) {
        this.precioVentaConstructores = precioVentaConstructores;
    }

    public void setCodigoNaveConstruye(int codigoNaveConstruye) {
        this.codigoNaveConstruye = codigoNaveConstruye;
    }
}
