package dev.danielparin;


import dev.danielparin.models.Persona;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    static Scanner scanner = new Scanner(System.in);
    static final String PATH_FICHERO = "input.csv";

    static ArrayList<Persona> personas = new ArrayList<>();
    static ArrayList<Persona> personasFiltradas = new ArrayList<>();

    public static void main(String[] args) {

        leerCsv();

        System.out.println("Ingrese una edad o rango de edad para filtrar: ");
        String[] filtEdad = scanner.nextLine().split(","); // Con esto nos permitimos que pueda ingresar una o dos edades.
        System.out.print("Ingrese un sexo, escribiendo solo la inicial ((M)asculino/(F)emenino/(O)tro): ");
        String filtSexo = scanner.nextLine();
        System.out.print("ingrese la profesion o profesiones: ");
        String filtProfesion[] = scanner.nextLine().split(","); // Con esto nos permitimos el que pueda ingresar las profesiones que quiera.

        // JNúñez: Demasiado nivel de anidamiento, se puede plantear de una formna más limpia
        for (Persona persona : personas) {

            //JNúñez: aquí incumples un requisito, si no inserta edad no debería afectar el filtro
            if (filtEdad.length > 0) { // Como se actua distinto dependiendo si solo hay una edad o es un rango hacemos un if.

                //Realizamos las consultas para un rango de edad.
                if (persona.getEdad() >= Integer.parseInt(filtEdad[0])
                        && persona.getEdad() <= Integer.parseInt(filtEdad[1])
                        && persona.getSexo().equals(filtSexo)) {

                    for (String s : filtProfesion) {
                        //Nos sirve para cualquier cantidad de profesiones puestas (1- infinitas)
                        if (persona.getProfesion().equals(s)) {
                            //Aquí ya añado directo porque si están en este filtro es que han pasado el resto y, por tanto, cumplen.
                            personasFiltradas.add(persona);
                        }
                    }
                    //Mismo caso que antes pero para una edad ( no un rango )
                } else if (persona.getEdad() == Integer.parseInt(filtEdad[0])
                        && persona.getSexo().equals(filtSexo)) {

                    for (String s : filtProfesion) {
                        if (persona.getProfesion().equals(s)) {
                            personasFiltradas.add(persona);
                        }
                    }
                } else {
                    System.err.println("No hay nada con esas consultas.");
                }
            }
        }

        System.out.print("Que nombre quieres poner al nuevo fichero? (Añada la extension .csv)");
        String nombreFichero = scanner.nextLine();
        if (PATH_FICHERO.equals(nombreFichero)) { // Si el path del fichero de donde hemos sacado los datos es igual, nos avisa.
            //Nos da la opción de elegir si sobreescribirlo o no.
            System.out.println("Quieres sobrescribirlo? (Y/N)");

            if (scanner.nextLine().toLowerCase().equals("y")) { // pasamos a minuscula para que no haya problemas.
                sobrescribirCsv();
            } else if (scanner.nextLine().toLowerCase().equals("n")) {
                escribirAdjunto();
            } else {
                System.err.println("Error al seleccionar escritura.");
            }
        } else {
            escribirNuevo(nombreFichero);
        }
    }

    private static void escribirNuevo(String nombreFichero) {


        File file = new File(nombreFichero); // Aquí creo el fichero aparte con el nombre puesto.

        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("nombre,edad,sexo,profesion");

            for (Persona persona : personas) {
                writer.println(persona.toString());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Fichero creado.");
    }

    private static void escribirAdjunto() {
        try (FileWriter fw = new FileWriter(PATH_FICHERO, true); // El true nos permite añadir y no sobrescribir.
             PrintWriter writer = new PrintWriter(fw)) {
            writer.println("nombre,edad,sexo,profesion");
            for (Persona persona : personas) {
                writer.println(persona.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Se ha añadido la informacion al fichero.");
    }


    //JNúñez: no hay consistencia con los tabulados de los diferentes métodos, entre otros
        private static void sobrescribirCsv() {

            try (PrintWriter writer = new PrintWriter(PATH_FICHERO)) {
                writer.println("nombre,edad,sexo,profesion");

                for (Persona persona : personas) {
                    writer.println(persona.toString());
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
    }

    private static void leerCsv() {



        try ( BufferedReader br = new BufferedReader(new FileReader(PATH_FICHERO)) ) {

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(","); //Separamos por comas para obtener un array donde cada elemento será el dato.

                personas.add(new Persona(
                        datos[0],
                        Integer.parseInt(datos[1]),
                        datos[2],
                        datos[3]
                ));
            }

            personas.remove(0);//Quitamos la cabecera.
        } catch (IOException e) {
            System.err.println("Error al leer fichero");
        }
    }
}