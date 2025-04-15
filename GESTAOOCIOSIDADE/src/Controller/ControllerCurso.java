package Controller;

import Model.Curso;
import DAO.CursoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerCurso implements Initializable {

    @FXML
    private Button btnBuscarCurso;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnExcluir;

    @FXML
    private TableColumn<Curso, String> colIdCurso;

    @FXML
    private TableColumn<Curso, String> colNomeCurso;

    @FXML
    private TableView<Curso> tabelaCursos;

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

        colIdCurso.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNomeCurso.setCellValueFactory(new PropertyValueFactory<>("nome"));
    }
}