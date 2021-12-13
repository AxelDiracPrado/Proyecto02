import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/**
 * Clase que ayuda a obtener, modificar y manipular los pixeles de una imagen.
 */
public class PixelImage {
	private BufferedImage image;

	public PixelImage(String imgRute) throws IOException{
		this.image = ImageIO.read(new File(imgRute));
	}

	public void cambiarPixel() throws IOException {
		int h = image.getHeight();
		int w = image.getWidth();
		
		int[] dataBuffInt = image.getRGB(0, 0, w, h, null, 0, w);
		for(int i = 0; i < dataBuffInt.length -10; i++) {
			Color c = new Color(dataBuffInt[i],true);
			int red = c.getGreen();
			int green = c.getGreen();
			int blue = c.getGreen();
			int alpha = c.getAlpha();
			c = new Color(red,green,blue,alpha);
			dataBuffInt[i] = c.getRGB();
		}
		image.setRGB(0,0,w,h,dataBuffInt,0,w);
		File outputfile = new File("saved.png");
    	ImageIO.write(image, "png", outputfile);
	}

	public static void main(String[] args) throws IOException{
		PixelImage image = new PixelImage("image.png");
		image.cambiarPixel();
	}

	public void modificaDosPixeles(char caracter, int pixel1,int pixel2) {
		int cr = (int) caracter;
		Color color1 = new Color(pixel1,true);
		Color color2 = new Color(pixel2,true);

	}

	
}