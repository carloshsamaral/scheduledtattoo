package br.com.adaconsultoria.controllers;

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

import br.com.adaconsultoria.entities.Orcamento;
import br.com.adaconsultoria.entities.Pessoa;
import br.com.adaconsultoria.entities.Studio;
import br.com.adaconsultoria.repositories.IOrcamentoRepository;
import br.com.adaconsultoria.repositories.IPessoaRepository;
import br.com.adaconsultoria.repositories.IStudioRepository;
import br.com.adaconsultoria.requests.OrcamentoPostRequest;
import br.com.adaconsultoria.requests.OrcamentoPutRequest;
import io.swagger.annotations.ApiOperation;

@Transactional
@Controller
public class OrcamentoController {

	private static final String ENDPOINT = "/api/orcamento";

	@Autowired
	private IOrcamentoRepository orcamentoRepository;
	
	@Autowired
	private IStudioRepository studioRepository;
	
	@Autowired
	private IPessoaRepository pessoaRepository;

	
	@ApiOperation("Endpoint para cadastro de orcamento")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	public ResponseEntity<String> post(@RequestBody OrcamentoPostRequest request) {
		try {
			
			Optional<Studio> studio = studioRepository.findById(request.getId_studio());
			if(studio.isEmpty()) {
				return ResponseEntity.badRequest().body("Erro ao inserir o orçamento, studio não cadastrado.");
			}
			Studio s = studio.get();
			
			Optional<Pessoa> prof = pessoaRepository.findById(request.getId_profissional());
			
			if(prof.isEmpty()) {
				return ResponseEntity.badRequest().body("Erro ao inserir o orçamento, profissional não cadastrado");
			}
			
			Pessoa profi = prof.get();
			
			Optional<Pessoa> cli = pessoaRepository.findById(request.getId_cliente());
			
			if (cli.isEmpty()) {
				return null;
			}
			
			Pessoa cl = cli.get();
			
			Orcamento o = new Orcamento();
			o.setFlag_ativo("S");
			o.setVlOrcamento(request.getVlOrcamento());
			o.setStudio(s);
			o.setProfissional(profi);
			o.setCliente(cl);
			o.setDtFimOrcamento(request.getDtFimOrcamento());
			o.setDtOrcamento(request.getDtOrcamento());
			o.setTipoServico(request.getTipoServico());
			orcamentoRepository.save(o);


			return ResponseEntity.status(HttpStatus.CREATED).body("Orcamento criada com sucesso");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Problema em inserir a orcamento");
		}

	}

	@ApiOperation("Endpoint para atualizar orcamento")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.PUT)
	public ResponseEntity<String> putPessoa(@RequestBody OrcamentoPutRequest request) {
		try {
			Optional<Orcamento> orcamento = orcamentoRepository.findById(request.getId_orcamento());
			if (orcamento.isEmpty()) {
				return ResponseEntity.badRequest().body("Orcamento não exite");
			}
			
			Orcamento o = orcamento.get();
			Optional<Studio> studio = studioRepository.findById(request.getId_studio());
			if(studio.isEmpty()) {
				return ResponseEntity.badRequest().body("Erro ao inserir o orçamento, studio não cadastrado.");
			}
			Studio s = studio.get();
			
			Optional<Pessoa> prof = pessoaRepository.findById(request.getId_profissional());
			
			if(prof.isEmpty()) {
				return ResponseEntity.badRequest().body("Erro ao inserir o orçamento, profissional não cadastrado");
			}
			
			Pessoa profi = prof.get();
			
			Optional<Pessoa> cli = pessoaRepository.findById(request.getId_cliente());
			
			if (cli.isEmpty()) {
				return null;
			}
			
			Pessoa cl = cli.get();
			
			o.setFlag_ativo("S");
			o.setVlOrcamento(request.getVlOrcamento());
			o.setStudio(s);
			o.setProfissional(profi);
			o.setCliente(cl);
			o.setDtFimOrcamento(request.getDtFimOrcamento());
			o.setDtOrcamento(request.getDtOrcamento());
			o.setTipoServico(request.getTipoServico());
			
			orcamentoRepository.save(o);
			
			return ResponseEntity.status(HttpStatus.CREATED).body("Orcamento atualizada com sucesso");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Problema em inserir a orcamento");
		}

	}

	@ApiOperation("Endpoint para retornar todas os orcamentos")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.GET)
	public ResponseEntity<List<Orcamento>> getAll() {
		try {
			List<Orcamento> lista = (List<Orcamento>) orcamentoRepository.findAll();

			return ResponseEntity.ok(lista);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@ApiOperation("Endpoint para retonar orcamento por ID")
	@RequestMapping(value = ENDPOINT + "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Orcamento> getById(@PathVariable("id") Integer idOrcamento) {
		try {
			Optional<Orcamento> orc = orcamentoRepository.findById(idOrcamento);
			if (orc.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);	
			}
			
			Orcamento o = orc.get();
			
			return ResponseEntity.ok(o);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	@ApiOperation("Endpoint para deletar orcamento por ID")
	@RequestMapping(value = ENDPOINT + "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) {
		try {
			Optional<Orcamento> orc = orcamentoRepository.findById(id);
			if (orc.isEmpty()) {
				return ResponseEntity.badRequest().body("Orcamento nao existente para delecao");
			}
			Orcamento o = orc.get();
			
			o.setFlag_ativo("N");
			orcamentoRepository.save(o);
			
			return ResponseEntity.accepted().body("Orcamento removida com sucesso");

		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Erro ao remover orcamento");
		}

	}

	

}
