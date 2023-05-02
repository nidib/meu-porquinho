package br.univille.cofrinho.controllers.categoria;

import br.univille.cofrinho.controllers.categoria.dtos.CategoriaResDTO;
import br.univille.cofrinho.controllers.categoria.dtos.CriarCategoriaReqDTO;
import br.univille.cofrinho.controllers.categoria.dtos.EditarCategoriaReqDTO;
import br.univille.cofrinho.domains.autenticacao.annotations.PrecisaEstarLogado;
import br.univille.cofrinho.domains.autenticacao.annotations.UsuarioLogadoId;
import br.univille.cofrinho.domains.categoria.CategoriaEntity;
import br.univille.cofrinho.domains.categoria.CategoriaService;
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
		List<CategoriaResDTO> categorias = this.categoriaService.obterCategorias(usuarioLogadoId)
			.stream()
			.map(categoria -> new CategoriaResDTO(categoria.getId(), categoria.getTitulo(), categoria.getTipo()))
			.toList();

		return new ResponseEntity<>(categorias, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CategoriaResDTO> criarCategoria(@Valid @RequestBody CriarCategoriaReqDTO categoria, @UsuarioLogadoId UUID usuarioLogadoId) {
		CategoriaEntity categoriaCriada = this.categoriaService.criarCategoria(categoria.titulo(), categoria.tipo(), usuarioLogadoId);

		return new ResponseEntity<>(
			new CategoriaResDTO(categoriaCriada.getId(), categoriaCriada.getTitulo(), categoriaCriada.getTipo()),
			HttpStatus.CREATED
		);
	}

	@PutMapping
	public ResponseEntity<CategoriaResDTO> editarCategoria(@Valid @RequestBody EditarCategoriaReqDTO categoria, @UsuarioLogadoId UUID usuarioLogadoId) {
		CategoriaEntity categoriaAtualizada = this.categoriaService.atualizarCategoria(
			categoria.id(), categoria.titulo(), categoria.tipo(), usuarioLogadoId
		);

		return new ResponseEntity<>(
			new CategoriaResDTO(categoriaAtualizada.getId(), categoriaAtualizada.getTitulo(), categoriaAtualizada.getTipo()),
			HttpStatus.OK
		);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarCategoria(@PathVariable("id") UUID id, @UsuarioLogadoId UUID usuarioLogadoId) {
		this.categoriaService.remover(id, usuarioLogadoId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
