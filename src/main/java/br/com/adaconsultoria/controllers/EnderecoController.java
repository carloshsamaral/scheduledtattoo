package br.com.adaconsultoria.controllers;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.adaconsultoria.entities.Endereco;
import br.com.adaconsultoria.entities.Pessoa;
import br.com.adaconsultoria.repositories.IEnderecoRepository;
import br.com.adaconsultoria.repositories.IPessoaRepository;
import br.com.adaconsultoria.requests.EnderecoDeleteRequest;
import br.com.adaconsultoria.requests.EnderecoPostRequest;
import br.com.adaconsultoria.requests.EnderecoPutRequest;
import io.swagger.annotations.ApiOperation;

@Transactional
@Controller
public class EnderecoController {

	private static final String ENDPOINT = "/api/endereco";
	
	@Autowired
	private IEnderecoRepository enderecoRepository;
	
	@Autowired
	private IPessoaRepository pessoaRepository;
	
	@ApiOperation("Endpoint para inserir o endereço")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	public ResponseEntity<String> post(@RequestBody EnderecoPostRequest request){

		try {
			
			Optional<Pessoa> pessoa = pessoaRepository.findById(request.getId_pessoa());
			
			Pessoa p = pessoa.get();
			
			Endereco e = new Endereco();
			
			
			e.setBairro(request.getBairro());
			e.setCep(request.getCep());
			e.setComplemento(request.getComplemento());
			e.setFlag_ativo("S");
			e.setFlag_removido("N");
			e.setLocalidade(request.getLocalidade());
			e.setLogradouro(request.getLogradouro());
			e.setUf(request.getUf());
			e.setPessoa(p);
			
			enderecoRepository.save(e);
			
			return ResponseEntity.status(HttpStatus.CREATED).body("Endereço criado com sucesso");
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Ocorreu um problema em inserir o endereço");
		}
		
	}
	
	@ApiOperation("Endpoint para retonar endereco por ID")
	@RequestMapping(value = ENDPOINT + "/{id_endereco}", method = RequestMethod.GET)
	public ResponseEntity<Endereco> getById(@PathVariable("id_endereco") Integer id_endereco) {
		try {
			Optional<Endereco> consulta = enderecoRepository.findById(id_endereco);

			if (consulta.isPresent()) {
				Endereco e = consulta.get();
				return ResponseEntity.status(HttpStatus.OK).body(e);
			}

			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}
	
	@ApiOperation("Endpoint para retonar endereco por ID de pessoa")
	@RequestMapping(value = ENDPOINT + "/pessoa/{id_pessoa}", method = RequestMethod.GET)
	public ResponseEntity<Endereco> getByIdPessoa(@PathVariable("id_pessoa") Integer id_pessoa) {
		try {
			Optional<Endereco> consulta = Optional.ofNullable(enderecoRepository.findByIdPessoa(id_pessoa));

			if (consulta.isPresent()) {
				Endereco e = consulta.get();
				return ResponseEntity.status(HttpStatus.OK).body(e);
			}

			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}
	
	
	@ApiOperation("Endpoint para atualizar endereço")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.PUT)
	public ResponseEntity<String> putEndereco(@RequestBody EnderecoPutRequest request){
		try {
			if (enderecoRepository.findById(request.getId_endereco()) == null) {
				return ResponseEntity.badRequest().body("O endereço " + request.getId_endereco() + " não está cadastrado");
			}
			Endereco e = new Endereco();
			
			e.setBairro(request.getBairro());
			e.setCep(request.getCep());
			e.setComplemento(request.getComplemento());
			e.setFlag_ativo("S");
			e.setFlag_removido("N");
			e.setLocalidade(request.getLocalidade());
			e.setLogradouro(request.getLogradouro());
			e.setUf(request.getUf());
			e.setId_endereco(request.getId_endereco());
			e.setPessoa(enderecoRepository.findById(request.getId_endereco()).get().getPessoa());
			enderecoRepository.save(e);
			
			return ResponseEntity.status(HttpStatus.CREATED).body("Endereço atualizada com sucesso");
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Problema em atualizar endereço");
		}
		
	}
	
	@ApiOperation("Endpoint para deletar endereço")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEndereco(@RequestBody EnderecoDeleteRequest request){
		try {
			
			if (enderecoRepository.findById(request.getId_endereco()) == null) {
				return ResponseEntity.badRequest().body("O endereço " + request.getId_endereco() + " não está cadastrado");
			}
			Optional<Endereco> consulta = Optional.ofNullable(enderecoRepository.findByIdPessoa(request.getId_endereco()));
			Endereco e = consulta.get();
			
			e.setId_endereco(request.getId_endereco());
			e.setFlag_ativo("N");
			e.setFlag_removido("S");
			
			enderecoRepository.save(e);
			
			return ResponseEntity.status(HttpStatus.CREATED).body("Endereço atualizada com sucesso");
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Problema em atualizar endereço");
		}
		
	}
	
}
