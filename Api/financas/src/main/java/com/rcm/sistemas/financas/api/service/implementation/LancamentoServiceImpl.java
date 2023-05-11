package com.rcm.sistemas.financas.api.service.implementation;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rcm.sistemas.financas.api.exceptions.RegraNegocioException;
import com.rcm.sistemas.financas.api.service.interfaces.LancamentoService;
import com.rcm.sistemas.financas.model.entity.Lancamento;
import com.rcm.sistemas.financas.model.enums.StatusLancamento;
import com.rcm.sistemas.financas.model.repository.LancamentoRepository;

@Service
public class LancamentoServiceImpl implements LancamentoService {

	@Autowired
	LancamentoRepository lancamentoRepository;

	@Override
	@Transactional
	public Lancamento salvar(Lancamento lancamento) {
		
		validar(lancamento);
		
		lancamento.setStatusLancamento(StatusLancamento.PENDENTE);
		
		return lancamentoRepository.save(lancamento);
	}

	@Override
	@Transactional
	public Lancamento atualizar(Lancamento lancamento) {
		
		Objects.requireNonNull(lancamento.getId());
		
		validar(lancamento);
		
		return lancamentoRepository.save(lancamento);
	}

	@Override
	@Transactional
	public void deletar(Lancamento lancamento) {
		
		Objects.requireNonNull(lancamento.getId());
		
		lancamentoRepository.deleteById(lancamento.getId());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Lancamento> buscar(Lancamento lancamentoFiltro) {

		Example example = Example.of(lancamentoFiltro,
				ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		return lancamentoRepository.findAll(example);
	}

	@Override
	public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
		lancamento.setStatusLancamento(status);
		atualizar(lancamento);
	}

	@Override
	public void validar(Lancamento lancamento) {
		
		if(lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals("")) {
			throw new RegraNegocioException("Informe uma descrição válida");
		}
		
		if(lancamento.getMes() == null || lancamento.getMes() < 1 || lancamento.getMes() > 12 ) {
			throw new RegraNegocioException("Informe um Mês válido");
		}
		
		if(lancamento.getAno() == null || lancamento.getMes().toString().length() != 4 ) {
			throw new RegraNegocioException("Informe um Ano válido");
		}
		
		if(lancamento.getUsuario() == null || lancamento.getUsuario().getId() == null ) {
			throw new RegraNegocioException("Informe um Usuário");
		}
		
		if(lancamento.getTipoLancamento() == null ) {
			throw new RegraNegocioException("Informe o tipo de lançamento");
		}
		
	}

}
