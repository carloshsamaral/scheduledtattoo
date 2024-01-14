package br.com.adaconsultoria.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterUserPostRequest {

	@JsonProperty("nome")
	private String nome;
	@JsonProperty("email")
	private String email;
	@JsonProperty("senha")
	private String senha;
	
}
