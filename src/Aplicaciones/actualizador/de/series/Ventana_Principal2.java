/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.actualizador.de.series;

//import Utiles.ClasesUtiles.Interfases.AccionTabla;
import Utiles.ClasesUtiles.Interfases.IsSeleccionado;
import Aplicaciones.actualizador.de.series.Secciones.*;
import Utiles.ClasesUtiles.Admininistrador.Admininistrador_De_Guardado;
import Utiles.ClasesUtiles.Admininistrador.Ventana_Administrador;
import static Utiles.ClasesUtiles.Admininistrador.Ventana_Administrador.EXTENCION;
import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeTabla;
import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Configuraciones.Ventanas.TablasAmpliableConfiguracionDeVideo;
//import Utiles.ClasesUtiles.Configuraciones.Ventanas.Informacion_ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Configuraciones.Ventanas.Ventana_ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Interfases.Accion_Cargar_Cancelar;
import Utiles.ClasesUtiles.Interfases.editingTabla;
import Utiles.ClasesUtiles.Interfases.selectionFila;
import Utiles.ClasesUtiles.Multimedia.Series.CatalogoDeSeries;
import static Utiles.ClasesUtiles.Multimedia.Series.CatalogoDeSeries.EXTENCION_CATALGO;
import Utiles.ClasesUtiles.Multimedia.Paquete.*;
import Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaMangas.CarpetaMangas;
import Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaSeries.CarpetaSeries;
import Utiles.ClasesUtiles.Tablas.Tabla;
import Utiles.MetodosUtiles.Archivo;
import static Utiles.MetodosUtiles.Archivo.crearArchivo;
import Utiles.MetodosUtiles.MetodosUtiles;
import Utiles.MetodosUtiles.Visual;
import static Utiles.MetodosUtiles.Visual.responerException;
import Aplicaciones.actualizador.de.series.Catalogos.*;
//import actualizador.de.series.Catalogos.CatalogosDeSeries;
//import actualizador.de.series.Catalogos.SeccionDeCatalogosDeSerieEntrada;
//import Aplicaciones.actualizador.de.series.Secciones.*;
//import actualizador.de.series.Secciones.SeccionSerie;
//import actualizador.de.series.Secciones.estadoPersonalizado;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
//import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
//import static actualizador.de.series.Ventana_Administrador.EXTENCION;
//import com.sun.javafx.fxml.PropertyChangeEvent;
//import java.awt.Color;
//import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
//import static java.awt.image.ImageObserver.WIDTH;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
//import java.util.EventObject;
//import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javafx.scene.control.RadioButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
//import javax.swing.JComboBox;
//import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import static todo.Todo.cerrar;

/**
 *
 * @author Rene
 */
public class Ventana_Principal2 extends javax.swing.JFrame {

    //private InformacionesDeTablas inf; PorVerCatalogo.isEmpty()=
    private EstadoActual EA;
    private CatalogosActuales C;
    private int baseDireccionSelecionada, entradaDireccionSelecionada, baseInvalidoSeleccionada, entradaInvalidoSeleccionada, personalizadoTablaSeleccionado, baseSeleccionada = 0, personalizadoBaseCBSeleccionado = 0, tablaCarpetaSeleccionada, personalizadoEntradaCBSeleccionado;
    private final int ANCHO_SELECT = 40;
    private JFileChooser jf;
    private final String DIRECCION_ULTIMO_ESTADO = "Data/Actualizador de Series/Estado Actual Actualizador de Series.est", DIRECCION_ADMINISTRADOR_DIRECCIONES_MANGA = "Data/Actualizador de Series/Administrador de direcciones manga" + EXTENCION, DIRECCION_ADMINISTRADOR_INVALIDOS_MANGA = "Data/Actualizador de Series/Administrador de directorios invalidos" + EXTENCION,
            DIRECCION_ADMINISTRADOR_DIRECCIONES_SERIES = "Data/Actualizador de Series/Administrador de direcciones series" + EXTENCION, DIRECCION_ADMINISTRADOR_INVALIDOS_SERIES = "Data/Actualizador de Series/Administrador de directorios invalidos series" + EXTENCION,
            DIRECCION_ADMINISTRADOR_PERSONALIZADOS_BASE_SERIES = "Data/Actualizador de Series/Administrador de personalizados base series" + EXTENCION, DIRECCION_ADMINISTRADOR_PERSONALIZADOS_BASE_MANGA = "Data/Actualizador de Series/Administrador de personalizados base manga" + EXTENCION, DIRECCION_ADMINISTRADOR_TXT_SERIES = "Data/Actualizador de Series/Administrador de direcciones TXT series" + EXTENCION, DIRECCION_ADMINISTRADOR_TXT_MANGA = "Data/Actualizador de Series/Administrador de direcciones TXT manga" + EXTENCION, DIRECCION_ADMINISTRADOR_DIRECCION_CARPETA = "Data/Actualizador de Series/Administrador de direcciones de carpeta paquete" + EXTENCION, DIRECCION_ADMINISTRADOR_NOMBRES_CARPETA = "Data/Actualizador de Series/Administrador de direcciones de nombres de carpeta" + EXTENCION, DIRECCION_ADMINISTRADOR_PERSONALIZADOS_ENTRADA_SERIES = "Data/Actualizador de Series/Administrador de personalizados entrada series" + EXTENCION, DIRECCION_ADMINISTRADOR_PERSONALIZADOS_ENTRADA_MANGA = "Data/Actualizador de Series/Administrador de personalizados entrada manga" + EXTENCION,
            DIRECCION_ADMINISTRADOR_SECCIONES_BASE = "Data/Actualizador de Series/Administrador de secciones base" + EXTENCION, DIRECCION_ADMINISTRADOR_SECCIONES_ENTRADA = "Data/Actualizador de Series/Administrador de secciones entrada" + EXTENCION,
            DIRECCION_ADMINISTRADOR_CONFIGURACION_DE_VIDEO = "Data/Actualizador de Series/Direccion administrador configuracion de video" + EXTENCION;
//            DIRECCION_TABLAS_CONFIGURACION_DE_VIDEO = "Data/Direccion tablas configuracion de video" + EXTENCION;

    private boolean base = false, IncluirExtrenosAnime = true, IncluirExtrenosSerie = true;/// IncluirExtrenosEntradaAnime = true, IncluirExtrenosEntradaSerie = true;
    // private CatalogoDeSeries cb, ce;
    private ConfiguracionDeVideo cdv;
//    TablasAmpliableConfiguracionDeVideo T;
    private JCheckBox CBFiltrosBase[], CBFiltrosEntrada[];
    public static char CHAR_MARCAR = '◙', CHAR_DESMARCAR = '○';

    private enum direccionPaquete {

        CARPETAS, PAQUETE, CONTENIDO
    }
    private direccionPaquete dir;

    private enum get {

        TABLA_DIRECCION, TABLA_INVALIDO, DIRECCIONES, CATALOGO_BASE, CATALOGO_ENTRADA, CATALOGO_BASE_STRING, CATALOGO_ENTRADA_STRING, INF, ACTUALIZAR_BASE_ACTUAL, DIRECCION_ADMINISTRADOR_DIRECCIONES_ACTUAL,
        DIRECCION_ADMINISTRADOR_INVALIDOS_ACTUAL, C, SECCION_BASE, SECCION_ENTRADA,
        DIRECCION_ADMINISTRADOR_PERSONALIZADO_BASE_ACTUAL, DIRECCION_ADMINISTRADOR_TXT_ACTUAL, SECCION_CATALGO_ENTRADA, DIRECCION_ADMINISTRADOR_PERSONALIZADO_ENTRADA_ACTUAL,
        ACTUALIZAR_TODO_ENTRADA;

        private int indice;
        private CatalogoDeSeries c;

        public void setIndice(int indice) {
            this.indice = indice;
        }

        public int getIndice() {
            return indice;
        }

        public CatalogoDeSeries getC() {
            return c;
        }

        public void setC(CatalogoDeSeries c) {
            this.c = c;
        }

    }

    private class AccionAdministrador implements Accion_Cargar_Cancelar {

        Ventana_Administrador f;

//        public AccionAdministrador(Ventana_Administrador f) {
//            this.f = f;
//        }
        public void setF(Ventana_Administrador f) {
            this.f = f;
        }

        @Override
        public void aceptar_Y_cargar() {
            try {
                addObjeto(f.getInformacionSelecionada());
//            visualizarVentanPrincipal();
            } catch (Exception ex) {
                responerException(ex);
            }
            f.setVisible(false);
            setVisible(true);
        }

        @Override
        public void cancelar() {
            f.setVisible(false);
            setVisible(true);
        }

    }
    public static Ventana_Administrador<LinkedList<Direccion>> ventana_Administrador_Direcciones;
    public static Ventana_Administrador<LinkedList<DirectoriosInvalidos>> ventana_Administrador_Invalidos;
    public static Ventana_Administrador<LinkedList<SeccionPersonalizada>> ventana_Administrador_Personalizado_Base;
    public static Ventana_Administrador<File> ventana_Administrador_Direcciones_TXT;
    public static Ventana_Administrador<File> ventana_Administrador_Direcciones_Carpetas;
    public static Ventana_Administrador<LinkedList<NombreCarpeta>> ventana_Administrador_Nombre_Carpeta;
    public static Ventana_Administrador<LinkedList<SeccionPersonalizada>> ventana_Administrador_Personalizado_Entrada;
    public static Ventana_Administrador<SeccionesBase> ventana_Administrador_Secciones_Base;
    public static Ventana_Administrador<SeccionesEntrada> ventana_Administrador_Secciones_Entrada;
    public static Ventana_Principal2 ventana_Principal2;
    public static JFrame ventanas[];
    public static Ventana_ConfiguracionDeVideo ventana_ConfiguracionDeVideo;

    /**
     * Creates new form Ventana_Principal2
     */
    public Ventana_Principal2() {
        initComponents();
        try {
            new File("Data/Actualizador de Series").mkdirs();
            if (!new File(DIRECCION_ULTIMO_ESTADO).exists()) {
                crearArchivo(DIRECCION_ULTIMO_ESTADO, new EstadoActual());
            }
            try {
//                cdv = ConfiguracionDeVideo.getDefault();
//EA=new EstadoActual();
                EA = (EstadoActual) Archivo.cargarArchivo(DIRECCION_ULTIMO_ESTADO);
                cdv = EA.getT().getCdvSelecionado();
                C = new CatalogosActuales(EA, cdv);
                actualizarCBPersonalizadosEntrada();
            } catch (Exception exx) {

                System.out.println("errrrrrrrrrro");
                exx.printStackTrace();
                crearArchivo(DIRECCION_ULTIMO_ESTADO, new EstadoActual());
                EA = (EstadoActual) Archivo.cargarArchivo(DIRECCION_ULTIMO_ESTADO);
                cdv = EA.getT().getCdvSelecionado();
                C = new CatalogosActuales(EA, cdv);
                actualizarCBPersonalizadosEntrada();
                crearArchivo(DIRECCION_ADMINISTRADOR_DIRECCIONES_MANGA, new Admininistrador_De_Guardado<LinkedList<Direccion>>());
//            }
//            if (!new File(DIRECCION_ADMINISTRADOR_DIRECCIONES_SERIES).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_DIRECCIONES_SERIES, new Admininistrador_De_Guardado<LinkedList<Direccion>>());
//            }
//            if (!new File(DIRECCION_ADMINISTRADOR_INVALIDOS_MANGA).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_INVALIDOS_MANGA, new Admininistrador_De_Guardado<LinkedList<DirectoriosInvalidos>>());
//            }
//            if (!new File(DIRECCION_ADMINISTRADOR_INVALIDOS_SERIES).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_INVALIDOS_SERIES, new Admininistrador_De_Guardado<LinkedList<DirectoriosInvalidos>>());
//            }
//            if (!new File(DIRECCION_ADMINISTRADOR_PERSONALIZADOS_BASE_MANGA).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_PERSONALIZADOS_BASE_MANGA, new Admininistrador_De_Guardado<LinkedList<SeccionPersonalizada>>());
//            }
//            if (!new File(DIRECCION_ADMINISTRADOR_PERSONALIZADOS_BASE_SERIES).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_PERSONALIZADOS_BASE_SERIES, new Admininistrador_De_Guardado<LinkedList<SeccionPersonalizada>>());
//            }
//            if (!new File(DIRECCION_ADMINISTRADOR_TXT_MANGA).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_TXT_MANGA, new Admininistrador_De_Guardado<File>());
//            }
//            if (!new File(DIRECCION_ADMINISTRADOR_TXT_SERIES).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_TXT_SERIES, new Admininistrador_De_Guardado<File>());
//            }
//
//            if (!new File(DIRECCION_ADMINISTRADOR_DIRECCION_CARPETA).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_DIRECCION_CARPETA, new Admininistrador_De_Guardado<File>());
//            }
//            if (!new File(DIRECCION_ADMINISTRADOR_NOMBRES_CARPETA).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_NOMBRES_CARPETA, new Admininistrador_De_Guardado<LinkedList<NombreCarpeta>>());
//            }
//
//            if (!new File(DIRECCION_ADMINISTRADOR_PERSONALIZADOS_ENTRADA_MANGA).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_PERSONALIZADOS_ENTRADA_MANGA, new Admininistrador_De_Guardado<LinkedList<SeccionPersonalizada>>());
//            }
//            if (!new File(DIRECCION_ADMINISTRADOR_PERSONALIZADOS_ENTRADA_SERIES).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_PERSONALIZADOS_ENTRADA_SERIES, new Admininistrador_De_Guardado<LinkedList<SeccionPersonalizada>>());
//            }
//
//            if (!new File(DIRECCION_ADMINISTRADOR_SECCIONES_BASE).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_SECCIONES_BASE, new Admininistrador_De_Guardado<SeccionesBase>());
//            }
//            if (!new File(DIRECCION_ADMINISTRADOR_SECCIONES_ENTRADA).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_SECCIONES_ENTRADA, new Admininistrador_De_Guardado<SeccionesEntrada>());
//            }
//            if (!new File(DIRECCION_ADMINISTRADOR_CONFIGURACION_DE_VIDEO).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_CONFIGURACION_DE_VIDEO, new Admininistrador_De_Guardado<TablasAmpliableConfiguracionDeVideo>());
                //********************************
            }
            if (!new File(DIRECCION_ADMINISTRADOR_DIRECCIONES_MANGA).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_DIRECCIONES_MANGA, new Admininistrador_De_Guardado<LinkedList<Direccion>>());
            }
            if (!new File(DIRECCION_ADMINISTRADOR_DIRECCIONES_SERIES).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_DIRECCIONES_SERIES, new Admininistrador_De_Guardado<LinkedList<Direccion>>());
            }
            if (!new File(DIRECCION_ADMINISTRADOR_INVALIDOS_MANGA).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_INVALIDOS_MANGA, new Admininistrador_De_Guardado<LinkedList<DirectoriosInvalidos>>());
            }
            if (!new File(DIRECCION_ADMINISTRADOR_INVALIDOS_SERIES).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_INVALIDOS_SERIES, new Admininistrador_De_Guardado<LinkedList<DirectoriosInvalidos>>());
            }
            if (!new File(DIRECCION_ADMINISTRADOR_PERSONALIZADOS_BASE_MANGA).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_PERSONALIZADOS_BASE_MANGA, new Admininistrador_De_Guardado<LinkedList<SeccionPersonalizada>>());
            }
            if (!new File(DIRECCION_ADMINISTRADOR_PERSONALIZADOS_BASE_SERIES).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_PERSONALIZADOS_BASE_SERIES, new Admininistrador_De_Guardado<LinkedList<SeccionPersonalizada>>());
            }
            if (!new File(DIRECCION_ADMINISTRADOR_TXT_MANGA).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_TXT_MANGA, new Admininistrador_De_Guardado<File>());
            }
            if (!new File(DIRECCION_ADMINISTRADOR_TXT_SERIES).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_TXT_SERIES, new Admininistrador_De_Guardado<File>());
            }

            if (!new File(DIRECCION_ADMINISTRADOR_DIRECCION_CARPETA).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_DIRECCION_CARPETA, new Admininistrador_De_Guardado<File>());
            }
            if (!new File(DIRECCION_ADMINISTRADOR_NOMBRES_CARPETA).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_NOMBRES_CARPETA, new Admininistrador_De_Guardado<LinkedList<NombreCarpeta>>());
            }

            if (!new File(DIRECCION_ADMINISTRADOR_PERSONALIZADOS_ENTRADA_MANGA).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_PERSONALIZADOS_ENTRADA_MANGA, new Admininistrador_De_Guardado<LinkedList<SeccionPersonalizada>>());
            }
            if (!new File(DIRECCION_ADMINISTRADOR_PERSONALIZADOS_ENTRADA_SERIES).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_PERSONALIZADOS_ENTRADA_SERIES, new Admininistrador_De_Guardado<LinkedList<SeccionPersonalizada>>());
            }

            if (!new File(DIRECCION_ADMINISTRADOR_SECCIONES_BASE).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_SECCIONES_BASE, new Admininistrador_De_Guardado<SeccionesBase>());
            }
            if (!new File(DIRECCION_ADMINISTRADOR_SECCIONES_ENTRADA).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_SECCIONES_ENTRADA, new Admininistrador_De_Guardado<SeccionesEntrada>());
            }
            if (!new File(DIRECCION_ADMINISTRADOR_CONFIGURACION_DE_VIDEO).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_CONFIGURACION_DE_VIDEO, new Admininistrador_De_Guardado<TablasAmpliableConfiguracionDeVideo>());
            }
//System.out.println("a");
//            crearArchivo(DIRECCION_ULTIMO_ESTADO, new EstadoActual());
//            System.out.println("b");
            ventana_Principal2 = this;
            AccionAdministrador a1 = new AccionAdministrador(), a2 = new AccionAdministrador();
            ventana_Administrador_Direcciones = new Ventana_Administrador(get_DIRECCION_ADMINISTRADOR_DIRECCIONES_ACTUAL(), a1);
            a1.setF(ventana_Administrador_Direcciones);
            ventana_Administrador_Invalidos = new Ventana_Administrador(get_DIRECCION_ADMINISTRADOR_INVALIDOS_ACTUAL(), a2);
            a2.setF(ventana_Administrador_Invalidos);
            ventanas = new JFrame[]{this, ventana_Administrador_Direcciones, ventana_Administrador_Invalidos};
            //  BGEntrada.add(RSeguidosEntrada);
            //*************************************
//            System.out.println("c");

            CBFiltrosBase = new JCheckBox[]{CBEnEsperaBase, CBExtrenosBase, CBFinalizadasBase, CBMayoresBase, CBMenoresBase, CBPersonalizadosBase, CBPorVerBase, CBPrimerosBase, CBQueTengoBase, CBSeguidosBase, CBSoloBase, CBUltimosBase};
            CBFiltrosEntrada = new JCheckBox[]{CBEnEsperaEntrada, CBExtrenosEntrada, CBFinalizadasEntrada, CBMayoresEntrada, CBMenoresEntrada, CBPersonalizadosEntrada, CBPorVerEntrada, CBPrimerosEntrada, CBQueTengoEntrada, CBSeguidosEntrada, CBSoloEntrada, CBUltimosEntrada};

            baseDireccionSelecionada = entradaDireccionSelecionada = baseInvalidoSeleccionada = entradaInvalidoSeleccionada = personalizadoTablaSeleccionado
                    = tablaCarpetaSeleccionada = personalizadoEntradaCBSeleccionado = 0;
            // ANCHO_SELECT = 40;

            jf = new JFileChooser();
            jf.setFileFilter(new FileNameExtensionFilter("TXT y Catalogos (" + EXTENCION_CATALGO + ") ", "txt", EXTENCION_CATALGO));
            jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            crearPredeterminadoPersonalizadoBaseDeSerNecesario();
            actualizarTFPanelPaquete();
            //  actualizarTFDireccionCarpeta();
            actualizarTablaCarpetas();
            actualizarCBPersonalizadosBase();

            actualizarFiltroBase();
            actualizarFiltroEntrada();
            actualizarRFormaStringBase();
            actualizarRFormaStringEntrada();
            actualizarREstadoPersonalizado();

            actualizarTablas();
            actualizarTBase();
            actualizarTEntrada();
            actualizarMarcas();
            seguridad();

            setLocationRelativeTo(null);

            //  getCs().getEntradaTodo().getFaltanPorCopiar().getTodo().imprimir();
//            getCs().getEntradaClasicas().getFaltanPorCopiar().getSeguidos().imprimir();
//            System.out.println("e");
        } catch (Exception ex) {
            responerException(ex);
        }

    }
//************************  Cargar
//      try {
//            ventana_Principal2.addObjeto( getInformacionSelecionada());
//            visualizarVentanPrincipal();
//        } catch (Exception ex) {
//            responerException(ex);
//        }
    //************************ 
    //  visualizarVentanPrincipal();

    public void actualizarTFPanelPaquete() {
        actualizarTFDireccionCarpeta();
        actualizarTFDireccionContenido();
        actualizarTFDireccionPaquete();
        actualizarTFDireccionTXT();
    }

    public void actualizarTFDireccionCarpeta() {
        TFDireccionCarpetas.setText(EA.getCdc().getDireccion().toString());
    }

    public void actualizarTFDireccionContenido() {
        TFDireccionContenido.setText(EA.getContenido().toString());
    }

    public void actualizarTFDireccionPaquete() {
        TFDireccionPaquete.setText(EA.getPaquete().toString());
    }

    public void actualizarTFDireccionTXT() {
        TFDireccionTXT.setText(EA.getTXT().toString());
    }

    public SeccionPersonalizada getSeccionPersonalizadaBaseActual() {
        return CBManualBase.getSelectedIndex() != -1 ? getPersonalizadosBase().get(CBManualBase.getSelectedIndex()) : null;
    }

    public void actualizarTBase() {
        String a[] = {};
        if (baseSeleccionada == 1) {

            if (REscrituraManualBase.isEnabled()) {
                SeccionPersonalizada s = getSeccionPersonalizadaBaseActual();
                if (REscrituraManualBase.isSelected()) {
                    a = s.getLineas();
                }
                if (RDireccionesManualBase.isSelected()) {
                    a = (CBManualBase.getSelectedIndex() == -1 || getCs().getBasicos().getPersonalizadosDiereccion().isEmpty()) ? (new String[]{}) : getStringDeCatalgo(getCs().getBasicos().getPersonalizadosDiereccion().get(CBManualBase.getSelectedIndex()), true);
                }
                if (RCatalogoAlmacenado.isSelected()) {
//                    a = getStringDeCatalgo(getInf().getPersonalizadas().get(CBManualBase.getSelectedIndex()).getCatalogo(), true);
                    a = getStringDeCatalgo(getInf().getSeccionesBase().getPersonalizadas().get(CBManualBase.getSelectedIndex()).getCatalogo(), true);
                }
            }
        } else {
            a = getCatalogoBaseString();

        }
        Visual.setTextJTextArea(TBase, a);
    }

    public void actualizarTEntrada() {
        //System.out.println("entrada");
//        System.out.println("c c");
        String a[] = getCatalogoEntradaString();
//        System.out.println("c 2");
        //  de.imprimir();
//        Visual.setTextJTextArea(TEntraron, getCatalogoEntradaString());
        Visual.setTextJTextArea(TEntraron, a);
    }

    private CatalogoDeSeries getCatalogoEntrada() {
        try {
            return ((CatalogoDeSeries) getDeBaseActual(get.CATALOGO_ENTRADA));
        } catch (IOException ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private String[] getCatalogoEntradaString() {
        //  CatalogoDeSeries de = ((CatalogoDeSeries) getDeBaseActual(get.CATALOGO_ENTRADA));
        CatalogoDeSeries de = getCatalogoEntrada();
//        System.out.println("c c c");
        get g = get.CATALOGO_ENTRADA_STRING;
        g.setC(de);
        try {
            String[] A = (String[]) getDeBaseActual(g);
//            System.out.println("c c c c");
            return A;
        } catch (IOException ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private CatalogoDeSeries getCatalogoBase() {
        try {
            return ((CatalogoDeSeries) getDeBaseActual(get.CATALOGO_BASE));
        } catch (IOException ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private String[] getCatalogoBaseString() {
        //  CatalogoDeSeries de = ((CatalogoDeSeries) getDeBaseActual(get.CATALOGO_BASE));
        try {
            CatalogoDeSeries de = getCatalogoBase();
            get g = get.CATALOGO_BASE_STRING;
            g.setC(de);

            return (String[]) getDeBaseActual(g);
        } catch (IOException ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
//    public public getCatalogoDeBase(){
//    
//    }

    public void add(SeccionesBase s) throws FileNotFoundException {
        getInf().getSeccionesBase().addAll(s);
        actualizarBase();
        actualizarEntrada();
        actualizarCBPersonalizadosBase();

        actualizarFiltroBase();
//        actualizarFiltroEntrada();
        actualizarRFormaStringBase();
//        actualizarRFormaStringEntrada();
        actualizarREstadoPersonalizado();

        actualizarDireccionBase();
//        actualizarDireccionEntrada();
        actualizarTablaSeleccionadosPersonalizados();
        actualizarTBase();
        actualizarTEntrada();
        actualizarMarcas();
        seguridad();
    }

    public void add(SeccionesEntrada s) throws FileNotFoundException {
        getInf().getSeccionesEntrada().addAll(s);
        actualizarEntrada();
        actualizarCBPersonalizadosBase();

//        actualizarFiltroBase();
        actualizarFiltroEntrada();
//        actualizarRFormaStringBase();
        actualizarRFormaStringEntrada();
//        actualizarREstadoPersonalizado();

//        actualizarDireccionBase();
        actualizarDireccionEntrada();
//        actualizarTablaSeleccionadosPersonalizados();
        actualizarTBase();
        actualizarTEntrada();
        actualizarMarcas();
//        actualizarBase();
//        aa;
        seguridad();
    }

    public void add(File f) throws FileNotFoundException {
        if (TPTodo.getSelectedIndex() == 0) {
            setFileEnTF_TXT(f);
        } else {
            switch (dir) {
                case CARPETAS:
                    setFileEnTF_DireccionCarpeta(f);
                    break;
                case CONTENIDO:
                    setFileEnTF_DireccionContenido(f);
                    break;
                case PAQUETE:
                    setFileEnTF_DireccionPaquete(f);
                    break;
            }

        }
        seguridad();
    }

    public void setFileEnTF_DireccionCarpeta(File f) {
        EA.getCdc().setDireccion(f);
        TFDireccionCarpetas.setText(f.toString());
    }

    public void setFileEnTF_DireccionPaquete(File f) {
        EA.setPaquete(f);
        TFDireccionPaquete.setText(f.toString());
    }

    public void setFileEnTF_DireccionContenido(File f) {
        EA.setContenido(f);
        TFDireccionContenido.setText(f.toString());
    }

    public void addObjeto(Object o) throws FileNotFoundException {
        if (o instanceof LinkedList) {
            add((LinkedList) o);
            return;
        }
        if (o instanceof File) {
            add((File) o);
            return;
        }
        if (o instanceof SeccionesBase) {
            add((SeccionesBase) o);
            return;
        }
        if (o instanceof SeccionesEntrada) {
            add((SeccionesEntrada) o);
//            return ;
        }
    }

    public void add(LinkedList l) throws FileNotFoundException {
        if (!l.isEmpty()) {
            if (l.get(0) instanceof Direccion) {
                if (base) {
                    getDireccionesBaseActual().addAll(l);
                    actualizarDireccionBase();
                    actualizarBaseActual();
                    actualizarEntrada();
                    actualizarTBase();
                    actualizarTEntrada();
                } else {
//                    System.out.println("a");
                    getEntrada().addAll(l);
//                    System.out.println("b");
                    actualizarDireccionEntrada();

//                    System.out.println("d");
                    actualizarEntradaActual();
                    getInf().getSeccionesEntrada().actualizarDirecionesEntradaTodo();
                    actualizarTodoEntradaSiEsNecesario();
//                    System.out.println("c");
                    actualizarTEntrada();
//                    System.out.println("e");
                }
                actualizarMarcas();
                seguridad();
                return;
            }
            if (l.get(0) instanceof DirectoriosInvalidos) {
                if (base) {
                    getInvalidosBaseActual().addAll(l);
                    actualizarInvalidosBase(baseInvalidoSeleccionada);
                    actualizarBaseActual();
                    actualizarEntrada();
                    actualizarTBase();
                    actualizarTEntrada();
                } else {
                    getInvalidosEntradaActual().addAll(l);
                    actualizarInvalidosEntrada(entradaInvalidoSeleccionada);
                    actualizarEntradaActual();
                    getInf().getSeccionesEntrada().actualizarDirecionesEntradaTodo();
                    actualizarTodoEntradaSiEsNecesario();
                    actualizarTEntrada();
                }

            }
            if (l.get(0) instanceof SeccionPersonalizada) {
                if (base) {
//                    getPersonalizadosBase().addAll(l);
                    getInf().getSeccionesBase().personalizadoAddAll(l);
                    actualizarCBPersonalizadosBase();
                    actualizarREstadoPersonalizado();
                    actualizarFiltroBase();
                    actualizarBaseActual();
                    actualizarEntrada();
//                    actualizarTodoEntradaSiEsNecesario();
                    actualizarTBase();
                    actualizarTEntrada();
                    actualizarTablaSeleccionadosPersonalizados();
                } else {
//                    getInf().getSeccionesEntrada().getPersonalizadas().addAll(l);
                    getInf().getSeccionesEntrada().personalizadoAddAll(l);
                    actualizarCBPersonalizadosEntrada();
                    actualizarFiltroEntrada();
                    actualizarEntradaActual();
                    actualizarTodoEntradaSiEsNecesario();
//                actualizarTBase();
                    actualizarTEntrada();
                }

            }
            if (l.get(0) instanceof NombreCarpeta) {
                EA.getCdc().getNombresCarpetas().addAll(l);
                actualizarTablaCarpetas();
                seguridad();
            }
        }
        actualizarMarcas();
        seguridad();
    }
//   public void actualizarTBase(){
//   
//   
//   }

    /**
     * indice=-1 si lo que se quiere son las direcciones
     *
     * @param indice
     * @return
     */
    private Tabla getTablaBaseActual(int indice) {
        get g = get.TABLA_DIRECCION;
        g.setIndice(indice);
        if (indice >= 0) {
            g = get.TABLA_INVALIDO;
            // g.setIndice(indice);
        }

        try {
            return (Tabla) getDeBaseActual(g);
        } catch (IOException ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private CatalogosDeSeries getCs() {
        try {
            return (CatalogosDeSeries) getDeBaseActual(get.C);
        } catch (IOException ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private InformacionDeSecciones getInf() {
        try {
            return (InformacionDeSecciones) getDeBaseActual(get.INF);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void actualizarTodasLasBases() {
        try {
            C.getAnime().getBasicos().actualizar(cdv);
            C.getSeries().getBasicos().actualizar(cdv);
        } catch (Exception ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void actualizarBase() {
        try {
            getCs().getBasicos().actualizar(cdv);
        } catch (Exception ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CatalogoDeSeries actualizarBaseActual() {
        try {
            return (CatalogoDeSeries) getDeBaseActual(get.ACTUALIZAR_BASE_ACTUAL);
        } catch (IOException ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String get_DIRECCION_ADMINISTRADOR_TXT_ACTUAL() {
        try {
            return (String) getDeBaseActual(get.DIRECCION_ADMINISTRADOR_TXT_ACTUAL);
        } catch (Exception ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String get_DIRECCION_ADMINISTRADOR_PERSONALIZADO_BASE_ACTUAL() {
        try {
            return (String) getDeBaseActual(get.DIRECCION_ADMINISTRADOR_PERSONALIZADO_BASE_ACTUAL);
        } catch (Exception ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String get_DIRECCION_ADMINISTRADOR_PERSONALIZADO_ENTRADA_ACTUAL() {
        try {
            return (String) getDeBaseActual(get.DIRECCION_ADMINISTRADOR_PERSONALIZADO_ENTRADA_ACTUAL);
        } catch (Exception ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String get_DIRECCION_ADMINISTRADOR_DIRECCIONES_ACTUAL() {
        try {
            return (String) getDeBaseActual(get.DIRECCION_ADMINISTRADOR_DIRECCIONES_ACTUAL);
        } catch (Exception ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String get_DIRECCION_ADMINISTRADOR_INVALIDOS_ACTUAL() {
        try {
            return (String) getDeBaseActual(get.DIRECCION_ADMINISTRADOR_INVALIDOS_ACTUAL);
        } catch (Exception ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int indiceCBSeccion() {
        return CBPersonalizadosSeccion.getSelectedIndex();
    }

    public SeccionDeCatalogosDeSerieEntrada getSeccionEntradaPersonalizadaActual() {
        return getCs().getPersonalizadasEntrada().get(indiceCBSeccion());
    }

    public void actualizarTodoEntradaSiEsNecesario() {
        if (!RTodoSeccion.isSelected()) {
            actualizarTodoEntrada();
        }
        getInf().getSeccionesEntrada().actualizarDirecionesEntradaTodo();
    }

    public void actualizarTodoEntrada() {
        try {
            getDeBaseActual(get.ACTUALIZAR_TODO_ENTRADA);
        } catch (Exception ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * indice=-1 si lo que se quiere son las tablas de direcciones<br>
     * indice=-2 si lo que se quiere son los objetos direccion
     *
     * @param indice
     * @return
     */
    private Object getDeBaseActual(get g) throws FileNotFoundException, IOException, ClassNotFoundException {
        InformacionDeSecciones inf;
        CatalogosDeSeries Cs;
        if (RAnime.isSelected()) {

            if (g == g.DIRECCION_ADMINISTRADOR_DIRECCIONES_ACTUAL) {
//                System.out.println("manga");
//                System.out.println("DIRECCION_ADMINISTRADOR_DIRECCIONES_MANGA="+DIRECCION_ADMINISTRADOR_DIRECCIONES_MANGA);
                return DIRECCION_ADMINISTRADOR_DIRECCIONES_MANGA;
            }
            if (g == g.DIRECCION_ADMINISTRADOR_INVALIDOS_ACTUAL) {
                return DIRECCION_ADMINISTRADOR_INVALIDOS_MANGA;
            }
            if (g == g.DIRECCION_ADMINISTRADOR_PERSONALIZADO_BASE_ACTUAL) {
                return DIRECCION_ADMINISTRADOR_PERSONALIZADOS_BASE_MANGA;
            }
            if (g == g.DIRECCION_ADMINISTRADOR_TXT_ACTUAL) {
                return DIRECCION_ADMINISTRADOR_TXT_MANGA;
            }
            if (g == g.DIRECCION_ADMINISTRADOR_PERSONALIZADO_ENTRADA_ACTUAL) {
                return DIRECCION_ADMINISTRADOR_PERSONALIZADOS_ENTRADA_MANGA;
            }
            inf = EA.getAnime();
            Cs = C.getAnime();
        } else {

            if (g == g.DIRECCION_ADMINISTRADOR_DIRECCIONES_ACTUAL) {
//                System.out.println("series");
//                System.out.println("DIRECCION_ADMINISTRADOR_DIRECCIONES_SERIES="+DIRECCION_ADMINISTRADOR_DIRECCIONES_SERIES);
                return DIRECCION_ADMINISTRADOR_DIRECCIONES_SERIES;
            }
            if (g == g.DIRECCION_ADMINISTRADOR_INVALIDOS_ACTUAL) {
                return DIRECCION_ADMINISTRADOR_INVALIDOS_SERIES;
            }
            if (g == g.DIRECCION_ADMINISTRADOR_PERSONALIZADO_BASE_ACTUAL) {
                return DIRECCION_ADMINISTRADOR_PERSONALIZADOS_BASE_SERIES;
            }
            if (g == g.DIRECCION_ADMINISTRADOR_TXT_ACTUAL) {
                return DIRECCION_ADMINISTRADOR_TXT_SERIES;
            }
            if (g == g.DIRECCION_ADMINISTRADOR_PERSONALIZADO_ENTRADA_ACTUAL) {
                return DIRECCION_ADMINISTRADOR_PERSONALIZADOS_ENTRADA_SERIES;
            }
            inf = EA.getSeries();
            Cs = C.getSeries();
        }
        if (g == g.INF) {
            return inf;
        }
        if (g == g.C) {
            return Cs;
        }
        if (g == g.ACTUALIZAR_TODO_ENTRADA) {
            Cs.getEntradaTodo().actualizarCatalogos(CBTodoExtrenosSeccion.isSelected(), cdv);
            inf.getSeccionesEntrada().actualizarDirecionesEntradaTodo();
            return Cs.getEntradaTodo();
        }
        SeccionDeCatalogosDeSerieEntrada S = null;
        if (RClasicasSeccion.isSelected()) {
            S = Cs.getEntradaClasicas();
        }
        if (RTXSeccion.isSelected()) {
            S = Cs.getEntradaTX();
        }
        if (RDobladasSeccion.isSelected()) {
            S = Cs.getEntradaDobladas();
        }
        if (RPersonalizadosSeccion.isSelected()) {
            S = getSeccionEntradaPersonalizadaActual();
        }
        if (RTodoSeccion.isSelected()) {
            S = Cs.getEntradaTodo();
        }
        if (RFinalizadasSeccion.isSelected()) {
            S = Cs.getEntradaFinalizadas();
        }
        if (g == g.SECCION_CATALGO_ENTRADA) {
            return S;
        }
        if (g == g.SECCION_ENTRADA) {
            return S.getEntrada().getEntradaCorrespondiente();
            //  return inf.getEntrada();
        }
        if (g != g.CATALOGO_ENTRADA && g != g.CATALOGO_ENTRADA_STRING) {
            if (baseSeleccionada == 1) {
                switch (g) {
                    case TABLA_DIRECCION:
                        return ((SeccionPersonalizada) getDeBaseActual(g.SECCION_BASE)).getTabla();
                    case TABLA_INVALIDO:
                        return ((SeccionPersonalizada) getDeBaseActual(g.SECCION_BASE)).getTablaInvalidos(g.getIndice());
                    case DIRECCIONES:

                        return (inf.getSeccionesBase().getPersonalizadas().isEmpty() || CBManualBase.getSelectedIndex() == -1) ? (null) : inf.getSeccionesBase().getPersonalizadas().get(CBManualBase.getSelectedIndex()).getDirecciones();
                    case CATALOGO_BASE:
                        if (!inf.getSeccionesBase().getPersonalizadas().isEmpty()) {
                            switch (inf.getSeccionesBase().getPersonalizadas().get(CBManualBase.getSelectedIndex()).getEstado()) {
                                case CATALOGO:
                                    // return inf.getPersonalizadas().get(CBManualBase.getSelectedIndex()).getCatalogo();
                                    return inf.getSeccionesBase().getPersonalizadas().get(CBManualBase.getSelectedIndex()).getCatalogo();
                                case DIRECCIONES:
                                    return Cs.getBasicos().getPersonalizadosDiereccion().get(CBManualBase.getSelectedIndex());
                                case ESCRITURA:
                                    return Cs.getBasicos().getPersonalizadosString().get(CBManualBase.getSelectedIndex());
                            }
                        }

                        return null;
                    case CATALOGO_BASE_STRING:
                        return getStringDeCatalgo(g.getC(), true);
                    case ACTUALIZAR_BASE_ACTUAL:
                        Cs.getBasicos().actualizarPersonalizados(cdv);
                        return getDeBaseActual(g.CATALOGO_BASE);
                    case SECCION_BASE:
                        // return inf.getPersonalizadas().get(CBManualBase.getSelectedIndex());
                        return inf.getSeccionesBase().getPersonalizadas().get(CBManualBase.getSelectedIndex());
                }
            } else {
                if (REnEspera.isSelected()) {
                    switch (g) {
                        case TABLA_DIRECCION:
                            // return inf.getTablaDireccionEnEspera();
                            return inf.getSeccionesBase().getEnEspera().getTabla();
                        case TABLA_INVALIDO:
                            //return inf.getTablaInvalidosEnEspera(g.getIndice());
                            return inf.getSeccionesBase().getEnEspera().getTablaInvalidos(g.getIndice());
                        case DIRECCIONES:
                            return inf.getSeccionesBase().getEnEspera().getDirecciones();
                        case CATALOGO_BASE:
                            //return inf.getEnEsperaCatalogo();
                            return Cs.getBasicos().getEnEspera();
                        case CATALOGO_BASE_STRING:
                            // return g.getC().getNombresDeSeries();
                            return getStringDeCatalgo(g.getC(), true);
                        case ACTUALIZAR_BASE_ACTUAL:
                            Cs.getBasicos().actualizarEnEspera(cdv);
                            return Cs.getBasicos().getEnEspera();
                        //  inf.actualizarCatalogoDeSeriesEnEspera(cdv);
                        //  return inf.getEnEsperaCatalogo();
                        case SECCION_BASE:
                            return inf.getSeccionesBase().getEnEspera();
                    }
                }
                if (RPorVer.isSelected()) {
                    switch (g) {
                        case TABLA_DIRECCION:
//                        System.out.println("a");
                            return inf.getSeccionesBase().getPorVer().getTabla();
                        // return inf.getTablaDireccionPorVer();
                        case TABLA_INVALIDO:
//                        System.out.println("b");
                            //return inf.getTablaInvalidosPorVer(g.getIndice());
                            return inf.getSeccionesBase().getPorVer().getTablaInvalidos(g.getIndice());
                        case DIRECCIONES:
//                        System.out.println("c");
                            return inf.getSeccionesBase().getPorVer().getDirecciones();
                        case CATALOGO_BASE:
//                        System.out.println("d");
                            //  return inf.getPorVerCatalogo();
                            return Cs.getBasicos().getPorVer();
                        case CATALOGO_BASE_STRING:
//                        System.out.println("e");
                            // return g.getC().getNombresDeSeries();
                            return getStringDeCatalgo(g.getC(), true);
                        case ACTUALIZAR_BASE_ACTUAL:
//                        System.out.println("e");
                            Cs.getBasicos().actualizarPorVer(cdv);
                            return Cs.getBasicos().getPorVer();
                        //  inf.actualizarCatalogoDeSeriesPorVer(cdv);
                        // return inf.getPorVerCatalogo();
                        case SECCION_BASE:
//                        System.out.println("f");
                            return inf.getSeccionesBase().getPorVer();
                    }
                    //  return indice == -1 ? inf.getTablaDireccionPorVer() : indice != -2 ? inf.getTablaInvalidosPorVer(indice) : inf.getPorVer();
                }
                if (RQueTengo.isSelected()) {
                    switch (g) {
                        case TABLA_DIRECCION:
                            // return inf.getTablaDireccionQueTengo();
                            return inf.getSeccionesBase().getQueTengo().getTabla();
                        case TABLA_INVALIDO:
                            return inf.getSeccionesBase().getQueTengo().getTablaInvalidos(g.getIndice());
                        // return inf.getTablaInvalidosQueTengo(g.getIndice());
                        case DIRECCIONES:
                            return inf.getSeccionesBase().getQueTengo().getDirecciones();
                        case CATALOGO_BASE:
                            //   return inf.getQueTengoCatalogo();
                            return Cs.getBasicos().getQueTengo();
                        case CATALOGO_BASE_STRING:
                            //  return g.getC().getNombresDeCapitulos();
                            return getStringDeCatalgo(g.getC(), true);
                        case ACTUALIZAR_BASE_ACTUAL:
                            Cs.getBasicos().actualizarQueTengo(cdv);
                            return Cs.getBasicos().getQueTengo();
                        //  inf.actualizarCatalogoDeSeriesQueTengo(cdv);
                        // return inf.getQueTengoCatalogo();
                        case SECCION_BASE:
                            return inf.getSeccionesBase().getQueTengo();
                    }
                    //  return indice == -1 ? inf.getTablaDireccionQueTengo() : indice != -2 ? inf.getTablaInvalidosQueTengo(indice) : inf.getQueTengo();
                }
                if (RSeguidos.isSelected()) {
                    switch (g) {
                        case TABLA_DIRECCION:
                            //  return inf.getTablaDireccionSeguidos();
                            return inf.getSeccionesBase().getSeguidos().getTabla();
                        case TABLA_INVALIDO:
                            // return inf.getTablaInvalidosSeguidos(g.getIndice());
                            return inf.getSeccionesBase().getSeguidos().getTablaInvalidos(g.getIndice());
                        case DIRECCIONES:
                            return inf.getSeccionesBase().getSeguidos().getDirecciones();
                        case CATALOGO_BASE:
                            //  return inf.getSeguidosCatalogo();
                            return Cs.getBasicos().getSeguidos();
                        case CATALOGO_BASE_STRING:
                            // return g.getC().getNombresDeSeries();
                            return getStringDeCatalgo(g.getC(), true);
                        case ACTUALIZAR_BASE_ACTUAL:
                            // inf.actualizarCatalogoDeSeriesSeguidos(cdv);
//                        System.out.println("aaaaaaaaaaaaaaa");
//                      return inf.getSeguidosCatalogo();
//                        System.out.println("act");
                            Cs.getBasicos().actualizarSeguidos(cdv);
                            return Cs.getBasicos().getSeguidos();
                        case SECCION_BASE:
                            return inf.getSeccionesBase().getSeguidos();
                    }
                    // return indice == -1 ? inf.getTablaDireccionSeguidos() : indice != -2 ? inf.getTablaInvalidosSeguidos(indice) : inf.getSeguidos();
                }
                if (RFinalizadas.isSelected()) {
                    switch (g) {
                        case TABLA_DIRECCION:
                            return inf.getSeccionesBase().getFinalizadas().getTabla();
                        // return inf.getTablaDireccionFinalizadas();
                        case TABLA_INVALIDO:
                            // return inf.getTablaInvalidosFinalizadas(g.getIndice());
                            return inf.getSeccionesBase().getFinalizadas().getTablaInvalidos(g.getIndice());
                        case DIRECCIONES:
                            return inf.getSeccionesBase().getFinalizadas().getDirecciones();
                        case CATALOGO_BASE:
                            //  return inf.getFinalizadasCatalogo();
                            return Cs.getBasicos().getFinalizadas();
                        case CATALOGO_BASE_STRING:
                            //  return g.getC().getNombresDeSeries();
                            return getStringDeCatalgo(g.getC(), true);
                        case ACTUALIZAR_BASE_ACTUAL:
////                        inf.actualizarCatalogoDeSeriesFinalizadas(cdv);
////                        return inf.getFinalizadasCatalogo();
                            Cs.getBasicos().actualizarFinalizadas(cdv);
                            return Cs.getBasicos().getFinalizadas();
                        case SECCION_BASE:
                            return inf.getSeccionesBase().getFinalizadas();
                    }
                    // return indice == -1 ? inf.getTablaDireccionEnEspera() : indice != -2 ? inf.getTablaInvalidosEnEspera(indice) : inf.getEnEspera();
                }
            }

        } else {
            if (RFaltanPorCopiar.isSelected()) {
                if (RPersonalizados.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            return S.getFaltanPorCopiar().getPersonalizados();
                        //  return Cs.getFaltanPorCopiar().getPersonalizados();
                        case CATALOGO_ENTRADA_STRING:
                            return getStringDeCatalgo(g.getC(), false);

                    }
                }
                if (RTodo.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            //  return Cs.getFaltanPorCopiar().getTodo();
                            return S.getFaltanPorCopiar().getTodo();
                        case CATALOGO_ENTRADA_STRING:
                            return getStringDeCatalgo(g.getC(), false);

                    }
                }
                if (RSeguidosEntrada.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            //   return Cs.getFaltanPorCopiar().getSeguidos();
                            return S.getFaltanPorCopiar().getSeguidos();
                        case CATALOGO_ENTRADA_STRING:
                            return getStringDeCatalgo(g.getC(), false);
                    }
                }
                if (RExtrenos.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            //   return Cs.getFaltanPorCopiar().getExtrenos();
                            return S.getFaltanPorCopiar().getExtrenos();
                        case CATALOGO_ENTRADA_STRING:
                            return getStringDeCatalgo(g.getC(), false);
                    }
                }
                if (REnEsperaEntrada.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            //   return Cs.getFaltanPorCopiar().getEnEspera();
                            return S.getFaltanPorCopiar().getEnEspera();
                        case CATALOGO_ENTRADA_STRING:
                            return getStringDeCatalgo(g.getC(), false);
                    }
                }
                if (RPorVerEntrada.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            //    return Cs.getFaltanPorCopiar().getPorVer();
                            return S.getFaltanPorCopiar().getPorVer();
                        case CATALOGO_ENTRADA_STRING:
                            return getStringDeCatalgo(g.getC(), false);
                    }
                }
                if (RFinalizadasEntrada.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            //    return Cs.getFaltanPorCopiar().getFinalizadas();
                            return S.getFaltanPorCopiar().getFinalizadas();
                        case CATALOGO_ENTRADA_STRING:
//                            System.out.println("c 0");
                            return getStringDeCatalgo(g.getC(), false);
                    }
                }
            }
            if (REntraron.isSelected()) {
                if (RPersonalizados.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            //   return Cs.getEntrada().getPersonalizadosRelacionados();
                            return S.getEntrada().getPersonalizadosRelacionados();
                        case CATALOGO_ENTRADA_STRING:
                            return getStringDeCatalgo(g.getC(), false);

                    }
                }
                if (RTodo.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            //  return Cs.getEntrada().getTodo();
                            return S.getEntrada().getTodo();
                        case CATALOGO_ENTRADA_STRING:
                            return getStringDeCatalgo(g.getC(), false);
                    }
                }
                if (RSeguidosEntrada.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            // return Cs.getEntrada().getSeguidos();
                            return S.getEntrada().getSeguidos();
                        case CATALOGO_ENTRADA_STRING:
                            return getStringDeCatalgo(g.getC(), false);
                    }
                }
                if (RExtrenos.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            //      return Cs.getEntrada().getExtrenos();
                            return S.getEntrada().getExtrenos();
                        case CATALOGO_ENTRADA_STRING:
                            return getStringDeCatalgo(g.getC(), false);
                    }
                }
                if (REnEsperaEntrada.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            //       return Cs.getEntrada().getEnEspera();
                            return S.getEntrada().getEnEspera();
                        case CATALOGO_ENTRADA_STRING:
                            return getStringDeCatalgo(g.getC(), false);
                    }
                }
                if (RPorVerEntrada.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            // return Cs.getEntrada().getPorVer();
                            return S.getEntrada().getPorVer();
                        case CATALOGO_ENTRADA_STRING:
                            return getStringDeCatalgo(g.getC(), false);
                    }
                }
                if (RFinalizadasEntrada.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            return S.getEntrada().getFinalizadas();
                        case CATALOGO_ENTRADA_STRING:
                            return getStringDeCatalgo(g.getC(), false);
                    }
                }
            }

            if (RFaltaron.isSelected()) {
                if (RPersonalizados.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            return S.getFaltantes().getPersonalizados();
                        case CATALOGO_ENTRADA_STRING:
                            return getStringDeCatalgo(g.getC(), false);

                    }
                    // return inf.getCatalogoDeSeriesFaltanPorCopiarTodo(cdv);
                }
                if (RTodo.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            //return inf.getEntraronTodoCatalogo();
                            return S.getFaltantes().getTodo();
                        case CATALOGO_ENTRADA_STRING:
                            // return g.getC().getNombresDeCapitulos();
                            return getStringDeCatalgo(g.getC(), false);
                    }
                    //  return inf.getCatalogoDeSeriesEntraronTodo(cdv);
                }
                if (RSeguidosEntrada.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            //return inf.getEntraronSeguidosCatalogo();
                            return S.getFaltantes().getSeguidos();
                        case CATALOGO_ENTRADA_STRING:
                            //return g.getC().getNombresDeCapitulos();
                            return getStringDeCatalgo(g.getC(), false);
                    }
                    //     return inf.getCatalogoDeSeriesEntraronSeguidos(cdv);
                }
                if (RExtrenos.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            // return inf.getEntraronExtrenosCatalogo();
                            return S.getFaltantes().getExtrenos();
                        case CATALOGO_ENTRADA_STRING:
                            // return g.getC().getNombresDeCapitulos();
                            return getStringDeCatalgo(g.getC(), false);
                    }
                    // return inf.getCatalogoDeSeriesEntraronExtrenos(cdv);
                }
                if (REnEsperaEntrada.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            //return inf.getEntraronEnEsperaCatalogo();
                            return S.getFaltantes().getEnEspera();
                        case CATALOGO_ENTRADA_STRING:
                            // return g.getC().getNombresDeCapitulos();
                            return getStringDeCatalgo(g.getC(), false);
                    }
                    // return inf.getCatalogoDeSeriesEntraronEnEspera(cdv);
                }
                if (RPorVerEntrada.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            // return inf.getEntraronPorVerCatalogo();
                            return S.getFaltantes().getPorVer();
                        case CATALOGO_ENTRADA_STRING:
                            // return g.getC().getNombresDeCapitulos();
                            return getStringDeCatalgo(g.getC(), false);
                    }
                    //   return inf.getCatalogoDeSeriesEntraronPorVer(cdv);
                }
                if (RFinalizadasEntrada.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            // return inf.getEntraronFinalizadasCatalogo();
                            return S.getFaltantes().getFinalizadas();
                        case CATALOGO_ENTRADA_STRING:
                            // return g.getC().getNombresDeCapitulos();
                            return getStringDeCatalgo(g.getC(), false);
                    }
                }
            }
            if (RContrarios.isSelected()) {
                if (RPersonalizados.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            return S.getContrarios().getPersonalizados();
                        //  return Cs.getFaltanPorCopiar().getPersonalizados();
                        case CATALOGO_ENTRADA_STRING:
                            return getStringDeCatalgo(g.getC(), false);

                    }
                }
                if (RTodo.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            //  return Cs.getFaltanPorCopiar().getTodo();
                            return S.getContrarios().getTodo();
                        case CATALOGO_ENTRADA_STRING:
                            return getStringDeCatalgo(g.getC(), false);

                    }
                }
                if (RSeguidosEntrada.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            //   return Cs.getFaltanPorCopiar().getSeguidos();
                            return S.getContrarios().getSeguidos();
                        case CATALOGO_ENTRADA_STRING:
                            return getStringDeCatalgo(g.getC(), false);
                    }
                }
                if (RExtrenos.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            //   return Cs.getFaltanPorCopiar().getExtrenos();
                            return S.getContrarios().getExtrenos();
                        case CATALOGO_ENTRADA_STRING:
                            return getStringDeCatalgo(g.getC(), false);
                    }
                }
                if (REnEsperaEntrada.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            //   return Cs.getFaltanPorCopiar().getEnEspera();
                            return S.getContrarios().getEnEspera();
                        case CATALOGO_ENTRADA_STRING:
                            return getStringDeCatalgo(g.getC(), false);
                    }
                }
                if (RPorVerEntrada.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            //    return Cs.getFaltanPorCopiar().getPorVer();
                            return S.getContrarios().getPorVer();
                        case CATALOGO_ENTRADA_STRING:
                            return getStringDeCatalgo(g.getC(), false);
                    }
                }
                if (RFinalizadasEntrada.isSelected()) {
                    switch (g) {
                        case CATALOGO_ENTRADA:
                            //    return Cs.getFaltanPorCopiar().getFinalizadas();
                            return S.getContrarios().getFinalizadas();
                        case CATALOGO_ENTRADA_STRING:
                            return getStringDeCatalgo(g.getC(), false);
                    }
                }
            }
//            if (RFinalizadasQueTengo.isSelected()) {
//                switch (g) {
//                    case CATALOGO_ENTRADA:
//                        // return inf.getFinalizadasQueTengoCatalogo();
//                        return Cs.getBasicos().getFinalizadasQueTengo();
//                    case CATALOGO_ENTRADA_STRING:
//                        // System.out.println("b");
//                        // return g.getC().getNombresDeSeries();
//                        return getStringDeCatalgo(g.getC(), false);
//                }
//            }
        }

        return null;
    }

    private SeccionSerie getSeccionSerieBaseActual() {
        try {
            return ((SeccionSerie) getDeBaseActual(get.SECCION_BASE));
        } catch (Exception ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void actualizarRFormaStringBase() {

//        boolean capitulos = getSeccionSerieBaseActual().isVerCapitulos();
//        if (capitulos) {
//            RCapitulosBase.setSelected(true);
//        } else {
//            RNombresDeSeriesBase.setSelected(true);
//        }
        switch (getSeccionSerieBaseActual().getFormaString()) {
            case CAPITULOS:
                RCapitulosBase.setSelected(true);
                break;
            case SOLO_CAPITULOS:
                RSoloCapitulosBase.setSelected(true);
                break;
            case NOMBRES_DE_SERIES:
                RNombresDeSeriesBase.setSelected(true);
                break;
        }
    }

    private void setFormaStringBase() {
        try {
            setFormaString(((SeccionSerie) getDeBaseActual(get.SECCION_BASE)), RCapitulosBase, RSoloCapitulosBase, RNombresDeSeriesBase);

//           ((SeccionSerie) getDeBaseActual(get.SECCION_BASE)).setVerCapitulos(RCapitulosBase.isSelected());
        } catch (Exception ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private SeccionSerie getSeccionSerieEntradaActual() {
        try {
            return ((SeccionSerie) getDeBaseActual(get.SECCION_ENTRADA));
        } catch (Exception ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void actualizarRFormaStringEntrada() {
        switch (getSeccionSerieEntradaActual().getFormaString()) {
            case CAPITULOS:
                RCapitulosEntrada.setSelected(true);
                break;
            case SOLO_CAPITULOS:
                RSoloCapitulosEntrada.setSelected(true);
                break;
            case NOMBRES_DE_SERIES:
                RNombresDeSeriesEntrada.setSelected(true);
                break;
        }
//        try {
//            boolean capitulos = ((SeccionSerie) getDeBaseActual(get.SECCION_ENTRADA)).isVerCapitulos();
//            if (capitulos) {
//                RCapitulosEntrada.setSelected(true);
//            } else {
//                RNombresDeSeriesEntrada.setSelected(true);
//            }
//            //   RCapitulosEntrada.setSelected(((SeccionSerie) getDeBaseActual(get.SECCION_ENTRADA)).isVerCapitulos());
//        } catch (Exception ex) {
//            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private void setFormaString(SeccionSerie s, JRadioButton Capitulos, JRadioButton SoloCapitulos, JRadioButton NombresDeSeries) {
        if (Capitulos.isSelected()) {
            s.setFormaString(SeccionSerie.FormaString.CAPITULOS);
            return;
        }
        if (SoloCapitulos.isSelected()) {
            s.setFormaString(SeccionSerie.FormaString.SOLO_CAPITULOS);
            return;
        }
        if (NombresDeSeries.isSelected()) {
            s.setFormaString(SeccionSerie.FormaString.NOMBRES_DE_SERIES);
            return;
        }
    }

    private void setFormaStringEntrada() {

        try {
            setFormaString(((SeccionSerie) getDeBaseActual(get.SECCION_ENTRADA)), RCapitulosEntrada, RSoloCapitulosEntrada, RNombresDeSeriesEntrada);
//            ((SeccionSerie) getDeBaseActual(get.SECCION_ENTRADA)).setVerCapitulos(RCapitulosEntrada.isSelected());
        } catch (Exception ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String[] getStringDeCatalgo(CatalogoDeSeries c, boolean base) {
        //        if(){}
        JRadioButton Capitulos = base ? RCapitulosBase : RCapitulosEntrada, 
                SoloCapitulos = base ? RSoloCapitulosBase : RSoloCapitulosEntrada,
                NombresDeSeries = base ? RNombresDeSeriesBase : RNombresDeSeriesEntrada;
        if (Capitulos.isSelected()) {
//            System.out.println("c 5");
//            c.ordenarPorCap1();
            String[] A = c.getNombresDeCapitulos();
//            System.out.println("c 6");
//            System.out.println(Arrays.toString(A));
            return A;
        }
        if (SoloCapitulos.isSelected()) {
//            System.out.println("c 7");
            String[] A = c.getNombresDeCapitulosNoVacios();
//             System.out.println("c 8");
            return A;
        }
        if (NombresDeSeries.isSelected()) {
//            System.out.println("c 9");
            String[] A = c.getNombresDeSeries();
//            System.out.println("c 10");
            return A;
        }

        return null;
//       if(base){}else{}
//        return base ? (RCapitulosBase.isSelected() ? c.getNombresDeCapitulos() : c.getNombresDeSeries()) : (RCapitulosEntrada.isSelected() ? c.getNombresDeCapitulos() : c.getNombresDeSeries());
    }

    public Tabla getTablaDireccionEntrada() {
        return getSeccionSerieEntradaActual().getTabla();
//        if (RAnime.isSelected()) {
//            return EA.getAnime().getEntrada().getTabla();
//        } else {
//            return EA.getSeries().getEntrada().getTabla();
//        }
    }

    public Tabla getTablaInvalidosEntrada(int indice) {
        return getSeccionSerieEntradaActual().getTablaInvalidos(indice);
//        if (RAnime.isSelected()) {
//            return EA.getAnime().getEntrada().getTablaInvalidos(indice);
//        } else {
//            return EA.getSeries().getEntrada().getTablaInvalidos(indice);
//        }
    }

    public LinkedList<Direccion> getEntrada() {
        return getSeccionSerieEntradaActual().getDirecciones();
//        if (RAnime.isSelected()) {
//            return EA.getAnime().getEntrada().getDirecciones();
//        } else {
//            return EA.getSeries().getEntrada().getDirecciones();
//        }
    }
//    public LinkedList<Direccion> getDireccionesBaseActual() {
//        return (LinkedList<Direccion>) getDeBaseActual(-2);
//    }

    public LinkedList<Direccion> getDireccionesBaseActual() {
        try {
            return (LinkedList<Direccion>) getDeBaseActual(get.DIRECCIONES);
        } catch (Exception ex) {
//            System.out.println("error rrrrrrrrr");
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void activarDireccion(LinkedList<Direccion> d, JTable t, int fila) {
        // System.out.println("(Boolean) t.getValueAt(fila, 0)="+(Boolean) t.getValueAt(fila, 0));
        d.get(fila).setSeleccionado((Boolean) t.getValueAt(fila, 0));
        //   System.out.println("d.get(fila).isSeleccionado()="+d.get(fila).isSeleccionado());
    }

    private void activarPersonalizadoBase(JTable t, int fila) throws FileNotFoundException {
        getInf().getSeccionesBase().getPersonalizadas().get(fila).setSeleccionado((Boolean) t.getValueAt(fila, 0));
    }

    public boolean getSelecionadoTablaCarpeta(int fila) {
        return (boolean) JTTablaCarpetas.getValueAt(fila, 0);
    }

    public void actualizarSelecionadoTablaCarpeta(int fila) {
        EA.getCdc().getNombresCarpetas().get(fila).setSeleccionado(getSelecionadoTablaCarpeta(fila));
    }

    public String getNombreTablaCarpeta(int fila) {
        return (String) JTTablaCarpetas.getValueAt(fila, 0);
    }

    public void actualizarNombreTablaCarpeta(int fila) {
        EA.getCdc().getNombresCarpetas().get(fila).setNombreCarpeta(getNombreTablaCarpeta(fila));
    }

    public void actualizarTablaCarpetas() throws FileNotFoundException {
        // System.out.println("actualizarDireccionEntrada");
        editingTabla Ac = new editingTabla() {

            @Override
            public void editingStopped(ChangeEvent e, JTable t, int fila, int columna) {
                try {
                    if (columna == 0) {
                        actualizarSelecionadoTablaCarpeta(fila);
                        actualizarMarcar(EA.getCdc().getNombresCarpetas(), BSeñalarTablaCarpetas);
                    } else {
                        actualizarNombreTablaCarpeta(fila);
                    }
                    seguridad();
                    //     System.out.println("bbb");
//                    activarDireccion(getEntrada(), t, fila);
//                     actualizarEntradaActual();
//                    actualizarTEntrada();
                } catch (Exception ex) {
                    responerException(ex);
                }
            }

            @Override
            public void editingCanceled(ChangeEvent e, JTable t, int fila, int columna) {
                // System.out.println("canc 2");
            }
        };
        selectionFila Sf = new selectionFila() {
            @Override
            public void mousePressed(MouseEvent e, JTable t) {
                tablaCarpetaSeleccionada = t.getSelectedRow();

            }

        };

        JTTablaCarpetas.setToolTipText("tabla carpetas");
        ConfiguracionDeTabla cdt = new ConfiguracionDeTabla(PPaquete, PTablaCarpetas, SPTablaCarpetas, JTTablaCarpetas, EA.getCdc().getTabla(), Ac, Sf, ANCHO_SELECT, -1, new int[]{0, 1});
        Visual.actualizarTablaSeleccionable(cdt);

        //  actualizarInvalidosEntrada(entradaDireccionSelecionada = 0);
    }

    public void actualizarTablaSeleccionadosPersonalizados() throws FileNotFoundException {
//        System.out.println("SeleccionadosPersonalizados");
        editingTabla Et = new editingTabla() {

            @Override
            public void editingStopped(ChangeEvent e, JTable t, int fila, int columna) {
                try {
                    activarPersonalizadoBase(t, fila);
                    //activarDireccion(getDireccionesBaseActual(), t, fila);
                    actualizarBaseActual();
                    actualizarEntrada();
                    actualizarTBase();
//                    actualizarTodoEntradaSiEsNecesario();
                    actualizarTEntrada();

                    seguridad();
                } catch (Exception ex) {
                    responerException(ex);
                }
            }

            @Override
            public void editingCanceled(ChangeEvent e, JTable t, int fila, int columna) {
            }
        };
        selectionFila Sf = new selectionFila() {

            @Override
            public void mousePressed(MouseEvent e, JTable t) {
                personalizadoTablaSeleccionado = t.getSelectedRow();

            }

        };
        JTSeleccionadosManualBase.setToolTipText("nombres personalizados");
        ConfiguracionDeTabla cdt = new ConfiguracionDeTabla(PPersonalizadoEntrada, PSeleccionadosManualBase, SPSeleccionadosManualBase, JTSeleccionadosManualBase, getInf().getSeccionesBase().getTablaNombresPersonalizadosBase(), Et, Sf, ANCHO_SELECT, -1);
        Visual.actualizarTablaSeleccionable(cdt);
        //  actualizarInvalidosBase(baseDireccionSelecionada = 0);

    }

    public void actualizarDireccionBase() throws FileNotFoundException {
        //  System.out.println("actualizarDireccionBase");
        editingTabla Et = new editingTabla() {

            @Override
            public void editingStopped(ChangeEvent e, JTable t, int fila, int columna) {
                try {
//                       System.out.println("aaa");
                    activarDireccion(getDireccionesBaseActual(), t, fila);
                    actualizarBaseActual();
//                    System.out.println("bbbbbbb");
                    actualizarEntrada();
//                    System.out.println("ccccccccc");
                    //  actualizarTs();
                    actualizarTBase();
//                    System.out.println("dddddddd");
//                    actualizarTodoEntradaSiEsNecesario();
                    actualizarTEntrada();
                    actualizarMarcar(getDireccionesBaseActual(), BSeñalarTDireccionesBase);
                    seguridad();
                } catch (Exception ex) {
                    responerException(ex);
                }
            }

            @Override
            public void editingCanceled(ChangeEvent e, JTable t, int fila, int columna) {
                //   System.out.println("canc");
            }
        };
        selectionFila Sf = new selectionFila() {

            @Override
            public void mousePressed(MouseEvent e, JTable t) {
                baseDireccionSelecionada = t.getSelectedRow();
                try {
                    actualizarInvalidosBase(baseDireccionSelecionada);
                    //  System.out.println("baseSelecionada=" + baseSelecionada);
                } catch (Exception ex) {
                    responerException(ex);
                }
            }

        };
        JTDireccionesBase.setToolTipText("direccion base");
//        System.out.println("**************");
//        Tabla tt=getTablaBaseActual(-1);
//        for (int i = 0; i < tt.getRowCount(); i++) {
//            String fi="";
//            for (int j = 0; j < tt.getColumnCount(); j++) {
//                fi+=" "+tt.getValueAt(i, j);
//               
//            }
//            System.out.println(fi);
//            
//        }
//          System.out.println("**************");

        // ConfiguracionDeTabla cdt = new ConfiguracionDeTabla(PTodo, PDireccionesBase, SPDireccionesBase, JTDireccionesBase, getTablaBaseActual(-1), Et, Sf, ANCHO_SELECT, -1);
        ConfiguracionDeTabla cdt = new ConfiguracionDeTabla(PSeries, PDireccionesBase, SPDireccionesBase, JTDireccionesBase, getTablaBaseActual(-1), Et, Sf, ANCHO_SELECT, -1);
        Visual.actualizarTablaSeleccionable(cdt);
        actualizarInvalidosBase(baseDireccionSelecionada = 0);

    }

    public void actualizarDireccionEntrada() throws FileNotFoundException {
        // System.out.println("actualizarDireccionEntrada");
        editingTabla Ac = new editingTabla() {

            @Override
            public void editingStopped(ChangeEvent e, JTable t, int fila, int columna) {
                try {
                    //     System.out.println("bbb");
                    activarDireccion(getEntrada(), t, fila);
                    //actualizarBaseActual();
                    actualizarEntradaActual();
                    actualizarTodoEntradaSiEsNecesario();
                    actualizarTEntrada();
                    actualizarMarcar(getEntrada(), BSeñalarTDireccionesEntraron);
                    seguridad();
                } catch (Exception ex) {
                    responerException(ex);
                }
            }

            @Override
            public void editingCanceled(ChangeEvent e, JTable t, int fila, int columna) {
                // System.out.println("canc 2");
            }
        };
        selectionFila Sf = new selectionFila() {

            @Override
            public void mousePressed(MouseEvent e, JTable t) {
                entradaDireccionSelecionada = t.getSelectedRow();
                try {
                    actualizarInvalidosEntrada(entradaDireccionSelecionada);
                    //  System.out.println("entradaSelecionada=" + entradaSelecionada);
                } catch (Exception ex) {
                    responerException(ex);
                }
            }

        };
        JTDireccionesEntraron.setToolTipText("direccion entraron");
        ConfiguracionDeTabla cdt = new ConfiguracionDeTabla(PSeries, PDireccionesEntraron, SPDireccionesEntraron, JTDireccionesEntraron, getTablaDireccionEntrada(), Ac, Sf, ANCHO_SELECT, -1);
        Visual.actualizarTablaSeleccionable(cdt);

        actualizarInvalidosEntrada(entradaDireccionSelecionada = 0);
    }

    private void activarInvalido(LinkedList<DirectoriosInvalidos> d, JTable t, int fila) {
//        System.out.println("fila=" + fila);
        d.get(fila).setSeleccionado((Boolean) t.getValueAt(fila, 0));
    }

    public void actualizarInvalidosBase(int indice) throws FileNotFoundException {
        editingTabla Ac = new editingTabla() {

            @Override
            public void editingStopped(ChangeEvent e, JTable t, int fila, int columna) {
                try {
                    //  System.out.println("ddd");
                    activarInvalido(getInvalidosBaseActual(), t, fila);
                    actualizarBaseActual();
                    actualizarEntrada();
//                    actualizarTs();
                    actualizarTBase();
//                    actualizarTodoEntradaSiEsNecesario();
                    actualizarTEntrada();
                    actualizarMarcar(getInvalidosBaseActual(), BSeñalarTInvalidosBase);
                    seguridad();
                } catch (Exception ex) {
                    responerException(ex);
                }
            }

            @Override
            public void editingCanceled(ChangeEvent e, JTable t, int fila, int columna) {
                //   System.out.println("canc 3");
            }
        };
        selectionFila Sf = new selectionFila() {

            @Override
            public void mousePressed(MouseEvent e, JTable t) {
                baseInvalidoSeleccionada = t.getSelectedRow();

            }

        };
        JTInvalidosBase.setToolTipText("invalidos base");
        //  getTablaBaseActual(indice).imprimir();
        //  ConfiguracionDeTabla cdt = new ConfiguracionDeTabla(PTodo, PDireccionesBase, SPDireccionesBase, JTDireccionesBase, getTablaBaseActual(-1), Et, Sf, ANCHO_SELECT, -1);
        ConfiguracionDeTabla cdt = new ConfiguracionDeTabla(PSeries, PInvalidosBase, SPInvalidosBase, JTInvalidosBase, getTablaBaseActual(indice), Ac, Sf, ANCHO_SELECT, -1);
        Visual.actualizarTablaSeleccionable(cdt);
        //actualizarTBase();
        //actualizarTEntrada();
        if (getInvalidosBaseActual() != null) {
            actualizarMarcar(getInvalidosBaseActual(), BSeñalarTInvalidosBase);
        }
    }

    public void actualizarInvalidosEntrada(int indice) throws FileNotFoundException {
        editingTabla Ac = new editingTabla() {

            @Override
            public void editingStopped(ChangeEvent e, JTable t, int fila, int columna) {
                //System.out.println("a f="+fila+" c="+columna);

                try {
                    //   System.out.println("eee");
                    activarInvalido(getInvalidosEntradaActual(), t, fila);
                    //   actualizarBaseActual();
                    actualizarEntradaActual();
                    actualizarTodoEntradaSiEsNecesario();
                    actualizarTEntrada();
                    actualizarMarcar(getInvalidosEntradaActual(), BSeñalarTInvalidosEntrada);
                    seguridad();
                } catch (Exception ex) {
                    responerException(ex);
                };
                // t.getCellRenderer(fila, columna).getTableCellRendererComponent(t, t.getValueAt(fila, columna), true, true, fila, columna).setForeground(Color.red);
                //  t.get
            }

            @Override
            public void editingCanceled(ChangeEvent e, JTable t, int fila, int columna) {
                // System.out.println("canc 4");
            }
        };
        selectionFila Sf = new selectionFila() {

            @Override
            public void mousePressed(MouseEvent e, JTable t) {
                entradaInvalidoSeleccionada = t.getSelectedRow();

            }

        };
        JTInvalidosEntraron.setToolTipText("invalidos entraron");
        ConfiguracionDeTabla cdt = new ConfiguracionDeTabla(PSeries, PInvalidosEntraron, SPInvalidosEntraron, JTInvalidosEntraron, getTablaInvalidosEntrada(indice), Ac, Sf, ANCHO_SELECT, -1);
        Visual.actualizarTablaSeleccionable(cdt);
        if (getInvalidosEntradaActual() != null) {
            actualizarMarcar(getInvalidosEntradaActual(), BSeñalarTInvalidosEntrada);
        }
        // actualizarTEntrada();
    }

    public void actualizarTablas() throws FileNotFoundException {
        actualizarDireccionBase();
        actualizarDireccionEntrada();
        actualizarTablaSeleccionadosPersonalizados();
//        actualizarInvalidosBase(baseSelecionada);
//        actualizarInvalidosEntrada(entradaSelecionada);
    }

    public void seguridad() {
        LinkedList<SeccionPersonalizada> personalizadas = getPersonalizadosBase();
        Visual.activarJComponent(!(personalizadas == null || personalizadas.isEmpty()), CBManualBase, BEliminarManualBase, BClearManualBase, BGuardarManualBase, REscrituraManualBase, RCatalogoAlmacenado, RDireccionesManualBase);
        TBase.setEditable(false);
        switch (TPBase.getSelectedIndex()) {
            case 0:
                Visual.activarJComponent(RCapitulosBase, RNombresDeSeriesBase, BGuardarDireccionBase, BGuardarDireccionEntrada, BDireccionesBaseAdministrador, BInvalidoBaseAdministrador, BBuscarDireccionBase,
                        BAgregarInvalidoBase, BEliminarDireccionBase, BEliminarInvalidoBase);
                break;
            case 2:
                Visual.activarJComponent(RCapitulosBase, RNombresDeSeriesBase, BGuardarDireccionBase, BGuardarDireccionEntrada, BDireccionesBaseAdministrador, BInvalidoBaseAdministrador, BBuscarDireccionBase,
                        BAgregarInvalidoBase, BEliminarDireccionBase, BEliminarInvalidoBase);
                break;
            case 1:
                // LinkedList<SeccionPersonalizada> personalizadas = getPersonalizados();
                if (personalizadas == null || personalizadas.isEmpty()) {
                    // Visual.activarJComponent(false, CBManualBase, BEliminarManualBase, BClearManualBase, BGuardarManualBase, REscrituraManualBase, RCatalogoAlmacenado, RDireccionesManualBase);
                    Visual.activarJComponent(false, RCapitulosBase, RNombresDeSeriesBase, BGuardarDireccionBase, BGuardarDireccionEntrada, BDireccionesBaseAdministrador, BInvalidoBaseAdministrador, BBuscarDireccionBase,
                            BAgregarInvalidoBase, BEliminarDireccionBase, BEliminarInvalidoBase);

                } else {
                    Visual.activarJComponent(CBManualBase, BEliminarManualBase, BClearManualBase, BGuardarManualBase, REscrituraManualBase, RCatalogoAlmacenado, RDireccionesManualBase);
                    switch (personalizadas.get(CBManualBase.getSelectedIndex()).getEstado()) {
                        case CATALOGO:
                            Visual.activarJComponent(RCapitulosBase, RNombresDeSeriesBase);
                            Visual.activarJComponent(false, BGuardarDireccionBase, BGuardarDireccionEntrada, BDireccionesBaseAdministrador, BInvalidoBaseAdministrador, BBuscarDireccionBase,
                                    BAgregarInvalidoBase, BEliminarDireccionBase, BEliminarInvalidoBase);
                            break;
                        case DIRECCIONES:
                            Visual.activarJComponent(RCapitulosBase, RNombresDeSeriesBase, BGuardarDireccionBase, BGuardarDireccionEntrada, BDireccionesBaseAdministrador, BInvalidoBaseAdministrador, BBuscarDireccionBase,
                                    BAgregarInvalidoBase, BEliminarDireccionBase, BEliminarInvalidoBase);
                            break;
                        case ESCRITURA:
                            Visual.activarJComponent(false, RCapitulosBase, RNombresDeSeriesBase, BGuardarDireccionBase, BGuardarDireccionEntrada, BDireccionesBaseAdministrador, BInvalidoBaseAdministrador, BBuscarDireccionBase,
                                    BAgregarInvalidoBase, BEliminarDireccionBase, BEliminarInvalidoBase);
                            TBase.setEditable(true);
                            break;
                    }
                    // TBase.setEditable(REscrituraManualBase.isSelected());
                }
                break;
        }
        Visual.activarJComponent(getDireccionesBaseActual() != null && !getDireccionesBaseActual().isEmpty(), BClearDireccionBase, BEliminarDireccionBase, BGuardarDireccionBase, BSeñalarTDireccionesBase);
        Visual.activarJComponent(!getEntrada().isEmpty(), BClearDireccionEntrada, BEliminarDireccionEntrada, BGuardarDireccionEntrada, BSeñalarTDireccionesEntraron);
        Visual.activarJComponent(getInvalidosBaseActual() != null && !getInvalidosBaseActual().isEmpty(), BClearInvalidosBase, BEliminarInvalidoBase, BGuardarInvalidosBase, BSeñalarTInvalidosBase);
        Visual.activarJComponent(getInvalidosEntradaActual() != null && !getInvalidosEntradaActual().isEmpty(), BClearInvalidosBase, BEliminarInvalidoEntrada, BGuardarInvalidosEntrada, BSeñalarTInvalidosEntrada);
        Visual.activarJComponent(!EA.getCdc().getNombresCarpetas().isEmpty(), BClearTablaCarpetas, BEliminarTablaCarpetas, BGuardarTablaCarpetas, BSeñalarTablaCarpetas);
        Visual.seguridadJTextField_File_Directorio(TFDireccionTXT, BClearDireccionTXT, BGuardarDireccionTXT);
        Visual.seguridadJTextField_File_Directorio(TFDireccionCarpetas, BClearDireccionCarpetas, BGuardarDireccionCarpetas);
        Visual.seguridadJTextField_File_Directorio(TFDireccionContenido, BClearDireccionContenido, BGuardarDireccionContenido, BTXTContenido);
        Visual.seguridadJTextField_File_Directorio(TFDireccionPaquete, BClearDireccionPaquete, BGuardarDireccionPaquete, BCargarPaquete);
        seguridadJTFFiltro(TFiltroMayoresBase, CBMayoresBase);
        seguridadJTFFiltro(TFiltroMenoresBase, CBMenoresBase);
        seguridadJTFFiltro(TFiltroSoloBase, CBSoloBase);
        seguridadJTFFiltro(TFiltroMayoresEntrada, CBMayoresEntrada);
        seguridadJTFFiltro(TFiltroMenoresEntrada, CBMenoresEntrada);
        seguridadJTFFiltro(TFiltroSoloEntrada, CBSoloEntrada);
        Visual.activarJComponent(BClearDireccionTXT.isEnabled() && !getCatalogoBase().isEmpty(), BTXTBase, BCatalogoBase);
        Visual.activarJComponent(BClearDireccionTXT.isEnabled() && !getCatalogoEntrada().isEmpty(), BTXTEntrada, BCatalogoEntrada);
        Visual.activarJComponent(BClearDireccionCarpetas.isEnabled() && !EA.getCdc().getNombresCarpetas().isEmpty(), BCrearCarpetas);
        Visual.activarJComponent(BClearDireccionPaquete.isEnabled() && BClearDireccionContenido.isEnabled(), BCopiarContenido);
        Visual.activarJComponent(BEliminarManualBase.isEnabled(), BAlmacenSeleccionado);
        Visual.activarJComponent(RTodoSeccion.isSelected()&&!RExtrenos.isSelected(), CBTodoExtrenosSeccion);
        Visual.activarJComponent(RTodo.isSelected()&&!RExtrenos.isSelected(), CBTodoExtrenosEntrada);
//        if(!getInf().getSeccionesBase().getPersonalizadas().isEmpty()){
//            //Color c=
//            estadoPersonalizado ep=getInf().getSeccionesBase().getPersonalizadas().get(CBManualBase.getSelectedIndex()).getEstado();
//            REscrituraManualBase.setForeground(ep==ep.ESCRITURA?Color.RED:Color.BLACK);
//            RDireccionesManualBase.setForeground(ep==ep.DIRECCIONES?Color.RED:Color.BLACK);
//            RCatalogoAlmacenado.setForeground(ep==ep.CATALOGO?Color.RED:Color.BLACK);
//                    
//        }
        seguridadEntrada();

    }

    public void seguridadJTFFiltro(JTextField t, JCheckBox r) {
        if (!Visual.seguridadJTextField_Entero(t, r)) {
            r.setSelected(false);
        }

    }

    public void seguridadEntrada() {

//        try {
        Visual.activarJComponent(!getInf().getSeccionesEntrada().getPersonalizadas().isEmpty(), RPersonalizadosSeccion,
                BEliminarPersonalizadosSeccion, BClearPersonalizadosSeccion, BGuardarAllPersonalizadosSeccion, BGuardarPersonalizadosSeccion);
        Visual.activarJComponent(RPersonalizadosSeccion.isSelected(), CBPersonalizadosSeccion);
        Visual.activarJComponent(!RTodoSeccion.isSelected(), BBuscarDireccionEntrada, BEliminarDireccionEntrada, BEliminarInvalidoEntrada, BAgregarInvalidoEntrada,
                BDireccionesEntradaAdministrador, BInvalidoEntradaAdministrador);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public LinkedList<SeccionPersonalizada> getPersonalizadosBase() {
//        try {
        return getInf().getSeccionesBase().getPersonalizadas();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
    }

    private LinkedList<DirectoriosInvalidos> getDirectoriosInvalidosSerie(File f, JCheckBox SinCarpetas) {
        LinkedList<DirectoriosInvalidos> directoriosInvalidos = new LinkedList<DirectoriosInvalidos>();
        if (f.isDirectory()) {
//                if (CBSinCarpetasBase.isSelected()) {
//                    File F[] = f.listFiles();
//                    for (int i = 0; i < F.length; i++) {
//                        if (F[i].isDirectory()) {
//                            directoriosInvalidos.add(new DirectoriosInvalidos(F[i].getName(), true));
//                        }
//                    }
//                } else {
            String sub[] = {"! Subtitulos", "!!!Estrenos", "Subtitulos", "SUB", "Estrenos"};
            File F[] = f.listFiles();
            for (int i = 0; i < F.length; i++) {
                if (F[i].isDirectory()) {
                    if (SinCarpetas.isSelected()
                            || (MetodosUtiles.startsWith(F[i].getName(), "!", "_")) || (MetodosUtiles.or(F[i].getName(), true, sub))) {
                        directoriosInvalidos.add(new DirectoriosInvalidos(F[i].getName(), true));
                    }

                }
            }
//                }
        }
        return directoriosInvalidos;
    }

    public void addDireccionBase(File f) throws FileNotFoundException {
        if (f.isDirectory() || Archivo.esTXT(f)) {

            getDireccionesBaseActual().add(new Direccion(new File(f.toString()), true, getDirectoriosInvalidosSerie(f, CBSinCarpetasBase)));

            actualizarDireccionBase();
            //  seguridad();
        }
    }

    public void addDireccionEntrada(File f) throws FileNotFoundException {
        if (f.isDirectory() || Archivo.esTXT(f)) {

//            LinkedList<DirectoriosInvalidos> directoriosInvalidos = new LinkedList<DirectoriosInvalidos>();
//            if (f.isDirectory() && CBSinCarpetasEntrada.isSelected()) {
//                File F[] = f.listFiles();
//                for (int i = 0; i < F.length; i++) {
//                    if (F[i].isDirectory()) {
//                        directoriosInvalidos.add(new DirectoriosInvalidos(F[i].getName(), true));
//                    }
//                }
//            }
//            getEntrada().add(new Direccion(new File(f.toString()), true, directoriosInvalidos));
            getEntrada().add(new Direccion(new File(f.toString()), true, getDirectoriosInvalidosSerie(f, CBSinCarpetasEntrada)));
            actualizarDireccionEntrada();
            // seguridad();
        }
    }

    public void eliminarDireccionBase() throws FileNotFoundException {
        if (eliminarDireccion(getDireccionesBaseActual(), baseDireccionSelecionada)) {
            actualizarDireccionBase();
        }
    }

    public void eliminarDireccionEntrada() throws FileNotFoundException {
        if (eliminarDireccion(getEntrada(), entradaDireccionSelecionada)) {
            actualizarDireccionEntrada();
        }
    }

    private boolean eliminarDireccion(LinkedList<Direccion> d, int indice) {
        if (indice >= 0 && indice < d.size()) {
            d.remove(indice);
            return true;
        }
        return false;
    }

    public void addInvalidoBase(String a) throws FileNotFoundException {
        if (addInvalido(getDireccionesBaseActual(), baseDireccionSelecionada, a)) {
            actualizarInvalidosBase(baseDireccionSelecionada);
        }
    }

    public void addInvalidoEntrada(String a) throws FileNotFoundException {
        if (addInvalido(getEntrada(), entradaDireccionSelecionada, a)) {
            actualizarInvalidosEntrada(entradaDireccionSelecionada);
        }
    }

    private boolean addInvalido(LinkedList<Direccion> d, int indice, String a) {
        if (indice >= 0 && indice < d.size() && !a.isEmpty()) {
            d.get(indice).getDirectoriosInvalidos().add(new DirectoriosInvalidos(a, true));
            return true;
        }
        return false;
    }

    public LinkedList<DirectoriosInvalidos> getInvalidosBaseActual() {
        LinkedList<Direccion> d = getDireccionesBaseActual();
        if (d != null) {
            if (baseDireccionSelecionada < d.size()) {
                return d.get(baseDireccionSelecionada).getDirectoriosInvalidos();
            }
        }
        return null;
    }

    public LinkedList<DirectoriosInvalidos> getInvalidosEntradaActual() {
        LinkedList<Direccion> d = getEntrada();
        if (d != null) {
            if (entradaInvalidoSeleccionada < d.size()) {
                return d.get(entradaInvalidoSeleccionada).getDirectoriosInvalidos();
            }
        }
        return null;
    }

    public void clearInvalidoBase() throws FileNotFoundException {
        if (clearInvalido(getDireccionesBaseActual(), baseDireccionSelecionada)) {
            actualizarInvalidosBase(baseDireccionSelecionada);
        }
    }

    public void clearInvalidoEntrada() throws FileNotFoundException {
        if (clearInvalido(getEntrada(), entradaDireccionSelecionada)) {
            actualizarInvalidosEntrada(entradaDireccionSelecionada);
        }
    }

    public void eliminarInvalidoBase() throws FileNotFoundException {
        if (eliminarInvalido(getDireccionesBaseActual(), baseDireccionSelecionada, baseInvalidoSeleccionada)) {
            actualizarInvalidosBase(baseDireccionSelecionada);
        }
    }

    public void eliminarInvalidoEntrada() throws FileNotFoundException {
        if (eliminarInvalido(getEntrada(), entradaDireccionSelecionada, entradaInvalidoSeleccionada)) {
            actualizarInvalidosEntrada(entradaDireccionSelecionada);
        }
    }

    private boolean eliminarInvalido(LinkedList<Direccion> d, int indice, int indiceIn) {
        if (indice >= 0 && indice < d.size() && indiceIn >= 0 && indiceIn < d.get(indice).getDirectoriosInvalidos().size()) {
            d.get(indice).getDirectoriosInvalidos().remove(indiceIn);
            return true;
        }
        return false;
    }

    private boolean clearInvalido(LinkedList<Direccion> d, int indice) {
        if (indice >= 0 && indice < d.size()) {
            d.get(indice).getDirectoriosInvalidos().clear();
            return true;
        }
        return false;
    }
//    public void actualizar() throws FileNotFoundException {
//        actualizarTablas();
//       // actualizarTs();
//         actualizarTBase();
//                    actualizarTEntrada();
//    }
//    public void actualizarTs() throws FileNotFoundException {
//        actualizarDireccionBase();
//        actualizarDireccionEntrada();
//    }
//    private void actualizarTabla(JScrollPane sp,JTable ){}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BGClasificacion = new javax.swing.ButtonGroup();
        BGBase = new javax.swing.ButtonGroup();
        BGEntrada = new javax.swing.ButtonGroup();
        BGCuantificacion = new javax.swing.ButtonGroup();
        BGPersonalizados = new javax.swing.ButtonGroup();
        BGFormaStringBase = new javax.swing.ButtonGroup();
        BGFormaStringEntrada = new javax.swing.ButtonGroup();
        BGFiltroBase = new javax.swing.ButtonGroup();
        BGFiltroEntrada = new javax.swing.ButtonGroup();
        BSSeccionEntrada = new javax.swing.ButtonGroup();
        PTodo = new javax.swing.JPanel();
        TPTodo = new javax.swing.JTabbedPane();
        PSeries = new javax.swing.JPanel();
        BAdministradorDireccionTXT = new javax.swing.JButton();
        SPDireccionesEntraron = new javax.swing.JScrollPane();
        PDireccionesEntraron = new javax.swing.JPanel();
        JTDireccionesEntraron = new javax.swing.JTable();
        SPInvalidosEntraron = new javax.swing.JScrollPane();
        PInvalidosEntraron = new javax.swing.JPanel();
        JTInvalidosEntraron = new javax.swing.JTable();
        BInvalidoEntradaAdministrador = new javax.swing.JButton();
        RNombresDeSeriesBase = new javax.swing.JRadioButton();
        BGuardarInvalidosEntrada = new javax.swing.JButton();
        BGuardarDireccionEntrada = new javax.swing.JButton();
        CBSinCarpetasBase = new javax.swing.JCheckBox();
        BSeñalarTInvalidosEntrada = new javax.swing.JButton();
        BAgregarDireccionTXT = new javax.swing.JButton();
        SPTEntraron = new javax.swing.JScrollPane();
        TEntraron = new javax.swing.JTextArea();
        BGuardarDireccionBase = new javax.swing.JButton();
        TPEntrada = new javax.swing.JTabbedPane();
        PSeccionEntrada = new javax.swing.JPanel();
        RTXSeccion = new javax.swing.JRadioButton();
        RClasicasSeccion = new javax.swing.JRadioButton();
        RDobladasSeccion = new javax.swing.JRadioButton();
        RPersonalizadosSeccion = new javax.swing.JRadioButton();
        CBPersonalizadosSeccion = new javax.swing.JComboBox();
        BEliminarPersonalizadosSeccion = new javax.swing.JButton();
        BAgregarPersonalizadosSeccion = new javax.swing.JButton();
        BGuardarPersonalizadosSeccion = new javax.swing.JButton();
        BGuardarAllPersonalizadosSeccion = new javax.swing.JButton();
        BClearPersonalizadosSeccion = new javax.swing.JButton();
        BAdministradorPersonalizadosSeccion = new javax.swing.JButton();
        RTodoSeccion = new javax.swing.JRadioButton();
        RFinalizadasSeccion = new javax.swing.JRadioButton();
        BAgregarEntrada = new javax.swing.JButton();
        CBTodoExtrenosSeccion = new javax.swing.JCheckBox();
        PGeneralEntrada = new javax.swing.JPanel();
        RFaltanPorCopiar = new javax.swing.JRadioButton();
        RTodo = new javax.swing.JRadioButton();
        RSeguidosEntrada = new javax.swing.JRadioButton();
        RExtrenos = new javax.swing.JRadioButton();
        REntraron = new javax.swing.JRadioButton();
        REnEsperaEntrada = new javax.swing.JRadioButton();
        RPorVerEntrada = new javax.swing.JRadioButton();
        RFinalizadasEntrada = new javax.swing.JRadioButton();
        RPersonalizados = new javax.swing.JRadioButton();
        RFaltaron = new javax.swing.JRadioButton();
        RContrarios = new javax.swing.JRadioButton();
        CBTodoExtrenosEntrada = new javax.swing.JCheckBox();
        PPersonalizadoEntrada = new javax.swing.JPanel();
        SPSeleccionadosManualBase = new javax.swing.JScrollPane();
        PSeleccionadosManualBase = new javax.swing.JPanel();
        JTSeleccionadosManualBase = new javax.swing.JTable();
        PFiltrosEntrada = new javax.swing.JPanel();
        CBPrimerosEntrada = new javax.swing.JCheckBox();
        CBUltimosEntrada = new javax.swing.JCheckBox();
        CBSeguidosEntrada = new javax.swing.JCheckBox();
        CBQueTengoEntrada = new javax.swing.JCheckBox();
        CBEnEsperaEntrada = new javax.swing.JCheckBox();
        CBPorVerEntrada = new javax.swing.JCheckBox();
        CBFinalizadasEntrada = new javax.swing.JCheckBox();
        CBExtrenosEntrada = new javax.swing.JCheckBox();
        CBPersonalizadosEntrada = new javax.swing.JCheckBox();
        CBMenoresEntrada = new javax.swing.JCheckBox();
        CBMayoresEntrada = new javax.swing.JCheckBox();
        TFiltroSoloEntrada = new javax.swing.JTextField();
        CBSoloEntrada = new javax.swing.JCheckBox();
        TFiltroMenoresEntrada = new javax.swing.JTextField();
        TFiltroMayoresEntrada = new javax.swing.JTextField();
        CBCapitulos1y0Entrada = new javax.swing.JCheckBox();
        RRelacionadosEntrada = new javax.swing.JRadioButton();
        RFaltantesEntrada = new javax.swing.JRadioButton();
        RContrariosEntrada = new javax.swing.JRadioButton();
        BSeñalarTDireccionesBase = new javax.swing.JButton();
        TFDireccionTXT = new javax.swing.JTextField();
        RNombresDeSeriesEntrada = new javax.swing.JRadioButton();
        BDireccionesBaseAdministrador = new javax.swing.JButton();
        BEliminarDireccionEntrada = new javax.swing.JButton();
        BAgregarInvalidoEntrada = new javax.swing.JButton();
        RSeries = new javax.swing.JRadioButton();
        BSeñalarTDireccionesEntraron = new javax.swing.JButton();
        BEliminarInvalidoBase = new javax.swing.JButton();
        BEliminarInvalidoEntrada = new javax.swing.JButton();
        BClearDireccionTXT = new javax.swing.JButton();
        CBSinCarpetasEntrada = new javax.swing.JCheckBox();
        BEliminarDireccionBase = new javax.swing.JButton();
        BBuscarDireccionBase = new javax.swing.JButton();
        RAnime = new javax.swing.JRadioButton();
        BTXTBase = new javax.swing.JButton();
        BAgregarInvalidoBase = new javax.swing.JButton();
        RCapitulosEntrada = new javax.swing.JRadioButton();
        BTXTEntrada = new javax.swing.JButton();
        SPInvalidosBase = new javax.swing.JScrollPane();
        PInvalidosBase = new javax.swing.JPanel();
        JTInvalidosBase = new javax.swing.JTable();
        BGuardarDireccionTXT = new javax.swing.JButton();
        BDireccionesEntradaAdministrador = new javax.swing.JButton();
        BBuscarDireccionEntrada = new javax.swing.JButton();
        BActualizarBase = new javax.swing.JButton();
        RCapitulosBase = new javax.swing.JRadioButton();
        BGuardarInvalidosBase = new javax.swing.JButton();
        TPBase = new javax.swing.JTabbedPane();
        PGeneral = new javax.swing.JPanel();
        RSeguidos = new javax.swing.JRadioButton();
        RQueTengo = new javax.swing.JRadioButton();
        REnEspera = new javax.swing.JRadioButton();
        RPorVer = new javax.swing.JRadioButton();
        RFinalizadas = new javax.swing.JRadioButton();
        PPersonalizado = new javax.swing.JPanel();
        BAgregarManualBase = new javax.swing.JButton();
        CBManualBase = new javax.swing.JComboBox();
        BEliminarManualBase = new javax.swing.JButton();
        BClearManualBase = new javax.swing.JButton();
        BGuardarManualBase = new javax.swing.JButton();
        BDireccionesManualBase = new javax.swing.JButton();
        REscrituraManualBase = new javax.swing.JRadioButton();
        RDireccionesManualBase = new javax.swing.JRadioButton();
        RCatalogoAlmacenado = new javax.swing.JRadioButton();
        BGuardarAllManualBase = new javax.swing.JButton();
        BAlmacenSeleccionado = new javax.swing.JButton();
        BAlmacenPredeterminado = new javax.swing.JButton();
        PFiltrosBase = new javax.swing.JPanel();
        CBPrimerosBase = new javax.swing.JCheckBox();
        CBExtrenosBase = new javax.swing.JCheckBox();
        CBUltimosBase = new javax.swing.JCheckBox();
        CBEnEsperaBase = new javax.swing.JCheckBox();
        CBPorVerBase = new javax.swing.JCheckBox();
        CBQueTengoBase = new javax.swing.JCheckBox();
        CBSeguidosBase = new javax.swing.JCheckBox();
        CBFinalizadasBase = new javax.swing.JCheckBox();
        CBPersonalizadosBase = new javax.swing.JCheckBox();
        CBMayoresBase = new javax.swing.JCheckBox();
        CBMenoresBase = new javax.swing.JCheckBox();
        CBSoloBase = new javax.swing.JCheckBox();
        TFiltroMenoresBase = new javax.swing.JTextField();
        TFiltroSoloBase = new javax.swing.JTextField();
        TFiltroMayoresBase = new javax.swing.JTextField();
        CBCapitulos1y0Base = new javax.swing.JCheckBox();
        RRelacionadosBase = new javax.swing.JRadioButton();
        RFaltantesBase = new javax.swing.JRadioButton();
        RContrariosBase = new javax.swing.JRadioButton();
        BActualizarEntraron = new javax.swing.JButton();
        BInvalidoBaseAdministrador = new javax.swing.JButton();
        SPTBase = new javax.swing.JScrollPane();
        TBase = new javax.swing.JTextArea();
        SPDireccionesBase = new javax.swing.JScrollPane();
        PDireccionesBase = new javax.swing.JPanel();
        JTDireccionesBase = new javax.swing.JTable();
        BSeñalarTInvalidosBase = new javax.swing.JButton();
        BClearEntrada = new javax.swing.JButton();
        BClearBase = new javax.swing.JButton();
        BClearInvalidosEntrada = new javax.swing.JButton();
        BClearInvalidosBase = new javax.swing.JButton();
        BClearDireccionEntrada = new javax.swing.JButton();
        BClearDireccionBase = new javax.swing.JButton();
        BGuardarContenidoBase = new javax.swing.JButton();
        BAdministradorContenidoBase = new javax.swing.JButton();
        BCatalogoEntrada = new javax.swing.JButton();
        BAdministradorContenidoEntrada = new javax.swing.JButton();
        BGuardarContenidoEntrada = new javax.swing.JButton();
        BCatalogoBase = new javax.swing.JButton();
        RSoloCapitulosEntrada = new javax.swing.JRadioButton();
        RSoloCapitulosBase = new javax.swing.JRadioButton();
        PPaquete = new javax.swing.JPanel();
        BAgregarDireccionCarpetas = new javax.swing.JButton();
        BAdministradorDireccionCarpetas = new javax.swing.JButton();
        BGuardarDireccionCarpetas = new javax.swing.JButton();
        BClearDireccionCarpetas = new javax.swing.JButton();
        TFDireccionContenido = new javax.swing.JTextField();
        SPTablaCarpetas = new javax.swing.JScrollPane();
        PTablaCarpetas = new javax.swing.JPanel();
        JTTablaCarpetas = new javax.swing.JTable();
        BAgregarTablaCarpetas = new javax.swing.JButton();
        BSeñalarTablaCarpetas = new javax.swing.JButton();
        BEliminarTablaCarpetas = new javax.swing.JButton();
        BAdministradorTablaCarpetas = new javax.swing.JButton();
        BGuardarTablaCarpetas = new javax.swing.JButton();
        BClearTablaCarpetas = new javax.swing.JButton();
        BCrearCarpetas = new javax.swing.JButton();
        TFDireccionCarpetas = new javax.swing.JTextField();
        TFDireccionPaquete = new javax.swing.JTextField();
        BAgregarDireccionPaquete = new javax.swing.JButton();
        BAdministradorDireccionPaquete = new javax.swing.JButton();
        BGuardarDireccionPaquete = new javax.swing.JButton();
        BClearDireccionPaquete = new javax.swing.JButton();
        BAgregarDireccionContenido = new javax.swing.JButton();
        BAdministradorDireccionContenido = new javax.swing.JButton();
        BGuardarDireccionContenido = new javax.swing.JButton();
        BClearDireccionContenido = new javax.swing.JButton();
        BCopiarContenido = new javax.swing.JButton();
        SProfundidad = new javax.swing.JSpinner();
        LDireccionDeLaCarpeta = new javax.swing.JLabel();
        LDireccionDeCarpetas1 = new javax.swing.JLabel();
        LDireccionDelPaquete1 = new javax.swing.JLabel();
        BTXTContenido = new javax.swing.JButton();
        BCargarPaquete = new javax.swing.JButton();
        BClearAll = new javax.swing.JButton();
        BConfiguracion = new javax.swing.JButton();
        BAtras = new javax.swing.JButton();

        BGClasificacion .add(RAnime);
        BGClasificacion.add(RSeries);

        BGBase.add(REnEspera);
        BGBase.add(RPorVer);
        BGBase.add(RQueTengo);
        BGBase.add(RSeguidos);
        BGBase.add(RFinalizadas);

        BGEntrada.add(REnEsperaEntrada);
        BGEntrada.add(RPorVerEntrada);
        BGEntrada.add(RTodo);
        BGEntrada.add(RExtrenos);
        BGEntrada.add(RSeguidosEntrada);
        BGEntrada.add(RFinalizadasEntrada);
        BGEntrada.add(RPersonalizados);

        BGCuantificacion.add(RFaltanPorCopiar);
        BGCuantificacion.add(REntraron);
        BGCuantificacion.add(RFaltaron);
        BGCuantificacion.add(RContrarios);

        BGPersonalizados.add(REscrituraManualBase);
        BGPersonalizados.add(RDireccionesManualBase);
        BGPersonalizados.add(RCatalogoAlmacenado);

        BGFormaStringBase.add(RCapitulosBase);
        BGFormaStringBase.add(RNombresDeSeriesBase);
        BGFormaStringBase.add(RSoloCapitulosBase);

        BGFormaStringEntrada.add(RCapitulosEntrada);
        BGFormaStringEntrada.add(RNombresDeSeriesEntrada);
        BGFormaStringEntrada.add(RSoloCapitulosEntrada);

        BGFiltroBase.add(RRelacionadosBase);
        BGFiltroBase.add(RFaltantesBase);
        BGFiltroBase.add(RContrariosBase);

        BGFiltroEntrada.add(RRelacionadosEntrada);
        BGFiltroEntrada.add(RFaltantesEntrada);
        BGFiltroEntrada.add(RContrariosEntrada);

        BSSeccionEntrada.add(RTXSeccion);
        BSSeccionEntrada.add(RClasicasSeccion);
        BSSeccionEntrada.add(RDobladasSeccion);
        BSSeccionEntrada.add(RPersonalizadosSeccion);
        BSSeccionEntrada.add(RTodoSeccion);
        BSSeccionEntrada.add(RFinalizadasSeccion);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Actualizador de Series");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                cerrandoVentana(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                cerrarVentana(evt);
            }
        });

        PTodo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TPTodo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cambioPanelTodo(evt);
            }
        });

        PSeries.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BAdministradorDireccionTXT.setText("^");
        BAdministradorDireccionTXT.setToolTipText("Abre la ventana del administrador de direcciones");
        BAdministradorDireccionTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAdministradorDireccionTXTActionPerformed(evt);
            }
        });
        PSeries.add(BAdministradorDireccionTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 610, -1, -1));

        PDireccionesEntraron.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JTDireccionesEntraron.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        JTDireccionesEntraron.setToolTipText("Tabla de direcciones");
        JTDireccionesEntraron.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                apretoTeclaEntrada(evt);
            }
        });
        PDireccionesEntraron.add(JTDireccionesEntraron, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 120));

        SPDireccionesEntraron.setViewportView(PDireccionesEntraron);

        PSeries.add(SPDireccionesEntraron, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 500, 250, 100));

        PInvalidosEntraron.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JTInvalidosEntraron.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        JTInvalidosEntraron.setToolTipText("Tabla de nombres de carpetas ignoradas");
        JTInvalidosEntraron.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                apretoTeclaInvalidosEntrada(evt);
            }
        });
        PInvalidosEntraron.add(JTInvalidosEntraron, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 120));

        SPInvalidosEntraron.setViewportView(PInvalidosEntraron);

        PSeries.add(SPInvalidosEntraron, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 500, 160, 100));

        BInvalidoEntradaAdministrador.setText("^");
        BInvalidoEntradaAdministrador.setToolTipText("Abre la ventana del administrador de nombres de carpetas invalidas");
        BInvalidoEntradaAdministrador.setMargin(new java.awt.Insets(2, 2, 2, 2));
        BInvalidoEntradaAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BInvalidoEntradaAdministradorActionPerformed(evt);
            }
        });
        PSeries.add(BInvalidoEntradaAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 470, 40, -1));

        RNombresDeSeriesBase.setText("Nombres de Series");
        RNombresDeSeriesBase.setToolTipText("<html>\nMuestra  los nombres de  las seires\n</html>");
        RNombresDeSeriesBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFormaDeVerBase(evt);
            }
        });
        PSeries.add(RNombresDeSeriesBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, -1, -1));

        BGuardarInvalidosEntrada.setText("G");
        BGuardarInvalidosEntrada.setToolTipText("Guarda este conjunto nombres de carpetas a ignorar");
        BGuardarInvalidosEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarInvalidosEntradaActionPerformed(evt);
            }
        });
        PSeries.add(BGuardarInvalidosEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 440, -1, -1));

        BGuardarDireccionEntrada.setText("G");
        BGuardarDireccionEntrada.setToolTipText("Guarda este conjunto de direcciones");
        BGuardarDireccionEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarDireccionEntradaActionPerformed(evt);
            }
        });
        PSeries.add(BGuardarDireccionEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 440, -1, -1));

        CBSinCarpetasBase.setText("Sin Carpetas");
        CBSinCarpetasBase.setToolTipText("Al agregar \"+\"  pasa automaticamente todas las carpetas como nombres de carpetas invalidas");
        PSeries.add(CBSinCarpetasBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 440, -1, -1));

        BSeñalarTInvalidosEntrada.setText("o");
        BSeñalarTInvalidosEntrada.setMargin(new java.awt.Insets(2, 2, 2, 2));
        BSeñalarTInvalidosEntrada.setMinimumSize(new java.awt.Dimension(10, 10));
        BSeñalarTInvalidosEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSeñalarTInvalidosEntradaActionPerformed(evt);
            }
        });
        PSeries.add(BSeñalarTInvalidosEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 470, -1, 30));

        BAgregarDireccionTXT.setText("+");
        BAgregarDireccionTXT.setToolTipText("busca una direccion de una carpeta");
        BAgregarDireccionTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAgregarDireccionTXTActionPerformed(evt);
            }
        });
        PSeries.add(BAgregarDireccionTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, -1, -1));

        TEntraron.setEditable(false);
        TEntraron.setColumns(20);
        TEntraron.setRows(5);
        TEntraron.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apretoTextArea(evt);
            }
        });
        SPTEntraron.setViewportView(TEntraron);

        PSeries.add(SPTEntraron, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 420, 250));

        BGuardarDireccionBase.setText("G");
        BGuardarDireccionBase.setToolTipText("Guarda este conjunto de direcciones");
        BGuardarDireccionBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarDireccionBaseActionPerformed(evt);
            }
        });
        PSeries.add(BGuardarDireccionBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 440, -1, -1));

        PSeccionEntrada.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RTXSeccion.setSelected(true);
        RTXSeccion.setText("TX");
        RTXSeccion.setToolTipText("<html>\nSeccion donde se encuentran las carpetas de series que se estan actualmente en transmicion\n</html>");
        RTXSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoSeccionEntrada(evt);
            }
        });
        PSeccionEntrada.add(RTXSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, -1, -1));

        RClasicasSeccion.setText("Clasicas");
        RClasicasSeccion.setToolTipText("<html>\nSeccion donde se encuentran las carpetas de series que ya terminaron su transmicion <br>\npero se encuentran saliendo como Clasicas\n</html>");
        RClasicasSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoSeccionEntrada(evt);
            }
        });
        PSeccionEntrada.add(RClasicasSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, -1, -1));

        RDobladasSeccion.setText("Dobladas");
        RDobladasSeccion.setToolTipText("<html>\nSeccion donde se encuentran las carpetas de series que se estan actualmente en transmicion <br>\ny se encuentran Dobladas  al Español\n</html>");
        RDobladasSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoSeccionEntrada(evt);
            }
        });
        PSeccionEntrada.add(RDobladasSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, -1, -1));

        RPersonalizadosSeccion.setToolTipText("<html>\nSeccion donde se encuentran las secciones con con clasificacion personalizada\n</html>");
        RPersonalizadosSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoSeccionEntrada(evt);
            }
        });
        PSeccionEntrada.add(RPersonalizadosSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        CBPersonalizadosSeccion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBPersonalizadosSeccion.setToolTipText("Para seleccionar la seccion personalizada de entrada");
        CBPersonalizadosSeccion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selecionaoCBPersonalizadoEntrada(evt);
            }
        });
        PSeccionEntrada.add(CBPersonalizadosSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 130, -1));

        BEliminarPersonalizadosSeccion.setText("-");
        BEliminarPersonalizadosSeccion.setToolTipText("Eliminar la seccion personalizada actual");
        BEliminarPersonalizadosSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEliminarPersonalizadosSeccionActionPerformed(evt);
            }
        });
        PSeccionEntrada.add(BEliminarPersonalizadosSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 40, -1));

        BAgregarPersonalizadosSeccion.setText("+");
        BAgregarPersonalizadosSeccion.setToolTipText("Crear una nueva seccion personalizada");
        BAgregarPersonalizadosSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAgregarPersonalizadosSeccionActionPerformed(evt);
            }
        });
        PSeccionEntrada.add(BAgregarPersonalizadosSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        BGuardarPersonalizadosSeccion.setText("G");
        BGuardarPersonalizadosSeccion.setToolTipText("Guarda la seccion personalizada actual en el administrador de secciones personalizadas");
        BGuardarPersonalizadosSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarPersonalizadosSeccionActionPerformed(evt);
            }
        });
        PSeccionEntrada.add(BGuardarPersonalizadosSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, -1));

        BGuardarAllPersonalizadosSeccion.setText("G A");
        BGuardarAllPersonalizadosSeccion.setToolTipText("Guarda todas las secciones personalizadas  en el administrador de secciones personalizadas");
        BGuardarAllPersonalizadosSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarAllPersonalizadosSeccionActionPerformed(evt);
            }
        });
        PSeccionEntrada.add(BGuardarAllPersonalizadosSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, -1));

        BClearPersonalizadosSeccion.setText("C");
        BClearPersonalizadosSeccion.setToolTipText("<html>\nElimina todas las seccones personalizadas\n<\\html>");
        BClearPersonalizadosSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BClearPersonalizadosSeccionActionPerformed(evt);
            }
        });
        PSeccionEntrada.add(BClearPersonalizadosSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, -1, -1));

        BAdministradorPersonalizadosSeccion.setText("^");
        BAdministradorPersonalizadosSeccion.setToolTipText("Abre la ventana del administrador de secciones personalizadas");
        BAdministradorPersonalizadosSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAdministradorPersonalizadosSeccionActionPerformed(evt);
            }
        });
        PSeccionEntrada.add(BAdministradorPersonalizadosSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 50, -1));

        RTodoSeccion.setText("Todo");
        RTodoSeccion.setToolTipText("<html>\nSeccion donde acumula toda la informacion de las demas Secciones\n</html>");
        RTodoSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoSeccionEntrada(evt);
            }
        });
        PSeccionEntrada.add(RTodoSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        RFinalizadasSeccion.setText("Finalizadas");
        RFinalizadasSeccion.setToolTipText("<html>\nSeccion donde se encuentran las carpetas de series que entraron y son temporadas finalizadas\n</html>");
        RFinalizadasSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoSeccionEntrada(evt);
            }
        });
        PSeccionEntrada.add(RFinalizadasSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, -1, -1));

        BAgregarEntrada.setText("+");
        BAgregarEntrada.setToolTipText("<html>\nDistribuye el contenido ( Direcciones ) de la carpeta  que contiene las series  <br>\n correctamente entre las distintas secciones\n</html>");
        BAgregarEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAgregarEntradaActionPerformed(evt);
            }
        });
        PSeccionEntrada.add(BAgregarEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        CBTodoExtrenosSeccion.setSelected(true);
        CBTodoExtrenosSeccion.setText("T  Extrenos");
        CBTodoExtrenosSeccion.setToolTipText("<html>\nDecide si incluir los Extrenos en el contenido de la seccion Todo\n</html>");
        CBTodoExtrenosSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoCBIncluirExtrenoSeccion(evt);
            }
        });
        PSeccionEntrada.add(CBTodoExtrenosSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        TPEntrada.addTab("Seccion", PSeccionEntrada);

        PGeneralEntrada.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RFaltanPorCopiar.setForeground(new java.awt.Color(0, 0, 102));
        RFaltanPorCopiar.setSelected(true);
        RFaltanPorCopiar.setText("Faltan Por Copiar");
        RFaltanPorCopiar.setToolTipText("<html>\nSeccion donde se encuentran las series que entraron pero no estan<br>\no son superiores a los capitulos  en la/s carpeta/s de las seccion \"Que Tengo\"  <br>\n\n</html>");
        RFaltanPorCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRadioTipoEntrada(evt);
            }
        });
        PGeneralEntrada.add(RFaltanPorCopiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        RTodo.setSelected(true);
        RTodo.setText("Todo");
        RTodo.setToolTipText("<html>\nSeccion donde acumula toda la informacion de las demas Secciones\n</html>");
        RTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRadioEntrada(evt);
            }
        });
        PGeneralEntrada.add(RTodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        RSeguidosEntrada.setText("Seguidos");
        RSeguidosEntrada.setToolTipText("<html>\nSeccion donde se encuentran las series que coinciden con las<br>\nque estan  en la/s carpeta/s de las seccion \"Seguidos\"  <br>\n\n</html>");
        RSeguidosEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRadioEntrada(evt);
            }
        });
        PGeneralEntrada.add(RSeguidosEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        RExtrenos.setText("Extrenos");
        RExtrenos.setToolTipText("<html>\nSeccion donde se encuentran las series que entraron que contienen capitulos uno\n\n</html>");
        RExtrenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRadioEntrada(evt);
            }
        });
        PGeneralEntrada.add(RExtrenos, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, -1, -1));

        REntraron.setForeground(new java.awt.Color(0, 0, 102));
        REntraron.setText("Entraron");
        REntraron.setToolTipText("<html>\nSeccion donde se encuentran las series que entraron y coinciden con los parametros\n\n</html>");
        REntraron.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRadioTipoEntrada(evt);
            }
        });
        PGeneralEntrada.add(REntraron, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, -1, -1));

        REnEsperaEntrada.setText("En Espera");
        REnEsperaEntrada.setToolTipText("<html>\nSeccion donde se encuentran las series que coinciden con las<br>\nque estan  en la/s carpeta/s de las seccion \"En Espera\"  <br>\n\n</html>");
        REnEsperaEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRadioEntrada(evt);
            }
        });
        PGeneralEntrada.add(REnEsperaEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, -1, -1));

        RPorVerEntrada.setText("Por ver");
        RPorVerEntrada.setToolTipText("<html>\nSeccion donde se encuentran las series que coinciden con las<br>\nque estan  en la/s carpeta/s de las seccion \"Por Ver\"  <br>\n\n</html>");
        RPorVerEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRadioEntrada(evt);
            }
        });
        PGeneralEntrada.add(RPorVerEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, -1, -1));

        RFinalizadasEntrada.setText("Finalizadas");
        RFinalizadasEntrada.setToolTipText("<html>\nSeccion donde se encuentran las series que coinciden con las<br>\nque estan  en la/s carpeta/s de las seccion \"Finalizadas\"  <br>\n\n</html>");
        RFinalizadasEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRadioEntrada(evt);
            }
        });
        PGeneralEntrada.add(RFinalizadasEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, -1, -1));

        RPersonalizados.setText("Personalizados");
        RPersonalizados.setToolTipText("<html>\nSeccion donde se encuentran las series que coinciden con las<br>\nque estan  en la/s carpeta/s de las secciones  \"Personalizadas\"  <br>\nseleccionadas que son tomadas como base \n</html>");
        RPersonalizados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRadioEntrada(evt);
            }
        });
        PGeneralEntrada.add(RPersonalizados, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, -1, -1));

        RFaltaron.setForeground(new java.awt.Color(0, 0, 102));
        RFaltaron.setText("Faltaron");
        RFaltaron.setToolTipText("<html>\nSeccion donde se encuentran las series que entraron pero no estan<br>\no son superiores a los capitulos  en la/s carpeta/s de las seccion seleccionada  <br>\n\n</html>");
        RFaltaron.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRadioTipoEntrada(evt);
            }
        });
        PGeneralEntrada.add(RFaltaron, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, -1, -1));

        RContrarios.setForeground(new java.awt.Color(0, 0, 102));
        RContrarios.setText("Contrarios");
        RContrarios.setToolTipText("<html>\nSeccion donde se encuentran las series que entraron y NO coinciden con los parametros\n\n</html>");
        RContrarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRadioTipoEntrada(evt);
            }
        });
        PGeneralEntrada.add(RContrarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, -1, -1));

        CBTodoExtrenosEntrada.setSelected(true);
        CBTodoExtrenosEntrada.setText(" T  Extrenos");
        CBTodoExtrenosEntrada.setToolTipText("<html>\nDecide si incluir los Extrenos en el contenido de la seccion Todo\n</html>");
        CBTodoExtrenosEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoCBIncluirExtrenoEntrada(evt);
            }
        });
        PGeneralEntrada.add(CBTodoExtrenosEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        TPEntrada.addTab("General", PGeneralEntrada);

        PPersonalizadoEntrada.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PSeleccionadosManualBase.setBackground(new java.awt.Color(255, 255, 255));
        PSeleccionadosManualBase.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JTSeleccionadosManualBase.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        JTSeleccionadosManualBase.setToolTipText("<html>\nTabla para seleccionar cuales secciones \"Personalizados\" de la base<br>\nseran tenidas en cuenta en los resultados de la Entrada\n</html>");
        PSeleccionadosManualBase.add(JTSeleccionadosManualBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, -1));

        SPSeleccionadosManualBase.setViewportView(PSeleccionadosManualBase);

        PPersonalizadoEntrada.add(SPSeleccionadosManualBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 390, 70));

        TPEntrada.addTab("Personalizado", PPersonalizadoEntrada);

        PFiltrosEntrada.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CBPrimerosEntrada.setForeground(new java.awt.Color(0, 0, 102));
        CBPrimerosEntrada.setText("Primeros ");
        CBPrimerosEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroEntrada(evt);
            }
        });
        PFiltrosEntrada.add(CBPrimerosEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, -1, -1));

        CBUltimosEntrada.setForeground(new java.awt.Color(0, 0, 102));
        CBUltimosEntrada.setText("Ultimos");
        CBUltimosEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroEntrada(evt);
            }
        });
        PFiltrosEntrada.add(CBUltimosEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, -1));

        CBSeguidosEntrada.setForeground(new java.awt.Color(102, 0, 0));
        CBSeguidosEntrada.setText("Seguidos");
        CBSeguidosEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroEntrada(evt);
            }
        });
        PFiltrosEntrada.add(CBSeguidosEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, -1, -1));

        CBQueTengoEntrada.setForeground(new java.awt.Color(102, 0, 0));
        CBQueTengoEntrada.setText("Que Tengo");
        CBQueTengoEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroEntrada(evt);
            }
        });
        PFiltrosEntrada.add(CBQueTengoEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, -1, -1));

        CBEnEsperaEntrada.setForeground(new java.awt.Color(102, 0, 0));
        CBEnEsperaEntrada.setText("En Espera");
        CBEnEsperaEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroEntrada(evt);
            }
        });
        PFiltrosEntrada.add(CBEnEsperaEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, -1, -1));

        CBPorVerEntrada.setForeground(new java.awt.Color(102, 0, 0));
        CBPorVerEntrada.setText("Por ver");
        CBPorVerEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroEntrada(evt);
            }
        });
        PFiltrosEntrada.add(CBPorVerEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, -1, -1));

        CBFinalizadasEntrada.setForeground(new java.awt.Color(102, 0, 0));
        CBFinalizadasEntrada.setText("Finalizadas");
        CBFinalizadasEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroEntrada(evt);
            }
        });
        PFiltrosEntrada.add(CBFinalizadasEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, -1));

        CBExtrenosEntrada.setForeground(new java.awt.Color(0, 0, 102));
        CBExtrenosEntrada.setText("Extrenos");
        CBExtrenosEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroEntrada(evt);
            }
        });
        PFiltrosEntrada.add(CBExtrenosEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        CBPersonalizadosEntrada.setForeground(new java.awt.Color(102, 0, 0));
        CBPersonalizadosEntrada.setText("Personalizados");
        CBPersonalizadosEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroEntrada(evt);
            }
        });
        PFiltrosEntrada.add(CBPersonalizadosEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, -1, -1));

        CBMenoresEntrada.setForeground(new java.awt.Color(0, 0, 102));
        CBMenoresEntrada.setText("Menores Que");
        CBMenoresEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroEntrada(evt);
            }
        });
        PFiltrosEntrada.add(CBMenoresEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        CBMayoresEntrada.setForeground(new java.awt.Color(0, 0, 102));
        CBMayoresEntrada.setText("Mayores Que");
        CBMayoresEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroEntrada(evt);
            }
        });
        PFiltrosEntrada.add(CBMayoresEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, -1, -1));

        TFiltroSoloEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                escribiendoNumero(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                escribioTFiltroSoloEntrada(evt);
            }
        });
        PFiltrosEntrada.add(TFiltroSoloEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 40, -1));

        CBSoloEntrada.setForeground(new java.awt.Color(0, 0, 102));
        CBSoloEntrada.setText("Solo");
        CBSoloEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroEntrada(evt);
            }
        });
        PFiltrosEntrada.add(CBSoloEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, -1, -1));

        TFiltroMenoresEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                escribioTFiltroMenoresEntrada(evt);
            }
        });
        TFiltroMenoresEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                escribiendoNumero(evt);
            }
        });
        PFiltrosEntrada.add(TFiltroMenoresEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 40, -1));

        TFiltroMayoresEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                escribiendoNumero(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                escribioTFiltroMayoresEntrada(evt);
            }
        });
        PFiltrosEntrada.add(TFiltroMayoresEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 40, -1));

        CBCapitulos1y0Entrada.setForeground(new java.awt.Color(0, 0, 102));
        CBCapitulos1y0Entrada.setText("Capitulos 1 y 0");
        CBCapitulos1y0Entrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroEntrada(evt);
            }
        });
        PFiltrosEntrada.add(CBCapitulos1y0Entrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, -1));

        RRelacionadosEntrada.setForeground(new java.awt.Color(102, 0, 0));
        RRelacionadosEntrada.setSelected(true);
        RRelacionadosEntrada.setText("Relacionados");
        RRelacionadosEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroEntrada(evt);
            }
        });
        PFiltrosEntrada.add(RRelacionadosEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        RFaltantesEntrada.setForeground(new java.awt.Color(102, 0, 0));
        RFaltantesEntrada.setText("Faltantes");
        RFaltantesEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroEntrada(evt);
            }
        });
        PFiltrosEntrada.add(RFaltantesEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, -1, -1));

        RContrariosEntrada.setForeground(new java.awt.Color(102, 0, 0));
        RContrariosEntrada.setText("Contrarios");
        RContrariosEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroEntrada(evt);
            }
        });
        PFiltrosEntrada.add(RContrariosEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, -1));

        TPEntrada.addTab("Filtros", PFiltrosEntrada);

        TPEntrada.setSelectedIndex(1);

        PSeries.add(TPEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 420, 130));

        BSeñalarTDireccionesBase.setText("0");
        BSeñalarTDireccionesBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSeñalarTDireccionesBaseActionPerformed(evt);
            }
        });
        PSeries.add(BSeñalarTDireccionesBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 40, -1));

        TFDireccionTXT.setToolTipText("Direccion donde se creara el TXT ol Catalogo \".catl\"");
        TFDireccionTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                escribioTFDireccionTXT(evt);
            }
        });
        PSeries.add(TFDireccionTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 610, 670, -1));

        RNombresDeSeriesEntrada.setText("Nombres de Series");
        RNombresDeSeriesEntrada.setToolTipText("<html>\nMuestra  los nombres de  las seires\n</html>");
        RNombresDeSeriesEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFormaDeVerEntrada(evt);
            }
        });
        PSeries.add(RNombresDeSeriesEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, -1, -1));

        BDireccionesBaseAdministrador.setText("^");
        BDireccionesBaseAdministrador.setToolTipText("Abre la ventana del administrador de direcciones");
        BDireccionesBaseAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDireccionesBaseAdministradorActionPerformed(evt);
            }
        });
        PSeries.add(BDireccionesBaseAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 470, 50, -1));

        BEliminarDireccionEntrada.setText("-");
        BEliminarDireccionEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEliminarDireccionEntradaActionPerformed(evt);
            }
        });
        PSeries.add(BEliminarDireccionEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 470, -1, -1));

        BAgregarInvalidoEntrada.setText("+");
        BAgregarInvalidoEntrada.setToolTipText("agrega un nombre de una carpeta que va ser ignorada");
        BAgregarInvalidoEntrada.setMargin(new java.awt.Insets(2, 2, 2, 2));
        BAgregarInvalidoEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAgregarInvalidoEntradaActionPerformed(evt);
            }
        });
        PSeries.add(BAgregarInvalidoEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 470, -1, -1));

        RSeries.setText("Series");
        RSeries.setToolTipText("Seccion especializada en Series en Persona");
        RSeries.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRadioTipo(evt);
            }
        });
        PSeries.add(RSeries, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, -1, -1));

        BSeñalarTDireccionesEntraron.setText("0");
        BSeñalarTDireccionesEntraron.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSeñalarTDireccionesEntraronActionPerformed(evt);
            }
        });
        PSeries.add(BSeñalarTDireccionesEntraron, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 470, 40, -1));

        BEliminarInvalidoBase.setText("-");
        BEliminarInvalidoBase.setMargin(new java.awt.Insets(2, 2, 2, 2));
        BEliminarInvalidoBase.setMinimumSize(new java.awt.Dimension(10, 10));
        BEliminarInvalidoBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEliminarInvalidoBaseActionPerformed(evt);
            }
        });
        PSeries.add(BEliminarInvalidoBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 470, -1, -1));

        BEliminarInvalidoEntrada.setText("-");
        BEliminarInvalidoEntrada.setMargin(new java.awt.Insets(2, 2, 2, 2));
        BEliminarInvalidoEntrada.setMinimumSize(new java.awt.Dimension(10, 10));
        BEliminarInvalidoEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEliminarInvalidoEntradaActionPerformed(evt);
            }
        });
        PSeries.add(BEliminarInvalidoEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 470, -1, -1));

        BClearDireccionTXT.setText("C");
        BClearDireccionTXT.setToolTipText("Limpia la direccion");
        BClearDireccionTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BClearDireccionTXTActionPerformed(evt);
            }
        });
        PSeries.add(BClearDireccionTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 610, -1, -1));

        CBSinCarpetasEntrada.setText("Sin Carpetas");
        CBSinCarpetasEntrada.setToolTipText("Al agregar \"+\"  pasa automaticamente todas las carpetas como nombres de carpetas invalidas");
        PSeries.add(CBSinCarpetasEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 440, -1, -1));

        BEliminarDireccionBase.setText("-");
        BEliminarDireccionBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEliminarDireccionBaseActionPerformed(evt);
            }
        });
        PSeries.add(BEliminarDireccionBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 470, -1, -1));

        BBuscarDireccionBase.setText("+");
        BBuscarDireccionBase.setToolTipText("<html>\nagrega direcciones (Carpetas), TXT (lista de series)<br>\n o \".catl\" (Catalogo de Series en formato binario)\n</html>");
        BBuscarDireccionBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBuscarDireccionBaseActionPerformed(evt);
            }
        });
        PSeries.add(BBuscarDireccionBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 470, -1, -1));

        RAnime.setSelected(true);
        RAnime.setText("Anime");
        RAnime.setToolTipText("Seccion especializada en Anime");
        RAnime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRadioTipo(evt);
            }
        });
        PSeries.add(RAnime, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, -1, -1));

        BTXTBase.setText("TXT");
        BTXTBase.setToolTipText("Almacena en TXT el contenido del cuadro de texto de la base");
        BTXTBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTXTBaseActionPerformed(evt);
            }
        });
        PSeries.add(BTXTBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, -1, -1));

        BAgregarInvalidoBase.setText("+");
        BAgregarInvalidoBase.setToolTipText("agrega un nombre de una carpeta que va ser ignorada");
        BAgregarInvalidoBase.setMargin(new java.awt.Insets(2, 2, 2, 2));
        BAgregarInvalidoBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAgregarInvalidoBaseActionPerformed(evt);
            }
        });
        PSeries.add(BAgregarInvalidoBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 470, -1, -1));

        RCapitulosEntrada.setSelected(true);
        RCapitulosEntrada.setText("Capitulos");
        RCapitulosEntrada.setToolTipText("<html>\nMuestra  los capitulos que contiene las seires<br>\nSi no contiene una serie capitulos pone NOMBRE [ sin capitulos ]\n</html>");
        RCapitulosEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFormaDeVerEntrada(evt);
            }
        });
        PSeries.add(RCapitulosEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, -1, -1));

        BTXTEntrada.setText("TXT");
        BTXTEntrada.setToolTipText("Almacena en TXT el contenido del cuadro de texto de la entrada");
        BTXTEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTXTEntradaActionPerformed(evt);
            }
        });
        PSeries.add(BTXTEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 440, -1, -1));

        PInvalidosBase.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JTInvalidosBase.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        JTInvalidosBase.setToolTipText("Tabla de nombres de carpetas ignoradas");
        JTInvalidosBase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                apretoTeclaInvalidosBase(evt);
            }
        });
        PInvalidosBase.add(JTInvalidosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 120));

        SPInvalidosBase.setViewportView(PInvalidosBase);

        PSeries.add(SPInvalidosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 500, 160, 100));

        BGuardarDireccionTXT.setText("G");
        BGuardarDireccionTXT.setToolTipText("Guarda la direccion en el administrador dedirecciones");
        BGuardarDireccionTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarDireccionTXTActionPerformed(evt);
            }
        });
        PSeries.add(BGuardarDireccionTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 610, -1, -1));

        BDireccionesEntradaAdministrador.setText("^");
        BDireccionesEntradaAdministrador.setToolTipText("Abre la ventana del administrador de direcciones");
        BDireccionesEntradaAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDireccionesEntradaAdministradorActionPerformed(evt);
            }
        });
        PSeries.add(BDireccionesEntradaAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 470, 50, -1));

        BBuscarDireccionEntrada.setText("+");
        BBuscarDireccionEntrada.setToolTipText("<html>\nagrega direcciones (Carpetas), TXT (lista de series)<br>\n o \".catl\" (Catalogo de Series en formato binario)\n</html>");
        BBuscarDireccionEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBuscarDireccionEntradaActionPerformed(evt);
            }
        });
        PSeries.add(BBuscarDireccionEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 470, -1, -1));

        BActualizarBase.setText("A");
        BActualizarBase.setToolTipText("Actualiza la seccion actual");
        BActualizarBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActualizarBaseActionPerformed(evt);
            }
        });
        PSeries.add(BActualizarBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, 40, -1));

        RCapitulosBase.setSelected(true);
        RCapitulosBase.setText("Capitulos");
        RCapitulosBase.setToolTipText("<html>\nMuestra  los capitulos que contiene las seires<br>\nSi no contiene una serie capitulos pone NOMBRE [ sin capitulos ]\n</html>");
        RCapitulosBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFormaDeVerBase(evt);
            }
        });
        PSeries.add(RCapitulosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        BGuardarInvalidosBase.setText("G");
        BGuardarInvalidosBase.setToolTipText("Guarda este conjunto nombres de carpetas a ignorar");
        BGuardarInvalidosBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarInvalidosBaseActionPerformed(evt);
            }
        });
        PSeries.add(BGuardarInvalidosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 440, -1, -1));

        TPBase.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cambioPanelBase(evt);
            }
        });

        PGeneral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RSeguidos.setSelected(true);
        RSeguidos.setText("Seguidos");
        RSeguidos.setToolTipText("Seccion para las series que seguidas que estan saliendo en la actualidad");
        RSeguidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRadioBase(evt);
            }
        });
        PGeneral.add(RSeguidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        RQueTengo.setText("Que Tengo");
        RQueTengo.setToolTipText("Seccion para ubicar la/s carpeta/s que contienen las series que se tienen copiadas en la actualidad");
        RQueTengo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRadioBase(evt);
            }
        });
        PGeneral.add(RQueTengo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        REnEspera.setText("En Espera");
        REnEspera.setToolTipText("Seccion para las series que ya terminaron alguna temporada con antelacion y no se encuentran\natualmente en transmicion");
        REnEspera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRadioBase(evt);
            }
        });
        PGeneral.add(REnEspera, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, -1));

        RPorVer.setText("Por ver");
        RPorVer.setToolTipText("<html>\nSeccion para las series de las cuales se espera su presencian en el paquete acual.<br>\nGeneralmente de dicadas a aquellas que se espera su extreno o que fueron<br>\n ignoranadas en su momento y se esta pendiente de su reaparicion.\n</html>");
        RPorVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRadioBase(evt);
            }
        });
        PGeneral.add(RPorVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, -1, -1));

        RFinalizadas.setText("Finalizadas");
        RFinalizadas.setToolTipText("<html>\nSeccion para las series que se tienen con todos  o la mayoria de susu capitulos\n</html>");
        RFinalizadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRadioBase(evt);
            }
        });
        PGeneral.add(RFinalizadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        TPBase.addTab("General", PGeneral);

        PPersonalizado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BAgregarManualBase.setText("+");
        BAgregarManualBase.setToolTipText("Crear una nueva seccion personalizada");
        BAgregarManualBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAgregarManualBaseActionPerformed(evt);
            }
        });
        PPersonalizado.add(BAgregarManualBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, -1, -1));

        CBManualBase.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBManualBase.setToolTipText("Secciones personalizadas\n");
        CBManualBase.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                seleccionarCBManualBase(evt);
            }
        });
        PPersonalizado.add(CBManualBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 140, -1));

        BEliminarManualBase.setText("-");
        BEliminarManualBase.setToolTipText("Eliminar la seccion personalizada actual");
        BEliminarManualBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEliminarManualBaseActionPerformed(evt);
            }
        });
        PPersonalizado.add(BEliminarManualBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 40, -1));

        BClearManualBase.setText("C");
        BClearManualBase.setToolTipText("<html>\nElimina todas las seccones personalizadas, reseteando <br>\ny dejando solo la (_Predeterminada)\n<\\html>");
        BClearManualBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BClearManualBaseActionPerformed(evt);
            }
        });
        PPersonalizado.add(BClearManualBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, -1, -1));

        BGuardarManualBase.setText("G");
        BGuardarManualBase.setToolTipText("Guarda la seccion personalizada actual en el administrador de secciones personalizadas\n");
        BGuardarManualBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarManualBaseActionPerformed(evt);
            }
        });
        PPersonalizado.add(BGuardarManualBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, -1, -1));

        BDireccionesManualBase.setText("^");
        BDireccionesManualBase.setToolTipText("Abre la ventana del administrador de secciones personalizadas");
        BDireccionesManualBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDireccionesManualBaseActionPerformed(evt);
            }
        });
        PPersonalizado.add(BDireccionesManualBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 50, -1));

        REscrituraManualBase.setText("Escritura");
        REscrituraManualBase.setToolTipText("<html>\nModo escritura<br>\nHabilita la entrada de texto para poder introducir el <br>\nlistado de series de forma manual\n</html>");
        REscrituraManualBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoREstadoPersonalizado(evt);
            }
        });
        PPersonalizado.add(REscrituraManualBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        RDireccionesManualBase.setSelected(true);
        RDireccionesManualBase.setText("Direcciones");
        RDireccionesManualBase.setToolTipText("<html>\nModo direcciones<br>\nCarga la inforacion desde las direcciones asignadas\n</html>");
        RDireccionesManualBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoREstadoPersonalizado(evt);
            }
        });
        PPersonalizado.add(RDireccionesManualBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, -1, -1));

        RCatalogoAlmacenado.setText("Catalogo Almacenado");
        RCatalogoAlmacenado.setToolTipText("<html>\nModo Catalogo Almacenado<br>\nUtiliza la informacion recivida mediante la copia del catalogo<br>\nexistente en la entrada (Donde esta el paquete)\n</html>");
        RCatalogoAlmacenado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoREstadoPersonalizado(evt);
            }
        });
        PPersonalizado.add(RCatalogoAlmacenado, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, -1, -1));

        BGuardarAllManualBase.setText("G A");
        BGuardarAllManualBase.setToolTipText("Guarda todas las secciones personalizadas  en el administrador de secciones personalizadas");
        BGuardarAllManualBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarAllManualBaseActionPerformed(evt);
            }
        });
        PPersonalizado.add(BGuardarAllManualBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, -1, -1));

        BAlmacenSeleccionado.setText("CAT E");
        BAlmacenSeleccionado.setToolTipText("<html>\nCopia el catalogo de series actual de Entrada hacia la seccion<br>\npersonaliza actual, dentro del modo Catalogo Almacenado\n</html>");
        BAlmacenSeleccionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAlmacenSeleccionadoActionPerformed(evt);
            }
        });
        PPersonalizado.add(BAlmacenSeleccionado, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 70, -1));

        BAlmacenPredeterminado.setText("CAT P E");
        BAlmacenPredeterminado.setToolTipText("<html>\nCopia el catalogo de series actual de Entrada hacia la seccion<br>\npersonaliza (_Predeterminada), dentro del modo Catalogo Almacenado\n</html>");
        BAlmacenPredeterminado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAlmacenPredeterminadoActionPerformed(evt);
            }
        });
        PPersonalizado.add(BAlmacenPredeterminado, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, -1, -1));

        TPBase.addTab("Personalizado", PPersonalizado);

        PFiltrosBase.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CBPrimerosBase.setForeground(new java.awt.Color(0, 0, 102));
        CBPrimerosBase.setText("Primeros ");
        CBPrimerosBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroBase(evt);
            }
        });
        PFiltrosBase.add(CBPrimerosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, -1, -1));

        CBExtrenosBase.setForeground(new java.awt.Color(0, 0, 102));
        CBExtrenosBase.setText("Extrenos");
        CBExtrenosBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroBase(evt);
            }
        });
        PFiltrosBase.add(CBExtrenosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        CBUltimosBase.setForeground(new java.awt.Color(0, 0, 102));
        CBUltimosBase.setText("Ultimos");
        CBUltimosBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroBase(evt);
            }
        });
        PFiltrosBase.add(CBUltimosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, -1));

        CBEnEsperaBase.setForeground(new java.awt.Color(102, 0, 0));
        CBEnEsperaBase.setText("En Espera");
        CBEnEsperaBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroBase(evt);
            }
        });
        PFiltrosBase.add(CBEnEsperaBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, -1, -1));

        CBPorVerBase.setForeground(new java.awt.Color(102, 0, 0));
        CBPorVerBase.setText("Por ver");
        CBPorVerBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroBase(evt);
            }
        });
        PFiltrosBase.add(CBPorVerBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, -1, -1));

        CBQueTengoBase.setForeground(new java.awt.Color(102, 0, 0));
        CBQueTengoBase.setText("Que Tengo");
        CBQueTengoBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroBase(evt);
            }
        });
        PFiltrosBase.add(CBQueTengoBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, -1, -1));

        CBSeguidosBase.setForeground(new java.awt.Color(102, 0, 0));
        CBSeguidosBase.setText("Seguidos");
        CBSeguidosBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroBase(evt);
            }
        });
        PFiltrosBase.add(CBSeguidosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, -1, -1));

        CBFinalizadasBase.setForeground(new java.awt.Color(102, 0, 0));
        CBFinalizadasBase.setText("Finalizadas");
        CBFinalizadasBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroBase(evt);
            }
        });
        PFiltrosBase.add(CBFinalizadasBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, -1));

        CBPersonalizadosBase.setForeground(new java.awt.Color(102, 0, 0));
        CBPersonalizadosBase.setText("Personalizados");
        CBPersonalizadosBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroBase(evt);
            }
        });
        PFiltrosBase.add(CBPersonalizadosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, -1, -1));

        CBMayoresBase.setForeground(new java.awt.Color(0, 0, 102));
        CBMayoresBase.setText("Mayores Que");
        CBMayoresBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroBase(evt);
            }
        });
        PFiltrosBase.add(CBMayoresBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, -1, -1));

        CBMenoresBase.setForeground(new java.awt.Color(0, 0, 102));
        CBMenoresBase.setText("Menores Que");
        CBMenoresBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroBase(evt);
            }
        });
        PFiltrosBase.add(CBMenoresBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        CBSoloBase.setForeground(new java.awt.Color(0, 0, 102));
        CBSoloBase.setText("Solo");
        CBSoloBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroBase(evt);
            }
        });
        PFiltrosBase.add(CBSoloBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, -1, -1));

        TFiltroMenoresBase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                escribiendoNumero(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                escribioTFiltroMenoresBase(evt);
            }
        });
        PFiltrosBase.add(TFiltroMenoresBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 40, -1));

        TFiltroSoloBase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                escribiendoNumero(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                escribioTFiltroSoloBase(evt);
            }
        });
        PFiltrosBase.add(TFiltroSoloBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 40, -1));

        TFiltroMayoresBase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                escribiendoNumero(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                escribioTFiltroMayoresBase(evt);
            }
        });
        PFiltrosBase.add(TFiltroMayoresBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 40, 30));

        CBCapitulos1y0Base.setForeground(new java.awt.Color(0, 0, 102));
        CBCapitulos1y0Base.setText("Capitulos 1 y 0");
        CBCapitulos1y0Base.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroBase(evt);
            }
        });
        PFiltrosBase.add(CBCapitulos1y0Base, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, -1));

        RRelacionadosBase.setForeground(new java.awt.Color(102, 0, 0));
        RRelacionadosBase.setSelected(true);
        RRelacionadosBase.setText("Relacionados");
        RRelacionadosBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroBase(evt);
            }
        });
        PFiltrosBase.add(RRelacionadosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, -1, -1));

        RFaltantesBase.setForeground(new java.awt.Color(102, 0, 0));
        RFaltantesBase.setText("Faltantes");
        RFaltantesBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroBase(evt);
            }
        });
        PFiltrosBase.add(RFaltantesBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, -1, -1));

        RContrariosBase.setForeground(new java.awt.Color(102, 0, 0));
        RContrariosBase.setText("Contrarios");
        RContrariosBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFiltroBase(evt);
            }
        });
        PFiltrosBase.add(RContrariosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, -1));

        TPBase.addTab("Filtros", PFiltrosBase);

        PSeries.add(TPBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 420, 130));

        BActualizarEntraron.setText("A");
        BActualizarEntraron.setToolTipText("Actualiza la seccion actual");
        BActualizarEntraron.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActualizarEntraronActionPerformed(evt);
            }
        });
        PSeries.add(BActualizarEntraron, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 150, 40, -1));

        BInvalidoBaseAdministrador.setText("^");
        BInvalidoBaseAdministrador.setToolTipText("Abre la ventana del administrador de nombres de carpetas invalidas");
        BInvalidoBaseAdministrador.setMargin(new java.awt.Insets(2, 2, 2, 2));
        BInvalidoBaseAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BInvalidoBaseAdministradorActionPerformed(evt);
            }
        });
        PSeries.add(BInvalidoBaseAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 470, 40, -1));

        TBase.setEditable(false);
        TBase.setColumns(20);
        TBase.setRows(5);
        TBase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apretoTextArea(evt);
            }
        });
        SPTBase.setViewportView(TBase);

        PSeries.add(SPTBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 420, 250));

        PDireccionesBase.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JTDireccionesBase.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        JTDireccionesBase.setToolTipText("Tabla de direcciones");
        JTDireccionesBase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                apreto(evt);
            }
        });
        JTDireccionesBase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                apretoTeclaBase(evt);
            }
        });
        PDireccionesBase.add(JTDireccionesBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 120));

        SPDireccionesBase.setViewportView(PDireccionesBase);

        PSeries.add(SPDireccionesBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 250, 100));

        BSeñalarTInvalidosBase.setText("o");
        BSeñalarTInvalidosBase.setMargin(new java.awt.Insets(2, 2, 2, 2));
        BSeñalarTInvalidosBase.setMinimumSize(new java.awt.Dimension(10, 10));
        BSeñalarTInvalidosBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSeñalarTInvalidosBaseActionPerformed(evt);
            }
        });
        PSeries.add(BSeñalarTInvalidosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 470, -1, 30));

        BClearEntrada.setText("C");
        BClearEntrada.setToolTipText("Vacia todas las Secciones Entrada actuales ");
        BClearEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BClearEntradaActionPerformed(evt);
            }
        });
        PSeries.add(BClearEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 150, -1, -1));

        BClearBase.setText("C");
        BClearBase.setToolTipText("Vacia todas las Secciones Base actuales ");
        BClearBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BClearBaseActionPerformed(evt);
            }
        });
        PSeries.add(BClearBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, -1, -1));

        BClearInvalidosEntrada.setText("C");
        BClearInvalidosEntrada.setToolTipText("Elimina todas los nombres de carpetas no validos");
        BClearInvalidosEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BClearInvalidosEntradaActionPerformed(evt);
            }
        });
        PSeries.add(BClearInvalidosEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 440, -1, -1));

        BClearInvalidosBase.setText("C");
        BClearInvalidosBase.setToolTipText("Elimina todas los nombres de carpetas no validos");
        BClearInvalidosBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BClearInvalidosBaseActionPerformed(evt);
            }
        });
        PSeries.add(BClearInvalidosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 440, -1, -1));

        BClearDireccionEntrada.setText("C");
        BClearDireccionEntrada.setToolTipText("Elimina todas las direcciones");
        BClearDireccionEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BClearDireccionEntradaActionPerformed(evt);
            }
        });
        PSeries.add(BClearDireccionEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 470, -1, -1));

        BClearDireccionBase.setText("C");
        BClearDireccionBase.setToolTipText("Elimina todas las direcciones");
        BClearDireccionBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BClearDireccionBaseActionPerformed(evt);
            }
        });
        PSeries.add(BClearDireccionBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 470, -1, -1));

        BGuardarContenidoBase.setText("G");
        BGuardarContenidoBase.setToolTipText("Guarda el conjunto de secciones base actual en el administrador de Conjunto de Secciones Base");
        BGuardarContenidoBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarContenidoBaseActionPerformed(evt);
            }
        });
        PSeries.add(BGuardarContenidoBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, -1, -1));

        BAdministradorContenidoBase.setText("^");
        BAdministradorContenidoBase.setToolTipText("Abre la ventana del administrador de Conjunto de Secciones Base");
        BAdministradorContenidoBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAdministradorContenidoBaseActionPerformed(evt);
            }
        });
        PSeries.add(BAdministradorContenidoBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, -1, -1));

        BCatalogoEntrada.setText("CAT");
        BCatalogoEntrada.setToolTipText("Almacena en formato binario el catalogo de series actual de la entrada");
        BCatalogoEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCatalogoEntradaActionPerformed(evt);
            }
        });
        PSeries.add(BCatalogoEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 440, -1, -1));

        BAdministradorContenidoEntrada.setText("^");
        BAdministradorContenidoEntrada.setToolTipText("Abre la ventana del administrador de Conjunto de Secciones Base");
        BAdministradorContenidoEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAdministradorContenidoEntradaActionPerformed(evt);
            }
        });
        PSeries.add(BAdministradorContenidoEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 150, -1, -1));

        BGuardarContenidoEntrada.setText("G");
        BGuardarContenidoEntrada.setToolTipText("Guarda el conjunto de secciones entrada actual en el administrador de Conjunto de Secciones Entrada");
        BGuardarContenidoEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarContenidoEntradaActionPerformed(evt);
            }
        });
        PSeries.add(BGuardarContenidoEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 150, -1, -1));

        BCatalogoBase.setText("CAT");
        BCatalogoBase.setToolTipText("Almacena en formato binario el catalogo de series actual de la base");
        BCatalogoBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCatalogoBaseActionPerformed(evt);
            }
        });
        PSeries.add(BCatalogoBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 440, -1, -1));

        RSoloCapitulosEntrada.setText("Solo");
        RSoloCapitulosEntrada.setToolTipText("<html>\nMuestra solo los capitulos de las Series\n</html>");
        RSoloCapitulosEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFormaDeVerEntrada(evt);
            }
        });
        PSeries.add(RSoloCapitulosEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 150, -1, -1));

        RSoloCapitulosBase.setText("Solo");
        RSoloCapitulosBase.setToolTipText("<html>\nMuestra solo los capitulos de las Series\n</html>");
        RSoloCapitulosBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoFormaDeVerBase(evt);
            }
        });
        PSeries.add(RSoloCapitulosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        TPTodo.addTab("Series", PSeries);

        PPaquete.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BAgregarDireccionCarpetas.setText("+");
        BAgregarDireccionCarpetas.setToolTipText("busca una direccion de una carpeta");
        BAgregarDireccionCarpetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAgregarDireccionCarpetasActionPerformed(evt);
            }
        });
        PPaquete.add(BAgregarDireccionCarpetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        BAdministradorDireccionCarpetas.setText("^");
        BAdministradorDireccionCarpetas.setToolTipText("Abre la ventana del administrador de direcciones");
        BAdministradorDireccionCarpetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAdministradorDireccionCarpetasActionPerformed(evt);
            }
        });
        PPaquete.add(BAdministradorDireccionCarpetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        BGuardarDireccionCarpetas.setText("G");
        BGuardarDireccionCarpetas.setToolTipText("Guarda la direccion en el administrador dedirecciones");
        BGuardarDireccionCarpetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarDireccionCarpetasActionPerformed(evt);
            }
        });
        PPaquete.add(BGuardarDireccionCarpetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, -1, -1));

        BClearDireccionCarpetas.setText("C");
        BClearDireccionCarpetas.setToolTipText("Limpia la direccion");
        BClearDireccionCarpetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BClearDireccionCarpetasActionPerformed(evt);
            }
        });
        PPaquete.add(BClearDireccionCarpetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, -1, -1));

        TFDireccionContenido.setToolTipText("Direccion de la carpata para trabajar");
        TFDireccionContenido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                escribioTFDireccionContenido(evt);
            }
        });
        PPaquete.add(TFDireccionContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 420, 480, -1));

        PTablaCarpetas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JTTablaCarpetas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        JTTablaCarpetas.setToolTipText("Tabla de Carpetas");
        PTablaCarpetas.add(JTTablaCarpetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        SPTablaCarpetas.setViewportView(PTablaCarpetas);

        PPaquete.add(SPTablaCarpetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 320, 120));

        BAgregarTablaCarpetas.setText("+");
        BAgregarTablaCarpetas.setToolTipText("agrega un nombre de carpeta a crear");
        BAgregarTablaCarpetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAgregarTablaCarpetasActionPerformed(evt);
            }
        });
        PPaquete.add(BAgregarTablaCarpetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        BSeñalarTablaCarpetas.setText("0");
        BSeñalarTablaCarpetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSeñalarTablaCarpetasActionPerformed(evt);
            }
        });
        PPaquete.add(BSeñalarTablaCarpetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 40, -1));

        BEliminarTablaCarpetas.setText("-");
        BEliminarTablaCarpetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEliminarTablaCarpetasActionPerformed(evt);
            }
        });
        PPaquete.add(BEliminarTablaCarpetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, -1, -1));

        BAdministradorTablaCarpetas.setText("^");
        BAdministradorTablaCarpetas.setToolTipText("Abre la ventana del administrador de carpetas");
        BAdministradorTablaCarpetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAdministradorTablaCarpetasActionPerformed(evt);
            }
        });
        PPaquete.add(BAdministradorTablaCarpetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, -1, -1));

        BGuardarTablaCarpetas.setText("G");
        BGuardarTablaCarpetas.setToolTipText("Guarda este conjunto de nombres de carpeta");
        BGuardarTablaCarpetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarTablaCarpetasActionPerformed(evt);
            }
        });
        PPaquete.add(BGuardarTablaCarpetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, -1, -1));

        BClearTablaCarpetas.setText("C");
        BClearTablaCarpetas.setToolTipText("Elimina todos los nombres de carpetas");
        BClearTablaCarpetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BClearTablaCarpetasActionPerformed(evt);
            }
        });
        PPaquete.add(BClearTablaCarpetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, -1, -1));

        BCrearCarpetas.setText("Crear Carpetas");
        BCrearCarpetas.setToolTipText("<html>\nCrea las carpetas con los nombres de la Tabla de Carpetas<br>\ndentro de la direccion de las carpetas\n</html>");
        BCrearCarpetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCrearCarpetasActionPerformed(evt);
            }
        });
        PPaquete.add(BCrearCarpetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, -1));

        TFDireccionCarpetas.setToolTipText("Direccion donde se crearan las carpetas");
        TFDireccionCarpetas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                escribioTFDireccionCarpetas(evt);
            }
        });
        PPaquete.add(TFDireccionCarpetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 480, -1));

        TFDireccionPaquete.setToolTipText("Direccion donde de la carpeta el paquete");
        TFDireccionPaquete.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                escribioTFDireccionPaquete(evt);
            }
        });
        PPaquete.add(TFDireccionPaquete, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 480, -1));

        BAgregarDireccionPaquete.setText("+");
        BAgregarDireccionPaquete.setToolTipText("busca una direccion de una carpeta");
        BAgregarDireccionPaquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAgregarDireccionPaqueteActionPerformed(evt);
            }
        });
        PPaquete.add(BAgregarDireccionPaquete, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        BAdministradorDireccionPaquete.setText("^");
        BAdministradorDireccionPaquete.setToolTipText("Abre la ventana del administrador de direcciones");
        BAdministradorDireccionPaquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAdministradorDireccionPaqueteActionPerformed(evt);
            }
        });
        PPaquete.add(BAdministradorDireccionPaquete, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        BGuardarDireccionPaquete.setText("G");
        BGuardarDireccionPaquete.setToolTipText("Guarda la direccion en el administrador dedirecciones");
        BGuardarDireccionPaquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarDireccionPaqueteActionPerformed(evt);
            }
        });
        PPaquete.add(BGuardarDireccionPaquete, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, -1, -1));

        BClearDireccionPaquete.setText("C");
        BClearDireccionPaquete.setToolTipText("Limpia la direccion");
        BClearDireccionPaquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BClearDireccionPaqueteActionPerformed(evt);
            }
        });
        PPaquete.add(BClearDireccionPaquete, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, -1, -1));

        BAgregarDireccionContenido.setText("+");
        BAgregarDireccionContenido.setToolTipText("busca una direccion de una carpeta");
        BAgregarDireccionContenido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAgregarDireccionContenidoActionPerformed(evt);
            }
        });
        PPaquete.add(BAgregarDireccionContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, -1));

        BAdministradorDireccionContenido.setText("^");
        BAdministradorDireccionContenido.setToolTipText("Abre la ventana del administrador de direcciones");
        BAdministradorDireccionContenido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAdministradorDireccionContenidoActionPerformed(evt);
            }
        });
        PPaquete.add(BAdministradorDireccionContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, -1, -1));

        BGuardarDireccionContenido.setText("G");
        BGuardarDireccionContenido.setToolTipText("Guarda la direccion en el administrador dedirecciones");
        BGuardarDireccionContenido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarDireccionContenidoActionPerformed(evt);
            }
        });
        PPaquete.add(BGuardarDireccionContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 420, -1, -1));

        BClearDireccionContenido.setText("C");
        BClearDireccionContenido.setToolTipText("Limpia la direccion");
        BClearDireccionContenido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BClearDireccionContenidoActionPerformed(evt);
            }
        });
        PPaquete.add(BClearDireccionContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, -1, -1));

        BCopiarContenido.setText("Copiar Contenido");
        BCopiarContenido.setToolTipText("<html>\nCopia en modo TXT el contenido de la carpeta del paquete<br>\ndentro de la carpeta para trabajar\n</html>");
        BCopiarContenido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCopiarContenidoActionPerformed(evt);
            }
        });
        PPaquete.add(BCopiarContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, -1, -1));

        SProfundidad.setModel(new javax.swing.SpinnerNumberModel(6, 1, 20, 1));
        SProfundidad.setToolTipText("cantidad de niveles que profundisara la copia de contenido");
        PPaquete.add(SProfundidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 460, -1, -1));

        LDireccionDeLaCarpeta.setText("Direccion De la Carpeta");
        PPaquete.add(LDireccionDeLaCarpeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 390, -1, -1));

        LDireccionDeCarpetas1.setText("Direccion De Carpetas");
        PPaquete.add(LDireccionDeCarpetas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, -1, -1));

        LDireccionDelPaquete1.setText("Direccion Del Paquete");
        PPaquete.add(LDireccionDelPaquete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, -1));

        BTXTContenido.setText("TXT Contenido");
        BTXTContenido.setToolTipText("<html>\nCopia el contenido de la carpeta para trabajar en un TXT <br>\ndentro de esta misma carpeta\n</html>");
        BTXTContenido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTXTContenidoActionPerformed(evt);
            }
        });
        PPaquete.add(BTXTContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 460, -1, -1));

        BCargarPaquete.setText("Cargar");
        BCargarPaquete.setToolTipText("<html>\nDistribuye el contenido ( Direcciones ) del paquete correctamente entre las distintas secciones\n</html>");
        BCargarPaquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCargarPaqueteActionPerformed(evt);
            }
        });
        PPaquete.add(BCargarPaquete, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        TPTodo.addTab("Paquete", PPaquete);

        PTodo.add(TPTodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 950, 680));

        BClearAll.setText("C");
        BClearAll.setToolTipText("Vacia todas las Secciones actuales ");
        BClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BClearAllActionPerformed(evt);
            }
        });
        PTodo.add(BClearAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, -1, -1));

        BConfiguracion.setText("Configuracion");
        BConfiguracion.setToolTipText("Abre la ventana de configuracion");
        BConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BConfiguracionActionPerformed(evt);
            }
        });
        PTodo.add(BConfiguracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 0, -1, -1));

        BAtras.setText("Atras");
        BAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAtrasActionPerformed(evt);
            }
        });
        PTodo.add(BAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 0, 60, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 932, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PTodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BBuscarDireccionBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBuscarDireccionBaseActionPerformed
        if (Visual.buscador(jf, this)) {
            try {
                addDireccionBase(jf.getSelectedFile());
                actualizarBaseActual();
                actualizarEntrada();
                actualizarTBase();
//                actualizarTodoEntradaSiEsNecesario();
                actualizarTEntrada();
                seguridad();
            } catch (Exception ex) {
                responerException(ex);
            }

        }
    }//GEN-LAST:event_BBuscarDireccionBaseActionPerformed

    private void BBuscarDireccionEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBuscarDireccionEntradaActionPerformed

        //jf.
        if (Visual.buscador(jf, this)) {
            try {
                addDireccionEntrada(jf.getSelectedFile());
                actualizarEntradaActual();
                getInf().getSeccionesEntrada().actualizarDirecionesEntradaTodo();
                actualizarTodoEntradaSiEsNecesario();
                actualizarTEntrada();
                seguridad();
            } catch (Exception ex) {
                responerException(ex);
            }

        }
    }//GEN-LAST:event_BBuscarDireccionEntradaActionPerformed

    private void BActualizarBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BActualizarBaseActionPerformed
//        try {
//            leerTBaseDeSerNecesario();
//
//            actualizarDireccionBase();
//            actualizarTablaSeleccionadosPersonalizados();
//            actualizarBaseActual();
////            if(!getCs().getBasicos().getPersonalizadosString().isEmpty()){
////                System.out.println(getInf().getPersonalizadas().get(0).getEstado());
////             getCs().getBasicos().getPersonalizadosString().get(0).imprimir();
////            }else{
////                System.out.println("vacio");
////            }
//
//            actualizarEntradaActual();
////            if (!getCs().getEntrada().getPersonalizadosSeleccionados().isEmpty()) {
////                // System.out.println(getInf().getPersonalizadas().get(0).getEstado());
////                // getCs().getBasicos().getPersonalizadosString().get(0).imprimir();
////                getCs().getEntrada().getPersonalizadosSeleccionados().imprimir();
////            } else {
////                System.out.println("vacio");
////            }
//            actualizarTBase();
//            actualizarTEntrada();
//            // actualizar();
//            seguridad();
//
////             actualizarCBPersonalizadosBase();
////            actualizarREstadoPersonalizado();
////            actualizarTablas();
////            actualizarTBase();
////            actualizarTEntrada();
////            seguridad();
//        } catch (Exception ex) {
//            responerException(ex);
//        }
        actualizarB();
    }//GEN-LAST:event_BActualizarBaseActionPerformed

    private void BActualizarEntraronActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BActualizarEntraronActionPerformed
        try {
            actualizarDireccionEntrada();
            actualizarEntradaActual();
            actualizarTodoEntradaSiEsNecesario();
            actualizarTEntrada();
            //actualizar();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }

    }//GEN-LAST:event_BActualizarEntraronActionPerformed

    private void apreto(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_apreto

    }//GEN-LAST:event_apreto

    private void BEliminarDireccionBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEliminarDireccionBaseActionPerformed
//        try {
//            eliminarDireccionBase();
//            actualizarBaseActual();
//            actualizarEntrada();
//            actualizarTBase();
//     actualizarTEntrada();
//            seguridad();
//        } catch (Exception ex) {
//            responerException(ex);
//        }
        menosSerieBase();
    }//GEN-LAST:event_BEliminarDireccionBaseActionPerformed

    private void BEliminarDireccionEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEliminarDireccionEntradaActionPerformed
//        try {
//            eliminarDireccionEntrada();
//
//            actualizarEntradaActual();
//            getInf().getSeccionesEntrada().actualizarDirecionesEntradaTodo();
//            actualizarTodoEntradaSiEsNecesario();
//            actualizarTEntrada();
//            seguridad();
//        } catch (Exception ex) {
//            responerException(ex);
//        }
        menosSerieEntrada();
    }//GEN-LAST:event_BEliminarDireccionEntradaActionPerformed

    private void BAgregarInvalidoBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAgregarInvalidoBaseActionPerformed
        masBase();
    }//GEN-LAST:event_BAgregarInvalidoBaseActionPerformed

    private void BEliminarInvalidoBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEliminarInvalidoBaseActionPerformed
        menosBase();
    }//GEN-LAST:event_BEliminarInvalidoBaseActionPerformed

    private void BAgregarInvalidoEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAgregarInvalidoEntradaActionPerformed
        masEntrada();
    }//GEN-LAST:event_BAgregarInvalidoEntradaActionPerformed

    private void BEliminarInvalidoEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEliminarInvalidoEntradaActionPerformed
        menosEntrada();
    }//GEN-LAST:event_BEliminarInvalidoEntradaActionPerformed

    private void BDireccionesBaseAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDireccionesBaseAdministradorActionPerformed
        visualizarVentanaAdministradorDirecciones();
        base = true;
    }//GEN-LAST:event_BDireccionesBaseAdministradorActionPerformed

    private void BInvalidoBaseAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BInvalidoBaseAdministradorActionPerformed
        visualizarVentanaAdministradorInvalidos();
        base = true;
    }//GEN-LAST:event_BInvalidoBaseAdministradorActionPerformed

    private void BDireccionesEntradaAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDireccionesEntradaAdministradorActionPerformed
        visualizarVentanaAdministradorDirecciones();
        base = false;
    }//GEN-LAST:event_BDireccionesEntradaAdministradorActionPerformed

    private void BInvalidoEntradaAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BInvalidoEntradaAdministradorActionPerformed
//        visualizarVentanaAdministradorInvalidos();
        base = false;
    }//GEN-LAST:event_BInvalidoEntradaAdministradorActionPerformed

    private void BGuardarDireccionBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarDireccionBaseActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de las direcciones");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                Admininistrador_De_Guardado<LinkedList<Direccion>> A = new Admininistrador_De_Guardado(get_DIRECCION_ADMINISTRADOR_DIRECCIONES_ACTUAL());
                A.agregarInformacion(nuevoNombre, getDireccionesBaseActual());
                A.guardarAdminastrador(get_DIRECCION_ADMINISTRADOR_DIRECCIONES_ACTUAL());
                // visualizarVentanaAdministradorDirecciones();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BGuardarDireccionBaseActionPerformed

    private void BGuardarInvalidosBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarInvalidosBaseActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de los directorios invalidos");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                Admininistrador_De_Guardado<LinkedList<DirectoriosInvalidos>> A = new Admininistrador_De_Guardado(get_DIRECCION_ADMINISTRADOR_INVALIDOS_ACTUAL());
                A.agregarInformacion(nuevoNombre, getInvalidosBaseActual());
                A.guardarAdminastrador(get_DIRECCION_ADMINISTRADOR_INVALIDOS_ACTUAL());
                // visualizarVentanaAdministradorInvalidos();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BGuardarInvalidosBaseActionPerformed

    private void BGuardarDireccionEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarDireccionEntradaActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de las direcciones");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                Admininistrador_De_Guardado<LinkedList<Direccion>> A = new Admininistrador_De_Guardado(get_DIRECCION_ADMINISTRADOR_DIRECCIONES_ACTUAL());
                A.agregarInformacion(nuevoNombre, getEntrada());
                A.guardarAdminastrador(get_DIRECCION_ADMINISTRADOR_DIRECCIONES_ACTUAL());
                //  visualizarVentanaAdministradorDirecciones();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BGuardarDireccionEntradaActionPerformed

    private void BGuardarInvalidosEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarInvalidosEntradaActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de los directorios invalidos");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                Admininistrador_De_Guardado<LinkedList<DirectoriosInvalidos>> A = new Admininistrador_De_Guardado(get_DIRECCION_ADMINISTRADOR_INVALIDOS_ACTUAL());
                A.agregarInformacion(nuevoNombre, getInvalidosEntradaActual());
                A.guardarAdminastrador(get_DIRECCION_ADMINISTRADOR_INVALIDOS_ACTUAL());
                visualizarVentanaAdministradorInvalidos();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BGuardarInvalidosEntradaActionPerformed

    private void cerrarVentana(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_cerrarVentana
//        try {
//          crearArchivo(DIRECCION_ULTIMO_ESTADO, EA);
//            System.out.println("bb");
//        } catch (Exception ex) {
//            System.out.println("aa");
//                responerException(ex);
//            }

        alCerrar(this);
    }//GEN-LAST:event_cerrarVentana

    private void cerrandoVentana(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_cerrandoVentana
        try {
//            jf.cancelSelection();
//            jf = null;
            crearArchivo(DIRECCION_ULTIMO_ESTADO, EA);
//            System.out.println("cc");
        } catch (Exception ex) {
//            System.out.println("dd");
            responerException(ex);
        }
    }//GEN-LAST:event_cerrandoVentana

    private void apretoRadioEntrada(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apretoRadioEntrada
        try {
            actualizarTEntrada();
            actualizarMarcas();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_apretoRadioEntrada

    private void apretoRadioTipo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apretoRadioTipo
        try {
            actualizarCBTodoExtrenos();
            actualizarRFormaStringEntrada();
            actualizarRFormaStringBase();

            actualizarTablas();
            actualizarTBase();
            actualizarTEntrada();

            actualizarFiltroBase();
            actualizarFiltroEntrada();
            actualizarMarcas();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_apretoRadioTipo

    private void apretoRadioTipoEntrada(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apretoRadioTipoEntrada
        try {

            actualizarTEntrada();
            actualizarMarcas();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_apretoRadioTipoEntrada

    private void apretoRadioBase(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apretoRadioBase
        try {
//            System.out.println("apreto r base *******************");
            actualizarRFormaStringBase();
//            System.out.println("termino *************************");
            actualizarDireccionBase();
            actualizarTBase();
            //  actualizarTEntrada();

//            actualizarFiltroBase();
            actualizarFiltroEntrada();
            actualizarMarcas();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_apretoRadioBase

    private void apretoFormaDeVerBase(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apretoFormaDeVerBase
        try {
            setFormaStringBase();
            actualizarTBase();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_apretoFormaDeVerBase

    private void apretoFormaDeVerEntrada(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apretoFormaDeVerEntrada
        try {
            setFormaStringEntrada();
            actualizarTEntrada();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_apretoFormaDeVerEntrada

    private void apretoFiltroBase(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apretoFiltroBase
        seguridadApretoCBFiltro(evt, getSeccionSerieBaseActual().getFiltro(), CBEnEsperaBase, CBExtrenosBase, CBFinalizadasBase, CBMayoresBase, CBMenoresBase, CBPersonalizadosBase, CBPorVerBase, CBPrimerosBase, CBQueTengoBase, CBSeguidosBase, CBSoloBase, CBUltimosBase,
                CBCapitulos1y0Base, RRelacionadosBase, RFaltantesBase, RContrariosBase);
        actualizarFiltroBase();

        try {
//            System.out.println("getSeccionSerieBaseActual().getFiltro().isEmpty()="+getSeccionSerieBaseActual().getFiltro().isEmpty());
            actualizarBaseActual();
            actualizarEntradaActual();
            actualizarTBase();
            actualizarTodoEntradaSiEsNecesario();
            actualizarTEntrada();
            seguridad();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_apretoFiltroBase

    private void apretoFiltroEntrada(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apretoFiltroEntrada
        seguridadApretoCBFiltro(evt, getSeccionSerieEntradaActual().getFiltro(), CBEnEsperaEntrada, CBExtrenosEntrada, CBFinalizadasEntrada,
                CBMayoresEntrada, CBMenoresEntrada, CBPersonalizadosEntrada,
                CBPorVerEntrada, CBPrimerosEntrada, CBQueTengoEntrada, CBSeguidosEntrada,
                CBSoloEntrada, CBUltimosEntrada, CBCapitulos1y0Entrada, RRelacionadosEntrada, RFaltantesEntrada, RContrariosEntrada);
        actualizarFiltroEntrada();
        try {
            actualizarEntradaActual();
            //actualizarTBase();
            actualizarTodoEntradaSiEsNecesario();
            actualizarTEntrada();
            seguridad();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_apretoFiltroEntrada

    private void escribioTFiltroMenoresBase(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_escribioTFiltroMenoresBase

        if (MetodosUtiles.esNatural(TFiltroMenoresBase.getText())) {
            getSeccionSerieBaseActual().getFiltro().setNumeroMenores(Integer.parseInt(TFiltroMenoresBase.getText()));
        }
        seguridad();
    }//GEN-LAST:event_escribioTFiltroMenoresBase

    private void escribioTFiltroMayoresBase(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_escribioTFiltroMayoresBase
        if (MetodosUtiles.esNatural(TFiltroMayoresBase.getText())) {
            getSeccionSerieBaseActual().getFiltro().setNumeroMayores(Integer.parseInt(TFiltroMayoresBase.getText()));
        }
        seguridad();
    }//GEN-LAST:event_escribioTFiltroMayoresBase

    private void escribioTFiltroSoloBase(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_escribioTFiltroSoloBase
        if (MetodosUtiles.esNatural(TFiltroSoloBase.getText())) {
            getSeccionSerieBaseActual().getFiltro().setNumeroSolo(Integer.parseInt(TFiltroSoloBase.getText()));
        }
        seguridad();
    }//GEN-LAST:event_escribioTFiltroSoloBase

    private void escribioTFiltroSoloEntrada(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_escribioTFiltroSoloEntrada
        if (MetodosUtiles.esNatural(TFiltroSoloEntrada.getText())) {
            getSeccionSerieEntradaActual().getFiltro().setNumeroSolo(Integer.parseInt(TFiltroSoloEntrada.getText()));
        }
        seguridad();
    }//GEN-LAST:event_escribioTFiltroSoloEntrada

    private void escribioTFiltroMayoresEntrada(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_escribioTFiltroMayoresEntrada
        if (MetodosUtiles.esNatural(TFiltroMayoresEntrada.getText())) {
            getSeccionSerieEntradaActual().getFiltro().setNumeroMayores(Integer.parseInt(TFiltroMayoresEntrada.getText()));
        }
        seguridad();
    }//GEN-LAST:event_escribioTFiltroMayoresEntrada

    private void escribioTFiltroMenoresEntrada(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_escribioTFiltroMenoresEntrada
        if (MetodosUtiles.esNatural(TFiltroMenoresEntrada.getText())) {
            getSeccionSerieEntradaActual().getFiltro().setNumeroMenores(Integer.parseInt(TFiltroMenoresEntrada.getText()));
        }
        seguridad();
    }//GEN-LAST:event_escribioTFiltroMenoresEntrada

    private void cambioPanelBase(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cambioPanelBase

        int p = TPBase.getSelectedIndex();
        if (p == 0 || p == 1) {
            if (p != baseSeleccionada) {
//                System.out.println("CBManualBase.isEnabled()=" + CBManualBase.isEnabled());
                if ((p == 1 && CBManualBase.isEnabled()) || p == 0) {
                    try {
                        leerTBaseDeSerNecesario();
                        baseSeleccionada = p;
                        actualizarRFormaStringBase();
                        actualizarDireccionBase();
                        actualizarTBase();
                        actualizarFiltroEntrada();
                        //   seguridad();
                    } catch (Exception ex) {
                        System.out.println("p=" + p + " anterior baseSeleccionada=" + baseSeleccionada);
                        System.out.println("eeeeeeeeeee");
                        responerException(ex);
                    }
                }

            }
            baseSeleccionada = p;
        }
        if (EA != null) {
            seguridad();
        }
    }//GEN-LAST:event_cambioPanelBase

    private void BAgregarManualBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAgregarManualBaseActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de la seccion");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                for (int i = 0; i < getPersonalizadosBase().size(); i++) {
                    if (getPersonalizadosBase().get(i).getNombre().equals(nuevoNombre)) {
                        return;
                    }
                }

                addNuevaSeccionPersonalizadaBase(nuevoNombre);
                actualizarCBPersonalizadosBase();

                seleccionarUltimoAgregadoCBPersonalizadosBase();
                actualizarREstadoPersonalizado();
                actualizarFiltroBase();
                  actualizarDireccionBase();
//                addInvalidoBase(nuevoNombre);
//                actualizarBaseActual();
//                actualizarEntradaActual();
                actualizarTBase();
//                actualizarTEntrada();
                actualizarTablaSeleccionadosPersonalizados();
                actualizarMarcas();
                seguridad();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BAgregarManualBaseActionPerformed

    private void apretoREstadoPersonalizado(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apretoREstadoPersonalizado
        if (EA != null) {
            try {
                leerTBaseDeSerNecesario();
                cambiarEstadoPersonalizadoBase();
                actualizarTBase();
                actualizarMarcas();
                seguridad();
            } catch (Exception ex) {
                responerException(ex);
            }
        }

    }//GEN-LAST:event_apretoREstadoPersonalizado

    private void seleccionarCBManualBase(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_seleccionarCBManualBase
        if (EA != null) {

            try {
                if (evt.getStateChange() == ItemEvent.SELECTED
                        && personalizadoBaseCBSeleccionado != CBManualBase.getSelectedIndex()) {
                    personalizadoBaseCBSeleccionado = CBManualBase.getSelectedIndex();
                    //  System.out.println("cccccccccccccccc");
                    actualizarREstadoPersonalizado();
                    actualizarFiltroBase();
                    actualizarTBase();
                    seguridad();
                }

            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_seleccionarCBManualBase

    private void BEliminarManualBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEliminarManualBaseActionPerformed
        try {
            eliminarPersonalizadoBaseActual();
//            baseDireccionSelecionada--;
            actualizarCBPersonalizadosBase();
            actualizarREstadoPersonalizado();
            actualizarDireccionBase();
            actualizarBaseActual();
            actualizarEntrada();

            actualizarTBase();
//            actualizarTodoEntradaSiEsNecesario();
            actualizarTEntrada();
            // actualizar();
            actualizarTablaSeleccionadosPersonalizados();
            actualizarMarcas();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }

    }//GEN-LAST:event_BEliminarManualBaseActionPerformed

    private void BClearManualBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BClearManualBaseActionPerformed
        try {
            clearPersonalizadoBase();
            crearPredeterminadoPersonalizadoBaseDeSerNecesario();
            actualizarCBPersonalizadosBase();
            actualizarDireccionBase();
            actualizarBaseActual();
            actualizarEntrada();
            actualizarTBase();
//            actualizarTodoEntradaSiEsNecesario();
            actualizarTEntrada();
            // actualizar();
            actualizarTablaSeleccionadosPersonalizados();
            actualizarMarcas();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BClearManualBaseActionPerformed

    private void BDireccionesManualBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDireccionesManualBaseActionPerformed
        visualizarVentanaAdministradorPersonalizadoBase();
        base = true;
    }//GEN-LAST:event_BDireccionesManualBaseActionPerformed

    private void BGuardarManualBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarManualBaseActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de los personalizados");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                Admininistrador_De_Guardado<LinkedList<SeccionPersonalizada>> A = new Admininistrador_De_Guardado(get_DIRECCION_ADMINISTRADOR_PERSONALIZADO_BASE_ACTUAL());
                LinkedList<SeccionPersonalizada> p = new LinkedList<SeccionPersonalizada>();
                p.add(getPersonalizadosBase().get(indiceCBP()));
                A.agregarInformacion(nuevoNombre, p);
                A.guardarAdminastrador(get_DIRECCION_ADMINISTRADOR_PERSONALIZADO_BASE_ACTUAL());
                // visualizarVentanaAdministradorDirecciones();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BGuardarManualBaseActionPerformed

    private void BGuardarAllManualBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarAllManualBaseActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de los personalizados");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                Admininistrador_De_Guardado<LinkedList<SeccionPersonalizada>> A = new Admininistrador_De_Guardado(get_DIRECCION_ADMINISTRADOR_PERSONALIZADO_BASE_ACTUAL());
                A.agregarInformacion(nuevoNombre, getPersonalizadosBase());
                A.guardarAdminastrador(get_DIRECCION_ADMINISTRADOR_PERSONALIZADO_BASE_ACTUAL());
                // visualizarVentanaAdministradorDirecciones();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BGuardarAllManualBaseActionPerformed

    private void BAlmacenPredeterminadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAlmacenPredeterminadoActionPerformed
//        String predeterminado = "_Predeterminado";
        try {
//            if (getInf().getSeccionesBase().getPersonalizadas().isEmpty() || !getInf().getSeccionesBase().getPersonalizadas().get(0).getNombre().equals(predeterminado)) {
//                getInf().getSeccionesBase().getPersonalizadas().add(0, new SeccionPersonalizada(predeterminado));
//            }
            crearPredeterminadoPersonalizadoBaseDeSerNecesario();
            getInf().getSeccionesBase().getPersonalizadas().get(0).setCatalogo(getCatalogoEntrada());
            getInf().getSeccionesBase().getPersonalizadas().get(0).setEstado(estadoPersonalizado.CATALOGO);

            leerTBaseDeSerNecesario();
            //actualizarDireccionBase();
            actualizarTablaSeleccionadosPersonalizados();
            if (baseSeleccionada != 1) {
                actualizarBaseActual();
            }
            getCs().getBasicos().actualizarPersonalizados(cdv);
            actualizarEntradaActual();
            if (baseSeleccionada == 1) {
                actualizarTBase();
            }
            actualizarTEntrada();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BAlmacenPredeterminadoActionPerformed

    private void BAlmacenSeleccionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAlmacenSeleccionadoActionPerformed
        try {
            getPersonalizadosBase().get(indiceCBP()).setCatalogo(getCatalogoEntrada());
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BAlmacenSeleccionadoActionPerformed

    private void BAgregarDireccionTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAgregarDireccionTXTActionPerformed
        if (Visual.buscador(jf, this)) {
            try {
                //TFDireccionTXT.setText(jf.getSelectedFile().toString());
                setFileEnTF_TXT(jf.getSelectedFile());
                seguridad();
            } catch (Exception ex) {
                responerException(ex);
            }

        }
    }//GEN-LAST:event_BAgregarDireccionTXTActionPerformed

    private void BClearDireccionTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BClearDireccionTXTActionPerformed
        TFDireccionTXT.setText("");
    }//GEN-LAST:event_BClearDireccionTXTActionPerformed

    private void BAdministradorDireccionTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAdministradorDireccionTXTActionPerformed
        visualizarVentanaAdministradorTXT();
    }//GEN-LAST:event_BAdministradorDireccionTXTActionPerformed

    private void BTXTBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTXTBaseActionPerformed
        try {
            String nuevoNombre = JOptionPane.showInputDialog("Escriba el nombre del TXT");
            if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                Visual.guardarEnTXTJTextArea(TBase, new File(TFDireccionTXT.getText()), nuevoNombre);
            }
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BTXTBaseActionPerformed

    private void BTXTEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTXTEntradaActionPerformed
        try {
            String nuevoNombre = JOptionPane.showInputDialog("Escriba el nombre del TXT");
            if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                Visual.guardarEnTXTJTextArea(TEntraron, new File(TFDireccionTXT.getText()), nuevoNombre);
            }

        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BTXTEntradaActionPerformed

    private void BGuardarDireccionTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarDireccionTXTActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de las direccione del TXT");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                Admininistrador_De_Guardado<File> A = new Admininistrador_De_Guardado(get_DIRECCION_ADMINISTRADOR_TXT_ACTUAL());
                A.agregarInformacion(nuevoNombre, new File(TFDireccionTXT.getText()));
                A.guardarAdminastrador(get_DIRECCION_ADMINISTRADOR_TXT_ACTUAL());
                // visualizarVentanaAdministradorTXT();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BGuardarDireccionTXTActionPerformed

    private void cambioPanelTodo(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cambioPanelTodo
        // TODO add your handling code here:
    }//GEN-LAST:event_cambioPanelTodo

    private void BAdministradorDireccionCarpetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAdministradorDireccionCarpetasActionPerformed
        visualizarVentanaAdministradorDirecciones_Carpetas();
        dir = direccionPaquete.CARPETAS;
    }//GEN-LAST:event_BAdministradorDireccionCarpetasActionPerformed

    private void BAdministradorTablaCarpetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAdministradorTablaCarpetasActionPerformed
        visualizarVentanaAdministradorNombre_Carpeta();
    }//GEN-LAST:event_BAdministradorTablaCarpetasActionPerformed

    private void BAgregarDireccionCarpetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAgregarDireccionCarpetasActionPerformed
        jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (Visual.buscador(jf, this)) {
            try {
                //TFDireccionTXT.setText(jf.getSelectedFile().toString());
                setFileEnTF_DireccionCarpeta(jf.getSelectedFile());
                seguridad();
            } catch (Exception ex) {
                responerException(ex);
            }

        }
        jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    }//GEN-LAST:event_BAgregarDireccionCarpetasActionPerformed

    private void BAgregarTablaCarpetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAgregarTablaCarpetasActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de la carpeta");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                addNuevoNombreDeCarpeta(nuevoNombre);
                actualizarTablaCarpetas();
                seguridad();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BAgregarTablaCarpetasActionPerformed

    private void BGuardarDireccionCarpetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarDireccionCarpetasActionPerformed
        agregarEnAdministradorFile(new File(TFDireccionContenido.getText()), "Escriba el nuevo nombre de las direccion donde se crearan las carpetas");
//        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de las direccione de la carpeta del paquete");
//        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
//            try {
//                Admininistrador_De_Guardado<File> A = new Admininistrador_De_Guardado(DIRECCION_ADMINISTRADOR_DIRECCION_CARPETA);
//                A.agregarInformacion(nuevoNombre, new File(TFDireccionContenido.getText()));
//                A.guardarAdminastrador(DIRECCION_ADMINISTRADOR_DIRECCION_CARPETA);
//                // visualizarVentanaAdministradorTXT();
//            } catch (Exception ex) {
//                responerException(ex);
//            }
//        }
    }//GEN-LAST:event_BGuardarDireccionCarpetasActionPerformed

    private void BGuardarTablaCarpetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarTablaCarpetasActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de las carpetas");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                Admininistrador_De_Guardado<LinkedList<NombreCarpeta>> A = new Admininistrador_De_Guardado(DIRECCION_ADMINISTRADOR_NOMBRES_CARPETA);
                A.agregarInformacion(nuevoNombre, EA.getCdc().getNombresCarpetas());
                A.guardarAdminastrador(DIRECCION_ADMINISTRADOR_NOMBRES_CARPETA);
                // visualizarVentanaAdministradorDirecciones();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BGuardarTablaCarpetasActionPerformed

    private void BEliminarTablaCarpetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEliminarTablaCarpetasActionPerformed
        try {
            eliminarNombreCarpetaSeleccionado();
            actualizarTablaCarpetas();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BEliminarTablaCarpetasActionPerformed

    private void BClearTablaCarpetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BClearTablaCarpetasActionPerformed
        try {
            EA.getCdc().getNombresCarpetas().clear();
            actualizarTablaCarpetas();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BClearTablaCarpetasActionPerformed

    private void BClearDireccionCarpetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BClearDireccionCarpetasActionPerformed
        TFDireccionContenido.setText("");
        EA.getCdc().setDireccion(new File(""));
        seguridad();
    }//GEN-LAST:event_BClearDireccionCarpetasActionPerformed

    private void BCrearCarpetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCrearCarpetasActionPerformed

        for (int i = 0; i < EA.getCdc().getNombresCarpetas().size(); i++) {
            if (EA.getCdc().getNombresCarpetas().get(i).isSeleccionado()) {
                new File(EA.getCdc().getDireccion() + "\\" + EA.getCdc().getNombresCarpetas().get(i).getNombreCarpeta()).mkdirs();
            }

        }
    }//GEN-LAST:event_BCrearCarpetasActionPerformed

    private void apretoSeccionEntrada(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apretoSeccionEntrada
        try {
//            System.out.println("aaaaaaaaa");
            actualizarDireccionEntrada();
//            System.out.println("bbbbbbbbb");
            actualizarFiltroEntrada();
//            System.out.println("ccccccccccc");
            actualizarTEntrada();
//            System.out.println("ddddddddddddd");
            actualizarMarcas();
//            System.out.println("fffffffffff");
            seguridad();
//            System.out.println("ggggggggggggg");
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_apretoSeccionEntrada

    private void BAgregarPersonalizadosSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAgregarPersonalizadosSeccionActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de la seccion");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                for (int i = 0; i < getInf().getSeccionesEntrada().getPersonalizadas().size(); i++) {
                    if (getInf().getSeccionesEntrada().getPersonalizadas().get(i).getNombre().equals(nuevoNombre)) {
                        return;
                    }
                }

                // addNuevaSeccionPersonalizadaBase(nuevoNombre);
                addNuevaSeccionPersonalizadaEntrada(nuevoNombre);
                //actualizarCBPersonalizadosBase();
                actualizarCBPersonalizadosEntrada();
                Visual.seleccionarUltimoAgregadoJComboBox(CBPersonalizadosSeccion);
                //  seleccionarUltimoAgregadoCBPersonalizadosBase();
                // actualizarREstadoPersonalizado();
                //  actualizarTBase();
                actualizarDireccionEntrada();
                actualizarFiltroEntrada();
                actualizarTEntrada();
                actualizarMarcas();
                //  actualizarTablaSeleccionadosPersonalizados();
                seguridad();
            } catch (Exception ex) {
                responerException(ex);
            }
            // aa
        }
    }//GEN-LAST:event_BAgregarPersonalizadosSeccionActionPerformed

    private void BEliminarPersonalizadosSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEliminarPersonalizadosSeccionActionPerformed
        try {
//            eliminarPersonalizadoBaseActual();
            eliminarPersonalizadoEntradaActual();
            if (getInf().getSeccionesEntrada().getPersonalizadas().isEmpty()) {
                RClasicasSeccion.setSelected(true);
            }
            actualizarCBPersonalizadosEntrada();
            actualizarDireccionEntrada();
            actualizarFiltroEntrada();
//            actualizarCBPersonalizadosBase();
//            actualizarREstadoPersonalizado();
//            actualizarBaseActual();
            actualizarEntradaActual();
            getInf().getSeccionesEntrada().actualizarDirecionesEntradaTodo();
//            actualizarTBase();
            actualizarTodoEntradaSiEsNecesario();
            actualizarTEntrada();
            actualizarMarcas();
//            actualizarTablaSeleccionadosPersonalizados();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }

    }//GEN-LAST:event_BEliminarPersonalizadosSeccionActionPerformed

    private void BClearPersonalizadosSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BClearPersonalizadosSeccionActionPerformed
        try {
            clearPersonalizadoEntrada();
            RClasicasSeccion.setSelected(true);
            actualizarCBPersonalizadosEntrada();
            actualizarFiltroEntrada();
//            actualizarCBPersonalizadosBase();
//            actualizarBaseActual();
            actualizarEntradaActual();
//            actualizarTBase();
            getInf().getSeccionesEntrada().actualizarDirecionesEntradaTodo();
            actualizarTodoEntradaSiEsNecesario();
            actualizarTEntrada();
            actualizarMarcas();
//            actualizarTablaSeleccionadosPersonalizados();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }

    }//GEN-LAST:event_BClearPersonalizadosSeccionActionPerformed

    private void BAdministradorPersonalizadosSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAdministradorPersonalizadosSeccionActionPerformed
        visualizarVentanaAdministradorPersonalizadoEntrada();
        base = false;

    }//GEN-LAST:event_BAdministradorPersonalizadosSeccionActionPerformed

    private void BGuardarPersonalizadosSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarPersonalizadosSeccionActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de los personalizados");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                Admininistrador_De_Guardado<LinkedList<SeccionPersonalizada>> A = new Admininistrador_De_Guardado(get_DIRECCION_ADMINISTRADOR_PERSONALIZADO_ENTRADA_ACTUAL());
                LinkedList<SeccionPersonalizada> p = new LinkedList<SeccionPersonalizada>();
                p.add(getInf().getSeccionesEntrada().getPersonalizadas().get(indiceCBSeccion()));
                A.agregarInformacion(nuevoNombre, p);
                A.guardarAdminastrador(get_DIRECCION_ADMINISTRADOR_PERSONALIZADO_ENTRADA_ACTUAL());
                // visualizarVentanaAdministradorDirecciones();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BGuardarPersonalizadosSeccionActionPerformed

    private void BGuardarAllPersonalizadosSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarAllPersonalizadosSeccionActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de los personalizados");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                Admininistrador_De_Guardado<LinkedList<SeccionPersonalizada>> A = new Admininistrador_De_Guardado(get_DIRECCION_ADMINISTRADOR_PERSONALIZADO_ENTRADA_ACTUAL());
                A.agregarInformacion(nuevoNombre, getInf().getSeccionesEntrada().getPersonalizadas());
                A.guardarAdminastrador(get_DIRECCION_ADMINISTRADOR_PERSONALIZADO_ENTRADA_ACTUAL());
                // visualizarVentanaAdministradorDirecciones();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BGuardarAllPersonalizadosSeccionActionPerformed

    private void selecionaoCBPersonalizadoEntrada(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_selecionaoCBPersonalizadoEntrada
        if (EA != null) {

            try {
                if (evt.getStateChange() == ItemEvent.SELECTED
                        && personalizadoEntradaCBSeleccionado != CBPersonalizadosSeccion.getSelectedIndex()) {
                    personalizadoEntradaCBSeleccionado = CBPersonalizadosSeccion.getSelectedIndex();
                    //  System.out.println("cccccccccccccccc");
//                    actualizarREstadoPersonalizado();
//                    actualizarTBase();
                    actualizarDireccionEntrada();
                    actualizarFiltroEntrada();
                    actualizarTodoEntradaSiEsNecesario();
                    actualizarTEntrada();
                    actualizarMarcas();
                    seguridad();
                }

            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_selecionaoCBPersonalizadoEntrada

    private void BAdministradorDireccionPaqueteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAdministradorDireccionPaqueteActionPerformed
        visualizarVentanaAdministradorDirecciones_Carpetas();
        dir = direccionPaquete.PAQUETE;
    }//GEN-LAST:event_BAdministradorDireccionPaqueteActionPerformed

    private void BAdministradorDireccionContenidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAdministradorDireccionContenidoActionPerformed
        visualizarVentanaAdministradorDirecciones_Carpetas();
        dir = direccionPaquete.CONTENIDO;
    }//GEN-LAST:event_BAdministradorDireccionContenidoActionPerformed

    private void BAgregarDireccionPaqueteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAgregarDireccionPaqueteActionPerformed
        jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (Visual.buscador(jf, this)) {
            try {
                //TFDireccionTXT.setText(jf.getSelectedFile().toString());
                setFileEnTF_DireccionPaquete(jf.getSelectedFile());
                seguridad();
            } catch (Exception ex) {
                responerException(ex);
            }

        }
        jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    }//GEN-LAST:event_BAgregarDireccionPaqueteActionPerformed

    private void BAgregarDireccionContenidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAgregarDireccionContenidoActionPerformed
        jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (Visual.buscador(jf, this)) {
            try {
                //TFDireccionTXT.setText(jf.getSelectedFile().toString());
                setFileEnTF_DireccionContenido(jf.getSelectedFile());
                seguridad();
            } catch (Exception ex) {
                responerException(ex);
            }

        }
        jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    }//GEN-LAST:event_BAgregarDireccionContenidoActionPerformed

    private void BClearDireccionPaqueteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BClearDireccionPaqueteActionPerformed
        TFDireccionPaquete.setText("");
        EA.setPaquete(new File(""));
        seguridad();
    }//GEN-LAST:event_BClearDireccionPaqueteActionPerformed

    private void BClearDireccionContenidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BClearDireccionContenidoActionPerformed
        TFDireccionContenido.setText("");
        EA.setContenido(new File(""));
        seguridad();
    }//GEN-LAST:event_BClearDireccionContenidoActionPerformed

    private void BGuardarDireccionPaqueteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarDireccionPaqueteActionPerformed
        agregarEnAdministradorFile(new File(TFDireccionPaquete.getText()), "Escriba el nuevo nombre de la direccion de la carpeta del paquete");
    }//GEN-LAST:event_BGuardarDireccionPaqueteActionPerformed

    private void BGuardarDireccionContenidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarDireccionContenidoActionPerformed
        agregarEnAdministradorFile(new File(TFDireccionContenido.getText()), "Escriba el nuevo nombre de las direccion donde se guardara la informacion del paquete");
    }//GEN-LAST:event_BGuardarDireccionContenidoActionPerformed

    private void BCopiarContenidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCopiarContenidoActionPerformed
        Archivo.crearTXTContenidoDeCarpetaYCarpetas(EA.getPaquete(), (Integer) SProfundidad.getValue(), EA.getContenido(), EA.getPaquete().getName());
    }//GEN-LAST:event_BCopiarContenidoActionPerformed

    private void BTXTContenidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTXTContenidoActionPerformed
        File f = new File(TFDireccionContenido.getText());
        Archivo.crearTXTContenidoDeCarpeta(f, f, "contenido", new String[]{});
    }//GEN-LAST:event_BTXTContenidoActionPerformed

    private void BCargarPaqueteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCargarPaqueteActionPerformed
        try {
//            System.out.println("a");
            Paquete p = new Paquete(new File(TFDireccionPaquete.getText()), cdv);
//            System.out.println(p.isEmpty());
//            System.out.println("b");
//            LinkedList<Direccion> direcciones=new LinkedList<Direccion>();
            if (p.getMangas() != null) {
                if (p.getMangas().getTX() != null) {
//                    System.out.println("a");
                    EA.getAnime().getSeccionesEntrada().getTX().addAll(p.getMangas().getTX());
                }
                if (p.getMangas().getClasicas() != null) {
//                    System.out.println("b");
                    EA.getAnime().getSeccionesEntrada().getClasicas().addAll(p.getMangas().getClasicas());
                }
                if (p.getMangas().getFinalizadas() != null) {
//                    System.out.println("c");
                    EA.getAnime().getSeccionesEntrada().getFinalizadas().addAll(p.getMangas().getFinalizadas());
                }
            }
//            System.out.println("c");
            if (p.getSeries() != null) {
                if (p.getSeries().getEnTransmision() != null) {
                    if (p.getSeries().getEnTransmision().getTX() != null) {
//                        System.out.println("d");
                        EA.getSeries().getSeccionesEntrada().getTX().addAll(p.getSeries().getEnTransmision().getTX());
                    }
                    if (p.getSeries().getEnTransmision().getClasicas() != null) {
//                        System.out.println("e");
                        EA.getSeries().getSeccionesEntrada().getClasicas().addAll(p.getSeries().getEnTransmision().getClasicas());
                    }
                    if (p.getSeries().getEnTransmision().getDobladas() != null) {
//                        System.out.println("f");
                        EA.getSeries().getSeccionesEntrada().getDobladas().addAll(p.getSeries().getEnTransmision().getDobladas());
                    }
                }
                if (p.getSeries().getFinalizadas() != null) {
                    if (p.getSeries().getFinalizadas().getFinalizadas() != null) {
//                        System.out.println("g");
                        EA.getSeries().getSeccionesEntrada().getFinalizadas().addAll(p.getSeries().getFinalizadas().getFinalizadas());
                    }
                    if (p.getSeries().getFinalizadas().getFinalizadasEspañol() != null) {
//                        System.out.println("h");
                        EA.getSeries().getSeccionesEntrada().getFinalizadas().addAll(p.getSeries().getFinalizadas().getFinalizadasEspañol());
                    }
                }
            }
//            System.out.println("d");
//            actualizarDireccionEntrada();
//            actualizarEntradaActual();
//            
//             actualizarEntrada();
//            actualizarBase();
//            C.actualizarCatalogos(cdv);
            C.getAnime().actualizarEntradas(IncluirExtrenosAnime, cdv);
//             System.out.println("d 1");
            C.getSeries().actualizarEntradas(IncluirExtrenosSerie, cdv);
//            System.out.println("d 2");
            EA.getAnime().getSeccionesEntrada().actualizarDirecionesEntradaTodo();
//            System.out.println("d 3");
            EA.getSeries().getSeccionesEntrada().actualizarDirecionesEntradaTodo();
//            System.out.println("e");
            actualizarTablas();

            actualizarTBase();
            actualizarTEntrada();
//            System.out.println("f");
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BCargarPaqueteActionPerformed

    private void BClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BClearAllActionPerformed
        try {
            EA.getAnime().getSeccionesBase().clearBase();
            EA.getAnime().getSeccionesEntrada().clearEntradas();
            EA.getSeries().getSeccionesBase().clearBase();
            EA.getSeries().getSeccionesEntrada().clearEntradas();
            C.getAnime().getBasicos().getPersonalizadosDiereccion().clear();
            C.getAnime().getBasicos().getPersonalizadosString().clear();
            C.getSeries().getBasicos().getPersonalizadosDiereccion().clear();
            C.getSeries().getBasicos().getPersonalizadosString().clear();
            crearPredeterminadoPersonalizadoBaseDeSerNecesario();
//            actualizarBase();
            actualizarTodasLasBases();
            actualizarTodasLasEntradas();
//            actualizarEntrada();
            actualizarDireccionBase();
            actualizarDireccionEntrada();
            actualizarTBase();
            actualizarTEntrada();
            actualizarMarcas();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BClearAllActionPerformed

    private void BClearEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BClearEntradaActionPerformed
        try {
            getInf().getSeccionesEntrada().clearEntradas();
//        actualizarBaseActual();
            actualizarEntrada();
            actualizarDireccionEntrada();
//        actualizarTBase();
            actualizarTEntrada();
            actualizarMarcas();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BClearEntradaActionPerformed

    private void BClearBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BClearBaseActionPerformed
        try {
            getInf().getSeccionesBase().clearBase();
            C.getAnime().getBasicos().getPersonalizadosDiereccion().clear();
            C.getAnime().getBasicos().getPersonalizadosString().clear();
            C.getSeries().getBasicos().getPersonalizadosDiereccion().clear();
            C.getSeries().getBasicos().getPersonalizadosString().clear();
            crearPredeterminadoPersonalizadoBaseDeSerNecesario();
            actualizarBase();
            actualizarEntrada();
            actualizarDireccionBase();
            actualizarDireccionEntrada();
            actualizarTBase();
            actualizarTEntrada();
            actualizarMarcas();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BClearBaseActionPerformed

    private void BSeñalarTDireccionesBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSeñalarTDireccionesBaseActionPerformed
        apretoMarca(getDireccionesBaseActual(), BSeñalarTDireccionesBase);
        actualizarB();
    }//GEN-LAST:event_BSeñalarTDireccionesBaseActionPerformed

    private void BSeñalarTInvalidosBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSeñalarTInvalidosBaseActionPerformed
        apretoMarca(getInvalidosBaseActual(), BSeñalarTInvalidosBase);
        actualizarB();
    }//GEN-LAST:event_BSeñalarTInvalidosBaseActionPerformed

    private void BSeñalarTDireccionesEntraronActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSeñalarTDireccionesEntraronActionPerformed
        apretoMarca(getEntrada(), BSeñalarTDireccionesEntraron);
        try {
            actualizarDireccionEntrada();
            actualizarEntradaActual();
            actualizarTodoEntradaSiEsNecesario();
            actualizarTEntrada();
            //actualizar();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BSeñalarTDireccionesEntraronActionPerformed

    private void BSeñalarTInvalidosEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSeñalarTInvalidosEntradaActionPerformed
        apretoMarca(getInvalidosEntradaActual(), BSeñalarTInvalidosEntrada);
        try {
            actualizarDireccionEntrada();
            actualizarEntradaActual();
            actualizarTodoEntradaSiEsNecesario();
            actualizarTEntrada();
            //actualizar();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BSeñalarTInvalidosEntradaActionPerformed

    private void BSeñalarTablaCarpetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSeñalarTablaCarpetasActionPerformed
        try {
            apretoMarca(EA.getCdc().getNombresCarpetas(), BSeñalarTablaCarpetas);
            actualizarTablaCarpetas();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BSeñalarTablaCarpetasActionPerformed

    private void BAgregarEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAgregarEntradaActionPerformed
        if (Visual.buscador(jf, this)) {
            try {
                boolean actulalizar = false;
                if (RAnime.isSelected()) {
                    CarpetaMangas c = new CarpetaMangas(jf.getSelectedFile(), cdv);
//                     if (p.getMangas() != null) {
                    if (c.getTX() != null) {
                        EA.getAnime().getSeccionesEntrada().getTX().addAll(c.getTX());
                        actulalizar = true;
                    }
                    if (c.getClasicas() != null) {
                        EA.getAnime().getSeccionesEntrada().getClasicas().addAll(c.getClasicas());
                        actulalizar = true;
                    }
                    if (c.getFinalizadas() != null) {
                        EA.getAnime().getSeccionesEntrada().getFinalizadas().addAll(c.getFinalizadas());
                        actulalizar = true;
                    }
//            }
                    if (actulalizar) {
                        C.getAnime().actualizarEntradas(IncluirExtrenosAnime, cdv);
                        EA.getAnime().getSeccionesEntrada().actualizarDirecionesEntradaTodo();
//                        EA.getSeries().actualizarDirecionesEntradaTodo();
                        actualizarTablas();

                        actualizarTBase();
                        actualizarTEntrada();
                        seguridad();
                    }

                } else {
                    CarpetaSeries c = new CarpetaSeries(jf.getSelectedFile(), cdv);
//                    if (p.getSeries() != null) {
                    if (c.getEnTransmision() != null) {
                        if (c.getEnTransmision().getTX() != null) {
                            EA.getSeries().getSeccionesEntrada().getTX().addAll(c.getEnTransmision().getTX());
                            actulalizar = true;
                        }
                        if (c.getEnTransmision().getClasicas() != null) {
                            EA.getSeries().getSeccionesEntrada().getClasicas().addAll(c.getEnTransmision().getClasicas());
                            actulalizar = true;
                        }
                        if (c.getEnTransmision().getDobladas() != null) {
                            EA.getSeries().getSeccionesEntrada().getDobladas().addAll(c.getEnTransmision().getDobladas());
                            actulalizar = true;
                        }
                    }
                    if (c.getFinalizadas() != null) {
                        if (c.getFinalizadas().getFinalizadas() != null) {
                            EA.getSeries().getSeccionesEntrada().getFinalizadas().addAll(c.getFinalizadas().getFinalizadas());
                            actulalizar = true;
                        }
                        if (c.getFinalizadas().getFinalizadasEspañol() != null) {
                            EA.getSeries().getSeccionesEntrada().getFinalizadas().addAll(c.getFinalizadas().getFinalizadasEspañol());
                            actulalizar = true;
                        }
                    }
                    if (actulalizar) {
                        C.getSeries().actualizarEntradas(IncluirExtrenosSerie, cdv);
//                        EA.getAnime().actualizarDirecionesEntradaTodo();
                        EA.getSeries().getSeccionesEntrada().actualizarDirecionesEntradaTodo();
                        actualizarTablas();

                        actualizarTBase();
                        actualizarTEntrada();
                        actualizarMarcas();
                        seguridad();
                    }

//            }
                }

            } catch (Exception ex) {
                responerException(ex);
            }

        }

    }//GEN-LAST:event_BAgregarEntradaActionPerformed

    private void BClearDireccionBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BClearDireccionBaseActionPerformed
        try {
//            eliminarDireccionBase();
            getDireccionesBaseActual().clear();
            actualizarDireccionBase();
            actualizarBaseActual();
            actualizarEntrada();
            actualizarTBase();

//            actualizarTodoEntradaSiEsNecesario();
            actualizarTEntrada();
            // actualizar();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BClearDireccionBaseActionPerformed

    private void BClearInvalidosBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BClearInvalidosBaseActionPerformed
        try {

            clearInvalidoBase();
            actualizarBaseActual();
            actualizarEntrada();
            actualizarTBase();
//            actualizarTodoEntradaSiEsNecesario();
            actualizarTEntrada();
//            actualizar();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BClearInvalidosBaseActionPerformed

    private void BClearDireccionEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BClearDireccionEntradaActionPerformed
        try {
            getEntrada().clear();
            actualizarDireccionEntrada();

//            eliminarDireccionEntrada();
            actualizarEntradaActual();
            getInf().getSeccionesEntrada().actualizarDirecionesEntradaTodo();
            // actualizarTBase();
            actualizarTodoEntradaSiEsNecesario();
            actualizarTEntrada();
//            actualizar();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BClearDireccionEntradaActionPerformed

    private void BClearInvalidosEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BClearInvalidosEntradaActionPerformed
        try {
//        eliminarInvalidoEntrada();
            clearInvalidoEntrada();
            actualizarEntradaActual();
//             actualizarTBase();
            actualizarTodoEntradaSiEsNecesario();
            actualizarTEntrada();
//            actualizar();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BClearInvalidosEntradaActionPerformed

    private void BCatalogoBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCatalogoBaseActionPerformed
        try {
            String nuevoNombre = JOptionPane.showInputDialog("Escriba el nombre del Catalogo");
            if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
//                Visual.guardarEnTXTJTextArea(TBase, new File(TFDireccionTXT.getText()), nuevoNombre);
                Archivo.crearArchivo(new File(TFDireccionTXT.getText()), nuevoNombre, EXTENCION_CATALGO, getCatalogoBase());
            }
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BCatalogoBaseActionPerformed

    private void BCatalogoEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCatalogoEntradaActionPerformed
        try {
            String nuevoNombre = JOptionPane.showInputDialog("Escriba el nombre del Catalogo");
            if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
//                Visual.guardarEnTXTJTextArea(TBase, new File(TFDireccionTXT.getText()), nuevoNombre);
                Archivo.crearArchivo(new File(TFDireccionTXT.getText()), nuevoNombre, EXTENCION_CATALGO, getCatalogoEntrada());
            }
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BCatalogoEntradaActionPerformed

    private void BAdministradorContenidoBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAdministradorContenidoBaseActionPerformed
        visualizarVentanaAdministradorSecciones_Base();
    }//GEN-LAST:event_BAdministradorContenidoBaseActionPerformed

    private void BAdministradorContenidoEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAdministradorContenidoEntradaActionPerformed
        visualizarVentanaAdministradorSecciones_Entrada();
    }//GEN-LAST:event_BAdministradorContenidoEntradaActionPerformed

    private void BGuardarContenidoBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarContenidoBaseActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de la configuaracion de secciones ");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                Admininistrador_De_Guardado<SeccionesBase> A = new Admininistrador_De_Guardado(DIRECCION_ADMINISTRADOR_SECCIONES_BASE);
                A.agregarInformacion(nuevoNombre, getInf().getSeccionesBase());
                A.guardarAdminastrador(DIRECCION_ADMINISTRADOR_SECCIONES_BASE);
                // visualizarVentanaAdministradorDirecciones();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BGuardarContenidoBaseActionPerformed

    private void BGuardarContenidoEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarContenidoEntradaActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de la configuaracion de secciones ");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                Admininistrador_De_Guardado<SeccionesEntrada> A = new Admininistrador_De_Guardado(DIRECCION_ADMINISTRADOR_SECCIONES_ENTRADA);
                A.agregarInformacion(nuevoNombre, getInf().getSeccionesEntrada());
                A.guardarAdminastrador(DIRECCION_ADMINISTRADOR_SECCIONES_ENTRADA);
                // visualizarVentanaAdministradorDirecciones();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BGuardarContenidoEntradaActionPerformed

    private void BConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BConfiguracionActionPerformed
        visualizarVentanaConfiguracionDeVideo();
    }//GEN-LAST:event_BConfiguracionActionPerformed

    private void BAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAtrasActionPerformed
        Visual.maximizarVentana(todo.Todo.ventanaTodo);
        setVisible(false);
    }//GEN-LAST:event_BAtrasActionPerformed

    private void escribioTFDireccionPaquete(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_escribioTFDireccionPaquete
        EA.setPaquete(new File(TFDireccionPaquete.getText()));
        seguridad();

    }//GEN-LAST:event_escribioTFDireccionPaquete

    private void escribioTFDireccionCarpetas(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_escribioTFDireccionCarpetas
        EA.getCdc().setDireccion(new File(TFDireccionCarpetas.getText()));
        seguridad();
    }//GEN-LAST:event_escribioTFDireccionCarpetas

    private void escribioTFDireccionContenido(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_escribioTFDireccionContenido
        EA.setContenido(new File(TFDireccionContenido.getText()));
        seguridad();        // TODO add your handling code here:
    }//GEN-LAST:event_escribioTFDireccionContenido

    private void escribioTFDireccionTXT(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_escribioTFDireccionTXT
        EA.setTXT(new File(TFDireccionTXT.getText()));
        seguridad();
    }//GEN-LAST:event_escribioTFDireccionTXT

    private void escribiendoNumero(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_escribiendoNumero
        //if (!MetodosUtiles.esNumero(evt.getKeyChar() + "")) {
//        if (!(evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9')) {
//            evt.consume();
//        }
        Visual.escribirSoloNumerosEnteros(evt);
    }//GEN-LAST:event_escribiendoNumero

    private void apretoCBIncluirExtrenoSeccion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apretoCBIncluirExtrenoSeccion
        if (RAnime.isSelected()) {
            IncluirExtrenosAnime = CBTodoExtrenosSeccion.isSelected();

        } else {
            IncluirExtrenosSerie = CBTodoExtrenosSeccion.isSelected();
        }
        CBTodoExtrenosEntrada.setSelected(CBTodoExtrenosSeccion.isSelected());
        actualizarCatalogosTodoActual();
        actualizarTEntrada();
        seguridad();
//        System.out.println("IncluirExtrenosAnime="+IncluirExtrenosAnime);
    }//GEN-LAST:event_apretoCBIncluirExtrenoSeccion

    private void apretoCBIncluirExtrenoEntrada(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apretoCBIncluirExtrenoEntrada
        if (RAnime.isSelected()) {
            IncluirExtrenosAnime = CBTodoExtrenosEntrada.isSelected();

        } else {
            IncluirExtrenosSerie = CBTodoExtrenosEntrada.isSelected();
        }
        CBTodoExtrenosSeccion.setSelected(CBTodoExtrenosEntrada.isSelected());
        actualizarCatalogosTodoActual();
        actualizarTEntrada();
        seguridad();
//        System.out.println("IncluirExtrenosAnime="+IncluirExtrenosAnime);
    }//GEN-LAST:event_apretoCBIncluirExtrenoEntrada

    private void apretoTextArea(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apretoTextArea
        if (!((JTextArea) evt.getSource()).isEditable()) {
            Visual.señalarJTextTarea(((JTextArea) evt.getSource()), evt, cdv);
        }
//        System.out.println("aaaaa");
    }//GEN-LAST:event_apretoTextArea

    private void apretoTeclaBase(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apretoTeclaBase
        if (Visual.realizarDeleteTabla(evt, BEliminarDireccionBase)) {
            menosSerieBase();
        }
    }//GEN-LAST:event_apretoTeclaBase

    private void apretoTeclaInvalidosBase(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apretoTeclaInvalidosBase
        if (Visual.realizarDeleteTabla(evt, BEliminarInvalidoBase)) {
            menosBase();
        }
    }//GEN-LAST:event_apretoTeclaInvalidosBase

    private void apretoTeclaInvalidosEntrada(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apretoTeclaInvalidosEntrada
        if (Visual.realizarDeleteTabla(evt, BEliminarInvalidoEntrada)) {
            menosEntrada();
        }
    }//GEN-LAST:event_apretoTeclaInvalidosEntrada

    private void apretoTeclaEntrada(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apretoTeclaEntrada
        if (Visual.realizarDeleteTabla(evt, BEliminarDireccionEntrada)) {
            menosSerieEntrada();
        }
    }//GEN-LAST:event_apretoTeclaEntrada
    public void actualizarCatalogosTodoActual() {

        try {
            boolean IncluirExtrenos = (RAnime.isSelected() ? IncluirExtrenosAnime : IncluirExtrenosSerie);
//            System.out.println("IncluirExtrenos=" + IncluirExtrenos);
            getCs().getEntradaTX().actualizarCatalogosTodo(IncluirExtrenos);
            getCs().getEntradaClasicas().actualizarCatalogosTodo(IncluirExtrenos);
            getCs().getEntradaDobladas().actualizarCatalogosTodo(IncluirExtrenos);
            getCs().getEntradaFinalizadas().actualizarCatalogosTodo(IncluirExtrenos);
            for (int i = 0; i < getCs().getPersonalizadasEntrada().size(); i++) {
                getCs().getPersonalizadasEntrada().get(i).actualizarCatalogosTodo(IncluirExtrenos);
            }
            //**********************************
            getCs().getEntradaTodo().actualizarCatalogosTodo(IncluirExtrenos);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarCBTodoExtrenos() {
        if (RAnime.isSelected()) {
            CBTodoExtrenosSeccion.setSelected(IncluirExtrenosAnime);
            CBTodoExtrenosEntrada.setSelected(IncluirExtrenosAnime);
        } else {
            CBTodoExtrenosSeccion.setSelected(IncluirExtrenosSerie);
            CBTodoExtrenosEntrada.setSelected(IncluirExtrenosSerie);
        }

    }

    public void crearPredeterminadoPersonalizadoBaseDeSerNecesario() {
        String predeterminado = "_Predeterminado";
        try {
            for (int i = 0; i < 2; i++) {
                if ((i == 0 ? EA.getAnime() : EA.getSeries()).getSeccionesBase().getPersonalizadas().isEmpty() || !(i == 0 ? EA.getAnime() : EA.getSeries()).getSeccionesBase().getPersonalizadas().get(0).getNombre().equals(predeterminado)) {
                    (i == 0 ? EA.getAnime() : EA.getSeries()).getSeccionesBase().getPersonalizadas().add(0, new SeccionPersonalizada(predeterminado));
                    (i == 0 ? C.getAnime() : C.getSeries()).getBasicos().getPersonalizadosDiereccion().add(0, new CatalogoDeSeries());
                    (i == 0 ? C.getAnime() : C.getSeries()).getBasicos().getPersonalizadosString().add(0, new CatalogoDeSeries());
                }
            }

        } catch (Exception ex) {
            responerException(ex);
        }
    }

    public void actualizarMarcas() {
        actualizarMarcar(getDireccionesBaseActual(), BSeñalarTDireccionesBase);
        if (getInvalidosBaseActual() != null) {
            actualizarMarcar(getInvalidosBaseActual(), BSeñalarTInvalidosBase);
        }

        actualizarMarcar(getEntrada(), BSeñalarTDireccionesEntraron);
        if (getInvalidosEntradaActual() != null) {
            actualizarMarcar(getInvalidosEntradaActual(), BSeñalarTInvalidosEntrada);
        }
//        actualizarMarcar(getInvalidosEntradaActual(), BSeñalarTInvalidosEntrada);
        actualizarMarcar(EA.getCdc().getNombresCarpetas(), BSeñalarTablaCarpetas);
    }

    public void apretoMarca(LinkedList s, JButton b) {
        marcar(s, b);
        actualizarMarcar(s, b);
    }

    public void marcar(LinkedList s, JButton b) {
        for (int i = 0; i < s.size(); i++) {
            ((IsSeleccionado) s.get(i)).setSeleccionado(b.getText().equals(CHAR_MARCAR + ""));
        }
    }

    public void actualizarMarcar(LinkedList s, JButton b) {
        b.setText(getCharSelecciondo(s) + "");
    }

    public char getCharSelecciondo(LinkedList s) {
        for (int i = 0; i < s.size(); i++) {
            if (!((IsSeleccionado) s.get(i)).isSeleccionado()) {
                return CHAR_MARCAR;
            }
        }
        return CHAR_DESMARCAR;
    }
//    public void actualizarDirecionesEntradaTodo() {
//        getInf().actualizarDirecionesEntradaTodo();
//    }

    public void agregarEnAdministradorFile(File f, String mensaje) {
        String nuevoNombre = JOptionPane.showInputDialog(mensaje);
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                Admininistrador_De_Guardado<File> A = new Admininistrador_De_Guardado(DIRECCION_ADMINISTRADOR_DIRECCION_CARPETA);
                A.agregarInformacion(nuevoNombre, f);
                A.guardarAdminastrador(DIRECCION_ADMINISTRADOR_DIRECCION_CARPETA);
                // visualizarVentanaAdministradorTXT();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }

    public void eliminarNombreCarpetaSeleccionado() {
        EA.getCdc().getNombresCarpetas().remove(tablaCarpetaSeleccionada);
    }

    public void addNuevoNombreDeCarpeta(String a) {
        EA.getCdc().getNombresCarpetas().add(new NombreCarpeta(a));
    }

    public void setFileEnTF_TXT(File f) {
        EA.setTXT(f);
        TFDireccionTXT.setText(f.toString());
    }

    public void actualizarREstadoPersonalizado() {

//        try {
        if (CBManualBase.isEnabled() && !getInf().getSeccionesBase().getPersonalizadas().isEmpty()) {
            switch (getInf().getSeccionesBase().getPersonalizadas().get(indiceCBP()).getEstado()) {
                case CATALOGO:
                    RCatalogoAlmacenado.setSelected(true);
                    break;
                case DIRECCIONES:
                    RDireccionesManualBase.setSelected(true);
                    break;
                case ESCRITURA:
                    REscrituraManualBase.setSelected(true);
                    break;
            }
        }

//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public void cambiarEstadoPersonalizadoBase() {
        getPersonalizadosBase().get(indiceCBP()).setEstado(getEstadoPersonalizadoSeleccionado());
    }

    private estadoPersonalizado getEstadoPersonalizadoSeleccionado() {
        if (REscrituraManualBase.isSelected()) {
            return estadoPersonalizado.ESCRITURA;
        }
        if (RDireccionesManualBase.isSelected()) {
            return estadoPersonalizado.DIRECCIONES;
        }
        if (RCatalogoAlmacenado.isSelected()) {
            return estadoPersonalizado.CATALOGO;
        }
        return null;
    }

    public void clearPersonalizadoBase() {
        getPersonalizadosBase().clear();
        //   CBManualBase.removeAllItems();
    }

    public void clearPersonalizadoEntrada() {
        getInf().getSeccionesEntrada().getPersonalizadas().clear();
        //   CBManualBase.removeAllItems();
    }

    public void eliminarPersonalizadoBaseActual() {
        getPersonalizadosBase().remove(indiceCBP());
        //  CBManualBase.removeItemAt(indiceCBP());
    }

    public void eliminarPersonalizadoEntradaActual() {
        getInf().getSeccionesEntrada().getPersonalizadas().remove(indiceCBSeccion());
        //  CBManualBase.removeItemAt(indiceCBP());
    }

    public int indiceCBP() {
        return CBManualBase.getSelectedIndex();
    }

    public void actualizarCBPersonalizadosEntrada() {
        CBPersonalizadosSeccion.removeAllItems();
        LinkedList<SeccionPersonalizada> p = getInf().getSeccionesEntrada().getPersonalizadas();
        for (int i = 0; i < p.size(); i++) {
            CBPersonalizadosSeccion.addItem(p.get(i).getNombre());
        }
    }

    public void actualizarCBPersonalizadosBase() {
        CBManualBase.removeAllItems();
        LinkedList<SeccionPersonalizada> p = getPersonalizadosBase();
        for (int i = 0; i < p.size(); i++) {
            CBManualBase.addItem(p.get(i).getNombre());
        }
    }

    public void seleccionarUltimoAgregadoCBPersonalizadosBase() {
        CBManualBase.setSelectedIndex(CBManualBase.getItemCount() - 1);
    }

    public void addNuevaSeccionPersonalizadaBase(String nombre) {
//        try {
        getInf().getSeccionesBase().getPersonalizadas().add(new SeccionPersonalizada(nombre));
        getCs().getBasicos().getPersonalizadosDiereccion().add(new CatalogoDeSeries());
        getCs().getBasicos().getPersonalizadosString().add(new CatalogoDeSeries());
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    public void addNuevaSeccionPersonalizadaEntrada(String nombre) {
        getInf().getSeccionesEntrada().getPersonalizadas().add(new SeccionPersonalizada(nombre));
        getCs().addNewPersonalizadasEntrada(nombre, cdv);

    }

    public void actualizarFiltroBase() {
        FiltroSerie f = getSeccionSerieBaseActual().getFiltro();
        CBEnEsperaBase.setSelected(f.isEnEspera());
        CBExtrenosBase.setSelected(f.isExtrenos());
        CBFinalizadasBase.setSelected(f.isFinalizadas());
        CBMayoresBase.setSelected(f.isMayores());
        CBMenoresBase.setSelected(f.isMenores());
        CBPersonalizadosBase.setSelected(f.isPersonalizados());
        CBPorVerBase.setSelected(f.isPorVer());
        CBPrimerosBase.setSelected(f.isPrimeros());
        CBQueTengoBase.setSelected(f.isQueTengo());
        CBSeguidosBase.setSelected(f.isSeguidos());
        CBSoloBase.setSelected(f.isSolo());
        CBUltimosBase.setSelected(f.isUltimos());
        CBCapitulos1y0Base.setSelected(f.isCapitulos1y0());

        TFiltroMayoresBase.setText(f.getNumeroMayores() + "");
        TFiltroMenoresBase.setText(f.getNumeroMenores() + "");
        TFiltroSoloBase.setText(f.getNumeroSolo() + "");

        switch (f.getRelacion()) {
            case CONTRARIOS:
                RContrariosBase.setSelected(true);
                break;
            case FALTANTES:
                RFaltantesBase.setSelected(true);
                break;
            case RELACIONADOS:
                RRelacionadosBase.setSelected(true);
                break;
        }
//        RRelacionadosBase.setSelected(f.isRelacionados());
//        RFaltantesBase.setSelected(!f.isRelacionados());
    }

    public void actualizarFiltroEntrada() {
        FiltroSerie f = getSeccionSerieEntradaActual().getFiltro();
        CBEnEsperaEntrada.setSelected(f.isEnEspera());
        CBExtrenosEntrada.setSelected(f.isExtrenos());
        CBFinalizadasEntrada.setSelected(f.isFinalizadas());
        CBMayoresEntrada.setSelected(f.isMayores());
        CBMenoresEntrada.setSelected(f.isMenores());
        CBPersonalizadosEntrada.setSelected(f.isPersonalizados());
        CBPorVerEntrada.setSelected(f.isPorVer());
        CBPrimerosEntrada.setSelected(f.isPrimeros());
        CBQueTengoEntrada.setSelected(f.isQueTengo());
        CBSeguidosEntrada.setSelected(f.isSeguidos());
        CBSoloEntrada.setSelected(f.isSolo());
        CBUltimosEntrada.setSelected(f.isUltimos());
        CBCapitulos1y0Entrada.setSelected(f.isCapitulos1y0());

        TFiltroMayoresEntrada.setText(f.getNumeroMayores() + "");
        TFiltroMenoresEntrada.setText(f.getNumeroMenores() + "");
        TFiltroSoloEntrada.setText(f.getNumeroSolo() + "");

//        RRelacionadosEntrada.setSelected(f.isRelacionados());
//        RFaltantesEntrada.setSelected(!f.isRelacionados());
        switch (f.getRelacion()) {
            case CONTRARIOS:
                RContrariosEntrada.setSelected(true);
                break;
            case FALTANTES:
                RFaltantesEntrada.setSelected(true);
                break;
            case RELACIONADOS:
                RRelacionadosEntrada.setSelected(true);
                break;
        }

    }

//    public void seguridadApretoCBFiltro(ActionEvent evt) {
////        JCheckBox EnEspera, Extrenos, Finalizadas, Mayores,
////                Menores, Personalizados, PorVerBase, Primeros,
////                QueTengo, Seguidos, Solo, Ultimos;
//        if (Arrays.asList(CBFiltrosBase).contains(evt.getSource())) {
//            seguridadApretoCBFiltro(evt, CBEnEsperaBase, CBExtrenosBase, CBFinalizadasBase, CBMayoresBase, CBMenoresBase, CBPersonalizadosBase, CBPorVerBase, CBPrimerosBase, CBQueTengoBase, CBSeguidosBase, CBSoloBase, CBUltimosBase);
//
//////////            EnEspera = CBEnEsperaBase;
//////////            Extrenos = CBExtrenosBase;
//////////            Finalizadas = CBFinalizadasBase;
//////////            Mayores = CBMayoresBase;
//////////            Menores = CBMenoresBase;
//////////            Personalizados = CBPersonalizadosBase;
//////////            PorVerBase = CBPorVerBase;
//////////            Primeros = CBPrimerosBase;
//////////            QueTengo = CBQueTengoBase;
//////////            Seguidos = CBSeguidosBase;
//////////            Solo = CBSoloBase;
//////////            Ultimos = CBUltimosBase;
//        } else {
//            seguridadApretoCBFiltro(evt, CBEnEsperaEntrada, CBExtrenosEntrada, CBFinalizadasEntrada,
//                    CBMayoresEntrada, CBMenoresEntrada, CBPersonalizadosEntrada,
//                    CBPorVerEntrada, CBPrimerosEntrada, CBQueTengoEntrada, CBSeguidosEntrada,
//                    CBSoloEntrada, CBUltimosEntrada);
//        }
//
//    }
    private void seguridadApretoCBFiltro(ActionEvent evt, FiltroSerie f, JCheckBox EnEspera, JCheckBox Extrenos, JCheckBox Finalizadas, JCheckBox Mayores,
            JCheckBox Menores, JCheckBox Personalizados, JCheckBox PorVer, JCheckBox Primeros,
            JCheckBox QueTengo, JCheckBox Seguidos, JCheckBox Solo, JCheckBox Ultimos, JCheckBox Capitulos1y0, JRadioButton Relacionados, JRadioButton Faltantes, JRadioButton Contrarios) {
        if (evt.getSource() == Menores || evt.getSource() == Mayores) {
//            Solo.setSelected(false);
//            Extrenos.setSelected(false);
//            Primeros.setSelected(false);
//            Ultimos.setSelected(false);

            if (evt.getSource() == Menores) {
                f.setMenores(Menores.isSelected());
            } else {
                f.setMayores(Mayores.isSelected());
            }

            f.setSolo(false);
            f.setExtrenos(false);
            f.setPrimeros(false);
            f.setUltimos(false);
            f.setCapitulos1y0(false);
            return;
        }
        if (evt.getSource() == Solo) {
//            if (evt.getSource() == Solo) {
            f.setSolo(Solo.isSelected());
//            }
//            Menores.setSelected(false);
//            Mayores.setSelected(false);
//            Extrenos.setSelected(false);
//            Primeros.setSelected(false);
//            Ultimos.setSelected(false);

            f.setMenores(false);
            f.setMayores(false);
            f.setExtrenos(false);
            f.setPrimeros(false);
            f.setUltimos(false);
            f.setCapitulos1y0(false);
            return;
        }
        if (evt.getSource() == Extrenos) {
//            if (evt.getSource() == Extrenos) {
            f.setExtrenos(Extrenos.isSelected());
//            }
//            Menores.setSelected(false);
//            Mayores.setSelected(false);
//            Solo.setSelected(false);
//            Primeros.setSelected(false);
//            Ultimos.setSelected(false);

            f.setMenores(false);
            f.setMayores(false);
            f.setSolo(false);
            f.setPrimeros(false);
            f.setUltimos(false);
            f.setCapitulos1y0(false);
            return;
        }
        if (evt.getSource() == Primeros) {
//            if (evt.getSource() == Primeros) {
            f.setPrimeros(Primeros.isSelected());
//            }
//            Menores.setSelected(false);
//            Mayores.setSelected(false);
//            Solo.setSelected(false);
//            Extrenos.setSelected(false);
//            Ultimos.setSelected(false);

            f.setMenores(false);
            f.setMayores(false);
            f.setSolo(false);
            f.setExtrenos(false);
            f.setUltimos(false);
            f.setCapitulos1y0(false);
            return;
        }
        if (evt.getSource() == Ultimos) {
//            Menores.setSelected(false);
//            Mayores.setSelected(false);
//            Solo.setSelected(false);
//            Extrenos.setSelected(false);
//            Primeros.setSelected(false);
//            if (evt.getSource() == Ultimos) {
            f.setUltimos(Ultimos.isSelected());
//            }
            f.setMenores(false);
            f.setMayores(false);
            f.setSolo(false);
            f.setExtrenos(false);
            f.setPrimeros(false);
            f.setCapitulos1y0(false);
            return;
        }
        if (evt.getSource() == Capitulos1y0) {
//              System.out.println("Capitulos1y0.isSelected()="+Capitulos1y0.isSelected());
            //   if (evt.getSource() == Ultimos) {
            f.setCapitulos1y0(Capitulos1y0.isSelected());
            //   }

            f.setMenores(false);
            f.setMayores(false);
            f.setSolo(false);
            f.setExtrenos(false);
            f.setPrimeros(false);
            f.setUltimos(false);
            //   f.setCapitulos1y0(false);
            return;
        }

        if (evt.getSource() == EnEspera) {
            f.setEnEspera(EnEspera.isSelected());
            return;
        }
        if (evt.getSource() == Extrenos) {
            f.setExtrenos(Extrenos.isSelected());
            return;
        }
        if (evt.getSource() == Finalizadas) {
            f.setFinalizadas(Finalizadas.isSelected());
            return;
        }
        if (evt.getSource() == Personalizados) {
            f.setPersonalizados(Personalizados.isSelected());
            return;
        }

        if (evt.getSource() == PorVer) {
            f.setPorVer(PorVer.isSelected());
            return;
        }
        if (evt.getSource() == QueTengo) {
            f.setQueTengo(QueTengo.isSelected());
            return;
        }
        if (evt.getSource() == Seguidos) {
            f.setSeguidos(Seguidos.isSelected());
            return;
        }
        if (evt.getSource() == Relacionados || evt.getSource() == Faltantes || evt.getSource() == Contrarios) {
            if (Contrarios.isSelected()) {
                f.setRelacion(FiltroSerie.Relacion.CONTRARIOS);
            }
            if (Relacionados.isSelected()) {
                f.setRelacion(FiltroSerie.Relacion.RELACIONADOS);
            }
            if (Faltantes.isSelected()) {
                f.setRelacion(FiltroSerie.Relacion.FALTANTES);
            }
//            f.setRelacionados(Relacionados.isSelected());
            return;
        }
    }

    public void alCerrar(JFrame f) {
        cerrar(f, 6);

    }

    public SeccionDeCatalogosDeSerieEntrada getSeccionDeCatalogosDeSerieEntradaActual() {
        try {
            return (SeccionDeCatalogosDeSerieEntrada) getDeBaseActual(get.SECCION_CATALGO_ENTRADA);
        } catch (IOException ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ventana_Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    ;
    public void actualizarEntradaActual() throws FileNotFoundException {
        getSeccionDeCatalogosDeSerieEntradaActual().actualizarCatalogos(CBTodoExtrenosSeccion.isSelected(), cdv);
//        getCs().getEntrada().actualizar(cdv);
//        getCs().getFaltanPorCopiar().actualizar(cdv);
//        getCs().getFaltantes().actualizar(cdv);
    }

    public void actualizarEntrada() throws FileNotFoundException {
        getCs().getEntradaTX().actualizarCatalogos(CBTodoExtrenosSeccion.isSelected(), cdv);
        getCs().getEntradaDobladas().actualizarCatalogos(CBTodoExtrenosSeccion.isSelected(), cdv);
        getCs().getEntradaClasicas().actualizarCatalogos(CBTodoExtrenosSeccion.isSelected(), cdv);
        getCs().getEntradaFinalizadas().actualizarCatalogos(CBTodoExtrenosSeccion.isSelected(), cdv);
        getCs().getEntradaTodo().actualizarCatalogos(CBTodoExtrenosSeccion.isSelected(), cdv);
        getCs().actualizarPersonalizados(CBTodoExtrenosSeccion.isSelected(), cdv);
        getInf().getSeccionesEntrada().actualizarDirecionesEntradaTodo();

    }

    public void actualizarTodasLasEntradas() throws FileNotFoundException {
        for (int i = 0; i < 2; i++) {
            (i == 0 ? C.getAnime() : C.getSeries()).getEntradaTX().actualizarCatalogos((i == 0 ? IncluirExtrenosAnime : IncluirExtrenosSerie), cdv);
            (i == 0 ? C.getAnime() : C.getSeries()).getEntradaDobladas().actualizarCatalogos((i == 0 ? IncluirExtrenosAnime : IncluirExtrenosSerie), cdv);
            (i == 0 ? C.getAnime() : C.getSeries()).getEntradaClasicas().actualizarCatalogos((i == 0 ? IncluirExtrenosAnime : IncluirExtrenosSerie), cdv);
            (i == 0 ? C.getAnime() : C.getSeries()).getEntradaFinalizadas().actualizarCatalogos((i == 0 ? IncluirExtrenosAnime : IncluirExtrenosSerie), cdv);
            (i == 0 ? C.getAnime() : C.getSeries()).getEntradaTodo().actualizarCatalogos((i == 0 ? IncluirExtrenosAnime : IncluirExtrenosSerie), cdv);
            (i == 0 ? C.getAnime() : C.getSeries()).actualizarPersonalizados((i == 0 ? IncluirExtrenosAnime : IncluirExtrenosSerie), cdv);
            (i == 0 ? EA.getAnime() : EA.getSeries()).getSeccionesEntrada().actualizarDirecionesEntradaTodo();
        }

//        getCs().getEntradaTX().actualizarCatalogos(cdv);
//        getCs().getEntradaDobladas().actualizarCatalogos(cdv);
//        getCs().getEntradaClasicas().actualizarCatalogos(cdv);
//        getCs().getEntradaTodo().actualizarCatalogos(cdv);
//        getCs().actualizarPersonalizados(cdv);
//        getInf().getSeccionesEntrada().actualizarDirecionesEntradaTodo();
    }
//public void actualizarEntradas() throws FileNotFoundException {
//        C.actualizarCatalogos(cdv);
//                
//
//    }

    public void visualizarVentanaAdministradorSecciones_Entrada() {
        try {
            if (ventana_Administrador_Secciones_Entrada == null) {
                AccionAdministrador a = new AccionAdministrador();
                ventana_Administrador_Secciones_Entrada = new Ventana_Administrador(DIRECCION_ADMINISTRADOR_SECCIONES_ENTRADA, a);
                a.setF(ventana_Administrador_Secciones_Entrada);

            } else {
                ventana_Administrador_Secciones_Entrada.setDireccionAdministrador(DIRECCION_ADMINISTRADOR_SECCIONES_ENTRADA);
            }
            ventana_Administrador_Secciones_Entrada.setVisible(true);
            setVisible(false);
        } catch (Exception ex) {
            responerException(ex);
        }
    }

    public void visualizarVentanaAdministradorSecciones_Base() {
        try {
            if (ventana_Administrador_Secciones_Base == null) {
                AccionAdministrador a = new AccionAdministrador();
                ventana_Administrador_Secciones_Base = new Ventana_Administrador(DIRECCION_ADMINISTRADOR_SECCIONES_BASE, a);
                a.setF(ventana_Administrador_Secciones_Base);
            } else {
                ventana_Administrador_Secciones_Base.setDireccionAdministrador(DIRECCION_ADMINISTRADOR_SECCIONES_BASE);
            }
            ventana_Administrador_Secciones_Base.setVisible(true);
            setVisible(false);
        } catch (Exception ex) {
            responerException(ex);
        }
    }

    public void visualizarVentanaAdministradorNombre_Carpeta() {
        try {
            if (ventana_Administrador_Nombre_Carpeta == null) {
                AccionAdministrador a = new AccionAdministrador();
                ventana_Administrador_Nombre_Carpeta = new Ventana_Administrador(DIRECCION_ADMINISTRADOR_NOMBRES_CARPETA, a);
                a.setF(ventana_Administrador_Nombre_Carpeta);
            } else {
                ventana_Administrador_Nombre_Carpeta.setDireccionAdministrador(DIRECCION_ADMINISTRADOR_NOMBRES_CARPETA);
            }
            ventana_Administrador_Nombre_Carpeta.setVisible(true);
            setVisible(false);
        } catch (Exception ex) {
            responerException(ex);
        }
    }

    public void visualizarVentanaAdministradorDirecciones_Carpetas() {
        try {
            if (ventana_Administrador_Direcciones_Carpetas == null) {
                AccionAdministrador a = new AccionAdministrador();
                ventana_Administrador_Direcciones_Carpetas = new Ventana_Administrador(DIRECCION_ADMINISTRADOR_DIRECCION_CARPETA, a);
                a.setF(ventana_Administrador_Direcciones_Carpetas);
            } else {
                ventana_Administrador_Direcciones_Carpetas.setDireccionAdministrador(DIRECCION_ADMINISTRADOR_DIRECCION_CARPETA);
            }
            ventana_Administrador_Direcciones_Carpetas.setVisible(true);
            setVisible(false);
        } catch (Exception ex) {
            responerException(ex);
        }
    }

    public void visualizarVentanaAdministradorTXT() {
        try {
            if (ventana_Administrador_Direcciones_TXT == null) {
                AccionAdministrador a = new AccionAdministrador();
                ventana_Administrador_Direcciones_TXT = new Ventana_Administrador(get_DIRECCION_ADMINISTRADOR_TXT_ACTUAL(), a);
                a.setF(ventana_Administrador_Direcciones_TXT);
            } else {
                ventana_Administrador_Direcciones_TXT.setDireccionAdministrador(get_DIRECCION_ADMINISTRADOR_TXT_ACTUAL());
            }
            ventana_Administrador_Direcciones_TXT.setVisible(true);
            setVisible(false);
        } catch (Exception ex) {
            responerException(ex);
        }
    }

    public void visualizarVentanaAdministradorPersonalizadoBase() {
        try {
            if (ventana_Administrador_Personalizado_Base == null) {
                AccionAdministrador a = new AccionAdministrador();
                ventana_Administrador_Personalizado_Base = new Ventana_Administrador(get_DIRECCION_ADMINISTRADOR_PERSONALIZADO_BASE_ACTUAL(), a);
                a.setF(ventana_Administrador_Personalizado_Base);
            } else {
                ventana_Administrador_Personalizado_Base.setDireccionAdministrador(get_DIRECCION_ADMINISTRADOR_PERSONALIZADO_BASE_ACTUAL());
            }
            ventana_Administrador_Personalizado_Base.setVisible(true);
            setVisible(false);
        } catch (Exception ex) {
            responerException(ex);
        }
    }

    public void visualizarVentanaAdministradorPersonalizadoEntrada() {
        try {
            if (ventana_Administrador_Personalizado_Entrada == null) {
                AccionAdministrador a = new AccionAdministrador();
                ventana_Administrador_Personalizado_Entrada = new Ventana_Administrador(get_DIRECCION_ADMINISTRADOR_PERSONALIZADO_ENTRADA_ACTUAL(), a);
                a.setF(ventana_Administrador_Personalizado_Entrada);
            } else {
                ventana_Administrador_Personalizado_Entrada.setDireccionAdministrador(get_DIRECCION_ADMINISTRADOR_PERSONALIZADO_ENTRADA_ACTUAL());
            }
            ventana_Administrador_Personalizado_Entrada.setVisible(true);
            setVisible(false);
        } catch (Exception ex) {
            responerException(ex);
        }
    }

    public void visualizarVentanaConfiguracionDeVideo() {
        try {
            if (ventana_ConfiguracionDeVideo == null) {
                ventana_ConfiguracionDeVideo = new Ventana_ConfiguracionDeVideo(EA.getT(), DIRECCION_ADMINISTRADOR_CONFIGURACION_DE_VIDEO, new Accion_Cargar_Cancelar() {

                    @Override
                    public void aceptar_Y_cargar() {

                        EA.setT(ventana_ConfiguracionDeVideo.getTOriginal());
                        cdv = EA.getT().getCdvSelecionado();
                        try {

                            actualizarTodasLasBases();
                            actualizarTodasLasEntradas();
                            actualizarTBase();
                            actualizarTEntrada();
                        } catch (Exception ex) {
                            responerException(ex);
                        }
                        ventana_ConfiguracionDeVideo.setVisible(false);
                        setVisible(true);
                    }

                    @Override
                    public void cancelar() {
                        ventana_ConfiguracionDeVideo.setVisible(false);
                        setVisible(true);
                    }
                });
            } else {
                ventana_ConfiguracionDeVideo.setDireccionAdministrador(DIRECCION_ADMINISTRADOR_CONFIGURACION_DE_VIDEO);
            }

            ventana_ConfiguracionDeVideo.setVisible(true);
            setVisible(false);
        } catch (Exception ex) {
            responerException(ex);
        }
    }

    public void visualizarVentanaAdministradorDirecciones() {
        try {
            if (ventana_Administrador_Direcciones == null) {
                AccionAdministrador a = new AccionAdministrador();
                ventana_Administrador_Direcciones = new Ventana_Administrador(get_DIRECCION_ADMINISTRADOR_DIRECCIONES_ACTUAL(), a);
                a.setF(ventana_Administrador_Invalidos);
//                ventana_Administrador_Direcciones.getAc().;
            } else {
                ventana_Administrador_Direcciones.setDireccionAdministrador(get_DIRECCION_ADMINISTRADOR_DIRECCIONES_ACTUAL());
            }

            ventana_Administrador_Direcciones.setVisible(true);
            setVisible(false);
        } catch (Exception ex) {
            responerException(ex);
        }
    }

    public void visualizarVentanaAdministradorInvalidos() {
        try {
            if (ventana_Administrador_Invalidos == null) {
                AccionAdministrador a = new AccionAdministrador();
                ventana_Administrador_Invalidos = new Ventana_Administrador(get_DIRECCION_ADMINISTRADOR_INVALIDOS_ACTUAL(), a);
                a.setF(ventana_Administrador_Invalidos);
            } else {
                ventana_Administrador_Invalidos.setDireccionAdministrador(get_DIRECCION_ADMINISTRADOR_INVALIDOS_ACTUAL());
            }

            ventana_Administrador_Invalidos.setVisible(true);
            setVisible(false);
        } catch (Exception ex) {
            responerException(ex);
        }
    }

    public void masBase() {

        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de las direcciones");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                addInvalidoBase(nuevoNombre);
                actualizarBaseActual();
                actualizarEntrada();
                actualizarTBase();
//                actualizarTodoEntradaSiEsNecesario();
                actualizarTEntrada();
                //  actualizar();
                seguridad();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }

    public void masEntrada() {
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de las direcciones");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                addInvalidoEntrada(nuevoNombre);

                actualizarEntradaActual();
                getInf().getSeccionesEntrada().actualizarDirecionesEntradaTodo();
//                 actualizarTBase();
                actualizarTodoEntradaSiEsNecesario();
                actualizarTEntrada();
//                actualizar();
                seguridad();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }

    private void menosSerieBase() {
        try {
            eliminarDireccionBase();
            actualizarBaseActual();
            actualizarEntrada();
            actualizarTBase();

//            actualizarTodoEntradaSiEsNecesario();
            actualizarTEntrada();
            // actualizar();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }

    private void menosSerieEntrada() {
        try {
            eliminarDireccionEntrada();

            actualizarEntradaActual();
            getInf().getSeccionesEntrada().actualizarDirecionesEntradaTodo();
            // actualizarTBase();
            actualizarTodoEntradaSiEsNecesario();
            actualizarTEntrada();
//            actualizar();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }

    public void menosBase() {
        try {
            eliminarInvalidoBase();
            actualizarBaseActual();
            actualizarEntrada();
            actualizarTBase();
//            actualizarTodoEntradaSiEsNecesario();
            actualizarTEntrada();
//            actualizar();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }

    }

    public void menosEntrada() {
        try {
            eliminarInvalidoEntrada();

            actualizarEntradaActual();
//             actualizarTBase();
            actualizarTodoEntradaSiEsNecesario();
            actualizarTEntrada();
//            actualizar();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }

    }

    public void leerTBaseDeSerNecesario() {
        try {
            if (TBase.isEditable()) {
                getInf().getSeccionesBase().getPersonalizadas().get(CBManualBase.getSelectedIndex()).setLineas(Visual.leerJTextArea(TBase));
            }
        } catch (Exception ex) {
            responerException(ex);
        }
    }

    public void actualizarB() {
        try {
            leerTBaseDeSerNecesario();

            actualizarDireccionBase();
            actualizarTablaSeleccionadosPersonalizados();
            actualizarBaseActual();
//            if(!getCs().getBasicos().getPersonalizadosString().isEmpty()){
//                System.out.println(getInf().getPersonalizadas().get(0).getEstado());
//             getCs().getBasicos().getPersonalizadosString().get(0).imprimir();
//            }else{
//                System.out.println("vacio");
//            }

            actualizarEntradaActual();
//            if (!getCs().getEntrada().getPersonalizadosSeleccionados().isEmpty()) {
//                // System.out.println(getInf().getPersonalizadas().get(0).getEstado());
//                // getCs().getBasicos().getPersonalizadosString().get(0).imprimir();
//                getCs().getEntrada().getPersonalizadosSeleccionados().imprimir();
//            } else {
//                System.out.println("vacio");
//            }
            actualizarTodoEntradaSiEsNecesario();
            actualizarTBase();
            actualizarTEntrada();
            // actualizar();
            seguridad();

//             actualizarCBPersonalizadosBase();
//            actualizarREstadoPersonalizado();
//            actualizarTablas();
//            actualizarTBase();
//            actualizarTEntrada();
//            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana_Principal2.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana_Principal2.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana_Principal2.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana_Principal2.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_Principal2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BActualizarBase;
    private javax.swing.JButton BActualizarEntraron;
    private javax.swing.JButton BAdministradorContenidoBase;
    private javax.swing.JButton BAdministradorContenidoEntrada;
    private javax.swing.JButton BAdministradorDireccionCarpetas;
    private javax.swing.JButton BAdministradorDireccionContenido;
    private javax.swing.JButton BAdministradorDireccionPaquete;
    private javax.swing.JButton BAdministradorDireccionTXT;
    private javax.swing.JButton BAdministradorPersonalizadosSeccion;
    private javax.swing.JButton BAdministradorTablaCarpetas;
    private javax.swing.JButton BAgregarDireccionCarpetas;
    private javax.swing.JButton BAgregarDireccionContenido;
    private javax.swing.JButton BAgregarDireccionPaquete;
    private javax.swing.JButton BAgregarDireccionTXT;
    private javax.swing.JButton BAgregarEntrada;
    private javax.swing.JButton BAgregarInvalidoBase;
    private javax.swing.JButton BAgregarInvalidoEntrada;
    private javax.swing.JButton BAgregarManualBase;
    private javax.swing.JButton BAgregarPersonalizadosSeccion;
    private javax.swing.JButton BAgregarTablaCarpetas;
    private javax.swing.JButton BAlmacenPredeterminado;
    private javax.swing.JButton BAlmacenSeleccionado;
    private javax.swing.JButton BAtras;
    private javax.swing.JButton BBuscarDireccionBase;
    private javax.swing.JButton BBuscarDireccionEntrada;
    private javax.swing.JButton BCargarPaquete;
    private javax.swing.JButton BCatalogoBase;
    private javax.swing.JButton BCatalogoEntrada;
    private javax.swing.JButton BClearAll;
    private javax.swing.JButton BClearBase;
    private javax.swing.JButton BClearDireccionBase;
    private javax.swing.JButton BClearDireccionCarpetas;
    private javax.swing.JButton BClearDireccionContenido;
    private javax.swing.JButton BClearDireccionEntrada;
    private javax.swing.JButton BClearDireccionPaquete;
    private javax.swing.JButton BClearDireccionTXT;
    private javax.swing.JButton BClearEntrada;
    private javax.swing.JButton BClearInvalidosBase;
    private javax.swing.JButton BClearInvalidosEntrada;
    private javax.swing.JButton BClearManualBase;
    private javax.swing.JButton BClearPersonalizadosSeccion;
    private javax.swing.JButton BClearTablaCarpetas;
    private javax.swing.JButton BConfiguracion;
    private javax.swing.JButton BCopiarContenido;
    private javax.swing.JButton BCrearCarpetas;
    private javax.swing.JButton BDireccionesBaseAdministrador;
    private javax.swing.JButton BDireccionesEntradaAdministrador;
    private javax.swing.JButton BDireccionesManualBase;
    private javax.swing.JButton BEliminarDireccionBase;
    private javax.swing.JButton BEliminarDireccionEntrada;
    private javax.swing.JButton BEliminarInvalidoBase;
    private javax.swing.JButton BEliminarInvalidoEntrada;
    private javax.swing.JButton BEliminarManualBase;
    private javax.swing.JButton BEliminarPersonalizadosSeccion;
    private javax.swing.JButton BEliminarTablaCarpetas;
    private javax.swing.ButtonGroup BGBase;
    private javax.swing.ButtonGroup BGClasificacion;
    private javax.swing.ButtonGroup BGCuantificacion;
    private javax.swing.ButtonGroup BGEntrada;
    private javax.swing.ButtonGroup BGFiltroBase;
    private javax.swing.ButtonGroup BGFiltroEntrada;
    private javax.swing.ButtonGroup BGFormaStringBase;
    private javax.swing.ButtonGroup BGFormaStringEntrada;
    private javax.swing.ButtonGroup BGPersonalizados;
    private javax.swing.JButton BGuardarAllManualBase;
    private javax.swing.JButton BGuardarAllPersonalizadosSeccion;
    private javax.swing.JButton BGuardarContenidoBase;
    private javax.swing.JButton BGuardarContenidoEntrada;
    private javax.swing.JButton BGuardarDireccionBase;
    private javax.swing.JButton BGuardarDireccionCarpetas;
    private javax.swing.JButton BGuardarDireccionContenido;
    private javax.swing.JButton BGuardarDireccionEntrada;
    private javax.swing.JButton BGuardarDireccionPaquete;
    private javax.swing.JButton BGuardarDireccionTXT;
    private javax.swing.JButton BGuardarInvalidosBase;
    private javax.swing.JButton BGuardarInvalidosEntrada;
    private javax.swing.JButton BGuardarManualBase;
    private javax.swing.JButton BGuardarPersonalizadosSeccion;
    private javax.swing.JButton BGuardarTablaCarpetas;
    private javax.swing.JButton BInvalidoBaseAdministrador;
    private javax.swing.JButton BInvalidoEntradaAdministrador;
    private javax.swing.ButtonGroup BSSeccionEntrada;
    private javax.swing.JButton BSeñalarTDireccionesBase;
    private javax.swing.JButton BSeñalarTDireccionesEntraron;
    private javax.swing.JButton BSeñalarTInvalidosBase;
    private javax.swing.JButton BSeñalarTInvalidosEntrada;
    private javax.swing.JButton BSeñalarTablaCarpetas;
    private javax.swing.JButton BTXTBase;
    private javax.swing.JButton BTXTContenido;
    private javax.swing.JButton BTXTEntrada;
    private javax.swing.JCheckBox CBCapitulos1y0Base;
    private javax.swing.JCheckBox CBCapitulos1y0Entrada;
    private javax.swing.JCheckBox CBEnEsperaBase;
    private javax.swing.JCheckBox CBEnEsperaEntrada;
    private javax.swing.JCheckBox CBExtrenosBase;
    private javax.swing.JCheckBox CBExtrenosEntrada;
    private javax.swing.JCheckBox CBFinalizadasBase;
    private javax.swing.JCheckBox CBFinalizadasEntrada;
    private javax.swing.JComboBox CBManualBase;
    private javax.swing.JCheckBox CBMayoresBase;
    private javax.swing.JCheckBox CBMayoresEntrada;
    private javax.swing.JCheckBox CBMenoresBase;
    private javax.swing.JCheckBox CBMenoresEntrada;
    private javax.swing.JCheckBox CBPersonalizadosBase;
    private javax.swing.JCheckBox CBPersonalizadosEntrada;
    private javax.swing.JComboBox CBPersonalizadosSeccion;
    private javax.swing.JCheckBox CBPorVerBase;
    private javax.swing.JCheckBox CBPorVerEntrada;
    private javax.swing.JCheckBox CBPrimerosBase;
    private javax.swing.JCheckBox CBPrimerosEntrada;
    private javax.swing.JCheckBox CBQueTengoBase;
    private javax.swing.JCheckBox CBQueTengoEntrada;
    private javax.swing.JCheckBox CBSeguidosBase;
    private javax.swing.JCheckBox CBSeguidosEntrada;
    private javax.swing.JCheckBox CBSinCarpetasBase;
    private javax.swing.JCheckBox CBSinCarpetasEntrada;
    private javax.swing.JCheckBox CBSoloBase;
    private javax.swing.JCheckBox CBSoloEntrada;
    private javax.swing.JCheckBox CBTodoExtrenosEntrada;
    private javax.swing.JCheckBox CBTodoExtrenosSeccion;
    private javax.swing.JCheckBox CBUltimosBase;
    private javax.swing.JCheckBox CBUltimosEntrada;
    private javax.swing.JTable JTDireccionesBase;
    private javax.swing.JTable JTDireccionesEntraron;
    private javax.swing.JTable JTInvalidosBase;
    private javax.swing.JTable JTInvalidosEntraron;
    private javax.swing.JTable JTSeleccionadosManualBase;
    private javax.swing.JTable JTTablaCarpetas;
    private javax.swing.JLabel LDireccionDeCarpetas1;
    private javax.swing.JLabel LDireccionDeLaCarpeta;
    private javax.swing.JLabel LDireccionDelPaquete1;
    private javax.swing.JPanel PDireccionesBase;
    private javax.swing.JPanel PDireccionesEntraron;
    private javax.swing.JPanel PFiltrosBase;
    private javax.swing.JPanel PFiltrosEntrada;
    private javax.swing.JPanel PGeneral;
    private javax.swing.JPanel PGeneralEntrada;
    private javax.swing.JPanel PInvalidosBase;
    private javax.swing.JPanel PInvalidosEntraron;
    private javax.swing.JPanel PPaquete;
    private javax.swing.JPanel PPersonalizado;
    private javax.swing.JPanel PPersonalizadoEntrada;
    private javax.swing.JPanel PSeccionEntrada;
    private javax.swing.JPanel PSeleccionadosManualBase;
    private javax.swing.JPanel PSeries;
    private javax.swing.JPanel PTablaCarpetas;
    private javax.swing.JPanel PTodo;
    private javax.swing.JRadioButton RAnime;
    private javax.swing.JRadioButton RCapitulosBase;
    private javax.swing.JRadioButton RCapitulosEntrada;
    private javax.swing.JRadioButton RCatalogoAlmacenado;
    private javax.swing.JRadioButton RClasicasSeccion;
    private javax.swing.JRadioButton RContrarios;
    private javax.swing.JRadioButton RContrariosBase;
    private javax.swing.JRadioButton RContrariosEntrada;
    private javax.swing.JRadioButton RDireccionesManualBase;
    private javax.swing.JRadioButton RDobladasSeccion;
    private javax.swing.JRadioButton REnEspera;
    private javax.swing.JRadioButton REnEsperaEntrada;
    private javax.swing.JRadioButton REntraron;
    private javax.swing.JRadioButton REscrituraManualBase;
    private javax.swing.JRadioButton RExtrenos;
    private javax.swing.JRadioButton RFaltanPorCopiar;
    private javax.swing.JRadioButton RFaltantesBase;
    private javax.swing.JRadioButton RFaltantesEntrada;
    private javax.swing.JRadioButton RFaltaron;
    private javax.swing.JRadioButton RFinalizadas;
    private javax.swing.JRadioButton RFinalizadasEntrada;
    private javax.swing.JRadioButton RFinalizadasSeccion;
    private javax.swing.JRadioButton RNombresDeSeriesBase;
    private javax.swing.JRadioButton RNombresDeSeriesEntrada;
    private javax.swing.JRadioButton RPersonalizados;
    private javax.swing.JRadioButton RPersonalizadosSeccion;
    private javax.swing.JRadioButton RPorVer;
    private javax.swing.JRadioButton RPorVerEntrada;
    private javax.swing.JRadioButton RQueTengo;
    private javax.swing.JRadioButton RRelacionadosBase;
    private javax.swing.JRadioButton RRelacionadosEntrada;
    private javax.swing.JRadioButton RSeguidos;
    private javax.swing.JRadioButton RSeguidosEntrada;
    private javax.swing.JRadioButton RSeries;
    private javax.swing.JRadioButton RSoloCapitulosBase;
    private javax.swing.JRadioButton RSoloCapitulosEntrada;
    private javax.swing.JRadioButton RTXSeccion;
    private javax.swing.JRadioButton RTodo;
    private javax.swing.JRadioButton RTodoSeccion;
    private javax.swing.JScrollPane SPDireccionesBase;
    private javax.swing.JScrollPane SPDireccionesEntraron;
    private javax.swing.JScrollPane SPInvalidosBase;
    private javax.swing.JScrollPane SPInvalidosEntraron;
    private javax.swing.JScrollPane SPSeleccionadosManualBase;
    private javax.swing.JScrollPane SPTBase;
    private javax.swing.JScrollPane SPTEntraron;
    private javax.swing.JScrollPane SPTablaCarpetas;
    private javax.swing.JSpinner SProfundidad;
    private javax.swing.JTextArea TBase;
    private javax.swing.JTextArea TEntraron;
    private javax.swing.JTextField TFDireccionCarpetas;
    private javax.swing.JTextField TFDireccionContenido;
    private javax.swing.JTextField TFDireccionPaquete;
    private javax.swing.JTextField TFDireccionTXT;
    private javax.swing.JTextField TFiltroMayoresBase;
    private javax.swing.JTextField TFiltroMayoresEntrada;
    private javax.swing.JTextField TFiltroMenoresBase;
    private javax.swing.JTextField TFiltroMenoresEntrada;
    private javax.swing.JTextField TFiltroSoloBase;
    private javax.swing.JTextField TFiltroSoloEntrada;
    private javax.swing.JTabbedPane TPBase;
    private javax.swing.JTabbedPane TPEntrada;
    private javax.swing.JTabbedPane TPTodo;
    // End of variables declaration//GEN-END:variables
}
