package es.system.jpexposito.springboot.service;

import es.system.jpexposito.springboot.exception.ResourceNotFoundException;
import es.system.jpexposito.springboot.model.Role;
import es.system.jpexposito.springboot.model.User;
import es.system.jpexposito.springboot.repository.RoleRepository;
import es.system.jpexposito.springboot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class RoleService implements RoleServiceInterface {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    /**
     * Funcion que obtiene un Rol por id
     */
    @Transactional
    @Override
    public Role getRoleById(int roleId) throws ResourceNotFoundException {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + roleId));
    }

    /**
     * Funcion que permite obtener un Rol por nombre
     */
    @Transactional
    @Override
    public Role getRoleByName(String roleName) throws ResourceNotFoundException {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with name " + roleName));
    }

    /**
     * Funcion que crea un nuevo rol
     */
    @Transactional
    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    /**
     * Funcion que permite actualizar un rol 
     */
    @Transactional
    @Override
    public Role updateRole(int roleId, Role roleDetails) throws ResourceNotFoundException {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + roleId));

        role.setName(roleDetails.getName());
        return roleRepository.save(role);
    }

    /**
     * Funcion que permite eliminar un rol
     */
    @Transactional
    @Override
    public void deleteRole(int roleId) throws ResourceNotFoundException {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + roleId));

        Set<User> usersWithRole = role.getUsers();
        for (User user : usersWithRole) {
            user.setRole(null);
            userRepository.save(user);  
        }

        roleRepository.delete(role);
    }
}
