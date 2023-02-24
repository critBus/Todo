/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.actualizador.de.series;

import Utiles.ClasesUtiles.Configuraciones.Ventanas.TablasAmpliableConfiguracionDeVideo;
import Aplicaciones.actualizador.de.series.Secciones.InformacionDeSecciones;
import java.io.File;
//import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import java.io.FileNotFoundException;
import java.io.Serializable;

/**
 *
 * @author Rene
 */
public class EstadoActual implements Serializable {

    private InformacionDeSecciones anime, series;
    private CreadorDeCarpetas Cdc;
    private File paquete, contenido,TXT;
    private TablasAmpliableConfiguracionDeVideo T;

    public EstadoActual() throws FileNotFoundException {
        this(new InformacionDeSecciones(), new InformacionDeSecciones(), CreadorDeCarpetas.getDefault(), new File(""), new File(""), new File(""),new TablasAmpliableConfiguracionDeVideo());
    }

    public EstadoActual(InformacionDeSecciones anime, InformacionDeSecciones series, CreadorDeCarpetas Cdc, File paquete, File contenido, File TXT, TablasAmpliableConfiguracionDeVideo T) {
        this.anime = anime;
        this.series = series;
        this.Cdc = Cdc;
        this.paquete = paquete;
        this.contenido = contenido;
        this.TXT = TXT;
        this.T = T;
    }

   

    public TablasAmpliableConfiguracionDeVideo getT() {
        return T;
    }

    public void setT(TablasAmpliableConfiguracionDeVideo T) {
        this.T = T;
    }

    public File getTXT() {
        return TXT;
    }

    public void setTXT(File TXT) {
        this.TXT = TXT;
    }

    

    public File getPaquete() {
        return paquete;
    }

    public void setPaquete(File paquete) {
        this.paquete = paquete;
    }

    public File getContenido() {
        return contenido;
    }

    public void setContenido(File contenido) {
        this.contenido = contenido;
    }

    public InformacionDeSecciones getAnime() {
        return anime;
    }

    public void setAnime(InformacionDeSecciones anime) {
        this.anime = anime;
    }

    public InformacionDeSecciones getSeries() {
        return series;
    }

    public void setSeries(InformacionDeSecciones series) {
        this.series = series;
    }

//    public void actualizarCatalogos(ConfiguracionDeVideo cdv) throws FileNotFoundException {
//        anime.actualizarCatalogos(cdv);
//        series.actualizarCatalogos(cdv);
//    }
    public CreadorDeCarpetas getCdc() {
        return Cdc;
    }

    public void setCdc(CreadorDeCarpetas Cdc) {
        this.Cdc = Cdc;
    }
}
