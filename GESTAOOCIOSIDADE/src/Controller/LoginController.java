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
    public void initialize() {
        btnLogin.setOnAction(event -> {
            System.out.println("Tentativa de login: " + txtNome.getText());
            // Adicionar lógica de autenticação
        });

        btnRegistrar.setOnAction(event -> {
            try {
                actionRegistrar(new ActionEvent()); // Chama o método para abrir a tela de cadastro
            } catch (IOException e) {
                e.printStackTrace();
                // Tratar a exceção, por exemplo, exibir uma mensagem de erro
            }
        });

        linkEsqueciSenha.setOnAction(event -> {
            System.out.println("Redirecionando para recuperação de senha...");
            // Adicionar lógica para abrir uma nova tela de recuperação de senha
        });
    }
    @FXML
    void actionRegistrar(ActionEvent event)throws IOException {
    	
        // Carrega o arquivo FXML da tela de cadastro de agentes
        Parent root = FXMLLoader.load(getClass().getResource("/View/ViewRegistroAgente.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Cadastro de Agentes");
        stage.show();
        
        // Opcional: Fechar a tela de login
        // ((Stage) btnRegistrar.getScene().getWindow()).close();
    }
}