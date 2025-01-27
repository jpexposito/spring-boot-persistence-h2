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

    /**
     * Endpoint para obtener todos los productos (accesible para todos los usuarios autenticados)
     * @return Una lista de productos
     */
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/products")
    public List<Product> getAllProducts(@RequestHeader(value = "Authorization") String authHeader) {
        log.info("Authorization Header: {}", authHeader);
        return new ArrayList<>();
    }

    /**
     * Endpoint para crear un producto (solo accesible para usuarios con rol 'ADMIN')
     * @param product
     * @return
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return product;
    }

    /**
     * Endpoint para actualizar un producto (solo accesible para usuarios con rol 'ADMIN')
     * @param id
     * @param product
     * @return 
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        return product;
    }
}

