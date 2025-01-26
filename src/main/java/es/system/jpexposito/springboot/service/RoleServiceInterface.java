package es.system.jpexposito.springboot.service;

import es.system.jpexposito.springboot.exception.ResourceNotFoundException;
import es.system.jpexposito.springboot.model.Role;

import java.util.List;

public interface RoleServiceInterface {

    List<Role> getAllRoles();

    Role getRoleById(int roleId) throws ResourceNotFoundException;

    Role getRoleByName(String roleName) throws ResourceNotFoundException;

    Role createRole(Role role);

    Role updateRole(int roleId, Role roleDetails) throws ResourceNotFoundException;

    void deleteRole(int roleId) throws ResourceNotFoundException;
}
