package org.iesvdm.api_rest.service;

import org.iesvdm.api_rest.domain.Usuario;
import org.iesvdm.api_rest.exception.EntityNotFoundException;
import org.iesvdm.api_rest.exception.NotCouplingIdException;
import org.iesvdm.api_rest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> all(){return this.usuarioRepository.findAll();}

    public Page<Usuario> findByName(String cadena){

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "nombre"));
        return this.usuarioRepository.findUsuariosByNombreContainsIgnoreCase(cadena, pageable);
    }

    public Usuario save(Usuario usuario){return this.usuarioRepository.save(usuario);}

    public Usuario one(Long id){
        return this.usuarioRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(id, Usuario.class));
    }

    public Usuario replace(Long id, Usuario usuario){
        return this.usuarioRepository.findById(id).map( u -> {
            if (id.equals(usuario.getId())) return this.usuarioRepository.save(usuario);
            else throw new NotCouplingIdException(id, usuario.getId(), Usuario.class);
        }).orElseThrow(()-> new EntityNotFoundException(id, Usuario.class));
    }

    public void delete(Long id){
        this.usuarioRepository.findById(id).map( u -> {this.usuarioRepository.delete(u);
                    return u;})
                .orElseThrow(()-> new EntityNotFoundException(id, Usuario.class));
    }
}
