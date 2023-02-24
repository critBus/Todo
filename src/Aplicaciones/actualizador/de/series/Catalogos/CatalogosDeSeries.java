/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.actualizador.de.series.Catalogos;

import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Aplicaciones.actualizador.de.series.Catalogos.SeccionEntradaTodo.SeccionDeCatalogosDeSerieEntradaTodo;
//import Utiles.ClasesUtiles.Multimedia.Series.CatalogoDeSeries;
//import actualizador.de.series.Direccion;
//import actualizador.de.series.DirectoriosInvalidos;
//import actualizador.de.series.FiltroSerie;
import Aplicaciones.actualizador.de.series.Secciones.InformacionDeSecciones;
//import Aplicaciones.actualizador.de.series.Secciones.SeccionSerie;
//import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.Serializable;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class CatalogosDeSeries {
//bbbb

    private InformacionDeSecciones inf;
    private CatalogosDeSeriesBasicos basicos;
    private SeccionDeCatalogosDeSerieEntrada EntradaClasicas, EntradaTX, EntradaDobladas, EntradaFinalizadas;
    private LinkedList<SeccionDeCatalogosDeSerieEntrada> personalizadasEntrada;
    private SeccionDeCatalogosDeSerieEntradaTodo EntradaTodo;
//    private CatalogosDeSeriesEntrada entrada;
//    private CatalogosDeSeriesFaltanPorCopiar faltanPorCopiar;
//    private CatalogosDeSeriesFaltantes faltantes;

    public CatalogosDeSeries(InformacionDeSecciones inf, ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
        this.inf = inf;
        basicos = new CatalogosDeSeriesBasicos(inf);
        basicos.actualizar(cdv);
//        System.out.println("aaa");
//        System.out.println("inf.getEntradaClasicas()="+inf.getEntradaClasicas());
//        System.out.println("bbb");
//        System.out.println("Entradas Clasicas------------------------------");
        EntradaClasicas = new SeccionDeCatalogosDeSerieEntrada("Entradas Clasicas", inf, inf.getSeccionesEntrada().getClasicas(), basicos, cdv);
//        EntradaClasicas.getFaltanPorCopiar().getSeguidos().imprimir();
//        System.out.println("Entrada Dobladas----------------------------");
        EntradaDobladas = new SeccionDeCatalogosDeSerieEntrada("Entrada Dobladas", inf, inf.getSeccionesEntrada().getDobladas(), basicos, cdv);
//        System.out.println("Entrada TX---------------------------");
        EntradaTX = new SeccionDeCatalogosDeSerieEntrada("Entrada TX", inf, inf.getSeccionesEntrada().getTX(), basicos, cdv);
//        EntradaTX.getFaltanPorCopiar().getSeguidos().imprimir();
        personalizadasEntrada = new LinkedList<SeccionDeCatalogosDeSerieEntrada>();
        for (int i = 0; i < inf.getSeccionesEntrada().getPersonalizadas().size(); i++) {
            personalizadasEntrada.add(new SeccionDeCatalogosDeSerieEntrada(inf.getSeccionesEntrada().getPersonalizadas().get(i).getNombre(), inf, inf.getSeccionesEntrada().getPersonalizadas().get(i), basicos, cdv));
        }
        EntradaFinalizadas = new SeccionDeCatalogosDeSerieEntrada("Entrada Finalizadas", inf, inf.getSeccionesEntrada().getFinalizadas(), basicos, cdv);
//        System.out.println("Entradas todo----------------------------");
//        EntradaClasicas.getFaltanPorCopiar().getSeguidos().imprimir();
        EntradaTodo = new SeccionDeCatalogosDeSerieEntradaTodo(this, "Entradas todo", inf, inf.getSeccionesEntrada().getTodo(), basicos, cdv);
//EntradaClasicas.getFaltanPorCopiar().getSeguidos().imprimir();
//        entrada = new CatalogosDeSeriesEntrada(basicos, inf.getEntrada().getFiltro());
//        faltanPorCopiar = new CatalogosDeSeriesFaltanPorCopiar(basicos, entrada, inf.getEntrada().getFiltro());
//        faltantes = new CatalogosDeSeriesFaltantes(basicos, entrada, inf.getEntrada().getFiltro());
//        actualizarCatalogos(cdv);
    }

    public SeccionDeCatalogosDeSerieEntrada getEntradaFinalizadas() {
        return EntradaFinalizadas;
    }

    public void setEntradaFinalizadas(SeccionDeCatalogosDeSerieEntrada EntradaFinalizadas) {
        this.EntradaFinalizadas = EntradaFinalizadas;
    }

    public SeccionDeCatalogosDeSerieEntradaTodo getEntradaTodo() {
        return EntradaTodo;
    }

    public void setEntradaTodo(SeccionDeCatalogosDeSerieEntradaTodo EntradaTodo) {
        this.EntradaTodo = EntradaTodo;
    }

    public void addNewPersonalizadasEntrada(String nombre, ConfiguracionDeVideo cdv) {

        try {
            personalizadasEntrada.add(new SeccionDeCatalogosDeSerieEntrada(nombre, inf, inf.getSeccionesEntrada().getPersonalizadas().getLast(), basicos, cdv));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CatalogosDeSeries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void actualizarCatalogos(ConfiguracionDeVideo cdv) throws FileNotFoundException {
//        basicos.actualizar(cdv);
//        entrada.actualizar(cdv);
//        faltanPorCopiar.actualizar(cdv);
//        faltantes.actualizar(cdv);
//
//    }
    public void actualizarEntradas(boolean incluirExtrenos,ConfiguracionDeVideo cdv) throws FileNotFoundException {
        EntradaClasicas.actualizarCatalogos(incluirExtrenos,cdv);
//        System.out.println("d 1 1");
        EntradaDobladas.actualizarCatalogos(incluirExtrenos,cdv);
//        System.out.println("d 1 2");
        EntradaTX.actualizarCatalogos(incluirExtrenos,cdv);
        EntradaFinalizadas.actualizarCatalogos(incluirExtrenos,cdv);
//        System.out.println("d 1 3");
        EntradaTodo.actualizarCatalogos(incluirExtrenos,cdv);
//        System.out.println("d 1 4");
        actualizarPersonalizados(incluirExtrenos,cdv);
    }

    public void actualizarCatalogos(boolean incluirExtrenos,ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
        basicos.actualizar(cdv);
//        EntradaClasicas.actualizarCatalogos(cdv);
//        EntradaDobladas.actualizarCatalogos(cdv);
//        EntradaTX.actualizarCatalogos(cdv);
//        EntradaTodo.actualizarCatalogos(cdv);
//        actualizarPersonalizados(cdv);
        actualizarEntradas(incluirExtrenos,cdv);

    }

    public void actualizarPersonalizados(boolean incluirExtrenos,ConfiguracionDeVideo cdv) throws FileNotFoundException {
        for (int i = 0; i < personalizadasEntrada.size(); i++) {
            personalizadasEntrada.get(i).actualizarCatalogos(incluirExtrenos,cdv);
        }
    }

    public InformacionDeSecciones getInf() {
        return inf;
    }

    public void setInf(InformacionDeSecciones inf) {
        this.inf = inf;
    }

    public CatalogosDeSeriesBasicos getBasicos() {
        return basicos;
    }

    public void setBasicos(CatalogosDeSeriesBasicos basicos) {
        this.basicos = basicos;
    }

    public SeccionDeCatalogosDeSerieEntrada getEntradaClasicas() {
        return EntradaClasicas;
    }

    public void setEntradaClasicas(SeccionDeCatalogosDeSerieEntrada EntradaClasicas) {
        this.EntradaClasicas = EntradaClasicas;
    }

    public SeccionDeCatalogosDeSerieEntrada getEntradaTX() {
        return EntradaTX;
    }

    public void setEntradaTX(SeccionDeCatalogosDeSerieEntrada EntradaTX) {
        this.EntradaTX = EntradaTX;
    }

    public SeccionDeCatalogosDeSerieEntrada getEntradaDobladas() {
        return EntradaDobladas;
    }

    public void setEntradaDobladas(SeccionDeCatalogosDeSerieEntrada EntradaDobladas) {
        this.EntradaDobladas = EntradaDobladas;
    }

    public LinkedList<SeccionDeCatalogosDeSerieEntrada> getPersonalizadasEntrada() {
        return personalizadasEntrada;
    }

    public void setPersonalizadasEntrada(LinkedList<SeccionDeCatalogosDeSerieEntrada> personalizadasEntrada) {
        this.personalizadasEntrada = personalizadasEntrada;
    }

}
