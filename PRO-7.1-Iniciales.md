## Preguntas 

## 1.¿Que librería/clases has utilizado para realizar la práctica.? Comenta los métodos mas interesantes

He utilizado la clase File que tiene un método llamado readLines() que te retorna una lista con todas las líneas que cotenga el fichero.

## 2. ¿Que formato le has dado a la información del fichero para guardar y recuperar la información? 

Le he dado el formato "texto1|texto2|texto3".

## 2. ¿Que estrategia has usado para trabajar con los ficheros? (Carpeta en donde se guardan los ficheros, cuando crear los archivos, ....) 

Se crean los ficheros si es qeu no existen en la carpeta salida, si existen solo edita el contenido.

## 2.c ¿Cómo se gestionan los errores? Pon ejemplos de código (enlace permanente al código en GitHub).

En el caso en el que haya algún fallo al realizar alguna operacion se lanza un excepcion: 

- DaoHotel : https://github.com/IES-Rafael-Alberti/2526-u7-7-3-travelbookerdao-oscargarciajaen/blob/879bbdec9e038b340fd5ec10096040c8186773c2/src/main/kotlin/es/iesra/datos/DaoHotel.kt#L10-L23

- DaoVuelo : https://github.com/IES-Rafael-Alberti/2526-u7-7-3-travelbookerdao-oscargarciajaen/blob/879bbdec9e038b340fd5ec10096040c8186773c2/src/main/kotlin/es/iesra/datos/DaoVuelo.kt#L12-L21

## 3. Describe la forma de acceso para leer información 

Leo los datos del archivo con readLine(), luego lo convierto en un objeto del tipo de reserva que sea y asi puedo mostrarlo.

## 3. Describe la forma de acceso para escribir información 

Accedo al archivo si existe, si no, lo creo y escribo los datos separados por el carácter |.

## 3. Describe la forma de acceso para actualizar información. Pon ejemplos de código (enlace permanente al código en GitHub).

Busco mediante el id y sobreescribo los datos de la reserva manteniendo el id.

Código: 

