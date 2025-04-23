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
    private ComboBox<Agente> comboAgente; // Alterado para ComboBox<Agente>

    @FXML
    private ComboBox<Curso> comboCurso; // Alterado para ComboBox<Curso>

    @FXML
    private ComboBox<Instrutores> comboInstrutor; // Alterado para ComboBox<Instrutores>

    @FXML
    private DatePicker dateFinal;

    @FXML
    private DatePicker dateInicial;

    @FXML
    private TextField txtBuscaInstrutor;

    @FXML
    private TextField txtDescricaoTarefa;

    private TarefaDAO tarefaDAO;
    private ObservableList<Tarefa> listaDeTarefas;

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
                return null; // Não precisamos da conversão inversa aqui
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

        tarefaDAO = new TarefaDAO();
        carregarTarefas();
        configurarTabela();
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

        tabelaInstrutores.setItems(listaDeTarefas);
    }

    @FXML
    void ActionAdicionarTarefa(ActionEvent event) {
        Instrutores instrutorSelecionado = comboInstrutor.getValue();
        Agente agenteSelecionado = comboAgente.getValue();
        Curso cursoSelecionado = comboCurso.getValue();
        LocalDate dataIni = dateInicial.getValue();
        LocalDate dataFim = dateFinal.getValue();
        String descricao = txtDescricaoTarefa.getText();

        if (instrutorSelecionado == null || agenteSelecionado == null || cursoSelecionado == null || dataIni == null || dataFim == null || descricao.isEmpty()) {
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
        novaTarefa.setDescricao(descricao);

        tarefaDAO.adicionarTarefa(novaTarefa);

        // Recarrega as tarefas do banco
        List<Tarefa> novasTarefas = tarefaDAO.listarTarefasComDisponibilidade();
        // Limpa a lista existente e adiciona todos os novos itens
        listaDeTarefas.clear();
        listaDeTarefas.addAll(novasTarefas);
 tabelaInstrutores.refresh();

        comboInstrutor.setValue(null);
        comboAgente.setValue(null);
        comboCurso.setValue(null);
        dateInicial.setValue(null);
        dateFinal.setValue(null);
        txtDescricaoTarefa.clear();
    }
    @FXML
    void onactionConsultarCurso(ActionEvent event) {
        javafx.stage.Stage stageAtual = (javafx.stage.Stage) btnBuscarInstrutor.getScene().getWindow();
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
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText("Erro ao abrir a tela de consulta de cursos");
            alerta.setContentText(e.getMessage());
            alerta.show();
        }
    }

    @FXML
    void onactionConsultarInstrutor(ActionEvent event) {
        javafx.stage.Stage stageAtual = (javafx.stage.Stage) btnBuscarInstrutor.getScene().getWindow();
        stageAtual.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ViewInstrutor.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Consulta de Cursos");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText("Erro ao abrir a tela de consulta de cursos");
            alerta.setContentText(e.getMessage());
            alerta.show();
        }
    }


    @FXML
    void onactionInstrutor(ActionEvent event) {
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
}