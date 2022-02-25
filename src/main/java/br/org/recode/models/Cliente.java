package br.org.recode.models;
import javax.persistence.*;

@Entity @Table(name="clientes")
public class Cliente {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 40)
	private String nome;
	
	@Column(nullable = false, columnDefinition = "char(11)")
	private String cpf;
	
	@Column(nullable = false, columnDefinition = "date")
	private String nascimento;
	
	@Column(nullable = true, length = 60)
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco")
	private Endereco endereco;

	@Column(nullable = false, length = 255)
	private String imageUrl;

	

	public String getImageUrl() {return imageUrl;}

	public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", nascimento=" + nascimento + ", email="
				+ email + ", endereco=" + endereco + "]";
	}
	
	
}
