/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.actualizador.de.series.Secciones;

import Aplicaciones.actualizador.de.series.Direccion;
import Utiles.ClasesUtiles.Interfases.addAll;
import Utiles.ClasesUtiles.Tablas.Tabla;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class SeccionesBase implements Serializable, addAll<SeccionesBase> {

    private SeccionSerie Seguidos, QueTengo, EnEspera, PorVer, Finalizadas;
    private LinkedList<SeccionPersonalizada> personalizadas;

    public SeccionesBase() {
        this(new SeccionSerie(SeccionSerie.FormaString.NOMBRES_DE_SERIES), new SeccionSerie(SeccionSerie.FormaString.SOLO_CAPITULOS), new SeccionSerie(SeccionSerie.FormaString.NOMBRES_DE_SERIES), new SeccionSerie(SeccionSerie.FormaString.NOMBRES_DE_SERIES), new SeccionSerie(SeccionSerie.FormaString.NOMBRES_DE_SERIES), new LinkedList<SeccionPersonalizada>());
    }

    public SeccionesBase(SeccionSerie Seguidos, SeccionSerie QueTengo, SeccionSerie EnEspera, SeccionSerie PorVer, SeccionSerie Finalizadas, LinkedList<SeccionPersonalizada> personalizadas) {
        this.Seguidos = Seguidos;
        this.QueTengo = QueTengo;
        this.EnEspera = EnEspera;
        this.PorVer = PorVer;
        this.Finalizadas = Finalizadas;
        this.personalizadas = personalizadas;
    }

    public void clearBase() {

        EnEspera.getDirecciones().clear();
        Finalizadas.getDirecciones().clear();
        PorVer.getDirecciones().clear();
        QueTengo.getDirecciones().clear();
        Seguidos.getDirecciones().clear();
        personalizadas.clear();
    }

    public Tabla getTablaNombresPersonalizadosBase() {
        String[] nombres = new String[personalizadas.size()];
        boolean[] selec = new boolean[personalizadas.size()];
        for (int i = 0; i < personalizadas.size(); i++) {
            nombres[i] = personalizadas.get(i).getNombre();
            selec[i] = personalizadas.get(i).isSeleccionado();
        }
        return Tabla.crearStringSeleccionable(nombres, selec);
    }

    public SeccionSerie getSeguidos() {
        return Seguidos;
    }

    public void setSeguidos(SeccionSerie Seguidos) {
        this.Seguidos = Seguidos;
    }

    public SeccionSerie getQueTengo() {
        return QueTengo;
    }

    public void setQueTengo(SeccionSerie QueTengo) {
        this.QueTengo = QueTengo;
    }

    public SeccionSerie getEnEspera() {
        return EnEspera;
    }

    public void setEnEspera(SeccionSerie EnEspera) {
        this.EnEspera = EnEspera;
    }

    public SeccionSerie getPorVer() {
        return PorVer;
    }

    public void setPorVer(SeccionSerie PorVer) {
        this.PorVer = PorVer;
    }

    public SeccionSerie getFinalizadas() {
        return Finalizadas;
    }

    public void setFinalizadas(SeccionSerie Finalizadas) {
        this.Finalizadas = Finalizadas;
    }

    public LinkedList<SeccionPersonalizada> getPersonalizadas() {
        return personalizadas;
    }

    public void setPersonalizadas(LinkedList<SeccionPersonalizada> personalizadas) {
        this.personalizadas = personalizadas;
    }

    @Override
    public void addAll(SeccionesBase s) {
        EnEspera.getDirecciones().addAll(s.EnEspera.getDirecciones());
        Finalizadas.getDirecciones().addAll(s.Finalizadas.getDirecciones());
        PorVer.getDirecciones().addAll(s.PorVer.getDirecciones());
        QueTengo.getDirecciones().addAll(s.QueTengo.getDirecciones());
        Seguidos.getDirecciones().addAll(s.Seguidos.getDirecciones());
//        personalizadas.addAll(s.getPersonalizadas());
        personalizadoAddAll(s.getPersonalizadas());
    }

    public void personalizadoAddAll(LinkedList<SeccionPersonalizada> s) {
        for (int i = 0; i < s.size(); i++) {
            boolean agregar = true;
            for (int j = 0; j < personalizadas.size(); j++) {
                if (s.get(i).getNombre().equals(personalizadas.get(j).getNombre())) {
                    agregar = false;
                    break;
                }
            }
            if (agregar) {
                personalizadas.add(s.get(i));
            }
        }
    }

    @Override
    public String toString() {
        String a = "Seccion Base " + "\n";
        LinkedList<Direccion> d = Seguidos.getDirecciones();
        a += "\nSeguidos:" + "\n";
        for (int i = 0; i < d.size(); i++) {
            a += d.get(i).getF().toString() + "\n";
        }
        if (d.isEmpty()) {
            a += "[ sin direcciones ]" + "\n";
        }
        d = QueTengo.getDirecciones();
        a += "\nQueTengo:" + "\n";
        for (int i = 0; i < d.size(); i++) {
            a += d.get(i).getF().toString() + "\n";
        }
        if (d.isEmpty()) {
            a += "[ sin direcciones ]" + "\n";
        }
        d = EnEspera.getDirecciones();
        a += "\nEnEspera:" + "\n";
        for (int i = 0; i < d.size(); i++) {
            a += d.get(i).getF().toString() + "\n";
        }
        if (d.isEmpty()) {
            a += "[ sin direcciones ]" + "\n";
        }
        d = PorVer.getDirecciones();
        a += "\nPorVer:" + "\n";
        for (int i = 0; i < d.size(); i++) {
            a += d.get(i).getF().toString() + "\n";
        }
        if (d.isEmpty()) {
            a += "[ sin direcciones ]" + "\n";
        }
        d = Finalizadas.getDirecciones();
        a += "\nFinalizadas:" + "\n";
        for (int i = 0; i < d.size(); i++) {
            a += d.get(i).getF().toString() + "\n";
        }
        if (d.isEmpty()) {
            a += "[ sin direcciones ]" + "\n";
        }
        a += "\nPersonalizados:" + "\n";
        if (personalizadas.isEmpty()) {
            a += "[ sin Personalizados ]" + "\n";
        } else {
            for (int i = 0; i < personalizadas.size(); i++) {
                a += "\n" + personalizadas.get(i) + "\n";
//                a += "\nNombre:" + personalizadas.get(i).getNombre() + "\n";
//                a += "Estado:" + personalizadas.get(i).getEstado() + "\n";
//                a += "Direcciones:" + "\n";
//                d = personalizadas.get(i).getDirecciones();
//                for (int j = 0; j < d.size(); j++) {
//                    a += d.get(j).getF().toString() + "\n";
//                }
//                if (d.isEmpty()) {
//                    a += "[ sin direcciones ]" + "\n";
//                }
            }

        }
        return a;
    }

}
