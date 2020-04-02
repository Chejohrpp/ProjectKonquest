package Planetas;
import static java.lang.Math.random;
import java.util.Random;

/**
 *
 * @author chejohrpp
 */
public class Planeta {
    Random random = new Random();
    private String nombre;
    private double porcentajeMuertes;
    private int cantidadDinero;
    private int cantidadNaves;
    private int cantidadGuerreros;    
    private int cantDineroTurno;
    private int cantConstructores;

    public Planeta(String nombre, double porcentajeMuertes, int cantidadDinero, int cantidadNaves, int cantidadGuerreros, int cantDineroTurno,int cantConstructores) {
        this.nombre = nombre;
        this.porcentajeMuertes = porcentajeMuertes;
        this.cantidadDinero = cantidadDinero;
        this.cantidadNaves = cantidadNaves;
        this.cantidadGuerreros = cantidadGuerreros;
        this.cantDineroTurno = cantDineroTurno;
        this.cantConstructores = cantConstructores;
    }

    public int getCantDineroTurno() {
        return cantDineroTurno;
    }

    public int getCantConstructores() {
        return cantConstructores;
    }

    public void setCantConstructores(int cantConstructores) {
        this.cantConstructores = cantConstructores;
    }

    public void setCantDineroTurno(int cantDineroTurno) {
        this.cantDineroTurno = cantDineroTurno;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPorcentajeMuertes() {
        return porcentajeMuertes;
    }
    public void setPorcentajeMuertes(double porcentajeMuertes) {
        this.porcentajeMuertes = porcentajeMuertes;
    }
    public int getCantidadDinero() {
        return cantidadDinero;
    }
    public void setCantidadDinero(int cantidadDinero) {
        this.cantidadDinero = cantidadDinero;
    }
    public int getCantidadNaves() {
        return cantidadNaves;
    }
    public void setCantidadNaves(int cantidadNaves) {
        this.cantidadNaves = cantidadNaves;
    }
    public int getCantidadGuerreros() {
        return cantidadGuerreros;
    }
    public void setCantidadGuerreros(int cantidadGuerreros) {
        this.cantidadGuerreros = cantidadGuerreros;
    }
    //Generar aleatoreamente la cantidad de Guerreros al finalizar un turno
    public int RandomCantGuerreroFinalizarTurno(){
    Random random = new Random();
    return random.nextInt(11)+15;
    }
    //Generar aleatoriamente el porcentaje de muerte a un planeta
    public double RandomPorcentajeMuertes(){
    return  Math.random();
    }
    //Generar aleatoriamente un cantidad de dinero para un planeta
    public int RandomcantDinero(){
    Random random = new Random();
    return random.nextInt(401)+100;
    }
    //Generar aleatoreamente una cantidad de naves a planeta
    public int RandomCantNaves(){
    Random random = new Random();
    return random.nextInt(3)+1;
    }
    //Un metodo usado para la clase hijos de generar cantidad de dinero por turnos al planeta
    public int RandomCantDineroTurno(){
    return 0;
    }
    @Override
    public String toString() {
        return "Planeta {" + "nombre=" + getNombre() + ", porcentajeMuertes=" + getPorcentajeMuertes() + ", cantidadDinero=" + getCantidadDinero() + ", cantidadNaves=" + getCantidadNaves() + ", cantidadGuerreros=" + getCantidadGuerreros() + ", cantDineroTurno=" + getCantDineroTurno() + '}';
    }
    
}
