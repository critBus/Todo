/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.Contenido_Carpeta;

import Utiles.ClasesUtiles.Tablas.Tabla;
import java.io.File;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class Estado_Actual implements Serializable{
 private Direcciones_De_Carpetas direcciones_De_Carpetas;
    private File direccionTXT;

    public Estado_Actual() {
        this(new Direcciones_De_Carpetas(), new File(""));
    }

    public Estado_Actual(Direcciones_De_Carpetas direcciones_De_Carpetas, File direccionTXT) {
        this.direcciones_De_Carpetas = direcciones_De_Carpetas;
        this.direccionTXT = direccionTXT;
    }

    public Direcciones_De_Carpetas getDirecciones_De_Carpetas() {
        return direcciones_De_Carpetas;
    }

    public void setDirecciones_De_Carpetas(Direcciones_De_Carpetas direcciones_De_Carpetas) {
        this.direcciones_De_Carpetas = direcciones_De_Carpetas;
    }

    public File getDireccionTXT() {
        return direccionTXT;
    }

    public void setDireccionTXT(File direccionTXT) {
        this.direccionTXT = direccionTXT;
    }

    

}
