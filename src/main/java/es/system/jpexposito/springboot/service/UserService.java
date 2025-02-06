package es.system.jpexposito.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import es.system.jpexposito.springboot.exception.ResourceNotFoundException;
import es.system.jpexposito.springboot.model.User;
import es.system.jpexposito.springboot.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Obtener todos los usuarios
     */
    @Transactional
    public List<User> getAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    /**
     * Obtener un usuario por ID
     */
    @Transactional
    public User getUserById(@PathVariable(value = "id") int userId) throws ResourceNotFoundException {
        log.info("Fetching user with id: {}", userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
    }

    /**
     * Crear un nuevo usuario
     */
    @Transactional
    public User createUser(@Valid @RequestBody User user) {
        log.info("Creating new user with name: {}", user.getName());
        return userRepository.save(user);
    }

    /**
     * Actualizar un usuario existente
     */
    @Transactional
    public User updateUser(@PathVariable(value = "id") int userId,
                           @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        log.info("Updating user with id: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        user.setName(userDetails.getName());
        return userRepository.save(user);
    }

    /**
     * Funcion que permite eliminar un usuario por id
     */
    @Transactional
    public void deleteUser(@PathVariable(value = "id") int userId) throws ResourceNotFoundException {
        log.info("Deleting user with id: {}", userId);
    
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
    
        
    if (user.getRole() != null) {
        user.setRole(null);
        userRepository.save(user);  
    }
        userRepository.delete(user);
        log.info("User with id: {} deleted successfully", userId);
    }
    
}
