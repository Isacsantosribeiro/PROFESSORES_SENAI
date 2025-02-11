package Model;

public class ProdutoVenda {
	private String idprodutovenda;
	private String idvenda;
	private String idproduto;
	private String quantidade;
	public ProdutoVenda() {
		super();
	}
	public ProdutoVenda(String idprodutovenda, String idvenda, String idproduto, String quantidade) {
		super();
		this.idprodutovenda = idprodutovenda;
		this.idvenda = idvenda;
		this.idproduto = idproduto;
		this.quantidade = quantidade;
	}
	public String getIdprodutovenda() {
		return idprodutovenda;
	}
	public void setIdprodutovenda(String idprodutovenda) {
		this.idprodutovenda = idprodutovenda;
	}
	public String getIdvenda() {
		return idvenda;
	}
	public void setIdvenda(String idvenda) {
		this.idvenda = idvenda;
	}
	public String getIdproduto() {
		return idproduto;
	}
	public void setIdproduto(String idproduto) {
		this.idproduto = idproduto;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
	
	

}
