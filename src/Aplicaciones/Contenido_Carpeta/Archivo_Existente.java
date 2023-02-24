/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.Contenido_Carpeta;

import Utiles.MetodosUtiles.Archivo;
import Utiles.MetodosUtiles.MetodosUtiles;
import Utiles.MetodosUtiles.Videos;
import java.io.File;
import java.io.Serializable;

/**
 *
 * @author Rene
 */
public class Archivo_Existente implements Comparable<Archivo_Existente>, Serializable {

//    private String nombre;
    private File direccion;
    private double tamaño;
    private int cantidaDeVideos;
    private formaDeComparar forma;
    private boolean ascendente;

    public Archivo_Existente(File f) {
        double cant[] = Videos.tamañoArchivoYCantidadDeVideos(f);
        inizailizar(f, cant[0], (int) cant[1], formaDeComparar.TAMAÑO_POR_CAPITULO, false);
    }

    public Archivo_Existente(File direccion, double tamaño, int cantidaDeVideos, formaDeComparar forma, boolean ascendente) {
        this.direccion = direccion;
        this.tamaño = tamaño;
        this.cantidaDeVideos = cantidaDeVideos;
        this.forma = forma;
        this.ascendente = ascendente;
    }

    private void inizailizar(File direccion, double tamaño, int cantidaDeVideos, formaDeComparar forma, boolean ascendente) {
        this.direccion = direccion;
        this.tamaño = tamaño;
        this.cantidaDeVideos = cantidaDeVideos;
        this.forma = forma;
        this.ascendente = ascendente;
    }

    public boolean isAscendente() {
        return ascendente;
    }

    public void setAscendente(boolean ascendente) {
        this.ascendente = ascendente;
    }

    public File getDireccion() {
        return direccion;
    }

    public void setDireccion(File direccion) {
        this.direccion = direccion;
    }

//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
    public double getTamaño() {
        return tamaño;
    }

    public String getTamañoString() {
        return Archivo.tamaño(tamaño);
    }

    public String getTamañoPorCapituloString() {
        return Archivo.tamaño(tamaño / cantidaDeVideos);
    }

    public int getCantidaDeVideos() {
        return cantidaDeVideos;
    }

    public void setCantidaDeVideos(int cantidaDeVideos) {
        this.cantidaDeVideos = cantidaDeVideos;
    }

    public void setTamaño(double tamaño) {
        this.tamaño = tamaño;
    }

    public formaDeComparar getForma() {
        return forma;
    }

    public void setForma(formaDeComparar forma) {
        this.forma = forma;
    }

    @Override
    public int compareTo(Archivo_Existente o) {
        switch (forma) {
            case NOMBRE:
                return resul(MetodosUtiles.compararStringAConStringB(direccion.getName(), o.direccion.getName(), true));
            case TAMAÑO:
                return resul(new Double(tamaño).compareTo(o.tamaño));
            case TAMAÑO_POR_CAPITULO:
                return resul(new Double(tamaño / cantidaDeVideos).compareTo(o.tamaño / o.cantidaDeVideos));
            case CANTIDAD_DE_CAPITULOS:
                return resul(new Double(cantidaDeVideos).compareTo((double)o.cantidaDeVideos));
//            case TAMAÑO_MAYORES:
//                int r = new Double(tamaño).compareTo(o.tamaño);
//                return r == 0 ? r : (r < 0 ? 1 : -1);
//            case TAMAÑO_MENORES:
//                return new Double(tamaño).compareTo(o.tamaño);
//            case TAMAÑO_MENORES_POR_CAPITULO:
//                return new Double(tamaño / cantidaDeVideos).compareTo(o.tamaño / o.cantidaDeVideos);
//            case TAMAÑO_MAYORES_POR_CAPITULO:
//                int r2 = new Double(tamaño / cantidaDeVideos).compareTo(o.tamaño / o.cantidaDeVideos);
//                return r2 == 0 ? r2 : (r2 < 0 ? 1 : -1);

        }
        return -1;
    }

    private int resul(int i) {
        return i == 0 || ascendente ? i : i * -1;
    }
}
