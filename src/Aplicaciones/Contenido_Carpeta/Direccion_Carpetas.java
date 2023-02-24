/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.Contenido_Carpeta;

//import Aplicaciones.actualizador.de.series.*;
import Utiles.ClasesUtiles.Interfases.IsSeleccionado;
import java.io.File;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class Direccion_Carpetas implements Serializable,IsSeleccionado{

    private File f;
    private boolean seleccionado;

    public Direccion_Carpetas(File f, boolean seleccionado) {
        this.f = f;
        this.seleccionado = seleccionado;
    }

   
    


    public File getF() {
        return f;
    }

    public void setF(File f) {
        this.f = f;
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
        return f.toString();
    }

}
