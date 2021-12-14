import java.util.*;
import java.io.*;
public class Main{
    public static String muestraContenido(String archivo) throws FileNotFoundException, IOException { 
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

    public static void main(String[] args) throws IOException {
        
        String s = muestraContenido("archivo.txt"); 
        byte[] bytes = s.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes)
            {
                int val = b;
               
                for (int i = 0; i < 8; i++)
                    {
                        binary.append((val & 128) == 0 ? 0 : 1);
                        val <<= 1;
                       
                    }
                binary.append(' ');
            }
        System.out.println("'" + s + "' a binario " + binary);

    }

    
}
