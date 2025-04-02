package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    public void initialize() {
        btnLogin.setOnAction(event -> {
            System.out.println("Tentativa de login: " + txtNome.getText());
            // Adicionar lógica de autenticação
        });

        btnRegistrar.setOnAction(event -> {
            System.out.println("Abrindo tela de registro...");
            // Redirecionar para tela de cadastro
        });

        linkEsqueciSenha.setOnAction(event -> {
            System.out.println("Redirecionando para recuperação de senha...");
            // Adicionar lógica para abrir uma nova tela de recuperação de senha
        });
    }
}
