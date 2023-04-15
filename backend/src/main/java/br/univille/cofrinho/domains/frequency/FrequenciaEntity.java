package br.univille.cofrinho.domains.frequency;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "frequency", schema = "main")
public class FrequenciaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name="id")
	protected UUID id;

	@Column(name="name")
	protected String nome;

	@Column(name = "level")
	protected int ordem;

	@Column(name="created_at")
	@CreationTimestamp
	private Date criadoEm;

	@Column(name="updated_at")
	@UpdateTimestamp
	private Date atualizadoEm;

}
