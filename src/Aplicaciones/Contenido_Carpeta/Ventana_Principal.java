/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.Contenido_Carpeta;

import Utiles.ClasesUtiles.Admininistrador.Admininistrador_De_Guardado;
import Utiles.ClasesUtiles.Admininistrador.Ventana_Administrador;
import static Utiles.ClasesUtiles.Admininistrador.Ventana_Administrador.EXTENCION;
import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeTabla;
import Utiles.ClasesUtiles.Interfases.Accion_Cargar_Cancelar;
import Utiles.ClasesUtiles.Interfases.IsSeleccionado;
import Utiles.ClasesUtiles.Interfases.editingTabla;
import Utiles.ClasesUtiles.Interfases.selectionFila;
import Utiles.MetodosUtiles.Archivo;
import static Utiles.MetodosUtiles.Archivo.crearArchivo;
import Utiles.MetodosUtiles.Visual;
import static Utiles.MetodosUtiles.Visual.maximizarVentana;
import static Utiles.MetodosUtiles.Visual.responerException;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import static todo.Todo.cerrar;
import static todo.Todo.ventanaTodo;

/**
 *
 * @author Rene
 */
public class Ventana_Principal extends javax.swing.JFrame {

    private final String CARPETA = "Data/Contenido de Carpeta/", DIRECCION_ULTIMO_ESTADO = CARPETA + "Estado Actual contenido de carpeta.est", DIRECCION_ADMINISTRADOR_DIRECCIONES = CARPETA + "Administrador de direcciones" + EXTENCION,
            DIRECCION_ADMINISTRADOR_DIRECCIONES_TXT = CARPETA + "Administrador de direcciones TXT" + EXTENCION;
    public static char CHAR_MARCAR = '◙', CHAR_DESMARCAR = '○';
    private final int ANCHO_SELECT = 40;
    private int baseDireccionSelecionada, entradaDireccionSelecionada, baseInvalidoSeleccionada, entradaInvalidoSeleccionada, personalizadoTablaSeleccionado, baseSeleccionada = 0, personalizadoBaseCBSeleccionado = 0, tablaCarpetaSeleccionada, personalizadoEntradaCBSeleccionado;
    private Estado_Actual EA;
    private JFileChooser jf;
    private Carpetas C;
    public static Ventana_Administrador<LinkedList<Direccion_Carpetas>> ventana_Administrador_Drecciones_Carpetas;
    public static Ventana_Administrador<File> ventana_Administrador_Direcciones_TXT;
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
            } catch (Exception exx) {
                System.out.println("errrrrrrrrrro");
                exx.printStackTrace();
                crearArchivo(DIRECCION_ULTIMO_ESTADO, new Estado_Actual());
                EA = (Estado_Actual) Archivo.cargarArchivo(DIRECCION_ULTIMO_ESTADO);
                crearArchivo(DIRECCION_ADMINISTRADOR_DIRECCIONES, new Admininistrador_De_Guardado<LinkedList<Direccion_Carpetas>>());
                crearArchivo(DIRECCION_ADMINISTRADOR_DIRECCIONES_TXT, new Admininistrador_De_Guardado<File>());

            }
            if (!new File(DIRECCION_ADMINISTRADOR_DIRECCIONES).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_DIRECCIONES, new Admininistrador_De_Guardado<LinkedList<Direccion_Carpetas>>());
            }
            if (!new File(DIRECCION_ADMINISTRADOR_DIRECCIONES_TXT).exists()) {
                crearArchivo(DIRECCION_ADMINISTRADOR_DIRECCIONES_TXT, new Admininistrador_De_Guardado<File>());
            }
            jf = new JFileChooser();
            jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            baseDireccionSelecionada = entradaDireccionSelecionada = baseInvalidoSeleccionada = entradaInvalidoSeleccionada = personalizadoTablaSeleccionado
                    = tablaCarpetaSeleccionada = personalizadoEntradaCBSeleccionado = 0;

            TFDireccionTXT.setText(EA.getDireccionTXT().toString());
//            System.out.println("a");
            actualizarTablaDireccion();
//            System.out.println("b");
            actualizarMarcarDirecciones();
//            System.out.println("c");
            actualizarTablaCarpetas();
//            System.out.println("d");
            seguridad();

            setLocationRelativeTo(null);
            ventana_Principal = this;
            ventanas = new JFrame[]{this, ventana_Administrador_Direcciones_TXT, ventana_Administrador_Drecciones_Carpetas};
        } catch (Exception ex) {
            responerException(ex);
        }
//        Carpetas c = new Carpetas(new File("G:\\Manga\\Finalizadas"), new String[]{});
    }

    public void seguridad() {
        Visual.activarJComponent(!EA.getDirecciones_De_Carpetas().getDirecciones_Carpetas().isEmpty(), BClearDireccionBase, BEliminarDireccionBase, BGuardarDireccionBase, BSeñalarTDireccionesBase);
        Visual.seguridadJTextField_File_Directorio(TFDireccionTXT, BClearDireccionTXT, BGuardarDireccionTXT);
        Visual.activarJComponent(BClearDireccionTXT.isEnabled() && !C.isEmpty(), BTXTBase, BTXTSoloContenido);
//        Visual.activarJComponent( !C.isEmpty(), BOrdenar);

    }

    public void alCerrar(JFrame f) {
        cerrar(f, 8);

    }

    public void menosDireccionBase() {
        try {
            eliminarDireccionBase();
            actualizarTablaDireccion();
            actualizarMarcarDirecciones();
            actualizarTablaCarpetas();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }

//     try {
//            actualizarTablaCarpetas();
//        } catch (Exception ex) {
//            responerException(ex);
//        }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BGOrdenar = new javax.swing.ButtonGroup();
        BGAscendente = new javax.swing.ButtonGroup();
        PTodo = new javax.swing.JPanel();
        SPDireccionesBase = new javax.swing.JScrollPane();
        PDireccionesBase = new javax.swing.JPanel();
        JTDireccionesBase = new javax.swing.JTable();
        BClearDireccionTXT = new javax.swing.JButton();
        BGuardarDireccionTXT = new javax.swing.JButton();
        BAdministradorDireccionTXT = new javax.swing.JButton();
        BAgregarDireccionTXT = new javax.swing.JButton();
        TFDireccionTXT = new javax.swing.JTextField();
        BDireccionesBaseAdministrador = new javax.swing.JButton();
        BClearDireccionBase = new javax.swing.JButton();
        BEliminarDireccionBase = new javax.swing.JButton();
        BBuscarDireccionBase = new javax.swing.JButton();
        BSeñalarTDireccionesBase = new javax.swing.JButton();
        BGuardarDireccionBase = new javax.swing.JButton();
        BTXTBase = new javax.swing.JButton();
        SPTablaCarpetas = new javax.swing.JScrollPane();
        PTablaCarpetas = new javax.swing.JPanel();
        JTTablaCarpetas = new javax.swing.JTable();
        RAlfabeticamente = new javax.swing.JRadioButton();
        BActualizar = new javax.swing.JButton();
        BTXTSoloContenido = new javax.swing.JButton();
        BAtras = new javax.swing.JButton();
        RAscendente = new javax.swing.JRadioButton();
        RDescendente = new javax.swing.JRadioButton();
        RTamaño = new javax.swing.JRadioButton();
        RTamCap = new javax.swing.JRadioButton();
        RCantidadDeVideos = new javax.swing.JRadioButton();

        BGOrdenar.add(RAlfabeticamente);
        BGOrdenar.add(RCantidadDeVideos);
        BGOrdenar.add(RTamCap);
        BGOrdenar.add(RTamaño);

        BGAscendente.add(RAscendente);
        BGAscendente.add(RDescendente);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Contenido de Carpetas");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                cerrandoVentana(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                cerroVentana(evt);
            }
        });

        PTodo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
                apretoTeclaTablaDirecciones(evt);
            }
        });
        PDireccionesBase.add(JTDireccionesBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 120));

        SPDireccionesBase.setViewportView(PDireccionesBase);

        PTodo.add(SPDireccionesBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 760, 100));

        BClearDireccionTXT.setText("C");
        BClearDireccionTXT.setToolTipText("Limpia la direccion");
        BClearDireccionTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BClearDireccionTXTActionPerformed(evt);
            }
        });
        PTodo.add(BClearDireccionTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 550, -1, -1));

        BGuardarDireccionTXT.setText("G");
        BGuardarDireccionTXT.setToolTipText("Guarda la direccion en el administrador dedirecciones");
        BGuardarDireccionTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarDireccionTXTActionPerformed(evt);
            }
        });
        PTodo.add(BGuardarDireccionTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 550, -1, -1));

        BAdministradorDireccionTXT.setText("^");
        BAdministradorDireccionTXT.setToolTipText("Abre la ventana del administrador de direcciones");
        BAdministradorDireccionTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAdministradorDireccionTXTActionPerformed(evt);
            }
        });
        PTodo.add(BAdministradorDireccionTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 550, -1, -1));

        BAgregarDireccionTXT.setText("+");
        BAgregarDireccionTXT.setToolTipText("busca una direccion de una carpeta");
        BAgregarDireccionTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAgregarDireccionTXTActionPerformed(evt);
            }
        });
        PTodo.add(BAgregarDireccionTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, -1, -1));

        TFDireccionTXT.setToolTipText("Direccion donde se creara el TXT");
        TFDireccionTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                escribioTFDireccionTXT(evt);
            }
        });
        PTodo.add(TFDireccionTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, 760, -1));

        BDireccionesBaseAdministrador.setText("^");
        BDireccionesBaseAdministrador.setToolTipText("Abre la ventana del administrador de direcciones");
        BDireccionesBaseAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDireccionesBaseAdministradorActionPerformed(evt);
            }
        });
        PTodo.add(BDireccionesBaseAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, 50, -1));

        BClearDireccionBase.setText("C");
        BClearDireccionBase.setToolTipText("Elimina todas las direcciones");
        BClearDireccionBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BClearDireccionBaseActionPerformed(evt);
            }
        });
        PTodo.add(BClearDireccionBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, -1, -1));

        BEliminarDireccionBase.setText("-");
        BEliminarDireccionBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEliminarDireccionBaseActionPerformed(evt);
            }
        });
        PTodo.add(BEliminarDireccionBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 410, -1, -1));

        BBuscarDireccionBase.setText("+");
        BBuscarDireccionBase.setToolTipText("<html>\nagrega direcciones (Carpetas)\n</html>");
        BBuscarDireccionBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBuscarDireccionBaseActionPerformed(evt);
            }
        });
        PTodo.add(BBuscarDireccionBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 410, -1, -1));

        BSeñalarTDireccionesBase.setText("0");
        BSeñalarTDireccionesBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSeñalarTDireccionesBaseActionPerformed(evt);
            }
        });
        PTodo.add(BSeñalarTDireccionesBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 40, -1));

        BGuardarDireccionBase.setText("G");
        BGuardarDireccionBase.setToolTipText("Guarda este conjunto de direcciones");
        BGuardarDireccionBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarDireccionBaseActionPerformed(evt);
            }
        });
        PTodo.add(BGuardarDireccionBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 410, -1, -1));

        BTXTBase.setText("TXT");
        BTXTBase.setToolTipText("Almacena en TXT el contenido del cuadro de texto de la base");
        BTXTBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTXTBaseActionPerformed(evt);
            }
        });
        PTodo.add(BTXTBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 410, -1, -1));

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

        PTodo.add(SPTablaCarpetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 760, 310));

        RAlfabeticamente.setForeground(new java.awt.Color(0, 0, 102));
        RAlfabeticamente.setText("Alfabeticamente");
        RAlfabeticamente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoROrden(evt);
            }
        });
        PTodo.add(RAlfabeticamente, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 380, -1, -1));

        BActualizar.setText("A");
        BActualizar.setToolTipText("Actualizar");
        BActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActualizarActionPerformed(evt);
            }
        });
        PTodo.add(BActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 410, -1, -1));

        BTXTSoloContenido.setText("TXT Solo Contenido");
        BTXTSoloContenido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTXTSoloContenidoActionPerformed(evt);
            }
        });
        PTodo.add(BTXTSoloContenido, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 550, -1, -1));

        BAtras.setText("Atras");
        BAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAtrasActionPerformed(evt);
            }
        });
        PTodo.add(BAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 70, -1));

        RAscendente.setForeground(new java.awt.Color(102, 0, 0));
        RAscendente.setText("Ascendente");
        RAscendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretorRAscendente(evt);
            }
        });
        PTodo.add(RAscendente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        RDescendente.setForeground(new java.awt.Color(102, 0, 0));
        RDescendente.setSelected(true);
        RDescendente.setText("Descendente");
        RDescendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretorRAscendente(evt);
            }
        });
        PTodo.add(RDescendente, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, -1, -1));

        RTamaño.setForeground(new java.awt.Color(0, 0, 102));
        RTamaño.setText("Tamaño");
        RTamaño.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoROrden(evt);
            }
        });
        PTodo.add(RTamaño, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, -1, -1));

        RTamCap.setForeground(new java.awt.Color(0, 0, 102));
        RTamCap.setSelected(true);
        RTamCap.setText("Tam/Cap");
        RTamCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoROrden(evt);
            }
        });
        PTodo.add(RTamCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 380, -1, -1));

        RCantidadDeVideos.setForeground(new java.awt.Color(0, 0, 102));
        RCantidadDeVideos.setText("Cantidad de Videos");
        RCantidadDeVideos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoROrden(evt);
            }
        });
        PTodo.add(RCantidadDeVideos, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 380, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 797, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JTDireccionesBaseapreto(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTDireccionesBaseapreto

    }//GEN-LAST:event_JTDireccionesBaseapreto

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

    private void escribioTFDireccionTXT(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_escribioTFDireccionTXT
        EA.setDireccionTXT(new File(TFDireccionTXT.getText()));
        seguridad();
    }//GEN-LAST:event_escribioTFDireccionTXT

    private void BDireccionesBaseAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDireccionesBaseAdministradorActionPerformed
        visualizarVentanaAdministradorDirecciones();

    }//GEN-LAST:event_BDireccionesBaseAdministradorActionPerformed

    private void BClearDireccionBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BClearDireccionBaseActionPerformed
        try {
            EA.getDirecciones_De_Carpetas().getDirecciones_Carpetas().clear();
            actualizarTablaDireccion();
            actualizarMarcarDirecciones();
            actualizarTablaCarpetas();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BClearDireccionBaseActionPerformed

    private void BEliminarDireccionBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEliminarDireccionBaseActionPerformed
//        try {
//            eliminarDireccionBase();
//            actualizarTablaDireccion();
//            actualizarMarcarDirecciones();
//            actualizarTablaCarpetas();
//            seguridad();
//        } catch (Exception ex) {
//            responerException(ex);
//        }
        menosDireccionBase();
    }//GEN-LAST:event_BEliminarDireccionBaseActionPerformed

    private void BBuscarDireccionBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBuscarDireccionBaseActionPerformed
        if (Visual.buscador(jf, this)) {
            try {
                addDireccionBase(jf.getSelectedFile());
                actualizarTablaDireccion();
                actualizarMarcarDirecciones();
                actualizarTablaCarpetas();
                seguridad();
            } catch (Exception ex) {
                responerException(ex);
            }

        }
    }//GEN-LAST:event_BBuscarDireccionBaseActionPerformed

    private void BSeñalarTDireccionesBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSeñalarTDireccionesBaseActionPerformed
        try {
            apretoMarca(EA.getDirecciones_De_Carpetas().getDirecciones_Carpetas(), BSeñalarTDireccionesBase);
            actualizarTablaDireccion();
            actualizarMarcarDirecciones();
            actualizarTablaCarpetas();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BSeñalarTDireccionesBaseActionPerformed

    private void BGuardarDireccionBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarDireccionBaseActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de las direcciones");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                Admininistrador_De_Guardado<LinkedList<Direccion_Carpetas>> A = new Admininistrador_De_Guardado(DIRECCION_ADMINISTRADOR_DIRECCIONES);
                A.agregarInformacion(nuevoNombre, EA.getDirecciones_De_Carpetas().getDirecciones_Carpetas());
                A.guardarAdminastrador(DIRECCION_ADMINISTRADOR_DIRECCIONES);
                // visualizarVentanaAdministradorDirecciones();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BGuardarDireccionBaseActionPerformed

    private void BTXTBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTXTBaseActionPerformed
        try {
            String nuevoNombre = JOptionPane.showInputDialog("Escriba el nombre del TXT");
            if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                Archivo.crearTXT(new File(TFDireccionTXT.getText()), nuevoNombre, C.getLineasTXTTabla());
//                Visual.guardarEnTXTJTextArea(TBase, new File(TFDireccionTXT.getText()), nuevoNombre);
            }
        } catch (Exception ex) {
            responerException(ex);
        }


    }//GEN-LAST:event_BTXTBaseActionPerformed

    private void apretoROrden(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apretoROrden
        try {
            actualizarTablaCarpetas();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_apretoROrden

    private void BTXTSoloContenidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTXTSoloContenidoActionPerformed
        try {
            String nuevoNombre = JOptionPane.showInputDialog("Escriba el nombre del TXT");
            if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                Archivo.crearTXT(new File(TFDireccionTXT.getText()), nuevoNombre, C.getLineasSoloContenido());
            }
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BTXTSoloContenidoActionPerformed

    private void cerrandoVentana(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_cerrandoVentana
        try {
            crearArchivo(DIRECCION_ULTIMO_ESTADO, EA);
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_cerrandoVentana

    private void cerroVentana(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_cerroVentana
        alCerrar(this);
    }//GEN-LAST:event_cerroVentana

    private void BAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAtrasActionPerformed
        maximizarVentana(ventanaTodo);
        setVisible(false);
    }//GEN-LAST:event_BAtrasActionPerformed

    private void apretoTeclaTablaDirecciones(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apretoTeclaTablaDirecciones
        if (Visual.realizarDeleteTabla(evt, BEliminarDireccionBase)) {
            menosDireccionBase();
        }
    }//GEN-LAST:event_apretoTeclaTablaDirecciones

    private void BActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BActualizarActionPerformed
        try {
            actualizarTablaDireccion();
            actualizarMarcarDirecciones();

            actualizarTablaCarpetas();
            seguridad();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BActualizarActionPerformed

    private void apretorRAscendente(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apretorRAscendente
        try {

            actualizarTablaCarpetas();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_apretorRAscendente

    public void addDireccionBase(File f) {
        EA.getDirecciones_De_Carpetas().getDirecciones_Carpetas().add(new Direccion_Carpetas(new File(f.toString()), true));
    }

    public void eliminarDireccionBase() throws FileNotFoundException {
        if (eliminarDireccion(EA.getDirecciones_De_Carpetas().getDirecciones_Carpetas(), baseDireccionSelecionada)) {
//            actualizarDireccionBase();
        }
    }

    private boolean eliminarDireccion(LinkedList<Direccion_Carpetas> d, int indice) {
        if (indice >= 0 && indice < d.size()) {
            d.remove(indice);
            return true;
        }
        return false;
    }

    public void setFileEnTF_TXT(File f) {
        TFDireccionTXT.setText(f.toString());
    }

    public void visualizarVentanaAdministradorTXT() {
        try {
            if (ventana_Administrador_Direcciones_TXT == null) {
                ventana_Administrador_Direcciones_TXT = new Ventana_Administrador(DIRECCION_ADMINISTRADOR_DIRECCIONES_TXT, new Accion_Cargar_Cancelar() {

                    @Override
                    public void aceptar_Y_cargar() {
                        TFDireccionTXT.setText(ventana_Administrador_Direcciones_TXT.getInformacionSelecionada().toString());
                        seguridad();
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

//    public void add(LinkedList l) throws FileNotFoundException {
//        if (!l.isEmpty()) {
//            if (l.get(0) instanceof Direccion) {
//                if (base) {
//                    getDireccionesBaseActual().addAll(l);
//                    actualizarDireccionBase();
//                    actualizarBaseActual();
//                    actualizarEntrada();
//                    actualizarTBase();
//                    actualizarTEntrada();
//                }
//            }
//        }
//    }
    public void visualizarVentanaAdministradorDirecciones() {
        try {
            if (ventana_Administrador_Drecciones_Carpetas == null) {
                ventana_Administrador_Drecciones_Carpetas = new Ventana_Administrador(DIRECCION_ADMINISTRADOR_DIRECCIONES, new Accion_Cargar_Cancelar() {

                    @Override
                    public void aceptar_Y_cargar() {
                        try {
                            EA.getDirecciones_De_Carpetas().getDirecciones_Carpetas().addAll(ventana_Administrador_Drecciones_Carpetas.getInformacionSelecionada());
                            actualizarTablaDireccion();
                            actualizarMarcarDirecciones();
                            actualizarTablaCarpetas();
                            seguridad();
                        } catch (Exception ex) {
                            responerException(ex);
                        }
                        ventana_Administrador_Drecciones_Carpetas.setVisible(false);
                        setVisible(true);
                    }

                    @Override
                    public void cancelar() {
                        ventana_Administrador_Drecciones_Carpetas.setVisible(false);
                        setVisible(true);
                    }
                });
            } else {
                ventana_Administrador_Drecciones_Carpetas.setDireccionAdministrador(DIRECCION_ADMINISTRADOR_DIRECCIONES);
            }

            ventana_Administrador_Drecciones_Carpetas.setVisible(true);
            setVisible(false);
        } catch (Exception ex) {
            responerException(ex);
        }
    }

    private void activarDireccion(LinkedList<Direccion_Carpetas> d, JTable t, int fila) {
        // System.out.println("(Boolean) t.getValueAt(fila, 0)="+(Boolean) t.getValueAt(fila, 0));
        d.get(fila).setSeleccionado((Boolean) t.getValueAt(fila, 0));
        //   System.out.println("d.get(fila).isSeleccionado()="+d.get(fila).isSeleccionado());
    }

    public void actualizarMarcarDirecciones() {
        actualizarMarcar(EA.getDirecciones_De_Carpetas().getDirecciones_Carpetas(), BSeñalarTDireccionesBase);
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

    public void actualizarTablaDireccion() throws FileNotFoundException {
        editingTabla Et = new editingTabla() {

            @Override
            public void editingStopped(ChangeEvent e, JTable t, int fila, int columna) {
                try {

                    activarDireccion(EA.getDirecciones_De_Carpetas().getDirecciones_Carpetas(), t, fila);
                    actualizarTablaCarpetas();
                    actualizarMarcarDirecciones();
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

            }

        };
        JTDireccionesBase.setToolTipText("direccion base");
        ConfiguracionDeTabla cdt = new ConfiguracionDeTabla(PTodo, PDireccionesBase, SPDireccionesBase, JTDireccionesBase, EA.getDirecciones_De_Carpetas().getTabla_Direcciones_Carpetas(), Et, Sf, ANCHO_SELECT, -1);
        Visual.actualizarTablaSeleccionable(cdt);
    }

    public void actualizarTablaCarpetas() throws FileNotFoundException {
        C = new Carpetas(EA.getDirecciones_De_Carpetas().getDireccionesSeleccionadas());
        ordenar(C);
        ConfiguracionDeTabla cdt = new ConfiguracionDeTabla(PTodo, PTablaCarpetas, SPTablaCarpetas, JTTablaCarpetas, C.getTabla());
        Visual.actualizarTablaString(cdt);
    }

    public void ordenar(Carpetas c) {
        C.setAscendente(RAscendente.isSelected());
        if (RCantidadDeVideos.isSelected()) {
            c.ordenarCantidadDeVideos();
            return;
        }
        if (RTamCap.isSelected()) {
            c.ordenarTamañoPorCapitulos();
            return;
        }
        if (RAlfabeticamente.isSelected()) {
            c.ordenarNombre();
            return;
        }
        if (RTamaño.isSelected()) {
            c.ordenarTamaño();
//            return;
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
    private javax.swing.JButton BAdministradorDireccionTXT;
    private javax.swing.JButton BAgregarDireccionTXT;
    private javax.swing.JButton BAtras;
    private javax.swing.JButton BBuscarDireccionBase;
    private javax.swing.JButton BClearDireccionBase;
    private javax.swing.JButton BClearDireccionTXT;
    private javax.swing.JButton BDireccionesBaseAdministrador;
    private javax.swing.JButton BEliminarDireccionBase;
    private javax.swing.ButtonGroup BGAscendente;
    private javax.swing.ButtonGroup BGOrdenar;
    private javax.swing.JButton BGuardarDireccionBase;
    private javax.swing.JButton BGuardarDireccionTXT;
    private javax.swing.JButton BSeñalarTDireccionesBase;
    private javax.swing.JButton BTXTBase;
    private javax.swing.JButton BTXTSoloContenido;
    private javax.swing.JTable JTDireccionesBase;
    private javax.swing.JTable JTTablaCarpetas;
    private javax.swing.JPanel PDireccionesBase;
    private javax.swing.JPanel PTablaCarpetas;
    private javax.swing.JPanel PTodo;
    private javax.swing.JRadioButton RAlfabeticamente;
    private javax.swing.JRadioButton RAscendente;
    private javax.swing.JRadioButton RCantidadDeVideos;
    private javax.swing.JRadioButton RDescendente;
    private javax.swing.JRadioButton RTamCap;
    private javax.swing.JRadioButton RTamaño;
    private javax.swing.JScrollPane SPDireccionesBase;
    private javax.swing.JScrollPane SPTablaCarpetas;
    private javax.swing.JTextField TFDireccionTXT;
    // End of variables declaration//GEN-END:variables
}
