package com.rcm.sistemas.financas.api.service.interfaces;

import java.util.List;

import com.rcm.sistemas.financas.model.entity.Usuario;

public interface UsuarioService {

	Usuario autenticar(String email, String senha);

	Usuario salvarUsuario(Usuario usuario);

	void validarEmail(String email);
	
	List<Usuario> listaUsuario();;

}
