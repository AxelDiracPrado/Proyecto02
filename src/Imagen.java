import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class Imagen{
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
    public static StringBuilder stringBinario(byte[] bytes){
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes)
            {
                int val = b;
               
                for (int i = 0; i < 8; i++)
                    {
                        binary.append((val & 128) == 0 ? 0 : 1);
                        val <<= 1;
                       
                    }
            }
        return binary;
        
    }
    public static int comparador(int canal, char binario){
        int regreso=canal;
        int lsb = canal & 1;
        int binary = binario - '0';
        if(lsb==binary) return regreso;
        else if(lsb==0 && binary==1) return regreso | 1; 
        else if(lsb==1 && binary==0) return regreso & 254; 
        return 0;
    }
    public static String recuperaBinarios(int pos,BufferedImage img,Queue<Integer> cola){
        String str = "";
        for(int i=0;i<pos;i++){
            int p = img.getRGB(0,i);
            int a = (p>>24) & 0xff;
            str += Integer.toString(cola.poll()).charAt(0);
            int r = (p>>16) & 0xff;
            str += (char) r & 1;
            int g = (p>>8) & 0xff;
            str += (char) g & 1;
            int b = p & 0xff;
            str += (char) b & 1;
        }
        return str;
    }

  public static void main(String args[])throws IOException{
      Queue<Integer> cola=new LinkedList();
    BufferedImage img = null;
    File f = null;
    String s = muestraContenido("archivo.txt"); 
    byte[] bytes = s.getBytes();
    StringBuilder string= stringBinario(bytes);
    System.out.println(string);
    int longitud=string.length();
    //read image
    try{
      f = new File("image.png");
      img = ImageIO.read(f);
    }catch(IOException e){
      System.out.println(e);
    }
    int posicion=0;
    for(int i=0;i<longitud;i=i+4){
    //conseguimos el valor del pixel
    int p = img.getRGB(0,posicion);

    //conseguimos a alpha
    int a = (p>>24) & 0xff;

    a=comparador(a,string.charAt(i));
    cola.add(a & 1);
    //conseguimos la componente roja
    int r = (p>>16) & 0xff;
    r=comparador(r,string.charAt(i+1));
    //conseguimos la componente verde
    int g = (p>>8) & 0xff;
    g=comparador(g,string.charAt(i+2));
    //conseguimos la componente azul
    int b = p & 0xff;
    b=comparador(b,string.charAt(i+3));
   
    p = (a<<24) | (r<<16) | (g<<8) | b;
    img.setRGB(0, posicion, p);

    posicion++;
    }
 
    //Escribimos la imagen
    try{
        f = new File("image.png");
        ImageIO.write(img, "png", f);
    }catch(IOException e){
        System.out.println(e);
    }
    String recuperacion=recuperaBinarios(posicion,img,cola);
    System.out.println(recuperacion);
    
    String str1 = "";
    for (int j = 0; j < recuperacion.length()/8; j++) {
        int a = Integer.parseInt(recuperacion.substring(8*j,(j+1)*8),2);
        str1 += (char)(a);
    }
    System.out.println(str1);
   
  }
}
