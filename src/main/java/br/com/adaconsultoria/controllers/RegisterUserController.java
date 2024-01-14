package br.com.adaconsultoria.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.adaconsultoria.entities.Usuario;
import br.com.adaconsultoria.helpers.MD5Helper;
import br.com.adaconsultoria.repositories.IUsuarioRepository;
import br.com.adaconsultoria.requests.RegisterUserPostRequest;
import io.swagger.annotations.ApiOperation;

@Transactional
@Controller
public class RegisterUserController {

	private static final String ENDPOINT = "/api/register-user";
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@ApiOperation("Metodo para registrar usuario")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	public ResponseEntity<String> post(@RequestBody RegisterUserPostRequest request){
		try {
			Usuario u = new Usuario();
			
			if(usuarioRepository.findByEmail(request.getEmail()) != null) {
				return ResponseEntity.badRequest().body("E-mail já cadastrando para usuario");
			}
			u.setEmail(request.getEmail());
			u.setNome(request.getNome());
			u.setSenha(MD5Helper.encrypt(request.getSenha()));
			usuarioRepository.save(u);
			return ResponseEntity.status(HttpStatus.CREATED).body("Parabéns '"+ u.getNome() + "', sua conta foi criada com sucesso!");
			
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Erro ao cadastrar usuário");
		}

	}
	
}
