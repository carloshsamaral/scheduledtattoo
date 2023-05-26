package br.com.adaconsultoria.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.adaconsultoria.entities.Orcamento;
import br.com.adaconsultoria.entities.Pessoa;

public interface IOrcamentoRepository extends CrudRepository<Orcamento, Integer> {

	@Query("select p from Pessoa p where p.cpf = :param1")
	Pessoa findByCPF (@Param("param1")String cpf) throws Exception;
	
	@Query("select p from Pessoa p where p.email = :param1")
	Pessoa findByEmail (@Param("param1") String email) throws Exception;
	
}
