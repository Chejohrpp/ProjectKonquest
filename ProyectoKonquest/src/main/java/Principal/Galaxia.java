package Principal;

import java.util.Scanner;
import java.util.Random;
import Constructoress.Arquitecto;
import Constructoress.Ingeniero;
import Constructoress.MaestroDeObra;
import Constructoress.Obrero;
import Guerreros.FisionGuy;
import Guerreros.Groot;
import Guerreros.Magma;
import Guerreros.Mole;
import Guerreros.Nemo;
import Naves.Millenial_Falcon;
import Naves.Naboo_N1;
import Naves.Star_Destroyer;
import Naves.X_Wing;
import Planetas.Agua;
import Planetas.Fuego;
import Planetas.Organico;
import Planetas.Planeta;
import Planetas.Radioactivo;
import Planetas.Tierra;
import java.text.DecimalFormat;
/**
 *
 * @author sergi
 * carnet:201931555
 */
public class Galaxia {    
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    int turnosj1;
    int turnosj2;
    int cantTierra;
    int cantAgua;
    int cantFuego;
    int cantOrganico;
    int cantRadioactivo;
    String tablero[][];
    String tableroNombrePlaneta[][];
    String tableroNombreDueño[][];
    boolean finJuego = false;
    boolean turnoJugador = true;
    //Variables para inicialzar el objeto y abstraer sus metodos, no se usa
    Tierra tierra1 = new Tierra("nombre",0.9,20,20,20,20,1);
    Agua agua1 = new Agua("nombre",0.9,20,20,20,20,1);
    Fuego fuego1 = new Fuego("nombre",0.9,20,20,20,20,1);
    Radioactivo radioactivo1 = new Radioactivo("nombre",0.9,20,20,20,20,1);
    Organico organico1 = new Organico("nombre",0.9,20,20,20,20,1);
    Planeta planeta = new Planeta("NOMBRE",02,1,1,1,20,1);
    Arquitecto arquitecto = new Arquitecto(1,250,175,3);
    Ingeniero ingeniero = new Ingeniero(1,300,200,4);
    MaestroDeObra maestroObra = new MaestroDeObra(2,100,70,2);
    Obrero obrero = new Obrero(3,50,40,1);
    Mole mole = new Mole(1.5,1);
    Nemo nemo = new Nemo(1.6,1);
    Magma magma = new Magma(1.75,2);
    Groot groot = new Groot(1.85,3);
    FisionGuy fisionGuy = new FisionGuy(1.95,4);
    Naboo_N1 nabooN1 = new Naboo_N1(1,25,40,1);
    X_Wing xWing = new X_Wing(2,42,50,1.25);
    Millenial_Falcon millenialFalcon = new Millenial_Falcon(3,58,70,1.5);
    Star_Destroyer starDestroyer = new Star_Destroyer(4,80,100,1.75); //aqui termina las variables de inicializacion
    
    Planeta planetaj1;
    Planeta planetaj2;
    Tierra[] tierra;
    Agua[] agua;
    Fuego[] fuego;
    Organico[] organico;
    Radioactivo[] radioactivo;
    PlanetaNombre[] planetaNombre;
    int cantEnvios;
    double porcentajeMuerte;
    int posicionPlaneta;
    boolean verificarEnvioTropas = false;
    RegistroEnvios[] registroTotal = new RegistroEnvios[1];    
    //Constantes para color fondo
    public static final String RED_BACKGROUND = "\u001B[41m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String PURPLE_BACKGROUND = "\u001B[45m";
    public static final String RESET = "\u001B[0m";//termina las constantes de color de fondo
    //Inicio del juego
    public void Started(int tamañoX,int tamañoY, int cantidadPlanetasNeutrales, String j1, String j2){
        probabilidadPlaneta(cantidadPlanetasNeutrales);
        tablero = new String[tamañoX][tamañoY];      
        tableroNombrePlaneta = new String[tamañoX][tamañoY];
        tableroNombreDueño = new String[tamañoX][tamañoY];
        planetaNombre = new PlanetaNombre[cantidadPlanetasNeutrales+2];
        ModificarPlaneta(j1,j2,cantidadPlanetasNeutrales);
        RellenarPlanetas();
        UbicarPlanetasMapa(cantidadPlanetasNeutrales,tamañoX,tamañoY,j1,j2);
        while (finJuego == false){            
            Game(turnoJugador,j1,turnosj1,tamañoX,tamañoY);             
            Game(turnoJugador,j2,turnosj2,tamañoX,tamañoY);  
            turnosj1 += 1;           
            BatallaPlanetas(turnosj1,j1,1);
            VerificarJuego(j1);
            turnosj2 += 1;
            BatallaPlanetas(turnosj2,j2,2);
            VerificarJuego(j2);
            buscarPlaneta("planeta",2);
        }
    }
    //Sacar la probabilidad si el tipo de planeta puede o no aparecer
    public void probabilidadPlaneta(int cantidadPlanetasNeutrales){
        Random random = new Random();
        for (int i = 0; i < cantidadPlanetasNeutrales; i++) {
            int probabilidadPlaneta = random.nextInt(100)+1;
            if (probabilidadPlaneta >= 1 && probabilidadPlaneta <= 45){
                cantTierra += 1 ;
            } else if (probabilidadPlaneta > 45 && probabilidadPlaneta <= 70){
                cantAgua += 1 ; 
            } else if (probabilidadPlaneta > 70 && probabilidadPlaneta <= 85){
                cantFuego += 1 ; 
            } else if (probabilidadPlaneta > 85 && probabilidadPlaneta <= 95){
                cantOrganico += 1 ; 
            } else if (probabilidadPlaneta > 95 && probabilidadPlaneta <= 100){
                cantRadioactivo += 1 ; 
            } 
        }
    }
    // Se usa para modificar el planeta de los dos jugadores al inicio de la partida
    public void ModificarPlaneta(String jugador1, String jugador2,int cantPlaneta){  
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre de su planeta " +jugador1);
        String NombrePlaneta1 = scanner.nextLine();
        System.out.println("Ingrese el nombre de su planeta " +jugador2);
        String NombrePlaneta2 = scanner.nextLine();
        System.out.println("ATENCION: Los datos que ingresen a partir de ahora son para los dos planetas para un mejor balance");
        System.out.printf("Los datos que no aparecen seran puestos de forma aleatoria diferente en cada planeta \n");
        System.out.println("Ingrese el numero para el tipo de planeta ");
        System.out.printf(" 1.Tierra \n 2.Agua \n 3.Fuego \n 4.Organico \n 5.Radioactivo\n");
        int TipoPlaneta = Integer.parseInt(scanner.nextLine());
        System.out.printf("Ingrese la cantidad Dinero Inicial del planeta \n");
        int cantidadInicialPLaneta = scanner.nextInt();
        System.out.printf("Ingrese la cantidad de constructores \n");
        int cantConstructores = scanner.nextInt();
        System.out.printf("Ingrese la cantidad de Naves \n");
        int cantNaves = scanner.nextInt();
        System.out.printf("Ingrese la cantidad de Guerreros \n");
        int cantGuerreros = scanner.nextInt();
        System.out.printf("Ingrese la cantidad Dinero para cada turno \n");
        int cantidadDinero = scanner.nextInt();
        planetaj1 = new Planeta(NombrePlaneta1,planeta.RandomPorcentajeMuertes(),cantidadInicialPLaneta,cantNaves,cantGuerreros,cantidadDinero,cantConstructores);
        planetaj2 = new Planeta(NombrePlaneta2,planeta.RandomPorcentajeMuertes(),cantidadInicialPLaneta,cantNaves,cantGuerreros,cantidadDinero,cantConstructores);
        VerificarTipoPlanetaJUgadores(TipoPlaneta,NombrePlaneta1,NombrePlaneta2,jugador1,jugador2,cantPlaneta);
    }
    //Se usa para ver el tipo de planeta elegido por los jugadores
    public void VerificarTipoPlanetaJUgadores(int tipoPlaneta, String Planetaj1, String Planetaj2, String j1, String j2, int cantPlaneta){        
        if (tipoPlaneta == 1) {
            planetaNombre[cantPlaneta] = new PlanetaNombre(Planetaj1,j1,"MOLE");
            planetaNombre[cantPlaneta+1] = new PlanetaNombre(Planetaj2,j2,"MOLE");
        }else if (tipoPlaneta == 2) {
            planetaNombre[cantPlaneta] = new PlanetaNombre(Planetaj1,j1,"NEMO");
            planetaNombre[cantPlaneta+1] = new PlanetaNombre(Planetaj2,j2,"NEMO");
        } else if (tipoPlaneta == 3) {
           planetaNombre[cantPlaneta] = new PlanetaNombre(Planetaj1,j1,"MAGMA");
           planetaNombre[cantPlaneta+1] = new PlanetaNombre(Planetaj2,j2,"MAGMA");
        }else if (tipoPlaneta == 4) {
            planetaNombre[cantPlaneta] = new PlanetaNombre(Planetaj1,j1,"GROOT");
            planetaNombre[cantPlaneta+1] = new PlanetaNombre(Planetaj2,j2,"GROOT");
        } else if (tipoPlaneta == 5) {
            planetaNombre[cantPlaneta] = new PlanetaNombre(Planetaj1,j1,"FISION GUY");
            planetaNombre[cantPlaneta+1] = new PlanetaNombre(Planetaj2,j2,"FISION GUY");
        }
    }
    //Se usa para llenar los atributos de los planetas dependiendo de su tipo
    public void RellenarPlanetas(){
        if (cantTierra > 0) {
            tierra = new Tierra[cantTierra];
            for (int i = 0; i < tierra.length; i++) {
                char nombre = (char)(i+65);
                String nOmbre = Character.toString(nombre);
                tierra[i] = new Tierra(nOmbre,planeta.RandomPorcentajeMuertes(),planeta.RandomcantDinero(),planeta.RandomCantNaves(),tierra1.RandomCantGuerreroFinalizarTurno(),tierra1.RandomCantDineroTurno(),1);
                planetaNombre[i] = new PlanetaNombre(nOmbre,"Neutral","MOLE");
                //System.out.println(tierra[i].toString());
            }
        }
        if (cantAgua > 0) {
            agua = new Agua[cantAgua];
            for (int i = 0; i < agua.length; i++) {
                char nombre = (char)(i+65+cantTierra);
                String nOmbre = Character.toString(nombre);
                agua[i] = new Agua(nOmbre,planeta.RandomPorcentajeMuertes(),planeta.RandomcantDinero(),planeta.RandomCantNaves(),agua1.RandomCantGuerreroFinalizarTurno(),agua1.RandomCantDineroTurno(),1);
                planetaNombre[i+cantTierra] = new PlanetaNombre(nOmbre,"Neutral","NEMO");
                //System.out.println(agua[i].toString());
            }
        }
        if (cantFuego > 0) {
            fuego = new Fuego[cantFuego];
            for (int i = 0; i < fuego.length; i++) {
                char nombre = (char)(i+65+cantTierra+cantAgua);
                String nOmbre = Character.toString(nombre);
                fuego[i] = new Fuego(nOmbre,planeta.RandomPorcentajeMuertes(),planeta.RandomcantDinero(),planeta.RandomCantNaves(),fuego1.RandomCantGuerreroFinalizarTurno(),fuego1.RandomCantDineroTurno(),1);
                planetaNombre[i+cantTierra+cantAgua] = new PlanetaNombre(nOmbre,"Neutral","MAGMA");
                //System.out.println(fuego[i].toString());
            }
        }
        if (cantOrganico > 0) {
            organico = new Organico[cantOrganico];
            for (int i = 0; i < organico.length; i++) {
                char nombre = (char)(i+65+cantTierra+cantAgua+cantFuego);
                String nOmbre = Character.toString(nombre);
                organico[i] = new Organico(nOmbre,planeta.RandomPorcentajeMuertes(),planeta.RandomcantDinero(),planeta.RandomCantNaves(),organico1.RandomCantGuerreroFinalizarTurno(),organico1.RandomCantDineroTurno(),1);
                planetaNombre[i+cantTierra+cantAgua+cantFuego] = new PlanetaNombre(nOmbre,"Neutral","GROOT");
                //System.out.println(organico[i].toString());
            }
        }
        if (cantRadioactivo > 0) {
            radioactivo = new Radioactivo[cantRadioactivo];;
            for (int i = 0; i < radioactivo.length; i++) {
                char nombre = (char)(i+65+cantTierra+cantAgua+cantFuego+cantOrganico);
                String nOmbre = Character.toString(nombre);
                radioactivo[i] = new Radioactivo(nOmbre,planeta.RandomPorcentajeMuertes(),planeta.RandomcantDinero(),planeta.RandomCantNaves(),radioactivo1.RandomCantGuerreroFinalizarTurno(),radioactivo1.RandomCantDineroTurno(),1);
                planetaNombre[i+cantTierra+cantAgua+cantFuego+cantOrganico] = new PlanetaNombre(nOmbre,"Neutral","FISION GUY");
                //System.out.println(radioactivo[i].toString());
            }
        }
    }
    //Es el menu del jugador y las opciones que puede tomar
    public void Game(boolean TurnoJugador, String jugador, int cantTurnos,int tamañoX,int tamañoY){
        if (TurnoJugador) {
            DibujarMapa(tamañoX,tamañoY);
        }
        while (TurnoJugador == true){
            System.out.println("");
            System.out.println("MENU OPCIONES");
            System.out.println(jugador +" presione el numero de la accion que desea hacer");
            System.out.printf(" 1. Medicion de Distancias \n 2. Ver Planeta \n 3. Consultar Flotas \n 4. Envío Flota \n 5. Construir nave \n 6. Tienda \n 7. Terminar Turno \n"
                    + " 8. Rendirse ante el enemigo \n");
            int decision = scanner.nextInt();
            if (decision == 1) {
                MedicionDistancias();
            } else if (decision == 2) {
                VerPlaneta(jugador);
            } else if (decision == 3) {
                ConsultaFlotas(cantTurnos,jugador);
            } else if (decision == 4) {
                EnvioFlota(jugador,cantTurnos);
            } else if (decision == 5) {
                ConstruirNave(jugador);
            } else if (decision == 6) {
                Tienda();
            } else if (decision == 7) {
                TurnoJugador = false;
                
            } else if ( decision == 8) {
                System.out.println("Lo siento "+ jugador+" caistes bajo el imperio de tu enemigo");
                finJuego = true;
                turnoJugador = false;
                TurnoJugador = false;
            } else {
                System.out.println("Comando no aceptable");
            }
        }
    }
    //DIbuja el mapa
    public void DibujarMapa(int tamañoX, int tamañoY){
        System.out.println("");
        for (int i = 1; i <= tamañoX; i++) {
            char ascci = (char)(i+64);
            System.out.printf("%15C          ",ascci);
        }
        System.out.println("");
        for (int y = 0; y < tamañoY; y++) {
            System.out.printf("\n %d ",y+1);
            for (int x = 0; x < tamañoX; x++) {
                if (tablero[x][y] == null) {
                    System.out.printf(" %23s ", " ");
                } else{
                    
                    System.out.printf(" %23s ",tablero[x][y]);
                }
            }    
            System.out.printf(" \n \n");
        }
    }
    //Verificar si gano o perdio el juego
    public void VerificarJuego(String jugador){
        int contador = 0;
        for (int i = 0; i < planetaNombre.length; i++) {
            if (jugador.equalsIgnoreCase(planetaNombre[i].getDueñoPlaneta())) {
                contador += 1;
            }            
        }
        if (contador == planetaNombre.length) {
            System.out.println("Felicidades "+jugador+" acaba de ganar a su enemigo");
            finJuego = true;
            turnoJugador = false;
        }
    }
    //Es para medir la distancia entre un espacio/planeta a otro espacio/planeta dentro del tablero
    public void MedicionDistancias(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el planeta de origen hacia el planeta destino separado por una coma (,)");
        String medicionDistancia = scanner.nextLine();
        String[] planetas = medicionDistancia.split(",");
        int posicionPlanetaOrigenX = PosicionX(planetas[0]);
        int posicionPlanetaOrigenY = PosicionY(planetas[0]);
        int posicionPlanetaDestinoX = PosicionX(planetas[1]);
        int posicionPlanetaDestinoY = PosicionY(planetas[1]);
        double distancia = 0;
        if ((posicionPlanetaOrigenX-posicionPlanetaDestinoX) == 0) {
            if (posicionPlanetaOrigenY > posicionPlanetaDestinoY) {
                distancia = posicionPlanetaOrigenY - posicionPlanetaDestinoY; 
            }else{
                distancia = posicionPlanetaDestinoY - posicionPlanetaOrigenY;
            }
            System.out.println("La distancia desde "+planetas[0]+" hasta "+planetas[1]+" es de: "+ distancia+ " años luz");
        } else if ((posicionPlanetaOrigenY-posicionPlanetaDestinoY) == 0) {
            if (posicionPlanetaOrigenX > posicionPlanetaDestinoX) {
                distancia = posicionPlanetaOrigenX - posicionPlanetaDestinoX; 
            }else{
                distancia = posicionPlanetaDestinoX - posicionPlanetaOrigenX;
            }
            System.out.println("La distancia desde "+planetas[0]+" hasta "+planetas[1]+" es de: "+ distancia+ " años luz");
        } else{
            distancia = Math.sqrt(Math.pow(posicionPlanetaDestinoX-posicionPlanetaOrigenX,2)+Math.pow(posicionPlanetaDestinoY-posicionPlanetaOrigenY,2));
            DecimalFormat formato = new DecimalFormat("#.00");
            String distanciaElegante = formato.format(distancia);
            System.out.println("La distancia desde "+planetas[0]+" hasta "+planetas[1]+" es de: "+ distanciaElegante+ " años luz");
        }
        
    }
    // Para ver los atributos de cada planeta
    public void VerPlaneta(String jugador){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el Planeta que desea ver sus Datos conforme a su posicion en el mapa");
        String PosicionMapa = scanner.nextLine();        
        int posicionX = PosicionX(PosicionMapa);
        int posicionY = PosicionY(PosicionMapa);
        //System.out.printf("posicion X: %d \n posicion Y %d \n",posicionX,posicionY); Es para verificar la posicion del tablero
        if (tablero[posicionX][posicionY] != null) {
            if (jugador.equalsIgnoreCase(tableroNombreDueño[posicionX][posicionY])) {
                buscarPlaneta(tableroNombrePlaneta[posicionX][posicionY],1);
                System.out.println("");
            } else if (tableroNombreDueño[posicionX][posicionY].equalsIgnoreCase("Neutral")) {
                 buscarPlaneta(tableroNombrePlaneta[posicionX][posicionY],1);
                 System.out.println("");
            } else{
                System.out.println("Este Planeta no le pertenece, No puede ver sus atributos");
            }
        } else{
            System.out.println("No hay Ningun planeta en esa Posicion");
        }
    }
    //calcular la posicion X del tablero 
    public int PosicionX(String cadena){
        char posX = cadena.charAt(0);
        String POSX = Character.toString(posX);
        POSX = POSX.toUpperCase();
        posX = POSX.charAt(0);
        int posicionX = ((int)posX)-65;
        return posicionX;
    }
    //calcular la posicion Y del tablero
    public int PosicionY(String cadena){
        if (cadena.length() > 2) {
            String PosY = "";
            for (int i = 1; i < cadena.length(); i++) {
                PosY = PosY + cadena.charAt(i);                
            }
            int posicionY = Integer.parseInt(PosY)-1;
            return posicionY;
        } else{
        char posY = cadena.charAt(1);
        String POSY = Character.toString(posY);
        int posicionY = Integer.parseInt(POSY)-1;
        return posicionY;   
        }
    }
    //busca el planeta e imprime sus datos o regenera los planetas en cada finalizacion de turno
    public void buscarPlaneta(String NombrePlaneta, int accionHacer){
        int n = 0; //para no verificar las demas condicionales y generar mucha memoria
        if (cantTierra > 0 && n == 0) {
            for (int i = 0; i < tierra.length; i++) {                
                if (accionHacer == 1) {
                    if (NombrePlaneta.equals(tierra[i].getNombre())) {
                        System.out.printf("\n Nombre: %s \n tipo Planeta: Tierra \n Porcentaje de Muerte: %.2f %n \n Cantidad De dinero %d "
                            + "\n Cantidad de Naves: %d \n Cantidad de Guerreros: %d \n Cantidad de Constructores: %d", tierra[i].getNombre(),
                            tierra[i].getPorcentajeMuertes(),tierra[i].getCantidadDinero(),tierra[i].getCantidadNaves(),tierra[i].getCantidadGuerreros(),
                            tierra[i].getCantConstructores());
                        n = 1; //Si encontro ya el planeta
                        break;
                    }
                } else if (accionHacer == 2) {
                    tierra[i].setCantidadDinero(tierra[i].getCantidadDinero()+tierra[i].getCantDineroTurno());
                    tierra[i].setCantidadGuerreros(tierra[i].getCantidadGuerreros()+tierra[i].RandomCantGuerreroFinalizarTurno());
                } else if (accionHacer == 3) {
                    if (NombrePlaneta.equalsIgnoreCase(tierra[i].getNombre())) {
                        tierra[i].setCantidadNaves(tierra[i].getCantidadNaves()+1);
                    } else if (accionHacer == 4) {
                        if (NombrePlaneta.equalsIgnoreCase(tierra[i].getNombre())) {
                            porcentajeMuerte = tierra[i].getPorcentajeMuertes();
                        }
                    }
                }
            }
        }  if(cantAgua > 0 && n == 0) {
             for (int i = 0; i < agua.length; i++) {                
                if (accionHacer == 1) {
                    if (NombrePlaneta.equals(agua[i].getNombre())) {
                        System.out.printf("\n Nombre: %s \n tipo Planeta: Agua \n Porcentaje de Muerte: %.2f %n \n Cantidad De dinero %d "
                            + "\n Cantidad de Naves: %d \n Cantidad de Guerreros: %d \n Cantidad de Constructores: %d", agua[i].getNombre(),
                            agua[i].getPorcentajeMuertes(),agua[i].getCantidadDinero(),agua[i].getCantidadNaves(),agua[i].getCantidadGuerreros(),
                            agua[i].getCantConstructores());
                        n = 1;
                        break;
                    }
                } else if (accionHacer == 2) {
                    agua[i].setCantidadDinero(agua[i].getCantidadDinero()+agua[i].getCantDineroTurno());
                    agua[i].setCantidadGuerreros(agua[i].getCantidadGuerreros()+agua[i].RandomCantGuerreroFinalizarTurno());
                } else if (accionHacer == 3) {
                    if (NombrePlaneta.equalsIgnoreCase(agua[i].getNombre())) {
                        agua[i].setCantidadNaves(agua[i].getCantidadNaves()+1);
                    }
                } else if (accionHacer == 4) {
                        if (NombrePlaneta.equalsIgnoreCase(agua[i].getNombre())) {
                            porcentajeMuerte = agua[i].getPorcentajeMuertes();
                        }
                    }
            }
        }
        if(cantFuego > 0 && n == 0) {
             for (int i = 0; i < fuego.length; i++) {                
                if (accionHacer == 1) {
                    if (NombrePlaneta.equals(fuego[i].getNombre())) {
                        System.out.printf("\n Nombre: %s \n tipo Planeta: Fuego \n Porcentaje de Muerte: %.2f %n \n Cantidad De dinero %d "
                            + "\n Cantidad de Naves: %d \n Cantidad de Guerreros: %d \n Cantidad de Constructores: %d", fuego[i].getNombre(),
                            fuego[i].getPorcentajeMuertes(),fuego[i].getCantidadDinero(),fuego[i].getCantidadNaves(),fuego[i].getCantidadGuerreros(),
                            fuego[i].getCantConstructores());
                        n = 1;
                        break;
                    }
                } else if (accionHacer == 2) {
                    fuego[i].setCantidadDinero(fuego[i].getCantidadDinero()+fuego[i].getCantDineroTurno());
                    fuego[i].setCantidadGuerreros(fuego[i].getCantidadGuerreros()+fuego[i].RandomCantGuerreroFinalizarTurno());
                } else if (accionHacer == 3) {
                    if (NombrePlaneta.equalsIgnoreCase(fuego[i].getNombre())) {
                        fuego[i].setCantidadNaves(fuego[i].getCantidadNaves()+1);
                    }
                }else if (accionHacer == 4) {
                        if (NombrePlaneta.equalsIgnoreCase(fuego[i].getNombre())) {
                            porcentajeMuerte = fuego[i].getPorcentajeMuertes();
                        }
                    }
            }
        }
        if(cantOrganico > 0 && n == 0) {
             for (int i = 0; i < organico.length; i++) {                
                if (accionHacer == 1) {
                    if (NombrePlaneta.equals(organico[i].getNombre())) {
                        System.out.printf("\n Nombre: %s \n tipo Planeta: Organico \n Porcentaje de Muerte: %.2f %n \n Cantidad De dinero %d "
                            + "\n Cantidad de Naves: %d \n Cantidad de Guerreros: %d \n Cantidad de Constructores: %d", organico[i].getNombre(),
                            organico[i].getPorcentajeMuertes(),organico[i].getCantidadDinero(),organico[i].getCantidadNaves(),organico[i].getCantidadGuerreros(),
                            organico[i].getCantConstructores());
                        n = 1; 
                        break;
                    }
                } else if (accionHacer == 2) {
                    organico[i].setCantidadDinero(organico[i].getCantidadDinero()+organico[i].getCantDineroTurno());
                    organico[i].setCantidadGuerreros(organico[i].getCantidadGuerreros()+organico[i].RandomCantGuerreroFinalizarTurno());
                }  else if (accionHacer == 3) {
                    if (NombrePlaneta.equalsIgnoreCase(organico[i].getNombre())) {
                        organico[i].setCantidadNaves(organico[i].getCantidadNaves()+1);
                    }
                } else if (accionHacer == 4) {
                        if (NombrePlaneta.equalsIgnoreCase(organico[i].getNombre())) {
                            porcentajeMuerte = organico[i].getPorcentajeMuertes();
                        }
                    }
            }
        }
        if(cantRadioactivo > 0 && n == 0) {
             for (int i = 0; i < radioactivo.length; i++) {                
                if (accionHacer == 1) {
                    if (NombrePlaneta.equals(radioactivo[i].getNombre())) {
                        System.out.printf("\n Nombre: %s \n tipo Planeta: Radioactivo \n Porcentaje de Muerte: %.2f %n \n Cantidad De dinero %d "
                            + "\n Cantidad de Naves: %d \n Cantidad de Guerreros: %d \n Cantidad de Constructores: %d", radioactivo[i].getNombre(),
                            radioactivo[i].getPorcentajeMuertes(),radioactivo[i].getCantidadDinero(),radioactivo[i].getCantidadNaves(),radioactivo[i].getCantidadGuerreros(),
                            radioactivo[i].getCantConstructores());
                        n = 1;
                        break;
                    }
                } else if (accionHacer == 2) {
                    radioactivo[i].setCantidadDinero(radioactivo[i].getCantidadDinero()+radioactivo[i].getCantDineroTurno());
                    radioactivo[i].setCantidadGuerreros(radioactivo[i].getCantidadGuerreros()+radioactivo[i].RandomCantGuerreroFinalizarTurno());
                }  else if (accionHacer == 3) {
                    if (NombrePlaneta.equalsIgnoreCase(radioactivo[i].getNombre())) {
                        radioactivo[i].setCantidadNaves(radioactivo[i].getCantidadNaves()+1);
                    }
                } else if (accionHacer == 4) {
                        if (NombrePlaneta.equalsIgnoreCase(radioactivo[i].getNombre())) {
                            porcentajeMuerte = radioactivo[i].getPorcentajeMuertes();
                        }
                    }
            }
        } 
        if (accionHacer == 1) {
            if (NombrePlaneta.equalsIgnoreCase(planetaj1.getNombre())) {
            System.out.printf("\n Nombre: %s \n Porcentaje de Muerte: %.2f %n \n Cantidad De dinero %d "
                            + "\n Cantidad de Naves: %d \n Cantidad de Guerreros: %d \n Cantidad de Constructores: %d", planetaj1.getNombre(),
                            planetaj1.getPorcentajeMuertes(),planetaj1.getCantidadDinero(),planetaj1.getCantidadNaves(),planetaj1.getCantidadGuerreros(),
                            planetaj1.getCantConstructores());            
        }
        } else if (accionHacer == 2) {
            planetaj1.setCantidadDinero(planetaj1.getCantidadDinero()+planetaj1.getCantDineroTurno());
            planetaj1.setCantidadGuerreros(planetaj1.getCantidadGuerreros()+planetaj1.RandomCantGuerreroFinalizarTurno());
        }  else if (accionHacer == 3) {
                    if (NombrePlaneta.equalsIgnoreCase(planetaj1.getNombre())) {
                        planetaj1.setCantidadNaves(planetaj1.getCantidadNaves()+1);
                    }
        } else if (accionHacer == 4) {
                        if (NombrePlaneta.equalsIgnoreCase(planetaj1.getNombre())) {
                            porcentajeMuerte = planetaj1.getPorcentajeMuertes();
                        }
                    } 
        
        if (accionHacer == 1) {
            if (NombrePlaneta.equalsIgnoreCase(planetaj2.getNombre())) {
            System.out.printf("\n Nombre: %s \n Porcentaje de Muerte: %.2f %n \n Cantidad De dinero %d "
                            + "\n Cantidad de Naves: %d \n Cantidad de Guerreros: %d \n Cantidad de Constructores: %d", planetaj2.getNombre(),
                            planetaj2.getPorcentajeMuertes(),planetaj2.getCantidadDinero(),planetaj2.getCantidadNaves(),planetaj2.getCantidadGuerreros(),
                            planetaj2.getCantConstructores());
        }
        } else if (accionHacer == 2) {
            planetaj2.setCantidadDinero(planetaj2.getCantidadDinero()+planetaj2.getCantDineroTurno());
            planetaj2.setCantidadGuerreros(planetaj2.getCantidadGuerreros()+planetaj2.RandomCantGuerreroFinalizarTurno());
        }  else if (accionHacer == 3) {
                    if (NombrePlaneta.equalsIgnoreCase(planetaj2.getNombre())) {
                        planetaj2.setCantidadNaves(planetaj2.getCantidadNaves()+1);
                    }
        } else if (accionHacer == 4) {
                        if (NombrePlaneta.equalsIgnoreCase(planetaj2.getNombre())) {
                            porcentajeMuerte = planetaj2.getPorcentajeMuertes();
                        }
                    }
    }
    //Consultar las naves que fueron enviadas y no han llegado
    public void ConsultaFlotas(int turnoActual, String jugador){  
        System.out.println("El turno actual: "+ turnoActual);
        System.out.println("Envios de naves que no han llegado a su destino: \n");
        int n = 0; //verificar si hay al menos una nave viajando
        if (registroTotal[0] == null) {
            System.out.println(" \n No hay naves viajando en este momento \n");
        }else if (registroTotal[0] != null && cantEnvios == 1 && registroTotal[0].isEntregado() == true) {
             if (jugador.equalsIgnoreCase(registroTotal[0].getNombreJugador())) {
                System.out.println(registroTotal[0].toString());
            }
        } else{
            for (int i = 0; i < registroTotal.length; i++) {
            if (jugador.equalsIgnoreCase(registroTotal[i].getNombreJugador())&& registroTotal[i].isEntregado() == true) {
                System.out.println(registroTotal[i].toString());
                n = 1;
            }
            }
            if (n == 0) {
                System.out.println("\n No hay naves viajando en este momento\n");
            }
        }       
    }
    //Para enviar tropas
    public void EnvioFlota(String jugador,int turnoActual){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Mande su tropa a conquistar con el formtato: Planeta Origen,Cantidad Guerreros,Tipo Guerreros,Tipo Nave: NABOO N-1/X-WING/MILLENIAL FALCON/STAR DESTROYER,Planeta Destino");
        String cadenaEnvioFlota = scanner.nextLine();
        String[] EnvioFlota = cadenaEnvioFlota.split(",");
        int PosicionOrigenX = PosicionX(EnvioFlota[0]);
        int PosicionOrigenY = PosicionY(EnvioFlota[0]);
        int PosicionDestinoX = PosicionX(EnvioFlota[4]);
        int PosicionDestinoY = PosicionY(EnvioFlota[4]);
        int cantGuerreros = Integer.parseInt(EnvioFlota[1]);
        String tipoGuerreros = EnvioFlota[2];
        String tipoNave = EnvioFlota[3];        
        boolean noHayProblemas = true;        
        double distancia = calcularDistancia(PosicionOrigenX,PosicionDestinoX,PosicionOrigenY,PosicionDestinoY);
        double velocidadNave = 1;
        if (tipoNave.equalsIgnoreCase("NABOO N-1")) {
            velocidadNave = nabooN1.getVelocidadNave();
        } else if (tipoNave.equalsIgnoreCase("X-WING")) {
            velocidadNave = xWing.getVelocidadNave();
        }else if (tipoNave.equalsIgnoreCase("MILLENIAL FALCON")) {
            velocidadNave = millenialFalcon.getVelocidadNave();
        }else if (tipoNave.equalsIgnoreCase("STAR DESTROYER")) {
            velocidadNave = starDestroyer.getVelocidadNave();
        } else{
        System.out.println("No se reconoce el tipo de nave");
        noHayProblemas = false;
        }
        double turnoLlegadaDouble = (distancia/velocidadNave);
        int turnoLlegada = (int)Math.round(turnoLlegadaDouble);
        turnoLlegada = turnoLlegada +turnoActual;
        boolean PlanetaOrigen = verificarPlanetaEsJugador(PosicionOrigenX,PosicionOrigenY,jugador);
        if (PlanetaOrigen == false) {
            System.out.println("El planeta de origen colocado no es suyo");
            noHayProblemas = false;
        }else{
            verificarEnvioTropas = false;
            boolean cantGuerrerosVerificar = CambiarAtributosPlanetas(tableroNombrePlaneta[PosicionOrigenX][PosicionOrigenY],cantGuerreros,1);
            boolean verificarTipoGuerrero = VerificarTipoGuerrero(tableroNombrePlaneta[PosicionOrigenX][PosicionOrigenY],tipoGuerreros);
            boolean cantNaves = CambiarAtributosPlanetas(tableroNombrePlaneta[PosicionOrigenX][PosicionOrigenY],cantGuerreros,2);
            if (cantGuerrerosVerificar == false) {
                noHayProblemas = false;
                
                System.out.println("Supera el limite de guerreros que coloco");
            }
            if (verificarTipoGuerrero == false) {
                noHayProblemas = false;
                
                System.out.println("No hay ese tipo de Guerrero en el planeta de origen");
            }
            if (cantNaves == false) {
                noHayProblemas = false;
                System.out.println("No hay suficientes naves para enviarlos al Planeta destino");
            }
        }
        PlanetaOrigen = verificarSiExistePlaneta(PosicionDestinoX,PosicionDestinoY);
        if (PlanetaOrigen == false) {
            System.out.println("El planeta de destino colocado no existe");
            noHayProblemas = false;
        }
        PlanetaOrigen = verificarPlanetaEsJugador(PosicionDestinoX,PosicionDestinoY,jugador);
        if (PlanetaOrigen == true) {
            System.out.println("No puedes enviar tropas de conquista a planetas que son gobernados por usted");
            noHayProblemas = false;
        }
        if (noHayProblemas == true) {
            cantEnvios += 1;
            RedimensionarRegistroTotal();
            registroTotal[cantEnvios-1] = new RegistroEnvios(EnvioFlota[0],cantGuerreros,tipoGuerreros,tipoNave,EnvioFlota[4],turnoLlegada,jugador,true);
            System.out.println("El envio de su nave fue exitoso");
            verificarEnvioTropas = true;
            boolean cantGuerrerosVerificar = CambiarAtributosPlanetas(tableroNombrePlaneta[PosicionOrigenX][PosicionOrigenY],cantGuerreros,1);            
            boolean cantNaves = CambiarAtributosPlanetas(tableroNombrePlaneta[PosicionOrigenX][PosicionOrigenY],cantGuerreros,2);
        }
        
    }
    //Verificar Si el planeta Ingresado al envio de flotas es del jugador que lo ordeno
    public boolean verificarPlanetaEsJugador(int posicionX, int posicionY,String jugador){
        if (jugador.equalsIgnoreCase(tableroNombreDueño[posicionX][posicionY])) {
            return true;
        } else{
            return false;
        }
    }
    //Verificar si el planeta existe hacia donde va la nave
    public boolean verificarSiExistePlaneta(int posiscionX, int posicionY){
        if (tablero[posiscionX][posicionY] != null) {
            return true;
        } else {
            return false;
        }
    }
    //veririfcar el tipo de Guerrero de ese planeta
    public boolean VerificarTipoGuerrero(String nombrePlaneta, String TipoGuerrero){
        for (int i = 0; i < planetaNombre.length; i++) {
            if (planetaNombre[i].getNombrePlaneta().equalsIgnoreCase(nombrePlaneta)) {
                if (planetaNombre[i].getTipoGuerreros().equalsIgnoreCase(TipoGuerrero)) {
                    return true;
                }
            }
            
        }
        return false;
    }
    //cambiar los atributos de los planetas
    public boolean CambiarAtributosPlanetas(String NombrePlaneta, int cantGuerrerosEnviados, int opcionCambiar){ 
        if (cantTierra > 0) {
            for (int i = 0; i < tierra.length; i++) {
                if (opcionCambiar == 1) {
                    if (NombrePlaneta.equalsIgnoreCase(tierra[i].getNombre())) {
                        if (tierra[i].getCantidadGuerreros()>=cantGuerrerosEnviados && cantGuerrerosEnviados !=0) {
                            if (verificarEnvioTropas == true) {
                                tierra[i].setCantidadGuerreros(tierra[i].getCantidadGuerreros()-cantGuerrerosEnviados);
                            }
                        
                        return true;
                        }   
                    } 
                } else if (opcionCambiar == 2) {
                    if (NombrePlaneta.equalsIgnoreCase(tierra[i].getNombre())) {
                        if (tierra[i].getCantidadNaves()>=1 ) {
                            if (verificarEnvioTropas == true) {
                                tierra[i].setCantidadNaves(tierra[i].getCantidadNaves()-1);
                            }
                        return true;
                        }   
                    } 
                }                                   
            }
        }
        if (cantFuego > 0) {
            for (int i = 0; i < fuego.length; i++) {
                if (opcionCambiar == 1) {
                    if (NombrePlaneta.equalsIgnoreCase(fuego[i].getNombre())) {
                        if (fuego[i].getCantidadGuerreros()>=cantGuerrerosEnviados && cantGuerrerosEnviados !=0 ) {
                            if (verificarEnvioTropas == true) {
                                fuego[i].setCantidadGuerreros(fuego[i].getCantidadGuerreros()-cantGuerrerosEnviados);
                            }
                        
                        return true;                        
                    }                
                }  
                } else if (opcionCambiar == 2) {
                    if (NombrePlaneta.equalsIgnoreCase(fuego[i].getNombre())) {
                        if (fuego[i].getCantidadNaves()>=1 ) {
                            if (verificarEnvioTropas == true) {
                                fuego[i].setCantidadNaves(fuego[i].getCantidadNaves()-1);
                            }                        
                        return true;
                        }   
                    } 
                }                              
            }
        }
        if (cantAgua > 0) {
            for (int i = 0; i < agua.length; i++) {
                if (opcionCambiar == 1) {
                    if (NombrePlaneta.equalsIgnoreCase(agua[i].getNombre())) {
                        if (agua[i].getCantidadGuerreros()>=cantGuerrerosEnviados && cantGuerrerosEnviados !=0 ) {
                            if (verificarEnvioTropas == true) {
                                agua[i].setCantidadGuerreros(agua[i].getCantidadGuerreros()-cantGuerrerosEnviados);
                            }
                        
                        return true;
                    }                
                } 
                } else if (opcionCambiar == 2) {
                    if (NombrePlaneta.equalsIgnoreCase(agua[i].getNombre())) {
                        if (agua[i].getCantidadNaves()>=1 ) {
                            if (verificarEnvioTropas == true) {
                                agua[i].setCantidadNaves(agua[i].getCantidadNaves()-1);
                            }
                        
                        return true;
                        }   
                    } 
                }                                
            }
        }
        if (cantOrganico > 0) {
            for (int i = 0; i < organico.length; i++) {
                if (opcionCambiar == 1) {
                    if (NombrePlaneta.equalsIgnoreCase(organico[i].getNombre())) {
                        if (organico[i].getCantidadGuerreros()>=cantGuerrerosEnviados && cantGuerrerosEnviados !=0) {
                            if (verificarEnvioTropas == true) {
                                organico[i].setCantidadGuerreros(organico[i].getCantidadGuerreros()-cantGuerrerosEnviados);
                            }
                        
                        return true;
                    }                
                } 
                } else if (opcionCambiar == 2) {
                    if (NombrePlaneta.equalsIgnoreCase(organico[i].getNombre())) {
                        if (organico[i].getCantidadNaves()>=1 ) {
                            if (verificarEnvioTropas == true) {
                                organico[i].setCantidadNaves(organico[i].getCantidadNaves()-1);
                            }
                        
                        return true;
                        }   
                    } 
                }                                
            }
        }
        if (cantRadioactivo > 0) {
            for (int i = 0; i < radioactivo.length; i++) {
                if (opcionCambiar == 1) {
                   if (NombrePlaneta.equalsIgnoreCase(radioactivo[i].getNombre())) {
                    if (radioactivo[i].getCantidadGuerreros()>=cantGuerrerosEnviados && cantGuerrerosEnviados !=0) {
                        if (verificarEnvioTropas == true) {
                              radioactivo[i].setCantidadGuerreros(radioactivo[i].getCantidadGuerreros()-cantGuerrerosEnviados);   
                            }
                                               
                        return true;
                    }                
                }  
                } else if (opcionCambiar == 2) {
                    if (NombrePlaneta.equalsIgnoreCase(radioactivo[i].getNombre())) {
                        if (radioactivo[i].getCantidadNaves()>=1 ) {
                            if (verificarEnvioTropas == true) {
                                radioactivo[i].setCantidadNaves(radioactivo[i].getCantidadNaves()-1);
                            }
                        
                        return true;
                        }   
                    } 
                }                               
            }
        }
        if (NombrePlaneta.equalsIgnoreCase(planetaj1.getNombre())) {
            if (opcionCambiar == 1) {
                if (planetaj1.getCantidadGuerreros()>=cantGuerrerosEnviados && cantGuerrerosEnviados !=0) {
                    if (verificarEnvioTropas == true) {
                        planetaj1.setCantidadGuerreros(planetaj1.getCantidadGuerreros()-cantGuerrerosEnviados);
                     }
                    
                    return true;
                }  
            } else if (opcionCambiar == 2) {
                    if (NombrePlaneta.equalsIgnoreCase(planetaj1.getNombre())) {
                        if (planetaj1.getCantidadNaves()>=1 ) {
                            if (verificarEnvioTropas == true) {
                               planetaj1.setCantidadNaves(planetaj1.getCantidadNaves()-1); 
                            }
                            
                            return true;
                        }   
                    } 
                }           
            
        } if (NombrePlaneta.equalsIgnoreCase(planetaj2.getNombre())) {
            if (opcionCambiar == 1) {
                if (planetaj2.getCantidadGuerreros()>=cantGuerrerosEnviados && cantGuerrerosEnviados !=0) {
                    if (verificarEnvioTropas == true) {
                      planetaj2.setCantidadGuerreros(planetaj2.getCantidadGuerreros()-cantGuerrerosEnviados);          
                    }
                    
                    return true;
                }  
            } else if (opcionCambiar == 2) {
                    if (NombrePlaneta.equalsIgnoreCase(planetaj2.getNombre())) {
                        if (planetaj2.getCantidadNaves()>=1 ) {
                            if (verificarEnvioTropas == true) {
                               planetaj2.setCantidadNaves(planetaj2.getCantidadNaves()-1); 
                            }
                            
                            return true;
                        }   
                    } 
                }
        }
        return false;
    }
        
    //Ampliar el registro de envios
    public void RedimensionarRegistroTotal(){
        if (registroTotal[0] == null) {
            registroTotal = new RegistroEnvios[1];
        } else{
            RegistroEnvios[] registro = new RegistroEnvios[registroTotal.length];
            for (int i = 0; i < registroTotal.length; i++) {
                registro[i] = registroTotal[i];
            }
            registroTotal = new RegistroEnvios[cantEnvios];
            for (int i = 0; i < (registroTotal.length-1); i++) {
                registroTotal[i] = registro[i];
            }
        } 
    }
    //Calcular al distancia para enviar flotas
    public double calcularDistancia(int posicionPlanetaOrigenX, int posicionPlanetaDestinoX, int posicionPlanetaOrigenY, int posicionPlanetaDestinoY){
        double distancia = 0;
        if ((posicionPlanetaOrigenX-posicionPlanetaDestinoX) == 0) {
            if (posicionPlanetaOrigenY > posicionPlanetaDestinoY) {
                distancia = posicionPlanetaOrigenY - posicionPlanetaDestinoY; 
            }else{
                return distancia = posicionPlanetaDestinoY - posicionPlanetaOrigenY;
            }
        } else if ((posicionPlanetaOrigenY-posicionPlanetaDestinoY) == 0) {
            if (posicionPlanetaOrigenX > posicionPlanetaDestinoX) {
                return distancia = posicionPlanetaOrigenX - posicionPlanetaDestinoX; 
            }else{
                return distancia = posicionPlanetaDestinoX - posicionPlanetaOrigenX;
            }
        } else{
             return distancia = Math.sqrt(Math.pow(posicionPlanetaDestinoX-posicionPlanetaOrigenX,2)+Math.pow(posicionPlanetaDestinoY-posicionPlanetaOrigenY,2));
        }
        return distancia;
    }
    // Para construir la nave
    public void ConstruirNave(String jugador){
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿En donde quiere construir la nave?");
        System.out.println("Constructores Disponibles en los planeta:");
        String CadenaNaveIngeniero = scanner.nextLine();
        String NaveIngeniero[] = CadenaNaveIngeniero.split(",");
        int posicionPlanetaX = PosicionX(NaveIngeniero[0]);
        int posicionPlanetaY = PosicionY(NaveIngeniero[0]);
        String tipoConstructor = NaveIngeniero[1];
        boolean miPlaneta = verificarPlanetaEsJugador(posicionPlanetaX,posicionPlanetaY,jugador);
        if (miPlaneta==true) {
            
            if (tipoConstructor.equalsIgnoreCase("OBRERO")) {
                
                
            }else if (tipoConstructor.equalsIgnoreCase("MAESTRO DE OBRA")) {
               
            }else if (tipoConstructor.equalsIgnoreCase("ARQUITECTO")) {
                
            } else if (tipoConstructor.equalsIgnoreCase("INGENIERO")) {
                
            }else{
                System.out.println("No se reconoce el tipo de constructor indicado");
            }
        }else{
            
        }
        System.out.println("Fuera de Servicio");
    }
    //Verificar si hay Constructores
    public void verificarConstructores(int posicionPlanetaX, int posicionPlanetaY){ 
        boolean verificarConstructores = CambiarAtributosPlanetas(tableroNombrePlaneta[posicionPlanetaX][posicionPlanetaY],1,4);
        if (verificarConstructores == true) {
            
        } else{
            System.out.println("No hay sufientes constructores");
        }
    }
    //Para comprar o vender en la tienda
    public void Tienda(){        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido a la tienda \n Que opcion desea hacer: ");
        System.out.printf(" 1.Comprar Constructores \n 2.Vender Constructores \n 3.Vender Naves \n 4.Salir \n");
        int decisionTIenda = scanner.nextInt();
        if (decisionTIenda == 1) {
            
        }else if (decisionTIenda == 2) {
            
        } else if (decisionTIenda == 3) {
            
        } else{
        }
        System.out.println("\n ATENCION: la tienda no es disponible en este momento \n");
    }
    //Para comprar un constructor en la tienda
    public void ComprarConstructores(){
        
    }
    //Para vender un constructor en la tienda
    public void VenderConstructores(){
        
    }
    //Para vender Naves en la tienda
    public void VenderNaves(){
        
    }
    //Para conocer quien es el que vence y quien es el que gana en una arribo de planetas
    public void BatallaPlanetas(int turnoActual, String jugador,int codigoJugador){   
        Random random = new Random();
        if (registroTotal[0] != null && cantEnvios == 1) {
            if (registroTotal[0].getTurnollegada() == turnoActual && registroTotal[0].isEntregado() == true && jugador.equalsIgnoreCase(registroTotal[0].getNombreJugador())) {
                    registroTotal[0].setEntregado(false);
                    int posicionX = PosicionX(registroTotal[0].getPlanetaOrigen());
                    int posicionY = PosicionY(registroTotal[0].getPlanetaOrigen());
                    buscarPlaneta(tableroNombrePlaneta[posicionX][posicionY],3);
                    System.out.println("Nave devuelta a su planeta de Origen");
                    buscarPlaneta(tableroNombrePlaneta[posicionX][posicionY],4);
                    double porcentaje = 0;
                    if (registroTotal[0].getTipoGuerreros().equalsIgnoreCase("MOLE")) {
                        porcentaje = porcentajeMuerte*mole.getFatorMuerte();
                    } else if (registroTotal[0].getTipoGuerreros().equalsIgnoreCase("NEMO")) {
                        porcentaje = porcentajeMuerte*nemo.getFatorMuerte();
                    } else if (registroTotal[0].getTipoGuerreros().equalsIgnoreCase("GROOT")) {
                        porcentaje = porcentajeMuerte*groot.getFatorMuerte();
                    } else if (registroTotal[0].getTipoGuerreros().equalsIgnoreCase("MAGMA")) {
                        porcentaje = porcentajeMuerte*magma.getFatorMuerte();
                    } else if (registroTotal[0].getTipoGuerreros().equalsIgnoreCase("FISION GUY")) {
                        porcentaje = porcentajeMuerte*fisionGuy.getFatorMuerte();
                    }
                    int porcentajeEntero = (int)Math.round(porcentaje);
                    int aletoriedadJugada = random.nextInt(3);
                    if (porcentajeEntero+1 >= aletoriedadJugada) {
                        System.out.println("La conquista de "+jugador+" fue un exito hacia el planeta "+registroTotal[0].getPlanetaDestino());
                        int posicionDestinoX = PosicionX(registroTotal[0].getPlanetaDestino());
                        int posicionDestinoY = PosicionY(registroTotal[0].getPlanetaDestino());
                        cambiarDueño(tableroNombrePlaneta[posicionDestinoX][posicionDestinoY],jugador);
                        cambiarColor(codigoJugador,posicionPlaneta,posicionDestinoX,posicionDestinoY);
                        
                    }else{
                        System.out.println("La conquista de "+jugador+" fue un fracaso hacia el planeta "+registroTotal[0].getPlanetaDestino());
                    }
                }
        } else if (cantEnvios > 1) {
            for (int i = 0; i < registroTotal.length; i++) {
                if (registroTotal[i].getTurnollegada() == turnoActual && registroTotal[i].isEntregado() == true && jugador.equalsIgnoreCase(registroTotal[i].getNombreJugador())) {
                    registroTotal[i].setEntregado(false);
                    int posicionX = PosicionX(registroTotal[i].getPlanetaOrigen());
                    int posicionY = PosicionY(registroTotal[i].getPlanetaOrigen());
                    buscarPlaneta(tableroNombrePlaneta[posicionX][posicionY],3);
                    System.out.println("Nave devuelta a su planeta de Origen");
                    buscarPlaneta(tableroNombrePlaneta[posicionX][posicionY],4);
                    double porcentaje = 0;
                    if (registroTotal[i].getTipoGuerreros().equalsIgnoreCase("MOLE")) {
                        porcentaje = porcentajeMuerte*mole.getFatorMuerte();
                    } else if (registroTotal[i].getTipoGuerreros().equalsIgnoreCase("NEMO")) {
                        porcentaje = porcentajeMuerte*nemo.getFatorMuerte();
                    } else if (registroTotal[i].getTipoGuerreros().equalsIgnoreCase("GROOT")) {
                        porcentaje = porcentajeMuerte*groot.getFatorMuerte();
                    } else if (registroTotal[i].getTipoGuerreros().equalsIgnoreCase("MAGMA")) {
                        porcentaje = porcentajeMuerte*magma.getFatorMuerte();
                    } else if (registroTotal[i].getTipoGuerreros().equalsIgnoreCase("FISION GUY")) {
                        porcentaje = porcentajeMuerte*fisionGuy.getFatorMuerte();
                    }
                    int porcentajeEntero = (int)Math.round(porcentaje);
                    int aletoriedadJugada = random.nextInt(3);
                    if (porcentajeEntero+1 >= aletoriedadJugada) {
                        System.out.println("La conquista de "+jugador+" fue un exito hacia el planeta "+registroTotal[i].getPlanetaDestino());
                        int posicionDestinoX = PosicionX(registroTotal[i].getPlanetaDestino());
                        int posicionDestinoY = PosicionY(registroTotal[i].getPlanetaDestino());
                        cambiarDueño(tableroNombrePlaneta[posicionDestinoX][posicionDestinoY],jugador);
                        cambiarColor(codigoJugador,posicionPlaneta,posicionDestinoX,posicionDestinoY);
                        
                    }else{
                        System.out.println("La conquista de "+jugador+" fue un fracaso hacia el planeta "+registroTotal[i].getPlanetaDestino());
                    }
                }
            
            }
        }
    }
    //cambiamos el dueño de un planeta
    public void cambiarDueño(String nombrePlaneta,String jugador){
        for (int i = 0; i < planetaNombre.length; i++) {
            if (nombrePlaneta.equalsIgnoreCase(planetaNombre[i].getNombrePlaneta())) {
                planetaNombre[i].setDueñoPlaneta(jugador);
                posicionPlaneta = i;
                break;
            }            
        }
    }
    //modificar el metodo de cambiarColorDueño
    public void cambiarColor(int codigoJugador,int PosicionPlaneta,int randomX,int randomY){
                    if (codigoJugador == 1) {
                        tablero[randomX][randomY]= RED_BACKGROUND+"Nombre: "+planetaNombre[PosicionPlaneta].getNombrePlaneta()+" Dueño: "+planetaNombre[PosicionPlaneta].getDueñoPlaneta()+RESET;                        
                        tableroNombreDueño[randomX][randomY] = planetaNombre[PosicionPlaneta].getDueñoPlaneta();                        
                    } else if (codigoJugador == 2) {
                        tablero[randomX][randomY]= BLUE_BACKGROUND+"Nombre: "+planetaNombre[PosicionPlaneta].getNombrePlaneta()+" Dueño: "+planetaNombre[PosicionPlaneta].getDueñoPlaneta()+RESET;                        
                        tableroNombreDueño[randomX][randomY] = planetaNombre[PosicionPlaneta].getDueñoPlaneta();
                    }
    }
    //Asigna una ubicacion en el mapa a un planeta
    public void UbicarPlanetasMapa(int cantidadPlanetasNeutrales,int tamX,int tamY, String j1, String j2){
        Random random = new Random();
        for (int i = 0; i < cantidadPlanetasNeutrales+2; i++) {
           boolean ocupado = true;
            while(ocupado == true){
                int randomX = random.nextInt(tamX);
                int randomY = random.nextInt(tamY);
                if (tablero[randomX][randomY] == null) {
                   cambiarColorDueño(randomX,randomY,j1,j2,i);  
                   ocupado = false;
                }
            }
        }
    }
    //Asignarle un color a un planeta dependiendo del dueño
    public void cambiarColorDueño(int randomX, int randomY, String j1, String j2, int i){
         if (planetaNombre[i].getDueñoPlaneta().equalsIgnoreCase("Neutral")) {
                        tablero[randomX][randomY]= PURPLE_BACKGROUND+"Nombre: "+planetaNombre[i].getNombrePlaneta()+" Dueño: "+planetaNombre[i].getDueñoPlaneta()+RESET;
                        tableroNombrePlaneta[randomX][randomY] = planetaNombre[i].getNombrePlaneta();
                        tableroNombreDueño[randomX][randomY] = planetaNombre[i].getDueñoPlaneta();
                    } else if (planetaNombre[i].getDueñoPlaneta().equalsIgnoreCase(j1)) {
                        tablero[randomX][randomY]= RED_BACKGROUND+"Nombre: "+planetaNombre[i].getNombrePlaneta()+" Dueño: "+planetaNombre[i].getDueñoPlaneta()+RESET;
                        tableroNombrePlaneta[randomX][randomY] = planetaNombre[i].getNombrePlaneta();
                        tableroNombreDueño[randomX][randomY] = planetaNombre[i].getDueñoPlaneta();                        
                    } else if (planetaNombre[i].getDueñoPlaneta().equalsIgnoreCase(j2)) {
                        tablero[randomX][randomY]= BLUE_BACKGROUND+"Nombre: "+planetaNombre[i].getNombrePlaneta()+" Dueño: "+planetaNombre[i].getDueñoPlaneta()+RESET;
                        tableroNombrePlaneta[randomX][randomY] = planetaNombre[i].getNombrePlaneta();
                        tableroNombreDueño[randomX][randomY] = planetaNombre[i].getDueñoPlaneta();
                    }
    }
}
