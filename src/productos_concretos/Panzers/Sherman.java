/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos_concretos.Panzers;

import productos_abstractos.Tanque;
import productos_abstractos.Unidad;

/**
 *
 * @author rau3
 */
public class Sherman implements Tanque ,Unidad{
    
    private final int espera,velocidad;
    private int danio, vida,faseCreacion;
    private final static int cosMetal=700,cosMoneda=3000;
    private Unidad objetivo;

    public Sherman() {
        this.espera=2;
        this.velocidad=2;
        this.danio = 500;
        this.vida = 800;
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
    

    public static int getCosMetal() {
        return cosMetal;
    }

    public static int getCosMoneda() {
        return cosMoneda;
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
    
    
    
    
    public void atacar() {
        int nuevaVida;
        nuevaVida = this.objetivo.getVida()-this.danio;
        this.objetivo.setVida(nuevaVida);
        
        if(this.objetivo.getVida()<=0){
            this.objetivo=null;
        }
    }

    @Override
    public void destruir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
