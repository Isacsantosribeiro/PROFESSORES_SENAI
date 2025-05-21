package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import Model.Tarefa;

public class ControllerDetalheTarefa implements Initializable {

    @FXML private Label lblInstrutor;
    @FXML private Label lblCurso;
    @FXML private Label lblAgente;
    @FXML private Label lblTurno;
    @FXML private Label lblDataInicial;
    @FXML private Label lblDataFinal;
    @FXML private Label lblDisponibilidade;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setTarefa(Tarefa tarefa) {
        lblInstrutor.setText(tarefa.getNomeInstrutor());
        lblCurso.setText(tarefa.getNomeCurso());
        lblAgente.setText(tarefa.getNomeAgente());
        lblTurno.setText(tarefa.getTurno());
        lblDataInicial.setText(tarefa.getDataInicial()); 
        lblDataFinal.setText(tarefa.getDataFinal());     
        lblDisponibilidade.setText(tarefa.getDisponibilidade());
    }
}