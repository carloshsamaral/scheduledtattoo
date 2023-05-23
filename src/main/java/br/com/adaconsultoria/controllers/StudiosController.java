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

import br.com.adaconsultoria.entities.Studio;
import br.com.adaconsultoria.repositories.IStudioRepository;
import br.com.adaconsultoria.requests.StudiosPostRequest;
import br.com.adaconsultoria.requests.StudiosPutRequest;
import io.swagger.annotations.ApiOperation;

@Transactional
@Controller
public class StudiosController {

	private static final String ENDPOINT = "/api/studio";

	@Autowired
	private IStudioRepository studioRepository;

	@ApiOperation("Endpoint para criação de Studios.")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	public ResponseEntity<String> post(@RequestBody StudiosPostRequest request) {
		try {

			if (studioRepository.findByCNPJ(request.getCnpj()) != null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("O CNPJ " + request.getCnpj() + " já está cadastrado no sistema");
			}

			Studio st = new Studio();

			st.setCnpj(request.getCnpj());
			st.setNomeFantasia(request.getNomeFantasia());
			st.setRazaoSocial(request.getRazaoSocial());
			st.setFlag_ativo("S");
			st.setFlag_removido("N");

			studioRepository.save(st);

			return ResponseEntity.status(HttpStatus.CREATED).body("Studio criado com sucesso");

		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Problema ao inserir no DB " + e.getMessage());
		}

	}

	@ApiOperation("Endpoint para atualização de Studios.")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.PUT)
	public ResponseEntity<String> put(@RequestBody StudiosPutRequest request) {

		try {
			Optional<Studio> consulta = studioRepository.findById(request.getIdStudio());

			if (consulta.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Studio com o ID " + request.getIdStudio() + " não encontrado.");
			}
			Studio st = consulta.get();

			st.setNomeFantasia(request.getNomeFantasia());
			st.setRazaoSocial(request.getRazaoSocial());

			studioRepository.save(st);

			return ResponseEntity.status(HttpStatus.OK).body("Studio atualizado com sucesso");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Problema ao atualizar no BD " + e.getMessage());
		}

	}

	@ApiOperation("Endpoint para deleção de Studios.")
	@RequestMapping(value = ENDPOINT + "/{idEmpresa}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("idEmpresa") Integer idStudio) {
		try {
			Optional<Studio> consulta = studioRepository.findById(idStudio);

			if (consulta.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Studio com o ID " + idStudio + " não encontrado.");
			}
			Studio st = consulta.get();

			st.setFlag_removido("S");

			studioRepository.save(st);

			return ResponseEntity.status(HttpStatus.OK).body("Studio deletado com sucesso");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Problema ao atualizar no BD " + e.getMessage());
		}
	}

	@ApiOperation("Endpoint para retorno de todos os Studios.")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.GET)
	public ResponseEntity<List<Studio>> getAll() {
		try {
			List<Studio> lista = (List<Studio>) studioRepository.findAll();

			return ResponseEntity.ok(lista);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	@ApiOperation("Endpoint para retorno de um único Studios.")
	@RequestMapping(value = ENDPOINT + "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Studio> getById(@PathVariable("id") Integer idStudio) {
		try {
			Optional<Studio> consulta = studioRepository.findById(idStudio);

			if (consulta.isPresent()) {

				Studio st = consulta.get();
				return ResponseEntity.status(HttpStatus.OK).body(st);

			}
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}

}
