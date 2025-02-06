package es.system.jpexposito.springboot.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    private int id;
    private String name;
    private long cost;

    public Product() {}

    public Product(String name, long cost) {
        this.name = name;
        this.cost = cost;
    }
}
