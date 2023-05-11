package com.rcm.sistemas.financas.api.service.implementation;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.rcm.sistemas.financas.api.exceptions.ErroAutenticacao;
import com.rcm.sistemas.financas.api.exceptions.RegraNegocioException;
import com.rcm.sistemas.financas.api.service.interfaces.UsuarioService;
import com.rcm.sistemas.financas.model.entity.Usuario;
import com.rcm.sistemas.financas.model.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository repository;

	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {

		Optional<Usuario> usuario = repository.findByEmail(email);

		if (!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuário não encontrado para o email informado.");
		}

		//boolean senhasBatem = encoder.matches(senha, usuario.get().getSenha());

		if (!usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha inválida.");
		}

		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		// criptografarSenha(usuario);
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);

		if (existe) {
			throw new RegraNegocioException("Já existe um usuário cadastrado com esse email.");
		}
	}

	@Override
	public List<Usuario> listaUsuario() {
		return repository.findAll();
	}

}
