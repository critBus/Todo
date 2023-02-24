/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.actualizador.de.series.Catalogos;

import Aplicaciones.actualizador.de.series.Catalogos.Secciones.*;
import Aplicaciones.actualizador.de.series.Secciones.*;
//import actualizador.de.series.Catalogos.Secciones.CatalogosDeSeriesEntrada;
//import actualizador.de.series.Catalogos.Secciones.CatalogosDeSeriesFaltanPorCopiar;
import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
//import actualizador.de.series.Catalogos.Secciones.CatalogosDeSeriesContrario;
//import actualizador.de.series.Secciones.InformacionDeSecciones;
//import actualizador.de.series.Secciones.SeccionSerie;
import java.io.FileNotFoundException;

/**
 *
 * @author Rene
 */
public class SeccionDeCatalogosDeSerieEntrada {
    //bbbb
    private String nombre;
    private InformacionDeSecciones inf;
    private CatalogosDeSeriesBasicos basicos;
    private CatalogosDeSeriesEntrada entrada;
    private CatalogosDeSeriesFaltanPorCopiar faltanPorCopiar;
    private CatalogosDeSeriesFaltantes faltantes;
     private CatalogosDeSeriesContrario contrarios;

    public SeccionDeCatalogosDeSerieEntrada() {
        
    }

    
//    public SeccionDeCatalogosDeSerieEntrada(String nombre,InformacionDeSecciones inf, SeccionSerie entradaCorrespondiente, CatalogosDeSeriesBasicos basicos, ConfiguracionDeVideo cdv) throws FileNotFoundException {
//        this(nombre,inf, entradaCorrespondiente, basicos, cdv, true);
//    }

    public SeccionDeCatalogosDeSerieEntrada(String nombre,InformacionDeSecciones inf, SeccionSerie entradaCorrespondiente, CatalogosDeSeriesBasicos basicos, ConfiguracionDeVideo cdv) throws FileNotFoundException {
        this.nombre=nombre;
        this.inf = inf;
        this.basicos = basicos;
//         if (actualizar) {
        entrada = new CatalogosDeSeriesEntrada(basicos, entradaCorrespondiente, entradaCorrespondiente.getFiltro());
        faltanPorCopiar = new CatalogosDeSeriesFaltanPorCopiar(basicos, entrada, entradaCorrespondiente.getFiltro());
        faltantes = new CatalogosDeSeriesFaltantes(basicos, entrada, entradaCorrespondiente.getFiltro());
//        System.out.println(entradaCorrespondiente.getFiltro());
       contrarios  = new CatalogosDeSeriesContrario (basicos, entrada, entradaCorrespondiente.getFiltro());
            actualizarCatalogos(true,cdv);
//        }
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void actualizarCatalogos(boolean incluirExtrenos,ConfiguracionDeVideo cdv) throws FileNotFoundException {
//         basicos.actualizar(cdv);
//        System.out.println(" 1 1 1");
        entrada.actualizar(incluirExtrenos,cdv);
//        System.out.println(" 1 1 2");
        faltanPorCopiar.actualizar(incluirExtrenos,cdv);
//        System.out.println(" 1 1 3");
        faltantes.actualizar(incluirExtrenos,cdv);
//        System.out.println(" 1 1 4");
        contrarios.actualizar(incluirExtrenos,cdv);
    }
public void actualizarCatalogosTodo(boolean incluirExtrenos) throws FileNotFoundException {
entrada.actualizarTodo(incluirExtrenos);
faltanPorCopiar.actualizarTodo(incluirExtrenos);
faltantes.actualizarTodo(incluirExtrenos);
contrarios.actualizarTodo(incluirExtrenos);
}
    
    public CatalogosDeSeriesContrario getContrarios() {
        return contrarios;
    }

    public void setContrarios(CatalogosDeSeriesContrario contrarios) {
        this.contrarios = contrarios;
    }
    
    public InformacionDeSecciones getInf() {
        return inf;
    }
    
    public void setInf(InformacionDeSecciones inf) {
        this.inf = inf;
    }
    
    public CatalogosDeSeriesBasicos getBasicos() {
        return basicos;
    }
    
    public void setBasicos(CatalogosDeSeriesBasicos basicos) {
        this.basicos = basicos;
    }
    
    public CatalogosDeSeriesEntrada getEntrada() {
        return entrada;
    }
    
    public void setEntrada(CatalogosDeSeriesEntrada entrada) {
        this.entrada = entrada;
    }
    
    public CatalogosDeSeriesFaltanPorCopiar getFaltanPorCopiar() {
        return faltanPorCopiar;
    }
    
    public void setFaltanPorCopiar(CatalogosDeSeriesFaltanPorCopiar faltanPorCopiar) {
        this.faltanPorCopiar = faltanPorCopiar;
    }
    
    public CatalogosDeSeriesFaltantes getFaltantes() {
        return faltantes;
    }
    
    public void setFaltantes(CatalogosDeSeriesFaltantes faltantes) {
        this.faltantes = faltantes;
    }
    
}
