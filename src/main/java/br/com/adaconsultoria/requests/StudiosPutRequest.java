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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class StudiosPutRequest {

	@JsonProperty("idEmpresa")
	private Integer idEmpresa;
	@JsonProperty("nomeFantasia")
	private String nomeFantasia;
	@JsonProperty("razaoSocial")
	private String razaoSocial;
	@JsonProperty("cnpj")
	private String cnpj;
	
}
