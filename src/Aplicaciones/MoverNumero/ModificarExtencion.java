/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.MoverNumero;

//import Utiles.ClasesUtiles.Admininistrador_De_Guardado;
//import Aplicaciones.modificarextencion.*;
import Utiles.ClasesUtiles.Admininistrador.Admininistrador_De_Guardado;
import Utiles.ClasesUtiles.Admininistrador.Ventana_Administrador;
import Utiles.ClasesUtiles.Configuraciones.Ventanas.Ventana_ConfiguracionDeVideo;
import Utiles.Exepciones.ExisteException;
import Utiles.Exepciones.NoEncontradoException;
import Utiles.Exepciones.NoPermitidoException;
import Utiles.Exepciones.PINException;
import static Utiles.MetodosUtiles.Archivo.*;
import static Utiles.MetodosUtiles.Visual.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Rene
 */
public class ModificarExtencion {

    public static Ventana_Principal ventanaPrincipal;
    public static Ventana_Administrador<File> ventana_Administrador;
    public static Ventana_ConfiguracionDeVideo ventana_ConfiguracionDeVideo;
    public static final String DIRECCION_ESTADO_ACTUAL = "Data/Mover Numero/Estado Actual.conf",
            DIRECCION_ADMINISTRADOR_CONFIGURACIONES = "Data/Mover Numero/Administrador de Configuraciones.ads", DIRECCION_ADMINISTRADOR_DIRECCIONES = "Data/Mover Numero/Administrador.adm";
//public static  MoverNumeroConfiguracion conf;
    public static JFrame todasLasVentanas[];

    /**
     * G:\experimento
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        estiloNimbus();
        new File("Data").mkdir();

        //File configuracion=new File("Data/Configuracion Mover Numero.conf");
        try {
//            if (!new File(DIRECCION_ESTADO_ACTUAL).exists()) {
//                crearArchivo(DIRECCION_ESTADO_ACTUAL, new MoverNumeroConfiguracion());
//            }
//            if (!new File(DIRECCION_ADMINISTRADOR_CONFIGURACIONES).exists()) {
//                crearArchivo(DIRECCION_ADMINISTRADOR_CONFIGURACIONES, new Admininistrador_De_Guardado<MoverNumeroConfiguracion>());
//            }
            ventanaPrincipal = new Ventana_Principal();
            ventanaPrincipal.setVisible(true);
        } catch (Exception ex) {
            responerException(ex);
        }

    }

//    protected static void responerException(Exception ex) {
//        if (ex instanceof NoEncontradoException || ex instanceof PINException || ex instanceof ExisteException || ex instanceof NoPermitidoException) {
//            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            ex.printStackTrace();
//            return;
//        }
////        if (ex instanceof PINException) {
////            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
////            return;
////        }
////        if (ex instanceof ExisteException) {
////            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
////            return;
////        }
//        if (ex instanceof IOException) {
//            JOptionPane.showMessageDialog(null, "No se pudo guardar el archibo", "Error", JOptionPane.ERROR_MESSAGE);
//              ex.printStackTrace();
//            return;
//        }
//        if (ex instanceof FileNotFoundException) {
//            JOptionPane.showMessageDialog(null, "No se encuentra el archibo", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        ex.printStackTrace();
//    }
    public static void mover(File f, String antesIngnorar[], String despuesIgnorar[], String union[]) {

        if (f.isDirectory()) {
            String contenido[] = f.list();
            //  System.out.println(Arrays.toString(contenido));
            for (String c : contenido) {
                File c2 = new File(f + "/" + c);
                if (c2.isDirectory()) {
                    continue;
                }
                mover(c2, antesIngnorar, despuesIgnorar, union);

            }
        } else {
            System.out.println("f.getName()=" + f.getName());
//            System.out.println(Arrays.toString(getCapitulosDeNombre(f.getName(), antesIngnorar, despuesIgnorar, union)));
        }
    }

}
