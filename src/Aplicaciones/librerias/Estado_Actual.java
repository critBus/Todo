/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.librerias;

import Utiles.MetodosUtiles.Archivo;
import Utiles.MetodosUtiles.MetodosUtiles;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class Estado_Actual implements Serializable {

    // private LinkedList<File> direccionesExactas, direccionesMultiples;
    //  private LinkedList<Biblioteca> bibliotecas;
    private interface Forma_Tabla extends Serializable {

        public int sise();

        public Object[][] getTabla();

        public int ColsSise();

        public String[] getTitulos();

        public Class[] getClassTabla();

        public boolean[] canEdit();

        public boolean isEmpty();
    }

    private class Tabla_Proyectos implements Forma_Tabla, Serializable {

        private LinkedList<Boolean> selecionados;
        private LinkedList<Biblioteca> bibliotecas;

        public Tabla_Proyectos() {
            this(new LinkedList<Boolean>(), new LinkedList<Biblioteca>());
        }

        public Tabla_Proyectos(LinkedList<Boolean> selecionados, LinkedList<Biblioteca> bibliotecas) {
            this.selecionados = selecionados;
            this.bibliotecas = bibliotecas;
        }

        public void actualizar() throws FileNotFoundException {
            LinkedList<Biblioteca> bibliotecasNuevas = new LinkedList<>();
            //  LinkedList<Boolean> selecionadosNuevos = new LinkedList<>();
            for (int i = 0; i < tabla_Direcciones.getDireccionesExactas().size(); i++) {
                if (tabla_Direcciones.getSelecionadosExacatas().get(i)) {
                    Biblioteca b = Librerias.biblioteca(tabla_Direcciones.getDireccionesExactas().get(i));
                    if (b != null) {
                        bibliotecasNuevas.add(b);
                        // selecionadosNuevos.add(true);
                    }
                }
            }
            for (int i = 0; i < tabla_Direcciones.getDireccionesMultiples().size(); i++) {
                if (tabla_Direcciones.getSelecionadosMultiples().get(i)) {
                    Biblioteca b[] = Librerias.bibliotecaMultiples(tabla_Direcciones.getDireccionesMultiples().get(i));
                    if (b != null) {
                        for (int j = 0; j < b.length; j++) {
                            bibliotecasNuevas.add(b[j]);
                            //selecionadosNuevos.add(true);
                        }
                    }
                }
            }
            For1:
            for (int i = 0; i < bibliotecas.size(); i++) {
                for (int j = 0; j < bibliotecasNuevas.size(); j++) {
                    if (MetodosUtiles.igualesC(bibliotecas.get(i), bibliotecasNuevas.get(j))) {
                        bibliotecasNuevas.remove(j);
                        //selecionadosNuevos.remove(j);
                        continue For1;
                    }
                }
                selecionados.remove(i);
                bibliotecas.remove(i--);

            }
            //  bibliotecas = new LinkedList<>(bibliotecasNuevas);
            for (int i = 0; i < bibliotecasNuevas.size(); i++) {
                bibliotecas.add(bibliotecasNuevas.get(i));
                selecionados.add(true);
            }

            tabla_Biblioteca.actualizar();
        }

        public boolean setSelcionado(int indice, boolean selecionado) {
            if (selecionado != selecionados.get(indice)) {
                selecionados.set(indice, selecionado);
                tabla_Biblioteca.actualizar();
                return true;
            }
            return false;

        }

        public LinkedList<Biblioteca> getBibliotecasSelecionadas() {
            LinkedList<Biblioteca> bibliotecasSelecionadas = new LinkedList<Biblioteca>();
            for (int i = 0; i < bibliotecas.size(); i++) {
                if (selecionados.get(i)) {
                    bibliotecasSelecionadas.add(bibliotecas.get(i));
                }
            }
            return bibliotecasSelecionadas;
        }

        @Override
        public int sise() {
            return selecionados.size();
        }

        @Override
        public Object[][] getTabla() {
            Object res[][] = new Object[sise() == 0 ? 1 : sise()][ColsSise()];
            for (int i = 0; i < selecionados.size(); i++) {
                res[i][0] = selecionados.get(i);
                res[i][1] = bibliotecas.get(i).getNombreProyecto();
                res[i][2] = bibliotecas.get(i).getNombreCarpeta();
            }
            // System.out.println("res f="+res.length+" c="+(res.length>0?res[0].length:"0"));
            return res;
        }

        @Override
        public int ColsSise() {
            return 3;
        }

        @Override
        public String[] getTitulos() {
            return new String[]{"Selec", "Proyectos", "Carpetas"};
        }

        @Override
        public Class[] getClassTabla() {
            return new Class[]{
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
            };
        }

        @Override
        public boolean[] canEdit() {
            return new boolean[]{
                true, false, false
            };
        }

        @Override
        public boolean isEmpty() {
            return bibliotecas.isEmpty();
        }
    }

    private class Tabla_Biblioteca implements Forma_Tabla, Serializable {

        private LinkedList<Biblioteca> bibliotecas;
        private LinkedList<Integer> superiores;
        private LinkedList<Integer> inferiores;
        private LinkedList<Integer> iguales;
        private LinkedList<Integer> distintos;

        public Tabla_Biblioteca() {
            this(new LinkedList<Biblioteca>());
        }

        public Tabla_Biblioteca(LinkedList<Biblioteca> bibliotecas) {
            inicializar(bibliotecas);
        }

        public void inicializar(LinkedList<Biblioteca> bibliotecas) {
            this.bibliotecas = bibliotecas;
            if (!bibliotecas.isEmpty()) {
                Librerias.ordenarBibliotecas(bibliotecas);
                Biblioteca B[] = bibliotecas.toArray(new Biblioteca[]{});
                superiores = Librerias.clasesConVersionesSuperioresInteger(B, 0);
                inferiores = Librerias.clasesConVersionesInferioresInteger(B, 0);
                iguales = Librerias.clasesConVersionesIgualesInteger(B, 0);
                distintos = Librerias.clasesConVersionesDistintasInteger(B, 0);
            } else {
                superiores = new LinkedList<Integer>();
                inferiores = new LinkedList<Integer>();
                iguales = new LinkedList<Integer>();
                distintos = new LinkedList<Integer>();
            }
        }

        public LinkedList<Biblioteca> getBibliotecas() {
            return bibliotecas;
        }

        public LinkedList<Integer> getSuperiores() {
            return superiores;
        }

        public LinkedList<Integer> getInferiores() {
            return inferiores;
        }

        public LinkedList<Integer> getIguales() {
            return iguales;
        }

        public void actualizar() {
            inicializar(tabla_Proyectos.getBibliotecasSelecionadas());
        }

        @Override
        public int sise() {
            int sise = 0;
            if (!bibliotecas.isEmpty()) {
                sise += bibliotecas.get(0).sise() + 5;
                if (!superiores.isEmpty()) {
                    sise += superiores.size() + 2;
                }
                if (!inferiores.isEmpty()) {
                    sise += inferiores.size() + 2;
                }
                if (!iguales.isEmpty()) {
                    sise += iguales.size() + 2;
                }
                if (!distintos.isEmpty()) {
                    sise += distintos.size() + 2;
                }
            }

            return sise;
        }

        @Override
        public Object[][] getTabla() {
            Object res[][] = new Object[sise() == 0 ? 1 : sise()][ColsSise()];
            if (ColsSise() > 0) {
                for (int i = 0; i < res[0].length; i++) {
                    int indiceFila = 0, indiceColumna = i - 1;
                    if (i == 0) {
                        res[indiceFila++][0] = "Proyecto";
                        res[indiceFila++][0] = "Carpeta";
                        res[indiceFila++][0] = "Version";
                        if (!superiores.isEmpty()) {
                            res[indiceFila++][i] = "";
                            res[indiceFila++][i] = "Superiores";
                            for (int j = 0; j < superiores.size(); j++) {
                                res[indiceFila++][i] = Nombre_Clase.values()[superiores.get(j)].getNombre();
                            }
                        }
                        if (!inferiores.isEmpty()) {
                            res[indiceFila++][i] = "";
                            res[indiceFila++][i] = "Inferiores";
                            for (int j = 0; j < inferiores.size(); j++) {
                                res[indiceFila++][i] = Nombre_Clase.values()[inferiores.get(j)].getNombre();
                            }
                        }
                        if (!iguales.isEmpty()) {
                            res[indiceFila++][i] = "";
                            res[indiceFila++][i] = "Iguales";
                            for (int j = 0; j < iguales.size(); j++) {
                                res[indiceFila++][i] = Nombre_Clase.values()[iguales.get(j)].getNombre();
                            }
                        }
                        if (!distintos.isEmpty()) {
                            res[indiceFila++][i] = "";
                            res[indiceFila++][i] = "Distintos";
                            for (int j = 0; j < distintos.size(); j++) {
                                res[indiceFila++][i] = Nombre_Clase.values()[distintos.get(j)].getNombre();
                            }
                        }
                        res[indiceFila++][i] = "";
                        res[indiceFila++][i] = "Clases";
                        for (int j = 0; j < Nombre_Clase.values().length; j++) {
                            res[indiceFila++][i] = Nombre_Clase.values()[j].getNombre();
                        }
                        continue;
                    }

//                    res[0][i] = bibliotecas.get(i).getNombreProyecto();
//                    res[1][i] = "";
                    res[indiceFila++][i] = bibliotecas.get(indiceColumna).getNombreProyecto();
                    res[indiceFila++][i] = bibliotecas.get(indiceColumna).getNombreCarpeta();
                    res[indiceFila++][i] = bibliotecas.get(indiceColumna).getVersionTotal();
                    if (!superiores.isEmpty()) {
                        res[indiceFila++][i] = "";
                        res[indiceFila++][i] = "++++++";
                        for (int j = 0; j < superiores.size(); j++) {
                            res[indiceFila++][i] = bibliotecas.get(indiceColumna).getVersion(superiores.get(j));
                        }
                    }
                    if (!inferiores.isEmpty()) {
                        res[indiceFila++][i] = "";
                        res[indiceFila++][i] = "-------";
                        for (int j = 0; j < inferiores.size(); j++) {
                            res[indiceFila++][i] = bibliotecas.get(indiceColumna).getVersion(inferiores.get(j));
                        }
                    }
                    if (!iguales.isEmpty()) {
                        res[indiceFila++][i] = "";
                        res[indiceFila++][i] = "= = = = =";
                        for (int j = 0; j < iguales.size(); j++) {
                            res[indiceFila++][i] = bibliotecas.get(indiceColumna).getVersion(iguales.get(j));
                        }
                    }
                    if (!distintos.isEmpty()) {
                        res[indiceFila++][i] = "";
                        res[indiceFila++][i] = "/=/=/=/=/=/";
                        for (int j = 0; j < distintos.size(); j++) {
                            res[indiceFila++][i] = bibliotecas.get(indiceColumna).getVersion(distintos.get(j));
                        }
                    }
                    res[indiceFila++][i] = "";
                    res[indiceFila++][i] = "******";
                    for (int j = 0; j < bibliotecas.get(indiceColumna).sise(); j++) {
                        res[indiceFila++][i] = bibliotecas.get(indiceColumna).getVersion(j);
                    }
                }
            }
            // System.out.println("res bi f=" + res.length + " c=" + (res.length > 0 ? res[0].length : "0"));
            return res;
        }

        @Override
        public int ColsSise() {
            return bibliotecas.isEmpty() ? 0 : bibliotecas.size() + 1;
        }

        @Override
        public String[] getTitulos() {
            String titulos[] = new String[ColsSise()];
            for (int i = 0; i < ColsSise(); i++) {
                titulos[i] = i == 0 ? "*******" : bibliotecas.get(i - 1).getNombreProyecto();

            }
            return titulos;
        }

        @Override
        public Class[] getClassTabla() {
            Class[] types = new Class[ColsSise()];
            Arrays.fill(types, java.lang.String.class);
            return types;
        }

        @Override
        public boolean[] canEdit() {
            boolean[] canEdit = new boolean[ColsSise()];
            Arrays.fill(canEdit, true);
            return canEdit;
        }

        @Override
        public boolean isEmpty() {
            return bibliotecas.isEmpty();
        }
    }

    private class Tabla_Direcciones implements Forma_Tabla, Serializable {

        private LinkedList<Boolean> selecionadosExacatas, selecionadosMultiples;
        private LinkedList<File> direccionesExactas, direccionesMultiples;

        public Tabla_Direcciones() {
            this(new LinkedList<Boolean>(), new LinkedList<Boolean>(), new LinkedList<File>(), new LinkedList<File>());
        }

        public Tabla_Direcciones(LinkedList<Boolean> selecionadosExacatas, LinkedList<Boolean> selecionadosMultiples, LinkedList<File> direccionesExactas, LinkedList<File> direccionesMultiples) {
            inicializar(selecionadosExacatas, selecionadosMultiples, direccionesExactas, direccionesMultiples);
        }

        public void inicializar() {
            inicializar(new LinkedList<Boolean>(), new LinkedList<Boolean>(), new LinkedList<File>(), new LinkedList<File>());
        }

        public void inicializar(LinkedList<Boolean> selecionadosExacatas, LinkedList<Boolean> selecionadosMultiples, LinkedList<File> direccionesExactas, LinkedList<File> direccionesMultiples) {
            this.selecionadosExacatas = selecionadosExacatas;
            this.selecionadosMultiples = selecionadosMultiples;
            this.direccionesExactas = direccionesExactas;
            this.direccionesMultiples = direccionesMultiples;
        }

        public boolean setSelcionado(int indice, boolean selecionado) throws FileNotFoundException {
            boolean actualizar = false;
            if (indice < selecionadosExacatas.size()) {
                if (selecionado != selecionadosExacatas.get(indice)) {
                    selecionadosExacatas.set(indice, selecionado);
                    actualizar = true;
                }

            } else {
                // System.out.println("indice="+indice);
                indice -= selecionadosExacatas.size();
                if (selecionado != selecionadosMultiples.get(indice)) {
                    selecionadosMultiples.set(indice, selecionado);
                    actualizar = true;
                }
            }
            if (actualizar) {
                actualizarTablas();
            }
            return actualizar;
        }

        public void eliminarDirecionesSelecionadas() throws FileNotFoundException {
            // LinkedList<Integer> indicesSelecionados = new LinkedList<Integer>();
            for (int i = 0; i < selecionadosExacatas.size(); i++) {
                if (selecionadosExacatas.get(i)) {
                    // indicesSelecionados.add(i);
                    direccionesExactas.remove(i);
                    selecionadosExacatas.remove(i--);
                }
            }
            for (int i = 0; i < selecionadosMultiples.size(); i++) {
                if (selecionadosMultiples.get(i)) {
                    //indicesSelecionados.add(i + selecionadosMultiples.size());
                    direccionesMultiples.remove(i);
                    selecionadosMultiples.remove(i--);
                }
            }
            actualizarTablas();
        }

        public boolean isEmpty() {
            return direccionesExactas.isEmpty() && direccionesMultiples.isEmpty();
        }

        public void eliminarDireciones(int... I) throws FileNotFoundException {
            Arrays.sort(I);
            int cantidadEliminados = 0;
            for (int i = 0; i < I.length; i++) {
                int indice = I[i] - cantidadEliminados++;
                if (indice < selecionadosExacatas.size()) {
                    direccionesExactas.remove(indice);
                    selecionadosExacatas.remove(indice);
                } else {
                    direccionesMultiples.remove(indice);
                    selecionadosMultiples.remove(indice);
                }
            }
            actualizarTablas();
        }

        public void clear() throws FileNotFoundException {
            inicializar();
            actualizarTablas();
        }

        public void addDireccionExacta(File f) throws FileNotFoundException {
            addDireccionExacta(f, true);
        }

        public void addDireccionExacta(File f, boolean selecionado) throws FileNotFoundException {
            System.out.println("intento");
            if (!Archivo.contieneAFile(direccionesExactas, f)) {

                direccionesExactas.add(f);
                selecionadosExacatas.add(selecionado);
                tabla_Proyectos.actualizar();
                System.out.println("paso");
            }

        }

        public LinkedList<File> getDirecciones() {
            LinkedList<File> direciones = new LinkedList<File>(direccionesExactas);
            direciones.addAll(new LinkedList<File>(direccionesMultiples));
            return direciones;
        }

        public void addDireccionMultiple(File f, boolean selecionado) throws FileNotFoundException {
            if (!Archivo.contieneAFile(direccionesMultiples, f)) {
                direccionesMultiples.add(f);
                selecionadosMultiples.add(selecionado);
                tabla_Proyectos.actualizar();
            }

        }

        public void addDireccionMultiple(File f) throws FileNotFoundException {
            addDireccionMultiple(f, true);
        }

        public LinkedList<Boolean> getSelecionadosExacatas() {
            return selecionadosExacatas;
        }

        public LinkedList<Boolean> getSelecionadosMultiples() {
            return selecionadosMultiples;
        }

        public LinkedList<File> getDireccionesExactas() {
            return direccionesExactas;
        }

        public LinkedList<File> getDireccionesMultiples() {
            return direccionesMultiples;
        }

        public LinkedList<File> getDireccionesSelecionadasExactas() {
            LinkedList<File> direccionesSelecionadasExactas = new LinkedList<File>();
            for (int i = 0; i < direccionesExactas.size(); i++) {
                if (selecionadosExacatas.get(i)) {
                    direccionesSelecionadasExactas.add(direccionesExactas.get(i));
                }
            }
            return direccionesSelecionadasExactas;
        }

        public LinkedList<File> getDireccionesSelecionadasMultiples() {
            LinkedList<File> direccionesSelecionadasMultiples = new LinkedList<File>();
            for (int i = 0; i < direccionesMultiples.size(); i++) {
                if (selecionadosMultiples.get(i)) {
                    direccionesSelecionadasMultiples.add(direccionesMultiples.get(i));
                }
            }
            return direccionesSelecionadasMultiples;
        }

        public boolean contieneAlFile(File f) {
            return (Archivo.contieneAFile(direccionesExactas, f) || Archivo.contieneAFile(direccionesMultiples, f));
        }

        @Override
        public int sise() {
            return selecionadosExacatas.size() + selecionadosMultiples.size();
        }

        @Override
        public Object[][] getTabla() {
            Object res[][] = new Object[sise() == 0 ? 1 : sise()][ColsSise()];

            for (int i = 0; i < selecionadosExacatas.size(); i++) {
                res[i][0] = selecionadosExacatas.get(i);
                res[i][1] = direccionesExactas.get(i);
            }
            int indice = selecionadosExacatas.size();
            for (int i = 0; i < selecionadosMultiples.size(); i++) {
                res[i + indice][0] = selecionadosMultiples.get(i);
                res[i + indice][1] = direccionesMultiples.get(i);
            }

            return res;
        }

        @Override
        public int ColsSise() {
            return 2;
        }

        @Override
        public String[] getTitulos() {
            return new String[]{"Selec", "Direcciones"};
        }

        @Override
        public Class[] getClassTabla() {
            return new Class[]{
                java.lang.Boolean.class, java.lang.String.class
            };
        }

        @Override
        public boolean[] canEdit() {
            return new boolean[]{
                true, false
            };
        }
    }

    private Tabla_Proyectos tabla_Proyectos;
    private Tabla_Biblioteca tabla_Biblioteca;
    private Tabla_Direcciones tabla_Direcciones;

    public Estado_Actual() {
        tabla_Biblioteca = new Tabla_Biblioteca();
        tabla_Direcciones = new Tabla_Direcciones();
        tabla_Proyectos = new Tabla_Proyectos();

    }

    public void direccionExacta(File f) throws FileNotFoundException {
        tabla_Direcciones.addDireccionExacta(f);
    }

    public void direccionMultiple(File f) throws FileNotFoundException {
        tabla_Direcciones.addDireccionMultiple(f);
    }

    public Object[][] tablaBiblioteca() {
        return tabla_Biblioteca.getTabla();
    }

    public Object[][] tablaDirecciones() {
        return tabla_Direcciones.getTabla();
    }

    public Object[][] tablaProyectos() {
        return tabla_Proyectos.getTabla();
    }

    public String[] titulosProyectos() {
        return tabla_Proyectos.getTitulos();
    }

    public String[] titulosBiblioteca() {
        return tabla_Biblioteca.getTitulos();
    }

    public String[] titulosDirecciones() {
        return tabla_Direcciones.getTitulos();
    }

    public int siseProyectos() {
        return tabla_Proyectos.sise();
    }

    public int siseBiblioteca() {
        return tabla_Biblioteca.sise();
    }

    public int siseDirecciones() {
        return tabla_Direcciones.sise();
    }

    public Class[] ClassProyectos() {
        return tabla_Proyectos.getClassTabla();
    }

    public Class[] ClassBiblioteca() {
        return tabla_Biblioteca.getClassTabla();
    }

    public Class[] ClassDirecciones() {
        return tabla_Direcciones.getClassTabla();
    }

    public boolean[] canEditProyectos() {
        return tabla_Proyectos.canEdit();
    }

    public boolean[] canEditBiblioteca() {
        return tabla_Biblioteca.canEdit();
    }

    public boolean[] canEditDirecciones() {
        return tabla_Direcciones.canEdit();
    }

    public void actualizarTablas() throws FileNotFoundException {
        tabla_Proyectos.actualizar();
    }

    public boolean setSelecionadoDirecciones(int indice, boolean selecionado) throws FileNotFoundException {
        return tabla_Direcciones.setSelcionado(indice, selecionado);
    }

    public boolean setSelecionadoProyectos(int indice, boolean selecionado) {
        return tabla_Proyectos.setSelcionado(indice, selecionado);
    }

    public boolean contieneAlFileProyectosDirecciones(File f) {
        return tabla_Direcciones.contieneAlFile(f);
    }

    public LinkedList<File> getDirecciones() {
        return tabla_Direcciones.getDirecciones();
    }

    public void eliminarDireciones(int... I) throws FileNotFoundException {
        tabla_Direcciones.eliminarDireciones(I);
    }

    public void eliminarDirecionesSelecionadas() throws FileNotFoundException {
        tabla_Direcciones.eliminarDirecionesSelecionadas();
    }

    public void clear() throws FileNotFoundException {
        tabla_Direcciones.clear();
    }

    public boolean isEmptyDirecciones() {
        return tabla_Direcciones.isEmpty();
    }

    public boolean isEmptyProyectos() {
        return tabla_Proyectos.isEmpty();
    }
//    public Tabla_Proyectos getTabla_Proyectos() {
//        return tabla_Proyectos;
//    }
//
//    public Tabla_Biblioteca getTabla_Biblioteca() {
//        return tabla_Biblioteca;
//    }
//
//    public Tabla_Direcciones getTabla_Direcciones() {
//        return tabla_Direcciones;
//    }
}
