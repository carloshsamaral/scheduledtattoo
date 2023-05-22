package br.com.adaconsultoria.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.adaconsultoria.enums.TipoPessoa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

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
		
	@OneToOne
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;

	@Column(name="flag_ativo", length = 1)
	private String flag_ativo;
	@Column(name="flag_removido", length = 1)
	private String flag_removido;
}
