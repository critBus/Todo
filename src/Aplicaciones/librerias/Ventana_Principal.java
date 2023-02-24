/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.librerias;

import Utiles.MetodosUtiles.Archivo;
import static Utiles.MetodosUtiles.Archivo.crearArchivo;
import Utiles.MetodosUtiles.Visual;
import static Utiles.MetodosUtiles.Visual.responerException;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import static Aplicaciones.librerias.Librerias.*;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Rene
 */
public class Ventana_Principal extends javax.swing.JFrame {

    private enum tabla {

        BIBLIOTECAS, DIRECCIONES, PROYECTOS;
    }
    JFileChooser jf;
    Estado_Actual E;
    private final int ANCHO_SELEC = 60;//X_DIRECCIONES,Y_DIRECCIONES;

    /**
     * Creates new form Ventana_Principal
     *
     * @param E
     */
    public Ventana_Principal(Estado_Actual E) {
        this.E = E;

        jf = new JFileChooser();
        jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        initComponents();
        Visual.estiloNimbus();
        setLocationRelativeTo(null);
        try {
            ventana_Administrador = new Ventana_Administrador(DIRECCION_ADMINISTRADOR_DIRECCIONES);
            todasLasVentanas = new JFrame[]{this, ventana_Administrador};
            if (E.isEmptyDirecciones()) {
                File f = new File("D:\\Rene\\Proyectos\\java\\Nuevo");
                if (f.exists()) {
                    E.direccionMultiple(f);
                    actualizarTablas();
                }
            }
        } catch (Exception ex) {
            responerException(ex);
        }
        seguridad();

    }

    public void actualizar() throws FileNotFoundException{
    E.actualizarTablas();
    actualizarTablas();
    seguridad();
    }
    public void actualizarTablaDirecciones() {
        TDirecciones = new JTable();
        TDirecciones.setModel(new javax.swing.table.DefaultTableModel(
                E.tablaDirecciones(),
                E.titulosDirecciones()
        ) {
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        addCellEditor(tabla.DIRECCIONES, E.siseDirecciones());
        SPDirecciones.setViewportView(TDirecciones);
        if (TDirecciones.getColumnModel().getColumnCount() > 0) {
            TDirecciones.getColumnModel().getColumn(0).setResizable(false);
            TDirecciones.getColumnModel().getColumn(1).setResizable(false);
        }
        TDirecciones.getColumnModel().getColumn(0).setMaxWidth(ANCHO_SELEC);
        //PTodo.add(SPDirecciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 650, 150));
        actualizarCoordenadas(SPDirecciones);

    }

//    public void actualizarCoordenadasTablaDirecciones() {
//        PTodo.add(SPDirecciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(SPDirecciones.getX(), SPDirecciones.getY(), SPDirecciones.getWidth(), SPDirecciones.getHeight()));
//    }
//
//    public void actualizarCoordenadasTablaProyectos() {
//        PTodo.add(SPProyectos, new org.netbeans.lib.awtextra.AbsoluteConstraints(SPProyectos.getX(), SPProyectos.getY(), SPProyectos.getWidth(), SPProyectos.getHeight()));
//    }
//
//    public void actualizarCoordenadasTablaBiblioteca() {
//    }
    public void actualizarCoordenadas(JScrollPane sp) {
        Visual.actualizarCoordenadas(PTodo, sp);
    }
public static void actualizarCoordenadas2(JPanel p, JScrollPane sp,JTable t) {
    
        p.add((sp=new JScrollPane(t)), new org.netbeans.lib.awtextra.AbsoluteConstraints(sp.getX(), sp.getY(), sp.getWidth(), sp.getHeight()));
    }
    public void actualizarTablaProyectos() {
        TProyectos = new JTable();
        TProyectos.setModel(new javax.swing.table.DefaultTableModel(
                E.tablaProyectos(),
                E.titulosProyectos()
        ) {
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        addCellEditor(tabla.PROYECTOS, E.siseProyectos());
        SPProyectos.setViewportView(TProyectos);
        if (TProyectos.getColumnModel().getColumnCount() > 0) {
            //System.out.println("E "+E.tablaProyectos()[0].length);

            // System.out.println("TProyectos.getColumnModel().getColumnCount()="+TProyectos.getColumnModel().getColumnCount());
            TProyectos.getColumnModel().getColumn(0).setResizable(false);
            TProyectos.getColumnModel().getColumn(1).setResizable(false);
            TProyectos.getColumnModel().getColumn(2).setResizable(false);
        }
        actualizarCoordenadas(SPProyectos);
        //PTodo.add(SPProyectos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 160, 320));
        TProyectos.getColumnModel().getColumn(0).setMaxWidth(ANCHO_SELEC);
         TProyectos.getColumnModel().getColumn(0).setMinWidth(20);
        TProyectos.getColumnModel().getColumn(1).setMinWidth(100);
        TProyectos.getColumnModel().getColumn(2).setMinWidth(200);
      //  TProyectos.setMinimumSize(new Dimension(500, TProyectos.getHeight()));
       
       // SPProyectos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       // TProyectos.setSize(500, TProyectos.getHeight());
        //      TProyectos.getColumnModel().getColumn(2).setMinWidth(100);
    }

    public void actualizarTablaBiblioteca() {
        PBiblioteca.removeAll();
        TBiblioteca = new JTable();
        TBiblioteca.setModel(new javax.swing.table.DefaultTableModel(
                E.tablaBiblioteca(),
                E.titulosBiblioteca()
        ) {
            Class[] types = E.ClassBiblioteca();
            boolean[] canEdit = E.canEditBiblioteca();

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        addCellEditor(tabla.BIBLIOTECAS, E.siseBiblioteca());
       // SPBiblioteca.setViewportView(TBiblioteca);
        if (TBiblioteca.getColumnModel().getColumnCount() > 0) {
            TBiblioteca.getColumnModel().getColumn(0).setResizable(false);
            TBiblioteca.getColumnModel().getColumn(1).setResizable(false);
        }
        //    System.out.println("sise E"+E.titulosBiblioteca().length);
//System.out.println("TBiblioteca.getColumnModel().getColumnCount()="+TBiblioteca.getColumnModel().getColumnCount());
       
        //PTodo.add(SPBiblioteca, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 470, 320));
       // actualizarCoordenadas(SPBiblioteca);4
        
         for (int i = 0; i < TBiblioteca.getColumnModel().getColumnCount(); i++) {
            TBiblioteca.getColumnModel().getColumn(i).setMinWidth(i == 0 ? 200 : 120);
        }
          
          PBiblioteca.add(TBiblioteca, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
       
//         TBiblioteca.setSize(1000, 500);
//           actualizarCoordenadas2(PTodo, SPBiblioteca,TBiblioteca);
//          System.out.println(TBiblioteca.getWidth()+"  "+TBiblioteca.getHeight());
         // TBiblioteca.validate();
         // SPBiblioteca.validate();
         // validate();
         // SPBiblioteca.setViewportView(TBiblioteca);
          // actualizarCoordenadas(SPBiblioteca);
          // TBiblioteca.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }

    public void actualizarTablas() {
        actualizarTablaDirecciones();
        actualizarTablaProyectos();
        actualizarTablaBiblioteca();
    }

    private void addCellEditor(final tabla Tabla, int cantFilas) {
        class Manejador implements CellEditorListener {

            int fila;
            JTable t;

            public Manejador(int fila, JTable t) {
                this.fila = fila;
                this.t = t;
            }

            public boolean isEmpty() {
                return Tabla == tabla.DIRECCIONES ? E.isEmptyDirecciones() : E.isEmptyProyectos();
            }

            @Override
            public void editingStopped(ChangeEvent e) {
                /// System.out.println("se llamo desde " + Tabla);

                try {
                    if (!isEmpty()) {
                        boolean actualizar = false;
                        if (Tabla == tabla.DIRECCIONES) {
                            actualizar = E.setSelecionadoDirecciones(fila, (Boolean) t.getValueAt(fila, 0));
                        } else {
                            actualizar = E.setSelecionadoProyectos(fila, (Boolean) t.getValueAt(fila, 0));
                        }
                        if (actualizar) {
                            E.actualizarTablas();
                            actualizarTablas();
                        }
                    }
                } catch (Exception ex) {
                    Visual.responerException(ex);
                }
            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }

        }

        final JTable t = getTabla(Tabla);
        for (int i = 0; i < cantFilas; i++) {
            //  t.getCellEditor(t.getRowCount() - 1, indiceInicial + i).
            t.getCellEditor(i, 0).addCellEditorListener(new Manejador(i, t));
        }

    }

    private JTable getTabla(tabla t) {
        switch (t) {
            case BIBLIOTECAS:
                return TBiblioteca;
            case DIRECCIONES:
                return TDirecciones;
            case PROYECTOS:
                return TProyectos;
        }
        return null;
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
        SPProyectos = new javax.swing.JScrollPane();
        TProyectos = new javax.swing.JTable();
        SPDirecciones = new javax.swing.JScrollPane();
        TDirecciones = new javax.swing.JTable();
        BAñadirDireccion = new javax.swing.JButton();
        BEliminarDireccion = new javax.swing.JButton();
        BVaciarDirecciones = new javax.swing.JButton();
        BDirecciones = new javax.swing.JButton();
        BConfiguarcion = new javax.swing.JButton();
        BGuardarDireciones = new javax.swing.JButton();
        BAtras = new javax.swing.JButton();
        BActualizar = new javax.swing.JButton();
        SPBiblioteca2 = new javax.swing.JScrollPane();
        PBiblioteca = new javax.swing.JPanel();
        TBiblioteca = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Librerias");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                CerroVentana(evt);
            }
        });

        PTodo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TProyectos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Selc", "Proyecto", "Carpeta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SPProyectos.setViewportView(TProyectos);
        if (TProyectos.getColumnModel().getColumnCount() > 0) {
            TProyectos.getColumnModel().getColumn(0).setResizable(false);
            TProyectos.getColumnModel().getColumn(1).setResizable(false);
            TProyectos.getColumnModel().getColumn(2).setResizable(false);
        }

        PTodo.add(SPProyectos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 290, 320));

        TDirecciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Selc", "Direcciones"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SPDirecciones.setViewportView(TDirecciones);
        if (TDirecciones.getColumnModel().getColumnCount() > 0) {
            TDirecciones.getColumnModel().getColumn(0).setResizable(false);
            TDirecciones.getColumnModel().getColumn(1).setResizable(false);
        }

        PTodo.add(SPDirecciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 830, 150));

        BAñadirDireccion.setText("Añadir Direccion");
        BAñadirDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAñadirDireccionActionPerformed(evt);
            }
        });
        PTodo.add(BAñadirDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, -1));

        BEliminarDireccion.setText("Eliminar Direcciones Selecionadas");
        BEliminarDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEliminarDireccionActionPerformed(evt);
            }
        });
        PTodo.add(BEliminarDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, -1, -1));

        BVaciarDirecciones.setText("Vaciar Direcciones");
        BVaciarDirecciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BVaciarDireccionesActionPerformed(evt);
            }
        });
        PTodo.add(BVaciarDirecciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, -1, -1));

        BDirecciones.setText("Direcciones");
        BDirecciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDireccionesActionPerformed(evt);
            }
        });
        PTodo.add(BDirecciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 410, -1, -1));

        BConfiguarcion.setText("Configuarcion");
        PTodo.add(BConfiguarcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 410, -1, -1));

        BGuardarDireciones.setText("Guardar Direciones");
        BGuardarDireciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarDirecionesActionPerformed(evt);
            }
        });
        PTodo.add(BGuardarDireciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 410, -1, -1));

        BAtras.setText("Atras");
        BAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAtrasActionPerformed(evt);
            }
        });
        PTodo.add(BAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, -1, -1));

        BActualizar.setText("Actualizar");
        BActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActualizarActionPerformed(evt);
            }
        });
        PTodo.add(BActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 110, -1));

        PBiblioteca.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TBiblioteca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "***", "Nombres de Proyectos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        PBiblioteca.add(TBiblioteca, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        if (TBiblioteca.getColumnModel().getColumnCount() > 0) {
            TBiblioteca.getColumnModel().getColumn(0).setResizable(false);
            TBiblioteca.getColumnModel().getColumn(1).setResizable(false);
        }

        SPBiblioteca2.setViewportView(PBiblioteca);

        PTodo.add(SPBiblioteca2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 520, 320));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 891, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PTodo, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BAñadirDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAñadirDireccionActionPerformed
        if (Visual.buscador(jf, this)) {
            addDireccion(jf.getSelectedFile());
            seguridad();
        }
    }//GEN-LAST:event_BAñadirDireccionActionPerformed

    private void BDireccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDireccionesActionPerformed
        visualizarVentanaAdministradorDirecciones();
    }//GEN-LAST:event_BDireccionesActionPerformed

    private void BGuardarDirecionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarDirecionesActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de las direcciones");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                Admininistrador_De_Guardado<java.io.File[]> A = new Admininistrador_De_Guardado(DIRECCION_ADMINISTRADOR_DIRECCIONES);
                A.agregarInformacion(nuevoNombre, E.getDirecciones().toArray(new File[]{}));
                A.guardarAdminastrador(DIRECCION_ADMINISTRADOR_DIRECCIONES);
                visualizarVentanaAdministradorDirecciones();
            } catch (Exception ex) {
                responerException(ex);
            }
        } // TODO add your handling code here:
    }//GEN-LAST:event_BGuardarDirecionesActionPerformed

    private void BEliminarDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEliminarDireccionActionPerformed
        try {
            E.eliminarDirecionesSelecionadas();
            actualizarTablas();
            seguridad();
        } catch (Exception ex) {
            Visual.responerException(ex);
        }
    }//GEN-LAST:event_BEliminarDireccionActionPerformed

    private void BVaciarDireccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BVaciarDireccionesActionPerformed
        try {
            E.clear();
            actualizarTablas();
            seguridad();
        } catch (Exception ex) {
            Visual.responerException(ex);
        }
    }//GEN-LAST:event_BVaciarDireccionesActionPerformed

    private void CerroVentana(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_CerroVentana
        cerrar(this);
    }//GEN-LAST:event_CerroVentana

    private void BAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAtrasActionPerformed
        Visual.maximizarVentana(todo.Todo.ventanaTodo);
        setVisible(false);
    }//GEN-LAST:event_BAtrasActionPerformed

    private void BActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BActualizarActionPerformed
        try {
            actualizar();
       } catch (Exception ex) {
            Visual.responerException(ex);
        }
    }//GEN-LAST:event_BActualizarActionPerformed

    public void addDireccion(File... F) {
        try {
            // System.out.println("en "+Librerias.esDireccionExacta(jf.getSelectedFile()));
            for (int i = 0; i < F.length; i++) {
                if (!E.contieneAlFileProyectosDirecciones(F[i])) {
                    if (Librerias.esDireccionExacta(F[i])) {
                        //  System.out.println("es");
                        E.direccionExacta(F[i]);
                    } else {
                        E.direccionMultiple(F[i]);
                    }
                }

            }

            actualizarTablas();
            seguridad();
        } catch (Exception ex) {
            Visual.responerException(ex);
        }
    }

    private void visualizarVentanaAdministradorDirecciones() {
        try {
            if (ventana_Administrador == null) {
                ventana_Administrador = new Ventana_Administrador(DIRECCION_ADMINISTRADOR_DIRECCIONES);
            }
            ventana_Administrador.setVisible(true);
            setVisible(false);
        } catch (Exception ex) {
            Visual.responerException(ex);
        }
    }

    private void seguridad() {
        //  System.out.println("E.isEmptyDirecciones()="+E.isEmptyDirecciones());
        Visual.activarJComponent(!E.isEmptyDirecciones(), BEliminarDireccion, BVaciarDirecciones);
    }

    public static void cerrar(JFrame f) {
        todo.Todo.cerrar(f,5);
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
                try {
                    new File("Data").mkdir();
                    if (!new File(DIRECCION_ULTIMO_ESTADO).exists()) {
                        crearArchivo(DIRECCION_ULTIMO_ESTADO, new Estado_Actual());
                    }
                    if (!new File(DIRECCION_ADMINISTRADOR_DIRECCIONES).exists()) {
                        crearArchivo(DIRECCION_ADMINISTRADOR_DIRECCIONES, new Admininistrador_De_Guardado<File>());
                    }
                    ventana_Principal = new Ventana_Principal((Estado_Actual) Archivo.cargarArchivo(DIRECCION_ULTIMO_ESTADO));

                    //  ventana_Principal = (Ventana_Principal) Archivo.cargarArchivo(DIRECCION_ULTIMO_ESTADO);
                    ventana_Principal.setVisible(true);
                    // Visual.estiloNimbus();
                } catch (Exception ex) {
                    Visual.responerException(ex);
                }
                //  new Ventana_Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BActualizar;
    private javax.swing.JButton BAtras;
    private javax.swing.JButton BAñadirDireccion;
    private javax.swing.JButton BConfiguarcion;
    private javax.swing.JButton BDirecciones;
    private javax.swing.JButton BEliminarDireccion;
    private javax.swing.JButton BGuardarDireciones;
    private javax.swing.JButton BVaciarDirecciones;
    private javax.swing.JPanel PBiblioteca;
    private javax.swing.JPanel PTodo;
    private javax.swing.JScrollPane SPBiblioteca2;
    private javax.swing.JScrollPane SPDirecciones;
    private javax.swing.JScrollPane SPProyectos;
    private javax.swing.JTable TBiblioteca;
    private javax.swing.JTable TDirecciones;
    private javax.swing.JTable TProyectos;
    // End of variables declaration//GEN-END:variables
}
