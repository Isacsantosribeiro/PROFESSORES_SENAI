package Controller;

import DAO.CursoDAO;
import Model.Curso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;

public class ControllerCadastroCurso {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField txtNome;

    @FXML
    void actionCadastrar(ActionEvent event) {
        String nomeCurso = txtNome.getText().trim();

        if (nomeCurso.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro de Validação");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, insira o nome do curso.");
            alert.showAndWait();
            return;
        }

        try {
            CursoDAO dao = new CursoDAO();

            if (dao.cursoExiste(nomeCurso)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Curso já existe");
                alert.setHeaderText(null);
                alert.setContentText("Curso já Cadastrado!!.");
                alert.showAndWait();
                return;
            }

            Curso curso = new Curso();
            curso.setNome(nomeCurso);
            dao.inserirCurso(curso);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Curso cadastrado com sucesso!");
            alert.showAndWait();

            txtNome.clear();

        } catch (RuntimeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao cadastrar curso: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    void actionCancelar(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/ViewPrincipal.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
