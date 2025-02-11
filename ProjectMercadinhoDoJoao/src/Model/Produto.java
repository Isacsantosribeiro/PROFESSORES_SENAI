package Model;

public class Produto {
	private String idProduto;
	private String idfornecedor;
	private String nome;
	private String codBarra;
	private String lote;
	private String dataFab;
	private String dataVal;
	private String marca;
	private String categoria;
	private String unidadeDeMedida;
	private String precoUnitario;
	private String estoque;
	
	
	
	
	
	public Produto() {
		super();
	}
	public Produto(String idProduto, String idfornecedor, String nome, String codBarra, String lote, String dataFab,
			String dataVal, String marca, String categoria, String unidadeDeMedida, String precoUnitario,
			String estoque) {
		super();
		this.idProduto = idProduto;
		this.idfornecedor = idfornecedor;
		this.nome = nome;
		this.codBarra = codBarra;
		this.lote = lote;
		this.dataFab = dataFab;
		this.dataVal = dataVal;
		this.marca = marca;
		this.categoria = categoria;
		this.unidadeDeMedida = unidadeDeMedida;
		this.precoUnitario = precoUnitario;
		this.estoque = estoque;
	}
	public String getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}
	public String getIdfornecedor() {
		return idfornecedor;
	}
	public void setIdfornecedor(String idfornecedor) {
		this.idfornecedor = idfornecedor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodBarra() {
		return codBarra;
	}
	public void setCodBarra(String codBarra) {
		this.codBarra = codBarra;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public String getDataFab() {
		return dataFab;
	}
	public void setDataFab(String dataFab) {
		this.dataFab = dataFab;
	}
	public String getDataVal() {
		return dataVal;
	}
	public void setDataVal(String dataVal) {
		this.dataVal = dataVal;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getUnidadeDeMedida() {
		return unidadeDeMedida;
	}
	public void setUnidadeDeMedida(String unidadeDeMedida) {
		this.unidadeDeMedida = unidadeDeMedida;
	}
	public String getPrecoUnitario() {
		return precoUnitario;
	}
	public void setPrecoUnitario(String precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	public String getEstoque() {
		return estoque;
	}
	public void setEstoque(String estoque) {
		this.estoque = estoque;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	
	
	
	
	
	