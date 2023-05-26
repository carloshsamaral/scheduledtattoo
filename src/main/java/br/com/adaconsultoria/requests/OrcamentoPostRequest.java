package br.com.adaconsultoria.requests;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.adaconsultoria.enums.TipoServico;
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
public class OrcamentoPostRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id_profissional")
	private Integer id_profissional; 
	
	@JsonProperty("id_cliente")
	private Integer id_cliente;
	
	@JsonProperty("data_orcamento")
	private Date dtOrcamento;
	
	@JsonProperty("data_fim_orcamento")
	private Date dtFimOrcamento;
	
	@JsonProperty("valor_orcamento")
	private BigDecimal vlOrcamento;
	
	@JsonProperty("tipo_servico")
	private TipoServico tipoServico;
	
	@JsonProperty("id_studio")
	private Integer id_studio;
	
	
}
