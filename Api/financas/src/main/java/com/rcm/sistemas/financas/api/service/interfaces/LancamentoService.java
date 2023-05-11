package com.rcm.sistemas.financas.api.service.interfaces;

import java.util.List;

import com.rcm.sistemas.financas.model.entity.Lancamento;
import com.rcm.sistemas.financas.model.enums.StatusLancamento;

public interface LancamentoService {

	Lancamento salvar(Lancamento lancamento);

	Lancamento atualizar(Lancamento lancamento);
	
	void deletar(Lancamento lancamento);
	
	List<Lancamento> buscar( Lancamento lancamentoFiltro);
	
	void atualizarStatus(Lancamento lancamento, StatusLancamento status);
	
	void validar(Lancamento lancamento);
	
	

}
