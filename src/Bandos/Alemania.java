/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bandos;

import InterfazBandos.Pais;
import enumeraciones.Edificaciones;
import fabricas_abstractas.AbstractFactory;
import fabricas_concretas.CreadorEdificaciones;

/**
 *
 * @author rau3
 */
public class Alemania implements Pais{
    int metal, marcoImperial=10000,hormigon;
    AbstractFactory edificaciones;
    
    @Override
    public AbstractFactory crearEdificacion(Edificaciones tipo) throws Exception {

        return CreadorEdificaciones.getFactory(tipo);

    }
    
    
}
