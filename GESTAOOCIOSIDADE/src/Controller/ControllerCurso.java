package Controller;
import Model.Curso;
import Util.Alerts;
import DAO.CursoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerCurso implements Initializable {

    @FXML
    private Button btnSair;
    @FXML
    private Button btnBuscarCurso;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnExcluir;

    @FXML
    private TableColumn<Curso, Integer> colIdCurso;

    @FXML
    private TableColumn<Curso, String> colNomeCurso;

    @FXML
    private TableView<Curso> tabelaCursos;

    @FXML
    void onactionSair(ActionEvent event) {
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
    void onactionBuscar(ActionEvent event) {
        String textoBusca = txtBuscaCurso.getText();

        if (textoBusca == null || textoBusca.trim().isEmpty()) {
            carregarTableCursos();
            return;
        }

        CursoDAO cursoDAO = new CursoDAO();
        Curso cursoBusca = new Curso();

        try {
            int id = Integer.parseInt(textoBusca.trim());
            cursoBusca.setIdCurso(id);
            cursoBusca.setNome("");
        } catch (NumberFormatException e) {
            cursoBusca.setIdCurso(0);
            cursoBusca.setNome(textoBusca.trim());
        }

        ArrayList<Curso> resultados = cursoDAO.search(cursoBusca);

        if (resultados.isEmpty()) {
            Alerts.showAlert("Resultado", null, "Nenhum curso encontrado.", Alert.AlertType.INFORMATION);
        }

        tabelaCursos.getItems().setAll(resultados);
    }


    @FXML
    void onactionExcluir(ActionEvent event) {
        Curso cursoSelecionado = tabelaCursos.getSelectionModel().getSelectedItem();

        if (cursoSelecionado != null) {
            boolean confirmar = Alerts.showConfirmation("Confirmação", "Deseja realmente excluir o curso?");

            if (confirmar) {
                try {
                    CursoDAO dao = new CursoDAO();
                    dao.delete(cursoSelecionado); 
                    tabelaCursos.getItems().remove(cursoSelecionado);
                    Alerts.showAlert("Sucesso", null, "Curso excluído com sucesso!", AlertType.INFORMATION);
                } catch (Exception e) {
                    Alerts.showAlert("Erro", null, "Curso vinculado a uma tarefa! " + e.getMessage(), AlertType.ERROR);
                }
            }
        } else {
            Alerts.showAlert("Atenção", null, "Nenhum curso selecionado!", AlertType.WARNING);
        }
    }

    @FXML
    private TextField txtBuscaCurso;

    private ObservableList<Curso> arrayCursos;
    private CursoDAO cursoDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cursoDAO = new CursoDAO();
        carregarTableCursos();
    }

    public void carregarTableCursos() {
        ArrayList<Curso> listaCursos = cursoDAO.read();
        arrayCursos = FXCollections.observableList(listaCursos);

        tabelaCursos.setItems(arrayCursos);

        colIdCurso.setCellValueFactory(new PropertyValueFactory<>("idCurso")); 
        colNomeCurso.setCellValueFactory(new PropertyValueFactory<>("nome"));
    }
}