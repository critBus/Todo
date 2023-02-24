/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.actualizador.de.series.Catalogos.Secciones;

import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Multimedia.Series.CatalogoDeSeries;
import Aplicaciones.actualizador.de.series.Catalogos.*;
import Aplicaciones.actualizador.de.series.*;
import Aplicaciones.actualizador.de.series.Secciones.*;
import java.io.FileNotFoundException;

/**
 *
 * @author Rene
 */
public abstract class ConjuntoEntradaCatalogosDeSeries {
//  BaseTodo

    protected CatalogosDeSeriesBasicos basicos;
    protected CatalogoDeSeries  Seguidos, Extrenos, EnEspera, PorVer, Todo, Finalizadas;
     // private LinkedList<CatalogoDeSeries> personalizados;

    protected FiltroSerie filtro;

    public CatalogoDeSeries actualizarCatalogoSegunFiltro(CatalogoDeSeries c) {
        return CatalogosDeSeriesBasicos.actualizarCatalogoSegunFiltro(basicos, c,getFiltro() );
    }

//    public abstract void actualizarEntrada(ConfiguracionDeVideo cdv) throws FileNotFoundException;

    public abstract void actualizarSeguidos(ConfiguracionDeVideo cdv) throws FileNotFoundException;

    public abstract void actualizarExtrenos(ConfiguracionDeVideo cdv) throws FileNotFoundException;

    public abstract void actualizarEnEspera(ConfiguracionDeVideo cdv) throws FileNotFoundException;

    public abstract void actualizarPorVer(ConfiguracionDeVideo cdv) throws FileNotFoundException;

    public abstract void actualizarFinalizadas(ConfiguracionDeVideo cdv) throws FileNotFoundException;

    public abstract void actualizarTodo(boolean incluirExtrenos) throws FileNotFoundException;

    public FiltroSerie getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroSerie filtro) {
        this.filtro = filtro;
    }

    
    public CatalogosDeSeriesBasicos getBasicos() {
        return basicos;
    }

    public void setBasicos(CatalogosDeSeriesBasicos basicos) {
        this.basicos = basicos;
    }

    public CatalogoDeSeries getSeguidos() {
        return Seguidos;
    }

    public void setSeguidos(CatalogoDeSeries Seguidos) {
        this.Seguidos = Seguidos;
    }

    public CatalogoDeSeries getExtrenos() {
        return Extrenos;
    }

    public void setExtrenos(CatalogoDeSeries Extrenos) {
        this.Extrenos = Extrenos;
    }

    public CatalogoDeSeries getEnEspera() {
        return EnEspera;
    }

    public void setEnEspera(CatalogoDeSeries EnEspera) {
        this.EnEspera = EnEspera;
    }

    public CatalogoDeSeries getPorVer() {
        return PorVer;
    }

    public void setPorVer(CatalogoDeSeries PorVer) {
        this.PorVer = PorVer;
    }

    public CatalogoDeSeries getTodo() {
        return Todo;
    }

    public void setTodo(CatalogoDeSeries Todo) {
        this.Todo = Todo;
    }

    public CatalogoDeSeries getFinalizadas() {
        return Finalizadas;
    }

    public void setFinalizadas(CatalogoDeSeries Finalizadas) {
        this.Finalizadas = Finalizadas;
    }

    public void actualizar(boolean incluirExtrenos,ConfiguracionDeVideo cdv) throws FileNotFoundException {
//        actualizarEntrada(cdv);
        // Entrada.imprimir();

//        System.out.println("d 0 1");
        actualizarEnEspera(cdv);
//         System.out.println("d 0 2");
        actualizarExtrenos(cdv);
//         System.out.println("d 0 3");
        actualizarPorVer(cdv);
//         System.out.println("d 0 4");
        actualizarSeguidos(cdv);
        actualizarFinalizadas(cdv);
    }

   

}
