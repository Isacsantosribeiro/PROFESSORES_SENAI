package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import DAO.ClienteDAO;
import DAO.ProdutoDAO;
import DAO.ProdutoVendaDAO;
import DAO.VendaDAO;
import Model.Cliente;
import Model.Produto;
import Model.ProdutoVenda;
import Model.Venda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class controllerRegistrarVenda implements Initializable{

    @FXML
    private Button btAdicionar;

    @FXML
    private Button btCancelar;

    @FXML
    private Button btRegistrar;

    @FXML
    private ChoiceBox<String> choiceFormaPgto;

    @FXML
    private ChoiceBox<String> choiceProduto;

    @FXML
    private TableColumn<Produto,String> columnIndice;

    @FXML
    private TableColumn<Produto,String> columnPrecoTotal;

    @FXML
    private TableColumn<Produto,String> columnPrecoUn;

    @FXML
    private TableColumn<Produto,String> columnProduto;

    @FXML
    private TableColumn<Produto,String> columnQuantidade;

    @FXML
    private TableView<Produto> tableProdutos;

    @FXML
    private TextField txtCliente;


    @FXML
    private TextField txtProduto;
    
    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtDesconto;

    @FXML
    private TextField txtPrecoUn;

    @FXML
    private TextField txtQuantidade;

    @FXML
    private TextField txtTipoUn;

    @FXML
    private TextField txtPrecoTotal;

    @FXML
    private TextField txtVendedor;
    
    
    
    private static Produto produtoVenda = new Produto();
    double totalVenda;
    double desconto;
    private ArrayList<Produto> ArrayProdutos = new ArrayList<>();
    
    @FXML
    void actionAdicionar(ActionEvent event) {
    	produtoVenda.setNome(txtProduto.getText());
    	produtoVenda.setEstoque(txtQuantidade.getText());
    	produtoVenda.setPrecoUnitario(txtPrecoUn.getText());
    	produtoVenda.setPrecoTotal(txtPrecoTotal.getText());
    	produtoVenda.setIdProduto(""+ ArrayProdutos.size());
    	String valor = txtPrecoTotal.getText();
    	valor = valor.replace(",",".");
    	double precoTotal = Double.parseDouble(valor);
    	
    	totalVenda = totalVenda + precoTotal;
    	valor = String.format("%.2f", totalVenda);
    	txtPrecoTotal.setText(valor);
    	
    	
    	valor = txtDesconto.getText();
    	valor = valor.replace(",",".");
    	double valordesconto = Double.parseDouble(valor);
    	desconto = desconto + valordesconto;
    	
    	
    	
    	ArrayProdutos.add(produtoVenda);
    	
    	carregarTableProdutos(ArrayProdutos);
    	

    }

    @FXML
   void actionCancelar(ActionEvent event) throws IOException {
   	
   txtCliente.setText("");
   txtCpf.setText("");
   txtVendedor.setText("");
    txtProduto.setText("");
     txtCodigo.setText("");
     txtPrecoUn.setText("");
      txtPrecoTotal.setText("");
    txtDesconto.setText("");
      txtQuantidade.setText("");        
      txtPrecoTotal.setText("");
      choiceFormaPgto.setValue(null);
    	Stage stage = (Stage) btCancelar.getScene().getWindow();
   	stage.close();
   }

    @FXML
    void actionRegistrar(ActionEvent event) {
    	Venda venda = new Venda();
    	VendaDAO vendaDAO = new VendaDAO();
    	Cliente cliente = new Cliente();
    	ClienteDAO clienteDAO = new ClienteDAO();
    	Produto produto = new Produto();
    	ProdutoDAO produtoDAO = new ProdutoDAO();
    	ProdutoVenda produtoVenda = new ProdutoVenda();
    	ProdutoVendaDAO produtoVendaDAO = new ProdutoVendaDAO();
    	ArrayList<Cliente> clientes = new ArrayList<>();
    	ArrayList<Produto> produtos = new ArrayList<>();
    	
    	cliente.setCpf(txtCpf.getText());
    	clientes = clienteDAO.search(cliente);
    	cliente = clientes.get(0);
    	
    	
    	venda.setIdfuncionario(controllerLogin.funcionario.getId());
    	venda.setIdcliente(cliente.getId());
    	venda.setFormaDePagamento(choiceFormaPgto.getValue().toString());
    	venda.setDesconto(""+ desconto);
    	venda.setPrecoTotal(txtPrecoTotal.getText());
    	vendaDAO.create(venda);
    	
    	
    	for (int i = 0; i <ArrayProdutos.size();i++) {
    		String idProduto;
    	produto = ArrayProdutos.get(i);
    	produtos = produtoDAO.search(produto);
    	produto = produtos.get(0);
    	idProduto = produto.getIdProduto();
    	produto = ArrayProdutos.get(i);
    	produto.setIdProduto(idProduto);
    	produtoVenda.setIdproduto(idProduto);
    	produtoVenda.setQuantidade(produto.getEstoque());
    	
    	produtoVenda.setIdvenda(vendaDAO.readID());
    	produtoVendaDAO.create(produtoVenda);
    	
    	
        
         txtCliente.setText(null);
         txtCpf.setText(null);
          txtProduto.setText(null);
           txtCodigo.setText(null);
           txtPrecoUn.setText(null);
           txtPrecoTotal.setText(null);
             txtDesconto.setText(null);
             txtQuantidade.setText(null);
             txtPrecoTotal.setText(null);
    }
    	ArrayProdutos = null;
    }
    
    @FXML
    void actionProdutoclick(MouseEvent event) {
        if (txtProduto.getText().length() > 3) {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produto = new Produto();
            produto.setNome(txtProduto.getText()); // Corrigido para buscar o nome correto

            ArrayList<Produto> produtos = produtoDAO.search(produto);

            if (!produtos.isEmpty()) {
                produto = produtos.get(0);
                txtCodigo.setText(produto.getCodBarra());

                String precoUn = produto.getPrecoUnitario();
                double valorUn = Double.parseDouble(precoUn);
                precoUn = String.format("%.2f", valorUn);
                txtPrecoUn.setText(precoUn);
            } else {
                System.out.println("Nenhum produto encontrado!");
                txtCodigo.setText(null);
                txtPrecoUn.setText(null);
            }
        } else {
            txtCodigo.setText(null);
            txtPrecoUn.setText(null);
        }
    }


    @FXML
    void actionProdutotype(KeyEvent event) {
    	if(txtProduto.getText().length() > 3) {
    	    ProdutoDAO produtoDAO = new ProdutoDAO();
    	    Produto produto = new Produto();
    	    produto.setNome(txtProduto.getText());
    	    ArrayList<Produto> produtos = new ArrayList<>();
    	    produtos = produtoDAO.search(produto);
    	    produto = produtos.get(0);
    	    txtCodigo.setText(produto.getCodBarra());
    	    
    	    String precoUn;
        	precoUn = produto.getPrecoUnitario();
        	double valorUn = Double.parseDouble(precoUn);
        	precoUn = String.format("%.2f", valorUn);
        	txtPrecoUn.setText(precoUn);
    	}else {
    		txtCodigo.setText(null);
    		txtPrecoUn.setText(null);
    	     
    	}
    	
    }
        

    
    
    @FXML
    void actionCPFclick(MouseEvent event) {
    	if(txtCliente.getText().length()> 3) {
        	ClienteDAO clienteDAO = new ClienteDAO();
        	Cliente cliente = new Cliente();
        	cliente.setNome(txtCliente.getText());
        	ArrayList<Cliente> clientes = new ArrayList<>();
        	clientes = clienteDAO.search(cliente);
        	cliente = clientes.get(0);
        	txtCpf.setText(cliente.getCpf());	
    }
    }

    @FXML
    void  actionCPFtype(KeyEvent event) {
    	if(txtCliente.getText().length()> 3) {
    	ClienteDAO clienteDAO = new ClienteDAO();
    	Cliente cliente = new Cliente();
    	cliente.setNome(txtCliente.getText());
    	ArrayList<Cliente> clientes = new ArrayList<>();
    	clientes = clienteDAO.search(cliente);
    	cliente = clientes.get(0);
    	txtCpf.setText(cliente.getCpf());
    	}else {
    		txtCpf.setText(null);
    }
    }
    
    
    
    @FXML
    void actionDesconto(KeyEvent event) {
    	ProdutoDAO produtoDAO = new ProdutoDAO();
    	Produto produto = new Produto();
    	produto.setNome(txtProduto.getText());
    	ArrayList<Produto> produtos = new ArrayList<>();
    	produtos = produtoDAO.search(produto);
    	produto = produtos.get(0);
    	double quantidade = Double.parseDouble(txtQuantidade.getText());
    	double precoUN = Double.parseDouble(produto.getPrecoUnitario()) ;
    	
    	if(quantidade >= 15) {
    		double desconto = (precoUN * quantidade) * 0.05;
    		double precoTotal = precoUN * quantidade - desconto;
    		txtDesconto.setText(""+ String.format("%.2f", desconto));
    		txtPrecoTotal.setText(""+ String.format("%.2f", desconto));
    	}else if(quantidade < 15) {
    		double precoTotal = precoUN * quantidade;
    		txtDesconto.setText("0,00");
    		txtPrecoTotal.setText(""+ String.format("%.2f", precoTotal));
    	
    	}
    	else{
    		txtPrecoTotal.setText(null);
    		txtDesconto.setText(null);
    		txtPrecoTotal.setText(null);
    		
    	}
    	}
   
    private void carregarTableProdutos(ArrayList<Produto> ArrayProdutos) {
    	ObservableList <Produto> produtosVendidos = FXCollections.observableArrayList(ArrayProdutos);
    	
    	columnIndice.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
    	columnProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
    	columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("estoque"));
    	columnPrecoUn.setCellValueFactory(new PropertyValueFactory<>("precoUnitario"));
    	columnPrecoTotal.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));
    	
    	tableProdutos.setItems(produtosVendidos);
    }
    
    
    public void initialize(URL arg0, ResourceBundle arg1) {
	//TODO Auto-generate method stub
    	choiceFormaPgto.getItems().add("Debito");
    	choiceFormaPgto.getItems().add("Dinheiro");
    	choiceFormaPgto.getItems().add("Pix");
    	txtVendedor.setText(controllerLogin.funcionario.getNome());
    	
    	
   
    	ProdutoDAO produtoDAO = new ProdutoDAO();
    	ArrayList<String> nomesProdutos = new ArrayList<String>();
    	nomesProdutos = produtoDAO.readProdutoByNome();
    	String[] produto = new String [nomesProdutos.size()];
    	
    	for (int i = 0; i < nomesProdutos.size(); i++) {
    		produto[i] = nomesProdutos.get(i);
    	}
    	TextFields.bindAutoCompletion(txtProduto, produto);
    	
    	
    	ClienteDAO clienteDAO = new ClienteDAO();
    	ArrayList<String> nomesClientes = new ArrayList<String>();
    	nomesClientes = clienteDAO.readClienteByNome();
    	String[] cliente = new String [nomesClientes.size()];
    	
    	for (int i = 0; i < nomesClientes.size(); i++) {
    		cliente[i] = nomesClientes.get(i);
    	}
    	TextFields.bindAutoCompletion(txtCliente, cliente);
  
}
}