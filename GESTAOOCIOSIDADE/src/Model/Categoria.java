package Model;

public class Categoria {
	private String id;
	private String nome;
	
	
	
	/**
	 * 
	 */
	public Categoria() {
		super();
	}
	/**
	 * @param id
	 * @param nome
	 */
	public Categoria(String id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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
	
	

}
