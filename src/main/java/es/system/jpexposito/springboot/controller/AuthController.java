package es.system.jpexposito.springboot.controller;

import es.system.jpexposito.springboot.security.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import es.system.jpexposito.springboot.security.CustomUserDetailsService;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    private CustomUserDetailsService userDetailsService;    
    private JwtUtils jwtUtils;
    private AuthenticationManager authenticationManager;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtUtils(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }


    @Autowired
    public void setCustomUserDetailsService(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password));

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        Collection<? extends GrantedAuthority> autorities = userDetails.getAuthorities();
        return ResponseEntity.ok(jwtUtils.generateToken(userDetails.getUsername(),autorities));
        } catch(BadCredentialsException eb) {
            log.error("Se ha producido un error por credenciales invalidas:{}",eb.getMessage());
        } catch (Exception e) {
            log.error("Se ha producido un error no controlado:{}",e.getMessage());
        }
        return ResponseEntity.status(401).body("Invalid username or password");
    }
}
