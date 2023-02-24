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
import java.io.FileNotFoundException;

/**
 *
 * @author Rene
 */
public abstract class ConjuntoEntradaCatalogosDeSeriesConEntrada extends ConjuntoEntradaCatalogosDeSeries {

    protected CatalogosDeSeriesEntrada entradas;
     protected CatalogoDeSeries personalizados;
//     protected FiltroSerie filtro;

    public ConjuntoEntradaCatalogosDeSeriesConEntrada(CatalogosDeSeriesBasicos basicos, CatalogosDeSeriesEntrada entradas, FiltroSerie filtro) {
        this.basicos = basicos;
        this.entradas = entradas;
        this.filtro = filtro;
    }

//    public FiltroSerie getFiltro() {
//        return filtro;
//    }
//
//    public void setFiltro(FiltroSerie filtro) {
//        this.filtro = filtro;
//    }

    public CatalogoDeSeries getPersonalizados() {
        return personalizados;
    }

    public void setPersonalizados(CatalogoDeSeries personalizados) {
        this.personalizados = personalizados;
    }

    public abstract void actualizarPersonalizados(ConfiguracionDeVideo cdv) throws FileNotFoundException;

    @Override
    public void actualizar(boolean incluirExtrenos,ConfiguracionDeVideo cdv) throws FileNotFoundException {
        super.actualizar(incluirExtrenos,cdv);
        actualizarPersonalizados(cdv);
//          System.out.println("d 1 4");
        actualizarTodo(incluirExtrenos);
//          System.out.println("d 1 5 ******************************************");
//        getSeguidos().imprimir();
    }
}
