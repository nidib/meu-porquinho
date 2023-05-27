package br.univille.meuporquinho.domains.contabancaria;

import br.univille.meuporquinho.domains.usuario.UsuarioEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "conta_bancaria", schema = "main")
public class ContaBancariaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private UUID id;

	@Column(name = "titulo")
	private String titulo;

	@Column(name = "saldo")
	private Long saldo;

	@Column(name = "dia_do_vencimento_da_fatura")
	private int diaDoVencimentoDaFatura;

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

	public ContaBancariaEntity() {}

	public ContaBancariaEntity(String titulo, Long saldo, int diaDoVencimentoDaFatura, UsuarioEntity usuario) {
		this.titulo = titulo;
		this.saldo = saldo;
		this.diaDoVencimentoDaFatura = diaDoVencimentoDaFatura;
		this.usuario = usuario;
	}

	public UUID getId() {
		return this.id;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public Long getSaldo() {
		return this.saldo;
	}

	public int getDiaDoVencimentoDaFatura() {
		return this.diaDoVencimentoDaFatura;
	}

}
