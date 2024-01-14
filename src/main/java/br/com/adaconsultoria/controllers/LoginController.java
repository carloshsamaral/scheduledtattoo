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
import br.com.adaconsultoria.requests.LoginPostRequest;
import br.com.adaconsultoria.security.TokenSecurity;
import io.swagger.annotations.ApiOperation;

@Transactional
@Controller
public class LoginController {
/*
	private static final String ENDPOINT = "/api/login";
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@ApiOperation("Metodo para logar usuario")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	public ResponseEntity<String> post(@RequestBody LoginPostRequest request){
		try {
			Usuario usuario = usuarioRepository.findByEmailSenha(request.getEmail(), MD5Helper.encrypt(request.getSenha()));
			if(usuario == null)
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso negado. E-mail ou senha inválidos");
			
			
			//return ResponseEntity.ok("ACESSOU");
			return ResponseEntity.ok(TokenSecurity.generateToken(usuario.getEmail()));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	*/
}
