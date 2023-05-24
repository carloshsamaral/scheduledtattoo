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

import br.com.adaconsultoria.entities.Despesa;
import br.com.adaconsultoria.entities.Studio;
import br.com.adaconsultoria.repositories.IDespesaRepository;
import br.com.adaconsultoria.repositories.IStudioRepository;
import br.com.adaconsultoria.requests.DespesaPostRequest;
import br.com.adaconsultoria.requests.DespesaPutRequest;
import io.swagger.annotations.ApiOperation;

@Transactional
@Controller
public class DespesaController {

	private static final String ENDPOINT = "/api/despesa";

	@Autowired
	private IDespesaRepository despesaRepository;

	@Autowired
	private IStudioRepository studioRepository;

	@ApiOperation("Endpoint para atualizar de despesa")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.PUT)
	public ResponseEntity<String> post(@RequestBody DespesaPutRequest request) {
		try {

			Optional<Despesa> despesa = despesaRepository.findById(request.getId_despesa());			
			Optional<Studio> studio = studioRepository.findById(request.getId_Studio());
			Studio st = studio.get();
			Despesa d = despesa.get();

			d.setCnpj(request.getCnpj());
			d.setDescritivo(request.getDescritivo());
			d.setDtCadastro(request.getDtCadastro());
			d.setDtDebto(request.getDtDebto());
			d.setFlagAtivo("S");
			d.setFlagRemovido("N");
			d.setNome(request.getNome());
			d.setQtdParcelas(request.getQtdParcelas());
			d.setRazaoSocial(request.getRazaoSocial());
			d.setStudio(st);
			d.setValorTotal(request.getValorTotal());

			despesaRepository.save(d);

			return ResponseEntity.status(HttpStatus.CREATED).body("Despesa criada com sucesso");

		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Problema em conectar na API");
		}

	}

	@ApiOperation("Endpoint para inserir despesa")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	public ResponseEntity<String> post(@RequestBody DespesaPostRequest request) {
		try {

			Optional<Studio> studio = studioRepository.findById(request.getId_studio());
			
			Despesa d = new Despesa();
			d.setCnpj(request.getCnpj());
			d.setDescritivo(request.getDescritivo());
			d.setFlagAtivo("S");
			d.setFlagRemovido("N");
			d.setNome(request.getNome());
			d.setQtdParcelas(request.getQtdParcelas());
			d.setRazaoSocial(request.getRazaoSocial());
			d.setStudio(studio.get());
			
			despesaRepository.save(d);

			return ResponseEntity.status(HttpStatus.CREATED).body("Despesa criada com sucesso");
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Problema ao inserir despesa");
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation("Endpoint para retornar todas as despesas")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.GET)
	public ResponseEntity<List<Despesa>> getAll(){
		try {
			List<Despesa> d = (List<Despesa>) despesaRepository.findAll();
			return ResponseEntity.ok(d);
		} catch (Exception e) {
			return (ResponseEntity<List<Despesa>>) ResponseEntity.internalServerError();
		}
	}
	
	@ApiOperation("Endpoint para deletar uma despesa")
	@RequestMapping(value = ENDPOINT + "/{id}", method= RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("id") Integer id){
		try {
			Optional<Despesa> despesa = despesaRepository.findById(id);
			Despesa d = despesa.get();
			
			d.setFlagAtivo("N");
			d.setFlagRemovido("S");
			
			despesaRepository.save(d);
			
			return ResponseEntity.ok("Despesa deletada com sucesso");
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Problema em deletar a despesa");
		}
		
	}
	
	
	

}
