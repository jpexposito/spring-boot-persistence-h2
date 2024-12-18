package es.system.jpexposito.springboot.soap.service;

import java.util.List;

import es.system.jpexposito.springboot.model.User;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService(targetNamespace = "mi.paquete.que.expone")
public interface UserServiceSoapInterface {
    @WebMethod
    List<User> getAllUsers();

    @WebMethod
    User getUserById(int userId);
    
}
