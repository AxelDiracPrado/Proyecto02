package esteganografia.utilidades;

public class Binarios {

	public static char[] arregloBinario(int i) {
		String iBinario = Integer.toBinaryString(i);
		while(iBinario.length() < 8) {
			iBinario ="0" + iBinario;
		}
		char[] iArreglo = iBinario.toCharArray();
		return iArreglo;
	}

	public static int binToInt(char[] binario) {
		String binarioStr = new String(binario);
		int entero = Integer.parseInt(binarioStr,2);
		return entero;
	}
}