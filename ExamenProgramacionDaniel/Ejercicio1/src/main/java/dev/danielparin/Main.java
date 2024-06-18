package dev.danielparin;


import java.util.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static final int MAX_NUM = 1000;

    public static void main(String[] args) {

        int numberElection;
        Set<Integer> primos = new TreeSet<>(); // No tendrá sentido volver a añadir un número que ya está por eso uso un TreeSet, aparte para ordenarlos.


        System.out.print("Ingrese un numero entre 1 y "+MAX_NUM+": ");
        numberElection = scanner.nextInt();

        while (numberElection<=0 | numberElection>MAX_NUM ){

            System.err.print("Numero incorrecto. Ingrese un numero entre 1 y "+MAX_NUM+": ");
            numberElection = scanner.nextInt();

        }

        /*
            Criba de números:
            1º Vemos si lo habíamos insertado ya.
            2º Vemos si el número pertenece a los 4 primeros primos.
            3º Si es par y distinto de 2 no es primo.
            4º Si hasta la mitad del número no hay ningún divisor, no podrá haber más.

            * Contamos ya con que el 1 es divisible y no llegaremos hasta el número, por tanto, a la que encuentre uno se sale.
         */

        if (primos.contains(numberElection)){

            System.out.println(numberElection + " es un numero primo.");
        }
        else if (numberElection<=3 || numberElection==5) { // ¿Es parte de los 4 primeros primos?

            System.out.println(numberElection + " es un numero primo.");
            primos.add(numberElection);

        }
        else if (numberElection % 2 == 0) { // ¿Es par?

            System.out.println(numberElection + " no es un numero primo.");
        }
        else {

            boolean isPrimo = true;
            int divisor = 3;

            while (isPrimo && divisor<=numberElection/2){ // Con nada que haya un divisor se sale.

                if (numberElection%divisor==0) {
                    isPrimo = false;
                }

                divisor+=2;

            }

            if(isPrimo){
                System.out.println(numberElection + " es un numero primo.");
                primos.add(numberElection);
            }else {
                System.out.println(numberElection + " no es un numero primo.");
            }
        }

    }
}

/*
    Podría plantearse el ejercicio que en vez de estructurarlo todo secuencialmente, tener una función que viera al principio que numeros primos sabemos,
    aparte de poder extendernos más en las reglas de division de los números (si un numero es par y su mitad es par es divisible por 8) para poder
    ajustar más la búsqueda final y con ello iterar menos veces y por tanto tener mejor rendimiento.

    Ej:
        Si sabemos que el último primo que tenemos es 11 ( sabiendo los anteriores) no tiene ninún sentido recorrer toda la estructura de código para
        saber si 7 es primo porque ya sabemos que lo es de antemano.
 */