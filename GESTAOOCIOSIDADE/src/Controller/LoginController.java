package Controller;

import java.io.IOException;

import DAO.AgenteDAO;
import Model.Agente;
import Util.Alerts;
import Util.Sessao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField txtNome;
    @FXML private PasswordField txtSenha;
    @FXML private Button btnLogin;
    @FXML private Button btnRegistrar;
    @FXML private Hyperlink linkEsqueciSenha;

    @FXML
    void actionEntrar(ActionEvent event) {
        String usuario = txtNome.getText().trim();
        String senha   = txtSenha.getText().trim();

        if (usuario.isEmpty() || senha.isEmpty()) {
            Alerts.showAlert("Erro!", "Erro de login!", "Preencha as informações de login para acessar!", AlertType.ERROR);
            return;
        }

        AgenteDAO agenteDAO = new AgenteDAO();
        Agente agente = agenteDAO.autenticarUser(usuario, senha);

        if (agente == null) {
            Alerts.showAlert("Erro!", "Erro de login!", "Verifique se as informações estão corretas e tente novamente!", AlertType.ERROR);
            return;
        }

        Sessao.setAgenteLogado(agente);

        Alerts.showAlert("Login bem sucedido",
                         "Seja bem-vindo, " + agente.getNome().split("\\s+")[0] + "!",
                         "Agora verifique a ociosidade dos instrutores!",
                         AlertType.INFORMATION);

        txtNome.clear();
        txtSenha.clear();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ViewPrincipal.fxml"));
            Parent root = loader.load();

            ControllerPrincipal controller = loader.getController();
            controller.setAgenteLogado(agente); 

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Tela Principal");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            Alerts.showAlert("Erro!", "Falha ao abrir a tela principal", e.getMessage(), AlertType.ERROR);
        }
    }

    @FXML
    void actionRegistrar(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/ViewRegistroAgente.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Cadastro de Agentes");
            stage.show();

            Stage current = (Stage) btnRegistrar.getScene().getWindow();
            current.close();
        } catch (IOException e) {
            e.printStackTrace();
            Alerts.showAlert("Erro!", "Não foi possível abrir o cadastro", e.getMessage(), AlertType.ERROR);
        }
    }
}
