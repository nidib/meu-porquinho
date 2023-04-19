package src.main.java.br.univille.cofrinho.domains.user;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "usuario", schema = "main")
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name="id")
	private UUID id;
	@Column(name="login")
	private String login;
	@Column(name="senha")
	private String senha;
	@Column(name="email")
	private String email;

	public UUID getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public String getEmail() {
		return email;
	}


}

