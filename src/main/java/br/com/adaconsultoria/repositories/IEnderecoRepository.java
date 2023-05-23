package br.com.adaconsultoria.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.adaconsultoria.entities.Endereco;

public interface IEnderecoRepository extends CrudRepository<Endereco, Integer> {

	@Query("select e from Endereco e where e.cep = :param1")
	Endereco findByCEP (@Param("param1")String cpf) throws Exception;
	
	@Query("select e from Endereco e where e.pessoa.id_pessoa = :param1")
	Endereco findByIdPessoa (@Param("param1")Integer id_pessoa) throws Exception;
		
	
}
