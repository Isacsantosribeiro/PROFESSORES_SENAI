package Model;

public class Fornecedor {
private String id;
private String nome;
private String cnpj; 
private String telefone;
private String endereco;


public Fornecedor() {
	super();
}
public Fornecedor(String id, String nome, String cnpj, String telefone, String endereco) {
	super();
	this.id = id;
	this.nome = nome;
	this.cnpj = cnpj;
	this.telefone = telefone;
	this.endereco = endereco;
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
public String getCnpj() {
	return cnpj;
}
public void setCnpj(String cnpj) {
	this.cnpj = cnpj;
}
public String getTelefone() {
	return telefone;
}
public void setTelefone(String telefone) {
	this.telefone = telefone;
}
public String getEndereco() {
	return endereco;
}
public void setEndereco(String endereco) {
	this.endereco = endereco;
}
	
	




}
