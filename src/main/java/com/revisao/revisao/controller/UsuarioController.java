package com.revisao.revisao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revisao.revisao.model.Usuario;
import com.revisao.revisao.repository.UsuarioRepository;
import com.revisao.revisao.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repositoryUsuario;
	@Autowired
	private UsuarioService serviceUsuario;
	
	@PostMapping
	public ResponseEntity<Object> postUsuario(@RequestBody Usuario usuario) {
		return serviceUsuario.cadastrarUsuario(usuario)
				.map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(400).body("Email ja cadastrado, tente outro"));
	}
	
	@PutMapping
	public ResponseEntity<Object> putUsuario(@RequestBody Usuario usuario) {
		return serviceUsuario.alterarUsuario(usuario)
				.map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(400).body("Email ja cadastrado, tente outro"));
	}
	
	@DeleteMapping
	public void deleteUsuario(@RequestParam long id) {
		repositoryUsuario.deleteById(id);
	}
	
	@GetMapping
	public ResponseEntity<Object> getAllUsuario() {
		List<Usuario> listaUsuarios = repositoryUsuario.findAll();
		if (listaUsuarios.isEmpty()) {
			return ResponseEntity.status(404).body("Não existe nenhum usuario");
		} else {
			return ResponseEntity.status(200).body(listaUsuarios);
		}
	}
	
	@GetMapping(path = "/pesquisar",params = "id")
	public ResponseEntity<Usuario> getById(@RequestParam long id){
		return repositoryUsuario.findById(id)
				.map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@GetMapping(path = "/pesquisar", params = "nome")
	public ResponseEntity<Object> getByNome(@RequestParam String nome){
		List<Usuario> listaPorNome = repositoryUsuario.findAllByNomeContainingIgnoreCase(nome);
		if (listaPorNome.isEmpty()) {
			return ResponseEntity.status(404).body("Não existe nenhum usuario com esse nome");
		} else {
			return ResponseEntity.status(200).body(listaPorNome);
		}
	}
	
	@GetMapping(path = "/pesquisar", params = "email")
	public ResponseEntity<Object> getByEmail(@RequestParam String email){
		return repositoryUsuario.findByEmailIgnoreCase(email)
				.map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(404).body("Não existe nenhum usuario com esse email"));
	}
}
