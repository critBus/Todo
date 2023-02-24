/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;

//import Aplicaciones.actualizador.de.series.Ventana_Principal2;
//import Aplicaciones.librerias.Estado_Actual;
//import static Aplicaciones.modificarextencion.ModificarExtencion.ventanaPrincipal;
//import Aplicaciones.modificarextencion.Ventana_Principal;
import Utiles.ClasesUtiles.Admininistrador.Admininistrador_De_Guardado;
import Utiles.ClasesUtiles.Configuraciones.Ventanas.TablasAmpliableConfiguracionDeVideo;
//import Utiles.ClasesUtiles.Admininistrador_De_Guardado;
import Utiles.MetodosUtiles.Archivo;
import static Utiles.MetodosUtiles.Archivo.crearArchivo;
import static Utiles.MetodosUtiles.Visual.minimizarVentana;//
import static Utiles.MetodosUtiles.Visual.algunaEsVisible;
import Utiles.MetodosUtiles.Visual;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
//import Aplicaciones.Contenido_Carpeta.*;

/**
 *
 * @author Rene
 */
public class Todo extends javax.swing.JFrame {

    Aplicaciones.editor_de_subtitulos3.Ventana_Principal editorDeSubtitulos;
    Aplicaciones.editor_de_texto.Ventana_Principal editorDeTexto;
    Aplicaciones.convertir.Ventana_Principal convertidor;
    Aplicaciones.calculadora_inteligente.Calculadora_Inteligente calculadoraInteligente;
    //  Aplicaciones.modificarextencion.Ventana_Principal ventanaPrincipal;
    JButton B[];
    public static Todo ventanaTodo;

    /**
     * Creates new form Todo
     */
    public Todo() {
        initComponents();
        // setLocation(300, 300);
        setLocationRelativeTo(null);
        ventanaTodo = this;
        B = new JButton[]{BModificarExtencion, BEditorDeSubtitulo, BEditorDeTexto, BCalculadora, BConvertidor, BLibrerias, BActualizadorDeSeries, BMoverNumero, BContenidoCarpeta, BCopiadorDeSubtitulos};
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        PTodo = new javax.swing.JPanel();
        BEditorDeTexto = new javax.swing.JButton();
        BEditorDeSubtitulo = new javax.swing.JButton();
        BConvertidor = new javax.swing.JButton();
        LLeyen = new javax.swing.JLabel();
        BCalculadora = new javax.swing.JButton();
        BModificarExtencion = new javax.swing.JButton();
        BLibrerias = new javax.swing.JButton();
        BActualizadorDeSeries = new javax.swing.JButton();
        BMoverNumero = new javax.swing.JButton();
        BContenidoCarpeta = new javax.swing.JButton();
        BCopiadorDeSubtitulos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Todo");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                AbrioVentana(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                ActivoVentana(evt);
            }
        });

        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 0, 0), 1, true));

        PTodo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PTodo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BEditorDeTexto.setText("Editor de Texto");
        BEditorDeTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEditorDeTextoActionPerformed(evt);
            }
        });
        PTodo.add(BEditorDeTexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 150, -1));

        BEditorDeSubtitulo.setText("Editor de Subtitulo");
        BEditorDeSubtitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEditorDeSubtituloActionPerformed(evt);
            }
        });
        PTodo.add(BEditorDeSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, 150, -1));

        BConvertidor.setText("Convertidor");
        BConvertidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BConvertidorActionPerformed(evt);
            }
        });
        PTodo.add(BConvertidor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 150, -1));

        LLeyen.setFont(new java.awt.Font("Blackadder ITC", 1, 24)); // NOI18N
        LLeyen.setForeground(new java.awt.Color(153, 0, 0));
        LLeyen.setText(" Leyen");
        PTodo.add(LLeyen, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 90, -1));

        BCalculadora.setText("Calculadora");
        BCalculadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCalculadoraActionPerformed(evt);
            }
        });
        PTodo.add(BCalculadora, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, 150, -1));

        BModificarExtencion.setText("Modificar Extencion");
        BModificarExtencion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BModificarExtencionActionPerformed(evt);
            }
        });
        PTodo.add(BModificarExtencion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 150, -1));

        BLibrerias.setText("Librerias");
        BLibrerias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLibreriasActionPerformed(evt);
            }
        });
        PTodo.add(BLibrerias, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, 150, -1));

        BActualizadorDeSeries.setText("Actualizador de Series");
        BActualizadorDeSeries.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActualizadorDeSeriesActionPerformed(evt);
            }
        });
        PTodo.add(BActualizadorDeSeries, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 150, -1));

        BMoverNumero.setText("Mover Numero");
        BMoverNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BMoverNumeroActionPerformed(evt);
            }
        });
        PTodo.add(BMoverNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 150, -1));

        BContenidoCarpeta.setText("Contenido Carpeta");
        BContenidoCarpeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BContenidoCarpetaActionPerformed(evt);
            }
        });
        PTodo.add(BContenidoCarpeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 150, -1));

        BCopiadorDeSubtitulos.setText("Copiador  Subtitulos");
        BCopiadorDeSubtitulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCopiadorDeSubtitulosActionPerformed(evt);
            }
        });
        PTodo.add(BCopiadorDeSubtitulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 150, -1));

        jScrollPane1.setViewportView(PTodo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BEditorDeSubtituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditorDeSubtituloActionPerformed
        if (editorDeSubtitulos == null) {
            editorDeSubtitulos = new Aplicaciones.editor_de_subtitulos3.Ventana_Principal();
        }

        // editorDeSubtitulos.setVisible(true);
        //   setVisible(false);
        alAbrirUnaAplicacion(editorDeSubtitulos);
    }//GEN-LAST:event_BEditorDeSubtituloActionPerformed

    private void BEditorDeTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditorDeTextoActionPerformed
        try {
            if (editorDeTexto == null) {
                editorDeTexto = new Aplicaciones.editor_de_texto.Ventana_Principal();
            }
        // editorDeTexto.setVisible(true);
            //  setVisible(false);
            alAbrirUnaAplicacion(editorDeTexto);
        } catch (Exception ex) {
            Visual.responerException(ex);
        }
    }//GEN-LAST:event_BEditorDeTextoActionPerformed

    private void BConvertidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BConvertidorActionPerformed
        if (convertidor == null) {
            convertidor = new Aplicaciones.convertir.Ventana_Principal();
        }
        //convertidor.setVisible(true);
        //  setVisible(false);
        alAbrirUnaAplicacion(convertidor);
    }//GEN-LAST:event_BConvertidorActionPerformed

    private void BCalculadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCalculadoraActionPerformed
        if (calculadoraInteligente == null) {
            calculadoraInteligente = new Aplicaciones.calculadora_inteligente.Calculadora_Inteligente();
        }
        //calculadoraInteligente.setVisible(true);
        //  setVisible(false);
        alAbrirUnaAplicacion(calculadoraInteligente);
    }//GEN-LAST:event_BCalculadoraActionPerformed

    private void BModificarExtencionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BModificarExtencionActionPerformed
        try {
            if (Aplicaciones.modificarextencion.ModificarExtencion.ventanaPrincipal == null) {
                new File("Data/Modificar Extencion").mkdirs();
                String DIRECCION_ESTADO_ACTUAL = Aplicaciones.modificarextencion.ModificarExtencion.DIRECCION_ESTADO_ACTUAL;
                String DIRECCION_ADMINISTRADOR_CONFIGURACIONES = Aplicaciones.modificarextencion.ModificarExtencion.DIRECCION_ADMINISTRADOR_CONFIGURACIONES;

                if (!new File(DIRECCION_ESTADO_ACTUAL).exists()) {
                    crearArchivo(DIRECCION_ESTADO_ACTUAL, new Aplicaciones.modificarextencion.EstadoActual());
                }
                if (!new File(DIRECCION_ADMINISTRADOR_CONFIGURACIONES).exists()) {
                    crearArchivo(DIRECCION_ADMINISTRADOR_CONFIGURACIONES, new Admininistrador_De_Guardado<TablasAmpliableConfiguracionDeVideo>());
                }
                if (Aplicaciones.modificarextencion.ModificarExtencion.ventanaPrincipal == null) {
                    Aplicaciones.modificarextencion.ModificarExtencion.ventanaPrincipal = new Aplicaciones.modificarextencion.Ventana_Principal();
                }

            }

            //ventanaPrincipal.setVisible(true);
            //  setVisible(false);
            alAbrirUnaAplicacion(Aplicaciones.modificarextencion.ModificarExtencion.ventanaPrincipal);
        } catch (Exception ex) {
            Visual.responerException(ex);
        }


    }//GEN-LAST:event_BModificarExtencionActionPerformed

    private void AbrioVentana(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_AbrioVentana
        seguridad();
    }//GEN-LAST:event_AbrioVentana

    private void ActivoVentana(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_ActivoVentana
        seguridad();
    }//GEN-LAST:event_ActivoVentana

    private void BLibreriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLibreriasActionPerformed
        try {
            new File("Data").mkdir();

            if (!new File(Aplicaciones.librerias.Librerias.DIRECCION_ULTIMO_ESTADO).exists()) {
                crearArchivo(Aplicaciones.librerias.Librerias.DIRECCION_ULTIMO_ESTADO, new Aplicaciones.librerias.Estado_Actual());
            }
            if (!new File(Aplicaciones.librerias.Librerias.DIRECCION_ADMINISTRADOR_DIRECCIONES).exists()) {
                crearArchivo(Aplicaciones.librerias.Librerias.DIRECCION_ADMINISTRADOR_DIRECCIONES, new Aplicaciones.librerias.Admininistrador_De_Guardado<File>());
            }
            Object o = Archivo.cargarArchivo(Aplicaciones.librerias.Librerias.DIRECCION_ULTIMO_ESTADO);
            //System.out.println("o= "+o.getClass());
            if (Aplicaciones.librerias.Librerias.ventana_Principal == null) {
                Aplicaciones.librerias.Librerias.ventana_Principal = new Aplicaciones.librerias.Ventana_Principal((Aplicaciones.librerias.Estado_Actual) o);
            }

            //  Aplicaciones.librerias.Librerias.ventana_Principal.setVisible(true);
            alAbrirUnaAplicacion(Aplicaciones.librerias.Librerias.ventana_Principal);
        } catch (Exception ex) {
            Visual.responerException(ex);
        }
        //  new Ventana_Principal().setVisible(true);

    }//GEN-LAST:event_BLibreriasActionPerformed

    private void BActualizadorDeSeriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BActualizadorDeSeriesActionPerformed
        if (Aplicaciones.actualizador.de.series.Ventana_Principal2.ventana_Principal2 == null) {
            Aplicaciones.actualizador.de.series.Ventana_Principal2.ventana_Principal2 = new Aplicaciones.actualizador.de.series.Ventana_Principal2();
        }

        alAbrirUnaAplicacion(Aplicaciones.actualizador.de.series.Ventana_Principal2.ventana_Principal2);
    }//GEN-LAST:event_BActualizadorDeSeriesActionPerformed

    private void BMoverNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BMoverNumeroActionPerformed
        try {
            if (Aplicaciones.MoverNumero.ModificarExtencion.ventanaPrincipal == null) {
                new File("Data/Mover Numero").mkdirs();
                String DIRECCION_ESTADO_ACTUAL = Aplicaciones.MoverNumero.ModificarExtencion.DIRECCION_ESTADO_ACTUAL;
                String DIRECCION_ADMINISTRADOR_CONFIGURACIONES = Aplicaciones.MoverNumero.ModificarExtencion.DIRECCION_ADMINISTRADOR_CONFIGURACIONES;

                if (!new File(DIRECCION_ESTADO_ACTUAL).exists()) {
                    crearArchivo(DIRECCION_ESTADO_ACTUAL, new Aplicaciones.MoverNumero.EstadoActual());
                }
                if (!new File(DIRECCION_ADMINISTRADOR_CONFIGURACIONES).exists()) {
                    crearArchivo(DIRECCION_ADMINISTRADOR_CONFIGURACIONES, new Admininistrador_De_Guardado<TablasAmpliableConfiguracionDeVideo>());
                }
                if (Aplicaciones.MoverNumero.ModificarExtencion.ventanaPrincipal == null) {
                    Aplicaciones.MoverNumero.ModificarExtencion.ventanaPrincipal = new Aplicaciones.MoverNumero.Ventana_Principal();
                }

            }

            //ventanaPrincipal.setVisible(true);
            //  setVisible(false);
            alAbrirUnaAplicacion(Aplicaciones.MoverNumero.ModificarExtencion.ventanaPrincipal);
        } catch (Exception ex) {
            Visual.responerException(ex);
        }
    }//GEN-LAST:event_BMoverNumeroActionPerformed

    private void BContenidoCarpetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BContenidoCarpetaActionPerformed
        if (Aplicaciones.Contenido_Carpeta.Ventana_Principal.ventana_Principal == null) {
            Aplicaciones.Contenido_Carpeta.Ventana_Principal.ventana_Principal = new Aplicaciones.Contenido_Carpeta.Ventana_Principal();
        }

        alAbrirUnaAplicacion(Aplicaciones.Contenido_Carpeta.Ventana_Principal.ventana_Principal);
    }//GEN-LAST:event_BContenidoCarpetaActionPerformed

    private void BCopiadorDeSubtitulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCopiadorDeSubtitulosActionPerformed
        if (Aplicaciones.Copiador_De_Subtitulos.Ventana_Principal.ventana_Principal == null) {
            Aplicaciones.Copiador_De_Subtitulos.Ventana_Principal.ventana_Principal = new Aplicaciones.Copiador_De_Subtitulos.Ventana_Principal();
        }

        alAbrirUnaAplicacion(Aplicaciones.Copiador_De_Subtitulos.Ventana_Principal.ventana_Principal);
    }//GEN-LAST:event_BCopiadorDeSubtitulosActionPerformed

    private void seguridad() {
        // System.out.println("seguridad");
        if (editorDeSubtitulos != null) {
            BEditorDeSubtitulo.setEnabled(!algunaEsVisible(true, Aplicaciones.editor_de_subtitulos3.Ventana_Principal.todasLasVentanas));
        }
        if (editorDeTexto != null) {
            BEditorDeTexto.setEnabled(!editorDeTexto.isVisible());
        }
        if (convertidor != null) {
            BConvertidor.setEnabled(!convertidor.isVisible());
        }
        if (calculadoraInteligente != null) {
            BCalculadora.setEnabled(!calculadoraInteligente.isVisible());
        }
        if (Aplicaciones.modificarextencion.ModificarExtencion.ventanaPrincipal != null) {
            // System.out.println("enable="+!algunaEsVisible(true, Aplicaciones.modificarextencion.ModificarExtencion.todasLasVentanas));
            BModificarExtencion.setEnabled(!algunaEsVisible(true, Aplicaciones.modificarextencion.ModificarExtencion.todasLasVentanas));
        }

        if (Aplicaciones.librerias.Librerias.ventana_Principal != null) {
            BLibrerias.setEnabled(!algunaEsVisible(true, Aplicaciones.librerias.Librerias.todasLasVentanas));
        }

        if (Aplicaciones.MoverNumero.ModificarExtencion.ventanaPrincipal != null) {
            BMoverNumero.setEnabled(!algunaEsVisible(true, Aplicaciones.MoverNumero.ModificarExtencion.todasLasVentanas));
        }
        if (Aplicaciones.actualizador.de.series.Ventana_Principal2.ventana_Principal2 != null) {
            BActualizadorDeSeries.setEnabled(!algunaEsVisible(true, Aplicaciones.actualizador.de.series.Ventana_Principal2.ventanas));
        }
        if (Aplicaciones.Contenido_Carpeta.Ventana_Principal.ventana_Principal != null) {
            BContenidoCarpeta.setEnabled(!algunaEsVisible(true, Aplicaciones.Contenido_Carpeta.Ventana_Principal.ventanas));
        }
        if (Aplicaciones.Copiador_De_Subtitulos.Ventana_Principal.ventana_Principal != null) {
            BCopiadorDeSubtitulos.setEnabled(!algunaEsVisible(true, Aplicaciones.Copiador_De_Subtitulos.Ventana_Principal.ventanas));
        }
    }

    private void minimizar() {
        minimizarVentana(this);
    }

    private void alAbrirUnaAplicacion(JFrame f) {
        f.setVisible(true);
        minimizar();
        seguridad();
    }

    public static void cerrar(JFrame f, int exepto) {
        if (algunaAplicacionActiva(exepto)) {
            f.setVisible(false);
        } else {
            System.exit(0);
        }
    }

    private static boolean algunaAplicacionActiva(int exepto) {
        for (int i = 0; i < ventanaTodo.B.length; i++) {
            if (i != exepto && !ventanaTodo.B[i].isEnabled()) {
                return true;
            }
        }

        return false;
    }

//    public static JButton[] getB() {
//        return B;
//    }
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
            java.util.logging.Logger.getLogger(Todo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Todo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Todo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Todo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Todo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BActualizadorDeSeries;
    private javax.swing.JButton BCalculadora;
    private javax.swing.JButton BContenidoCarpeta;
    private javax.swing.JButton BConvertidor;
    private javax.swing.JButton BCopiadorDeSubtitulos;
    private javax.swing.JButton BEditorDeSubtitulo;
    private javax.swing.JButton BEditorDeTexto;
    private javax.swing.JButton BLibrerias;
    private javax.swing.JButton BModificarExtencion;
    private javax.swing.JButton BMoverNumero;
    private javax.swing.JLabel LLeyen;
    private javax.swing.JPanel PTodo;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
