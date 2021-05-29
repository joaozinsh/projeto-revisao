package com.revisao.revisao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_usuario") // Opcional
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull // Opcional
	@Column(name = "nomeUsuario") //Opcional
	@Size(min = 5, max = 100) // Opcional
	private String nome;
	
	@NotNull
	@Column(name = "emailUsuario")
	@Size(min = 11, max = 50)
	private String email;
	
	
	private @NotNull @Column(name = "senhaUsuario") @Size(min = 8, max = 16, message = "Entre 8 e 16 caracteres") String senha;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
