package Controller;

import java.io.IOException;

import DAO.AgenteDAO;
import Model.Agente;
import Util.Alerts;
import application.Main; // Importe a classe Main
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private TextField txtNome;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private Button btnLogin;

	@FXML
	private Button btnRegistrar;

	@FXML
	private Hyperlink linkEsqueciSenha;

	@FXML
	void actionEntrar(ActionEvent event) throws IOException {
	    AgenteDAO agenteDAO = new AgenteDAO();
	    Agente agente = agenteDAO.autenticarUser(txtNome.getText(), txtSenha.getText());

	    if (txtNome.getText().equals("") || txtSenha.getText().equals("")) {
	        Alerts.showAlert("Erro!", "Erro de login!", "Preencha as informações de login para acessar!", AlertType.ERROR);
	    } else if (agente == null) { 
	        Alerts.showAlert("Erro!", "Erro de login!", "Verifique se as informações estão corretas e tente novamente!", AlertType.ERROR);
	    } else { 
	        Alerts.showAlert("Login bem sucedido", "Seja bem vindo " + agente.getNome(), "Agora verifique a ociosidade dos instrutores!", AlertType.INFORMATION);
	        txtNome.setText("");
	        txtSenha.setText("");
	        Main.TelaHome();
	        ((javafx.scene.Node) event.getSource()).getScene().getWindow().hide();
	    }
	}

    @FXML
    void actionRegistrar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/ViewRegistroAgente.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Cadastro de Agentes");
        stage.show();

        ((Stage) btnRegistrar.getScene().getWindow()).close();
    }
}