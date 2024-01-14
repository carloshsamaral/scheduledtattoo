package br.com.adaconsultoria.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.adaconsultoria.enums.TipoPessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pessoa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pessoa")
	private Integer id_pessoa;
	@Column(name = "nome")
	private String nome;
	@Column(name = "email")
	private String email;
	@Column(name = "telefone")
	private String telefone;
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "tipoPessoa")
	private TipoPessoa tipoPessoa;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_pessoa")
	@JsonManagedReference	
	private List<Endereco> enderecoList = new ArrayList<>();

	@Column(name="flag_ativo", length = 1)
	private String flag_ativo;
	@Column(name="flag_removido", length = 1)
	private String flag_removido;
}