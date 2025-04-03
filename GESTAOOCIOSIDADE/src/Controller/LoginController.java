package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        // Lógica de autenticação (adicione sua lógica aqui)
        String nome = txtNome.getText();
        String senha = txtSenha.getText();

        // Exemplo de autenticação simples (substitua com sua lógica real)
        if (nome.equals("isac") && senha.equals("123")) {
            // Autenticação bem-sucedida, abre a tela principal
            Parent root = FXMLLoader.load(getClass().getResource("/View/ViewPrincipal.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Tela Principal");
            stage.show();

            // Fecha a tela de login
            ((Stage) btnLogin.getScene().getWindow()).close();
        } else {
            // Autenticação falhou, exibe mensagem de erro (opcional)
            System.out.println("Credenciais inválidas");
        }
    }

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