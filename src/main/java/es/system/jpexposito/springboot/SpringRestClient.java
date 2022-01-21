package es.system.jpexposito.springboot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import es.system.jpexposito.springboot.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SpringRestClient {

	private static final String GET_users_ENDPOINT_URL = "http://localhost:8080/api/v1/user";
	private static final String GET_User_ENDPOINT_URL = "http://localhost:8080/api/v1/user/{id}";
	private static final String CREATE_User_ENDPOINT_URL = "http://localhost:8080/api/v1/user";
	private static final String UPDATE_User_ENDPOINT_URL = "http://localhost:8080/api/v1/user/{id}";
	private static final String DELETE_User_ENDPOINT_URL = "http://localhost:8080/api/v1/user/{id}";
	private static RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		SpringRestClient springRestClient = new SpringRestClient();
		
		// Step1: first create a new User
		springRestClient.createUser();
		
		// Step 2: get new created User from step1
		springRestClient.getUserById();
		
		// Step3: get all users
		springRestClient.getusers();
		
		// Step4: Update User with id = 1
		springRestClient.updateUser();
		
		// Step5: Delete User with id = 1
		springRestClient.deleteUser();
	}

	private void getusers() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(GET_users_ENDPOINT_URL, HttpMethod.GET, entity,
				String.class);

		System.out.println(result);
	}

	private void getUserById() {

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");

		RestTemplate restTemplate = new RestTemplate();
		User result = restTemplate.getForObject(GET_User_ENDPOINT_URL, User.class, params);

		System.out.println(result);
	}

	private void createUser() {

		User newUser = new User("admin");

		RestTemplate restTemplate = new RestTemplate();
		User result = restTemplate.postForObject(CREATE_User_ENDPOINT_URL, newUser, User.class);

		System.out.println(result);
	}

	private void updateUser() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		User updatedUser = new User("admin123");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(UPDATE_User_ENDPOINT_URL, updatedUser, params);
	}

	private void deleteUser() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "1");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(DELETE_User_ENDPOINT_URL, params);
	}
}
