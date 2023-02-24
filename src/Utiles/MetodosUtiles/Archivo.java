/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.MetodosUtiles;

import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static Utiles.MetodosUtiles.MetodosUtiles.*;
import static Utiles.MetodosUtiles.Tempus.*;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.StringTokenizer;
import Utiles.Exepciones.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utiles.ClasesUtiles.Fichero;
import Utiles.ClasesUtiles.NombreClave;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;
import javax.swing.JTextArea;
//import solitario3.Admininistrador_De_Guardado;

/**
 * Metodos para poder manipular archibos
 *
 * Version 4.2
 *
 * @author Rene
 */
public abstract class Archivo {
// Arrays.toString  ConfiguracionDeVideo cdv cantidadAntesIngnorar[
//detenciones cdv.despuesIngnorar Detuvo ****************************

    public static final char caracteresNoPermitidosNombre[] = {'\\', '/', ':', '*', '?', '\"', '<', '>', '|'};// separacion[] = {' ', '-', '_', '☆', '!', '?', ';', '+', '-', '\'', '~', '.', '(', ')', '[', ']', '{', '}'};
    public static final String separacionString[] = {" ", "-", "_", "☆", "!", "?", ";", "+", "-", "\'", "~", ".", "(", ")", "[", "]", "{", "}", "*", ",", ":", "/"},
            Delimiters = " \t\n\r\f-☆!?;+\'~.()[]{}*,:/";

    public static final String saltarHastaDespuesDe[] = {".com"};

    public static StringTokenizer getTokenizer(String a) {
        return new StringTokenizer(a, Delimiters);
    }

    public static double tamañoArchivo(File f) {
        if (f.exists()) {
            double tam = 0;

            if (f.isDirectory()) {
                File F[] = f.listFiles();
                for (int i = 0; i < F.length; i++) {
                    tam += tamañoArchivo(F[i]);
                }
            } else {
                return f.length();
            }
            return tam;
        }
        return -1;
    }

    public static String tamaño(File f) {
        if (f.exists()) {
            if (f.isFile()) {
                return tamaño(f.length());
            } else {
                return tamaño(tamañoArchivo(f));
            }

        }
        return null;
    }

    public static String tamaño(double n) {
//        double n = N;// b kb mg g
//        //999 b
//        if (n < 1024) {
//            return n + " B";
//        }
////        String[] s=seccionesDeNomero(n);
//        //999 999 kb
//        n /= 1024;
//        if (n < 1024) {
//            return n / 1024 + " KB";
//        }

        for (int i = 0; i < 5; i++) {
            String tipo = "";
            switch (i) {
                case 0:
                    tipo = " B";
                    break;
                case 1:
                    tipo = " KB";
                    break;
                case 2:
                    tipo = " MB";
                    break;
                case 3:
                    tipo = " GB";
                    break;
                case 4:
                    tipo = " TB";
                    break;
            }
//            System.out.println("i="+i);
//            System.out.println("n="+n);
            if (n < 1024) {
                return String.format("%.3f", n) + tipo;
            }
            n /= 1024;
        }
        return n + " B";
    }

    private static String[] seccionesDeNomero(long n) {
        LinkedList<String> l = new LinkedList<String>();
        String a = l + "", s = "";
        for (int i = a.length() - 1; i >= 0; i--) {
            if (s.length() == 3) {
                l.add(s);
                s = "";
            }
            s = a.charAt(i) + s;
        }
        l.add(s);
//        Collections.reverse(l);
        return l.toArray(new String[]{});
    }

    /**
     * devuelbe un File (f + "/" + nombre + extencion)
     *
     * @param f
     * @param nombre
     * @param extencion
     * @return
     */
    public static File adjuntar(File f, String nombre, String extencion) {
        return new File(f + "/" + nombre + extencion);
    }

    /**
     * devuelbe un File (f.getParent() + "/" + nombre.getName())
     *
     * @param f
     * @param nombre
     * @return
     */
    public static File adjuntarAPadre(File f, File nombre) {
        return new File(f.getParent() + "/" + nombre.getName());
    }

    public static void crearArchivo(File f, String nombre, String extencion, Object objetoPrederterminado) throws IOException {
        if (f.exists()) {
            ObjectOutputStream F = new ObjectOutputStream(new FileOutputStream(adjuntar(f, nombre, extencion)));
            F.writeObject(objetoPrederterminado);
            F.close();
        }
    }

    public static String[] getNombresDeCarpeta(File F) {
        File[] Fl = F.listFiles();
        LinkedList<String> Invalidos = new LinkedList<String>();
        for (int i = 0; i < Fl.length; i++) {
            if (Fl[i].isDirectory()) {
                Invalidos.add(Fl[i].getName());
            }
        }
        return Invalidos.toArray(new String[]{});
    }

    /**
     * por separadores <br> utiliza StringTokenizer
     *
     * @param f
     * @param NoValidos
     * @return
     */
    public static String[] getInvalidos(File f, String NoValidos[]) {
        if (f.exists() && f.isDirectory()) {
            LinkedList<String> l = new LinkedList<>();
            File[] Fl = f.listFiles();
            for (int i = 0; i < Fl.length; i++) {
                if (Fl[i].isDirectory() && MetodosUtiles.contains(Fl[i].getName(), true, NoValidos)) {
                    l.add(Fl[i].getName());
                }
            }
            return l.toArray(new String[]{});
        }
        return null;
    }

    public static String[] leerTXT(File F[], String noValidos[][]) throws FileNotFoundException {
        TreeSet<String> lineas = new TreeSet();
        for (int i = 0; i < F.length; i++) {
            if (esTXT(F[i])) {
                Scanner e = new Scanner(F[i]);
                while (e.hasNextLine()) {
                    String l = e.nextLine();
                    if (!or(l, noValidos[i])) {
                        lineas.add(l);
                    }
                }
            }
        }
        return lineas.toArray(new String[]{});
    }

    public static LinkedList<String> leerTXTSinEspacios(LinkedList<File> F, LinkedList<LinkedList<String>> noValidos, ConfiguracionDeVideo cdv) throws FileNotFoundException {
        TreeSet<String> lineas = new TreeSet();
        for (int i = 0; i < F.size(); i++) {
            if (esTXT(F.get(i))) {
//                FileReader Fr = new FileReader(F.get(i));
                Scanner e = new Scanner(new FileReader(F.get(i)));
                while (e.hasNextLine()) {
                    String l = e.nextLine();
                    if (!MetodosUtiles.StringRealmenteVacio(l.trim()) && !or(l, noValidos.get(i))
                            && Videos.lineaValida(l, cdv)) {
                        lineas.add(l);
                    }
                }
//                lineas.addAll(new LinkedList<String>(Arrays.asList(leerTXT(F.get(i)))));
            }
        }
        return new LinkedList<String>(lineas);
    }

    public static LinkedList<String> leerTXT(LinkedList<File> F, LinkedList<LinkedList<String>> noValidos) throws FileNotFoundException {
        TreeSet<String> lineas = new TreeSet();
        for (int i = 0; i < F.size(); i++) {
            if (esTXT(F.get(i))) {
//                System.out.println("lineas");
                FileReader Fr = new FileReader(F.get(i));
                Scanner e = new Scanner(Fr);
//                System.out.println("F.get(i)="+F.get(i));
                while (e.hasNextLine()) {
                    String l = e.nextLine();
//                    System.out.println("l ="+l);
                    if (!or(l, noValidos.get(i))) {
                        lineas.add(l);
//                        System.out.println(l);
                    }
                }
//                lineas.addAll(new LinkedList<String>(Arrays.asList(leerTXT(F.get(i)))));
            }
        }
        return new LinkedList<String>(lineas);
    }

    public static String[] leerTXT(File f) throws FileNotFoundException {
        if (esTXT(f)) {
            LinkedList<String> lineas = new LinkedList<String>();
            FileReader Fr = new FileReader(f);
            Scanner e = new Scanner(Fr);
            while (e.hasNextLine()) {
                lineas.add(e.nextLine());
            }

            return lineas.toArray(new String[]{});
        }
        return null;
    }

    public static String[] leerTXTSinEspacios(File f, ConfiguracionDeVideo cdv) throws FileNotFoundException {
        if (esTXT(f)) {
            LinkedList<String> lineas = new LinkedList<String>();
            FileReader Fr = new FileReader(f);
            Scanner e = new Scanner(Fr);
            while (e.hasNextLine()) {
                String line = e.nextLine();
//                System.out.println("line="+line);
                if (!MetodosUtiles.StringRealmenteVacio(line.trim())
                        && Videos.lineaValida(line, cdv)) {
//                    System.out.println("true");
                    lineas.add(line);
                }

            }

            return lineas.toArray(new String[]{});
        }
        return null;
    }

    public static boolean esTXT(File f) {
        return f.exists() && f.isFile() && getExtencion(f).equalsIgnoreCase(".txt");
    }

    public static File[] directoriosValidos(File f, String directoriosNoValidos[]) {
        if (f.isDirectory()) {
            LinkedList<File> contenidoList = new LinkedList<File>(Arrays.asList(f.listFiles()));
            LinkedList<String> directoriosNoValidosList = new LinkedList<String>(Arrays.asList(directoriosNoValidos));
            eliminarDirectoriosNoValidos(contenidoList, directoriosNoValidosList);
            return contenidoList.toArray(new File[]{});
        }
        return null;
    }

    public static LinkedList<File> directoriosValidos(File f, LinkedList<String> directoriosNoValidos) {
        if (f.isDirectory()) {
            LinkedList<File> contenidoList = new LinkedList<File>(Arrays.asList(f.listFiles()));
            LinkedList<String> directoriosNoValidosList = new LinkedList<String>(directoriosNoValidos);
            eliminarDirectoriosNoValidos(contenidoList, directoriosNoValidosList);
            return contenidoList;
        }
        return null;
    }

    public static File[] directoriosValidos(File F[], String directoriosNoValidos[][]) {
        LinkedList<File> ff = new LinkedList<File>();

        for (int i = 0; i < F.length; i++) {
            File f[] = directoriosValidos(F[i], directoriosNoValidos[i]);
            if (f != null) {
                ff.addAll(Arrays.asList(f));
            }

        }

        return ff.toArray(new File[]{});
    }

    public static LinkedList<File> directoriosValidos(LinkedList<File> F, LinkedList<LinkedList<String>> directoriosNoValidos) {
        LinkedList<File> ff = new LinkedList<File>();
        for (int i = 0; i < F.size(); i++) {
            LinkedList<File> f = directoriosValidos(F.get(i), directoriosNoValidos.get(i));
            if (f != null) {
                ff.addAll(f);
            }

        }
        return ff;
    }

    public static void eliminarDirectoriosNoValidos(LinkedList<File> contenidoList, LinkedList<String> directoriosNoValidosList) {
        for (int i = 0; i < contenidoList.size(); i++) {
            for (int j = 0; j < directoriosNoValidosList.size(); j++) {
                if (contenidoList.get(i).getName().equalsIgnoreCase(directoriosNoValidosList.get(j))) {
                    contenidoList.remove(i--);
                    directoriosNoValidosList.remove(j);
                    break;
                }
            }

        }

    }

    public static void crearTXTContenidoDeCarpeta(File carpeta, File txt, String nombre, String invalidos[]) {
        if (carpeta.exists() && carpeta.isDirectory() && txt.exists() && txt.isDirectory()) {
            LinkedList<String> l = new LinkedList<String>(Arrays.asList(carpeta.list()));
            int cantBorrados = 0;
            For1:
            for (int i = 0; i < l.size(); i++) {
                for (int j = 0; j < invalidos.length; j++) {
                    if (l.get(i).equals(invalidos[j])) {
                        l.remove(i--);
                        if (cantBorrados++ == invalidos.length) {
                            break For1;
                        }
                        break;
                    }
                }
            }
            crearTXT(txt, nombre, l.toArray(new String[]{}));
        }
    }
//    public static void crearTXTContenidoDeCarpeta(File carpeta, File txt, String nombre) {
//        if (carpeta.exists() && carpeta.isDirectory() && txt.exists() && txt.isDirectory()) {
//            crearTXT(txt, nombre, carpeta.list());
//        }
//    }

    public static void crearTXTContenidoDeCarpetaYCarpetas(File carpeta, int nivelesCarpetasInternas, File txt, String nombre) {
        if (nivelesCarpetasInternas > 0 && carpeta.exists() && carpeta.isDirectory() && txt.exists() && txt.isDirectory()) {
            //   System.out.println("ss");

            txt = new File(txt.toString() + "\\" + carpeta.getName());
            txt.mkdirs();
            crearTXT(txt, carpeta.getName(), carpeta.list());
            for (File c : carpeta.listFiles()) {
                crearTXTContenidoDeCarpetaYCarpetas(c, nivelesCarpetasInternas - 1, txt, carpeta.getName());
            }

        }
    }

    /**
     * El primero (claves) es el que se utiliza para seleccionar elementos del
     * otro, los ficheros selecionados como respuesta pertenencen al segundo
     * (ficheros)
     *
     * @param claves
     * @param ficheros
     * @return
     */
    public static LinkedList<Fichero> getFicherosRelacionados(LinkedList<Fichero> claves, LinkedList<Fichero> ficheros) {
        LinkedList<Fichero> res = new LinkedList<Fichero>();
        for (Fichero fi : ficheros) {
            for (Fichero c : claves) {
                if (nombresRelacionados(c.getClave(), fi.getClave())) {
                    res.add(fi);
                }
            }
        }
        return res;
    }

    public static LinkedList<Fichero> getFicherosRelacionados(String claves[], LinkedList<Fichero> ficheros) {
        LinkedList<Fichero> res = new LinkedList<Fichero>();
        for (Fichero fi : ficheros) {
            for (String c : claves) {
                if (nombresRelacionados(c, fi.getClave())) {
                    res.add(fi);
                }
            }
        }
        return res;
    }

    public static void crearTXT(String direccion, String nombreTXT, String lines[]) {
        crearTXT(new File(direccion), nombreTXT, lines);
    }

    public static void crearTXT(File f, String nombreTXT, String lines[]) {
        if (f.isDirectory()) {
            try {

                PrintWriter p = new PrintWriter(adaptarExtencion(f + "/" + nombreTXT, ".txt", true));
                for (String l : lines) {
                    p.println(l);
                    // System.out.println("l="+l);
                }
                p.close();
            } catch (Exception e) {
                Visual.responerException(e);
            }
        }

    }

    public static String getParentName(File f) {
        return f.getParentFile().getName().isEmpty() ? f.getParentFile().toString() : f.getParentFile().getName();
    }

    public static boolean contieneAFile(LinkedList<File> F, File f) {
        return contieneAFile(F.toArray(new File[]{}), f);
    }

    public static boolean contieneAFile(File F[], File f) {
        for (File f2 : F) {
            //    System.out.println("f2="+f2);
            //    System.out.println("f="+f);
            if (f2.compareTo(f) == 0) {
                //       System.out.println("si");
                return true;
            }
            //  System.out.println("no");
        }
        return false;
    }

    public static void forzarNombreAFile(JFileChooser jf, String nombrePredeterminado, String extencion) {
        jf.setSelectedFile(forzarNombreAFile(jf.getSelectedFile(), nombrePredeterminado, extencion));
    }

    public static File forzarNombreAFile(File f, String nombrePredeterminado, String extencion) {
        if (f.isDirectory()) {
            return new File(f + "/" + nombrePredeterminado + extencion);
        }
        if (!Archivo.getExtencion(f).equals(extencion)) {
            Archivo.sustituirExtencionReal(f, extencion);
        }
        return f;
    }

    public static String getNombreRelacionadoClaveVideoDefault(String nombre) {
        return getNombreRelacionadoClave(nombre, ConfiguracionDeVideo.getDefault());
    }

    public static String getNombreRelacionadoClave(String nombre, ConfiguracionDeVideo cdv) {
        return getNombreRelacionadoClave(nombre, cdv, 0, nombre.length());
    }
//String saltarAlPrincipio[], String detenciones[]

//    public static String getNombreRelacionadoClave(String nombre, String saltarAlPrincipio[], String detenciones[]) {
//        return getNombreRelacionadoClave(nombre, saltarAlPrincipio, detenciones, 0, nombre.length());
//    }
    public static String getNombreRelacionadoClave(String nombre, ConfiguracionDeVideo cdv, int inicio, int end) {
        if(nombre.length()==0){
        return "";
        }
//                if (nombre.equals("The Blacklist 6x01.mkv")) {
//                    System.out.println("bbbbbbbbb");
//                }
//        System.out.println("nombre=" + nombre);
        int lenghOriginal = nombre.length();
        nombre = MetodosUtiles.arreglarPalabra(nombre.trim());
////        System.out.println("2 nombre=" + nombre);
        if (lenghOriginal > nombre.length()) {
            inicio -= (inicio == 0) ? inicio : lenghOriginal - nombre.length();
            end -= lenghOriginal - nombre.length();
        }
        if (end < 0||end>nombre.length()) {
            //  end = nombre.length() - 1;
            end = nombre.length();
            //System.out.println("aaa");
        } else {
            //  System.out.println("bbbb");
        }
        if(inicio<0||inicio>nombre.length()-1){
        inicio=0;
        }
        
        int salto[] = MetodosUtiles.indexOf(nombre.substring(inicio, end), cdv.saltarHastaDespuesDe);
        if (salto[0] != -1) {

            inicio += cdv.saltarHastaDespuesDe[salto[1]].length() + salto[0];
            //System.out.println("saltarHastaDespuesDe **************");
        }
        //0 inicio
//        System.out.println("nombre=" + nombre);
        int j2 = orIndice(nombre.charAt(0) + "", true, 0, cdv.rodearIgnorar);
//                System.out.println("j="+j);
        if (j2 != -1) {
            inicio += posicionFinalDe(nombre, cdv.rodearIgnorar[j2][0], cdv.rodearIgnorar[j2][1], inicio) + 1;
//                inicio = inicio + 1;
//                continue;
        }

        while (or(nombre.charAt(inicio), cdv.separacion)) {
            inicio++;
        }
        if (inicio > end && inicio >= nombre.length()) {
            return "";
        }

        int indiceSaltoPrincipio = contieneStringAEnIndiceAArregloStringB(true, inicio, nombre, cdv.saltarAlPrincipio);
        if (indiceSaltoPrincipio != -1) {
            int noSaltar = contieneStringAEnIndiceAArregloStringB(true, inicio, nombre, cdv.noSaltarAlPrincipio);
            if (noSaltar == -1) {
                inicio += cdv.saltarAlPrincipio[indiceSaltoPrincipio].length();
            }

        }

//        System.out.println("Nombre=" + nombre);
//        System.out.println("inicio=" + inicio + " end=" + end);
//        if(end<nombre.length()){
//        System.out.println("nombre.charAt(end)"+nombre.charAt(end));
//        }
//        
//        System.out.println("nombre.substring(inicio, end)=" + nombre.substring(inicio, end));
        for (int i = inicio; i < end; i++) {
            //nnnnnnnnn
//            System.out.println("i=" + i + " nombre.charAt(end)=" + nombre.charAt(i));
            // if(i==inicio&&or(nombre.charAt(i),'(','{','[')){

            if (i == inicio) {
                int j = orIndice(nombre.charAt(i) + "", true, 0, cdv.rodearIgnorar);
//                System.out.println("j="+j);
                if (j != -1) {
                    i += posicionFinalDe(nombre, cdv.rodearIgnorar[j][0], cdv.rodearIgnorar[j][1], inicio);
                    inicio = i + 1;
                    continue;
                }
            }

            if (i < nombre.length() - 1 && i > 0 && or(nombre.charAt(i), 'S', 's', 'T', 't') && esCharNumero(nombre.charAt(i + 1)) && or(nombre.charAt(i - 1), cdv.separacion)) {
                end = i;
                break;
            }

            int j = contieneStringAEnIndiceAArregloStringB(true, i, nombre, cdv.detencionesAbsolutas);
            if (j != -1) {
                end = i;
                break;
            }
            j = contieneStringAEnIndiceAArregloStringB(true, i, nombre, cdv.detenciones);
            if (j != -1) {
                if (cdv.detenciones[j].equalsIgnoreCase("temp")) {
//                    System.out.println("aaaa");
                    int sig = cdv.detenciones[j].length() + i;
//                    System.out.println("end");
//                    System.out.println("sig=" + sig);
//                    System.out.println("arreglarChar(nombre.charAt(sig))="+arreglarChar(nombre.charAt(sig)));
                    if (sig < nombre.length() && !esCharLetra(arreglarChar(nombre.charAt(sig)))) {
                        end = i;
                        break;
                    }
                } else {
                    end = i;
                    break;
                }
//                end = i;
//                break;
            } //*********
            Object O[] = obtenerFechaYcantidadDeCaracteresOriginal(nombre, i);
            if (O != null) {
                end = i;
                break;
            }
            String separacion = obtenerSeparacion(nombre, i, cdv);
            if (!separacion.isEmpty()) {
//                System.out.println("separacion="+separacion);
                if (esAleaterizacion(separacion)) {
                    end = i;
                    break;
                } else {
                    try {
                        double numero[] = buscarNumeroYCantidadDeCaracteresOriginal(separacion, 0, false, false);
                        if (numero[0] > 1980 && numero[0] < 3000) {
                            end = i;
                            break;
                        }
                    } catch (Exception ex) {

                    }
                    j = contieneStringAEnIndiceAArregloStringB(true, 0, separacion, 1, 0, Videos.extencionVideo.extencionesVideos());

                    if (j != -1) {
                        if (!(Videos.extencionVideo.extencionesVideos()[j].length() + i == end || Videos.extencionVideo.extencionesVideos()[j].length() + i == nombre.length())) {
                            int k = contieneStringAEnIndiceAArregloStringB(true, Videos.extencionVideo.extencionesVideos()[j].length() + i, nombre, 0, 0, cdv.separacionString);
                            if (k == -1) {
                                //System.out.println("salto");
                                continue;
                            }
//                        if(or(nombre.charAt(Videos.extencionVideo.extencionesVideos()[j].length()+i),'.',' ')){
//                        
//                        }
                        }
                        // System.out.println("j="+j+"Videos.extencionVideo.extencionesVideos()[j]="+Videos.extencionVideo.extencionesVideos()[j]);
                        end = i;
                        break;
                    }
                }

            }
            //*********
        }
//        nombre = nombre.replace("&", "y");
//        nombre = nombre.replace(" and ", " y ");
        lenghOriginal = nombre.length();
        nombre = Videos.sustituirAND(nombre, cdv);
        if (nombre.length() != lenghOriginal) {
            end -= lenghOriginal - nombre.length();
        }
        if (end > nombre.length()) {
            end = nombre.length();
        }
//        System.out.println("nombre=" + nombre);
//        System.out.println("inicio=" + inicio + " end=" + end);
        if (inicio > end) {
            int tem = inicio;
            inicio = end;
            end = tem;
        }
        String res = eliminarUnionesDeStringLine(((inicio != 0 || end != nombre.length()) ? nombre.substring(inicio != 0 ? inicio : 0, end != nombre.length() ? end : nombre.length()) : nombre).toLowerCase());
//        System.out.println("res=" + res);
        //  return eliminarUnionesDeStringLine(((inicio != 0 && end != nombre.length()) ? nombre.substring(inicio, end) : nombre).toLowerCase());
        return res;
    }

//    public static boolean esSeparacion(char a) {
//        return esSeparacion(a, ConfiguracionDeVideo.getDefault());
//    }
    public static boolean esSeparacion(char a, ConfiguracionDeVideo cdv) {
//        System.out.println("a=" + a);
//        System.out.println("Arrays.binarySearch(separacion, a)=" + Arrays.binarySearch(separacion, a));
        for (int i = 0; i < cdv.separacion.length; i++) {
//            if(a=='+'&&separacion[i]=='+'){
//                System.out.println("aaaaaaaaaaaaaaa");
//            }
            if (a == cdv.separacion[i]) {
                return true;
            }
        }
        return false;
        // return Arrays.binarySearch(separacion, a) >= 0;
    }

    public static String[] getSetNombresClaves(LinkedList<Fichero> ficheros) {
        TreeSet<String> claves = new TreeSet<String>();
        for (Fichero fi : ficheros) {
            for (String c : fi.getClaves()) {
                claves.add(c);
            }
        }
        LinkedList<String> list = new LinkedList<String>(claves);
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {

                if (nombresRelacionados(list.get(i), list.get(j))) {
                    if (list.get(j).length() < list.get(i).length()) {
                        list.set(i, list.get(j));
                    }
//                    System.out.println("nombresRelacionados(list.get(i), list.get(j))="+nombresRelacionados(list.get(i), list.get(j)));
//                    System.out.println("list.get(i)="+list.get(i));
//                    System.out.println("list.get(j)="+list.get(j));
                    list.remove(j--);
                }
            }
        }

        return list.toArray(new String[]{});
    }

    public static boolean nombresRelacionados(LinkedList<String> claves, String nombre) {
        for (String c : claves) {
            if (nombresRelacionados(c, nombre)) {
                return true;
            }
        }
        return false;
    }

    public static boolean nombresRelacionados(LinkedList<String> claves, LinkedList<String> nombres) {
        for (int i = 0; i < claves.size(); i++) {
            for (int j = 0; j < nombres.size(); j++) {
                if (nombresRelacionados(claves.get(i), nombres.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean nombresRelacionados(String marcardor, String nombre) {
        return marcardor.length() < nombre.length() ? nombresRelacionadosMarcador(marcardor, nombre) : nombresRelacionadosMarcador(nombre, marcardor);
    }

    private static String subs(String a, int i0, int iNoInCluida) {
        return a.substring(i0, iNoInCluida);
    }

    private static boolean esCharDesconocidoOR(char... B) {
        int end = B.length;
        for (int i = 0; i < end; i++) {
            if (B[i] == '�') {
                return true;
            }
        }

        return false;
    }

    /**
     * este es adaptado
     *
     * @param marcador
     * @param nombre
     * @return
     */
    public static boolean nombresRelacionadosMarcador(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        int MIN = 7;//"1234567"

        if (a.equals(b)) {
            return true;
        }
        int lengA = a.length();
        int lengB = b.length();
        if (lengA <= MIN || lengB <= MIN) {
            return false;//pq la comparacion de que si son == ya se iso arriba 
        }
        int MIN_IGUALES = 4;
        if (subs(a, 0, MIN_IGUALES).equals(subs(a, 0, MIN_IGUALES))) {
            return false;//pq tienen que ser iguales los primeros caracteres
        }

        int cantIguales = 0, cantDiferentes = 0, cantDiferentesSegidos = 0, indiceRuptura = MIN, recorrido = 0;
        boolean diferenciaGrave = false, ultimoPasoDelFor = false;//terminoFor
        //int distanciaRestanteMarcador = 0, distanciaRestanteNombre = 0;
        int distanciaRestanteA = lengA - MIN_IGUALES, distanciaRestanteB = lengB - MIN_IGUALES;
        int distanciaRestanteMenor = distanciaRestanteA < distanciaRestanteB ? distanciaRestanteA : distanciaRestanteB,
                distanciaRestanteMayor = distanciaRestanteA > distanciaRestanteB ? distanciaRestanteA : distanciaRestanteB;
        boolean terminoForEnUltimosCaracteresIguales = false;

        int endDelFor = (lengA <= lengB ? lengA : lengB);
        for (int i = MIN_IGUALES; i < endDelFor; i++) {
            recorrido++;
            //actualizar distancias restantes
            distanciaRestanteA--;
            distanciaRestanteB--;
            distanciaRestanteMenor--;
            distanciaRestanteMayor--;

            if (i == endDelFor - 1) {
                ultimoPasoDelFor = true;
            }

            char charA_Actual = a.charAt(i);
            char charB_Actual = b.charAt(i);

            if (charA_Actual == charB_Actual || esCharDesconocidoOR(charA_Actual, charB_Actual)) {
                cantIguales++;
                cantDiferentesSegidos = 0;

                if (ultimoPasoDelFor) {
                    terminoForEnUltimosCaracteresIguales = true;
                }
                continue;
            }

            if (!ultimoPasoDelFor) {
                char charA_Anterior = a.charAt(i - 1);
                char charB_Anterior = b.charAt(i - 1);

                //The InBeetwen === The InBetween
                //caso error humano, cuando existen 2''== seguidos y en uno de los dos se omite uno de estos ''
                // en general abbc--abc siendo b->i 
                //1: se comprueba la existencia de  2''== seguidos tomando como referencia el i actual y el anterior en ambos
                boolean charA_Actual_Igual_charA_Anterior = charA_Actual == charA_Anterior;
                boolean charB_Actual_Igual_charB_Anterior = charB_Actual == charB_Anterior;
                if (charA_Actual_Igual_charA_Anterior || charB_Actual_Igual_charB_Anterior) {

                    // se decide cual es el de los 2''== y cual no
                    // y se inicializan las v necesarias
                    char charDeDiferentesSeguidos_Anterior = '-';
                    char charDeIgualesSeguidos_Actual = '-';
                    if (charA_Actual_Igual_charA_Anterior) {
                        charDeDiferentesSeguidos_Anterior = charB_Anterior;
                        charDeIgualesSeguidos_Actual = charA_Actual;
                    } else {
                        charDeDiferentesSeguidos_Anterior = charA_Anterior;
                        charDeIgualesSeguidos_Actual = charB_Actual;
                    }
                    //2:se comprueba que el ''anterior del que es diferente, si es == al
                    // que tiene los 2''== coparandolo con su ''actual
                    if (charDeDiferentesSeguidos_Anterior == charDeIgualesSeguidos_Actual) {
                        char charA_Siguiente = a.charAt(i + 1);
                        char charB_Siguiente = b.charAt(i + 1);

                        char charDeDiferentesSeguidos_Actual = '-';
                        char charDeIgualesSeguidos_Siguiente = '-';
                        if (charA_Actual_Igual_charA_Anterior) {
                            charDeDiferentesSeguidos_Actual = charB_Actual;
                            charDeIgualesSeguidos_Siguiente = charA_Siguiente;
                        } else {
                            charDeDiferentesSeguidos_Actual = charA_Actual;
                            charDeIgualesSeguidos_Siguiente = charB_Siguiente;
                        }
                        //3: se comprueba si el '' a continuacion de los 2''== es == al '' actual del
                        // los tiene diferente
                        if (charDeDiferentesSeguidos_Actual == charDeIgualesSeguidos_Siguiente) {
                            char charA_Anterior2 = a.charAt(i - 2);
                            char charB_Anterior2 = b.charAt(i - 2);

                            char charDeIgualesSeguidos_Anterior2 = '-';
                            if (charA_Actual_Igual_charA_Anterior) {
                                charDeDiferentesSeguidos_Anterior = charB_Anterior;
                                charDeIgualesSeguidos_Anterior2 = charA_Anterior2;
                            } else {
                                charDeDiferentesSeguidos_Anterior = charA_Anterior;
                                charDeIgualesSeguidos_Anterior2 = charB_Anterior2;
                            }

                            //4:se comprueba si el ''-2 (abbc seria a,con 2b->i) es == al
                            // '' anterior al que los tiene diferente (abc seria a ,con b->i)
                            if (charDeDiferentesSeguidos_Anterior == charDeIgualesSeguidos_Anterior2) {

                                if (charA_Actual_Igual_charA_Anterior) {
                                    a = a.substring(1);
                                    lengA--;
                                    distanciaRestanteA--;
                                } else {
                                    b = b.substring(1);
                                    lengB--;
                                    distanciaRestanteB--;
                                }
                                cantIguales++;
                                cantDiferentesSegidos = 0;

                                //actualizar valores fijos
                                endDelFor = (lengA <= lengB ? lengA : lengB);
                                distanciaRestanteMenor = distanciaRestanteA < distanciaRestanteB ? distanciaRestanteA : distanciaRestanteB;
                                distanciaRestanteMayor = distanciaRestanteA > distanciaRestanteB ? distanciaRestanteA : distanciaRestanteB;

                                continue;
                            }

                        }

                    }

                }//fin caso abbc abc
                //aun dentro de if(!ultimoPasoDelFor)

            }
            //ya no hay casos que eviten que sean los actuales diferentes
            cantDiferentes++;
            cantDiferentesSegidos++;

            if (cantDiferentesSegidos == 3) {
                // como el informe final se basa en los que fueron iguales hasta el 
                //indice de ruptura (~~ el ultimo ''== )
                // y a este lo voy a desplasar 3'' atras pq ya son muchos '' diferences seguidos (3'')
                cantDiferentes -= 3;
                indiceRuptura = i - 3;
                if (recorrido < 5) {
                    return false;//pq el recorrido fue muy corto con muchas diferencias
                }

                if (recorrido < 10) {
                    if (distanciaRestanteMenor > 0) {//pendiente pq aun no se si usar marcador
                        return false;//la idea seria que si el marcador aun le quedan caracteres 
                        //pero todavia despues de este largo recorrido(10) no se ha dado una respuesta positiva
                        // no vale la pena seguir buscando, recordar que aqui ya existieron almenos 3''!= seguidos
                    }
                }

                if (recorrido < 14) {

                    if (((recorrido + MIN_IGUALES) < distanciaRestanteA / 2) || ((recorrido + MIN_IGUALES) < distanciaRestanteB / 2)) {
                        return false;
                    }
                }
                break;

            }//fin del if (cantDiferentesSegidos == 3) 

            if (cantDiferentes == 5) {//ver que son diferentes no seguidos
                return false; //resultado negativo principal
            }

            if (cantDiferentesSegidos == 2) {

                //pq no se admiten dos diferencias graves seguidas (diferenciaGrave = 2''!=)
                if (diferenciaGrave) {
                    cantDiferentes -= 2;//pq para la conclusión  final solo se toman en cuenta los resultados hasta el indice de ruptura, y este se va a acortar
                    indiceRuptura = i - 2;//indice de ruptura (~~ el ultimo ''== )
                    break;
                }
                //caso error humano: donde por omitir o gregar un caracter hay diferencia
                //principalmente donde los 4'' actuales(comensando por i) de uno son
                // == a los 4'' desde i-1 del otro
                //la solucion: eliminar un '' del primero (el ''0,que oviamente no es ninguno de estos)
                //hay que comprobar si tienen caracteres a continuacion suficientes para comparar,para
                //que salte uno error
                // se comprueba para evitar marcar un error grave innecesario 
                boolean esA4Seguidos = false;
                if ((distanciaRestanteA > 2 && distanciaRestanteB > 1 && (esA4Seguidos = subs(b, i - 1, i + 2).equals(subs(a, i, i + 3))))
                        || (distanciaRestanteA > 1 && distanciaRestanteB > 2 && subs(a, i - 1, i + 2).equals(subs(b, i, i + 3)))) {

                    if (esA4Seguidos) {
                        a = a.substring(1);
                        lengA--;
                        distanciaRestanteA--;
                    } else {
                        b = b.substring(1);
                        lengB--;
                        distanciaRestanteB--;
                    }
                    cantDiferentesSegidos = 0;
                    i++;//pq lo deja donde esta el ultimo ''== dando la oportunidad a un final positivo y despues  es donde podrian ser diferentes 
                    cantDiferentes--;

                    //actualizar valores fijos
                    endDelFor = (lengA <= lengB ? lengA : lengB);
                    distanciaRestanteMenor = distanciaRestanteA < distanciaRestanteB ? distanciaRestanteA : distanciaRestanteB;
                    distanciaRestanteMayor = distanciaRestanteA > distanciaRestanteB ? distanciaRestanteA : distanciaRestanteB;
                } else {
                    //no se pudo evitar marcar como diferencia grave
                    diferenciaGrave = true;
                }//fin del if caso 4''
            } else {
                //hay cualquier cantidad de diferentes seguidos,exepto 2,3,5 (creo que solo 1 y 4)

                if (cantDiferentesSegidos == 1) {
                    //caso error humano: donde por omitir o gregar un caracter hay diferencia
                    //principalmente donde los 4'' actuales(comensando por i) de uno son
                    // == a los 4'' desde i+1 del otro
                    //la solucion: eliminar un '' del segundo (el ''0,que oviamente no es ninguno de estos)
                    //hay que comprobar si tienen caracteres a continuacion suficientes para comparar,para
                    //que salte uno error
                    boolean esA4Seguidos = false;
                    if ((distanciaRestanteA > 2 && distanciaRestanteB > 1 && (esA4Seguidos = subs(b, i + 1, i + 4).equals(subs(a, i, i + 3))))
                            || (distanciaRestanteA > 1 && distanciaRestanteB > 2 && subs(a, i + 1, i + 4).equals(subs(b, i, i + 3)))) {

                        if (esA4Seguidos) {//b se recorta aqui, pq la solucion es recortar el que no tiene los 4'' a partir del actual
                            b = b.substring(1);
                            lengB--;
                            distanciaRestanteB--;
                        } else {
                            a = a.substring(1);
                            lengA--;
                            distanciaRestanteA--;
                        }
                        cantDiferentesSegidos = 0;
                        i += 2;//pq lo deja donde esta el ultimo ''== dando la oportunidad a un final positivo y despues  es donde podrian ser diferentes
                        cantDiferentes--;

                        //actualizar valores fijos
                        endDelFor = (lengA <= lengB ? lengA : lengB);
                        distanciaRestanteMenor = distanciaRestanteA < distanciaRestanteB ? distanciaRestanteA : distanciaRestanteB;
                        distanciaRestanteMayor = distanciaRestanteA > distanciaRestanteB ? distanciaRestanteA : distanciaRestanteB;
                    }//fin del if caso 4'' 2da vez
                }//fin del if (cantDiferentesSegidos==1) 

            }//fin del if (cantDiferentesSegidos == 2) -- arriba esta dentro del else

        }//fin del for

        //Conclusión 
        if (lengA != lengB) {

            if (cantDiferentes >= 2) {
                //si no fue un recorrido demasiado extenso pero
                // mas de la mitad del reccorido fueron ''diferentes -> no hay relacion
                if (recorrido < 10 || !(cantDiferentes < recorrido / 2)) {
                    return false;
                }
            }

            if (terminoForEnUltimosCaracteresIguales) {
                if (recorrido < 3 && distanciaRestanteMayor > 1) {
                    return false;
                }
                if (recorrido < 5 && cantDiferentes > 0 && distanciaRestanteMayor > 4) {
                    return false;
                }
                int lengMayor = lengA > lengB ? lengA : lengB;
                if (distanciaRestanteMayor > lengMayor * 2) {
                    return false;
                }
            }
        } else {// los leng son iguales
            if (diferenciaGrave || cantDiferentes >= 3) {
                return false;
            }

        }

        return true;

    }

    public static boolean nombresRelacionadosMarcador2(String marcador, String nombre) {

        final int min = 4;

        if (marcador.equalsIgnoreCase(nombre)) {
            return true;
        }
//        System.out.println("maracador=" + marcador);
//        System.out.println("nombre=" + nombre);
//        System.out.println("nombre.substring(0, min)=" + nombre.substring(0, min));
//        System.out.println("!marcador.startsWith(nombre.substring(0, min))=" + !marcador.startsWith(nombre.substring(0, min)));
        if (marcador.length() < min || nombre.length() < min || !marcador.startsWith(nombre.substring(0, min))) {

            return false;
        }
        int cantIguales = 0, cantDiferentes = 0, cantDiferentesSegidos = 0, indiceRuptura = min, recorrido = 0;
        boolean diferenciaGrave = false, terminoFor = false;
        int distanciaRestanteMarcador = 0, distanciaRestanteNombre = 0;

        for (int i = min; i < (marcador.length() <= nombre.length() ? marcador.length() : nombre.length()); i++) {
//            System.out.println("maracador=" + marcador);
//            System.out.println("nombre=" + nombre);
//            System.out.println("i=" + i);
//            System.out.println("marcador.charAt(i)=" + marcador.charAt(i) + "  nombre.charAt(i)=" + nombre.charAt(i));
            recorrido++;
            distanciaRestanteMarcador = marcador.length() - i - 1;
            distanciaRestanteNombre = nombre.length() - i - 1;

            if (charIgual(i, marcador, nombre) || nombre.charAt(i) == '�' || marcador.charAt(i) == '�') {
                cantIguales++;
                cantDiferentesSegidos = 0;

            } else {
                if (i != marcador.length() - 1) {
                    //The InBeetwen === The InBetween
                    if (marcador.charAt(i) == marcador.charAt(i - 1) && nombre.charAt(i - 1) == marcador.charAt(i) && nombre.charAt(i) == marcador.charAt(i + 1) && nombre.charAt(i - 2) == marcador.charAt(i - 2)) {
                        marcador = marcador.substring(1);
                        cantIguales++;
                        cantDiferentesSegidos = 0;
//                        System.out.println("uno");
                        continue;
                    }
                }

                if (i != nombre.length() - 1) {
                    //The InBeetwen === The InBetween
                    if (nombre.charAt(i) == nombre.charAt(i - 1) && marcador.charAt(i - 1) == nombre.charAt(i) && marcador.charAt(i) == nombre.charAt(i + 1) && marcador.charAt(i - 2) == nombre.charAt(i - 2)) {
                        nombre = nombre.substring(1);
                        cantIguales++;
                        cantDiferentesSegidos = 0;
//                        System.out.println("dos");
                        continue;
                    }
                }

                cantDiferentes++;
                cantDiferentesSegidos++;

                if (cantDiferentesSegidos == 3) {
                    cantDiferentes -= 3;
                    indiceRuptura = i - 3;
                    if (recorrido < 5) {
                        return false;
                    }
                    //nnnnnnnnnnnnn 6
                    if (recorrido < 10) {
                        if (distanciaRestanteMarcador > 0) {
                            return false;
                        }
                    }
                    //nnnnnnnnnnnnnnnnnn  8
                    if (recorrido < 14) {
                        //  boolean recorridoMenorNombre = (recorrido + min) < distanciaRestanteNombre / 2;
                        //  boolean cincuentPorcientoMarcador = (recorrido + min) < distanciaRestanteMarcador / 2;
                        if (((recorrido + min) < distanciaRestanteNombre / 2) || ((recorrido + min) < distanciaRestanteMarcador / 2)) {
                            return false;
                        }
                    }
                    break;

                }
                if (cantDiferentes == 5) {
                    return false;
                }

                if (cantDiferentesSegidos == 2) {
                    if (diferenciaGrave) {
                        cantDiferentes -= 2;
                        indiceRuptura = i - 2;
                        break;
                    }
                    if (distanciaRestanteNombre > 1 && distanciaRestanteMarcador > 2
                            && nombre.substring(i - 1, i + 2).equals(marcador.substring(i, i + 3))) {
                        cantDiferentesSegidos = 0;
//                        System.out.println("movio 1 *******************");
                        marcador = marcador.substring(1);
                        i++;
                        cantDiferentes--;
                    } else {
                        diferenciaGrave = true;
                    }
                } else {
//                    System.out.println("***************");
//                    System.out.println("distanciaRestanteNombre=" + distanciaRestanteNombre + " lengh=" + nombre.length());
//                    System.out.println("distanciaRestanteMarcador=" + distanciaRestanteMarcador + " lengh=" + marcador.length());
//                    System.out.println("i="+i+" i+1="+(i+1)+" i+4="+(i+4));
//                    System.out.println("i+3="+(i+3));
                    // desfasado hacia adelante el nombre
                    if (cantDiferentesSegidos == 1 && distanciaRestanteNombre > 3 && distanciaRestanteMarcador > 2
                            && nombre.substring(i + 1, i + 4).equals(
                                    marcador.substring(i, i + 3))) {
                        cantDiferentesSegidos = 0;
                        i += 2;
                        nombre = nombre.substring(1);
//                        System.out.println("movio 2 *******************");
                    }
                }

            }
            if (i == marcador.length() - 1) {
                terminoFor = true;
            }
        }
//        System.out.println("salio");
        if (nombre.length() != marcador.length() && cantDiferentes >= 2) {
            // int recorrido = indiceRuptura - min;
            //  System.out.println("!(cantDiferentes < recorrido / 2)="+!(cantDiferentes < recorrido / 2));
            // System.out.println("recorrido="+recorrido);
            if (recorrido < 10 || !(cantDiferentes < recorrido / 2)) {
                return false;
            }
        }
        if (nombre.length() != marcador.length() && terminoFor) {
//            System.out.println("distanciaRestanteNombre=" + distanciaRestanteNombre);
//            System.out.println("recorrido=" + recorrido);
            if (recorrido < 3 && distanciaRestanteNombre > 1) {
                return false;
            }
            if (recorrido < 5 && cantDiferentes > 0 && distanciaRestanteNombre > 4) {
                return false;
            }
            if (distanciaRestanteNombre > marcador.length() * 2) {
                return false;
            }

        }

        return true;
    }

    public static void existeComprobar(File... fs) throws ExisteException {
        if (!existen(fs)) {
            throw new ExisteException("No se pudo renombrar el archibo");
        }
    }

    /**
     * Devuelve un int[] con los indices de los que no existen, si su lengh=0 es
     * que todos existen
     *
     * @param f
     * @return
     */
    public static int[] existe(File... f) {
        LinkedList<Integer> noExisten = new LinkedList<Integer>();
        for (int i = 0; i < f.length; i++) {
            if (!f[i].exists()) {
                noExisten.add(i);
            }
        }
        return Arreglos.convertirArregloAint(noExisten);
    }

    public static boolean existen(File... fs) {
        for (File f : fs) {
            if (!f.exists()) {
                return false;
            }
        }
        return true;
    }

    public static void crearDireccion(File f) {
        if (!f.exists() && f.isDirectory()) {
            f.mkdirs();
        }
    }
//    public static String obtenerSeparacion(String a, int indiceSeparacion) {
//    return obtenerSeparacion(a, indiceSeparacion, ConfiguracionDeVideo.getDefault());
//    }

    public static String obtenerSeparacion(String a, int indiceSeparacion, ConfiguracionDeVideo cdv) {
        if (!(or(a.charAt(indiceSeparacion), cdv.separacion) || or(a.charAt(indiceSeparacion), '(', '{', '['))) {
            return "";
        }
        a = getNombre(a);
        String respuesta = "";
        for (int i = indiceSeparacion + 1; i < a.length(); i++) {
            if (or(a.charAt(i), cdv.separacion) || or(a.charAt(i), '}', ']', ')')) {
                break;
            }
            respuesta += a.charAt(i);
        }
        return respuesta;
    }

    public static boolean esAleaterizacion(String a) {
//        if (a.equals("[FE30D6C8")) {
//            System.out.println("eeeeeeeee");
//        }
        if (a.length() < 7) {
            return false;
        }
        int minMayusculas = 4, minMinusculas = 4, minNumeros = 0;
        if (a.length() < 12) {
            minMayusculas = 3;
            minMinusculas = 2;
            minNumeros = 3;
        }
        int cantidadDeMayusculas = 0, cantidadDeMinusculas = 0, cantidadDeNumeros = 0;
        boolean tieneMinusculas = false;
        for (int i = 0; i < a.length(); i++) {
            if (cantidadDeMayusculas > minMayusculas && cantidadDeMinusculas > minMinusculas && cantidadDeNumeros > minNumeros) {
                return true;
            }

            if (esCharLetraMayuscula(a.charAt(i))) {
                cantidadDeMayusculas++;
                continue;
            }
            if (esCharLetraMinuscula(a.charAt(i))) {
                cantidadDeMinusculas++;
                tieneMinusculas = true;
                continue;
            }
            if (esCharNumero(a.charAt(i))) {
                cantidadDeNumeros++;
                continue;
            }

        }
        if ((!tieneMinusculas) && cantidadDeMayusculas > minMayusculas && cantidadDeNumeros > minNumeros) {
            return true;
        }
        return false;
    }

    public static String arreglarDireccion(String direccion) {
        String nuevaDireccion = "";
        for (int i = 0; i < direccion.length(); i++) {
            nuevaDireccion += direccion.charAt(i) == '\\' ? '/' : direccion.charAt(i);
        }
        return nuevaDireccion;
    }

//    public static LinkedList<LinkedList<Fichero>> relacionarArchivosPorNombre(LinkedList<Fichero> archivos) {
//
////        LinkedList<Fichero> fs=new LinkedList<Fichero>();
////        for (int i = 0; i < archivos.size(); i++) {
////            fs.add(new Fichero(archivos));
////        }
//        LinkedList<LinkedList<Fichero>> organizados = new LinkedList<LinkedList<Fichero>>();
//        for (int i = 0; i < archivos.size(); i++) {
//            organizados.add(new LinkedList<Fichero>());
//            //  String nombre1 = archivos.get(i).getNombre();
//            for (int j = i + 1; j < archivos.size();) {
//                //  System.out.println("i=" + i + " j=" + j);
//                // System.out.println("archivos.get(i).getClave()=" + archivos.get(i).getClave());
//                // System.out.println("archivos.get(j).getClave()=" + archivos.get(j).getClave());
//                if (nombresRelacionados(archivos.get(i).getClave(), archivos.get(j).getClave())) {
//                    organizados.get(i).add(archivos.remove(j));
//                } else {
//                    j++;
//                }
//            }
//            organizados.get(i).add(archivos.get(i));
//        }
//        return organizados;
//    }
    public static <E extends NombreClave> LinkedList<LinkedList<E>> relacionarPorNombre(LinkedList<E> archivos) {

        LinkedList<LinkedList<E>> organizados = new LinkedList<LinkedList<E>>();
        for (int i = 0; i < archivos.size(); i++) {
            organizados.add(new LinkedList<E>());
            for (int j = i + 1; j < archivos.size();) {
//                System.out.println("i=" + i + " j=" + j);
//                System.out.println("archivos.get(i).getClave()=" + archivos.get(i).getClave());
//                System.out.println("archivos.get(j).getClave()=" + archivos.get(j).getClave());
                if (nombresRelacionados(archivos.get(i).getClaves(), archivos.get(j).getClaves())) {
                    organizados.get(i).add(archivos.remove(j));
//                    System.out.println("a");
                } else {
//                    System.out.println("b");
                    j++;
                }
            }
            organizados.get(i).add(archivos.get(i));
        }
        return organizados;
    }

    /**
     * Modifica el nombre del directorio en la ubicaion
     * <big> Peligro afecta al dato real</big>
     *
     * @param f
     * @param extencion
     * @return
     */
    public static boolean sustituirExtencionReal(File f, String extencion) {
        return f.getName().contains(".") ? f.renameTo(new File(f.getParent() + "/" + f.getName().substring(0, indiceExtencion(f)) + extencion)) : false;
    }

    /**
     * sustituye la extencion sin afectar al dato real
     *
     * @param f
     * @param extencion
     * @return
     */
    public static File sustituirExtencionFalsa(File f, String extencion) {
//        System.out.println("extencion="+extencion);
        return f.getName().contains(".") ? new File(f.getParent() + "/" + f.getName().substring(0, indiceExtencion(f)) + extencion) : f;
    }

    /**
     * concerva la extencion anterior
     *
     * @param f
     * @param nuevoNombre
     */
    public static void renombrar(File f, String nuevoNombre) throws NoPermitidoException, ExisteException {
        renombrar(f, nuevoNombre, getExtencion(f));
    }

    public static void renombrar(File f, String nuevoNombre, String extencion) throws NoPermitidoException, ExisteException {
        int j[] = contieneCharNoPermitidoNombreIndice(nuevoNombre, extencion);
        if (j[0] != -1) {
            throw new NoPermitidoException("Contiene " + (j[2] == 0 ? "  " + nuevoNombre + " " : " la  extencion " + extencion + " ") + " al caracter no permitido en un nombre \"" + caracteresNoPermitidosNombre[j[0]] + "\" en la posicion " + j[1]);
        }
        if (!f.exists()) {
            throw new ExisteException("No se pudo encontrar el archibo");
        }
        String nuevaDireccion = f.getParent() + "/" + nuevoNombre + extencion;
        f.renameTo(new File(nuevaDireccion));
        if (!new File(nuevaDireccion).exists()) {
            throw new ExisteException("No se pudo renombrar el archibo");
        }
    }

    public static boolean contieneCharNoPermitidoNombre(String... nombres) {
        for (String nombre : nombres) {
            for (int i = 0; i < nombre.length(); i++) {
                if (esCharNoPermitidoNombre(nombre.charAt(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void contieneCharNoPermitidoNombreComprobar(String... nombres) throws NoPermitidoException {
        int j[] = contieneCharNoPermitidoNombreIndice(nombres);
        if (j[0] != -1) {
            throw new NoPermitidoException("Contiene " + (j[2] == 0 ? " el nuevo nombre " : " la nueva extencion") + " al caracter no permitido en un nombre \"" + caracteresNoPermitidosNombre[j[0]] + "\" en la posicion " + j[1]);
        }
    }

    /**
     * Si contine caracteres que no se pueden utilizar en un nombre de carpeta o
     * archibo devuelbe int [] {indice del caracter incorrecto, indice dentro
     * del nombre, indice del nombre} sino tiene {-1,-1,-1}
     *
     * @param nombres String (...)
     * @return int[3]
     */
    public static int[] contieneCharNoPermitidoNombreIndice(String... nombres) {
        for (int i = 0; i < nombres.length; i++) {
            for (int j = 0; j < nombres[i].length(); j++) {
                int indiceCararcterIncorrecto = esCharNoPermitidoNombreIndice(nombres[i].charAt(j));
                if (indiceCararcterIncorrecto > -1) {
                    //  System.out.println("p= " + indiceCararcterIncorrecto + " " + j + " " + i);
                    return new int[]{indiceCararcterIncorrecto, j, i};
                }
            }
        }

        return new int[]{-1, -1, -1};
    }

    public static boolean esCharNoPermitidoNombre(char c) {
        return Arrays.binarySearch(caracteresNoPermitidosNombre, c) > -1;
    }

    public static int esCharNoPermitidoNombreIndice(char c) {
        return Arrays.binarySearch(caracteresNoPermitidosNombre, c);
    }

    /**
     * deubuleve el indice donde comieza la extencion solo dentro del nombre o
     * -1 sino tiene
     *
     * @param f
     * @return
     */
    public static int indiceExtencion(File f) {
//        int i= contieneStringContrario(f.getName(), ".");
//        if(esNumero(f.getName().charAt(i+1)+"")){
//        return -1;
//        }
        return indiceExtencion(f.getName());
    }

    public static int indiceExtencion(String nombre) {
        //return contieneStringContrario(nombre, ".");
        int i = contieneStringContrario(nombre, ".");
        // if (i != -1 && i < nombre.length() - 1 && (esNumero(nombre.charAt(i + 1) + ""))) {
        if (i != -1 && i < nombre.length() - 1 && ((!esCharLetra(nombre.charAt(i + 1))) ? (i < nombre.length() - 2 ? (nombre.charAt(i + 1) != '3' && nombre.charAt(i + 2) != 'g') : true) : (false))) {
            return -1;
        }
        return i;
    }

    public static boolean tieneExentencion(File f) {
        return getExtencion(f).isEmpty();
    }

    /**
     * devuelve el nombre sin extencion
     *
     * @param nombre
     * @return
     */
    public static String getNombre(String nombre) {
        return getNombre(new File(nombre));
    }

    /**
     * devuelve el nombre sin extencion
     *
     * @param f
     * @return
     */
    public static String getNombre(File f) {
        int indiceExtencion = indiceExtencion(f);
//        System.out.println(f.getName().charAt(indiceExtencion-3)+""+f.getName().charAt(indiceExtencion-2)+""+f.getName().charAt(indiceExtencion-1));
        return f.getName().substring(0, indiceExtencion < 0 ? f.getName().length() : indiceExtencion);
    }

    /**
     * devulebe "" si no tiene una extencion
     *
     * @param f
     * @return
     */
    public static String getExtencion(String direccion) {
        return getExtencion(new File(direccion));
    }

    /**
     * devulebe "" si no tiene una extencion
     *
     * @param f
     * @return
     */
    public static String getExtencion(File f) {
        return f.getName().contains(".") ? f.getName().substring(contieneStringContrario(f.getName(), "."), f.getName().length()) : "";
    }

    public static boolean contieneArchibosCarpeta(String direccion) {
        return contieneArchibosCarpeta(new File(direccion));
    }

    public static boolean contieneArchibosCarpeta(File f) {
        if (f.isDirectory()) {
            String contenido[] = f.list();
            for (String c : contenido) {
                File c2 = new File(f + "/" + c);
                if (c2.isFile()) {
                    return true;
                } else {
                    borrarContenidoDeCarpeta(c2);
                }
            }
            return false;
        }
        return true;

    }

    public static void borrarContenidoDeCarpeta(String direccion) {
        borrarContenidoDeCarpeta(new File(direccion));
    }

    public static void borrarContenidoDeCarpeta(File f) {
        if (f.isDirectory()) {
            System.out.println("borrando contenido carpeta " + f);
            String contenido[] = f.list();
            //  System.out.println(Arrays.toString(contenido));
            for (String c : contenido) {
                File c2 = new File(f + "/" + c);
                System.out.println("c2=" + c2);
                if (c2.isFile()) {
                    System.out.println("borrando");
                    System.out.println("delete " + c2.delete());
                } else {
                    borrarContenidoDeCarpeta(c2);
                }
            }
        } else {
            f.deleteOnExit();
        }
    }

    /**
     * copia dos Archibos de texto de original a copia no es mio pero funciona
     *
     * @param original
     * @param copia
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void copiar(File original, File copia) throws FileNotFoundException, IOException {
        if (original.isDirectory() || copia.isDirectory() || !original.exists()) {
            return;
        }
        if (!copia.exists()) {
            crearArchivo(copia);
        }
        BufferedInputStream lector = new BufferedInputStream(new FileInputStream(original));
        BufferedOutputStream escritor = new BufferedOutputStream(new FileOutputStream(copia));
        byte B[] = new byte[1000];
        int byteLeidos = lector.read(B);
        while (byteLeidos > 0) {
            escritor.write(B, 0, byteLeidos);
            byteLeidos = lector.read(B);
        }
        lector.close();
        escritor.close();
    }

    public static File adaptarExtencion(String direccion, String extencion, boolean sustituir) {
        return adaptarExtencion(new File(direccion), extencion, sustituir);
    }

    public static File adaptarExtencion(String direccion, String extencion) {
        return adaptarExtencion(new File(direccion), extencion, false);
    }

    public static File adaptarExtencion(File direccion, String extencion) {
        return adaptarExtencion(direccion, extencion, false);
    }

    public static File adaptarExtencion(File direccion, String extencion, boolean sustituir) {
        String direccionReal = direccion + "";
        if (!(direccionReal).contains(extencion) && !getExtencion(direccionReal).equals(extencion)) {
            direccionReal += extencion;
        }
        int numeroDeCopy = 2;
        String direccionReal2 = direccionReal;
        while (new File(direccionReal2).exists()) {
            if (sustituir) {
                new File(direccionReal2).delete();
                break;
            }
            direccionReal2 = direccionReal.substring(0, direccionReal.length() - extencion.length());
            direccionReal2 += "(" + numeroDeCopy + ")" + extencion;
            numeroDeCopy++;
        }
        return new File(direccionReal2);
    }

    public static Object cargarArchivoYCrearDeSerNesesario(Object objetoPrederterminado) {
        try {
            JFileChooser jf = new JFileChooser();
            jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jf.showDialog(null, "aceptar");
            return cargarArchivoYCrearDeSerNesesario(jf, objetoPrederterminado);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar el archivo", "Advertencia", JOptionPane.ERROR_MESSAGE);
        }
        return objetoPrederterminado;
    }

    public static Object cargarArchivoYCrearDeSerNesesario(JFileChooser jf, Object objetoPrederterminado) throws FileNotFoundException, IOException, ClassNotFoundException {
        return cargarArchivoYCrearDeSerNesesario(jf.getSelectedFile(), objetoPrederterminado);
    }

    public static Object cargarArchivoYCrearDeSerNesesario(File f, Object objetoPrederterminado) throws FileNotFoundException, IOException, ClassNotFoundException {
        return cargarArchivoYCrearDeSerNesesario(f, "", objetoPrederterminado);
    }

    public static Object cargarArchivoYCrearDeSerNesesario(String direccion, Object objetoPrederterminado) throws FileNotFoundException, IOException, ClassNotFoundException {
        return cargarArchivoYCrearDeSerNesesario(null, direccion, objetoPrederterminado);
    }

    private static Object cargarArchivoYCrearDeSerNesesario(File f, String direccion, Object objetoPrederterminado) throws FileNotFoundException, IOException, ClassNotFoundException {
        try {
            if (direccion.isEmpty()) {
                return cargarArchivo(f);
            } else {
                return cargarArchivo(direccion);
            }

        } catch (Exception ex) {
            if (direccion.isEmpty()) {
                crearArchivo(f, objetoPrederterminado);
                return cargarArchivo(f);
            } else {
                crearArchivo(direccion, objetoPrederterminado);
                return cargarArchivo(direccion);
            }

            //  return cargarArchivo(direccion);
        }
    }

    private static Object cargarArchivo() throws IOException, ClassNotFoundException {
        JFileChooser jf = new JFileChooser();
        jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jf.showDialog(null, "aceptar");
        return cargarArchivo(jf);
    }

    public static Object cargarArchivo(JFileChooser jf) throws IOException, ClassNotFoundException {
        return cargarArchivo(jf.getSelectedFile());
    }

    public static Object cargarArchivo(File direccion) throws IOException, ClassNotFoundException {
        return cargarArchivo(direccion, "");
    }

    public static Object cargarArchivo(String direccion) throws IOException, ClassNotFoundException {
        return cargarArchivo(null, direccion);
    }

    private static Object cargarArchivo(File f, String direccion) throws IOException, ClassNotFoundException {
        ObjectInputStream F3;
        if (direccion.isEmpty()) {
            F3 = new ObjectInputStream(new FileInputStream(f));
        } else {
            F3 = new ObjectInputStream(new FileInputStream(direccion));
        }

        Object O = F3.readObject();
        F3.close();
        return O;
    }

    public static void crearArchivo(Object objetoPrederterminado) {
        try {
            JFileChooser jf = new JFileChooser();
            jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jf.showDialog(null, "aceptar");
            crearArchivo(jf, objetoPrederterminado);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo crear el archivo", "Advertencia", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static void crearArchivo(JFileChooser jf, Object objetoPrederterminado, String extencion) throws IOException {
        crearArchivo(adaptarExtencion(jf.getSelectedFile(), extencion), objetoPrederterminado);
    }

    public static void crearArchivo(JFileChooser jf, Object objetoPrederterminado) throws IOException {
        crearArchivo(jf.getSelectedFile(), objetoPrederterminado);
    }

    public static void crearArchivo(File direccion, Object objetoPrederterminado) throws IOException {
        crearArchivo(direccion, "", objetoPrederterminado);
    }

    public static void crearArchivo(String direccion, Object objetoPrederterminado) throws IOException {
        crearArchivo(null, direccion, objetoPrederterminado);
    }

    private static void crearArchivo(File f, String direccion, Object objetoPrederterminado) throws IOException {
//        if(!f.exists()){
//            System.out.println("sssss");
//            new File(f.getParent()).mkdirs();
//            System.out.println("dddddd");
//          //  f.mkdirs();
//        f.createNewFile();
//        }

        ObjectOutputStream F;
        if (direccion.isEmpty()) {
//            System.out.println("aaaaaaaaaaaaaaaaaaaaa");
            F = new ObjectOutputStream(new FileOutputStream(f));

        } else {
//            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbb");
            F = new ObjectOutputStream(new FileOutputStream(direccion));
        }
        F.writeObject(objetoPrederterminado);
        F.close();
    }

    private static void crearArchivo(File f) throws IOException {
        f.createNewFile();
    }

    public static void formatearArchibo(String direccion) throws FileNotFoundException {
        formatearArchibo(new File(direccion));
    }

    public static void formatearArchibo(File direccion) throws FileNotFoundException {
        Formatter F = new Formatter(direccion);
    }

    public static Object cargarClassLast(String direccion) throws IOException {
        return cargarClassLast(new File(direccion));
    }

    public static Object cargarClassLast(File direccion) throws IOException {
        Vector O = cargarClass(direccion);
        // System.out.println("cantidad de objetos guardados=" + O.length);
        return O.get(O.size() - 1);
    }

    /**
     * Carga las clases guardadas dentro de un vector, devuelve un arreglo donde
     * la primera posición le corresponde a la penúltima clase guardada y
     * elimina la última, la segunda posición le corresponde a la cantidad de
     * elementos que quedan en el vector.
     *
     * @param direccion
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Object[] cargarClassPenultimoYEliminarSiguientesGetCantidad(File direccion) throws FileNotFoundException, IOException {
        Vector V = cargarClass(direccion);
        V.remove(V.size() - 1);
        System.out.println("cantidad de objetos guardados=" + V.size());
        formatearArchibo(direccion);
        guardarClass(direccion, V);
        return new Object[]{V.get(V.size() - 1), V.size()};
    }

    public static Object cargarClassPenultimoYEliminarSiguientes(String direccion) throws IOException {
        return cargarClassPenultimoYEliminarSiguientes(new File(direccion));
    }

    public static Object cargarClassPenultimoYEliminarSiguientes(File direccion) throws IOException {
        Vector O = cargarClass(direccion);
        O.remove(O.size() - 1);
        System.out.println("cantidad de objetos guardados=" + O.size());
        formatearArchibo(direccion);
        guardarClass(direccion, O);
//        ObjectOutputStream F = new ObjectOutputStream(new FileOutputStream(direccion));
//
//        for (int i = 0; i < O.length - 1; i++) {
//            F.writeObject(O[i]);
//        }

//        for (Object a : O) {
//             F.writeObject(a);
//        }
        //  F.close();
        return O.get(O.size() - 1);
    }

    public static Vector cargarClass(String direccion) throws IOException {
        return cargarClass(new File(direccion));
    }

    public static Vector cargarClass(File direccion) throws IOException {
        // LinkedList L = new LinkedList();
        Vector V = new Vector();
        try {
            ObjectInputStream F = new ObjectInputStream(new FileInputStream(direccion));
            V = (Vector) F.readObject();
//            while (true) {
//                L.add(F.readObject());
//            }

        } catch (IOException ex) {
            if (V.isEmpty()) {
                throw new IOException("No contiene clases");
            }
        } catch (ClassNotFoundException ex) {
            //  Logger.getLogger(Visual.class.getName()).log(Level.SEVERE, null, ex);
        }
        return V;
    }

    public static void guardarClass(String direccion, Object o) throws IOException {
        guardarClass(new File(direccion), o);
    }

    public static void guardarClass(File direccion, Object o) throws IOException {
        //  ObjectInputStream L = new ObjectInputStream(new FileInputStream(direccion));
        Vector V = new Vector();
        //  Object O[] = new Object[0];
        try {
            V = cargarClass(direccion);
        } catch (Exception ex) {

        }
        ObjectOutputStream F = new ObjectOutputStream(new FileOutputStream(direccion));
        if (o instanceof Vector) {
            V.addAll((Vector) o);
        } else {
            V.add(o);
        }

//        for (Object a : O) {
//            F.writeObject(a);
//        }
        F.writeObject(V);

        F.close();
    }

}
