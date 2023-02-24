/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.modificarextencion;

import java.io.Serializable;

/**
 *
 * @author Rene
 */
public class Direccion implements Serializable{

    private String direccion;
    private int PIN;

    public Direccion(String direccion, int PIN) {
        this.direccion = direccion;
        this.PIN = PIN;
    }

    public String getDireccion(int PIN) {
        return isPIN(PIN) ? direccion : "Pin incorrecto";
    }

    public boolean setDireccion(String direccion, int PIN) {
        if (isPIN(PIN)) {
            this.direccion = direccion;
            return true;
        }
        return false;
    }

    public int getPIN(int PIN) {
      //  System.out.println("Pin="+this.PIN);
        return isPIN(PIN) ? PIN : -1;
    }

    public boolean isPIN(int PIN) {
        return this.PIN == PIN;
    }

    public boolean setPIN(int PIN, int PIN_anterior) {

        if (PIN < 10000 || isPIN(PIN_anterior)) {
            this.PIN = PIN;
            return true;
        }
        return false;
    }

}
