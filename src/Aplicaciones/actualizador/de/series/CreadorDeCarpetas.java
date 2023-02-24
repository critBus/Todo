/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.actualizador.de.series;

import Utiles.ClasesUtiles.Tablas.Tabla;
import java.io.File;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class CreadorDeCarpetas implements Serializable {

    private File direccion;
    private LinkedList<NombreCarpeta> nombresCarpetas;

    public CreadorDeCarpetas(File direccion, LinkedList<NombreCarpeta> nombresCarpetas) {
        this.direccion = direccion;
        this.nombresCarpetas = nombresCarpetas;
    }

    public File getDireccion() {
        return direccion;
    }

    public void setDireccion(File direccion) {
        this.direccion = direccion;
    }

    public LinkedList<NombreCarpeta> getNombresCarpetas() {
        return nombresCarpetas;
    }

    public void setNombresCarpetas(LinkedList<NombreCarpeta> nombresCarpetas) {
        this.nombresCarpetas = nombresCarpetas;
    }

    public Tabla getTabla() {
        String a[] = new String[nombresCarpetas.size()];
        boolean b[] = new boolean[nombresCarpetas.size()];
        for (int i = 0; i < a.length; i++) {
            a[i] = nombresCarpetas.get(i).getNombreCarpeta();
            b[i] = nombresCarpetas.get(i).isSeleccionado();
        }
        return Tabla.crearStringSeleccionable(a, b,true);
    }

    public static LinkedList<NombreCarpeta> getDefaultNombresCarpetas() {
        String a[] = {"Android", "Instaladores", "Manga", "Series", "Peliculas", "Humor Cubano", "Curiosidades","Documentales","Juegos","Revistas"};
        LinkedList<NombreCarpeta> n = new LinkedList<NombreCarpeta>();
        for (int i = 0; i < a.length; i++) {
            n.add(new NombreCarpeta(a[i], true));
        }
        return n;
    }

    public static CreadorDeCarpetas getDefault() {
        return new CreadorDeCarpetas(new File("G:\\Paquete"), getDefaultNombresCarpetas());
    }
}
