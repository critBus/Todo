/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.actualizador.de.series;

import Utiles.MetodosUtiles.*;
import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Fichero;
import Utiles.ClasesUtiles.Multimedia.Paquete.Paquete;
import Utiles.ClasesUtiles.Multimedia.Series.*;
import Utiles.ClasesUtiles.Z;
import Utiles.Exepciones.ExisteException;
import Utiles.Exepciones.NoPermitidoException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class ActualizadorDeSeries {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, NoPermitidoException, ExisteException {
//        File f1 = new File("C:\\1\\Experimento\\Nueva carpeta (2)");//15,kb
//        Videos.eliminarNumeroDelPricipio(f1.toString());
//        File f2 = new File("G:\\Manga");//15,kb
//         File f3 = new File("G:\\Revolico [A FULL CON BUSCADOR] [02-08-19] .iso");//15,kb
//        double n=Archivo.tamañoArchivo(f2);
//         System.out.println(Archivo.tamaño(n));
//System.out.println(Paquete.constantesDelPaquete(Paquete.palabrasDelPaquete()));
      
        
        ConfiguracionDeVideo cdv = ConfiguracionDeVideo.getDefault();
        String di = "D:\\Series(Mangas)";//Brave 10 - 02.
        String di2 = "G:\\Manga\\Mangas [Anime] 2019\\Anime Online [Transmision]";
        String di3 = "G:\\Manga";
        String di4 = "D:\\Series(Mangas)\\__Nuevo\\Anime Online [Transmision]";
        String di5 = "G:\\Paquete\\Manga\\Anime Online [Transmision]";
        String di6 = "D:\\Series(Mangas)\\Por ver2.txt";
        String di7 = "D:\\Series(Mangas)\\__Finalizadas";
        String di8 = "G:\\Series";
        String di9 = "G:\\Series";
        String di10 = "G:\\Nueva carpeta\\Nueva carpeta\\Nueva carpeta\\[[24-06-2019]]\\Series\\En Transmision\\Series [TX]";
        String di11 = "G:\\Paquete\\Mangas [Anime] 2019\\Anime Online [Transmision]";
        String di12 = "E:\\Nueva carpeta";
        String di13 = "G:\\Series\\contenido.txt";
        String di14 = "G:\\_Seguridad\\Paquete Conv.txt";
        String di15 = "G:\\Nueva carpeta\\[[12-08-2019]]\\Series\\Finalizadas\\Series [Temporadas Finalizadas]";
        String di16 = "C:\\1\\Experimento\\Series [TX]";
        String di17 = "C:\\1\\Experimento\\! Subtitulos";
         String di18 = "F:\\nuevo\\Manga";
//        new Serie(di, cdv).imprimir();

//System.out.println(MetodosUtiles.containsString("Seitokai Yakuindomo 2nd Season 13 - 1 OVA",true, Videos.identificadoresTemporadas));
//          int cap[] = Videos.getCapitulosDeNombre(new File("D:\\Series(Mangas)\\__Finalizadas\\Brave 10\\Brave 10 - 01.mp4").getName(), cdv);
//          System.out.println(Arrays.toString(cap));
//                int cap2[] = Videos.getCapitulosDeNombre(new File("D:\\Series(Mangas)\\__Finalizadas\\Brave 10\\Brave 10 - 02.mp4").getName(), cdv);
//          System.out.println(Arrays.toString(cap2));
        //String di="D:\\Series(Mangas)\\__viejos";//D:\Series(Mangas)\__Finalizadas\Brave 10
//        String di = "D:\\Series(Mangas)";
        String noVal[] = {"__Finalizadas", "__Nuevo", "__ver", "__viejos", "_Comics", "_Peliculas Mangas", "_Videos de (Series Mangas)", "_Wallpaper (Mangas)", "_Presentaciones"};
        String noVal2[] = {"!! Wallpaper Mangas"};
        String noVal3[] = {"Anime Online [Transmision]", "Finalizadas"};
        String noVal4[] = {};
        String noVal5[] = {"!! Videos de Series Mangas", "!! Wallpaper Mangas", "Peliculas Mangas (Clasicas)", "Series Mangas Finalizadas Por Temporadas"};
        String noVal6[] = {};
        String noVal7[] = {};
        String noVal8[] = {};
        String noVal9[] = {"! Subtitulos", "!!!Estrenos"};
        String noVal10[] = {"! Subtitulos"};
        String noVal11[] = {};
        String noVal12[] = {};
        String noVal13[] = {};
        String noVal14[] = {};
        //Snowfall
//        System.out.println(Videos.getNombreSerie("Temple - [Temp 1] [Cap-01] [MP4] [441,45 Mb]", cdv));
//        System.out.println(new File(di12).exists());
//        System.out.println(Videos.getNombreSerie("Sex Education S01", cdv));
//        System.out.println("di16="+new File(di16).exists());
        CatalogoDeSeries c1 = new CatalogoDeSeries(new File("G:\\Nueva carpeta\\[[16-09-2019]]\\Series\\En Transmision\\Series [TX]"), noVal8, cdv);//5
        c1.imprimir(); //Profilage.2x09.l'âge.sombre
//        CatalogoDeSeries c2 = new CatalogoDeSeries(new File("C:\\1\\Experimento\\Nueva carpeta (3)"), noVal13, cdv);
//        c2.imprimir();
//        c1.addAll(c2);
//        c1.copiarSubtitulosACapitulos(cdv);
//         CatalogoDeSeries c3=c1.getCatalogoSeriesPrimerosCapitulos();
//          c3.imprimir();
//          System.out.println(Videos.lineaValida("     novelas en transmision"));
//        System.out.println("******************************************************");
        //  c2.imprimirNombresDeSeries();
        // System.out.println("******************************************************");
//        c3.imprimir();
        
//        Z<String> respuestaGetClave = Videos.getClaveZ("Temple - [Temp 1] [Cap-01] [MP4] [441,45 Mb]", cdv, null);
//        System.out.println("respuestaGetClave.respuestaString="+respuestaGetClave.respuestaString);
        //   System.out.println(compararStringAConStringB("Araburu Kisetsu no Otome-domo yo. Episodio 1.mp4", "Black Clover (TV) Episodio 90 Sub Español — AnimeFLV.mp4", true));
        //System.out.println(Archivo.getNombre("G:\\Paquete\\Mangas [Anime] 2019\\Anime Online [Transmision]\\Araburu Kisetsu no Otome-domo yo. Episodio 1.mp4"));
        // System.out.println(Videos.esVideo(new File("G:\\Paquete\\Mangas [Anime] 2019\\Anime Online [Transmision]\\Araburu Kisetsu no Otome-domo yo. Episodio 1.mp4")));
//        System.out.println(Videos.getClave("Dr. Stone Episodio 1.mp4", cdv));
//        System.out.println(Videos.getClave("Strike Witches_ 501 Butai Hasshin Shimasu! 9 {04-06-2019}", cdv));
//        System.out.println(Videos.getClave("C The Money of Soul and Possibility Control", cdv));
//        System.out.println(Archivo.nombresRelacionados(Videos.getClave("The InBeetwen", cdv), Videos.getClave("The InBetween", cdv)));
//        String a="W4RR10R SUB T01XE01";
//        System.out.println(Arrays.toString(Videos.getCapitulosDeNombreDelFinal("5 01 Gotham - [Temp 5] [Cap-01] [MP4] [367,37 Mb]", cdv)));
//        for (int i = 0; i < a.length(); i++) {
//            System.out.println("i="+i+" "+a.charAt(i));
//        }
//        aaaaaaaaaaaaaaaaaa
//        System.out.println(Arrays.toString(MetodosUtiles.containsStringSeparadoIndice("01-Desaparecidos {(temp 3)(esp)})", true, cdv.identificadoresTemporadas)));
//System.out.println(compararStringAConStringB(getNombrePrincipal(), o.getNombrePrincipal(), true));
//         Z<String> respuestaGetClave = Videos.getClaveZ("OtakUniverse.com_Pumpkin_Scissors_20_www.mundo-animex.com", cdv, null);
//         System.out.println(respuestaGetClave.respuesta);
//        System.out.println(Archivo.getNombre("D:\\Series(Mangas)\\__Finalizadas\\Isshuukan Friends\\Isshuukan friends - [Temp 1] [Cap-01] [MP4] [99.01 Mb]"));
//         String di="D:\\Series(Mangas)\\strike the blood";
//        new Serie(di, cdv).imprimir();
//        String a[] = Archivo.getSetNombresClaves(Videos.getFicherosDeVideo(di, noVal, cdv));
////        for (String a1 : a) {
////            System.out.println(a1);
////        }
//        String di2 = "G:\\Paquete\\Manga\\Anime Online [Transmision]";
//      //  String noVal2[] = {"__Finalizadas", "__Nuevo", "__ver", "__viejos", "_Comics", "_Peliculas Mangas", "_Videos de (Series Mangas)", "_Wallpaper (Mangas)", "_Presentaciones"};
        // String a2[] = Archivo.getSetNombresClaves(Videos.getFicherosDeVideo(di2,  cdv));
//        LinkedList<Fichero> rel = Archivo.getFicherosRelacionados(a, Videos.getFicherosDeVideo(di2, cdv));
//        for (Fichero fi : rel) {
//            System.out.println(fi.getNombre());
//        }
//// Archivo.crearTXT(di,"nuevo", a);
//
////        int ii[][]=Fichero.indicesSeparador("BOKU NO HERO ACADEMIA   ===My Hero Academia");
////        Arreglos.MostrarMatriz(ii);
////        
////        String d1="Isekai Maou to Shoukan Shoujo no Dorei Majutsu +++";
////        String c1=Archivo.getNombreRelacionadoClave(d1, cdv);
////        System.out.println(c1);
////        String d2="Isekai wa Smartphone to Tomo ni";
////        String c2=Archivo.getNombreRelacionadoClave(d2, cdv);
////        System.out.println(c2);
////        String d3="Boku Dake Ga Inai Machi";
////        String c3=Archivo.getNombreRelacionadoClave(d3, cdv);
////        System.out.println(Archivo.nombresRelacionados(c1, c2));
////        System.out.println(Archivo.nombresRelacionados(c1, c3));
////        System.out.println(Archivo.nombresRelacionados(c3, c2));
//        System.out.println(Videos.getNombreSerie(new File("CSI NY   [ - [Temp 2] [Cap-12] [AVI]"), 0, 19, cdv));
//        File d = new File("G:\\Nueva carpeta\\# ! 5.8.2019...[EL TROYANO]");//G:\Nueva carpeta\[[05-08-2019]]
//        File d2 = new File("G:\\Nueva carpeta\\[[05-08-2019]]");
//        File d3 = new File("G:\\Nueva carpeta\\Nueva carpeta\\Nueva carpeta\\[[24-06-2019]]");
//        File d4= new File("G:\\Nueva carpeta\\Nueva carpeta\\Nueva carpeta (2)\\[[01-07-2019]]");
//        File d5 = new File("G:\\Nueva carpeta\\Nueva carpeta\\Nueva carpeta (3)\\[[08-07-2019]]");
//        File d6 = new File("G:\\Nueva carpeta\\Nueva carpeta\\Nueva carpeta (4)\\[[15-07-2019]]");
//        File d7 = new File("G:\\Nueva carpeta\\Nueva Carpeta (2)\\[[22-07-2019]]");
//        File d8 = new File("G:\\Nueva carpeta\\Nueva Carpeta (2)\\[[29-07-2019]]");
//        File d10 = new File("G:\\Nueva carpeta\\# ! 5.8.2019...[EL TROYANO]");

//        System.out.println(Paquete.constantesDelPaquete(Paquete.palabrasDelPaquete(d,d2,d3,d4,d5,d6,d7,d8)));
//        ;
    }
}
