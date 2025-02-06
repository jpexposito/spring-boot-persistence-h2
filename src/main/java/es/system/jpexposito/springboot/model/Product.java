package es.system.jpexposito.springboot.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Product") 
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product implements Serializable {
    private int id;
    private String name;
    private long cost;

    public Product() {}

    public Product(String name, long cost) {
        this.name = name;
        this.cost = cost;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCost() {
        return this.cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

}
