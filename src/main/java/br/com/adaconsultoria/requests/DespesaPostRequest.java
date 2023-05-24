package br.com.adaconsultoria.requests;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class DespesaPostRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@JsonProperty("nome")
	private String nome;
	@JsonProperty("descritivo")
	private String descritivo;
	@JsonProperty("dtCadastro")
	private Date dtCadastro;
	@JsonProperty("dtDebto")
	private Date dtDebto;
	@JsonProperty("valorTotal")
	private BigDecimal valorTotal;
	@JsonProperty("qtdParcelas")
	private Integer qtdParcelas;
	@JsonProperty("razaoSocial")
	private String razaoSocial;
	@JsonProperty("cnpj")
	private String cnpj;
	@JsonProperty("Studio")
	private Integer id_studio;
	

	
}
