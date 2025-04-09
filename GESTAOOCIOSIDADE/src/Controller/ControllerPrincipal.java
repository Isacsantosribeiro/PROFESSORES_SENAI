package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerPrincipal {

    @FXML
    private Button btInstrutor;

    @FXML
    private Button btnAdicionarTarefa;

    @FXML
    private Button btnBuscarInstrutor;

    @FXML
    private TableColumn<?, ?> colAgente;

    @FXML
    private TableColumn<?, ?> colCurso;

    @FXML
    private TableColumn<?, ?> colData;

    @FXML
    private TableColumn<?, ?> colDisponibilidade;

    @FXML
    private TableColumn<?, ?> colInstrutor;

    @FXML
    private ComboBox<?> comboAgente;

    @FXML
    private ComboBox<?> comboCurso;

    @FXML
    private ComboBox<?> comboInstrutor;

    @FXML
    private DatePicker dateFinal;

    @FXML
    private DatePicker dateInicial;

    @FXML
    private TableView<?> tabelaInstrutores;

    @FXML
    private TextField txtBuscaInstrutor;

    @FXML
    private TextField txtDescricaoTarefa;

    @FXML
    void onactionIntrutor(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ViewRegistroInstrutor.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Cadastro de Instrutor");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
