package esteganografia.utilidades;

/**
 * Clase que maneja cadenas binarias.
 */
public class Binarios {

	/**
	 * Método que transforma uma cadena de texto a una cadena de
	 * 0's y 1's.
	 * @param cadena de texto
	 * @return representación en binario de la cadena
	 */
	public static StringBuilder stringBinario(String cadena){
        byte[] bytes = cadena.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
			}
        }
        return binary;
    }

    /**
     * Métod que reemplaza el dígito menos signifivativo de la representación
     * binaria de un canal RGBA por otro dígito pasado como parámetro.
     * @param canal represenación entera de R, G, B o A.
     * @param binario caracter binario que sera guardado en el canal.
     * @return canal modificado
     */
    public static int modificaCanal(int canal, char binario){
        int lsb = canal & 1;
        int binary = binario - '0';
        if(lsb==binary) return canal;
        else if(lsb==0 && binary==1) return canal | 1; 
        else if(lsb==1 && binary==0) return canal & 254;
        return 0;
    }
}