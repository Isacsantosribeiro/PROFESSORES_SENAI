package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import ConnectionFactory.ConnectionDatabase;
import DAO.AgenteDAO;
import DAO.CursoDAO;
import DAO.InstrutoresDAO;
import Model.Instrutores;
import Model.Agente;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;

import javafx.scene.Node;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;




public class ControllerPrincipal implements Initializable{

	
    @FXML
    private Button btInstrutor;

    @FXML
    private Button btnAdicionarTarefa;

    @FXML
    private Button btnBuscarInstrutor;

    @FXML
    private TableColumn<String,String> colAgente;

    @FXML
    private TableColumn<String,String> colCurso;

    @FXML
    private TableColumn<String,String> colData;

    @FXML
    private TableColumn<String,String> colDisponibilidade;

    @FXML
    private TableColumn<String,String> colInstrutor;

    @FXML
    private ComboBox<String> comboAgente;

    @FXML
    private ComboBox<String> comboCurso;

    @FXML
    private ComboBox<String> comboInstrutor;

    @FXML
    private DatePicker dateFinal;

    @FXML
    private DatePicker dateInicial;

    @FXML
    private TableView<?> tabelaInstrutores;

    @FXML
    private TextField txtBuscaInstrutor;

    @FXML
    private TextField txtDescricaoTarefa;
    
    @FXML
    void onactionConsultarCurso(ActionEvent event) {
    	
    	    try {
    	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ViewCurso.fxml"));
    	        Parent root = loader.load();

    	        Stage stage = new Stage();
    	        stage.setTitle("Consulta de Cursos");
    	        stage.setScene(new Scene(root));
    	        stage.show();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    }
    	}

    @FXML
    void onactionConsultarInstrutor(ActionEvent event) {

    }

    
    @FXML
    void onactionIntrutor(ActionEvent event) {
    	   javafx.stage.Stage stageAtual = (javafx.stage.Stage) btInstrutor.getScene().getWindow();
           stageAtual.close();

           
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ViewRegistroInstrutor.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Cadastro de Instrutor");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        
    }
    }
    @FXML
    void onactionCurso(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ViewRegistroCurso.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow(); 
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText("Erro ao abrir a tela de cadastro de curso");
            alerta.setContentText(e.getMessage());
            alerta.show();
        }
    }



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		        InstrutoresDAO instrutores = new InstrutoresDAO();
		        comboInstrutor.setItems(instrutores.buscarInstrutoresDoBanco());
		        
		        AgenteDAO Agente = new AgenteDAO();
		        comboAgente.setItems(Agente.buscarAgenteDoBanco());
		    
		        CursoDAO Curso = new CursoDAO();
		        comboCurso.setItems(Curso.buscarCursosDoBanco());
		        
	}
    
  
    




}
