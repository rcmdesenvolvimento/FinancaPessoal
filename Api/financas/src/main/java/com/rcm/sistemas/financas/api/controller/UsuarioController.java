package com.rcm.sistemas.financas.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rcm.sistemas.financas.api.dto.UsuarioDTO;
import com.rcm.sistemas.financas.api.exceptions.ErroAutenticacao;
import com.rcm.sistemas.financas.api.exceptions.RegraNegocioException;
import com.rcm.sistemas.financas.api.service.interfaces.UsuarioService;
import com.rcm.sistemas.financas.model.entity.Usuario;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	// private final LancamentoService lancamentoService;
	// private final JwtService jwtService;

	@GetMapping("/")
	private List<Usuario> findAll() {
		return service.listaUsuario();
	}

	@PostMapping("/autenticar")
	public ResponseEntity<?> autenticar(@RequestBody UsuarioDTO dto) {
		try {
			Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
			// String token = jwtService.gerarToken(usuarioAutenticado);
			// TokenDTO tokenDTO = new TokenDTO( usuarioAutenticado.getNome(), token);
			return ResponseEntity.ok(usuarioAutenticado);
		} catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {

		Usuario usuario = Usuario.builder().nome(dto.getNome()).email(dto.getEmail()).senha(dto.getSenha()).build();

		try {
			Usuario usuarioSalvo = service.salvarUsuario(usuario);
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	/*
	 * @GetMapping("{id}/saldo") public ResponseEntity
	 * obterSaldo( @PathVariable("id") Long id ) { Optional<Usuario> usuario =
	 * service.obterPorId(id);
	 * 
	 * if(!usuario.isPresent()) { return new ResponseEntity( HttpStatus.NOT_FOUND );
	 * }
	 * 
	 * BigDecimal saldo = lancamentoService.obterSaldoPorUsuario(id); return
	 * ResponseEntity.ok(saldo); }
	 */

}
