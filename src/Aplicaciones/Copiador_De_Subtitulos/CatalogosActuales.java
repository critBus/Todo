/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.Copiador_De_Subtitulos;

import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Multimedia.Series.CatalogoDeSeries;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class CatalogosActuales {

    private CatalogoDeSeries series, subtitulos;

    public CatalogosActuales(Estado_Actual EA,ConfiguracionDeVideo cdv) throws IOException, ClassNotFoundException {
        this(getCatalogoDeSeries(cdv, EA.getSeccion().getSeries().getDirecciones()),
                getCatalogoDeSeries(cdv, EA.getSeccion().getSubtitulos().getDirecciones()));
    }

    public CatalogosActuales(CatalogoDeSeries series, CatalogoDeSeries subtitulos) {
        this.series = series;
        this.subtitulos = subtitulos;
    }

    public void actualizarSerie(Estado_Actual EA,ConfiguracionDeVideo cdv) throws IOException, FileNotFoundException, ClassNotFoundException {
        series = getCatalogoDeSeries(cdv, EA.getSeccion().getSeries().getDirecciones());
    }

    public void actualizarSubtitulos(Estado_Actual EA,ConfiguracionDeVideo cdv) throws IOException, FileNotFoundException, ClassNotFoundException {
       subtitulos = getCatalogoDeSeries(cdv, EA.getSeccion().getSubtitulos().getDirecciones());
    }

    private static CatalogoDeSeries getCatalogoDeSeries(ConfiguracionDeVideo cdv, LinkedList<Direccion>... DD) throws FileNotFoundException, IOException, ClassNotFoundException {

        LinkedList<File> F = new LinkedList<File>();
        LinkedList<LinkedList<String>> inva = new LinkedList<LinkedList<String>>();
        for (int i = 0; i < DD.length; i++) {
            //LinkedList<Direccion> D=DD[i];
            for (int j = 0; j < DD[i].size(); j++) {
                Direccion d = DD[i].get(j);
                if (d.isSeleccionado()) {
                    LinkedList<String> invalidos = new LinkedList<String>();

                    for (int k = 0; k < d.getDirectoriosInvalidos().size(); k++) {
                        DirectoriosInvalidos di = d.getDirectoriosInvalidos().get(k);
                        if (di.isSeleccionado()) {
                            invalidos.add(di.getDirectorioInvalido());
                        }
                    }
                    F.add(d.getF());
                    inva.add(invalidos);
                }
            }
        }
//        System.out.println(F.size());
//        if(!F.isEmpty()){
//            System.out.println(F.get(0).toString());
//        }
//        System.out.println(inva);
//        System.out.println("ooooooo");
        return new CatalogoDeSeries(F, inva, cdv);
    }

    public CatalogoDeSeries getSeries() {
        return series;
    }

    public void setSeries(CatalogoDeSeries series) {
        this.series = series;
    }

    public CatalogoDeSeries getSubtitulos() {
        return subtitulos;
    }

    public void setSubtitulos(CatalogoDeSeries subtitulos) {
        this.subtitulos = subtitulos;
    }
    
}
