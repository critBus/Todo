/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.MoverNumero;

import Aplicaciones.modificarextencion.*;
import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Configuraciones.Ventanas.TablasAmpliableConfiguracionDeVideo;
import java.io.File;
import java.io.Serializable;

/**
 *
 * @author Rene
 */
public class EstadoActual implements Serializable {
//F:/Mis videos/Series(Mangas)/One Piece
    private TablasAmpliableConfiguracionDeVideo T;
    private File direccion;
    private ConfiguracionDeVideo cdv;

    public EstadoActual() {
        this(new TablasAmpliableConfiguracionDeVideo(), new File(""), ConfiguracionDeVideo.getDefault());
    }

    public EstadoActual(TablasAmpliableConfiguracionDeVideo T, File direccion, ConfiguracionDeVideo cdv) {
        this.T = T;
        this.direccion = direccion;
        this.cdv = cdv;
    }

    public ConfiguracionDeVideo getCdv() {
        return cdv;
    }

    public void setCdv(ConfiguracionDeVideo cdv) {
        this.cdv = cdv;
    }

    public TablasAmpliableConfiguracionDeVideo getT() {
        return T;
    }

    public void setT(TablasAmpliableConfiguracionDeVideo T) {
        this.T = T;
    }

    public File getDireccion() {
        return direccion;
    }

    public void setDireccion(File direccion) {
        this.direccion = direccion;
    }

}
