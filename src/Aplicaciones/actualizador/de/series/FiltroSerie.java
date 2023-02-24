/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.actualizador.de.series;

import java.io.Serializable;

/**
 *
 * @author Rene
 */
public class FiltroSerie implements Serializable {

    private boolean Menores, Mayores, Extrenos, Solo, Ultimos, Finalizadas,
            EnEspera, Personalizados, PorVer, Primeros,
            QueTengo, Seguidos, Capitulos1y0;
    private int numeroMenores, numeroMayores, numeroSolo;
   // private boolean relacionados;
    public Relacion relacion;
    public enum Relacion{
    RELACIONADOS,FALTANTES,CONTRARIOS
    }
    
    public FiltroSerie() {
        this(false, false, false, false, false, false, false, false, false, false, false, false, false, 0, 0, 0,Relacion.RELACIONADOS );
    }

    public FiltroSerie(boolean Menores, boolean Mayores, boolean Extrenos, boolean Solo, boolean Ultimos, boolean Finalizadas, boolean EnEspera, boolean Personalizados, boolean PorVer, boolean Primeros, boolean QueTengo, boolean Seguidos, boolean Capitulos1y0, int numeroMenores, int numeroMayores, int numeroSolo, Relacion relacion) {
        this.Menores = Menores;
        this.Mayores = Mayores;
        this.Extrenos = Extrenos;
        this.Solo = Solo;
        this.Ultimos = Ultimos;
        this.Finalizadas = Finalizadas;
        this.EnEspera = EnEspera;
        this.Personalizados = Personalizados;
        this.PorVer = PorVer;
        this.Primeros = Primeros;
        this.QueTengo = QueTengo;
        this.Seguidos = Seguidos;
        this.Capitulos1y0 = Capitulos1y0;
        this.numeroMenores = numeroMenores;
        this.numeroMayores = numeroMayores;
        this.numeroSolo = numeroSolo;
        this.relacion = relacion;
    }

   

  

    public boolean isCapitulos1y0() {
        return Capitulos1y0;
    }

    public void setCapitulos1y0(boolean Capitulos1y0) {
        this.Capitulos1y0 = Capitulos1y0;
    }

    public boolean isEnEspera() {
        return EnEspera;
    }

    public void setEnEspera(boolean EnEspera) {
        this.EnEspera = EnEspera;
    }

    public boolean isExtrenos() {
        return Extrenos;
    }

    public void setExtrenos(boolean Extrenos) {
        this.Extrenos = Extrenos;
    }

    public boolean isFinalizadas() {
        return Finalizadas;
    }

    public void setFinalizadas(boolean Finalizadas) {
        this.Finalizadas = Finalizadas;
    }

    public boolean isMayores() {
        return Mayores;
    }

    public void setMayores(boolean Mayores) {
        this.Mayores = Mayores;
    }

    public boolean isMenores() {
        return Menores;
    }

    public void setMenores(boolean Menores) {
        this.Menores = Menores;
    }

    public boolean isPersonalizados() {
        return Personalizados;
    }

    public void setPersonalizados(boolean Personalizados) {
        this.Personalizados = Personalizados;
    }

    public boolean isPorVer() {
        return PorVer;
    }

    public void setPorVer(boolean PorVerBase) {
        this.PorVer = PorVerBase;
    }

    public boolean isPrimeros() {
        return Primeros;
    }

    public void setPrimeros(boolean Primeros) {
        this.Primeros = Primeros;
    }

    public boolean isQueTengo() {
        return QueTengo;
    }

    public void setQueTengo(boolean QueTengo) {
        this.QueTengo = QueTengo;
    }

    public boolean isSeguidos() {
        return Seguidos;
    }

    public void setSeguidos(boolean Seguidos) {
        this.Seguidos = Seguidos;
    }

    public boolean isSolo() {
        return Solo;
    }

    public void setSolo(boolean Solo) {
        this.Solo = Solo;
    }

    public boolean isUltimos() {
        return Ultimos;
    }

    public void setUltimos(boolean Ultimos) {
        this.Ultimos = Ultimos;
    }

    public int getNumeroMenores() {
        return numeroMenores;
    }

    public void setNumeroMenores(int numeroMenores) {
        this.numeroMenores = numeroMenores;
    }

    public int getNumeroMayores() {
        return numeroMayores;
    }

    public void setNumeroMayores(int numeroMayores) {
        this.numeroMayores = numeroMayores;
    }

    public int getNumeroSolo() {
        return numeroSolo;
    }

    public void setNumeroSolo(int numeroSolo) {
        this.numeroSolo = numeroSolo;
    }

    public Relacion getRelacion() {
        return relacion;
    }

    public void setRelacion(Relacion relacion) {
        this.relacion = relacion;
    }

   

    public boolean isEmpty() {
        return !(isCapitulos1y0() || isEnEspera() || isExtrenos() || isFinalizadas() || isMayores() || isMenores() || isPersonalizados() || isPorVer() || isPrimeros() || isQueTengo() || isSeguidos() || isSolo() || isUltimos());
    }
}
