/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.actualizador.de.series.Catalogos.SeccionEntradaTodo;

import Aplicaciones.actualizador.de.series.Catalogos.Secciones.*;
import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Multimedia.Series.CatalogoDeSeries;
import Aplicaciones.actualizador.de.series.*;
import Aplicaciones.actualizador.de.series.Catalogos.*;
import Aplicaciones.actualizador.de.series.Secciones.*;
//import actualizador.de.series.Catalogos.*;
import java.io.FileNotFoundException;

/**
 *
 * @author Rene
 */
public class CatalogosDeSeriesEntradaTodo extends CatalogosDeSeriesEntrada {

    private CatalogosDeSeries Cs;

    public CatalogosDeSeriesEntradaTodo(CatalogosDeSeries Cs, CatalogosDeSeriesBasicos basicos, SeccionSerie seccionCorrespondiente, FiltroSerie filtro) {
        super(basicos, seccionCorrespondiente, filtro);
        this.Cs = Cs;
    }

    // /*************************************

    @Override
    public void actualizarEntrada(ConfiguracionDeVideo cdv) throws FileNotFoundException {

        setEntrada(new CatalogoDeSeries());
        for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {

            getEntrada().addAll(getCatalogosDeSeriesPersonalizada(i).getEntrada());
        }
//         Cs.getEntradaClasicas().getFaltanPorCopiar().getSeguidos().imprimir();
        for (int i = 0; i < 4; i++) {
//            Cs.getEntradaClasicas().getFaltanPorCopiar().getSeguidos().imprimir();
            getEntrada().addAll(getCatalogosDeSeries(i).getEntrada());
//             System.out.println(" i ="+i+" *****************************************");
//            Cs.getEntradaClasicas().getFaltanPorCopiar().getSeguidos().imprimir();
//            System.out.println("-------------------");
        }
        setEntrada(actualizarCatalogoSegunFiltro(getEntrada()));

//        Cs.getEntradaClasicas().getFaltanPorCopiar().getSeguidos().imprimir();
    }

    @Override
    public void actualizarSeguidos(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        setSeguidos(new CatalogoDeSeries());
        for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {
            getSeguidos().addAll(getCatalogosDeSeriesPersonalizada(i).getSeguidos());
        }
//      Cs.getEntradaClasicas().getFaltanPorCopiar().getSeguidos().imprimir();
        for (int i = 0; i < 4; i++) {
//            Cs.getEntradaClasicas().getFaltanPorCopiar().getSeguidos().imprimir();
//            getCatalogosDeSeries(i).getSeguidos().imprimir();
            getSeguidos().addAll(getCatalogosDeSeries(i).getSeguidos());
//             System.out.println(" i ="+i+" *****************************************");
//            Cs.getEntradaClasicas().getFaltanPorCopiar().getSeguidos().imprimir();
//            System.out.println("-------------------");
        }
        setSeguidos(actualizarCatalogoSegunFiltro(getSeguidos()));
//        Cs.getEntradaClasicas().getFaltanPorCopiar().getSeguidos().imprimir();
    }

    @Override
    public void actualizarExtrenos(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        setExtrenos(new CatalogoDeSeries());
        for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {
            getExtrenos().addAll(getCatalogosDeSeriesPersonalizada(i).getExtrenos());
        }
        for (int i = 0; i < 4; i++) {
            getExtrenos().addAll(getCatalogosDeSeries(i).getExtrenos());
        }
        setExtrenos(actualizarCatalogoSegunFiltro(getExtrenos()));
    }

    @Override
    public void actualizarEnEspera(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        setEnEspera(new CatalogoDeSeries());
        CatalogoDeSeries c = getEnEspera();
        for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {
            c.addAll(getCatalogosDeSeriesPersonalizada(i).getEnEspera());
        }
        for (int i = 0; i < 4; i++) {
            c.addAll(getCatalogosDeSeries(i).getEnEspera());
        }
        setEnEspera(actualizarCatalogoSegunFiltro(c));
    }

    @Override
    public void actualizarPorVer(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        setPorVer(new CatalogoDeSeries());
        CatalogoDeSeries c = getPorVer();
        for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {
            c.addAll(getCatalogosDeSeriesPersonalizada(i).getPorVer());
        }
        for (int i = 0; i < 4; i++) {
            c.addAll(getCatalogosDeSeries(i).getPorVer());
        }
        setPorVer(actualizarCatalogoSegunFiltro(c));
    }

    @Override
    public void actualizarFinalizadas(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        setFinalizadas(new CatalogoDeSeries());
        CatalogoDeSeries c = getFinalizadas();
        for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {
            c.addAll(getCatalogosDeSeriesPersonalizada(i).getFinalizadas());
        }
        for (int i = 0; i < 4; i++) {
            c.addAll(getCatalogosDeSeries(i).getFinalizadas());
        }
        setFinalizadas(actualizarCatalogoSegunFiltro(c));
    }

    @Override
    public void actualizarPersonalizadosRelacionados(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        setPersonalizadosRelacionados(new CatalogoDeSeries());
        CatalogoDeSeries c = getPersonalizadosRelacionados();
        for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {
            c.addAll(getCatalogosDeSeriesPersonalizada(i).getPersonalizadosRelacionados());
        }
        for (int i = 0; i < 4; i++) {
            c.addAll(getCatalogosDeSeries(i).getPersonalizadosRelacionados());
        }
        setPersonalizadosRelacionados(actualizarCatalogoSegunFiltro(c));
    }

    @Override
    public void actualizarPersonalizadosSeleccionados(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        setPersonalizadosSeleccionados(new CatalogoDeSeries());
        CatalogoDeSeries c = getPersonalizadosSeleccionados();
        for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {
            c.addAll(getCatalogosDeSeriesPersonalizada(i).getPersonalizadosSeleccionados());
        }
        for (int i = 0; i < 4; i++) {
            c.addAll(getCatalogosDeSeries(i).getPersonalizadosSeleccionados());
        }
        setPersonalizadosSeleccionados(actualizarCatalogoSegunFiltro(c));
    }

    @Override
    public void actualizarTodo(boolean incluirExtrenos) throws FileNotFoundException {
        setTodo(new CatalogoDeSeries());
        CatalogoDeSeries c = getTodo();
        for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {
            c.addAll(getCatalogosDeSeriesPersonalizada(i).getTodo());
        }
        for (int i = 0; i < 4; i++) {
//            System.out.println("i="+i+" *************************************************");
//            getCatalogosDeSeries(i).getTodo().imprimir();
            c.addAll(getCatalogosDeSeries(i).getTodo());
//            System.out.println("------------------------------------");
//            c.imprimir();
        }
//        setTodo(c);
         setTodo(actualizarCatalogoSegunFiltro(c));
    }
//*******************************
//    @Override
//    public void actualizarEntrada(ConfiguracionDeVideo cdv) throws FileNotFoundException {
//        setEntrada(new CatalogoDeSeries());
//        for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {
//
//            getEntrada().addNecesarios(getCatalogosDeSeriesPersonalizada(i).getEntrada());
//        }
//        for (int i = 0; i < 3; i++) {
//            getEntrada().addNecesarios(getCatalogosDeSeries(i).getEntrada());
//        }
//    }
//
//    @Override
//    public void actualizarSeguidos(ConfiguracionDeVideo cdv) throws FileNotFoundException {
//        setSeguidos(new CatalogoDeSeries());
//      for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {
//            getSeguidos().addNecesarios(getCatalogosDeSeriesPersonalizada(i).getSeguidos());
//        }
//        for (int i = 0; i < 3; i++) {
//            getSeguidos().addNecesarios(getCatalogosDeSeries(i).getSeguidos());
//        }
//    }
//
//    @Override
//    public void actualizarExtrenos(ConfiguracionDeVideo cdv) throws FileNotFoundException {
//        setExtrenos(new CatalogoDeSeries());
//        for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {
//            getExtrenos().addNecesarios(getCatalogosDeSeriesPersonalizada(i).getExtrenos());
//        }
//        for (int i = 0; i < 3; i++) {
//            getExtrenos().addNecesarios(getCatalogosDeSeries(i).getExtrenos());
//        }
//    }
//
//    @Override
//    public void actualizarEnEspera(ConfiguracionDeVideo cdv) throws FileNotFoundException {
//        setEnEspera(new CatalogoDeSeries());
//        CatalogoDeSeries c = getEnEspera();
//        for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {
//            c.addNecesarios(getCatalogosDeSeriesPersonalizada(i).getEnEspera());
//        }
//        for (int i = 0; i < 3; i++) {
//            c.addNecesarios(getCatalogosDeSeries(i).getEnEspera());
//        }
//    }
//
//    @Override
//    public void actualizarPorVer(ConfiguracionDeVideo cdv) throws FileNotFoundException {
//        setPorVer(new CatalogoDeSeries());
//        CatalogoDeSeries c = getPorVer();
//        for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {
//            c.addNecesarios(getCatalogosDeSeriesPersonalizada(i).getPorVer());
//        }
//        for (int i = 0; i < 3; i++) {
//            c.addNecesarios(getCatalogosDeSeries(i).getPorVer());
//        }
//    }
//
//    @Override
//    public void actualizarFinalizadas(ConfiguracionDeVideo cdv) throws FileNotFoundException {
//        setFinalizadas(new CatalogoDeSeries());
//        CatalogoDeSeries c = getFinalizadas();
//        for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {
//            c.addNecesarios(getCatalogosDeSeriesPersonalizada(i).getFinalizadas());
//        }
//        for (int i = 0; i < 3; i++) {
//            c.addNecesarios(getCatalogosDeSeries(i).getFinalizadas());
//        }
//    }
//
//    @Override
//    public void actualizarPersonalizadosRelacionados(ConfiguracionDeVideo cdv) throws FileNotFoundException {
//        setPersonalizadosRelacionados(new CatalogoDeSeries());
//        CatalogoDeSeries c = getPersonalizadosRelacionados();
//        for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {
//            c.addNecesarios(getCatalogosDeSeriesPersonalizada(i).getPersonalizadosRelacionados());
//        }
//        for (int i = 0; i < 3; i++) {
//            c.addNecesarios(getCatalogosDeSeries(i).getPersonalizadosRelacionados());
//        }
//    }
//
//    @Override
//    public void actualizarPersonalizadosSeleccionados(ConfiguracionDeVideo cdv) throws FileNotFoundException {
//        setPersonalizadosSeleccionados(new CatalogoDeSeries());
//        CatalogoDeSeries c = getPersonalizadosSeleccionados();
//        for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {
//            c.addNecesarios(getCatalogosDeSeriesPersonalizada(i).getPersonalizadosSeleccionados());
//        }
//        for (int i = 0; i < 3; i++) {
//            c.addNecesarios(getCatalogosDeSeries(i).getPersonalizadosSeleccionados());
//        }
//    }
//
//    @Override
//    public void actualizarTodo() throws FileNotFoundException {
//        setTodo(new CatalogoDeSeries());
//        CatalogoDeSeries c = getTodo();
//        for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {
//            c.addNecesarios(getCatalogosDeSeriesPersonalizada(i).getTodo());
//        }
//        for (int i = 0; i < 3; i++) {
//            c.addNecesarios(getCatalogosDeSeries(i).getTodo());
//        }
//    }

    private CatalogosDeSeriesEntrada getCatalogosDeSeries(int i) {
          return SeccionDeCatalogosDeSerieEntradaTodo.getCatalogosDeSeries(Cs, i).getEntrada();
//        switch (i) {
//            case 0:
//                return Cs.getEntradaClasicas().getEntrada();
//            case 1:
//                return Cs.getEntradaDobladas().getEntrada();
//            case 2:
//                return Cs.getEntradaTX().getEntrada();
//
//        }
//        return null;
    }

    private CatalogosDeSeriesEntrada getCatalogosDeSeriesPersonalizada(int i) {
        return Cs.getPersonalizadasEntrada().get(i).getEntrada();
    }
}
