package br.com.adaconsultoria.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="studio")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Studio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_studio")
	private Integer id_studio;
	@Column(name="nome_fantasia", length = 255, nullable = false)
	private String nomeFantasia;
	@Column(name="razao_social", length = 255, nullable = false)
	private String razaoSocial;
	@Column(name="cnpj", length = 255, nullable = false, unique = true)
	private String cnpj;	
	@Column(name="flag_ativo", length = 1)
	private String flag_ativo;
	@Column(name="flag_removido", length = 1)
	private String flag_removido;
	
}
