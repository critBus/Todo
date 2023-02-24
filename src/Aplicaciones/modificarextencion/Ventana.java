/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.modificarextencion;

import Utiles.MetodosUtiles.Arreglos;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Rene
 */
public class Ventana extends javax.swing.JFrame {

    Boolean bo[] = {true, true, false};
    String pro[][] = {{"asd", "zxc", "bnm"}, {"a", "b", "c"}};
    int nu[] = {0, 1, 2};
    LinkedList<String> excepciones, ejemplos;
    LinkedList<Integer> saltos;
    LinkedList<Boolean> activados;
    Object todo[][];

    /**
     * Creates new form Ventana
     */
    public Ventana() {
        initComponents();
        T.getColumnModel().getColumn(0).setMinWidth(20);
        T.getColumnModel().getColumn(0).setPreferredWidth(20);
        T.getColumnModel().getColumn(1).setMinWidth(20);
        T.getColumnModel().getColumn(1).setPreferredWidth(20);
        //  T.getModel().
        excepciones = new LinkedList<String>(Arrays.asList(pro[0]));
        ejemplos = new LinkedList<String>(Arrays.asList(pro[1]));
        saltos = new LinkedList<Integer>();
        activados = new LinkedList<Boolean>();
        for (int i = 0; i < nu.length; i++) {
            saltos.add(nu[i]);
            activados.add(bo[i]);
        }
        iniTodo();
        tabla();
        guardar();

//        S= new 
        // T.getColumnModel().getColumn(0).
        // T.getCellEditor(WIDTH, WIDTH).getCellEditorValue();
    }

    public void iniTodo() {
        todo = new Object[3][4];
        for (int i = 0; i < todo.length; i++) {
            //System.out.println("todo.length="+todo.length);
            todo[i][0] = bo[i];
            // System.out.println("bo[i]="+bo[i]);
            todo[i][1] = nu[i];
            // System.out.println("nu[i]="+nu[i]);
            todo[i][2] = pro[0][i];
            // System.out.println("pro[0][i]="+pro[0][i]);
            todo[i][3] = pro[1][i];
            // System.out.println("pro[1][i]="+pro[1][i]);
        }

    }

    public void tabla() {

        //  System.out.println("aaaaaaaaaaaaaaa");
        T.setModel(new javax.swing.table.DefaultTableModel(
                todo,
                new String[]{
                    "Act", "Salto", "Expciones", "Ejemplos"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        });
        for (int i = 0; i < 2; i++) {

            T.getCellEditor(T.getRowCount() - 1, 2 + i).addCellEditorListener(new CellEditorListener() {

                @Override
                public void editingStopped(ChangeEvent e) {
                    if ((getEjemplo(T.getRowCount() - 1) != null && !getEjemplo(T.getRowCount() - 1).isEmpty())
                            || (getExepcion(T.getRowCount() - 1) != null && !getExepcion(T.getRowCount() - 1).isEmpty())) {
                        addFila();
                    }
                }

                @Override
                public void editingCanceled(ChangeEvent e) {

                }

            });
        }System.out.println("T.getRowCount()="+T.getRowCount());
        int mul=26;
        jPanel1.add(JS, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 250, T.getRowCount()*mul));
        int max=40;
        T.getColumnModel().getColumn(0).setMinWidth(max);
        T.getColumnModel().getColumn(0).setPreferredWidth(max);
        T.getColumnModel().getColumn(0).setMaxWidth(max);
        T.getColumnModel().getColumn(1).setMinWidth(max);
        T.getColumnModel().getColumn(1).setPreferredWidth(max);
        T.getColumnModel().getColumn(1).setMaxWidth(max);
    }

    public void guardar() {
        guardar(T.getRowCount());
    }

    public void guardar(int filas) {
        todo = new Object[filas][4];
        for (int i = 0; i < filas; i++) {
            if (i < T.getRowCount()) {//System.out.println("todo.length="+todo.length);
                almacenar(i, 0, getActivado(i));
                // todo[i][0] = getActivado(i);
                // System.out.println("bo[i]="+bo[i]);
                almacenar(i, 1, getSalto(i));
                // todo[i][1] = getSalto(i);
                // System.out.println("nu[i]="+nu[i]);
                almacenar(i, 2, getExepcion(i));
                // todo[i][2] = getExepcion(i);
                // System.out.println("pro[0][i]="+pro[0][i]);
                almacenar(i, 3, getEjemplo(i));
                //todo[i][3] = getEjemplo(i);
                // System.out.println("pro[1][i]="+pro[1][i]);
            } else {
                todo[i][0] = false;
                todo[i][1] = 0;
            }

        }
//        System.out.println(T.getModel().getValueAt(0, 1));
//        System.out.println(T.getModel().getValueAt(2, 2));
    }

    private void almacenar(int fila, int columna, Object a) {
        if (a != null) {
            todo[fila][columna] = a;
        }
    }

    private boolean getActivado(int indice) {
        Object s = T.getModel().getValueAt(indice, 0);
        // return (boolean) T.getModel().getValueAt(indice, 0);
        return s == null ? false : (boolean) s;
    }

    private int getSalto(int indice) {
        // return (Integer) T.getModel().getValueAt(indice, 1);
        Object s = T.getModel().getValueAt(indice, 1);
        return s == null ? 0 : (Integer) s;
    }

    private String getExepcion(int indice) {
        //return null;
        return (String) T.getModel().getValueAt(indice, 2);
    }

    private String getEjemplo(int indice) {
        return (String) T.getModel().getValueAt(indice, 3);
    }

    public void addFila() {
        guardar(T.getRowCount() + 1);
        tabla();
       // Arreglos.MostrarMatrizObject(todo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JS = new javax.swing.JScrollPane();
        T = new javax.swing.JTable();
        S = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        T.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "null", "null"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        T.setColumnSelectionAllowed(true);
        T.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                soltarTeclado(evt);
            }
        });
        JS.setViewportView(T);
        T.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (T.getColumnModel().getColumnCount() > 0) {
            T.getColumnModel().getColumn(0).setResizable(false);
            T.getColumnModel().getColumn(0).setPreferredWidth(30);
            T.getColumnModel().getColumn(1).setResizable(false);
            T.getColumnModel().getColumn(2).setResizable(false);
            T.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel1.add(JS, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 240, 200));

        S.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        jPanel1.add(S, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, -1, -1));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jButton1)
                .addGap(55, 55, 55)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        addFila();
        System.out.println("T.getRowCount()="+T.getRowCount());
        int mul=26;
        jPanel1.add(JS, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 250, T.getRowCount()*mul));
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void soltarTeclado(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_soltarTeclado

    }//GEN-LAST:event_soltarTeclado

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.out.println(S.getValue());
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JS;
    private javax.swing.JSpinner S;
    private javax.swing.JTable T;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
