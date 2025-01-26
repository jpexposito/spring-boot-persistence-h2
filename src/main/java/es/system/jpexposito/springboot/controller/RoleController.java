package es.system.jpexposito.springboot.controller;

import es.system.jpexposito.springboot.exception.ResourceNotFoundException;
import es.system.jpexposito.springboot.model.Role;
import es.system.jpexposito.springboot.service.RoleServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class RoleController {

    private RoleServiceInterface roleService;

    @Autowired
    public void setRoleService(RoleServiceInterface roleService) {
        this.roleService = roleService;
    }

    @Operation(summary = "Get all roles")
    @GetMapping("/roles/")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @Operation(summary = "Get role by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    @GetMapping("/role/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable(value = "id") int roleId) throws ResourceNotFoundException {
        Role role = roleService.getRoleById(roleId);
        return ResponseEntity.ok().body(role);
    }

    @Operation(summary = "Get role by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    @GetMapping("/role/name/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable(value = "name") String roleName) throws ResourceNotFoundException {
        Role role = roleService.getRoleByName(roleName);
        return ResponseEntity.ok().body(role);
    }

    @Operation(summary = "Insert role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add/role/")
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @Operation(summary = "Update role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role updated successfully"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    @PutMapping("/update/role/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable(value = "id") int roleId,
                                           @RequestBody Role roleDetails) throws ResourceNotFoundException {
        final Role updatedRole = roleService.updateRole(roleId, roleDetails);
        return ResponseEntity.ok(updatedRole);
    }

    @Operation(summary = "Delete role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    @DeleteMapping("/delete/role/{id}")
    public Map<String, Boolean> deleteRole(@PathVariable(value = "id") int roleId) throws ResourceNotFoundException {
        roleService.deleteRole(roleId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
