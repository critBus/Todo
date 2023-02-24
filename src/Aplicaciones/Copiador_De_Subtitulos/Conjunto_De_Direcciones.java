/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.Copiador_De_Subtitulos;

import Utiles.ClasesUtiles.Tablas.Tabla;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class Conjunto_De_Direcciones implements Serializable{

  private  LinkedList<Direccion> direcciones;

    public Conjunto_De_Direcciones() {
        this(new LinkedList<Direccion>());
    }

    public Conjunto_De_Direcciones(LinkedList<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public LinkedList<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(LinkedList<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public Tabla getTabla() {
        String[] direcciones2 = new String[direcciones.size()];
        boolean[] selec = new boolean[direcciones.size()];
        for (int i = 0; i < direcciones.size(); i++) {
            direcciones2[i] = direcciones.get(i).getF().toString();
            selec[i] = direcciones.get(i).isSeleccionado();
        }
        return Tabla.crearStringSeleccionable(direcciones2, selec);
    }
    public Tabla getTablaInvalidos(int indice) {
        if (!direcciones.isEmpty() && indice >= 0 && indice < direcciones.size()) {
            String[] directorios = new String[direcciones.get(indice).getDirectoriosInvalidos().size()];
            boolean[] selec = new boolean[direcciones.get(indice).getDirectoriosInvalidos().size()];
            for (int i = 0; i < direcciones.get(indice).getDirectoriosInvalidos().size(); i++) {
                directorios[i] = direcciones.get(indice).getDirectoriosInvalidos().get(i).getDirectorioInvalido();
                selec[i] = direcciones.get(indice).getDirectoriosInvalidos().get(i).isSeleccionado();
            }
            // System.out.println("ssssssssssssssssssssss");
            return Tabla.crearStringSeleccionable(directorios, selec);
        }
//        return Tabla.crearStringSeleccionable(new String[]{""}, new boolean[]{false});
        return Tabla.crearStringSeleccionable(new String[]{}, new boolean[]{});
    }
}
