package br.com.adaconsultoria.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.adaconsultoria.repositories.IUsuarioRepository;
import io.swagger.annotations.ApiOperation;

@Transactional
@Controller
public class PasswordRecoverController {

	private static final String ENDPOINT = "/api/password-recover";
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@ApiOperation("Metodo para recuperar senha de usuario")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	public ResponseEntity<String> post(){
		return null;
	}
	
}
