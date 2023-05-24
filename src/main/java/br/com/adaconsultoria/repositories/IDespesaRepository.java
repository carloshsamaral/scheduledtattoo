package br.com.adaconsultoria.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.adaconsultoria.entities.Despesa;
import br.com.adaconsultoria.entities.Endereco;

public interface IDespesaRepository extends CrudRepository<Despesa, Integer> {

	@Query("select e from Despesa e where e.studio.id_studio = :param1")
	Endereco findByStudio (@Param("param1")Integer id_studio) throws Exception;
	
		
	
}
