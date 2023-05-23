package br.com.adaconsultoria.controllers;

import java.util.ArrayList;
import java.util.List;
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
import br.com.adaconsultoria.repositories.IPessoaRepository;
import br.com.adaconsultoria.requests.EnderecoPostRequest;
import br.com.adaconsultoria.requests.PessoaPostRequest;
import br.com.adaconsultoria.requests.PessoaPutRequest;
import io.swagger.annotations.ApiOperation;

@Transactional
@Controller
public class PessoaController {

	private static final String ENDPOINT = "/api/pessoa";

	@Autowired
	private IPessoaRepository pessoaRepository;

	
	@ApiOperation("Endpoint para cadastro de pessoas")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	public ResponseEntity<String> post(@RequestBody PessoaPostRequest request) {
		try {
			if (pessoaRepository.findByCPF(request.getCpf()) != null) {
				return ResponseEntity.badRequest().body("O CPF " + request.getCpf() + " ja esta cadastrado no sistema");
			}

			Pessoa p = new Pessoa();
			List<EnderecoPostRequest> ep = request.getEnderecoList();
			Endereco e = new Endereco();
			List<Endereco> listaEnd = new ArrayList<>();

			for (EnderecoPostRequest end : ep) {
				e.setBairro(end.getBairro());
				e.setCep(end.getCep());
				e.setComplemento(end.getComplemento());
				e.setFlag_ativo("S");
				e.setFlag_removido("N");
				e.setLocalidade(end.getLocalidade());
				e.setLogradouro(end.getLogradouro());
				e.setPessoa(p);
				e.setUf(end.getUf());
				listaEnd.add(e);
			}

			/*
			 * e.setBairro(ep.getBairro()); e.setCep(ep.getCep());
			 * e.setComplemento(ep.getComplemento()); e.setFlag_ativo("S");
			 * e.setFlag_removido("N"); e.setLocalidade(ep.getLocalidade());
			 * e.setLogradouro(ep.getLogradouro()); e.setPessoa(p); e.setUf(ep.getUf());
			 */

			p.setCpf(request.getCpf());
			p.setEmail(request.getEmail());
			p.setEnderecoList(listaEnd);
			p.setFlag_ativo("S");
			p.setFlag_removido("N");
			p.setNome(request.getNome());
			p.setTelefone(request.getTelefone());
			p.setTipoPessoa(request.getTipoPessoa());

			pessoaRepository.save(p);

			return ResponseEntity.status(HttpStatus.CREATED).body("Pessoa criada com sucesso");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Problema em inserir a pessoa");
		}

	}

	@ApiOperation("Endpoint para atualizar pessoa")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.PUT)
	public ResponseEntity<String> putPessoa(@RequestBody PessoaPutRequest request) {
		try {
			if (pessoaRepository.findByCPF(request.getCpf()) == null) {
				return ResponseEntity.badRequest().body("O CPF " + request.getCpf() + " não está cadastrado");
			}
			Pessoa p = new Pessoa();

			p.setCpf(request.getCpf());
			p.setEmail(request.getEmail());
			p.setFlag_ativo("S");
			p.setFlag_removido("N");
			p.setNome(request.getNome());
			p.setTelefone(request.getTelefone());
			p.setTipoPessoa(request.getTipoPessoa());

			pessoaRepository.save(p);

			return ResponseEntity.status(HttpStatus.CREATED).body("Pessoa atualizada com sucesso");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Problema em inserir a pessoa");
		}

	}

	@ApiOperation("Endpoint para retornar todas as pessoas")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> getAll() {
		try {
			List<Pessoa> lista = (List<Pessoa>) pessoaRepository.findAll();

			return ResponseEntity.ok(lista);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@ApiOperation("Endpoint para retonar Pessoa por ID")
	@RequestMapping(value = ENDPOINT + "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pessoa> getById(@PathVariable("id") Integer idPessoa) {
		try {
			Optional<Pessoa> consulta = pessoaRepository.findById(idPessoa);

			if (consulta.isPresent()) {
				Pessoa p = consulta.get();
				return ResponseEntity.status(HttpStatus.OK).body(p);
			}

			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	/*
	 * @ApiOperation("Endpoint para retonar Pessoa por CPF")
	 * 
	 * @RequestMapping(value = ENDPOINT + "/{cpf}", method = RequestMethod.GET)
	 * public ResponseEntity<Pessoa> getByCpf(@PathVariable("cpf") String cpf) { try
	 * { Optional<Pessoa> consulta = Optional.of(pessoaRepository.findByCPF(cpf));
	 * 
	 * if (consulta.isPresent()) { Pessoa p = consulta.get(); return
	 * ResponseEntity.status(HttpStatus.OK).body(p); }
	 * 
	 * return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); } catch
	 * (Exception e) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); }
	 * 
	 * }
	 */

	@ApiOperation("Endpoint para deletar pessoa por ID")
	@RequestMapping(value = ENDPOINT + "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) {
		try {
			Optional<Pessoa> consulta = pessoaRepository.findById(id);

			if (consulta.isEmpty()) {
				return ResponseEntity.badRequest().body("Pessoa com o ID " + id + " não encontrado");
			}

			Pessoa p = consulta.get();

			p.setFlag_ativo("N");
			p.setFlag_removido("N");

			pessoaRepository.save(p);

			return ResponseEntity.accepted().body("Pessoa removida com sucesso");

		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Erro ao remover pessoa");
		}

	}

	/*
	 * @ApiOperation("Endpoint para deletar pessoa por CPF")
	 * 
	 * @RequestMapping(value = ENDPOINT+"/{cpf}", method=RequestMethod.DELETE)
	 * public ResponseEntity<String> deleteByCpf(@PathVariable("cpf")String cpf){
	 * try { Optional<Pessoa> consulta =
	 * Optional.ofNullable(pessoaRepository.findByCPF(cpf));
	 * 
	 * if (consulta.isEmpty()) { return
	 * ResponseEntity.badRequest().body("Pessoa com o CPF "+cpf+" não encontrado");
	 * }
	 * 
	 * Pessoa p = consulta.get();
	 * 
	 * p.setFlag_ativo("N"); p.setFlag_removido("N");
	 * 
	 * pessoaRepository.save(p);
	 * 
	 * return ResponseEntity.accepted().body("Pessoa removida com sucesso");
	 * 
	 * } catch (Exception e) { return
	 * ResponseEntity.internalServerError().body("Erro ao remover pessoa"); }
	 * 
	 * }
	 */

}
