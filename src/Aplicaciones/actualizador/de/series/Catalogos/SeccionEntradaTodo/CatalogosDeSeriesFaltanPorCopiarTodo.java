/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.actualizador.de.series.Catalogos.SeccionEntradaTodo;

import Aplicaciones.actualizador.de.series.Catalogos.Secciones.*;
//import actualizador.de.series.Catalogos.Secciones.CatalogosDeSeriesEntrada;
//import actualizador.de.series.Catalogos.*;
import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Multimedia.Series.CatalogoDeSeries;
import Aplicaciones.actualizador.de.series.*;
import Aplicaciones.actualizador.de.series.Catalogos.*;
//import Aplicaciones.actualizador.de.series.Secciones.*;
import java.io.FileNotFoundException;

/**
 *
 * @author Rene
 */
public class CatalogosDeSeriesFaltanPorCopiarTodo extends CatalogosDeSeriesFaltanPorCopiar {

    private CatalogosDeSeries Cs;

    public CatalogosDeSeriesFaltanPorCopiarTodo(CatalogosDeSeries Cs, CatalogosDeSeriesBasicos basicos, CatalogosDeSeriesEntrada entradas, FiltroSerie filtro) {
        super(basicos, entradas, filtro);
        this.Cs = Cs;
    }
    //************************
    public CatalogoDeSeries actualizarCatalogoSegunFiltro(CatalogoDeSeries c) {
        return CatalogosDeSeriesBasicos.actualizarCatalogoSegunFiltro(getBasicos(), c, getFiltro());
    }
@Override
    public void actualizarSeguidos(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        setSeguidos(new CatalogoDeSeries());
        for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {
            getSeguidos().addAll(getCatalogosDeSeriesPersonalizada(i).getSeguidos());
        }
        for (int i = 0; i < 4; i++) {
//            System.out.println("ssssssssssssssssssssssssssssssssssssss");
//         getCatalogosDeSeries(i).getSeguidos().imprimir();
            getSeguidos().addAll(getCatalogosDeSeries(i).getSeguidos());
//            System.out.println("ddddddddddddddddddddddddddddddd");
//            getSeguidos().imprimir();
            
        }
        setSeguidos(actualizarCatalogoSegunFiltro(getSeguidos()));
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
    public void actualizarPersonalizados(ConfiguracionDeVideo cdv) throws FileNotFoundException {
        setPersonalizados(new CatalogoDeSeries());
        CatalogoDeSeries c = getPersonalizados();
        for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {
            c.addAll(getCatalogosDeSeriesPersonalizada(i).getPersonalizados());
        }
        for (int i = 0; i < 4; i++) {
            c.addAll(getCatalogosDeSeries(i).getPersonalizados());
        }
        setPersonalizados(actualizarCatalogoSegunFiltro(c));
    }

   public void actualizarTodo() throws FileNotFoundException {
        setTodo(new CatalogoDeSeries());
        CatalogoDeSeries c = getTodo();
        for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {
            c.addAll(getCatalogosDeSeriesPersonalizada(i).getTodo());
        }
        for (int i = 0; i < 4; i++) {
            c.addAll(getCatalogosDeSeries(i).getTodo());
        }
         setTodo(actualizarCatalogoSegunFiltro(c));
    }
    //***************************
//    @Override
//    public void actualizarSeguidos(ConfiguracionDeVideo cdv) throws FileNotFoundException {
//        setSeguidos(new CatalogoDeSeries());
//        for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {
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
//    public void actualizarPersonalizados(ConfiguracionDeVideo cdv) throws FileNotFoundException {
//        setPersonalizados(new CatalogoDeSeries());
//        CatalogoDeSeries c = getPersonalizados();
//        for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {
//            c.addNecesarios(getCatalogosDeSeriesPersonalizada(i).getPersonalizados());
//        }
//        for (int i = 0; i < 3; i++) {
//            c.addNecesarios(getCatalogosDeSeries(i).getPersonalizados());
//        }
//    }
//
//    @Override
//    public void actualizarTodo(ConfiguracionDeVideo cdv) throws FileNotFoundException {
//        setTodo(new CatalogoDeSeries());
//        CatalogoDeSeries c = getTodo();
//        for (int i = 0; i < Cs.getPersonalizadasEntrada().size(); i++) {
//            c.addNecesarios(getCatalogosDeSeriesPersonalizada(i).getTodo());
//        }
//        for (int i = 0; i < 3; i++) {
//            c.addNecesarios(getCatalogosDeSeries(i).getTodo());
//        }
//    }

    private CatalogosDeSeriesFaltanPorCopiar getCatalogosDeSeries(int i) {
          return SeccionDeCatalogosDeSerieEntradaTodo.getCatalogosDeSeries(Cs, i).getFaltanPorCopiar();
//        switch (i) {
//            case 0:
//                return Cs.getEntradaClasicas().getFaltanPorCopiar();
//            case 1:
//                return Cs.getEntradaDobladas().getFaltanPorCopiar();
//            case 2:
//                return Cs.getEntradaTX().getFaltanPorCopiar();
//
//        }
//        return null;
    }

    private CatalogosDeSeriesFaltanPorCopiar getCatalogosDeSeriesPersonalizada(int i) {
        return Cs.getPersonalizadasEntrada().get(i).getFaltanPorCopiar();
    }

}
