/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.editor_de_subtitulos3;

import static Utiles.MetodosUtiles.Visual.INT;
import static Utiles.MetodosUtiles.Visual.seguridadJTextField;
import static Aplicaciones.editor_de_subtitulos3.Ventana_Principal.*;
import Utiles.ClasesUtiles.Tiempo2.Tiempo;
import Utiles.Exepciones.IndiceFinalIncorrectoException;
import Utiles.Exepciones.IndiceIncorrectoException;
import Utiles.Exepciones.IndiceInicialIncorrectoException;
import static Utiles.MetodosUtiles.MetodosUtiles.or;
import static Utiles.MetodosUtiles.MetodosUtiles.orFalse;
import Utiles.MetodosUtiles.Visual;
import static Utiles.MetodosUtiles.Visual.*;

import java.awt.event.ActionEvent;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author Rene
 */
public class Ventana_Crear_Modificar extends javax.swing.JFrame {

    JTextField tiemposIniciales[], tiemposFinales[];

    /**
     * Creates new form Ventana_Crear_Modificar
     */
    public Ventana_Crear_Modificar() {
        initComponents();
        tiemposIniciales = new JTextField[]{THorasI, TMinutosI, TSegundosI, TMSegundosI};
        tiemposFinales = new JTextField[]{THorasF, TMinutosF, TSegundosF, TMSegundosF};

//        BGInicio.add(CDesdeElIndice);
//        BGInicio.add(CDesdeElInicio);
//        BGFin.add(CHastaElFinal);
//        BGFin.add(CHastaElIndice);
        setLocationRelativeTo(null);
        seguridad();
        todasLasVentanas[1]=this;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BGInicio = new javax.swing.ButtonGroup();
        BGFin = new javax.swing.ButtonGroup();
        PTodo = new javax.swing.JPanel();
        LDesdeCero = new javax.swing.JLabel();
        LModificar = new javax.swing.JLabel();
        LEliminar = new javax.swing.JLabel();
        LSecciones = new javax.swing.JLabel();
        BCrearNueva = new javax.swing.JButton();
        TIndiceFinal = new javax.swing.JTextField();
        TModificar = new javax.swing.JTextField();
        BModificar = new javax.swing.JButton();
        BEliminar = new javax.swing.JButton();
        BEliminarSecciomes = new javax.swing.JButton();
        CDesdeElIndice = new javax.swing.JCheckBox();
        CHastaElIndice = new javax.swing.JCheckBox();
        TEliminar = new javax.swing.JTextField();
        TIndiceInicio = new javax.swing.JTextField();
        BCancelar = new javax.swing.JButton();
        RDesdeElTiempo = new javax.swing.JRadioButton();
        THorasI = new javax.swing.JTextField();
        TMinutosI = new javax.swing.JTextField();
        TSegundosI = new javax.swing.JTextField();
        TMSegundosI = new javax.swing.JTextField();
        THorasF = new javax.swing.JTextField();
        TMinutosF = new javax.swing.JTextField();
        TSegundosF = new javax.swing.JTextField();
        TMSegundosF = new javax.swing.JTextField();
        RHastaElTiempo = new javax.swing.JRadioButton();
        LLogica = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Seccion");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                CerroVentana(evt);
            }
        });

        PTodo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LDesdeCero.setText("Crear una nueva seccion desde cero");
        PTodo.add(LDesdeCero, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, 20));

        LModificar.setText("Modificar una seccion existente en el indice");
        PTodo.add(LModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 20));

        LEliminar.setText(" Eliminar una seccion existente en el indice");
        PTodo.add(LEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 270, 20));

        LSecciones.setText("  Eliminar secciones desde:");
        PTodo.add(LSecciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 220, 20));

        BCrearNueva.setText("Crear Nueva");
        BCrearNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCrearNuevaActionPerformed(evt);
            }
        });
        PTodo.add(BCrearNueva, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 150, -1));

        TIndiceFinal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TIndiceFinal.setText("1");
        TIndiceFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TIndiceFinalActionPerformed(evt);
            }
        });
        TIndiceFinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                soltoElTeclado(evt);
            }
        });
        PTodo.add(TIndiceFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 40, -1));

        TModificar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TModificar.setText("1");
        TModificar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                soltoElTeclado(evt);
            }
        });
        PTodo.add(TModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 40, -1));

        BModificar.setText("Modificar");
        BModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BModificarActionPerformed(evt);
            }
        });
        PTodo.add(BModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 90, -1));

        BEliminar.setText("Eliminar");
        BEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEliminarActionPerformed(evt);
            }
        });
        PTodo.add(BEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 90, -1));

        BEliminarSecciomes.setText("Eliminar Secciomes");
        BEliminarSecciomes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEliminarSecciomesActionPerformed(evt);
            }
        });
        PTodo.add(BEliminarSecciomes, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 150, -1));

        CDesdeElIndice.setSelected(true);
        CDesdeElIndice.setText("desde el indice de");
        CDesdeElIndice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRadioButom(evt);
            }
        });
        PTodo.add(CDesdeElIndice, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        CHastaElIndice.setSelected(true);
        CHastaElIndice.setText("hasta el indice de");
        CHastaElIndice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRadioButom(evt);
            }
        });
        PTodo.add(CHastaElIndice, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        TEliminar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TEliminar.setText("1");
        TEliminar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                soltoElTeclado(evt);
            }
        });
        PTodo.add(TEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 40, -1));

        TIndiceInicio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TIndiceInicio.setText("1");
        TIndiceInicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                soltoElTeclado(evt);
            }
        });
        PTodo.add(TIndiceInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 40, -1));

        BCancelar.setText("Cancelar");
        BCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCancelarActionPerformed(evt);
            }
        });
        PTodo.add(BCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 110, 50));

        RDesdeElTiempo.setText(" Desde el tiempo ");
        RDesdeElTiempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRadioButom(evt);
            }
        });
        PTodo.add(RDesdeElTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, -1, -1));

        THorasI.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        THorasI.setText("0");
        THorasI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                soltoElTeclado(evt);
            }
        });
        PTodo.add(THorasI, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 30, -1));

        TMinutosI.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TMinutosI.setText("0");
        TMinutosI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                soltoElTeclado(evt);
            }
        });
        PTodo.add(TMinutosI, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 30, -1));

        TSegundosI.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TSegundosI.setText("0");
        TSegundosI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                soltoElTeclado(evt);
            }
        });
        PTodo.add(TSegundosI, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, 30, -1));

        TMSegundosI.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TMSegundosI.setText("0");
        TMSegundosI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                soltoElTeclado(evt);
            }
        });
        PTodo.add(TMSegundosI, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, 50, -1));

        THorasF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        THorasF.setText("0");
        THorasF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                soltoElTeclado(evt);
            }
        });
        PTodo.add(THorasF, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, 30, -1));

        TMinutosF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TMinutosF.setText("0");
        TMinutosF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                soltoElTeclado(evt);
            }
        });
        PTodo.add(TMinutosF, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, 30, -1));

        TSegundosF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TSegundosF.setText("0");
        TSegundosF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                soltoElTeclado(evt);
            }
        });
        PTodo.add(TSegundosF, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, 30, -1));

        TMSegundosF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TMSegundosF.setText("0");
        TMSegundosF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                soltoElTeclado(evt);
            }
        });
        PTodo.add(TMSegundosF, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 250, 50, -1));

        RHastaElTiempo.setText("Hasta el tiempo");
        RHastaElTiempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRadioButom(evt);
            }
        });
        PTodo.add(RHastaElTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, -1, -1));

        LLogica.setForeground(new java.awt.Color(204, 0, 0));
        PTodo.add(LLogica, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void apretoRadioButom(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apretoRadioButom
        selecionarRSecciones(evt);
        seguridad();
    }//GEN-LAST:event_apretoRadioButom

    private void BModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BModificarActionPerformed
        visualizarEditarSeccion(Integer.parseInt(TModificar.getText()) - 1);
    }//GEN-LAST:event_BModificarActionPerformed

    private void BCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCancelarActionPerformed
        visualizarVentanPrincipal();       // TODO add your handling code here:
    }//GEN-LAST:event_BCancelarActionPerformed

    private void BCrearNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCrearNuevaActionPerformed
        visualizarEditarSeccion();        // TODO add your handling code here:
    }//GEN-LAST:event_BCrearNuevaActionPerformed

    private void BEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEliminarActionPerformed
        S.eliminarSeccion(Integer.parseInt(TEliminar.getText()) - 1);
        seguridad();
        ventana_Principal.actualizarT();
        //  setVisible(false);
    }//GEN-LAST:event_BEliminarActionPerformed

    private void BEliminarSecciomesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEliminarSecciomesActionPerformed
        try {
            if (RDesdeElTiempo.isSelected() && RHastaElTiempo.isSelected()) {
                S.eliminarSeccionesEntre(getInferior(), getSuperior());
            } else {
                if (RDesdeElTiempo.isSelected()) {
                    S.eliminarDesde(getInferior());
                }
                if (RHastaElTiempo.isSelected()) {
                    S.eliminarHasta(getSuperior());
                }
            }

            if (CDesdeElIndice.isSelected() && CHastaElIndice.isSelected()) {
                S.eliminarSecciones(inT(TIndiceInicio) - 1, inT(TIndiceFinal) - 1);
            } else {
                if (CDesdeElIndice.isSelected()) {
                    S.eliminarSecciones(inT(TIndiceInicio) - 1, S.sise());
                }
                if (CHastaElIndice.isSelected()) {
                    S.eliminarSecciones(0, inT(TIndiceFinal) - 1);
                }
            }
            // S.eliminarSecciones(CDesdeElIndice.isSelected() ? Integer.parseInt(TIndiceInicio.getText()) - 1 : 0, CHastaElIndice.isSelected() ? Integer.parseInt(TIndiceFinal.getText()) - 1 : S.S.length);
        } catch (Exception ex) {
            Visual.responerException(ex);
        }
        seguridad();
        ventana_Principal.actualizarT();
        // setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_BEliminarSecciomesActionPerformed

    private void soltoElTeclado(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_soltoElTeclado
        seguridad();
    }//GEN-LAST:event_soltoElTeclado

    private void TIndiceFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TIndiceFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TIndiceFinalActionPerformed

    private void CerroVentana(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_CerroVentana
        setVisible(false);
        ventana_Principal.seguridad();
    }//GEN-LAST:event_CerroVentana
    private Tiempo getInferior() {
        return extraerTiempo(tiemposIniciales);
    }

    private Tiempo getSuperior() {
        return extraerTiempo(tiemposFinales);
    }

    private void selecionarRSecciones(ActionEvent evt) {//&& ((AbstractButton) evt.getSource()).isSelected()

        if (or(evt.getSource(), CDesdeElIndice, RDesdeElTiempo, CHastaElIndice, RHastaElTiempo)
                && algunoEstaSelecionadoExepto((AbstractButton) evt.getSource(), RDesdeElTiempo, RHastaElTiempo, CDesdeElIndice, CHastaElIndice)) {
            // System.out.println("((AbstractButton) evt.getSource()).isSelected()=" + ((AbstractButton) evt.getSource()).isSelected());
            // System.out.println("desactivar");
            // ((AbstractButton) evt.getSource()).setSelected(false);
        } else {
            ((AbstractButton) evt.getSource()).setSelected(true);
        }

        if (evt.getSource() == RDesdeElTiempo) {
            selecionar(false, CDesdeElIndice, CHastaElIndice);
            return;
        }
        if (evt.getSource() == RHastaElTiempo) {
            selecionar(false, CDesdeElIndice, CHastaElIndice);
            return;
        }
        if (evt.getSource() == CDesdeElIndice) {
            selecionar(false, RDesdeElTiempo, RHastaElTiempo);
            return;
        }
        if (evt.getSource() == CHastaElIndice) {
            selecionar(false, RDesdeElTiempo, RHastaElTiempo);
        }
    }

    private void activartiemposIniciales(boolean activar) {
        THorasI.setEnabled(activar);
        TMSegundosI.setEnabled(activar);
        TMinutosI.setEnabled(activar);
        TSegundosI.setEnabled(activar);
    }

    private void activartiemposFinales(boolean activar) {
        THorasF.setEnabled(activar);
        TMSegundosF.setEnabled(activar);
        TMinutosF.setEnabled(activar);
        TSegundosF.setEnabled(activar);
    }

    private void seguridad() {
        TIndiceInicio.setEnabled(CDesdeElIndice.isSelected());
        TIndiceFinal.setEnabled(CHastaElIndice.isSelected());
        activartiemposIniciales(RDesdeElTiempo.isSelected());
        activartiemposFinales(RHastaElTiempo.isSelected());
        seguridadJTextField(INT, true, 0, S.sise(), BModificar, TModificar);
        seguridadJTextField(INT, true, 0, S.sise(), BEliminar, TEliminar);
        boolean tiemposSeguros = true;
        LinkedList<JTextField> componentes = new LinkedList<JTextField>();
        if (CDesdeElIndice.isSelected() || CHastaElIndice.isSelected()) {
            if (CDesdeElIndice.isSelected()) {
                componentes.add(TIndiceInicio);
            }
            if (CHastaElIndice.isSelected()) {
                componentes.add(TIndiceFinal);
            }
            tiemposSeguros = seguridadJTextField(INT, true, 0, S.sise(), BEliminarSecciomes, componentes.toArray(new JTextField[]{}));
            if (tiemposSeguros && CDesdeElIndice.isSelected() && CHastaElIndice.isSelected()) {
                tiemposSeguros = seguridadLogicaIndices();
                actualizarLLogica(false, tiemposSeguros);
            }
        }
        if (RDesdeElTiempo.isSelected()) {
            tiemposSeguros = orFalse(tiemposSeguros, seguridadJTextField(new String[]{TIEMPO60, MSEGUNDOS, TIEMPO60, TIEMPO60}, new JComponent[]{BEliminarSecciomes}, THorasI, TMSegundosI, TMinutosI, TSegundosI));
//            if (tiemposSeguros) {
//                tiemposSeguros = getInferior().mayorIgualQue(S.getFirstTaimInicial());
//            }
        }
        if (RHastaElTiempo.isSelected()) {
            tiemposSeguros = orFalse(tiemposSeguros, seguridadJTextField(new String[]{TIEMPO60, MSEGUNDOS, TIEMPO60, TIEMPO60}, new JComponent[]{BEliminarSecciomes}, THorasF, TMSegundosF, TMinutosF, TSegundosF));
//            if (tiemposSeguros) {
//                tiemposSeguros = getSuperior().menorIgualQue(S.getLastTaimInicial());
//            }
        }
        if (tiemposSeguros && RDesdeElTiempo.isSelected() && RHastaElTiempo.isSelected()) {
            tiemposSeguros = seguridadLogicaTiempos();
            actualizarLLogica(true, tiemposSeguros);
        }
        if (tiemposSeguros && !LLogica.getText().isEmpty()) {
            LLogica.setText("");
        }
        activarJComponent(tiemposSeguros, BEliminarSecciomes);
    }

    private boolean seguridadLogicaTiempos() {
        Tiempo inferior = getInferior(), superior = getSuperior();
        return inferior.menorQue(superior);
    }

    private boolean seguridadLogicaIndices() {
        return inT(TIndiceInicio) < inT(TIndiceFinal);
    }

    private void actualizarLLogica(boolean esTiempos, boolean correcto) {
        LLogica.setText(correcto ? "" : "El " + (esTiempos ? "tiempo" : "indice") + " final tiene que ser mayor que el inicial");
    }

    private void visualizarEditarSeccion(int indice) {
        ventana_Editar_Seccion = new Ventana_Editar_Seccion(indice);
        ventana_Editar_Seccion.setVisible(true);
        setVisible(false);
    }

    private void visualizarEditarSeccion() {
        ventana_Editar_Seccion = new Ventana_Editar_Seccion();
        ventana_Editar_Seccion.setVisible(true);
        setVisible(false);
    }

    private void visualizarVentanPrincipal() {
        ventana_Principal.setVisible(true);
        setVisible(false);
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
            java.util.logging.Logger.getLogger(Ventana_Crear_Modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana_Crear_Modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana_Crear_Modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana_Crear_Modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_Crear_Modificar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BCancelar;
    private javax.swing.JButton BCrearNueva;
    private javax.swing.JButton BEliminar;
    private javax.swing.JButton BEliminarSecciomes;
    private javax.swing.ButtonGroup BGFin;
    private javax.swing.ButtonGroup BGInicio;
    private javax.swing.JButton BModificar;
    private javax.swing.JCheckBox CDesdeElIndice;
    private javax.swing.JCheckBox CHastaElIndice;
    private javax.swing.JLabel LDesdeCero;
    private javax.swing.JLabel LEliminar;
    private javax.swing.JLabel LLogica;
    private javax.swing.JLabel LModificar;
    private javax.swing.JLabel LSecciones;
    private javax.swing.JPanel PTodo;
    private javax.swing.JRadioButton RDesdeElTiempo;
    private javax.swing.JRadioButton RHastaElTiempo;
    private javax.swing.JTextField TEliminar;
    private javax.swing.JTextField THorasF;
    private javax.swing.JTextField THorasI;
    private javax.swing.JTextField TIndiceFinal;
    private javax.swing.JTextField TIndiceInicio;
    private javax.swing.JTextField TMSegundosF;
    private javax.swing.JTextField TMSegundosI;
    private javax.swing.JTextField TMinutosF;
    private javax.swing.JTextField TMinutosI;
    private javax.swing.JTextField TModificar;
    private javax.swing.JTextField TSegundosF;
    private javax.swing.JTextField TSegundosI;
    // End of variables declaration//GEN-END:variables
}
