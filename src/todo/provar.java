/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;

//import Utiles.ClasesUtiles.ComparadorOrdenAlfabetico;
import Utiles.ClasesUtiles.Comparadores.ComparadorOrdenAlfabetico;
import Utiles.ClasesUtiles.Tiempo2.Tiempo;
import Utiles.Exepciones.ExisteException;
import Utiles.Exepciones.NoPermitidoException;
import Utiles.MetodosUtiles.MetodosUtiles;
import static Utiles.MetodosUtiles.Tempus.*;
import static Utiles.MetodosUtiles.Arreglos.*;
import Utiles.MetodosUtiles.Videos;
import java.io.File;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rene
 */
public class provar {

    public static void main(String[] args) {
        try {
            File f[] = Videos.direccionesDeVideos("D:\\Rene\\ISCA\\Redes\\CCNA_R&Sv6_es_Modulo_1\\course");
//        for (int i = 0; i < f.length; i++) {
//            System.out.println(f[i]);
//        }
//        int f[] = {5, 6, 8, 2, 3};//Arrays.asList(converirArregloAObjetoInteger(f))
//        TreeSet<Integer> t = new TreeSet<Integer>(Arrays.asList(converirArregloAObjetoInteger(f)));
////        for (int i = 0; i < 10; i++) {
////            t.add(new Integer(i));
////        }
//        MetodosUtiles.mostrarSet(t.tailSet(5));
//        MetodosUtiles.mostrarSet(t.headSet(5));
////        TreeSet<Integer> t2 = new TreeSet<Integer>((TreeSet<Integer>) t.subSet(new Integer(4), new Integer(6)));
////        t.removeAll(t2);
////        MetodosUtiles.mostrarSet(t);
// Tiempo t1=new Tiempo( 0, 0,0,0,true);
// Tiempo t2=new Tiempo( 0, 0,1,0,true);
//   t1.restarConNegativos(t2);
// t1.aumentarConNegativos(t2);
//  System.out.println(t1);
//        System.out.println(String.format("%03d", 5));
//        System.out.println(MetodosUtiles.arreglarPalabra("ÁNGELES DE CHARLIE === Charlie's Angels "));
//        System.out.println(MetodosUtiles.arreglarChar('Á'));
//        ArrayList<String> a = new ArrayList();
//        a.add("Chicos buenos");
//        a.add("ÁNGELES DE CHARLIE === Charlie's Angels ");
//        Collections.sort(a, new ComparadorOrdenAlfabetico(true));
//        System.out.println(a.toString());

            String dire = "C:\\1\\Experimento\\Nueva carpeta";
            Videos.moverNumero(dire);
//            Videos.eliminarNumeroDelPricipio(dire);
//            Videos.moverNumero(dire);
        } catch (NoPermitidoException ex) {
            Logger.getLogger(provar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExisteException ex) {
            Logger.getLogger(provar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
