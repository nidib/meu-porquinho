package br.univille.meuporquinho.domains.movimentacao;

import br.univille.meuporquinho.domains.contabancaria.ContaBancariaEntity;
import br.univille.meuporquinho.domains.frequencia.FrequenciaEntity;
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

}


