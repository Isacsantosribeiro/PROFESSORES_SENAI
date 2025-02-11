package Model;

public class Funcionario {
	private String id;
	private String nome;
	private String cpf;
	private String dataDeNascimento;
	private String email;
	private String genero;
	private String endereco;
	private String telefone;
	private String cargo;
	private String salario;
	private String dataDeAdimissao;
	private String senha;

	public Funcionario() {
		super();
	}
	public Funcionario(String id, String nome, String cpf, String dataDeNascimento, String email, String genero,
			String endereco, String telefone, String cargo, String salario, String dataDeAdimissao, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
		this.email = email;
		this.genero = genero;
		this.endereco = endereco;
		this.telefone = telefone;
		this.cargo = cargo;
		this.salario = salario;
		this.dataDeAdimissao = dataDeAdimissao;
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
	public String getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getSalario() {
		return salario;
	}
	public void setSalario(String salario) {
		this.salario = salario;
	}
	public String getDataDeAdimissao() {
		return dataDeAdimissao;
	}
	public void setDataDeAdimissao(String dataDeAdimissao) {
		this.dataDeAdimissao = dataDeAdimissao;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
	
	
}
