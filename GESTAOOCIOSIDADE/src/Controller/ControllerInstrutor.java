package Controller;

import Model.Instrutores;
import Util.Alerts;
import DAO.InstrutoresDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable; 
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory; 
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ControllerInstrutor implements Initializable {

    @FXML
    private Button btnBuscarInstrutor;

    @FXML
    void ActionSair(ActionEvent event) {
    	 try {
             Parent root = FXMLLoader.load(getClass().getResource("/View/ViewPrincipal.fxml"));
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(new Scene(root));
             stage.show();
         } catch (Exception e) {
             e.printStackTrace();
         }
    }
    @FXML
    private Button btnSair;
    
    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnExcluir;

    @FXML
    private TableColumn<Instrutores, String> colCpfInstrutor;

    @FXML
    private TableColumn<Instrutores, String> colIdInstrutor;

    @FXML
    private TableColumn<Instrutores, String> colNomeInstrutor;

    @FXML
    private TableView<Instrutores> tabelaInstrutores;

    @FXML
    private TextField txtBuscaInstrutor;
    
    @FXML
    void onactionBuscar(ActionEvent event) {
        String textoBusca = txtBuscaInstrutor.getText();

        if (textoBusca == null || textoBusca.trim().isEmpty()) {
            carregarTabelaInstrutores();
            return;
        }

        Instrutores instrutorBusca = new Instrutores();

        if (textoBusca.trim().matches("\\d{11}")) {
            instrutorBusca.setCpf(textoBusca.trim());
            instrutorBusca.setNome("");
        } else {
            instrutorBusca.setCpf(""); 
            instrutorBusca.setNome(textoBusca.trim());
        }

        ArrayList<Instrutores> instrutoresEncontrados = instrutorDAO.search(instrutorBusca);

        if (instrutoresEncontrados.isEmpty()) {
            Alerts.showAlert("Resultado", null, "Nenhum instrutor encontrado.", Alert.AlertType.INFORMATION);
        }

        tabelaInstrutores.getItems().setAll(instrutoresEncontrados);
    }



    private InstrutoresDAO instrutorDAO;
    private ObservableList<Instrutores> listaObservableDeInstrutores;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instrutorDAO = new InstrutoresDAO();
        carregarTabelaInstrutores();
    }

    public void carregarTabelaInstrutores() {
        ArrayList<Instrutores> listaDeInstrutores = instrutorDAO.read();
        listaObservableDeInstrutores = FXCollections.observableArrayList(listaDeInstrutores);

        tabelaInstrutores.setItems(listaObservableDeInstrutores);

        colIdInstrutor.setCellValueFactory(new PropertyValueFactory<>("idInstrutor"));
        colNomeInstrutor.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpfInstrutor.setCellValueFactory(new PropertyValueFactory<>("cpf"));
    }
    
    @FXML
    void ActionExcluir(ActionEvent event) {
        Instrutores instrutorSelecionado = tabelaInstrutores.getSelectionModel().getSelectedItem();

        if (instrutorSelecionado != null) {
            boolean confirmar = Alerts.showConfirmation("Confirmação", "Deseja realmente excluir o instrutor?");
            
            if (confirmar) {
                try {
                    InstrutoresDAO dao = new InstrutoresDAO();
                    dao.delete(instrutorSelecionado);
                    tabelaInstrutores.getItems().remove(instrutorSelecionado);
                    Alerts.showAlert("Sucesso", null, "Instrutor excluído com sucesso!", AlertType.INFORMATION);
                } catch (Exception e) {
                    Alerts.showAlert("Erro", null, "Erro ao excluir o instrutor: " + e.getMessage(), AlertType.ERROR);
                }
            }
        } else {
            Alerts.showAlert("Atenção", null, "Nenhum instrutor selecionado!", AlertType.WARNING);
        }
    }

    
    
    
    }

   
