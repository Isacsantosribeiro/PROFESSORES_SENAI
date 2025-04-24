package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import ConnectionFactory.ConnectionDatabase;
import DAO.AgenteDAO;
import DAO.CursoDAO;
import DAO.InstrutoresDAO;
import DAO.TarefaDAO;
import Model.Agente;
import Model.Curso;
import Model.Instrutores;
import Model.Tarefa;
import Util.Alerts; // Importe a classe Alerts

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class ControllerPrincipal implements Initializable {

    @FXML
    private Button btInstrutor;

    @FXML
    private Button btnAdicionarTarefa;

    @FXML
    private Button btnBuscarTarefa;
    
    @FXML
    private Button btnBuscarInstrutor;

    @FXML
    private TableView<Tarefa> tabelaInstrutores;

    @FXML
    private TableColumn<Tarefa, String> colAgente;

    @FXML
    private TableColumn<Tarefa, String> colCurso;

    @FXML
    private TableColumn<Tarefa, String> colData;

    @FXML
    private TableColumn<Tarefa, String> colDisponibilidade;

    @FXML
    private TableColumn<Tarefa, String> colInstrutor;

    @FXML
    private TableColumn<Tarefa, String> colDataFinal;

    @FXML
    private ComboBox<Agente> comboAgente;

    @FXML
    private ComboBox<Curso> comboCurso;

    @FXML
    private ComboBox<Instrutores> comboInstrutor;

    @FXML
    private DatePicker dateFinal;

    @FXML
    private DatePicker dateInicial;

    @FXML
    private TextField txtBuscaInstrutor;

    @FXML
    private ComboBox<String> comboTurno;

    @FXML
    private TableColumn<Tarefa, String> colTurno;

    @FXML
    private Button btnExcluirTarefa; 

    private TarefaDAO tarefaDAO;
    private ObservableList<Tarefa> listaDeTarefas;
    private Agente agenteLogado; 

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        InstrutoresDAO instrutoresDAO = new InstrutoresDAO();
        ObservableList<Instrutores> listaInstrutores = instrutoresDAO.buscarInstrutoresDoBanco();
        comboInstrutor.setItems(listaInstrutores);
        comboInstrutor.setConverter(new StringConverter<Instrutores>() {
            @Override
            public String toString(Instrutores instrutor) {
                return (instrutor != null) ? instrutor.getNome() : "";
            }

            @Override
            public Instrutores fromString(String string) {
                return null; 
            }
        });

        AgenteDAO agenteDAO = new AgenteDAO();
        ObservableList<Agente> listaAgentes = agenteDAO.buscarAgenteDoBanco();
        comboAgente.setItems(listaAgentes);
        comboAgente.setConverter(new StringConverter<Agente>() {
            @Override
            public String toString(Agente agente) {
                return (agente != null) ? agente.getNome() : "";
            }

            @Override
            public Agente fromString(String string) {
                return null;
            }
        });

        CursoDAO cursoDAO = new CursoDAO();
        ObservableList<Curso> listaCursos = cursoDAO.buscarCursosDoBanco();
        comboCurso.setItems(listaCursos);
        comboCurso.setConverter(new StringConverter<Curso>() {
            @Override
            public String toString(Curso curso) {
                return (curso != null) ? curso.getNome() : "";
            }

            @Override
            public Curso fromString(String string) {
                return null;
            }
        });

        ObservableList<String> turnos = FXCollections.observableArrayList("Matutino", "Vespertino", "Noturno");
        comboTurno.setItems(turnos);

        tarefaDAO = new TarefaDAO();
        carregarTarefas();
        configurarTabela();
        btnExcluirTarefa.setOnAction(this::ActionExcluirTarefa);
    }

    private void carregarTarefas() {
        List<Tarefa> tarefas = tarefaDAO.listarTarefasComDisponibilidade();
        if (listaDeTarefas == null) {
            listaDeTarefas = FXCollections.observableArrayList();
        } else {
            listaDeTarefas.clear();
        }
        listaDeTarefas.addAll(tarefas);
    }

    private void configurarTabela() {
        colInstrutor.setCellValueFactory(new PropertyValueFactory<>("nomeInstrutor"));
        colAgente.setCellValueFactory(new PropertyValueFactory<>("nomeAgente"));
        colCurso.setCellValueFactory(new PropertyValueFactory<>("nomeCurso"));
        colData.setCellValueFactory(new PropertyValueFactory<>("dataInicial"));
        colDataFinal.setCellValueFactory(new PropertyValueFactory<>("dataFinal"));
        colDisponibilidade.setCellValueFactory(new PropertyValueFactory<>("disponibilidade"));
        colTurno.setCellValueFactory(new PropertyValueFactory<>("turno"));

        tabelaInstrutores.setItems(listaDeTarefas);
    }
    
    @FXML
    void ActionBuscarTarefa(ActionEvent event) {
    	   String termoBusca = txtBuscaInstrutor.getText();
    	    TarefaDAO tarefaDAO = new TarefaDAO();
    	    List<Tarefa> resultados = tarefaDAO.buscarTarefasPorInstrutor(termoBusca);
    	    listaDeTarefas.clear(); 
    	    listaDeTarefas.addAll(resultados); 
    	    tabelaInstrutores.refresh(); 
    	

    }


    @FXML
    void ActionExcluirTarefa(ActionEvent event) {
        Tarefa tarefaSelecionada = tabelaInstrutores.getSelectionModel().getSelectedItem();
        if (tarefaSelecionada != null) {
            abrirTelaAutenticacao(tarefaSelecionada.getIdTarefa(), 1); 
        } else {
            mostrarAlerta("Atenção", "Selecione uma tarefa para excluir.");
        }
    }

    private void abrirTelaAutenticacao(int idTarefaParaExcluir, int idAgenteAutenticado) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/TelaAutenticacaoAgente.fxml"));
            Parent root = loader.load();
            ControllerAutenticacaoAgente controller = loader.getController();
            controller.setDadosExclusao(idTarefaParaExcluir, idAgenteAutenticado, this);
            Stage stage = new Stage();
            stage.setTitle("Autenticação do Agente");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Erro", "Não foi possível abrir a tela de autenticação.");
        }
    }

    public void confirmarExclusao(int idTarefa) {
        if (Alerts.showConfirmacaoExcluirTarefa("a tarefa")) {
            if (tarefaDAO.excluirTarefa(idTarefa)) {
                Alerts.showTarefaExcluidaSucesso();
                listaDeTarefas.removeIf(tarefa -> tarefa.getIdTarefa() == idTarefa);
                tabelaInstrutores.refresh();
            } else {
                Alerts.showAlert("Erro", null, "Falha ao excluir a tarefa.", AlertType.ERROR);
            }
        }
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }


    @FXML
    void ActionAdicionarTarefa(ActionEvent event) {
        Instrutores instrutorSelecionado = comboInstrutor.getValue();
        Agente agenteSelecionado = comboAgente.getValue();
        Curso cursoSelecionado = comboCurso.getValue();
        LocalDate dataIni = dateInicial.getValue();
        LocalDate dataFim = dateFinal.getValue();
        String turnoSelecionado = comboTurno.getValue();

        if (instrutorSelecionado == null || agenteSelecionado == null || cursoSelecionado == null || dataIni == null || dataFim == null || turnoSelecionado == null) {
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("Atenção");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, preencha todos os campos para adicionar a tarefa.");
            alerta.showAndWait();
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataInicialFormatada = dataIni.format(formatter);
        String dataFinalFormatada = dataFim.format(formatter);

        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setCodInstrutor(String.valueOf(instrutorSelecionado.getIdInstrutor()));
        novaTarefa.setCodAgente(String.valueOf(agenteSelecionado.getIdAgente()));
        novaTarefa.setCodCurso(String.valueOf(cursoSelecionado.getIdCurso()));
        novaTarefa.setDataInicial(dataInicialFormatada);
        novaTarefa.setDataFinal(dataFinalFormatada);
        novaTarefa.setTurno(turnoSelecionado);

        tarefaDAO.adicionarTarefa(novaTarefa);

        List<Tarefa> novasTarefas = tarefaDAO.listarTarefasComDisponibilidade();
        listaDeTarefas.clear();
        listaDeTarefas.addAll(novasTarefas);
        tabelaInstrutores.refresh();

        comboInstrutor.setValue(null);
        comboAgente.setValue(null);
        comboCurso.setValue(null);
        comboTurno.setValue(null);
        dateInicial.setValue(null);
        dateFinal.setValue(null);
    }

    @FXML
    void onactionConsultarCurso(ActionEvent event) {
        Stage stageAtual = (Stage) btnBuscarInstrutor.getScene().getWindow();
        stageAtual.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ViewCurso.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Consulta de Cursos");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alerts.showAlert("Erro", "Erro ao abrir a tela de consulta de cursos", e.getMessage(), AlertType.ERROR);
        }
    }

    @FXML
    void onactionConsultarInstrutor(ActionEvent event) {
        Stage stageAtual = (Stage) btnBuscarInstrutor.getScene().getWindow();
        stageAtual.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ViewInstrutor.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Consulta de Instrutores");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alerts.showAlert("Erro", "Erro ao abrir a tela de consulta de instrutores", e.getMessage(), AlertType.ERROR);
        }
    }


    @FXML
    void onactionInstrutor(ActionEvent event) {
        Stage stageAtual = (Stage) btInstrutor.getScene().getWindow();
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
            Alerts.showAlert("Erro", "Erro ao abrir a tela de cadastro de instrutor", e.getMessage(), AlertType.ERROR);
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
            Alerts.showAlert("Erro", "Erro ao abrir a tela de cadastro de curso", e.getMessage(), AlertType.ERROR);
        }
    }
}