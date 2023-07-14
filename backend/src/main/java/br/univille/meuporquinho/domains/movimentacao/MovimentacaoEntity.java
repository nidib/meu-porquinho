package br.univille.meuporquinho.domains.movimentacao;

import br.univille.meuporquinho.domains.categoria.CategoriaEntity;
import br.univille.meuporquinho.domains.contabancaria.ContaBancariaEntity;
import br.univille.meuporquinho.domains.frequencia.FrequenciaEntity;
import br.univille.meuporquinho.domains.prioridade.PrioridadeEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "movimentacao", schema = "main")
public class MovimentacaoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	UUID id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "valor")
	long valor;

	@Column(name = "data")
	LocalDate data;

	@Column(name = "data_de_conclusao")
	LocalDate dataDeConclusao;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "conta_bancaria_id")
	ContaBancariaEntity contaBancaria;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria_id")
	CategoriaEntity categoria;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "prioridade_id")
	PrioridadeEntity prioridade;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "frequencia_id")
	FrequenciaEntity  frequencia;

	@CreationTimestamp
	@Column(name = "criado_em")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime criadoEm;

	@UpdateTimestamp
	@Column(name = "atualizado_em")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime atualizadoEm;

	public MovimentacaoEntity() {}

	public MovimentacaoEntity(String nome, long valor, LocalDate data, LocalDate dataDeConclusao, ContaBancariaEntity contaBancaria, CategoriaEntity categoria) {
		this.nome = nome;
		this.valor = valor;
		this.data = data;
		this.dataDeConclusao = dataDeConclusao;
		this.contaBancaria = contaBancaria;
		this.categoria = categoria;
	}

	public UUID getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public long getValor() {
		return this.valor;
	}

	public LocalDate getData() { return this.data; }

	public LocalDate getDataDeConclusao() { return this.dataDeConclusao; }

	public ContaBancariaEntity getContaBancaria() {
		return this.contaBancaria;
	}

	public CategoriaEntity getCategoria() {
		return this.categoria;
	}

	public PrioridadeEntity getPrioridade() {
		return this.prioridade;
	}

	public FrequenciaEntity getFrequencia() {
		return this.frequencia;
	}

}


