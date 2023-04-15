package br.univille.cofrinho.domains.priority;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "priority", schema = "main")
public class PrioridadeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	protected UUID id;

	@Column(name = "name")
	protected String nome;

	@Column(name = "level")
	protected int ordem;

	@Column(name = "created_at")
	@CreationTimestamp
	private Date criadoEm;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private Date atualizadoEm;

}
