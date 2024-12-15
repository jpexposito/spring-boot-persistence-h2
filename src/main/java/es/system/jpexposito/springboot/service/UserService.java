package es.system.jpexposito.springboot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;

import es.system.jpexposito.springboot.exception.ResourceNotFoundException;
import es.system.jpexposito.springboot.model.User;
import es.system.jpexposito.springboot.repository.UserRepository;
import jakarta.validation.Valid;

@Component
public class UserService implements UserServiceInterface{

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(@PathVariable(value = "id") int userId) throws ResourceNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
    }

    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    public User updateUser(@PathVariable(value = "id") int userId,
                                           @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        user.setName(userDetails.getName());
        return userRepository.save(user);
    }

    public void deleteUser(@PathVariable(value = "id") int userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        userRepository.delete(user);
    }
    
}
