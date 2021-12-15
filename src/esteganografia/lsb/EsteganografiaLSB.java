package esteganografia.lsb;

import esteganografia.utilidades.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;

public class EsteganografiaLSB {

	private BufferedImage image;

	public EsteganografiaLSB(String imgRute) throws IOException{
		this.image = ImageIO.read(new File(imgRute));
	}

	public int[] esconderChar (int pixel1, int pixel2, char c) {
		int[] pixeles = new int[2];
		char[] cBin = Binarios.arregloBinario(c);
		int[] compPix1 = PixelImage.componentesPixel(pixel1);
		int[] compPix2 = PixelImage.componentesPixel(pixel2);
		for(int i = 0; i < 8; i++){
			if(i < 4) {
				char[] pix1Comp = Binarios.arregloBinario(compPix1[i]);
				pix1Comp[7] = cBin[i];
				compPix1[i] = Binarios.binToInt(pix1Comp);
			} else{
				char[] pix2Comp = Binarios.arregloBinario(compPix2[i-4]);
				pix2Comp[7] = cBin[i];
				compPix2[i-4] = Binarios.binToInt(pix2Comp);
			}
		}
		pixeles[0] = PixelImage.obtenerRGBA(compPix1);
		pixeles[1] = PixelImage.obtenerRGBA(compPix2);
		return pixeles;
	}

	public void esconderString(String str, String salida) throws IOException {
		int h = image.getHeight();
		int w = image.getWidth();
		char[] charText = str.toCharArray();
		int[] dataBuffInt = image.getRGB(0, 0, w, h, null, 0, w);
		System.out.println(obtenerCaracter(dataBuffInt[3],dataBuffInt[4]));
		for(int i = 0; i < charText.length; i++) {
			int pixel1 = dataBuffInt[2*i];
			int pixel2 = dataBuffInt[2*i + 1];
			int[] pixeles = esconderChar(pixel1,pixel2,charText[i]);
			dataBuffInt[2*i] = pixeles[0];
			dataBuffInt[2*i + 1] = pixeles[1];
		}
		System.out.println(obtenerCaracter(dataBuffInt[3],dataBuffInt[4]));

		image.setRGB(0,0,w,h,dataBuffInt,0,w);
		File outputfile = new File("./archivos/"+ salida + ".png");
		ImageIO.write(image, "png", outputfile);
	} 

	public char obtenerCaracter(int pixel1, int pixel2) {
		char[] cBin = new char[8];
		int[] compPix1 = PixelImage.componentesPixel(pixel1);
		int[] compPix2 = PixelImage.componentesPixel(pixel2);
		for(int i = 0; i < cBin.length; i++){
			if(i < 4) {
				char[] pix1Comp = Binarios.arregloBinario(compPix1[i]);
				cBin[i] = pix1Comp[7];
			} else{
				char[] pix2Comp = Binarios.arregloBinario(compPix2[i-4]);
				cBin[i] = pix2Comp[7];
			}
		}
		char c = (char) Binarios.binToInt(cBin);
		return c;
	}

	public String obtenerString() {
		String salida = "";
		int h = image.getHeight();
		int w = image.getWidth();
		int longTexto = (h-1)*(w-1);
		int[] dataBuffInt = image.getRGB(0, 0, w, h, null, 0, w);
		for(int i = 0; i < 3; i++) {
			int pixel1 = dataBuffInt[2*i];
			int pixel2 = dataBuffInt[2*i + 1];
			salida += obtenerCaracter(pixel1,pixel2);
		}
		return salida;
	}

	public static void main(String[] args) throws IOException{
		EsteganografiaLSB uno = new EsteganografiaLSB("./archivos/image.png");
		uno.esconderString("aa","axel");
		System.out.println(uno.obtenerString());
		EsteganografiaLSB otro = new EsteganografiaLSB("./archivos/axel.png");
		System.out.println(otro.obtenerString());		




		int[] pixeles = uno.esconderChar(-1,-33286381,'a');

		int[] comps = PixelImage.componentesPixel(-1);
		System.out.println(Arrays.toString(comps));
		String[] bins = new String[4];
		for(int i = 0; i <4; i++) {
			bins[i] = new String(Binarios.arregloBinario(comps[i]));
		}
		System.out.println(Arrays.toString(bins));

		System.out.println(Arrays.toString(Binarios.arregloBinario('a')));

		System.out.println(pixeles[0]);
		int[] comps2 = PixelImage.componentesPixel(pixeles[0]);
		System.out.println(Arrays.toString(comps2));
		String[] bins2 = new String[4];
		for(int i = 0; i <4; i++) {
			bins2[i] = new String(Binarios.arregloBinario(comps2[i]));
		}
		System.out.println(Arrays.toString(bins2));

		char c = uno.obtenerCaracter(pixeles[0],pixeles[1]);
		System.out.println(c);

		


	}

}