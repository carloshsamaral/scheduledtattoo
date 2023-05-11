package br.com.adaconsultoria.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Setter;

@Entity
@Table(name="STUDIO")
@Setter
public class Studio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_studio")
	private Integer idStudio;
	@Column(name="nome_fantasia", length = 255, nullable = false)
	private String nomeFantasia;
	@Column(name="razao_social", length = 255, nullable = false)
	private String razaoSocial;
	@Column(name="cnpj", length = 255, nullable = false, unique = true)
	private String cnpj;
	
}
