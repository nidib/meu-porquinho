package br.univille.meuporquinho.domains.usuario;

import br.univille.meuporquinho.domains.categoria.CategoriaEntity;
import br.univille.meuporquinho.domains.contabancaria.ContaBancariaEntity;
import br.univille.meuporquinho.domains.perfil.PerfilEntity;
import br.univille.meuporquinho.utils.SanitizacaoDeTextoUtils;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "perfil_id")
	private PerfilEntity perfil;

	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<CategoriaEntity> categorias;

	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<ContaBancariaEntity> contasBancarias;

	@CreationTimestamp
	@Column(name = "criado_em")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime criadoEm;

	@UpdateTimestamp
	@Column(name = "atualizado_em")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime atualizadoEm;

	public UsuarioEntity() {
	}

	public UsuarioEntity(String login, String senha, String email, PerfilEntity perfil) {
		this.login = SanitizacaoDeTextoUtils.lowerCaseERemoveEspacos(login);
		this.senha = senha;
		this.email = SanitizacaoDeTextoUtils.lowerCaseERemoveEspacos(email);
		this.perfil = perfil;
	}

	public UUID getId() {
		return this.id;
	}

	public String getLogin() {
		return this.login;
	}

	public String getEmail() {
		return this.email;
	}

	public String getSenha() {
		return this.senha;
	}

	public PerfilEntity getPerfil() {
		return this.perfil;
	}

}
