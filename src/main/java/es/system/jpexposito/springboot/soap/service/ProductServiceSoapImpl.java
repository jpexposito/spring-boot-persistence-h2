package es.system.jpexposito.springboot.soap.service;

import java.util.ArrayList;
import java.util.List;

import es.system.jpexposito.springboot.model.Product;
import jakarta.jws.WebService;

@WebService(endpointInterface = "es.system.jpexposito.springboot.soap.service.ProductServiceSoapInterface")
public class ProductServiceSoapImpl implements ProductServiceSoapInterface{


    Product product = new Product("Mi producto", 10l); 

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(product);
        return products;
    }
    
}
