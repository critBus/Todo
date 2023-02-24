/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.actualizador.de.series.Catalogos.SeccionEntradaTodo;

import Aplicaciones.actualizador.de.series.Catalogos.Secciones.*;
//import actualizador.de.series.Catalogos.Secciones.CatalogosDeSeriesFaltanPorCopiar;
//import actualizador.de.series.Catalogos.Secciones.CatalogosDeSeriesFaltantes;
import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Aplicaciones.actualizador.de.series.Catalogos.*;
import Aplicaciones.actualizador.de.series.Catalogos.Secciones.*;
import Aplicaciones.actualizador.de.series.Secciones.*;
//import actualizador.de.series.Secciones.SeccionSerie;
import java.io.FileNotFoundException;

/**
 *
 * @author Rene
 */
public class SeccionDeCatalogosDeSerieEntradaTodo extends SeccionDeCatalogosDeSerieEntrada {

    private CatalogosDeSeriesEntradaTodo entradaTodo;
    private CatalogosDeSeriesFaltanPorCopiarTodo faltanPorCopiarTodo;
    private CatalogosDeSeriesFaltantesTodo faltantesTodo;
    private CatalogosDeSeriesContrarioTodo contrarioTodo;
    private CatalogosDeSeries Cs;

    public SeccionDeCatalogosDeSerieEntradaTodo(CatalogosDeSeries Cs, String nombre, InformacionDeSecciones inf, SeccionSerie entradaCorrespondiente, CatalogosDeSeriesBasicos basicos, ConfiguracionDeVideo cdv) throws FileNotFoundException {

        this.Cs = Cs;
        setNombre(nombre);
        setInf(inf);
        setBasicos(basicos);
        entradaTodo = new CatalogosDeSeriesEntradaTodo(Cs, basicos, entradaCorrespondiente, entradaCorrespondiente.getFiltro());
//        Cs.getEntradaTX().getFaltanPorCopiar().getSeguidos().imprimir();
        faltanPorCopiarTodo = new CatalogosDeSeriesFaltanPorCopiarTodo(Cs, basicos, entradaTodo, entradaCorrespondiente.getFiltro());

        faltantesTodo = new CatalogosDeSeriesFaltantesTodo(Cs, basicos, entradaTodo, entradaCorrespondiente.getFiltro());
        contrarioTodo = new CatalogosDeSeriesContrarioTodo(Cs, basicos, entradaTodo, entradaCorrespondiente.getFiltro());
//        Cs.getEntradaClasicas().getFaltanPorCopiar().getSeguidos().imprimir();
//        Cs.getEntradaTX().getFaltanPorCopiar().getSeguidos().imprimir();
        actualizarCatalogos(true, cdv);

    }

    @Override
    public CatalogosDeSeriesEntrada getEntrada() {
        return entradaTodo; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CatalogosDeSeriesFaltanPorCopiar getFaltanPorCopiar() {
        return faltanPorCopiarTodo; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CatalogosDeSeriesFaltantes getFaltantes() {
        return faltantesTodo; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CatalogosDeSeriesContrario getContrarios() {
        return contrarioTodo; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizarCatalogos(boolean incluirExtrenos, ConfiguracionDeVideo cdv) throws FileNotFoundException {
//       Cs.getEntradaClasicas().getFaltanPorCopiar().getSeguidos().imprimir();
        entradaTodo.actualizar(incluirExtrenos, cdv);
//        Cs.getEntradaTX().getFaltanPorCopiar().getSeguidos().imprimir();
//        Cs.getEntradaClasicas().getFaltanPorCopiar().getSeguidos().imprimir();
        faltanPorCopiarTodo.actualizar(incluirExtrenos, cdv);
        faltantesTodo.actualizar(incluirExtrenos, cdv);
        contrarioTodo.actualizar(incluirExtrenos, cdv);
    }

    @Override
    public void actualizarCatalogosTodo(boolean incluirExtrenos) throws FileNotFoundException {
        entradaTodo.actualizarTodo(incluirExtrenos);
        faltanPorCopiarTodo.actualizarTodo(incluirExtrenos);
        faltantesTodo.actualizarTodo(incluirExtrenos);
        contrarioTodo.actualizarTodo(incluirExtrenos);
    }

    public static SeccionDeCatalogosDeSerieEntrada getCatalogosDeSeries(CatalogosDeSeries Cs, int i) {
        switch (i) {
            case 0:
                return Cs.getEntradaClasicas();
            case 1:
                return Cs.getEntradaDobladas();
            case 2:
                return Cs.getEntradaTX();
            case 3:
                return Cs.getEntradaFinalizadas();

        }
        return null;
    }

}
