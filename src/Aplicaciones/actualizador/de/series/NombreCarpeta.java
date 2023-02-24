/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.actualizador.de.series;

import Utiles.ClasesUtiles.Interfases.IsSeleccionado;
import java.io.Serializable;

/**
 *
 * @author Rene
 */
public class NombreCarpeta implements Serializable,IsSeleccionado{
    private String nombreCarpeta;
    private boolean seleccionado;

    public NombreCarpeta(String nombreCarpeta) {
        this(nombreCarpeta,true);
    }

    public NombreCarpeta(String nombreCarpeta, boolean seleccionado) {
        this.nombreCarpeta = nombreCarpeta;
        this.seleccionado = seleccionado;
    }

    public String getNombreCarpeta() {
        return nombreCarpeta;
    }

    public void setNombreCarpeta(String nombreCarpeta) {
        this.nombreCarpeta = nombreCarpeta;
    }

    @Override
    public boolean isSeleccionado() {
        return seleccionado;
    }

    @Override
    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    @Override
    public String toString() {
        return nombreCarpeta; //To change body of generated methods, choose Tools | Templates.
    }
     
}
