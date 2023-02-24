/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.Contenido_Carpeta;

import Utiles.ClasesUtiles.Tablas.Tabla;
import Utiles.MetodosUtiles.Archivo;
import Utiles.MetodosUtiles.Arreglos;
import Utiles.MetodosUtiles.MetodosUtiles;
import static Utiles.MetodosUtiles.MetodosUtiles.or;
import java.io.File;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class Carpetas implements Serializable {
    
    LinkedList<Archivo_Existente> archivos;
    
    public Carpetas(File f, String invalidos[]) {
        this(new LinkedList<Archivo_Existente>());
        if (f.exists() && f.isDirectory()) {
            File F[] = f.listFiles();
            for (int i = 0; i < F.length; i++) {
                if (or(F[i].getName(), invalidos)) {
                    continue;
                }
                archivos.add(new Archivo_Existente(F[i]));
            }
        }
        
    }
    
    public Carpetas(File... Fl) {
        this(new LinkedList<Archivo_Existente>());
        for (File f : Fl) {
            if (f.exists() && f.isDirectory()) {
                File F[] = f.listFiles();
                for (int i = 0; i < F.length; i++) {
                    archivos.add(new Archivo_Existente(F[i]));
                }
            }
        }
        
    }
    
    public Carpetas(LinkedList<Archivo_Existente> archivos) {
        this.archivos = archivos;
    }
    
    public void ordenarTamaño() {
        ordenar(formaDeComparar.TAMAÑO);
    }
    
    public void setAscendente(boolean ascendente) {
        for (int i = 0; i < archivos.size(); i++) {
            archivos.get(i).setAscendente(ascendente);
        }
    }
    
    public void ordenarTamañoPorCapitulos() {
        ordenar(formaDeComparar.TAMAÑO_POR_CAPITULO);
    }
    
    public void ordenarCantidadDeVideos() {
        ordenar(formaDeComparar.CANTIDAD_DE_CAPITULOS);
    }
    
    public void ordenarNombre() {
        ordenar(formaDeComparar.NOMBRE);
    }
    
    private void ordenar(formaDeComparar form) {
        for (int i = 0; i < archivos.size(); i++) {
            archivos.get(i).setForma(form);
        }
        Collections.sort(archivos);
    }
    
    private String[][] getMatrisStringDeTabla() {
//        String A[][] = new String[archivos.size() + 1][6];
//
//        for (int i = 0; i < A.length; i++) {
//            if (i == 0) {
//                A[i][0] = "Nombre";
//                A[i][1] = "Tamaño";
//                A[i][2] = "Videos";
//                A[i][3] = "Carpeta";
//                A[i][4] = "Disco";
//                A[i][5] = "Direccion";
//                continue;
//            }
//
//            A[i][0] = archivos.get(i - 1).getDireccion().getName();
//            A[i][1] = archivos.get(i - 1).getTamañoString();
//            A[i][2] = archivos.get(i - 1).getCantidaDeVideos() + "";
//            A[i][3] = archivos.get(i - 1).getDireccion().getParentFile().getName();
//            A[i][4] = archivos.get(i - 1).getDireccion().toString().substring(0, 3);
//            A[i][5] = archivos.get(i - 1).getDireccion().toString();
//
//        }
//        return A;
        String A[][] = new String[archivos.size() + 1][7];
        
        for (int i = 0; i < A.length; i++) {
            if (i == 0) {
                A[i][0] = "Nombre";
                A[i][1] = "Tamaño";
                A[i][2] = "Videos";
                A[i][3] = "Tam/Cap";
                A[i][4] = "Carpeta";
                A[i][5] = "Disco";
                A[i][6] = "Direccion";
                continue;
            }
            
            A[i][0] = archivos.get(i - 1).getDireccion().getName();
            A[i][1] = archivos.get(i - 1).getTamañoString();
            A[i][2] = archivos.get(i - 1).getCantidaDeVideos() + "";
            A[i][3] = archivos.get(i - 1).getTamañoPorCapituloString() + "";
            A[i][4] = archivos.get(i - 1).getDireccion().getParentFile().getName();
            A[i][5] = archivos.get(i - 1).getDireccion().toString().substring(0, 3);
            A[i][6] = archivos.get(i - 1).getDireccion().toString();
            
        }
        return A;
    }
    
    public String[] getLineasTXTTabla() {
        String A[][] = getMatrisStringDeTabla();
        String[] a = new String[A.length];
        int max[] = Arreglos.arregloFill(0, archivos.size());
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < 7; j++) {
                if (A[i][j].length() > max[j]) {
                    max[j] = A[i][j].length();
                }
            }
        }
        for (int i = 0; i < A.length; i++) {
            a[i] = String.format("%-" + (max[0]) + "s %" + (max[1]) + "s %" + (max[2]) + "s %" + (max[3]) + "s %-" + (max[4]) + "s %" + (max[5]) + "s %-" + (max[6]) + "s", A[i][0], A[i][1], A[i][2], A[i][3], A[i][4], A[i][5], A[i][6]);
        }
        return a;
    }
    
    public String[] getLineasSoloContenido() {
        String A[] = new String[archivos.size()];
        for (int i = 0; i < A.length; i++) {
            A[i] = archivos.get(i).getDireccion().getName();
        }
        return A;
    }
    
    public boolean isEmpty() {
        return archivos.isEmpty();
    }
    
    public Tabla getTabla() {
//        String A[][] = new String[archivos.size() + 1][6];
//        for (int i = 0; i < A.length; i++) {
//            if (i == 0) {
//                A[i][0] = "Nombre";
//                A[i][1] = "Tamaño";
//                A[i][2] = "Videos";
//                A[i][3] = "Carpeta";
//                A[i][4] = "Disco";
//                A[i][5] = "Direccion";
//                continue;
//            }
//
//            A[i][0] = archivos.get(i - 1).getDireccion().getName();
//            A[i][1] = archivos.get(i - 1).getTamañoString();
//            A[i][2] = archivos.get(i - 1).getCantidaDeVideos() + "";
//            A[i][3] = archivos.get(i - 1).getDireccion().getParentFile().getName();
//            A[i][4] = archivos.get(i - 1).getDireccion().toString().substring(0, 3);
//            A[i][5] = archivos.get(i - 1).getDireccion().toString();
//
//        }

//        Tabla T = Tabla.crearStringMatriz(getMatrisStringDeTabla());
        return Tabla.crearStringMatriz(getMatrisStringDeTabla(), 7);
    }
//
//    private Object[] getMaxLength(int indice) {
//        int max = 0;
//        String linea[] = new String[archivos.size()];
//        switch (indice) {
//            case 0:
//                //****************
////                for (int i = 0; i < archivos.size(); i++) {
////                    if (archivos.get(i).getNombre().length() > max) {
////                        max = archivos.get(i).getNombre().length();
////                    }
////                    linea[i]=archivos.get(i).getNombre();
////                }
//                break;
//            case 1:
//                max = 10;
//                for (int i = 0; i < archivos.size(); i++) {
//                    linea[i] = archivos.get(i).getTamañoString();
//                }
//                break;
//            case 2:
//                for (int i = 0; i < archivos.size(); i++) {
//                    if ((archivos.get(i).getCantidaDeVideos() + "").length() > max) {
//                        max = (archivos.get(i).getCantidaDeVideos() + "").length();
//                    }
//                    linea[i] = archivos.get(i).getCantidaDeVideos() + "";
//                }
//                break;
//            case 3:
//                break;
//            case 4:
//                break;
//            case 5:
//                break;
//        }
//
//        return new Object[]{linea, max};
//    }

//    public void crearTXT(File f, String nombre) {
//        String lineas[] = new String[archivos.size()];
//        int max = 0;
//        for (int i = 0; i < archivos.size(); i++) {
//            if (archivos.get(i).getNombre().length() > max) {
//                max = archivos.get(i).getNombre().length();
//            }
//        }
//        for (int i = 0; i < archivos.size(); i++) {
//            lineas[i] = String.format("%-" + (max) + "s %" + (10) + "s", archivos.get(i).getNombre(), archivos.get(i).getTamañoString());
//        }
//        Archivo.crearTXT(f, nombre, lineas);
//    }
}
