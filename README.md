### Ejercicio 1 
(3 ptos)

Escribe un programa en Java que determine si un número dado por el usuario es primo o no. El número debe estar en el rango de 1 hasta un límite puesto como constante, p.e 1000.

Requisitos:
 - El programa debe solicitar al usuario que introduzca un número entero entre 1 y el límite superior (1000).
 - Debe verificar si el número es primo.
 - Un número primo es aquel que solo es divisible por 1 y por sí mismo.
 - El programa debe imprimir un mensaje indicando si el número es primo o no.

Ejemplo de Entrada/Salida:

``` sh
Ingrese un número entre 1 y 1000: 29
29 es un número primo.
```

(+1 punto)
Haz una segunda versión de la aplicación donde vayas guardando los primos ya calculados y compruebes primero si ya se calculó previamente.


### Ejercicio 2
(3 ptos)

Escribe un programa en Java que lea un fichero CSV,  filtre la información, y escriba los resultados en un nuevo fichero CSV.

Requisitos:
 - Crea una clase Persona con atributos nombre, edad, sexo, y profesion.
 - Lee los datos desde un fichero CSV llamado input.csv que contiene una lista de personas en el formato nombre,edad,sexo,profesion.
 - pregunta al usuario por la edad, el sexo y la profesión y filtra los datos según la entrada ("" o intro por parte de el usuario en un campo indicará que no se filtrará dicho campo)
 - Escribe los resultados en un nuevo fichero CSV llamado output.csv.

CSV de entrada (input.csv):
 - La edad será un número entre 1 y 100
 - El sexo ["F", "M", "O"]
 - Profesión solo podrá ser ["Ingeniero", "Doctor", "Abogado", "Profesor", "Estudiante", "Arquitecto", "Enfermero", "Científico", "Contador", "Artista", "Mecánico", "Chef", "Electricista",  "Periodista"]

Ejemplo de Entrada/Salida:

``` sh
Ingrese una edad concreta para filtrar por esa edad: 41
Ingrese un sexo, escribiendo solo la inicial ((M)asculino/(F)emenino/(O)tro): F
Ingrese una profesión: Chef
```

(+1 punto)
Añade a la aplicación:
 - control sobre el fichero de salida, preguntando al usuario qué nombre le quiere poner y avisando de, si existe, si se quiere sobreescribir.
 - cambiar la edad para que admita un número o 2 separados por comas para definir un rango. Ej: 19,85 (los mayores a 19 y menores a 85 ambos incluidos)
 - aceptar más de una profesión separando por comas. Ej: Chef,Abogado (cualquiera que sea chef o abogado) 



### Ejercicio 3
(4 ptos)

Escribe un programa en Java que realice operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en una base de datos SQL que elijas. El programa debe gestionar una lista de productos con un identificador (del tipo que elijas), nombre y precio.

Requisitos:
 - Crea una clase Producto con atributos id, nombre, y precio.
 - Configure una base de datos sql y crea una tabla: productos con las columnas id, nombre, y precio.
 - Implementa las operaciones CRUD:
    - Crear: Añadir un nuevo producto.
    - Leer: Mostrar la lista de productos.
    - Actualizar: Modificar los detalles de un producto existente.
    - Eliminar: Borrar un producto de la base de datos.
 - Usa conceptos de OOP para estructurar el programa e indica cuales son (con comentarios).



#### Método de evaluación
 - La resolución correcta del ejercicio representará el 70% de la nota.
 - La limpieza en el código representará el 10% de la nota.
 - Los comentarios explicando las decisiones importantes representará el 20%.
 - Se puntuará sobre 10 aunque el máximo sea 12.

¡Buena suerte con tu examen! 
