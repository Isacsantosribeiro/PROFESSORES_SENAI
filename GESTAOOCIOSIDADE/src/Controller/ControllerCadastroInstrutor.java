package Controller;

import DAO.InstrutoresDAO;
import Model.Instrutores;
import Util.Alerts;
import Util.CPFValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControllerCadastroInstrutor {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtNome;

    @FXML
    void actionCadastrar(ActionEvent event) {
        String nome = txtNome.getText().trim();
        String cpf = txtCPF.getText().trim();

        if (nome.isEmpty()) {
            Alerts.showAlert("Erro", "Nome incompleto!", "Por favor, preencha o nome do instrutor!", AlertType.ERROR);
            return;
        }

        if (cpf.isEmpty() || !CPFValidator.validarCPF(cpf)) {
            Alerts.showAlert("Erro", "CPF inválido!", "Verifique o CPF e tente novamente!", AlertType.ERROR);
            return;
        }

       
        Instrutores instrutor = new Instrutores();
        instrutor.setNome(nome);
        instrutor.setCpf(cpf);

        
        InstrutoresDAO dao = new InstrutoresDAO();
        boolean sucesso = dao.inserirInstrutor(instrutor);

        if (sucesso) {
            Alerts.showAlert("Sucesso", null, "Instrutor cadastrado com sucesso!", AlertType.INFORMATION);
        
            txtNome.clear();
            txtCPF.clear();
        } else {
            Alerts.showAlert("Erro", null, "Falha ao cadastrar o instrutor!", AlertType.ERROR);
        }
    }

    @FXML
    void actionCancelar(ActionEvent event) {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/View/ViewPrincipal.fxml"));
            javafx.scene.Parent root = loader.load();

            javafx.scene.Scene scene = new javafx.scene.Scene(root);
            javafx.stage.Stage stage = (javafx.stage.Stage) btnCancelar.getScene().getWindow();

            stage.setScene(scene);
            stage.setTitle("Tela Principal");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            Alerts.showAlert("Erro", "Erro ao voltar", "Não foi possível voltar à tela principal!", AlertType.ERROR);
        }
    }
}

