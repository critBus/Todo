/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.convertir;

import Utiles.ClasesUtiles.Medidas.*;
import Utiles.ClasesUtiles.Medidas.Unidades.UnidadDeMedida;

/**
 *
 * @author Rene
 */
public class Convertidor {

    private TipoDeMedida medidas[] = TipoDeMedida.values();
    private int IndiceMedidaActual = 0;
    private UnidadDeMedida unidades[] = medidas[IndiceMedidaActual].getUnidades();
    private int IndiceUnidadBase = 0;
    private int IndiceUnidadResultante = 0;
//    private double base = 0;
//    private double resultado = 0;

    public TipoDeMedida[] getMedidas() {
        return medidas;
    }

//    public void setMedidas(TipoDeMedida[] medidas) {
//        this.medidas = medidas;
//    }

    public int getIndiceMedidaActual() {
        return IndiceMedidaActual;
    }

    public void setIndiceMedidaActual(int IndiceMedidaActual) {
        this.IndiceMedidaActual = IndiceMedidaActual;
        unidades = medidas[IndiceMedidaActual].getUnidades();
    }

    public UnidadDeMedida[] getUnidades() {
        return unidades;
    }

//    public void setUnidades(UnidadDeMedida[] unidades) {
//        this.unidades = unidades;
//    }

    public int getIndiceUnidadBase() {
        return IndiceUnidadBase;
    }

    public void setIndiceUnidadBase(int IndiceUnidadBase) {
        this.IndiceUnidadBase = IndiceUnidadBase;
    }

    public int getIndiceUnidadResultante() {
        return IndiceUnidadResultante;
    }

    public void setIndiceUnidadResultante(int IndiceUnidadResultante) {
        this.IndiceUnidadResultante = IndiceUnidadResultante;
    }
    
    public UnidadDeMedida getUnidadBase(){
    return unidades[IndiceUnidadBase];
    }
    public UnidadDeMedida getUnidadResultante(){
    return unidades[IndiceUnidadResultante];
    }
    
    public double convertir(double cantidad){
    return cantidad==0?0:unidades[IndiceUnidadBase].getEquivalencia(unidades[IndiceUnidadResultante], cantidad);
    }

}
