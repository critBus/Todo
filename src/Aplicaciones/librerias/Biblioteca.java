/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.librerias;;

import Utiles.MetodosUtiles.Archivo;
import Utiles.MetodosUtiles.MetodosUtiles;
import java.io.File;
import java.io.Serializable;

/**
 *
 * @author Rene
 */
public class Biblioteca implements Comparable<Biblioteca>,Serializable {

    public static final Nombre_Clase nombresDeClases[] = Nombre_Clase.values();
    private double versiones[], versionTotal;
    public static final String nombreVersion = "Version";
    private String nombreProyecto, nombreCarpeta;
    private Nombre_Clase clasesQueContiene[];
    private File direccion;

//    public Biblioteca(double[] versiones, String nombreProyecto) {
//        this.versiones = versiones;
//        this.nombreProyecto = nombreProyecto;
//        versionTotal = 0;
//        for (double v : versiones) {
//            versionTotal = Double.parseDouble(sutituirComa(String.format("%.1f", (versionTotal += v))));
//        }
//    }
    public Biblioteca(double[] versiones, File direccion) {
        this(versiones, direccion, Nombre_Clase.values());
    }

    public Biblioteca(double[] versiones, File direccion, Nombre_Clase[] clasesQueContiene) {
        this.versiones = versiones;
        this.nombreProyecto = direccion.getName();
        this.clasesQueContiene = clasesQueContiene;
        this.direccion = direccion;//direccion.getParentFile().getName()
        nombreCarpeta = Archivo.getParentName(direccion);
        versionTotal = 0;
        for (double v : versiones) {
            versionTotal = Double.parseDouble(sutituirComa(String.format("%.1f", (versionTotal += v))));
        }
    }

    private String sutituirComa(String a) {
        return a.substring(0, a.indexOf(",")) + "." + a.substring(a.indexOf(",") + 1);
    }

    public double[] getVersiones() {
        return versiones;
    }

    public double getVersion(Nombre_Clase n) {
        return versiones[n.getIndiceActual()];
    }

    public double getVersion(int indice) {
        return versiones[indice];
    }

    public static String getNombreVersion() {
        return nombreVersion;
    }

    public int sise() {
        return clasesQueContiene.length;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setClasesQueContiene(Nombre_Clase[] clasesQueContiene) {
        this.clasesQueContiene = clasesQueContiene;
    }

    public double getVersionTotal() {
        return versionTotal;
    }

    public Nombre_Clase getNombreClase(int indice) {
        return clasesQueContiene[indice];
    }

    @Override
    public int compareTo(Biblioteca o) {
        int com = new Double(versionTotal).compareTo(o.getVersionTotal());
        if (com == 0) {
            if ((com = direccion.compareTo(o.getDireccion())) == 0) {
                if ((com = new Integer(sise()).compareTo(o.sise())) == 0) {
                    for (int i = 0; i < sise(); i++) {
                        if ((com = new Double(getVersion(i)).compareTo(o.getVersion(i))) != 0) {
                            break;
                        }
                    }
                }
            }
        }
        return com;
    }

    public String getNombreCarpeta() {
        return nombreCarpeta;
    }

    public File getDireccion() {
        return direccion;
    }

}
