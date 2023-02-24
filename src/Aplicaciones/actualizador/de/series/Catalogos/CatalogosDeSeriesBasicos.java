/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.actualizador.de.series.Catalogos;

import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Multimedia.Series.CatalogoDeSeries;
import Aplicaciones.actualizador.de.series.*;
//import actualizador.de.series.DirectoriosInvalidos;
//import actualizador.de.series.FiltroSerie;
import Aplicaciones.actualizador.de.series.Secciones.*;
//import actualizador.de.series.Secciones.SeccionPersonalizada;
//import actualizador.de.series.Secciones.SeccionSerie;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class CatalogosDeSeriesBasicos implements Serializable {
   //bbbb
    private InformacionDeSecciones inf;
    //  private CatalogoDeSeries Seguidos, QueTengo, EnEspera, PorVer, Finalizadas, BaseTodo, FinalizadasQueTengo;
    private CatalogoDeSeries Seguidos, QueTengo, EnEspera, PorVer, Finalizadas;// BaseTodo;
    private LinkedList<CatalogoDeSeries> personalizadosString, personalizadosDireccion;
    private CatalogosDeSeriesBasicos originales;
    // private SeccionSerie entradaCorrespondiente;
    // private FiltroSerie filtro;

    public CatalogosDeSeriesBasicos(InformacionDeSecciones inf) {
        this.inf = inf;
        // this.entradaCorrespondiente=entradaCorrespondiente;
        personalizadosString = new LinkedList<CatalogoDeSeries>();
        personalizadosDireccion = new LinkedList<CatalogoDeSeries>();

    }

    private CatalogoDeSeries actualizarCatalogoSegunFiltro(CatalogoDeSeries c, FiltroSerie f) {
        return actualizarCatalogoSegunFiltro(originales, c, f);
    }

    /**
     * faltan los personalizados
     *
     * @param originales
     * @param c
     * @param f
     * @return
     */
    public static CatalogoDeSeries actualizarCatalogoSegunFiltro(CatalogosDeSeriesBasicos originales, CatalogoDeSeries c, FiltroSerie f) {

        if (!f.isEmpty()) {
//            System.out.println("aaa");
            //System.out.println("!c.isEmpty()="+!c.isEmpty());
            if (c.isEmpty()) {
                //   c.imprimir();
                // System.out.println("return");
                return c;
            }
            if (f.isExtrenos()) {
//                System.out.println("a");
                c = c.getCatalogoSeriesConPrimerosCapitulos();
                if (c.isEmpty()) {
                    return c;
                }
            }
            if (f.isPrimeros()) {
//                System.out.println("b");
                c = c.getCatalogoSeriesPrimerosCapitulos();
                if (c.isEmpty()) {
                    return c;
                }
            }
            if (f.isUltimos()) {
//                System.out.println("c");
                c = c.getCatalogoSeriesUltimosCapitulos();
                if (c.isEmpty()) {
                    return c;
                }
            }
            if (f.isCapitulos1y0()) {
//                System.out.println("c 2");
                c = c.getCatalogoSeriesCapitulos1y0();
                if (c.isEmpty()) {
//                    System.out.println("vacio");
                    return c;
                }
            }
            if (f.isSolo()) {
//                System.out.println("d");
                c = c.getCatalogoSeriesSoloCapitulos(f.getNumeroSolo());
                if (c.isEmpty()) {
                    return c;
                }
            }
            if (f.isMayores()) {
//                System.out.println("e");
                c = c.getCatalogoSeriesMayoresQue(f.getNumeroMayores());
                if (c.isEmpty()) {
                    return c;
                }
            }
            if (f.isMenores()) {
//                System.out.println("f");
                c = c.getCatalogoSeriesMenoresQue(f.getNumeroMenores());
                if (c.isEmpty()) {
                    return c;
                }
            }
            switch (f.getRelacion()) {
                case RELACIONADOS:
                     //*****************************
                if (f.isEnEspera()) {
//                System.out.println("g");
                    c = originales.getEnEspera().getCatalogoRelacionado(c);
                    if (c.isEmpty()) {
                        return c;
                    }
                }
                if (f.isFinalizadas()) {
//                System.out.println("h");
                    c = originales.getFinalizadas().getCatalogoRelacionado(c);
                    if (c.isEmpty()) {
                        return c;
                    }
                }
                //***********************************
                //Filtrar por los personalizados 
                if (f.isPersonalizados()) {

                    LinkedList<SeccionPersonalizada> pEr = originales.getInf().getSeccionesBase().getPersonalizadas();

//                LinkedList<CatalogoDeSeries> p = originales.getPersonalizados();
                    for (int i = 0; i < pEr.size(); i++) {
                        if (pEr.get(i).isSeleccionado()) {
                            switch (pEr.get(i).getEstado()) {
                                case CATALOGO:
                                    c = pEr.get(i).getCatalogo().getCatalogoRelacionado(c);
                                    break;
                                case DIRECCIONES:
                                    c = originales.getPersonalizadosDiereccion().get(i).getCatalogoRelacionado(c);
                                    break;
                                case ESCRITURA:
                                    c = originales.getPersonalizadosString().get(i).getCatalogoRelacionado(c);
                                    break;
                            }
                            if (c.isEmpty()) {
                                return c;
                            }
                        }

                    }

//                System.out.println("i");
                    //c = originales.EnEspera.getCatalogoRelacionado(c);
                }
                if (f.isPorVer()) {
//                System.out.println("j");
                    c = originales.getPorVer().getCatalogoRelacionado(c);
                    if (c.isEmpty()) {
                        return c;
                    }
                }
                if (f.isQueTengo()) {
//                System.out.println("k");
                    c = originales.getQueTengo().getCatalogoRelacionado(c);
                    if (c.isEmpty()) {
                        return c;
                    }
                }
                if (f.isSeguidos()) {
//                System.out.println("l");
                    c = originales.getSeguidos().getCatalogoRelacionado(c);
                    if (c.isEmpty()) {
                        return c;
                    }
                }
                    break;
                case FALTANTES:
                    
                if (f.isEnEspera()) {
//                System.out.println("g");
                    c = originales.getEnEspera().getCatalogoSeriesFaltantes(c);
                    if (c.isEmpty()) {
                        return c;
                    }
                }
                if (f.isFinalizadas()) {
//                System.out.println("h");
                    c = originales.getFinalizadas().getCatalogoSeriesFaltantes(c);
                    if (c.isEmpty()) {
                        return c;
                    }
                }
                //***********************************
                //Filtrar por los personalizados 
                if (f.isPersonalizados()) {

                    LinkedList<SeccionPersonalizada> pEr = originales.getInf().getSeccionesBase().getPersonalizadas();

//                LinkedList<CatalogoDeSeries> p = originales.getPersonalizados();
                    for (int i = 0; i < pEr.size(); i++) {
                        if (pEr.get(i).isSeleccionado()) {
                            switch (pEr.get(i).getEstado()) {
                                case CATALOGO:
                                    c = pEr.get(i).getCatalogo().getCatalogoSeriesFaltantes(c);
                                    break;
                                case DIRECCIONES:
                                    c = originales.getPersonalizadosDiereccion().get(i).getCatalogoSeriesFaltantes(c);
                                    break;
                                case ESCRITURA:
                                    c = originales.getPersonalizadosString().get(i).getCatalogoSeriesFaltantes(c);
                                    break;
                            }
                            if (c.isEmpty()) {
                                return c;
                            }
                        }

                    }

//                System.out.println("i");
                    //c = originales.EnEspera.getCatalogoRelacionado(c);
                }
                if (f.isPorVer()) {
//                System.out.println("j");
                    c = originales.getPorVer().getCatalogoSeriesFaltantes(c);
                    if (c.isEmpty()) {
                        return c;
                    }
                }
                if (f.isQueTengo()) {
//                System.out.println("k");
                    c = originales.getQueTengo().getCatalogoSeriesFaltantes(c);
                    if (c.isEmpty()) {
                        return c;
                    }
                }
                if (f.isSeguidos()) {
//                System.out.println("l");
                    c = originales.getSeguidos().getCatalogoSeriesFaltantes(c);
                    if (c.isEmpty()) {
                        return c;
                    }
                }
                    break;
                case CONTRARIOS:
                   
                if (f.isEnEspera()) {
//                System.out.println("g");
                    c = originales.getEnEspera().getCatalogoSeriesContrario(c);
                    if (c.isEmpty()) {
                        return c;
                    }
                }
                if (f.isFinalizadas()) {
//                System.out.println("h");
                    c = originales.getFinalizadas().getCatalogoSeriesContrario(c);
                    if (c.isEmpty()) {
                        return c;
                    }
                }
                //***********************************
                //Filtrar por los personalizados 
                if (f.isPersonalizados()) {

                    LinkedList<SeccionPersonalizada> pEr = originales.getInf().getSeccionesBase().getPersonalizadas();

//                LinkedList<CatalogoDeSeries> p = originales.getPersonalizados();
                    for (int i = 0; i < pEr.size(); i++) {
                        if (pEr.get(i).isSeleccionado()) {
                            switch (pEr.get(i).getEstado()) {
                                case CATALOGO:
                                    c = pEr.get(i).getCatalogo().getCatalogoSeriesContrario(c);
                                    break;
                                case DIRECCIONES:
                                    c = originales.getPersonalizadosDiereccion().get(i).getCatalogoSeriesContrario(c);
                                    break;
                                case ESCRITURA:
                                    c = originales.getPersonalizadosString().get(i).getCatalogoSeriesContrario(c);
                                    break;
                            }
                            if (c.isEmpty()) {
                                return c;
                            }
                        }

                    }

//                System.out.println("i");
                    //c = originales.EnEspera.getCatalogoRelacionado(c);
                }
                if (f.isPorVer()) {
//                System.out.println("j");
                    c = originales.getPorVer().getCatalogoSeriesContrario(c);
                    if (c.isEmpty()) {
                        return c;
                    }
                }
                if (f.isQueTengo()) {
//                System.out.println("k");
                    c = originales.getQueTengo().getCatalogoSeriesContrario(c);
                    if (c.isEmpty()) {
                        return c;
                    }
                }
                if (f.isSeguidos()) {
//                System.out.println("l");
                    c = originales.getSeguidos().getCatalogoSeriesContrario(c);
                    if (c.isEmpty()) {
                        return c;
                    }
                }
                    break;
            }
//            if (f.getRelacion()) {
//
//               
//            } else {
//                //*****************
//
//            }

            // CatalogoDeSeries c2=c;
        }

        return c;
    }

//    public void actualizarFinalizadasQueTengo(ConfiguracionDeVideo cdv) throws FileNotFoundException {
//
//        //FinalizadasQueTengo = PorVer.getCatalogoRelacionado(Finalizadas);
//    }
    public void actualizarSeguidos(ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
        // Seguidos = getCatalogoDeSeries(cdv, inf.getSeguidos().getDirecciones());
        originales.actualizarSinFiltroSeguidos(cdv);
        
//        Seguidos.imprimir();
//        System.out.println("inf.getSeguidos().getFiltro().isEmpty()="+inf.getSeguidos().getFiltro().isEmpty());
        Seguidos = actualizarCatalogoSegunFiltro(originales.getSeguidos(), inf.getSeccionesBase().getSeguidos().getFiltro());
        // Seguidos.imprimir();
    }

    public void actualizarQueTengo(ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
        // QueTengo = getCatalogoDeSeries(cdv, inf.getQueTengo().getDirecciones());
        originales.actualizarSinFiltroQueTengo(cdv);
        QueTengo = actualizarCatalogoSegunFiltro(originales.getQueTengo(), inf.getSeccionesBase().getQueTengo().getFiltro());
    }

    public void actualizarEnEspera(ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
        // EnEspera = getCatalogoDeSeries(cdv, inf.getEnEspera().getDirecciones());
        originales.actualizarSinFiltroEnEspera(cdv);
        EnEspera = actualizarCatalogoSegunFiltro(originales.getEnEspera(), inf.getSeccionesBase().getEnEspera().getFiltro());
    }

    public void actualizarPorVer(ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
        // PorVer = getCatalogoDeSeries(cdv, inf.getEntrada().getDirecciones());
        originales.actualizarSinFiltroPorVer(cdv);
        PorVer = actualizarCatalogoSegunFiltro(originales.getPorVer(), inf.getSeccionesBase().getPorVer().getFiltro());
    }

    public void actualizarFinalizadas(ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
        //  Finalizadas = getCatalogoDeSeries(cdv, inf.getFinalizadas().getDirecciones());
        originales.actualizarSinFiltroFinalizadas(cdv);
        Finalizadas = actualizarCatalogoSegunFiltro(originales.getFinalizadas(), inf.getSeccionesBase().getFinalizadas().getFiltro());
    }

    public void actualizarPersonalizados(ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
        originales.actualizarSinFiltroPersonalizados(cdv);
        LinkedList<SeccionPersonalizada> pEr = inf.getSeccionesBase().getPersonalizadas();
        LinkedList<CatalogoDeSeries> p1 = originales.getPersonalizadosDiereccion(), p2 = originales.getPersonalizadosString();
        personalizadosDireccion.clear();
        personalizadosString.clear();
        for (int i = 0; i < p1.size(); i++) {
            personalizadosDireccion.add(actualizarCatalogoSegunFiltro(p1.get(i), pEr.get(i).getFiltro()));
            personalizadosString.add(actualizarCatalogoSegunFiltro(p2.get(i), pEr.get(i).getFiltro()));
        }

    }

//    private void actualizarSinFiltroPersonalizadosDirecciones(ConfiguracionDeVideo cdv) throws FileNotFoundException {
//        LinkedList<SeccionPersonalizada> p = inf.getPersonalizadas();
//        personalizadosDireccion.clear();
//        for (int i = 0; i < p.size(); i++) {
//            personalizadosDireccion.add(getCatalogoDeSeries(cdv, p.get(i).getDirecciones()));
//        }
//
//    }
//
//    private void actualizarSinFiltroPersonalizadosString(ConfiguracionDeVideo cdv) throws FileNotFoundException {
//        LinkedList<SeccionPersonalizada> p = inf.getPersonalizadas();
//        personalizadosString.clear();
//        for (int i = 0; i < p.size(); i++) {
//            personalizadosString.add(new CatalogoDeSeries(p.get(i).getLineas(), cdv));
//        }
//
//    }
    private void actualizarSinFiltroPersonalizados(ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
//        actualizarSinFiltroPersonalizadosDirecciones(cdv);
//        actualizarSinFiltroPersonalizadosString(cdv);
        LinkedList<SeccionPersonalizada> p = inf.getSeccionesBase().getPersonalizadas();
        personalizadosDireccion.clear();
        personalizadosString.clear();
        for (int i = 0; i < p.size(); i++) {
            personalizadosDireccion.add(getCatalogoDeSeries(cdv, p.get(i).getDirecciones()));
            personalizadosString.add(new CatalogoDeSeries(p.get(i).getLineas(), cdv));
        }

    }

    private void actualizarSinFiltroSeguidos(ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
//        System.out.println(inf.getSeccionesBase().getSeguidos().getDirecciones());
        Seguidos = getCatalogoDeSeries(cdv, inf.getSeccionesBase().getSeguidos().getDirecciones());
        
//        Seguidos.imprimir();
    }

    private void actualizarSinFiltroQueTengo(ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
        QueTengo = getCatalogoDeSeries(cdv, inf.getSeccionesBase().getQueTengo().getDirecciones());
    }

    private void actualizarSinFiltroEnEspera(ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
        EnEspera = getCatalogoDeSeries(cdv, inf.getSeccionesBase().getEnEspera().getDirecciones());
    }

    private void actualizarSinFiltroPorVer(ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
        // PorVer = getCatalogoDeSeries(cdv, inf.getEntrada().getDirecciones());
        PorVer = getCatalogoDeSeries(cdv, inf.getSeccionesBase().getPorVer().getDirecciones());
    }

    private void actualizarSinFiltroFinalizadas(ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
        Finalizadas = getCatalogoDeSeries(cdv, inf.getSeccionesBase().getFinalizadas().getDirecciones());
    }

//    private void actualizarSinFiltroBaseTodo(ConfiguracionDeVideo cdv) throws FileNotFoundException {
//        BaseTodo = new CatalogoDeSeries();
//        BaseTodo.addNecesarios(Seguidos, EnEspera, PorVer, Finalizadas);
//    }
    //*****************************
    public CatalogoDeSeries getEntradaElegida(SeccionSerie s, ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
//        System.out.println("s="+s);
//        System.out.println("cdv="+cdv);
        return getCatalogoDeSeries(cdv, s.getDirecciones());
    }

    protected CatalogoDeSeries getEntradaClasicas(ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
        return getCatalogoDeSeries(cdv, inf.getSeccionesEntrada().getClasicas().getDirecciones());
    }

    protected CatalogoDeSeries getEntradaTX(ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
        return getCatalogoDeSeries(cdv, inf.getSeccionesEntrada().getTX().getDirecciones());
    }

    protected CatalogoDeSeries getEntradaDobladas(ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
        return getCatalogoDeSeries(cdv, inf.getSeccionesEntrada().getDobladas().getDirecciones());
    }

    protected CatalogoDeSeries getEntradaPersonalizadas(int indice, ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
        return getCatalogoDeSeries(cdv, inf.getSeccionesEntrada().getPersonalizadas().get(indice).getDirecciones());
    }

    private CatalogoDeSeries getCatalogoDeSeries(ConfiguracionDeVideo cdv, LinkedList<Direccion>... DD) throws FileNotFoundException, IOException, ClassNotFoundException {

        LinkedList<File> F = new LinkedList<File>();
        LinkedList<LinkedList<String>> inva = new LinkedList<LinkedList<String>>();
        for (int i = 0; i < DD.length; i++) {
            //LinkedList<Direccion> D=DD[i];
            for (int j = 0; j < DD[i].size(); j++) {
                Direccion d = DD[i].get(j);
                if (d.isSeleccionado()) {
                    LinkedList<String> invalidos = new LinkedList<String>();

                    for (int k = 0; k < d.getDirectoriosInvalidos().size(); k++) {
                        DirectoriosInvalidos di = d.getDirectoriosInvalidos().get(k);
                        if (di.isSeleccionado()) {
                            invalidos.add(di.getDirectorioInvalido());
                        }
                    }
                    F.add(d.getF());
                    inva.add(invalidos);
                }
            }
        }
//        System.out.println(inva);
//        System.out.println("ooooooo");
        return new CatalogoDeSeries(F, inva, cdv);
    }

    public void actualizar(ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
        originales = new CatalogosDeSeriesBasicos(inf);
        //   originales.actualizarSinFiltros(cdv);
        actualizarEnEspera(cdv);
        actualizarPorVer(cdv);
        actualizarQueTengo(cdv);
        actualizarSeguidos(cdv);
        actualizarFinalizadas(cdv);
        actualizarPersonalizados(cdv);
//        actualizarFinalizadasQueTengo(cdv);
//        actualizarBaseTodo(cdv);
    }

    private void actualizarSinFiltros(ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
        actualizarSinFiltroEnEspera(cdv);
        actualizarSinFiltroPorVer(cdv);
        actualizarSinFiltroQueTengo(cdv);
        actualizarSinFiltroSeguidos(cdv);
        actualizarSinFiltroFinalizadas(cdv);
        actualizarSinFiltroPersonalizados(cdv);
//        actualizarSinFiltroFinalizadasQueTengo(cdv);
//        actualizarSinFiltroBaseTodo(cdv);
    }

    public InformacionDeSecciones getInf() {
        return inf;
    }

    public void setInf(InformacionDeSecciones inf) {
        this.inf = inf;
    }

    public CatalogoDeSeries getSeguidos() {
        return Seguidos;
    }

    public void setSeguidos(CatalogoDeSeries Seguidos) {
        this.Seguidos = Seguidos;
    }

    public CatalogoDeSeries getQueTengo() {
        return QueTengo;
    }

    public void setQueTengo(CatalogoDeSeries QueTengo) {
        this.QueTengo = QueTengo;
    }

    public CatalogoDeSeries getEnEspera() {
        return EnEspera;
    }

    public void setEnEspera(CatalogoDeSeries EnEspera) {
        this.EnEspera = EnEspera;
    }

    public CatalogoDeSeries getPorVer() {
        return PorVer;
    }

    public void setPorVer(CatalogoDeSeries PorVer) {
        this.PorVer = PorVer;
    }

    public CatalogoDeSeries getFinalizadas() {
        return Finalizadas;
    }

    public void setFinalizadas(CatalogoDeSeries Finalizadas) {
        this.Finalizadas = Finalizadas;
    }

//    public CatalogoDeSeries getEntrada() {
//        return Entrada;
//    }
//
//    public void setEntrada(CatalogoDeSeries Entrada) {
//        this.Entrada = Entrada;
//    }
//    public CatalogoDeSeries getBaseTodo() {
//        return BaseTodo;
//    }
//
//    public void setBaseTodo(CatalogoDeSeries BaseTodo) {
//        this.BaseTodo = BaseTodo;
//    }
//    public CatalogoDeSeries getFinalizadasQueTengo() {
//        return FinalizadasQueTengo;
//    }
//
//    public void setFinalizadasQueTengo(CatalogoDeSeries FinalizadasQueTengo) {
//        this.FinalizadasQueTengo = FinalizadasQueTengo;
//    }
    public LinkedList<CatalogoDeSeries> getPersonalizadosString() {
        return personalizadosString;
    }

    public void setPersonalizadosString(LinkedList<CatalogoDeSeries> personalizadosString) {
        this.personalizadosString = personalizadosString;
    }

    public LinkedList<CatalogoDeSeries> getPersonalizadosDiereccion() {
        return personalizadosDireccion;
    }

    public void setPersonalizadosDiereccion(LinkedList<CatalogoDeSeries> personalizadosDiereccion) {
        this.personalizadosDireccion = personalizadosDiereccion;
    }

}
