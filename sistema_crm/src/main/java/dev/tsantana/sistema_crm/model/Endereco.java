package dev.tsantana.sistema_crm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Table(name = "Endereco")
@Entity
public class Endereco {

	@Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String cep;

	@NotBlank
	private String logradouro;

	@NotBlank
	private String numero;

	private String complemento;

	@NotBlank
	private String bairro;

	@NotBlank
	private String localidade;

	@NotBlank
	private String uf;

	@NotBlank
	private String ddd;

	@NotEmpty
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Endereco(Long id, String cep, String logradouro, String numero, String complemento, String bairro,
			String localidade, String uf, String ddd) {
		super();
		this.id = id;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.ddd = ddd;
	}

}
