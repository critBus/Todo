/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.Contenido_Carpeta;

import Utiles.ClasesUtiles.Tablas.Tabla;
import java.io.File;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class Direcciones_De_Carpetas implements Serializable{

    private LinkedList<Direccion_Carpetas> direcciones_Carpetas;

    public Direcciones_De_Carpetas() {
        this(new LinkedList<Direccion_Carpetas>());
    }

    public Direcciones_De_Carpetas(LinkedList<Direccion_Carpetas> direcciones_Carpetas) {
        this.direcciones_Carpetas = direcciones_Carpetas;
    }

    public Tabla getTabla_Direcciones_Carpetas() {
        String[] direcciones2 = new String[direcciones_Carpetas.size()];
        boolean[] selec = new boolean[direcciones_Carpetas.size()];
        for (int i = 0; i < direcciones_Carpetas.size(); i++) {
            direcciones2[i] = direcciones_Carpetas.get(i).getF().toString();
            selec[i] = direcciones_Carpetas.get(i).isSeleccionado();
        }
        return Tabla.crearStringSeleccionable(direcciones2, selec);
    }

    public File[] getDireccionesSeleccionadas() {
        LinkedList<File> d = new LinkedList();
        for (int i = 0; i < direcciones_Carpetas.size(); i++) {
            if (direcciones_Carpetas.get(i).isSeleccionado()) {
                d.add(direcciones_Carpetas.get(i).getF());
            }
        }
        return d.toArray(new File[]{});
    }

    public LinkedList<Direccion_Carpetas> getDirecciones_Carpetas() {
        return direcciones_Carpetas;
    }

    public void setDirecciones_Carpetas(LinkedList<Direccion_Carpetas> direcciones_Carpetas) {
        this.direcciones_Carpetas = direcciones_Carpetas;
    }
    
}
