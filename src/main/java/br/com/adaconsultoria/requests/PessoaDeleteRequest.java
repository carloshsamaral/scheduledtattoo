package br.com.adaconsultoria.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.adaconsultoria.entities.Endereco;
import br.com.adaconsultoria.enums.TipoPessoa;
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
public class PessoaDeleteRequest {

	@JsonProperty("id_pessoa")
	private Integer id_pessoa;
	@JsonProperty("nome")
	private String nome;
	@JsonProperty("email")
	private String email;
	@JsonProperty("telefone")
	private String telefone;
	@JsonProperty("cpf")
	private String cpf;

	@JsonProperty("tipoPessoa")
	private TipoPessoa tipoPessoa;
	
	@JsonProperty("endereco")
	private Endereco endereco;

}
