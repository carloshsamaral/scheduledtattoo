package br.com.adaconsultoria.entities;

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

@Entity
@Table(name = "Usuario")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private Integer idUsuario;
	@Column(name="nome")
	private String nome;
	@Column(name="email")
	private String email;
	@Column(name="senha")
	private String senha;
	
	
}
