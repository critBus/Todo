/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.actualizador.de.series.Catalogos.Secciones;

import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
//import Utiles.ClasesUtiles.Multimedia.Series.CatalogoDeSeries;
import Aplicaciones.actualizador.de.series.Catalogos.*;
import Aplicaciones.actualizador.de.series.*;
import Utiles.ClasesUtiles.Multimedia.Series.CatalogoDeSeries;
import java.io.FileNotFoundException;
//import java.io.Serializable;
//import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class CatalogosDeSeriesFaltanPorCopiar extends ConjuntoEntradaCatalogosDeSeriesConEntrada{

//    private CatalogosDeSeriesBasicos basicos;
//    private CatalogosDeSeriesEntrada entradas;
//    private CatalogoDeSeries Todo, Seguidos, Extrenos, EnEspera, PorVer, Finalizadas;
//   
//    private CatalogoDeSeries personalizados;
//    private FiltroSerie filtro;

    public CatalogosDeSeriesFaltanPorCopiar(CatalogosDeSeriesBasicos basicos, CatalogosDeSeriesEntrada entradas, FiltroSerie filtro) {
        super(basicos, entradas, filtro);
    }

//    public FiltroSerie getFiltro() {
//        return filtro;
//    }
//
//    public void setFiltro(FiltroSerie filtro) {
//        this.filtro = filtro;
//    }

    @Override
    public void actualizarTodo(boolean incluirExtrenos) throws FileNotFoundException {
        Todo = basicos.getQueTengo().getCatalogoSeriesFaltantes(entradas.getTodo());
    }

    @Override
    public void actualizarSeguidos(ConfiguracionDeVideo cdv) throws FileNotFoundException {
//        entradas.getSeguidos().imprimir();
        Seguidos = basicos.getQueTengo().getCatalogoSeriesFaltantes(entradas.getSeguidos());
//        Seguidos.imprimir();
        
    }

    @Override
    public void actualizarExtrenos(ConfiguracionDeVideo cdv) throws FileNotFoundException {
//       entradas.imprimir();
        CatalogoDeSeries c=entradas.getExtrenos();
//        c.imprimir();
        Extrenos = basicos.getQueTengo().getCatalogoSeriesFaltantes(c);
    }

    @Override
    public void actualizarEnEspera(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        EnEspera = basicos.getQueTengo().getCatalogoSeriesFaltantes(entradas.getEnEspera());
    }

    @Override
    public void actualizarPorVer(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        PorVer = basicos.getQueTengo().getCatalogoSeriesFaltantes(entradas.getPorVer());
    }

    @Override
    public void actualizarFinalizadas(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        //  Finalizadas = basicos.getFinalizadas().getCatalogoSeriesFaltantes(entradas.getFinalizadas());
        Finalizadas = basicos.getQueTengo().getCatalogoSeriesFaltantes(entradas.getFinalizadas());
    }

    @Override
    public void actualizarPersonalizados(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        personalizados = basicos.getQueTengo().getCatalogoSeriesFaltantes(entradas.getPersonalizadosRelacionados());

    }

//    public void actualizar(ConfiguracionDeVideo cdv) throws FileNotFoundException {
//        actualizarEnEspera(cdv);
////          System.out.println("d 1 1");
//        actualizarExtrenos(cdv);
////          System.out.println("d 1 2");
//        actualizarPorVer(cdv);
////          System.out.println("d 1 3");
//        actualizarSeguidos(cdv);
//        actualizarFinalizadas(cdv);
//        actualizarPersonalizados(cdv);
////          System.out.println("d 1 4");
//        actualizarTodo();
////          System.out.println("d 1 5 ******************************************");
////        getSeguidos().imprimir();
//    }
//
//    public CatalogosDeSeriesBasicos getBasicos() {
//        return basicos;
//    }
//
//    public void setBasicos(CatalogosDeSeriesBasicos basicos) {
//        this.basicos = basicos;
//    }
//
//    public CatalogosDeSeriesEntrada getEntradas() {
//        return entradas;
//    }
//
//    public void setEntradas(CatalogosDeSeriesEntrada entradas) {
//        this.entradas = entradas;
//    }
//
//    public CatalogoDeSeries getTodo() {
//        return Todo;
//    }
//
//    public void setTodo(CatalogoDeSeries Todo) {
//        this.Todo = Todo;
//    }
//
//    public CatalogoDeSeries getSeguidos() {
//        return Seguidos;
//    }
//
//    public void setSeguidos(CatalogoDeSeries Seguidos) {
//        this.Seguidos = Seguidos;
//    }
//
//    public CatalogoDeSeries getExtrenos() {
//        return Extrenos;
//    }
//
//    public void setExtrenos(CatalogoDeSeries Extrenos) {
//        this.Extrenos = Extrenos;
//    }
//
//    public CatalogoDeSeries getEnEspera() {
//        return EnEspera;
//    }
//
//    public void setEnEspera(CatalogoDeSeries EnEspera) {
//        this.EnEspera = EnEspera;
//    }
//
//    public CatalogoDeSeries getPorVer() {
//        return PorVer;
//    }
//
//    public void setPorVer(CatalogoDeSeries PorVer) {
//        this.PorVer = PorVer;
//    }
//
//    public CatalogoDeSeries getFinalizadas() {
//        return Finalizadas;
//    }
//
//    public void setFinalizadas(CatalogoDeSeries Finalizadas) {
//        this.Finalizadas = Finalizadas;
//    }
//
//    public CatalogoDeSeries getPersonalizados() {
//        return personalizados;
//    }
//
//    public void setPersonalizados(CatalogoDeSeries personalizados) {
//        this.personalizados = personalizados;
//    }

  

}
