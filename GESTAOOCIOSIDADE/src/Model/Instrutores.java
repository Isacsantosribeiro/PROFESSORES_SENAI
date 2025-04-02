package Model;

public class Instrutores {
	
	private String id;
	private String nome;
	private String cpf;
	
	/**
	 * 
	 */
	public Instrutores() {
		super();
	}
	/**
	 * @param id
	 * @param nome
	 * @param cpf
	 */
	public Instrutores(String id, String nome, String cpf) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
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
	

}
