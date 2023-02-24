/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.actualizador.de.series.Secciones;

import Utiles.ClasesUtiles.Interfases.addAll;
import Aplicaciones.actualizador.de.series.Direccion;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class SeccionesEntrada implements Serializable, addAll<SeccionesEntrada> {

    private SeccionSerie Clasicas, TX, Dobladas, Todo, Finalizadas;
    private LinkedList<SeccionPersonalizada> personalizadas;

    public SeccionesEntrada() {
        this(new SeccionSerie(SeccionSerie.FormaString.CAPITULOS), new SeccionSerie(SeccionSerie.FormaString.CAPITULOS), new SeccionSerie(SeccionSerie.FormaString.CAPITULOS), new SeccionSerie(SeccionSerie.FormaString.CAPITULOS), new SeccionSerie(SeccionSerie.FormaString.CAPITULOS), new LinkedList<SeccionPersonalizada>());
    }

    public SeccionesEntrada(SeccionSerie Clasicas, SeccionSerie TX, SeccionSerie Dobladas, SeccionSerie Todo, SeccionSerie Finalizadas, LinkedList<SeccionPersonalizada> personalizadas) {
        this.Clasicas = Clasicas;
        this.TX = TX;
        this.Dobladas = Dobladas;
        this.Todo = Todo;
        this.Finalizadas = Finalizadas;
        this.personalizadas = personalizadas;
    }

    public void clearEntradas() {
        Clasicas.getDirecciones().clear();
        Dobladas.getDirecciones().clear();
        Finalizadas.getDirecciones().clear();
        TX.getDirecciones().clear();
        Todo.getDirecciones().clear();
        personalizadas.clear();

    }

    public void actualizarDirecionesEntradaTodo() {
        LinkedList<Direccion> d = new LinkedList<Direccion>();
        for (int i = 0; i < 4 + personalizadas.size(); i++) {
            d.addAll(getEntradaCorrespondiente(i).getDirecciones());
        }
        Todo.setDirecciones(d);

    }

    private SeccionSerie getEntradaCorrespondiente(int i) {
        switch (i) {
            case 0:
                return Clasicas;

            case 1:
                return Dobladas;

            case 2:
                return Finalizadas;

            case 3:
                return TX;

        }

        return personalizadas.get(i - 4);
    }

    public void seEliminoDireccionEnEntradaTodo(int indice) {
        for (int i = 0; i < 4 + personalizadas.size(); i++) {

            SeccionSerie s = getEntradaCorrespondiente(i);
            if (indice < s.getDirecciones().size()) {
                s.getDirecciones().remove(indice);
                break;
            }
            indice -= s.getDirecciones().size();

        }

    }

    public SeccionSerie getClasicas() {
        return Clasicas;
    }

    public void setClasicas(SeccionSerie Clasicas) {
        this.Clasicas = Clasicas;
    }

    public SeccionSerie getTX() {
        return TX;
    }

    public void setTX(SeccionSerie TX) {
        this.TX = TX;
    }

    public SeccionSerie getDobladas() {
        return Dobladas;
    }

    public void setDobladas(SeccionSerie Dobladas) {
        this.Dobladas = Dobladas;
    }

    public SeccionSerie getTodo() {
        return Todo;
    }

    public void setTodo(SeccionSerie Todo) {
        this.Todo = Todo;
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
    public void addAll(SeccionesEntrada s) {
        Clasicas.getDirecciones().addAll(s.Clasicas.getDirecciones());
        Dobladas.getDirecciones().addAll(s.Dobladas.getDirecciones());
        Finalizadas.getDirecciones().addAll(s.Finalizadas.getDirecciones());
        TX.getDirecciones().addAll(s.TX.getDirecciones());
        Todo.getDirecciones().addAll(s.Todo.getDirecciones());
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
        LinkedList<Direccion> d = TX.getDirecciones();
        a += "\nSeguidos:" + "\n";
        for (int i = 0; i < d.size(); i++) {
            a += d.get(i).getF().toString() + "\n";
        }
        if (d.isEmpty()) {
            a += "[ sin direcciones ]" + "\n";
        }
        d = Clasicas.getDirecciones();
        a += "\nQueTengo:" + "\n";
        for (int i = 0; i < d.size(); i++) {
            a += d.get(i).getF().toString() + "\n";
        }
        if (d.isEmpty()) {
            a += "[ sin direcciones ]" + "\n";
        }
        d = Dobladas.getDirecciones();
        a += "\nEnEspera:" + "\n";
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
                a += personalizadas.get(i) + "\n";
            }

        }
        return a;
    }

}
