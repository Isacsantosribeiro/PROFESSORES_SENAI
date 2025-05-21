package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import DAO.AgenteDAO;
import Model.Agente;
import Util.Alerts; 

public class ControllerAutenticacaoAgente {

    @FXML
    private PasswordField txtSenhaAgente;

    @FXML
    private Button btnConfirmar;

    @FXML
    private Button btnCancelar;

    private int idTarefaParaExcluir;
    private int idAgenteAutenticado;
    private ControllerPrincipal controllerPrincipal;
    private AgenteDAO agenteDAO = new AgenteDAO();

    public void setDadosExclusao(int idTarefa, int idAgente, ControllerPrincipal principalController) {
        this.idTarefaParaExcluir = idTarefa;
        this.idAgenteAutenticado = idAgente;
        this.controllerPrincipal = principalController;
    }

    @FXML
    void actionConfirmar(ActionEvent event) {
        String senhaDigitada = txtSenhaAgente.getText();
        Agente agente = agenteDAO.buscarAgentePorId(String.valueOf(idAgenteAutenticado));

        if (agente != null && agente.getSenha().equals(senhaDigitada)) {
            controllerPrincipal.confirmarExclusao(idTarefaParaExcluir);
            Stage stage = (Stage) btnConfirmar.getScene().getWindow();
            stage.close();
        } else {
            Alerts.showAlert("Erro de Autenticação", null, "Senha incorreta!", javafx.scene.control.Alert.AlertType.ERROR);
            txtSenhaAgente.clear();
        }
    }

    @FXML
    void actionCancelar(ActionEvent event) {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }
}