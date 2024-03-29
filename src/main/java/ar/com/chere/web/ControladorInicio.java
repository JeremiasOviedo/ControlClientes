package ar.com.chere.web;

import ar.com.chere.domain.Persona;
import ar.com.chere.service.PersonaService;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorInicio {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user) {
        String mensaje = "Hola mundo con thymeleaf";

        List<Persona> personas = personaService.listarPersonas();
        log.info("Ejecutando el controlador Spring MVC");
        log.info("Usuario logueado: " + user);
        model.addAttribute("personas", personas);
       Double saldoTotal= 0D;
       for(Persona p: personas){
           saldoTotal += p.getSaldo();
       }
       model.addAttribute("saldoTotal", saldoTotal);
               model.addAttribute("totalClientes",personas.size() );
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Persona persona) {
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores) {
        if (errores.hasErrors()) {
            return "modificar";
        }
        personaService.guardar(persona);
        return "redirect:/";

    }

    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model) {

        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }
    
       
    @GetMapping("/info/{idPersona}")
    public String info(Persona persona, Model model) {

        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "info";
    } 
    
    @GetMapping("/eliminar")
    public String eliminar(Persona persona, Model model) {

        personaService.eliminar(persona);

        return "redirect:/";

    }
    
    
}
