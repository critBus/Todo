/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.Copiador_De_Subtitulos;

import Utiles.ClasesUtiles.Configuraciones.Ventanas.TablasAmpliableConfiguracionDeVideo;
import java.io.File;
import java.io.Serializable;

/**
 *
 * @author Rene
 */
public class Estado_Actual implements Serializable {

    private Seccion seccion; 
    private File paquete;
    private TablasAmpliableConfiguracionDeVideo T;

    public Estado_Actual() {
        this(new Seccion(), new File(""),new TablasAmpliableConfiguracionDeVideo());
    }

    public Estado_Actual(Seccion seccion, File paquete, TablasAmpliableConfiguracionDeVideo T) {
        this.seccion = seccion;
        this.paquete = paquete;
        this.T = T;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public TablasAmpliableConfiguracionDeVideo getT() {
        return T;
    }

    public void setT(TablasAmpliableConfiguracionDeVideo T) {
        this.T = T;
    }

    

    public File getPaquete() {
        return paquete;
    }

    public void setPaquete(File paquete) {
        this.paquete = paquete;
    }

}
