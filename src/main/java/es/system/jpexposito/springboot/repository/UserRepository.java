package es.system.jpexposito.springboot.repository;

import es.system.jpexposito.springboot.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByName(String name); 

}
