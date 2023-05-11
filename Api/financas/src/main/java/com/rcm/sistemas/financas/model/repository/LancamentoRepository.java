package com.rcm.sistemas.financas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rcm.sistemas.financas.model.entity.Lancamento;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
