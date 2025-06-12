Este proyecto, como veremos, usa `maven` para gestionar sus dependencias.

Por ello, la primera vez que abrimos el proyecto (cuando no existe aún la subcarpeta `.idea/`), seleccionames el fichero `pom.xml` y le decimos que lo **abra como proyecto**

- Si al abrirlo se muestra algún error, **seguid las instrucciones para subsanarlo** (normalmente piden que se haga un `build` del proyecto)

- Puede ser necesario que en la configuración de IntelliJ se cambie `maven/importing/JDK for importer` a la versión de java que estéis usando.

# Sub-segmento consecutivo de suma máxima

Dado un array, calcular el máximo de las sumas de sus subsegmentos (de elementos consecutivos) no vacíos.

Si el array está vacío, no tiene subsegmentos no vacíos, y el valor a retornar serà `Integer.MIN_VALUE`

## JMH

- [JMH](https://openjdk.org/projects/code-tools/jmh/) (Java Microbenchmarking Harness) es el framework oficial de Java para realizar pruebas de rendimiento
- Puntos importantes:
  - Dependencias:
    - org.openjdk.jmh:jmh-core:1.37
    - org.openjdk.jmh:jmh-generator-annprocess:1.37
  - Los métodos cuyo rendimiento se ha de computar anotados con `@Benchmark`
  - El programa principal configura las opciones de la ejecución
- Enlaces sobre JMH:
  - [Java Microbenchmarks with JMH, Part 1](https://blog.avenuecode.com/java-microbenchmarks-with-jmh-part-1)
  - [Java Microbenchmarks with JMH, Part 2](https://blog.avenuecode.com/java-microbenchmarks-with-jmh-part-2)
  - [Java Microbenchmarks with JMH, Part 3](https://blog.avenuecode.com/java-microbenchmarks-with-jmh-part-3)
  - [Microbenchmarking with Java](https://www.baeldung.com/java-microbenchmark-harness)
  - [JMH - Java Microbenchmark Harness](https://howtodoinjava.com/java/library/jmh-java-microbenchmark-harness/)
  - [JMH - Java Microbenchmark Harness](https://jenkov.com/tutorials/java-performance/jmh.html)
  
## Maven

- Aunque podríamos añadir, de forma más o menos similar a lo que hacemos con JUnit, la biblioteca JMH, cuando el número de dependencias de un proyecto aumentas, es conveniente usar un gestor de dependencias
- En el mundo de java, el más utilizado es `maven` que, al ser tan utilizado, permite ser usado desde IntelliJ de forma transparente
- Puntos importantes:
  - `pom.xml` fichero de configuración
  - `src/main/java` directorio del código del proyecto
  - `src/test/java` directorio del código de tests

## MCSS1

Es la solución *immediata*:

- se generan el inicio del subsegmento
- se general el final del subsegmento
- se suma el subsegmento
- se actualiza el máximo encontrado hasta el momento

¿Què coste tiene esta solución?

Rendimiento medido con JMH (en un momento concreto de una máquina concreta):

       N   |  Average Time
    -------|--------------------------------
      100  |   45877,142 ±   4630,199  ns/op
      200  |  336634,262 ±  27578,471  ns/op
      300  | 1217840,548 ± 436414,836  ns/op
      400  | 2814769,609 ± 154539,861  ns/op
      500  | 5547684,765 ± 136939,676  ns/op

## MCSS2

Si se analiza con más calma el problema, se puede encontrar una **solución lineal**.

**Pista:** A cada vuelta del bucle considerar que se lleva calculada la suma del subsegmento máximo que acaba en esa posición.

Podéis encontrar dicha solución en el artículo de la wikipedia titulado ([Maximum subarray problem](https://en.wikipedia.org/wiki/Maximum_subarray_problem)).

En este caso, el rendimiento medido por JMH ha siso:

       N   |  Average Time
    -------|------------------------
      100  |  94,373 ±  1,512  ns/op
      200  | 188,155 ±  4,392  ns/op
      300  | 290,124 ± 28,791  ns/op
      400  | 371,268 ±  5,074  ns/op
      500  | 469,449 ±  5,802  ns/op
