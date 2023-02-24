/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.actualizador.de.series.Catalogos.Secciones;

import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Multimedia.Series.CatalogoDeSeries;
import Aplicaciones.actualizador.de.series.Catalogos.*;
import Aplicaciones.actualizador.de.series.*;
import Aplicaciones.actualizador.de.series.Secciones.*;
import static Aplicaciones.actualizador.de.series.Secciones.estadoPersonalizado.*;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.Serializable;
//import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rene
 */
public class CatalogosDeSeriesEntrada extends ConjuntoEntradaCatalogosDeSeries {
//  BaseTodo
   //bbbb
//    private CatalogosDeSeriesBasicos basicos;
//    private CatalogoDeSeries Entrada, Seguidos, Extrenos, EnEspera, PorVer, Todo, Finalizadas;
    private CatalogoDeSeries personalizadosSeleccionados;
    private CatalogoDeSeries personalizadosRelacionados;

//    private FiltroSerie filtro;
    private SeccionSerie entradaCorrespondiente;

    private CatalogoDeSeries Entrada;

    public CatalogosDeSeriesEntrada(CatalogosDeSeriesBasicos basicos, SeccionSerie seccionCorrespondiente, FiltroSerie filtro) {
        this.basicos = basicos;
        this.filtro = filtro;
        this.entradaCorrespondiente = seccionCorrespondiente;
    }

    public SeccionSerie getEntradaCorrespondiente() {
        return entradaCorrespondiente;
    }

    public void setEntradaCorrespondiente(SeccionSerie entradaCorrespondiente) {
        this.entradaCorrespondiente = entradaCorrespondiente;
    }

    @Override
    public CatalogoDeSeries actualizarCatalogoSegunFiltro(CatalogoDeSeries c) {
        return CatalogosDeSeriesBasicos.actualizarCatalogoSegunFiltro(basicos, c, filtro);
    }

  
    public void actualizarEntrada(ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
//        Entrada = basicos.getEntrada(cdv);
//        System.out.println("** 0");
        Entrada = basicos.getEntradaElegida(entradaCorrespondiente, cdv);
//        System.out.println("** 1");
        Entrada = actualizarCatalogoSegunFiltro(Entrada);
    }

    @Override
    public void actualizarSeguidos(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        Seguidos = basicos.getSeguidos().getCatalogoRelacionado(Entrada);
        //  Seguidos = actualizarCatalogoSegunFiltro(Seguidos);
    }

    @Override
    public void actualizarExtrenos(ConfiguracionDeVideo cdv) throws FileNotFoundException {
//       Entrada.imprimir();
       
        Extrenos = Entrada.getCatalogoSeriesConPrimerosCapitulos();
//        Extrenos.imprimir();
        //  Extrenos = actualizarCatalogoSegunFiltro(Extrenos);
    }

    @Override
    public void actualizarEnEspera(ConfiguracionDeVideo cdv) throws FileNotFoundException {
//        System.out.println("basicos="+basicos);
//        System.out.println("basicos.getEnEspera()="+basicos.getEnEspera());
        EnEspera = basicos.getEnEspera().getCatalogoRelacionado(Entrada);
        // EnEspera = actualizarCatalogoSegunFiltro(EnEspera);
    }

    @Override
    public void actualizarPorVer(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        PorVer = basicos.getPorVer().getCatalogoRelacionado(Entrada);
        //  PorVer = actualizarCatalogoSegunFiltro(PorVer);
    }

    @Override
    public void actualizarFinalizadas(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        Finalizadas = basicos.getFinalizadas().getCatalogoRelacionado(Entrada);
        //   Finalizadas  = actualizarCatalogoSegunFiltro(Finalizadas );
    }

    public void actualizarPersonalizadosRelacionados(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        // Finalizadas = basicos.getFinalizadas().getCatalogoRelacionado(Entrada);
        actualizarPersonalizadosSeleccionados(cdv);
        personalizadosRelacionados = personalizadosSeleccionados.getCatalogoRelacionado(Entrada);
    }

    public void actualizarPersonalizadosSeleccionados(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        // Finalizadas = basicos.getFinalizadas().getCatalogoRelacionado(Entrada);
        personalizadosSeleccionados = new CatalogoDeSeries();
//        System.out.println("basicos.getPersonalizadosDiereccion().size()="+basicos.getPersonalizadosDiereccion().size());
        for (int i = 0; i < basicos.getPersonalizadosDiereccion().size(); i++) {
            if (basicos.getInf().getSeccionesBase().getPersonalizadas().get(i).isSeleccionado()) {
                switch (basicos.getInf().getSeccionesBase().getPersonalizadas().get(i).getEstado()) {
                    case CATALOGO:
                        personalizadosSeleccionados.addNecesarios(basicos.getInf().getSeccionesBase().getPersonalizadas().get(i).getCatalogo());
                        break;
                    case DIRECCIONES:
                        personalizadosSeleccionados.addNecesarios(basicos.getPersonalizadosDiereccion().get(i));
                        break;
                    case ESCRITURA:
                        personalizadosSeleccionados.addNecesarios(basicos.getPersonalizadosString().get(i));
                        break;
                }
            }

        }
        //personalizadosEntrada=personalizadosEntrada.getCatalogoRelacionado(Entrada);
    }

    @Override
    public void actualizarTodo(boolean incluirExtrenos) throws FileNotFoundException {
        Todo = new CatalogoDeSeries();
        if(incluirExtrenos){
        Todo.addNecesarios(Seguidos, Extrenos, EnEspera, PorVer, Finalizadas, personalizadosRelacionados);
        }else{
//            System.out.println("sin extrenos");
        Todo.addNecesarios(Seguidos, EnEspera, PorVer, Finalizadas, personalizadosRelacionados);
        }
        
        //  Todo  = actualizarCatalogoSegunFiltro(Todo );
    }

    @Override
    public void actualizar(boolean incluirExtrenos,ConfiguracionDeVideo cdv) throws FileNotFoundException {
        try {
//            System.out.println("-- 0 1");
            actualizarEntrada(cdv);
        } catch (IOException ex) {
            Logger.getLogger(CatalogosDeSeriesEntrada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CatalogosDeSeriesEntrada.class.getName()).log(Level.SEVERE, null, ex);
        } 
//        System.out.println("-- 0 2");
        super.actualizar(incluirExtrenos,cdv);
//        System.out.println("-- 0 3");
        actualizarPersonalizadosRelacionados(cdv);
//         System.out.println("-- 0 5");
        actualizarTodo(incluirExtrenos);
//         System.out.println("-- 0 6");
    }

    public CatalogoDeSeries getEntrada() {
        return Entrada;
    }

    public void setEntrada(CatalogoDeSeries Entrada) {
        this.Entrada = Entrada;
    }

    public CatalogoDeSeries getPersonalizadosRelacionados() {
        return personalizadosRelacionados;
    }

    public void setPersonalizadosRelacionados(CatalogoDeSeries personalizadosRelacionados) {
        this.personalizadosRelacionados = personalizadosRelacionados;
    }

    public CatalogoDeSeries getPersonalizadosSeleccionados() {
        return personalizadosSeleccionados;
    }

    public void setPersonalizadosSeleccionados(CatalogoDeSeries personalizadosSeleccionados) {
        this.personalizadosSeleccionados = personalizadosSeleccionados;
    }

}
