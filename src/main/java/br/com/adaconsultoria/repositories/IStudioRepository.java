package br.com.adaconsultoria.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.adaconsultoria.entities.Studio;

public interface IStudioRepository extends CrudRepository<Studio, Integer> {

	@Query("select s from Studio s where s.cnpj = :param1")
	Studio findByCNPJ (@Param("param1")String cnpj) throws Exception;
	
	@Query("select s from Studio s where s.cnpj = :param1")
	Studio findByRazaoSocial (@Param("param1") String razaoSocial) throws Exception;
	
}
