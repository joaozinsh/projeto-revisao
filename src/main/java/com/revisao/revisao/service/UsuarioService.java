package com.revisao.revisao.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revisao.revisao.model.Usuario;
import com.revisao.revisao.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Optional<Object> cadastrarUsuario(Usuario usuario){
		Optional<Object> emailExistente = repository.findByEmailIgnoreCase(usuario.getEmail());
		if (emailExistente.isPresent()) {
			return Optional.empty();
		} else {
			return Optional.of(repository.save(usuario));
		}
	}
	
	public Optional<Object> alterarUsuario(Usuario usuario){
		Optional<Object> emailExistente = repository.findByEmailIgnoreCase(usuario.getEmail());
		if (emailExistente.isPresent()) {
			return Optional.empty();
		} else {
			return Optional.of(repository.save(usuario));
		}
	}
}
