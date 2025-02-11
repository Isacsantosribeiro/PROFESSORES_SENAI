package Model;

public class Venda {
	private String idvenda;
	private String idcliente;
	private String idfuncionario;
	private String formaDePagamento;
	private String desconto;
	private String dataVenda;
	private String precoTotal;

	public Venda() {
		super();
	}
	public Venda(String idvenda, String idcliente, String idfuncionario, String formaDePagamento, String desconto,
			String dataVenda, String precoTotal) {
		super();
		this.idvenda = idvenda;
		this.idcliente = idcliente;
		this.idfuncionario = idfuncionario;
		this.formaDePagamento = formaDePagamento;
		this.desconto = desconto;
		this.dataVenda = dataVenda;
		this.precoTotal = precoTotal;
	}
	public String getIdvenda() {
		return idvenda;
	}
	public void setIdvenda(String idvenda) {
		this.idvenda = idvenda;
	}
	public String getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(String idcliente) {
		this.idcliente = idcliente;
	}
	public String getIdfuncionario() {
		return idfuncionario;
	}
	public void setIdfuncionario(String idfuncionario) {
		this.idfuncionario = idfuncionario;
	}
	public String getFormaDePagamento() {
		return formaDePagamento;
	}
	public void setFormaDePagamento(String formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}
	public String getDesconto() {
		return desconto;
	}
	public void setDesconto(String desconto) {
		this.desconto = desconto;
	}
	public String getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}
	public String getPrecoTotal() {
		return precoTotal;
	}
	public void setPrecoTotal(String precoTotal) {
		this.precoTotal = precoTotal;
	}
	
	
	
	
	

}

