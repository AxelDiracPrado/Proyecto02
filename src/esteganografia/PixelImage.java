import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;



/**
 * Clase que ayuda a obtener, modificar y manipular los pixeles de una imagen.
 */
public class PixelImage {
	private BufferedImage image;

	public PixelImage(String imgRute) throws IOException{
		this.image = ImageIO.read(new File(imgRute));
	}


	public char[] arregloBinario(int i) {
		String iBinario = Integer.toBinaryString(i);
		while(iBinario.length() < 8) {
			iBinario ="0" + iBinario;
		}
		char[] iArreglo = iBinario.toCharArray();
		return iArreglo;
	}

	public int binToInt(char[] binario) {
		String binarioStr = new String(binario);
		int entero = Integer.parseInt(binarioStr,2);
		return entero;
	}

	public int[] componentesPixel(int pixel) {
		int[] componentes = new int[4];
		Color colorPixel = new Color(pixel);
		componentes[0] = colorPixel.getRed();
		componentes[1] = colorPixel.getGreen();
		componentes[2] = colorPixel.getBlue();
		componentes[3] = colorPixel.getAlpha();
		return componentes;
	}

	public int obtenerRGBA(int[] comps) {
		Color c =  new Color(comps[0], comps[1],comps[2],comps[3]);
		return c.getRGB();
	}


	public int[] esconderChar (int pixel1, int pixel2, char c) {
		int[] pixeles = new int[2];
		char[] cBin = arregloBinario(c);
		int[] compPix1 = componentesPixel(pixel1);
		int[] compPix2 = componentesPixel(pixel2);
		for(int i = 0; i < cBin.length; i++){
			if(i < 4) {
				char[] pix1Comp = arregloBinario(compPix1[i]);
				pix1Comp[7] = cBin[i];
				compPix1[i] = binToInt(pix1Comp);

			} else{
				char[] pix2Comp = arregloBinario(compPix2[i-4]);
				pix2Comp[7] = cBin[i];
				compPix2[i-4] = binToInt(pix2Comp);
			}
		}
		pixeles[0] = obtenerRGBA(compPix1);
		pixeles[1] = obtenerRGBA(compPix2);
		return pixeles;
	}

	public void esconderString(String str) throws IOException {
		int h = image.getHeight();
		int w = image.getWidth();
		char[] charText = str.toCharArray();
		int[] dataBuffInt = image.getRGB(0, 0, w, h, null, 0, w);
		for(int i = 0; i < charText.length; i++) {
			int pixel1 = dataBuffInt[2*i];
			int pixel2 = dataBuffInt[2*i + 1];
			dataBuffInt[2*i] = esconderChar(pixel1,pixel2,charText[i])[0];
			dataBuffInt[2*i + 1] = esconderChar(pixel1,pixel2,charText[i])[1];
		}
		image.setRGB(0,0,w,h,dataBuffInt,0,w);
		File outputfile = new File("saved.png");
		ImageIO.write(image, "png", outputfile);
	} 

	public static void main(String[] args) throws IOException{
		PixelImage image = new PixelImage("image.png");
		image.esconderString("AxelPradoEscamilla");
		System.out.println(new String(image.arregloBinario(2)));
	}


	
}