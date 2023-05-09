package br.univille.cofrinho.domains.categoria;

import br.univille.cofrinho.domains.usuario.UsuarioEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "categoria", schema = "main")
public class CategoriaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private UUID id;

	@Column(name = "nome")
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private TipoCategoriaEnum tipo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	private UsuarioEntity usuario;

	@CreationTimestamp
	@Column(name = "criado_em")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime criadoEm;

	@UpdateTimestamp
	@Column(name = "atualizado_em")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime atualizadoEm;

	public CategoriaEntity() {
	}

	public CategoriaEntity(UUID id, String nome, TipoCategoriaEnum tipo, UsuarioEntity usuario, LocalDateTime criadoEm) {
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.usuario = usuario;
		this.criadoEm = criadoEm;
	}

	public CategoriaEntity(String nome, TipoCategoriaEnum tipo, UsuarioEntity usuario) {
		this.nome = nome;
		this.tipo = tipo;
		this.usuario = usuario;
	}

	public UUID getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public TipoCategoriaEnum getTipo() {
		return tipo;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

}
