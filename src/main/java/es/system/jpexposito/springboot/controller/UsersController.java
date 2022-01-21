package es.system.jpexposito.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import es.system.jpexposito.springboot.exception.ResourceNotFoundException;
import es.system.jpexposito.springboot.model.User;
import es.system.jpexposito.springboot.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("/api/v1")
public class UsersController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/user")
	public List<User> getAllusers() {
		return userRepository.findAll();
	}


	@Operation(summary="Get all users")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not find") })
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long UserId)
			throws ResourceNotFoundException {
		User user = userRepository.findById(UserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + UserId));
		return ResponseEntity.ok().body(user);
	}

	@Operation(summary="Insert user")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad Request. Problem ") })
	@PostMapping("/user")
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}

	@Operation(summary="Update user")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not find") })
	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long UserId,
											   @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
		User user = userRepository.findById(UserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + UserId));

		user.setName(userDetails.getName());
		final User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}

	@Operation(summary="Delete user")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Not find") })
	@DeleteMapping("/user/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long UserId)
			throws ResourceNotFoundException {
		User user = userRepository.findById(UserId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + UserId));

		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
