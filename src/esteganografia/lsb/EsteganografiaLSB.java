package esteganografia.lsb;

import esteganografia.utilidades.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * Clase que aplica la esteganografía por el método LSB
 */
public class EsteganografiaLSB {

	/**
	 * Método que recupera de una imagen los dígitos binarios de una cadena.
	 * @param pos entero que representa hasta que posición de la imagen
	 * 			se tomarán los pixeles.
	 * @param img Buffer de la imagen que será modificada.
	 * @return el texto escondido en la imagen.
	 */
	public static String recuperaBinarios(int pos,BufferedImage img){
        String str = "";
        for(int i = 0; i < pos; i++) {
            int p = img.getRGB(0,i);
            int a = (p>>24) & 0xff;
            str +=  (char) a & 1; 
            int r = (p>>16) & 0xff;
            str += (char) r & 1;
            int g = (p>>8) & 0xff;
            str += (char) g & 1;
            int b = p & 0xff;
            str += (char) b & 1;
        }
        return str;
    }


    /**
     * Métod que recupera el texto de una imagen tomando su representación 
     * de bytes.
     * @param posicion indica el limite de imagen a usar.
     * @param imagen indica el nombre de la imagen.
     * @param salida nombre del archivo de salida.
     */
    public static void recuperarCadena(int posicion, String imagen, String salida) throws IOException {
    	BufferedImage img = null;
    	File f = null;
	    try {
      		f = new File(imagen);
      		img = ImageIO.read(f);
    	} catch(IOException e){
      		System.out.println(e);
    	}
    	String recuperacion=recuperaBinarios(posicion,img);
    
    	String str1 = "";
    	for (int j = 0; j < recuperacion.length()/8; j++) {
        	int a = Integer.parseInt(recuperacion.substring(8*j,(j+1)*8),2);
        	str1 += (char)(a);
    	}
    	Texto.escribirContenido(str1,"./archivos/" + salida + ".txt");
    }

    public static BufferedImage copiarImage(BufferedImage img) {
    	int w = img.getWidth();
    	int h = img.getHeight();
    	BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_4BYTE_ABGR);
    	for (int x = 0; x<w; x++ ) {
    		for (int y = 0; y<h;y++) {
    			image.setRGB(x,y,img.getRGB(x,y));
    		}
    	}
    	return image;
    }

    public static void esconderCadena(String cadena, BufferedImage img, String salida) {
    	BufferedImage image = copiarImage(img);
    	StringBuilder binario = Binarios.stringBinario(cadena);
    	int longitud = binario.length();
    	int posicion = 0;
    	for(int i = 0; i < longitud; i = i + 4) {
    		int p = image.getRGB(0,posicion);
    		int a = (p>>24) & 0xff;
    		a = Binarios.modificaCanal(a,binario.charAt(i));
	    
	    	int r = (p>>16) & 0xff;
    		r=Binarios.modificaCanal(r,binario.charAt(i+1));
		
	    	int g = (p>>8) & 0xff;
    		g=Binarios.modificaCanal(g,binario.charAt(i+2));
	    
    		int b = p & 0xff;
    		b=Binarios.modificaCanal(b,binario.charAt(i+3));
	   
    		p = (a<<24) | (r<<16) | (g<<8) | b;
    		image.setRGB(0, posicion, p);

	    	posicion++;
    	}
 
    	try {
        	File outputfile = new File("./archivos/" + salida + ".png");
			ImageIO.write(image, "png", outputfile);
    	} catch(IOException e) {
	        System.out.println(e);
    	}   
	}

	public static void esconderTexto(String entrada, String imagen, String salida) throws IOException {
		BufferedImage img = null;
    	File f = null;
	    try {
      		f = new File(imagen);
      		img = ImageIO.read(f);
    	} catch(IOException e){
      		System.out.println(e);
    	}
    	String texto = Texto.obtenerContenido(entrada); 
    	esconderCadena(texto, img, salida);
	}


}
	
