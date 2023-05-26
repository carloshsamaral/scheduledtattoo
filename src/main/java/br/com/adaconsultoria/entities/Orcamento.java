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

import br.com.adaconsultoria.enums.TipoServico;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "orcamento")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Orcamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_orcamento")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JoinColumn(referencedColumnName = "id_pessoa")
	
	private Pessoa profissional; 
	
	@OneToOne
	@JoinColumn(referencedColumnName = "id_pessoa")
	private Pessoa cliente;
	
	@Column(name = "data_orcamento")
	private Date dtOrcamento;
	
	@Column(name = "data_fim_orcamento")
	private Date dtFimOrcamento;
	
	@Column(name = "valor_orcamento")
	private BigDecimal vlOrcamento;
	
	@Column(name = "tipo_servico")
	private TipoServico tipoServico;
	
	@Column(name = "flag_ativo")
	private String flag_ativo;
	
	@OneToOne
	@JoinColumn(referencedColumnName = "id_studio", name = "fk_studio")
	private Studio studio;
	
}
