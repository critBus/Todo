/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.editor_de_texto;

import Utiles.ClasesUtiles.Admininistrador.Admininistrador_De_Guardado;
import Utiles.ClasesUtiles.Admininistrador.Ventana_Administrador;
import static Utiles.ClasesUtiles.Admininistrador.Ventana_Administrador.EXTENCION;
import Utiles.ClasesUtiles.Interfases.Accion_Cargar_Cancelar;
import Utiles.MetodosUtiles.Archivo;
import static Utiles.MetodosUtiles.Archivo.crearArchivo;
import javax.swing.JFileChooser;
import static Utiles.MetodosUtiles.Visual.*;
import static Utiles.MetodosUtiles.MetodosUtiles.*;
import Utiles.MetodosUtiles.Visual;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
//import java.util.concurrent.ExecutionException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static todo.Todo.*;

/**
 *
 * @author Rene
 */
public class Ventana_Principal extends javax.swing.JFrame {

    private final String CARPETA = "Data/Editor de Texto/",
            DIRECCION_ADMINISTRADOR_DIRECCIONES_TXT = CARPETA + "Administrador de direcciones TXT" + EXTENCION;
     private JFileChooser jf;
    JFileChooser FC = new JFileChooser();
    Editor E;
    public static Ventana_Administrador<File> ventana_Administrador_Direcciones_TXT;

    /**
     * Creates new form Ventana_Principal
     */
    public Ventana_Principal() throws IOException {
        super("Editor de Texto");
        initComponents();
        E = new Editor(T, true);
        BG1.add(RLineas);
        BG1.add(RPalabras);
        BG1.add(RPalabrasEnLineas);
        BG1.add(RPalabrasYLineas);
        if(!new File(CARPETA).exists()){
            new File(CARPETA).mkdirs();
        }
        if (!new File(DIRECCION_ADMINISTRADOR_DIRECCIONES_TXT).exists()) {
            crearArchivo(DIRECCION_ADMINISTRADOR_DIRECCIONES_TXT, new Admininistrador_De_Guardado<File>());
        }
         jf = new JFileChooser();
            jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        setLocationRelativeTo(null);
        actualizarStringDeBActualizarEspacios();
    }
public void visualizarVentanaAdministradorTXT() {
        try {
            if (ventana_Administrador_Direcciones_TXT == null) {
                ventana_Administrador_Direcciones_TXT = new Ventana_Administrador(DIRECCION_ADMINISTRADOR_DIRECCIONES_TXT, new Accion_Cargar_Cancelar() {

                    @Override
                    public void aceptar_Y_cargar() {
                        TFDireccionTXT.setText(ventana_Administrador_Direcciones_TXT.getInformacionSelecionada().toString());
                        ventana_Administrador_Direcciones_TXT.setVisible(false);
                        setVisible(true);
                    }

                    @Override
                    public void cancelar() {
                        ventana_Administrador_Direcciones_TXT.setVisible(false);
                        setVisible(true);
                    }
                });
            } else {
                ventana_Administrador_Direcciones_TXT.setDireccionAdministrador(DIRECCION_ADMINISTRADOR_DIRECCIONES_TXT);
            }
            ventana_Administrador_Direcciones_TXT.setVisible(true);
            setVisible(false);
        } catch (Exception ex) {
            responerException(ex);
        }
    }
    void responderExeption() {
        JOptionPane.showMessageDialog(Ventana_Principal.this, "Error", "No se pudo cargar el archibo", WIDTH);
    }

    void inicializarTexto() throws IOException {
        leerFileJTextArea(T, FC.getSelectedFile());
        activarBotones(true);
//        activarBotones();
        E = new Editor(T, CPrioridadNumerica.isSelected());
        LDirecion.setText(FC.getSelectedFile().getAbsolutePath());
    }

    void activarBotones(boolean activar) {
        Visual.activarJComponent(activar, BGuardar,
                BOrdenarAlfabeticamente,
                BRevertirOrden,
                BSepararEnLineas,
                BSepararEnPalabras,
                BLimpiar,
                RLineas,
                RPalabras,
                RLineas,
                RPalabrasEnLineas,
                RPalabrasYLineas, BActualizarEspacios, BMayusculasMinusculas, BComenzarConMayusculas);

//        BGuardar.setEnabled(true);
//        BOrdenarAlfabeticamente.setEnabled(true);
//        BRevertirOrden.setEnabled(true);
//        BSepararEnLineas.setEnabled(true);
//        BSepararEnPalabras.setEnabled(true);
//        BLimpiar.setEnabled(true);
//        RLineas.setEnabled(true);
//        RPalabras.setEnabled(true);
//        RLineas.setSelected(true);
//        RPalabrasEnLineas.setEnabled(true);
//        RPalabrasYLineas.setEnabled(true);
    }

//    void desactivarBotones() {
//        
//        BGuardar.setEnabled(false);
//        BRestaurar.setEnabled(false);
//        BOrdenarAlfabeticamente.setEnabled(false);
//        BRevertirOrden.setEnabled(false);
//        BSepararEnLineas.setEnabled(false);
//        BSepararEnPalabras.setEnabled(false);
//        BLimpiar.setEnabled(false);
//        RLineas.setEnabled(false);
//        RPalabras.setEnabled(false);
//        RLineas.setSelected(false);
//        RPalabrasEnLineas.setEnabled(false);
//        RPalabrasYLineas.setEnabled(false);
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void seguridad() {
//        Visual.activarJComponent(!EA.getDirecciones_De_Carpetas().getDirecciones_Carpetas().isEmpty(), BClearDireccionBase, BEliminarDireccionBase, BGuardarDireccionBase, BSeñalarTDireccionesBase);
        Visual.seguridadJTextField_File_Directorio(TFDireccionTXT, BClearDireccionTXT, BGuardarDireccionTXT);
        Visual.activarJComponent(BClearDireccionTXT.isEnabled() && !T.getText().isEmpty(), BTXTBase);
//        Visual.activarJComponent( !C.isEmpty(), BOrdenar);

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BG1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        PPricipal = new javax.swing.JPanel();
        LDirecion = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        T = new javax.swing.JTextArea();
        RLineas = new javax.swing.JRadioButton();
        RPalabras = new javax.swing.JRadioButton();
        LFormato = new javax.swing.JLabel();
        BCargar = new javax.swing.JButton();
        BRestaurar = new javax.swing.JButton();
        BGuardar = new javax.swing.JButton();
        BOrdenarAlfabeticamente = new javax.swing.JButton();
        BRevertirOrden = new javax.swing.JButton();
        RPalabrasEnLineas = new javax.swing.JRadioButton();
        BSepararEnLineas = new javax.swing.JButton();
        BSepararEnPalabras = new javax.swing.JButton();
        RPalabrasYLineas = new javax.swing.JRadioButton();
        BLimpiar = new javax.swing.JButton();
        CPrioridadNumerica = new javax.swing.JCheckBox();
        BAtras = new javax.swing.JButton();
        BMayusculasMinusculas = new javax.swing.JButton();
        BComenzarConMayusculas = new javax.swing.JButton();
        CBGuionBajo = new javax.swing.JCheckBox();
        BActualizarEspacios = new javax.swing.JButton();
        TFDireccionTXT = new javax.swing.JTextField();
        BClearDireccionTXT = new javax.swing.JButton();
        BGuardarDireccionTXT = new javax.swing.JButton();
        BAdministradorDireccionTXT = new javax.swing.JButton();
        BAgregarDireccionTXT = new javax.swing.JButton();
        BTXTBase = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editor de Texto");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                cerroVentana(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PPricipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LDirecion.setText("Carge o escriba un texto");
        PPricipal.add(LDirecion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        T.setColumns(20);
        T.setRows(5);
        T.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Escribir(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Soltar(evt);
            }
        });
        jScrollPane2.setViewportView(T);

        PPricipal.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 560, 320));

        RLineas.setSelected(true);
        RLineas.setText("por Lineas");
        RLineas.setEnabled(false);
        RLineas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precionarFormato(evt);
            }
        });
        PPricipal.add(RLineas, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 50, -1, -1));

        RPalabras.setText("por Palabras");
        RPalabras.setEnabled(false);
        RPalabras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precionarFormato(evt);
            }
        });
        PPricipal.add(RPalabras, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 80, -1, -1));

        LFormato.setText("Formato");
        PPricipal.add(LFormato, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, -1, -1));

        BCargar.setText("Cargar");
        BCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCargarActionPerformed(evt);
            }
        });
        PPricipal.add(BCargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 190, 80, -1));

        BRestaurar.setText("Restaurar");
        BRestaurar.setEnabled(false);
        BRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BRestaurarActionPerformed(evt);
            }
        });
        PPricipal.add(BRestaurar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 230, -1, -1));

        BGuardar.setText("Guardar");
        BGuardar.setEnabled(false);
        BGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarActionPerformed(evt);
            }
        });
        PPricipal.add(BGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 270, 80, -1));

        BOrdenarAlfabeticamente.setText("Ordenar alfabeticamente");
        BOrdenarAlfabeticamente.setEnabled(false);
        BOrdenarAlfabeticamente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BOrdenarAlfabeticamenteActionPerformed(evt);
            }
        });
        PPricipal.add(BOrdenarAlfabeticamente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, -1));

        BRevertirOrden.setText("Revertir el orden acual");
        BRevertirOrden.setEnabled(false);
        BRevertirOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BRevertirOrdenActionPerformed(evt);
            }
        });
        PPricipal.add(BRevertirOrden, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 160, -1));

        RPalabrasEnLineas.setText("Palabras en Linea");
        RPalabrasEnLineas.setEnabled(false);
        RPalabrasEnLineas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precionarFormato(evt);
            }
        });
        PPricipal.add(RPalabrasEnLineas, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 110, -1, -1));

        BSepararEnLineas.setText("Separar las palabras en lineas");
        BSepararEnLineas.setEnabled(false);
        BSepararEnLineas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSepararEnLineasActionPerformed(evt);
            }
        });
        PPricipal.add(BSepararEnLineas, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, -1, -1));

        BSepararEnPalabras.setText("Separar las lineas en palabras");
        BSepararEnPalabras.setEnabled(false);
        BSepararEnPalabras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSepararEnPalabrasActionPerformed(evt);
            }
        });
        PPricipal.add(BSepararEnPalabras, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 410, -1, -1));

        RPalabrasYLineas.setText("Palbras y lineas");
        RPalabrasYLineas.setEnabled(false);
        RPalabrasYLineas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precionarFormato(evt);
            }
        });
        PPricipal.add(RPalabrasYLineas, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, -1, -1));

        BLimpiar.setText("Limpiar");
        BLimpiar.setEnabled(false);
        BLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLimpiarActionPerformed(evt);
            }
        });
        PPricipal.add(BLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 310, 80, -1));

        CPrioridadNumerica.setText("Prioridad Numerica");
        PPricipal.add(CPrioridadNumerica, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 350, -1, -1));

        BAtras.setText("Atras");
        BAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAtrasActionPerformed(evt);
            }
        });
        PPricipal.add(BAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 420, 80, -1));

        BMayusculasMinusculas.setText("Mayusculas Minusculas");
        BMayusculasMinusculas.setEnabled(false);
        BMayusculasMinusculas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BMayusculasMinusculasActionPerformed(evt);
            }
        });
        PPricipal.add(BMayusculasMinusculas, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 370, 170, -1));

        BComenzarConMayusculas.setText("Comenzar con Mayus");
        BComenzarConMayusculas.setEnabled(false);
        BComenzarConMayusculas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BComenzarConMayusculasActionPerformed(evt);
            }
        });
        PPricipal.add(BComenzarConMayusculas, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 410, 170, -1));

        CBGuionBajo.setText("\" _ \"");
        CBGuionBajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBGuionBajoActionPerformed(evt);
            }
        });
        PPricipal.add(CBGuionBajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 380, -1, -1));

        BActualizarEspacios.setText("\" _ \"");
        BActualizarEspacios.setEnabled(false);
        BActualizarEspacios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActualizarEspaciosActionPerformed(evt);
            }
        });
        PPricipal.add(BActualizarEspacios, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 380, -1, -1));

        TFDireccionTXT.setToolTipText("Direccion donde se creara el TXT ");
        TFDireccionTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                escribioTFDireccionTXT(evt);
            }
        });
        PPricipal.add(TFDireccionTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 560, 25));

        BClearDireccionTXT.setText("C");
        BClearDireccionTXT.setToolTipText("Limpia la direccion");
        BClearDireccionTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BClearDireccionTXTActionPerformed(evt);
            }
        });
        PPricipal.add(BClearDireccionTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 450, -1, -1));

        BGuardarDireccionTXT.setText("G");
        BGuardarDireccionTXT.setToolTipText("Guarda la direccion en el administrador dedirecciones");
        BGuardarDireccionTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarDireccionTXTActionPerformed(evt);
            }
        });
        PPricipal.add(BGuardarDireccionTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 450, -1, -1));

        BAdministradorDireccionTXT.setText("^");
        BAdministradorDireccionTXT.setToolTipText("Abre la ventana del administrador de direcciones");
        BAdministradorDireccionTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAdministradorDireccionTXTActionPerformed(evt);
            }
        });
        PPricipal.add(BAdministradorDireccionTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, -1, -1));

        BAgregarDireccionTXT.setText("+");
        BAgregarDireccionTXT.setToolTipText("busca una direccion de una carpeta");
        BAgregarDireccionTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAgregarDireccionTXTActionPerformed(evt);
            }
        });
        PPricipal.add(BAgregarDireccionTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, -1, -1));

        BTXTBase.setText("TXT");
        BTXTBase.setToolTipText("Almacena en TXT el contenido del cuadro de texto de la base");
        BTXTBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTXTBaseActionPerformed(evt);
            }
        });
        PPricipal.add(BTXTBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 450, -1, -1));

        jScrollPane1.setViewportView(PPricipal);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Escribir(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Escribir


    }//GEN-LAST:event_Escribir

    private void BRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BRestaurarActionPerformed
        try {
            inicializarTexto();
        } catch (IOException ex) {
            responderExeption();
        }
    }//GEN-LAST:event_BRestaurarActionPerformed

    private void BCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCargarActionPerformed
        try {
            FC.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (FC.showOpenDialog(Ventana_Principal.this) != JFileChooser.CANCEL_OPTION) {
                inicializarTexto();
                BRestaurar.setEnabled(true);
            }
        } catch (IOException ex) {
            responderExeption();
        }
    }//GEN-LAST:event_BCargarActionPerformed

    private void BGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarActionPerformed
        try {
            FC.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (FC.showOpenDialog(Ventana_Principal.this) != JFileChooser.CANCEL_OPTION) {
                BRestaurar.setEnabled(true);
                guardarEnFileJTextArea(T, FC.getSelectedFile(), ".txt");
            }
        } catch (IOException ex) {
            responderExeption();
        }
    }//GEN-LAST:event_BGuardarActionPerformed

    private void BOrdenarAlfabeticamenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BOrdenarAlfabeticamenteActionPerformed
//VVVVVVVVVVV   
//        if (RPalabrasEnLineas.isSelected()) {
//            System.out.println("aqui");
//            E.organizarLasPalabrasEnLineas();
//            E.ordenarAlfabeticamenteLasLineas(false);
//            T.setText(E.getT().getText());
//        } else {
        //VVVVVVVVVVVVV 
        //nnnnnnnnnnnnnnnnn
        E.setPrioridadNumerica(CPrioridadNumerica.isSelected());
        E.limpiarLineas();
        if (RPalabrasEnLineas.isSelected()) {
            E.ordenarAlfabeticamenteLasPalbrasEnLasLineas();
            T.setText(E.getT().getText());
            //System.out.println("uno uno");
        }
        if (RPalabrasYLineas.isSelected()) {
            E.ordenarAlfabeticamenteLasPalbrasEnLasLineasYLasLineas();
            T.setText(E.getT().getText());
            // System.out.println("dos dos");
        }
        //nnnnnnnnnnnnnnnnnnnnn
        if (RLineas.isSelected()) {
            E.ordenarAlfabeticamenteLasLineas(false);
            T.setText(E.getT().getText());
            //System.out.println("uno");
        }
        if (RPalabras.isSelected()) {
            E.ordenarAlfabeticamenteLasPalabras(false);
            T.setText(E.getT().getText());
            //System.out.println("dos");
        }
        //}
    }//GEN-LAST:event_BOrdenarAlfabeticamenteActionPerformed

    private void BRevertirOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BRevertirOrdenActionPerformed
        //VVVV
//        if (RPalabrasEnLineas.isSelected()) {
//            //System.out.println("aqui");
//            E.organizarLasPalabrasEnLineas();
//            E.reverseLineas();
//            T.setText(E.getT().getText());
//        } else {
        //vvvvvvvvvvvvvv
        //nnnnnnnnnnnnnnnnnn
        E.limpiarLineas();
        if (RPalabrasEnLineas.isSelected()) {
            E.reversePalabrasEnLineas();
            T.setText(E.getT().getText());
        }
        if (RPalabrasYLineas.isSelected()) {
            E.reversePalabrasEnLineasYLineas();
            T.setText(E.getT().getText());
        }
        //nnnnnnnnnnn
        if (RLineas.isSelected()) {
            E.reverseLineas();
            T.setText(E.getT().getText());
            // System.out.println("uno");
        } else {
            E.reversePalabras();
            T.setText(E.getT().getText());
            // System.out.println("dos");
            //}
        }

//        if (RLineas.isSelected()) {
//            E.reverseLineas();
//            //T=E.getT();
//            System.out.println("tres");
//        } else {
//            E.reversePalabras();
//            // T=E.getT();
//            System.out.println("cuatro");
//        }
    }//GEN-LAST:event_BRevertirOrdenActionPerformed

    private void Soltar(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Soltar
        if (StringRealmenteVacio(T.getText())) {
//            desactivarBotones();
            activarBotones(false);
        } else if (!BGuardar.isEnabled()) {
//            activarBotones();
            activarBotones(true);
        }
        E = new Editor(T, CPrioridadNumerica.isSelected());
    }//GEN-LAST:event_Soltar

    private void precionarFormato(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precionarFormato
        if (evt.getSource() == RPalabrasYLineas) {
            E.setFormato(Formato.PALABRAS_Y_LINEAS);
        }
        if (evt.getSource() == RPalabrasEnLineas) {
            E.setFormato(Formato.PALABRAS_EN_LINEAS);
        }
        if (evt.getSource() == RLineas) {
            E.setFormato(Formato.LINEAS);
        }
        if (evt.getSource() == RPalabras) {
            E.setFormato(Formato.PALABRAS);
        }


    }//GEN-LAST:event_precionarFormato

    private void BSepararEnLineasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSepararEnLineasActionPerformed
        E.organizarLasPalabrasEnLineas();
        T.setText(E.getT().getText());
    }//GEN-LAST:event_BSepararEnLineasActionPerformed

    private void BSepararEnPalabrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSepararEnPalabrasActionPerformed
        E.organizarLasLineasEnPalabras();
        T.setText(E.getT().getText());
    }//GEN-LAST:event_BSepararEnPalabrasActionPerformed

    private void BLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLimpiarActionPerformed
//        desactivarBotones();
        activarBotones(true);
        T.setText("");
        E = new Editor(T, CPrioridadNumerica.isSelected());
        LDirecion.setText("Carge o escriba un texto");
    }//GEN-LAST:event_BLimpiarActionPerformed

    private void BAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAtrasActionPerformed
        maximizarVentana(ventanaTodo);
        setVisible(false);
    }//GEN-LAST:event_BAtrasActionPerformed

    private void cerroVentana(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_cerroVentana
        alCerrar(this);
    }//GEN-LAST:event_cerroVentana

    private void BMayusculasMinusculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BMayusculasMinusculasActionPerformed
        E.limpiarLineas();
        E.Mayusculas_Minusculas();
        T.setText(E.getT().getText());
    }//GEN-LAST:event_BMayusculasMinusculasActionPerformed

    private void BComenzarConMayusculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BComenzarConMayusculasActionPerformed
        E.limpiarLineas();
        E.comienzaConMayuscula();
        T.setText(E.getT().getText());
    }//GEN-LAST:event_BComenzarConMayusculasActionPerformed

    private void CBGuionBajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBGuionBajoActionPerformed
        E.setSeparadorSpacio(!CBGuionBajo.isSelected());
        actualizarStringDeBActualizarEspacios();
    }//GEN-LAST:event_CBGuionBajoActionPerformed

    private void BActualizarEspaciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BActualizarEspaciosActionPerformed
        E.limpiarLineas();
        E.actualizarJTextArea();
        T.setText(E.getT().getText());
    }//GEN-LAST:event_BActualizarEspaciosActionPerformed

    private void escribioTFDireccionTXT(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_escribioTFDireccionTXT
//        EA.setDireccionTXT(new File(TFDireccionTXT.getText()));
        seguridad();
    }//GEN-LAST:event_escribioTFDireccionTXT

    private void BClearDireccionTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BClearDireccionTXTActionPerformed
        TFDireccionTXT.setText("");
    }//GEN-LAST:event_BClearDireccionTXTActionPerformed

    private void BGuardarDireccionTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarDireccionTXTActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de las direccione del TXT");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                Admininistrador_De_Guardado<File> A = new Admininistrador_De_Guardado(DIRECCION_ADMINISTRADOR_DIRECCIONES_TXT);
                A.agregarInformacion(nuevoNombre, new File(TFDireccionTXT.getText()));
                A.guardarAdminastrador(DIRECCION_ADMINISTRADOR_DIRECCIONES_TXT);
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BGuardarDireccionTXTActionPerformed

    private void BAdministradorDireccionTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAdministradorDireccionTXTActionPerformed
        visualizarVentanaAdministradorTXT();
    }//GEN-LAST:event_BAdministradorDireccionTXTActionPerformed

    private void BAgregarDireccionTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAgregarDireccionTXTActionPerformed
        if (Visual.buscador(jf, this)) {
            try {
                setFileEnTF_TXT(jf.getSelectedFile());
                seguridad();
            } catch (Exception ex) {
                responerException(ex);
            }

        }
    }//GEN-LAST:event_BAgregarDireccionTXTActionPerformed

    private void BTXTBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTXTBaseActionPerformed
        try {
            String nuevoNombre = JOptionPane.showInputDialog("Escriba el nombre del TXT");
            if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                Visual.guardarEnTXTJTextArea(T, new File(TFDireccionTXT.getText()), nuevoNombre);
//                Archivo.crearTXT(new File(TFDireccionTXT.getText()), nuevoNombre, C.getLineasTXTTabla());
                //                Visual.guardarEnTXTJTextArea(TBase, new File(TFDireccionTXT.getText()), nuevoNombre);
            }
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BTXTBaseActionPerformed
public void setFileEnTF_TXT(File f) {
        TFDireccionTXT.setText(f.toString());
    }
    private void actualizarStringDeBActualizarEspacios() {
        BActualizarEspacios.setText(CBGuionBajo.isSelected() ? "\" _ \"" : "\"  \"");
    }

    public void alCerrar(JFrame f) {
        cerrar(f, 2);
    }
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Ventana_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Ventana_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Ventana_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Ventana_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Ventana_Principal().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BActualizarEspacios;
    private javax.swing.JButton BAdministradorDireccionTXT;
    private javax.swing.JButton BAgregarDireccionTXT;
    private javax.swing.JButton BAtras;
    private javax.swing.JButton BCargar;
    private javax.swing.JButton BClearDireccionTXT;
    private javax.swing.JButton BComenzarConMayusculas;
    private javax.swing.ButtonGroup BG1;
    private javax.swing.JButton BGuardar;
    private javax.swing.JButton BGuardarDireccionTXT;
    private javax.swing.JButton BLimpiar;
    private javax.swing.JButton BMayusculasMinusculas;
    private javax.swing.JButton BOrdenarAlfabeticamente;
    private javax.swing.JButton BRestaurar;
    private javax.swing.JButton BRevertirOrden;
    private javax.swing.JButton BSepararEnLineas;
    private javax.swing.JButton BSepararEnPalabras;
    private javax.swing.JButton BTXTBase;
    private javax.swing.JCheckBox CBGuionBajo;
    private javax.swing.JCheckBox CPrioridadNumerica;
    private javax.swing.JLabel LDirecion;
    private javax.swing.JLabel LFormato;
    private javax.swing.JPanel PPricipal;
    private javax.swing.JRadioButton RLineas;
    private javax.swing.JRadioButton RPalabras;
    private javax.swing.JRadioButton RPalabrasEnLineas;
    private javax.swing.JRadioButton RPalabrasYLineas;
    private javax.swing.JTextArea T;
    private javax.swing.JTextField TFDireccionTXT;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
