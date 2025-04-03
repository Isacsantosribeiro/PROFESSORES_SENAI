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
import Model.Agente; // Supondo que você tenha uma classe Agente em Model
import DAO.AgenteDAO; // Supondo que você tenha um AgenteDAO em DAO

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
    void actionCadastrar(ActionEvent event) {

    }

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

        // Cria um objeto Agente com os dados do formulário
        Agente agente = new Agente();
        agente.setNome(nome);
        agente.setCpf(cpf);
        agente.setSenha(senha);

        // Cria uma instância de AgenteDAO
        AgenteDAO agenteDAO = new AgenteDAO();

        // Salva o agente no banco de dados
        boolean cadastroSucesso = agenteDAO.inserirAgente(agente); // Supondo que você tenha um método inserirAgente no AgenteDAO

        if (cadastroSucesso) {
            mostrarAlerta("Sucesso", "Cadastro realizado com sucesso!", AlertType.INFORMATION);
            limparCampos();

            // Carrega e exibe a tela principal
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/View/ViewPrincipal.fxml")); // Certifique-se de que o caminho está correto
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Tela Principal");
                stage.show();

                // Fecha a tela de cadastro
                ((Stage) btnSalvar.getScene().getWindow()).close();
            } catch (IOException e) {
                e.printStackTrace();
                // Tratar a exceção, por exemplo, exibir uma mensagem de erro
            }

        } else {
            mostrarAlerta("Erro", "Erro ao cadastrar o agente.", AlertType.ERROR);
        }
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