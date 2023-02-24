/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.editor_de_texto;

import static Utiles.MetodosUtiles.Visual.*;
import static Utiles.MetodosUtiles.MetodosUtiles.*;
import Utiles.ClasesUtiles.Comparadores.ComparadorOrdenAlfabetico;
import java.util.ArrayList;
import javax.swing.JTextArea;
import java.util.Collections;
//import java.util.StringTokenizer;

/**
 *
 * @author Rene
 */
public class Editor {

    private JTextArea T;
    private ArrayList<String> lineas = new ArrayList<String>();
    private ArrayList<String> palabras = new ArrayList<String>();
    Formato formato = Formato.LINEAS;
    boolean prioridadNumerica;
    private boolean SeparadorSpacio=true;

    public boolean isPrioridadNumerica() {
        return prioridadNumerica;
    }

    public void setPrioridadNumerica(boolean prioridadNumerica) {
        this.prioridadNumerica = prioridadNumerica;
    }

    public Editor(JTextArea T, boolean prioridadNumerica) {
        this.T = T;
        this.prioridadNumerica = prioridadNumerica;
        actualizarLineasYPalabras();
    }

    public boolean isSeparadorSpacio() {
        return SeparadorSpacio;
    }

    public void setSeparadorSpacio(boolean SeparadorSpacio) {
        this.SeparadorSpacio = SeparadorSpacio;
    }

   

    public Formato getFormato() {
        return formato;
    }

    public void setFormato(Formato formato) {

        this.formato = formato;
        // actualizarJTextArea();
    }

    public JTextArea getT() {
        return T;
    }

    public void setT(JTextArea T) {
        this.T = T;
        actualizarLineasYPalabras();
    }

    public ArrayList<String> getLineas() {
        return lineas;
    }

    public void setLineas(ArrayList<String> lineas) {
        this.lineas = lineas;
    }

    public ArrayList<String> getPalabras() {
        return palabras;
    }

    public void setPalabras(ArrayList<String> palabras) {
        this.palabras = palabras;
    }
//nnnnnnnnn

    public void ordenarAlfabeticamenteLasPalbrasEnLasLineas() {
        for (int i = 0; i < lineas.size(); i++) {
            ArrayList<String> A = StringLineAArrayListString(lineas.get(i));
            Collections.sort(A, new ComparadorOrdenAlfabetico(prioridadNumerica));
            lineas.set(i, ArrayListStringAStringLine(A, true));
        }
        actualizarJTextArea();
    }

    public void ordenarAlfabeticamenteLasPalbrasEnLasLineasYLasLineas() {
        ordenarAlfabeticamenteLasPalbrasEnLasLineas();
        ordenarAlfabeticamenteLasLineas(false);
    }

    public void reversePalabrasEnLineas() {
        for (int i = 0; i < lineas.size(); i++) {
            ArrayList<String> A = StringLineAArrayListString(lineas.get(i));
            Collections.reverse(A);
            lineas.set(i, ArrayListStringAStringLine(A, true));
        }
        actualizarJTextArea();
    }

    public void reversePalabrasEnLineasYLineas() {
        reversePalabrasEnLineas();
        reverseLineas();
    }

    //nnnnnnnnnnnnn
    public void ordenarAlfabeticamenteLasLineas(boolean contrario) {

        Collections.sort(lineas, new ComparadorOrdenAlfabetico(prioridadNumerica, contrario));

        actualizarJTextArea();
    }

    public void ordenarAlfabeticamenteLasPalabras(boolean contrario) {

        Collections.sort(palabras, new ComparadorOrdenAlfabetico(prioridadNumerica, contrario));

        actualizarJTextArea();
    }

    public void reverseLineas() {
        Collections.reverse(lineas);
        actualizarJTextArea();
    }

    public void reversePalabras() {
        Collections.reverse(palabras);
        actualizarJTextArea();
    }

    private void actualizarLineasYPalabras() {
        lineas = JTextAreaAArrayListStringLine(T);
        palabras = JTextAreaAArrayListString(T);
    }

    public void Mayusculas_Minusculas() {
        boolean mayusculas = true;
        For:
        for (int i = 0; i < palabras.size(); i++) {
            for (int j = 1; j < palabras.get(i).length(); j++) {
                char a = palabras.get(i).charAt(j);
                if (esCharLetraMayuscula(a)) {
                    mayusculas = false;
                    break For;
                }
            }
        }

        for (int i = 0; i < lineas.size(); i++) {
            lineas.set(i, mayusculas ? lineas.get(i).toUpperCase() : lineas.get(i).toLowerCase());
        }
        for (int i = 0; i < palabras.size(); i++) {
            palabras.set(i, mayusculas ? palabras.get(i).toUpperCase() : palabras.get(i).toLowerCase());
        }
        actualizarJTextArea();

    }

    public void comienzaConMayuscula() {
        for (int i = 0; i < palabras.size(); i++) {
            if (palabras.get(i).length() > 1) {
                palabras.set(i, palabras.get(i).substring(0, 1).toUpperCase() + palabras.get(i).substring(1).toLowerCase());
            }
        }

        for (int i = 0; i < lineas.size(); i++) {
            if (lineas.get(i).length() > 1) {
                lineas.set(i, lineas.get(i).substring(0, 1).toUpperCase() + lineas.get(i).substring(1).toLowerCase());
            }
        }

        actualizarJTextArea();

    }

    public void actualizarJTextArea() {
        // ArrayList<String> recorrido;
        // System.out.println("f=" + formato);
        //vvvvvvvvv
        //if (formato == Formato.LINEAS||formato == Formato.PALABRAS_EN_LINEAS) {
        //vvvvvvvvvvvv
        //nnnnnnn
        if (formato == Formato.LINEAS || formato == Formato.PALABRAS_EN_LINEAS || formato == Formato.PALABRAS_Y_LINEAS) {
            //nnnnnn
            // System.out.println(lineas.toString());
            sustituirArrayListStringAJTextAreaEnLineas(T, lineas,SeparadorSpacio);
            // System.out.println("lllll");
        } else {
            //System.out.println("aqui2");
            sustituirArrayListStringAJTextAreaEnPalabras(T, palabras,SeparadorSpacio);
        }
        actualizarLineasYPalabras();
    }

    public int cantidadDeLineas() {
        return lineas.size();
    }

    public int cantidadDePalabras() {
        return palabras.size();
    }

    public void organizarLasPalabrasEnLineas() {
        lineas.clear();
        // lineas=palabras;
        for (String a : palabras) {
            lineas.add(a);
        }
        //System.out.println(lineas.toString());
        setFormato(formato.LINEAS);
        actualizarJTextArea();
    }

    public void organizarLasLineasEnPalabras() {
//        palabras.clear();
//       // lineas=palabras;
//        for (String a : lineas) {
//            palabras.add(a);
//        }
//        //System.out.println(lineas.toString());
        setFormato(formato.PALABRAS);
        actualizarJTextArea();
    }

    public void limpiarLineas() {
        lineas = eliminarEspaciosDelPrincipioStringEnArrayListString(lineas);
    }
}
