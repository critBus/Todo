/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.actualizador.de.series.Secciones;

//import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
//import Utiles.ClasesUtiles.Multimedia.Series.CatalogoDeSeries;
//import Utiles.ClasesUtiles.Multimedia.Series.Serie;
//import Utiles.ClasesUtiles.Tablas.Tabla;
//import actualizador.de.series.FiltroSerie;
//import java.io.File;
//import java.io.FileNotFoundException;
import Utiles.ClasesUtiles.Tablas.Tabla;
import Aplicaciones.actualizador.de.series.*;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class InformacionDeSecciones implements Serializable {
//d 1 4 3

    SeccionesBase seccionesBase;
    SeccionesEntrada seccionesEntrada;
//    private SeccionSerie Seguidos, QueTengo, EnEspera, PorVer, Finalizadas, EntradaClasicas, EntradaTX, EntradaDobladas, EntradaTodo, EntradaFinalizadas;
//    private LinkedList<SeccionPersonalizada> personalizadasBase, personalizadasEntrada;

    public InformacionDeSecciones() {
        this(new SeccionesBase(), new SeccionesEntrada());
//        this(new SeccionSerie(false), new SeccionSerie(true), new SeccionSerie(false), new SeccionSerie(false)aa, new SeccionSerie(false), new SeccionSerie(true), new SeccionSerie(true), new SeccionSerie(true), new SeccionSerie(true), new SeccionSerie(false), new LinkedList<SeccionPersonalizada>(), new LinkedList<SeccionPersonalizada>()
//        );
    }

    public InformacionDeSecciones(SeccionesBase seccionesBase, SeccionesEntrada seccionesEntrada) {
        this.seccionesBase = seccionesBase;
        this.seccionesEntrada = seccionesEntrada;
    }

    public SeccionesBase getSeccionesBase() {
        return seccionesBase;
    }

    public void setSeccionesBase(SeccionesBase seccionesBase) {
        this.seccionesBase = seccionesBase;
    }

    public SeccionesEntrada getSeccionesEntrada() {
        return seccionesEntrada;
    }

    public void setSeccionesEntrada(SeccionesEntrada seccionesEntrada) {
        this.seccionesEntrada = seccionesEntrada;
    }

}

//    public InformacionDeSecciones(SeccionSerie Seguidos, SeccionSerie QueTengo, SeccionSerie EnEspera, SeccionSerie PorVer, SeccionSerie Finalizadas, SeccionSerie EntradaClasicas, SeccionSerie EntradaTX, SeccionSerie EntradaDobladas, SeccionSerie EntradaTodo, SeccionSerie EntradaFinalizadas, LinkedList<SeccionPersonalizada> personalizadasBase, LinkedList<SeccionPersonalizada> personalizadasEntrada) {
//        this.Seguidos = Seguidos;
//        this.QueTengo = QueTengo;
//        this.EnEspera = EnEspera;
//        this.PorVer = PorVer;
//        this.Finalizadas = Finalizadas;
//        this.EntradaClasicas = EntradaClasicas;
//        this.EntradaTX = EntradaTX;
//        this.EntradaDobladas = EntradaDobladas;
//        this.EntradaTodo = EntradaTodo;
//        this.EntradaFinalizadas = EntradaFinalizadas;
//        this.personalizadasBase = personalizadasBase;
//        this.personalizadasEntrada = personalizadasEntrada;
//    }
//    public void clearEntradas() {
//        EntradaClasicas.getDirecciones().clear();
//        EntradaDobladas.getDirecciones().clear();
//        EntradaFinalizadas.getDirecciones().clear();
//        EntradaTX.getDirecciones().clear();
//        EntradaTodo.getDirecciones().clear();
//        personalizadasEntrada.clear();
//     
//    }
//    public void clearBase() {
//           
//        EnEspera.getDirecciones().clear();
//        Finalizadas.getDirecciones().clear();
//        PorVer.getDirecciones().clear();
//        QueTengo.getDirecciones().clear();
//        Seguidos.getDirecciones().clear();
//        personalizadasBase.clear();
//    }
//    public void actualizarDirecionesEntradaTodo() {
//        LinkedList<Direccion> d = new LinkedList<Direccion>();
//        for (int i = 0; i < 4 + personalizadasEntrada.size(); i++) {
//            d.addAll(getEntradaCorrespondiente(i).getDirecciones());
//        }
//        EntradaTodo.setDirecciones(d);
//
//    }
//
//    private SeccionSerie getEntradaCorrespondiente(int i) {
//        switch (i) {
//            case 0:
//                return EntradaClasicas;
//
//            case 1:
//                return EntradaDobladas;
//
//            case 2:
//                return EntradaFinalizadas;
//
//            case 3:
//                return EntradaTX;
//
//        }
//
//        return personalizadasEntrada.get(i - 4);
//    }
//
//    public void seEliminoDireccionEnEntradaTodo(int indice) {
//        for (int i = 0; i < 4 + personalizadasEntrada.size(); i++) {
//
//            SeccionSerie s = getEntradaCorrespondiente(i);
//            if (indice < s.getDirecciones().size()) {
//                s.getDirecciones().remove(indice);
//                break;
//            }
//            indice -= s.getDirecciones().size();
//
//        }
//
//    }
//    public SeccionSerie getEntradaTodo() {
//        return EntradaTodo;
//    }
//
//    public void setEntradaTodo(SeccionSerie EntradaTodo) {
//        this.EntradaTodo = EntradaTodo;
//    }
//
//    public SeccionSerie getEntradaFinalizadas() {
//        return EntradaFinalizadas;
//    }
//
//    public void setEntradaFinalizadas(SeccionSerie EntradaFinalizadas) {
//        this.EntradaFinalizadas = EntradaFinalizadas;
//    }
//
//    public SeccionSerie getSeguidos() {
////        System.out.println("seguidos");
//        return Seguidos;
//    }
//
//    public void setSeguidos(SeccionSerie Seguidos) {
//        this.Seguidos = Seguidos;
//    }
//
//    public SeccionSerie getQueTengo() {
////        System.out.println("que tengo");
//        return QueTengo;
//    }
//
//    public void setQueTengo(SeccionSerie QueTengo) {
//        this.QueTengo = QueTengo;
//    }
//
//    public SeccionSerie getEnEspera() {
////        System.out.println("en espera");
//        return EnEspera;
//    }
//
//    public void setEnEspera(SeccionSerie EnEspera) {
//        this.EnEspera = EnEspera;
//    }
//
//    public SeccionSerie getPorVer() {
//        return PorVer;
//    }
//
//    public void setPorVer(SeccionSerie PorVer) {
//        this.PorVer = PorVer;
//    }
//
//    public SeccionSerie getFinalizadas() {
////        System.out.println("finalizadas");
//        return Finalizadas;
//    }
//
//    public void setFinalizadas(SeccionSerie Finalizadas) {
//        this.Finalizadas = Finalizadas;
//    }
//
//    public SeccionSerie getEntradaClasicas() {
//        return EntradaClasicas;
//    }
//
//    public void setEntradaClasicas(SeccionSerie EntradaClasicas) {
//        this.EntradaClasicas = EntradaClasicas;
//    }
//
//    public SeccionSerie getEntradaTX() {
//        return EntradaTX;
//    }
//
//    public void setEntradaTX(SeccionSerie EntradaTX) {
//        this.EntradaTX = EntradaTX;
//    }
//
//    public SeccionSerie getEntradaDobladas() {
//        return EntradaDobladas;
//    }
//
//    public void setEntradaDobladas(SeccionSerie EntradaDobladas) {
//        this.EntradaDobladas = EntradaDobladas;
//    }
//
//    public LinkedList<SeccionPersonalizada> getPersonalizadasBase() {
//        return personalizadasBase;
//    }
//
//    public void setPersonalizadasBase(LinkedList<SeccionPersonalizada> personalizadasBase) {
//        this.personalizadasBase = personalizadasBase;
//    }
//
//    public LinkedList<SeccionPersonalizada> getPersonalizadasEntrada() {
//        return personalizadasEntrada;
//    }
//
//    public void setPersonalizadasEntrada(LinkedList<SeccionPersonalizada> personalizadasEntrada) {
//        this.personalizadasEntrada = personalizadasEntrada;
//    }
