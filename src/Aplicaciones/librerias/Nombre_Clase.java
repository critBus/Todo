/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.librerias;

import java.io.Serializable;

/**
 *
 * @author Rene
 */
public enum Nombre_Clase implements Serializable {

    ARCHIVO("Archivo", "\\MetodosUtiles", 0, 0), ARREGLOS("Arreglos", "\\MetodosUtiles", 1, 1), CONVERSIONES("Conversiones", "\\MetodosUtiles", 2, 2), METODOS_PARA_MANIPULAR_MIS_CLASES("MetodosParaManipularMisClases", "\\MetodosUtiles", 3, 3), METODOS_UTILES("MetodosUtiles", "\\MetodosUtiles", 4, 4), OPERACIONES("Operaciones", "\\MetodosUtiles", 5, 5), TEMPUS("Tempus", "\\MetodosUtiles", 6, 6), VISUAL("Visual", "\\MetodosUtiles", 7, 7), VENTANA_ADMINISTRADOR("Ventana_Administrador", "\\ClasesUtiles\\Admininistrador", 8, 8), ADMININISTRADOR_DE_GUARDADO("Admininistrador_De_Guardado", "\\ClasesUtiles\\Admininistrador", 9, 9),
    VIDEOS("Videos", "\\MetodosUtiles", 10, 10), Z("Z", "\\ClasesUtiles", 11, 11),
    CONFIGURACION_DE_TABLA("ConfiguracionDeTabla", "\\ClasesUtiles\\Configuraciones", 12, 12),
    CONFIGURACION_DE_VIDEO("ConfiguracionDeVideo", "\\ClasesUtiles\\Configuraciones", 13, 13),
    TABLAS_AMPLIABLE_CONFIGURACION_DE_VIDEO("TablasAmpliableConfiguracionDeVideo", "\\ClasesUtiles\\Configuraciones\\Ventanas", 14, 14),
    VENTANA_CONFIGURACION_DE_VIDEO("Ventana_ConfiguracionDeVideo", "\\ClasesUtiles\\Configuraciones\\Ventanas", 15, 15),
    CARPETA_CON_SERIES("CarpetaConSeries", "\\ClasesUtiles\\Multimedia\\Paquete", 16, 16),
    PAQUETE("Paquete", "\\ClasesUtiles\\Multimedia\\Paquete", 17, 17),
    CARPETA_MANGAS("CarpetaMangas", "\\ClasesUtiles\\Multimedia\\Paquete\\CarpetaMangas", 18, 18),
    CARPETA_MANGAS_CLASICAS("CarpetaMangasClasicas", "\\ClasesUtiles\\Multimedia\\Paquete\\CarpetaMangas", 19, 19),
    CARPETA_MANGAS_FINALIZADAS("CarpetaMangasFinalizadas", "\\ClasesUtiles\\Multimedia\\Paquete\\CarpetaMangas", 20, 20),
    CARPETA_MANGAS_TX("CarpetaMangasTX", "\\ClasesUtiles\\Multimedia\\Paquete\\CarpetaMangas", 21, 21),
    CARPETA_SERIES("CarpetaSeries", "\\ClasesUtiles\\Multimedia\\Paquete\\CarpetaSeries", 22, 22),
    CARPETA_SERIES_CLASICAS("CarpetaSeriesClasicas", "\\ClasesUtiles\\Multimedia\\Paquete\\CarpetaSeries", 23, 23),
    CARPETA_SERIES_DOBLADAS("CarpetaSeriesDobladas", "\\ClasesUtiles\\Multimedia\\Paquete\\CarpetaSeries", 24, 24),
    CARPETA_SERIES_EN_TRANSMISION("CarpetaSeriesEnTransmision", "\\ClasesUtiles\\Multimedia\\Paquete\\CarpetaSeries", 25, 25),
    CARPETA_SERIES_FINALIZADAS("CarpetaSeriesFinalizadas", "\\ClasesUtiles\\Multimedia\\Paquete\\CarpetaSeries", 26, 26),
    CARPETA_SERIES_TEMPORADAS_FINALIZADAS("CarpetaSeriesTemporadasFinalizadas", "\\ClasesUtiles\\Multimedia\\Paquete\\CarpetaSeries", 27, 27),
    CARPETA_SERIES_TEMPORADAS_FINALIZADAS_ESPAÑOL("CarpetaSeriesTemporadasFinalizadasEspañol", "\\ClasesUtiles\\Multimedia\\Paquete\\CarpetaSeries", 28, 28),
    CARPETA_SERIES_TX("CarpetaSeriesTX", "\\ClasesUtiles\\Multimedia\\Paquete\\CarpetaSeries", 29, 29),
    CAPITULO("Capitulo", "\\ClasesUtiles\\Multimedia\\Series", 30, 30),
    CATALOGO_DE_SERIES("CatalogoDeSeries", "\\ClasesUtiles\\Multimedia\\Series", 31, 31),
    ESTADO_SERIE("estadoSerie", "\\ClasesUtiles\\Multimedia\\Series", 32, 32),
    SERIE("Serie", "\\ClasesUtiles\\Multimedia\\Series", 33, 33),
    TEMPORADA("Temporada", "\\ClasesUtiles\\Multimedia\\Series", 34, 34),
    MANEJADOR_CELL_EDITOR("ManejadorCellEditor", "\\ClasesUtiles\\Tablas", 35, 35),
    MANEJADOR_SELECTION_FILA("ManejadorSelectionFila", "\\ClasesUtiles\\Tablas", 36, 36),
    TABLA("Tabla", "\\ClasesUtiles\\Tablas", 37, 37),
    ;
    
    private final String nombre, direccionInterna;
    private final int indice;
    private int indiceActual;

    private Nombre_Clase(String nombre, String direccionInterna, int indice, int indiceActual) {
        this.nombre = nombre;
        this.direccionInterna = direccionInterna;
        this.indice = indice;
        this.indiceActual = indiceActual;
    }

    public String getDireccionInterna() {
        return direccionInterna;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIndice() {
        return indice;
    }

    public int getIndiceActual() {
        return indiceActual;
    }

    public void setIndiceActual(int indiceActual) {
        this.indiceActual = indiceActual;
    }

}
