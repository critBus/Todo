/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.actualizador.de.series.Catalogos;

import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Aplicaciones.actualizador.de.series.EstadoActual;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.Serializable;

/**
 *
 * @author Rene
 */
public class CatalogosActuales  {

    private EstadoActual EA;
    private CatalogosDeSeries anime, series;

    public CatalogosActuales(EstadoActual EA, ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
        this.EA = EA;
        anime = new CatalogosDeSeries(EA.getAnime(), cdv);
        series = new CatalogosDeSeries(EA.getSeries(), cdv);
    }

    public EstadoActual getEA() {
        return EA;
    }

    public void setEA(EstadoActual EA) {
        this.EA = EA;
    }

    public CatalogosDeSeries getAnime() {
        return anime;
    }

    public void setAnime(CatalogosDeSeries anime) {
        this.anime = anime;
    }

    public CatalogosDeSeries getSeries() {
        return series;
    }

    public void setSeries(CatalogosDeSeries series) {
        this.series = series;
    }

//    public void actualizarCatalogos(boolean incluirExtrenos,ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
//        anime.actualizarCatalogos(incluirExtrenos,cdv);
//        series.actualizarCatalogos(incluirExtrenos,cdv);
//    }
}
