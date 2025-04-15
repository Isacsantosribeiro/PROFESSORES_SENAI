package Controller;

import Model.Instrutores;
import DAO.InstrutoresDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable; // Importe Initializable
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory; // Importe PropertyValueFactory

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.List; // Importe List

public class ControllerInstrutor implements Initializable {

    @FXML
    private Button btnBuscarInstrutor;

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

        colIdInstrutor.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNomeInstrutor.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpfInstrutor.setCellValueFactory(new PropertyValueFactory<>("cpf"));
    }

   
}