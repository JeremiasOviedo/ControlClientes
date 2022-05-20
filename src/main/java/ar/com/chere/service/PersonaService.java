package ar.com.chere.service;

import ar.com.chere.domain.Persona;
import java.util.*;

public interface PersonaService {
    
    public List <Persona> listarPersonas();
    public void guardar (Persona persona);
    public void eliminar (Persona persona);
    public Persona encontrarPersona(Persona persona);
}
