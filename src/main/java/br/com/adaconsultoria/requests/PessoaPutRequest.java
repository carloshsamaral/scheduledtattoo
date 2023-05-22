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
public class PessoaPutRequest {

	@JsonProperty("id_pessoa")
	private Integer id_pessoa;
	@JsonProperty("flag_ativo")
	private String flag_ativo;
	@JsonProperty("flag_removido")
	private String flag_removido;

}
