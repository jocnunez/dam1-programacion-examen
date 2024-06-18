package dev.danielparin.models;

import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private Double price;

    //Delego al constructor la creación del ID.
    public Product( String name, Double price) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id.toString();
    }

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
