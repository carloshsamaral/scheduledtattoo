package br.com.adaconsultoria.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Despesa")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Despesa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_despesa")
	private Integer id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "descritivo")
	private String descritivo;
	@Column(name = "dtCadastro")
	private Date dtCadastro;
	@Column(name = "dtDebto")
	private Date dtDebto;
	@Column(name = "valorTotal")
	private BigDecimal valorTotal;
	@Column(name = "qtdParcelas")
	private Integer qtdParcelas;
	@Column(name = "razaoSocial")
	private String razaoSocial;
	@Column(name = "cnpj")
	private String cnpj;
	@Column(name = "flagAtivo")
	private String flagAtivo;
	@Column(name = "flagRemovido")
	private String flagRemovido;
	
	@OneToOne
	@JoinColumn(name = "id_studio")
	private Studio studio;
	

	
}
