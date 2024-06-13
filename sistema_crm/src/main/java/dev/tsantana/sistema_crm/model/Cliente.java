package dev.tsantana.sistema_crm.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Table(name = "Cliente")
@Entity
public class Cliente {

	@Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;

	@Past
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private LocalDate nascimento;

	@ElementCollection
	@CollectionTable(name = "telefones")
	private Set<String> telefones = new HashSet<String>();

	@JsonIgnore
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private Set<Endereco> enderecos = new HashSet<Endereco>();

	public Cliente(Long id, String nome, LocalDate nascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.nascimento = nascimento;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	public void addEndereco(Endereco endereco) {
		this.enderecos.add(endereco);
	}

	public void addTelefone(String telefone) {
		this.telefones.add(telefone);
	}

	@Override
	public String toString() {
		return "Cliente: \n id=" + id + "\n nome=" + nome + "\n nascimento=" + nascimento;
	}

}
