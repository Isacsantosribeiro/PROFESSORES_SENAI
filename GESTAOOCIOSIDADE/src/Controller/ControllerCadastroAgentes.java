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
import Model.Agente;
import DAO.AgenteDAO;
import Util.Alerts;
import Util.CPFValidator;

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

    private final AgenteDAO agenteDAO = new AgenteDAO();
    private final CPFValidator cpfValidator = new CPFValidator();

    @FXML
    void actionCancelar(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/ViewLogin.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();

            ((Stage) btnCancelar.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
            Alerts.showAlert("Erro!", "Erro ao carregar tela!", "Não foi possível carregar a tela de login!", AlertType.ERROR);
        }
    }

    @FXML
    void actionCadastrar(ActionEvent event) {
        String nome = txtNome.getText();
        String cpf = txtCPF.getText();
        String senha = txtSenha.getText();

        if (nome.isEmpty()) {
            Alerts.showAlert("Erro", "Nome incompleto!", "Por favor, preencha o nome do agente!", AlertType.ERROR);
            return;
        }

        if (cpf.isEmpty() || !CPFValidator.validarCPF(cpf)) {
            Alerts.showAlert("Erro", "CPF inválido!", "Verifique o CPF e tente novamente!", AlertType.ERROR);
            return;
        }

        if (senha.isEmpty()) {
            Alerts.showAlert("Erro", "Senha incompleta!", "Por favor, preencha a senha do agente!", AlertType.ERROR);
            return;
        }

        if (agenteDAO.cpfExiste(cpf)) {
            Alerts.showAlert("Erro", "CPF já cadastrado!", "Este CPF já está registrado no sistema.", AlertType.ERROR);
            return;
        }

        Agente agente = new Agente();
        agente.setNome(nome);
        agente.setCpf(cpf);
        agente.setSenha(senha);

        try {
            agenteDAO.create(agente);
            Alerts.showAlert("Sucesso!", "Agente cadastrado!", "O agente foi cadastrado com sucesso!", AlertType.INFORMATION);
            limparCampos();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/View/ViewLogin.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Tela de Login");
                stage.show();
                ((Stage) btnSalvar.getScene().getWindow()).close();
            } catch (IOException e) {
                e.printStackTrace();
                Alerts.showAlert("Erro!", "Erro ao carregar tela!", "Não foi possível carregar a tela de login!", AlertType.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alerts.showAlert("Erro!", "Erro ao cadastrar agente!", "Ocorreu um erro ao salvar o agente no banco de dados.", AlertType.ERROR);
        }
    }


    @FXML
    void initialize() {
        btnCancelar.setOnAction(this::actionCancelar);
    }

    private void limparCampos() {
        txtNome.clear();
        txtCPF.clear();
        txtSenha.clear();
    }

    @SuppressWarnings("unused")
	private void mostrarAlerta(String titulo, String mensagem, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}