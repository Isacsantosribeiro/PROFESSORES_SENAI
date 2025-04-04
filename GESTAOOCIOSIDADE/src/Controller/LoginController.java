package Controller;

import java.io.IOException;

import DAO.AgenteDAO;
import Model.Agente;
import Util.Alerts;
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

	// Remova estas declarações, pois você já tem txtNome e txtSenha
	//@FXML
	//private TextField txtUser;
	//@FXML
	//private PasswordField txtPassword;
	//@FXML
	//private Button btnEntrar;

	@FXML
	void actionEntrar(ActionEvent event) throws IOException {
	    AgenteDAO agenteDAO = new AgenteDAO();
	    Agente agente = agenteDAO.autenticarUser(txtNome.getText(), txtSenha.getText());

	    if (txtNome.getText().isEmpty() || txtSenha.getText().isEmpty()) {
	        Alerts.showAlert("Erro!", "Erro de login!", "Preencha as informações de login para acessar!", AlertType.ERROR);
	    } else if (agente == null || agente.getCpf() == null) {
	        Alerts.showAlert("Erro!", "Erro de login!", "Verifique se as informações estão corretas e tente novamente!", AlertType.ERROR);
	    } else if (agente.getCpf().equals(txtNome.getText()) && agente.getSenha().equals(txtSenha.getText())) {
	        Alerts.showAlert("Login bem sucedido", "Seja bem vindo " + agente.getNome(), "Agora que acessou vá trabalhar!", AlertType.INFORMATION);
	        txtNome.setText("");
	        txtSenha.setText("");
	        Parent root = FXMLLoader.load(getClass().getResource("/View/ViewPrincipal.fxml"));
	        Scene scene = new Scene(root);
	        Stage stage = new Stage();
	        stage.setScene(scene);
	        stage.setTitle("Tela Principal");
	        stage.show();
	        ((Stage) btnLogin.getScene().getWindow()).close();
	    } else {
	        Alerts.showAlert("Erro!", "Erro de login!", "Falha ao autenticar. Verifique seu usuário e senha.", AlertType.ERROR);
	    }
	}

	// ... (o restante do seu LoginController)

    @FXML
    public void initialize() {
        btnLogin.setOnAction(event -> {
            System.out.println("Tentativa de login: " + txtNome.getText());
           
        });

        btnRegistrar.setOnAction(event -> {
            try {
                actionRegistrar(new ActionEvent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        linkEsqueciSenha.setOnAction(event -> {
            System.out.println("Redirecionando para recuperação de senha...");
        });
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