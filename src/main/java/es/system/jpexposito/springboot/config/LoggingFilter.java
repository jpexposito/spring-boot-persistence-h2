package es.system.jpexposito.springboot.config;

import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class LoggingFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Registrar la URL de la petición
        log.info("Request URL: {}", request.getRequestURI());
        
        // Registrar las cabeceras de la petición
        logger.info("Request Headers:");
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
            log.info("{}: {}", headerName, request.getHeader(headerName));
        });

        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }
}
