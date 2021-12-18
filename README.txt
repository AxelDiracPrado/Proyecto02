# Proyecto 02.

	## Compilar:
		### javac -d target/ src/esteganografia/Imagen.java src/esteganografia/utilidades/* src/esteganografia/lsb/EsteganografiaLSB.java
	
	## Ejecutar:
		### java -cp target/ esteganografia.Imagen <opcion> arg1 arg2 arg3
			<opcion>:
				h: ocultar texto.
					arg1: Ruta y nombre del archivo que contiene el texto a ocultar.
					arg2: Rutay nombre del archivo de imagen.
					arg3: El nombre del archivo de imagen resultante con los datos ocultos.
				
				u: develar texto.	
					arg1: La ruta y nombre del archivo con la imagen que contiene los datos ocultos.
					arg2: El nombre del archivo en el que se guardará el texto develado.

