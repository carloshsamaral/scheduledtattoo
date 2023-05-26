package br.com.adaconsultoria.requests;

import java.io.Serializable;

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
public class OrcamentoDeleteRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id_orcamento")
	private Integer id_orcamento;
	
	@JsonProperty("flag_ativo")
	private String flag_ativo;
}
