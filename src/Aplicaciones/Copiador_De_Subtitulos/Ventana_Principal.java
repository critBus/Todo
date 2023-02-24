/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.Copiador_De_Subtitulos;

import Utiles.ClasesUtiles.Admininistrador.Admininistrador_De_Guardado;
import Utiles.ClasesUtiles.Admininistrador.Ventana_Administrador;
import static Utiles.ClasesUtiles.Admininistrador.Ventana_Administrador.EXTENCION;
import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeTabla;
import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Configuraciones.Ventanas.TablasAmpliableConfiguracionDeVideo;
import Utiles.ClasesUtiles.Configuraciones.Ventanas.Ventana_ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Interfases.Accion_Cargar_Cancelar;
import Utiles.ClasesUtiles.Interfases.IsSeleccionado;
import Utiles.ClasesUtiles.Interfases.editingTabla;
import Utiles.ClasesUtiles.Interfases.selectionFila;
import Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaSeries.CarpetaSeriesTX;
import Utiles.ClasesUtiles.Multimedia.Paquete.Paquete;
import Utiles.ClasesUtiles.Multimedia.Series.CatalogoDeSeries;
import Utiles.MetodosUtiles.Archivo;
import static Utiles.MetodosUtiles.Archivo.crearArchivo;
import Utiles.MetodosUtiles.MetodosUtiles;
import Utiles.MetodosUtiles.Visual;
import static Utiles.MetodosUtiles.Visual.responerException;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import static todo.Todo.cerrar;

/**
 *
 * @author Rene
 */
public class Ventana_Principal extends javax.swing.JFrame {

    private final String CARPETA = "Data/Copiador de Subtitulos/", DIRECCION_ULTIMO_ESTADO = CARPETA + "Estado Actual Copiador de Subtitulos.est",
            DIRECCION_ADMINISTRADOR_DIRECCIONES_SERIES = CARPETA + "Administrador de direcciones de series" + EXTENCION,
            DIRECCION_ADMINISTRADOR_INVALIDOS_SERIES = CARPETA + "Administrador de invalidos de series" + EXTENCION,
            DIRECCION_ADMINISTRADOR_DIRECCIONES_SUBTITULOS = CARPETA + "Administrador de direcciones de Subtitulos" + EXTENCION,
            DIRECCION_ADMINISTRADOR_DIRECCIONES_PAQUETE = CARPETA + "Administrador de direcciones paquete" + EXTENCION,
            DIRECCION_ADMINISTRADOR_SECCION = CARPETA + "Administrador de direcciones seccion" + EXTENCION,
            DIRECCION_ADMINISTRADOR_CONFIGURACION_DE_VIDEO = CARPETA + "Administrador de configuracion de video" + EXTENCION;
    private int baseDireccionSelecionada, baseInvalidoSeleccionada, baseSeleccionada = 0, SubtituloSeleccionado = 0;
    public static char CHAR_MARCAR = '◙', CHAR_DESMARCAR = '○';
    private final int ANCHO_SELECT = 40;
    private Estado_Actual EA;
    private JFileChooser jf;
    private CatalogosActuales C;
    private ConfiguracionDeVideo cdv;
    public static Ventana_Administrador<LinkedList<Direccion>> ventana_Administrador_Direcciones_Series;
    public static Ventana_Administrador<LinkedList<Direccion>> ventana_Administrador_Direcciones_Subtitulos;
    public static Ventana_Administrador<LinkedList<DirectoriosInvalidos>> ventana_Administrador_Invalido_Series;
    public static Ventana_Administrador<File> ventana_Administrador_Paquete;
    public static Ventana_Administrador<Seccion> ventana_Administrador_Seccion;
    public static Ventana_ConfiguracionDeVideo ventana_ConfiguracionDeVideo;
    public static Ventana_Principal ventana_Principal;
    public static JFrame ventanas[];

    /**
     * Creates new form Ventana_Principal
     */
    public Ventana_Principal() {
        initComponents();
        File carpeta = new File(CARPETA);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        try {
            if (!new File(DIRECCION_ULTIMO_ESTADO).exists()) {
                crearArchivo(DIRECCION_ULTIMO_ESTADO, new Estado_Actual());
            }

            try {
                EA = (Estado_Actual) Archivo.cargarArchivo(DIRECCION_ULTIMO_ESTADO);
                cdv = EA.getT().getCdvSelecionado();
                C = new CatalogosActuales(EA, cdv);
            } catch (Exception ex) {
                responerException(ex);
                crearArchivo(DIRECCION_ULTIMO_ESTADO, new Estado_Actual());
                crearArchivo(DIRECCION_ADMINISTRADOR_CONFIGURACION_DE_VIDEO, new Admininistrador_De_Guardado<TablasAmpliableConfiguracionDeVideo>());
                EA = (Estado_Actual) Archivo.cargarArchivo(DIRECCION_ULTIMO_ESTADO);
                cdv = EA.getT().getCdvSelecionado();
                C = new CatalogosActuales(EA, cdv);
                crearArchivo(DIRECCION_ADMINISTRADOR_DIRECCIONES_SERIES, new Admininistrador_De_Guardado<LinkedList<Direccion>>());
                crearArchivo(DIRECCION_ADMINISTRADOR_DIRECCIONES_SUBTITULOS, new Admininistrador_De_Guardado<LinkedList<Direccion>>());
                crearArchivo(DIRECCION_ADMINISTRADOR_INVALIDOS_SERIES, new Admininistrador_De_Guardado<LinkedList<DirectoriosInvalidos>>());
                crearArchivo(DIRECCION_ADMINISTRADOR_SECCION, new Admininistrador_De_Guardado<LinkedList<Seccion>>());
                crearArchivo(DIRECCION_ADMINISTRADOR_DIRECCIONES_PAQUETE, new Admininistrador_De_Guardado<File>());
            }
            if (!new File(DIRECCION_ADMINISTRADOR_DIRECCIONES_SERIES).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_DIRECCIONES_SERIES, new Admininistrador_De_Guardado<LinkedList<Direccion>>());
            }
            if (!new File(DIRECCION_ADMINISTRADOR_DIRECCIONES_SUBTITULOS).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_DIRECCIONES_SUBTITULOS, new Admininistrador_De_Guardado<LinkedList<Direccion>>());
            }
            if (!new File(DIRECCION_ADMINISTRADOR_INVALIDOS_SERIES).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_INVALIDOS_SERIES, new Admininistrador_De_Guardado<LinkedList<DirectoriosInvalidos>>());
            }
            if (!new File(DIRECCION_ADMINISTRADOR_SECCION).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_SECCION, new Admininistrador_De_Guardado<LinkedList<Seccion>>());
            }
            if (!new File(DIRECCION_ADMINISTRADOR_DIRECCIONES_PAQUETE).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_DIRECCIONES_PAQUETE, new Admininistrador_De_Guardado<File>());
            }
            if (!new File(DIRECCION_ADMINISTRADOR_CONFIGURACION_DE_VIDEO).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_CONFIGURACION_DE_VIDEO, new Admininistrador_De_Guardado<TablasAmpliableConfiguracionDeVideo>());
            }
            jf = new JFileChooser();
            jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            baseDireccionSelecionada = baseInvalidoSeleccionada = SubtituloSeleccionado = 0;

            TFDireccionPaquete.setText(EA.getPaquete().toString());

            actualizarDireccionBase();
            actualizarTablaSubtitulos();
            actualizarMarcas();
//            C.actualizarSerie(EA, cdv);
//            C.actualizarSubtitulos(EA, cdv);
//            C.getSeries().imprimir();
//            C.getSubtitulos().imprimir();
            TFDireccionPaquete.setText(EA.getPaquete().toString());
            actualizarT();
            seguridad();
            ventana_Principal = this;
            ventanas = new JFrame[]{this, ventana_Administrador_Direcciones_Series, ventana_Administrador_Direcciones_Subtitulos,
                ventana_Administrador_Invalido_Series, ventana_Administrador_Paquete, ventana_Administrador_Seccion, ventana_ConfiguracionDeVideo};
            setLocationRelativeTo(null);
        } catch (Exception ex) {
            responerException(ex);
        }

    }

    public void alCerrar(JFrame f) {
        cerrar(f, 9);

    }

    private void actualizarMarcas() {
        actualizarMarcasBase();
        actualizarMarcasInvalidos();
        actualizarMarcasSubtitulos();
    }

    private void actualizarMarcasBase() {
        actualizarMarcar(EA.getSeccion().getSeries().getDirecciones(), BSeñalarTDireccionesBase);
    }

    private void actualizarMarcasInvalidos() {
        if (!EA.getSeccion().getSeries().getDirecciones().isEmpty()) {
            actualizarMarcar(EA.getSeccion().getSeries().getDirecciones().get(baseDireccionSelecionada).getDirectoriosInvalidos(), BSeñalarTInvalidosBase);
        }

    }

    private void actualizarMarcasSubtitulos() {
        actualizarMarcar(EA.getSeccion().getSubtitulos().getDirecciones(), BSeñalarTSubtitulosBase);
    }

    private void activarDireccion(LinkedList<Direccion> d, JTable t, int fila) {
        // System.out.println("(Boolean) t.getValueAt(fila, 0)="+(Boolean) t.getValueAt(fila, 0));
        d.get(fila).setSeleccionado((Boolean) t.getValueAt(fila, 0));
        //   System.out.println("d.get(fila).isSeleccionado()="+d.get(fila).isSeleccionado());
    }

    private void actualizarT() throws IOException {
        T.setText(copiarSubtitulos(true));
    }

    private String copiarSubtitulos(boolean copiar) throws IOException {
        if (C.getSeries().isEmpty() && C.getSubtitulos().isEmpty()) {
//            System.out.println("false");
            return "";
        }
        CatalogoDeSeries c = new CatalogoDeSeries();
//C.getSeries().imprimir();
        c.addAll(C.getSeries(), C.getSubtitulos());

//        c.imprimir();
        return c.copiarSubtitulosACapitulos(copiar, EA.getT().getCdvSelecionado());

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

    public void seguridad() {
        Visual.activarJComponent(EA.getSeccion().getSeries() != null && !EA.getSeccion().getSeries().getDirecciones().isEmpty(), BClearDireccionBase, BEliminarDireccionBase, BGuardarDireccionBase, BSeñalarTDireccionesBase);
        Visual.activarJComponent(EA.getSeccion().getSeries() != null && !EA.getSeccion().getSeries().getDirecciones().isEmpty()
                && !EA.getSeccion().getSeries().getDirecciones().get(baseDireccionSelecionada).getDirectoriosInvalidos().isEmpty(), BClearInvalidosBase, BEliminarInvalidoBase, BGuardarInvalidosBase, BSeñalarTInvalidosBase);
        Visual.seguridadJTextField_File_Directorio(TFDireccionPaquete, BClearDireccionPaquete, BGuardarDireccionPaquete, BCargarPaquete);
        Visual.activarJComponent(EA.getSeccion().getSubtitulos() != null && !EA.getSeccion().getSubtitulos().getDirecciones().isEmpty(), BClearSubtitulosBase, BEliminarSubtitulosBase, BGuardarSubtitulosBase, BSeñalarTSubtitulosBase);
        Visual.activarJComponent(!T.getText().isEmpty(), BCopiarSubtitulos);
    }

    public void actualizarDireccionBase() throws FileNotFoundException {
        //  System.out.println("actualizarDireccionBase");
        editingTabla Et = new editingTabla() {

            @Override
            public void editingStopped(ChangeEvent e, JTable t, int fila, int columna) {
                try {
//                       System.out.println("aaa");
                    activarDireccion(EA.getSeccion().getSeries().getDirecciones(), t, fila);
                    C.actualizarSerie(EA, cdv);
                    actualizarT();
                    actualizarMarcasBase();
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
        ConfiguracionDeTabla cdt = new ConfiguracionDeTabla(PTodo, PDireccionesBase, SPDireccionesBase, JTDireccionesBase, EA.getSeccion().getSeries().getTabla(), Et, Sf, ANCHO_SELECT, -1);
        Visual.actualizarTablaSeleccionable(cdt);
        actualizarInvalidosBase(baseDireccionSelecionada = 0);

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
                    activarInvalido(EA.getSeccion().getSeries().getDirecciones().get(baseDireccionSelecionada).getDirectoriosInvalidos(), t, fila);
                    C.actualizarSerie(EA, cdv);
                    actualizarT();
                    actualizarMarcasInvalidos();
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
        ConfiguracionDeTabla cdt = new ConfiguracionDeTabla(PTodo, PInvalidosBase, SPInvalidosBase, JTInvalidosBase, EA.getSeccion().getSeries().getTablaInvalidos(indice), Ac, Sf, ANCHO_SELECT, -1);
        Visual.actualizarTablaSeleccionable(cdt);
        //actualizarTBase();
        //actualizarTEntrada();
        if (!EA.getSeccion().getSeries().getDirecciones().isEmpty() && EA.getSeccion().getSeries().getDirecciones().get(indice).getDirectoriosInvalidos() != null) {
            actualizarMarcar(EA.getSeccion().getSeries().getDirecciones().get(indice).getDirectoriosInvalidos(), BSeñalarTInvalidosBase);
        }
    }

    public void actualizarTablaSubtitulos() throws FileNotFoundException {
        //  System.out.println("actualizarDireccionBase");
        editingTabla Et = new editingTabla() {

            @Override
            public void editingStopped(ChangeEvent e, JTable t, int fila, int columna) {
                try {
//                       System.out.println("aaa");
                    activarDireccion(EA.getSeccion().getSubtitulos().getDirecciones(), t, fila);
                    C.actualizarSubtitulos(EA, cdv);
                    actualizarT();
                    actualizarMarcasSubtitulos();
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
                SubtituloSeleccionado = t.getSelectedRow();
//                try {
//                    actualizarInvalidosBase(baseDireccionSelecionada);
//                    //  System.out.println("baseSelecionada=" + baseSelecionada);
//                } catch (Exception ex) {
//                    responerException(ex);
//                }
            }

        };
        ConfiguracionDeTabla cdt = new ConfiguracionDeTabla(PTodo, PSubtitulosBase, SPSubtitulosBase, JTSubtitulosBase, EA.getSeccion().getSubtitulos().getTabla(), Et, Sf, ANCHO_SELECT, -1);
        Visual.actualizarTablaSeleccionable(cdt);
//        actualizarInvalidosBase(baseDireccionSelecionada = 0);

    }

    private LinkedList<DirectoriosInvalidos> getDirectoriosInvalidosSerie(File f, JCheckBox SinCarpetas) throws FileNotFoundException {
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
                    if (Paquete.esCarpetaSubtitulos(F[i], cdv)) {
                        addSubtitulos(F[i]);
//                        EA.getSeccion().getSubtitulos().getDirecciones().add(new Direccion(new File(f.toString()), true, getDirectoriosInvalidosSerie(f, SinCarpetas)));
                        directoriosInvalidos.add(new DirectoriosInvalidos(F[i].getName(), true));
                        continue;
                    }

                    if (SinCarpetas.isSelected()
                            || (MetodosUtiles.startsWith(F[i].getName(), "!", "_"))) {
                        directoriosInvalidos.add(new DirectoriosInvalidos(F[i].getName(), true));
                    }

                }
            }
//                }
        }
        return directoriosInvalidos;
    }

    public boolean addSubtitulos(LinkedList<File> direcciones) throws FileNotFoundException {
        boolean agrego = false;
        for (int i = 0; i < direcciones.size(); i++) {
            if (addSubtitulos(direcciones.get(i))) {
                agrego = true;
            }
        }
        return agrego;
    }

    public boolean addSubtitulos(File f) throws FileNotFoundException {
        if (f.isDirectory()) {
            Direccion d = new Direccion(new File(f.toString()), true, getDirectoriosInvalidosSerie(f, CBSinCarpetasSubtitulos));
            for (int i = 0; i < EA.getSeccion().getSubtitulos().getDirecciones().size(); i++) {
                if (EA.getSeccion().getSubtitulos().getDirecciones().get(i).getF().toString().equals(d.getF().toString())) {
                    return false;
                }
            }
            EA.getSeccion().getSubtitulos().getDirecciones().add(d);
            return true;
        }
        return false;

//        if (f.isDirectory()) {
//            EA.getSeccion().getSubtitulos().getDirecciones().add(new Direccion(new File(f.toString()), true, getDirectoriosInvalidosSerie(f, CBSinCarpetasSubtitulos)));
//        }
    }

    public boolean addSerie(LinkedList<File> direcciones) throws FileNotFoundException {
        boolean agrego = false;
        for (int i = 0; i < direcciones.size(); i++) {
            if (addSerie(direcciones.get(i))) {
                agrego = true;
            }
        }
        return agrego;
    }

    public boolean addSerie(File f) throws FileNotFoundException {
        if (f.isDirectory()) {
            Direccion d = new Direccion(new File(f.toString()), true, getDirectoriosInvalidosSerie(f, CBSinCarpetasBase));
            for (int i = 0; i < EA.getSeccion().getSeries().getDirecciones().size(); i++) {
                if (EA.getSeccion().getSeries().getDirecciones().get(i).getF().toString().equals(d.getF().toString())) {
                    return false;
                }
            }
            EA.getSeccion().getSeries().getDirecciones().add(d);
            return true;
        }
        return false;
    }

    private void eliminarDireccionSubtitulo() {
        eliminarDireccion(EA.getSeccion().getSubtitulos().getDirecciones(), SubtituloSeleccionado);
    }

    private void eliminarDireccionSerie() {
        eliminarDireccion(EA.getSeccion().getSeries().getDirecciones(), baseDireccionSelecionada);
    }

    private boolean eliminarDireccion(LinkedList<Direccion> d, int indice) {
        if (indice >= 0 && indice < d.size()) {
            d.remove(indice);
            return true;
        }
        return false;
    }

    public void visualizarVentanaAdministradorSubtitulos() {
        try {
            if (ventana_Administrador_Direcciones_Subtitulos == null) {
                ventana_Administrador_Direcciones_Subtitulos = new Ventana_Administrador(DIRECCION_ADMINISTRADOR_DIRECCIONES_SUBTITULOS, new Accion_Cargar_Cancelar() {

                    @Override
                    public void aceptar_Y_cargar() {
                        EA.getSeccion().getSubtitulos().getDirecciones().addAll(ventana_Administrador_Direcciones_Subtitulos.getInformacionSelecionada());
                        try {
//                            actualizarDireccionBase();
                            actualizarTablaSubtitulos();
                            actualizarMarcasSubtitulos();
//                            actualizarMarcas();
//                            C.actualizarSerie(EA, cdv);
                            C.actualizarSubtitulos(EA, cdv);
                            actualizarT();
                        } catch (Exception ex) {
                            responerException(ex);
                        }
//                        EA.getDirecciones_De_Carpetas().getDirecciones_Carpetas().addAll(ventana_Administrador_Drecciones_Carpetas.getInformacionSelecionada());
                        ventana_Administrador_Direcciones_Subtitulos.setVisible(false);
                        setVisible(true);
                    }

                    @Override
                    public void cancelar() {
                        ventana_Administrador_Direcciones_Subtitulos.setVisible(false);
                        setVisible(true);
                    }
                });
            } else {
                ventana_Administrador_Direcciones_Subtitulos.setDireccionAdministrador(DIRECCION_ADMINISTRADOR_DIRECCIONES_SUBTITULOS);
            }

            ventana_Administrador_Direcciones_Subtitulos.setVisible(true);
            setVisible(false);
        } catch (Exception ex) {
            responerException(ex);
        }
    }

    public void visualizarVentanaAdministradorInvalido_Series() {
        try {
            if (ventana_Administrador_Invalido_Series == null) {
                ventana_Administrador_Invalido_Series = new Ventana_Administrador(DIRECCION_ADMINISTRADOR_INVALIDOS_SERIES, new Accion_Cargar_Cancelar() {

                    @Override
                    public void aceptar_Y_cargar() {
                        EA.getSeccion().getSeries().getDirecciones().get(baseInvalidoSeleccionada).getDirectoriosInvalidos().addAll(ventana_Administrador_Invalido_Series.getInformacionSelecionada());
                        try {
//                            actualizarDireccionBase();
                            actualizarInvalidosBase(baseDireccionSelecionada);
                            actualizarMarcasInvalidos();

//                            actualizarTablaSubtitulos();
//                            actualizarMarcas();
                            C.actualizarSerie(EA, cdv);
//                            C.actualizarSubtitulos(EA, cdv);
                            actualizarT();
                        } catch (Exception ex) {
                            responerException(ex);
                        }
                        ventana_Administrador_Invalido_Series.setVisible(false);
                        setVisible(true);
                    }

                    @Override
                    public void cancelar() {
                        ventana_Administrador_Invalido_Series.setVisible(false);
                        setVisible(true);
                    }
                });
            } else {
                ventana_Administrador_Invalido_Series.setDireccionAdministrador(DIRECCION_ADMINISTRADOR_INVALIDOS_SERIES);
            }

            ventana_Administrador_Invalido_Series.setVisible(true);
            setVisible(false);
        } catch (Exception ex) {
            responerException(ex);
        }
    }

    public void visualizarVentanaAdministradorDirecciones_Series() {
        try {
            if (ventana_Administrador_Direcciones_Series == null) {
                ventana_Administrador_Direcciones_Series = new Ventana_Administrador(DIRECCION_ADMINISTRADOR_DIRECCIONES_SERIES, new Accion_Cargar_Cancelar() {

                    @Override
                    public void aceptar_Y_cargar() {
                        EA.getSeccion().getSeries().getDirecciones().addAll(ventana_Administrador_Direcciones_Series.getInformacionSelecionada());
                        try {
                            actualizarDireccionBase();
                            actualizarTablaSubtitulos();
                            actualizarMarcas();
                            C.actualizarSerie(EA, cdv);
                            C.actualizarSubtitulos(EA, cdv);
                            actualizarT();
                        } catch (Exception ex) {
                            responerException(ex);
                        }
                        ventana_Administrador_Direcciones_Series.setVisible(false);
                        setVisible(true);
                    }

                    @Override
                    public void cancelar() {
                        ventana_Administrador_Direcciones_Series.setVisible(false);
                        setVisible(true);
                    }
                });
            } else {
                ventana_Administrador_Direcciones_Series.setDireccionAdministrador(DIRECCION_ADMINISTRADOR_DIRECCIONES_SERIES);
            }

            ventana_Administrador_Direcciones_Series.setVisible(true);
            setVisible(false);
        } catch (Exception ex) {
            responerException(ex);
        }
    }

    public void setFileEnTF_DireccionPaquete(File f) {
        EA.setPaquete(f);
        TFDireccionPaquete.setText(f.toString());
    }

    public void visualizarVentanaAdministradorPaquete() {
        try {
            if (ventana_Administrador_Paquete == null) {
                ventana_Administrador_Paquete = new Ventana_Administrador(DIRECCION_ADMINISTRADOR_DIRECCIONES_PAQUETE, new Accion_Cargar_Cancelar() {

                    @Override
                    public void aceptar_Y_cargar() {
                        TFDireccionPaquete.setText(ventana_Administrador_Paquete.getInformacionSelecionada().toString());
                        ventana_Administrador_Paquete.setVisible(false);
                        setVisible(true);
                    }

                    @Override
                    public void cancelar() {
                        ventana_Administrador_Paquete.setVisible(false);
                        setVisible(true);
                    }
                });
            } else {
                ventana_Administrador_Paquete.setDireccionAdministrador(DIRECCION_ADMINISTRADOR_DIRECCIONES_PAQUETE);
            }
            ventana_Administrador_Paquete.setVisible(true);
            setVisible(false);
        } catch (Exception ex) {
            responerException(ex);
        }
    }

    public void visualizarVentanaAdministradorSeccion() {
        try {
            if (ventana_Administrador_Seccion == null) {
                ventana_Administrador_Seccion = new Ventana_Administrador(DIRECCION_ADMINISTRADOR_SECCION, new Accion_Cargar_Cancelar() {

                    @Override
                    public void aceptar_Y_cargar() {
                        try {
                            EA.setSeccion(ventana_Administrador_Seccion.getInformacionSelecionada());
                            actualizarDireccionBase();
                            actualizarTablaSubtitulos();
                            actualizarMarcas();
                            C.actualizarSerie(EA, cdv);
                            C.actualizarSubtitulos(EA, cdv);
                            actualizarT();
                        } catch (Exception ex) {
                            responerException(ex);
                        }
                        ventana_Administrador_Seccion.setVisible(false);
                        setVisible(true);
                    }

                    @Override
                    public void cancelar() {
                        ventana_Administrador_Seccion.setVisible(false);
                        setVisible(true);
                    }
                });
            } else {
                ventana_Administrador_Seccion.setDireccionAdministrador(DIRECCION_ADMINISTRADOR_SECCION);
            }
            ventana_Administrador_Seccion.setVisible(true);
            setVisible(false);
        } catch (Exception ex) {
            responerException(ex);
        }
    }

    public void agregarEnAdministradorFile(File f, String mensaje) {
        String nuevoNombre = JOptionPane.showInputDialog(mensaje);
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                Admininistrador_De_Guardado<File> A = new Admininistrador_De_Guardado(DIRECCION_ADMINISTRADOR_DIRECCIONES_PAQUETE);
                A.agregarInformacion(nuevoNombre, f);
                A.guardarAdminastrador(DIRECCION_ADMINISTRADOR_DIRECCIONES_PAQUETE);
                // visualizarVentanaAdministradorTXT();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }

    public void addInvalidoBase(String a) throws FileNotFoundException {
        if (addInvalido(EA.getSeccion().getSeries().getDirecciones(), baseDireccionSelecionada, a)) {
//            actualizarInvalidosBase(baseDireccionSelecionada);
        }
    }

    private boolean addInvalido(LinkedList<Direccion> d, int indice, String a) {
        if (indice >= 0 && indice < d.size() && !a.isEmpty()) {
            d.get(indice).getDirectoriosInvalidos().add(new DirectoriosInvalidos(a, true));
            return true;
        }
        return false;
    }

    private void menosSubtitulo() {
        try {
            eliminarDireccionSubtitulo();
            actualizarTablaSubtitulos();
            actualizarMarcasSubtitulos();
            C.actualizarSubtitulos(EA, cdv);
            actualizarT();
            seguridad();

        } catch (Exception ex) {
            responerException(ex);
        }
    }

    private void menosSerie() {
        try {
            eliminarDireccionSerie();
            actualizarDireccionBase();
            actualizarMarcasBase();
            actualizarMarcasInvalidos();
            C.actualizarSerie(EA, cdv);
            actualizarT();
            seguridad();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }

    private void menosInvalidos() {
        try {
            EA.getSeccion().getSeries().getDirecciones().get(baseDireccionSelecionada).getDirectoriosInvalidos().remove(baseInvalidoSeleccionada);
            actualizarInvalidosBase(baseDireccionSelecionada);
            C.actualizarSerie(EA, cdv);
            actualizarT();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PTodo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        T = new javax.swing.JTextArea();
        SPSubtitulosBase = new javax.swing.JScrollPane();
        PSubtitulosBase = new javax.swing.JPanel();
        JTSubtitulosBase = new javax.swing.JTable();
        BSeñalarTSubtitulosBase = new javax.swing.JButton();
        BBuscarSubtitulosBase = new javax.swing.JButton();
        BEliminarSubtitulosBase = new javax.swing.JButton();
        BClearSubtitulosBase = new javax.swing.JButton();
        BSubtitulosBaseAdministrador = new javax.swing.JButton();
        BGuardarSubtitulosBase = new javax.swing.JButton();
        BActualizar = new javax.swing.JButton();
        BAgregarSeccion = new javax.swing.JButton();
        BCopiarSubtitulos = new javax.swing.JButton();
        BGuardarSeccion = new javax.swing.JButton();
        BAdministradorSeccion = new javax.swing.JButton();
        BAgregarDireccionPaquete = new javax.swing.JButton();
        BAdministradorDireccionPaquete = new javax.swing.JButton();
        BGuardarDireccionPaquete = new javax.swing.JButton();
        BClearDireccionPaquete = new javax.swing.JButton();
        TFDireccionPaquete = new javax.swing.JTextField();
        BCargarPaquete = new javax.swing.JButton();
        LDireccionDelPaquete1 = new javax.swing.JLabel();
        BBuscarDireccionBase = new javax.swing.JButton();
        BClearInvalidosBase = new javax.swing.JButton();
        BEliminarDireccionBase = new javax.swing.JButton();
        BSeñalarTDireccionesBase = new javax.swing.JButton();
        SPDireccionesBase = new javax.swing.JScrollPane();
        PDireccionesBase = new javax.swing.JPanel();
        JTDireccionesBase = new javax.swing.JTable();
        CBSinCarpetasSubtitulos = new javax.swing.JCheckBox();
        BInvalidoBaseAdministrador = new javax.swing.JButton();
        BSeñalarTInvalidosBase = new javax.swing.JButton();
        BClearDireccionBase = new javax.swing.JButton();
        BDireccionesBaseAdministrador = new javax.swing.JButton();
        BGuardarInvalidosBase = new javax.swing.JButton();
        BEliminarInvalidoBase = new javax.swing.JButton();
        BGuardarDireccionBase = new javax.swing.JButton();
        BAgregarInvalidoBase = new javax.swing.JButton();
        SPInvalidosBase = new javax.swing.JScrollPane();
        PInvalidosBase = new javax.swing.JPanel();
        JTInvalidosBase = new javax.swing.JTable();
        CBSinCarpetasBase = new javax.swing.JCheckBox();
        BConfiguracion = new javax.swing.JButton();
        BAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Copiador de Subtitulos");
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

        T.setEditable(false);
        T.setColumns(20);
        T.setRows(5);
        T.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apretoT(evt);
            }
        });
        jScrollPane1.setViewportView(T);

        PTodo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 680, 250));

        PSubtitulosBase.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JTSubtitulosBase.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        JTSubtitulosBase.setToolTipText("Tabla de direcciones de subtitulos");
        JTSubtitulosBase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JTSubtitulosBaseapreto(evt);
            }
        });
        JTSubtitulosBase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                apretoTeclaTablaSubtitulos(evt);
            }
        });
        PSubtitulosBase.add(JTSubtitulosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 120));

        SPSubtitulosBase.setViewportView(PSubtitulosBase);

        PTodo.add(SPSubtitulosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 680, 100));

        BSeñalarTSubtitulosBase.setText("0");
        BSeñalarTSubtitulosBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSeñalarTSubtitulosBaseActionPerformed(evt);
            }
        });
        PTodo.add(BSeñalarTSubtitulosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 40, -1));

        BBuscarSubtitulosBase.setText("+");
        BBuscarSubtitulosBase.setToolTipText("<html>\nagrega direcciones (Carpetas) de subtitulos\n</html>");
        BBuscarSubtitulosBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBuscarSubtitulosBaseActionPerformed(evt);
            }
        });
        PTodo.add(BBuscarSubtitulosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, -1, -1));

        BEliminarSubtitulosBase.setText("-");
        BEliminarSubtitulosBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEliminarSubtitulosBaseActionPerformed(evt);
            }
        });
        PTodo.add(BEliminarSubtitulosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 310, -1, -1));

        BClearSubtitulosBase.setText("C");
        BClearSubtitulosBase.setToolTipText("Elimina todas las direcciones");
        BClearSubtitulosBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BClearSubtitulosBaseActionPerformed(evt);
            }
        });
        PTodo.add(BClearSubtitulosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, -1, -1));

        BSubtitulosBaseAdministrador.setText("^");
        BSubtitulosBaseAdministrador.setToolTipText("Abre la ventana del administrador de direcciones de subtitulos");
        BSubtitulosBaseAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSubtitulosBaseAdministradorActionPerformed(evt);
            }
        });
        PTodo.add(BSubtitulosBaseAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 310, 50, -1));

        BGuardarSubtitulosBase.setText("G");
        BGuardarSubtitulosBase.setToolTipText("Guarda este conjunto de direcciones de subtitulos");
        BGuardarSubtitulosBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarSubtitulosBaseActionPerformed(evt);
            }
        });
        PTodo.add(BGuardarSubtitulosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 310, -1, -1));

        BActualizar.setText("A");
        BActualizar.setToolTipText("Actualiza el sitema");
        BActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActualizarActionPerformed(evt);
            }
        });
        PTodo.add(BActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 310, -1, -1));

        BAgregarSeccion.setText("+");
        BAgregarSeccion.setToolTipText("<html>\nagrega autamaticamente las direcciones de una carpeta que<br>\ncontine carpetas con subtitulos y series ( Series [TX] )\n</html>");
        BAgregarSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAgregarSeccionActionPerformed(evt);
            }
        });
        PTodo.add(BAgregarSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, -1, -1));

        BCopiarSubtitulos.setText("Copiar Subtitulos");
        BCopiarSubtitulos.setToolTipText("copia los subtitulos en las carpetas correspondientes");
        BCopiarSubtitulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCopiarSubtitulosActionPerformed(evt);
            }
        });
        PTodo.add(BCopiarSubtitulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 310, -1, -1));

        BGuardarSeccion.setText("G");
        BGuardarSeccion.setToolTipText("Guarda todas las direcciones");
        BGuardarSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarSeccionActionPerformed(evt);
            }
        });
        PTodo.add(BGuardarSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, -1, -1));

        BAdministradorSeccion.setText("^");
        BAdministradorSeccion.setToolTipText("Administrador de los conjuntos de direcciones de carpetas de Series y Subtitulos");
        BAdministradorSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAdministradorSeccionActionPerformed(evt);
            }
        });
        PTodo.add(BAdministradorSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, -1, -1));

        BAgregarDireccionPaquete.setText("+");
        BAgregarDireccionPaquete.setToolTipText("busca una direccion de una carpeta");
        BAgregarDireccionPaquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAgregarDireccionPaqueteActionPerformed(evt);
            }
        });
        PTodo.add(BAgregarDireccionPaquete, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 600, -1, -1));

        BAdministradorDireccionPaquete.setText("^");
        BAdministradorDireccionPaquete.setToolTipText("Abre la ventana del administrador de direcciones");
        BAdministradorDireccionPaquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAdministradorDireccionPaqueteActionPerformed(evt);
            }
        });
        PTodo.add(BAdministradorDireccionPaquete, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 600, -1, -1));

        BGuardarDireccionPaquete.setText("G");
        BGuardarDireccionPaquete.setToolTipText("Guarda la direccion en el administrador dedirecciones");
        BGuardarDireccionPaquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarDireccionPaqueteActionPerformed(evt);
            }
        });
        PTodo.add(BGuardarDireccionPaquete, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 600, -1, -1));

        BClearDireccionPaquete.setText("C");
        BClearDireccionPaquete.setToolTipText("Limpia la direccion");
        BClearDireccionPaquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BClearDireccionPaqueteActionPerformed(evt);
            }
        });
        PTodo.add(BClearDireccionPaquete, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 600, -1, -1));

        TFDireccionPaquete.setToolTipText("Direccion donde de la carpeta el paquete");
        TFDireccionPaquete.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                escribioTFDireccionPaquete(evt);
            }
        });
        PTodo.add(TFDireccionPaquete, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 630, 680, -1));

        BCargarPaquete.setText("Cargar");
        BCargarPaquete.setToolTipText("<html>\nDistribuye el contenido ( Direcciones ) del paquete correctamente entre las distintas secciones\n</html>");
        BCargarPaquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCargarPaqueteActionPerformed(evt);
            }
        });
        PTodo.add(BCargarPaquete, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, -1, -1));

        LDireccionDelPaquete1.setText("Direccion Del Paquete");
        PTodo.add(LDireccionDelPaquete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 600, -1, -1));

        BBuscarDireccionBase.setText("+");
        BBuscarDireccionBase.setToolTipText("<html>\nagrega direcciones (Carpetas)\n</html>");
        BBuscarDireccionBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBuscarDireccionBaseActionPerformed(evt);
            }
        });
        PTodo.add(BBuscarDireccionBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 450, -1, -1));

        BClearInvalidosBase.setText("C");
        BClearInvalidosBase.setToolTipText("Elimina todas los nombres de carpetas no validos");
        BClearInvalidosBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BClearInvalidosBaseActionPerformed(evt);
            }
        });
        PTodo.add(BClearInvalidosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 450, -1, -1));

        BEliminarDireccionBase.setText("-");
        BEliminarDireccionBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEliminarDireccionBaseActionPerformed(evt);
            }
        });
        PTodo.add(BEliminarDireccionBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 450, -1, -1));

        BSeñalarTDireccionesBase.setText("0");
        BSeñalarTDireccionesBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSeñalarTDireccionesBaseActionPerformed(evt);
            }
        });
        PTodo.add(BSeñalarTDireccionesBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 40, -1));

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
                JTDireccionesBaseapreto(evt);
            }
        });
        JTDireccionesBase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                apretoTeclaTablaSeries(evt);
            }
        });
        PDireccionesBase.add(JTDireccionesBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 120));

        SPDireccionesBase.setViewportView(PDireccionesBase);

        PTodo.add(SPDireccionesBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 400, 100));

        CBSinCarpetasSubtitulos.setText("Sin Carpetas");
        CBSinCarpetasSubtitulos.setToolTipText("Al agregar \"+\"  pasa automaticamente todas las carpetas como nombres de carpetas invalidas");
        PTodo.add(CBSinCarpetasSubtitulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, -1, -1));

        BInvalidoBaseAdministrador.setText("^");
        BInvalidoBaseAdministrador.setToolTipText("Abre la ventana del administrador de nombres de carpetas invalidas");
        BInvalidoBaseAdministrador.setMargin(new java.awt.Insets(2, 2, 2, 2));
        BInvalidoBaseAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BInvalidoBaseAdministradorActionPerformed(evt);
            }
        });
        PTodo.add(BInvalidoBaseAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 450, 40, -1));

        BSeñalarTInvalidosBase.setText("o");
        BSeñalarTInvalidosBase.setMargin(new java.awt.Insets(2, 2, 2, 2));
        BSeñalarTInvalidosBase.setMinimumSize(new java.awt.Dimension(10, 10));
        BSeñalarTInvalidosBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSeñalarTInvalidosBaseActionPerformed(evt);
            }
        });
        PTodo.add(BSeñalarTInvalidosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 450, -1, 30));

        BClearDireccionBase.setText("C");
        BClearDireccionBase.setToolTipText("Elimina todas las direcciones");
        BClearDireccionBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BClearDireccionBaseActionPerformed(evt);
            }
        });
        PTodo.add(BClearDireccionBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 450, -1, -1));

        BDireccionesBaseAdministrador.setText("^");
        BDireccionesBaseAdministrador.setToolTipText("Abre la ventana del administrador de direcciones");
        BDireccionesBaseAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDireccionesBaseAdministradorActionPerformed(evt);
            }
        });
        PTodo.add(BDireccionesBaseAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 450, 50, -1));

        BGuardarInvalidosBase.setText("G");
        BGuardarInvalidosBase.setToolTipText("Guarda este conjunto nombres de carpetas a ignorar");
        BGuardarInvalidosBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarInvalidosBaseActionPerformed(evt);
            }
        });
        PTodo.add(BGuardarInvalidosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 450, -1, -1));

        BEliminarInvalidoBase.setText("-");
        BEliminarInvalidoBase.setMargin(new java.awt.Insets(2, 2, 2, 2));
        BEliminarInvalidoBase.setMinimumSize(new java.awt.Dimension(10, 10));
        BEliminarInvalidoBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEliminarInvalidoBaseActionPerformed(evt);
            }
        });
        PTodo.add(BEliminarInvalidoBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 450, -1, -1));

        BGuardarDireccionBase.setText("G");
        BGuardarDireccionBase.setToolTipText("Guarda este conjunto de direcciones");
        BGuardarDireccionBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarDireccionBaseActionPerformed(evt);
            }
        });
        PTodo.add(BGuardarDireccionBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 450, -1, -1));

        BAgregarInvalidoBase.setText("+");
        BAgregarInvalidoBase.setToolTipText("agrega un nombre de una carpeta que va ser ignorada");
        BAgregarInvalidoBase.setMargin(new java.awt.Insets(2, 2, 2, 2));
        BAgregarInvalidoBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAgregarInvalidoBaseActionPerformed(evt);
            }
        });
        PTodo.add(BAgregarInvalidoBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 450, -1, -1));

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
                apretoTeclaTablaInvalidos(evt);
            }
        });
        PInvalidosBase.add(JTInvalidosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 120));

        SPInvalidosBase.setViewportView(PInvalidosBase);

        PTodo.add(SPInvalidosBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 480, 260, 100));

        CBSinCarpetasBase.setText("Sin Carpetas");
        CBSinCarpetasBase.setToolTipText("Al agregar \"+\"  pasa automaticamente todas las carpetas como nombres de carpetas invalidas");
        PTodo.add(CBSinCarpetasBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, -1, -1));

        BConfiguracion.setText("Configuracion");
        BConfiguracion.setToolTipText("Abre la ventana de configuracion");
        BConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BConfiguracionActionPerformed(evt);
            }
        });
        PTodo.add(BConfiguracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 120, -1));

        BAtras.setText("Atras");
        BAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAtrasActionPerformed(evt);
            }
        });
        PTodo.add(BAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 60, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JTSubtitulosBaseapreto(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTSubtitulosBaseapreto

    }//GEN-LAST:event_JTSubtitulosBaseapreto

    private void BSeñalarTSubtitulosBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSeñalarTSubtitulosBaseActionPerformed
        try {
            apretoMarca(EA.getSeccion().getSubtitulos().getDirecciones(), BSeñalarTSubtitulosBase);
            actualizarTablaSubtitulos();
            C.actualizarSubtitulos(EA, cdv);
            actualizarT();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BSeñalarTSubtitulosBaseActionPerformed

    private void BBuscarSubtitulosBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBuscarSubtitulosBaseActionPerformed
        if (Visual.buscador(jf, this)) {
            try {
                if (addSubtitulos(jf.getSelectedFile())) {
                    actualizarTablaSubtitulos();
                    actualizarMarcasSubtitulos();
                    C.actualizarSubtitulos(EA, cdv);
                    actualizarT();
                    seguridad();
                }

            } catch (Exception ex) {
                responerException(ex);
            }

        }
    }//GEN-LAST:event_BBuscarSubtitulosBaseActionPerformed

    private void BEliminarSubtitulosBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEliminarSubtitulosBaseActionPerformed
//        try {
//            eliminarDireccionSubtitulo();
//            actualizarTablaSubtitulos();
//            actualizarMarcasSubtitulos();
//            C.actualizarSubtitulos(EA, cdv);
//            actualizarT();
//            seguridad();
//
//        } catch (Exception ex) {
//            responerException(ex);
//        }
        menosSubtitulo();
    }//GEN-LAST:event_BEliminarSubtitulosBaseActionPerformed

    private void BClearSubtitulosBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BClearSubtitulosBaseActionPerformed
        try {
            EA.getSeccion().getSubtitulos().getDirecciones().clear();
            actualizarTablaSubtitulos();
            actualizarMarcasSubtitulos();
            C.actualizarSubtitulos(EA, cdv);
            actualizarT();
            seguridad();

        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BClearSubtitulosBaseActionPerformed

    private void BSubtitulosBaseAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSubtitulosBaseAdministradorActionPerformed
        visualizarVentanaAdministradorSubtitulos();
    }//GEN-LAST:event_BSubtitulosBaseAdministradorActionPerformed

    private void BGuardarSubtitulosBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarSubtitulosBaseActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de las direcciones");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                Admininistrador_De_Guardado<LinkedList<Direccion>> A = new Admininistrador_De_Guardado(DIRECCION_ADMINISTRADOR_DIRECCIONES_SUBTITULOS);
                A.agregarInformacion(nuevoNombre, EA.getSeccion().getSubtitulos().getDirecciones());
                A.guardarAdminastrador(DIRECCION_ADMINISTRADOR_DIRECCIONES_SUBTITULOS);
                // visualizarVentanaAdministradorDirecciones();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BGuardarSubtitulosBaseActionPerformed

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
//        jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    }//GEN-LAST:event_BAgregarDireccionPaqueteActionPerformed

    private void BAdministradorDireccionPaqueteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAdministradorDireccionPaqueteActionPerformed
        visualizarVentanaAdministradorPaquete();

    }//GEN-LAST:event_BAdministradorDireccionPaqueteActionPerformed

    private void BGuardarDireccionPaqueteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarDireccionPaqueteActionPerformed
        agregarEnAdministradorFile(new File(TFDireccionPaquete.getText()), "Escriba el nuevo nombre de la direccion de la carpeta del paquete");
    }//GEN-LAST:event_BGuardarDireccionPaqueteActionPerformed

    private void BClearDireccionPaqueteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BClearDireccionPaqueteActionPerformed
        TFDireccionPaquete.setText("");
        EA.setPaquete(new File(""));
        seguridad();
    }//GEN-LAST:event_BClearDireccionPaqueteActionPerformed

    private void escribioTFDireccionPaquete(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_escribioTFDireccionPaquete
        EA.setPaquete(new File(TFDireccionPaquete.getText()));
        seguridad();
    }//GEN-LAST:event_escribioTFDireccionPaquete

    private void BCargarPaqueteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCargarPaqueteActionPerformed
        try {
            Paquete p = new Paquete(new File(TFDireccionPaquete.getText()), cdv);
            if (p.getSeries() != null) {
                if (p.getSeries().getEnTransmision() != null) {
                    if (p.getSeries().getEnTransmision().getTX() != null) {
                        addSerie(p.getSeries().getEnTransmision().getTX().getDirecciones());
//                        addSubtitulos(p.getSeries().getEnTransmision().getTX().getSubtitulos().getDirecciones());
                    }
                    if (p.getSeries().getEnTransmision().getClasicas() != null) {
                        addSerie(p.getSeries().getEnTransmision().getClasicas().getDirecciones());
                    }
                    if (p.getSeries().getEnTransmision().getDobladas() != null) {
                        addSerie(p.getSeries().getEnTransmision().getDobladas().getDirecciones());
                    }
                }
                if (p.getSeries().getFinalizadas() != null) {
                    if (p.getSeries().getFinalizadas().getFinalizadas() != null) {
                        addSerie(p.getSeries().getFinalizadas().getFinalizadas().getDirecciones());
                    }
                    if (p.getSeries().getFinalizadas().getFinalizadasEspañol() != null) {
                        addSerie(p.getSeries().getFinalizadas().getFinalizadasEspañol().getDirecciones());
                    }
                }
            }

            actualizarDireccionBase();
            actualizarTablaSubtitulos();
            actualizarMarcas();
            C.actualizarSerie(EA, cdv);
            C.actualizarSubtitulos(EA, cdv);
            actualizarT();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BCargarPaqueteActionPerformed

    private void BBuscarDireccionBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBuscarDireccionBaseActionPerformed
        if (Visual.buscador(jf, this)) {
            try {
                addSerie(jf.getSelectedFile());
                actualizarDireccionBase();
                actualizarTablaSubtitulos();
                actualizarMarcas();
                C.actualizarSerie(EA, cdv);
                C.actualizarSubtitulos(EA, cdv);
                actualizarT();
                seguridad();
            } catch (Exception ex) {
                responerException(ex);
            }

        }
    }//GEN-LAST:event_BBuscarDireccionBaseActionPerformed

    private void BClearInvalidosBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BClearInvalidosBaseActionPerformed
        try {
            EA.getSeccion().getSeries().getDirecciones().get(baseDireccionSelecionada).getDirectoriosInvalidos().clear();
            actualizarInvalidosBase(baseDireccionSelecionada);
            actualizarMarcasInvalidos();
            C.actualizarSerie(EA, cdv);
            actualizarT();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }

    }//GEN-LAST:event_BClearInvalidosBaseActionPerformed

    private void BEliminarDireccionBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEliminarDireccionBaseActionPerformed
//        try {
//            eliminarDireccionSerie();
//            actualizarDireccionBase();
//            actualizarMarcasBase();
//            actualizarMarcasInvalidos();
//            C.actualizarSerie(EA, cdv);
//            actualizarT();
//            seguridad();
//            seguridad();
//        } catch (Exception ex) {
//            responerException(ex);
//        }
        menosSerie();
    }//GEN-LAST:event_BEliminarDireccionBaseActionPerformed

    private void BSeñalarTDireccionesBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSeñalarTDireccionesBaseActionPerformed
        try {
            apretoMarca(EA.getSeccion().getSeries().getDirecciones(), BSeñalarTDireccionesBase);
            actualizarDireccionBase();
            C.actualizarSerie(EA, cdv);
            actualizarT();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BSeñalarTDireccionesBaseActionPerformed

    private void JTDireccionesBaseapreto(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTDireccionesBaseapreto

    }//GEN-LAST:event_JTDireccionesBaseapreto

    private void BInvalidoBaseAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BInvalidoBaseAdministradorActionPerformed
        visualizarVentanaAdministradorInvalido_Series();

    }//GEN-LAST:event_BInvalidoBaseAdministradorActionPerformed

    private void BSeñalarTInvalidosBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSeñalarTInvalidosBaseActionPerformed

        try {
            apretoMarca(EA.getSeccion().getSeries().getDirecciones().get(baseDireccionSelecionada).getDirectoriosInvalidos(), BSeñalarTInvalidosBase);
            actualizarInvalidosBase(baseDireccionSelecionada);
            C.actualizarSerie(EA, cdv);
            actualizarT();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BSeñalarTInvalidosBaseActionPerformed

    private void BClearDireccionBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BClearDireccionBaseActionPerformed
        try {
            EA.getSeccion().getSeries().getDirecciones().clear();
            actualizarDireccionBase();
            actualizarMarcasBase();
            actualizarMarcasInvalidos();
            C.actualizarSerie(EA, cdv);
            actualizarT();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BClearDireccionBaseActionPerformed

    private void BDireccionesBaseAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDireccionesBaseAdministradorActionPerformed
        visualizarVentanaAdministradorDirecciones_Series();

    }//GEN-LAST:event_BDireccionesBaseAdministradorActionPerformed

    private void BGuardarInvalidosBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarInvalidosBaseActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de los directorios invalidos");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                Admininistrador_De_Guardado<LinkedList<DirectoriosInvalidos>> A = new Admininistrador_De_Guardado(DIRECCION_ADMINISTRADOR_INVALIDOS_SERIES);
                A.agregarInformacion(nuevoNombre, EA.getSeccion().getSeries().getDirecciones().get(baseDireccionSelecionada).getDirectoriosInvalidos());
                A.guardarAdminastrador(DIRECCION_ADMINISTRADOR_INVALIDOS_SERIES);
                // visualizarVentanaAdministradorInvalidos();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BGuardarInvalidosBaseActionPerformed

    private void BEliminarInvalidoBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEliminarInvalidoBaseActionPerformed
//        try {
//            EA.getSeccion().getSeries().getDirecciones().get(baseDireccionSelecionada).getDirectoriosInvalidos().remove(baseInvalidoSeleccionada);
//            actualizarInvalidosBase(baseDireccionSelecionada);
//            C.actualizarSerie(EA, cdv);
//            actualizarT();
//            seguridad();
//        } catch (Exception ex) {
//            responerException(ex);
//        }
        menosInvalidos();
    }//GEN-LAST:event_BEliminarInvalidoBaseActionPerformed

    private void BGuardarDireccionBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarDireccionBaseActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de las direcciones");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                Admininistrador_De_Guardado<LinkedList<Direccion>> A = new Admininistrador_De_Guardado(DIRECCION_ADMINISTRADOR_DIRECCIONES_SERIES);
                A.agregarInformacion(nuevoNombre, EA.getSeccion().getSeries().getDirecciones());
                A.guardarAdminastrador(DIRECCION_ADMINISTRADOR_DIRECCIONES_SERIES);
                // visualizarVentanaAdministradorDirecciones();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BGuardarDireccionBaseActionPerformed

    private void BAgregarInvalidoBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAgregarInvalidoBaseActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de las direcciones");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {

                addInvalidoBase(nuevoNombre);
                actualizarInvalidosBase(baseDireccionSelecionada);
                C.actualizarSerie(EA, cdv);
                actualizarT();
                seguridad();

            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BAgregarInvalidoBaseActionPerformed

    private void BConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BConfiguracionActionPerformed
        visualizarVentanaConfiguracionDeVideo();
    }//GEN-LAST:event_BConfiguracionActionPerformed

    private void BActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BActualizarActionPerformed
        try {
            C.actualizarSerie(EA, cdv);
            C.actualizarSubtitulos(EA, cdv);
//            C.getSubtitulos().imprimir();
            actualizarT();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BActualizarActionPerformed

    private void BAgregarSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAgregarSeccionActionPerformed
//       jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (Visual.buscador(jf, this)) {
            try {
                CarpetaSeriesTX tx = new CarpetaSeriesTX(jf.getSelectedFile(), new String[]{}, cdv);
                if (!tx.getDirecciones().isEmpty()) {
                    addSerie(tx.getDirecciones());

                }
                actualizarDireccionBase();
                actualizarTablaSubtitulos();
                actualizarMarcas();
                C.actualizarSerie(EA, cdv);
                C.actualizarSubtitulos(EA, cdv);
                actualizarT();
                seguridad();
            } catch (Exception ex) {
                responerException(ex);
            }

        }
    }//GEN-LAST:event_BAgregarSeccionActionPerformed

    private void BGuardarSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarSeccionActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nombre de seccion de direcciones");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                Admininistrador_De_Guardado<Seccion> A = new Admininistrador_De_Guardado(DIRECCION_ADMINISTRADOR_SECCION);
                A.agregarInformacion(nuevoNombre, EA.getSeccion());
                A.guardarAdminastrador(DIRECCION_ADMINISTRADOR_SECCION);
                // visualizarVentanaAdministradorDirecciones();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BGuardarSeccionActionPerformed

    private void BAdministradorSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAdministradorSeccionActionPerformed
        visualizarVentanaAdministradorSeccion();
    }//GEN-LAST:event_BAdministradorSeccionActionPerformed

    private void BCopiarSubtitulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCopiarSubtitulosActionPerformed
        try {
            copiarSubtitulos(false);
            C.actualizarSerie(EA, cdv);
            C.actualizarSubtitulos(EA, cdv);
            actualizarT();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BCopiarSubtitulosActionPerformed

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

    private void cerrarVentana(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_cerrarVentana
        alCerrar(this);
    }//GEN-LAST:event_cerrarVentana

    private void BAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAtrasActionPerformed
        Visual.maximizarVentana(todo.Todo.ventanaTodo);
        setVisible(false);
    }//GEN-LAST:event_BAtrasActionPerformed

    private void apretoT(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apretoT
        Visual.señalarJTextTarea(evt, cdv);
    }//GEN-LAST:event_apretoT

    private void apretoTeclaTablaSubtitulos(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apretoTeclaTablaSubtitulos
        if (Visual.realizarDeleteTabla(evt, BEliminarSubtitulosBase)) {
            menosSubtitulo();
        }
    }//GEN-LAST:event_apretoTeclaTablaSubtitulos

    private void apretoTeclaTablaSeries(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apretoTeclaTablaSeries
        if (Visual.realizarDeleteTabla(evt, BEliminarDireccionBase)) {
            menosSerie();
        }
    }//GEN-LAST:event_apretoTeclaTablaSeries

    private void apretoTeclaTablaInvalidos(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apretoTeclaTablaInvalidos
        if (Visual.realizarDeleteTabla(evt, BEliminarInvalidoBase)) {
            menosInvalidos();
        }       // TODO add your handling code here:
    }//GEN-LAST:event_apretoTeclaTablaInvalidos
    public void visualizarVentanaConfiguracionDeVideo() {
        try {
            if (ventana_ConfiguracionDeVideo == null) {
                ventana_ConfiguracionDeVideo = new Ventana_ConfiguracionDeVideo(EA.getT(), DIRECCION_ADMINISTRADOR_CONFIGURACION_DE_VIDEO, new Accion_Cargar_Cancelar() {

                    @Override
                    public void aceptar_Y_cargar() {
                        EA.setT(ventana_ConfiguracionDeVideo.getTOriginal());
                        cdv = EA.getT().getCdvSelecionado();
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
            java.util.logging.Logger.getLogger(Ventana_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BActualizar;
    private javax.swing.JButton BAdministradorDireccionPaquete;
    private javax.swing.JButton BAdministradorSeccion;
    private javax.swing.JButton BAgregarDireccionPaquete;
    private javax.swing.JButton BAgregarInvalidoBase;
    private javax.swing.JButton BAgregarSeccion;
    private javax.swing.JButton BAtras;
    private javax.swing.JButton BBuscarDireccionBase;
    private javax.swing.JButton BBuscarSubtitulosBase;
    private javax.swing.JButton BCargarPaquete;
    private javax.swing.JButton BClearDireccionBase;
    private javax.swing.JButton BClearDireccionPaquete;
    private javax.swing.JButton BClearInvalidosBase;
    private javax.swing.JButton BClearSubtitulosBase;
    private javax.swing.JButton BConfiguracion;
    private javax.swing.JButton BCopiarSubtitulos;
    private javax.swing.JButton BDireccionesBaseAdministrador;
    private javax.swing.JButton BEliminarDireccionBase;
    private javax.swing.JButton BEliminarInvalidoBase;
    private javax.swing.JButton BEliminarSubtitulosBase;
    private javax.swing.JButton BGuardarDireccionBase;
    private javax.swing.JButton BGuardarDireccionPaquete;
    private javax.swing.JButton BGuardarInvalidosBase;
    private javax.swing.JButton BGuardarSeccion;
    private javax.swing.JButton BGuardarSubtitulosBase;
    private javax.swing.JButton BInvalidoBaseAdministrador;
    private javax.swing.JButton BSeñalarTDireccionesBase;
    private javax.swing.JButton BSeñalarTInvalidosBase;
    private javax.swing.JButton BSeñalarTSubtitulosBase;
    private javax.swing.JButton BSubtitulosBaseAdministrador;
    private javax.swing.JCheckBox CBSinCarpetasBase;
    private javax.swing.JCheckBox CBSinCarpetasSubtitulos;
    private javax.swing.JTable JTDireccionesBase;
    private javax.swing.JTable JTInvalidosBase;
    private javax.swing.JTable JTSubtitulosBase;
    private javax.swing.JLabel LDireccionDelPaquete1;
    private javax.swing.JPanel PDireccionesBase;
    private javax.swing.JPanel PInvalidosBase;
    private javax.swing.JPanel PSubtitulosBase;
    private javax.swing.JPanel PTodo;
    private javax.swing.JScrollPane SPDireccionesBase;
    private javax.swing.JScrollPane SPInvalidosBase;
    private javax.swing.JScrollPane SPSubtitulosBase;
    private javax.swing.JTextArea T;
    private javax.swing.JTextField TFDireccionPaquete;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
