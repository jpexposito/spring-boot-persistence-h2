package es.system.jpexposito.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import es.system.jpexposito.springboot.exception.ResourceNotFoundException;
import es.system.jpexposito.springboot.model.Role;
import es.system.jpexposito.springboot.model.User;
import es.system.jpexposito.springboot.repository.RoleRepository;
import es.system.jpexposito.springboot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class RoleService implements RoleServiceInterface {

    private RoleRepository roleRepository;

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(int roleId) throws ResourceNotFoundException {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + roleId));
    }

    @Override
    public Role getRoleByName(String roleName) throws ResourceNotFoundException {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with name " + roleName));
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(int roleId, Role roleDetails) throws ResourceNotFoundException {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + roleId));

        role.setName(roleDetails.getName());
        return roleRepository.save(role);
    }

    @Override
public void deleteRole(int roleId) throws ResourceNotFoundException {
    Role role = roleRepository.findById(roleId)
            .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + roleId));

    Set<User> usersWithRole = role.getUsers(); 

    for (User user : usersWithRole) {
        user.getRoles().remove(role);  
        userRepository.save(user);  // Guardamos el usuario actualizado
    }

    roleRepository.delete(role);  
}

}
