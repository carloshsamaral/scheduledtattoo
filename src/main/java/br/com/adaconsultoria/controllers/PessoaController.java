package br.com.adaconsultoria.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.adaconsultoria.entities.Endereco;
import br.com.adaconsultoria.entities.Pessoa;
import br.com.adaconsultoria.repositories.IEnderecoRepository;
import br.com.adaconsultoria.repositories.IPessoaRepository;
import br.com.adaconsultoria.requests.EnderecoPostRequest;
import br.com.adaconsultoria.requests.PessoaPostRequest;
import io.swagger.annotations.ApiOperation;

@Transactional
@Controller
public class PessoaController {

	private static final String ENDPOINT = "/api/pessoas";

	@Autowired
	private IPessoaRepository pessoaRepository;
	
	@Autowired
	private IEnderecoRepository enderecoRepository;

	@ApiOperation("Endpoint para cadastro de pessoas")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	public ResponseEntity<String> post(@RequestBody PessoaPostRequest request) {
		try {
			if (pessoaRepository.findByCPF(request.getCpf()) != null) {
				return ResponseEntity.badRequest().body("O CPF " + request.getCpf() + " ja esta cadastrado no sistema");
			}

			Pessoa p = new Pessoa();
			EnderecoPostRequest epost = request.getEndereco();			
			Endereco e = new Endereco();
			
			e.setBairro(epost.getBairro());
			e.setCep(epost.getCep());
			e.setComplemento(epost.getComplemento());
			e.setFlag_ativo("S");
			e.setFlag_removido("N");
			e.setLocalidade(epost.getLocalidade());
			e.setLogradouro(epost.getLogradouro());
			e.setUf(epost.getUf());
			
			
			p.setCpf(request.getCpf());
			p.setEmail(request.getEmail());
			p.setEndereco(e);
			p.setFlag_ativo("S");
			p.setFlag_removido("N");
			p.setNome(request.getNome());
			p.setTelefone(request.getTelefone());
			p.setTipoPessoa(request.getTipoPessoa());

			enderecoRepository.save(e);
			
			pessoaRepository.save(p);

			return ResponseEntity.status(HttpStatus.CREATED).body("Pessoa criada com sucesso");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Problema em inserir a pessoa");
		}

	}
}
