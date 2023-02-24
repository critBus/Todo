/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.Copiador_De_Subtitulos;

import java.io.Serializable;

/**
 *
 * @author Rene
 */
public class Seccion implements Serializable{

    private Conjunto_De_Direcciones series, subtitulos;

    public Seccion() {
        this(new Conjunto_De_Direcciones(), new Conjunto_De_Direcciones());
    }

    public Seccion(Conjunto_De_Direcciones series, Conjunto_De_Direcciones subtitulos) {
        this.series = series;
        this.subtitulos = subtitulos;
    }

    public Conjunto_De_Direcciones getSeries() {
        return series;
    }

    public void setSeries(Conjunto_De_Direcciones series) {
        this.series = series;
    }

    public Conjunto_De_Direcciones getSubtitulos() {
        return subtitulos;
    }

    public void setSubtitulos(Conjunto_De_Direcciones subtitulos) {
        this.subtitulos = subtitulos;
    }

    @Override
    public String toString() {
        String a = "";
        if (series.getDirecciones().isEmpty()) {
            a += "[ sin series ]" + "\n";
        }
        for (int i = 0; i < series.getDirecciones().size(); i++) {
            a += series.getDirecciones().get(i) + "\n";
        }
        if (subtitulos.getDirecciones().isEmpty()) {
            a += "[ sin subtitulos ]" + "\n";
        }
        for (int i = 0; i < subtitulos.getDirecciones().size(); i++) {
            a += subtitulos.getDirecciones().get(i) + "\n";
        }
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

}
