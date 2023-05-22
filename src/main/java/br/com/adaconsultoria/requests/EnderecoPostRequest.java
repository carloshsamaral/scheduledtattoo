package br.com.adaconsultoria.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class EnderecoPostRequest {


	@JsonProperty("cep")
	private String cep;
	@JsonProperty("logradouro")
	private String logradouro;
	@JsonProperty("complemento")
	private String complemento;
	@JsonProperty("bairro")
	private String bairro;
	@JsonProperty("localidade")
	private String localidade;
	@JsonProperty("uf")
	private String uf;


}
