/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.actualizador.de.series.Secciones;

import Utiles.ClasesUtiles.Multimedia.Series.CatalogoDeSeries;
import Utiles.ClasesUtiles.Tablas.Tabla;
import Aplicaciones.actualizador.de.series.*;
import Aplicaciones.actualizador.de.series.*;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class SeccionPersonalizada extends SeccionSerie implements Serializable {

    // private LinkedList<Direccion> direcciones;
    // private boolean escritura;
    private estadoPersonalizado estado;
    private String lineas[], nombre;
    private CatalogoDeSeries catalogo;
    private boolean seleccionado;

    public SeccionPersonalizada(String nombre) {
        this(estadoPersonalizado.DIRECCIONES, new String[]{}, nombre, new CatalogoDeSeries(), true, new LinkedList<Direccion>(), FormaString.CAPITULOS, new FiltroSerie());
        //this(new LinkedList<Direccion>(),true,true,new String[]{},nombre);
    }

    public SeccionPersonalizada(estadoPersonalizado estado, String[] lineas, String nombre, CatalogoDeSeries catalogo, boolean seleccionado, LinkedList<Direccion> direcciones, FormaString formaString, FiltroSerie filtro) {
        super(direcciones, formaString, filtro);
        this.estado = estado;
        this.lineas = lineas;
        this.nombre = nombre;
        this.catalogo = catalogo;
        this.seleccionado = seleccionado;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public CatalogoDeSeries getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(CatalogoDeSeries catalogo) {
        this.catalogo = catalogo;
    }

    public estadoPersonalizado getEstado() {
        return estado;
    }

//    public SeccionPersonalizada(LinkedList<Direccion> direcciones, boolean escritura, boolean verCapitulos, String[] lineas, String nombre) {
//        this.direcciones = direcciones;
//        this.escritura = escritura;
//        this.verCapitulos = verCapitulos;
//        this.lineas = lineas;
//        this.nombre = nombre;
//    }
//    public LinkedList<Direccion> getDirecciones() {
//        return direcciones;
//    }
//
//    public void setDirecciones(LinkedList<Direccion> direcciones) {
//        this.direcciones = direcciones;
//    }
    public void setEstado(estadoPersonalizado estado) {
        this.estado = estado;
    }

//    public boolean isVerCapitulos() {
//        return verCapitulos;
//    }
//
//    public void setVerCapitulos(boolean verCapitulos) {
//        this.verCapitulos = verCapitulos;
//    }
    public String[] getLineas() {
        return lineas;
    }

    public void setLineas(String[] lineas) {
        this.lineas = lineas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        String a = nombre + "\n";
        int principal = estado.ordinal();
        a +="Estado: "+ estado.name() + "\n{" + "\n";
        switch (estado) {
            case CATALOGO:
                a += mostrarCatalogo();
                break;
            case DIRECCIONES:
                a += mostrarDirecciones();
                break;
            case ESCRITURA:
                a += mostrarEscrito();
                break;
        }
        for (int i = 0; i < 3; i++) {
            if (i != principal) {

                switch (i) {
                    case 0:
                        a += mostrarEscrito();
                        break;
                    case 1:
                        a += mostrarDirecciones();
                        break;
                    case 2:
                        a += mostrarCatalogo();
                        break;

                }
            }
        }
        return a + "}\n"; //To change body of generated methods, choose Tools | Templates.
    }

    private String mostrarEscrito() {
        String a = "";
        if (lineas.length == 0) {
            a += "[ sin escritura ]" + "\n";
            return a;
        }
        a += "Lineas: "+ "\n";
        for (int i = 0; i < lineas.length; i++) {
            a += lineas[i] + "\n";
        }

        return a;
    }

    private String mostrarDirecciones() {
        String a = "";
        if (getDirecciones().isEmpty()) {
            a += "[ sin direcciones ]" + "\n";
            return a;
        }
        a += "Direcciones: "+ "\n";
        for (int i = 0; i < getDirecciones().size(); i++) {
            a += getDirecciones().get(i).toString() + "\n";
        }

        return a;
    }

    private String mostrarCatalogo() {

        String a = "";
        if (catalogo.isEmpty()) {
            a += "[ Catalogo Vacio ]" + "\n";
            return a;
        }
        a += "Catalogo: "+ "\n";
        String c[] = catalogo.getNombresDeSeries();
        for (int i = 0; i < c.length; i++) {
            a += c[i] + "\n";
        }

        return a;
    }
}
