package br.com.adaconsultoria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.adaconsultoria.entities.Studio;
import br.com.adaconsultoria.repositories.IStudioRepository;
import br.com.adaconsultoria.requests.StudiosPostRequest;
import br.com.adaconsultoria.requests.StudiosPutRequest;
import io.swagger.annotations.ApiOperation;

@Controller
public class StudiosController {
	
	private static final String ENDPOINT = "/api/studios";
	
	@Autowired
	private IStudioRepository studioRepository;

	@ApiOperation("Endpoint para criação de Studios.")
	@RequestMapping(value = ENDPOINT, method= RequestMethod.POST)
	public ResponseEntity<String> post(@RequestBody StudiosPostRequest request){
		try {
		
			Studio st = new Studio();

			st.setCnpj(request.getCnpj());
			st.setNomeFantasia(request.getNomeFantasia());
			st.setRazaoSocial(request.getRazaoSocial());
			
			studioRepository.save(st);
					
			return ResponseEntity.status(HttpStatus.CREATED).body("Studio criado com sucesso");
			
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Problema ao inserir no DB "+e.getMessage());
		}
		
		
		
	}
	@ApiOperation("Endpoint para atualização de Studios.")
	@RequestMapping(value = ENDPOINT, method= RequestMethod.PUT)
	public ResponseEntity<String> put(@RequestBody StudiosPutRequest request){
			
		return ResponseEntity.status(HttpStatus.OK).body("Studio atualizado com sucesso");
		
	}
	@ApiOperation("Endpoint para deleção de Studios.")
	@RequestMapping(value = ENDPOINT + "/{idEmpresa}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("idEmpresa") Integer idEmpresa){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body("Studio deletado com sucesso");
	}
	@ApiOperation("Endpoint para retorno de todos os Studios.")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.GET)
	public ResponseEntity<String> getAll(){
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.body("Não há studio cadastrado");
	}
	@ApiOperation("Endpoint para retorno de um único Studios.")
	@RequestMapping(value = ENDPOINT + "/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> getById(@PathVariable("id") Integer id){
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
}
