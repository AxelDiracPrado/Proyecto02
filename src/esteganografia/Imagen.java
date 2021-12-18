package esteganografia;

import esteganografia.lsb.EsteganografiaLSB;
import esteganografia.utilidades.*;
import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class Imagen{
    
    

  public static void main(String args[]) throws IOException {
        if (args[0].equals("h")) {
            EsteganografiaLSB.esconderTexto(args[1],args[2],args[3]);
        } else if (args[0].equals("u")) {
            EsteganografiaLSB.recuperarCadena(100, args[1],args[2]);
        }
        else {
            System.out.println("Opción equivocada.");
        }
    }
}
