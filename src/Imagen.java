import esteganografia.lsb.EsteganografiaLSB;
import esteganografia.utilidades.*;
import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class Imagen{
    
    

  public static void main(String args[]) throws IOException {
        EsteganografiaLSB.esconderTexto("./archivos/archivo.txt","./archivos/image.png","salida");
        EsteganografiaLSB.recuperarCadena(100, "./archivos/salida.png","algo");
    }

}
