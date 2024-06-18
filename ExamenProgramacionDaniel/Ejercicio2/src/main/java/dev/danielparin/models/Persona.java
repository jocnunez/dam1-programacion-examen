package dev.danielparin.models;

public class Persona {
    private String nombre;
    private int edad;
    private  String sexo;
    private String profesion;

    public Persona(String nombre, int edad, String sexo, String profesion) {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.profesion = profesion;
    }


    public String getSexo() {
        return sexo;
    }


    public int getEdad() {
        return edad;
    }


    public String getProfesion() {
        return profesion;
    }

    @Override
    public String toString() {
        return nombre+","+edad+","+sexo+","+profesion;
    }
}
