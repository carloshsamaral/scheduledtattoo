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
public class StudiosDeleteRequest {

	@JsonProperty("id_studio")
	private Integer idStudio;
	@JsonProperty("flag_ativo")
	private String flag_ativo;
	@JsonProperty("flag_removido")
	private String flag_removido;

	
}
