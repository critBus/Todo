/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.librerias;

import Utiles.MetodosUtiles.Archivo;
import static Utiles.MetodosUtiles.Archivo.crearArchivo;
import Utiles.MetodosUtiles.Arreglos;
import Utiles.MetodosUtiles.Visual;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author Rene
 */
public class Librerias {

    public static Ventana_Principal ventana_Principal;
    public static Ventana_Administrador ventana_Administrador;
    public static final String DIRECCION_ULTIMO_ESTADO = "Data/ultimo estado.est", DIRECCION_ADMINISTRADOR_DIRECCIONES = "Data/administrador de direcciones.adm";
    public static JFrame[] todasLasVentanas;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            new File("Data").mkdir();
//            if (!new File(DIRECCION_ULTIMO_ESTADO).exists()) {
            crearArchivo(DIRECCION_ULTIMO_ESTADO, new Estado_Actual());
//            }
            if (!new File(DIRECCION_ADMINISTRADOR_DIRECCIONES).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_DIRECCIONES, new Admininistrador_De_Guardado<File>());
            }
            ventana_Principal = new Ventana_Principal((Estado_Actual) Archivo.cargarArchivo(DIRECCION_ULTIMO_ESTADO));

            //  ventana_Principal = (Ventana_Principal) Archivo.cargarArchivo(DIRECCION_ULTIMO_ESTADO);
            ventana_Principal.setVisible(true);
            Visual.estiloNimbus();
        } catch (Exception ex) {
            Visual.responerException(ex);
        }

//        Biblioteca B[] = bibliotecaMultiples("D:\\Rene\\Proyectos\\java\\Nuevo");
//        ordenarBibliotecas(B);
//        int Ig[] = clasesConVersionesSuperiores(B, 0);
//        int Il[] = clasesConVersionesInferiores(B, 0);
//        int Ie[] = clasesConVersionesIguales(B, 0);
//        for (Biblioteca b : B) {
//            System.out.println(b.getNombreProyecto() + " total= " + b.getVersionTotal());
//            for (int i = 0; i < Ig.length; i++) {
//                System.out.println("superior es " + b.getNombreClase(Ig[i]) + " con " + b.getVersion(Ig[i]) );
//            }
//            System.out.println("");
//            for (int i = 0; i < Il.length; i++) {
//                System.out.println("inferior es " + b.getNombreClase(Il[i]) + " con " + b.getVersion(Il[i]) );
//            }
//            System.out.println("");
//            for (int i = 0; i < Ie.length; i++) {
//                System.out.println("igual es " + b.getNombreClase(Ie[i]) + " con " + b.getVersion(Ie[i]) );
//            }
//            System.out.println("");
//            for (Nombre_Clase a : Nombre_Clase.values()) {
//                System.out.println(a + " " + b.getVersion(a));
//            }
//            System.out.println("********");
//        }
//
////        Biblioteca b = biblioteca("D:\\Rene\\Proyectos\\java\\Nuevo\\Todo");
////        System.out.println(b.getNombreProyecto());
////        for (Nombre_Clase a : Nombre_Clase.values()) {
////            System.out.println(a + " " + b.getVersion(a));
////        }
//         nombreClases();
    }

    public static void ordenarBibliotecas(Biblioteca B[]) {
        Collections.sort(Arrays.asList(B), Collections.reverseOrder());
    }

    public static void ordenarBibliotecas(List B) {
        Collections.sort(B, Collections.reverseOrder());
    }

    public static int[] clasesConVersionesSuperiores(Biblioteca B[], int indiceAComparar) {
        return clasesConVersiones(B, indiceAComparar, true, false, false);
    }

    public static int[] clasesConVersionesInferiores(Biblioteca B[], int indiceAComparar) {
        return clasesConVersiones(B, indiceAComparar, false, true, false);
    }

    public static int[] clasesConVersionesIguales(Biblioteca B[], int indiceAComparar) {
        return clasesConVersiones(B, indiceAComparar, false, false, true);
    }

    private static int[] clasesConVersiones(Biblioteca B[], int indiceAComparar, boolean superiores, boolean inferiores, boolean iguales) {
        LinkedList<Integer> I = clasesConVersionesInteger(B, indiceAComparar, superiores, inferiores, iguales);
        int I2[] = new int[I.size()];
        for (int i = 0; i < I2.length; i++) {
            I2[i] = I.get(i);
        }
        return I2;
    }

    public static LinkedList<Integer> clasesConVersionesSuperioresInteger(Biblioteca B[], int indiceAComparar) {
        return clasesConVersionesInteger(B, indiceAComparar, true, false, false);
    }

    public static LinkedList<Integer> clasesConVersionesInferioresInteger(Biblioteca B[], int indiceAComparar) {
        return clasesConVersionesInteger(B, indiceAComparar, false, true, false);
    }

    public static LinkedList<Integer> clasesConVersionesIgualesInteger(Biblioteca B[], int indiceAComparar) {
        return clasesConVersionesInteger(B, indiceAComparar, false, false, true);
    }

    public static LinkedList<Integer> clasesConVersionesDistintasInteger(Biblioteca B[], int indiceAComparar) {
        return clasesConVersionesInteger(B, indiceAComparar, false, false, false);
    }

    private static LinkedList<Integer> clasesConVersionesInteger(Biblioteca B[], int indiceAComparar, boolean superiores, boolean inferiores, boolean iguales) {
        LinkedList<Integer> I = new LinkedList<>();
        if (B.length > 1) {
            For1:
            for (int i = 0; i < B[indiceAComparar].sise(); i++) {
                for (int j = 0; j < B.length; j++) {//((!superiores&&!inferiores&&!iguales)&&B[indiceAComparar].getVersion(i) == B[j].getVersion(i))||
                    if (!superiores && !inferiores && !iguales) {
                        if (B[indiceAComparar].getVersion(i) != B[j].getVersion(i)) {
                            I.add(i);
                            continue For1;
                        }
                    } else {
                        if (indiceAComparar != j && ((superiores && B[indiceAComparar].getVersion(i) <= B[j].getVersion(i)) || (inferiores && B[indiceAComparar].getVersion(i) >= B[j].getVersion(i)) || (iguales && B[indiceAComparar].getVersion(i) != B[j].getVersion(i)))) {
                            continue For1;
                        }
                    }

                }
                if (superiores || inferiores || iguales) {
                    I.add(i);
                }

            }
        }

        return I;
    }

    public static Biblioteca[] bibliotecaMultiples(String direccion) throws FileNotFoundException {
        return bibliotecaMultiples(new File(direccion));
    }

    public static Biblioteca[] bibliotecaMultiples(File f) throws FileNotFoundException {
        //   System.out.println("** " + f.getName());
        LinkedList<Biblioteca> B = new LinkedList<>();
        if (f.isDirectory()) {
            String contenido[] = f.list();
            //   System.out.println("sssssssssss");
            for (String s : contenido) {
                //    System.out.println("pp "+s);
                File c2 = new File(f + "/" + s);
                // System.out.println("c2  "+c2.getName());
                if (c2.isDirectory()) {
                    if (c2.getName().equals("Paquetes Independientes")) {
                        c2 = new File(c2 + "/Paquetes");
                    }
                    Biblioteca actual = biblioteca(c2);
                    if (actual != null) {
                        // System.out.println("actu= "+actual.getNombreProyecto()+"  "+actual.getNombreCarpeta());
                        //  System.out.println("add  dddddddddddddd");
                        B.add(actual);
                    }
                }
            }

            return B.isEmpty() ? null : B.toArray(new Biblioteca[]{});
        }
        //return B.toArray(new Biblioteca[]{});
        return null;
    }

    public static Biblioteca biblioteca(String direccion) throws FileNotFoundException {
        return biblioteca(new File(direccion));
    }

    public static boolean esDireccionExacta(File f) {
        //System.out.println("f= "+new File(f + "\\src\\Utiles\\MetodosUtiles"));
        return new File(f + "\\src\\Utiles\\MetodosUtiles").exists();
    }

    public static Biblioteca biblioteca(File f) throws FileNotFoundException {
        // LinkedList<Biblioteca> B=new LinkedList<>();
        // Biblioteca B[] = new Biblioteca[Biblioteca.nombresDeClases.length];
        // System.out.println("-- " + f.getName());
//        if (f.getName().equals("Todo")) {
//            System.out.println("sssss");
//        }
        double V[] = new double[Biblioteca.nombresDeClases.length];
        Arrays.fill(V, -1);
//        System.out.println(f);
        For1:
        for (int i = 0; i < V.length; i++) { //System.out.println("i="+i);
            //File f2 = new File(f + "\\src\\Utiles\\MetodosUtiles\\" + Biblioteca.nombresDeClases[i].getNombre() + ".java");
            File f2 = new File(f + "\\src\\Utiles" + Biblioteca.nombresDeClases[i].getDireccionInterna() + "\\" + Biblioteca.nombresDeClases[i].getNombre() + ".java");

            if (!f2.exists()) {//System.out.println("no");
//                if (f.toString().equalsIgnoreCase("D:\\Rene\\Proyectos\\java\\Nuevo\\Todo")) {
//                    System.out.println("no existe=" + f2.toString());
//                }
                return null;
            }
            Scanner s = new Scanner(f2);
            String a = "";
            try {
                while ((a = s.next()) != null) {
                    //  System.out.println("a="+a);
                    if (a.equals(Biblioteca.nombreVersion)) {
                        // System.out.println("d="+d);
                        V[i] = Double.parseDouble(s.next());
                        //  V[i] = s.nextDouble();
                        s.close();
                        continue For1;
                    }
                }
            } catch (Exception e) {
//                if (f.toString().equalsIgnoreCase("D:\\Rene\\Proyectos\\java\\Nuevo\\Todo")) {
//                    System.out.println("no tiene=" + f2.toString());
//                }
            } finally {
                s.close();
            }
            return null;
        }
        return V[V.length - 1] != -1 ? new Biblioteca(V, f) : null;
    }

    public static void nombreClases() {

        File f = new File("D:\\Rene\\Proyectos\\java\\Nuevo\\Todo\\src\\Utiles\\MetodosUtiles");
        if (f.isDirectory()) {
            final String direccionInterna0 = "\\\\MetodosUtiles", direccionInterna1 = "\\\\ClasesUtiles";
            String contenido[] = f.list();
            contenido = Arreglos.ampliarArregloObject(contenido, 2);
            contenido[contenido.length - 2] = "D:\\Rene\\Proyectos\\java\\Nuevo\\Librerias\\src\\Utiles\\ClasesUtiles\\Ventana_Administrador.java";
            contenido[contenido.length - 1] = "D:\\Rene\\Proyectos\\java\\Nuevo\\Librerias\\src\\Utiles\\ClasesUtiles\\Admininistrador_De_Guardado.java";
            for (int i = 0; i < contenido.length; i++) {
                File c2 = new File(f + "/" + contenido[i]);
                String nombreOriginal = getNombre(c2), nombreModificado = nombreOriginal;
                for (int j = 1; j < nombreModificado.length(); j++) {
                    if (esMayuscula(nombreModificado.charAt(j)) && nombreModificado.charAt(j - 1) != '_') {
                        nombreModificado = nombreModificado.substring(0, j) + "_" + nombreModificado.substring(j);
                    }
                }

                System.out.print(nombreModificado.toUpperCase() + "(\"" + nombreOriginal + "\",\"" + (i < contenido.length - 2 ? direccionInterna0 : direccionInterna1) + "\"," + i + "," + i + "),");
            }

        }
        //ARCHIVO("Archivo",0),ARREGLOS("Arreglos",1),CONVERSIONES("Conversiones",2),METODOS_PARA_MANIPULAR_MIS_CLASES("MetodosParaManipularMisClases",3),METODOS_UTILES("MetodosUtiles",4),OPERACIONES("Operaciones",5),TEMPUS("Tempus",6),VISUAL("Visual",7);
    }

    public static boolean esMayuscula(char a) {
        return a >= 'A' && a <= 'Z';
    }

    public static String getNombre(File f) {
        return f.getName().substring(0, f.getName().lastIndexOf("."));
    }
    //public static 
}
