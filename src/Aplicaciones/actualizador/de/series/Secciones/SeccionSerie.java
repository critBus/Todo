/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.actualizador.de.series.Secciones;

import Utiles.ClasesUtiles.Interfases.addAll;
import Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaConSeries;
import Utiles.ClasesUtiles.Tablas.Tabla;
import Aplicaciones.actualizador.de.series.*;

import java.io.File;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class SeccionSerie implements Serializable, addAll<CarpetaConSeries>{
public enum FormaString{CAPITULOS,SOLO_CAPITULOS,NOMBRES_DE_SERIES}
    private LinkedList<Direccion> direcciones;
//    private boolean verCapitulos;
    private FormaString formaString;
    private FiltroSerie filtro;

    
    
    public SeccionSerie(FormaString formaString) {
        this(new LinkedList<Direccion>(),formaString, new FiltroSerie());
    }

    public SeccionSerie(LinkedList<Direccion> direcciones, FormaString formaString, FiltroSerie filtro) {
        this.direcciones = direcciones;
        this.formaString = formaString;
        this.filtro = filtro;
    }

   

    public FiltroSerie getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroSerie filtro) {
        this.filtro = filtro;
    }

    public LinkedList<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(LinkedList<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public FormaString getFormaString() {
        return formaString;
    }

    public void setFormaString(FormaString formaString) {
        this.formaString = formaString;
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

    @Override
    public void addAll(CarpetaConSeries c) {
        direcciones.addAll(getListaDireccion(c.getDirecciones(), c.getInvalidos()));
    }

    public static LinkedList<DirectoriosInvalidos> getListaInvalidos(LinkedList<String> invalidos) {
        LinkedList<DirectoriosInvalidos> d = new LinkedList<DirectoriosInvalidos>();
        for (int i = 0; i < invalidos.size(); i++) {
            d.add(new DirectoriosInvalidos(invalidos.get(i), true));
        }
        return d;
    }

    public static LinkedList<DirectoriosInvalidos> removerInexistentes(File f, LinkedList<DirectoriosInvalidos> d) {
        for (int i = 0; i < d.size(); i++) {
            if (!new File(f + "/" + d.get(i).getDirectorioInvalido()).exists()) {
                d.remove(i--);
            }
        }
        return d;
    }

    public static LinkedList<Direccion> getListaDireccion(LinkedList<File> direcciones, LinkedList<LinkedList<String>> invalidos) {
        LinkedList<Direccion> d = new LinkedList<Direccion>();
        for (int i = 0; i < direcciones.size(); i++) {
            d.add(new Direccion(direcciones.get(i), true, removerInexistentes(direcciones.get(i),getListaInvalidos(invalidos.get(i)))));
        }
        return d;
    }

}
