package br.univille.cofrinho.domains.usuario;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "usuario", schema = "main")
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private UUID id;

	@Column(name = "login")
	private String login;

	@Column(name = "senha")
	private String senha;

	@Column(name = "email")
	private String email;

	@CreationTimestamp
	@Column(name = "criado_em")
	private Date criadoEm;

	@UpdateTimestamp
	@Column(name = "atualizado_em")
	private Date atualizadoEm;

	public UUID getId() {
		return this.id;
	}

	public String getLogin() {
		return this.login;
	}

	public String getSenha() {
		return this.senha;
	}

	public String getEmail() {
		return this.email;
	}

}

