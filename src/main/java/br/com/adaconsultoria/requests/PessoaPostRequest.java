package br.com.adaconsultoria.requests;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class PessoaPostRequest {

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
	
	
	@JsonProperty("flag_ativo")
	private String flag_ativo;
	@JsonProperty("flag_removido")
	private String flag_removido;
	public List<EnderecoPostRequest> getEnderecoList() {
		// TODO Auto-generated method stub
		return null;
	}

}
