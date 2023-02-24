/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.editor_de_subtitulos3;

//import Aplicaciones.editor_de_subtitulos2.*;
import static Utiles.MetodosUtiles.Visual.*;

import static Utiles.MetodosUtiles.Arreglos.arregloObject;
import static Utiles.MetodosUtiles.MetodosUtiles.iguales;
import static Utiles.MetodosUtiles.MetodosUtiles.orFalse;
import static Aplicaciones.editor_de_subtitulos3.Ventana_Principal.*;
import Utiles.ClasesUtiles.Tiempo2.Tiempo;
import static Utiles.MetodosUtiles.MetodosUtiles.or;
//import Utiles.ClasesUtiles.Tiempo;
import static Utiles.MetodosUtiles.Visual.INT;
import static Utiles.MetodosUtiles.Visual.seguridadJTextField;
import java.awt.event.ActionEvent;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
//import java.awt.Point;

/**
 *
 * @author Rene
 */
public class Ventana_Aumento2 extends javax.swing.JFrame {

    static Tiempo diferenciaIsquierda = new Tiempo(0, 0, 0, 0,true);
    static Tiempo diferenciaDerecha = new Tiempo(0, 0, 0, 0,true);
    int hora = 0;
    int minuto = 0;
    int segundo = 0;
    int mSegundo = 0;
    int indice = 0;
    JTextField tiemposIniciales[], tiemposFinales[], tiemposAumento[];

    public Ventana_Aumento2() {
        initComponents();
        //  setLocation(300, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        tiemposIniciales = new JTextField[]{THorasI, TMinutosI, TSegundosI, TMSegundosI};
        tiemposFinales = new JTextField[]{THorasF, TMinutosF, TSegundosF, TMSegundosF};
        tiemposAumento = new JTextField[]{THoras, TMinutos, TSegundos, TMilisegundos};
        seguridad();
        setLocationRelativeTo(null);
        todasLasVentanas[4]=this;
    }

    public boolean seguridad() {
        // selecionarRSecciones();
        activarSiEstaSeleccionado(RModificarSoloLaSeccionEnElIndice, TIndice);
        BAvanzado.setEnabled(!(ventana_Calcular != null && ventana_Calcular.isVisible()));
        activartiemposIniciales(RDesdeElTiempo.isSelected());
        activartiemposFinales(RHastaElTiempo.isSelected());
        TIndiceInicial.setEnabled(RDesdeElIndice.isSelected());
        TIndiceFinal.setEnabled(RHastaElIndice.isSelected());
        boolean tiemposSeguros = seguridadJTextField(new String[]{TIEMPO60, MSEGUNDOS, TIEMPO60, TIEMPO60}, new JComponent[]{BMas, BMenos}, THoras, TMilisegundos, TMinutos, TSegundos);

        // boolean indiceSeguro = true;
        if (RModificarSoloLaSeccionEnElIndice.isSelected()) {
            tiemposSeguros = orFalse(tiemposSeguros, seguridadJTextField(INT, true, 0, S.sise(), new JComponent[]{BMas, BMenos}, TIndice));
        }
        if (RDesdeElTiempo.isSelected()) {
            tiemposSeguros = orFalse(tiemposSeguros, seguridadJTextField(new String[]{TIEMPO60, MSEGUNDOS, TIEMPO60, TIEMPO60}, new JComponent[]{BMas, BMenos}, THorasI, TMSegundosI, TMinutosI, TSegundosI));
//            if (tiemposSeguros) {
//                tiemposSeguros = getInferior().mayorIgualQue(S.getFirstTaimInicial());
//            }
        }
        if (RHastaElTiempo.isSelected()) {
            tiemposSeguros = orFalse(tiemposSeguros, seguridadJTextField(new String[]{TIEMPO60, MSEGUNDOS, TIEMPO60, TIEMPO60}, new JComponent[]{BMas, BMenos}, THorasF, TMSegundosF, TMinutosF, TSegundosF));
//            if (tiemposSeguros) {
//                tiemposSeguros = getSuperior().menorIgualQue(S.getLastTaimInicial());
//            }
        }
        if (tiemposSeguros && RDesdeElTiempo.isSelected() && RHastaElTiempo.isSelected()) {
            tiemposSeguros = seguridadLogicaTiempos();
            actualizarLLogica(true, tiemposSeguros);
        }
        if (RDesdeElIndice.isSelected()) {
            tiemposSeguros = orFalse(tiemposSeguros, seguridadJTextField(INT, true, 0, S.sise(), new JComponent[]{BMas, BMenos}, TIndiceInicial));
        }
        if (RHastaElIndice.isSelected()) {
            tiemposSeguros = orFalse(tiemposSeguros, seguridadJTextField(INT, true, 0, S.sise(), new JComponent[]{BMas, BMenos}, TIndiceFinal));
        }
        if (tiemposSeguros && RDesdeElIndice.isSelected() && RHastaElIndice.isSelected()) {
            tiemposSeguros = seguridadLogicaIndices();
            actualizarLLogica(false, tiemposSeguros);
        }
        if (tiemposSeguros && !LLogica.getText().isEmpty()) {
            LLogica.setText("");
        }
        activarJComponent(tiemposSeguros && !sonTodosCero(tiemposAumento), BMas, BMenos);
        return tiemposSeguros && !sonTodosCero(tiemposAumento);
    }

    private boolean seguridadLogicaTiempos() {
        return getInferior().menorQue(getSuperior());
    }

    private boolean seguridadLogicaIndices() {
        return inT(TIndiceInicial) < inT(TIndiceFinal);
    }

    private void actualizarLLogica(boolean esTiempos, boolean correcto) {
        LLogica.setText(correcto ? "" : "El " + (esTiempos ? "tiempo" : "indice") + " final tiene que ser mayor que el inicial");
    }

    public void reiniciarDiferencias() {
        diferenciaIsquierda = new Tiempo(0, 0, 0, 0,true);
        diferenciaDerecha = new Tiempo(0, 0, 0, 0,true);

    }

    public void actualizarLD() {
        LDiferencia.setText(diferenciaIsquierda.toString().equals(diferenciaDerecha.toString()) ? diferenciaIsquierda.toString() : diferenciaIsquierda.toString() + "   " + diferenciaDerecha.toString());
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

    //******
// if (seguridad()) {
//            hora = Integer.parseInt(THoras.getText());
//            minuto = Integer.parseInt(TMinutos.getText());
//            segundo = Integer.parseInt(TSegundos.getText());
//            mSegundo = Integer.parseInt(TMilisegundos.getText());
//            indice = Integer.parseInt(TIndice.getText());
//        }
    //******
// selecionarRSecciones(evt);
//        seguridad();
    //*******
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RBtiempos = new javax.swing.ButtonGroup();
        ScrollPanePrincipal = new javax.swing.JScrollPane();
        PTodo = new javax.swing.JPanel();
        LHoras = new javax.swing.JLabel();
        LMinutos = new javax.swing.JLabel();
        LSegundos = new javax.swing.JLabel();
        LMilisegundos = new javax.swing.JLabel();
        THoras = new javax.swing.JTextField();
        TMinutos = new javax.swing.JTextField();
        TSegundos = new javax.swing.JTextField();
        TMilisegundos = new javax.swing.JTextField();
        RMtif = new javax.swing.JRadioButton();
        RMti = new javax.swing.JRadioButton();
        RMtodoElSubtitulo = new javax.swing.JRadioButton();
        RModificarSoloLaSeccionEnElIndice = new javax.swing.JRadioButton();
        TIndice = new javax.swing.JTextField();
        RMtf = new javax.swing.JRadioButton();
        BAtras = new javax.swing.JButton();
        BMas = new javax.swing.JButton();
        BAvanzado = new javax.swing.JButton();
        BMenos = new javax.swing.JButton();
        LDiferencia = new javax.swing.JLabel();
        TMinutosI = new javax.swing.JTextField();
        TIndiceInicial = new javax.swing.JTextField();
        TSegundosI = new javax.swing.JTextField();
        TMSegundosI = new javax.swing.JTextField();
        RDesdeElTiempo = new javax.swing.JRadioButton();
        RHastaElTiempo = new javax.swing.JRadioButton();
        THorasF = new javax.swing.JTextField();
        TMinutosF = new javax.swing.JTextField();
        TSegundosF = new javax.swing.JTextField();
        TMSegundosF = new javax.swing.JTextField();
        RDesdeElIndice = new javax.swing.JRadioButton();
        RHastaElIndice = new javax.swing.JRadioButton();
        THorasI = new javax.swing.JTextField();
        TIndiceFinal = new javax.swing.JTextField();
        LLogica = new javax.swing.JLabel();

        RBtiempos.add(RMtf);
        RBtiempos.add(RMti);
        RBtiempos.add(RMtif);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sumar o Restar Tiempo");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                CerroVentana(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PTodo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LHoras.setText("Horas");
        PTodo.add(LHoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, -1, -1));

        LMinutos.setText("Minutos");
        PTodo.add(LMinutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, -1));

        LSegundos.setText("Segundos");
        PTodo.add(LSegundos, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, -1, -1));

        LMilisegundos.setText("Milisegundos");
        PTodo.add(LMilisegundos, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, -1));

        THoras.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        THoras.setText("0");
        THoras.setToolTipText("Horas\n");
        THoras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AntesDeEscribir(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DespuesDeEscribir(evt);
            }
        });
        PTodo.add(THoras, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 30, -1));

        TMinutos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TMinutos.setText("0");
        TMinutos.setToolTipText("Minutos");
        TMinutos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AntesDeEscribir(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DespuesDeEscribir(evt);
            }
        });
        PTodo.add(TMinutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 30, -1));

        TSegundos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TSegundos.setText("0");
        TSegundos.setToolTipText("Segundos");
        TSegundos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AntesDeEscribir(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DespuesDeEscribir(evt);
            }
        });
        PTodo.add(TSegundos, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 30, -1));

        TMilisegundos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TMilisegundos.setText("0");
        TMilisegundos.setToolTipText("Milisegundos");
        TMilisegundos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AntesDeEscribir(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DespuesDeEscribir(evt);
            }
        });
        PTodo.add(TMilisegundos, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 40, -1));

        RMtif.setSelected(true);
        RMtif.setText("Modificar el tiempo inicial y final");
        PTodo.add(RMtif, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        RMti.setText("Solo el tiempo inicial");
        PTodo.add(RMti, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        RMtodoElSubtitulo.setSelected(true);
        RMtodoElSubtitulo.setText("Modificar todo el subtitulo");
        RMtodoElSubtitulo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ApretarModificarSeccion2(evt);
            }
        });
        RMtodoElSubtitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApretarModificarSeccion(evt);
            }
        });
        RMtodoElSubtitulo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                seleccionoSecciones(evt);
            }
        });
        PTodo.add(RMtodoElSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, -1, -1));

        RModificarSoloLaSeccionEnElIndice.setText("Solo la seccion en el indice ");
        RModificarSoloLaSeccionEnElIndice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApretarModificarSeccion(evt);
            }
        });
        RModificarSoloLaSeccionEnElIndice.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                seleccionoSecciones(evt);
            }
        });
        RModificarSoloLaSeccionEnElIndice.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ApretarModificarSeccion2(evt);
            }
        });
        PTodo.add(RModificarSoloLaSeccionEnElIndice, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, -1, -1));

        TIndice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TIndice.setText("1");
        TIndice.setToolTipText("Indice a modificar\n\n");
        TIndice.setEnabled(false);
        TIndice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AntesDeEscribir(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DespuesDeEscribir(evt);
            }
        });
        PTodo.add(TIndice, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, 50, -1));

        RMtf.setText("Solo el tiempo final");
        PTodo.add(RMtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        BAtras.setText("Atras");
        BAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrecionarCancelar(evt);
            }
        });
        PTodo.add(BAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, -1));

        BMas.setText("+");
        BMas.setToolTipText("Aumentar tiempo a subtitulo");
        BMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrecionarMas(evt);
            }
        });
        PTodo.add(BMas, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 343, 70, 70));

        BAvanzado.setText("Avanzado");
        BAvanzado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrecionarAvanzado(evt);
            }
        });
        PTodo.add(BAvanzado, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 370, -1, -1));

        BMenos.setText("-");
        BMenos.setToolTipText("Restar tiempo a subtitulo");
        BMenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrecionarMenos(evt);
            }
        });
        PTodo.add(BMenos, new org.netbeans.lib.awtextra.AbsoluteConstraints(157, 343, 70, 70));
        PTodo.add(LDiferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, -1, -1));

        TMinutosI.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TMinutosI.setText("0");
        TMinutosI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DespuesDeEscribir(evt);
            }
        });
        PTodo.add(TMinutosI, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 30, -1));

        TIndiceInicial.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TIndiceInicial.setText("1");
        TIndiceInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DespuesDeEscribir(evt);
            }
        });
        PTodo.add(TIndiceInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 50, -1));

        TSegundosI.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TSegundosI.setText("0");
        TSegundosI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DespuesDeEscribir(evt);
            }
        });
        PTodo.add(TSegundosI, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 30, -1));

        TMSegundosI.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TMSegundosI.setText("0");
        TMSegundosI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DespuesDeEscribir(evt);
            }
        });
        PTodo.add(TMSegundosI, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 40, -1));

        RDesdeElTiempo.setText(" Desde el tiempo ");
        RDesdeElTiempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApretarModificarSeccion(evt);
            }
        });
        PTodo.add(RDesdeElTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        RHastaElTiempo.setText("Hasta el tiempo");
        RHastaElTiempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApretarModificarSeccion(evt);
            }
        });
        PTodo.add(RHastaElTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, -1, -1));

        THorasF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        THorasF.setText("0");
        THorasF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DespuesDeEscribir(evt);
            }
        });
        PTodo.add(THorasF, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, 30, -1));

        TMinutosF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TMinutosF.setText("0");
        TMinutosF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DespuesDeEscribir(evt);
            }
        });
        PTodo.add(TMinutosF, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, 30, -1));

        TSegundosF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TSegundosF.setText("0");
        TSegundosF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DespuesDeEscribir(evt);
            }
        });
        PTodo.add(TSegundosF, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, 30, -1));

        TMSegundosF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TMSegundosF.setText("0");
        TMSegundosF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DespuesDeEscribir(evt);
            }
        });
        PTodo.add(TMSegundosF, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 240, 40, -1));

        RDesdeElIndice.setText("Desde el indice");
        RDesdeElIndice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApretarModificarSeccion(evt);
            }
        });
        RDesdeElIndice.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                seleccionoSecciones(evt);
            }
        });
        RDesdeElIndice.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ApretarModificarSeccion2(evt);
            }
        });
        PTodo.add(RDesdeElIndice, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        RHastaElIndice.setText("Hasta el indice");
        RHastaElIndice.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ApretarModificarSeccion2(evt);
            }
        });
        RHastaElIndice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApretarModificarSeccion(evt);
            }
        });
        RHastaElIndice.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                seleccionoSecciones(evt);
            }
        });
        PTodo.add(RHastaElIndice, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, -1, -1));

        THorasI.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        THorasI.setText("0");
        THorasI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DespuesDeEscribir(evt);
            }
        });
        PTodo.add(THorasI, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 30, -1));

        TIndiceFinal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        TIndiceFinal.setText("1");
        TIndiceFinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DespuesDeEscribir(evt);
            }
        });
        PTodo.add(TIndiceFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, 50, -1));

        LLogica.setForeground(new java.awt.Color(204, 0, 0));
        PTodo.add(LLogica, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 180, 410, -1));

        ScrollPanePrincipal.setViewportView(PTodo);

        getContentPane().add(ScrollPanePrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DespuesDeEscribir(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DespuesDeEscribir
        //  if (seguridadJTextField("int", evt, arregloObject(BMenos, BMas), TIndice, THoras, TMinutos, TSegundos, TMilisegundos)) {
        if (seguridad()) {
            hora = Integer.parseInt(THoras.getText());
            minuto = Integer.parseInt(TMinutos.getText());
            segundo = Integer.parseInt(TSegundos.getText());
            mSegundo = Integer.parseInt(TMilisegundos.getText());
            indice = Integer.parseInt(TIndice.getText());
        }

    }//GEN-LAST:event_DespuesDeEscribir

    private void ApretarModificarSeccion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApretarModificarSeccion
        selecionarRSecciones(evt);
        seguridad();

    }//GEN-LAST:event_ApretarModificarSeccion

    private void PrecionarCancelar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrecionarCancelar
        visualizarVentanPrincipal();
    }//GEN-LAST:event_PrecionarCancelar

    private void PrecionarAvanzado(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrecionarAvanzado
        visualizarCalcular();
        seguridad();
        ventana_Principal.seguridad();
    }//GEN-LAST:event_PrecionarAvanzado

    private void PrecionarMenos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrecionarMenos
        AumentarDisminuir(false, indice);
        //  visualizarVentanPrincipal();
    }//GEN-LAST:event_PrecionarMenos

    private void PrecionarMas(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrecionarMas
        AumentarDisminuir(true, indice);
        // visualizarVentanPrincipal();
    }//GEN-LAST:event_PrecionarMas

    private void AntesDeEscribir(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AntesDeEscribir
        //eliminarTextoPredeterminado(evt, "0", THoras, TMilisegundos, TMinutos, TSegundos);
    }//GEN-LAST:event_AntesDeEscribir

    private void CerroVentana(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_CerroVentana
        // System.out.println("se cerro");
        setVisible(false);
        ventana_Principal.seguridad();
        // System.exit(0);
    }//GEN-LAST:event_CerroVentana

    private void ApretarModificarSeccion2(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ApretarModificarSeccion2
        //((AbstractButton) evt.getSource()).setSelected(true);
        // seguridad();
    }//GEN-LAST:event_ApretarModificarSeccion2

    private void seleccionoSecciones(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_seleccionoSecciones

    }//GEN-LAST:event_seleccionoSecciones

    private void selecionarRSecciones(ActionEvent evt) {//&& ((AbstractButton) evt.getSource()).isSelected()

        if (or(evt.getSource(), RDesdeElIndice, RDesdeElTiempo, RHastaElIndice, RHastaElTiempo)
                && algunoEstaSelecionadoExepto((AbstractButton) evt.getSource(), RMtodoElSubtitulo, RModificarSoloLaSeccionEnElIndice, RDesdeElTiempo, RHastaElTiempo, RDesdeElIndice, RHastaElIndice)) {
            // System.out.println("((AbstractButton) evt.getSource()).isSelected()=" + ((AbstractButton) evt.getSource()).isSelected());
            // System.out.println("desactivar");
            // ((AbstractButton) evt.getSource()).setSelected(false);
        } else {
            ((AbstractButton) evt.getSource()).setSelected(true);
        }

        if (evt.getSource() == RMtodoElSubtitulo) {
            selecionar(false, RModificarSoloLaSeccionEnElIndice, RDesdeElTiempo, RHastaElTiempo, RDesdeElIndice, RHastaElIndice);
            return;
        }
        if (evt.getSource() == RModificarSoloLaSeccionEnElIndice) {
            selecionar(false, RMtodoElSubtitulo, RDesdeElTiempo, RHastaElTiempo, RDesdeElIndice, RHastaElIndice);
            return;
        }
        if (evt.getSource() == RDesdeElTiempo) {
            selecionar(false, RModificarSoloLaSeccionEnElIndice, RMtodoElSubtitulo, RDesdeElIndice, RHastaElIndice);
            return;
        }
        if (evt.getSource() == RHastaElTiempo) {
            selecionar(false, RMtodoElSubtitulo, RModificarSoloLaSeccionEnElIndice, RDesdeElIndice, RHastaElIndice);
            return;
        }
        if (evt.getSource() == RDesdeElIndice) {
            selecionar(false, RMtodoElSubtitulo, RModificarSoloLaSeccionEnElIndice, RDesdeElTiempo, RHastaElTiempo);
            return;
        }
        if (evt.getSource() == RHastaElIndice) {
            selecionar(false, RMtodoElSubtitulo, RModificarSoloLaSeccionEnElIndice, RDesdeElTiempo, RHastaElTiempo);
        }
    }

    private void visualizarCalcular() {
        if (ventana_Calcular == null) {
            ventana_Calcular = new Ventana_Calcular();
        }
        ventana_Calcular.setVisible(true);
        //setVisible(false);

    }

    private void visualizarVentanPrincipal() {
        ventana_Principal.setVisible(true);
        setVisible(false);
    }

    private Tiempo getInferior() {
        return extraerTiempo(tiemposIniciales);
    }

    private Tiempo getSuperior() {
        return extraerTiempo(tiemposFinales);
    }

    private Tiempo getAumento() {
        return extraerTiempo(tiemposAumento);
    }

    private void AumentarDisminuir(boolean aumentar, int indice) {
        // Tiempo isquierda = new Tiempo(hora, minuto, segundo, mSegundo), derecha = new Tiempo(hora, minuto, segundo, mSegundo), inferior, superior;
        Tiempo isquierda = getAumento(), derecha = getAumento();
        if (!aumentar) {
            isquierda.volverNegativo();
            derecha.volverNegativo();
        }
        if (RMti.isSelected()) {
            isquierda.todoACero();
        }
        if (RMtf.isSelected()) {
            derecha.todoACero();
        }//|| RHastaElTiempo.isSelected()
        if (RDesdeElTiempo.isSelected() && RHastaElTiempo.isSelected()) {
            S.aumentarEntre(getInferior(), getSuperior(), isquierda, derecha);
        } else {
            if (RDesdeElTiempo.isSelected()) {
                S.aumentarDesde(getInferior(), isquierda, derecha);
            }
            if (RHastaElTiempo.isSelected()) {
                S.aumentarHasta(getSuperior(), isquierda, derecha);
            }
        }

        if (RDesdeElIndice.isSelected() && RHastaElIndice.isSelected()) {
            S.aumentarEntre(inT(TIndiceInicial), inT(TIndiceFinal), isquierda, derecha);
        } else {
            if (RDesdeElIndice.isSelected()) {
                S.aumentarDesde(inT(TIndiceInicial), isquierda, derecha);
            }
            if (RHastaElIndice.isSelected()) {
                S.aumentarHasta(inT(TIndiceFinal), isquierda, derecha);
            }
        }

        if (RModificarSoloLaSeccionEnElIndice.isSelected()) {
            S.aumentar(inT(TIndice), isquierda, derecha);
        }
        if (RMtodoElSubtitulo.isSelected()) {
            S.aumentar(isquierda, derecha);
        }
        S.ordernar();
        diferenciaIsquierda.aumentarConNegativos(isquierda);
        diferenciaDerecha.aumentarConNegativos(derecha);
//        if (aumentar) {
//            if (RMtodoElSubtitulo.isSelected()) {
//                S.aumentar(!RMtf.isSelected() ? hora : 0, !RMtf.isSelected() ? minuto : 0, !RMtf.isSelected() ? segundo : 0, !RMtf.isSelected() ? mSegundo : 0, !RMti.isSelected() ? hora : 0, !RMti.isSelected() ? minuto : 0, !RMti.isSelected() ? segundo : 0, !RMti.isSelected() ? mSegundo : 0);
//                if (!RMtf.isSelected()) {
//                    diferenciaIsquierda.aumentar(hora, minuto, segundo, mSegundo);
//                }
//                if (!RMti.isSelected()) {
//                    diferenciaDerecha.aumentar(hora, minuto, segundo, mSegundo);
//                }
//            } else {
//                S.aumentarIndice(indice, !RMtf.isSelected() ? hora : 0, !RMtf.isSelected() ? minuto : 0, !RMtf.isSelected() ? segundo : 0, !RMtf.isSelected() ? mSegundo : 0, !RMti.isSelected() ? hora : 0, !RMti.isSelected() ? minuto : 0, !RMti.isSelected() ? segundo : 0, !RMti.isSelected() ? mSegundo : 0);
//            }
//
//        } else {
//            if (RMtodoElSubtitulo.isSelected()) {
//                S.restar(!RMtf.isSelected() ? hora : 0, !RMtf.isSelected() ? minuto : 0, !RMtf.isSelected() ? segundo : 0, !RMtf.isSelected() ? mSegundo : 0, !RMti.isSelected() ? hora : 0, !RMti.isSelected() ? minuto : 0, !RMti.isSelected() ? segundo : 0, !RMti.isSelected() ? mSegundo : 0);
//                if (!RMtf.isSelected()) {
//                    diferenciaIsquierda.restar(hora, minuto, segundo, mSegundo);
//                }
//                if (!RMti.isSelected()) {
//                    diferenciaDerecha.restar(hora, minuto, segundo, mSegundo);
//                }
//            } else {
//                S.restarIndice(indice, !RMtf.isSelected() ? hora : 0, !RMtf.isSelected() ? minuto : 0, !RMtf.isSelected() ? segundo : 0, !RMtf.isSelected() ? mSegundo : 0, !RMti.isSelected() ? hora : 0, !RMti.isSelected() ? minuto : 0, !RMti.isSelected() ? segundo : 0, !RMti.isSelected() ? mSegundo : 0);
//            }
//        }
        actualizarLD();
        if (!iguales(0, hora, minuto, segundo, mSegundo)) {
            ventana_Principal.actualizarT();
        }
    }

    public void escribir(Tiempo t) {
        THoras.setText(t.getHora() + "");
        TMinutos.setText(t.getMinuto() + "");
        TSegundos.setText(t.getSegundo() + "");
        TMilisegundos.setText(t.getMSegundo() + "");
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
            java.util.logging.Logger.getLogger(Ventana_Aumento2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana_Aumento2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana_Aumento2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana_Aumento2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_Aumento2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAtras;
    private javax.swing.JButton BAvanzado;
    private javax.swing.JButton BMas;
    private javax.swing.JButton BMenos;
    private javax.swing.JLabel LDiferencia;
    private javax.swing.JLabel LHoras;
    private javax.swing.JLabel LLogica;
    private javax.swing.JLabel LMilisegundos;
    private javax.swing.JLabel LMinutos;
    private javax.swing.JLabel LSegundos;
    private javax.swing.JPanel PTodo;
    private javax.swing.ButtonGroup RBtiempos;
    private javax.swing.JRadioButton RDesdeElIndice;
    private javax.swing.JRadioButton RDesdeElTiempo;
    private javax.swing.JRadioButton RHastaElIndice;
    private javax.swing.JRadioButton RHastaElTiempo;
    private javax.swing.JRadioButton RModificarSoloLaSeccionEnElIndice;
    private javax.swing.JRadioButton RMtf;
    private javax.swing.JRadioButton RMti;
    private javax.swing.JRadioButton RMtif;
    private javax.swing.JRadioButton RMtodoElSubtitulo;
    private javax.swing.JScrollPane ScrollPanePrincipal;
    private javax.swing.JTextField THoras;
    private javax.swing.JTextField THorasF;
    private javax.swing.JTextField THorasI;
    private javax.swing.JTextField TIndice;
    private javax.swing.JTextField TIndiceFinal;
    private javax.swing.JTextField TIndiceInicial;
    private javax.swing.JTextField TMSegundosF;
    private javax.swing.JTextField TMSegundosI;
    private javax.swing.JTextField TMilisegundos;
    private javax.swing.JTextField TMinutos;
    private javax.swing.JTextField TMinutosF;
    private javax.swing.JTextField TMinutosI;
    private javax.swing.JTextField TSegundos;
    private javax.swing.JTextField TSegundosF;
    private javax.swing.JTextField TSegundosI;
    // End of variables declaration//GEN-END:variables
}
