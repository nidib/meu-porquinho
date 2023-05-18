package br.univille.meuporquinho.controllers.categoria;

import br.univille.meuporquinho.controllers.categoria.dtos.CategoriaResDTO;
import br.univille.meuporquinho.controllers.categoria.dtos.CriarCategoriaReqDTO;
import br.univille.meuporquinho.controllers.categoria.dtos.EditarCategoriaReqDTO;
import br.univille.meuporquinho.domains.autenticacao.annotations.PrecisaEstarLogado;
import br.univille.meuporquinho.domains.autenticacao.annotations.UsuarioLogadoId;
import br.univille.meuporquinho.domains.categoria.CategoriaEntity;
import br.univille.meuporquinho.domains.categoria.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@PrecisaEstarLogado
@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

	private final CategoriaService categoriaService;

	@Autowired
	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	@GetMapping
	public ResponseEntity<List<CategoriaResDTO>> listarCategorias(@UsuarioLogadoId UUID usuarioLogadoId) {
		List<CategoriaResDTO> categorias = this.categoriaService.obterDisponiveisPor(usuarioLogadoId)
			.stream()
			.map(categoria -> new CategoriaResDTO(categoria.getId(), categoria.getNome(), categoria.getTipo()))
			.toList();

		return new ResponseEntity<>(categorias, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CategoriaResDTO> criarCategoria(@Valid @RequestBody CriarCategoriaReqDTO categoria, @UsuarioLogadoId UUID usuarioLogadoId) {
		CategoriaEntity categoriaCriada = this.categoriaService.criar(categoria.nome(), categoria.tipo(), usuarioLogadoId);

		return new ResponseEntity<>(
			new CategoriaResDTO(categoriaCriada.getId(), categoriaCriada.getNome(), categoriaCriada.getTipo()),
			HttpStatus.CREATED
		);
	}

	@PutMapping
	public ResponseEntity<CategoriaResDTO> editarCategoria(@Valid @RequestBody EditarCategoriaReqDTO categoria, @UsuarioLogadoId UUID usuarioLogadoId) {
		CategoriaEntity categoriaAtualizada = this.categoriaService.atualizar(
			categoria.id(), categoria.nome(), categoria.tipo(), usuarioLogadoId
		);

		return new ResponseEntity<>(
			new CategoriaResDTO(categoriaAtualizada.getId(), categoriaAtualizada.getNome(), categoriaAtualizada.getTipo()),
			HttpStatus.OK
		);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarCategoria(@PathVariable("id") UUID id, @UsuarioLogadoId UUID usuarioLogadoId) {
		this.categoriaService.remover(id, usuarioLogadoId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
