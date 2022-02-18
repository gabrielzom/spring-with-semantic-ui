package br.org.recode.models;

import javax.persistence.*;

@Entity @Table(name = "historicos")
public class Historico {
	
	@Id @GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, columnDefinition = "date")
	private String data;
	
	@Column(nullable = true, columnDefinition = "decimal(10,2)")
	private double valor;
	
	@ManyToOne
	@JoinColumn(name = "cliente")
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
}

