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
public class CatalogosDeSeriesContrario extends ConjuntoEntradaCatalogosDeSeriesConEntrada {

    public CatalogosDeSeriesContrario(CatalogosDeSeriesBasicos basicos, CatalogosDeSeriesEntrada entradas, FiltroSerie filtro) {
        super(basicos, entradas, filtro);
    }

    @Override
    public void actualizarTodo(boolean incluirExtrenos) throws FileNotFoundException {
        Todo = new CatalogoDeSeries();
        if (incluirExtrenos) {
            Todo.addNecesarios(Seguidos, Extrenos, EnEspera, PorVer, Finalizadas, personalizados);
        } else {
            Todo.addNecesarios(Seguidos, EnEspera, PorVer, Finalizadas, personalizados);
        }

    }

    @Override
    public void actualizarSeguidos(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        Seguidos = basicos.getSeguidos().getCatalogoSeriesContrario(entradas.getEntrada());
    }

    @Override
    public void actualizarExtrenos(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        Extrenos = entradas.getExtrenos().getCatalogoSeriesContrario(entradas.getEntrada());
    }

    @Override
    public void actualizarEnEspera(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        EnEspera = basicos.getEnEspera().getCatalogoSeriesContrario(entradas.getEntrada());
    }

    @Override
    public void actualizarPorVer(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        PorVer = basicos.getPorVer().getCatalogoSeriesContrario(entradas.getEntrada());
    }

    @Override
    public void actualizarFinalizadas(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        Finalizadas = basicos.getFinalizadas().getCatalogoSeriesContrario(entradas.getEntrada());
    }

    @Override
    public void actualizarPersonalizados(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        personalizados = entradas.getPersonalizadosSeleccionados().getCatalogoSeriesContrario(entradas.getEntrada());

    }

}
