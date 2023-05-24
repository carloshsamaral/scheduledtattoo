package br.com.adaconsultoria.requests;

import java.io.Serializable;

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
public class DespesaDeleteRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id_despesa")
	private Integer id;

	

	
}
