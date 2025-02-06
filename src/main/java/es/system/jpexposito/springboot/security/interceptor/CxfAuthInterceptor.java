package es.system.jpexposito.springboot.security.interceptor;

import java.util.List;
import java.util.Map;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CxfAuthInterceptor extends  AbstractPhaseInterceptor<Message> {
    
    /**
     * Indica la fase en la que ejecuta el interceptor
     */

    public CxfAuthInterceptor() {
        super(Phase.PRE_INVOKE);  
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        log.info("Mensaje de entrada:"+message);
        Map<String, List<String>> protocolHeaders = (Map<String, List<String>>) message.get(Message.PROTOCOL_HEADERS);

        if (protocolHeaders != null && protocolHeaders.containsKey("Authorization")) {
            List<String> authorizationHeaders = protocolHeaders.get("Authorization");

            if (authorizationHeaders != null && !authorizationHeaders.isEmpty()) {
                String authorizationHeader = authorizationHeaders.get(0); 
                log.info("Authorization Header: " + authorizationHeader);
                // TODO: Se debe de realizar la autilizacion del tocken
            } 
        }else {
            throw new SecurityException("No se han incluido cabeceras de seguridad");
        }
    }
}
