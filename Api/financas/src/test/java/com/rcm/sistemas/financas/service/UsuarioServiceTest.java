package com.rcm.sistemas.financas.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rcm.sistemas.financas.api.service.interfaces.UsuarioService;
import com.rcm.sistemas.financas.model.entity.Usuario;
import com.rcm.sistemas.financas.model.repository.UsuarioRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	String email = "rcmdesenvolvimento@gmail.com";
	
	@Autowired
	UsuarioService service;
	
	@Autowired
	UsuarioRepository repository;
	
	@Test()
	public void deveValidarEmail() {
		
		repository.deleteAll();
		
		service.validarEmail(email);
		
	}
	
	public void deveLancarErroAoValidarEmailQuandoExistirCadastrado() {
		
		//Cenário
		Usuario usuario = Usuario.builder().nome("usuario").email(email).build();
		repository.save(usuario);
		
		//Ação
		service.validarEmail(email);
		
		
	}
	
	

}
