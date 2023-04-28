package br.univille.cofrinho.domains.usuario;

import br.univille.cofrinho.domains.perfil.PerfilEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
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

	@OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY)
	private PerfilEntity perfil;

	@CreationTimestamp
	@Column(name = "criado_em")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime criadoEm;

	@UpdateTimestamp
	@Column(name = "atualizado_em")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime atualizadoEm;

	public UsuarioEntity(String login, String senha, String email) {
		this.login = login;
		this.senha = senha;
		this.email = email;
	}

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
