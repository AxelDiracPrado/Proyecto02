package esteganografia.utilidades;

import esteganografia.utilidades.Binarios;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;



/**
 * Clase que ayuda a obtener, modificar y manipular los pixeles de una imagen.
 */
public class PixelImage {

	
	public static int[] componentesPixel(int pixel) {
		int[] componentes = new int[4];
		Color colorPixel = new Color(pixel, true);
		componentes[1] = colorPixel.getRed();
		componentes[2] = colorPixel.getGreen();
		componentes[3] = colorPixel.getBlue();
		componentes[0] = colorPixel.getAlpha();
		return componentes;
	}

	public static int obtenerRGBA(int[] comps) {
		Color c =  new Color(comps[1], comps[2],comps[3],comps[0]);
		return c.getRGB();
	}	
}