package Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import DAO.ClienteDAO;
import Model.Cliente;
import Model.Produto;
import Util.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class controllerCliente implements Initializable{

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btClientes;

    @FXML
    private Button btEditar;

    @FXML
    private Button btExcluir;

    @FXML
    private Button btFornecedores;

    @FXML
    private Button btFuncionarios;

    @FXML
    private Button btMain;

    @FXML
    private Button btPesquisar;

    @FXML
    private Button btProdutos;

    @FXML
    private Button btSair;

    @FXML
    private TableColumn<Cliente, String> columnCpf;

    @FXML
    private TableColumn<Cliente, String> columnDataNasc;

    @FXML
    private TableColumn<Cliente, String> columnEmail;

    @FXML
    private TableColumn<Cliente, String> columnEndereco;

    @FXML
    private TableColumn<Cliente, String> columnGenero;

    @FXML
    private TableColumn<Cliente, String> columnIndice;

    @FXML
    private TableColumn<Cliente, String > columnNome;

    @FXML
    private TableColumn<Cliente, String> columnTelefone;

    @FXML
    private TableView<Cliente> TableClientes;

    @FXML
    private TextField txtPesquisar;

    @FXML
    private Label txtUser;

    @FXML
    void Logout(ActionEvent event) {

    }

    @FXML
    void telaCliente(ActionEvent event) {
    	
    }
    
    @FXML
    void telaMain(ActionEvent event) {
    	
    }
    
    @FXML
    void telaTotalDeVendas(ActionEvent event) {
    	
    }
    
    @FXML
    void telaSair(ActionEvent event) {
    	
    }
    
    
    
    
    @FXML
    void telaVenda(ActionEvent event) {
    	
    }
    
    @FXML
    void telaFornecedor(ActionEvent event) {

    }

    @FXML
    void telaFuncionario(ActionEvent event) {

    }

    @FXML
    void telaProduto(ActionEvent event) {

    }
    
    @FXML
    void actionExcluir(ActionEvent event) {
    	
    int i =  TableClientes.getSelectionModel().getSelectedIndex();
    if(i == -1) {
    	Alerts.showAlert("Erro!", "Falha ao Excluir!", "Erro selecione um cliente para escliur!", AlertType.ERROR);
    }else {
    	Cliente cliente = new Cliente();
    	cliente = TableClientes.getItems().get(i);
    	
    	Alert confirmation = new Alert(AlertType.CONFIRMATION);
    	confirmation.setContentText("Deseja realmente excluir esse cliente?"+ cliente.getNome());
    	
    	Optional<ButtonType> resultado = confirmation.showAndWait();
    	
    	if(resultado.isPresent() && resultado.get() == ButtonType.OK) {
    	ClienteDAO clienteDAO = new ClienteDAO();
    	clienteDAO.delete(cliente);
    	
    	Alerts.showAlert("Sucesso!", "Cliente excluido!","O cliente"+ cliente.getNome() +  "O cliente foi excluido com sucesso!", AlertType.INFORMATION);
    	carregarTableClientes();
    }

    }
    }
    
    private ObservableList<Cliente> ArrayClientes;
    
    public void carregarTableClientes() {
    
    ClienteDAO clienteDAO = new ClienteDAO();
	
	ArrayClientes = FXCollections.observableList(clienteDAO.read());
	
	columnIndice.setCellValueFactory(new PropertyValueFactory<>("id"));
	columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
	columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
	columnDataNasc.setCellValueFactory(new PropertyValueFactory<>("dataNasc"));
	columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
	columnGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
	columnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
	columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
	TableClientes.setItems(ArrayClientes);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		carregarTableClientes();
		
	}


}
