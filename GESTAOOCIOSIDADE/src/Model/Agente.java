package Model;

public class Agente {
	private String id;
	private String nome;
	private String cpf;
	private String senha;
	
	
	/**
	 * 
	 */
	public Agente() {
		super();
	}
	/**
	 * @param id
	 * @param nome
	 * @param cpf
	 * @param senha
	 */
	public Agente(String id, String nome, String cpf, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}
