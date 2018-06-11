/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bandos;

import RaulWorld.Menu;
import enumeraciones.Aviones;
import enumeraciones.Divisiones;
import enumeraciones.Edificaciones;
import enumeraciones.Razas;
import enumeraciones.Tanques;
import fabricas_abstractas.AbstractFactory;
import fabricas_concretas.*;
import fabricas_concretas.ProcesadoraHormigon;
import java.util.ArrayList;
import java.util.Scanner;
import productos_concretos.Flugzeuge.Stuka;
import productos_concretos.Flugzeuge.Tu95;
import productos_concretos.Panzers.T34;
import productos_concretos.Panzers.TigerI;
import fabricas_abstractas.Fabrica;
import fabricas_abstractas.Recursos;
import productos_abstractos.Avion;
import productos_abstractos.Division;
import productos_abstractos.Tanque;
import productos_abstractos.Unidad;
import productos_concretos.Flugzeuge.AvionUS;
import productos_concretos.Panzers.Sherman;
import productos_concretos.Soldados.ComandantesRojo;
import productos_concretos.Soldados.DivisionInfanteria;
import productos_concretos.Soldados.DivisionSS;
import productos_concretos.Soldados.MarinesUS;
import productos_concretos.Soldados.SoldadoRojo;
import productos_concretos.Soldados.SoldadosUS;

/**
 *
 * @author rau3
 */
public class CentroDeMando implements Unidad{

    int metal, moneda, hormigon, vida;
    int maxMetal, maxMoneda, maxHormigon;
    ArrayList<AbstractFactory> fabricas = new ArrayList();
    String comandante;
    Razas nombre;

    public CentroDeMando(String comandante) {
        this.moneda = 10000;
        this.hormigon = 5000;
        this.metal = 3000;
        this.maxMetal = 6000;
        this.maxMoneda = 20000;
        this.maxHormigon = 10000;
        this.vida = 7000;
        this.comandante = comandante;
    }

    public AbstractFactory crearEdificacion() throws Exception {
        Exception e = new Exception("No hay suficientes recursos");
        Scanner scanner = new Scanner(System.in);
        AbstractFactory nuevaEdif;
        boolean eleccion = true;
        int edificacion;
        while (eleccion) {
            nuevaEdif = null;
            try {
                System.out.println("\nIngrese el tipo de edificacion que desea construir: ");
                System.out.println("\n1.Fabrica de tanques\n2. Fabrica de Aviones\n3. Cuartel\n"
                        + "4. Mina de Metal\n5. Casa de impuestos\n6. Procesadora de Hormigon\n7. Salir");
                System.out.println("Tipo de edificacion: ");
                edificacion = scanner.nextInt();

                switch (edificacion) {
                    case 1:
                        if (FabricaTanques.getCostHormigon() <= this.hormigon && FabricaTanques.getCostMonedas() <= this.moneda) {
                            nuevaEdif = CreadorEdificaciones.getFactory(Edificaciones.Tanques);
                            this.hormigon -= FabricaTanques.getCostHormigon();
                            this.moneda -= FabricaTanques.getCostMonedas();
                            System.out.println("Se ha creado una fabrica de tanques");
                        } else {
                            throw e;
                        }

                        break;
                    case 2:
                        if (FabricaAviones.getCostMonedas() <= this.moneda && FabricaAviones.getCostHormigon() <= this.hormigon) {
                            nuevaEdif = CreadorEdificaciones.getFactory(Edificaciones.Aviones);
                            this.hormigon -= FabricaAviones.getCostHormigon();
                            this.moneda -= FabricaAviones.getCostMonedas();

                        } else {
                            throw e;
                        }
                        break;
                    case 3:
                        if (Academia.getCostHormigon() <= this.hormigon && Academia.getCostMonedas() <= this.moneda) {
                            nuevaEdif = CreadorEdificaciones.getFactory(Edificaciones.Academia);
                            this.hormigon -= Academia.getCostHormigon();
                            this.moneda -= Academia.getCostMonedas();
                        } else {
                            throw e;
                        }
                        break;
                    case 4:
                        if (MinaMetal.getCostHormigon() <= this.hormigon && MinaMetal.getCostMonedas() <= this.moneda) {
                            nuevaEdif = CreadorEdificaciones.getFactory(Edificaciones.MinaMetal);
                            MinaMetal imp = (MinaMetal) nuevaEdif;
                            switch (this.nombre) {
                                case Alemania:
                                    imp.setProduccion(500);
                                    break;
                                case EstadosUnidos:
                                    imp.setProduccion(550);
                                    break;
                                case UnionSovietica:
                                    imp.setProduccion(400);
                                    break;
                                default:
                                    break;
                            }
                            this.hormigon -= MinaMetal.getCostHormigon();
                            this.moneda -= MinaMetal.getCostMonedas();
                        } else {
                            throw e;
                        }
                        break;
                    case 5:
                        if (CasaDeImpuestosA.getCostHormigon() <= this.hormigon && CasaDeImpuestosA.getCostMonedas() <= this.moneda) {
                            nuevaEdif = CreadorEdificaciones.getFactory(Edificaciones.Impuestos);
                            CasaDeImpuestosA imp = (CasaDeImpuestosA) nuevaEdif;
                            switch (this.nombre) {
                                case Alemania:
                                    imp.setProduccion(600);
                                    break;
                                case EstadosUnidos:
                                    imp.setProduccion(550);
                                    break;
                                case UnionSovietica:
                                    imp.setProduccion(400);
                                    break;
                                default:
                                    break;
                            }

                            this.hormigon -= CasaDeImpuestosA.getCostHormigon();
                            this.moneda -= CasaDeImpuestosA.getCostMonedas();
                        } else {
                            throw e;
                        }
                        //nuevaEdif.setProduccion(500);
                        break;
                    case 6:
                        if (ProcesadoraHormigon.getCostHormigon() <= this.hormigon && ProcesadoraHormigon.getCostMonedas() <= this.moneda) {
                            nuevaEdif = CreadorEdificaciones.getFactory(Edificaciones.Procesadora);
                            ProcesadoraHormigon imp = (ProcesadoraHormigon) nuevaEdif;
                            switch (this.nombre) {
                                case Alemania:
                                    imp.setProduccion(300);
                                    break;
                                case EstadosUnidos:
                                    imp.setProduccion(300);
                                    break;
                                case UnionSovietica:
                                    imp.setProduccion(300);
                                    break;
                                default:
                                    break;
                            }

                            this.hormigon -= CasaDeImpuestosA.getCostHormigon();
                            this.moneda -= CasaDeImpuestosA.getCostMonedas();

                        } else {
                            throw e;
                        }
                        break;
                    case 7:
                        eleccion = false;

                }
                if (nuevaEdif == null) {
                    throw new Exception("No hay suficientes recursos");
                } else {
                    this.fabricas.add(nuevaEdif);
                    System.out.println("Se ha guardado en el hangar");
                }
            } catch (Exception ex) {
                System.err.println("No hay suficientes recursos");
            }
        }
        return null;
    }

    public void mostrarRecursos() {
        System.out.println("\nMonedas: " + this.moneda + "|" + "Metal: " + this.metal + "|" + "Hormigon: " + this.hormigon);
    }
    
    public boolean crearUnidad(){
        Scanner scanner = new Scanner(System.in);
        int opc;
        boolean eleccion = true;
        while (eleccion) {
            try {
                System.out.println("\n1. Crear Vehiculo\n2. Entrenar Milicia\n3. Salir");
                System.out.print("Ingrese una opcion: ");
                opc = scanner.nextInt();
                switch(opc){
                    case 1:
                        crearVehiculos();
                        break;
                    case 2:
                        entrenarMilicias();
                        break;
                    case 3:
                        return true;
                    
                }

            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                
            }
        }
        return true;
    }
    
    public boolean entrenarMilicias() {
        boolean eleccion = true;
        int opc;
        Divisiones div = null;
        while (eleccion) {
            mostrarRecursos();
            try {
                System.out.println("Ingrese tipo de soldados que desea crear: \n1. Division normal\n2. Division Elite\n3. Salir\n");
                Scanner scanner = new Scanner(System.in);
                opc = scanner.nextInt();
                switch (opc) {
                    case 1:
                        div = Divisiones.Normal;
                        break;
                    case 2:
                        div = Divisiones.Elite;
                        break;
                    case 3:
                        return true;
                    default:
                        throw new Exception("Ingrese una opcion correcta");
                        
                }
                getMilicia(div);
                

            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }

        }

        return true;
    }

    public void getMilicia(Divisiones div) throws Exception {
        Fabrica edif;
        Exception e = new Exception("No hay suficientes recursos");
        for (AbstractFactory f : this.fabricas) {
            edif = (Fabrica) f;
            if (edif.getTipoEdificacion() == Edificaciones.Academia) {
                if (edif.getHangerSize() < 10) {
                    if (this.nombre == Razas.Alemania) {
                        if (div == Divisiones.Elite) {
                            if (DivisionSS.getCosMoneda() <= this.moneda) {
                                f.entrenarSoldados(Divisiones.SS);
                                this.moneda -= DivisionSS.getCosMoneda();
                            } else {
                                throw e;
                            }

                        } else if (div == Divisiones.Normal) {
                            if (DivisionInfanteria.getCosMoneda() <= this.moneda) {
                                f.entrenarSoldados(Divisiones.Infanteria);
                                this.moneda -= DivisionInfanteria.getCosMoneda();

                            } else {
                                throw e;
                            }

                        }

                    } else if (this.nombre == Razas.EstadosUnidos) {
                        if (div == Divisiones.Elite) {
                            if (MarinesUS.getCosMoneda() <= this.moneda) {
                                f.entrenarSoldados(Divisiones.USMarines);
                                this.moneda -= MarinesUS.getCosMoneda();
                            } else {
                                throw e;
                            }

                        } else if (div == Divisiones.Normal) {
                            if (SoldadosUS.getCosMoneda() <= this.moneda) {
                                f.entrenarSoldados(Divisiones.SoldadosUS);
                                this.moneda -= SoldadosUS.getCosMoneda();
                            } else {
                                throw e;
                            }

                        }

                    } else if (this.nombre == Razas.UnionSovietica) {
                        if (div == Divisiones.Elite) {
                            if (ComandantesRojo.getCosMoneda() <= this.moneda) {
                                f.entrenarSoldados(Divisiones.ComandantesR);
                                this.moneda -= ComandantesRojo.getCosMoneda();
                            } else {
                                throw e;
                            }

                        } else if (div == Divisiones.Normal) {
                            if (SoldadoRojo.getCosMoneda() <= this.moneda) {
                                f.entrenarSoldados(Divisiones.SoldadosR);
                                this.moneda -= SoldadoRojo.getCosMoneda();
                            } else {
                                throw e;
                            }
                        }
                    }
                }else{
                    System.err.println("Academias llenas");
                }
            }
        }
    }
    
    

    public boolean crearVehiculos() {
        boolean eleccion = true;
        int opc;
        Edificaciones tip = null;
        while (eleccion) {
            mostrarRecursos();
            try {
                System.out.println("\n1. Tanques\n2. Aviones\n3. Salir");
                Scanner scanner = new Scanner(System.in);
                opc = scanner.nextInt();
                switch (opc) {
                    case 1:
                        tip = Edificaciones.Tanques;
                        break;
                    case 2:
                        tip = Edificaciones.Aviones;
                        break;
                    case 3:

                        return true;
                }
                getVehiculo(tip);
                System.out.println("Se ha creado un vehiculo");

            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        return true;
    }
    
    public boolean getVehiculo(Edificaciones tip) throws Exception {
        Fabrica edif;
        Exception e = new Exception("No hay recursos suficientes");

        for (AbstractFactory f : this.fabricas) {
            edif = (Fabrica) f;
            if (edif.getHangerSize() < 10) {
                if (tip == edif.getTipoEdificacion()) {
                    if (tip == Edificaciones.Tanques) {
                        if (this.nombre == Razas.Alemania) {
                            if (TigerI.getCosMetal() <= this.metal && TigerI.getCosMoneda() <= this.moneda) {
                                f.crearTanque(Tanques.TigerI);
                                this.moneda -= TigerI.getCosMoneda();
                                this.metal -= TigerI.getCosMetal();
                                return true;
                            } else {
                                throw e;
                            }
                        } else if (this.nombre == Razas.UnionSovietica) {
                            if (T34.getCosMetal() <= this.metal && T34.getCosMoneda() <= this.moneda) {
                                f.crearTanque(Tanques.T34);
                                this.moneda -= T34.getCosMoneda();
                                this.metal -= T34.getCosMetal();
                                return true;
                            } else {
                                throw e;
                            }
                        } else if (this.nombre == Razas.EstadosUnidos) {
                            if (Sherman.getCosMetal() <= this.metal && Sherman.getCosMoneda() <= this.moneda) {
                                f.crearTanque(Tanques.Sherman);
                                this.moneda -= Sherman.getCosMoneda();
                                this.metal -= Sherman.getCosMetal();
                                return true;
                            } else {
                                throw e;
                            }
                        }
                    }
                    if (tip == Edificaciones.Aviones) {
                        if (this.nombre == Razas.Alemania) {
                            if (Stuka.getCosMetal() <= this.metal && Stuka.getCosMoneda() <= this.moneda) {
                                f.crearAvion(Aviones.Stuka);
                                this.moneda -= Stuka.getCosMoneda();
                                this.metal -= Stuka.getCosMetal();
                                return true;
                            } else {
                                throw e;
                            }
                        } else if (this.nombre == Razas.UnionSovietica) {
                            if (Tu95.getCosMetal() <= this.metal && Tu95.getCosMoneda() <= this.moneda) {
                                f.crearAvion(Aviones.Tu95);
                                this.moneda -= Tu95.getCosMoneda();
                                this.metal -= Tu95.getCosMetal();
                                return true;
                            } else {
                                throw e;
                            }
                        } else if (this.nombre == Razas.EstadosUnidos) {
                            if (AvionUS.getCosMetal() <= this.metal && AvionUS.getCosMoneda() <= this.moneda) {
                                f.crearAvion(Aviones.AvionUS);
                                this.metal -= AvionUS.getCosMetal();
                                this.moneda -= AvionUS.getCosMoneda();
                                return true;
                            } else {
                                throw e;
                            }
                        }
                    }
                }
            }
        }
        throw new Exception("No hay fabricas disponibles");
    }

    public boolean verificarDisponibilidad(Unidad unidad) {
        int verificar;
        verificar = unidad.getEspera() + unidad.getFaseCreacion();
        return (Menu.getFase() > verificar && unidad.getObjetivo() == null);
    }

    public boolean iniciarAtaque(CentroDeMando centro2) throws Exception {
        Edificaciones tip = null;
        Scanner scanner = new Scanner(System.in);
        boolean eleccion = true;
        int opc;
        while (eleccion) {
            try {
                System.out.println("\nIngrese con que desea atacar: ");
                System.out.println("\n1. Tanque\n2. Avion\n3. Milicia\n4. Salir");
                System.out.print("Opcion: ");
                opc = scanner.nextInt();

                switch (opc) {
                    case 1:
                        tip = Edificaciones.Tanques;
                        break;
                    case 2:
                        tip = Edificaciones.Aviones;
                        break;
                    case 3:
                        tip = Edificaciones.Academia;
                        break;
                    case 4:
                        return true;
                    default:
                        break;
                }

                for (AbstractFactory f : this.fabricas) {
                    Fabrica edif = (Fabrica) f;
                    if (edif.getTipoEdificacion() == tip) {
                        if (null != tip) switch (tip) {
                            case Academia:
                                for (Division d : edif.getCuartel()) {
                                    if (verificarDisponibilidad((Unidad) d)) {
                                        
                                        d.setObjetivo(elegirObjetivo(centro2));
                                        
                                        if (d.getObjetivo() == null) {
                                            System.out.println("No se ha puesto ningun objetivo");
                                        }
                                        return true;
                                    }
                                }
                                throw new Exception("No hay vehiculos disponibles");
                            case Tanques:
                                for (Tanque d : edif.getHangarTanques()) {
                                    if (verificarDisponibilidad((Unidad) d)) {
                                        
                                        d.setObjetivo(elegirObjetivo(centro2));
                                        if (d.getObjetivo() == null) {
                                            System.out.println("No se ha puesto ningun objetivo");
                                        }
                                        return true;
                                    }
                                }
                                throw new Exception("No hay vehiculos disponibles");
                            case Aviones:
                                for (Avion d : edif.getHangar()) {
                                    if (verificarDisponibilidad((Unidad) d)) {
                                        
                                        d.setObjetivo(elegirObjetivo(centro2));
                                        if (d.getObjetivo() == null) {
                                            System.out.println("No se ha puesto ningun objetivo");
                                        }
                                        return true;
                                    }
                                }
                                throw new Exception("No hay vehiculos disponibles");
                            default:
                                break;
                        }
                    }
                }
                System.err.println("No hay fabricas disponibles ");
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        return true;
    }

    public Unidad elegirObjetivo(CentroDeMando centro) throws Exception {
        Unidad uni = null;
        int opc, cont = 0;
        Scanner scanner = new Scanner(System.in);
        if (!centro.fabricas.isEmpty()) {
            for (AbstractFactory f : centro.fabricas) {
                cont++;
                System.out.println("\nEdificaciones enemigas disponibles para atacar: ");
                System.out.println(cont + "." + f.getTipoEdificacion().toString());

            }
            opc = scanner.nextInt();
            return (Unidad) centro.fabricas.get(opc-1);
        } else {
            System.out.println("\nPuede atacar el centro de mando, desea hacerlo?\n1. Si\n2. No");
            opc = scanner.nextInt();
            if (opc == 1) {
                return (Unidad) centro;
            } else if (opc == 2) {
                return null;
            }

        }
        return null;
    }

    public int getMetal() {
        return metal;
    }

    public void setMetal(int metal) {
        this.metal = metal;
    }

    public int getDolares() {
        return moneda;
    }

    public void setDolares(int dolares) {
        this.moneda = dolares;
    }

    public int getHormigon() {
        return hormigon;
    }

    public void setHormigon(int hormigon) {
        this.hormigon = hormigon;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getMaxMetal() {
        return maxMetal;
    }

    public void setMaxMetal(int maxMetal) {
        this.maxMetal = maxMetal;
    }

    public int getMaxDolares() {
        return maxMoneda;
    }

    public void setMaxDolares(int maxDolares) {
        this.maxMoneda = maxDolares;
    }

    public int getMaxHormigon() {
        return maxHormigon;
    }

    public void setMaxHormigon(int maxHormigon) {
        this.maxHormigon = maxHormigon;
    }

    public ArrayList<AbstractFactory> getEdificaciones() {
        return fabricas;
    }

    public void setEdificaciones(ArrayList<AbstractFactory> edificaciones) {
        this.fabricas = edificaciones;
    }

    public String getComandante() {
        return comandante;
    }

    public void setComandante(String comandante) {
        this.comandante = comandante;
    }

    @Override
    public int getFaseCreacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getEspera() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Unidad getObjetivo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}