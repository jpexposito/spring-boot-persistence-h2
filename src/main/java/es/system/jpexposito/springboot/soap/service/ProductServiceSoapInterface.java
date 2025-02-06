package es.system.jpexposito.springboot.soap.service;

import jakarta.jws.WebService;

import java.util.List;

import es.system.jpexposito.springboot.model.Product;
import es.system.jpexposito.springboot.model.User;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

@WebService(targetNamespace = "springboot.soap.service")
public interface ProductServiceSoapInterface {

    @WebMethod
    @WebResult(
     name="producto")
    List<Product> getAllProducts();
}
