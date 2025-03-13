package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import DAO.ClienteDAO;
import DAO.ProdutoDAO;
import Model.Cliente;
import Model.Produto;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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
    private TextField txtTotalDaCompra;

    @FXML
    private TextField txtVendedor;

    @FXML
    void actionAdicionar(ActionEvent event) {

    }

    @FXML
    void actionCancelar(ActionEvent event) {

    }

    @FXML
    void actionRegistrar(ActionEvent event) {

    }
    
    @FXML
    void actionProdutoclick(MouseEvent event) {
    	if(txtProduto.getText().length()> 3) {
        	ProdutoDAO produtoDAO = new ProdutoDAO();
        	Produto produto = new Produto();
        	produto.setNome(txtCliente.getText());
        	ArrayList<Produto> produtos = new ArrayList<>();
        	produtos = produtoDAO.search(produto);
        	produto = produtos.get(0);
        	txtCodigo.setText(produto.getCodBarra());
        	
        	String precoUn;
        	precoUn = produto.getPrecoUnitario();
        	double valorUn = Double.parseDouble(precoUn);
        	precoUn = String.format("%.2f", valorUn);
        	txtPrecoUn.setText("R$" + precoUn);
    	}else {
    		txtCodigo.setText(null);
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
        	txtPrecoUn.setText("R$" + precoUn);
    	}else {
    		txtCodigo.setText(null);
    	     
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