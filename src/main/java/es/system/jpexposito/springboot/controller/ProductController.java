package es.system.jpexposito.springboot.controller;

import es.system.jpexposito.springboot.model.Product;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ProductController {
    Product product = new Product("Mi producto", 10l); 
    /**
     * Endpoint para obtener todos los productos (accesible para todos los usuarios autenticados)
     * @return Una lista de productos
     */
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(product);
        return products;
    }

    /**
     * Endpoint para crear un producto 
     * @param product
     * @return
     */
    @PostMapping("/product")
    public Product createProduct(@RequestBody Product product) {
        return this.product;
    }

    /**
     * Endpoint para actualizar un producto 
     * @param id
     * @param product
     * @return 
     */
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        return product;
    }
}

