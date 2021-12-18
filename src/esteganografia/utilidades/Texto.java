package esteganografia.utilidades;

import java.io.*;
import java.util.*;

/**
 * clase que maneja los archivos de texto.
 */
public class Texto{

	/**
	 * Método que regresa una cadena con el contenido del archivo
	 * pasado como pará,metro.
	 * @param archivo nombre del archivo de texto
	 * @return cadena el contenido del archivo
	 */ 
	public static String obtenerContenido(String archivo) throws FileNotFoundException, IOException { 
        String cadena; 
        FileReader f = new FileReader(archivo); 
        BufferedReader b = new BufferedReader(f);
        String contenido="";
        while((cadena = b.readLine())!=null) { 
            contenido+=cadena;
        } 
        b.close();
        return contenido;
    }

    /**
     * Método que escribe una cadena en un texto.
     * @param contenido cadena que se escribirá en el archivo de texto
     * @param archivo e texto en el cual será escrito el contenido.
     */
    public static void escribirContenido(String contenido, String archivo) throws FileNotFoundException, IOException {
    	FileWriter f = new FileWriter(archivo); 
        BufferedWriter b = new BufferedWriter(f);
        b.write(contenido);
        b.close();
        System.out.println("el texto se guardo en: " + archivo);
    }
}