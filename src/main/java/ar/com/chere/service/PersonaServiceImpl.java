package ar.com.chere.service;

import ar.com.chere.dao.PersonaDao;
import ar.com.chere.domain.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaDao personadao;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> listarPersonas() {
        return (List<Persona>) personadao.findAll();

    }

    @Override
    @Transactional
    public void guardar(Persona persona) {
        personadao.save(persona);
    }

    @Override
    @Transactional
    public void eliminar(Persona persona) {

        personadao.delete(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona encontrarPersona(Persona persona) {
        return personadao.findById(persona.getIdPersona()).orElse(null);
    }

}
