import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class PasswordEncryptionTest {

    @Test
    void testPasswordEncryption() {
    // Crear un BCryptPasswordEncoder
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Contraseña original
    String rawPassword = "password123";

    // Cifrar la contraseña
    String encodedPassword = passwordEncoder.encode(rawPassword);

    // Imprimir la contraseña cifrada
    System.out.println("Contraseña cifrada: '" + encodedPassword+"'");

    // Verificar que la contraseña cifrada no sea la misma que la original
    assertNotEquals(rawPassword, encodedPassword);

    // Verificar que la contraseña original coincida con la contraseña cifrada
    assertTrue(passwordEncoder.matches(rawPassword, encodedPassword));
    }
    
}
