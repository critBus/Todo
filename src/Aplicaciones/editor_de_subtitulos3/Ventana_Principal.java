/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.editor_de_subtitulos3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static Utiles.MetodosUtiles.Visual.*;
import static Utiles.MetodosUtiles.Archivo.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static todo.Todo.*;
/**
 *
 * @author Rene
 */
public class Ventana_Principal extends javax.swing.JFrame {

    public static Ventana_Principal ventana_Principal;
    public static Ventana_Crear_Modificar ventana_Crear_Modificar;
    public static Ventana_Editar_Seccion ventana_Editar_Seccion;
    public static Ventana_Calcular ventana_Calcular;
    public static Ventana_Aumento2 ventana_Aumento2;
    public static JFrame todasLasVentanas[];
    private JFileChooser Fc = new JFileChooser(), jfAnterior;
    private Scanner s;
    protected static Subtitulo S;

    /**
     * Creates new form Ventana_Principal
     */
    public Ventana_Principal() {
        initComponents();
        setLocationRelativeTo(null);
        L.setText("Carge un subtitulo");
        ventana_Principal = this;
        todasLasVentanas=new JFrame[]{this,ventana_Crear_Modificar,ventana_Editar_Seccion,ventana_Calcular,ventana_Aumento2};
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
        BAumento = new javax.swing.JButton();
        BCalcular = new javax.swing.JButton();
        BCargar = new javax.swing.JButton();
        BEditarSeccion = new javax.swing.JButton();
        BRestaurar = new javax.swing.JButton();
        BGuardar = new javax.swing.JButton();
        L = new javax.swing.JLabel();
        BAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editor de Subtitulos");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                cerroVentana(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                seActivoVentana(evt);
            }
        });

        PTodo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        T.setEditable(false);
        T.setColumns(20);
        T.setRows(5);
        jScrollPane1.setViewportView(T);

        PTodo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 400, 410));

        BAumento.setText("+           -");
        BAumento.setEnabled(false);
        BAumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAumentoActionPerformed(evt);
            }
        });
        PTodo.add(BAumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 130, -1));

        BCalcular.setText("Calcular");
        BCalcular.setEnabled(false);
        BCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCalcularActionPerformed(evt);
            }
        });
        PTodo.add(BCalcular, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 460, 130, -1));

        BCargar.setText("Cargar");
        BCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCargarActionPerformed(evt);
            }
        });
        PTodo.add(BCargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 460, 120, -1));

        BEditarSeccion.setText("Editar Seccion");
        BEditarSeccion.setEnabled(false);
        BEditarSeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEditarSeccionActionPerformed(evt);
            }
        });
        PTodo.add(BEditarSeccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 130, -1));

        BRestaurar.setText("Restaurar");
        BRestaurar.setEnabled(false);
        BRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BRestaurarActionPerformed(evt);
            }
        });
        PTodo.add(BRestaurar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 490, 130, -1));

        BGuardar.setText("Guardar");
        BGuardar.setEnabled(false);
        BGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarActionPerformed(evt);
            }
        });
        PTodo.add(BGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 490, 120, -1));

        L.setText("jLabel1");
        PTodo.add(L, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 20));

        BAtras.setText("Atras");
        BAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAtrasActionPerformed(evt);
            }
        });
        PTodo.add(BAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PTodo, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCargarActionPerformed

        Fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (Fc.showOpenDialog(this) != JFileChooser.CANCEL_OPTION) {

            try {
                leerFileJTextArea(T, Fc);
                leerSubtituloActual(T.getText());
                activarTodosLosBotones(true);
            } catch (Exception ex) {
                responerException(ex);
            }
            L.setText(Fc.getSelectedFile().getPath());

        }
        seguridad();
    }//GEN-LAST:event_BCargarActionPerformed

    private void BRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BRestaurarActionPerformed
        if (dialogoSiNo("Si restaura perdera todos los canvios realizados", "Advertencia")) {
            try {
                leerFileJTextArea(T, jfAnterior);
                leerSubtituloActual(T.getText());
            } catch (Exception ex) {
                Fc.setSelectedFile(jfAnterior.getSelectedFile());
                L.setText(Fc.getSelectedFile().getPath());
                responerException(ex);
            }
        }
        seguridad();
        if (ventana_Aumento2 != null) {
            ventana_Aumento2.reiniciarDiferencias();
            ventana_Aumento2.actualizarLD();
        }
    }//GEN-LAST:event_BRestaurarActionPerformed

    private void BGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarActionPerformed
        try {
            guardarEnFileJTextArea(T, Fc.getSelectedFile(), ".srt", this);
        } catch (Exception ex) {
            responerException(ex);
        }
        seguridad();
    }//GEN-LAST:event_BGuardarActionPerformed

    private void BCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCalcularActionPerformed
        visualizarVentanaCalcular();
        seguridad();
    }//GEN-LAST:event_BCalcularActionPerformed

    private void BEditarSeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditarSeccionActionPerformed
        visualizarVentana_Crear_Modificar();

    }//GEN-LAST:event_BEditarSeccionActionPerformed

    private void BAumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAumentoActionPerformed
        visualizarVentanaAumento();
    }//GEN-LAST:event_BAumentoActionPerformed

    private void seActivoVentana(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_seActivoVentana
        seguridad();        // TODO add your handling code here:
    }//GEN-LAST:event_seActivoVentana

    private void BAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAtrasActionPerformed
        maximizarVentana(ventanaTodo);
        setVisible(false);
    }//GEN-LAST:event_BAtrasActionPerformed

    private void cerroVentana(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_cerroVentana
        alCerrar(this);
    }//GEN-LAST:event_cerroVentana

     public void alCerrar(JFrame f) {
        cerrar(f, 1);
    }
    public void seguridad() {
        BCalcular.setEnabled(!(ventana_Calcular != null && ventana_Calcular.isVisible()) && BGuardar.isEnabled());
        BAumento.setEnabled(!(ventana_Aumento2 != null && ventana_Aumento2.isVisible()) && BGuardar.isEnabled());
        BEditarSeccion.setEnabled(!(ventana_Crear_Modificar != null && ventana_Crear_Modificar.isVisible()) && BGuardar.isEnabled());
    }

    private void visualizarVentanaCalcular() {
        if (ventana_Calcular == null) {
            ventana_Calcular = new Ventana_Calcular();
        }
        ventana_Calcular.setVisible(true);
        //setVisible(false);
    }

    private void visualizarVentanaAumento() {
        if (ventana_Aumento2 == null) {
            ventana_Aumento2 = new Ventana_Aumento2();
        }
        ventana_Aumento2.setVisible(true);
       // setVisible(false);
        seguridad();
    }

    private void visualizarVentana_Crear_Modificar() {
        if (ventana_Crear_Modificar == null) {
            ventana_Crear_Modificar = new Ventana_Crear_Modificar();
        }
        // ventana_Crear_Modificar = new Ventana_Crear_Modificar();
        ventana_Crear_Modificar.setVisible(true);
      //  setVisible(false);
        seguridad();
    }

    private void activarBotones(boolean activar) {
        activarJComponent(activar, BAumento, BCalcular, BEditarSeccion, BGuardar);
    }

    private void activarTodosLosBotones(boolean activar) {
        activarJComponent(activar, BAumento, BCalcular, BEditarSeccion, BGuardar, BRestaurar);
    }

    private void lecturaIncorrecta(Subtitulo anterior) {
        L.setText("El subtitulo no esta escrito correctamente");
        activarBotones(false);
        S = anterior;
    }

    private void crearJfAnterior(File f) {
        jfAnterior = new JFileChooser(f);
        jfAnterior.setSelectedFile(f);
    }

    private void leerSubtituloActual() {
        try {
            leerSubtituloActual(new Scanner(Fc.getSelectedFile()));
        } catch (FileNotFoundException ex) {
            lecturaIncorrecta(S);
        }
    }

    private void leerSubtituloActual(String e) {
        leerSubtituloActual(new Scanner(e));
    }

    private void leerSubtituloActual(Scanner e) {
        Subtitulo S2 = S;
        try {
            s = e;
            S = new Subtitulo();
            int numeroDeLinea = 0;
            int c = 0;
            String seccion = "";
            String entrada;

            while (numeroDeLinea < T.getLineCount() - 1) {
                numeroDeLinea++;
                entrada = s.nextLine();
                if (entrada.equals("1")) {
                    continue;
                }
                if (entrada.isEmpty()) {
                    c++;
                    if (!seccion.isEmpty()) {
                        //   System.out.println("seccion="+seccion);
                        S.añadirSeccion(Subtitulo.crearSeccionDeSubtitulo(seccion));
                        seccion = "";
                    }
                    continue;
                }
                if (c == 1) {
                    c = 0;
                    continue;
                }

                for (int i = 0; i < entrada.length(); i++) {
                    if (entrada.charAt(i) == '�') {
                        seccion += "";
                    } else {
                        seccion += entrada.charAt(i);
                    }
                }
                seccion += "\n";
            }
            activarBotones(true);
            crearJfAnterior(Fc.getSelectedFile());
        } catch (NoSuchElementException | NumberFormatException ex) {
            lecturaIncorrecta(S2);
        }

    }

    public void actualizarT() {
        T.setText(S.getSubtitulo());
    }
//
//    
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
    private javax.swing.JButton BAtras;
    private javax.swing.JButton BAumento;
    private javax.swing.JButton BCalcular;
    private javax.swing.JButton BCargar;
    private javax.swing.JButton BEditarSeccion;
    private javax.swing.JButton BGuardar;
    private javax.swing.JButton BRestaurar;
    private javax.swing.JLabel L;
    private javax.swing.JPanel PTodo;
    private javax.swing.JTextArea T;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
