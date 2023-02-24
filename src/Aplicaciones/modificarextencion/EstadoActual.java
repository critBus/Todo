/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.modificarextencion;

import Utiles.ClasesUtiles.Configuraciones.Ventanas.TablasAmpliableConfiguracionDeVideo;
import java.io.File;
import java.io.Serializable;

/**
 *
 * @author Rene
 */
public class EstadoActual implements Serializable{
  private  TablasAmpliableConfiguracionDeVideo T;
  private  File direccion;

    public EstadoActual() {
        this(new TablasAmpliableConfiguracionDeVideo(), new File(""));
    }

    public EstadoActual(TablasAmpliableConfiguracionDeVideo T, File direccion) {
        this.T = T;
        this.direccion = direccion;
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
