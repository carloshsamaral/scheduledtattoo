package br.com.adaconsultoria.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Endereco")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Endereco implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	private Integer id_endereco;

	@Column(name = "cep")
	private String cep;
	@Column(name = "logradouro")
	private String logradouro;
	@Column(name = "complemento")
	private String complemento;
	@Column(name = "bairro")
	private String bairro;
	@Column(name = "localidade")
	private String localidade;
	@Column(name = "uf")
	private String uf;

	@Column(name = "flag_ativo", length = 1)
	private String flag_ativo;
	@Column(name = "flag_removido", length = 1)
	private String flag_removido;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_pessoa")
	@JsonBackReference
	private Pessoa pessoa;

}
