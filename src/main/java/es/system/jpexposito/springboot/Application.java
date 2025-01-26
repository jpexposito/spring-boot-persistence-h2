package es.system.jpexposito.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import jakarta.annotation.PostConstruct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

@SpringBootApplication
@ImportResource("classpath:cxf-service.xml") 
public class Application {
	

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	private void initDb() {
		System.out.println(String.format("****** Creating table: %s, and Inserting test data ******", "users"));

		System.out.println(String.format("****** Creating table: %s, and Inserting test data ******", "users"));

		String sqlStatements[] = {
			// Eliminar tablas si ya existen
			"drop table if exists users", 
			"drop table if exists roles",
			
			// Crear tabla de roles
			"create table roles (id int auto_increment, name varchar(255) unique, primary key (id))",
			
			// Crear tabla de usuarios con columna role_id (clave foránea hacia roles)
			"create table users (id int auto_increment, name varchar(255), password varchar(255), role_id int, primary key (id), foreign key (role_id) references roles(id))",
		
			// Insertar datos de ejemplo en la tabla roles
			"insert into roles(name) values('Admin')",
			"insert into roles(name) values('User')",
			
			// Insertar datos de ejemplo en la tabla users, asignando role_id
			"insert into users(name, password, role_id) values('Manuel', 'password123', 1)", 
			"insert into users(name, password, role_id) values('Pedro', 'password456', 2)"
		};
		
		Arrays.asList(sqlStatements).stream().forEach(sql -> {
			System.out.println(sql);
			jdbcTemplate.execute(sql);
		});

		System.out.println(String.format("****** Fetching from table: %s ******", "users"));
		jdbcTemplate.query("select id, name from users",
				new RowMapper<Object>() {
					@Override
					public Object mapRow(ResultSet rs, int i) throws SQLException {
						System.out.println(String.format("id:%s, name:%s",
								rs.getString("id"),
								rs.getString("name")));
						return null;
					}
				});
	}
}
