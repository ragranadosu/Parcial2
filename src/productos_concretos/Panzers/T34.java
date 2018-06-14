/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos_concretos.Panzers;

import enumeraciones.Edificaciones;
import productos_abstractos.Tanque;
import productos_abstractos.Unidad;

/**
 *
 * @author rau3
 */
public class T34 implements Tanque,Unidad{
    
    private final int espera,velocidad;
    private int danio, vida,faseCreacion,faseDeEnvio;
    private final static int cosMetal=300,cosMoneda=700;
    private Unidad objetivo;

    public T34() {
        this.espera = 1;
        this.velocidad = 2;
        this.danio = 300;
        this.vida = 600;
    }

    public int getEspera() {
        return espera;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getDanio() {
        return danio;
    }
    

    public int getFaseCreacion() {
        return faseCreacion;
    }

    public void setFaseCreacion(int faseCreacion) {
        this.faseCreacion = faseCreacion;
    }
    

    public Unidad getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Unidad objetivo) {
        this.objetivo = objetivo;
    }
    

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
    

    public static int getCosMetal() {
        return cosMetal;
    }

    public static int getCosMoneda() {
        return cosMoneda;
    }

    public int getFaseDeEnvio() {
        return faseDeEnvio;
    }

    public void setFaseDeEnvio(int faseDeEnvio) {
        this.faseDeEnvio = faseDeEnvio;
    }
    
    @Override
    public void atacar() {
        int nuevaVida;
        nuevaVida = this.objetivo.getVida()-this.danio;
        this.objetivo.setVida(nuevaVida);
        System.err.println("\nSe han bajado "+this.danio+"de vida al enemigo\n");
        
        if(this.objetivo.getVida()<=0){
            System.out.println("Se ha destruido la edificacion de tipo: "+this.objetivo.getTipoEdificacion());
            this.objetivo=null;
        }
    }

    @Override
    public void destruir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Edificaciones getTipoEdificacion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
