package com.rcm.sistemas.financas.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rcm.sistemas.financas.model.entity.Usuario;

// Teste de integração.

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;

	@Test
	public void deveVerificarAExistenciaDeUmEmail() {

		// Cenário
		Usuario usuario = Usuario.builder().nome("Ricardo").email("ricardo@gmail.com").build();
		repository.save(usuario);

		// Ação/Execução
		boolean emailExiste = repository.existsByEmail("ricardo"
				+ "@gmail.com");

		// Verificação
		Assertions.assertThat(emailExiste).isTrue();

	}
	
	
	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
		
		//cenário
		repository.deleteAll();
		
		//acao
		boolean result = repository.existsByEmail("usuario@email.com");
		
		//verificacao
		Assertions.assertThat(result).isFalse();
	}

}
