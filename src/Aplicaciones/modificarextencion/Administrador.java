/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aplicaciones.modificarextencion;

import static Utiles.MetodosUtiles.Archivo.*;
import java.util.Hashtable;
import Utiles.Exepciones.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author Rene
 */
public class Administrador implements Serializable {
    
    private Hashtable<String, Direccion> map;
    public static final String YA_EXITE = "Este nombre ya existe", PIN_ERRONEO = "Su numero es incorrecto", NO_ENCONTRADO = "No se encontro el nombre";
    
    public Administrador() {
        map = new Hashtable<String, Direccion>();
    }
    
    public Administrador(String direccion) throws IOException, ClassNotFoundException {
        map = ((Administrador) cargarArchivoYCrearDeSerNesesario(direccion, new Administrador())).map;
        
    }
    
    public String getDireccion(String nombre, int PIN) throws NoEncontradoException, PINException {
        comprobarPIN(nombre, PIN);
        return map.get(nombre).getDireccion(PIN);
    }
    
    public void addDireccion(String nombre, String direccion, int PIN) {
        addDireccion(nombre, new Direccion(direccion, PIN));
    }
    
    public void addDireccion(String nombre, Direccion d) {
        map.put(nombre, d);
    }

    public void addDirecciones(Administrador A) {
        map.putAll(A.map);
    }
    
    public void canviarNombre(String nombre, int PIN, String nuevoNombre) throws NoEncontradoException, PINException, ExisteException {
       // System.out.println("nombre="+nombre);
        //   System.out.println(Arrays.toString(map.keySet().toArray(new String[]{})));
        comprobarPIN(nombre, PIN);
        comprobarSiYaExiste(nuevoNombre);
        map.put(nuevoNombre, map.get(nombre));
        map.remove(nombre);
    }
    
    public void canviarDireccion(String nombre, int PIN, String nuevaDireccion) throws NoEncontradoException, PINException {
        comprobarPIN(nombre, PIN);
        map.get(nombre).setDireccion(nuevaDireccion, PIN);
    }
    
    public void remove(String nombre, int PIN) throws NoEncontradoException, PINException {
        comprobarPIN(nombre, PIN);
        map.remove(nombre);
    }
    
    public void comprobarPIN(String nombre, int PIN) throws NoEncontradoException, PINException {
        if (!map.containsKey(nombre)) {
            throw new NoEncontradoException(NO_ENCONTRADO);
        }
        if (PIN > 9999 || map.get(nombre).getPIN(PIN) != PIN) {
            //    System.out.println("rrrrrrrrrrrrrrrrrrrr");
            throw new PINException(PIN_ERRONEO);
        }
    }
    
    public void comprobarSiYaExiste(String nombre) throws ExisteException {
        if (map.containsKey(nombre)) {
            throw new ExisteException(YA_EXITE);
        }
    }
    
    public String[] getNombres() {
        return map.keySet().toArray(new String[]{});
    }

    public void guardar(String direccion) throws IOException {
        crearArchivo(direccion, this);
    }
    
}
