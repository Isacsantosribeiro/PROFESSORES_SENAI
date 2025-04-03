package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class ControllerCadastroAgentes {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCPF;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnCancelar;
    
    
    
    @FXML
    void actionCancelar(ActionEvent event) {
        try {
            // Carrega o arquivo FXML da tela de login
            Parent root = FXMLLoader.load(getClass().getResource("/View/ViewLogin.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
            
            // Fecha a tela de cadastro
            ((Stage) btnCancelar.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
            // Tratar a exceção, por exemplo, exibir uma mensagem de erro
        }
    }

    @FXML
    void initialize() {
        btnSalvar.setOnAction(event -> salvarCadastro());
        btnCancelar.setOnAction(event -> actionCancelar(event)); // Chama actionCancelar ao clicar em cancelar
    }

    private void salvarCadastro() {
        String nome = txtNome.getText();
        String cpf = txtCPF.getText();
        String senha = txtSenha.getText();

        if (nome.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
            mostrarAlerta("Erro", "Preencha todos os campos!", AlertType.ERROR);
            return;
        }

        // Aqui você pode adicionar a lógica para salvar no banco de dados
        System.out.println("Usuário cadastrado: " + nome + " | CPF: " + cpf);

        mostrarAlerta("Sucesso", "Cadastro realizado com sucesso!", AlertType.INFORMATION);
        limparCampos();
    }

    private void limparCampos() {
        txtNome.clear();
        txtCPF.clear();
        txtSenha.clear();
    }

    private void mostrarAlerta(String titulo, String mensagem, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}