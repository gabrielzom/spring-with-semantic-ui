package br.org.recode.models;

import javax.persistence.*;

@Entity
@Table(name = "enderecos")
public class Endereco {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 40)
	private String logradouro;
	
	@Column(nullable = false, length = 30)
	private String bairro;
	
	@Column(nullable = false)
	private int numero;
	
	@Column(nullable = false, length = 30)
	private String cidade;
	
	@Column(nullable = false, columnDefinition="char(2)" )
	private String uf;
	
	@Column(columnDefinition="char(8)")
	private String cep;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", logradouro=" + logradouro + ", bairro=" + bairro + ", numero=" + numero
				+ ", cidade=" + cidade + ", uf=" + uf + ", cep=" + cep + "]";
	}
	
	
}
