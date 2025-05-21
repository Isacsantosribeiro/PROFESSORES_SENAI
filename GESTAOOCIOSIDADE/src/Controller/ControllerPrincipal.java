package Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import DAO.AgenteDAO;
import DAO.CursoDAO;
import DAO.InstrutoresDAO;
import DAO.TarefaDAO;
import Model.Agente;
import Model.Curso;
import Model.Instrutores;
import Model.Tarefa;
import Util.Alerts;

public class ControllerPrincipal implements Initializable {

    @FXML private Button btInstrutor;
    @FXML private Button btnAdicionarTarefa;
    @FXML private Button btnBuscarTarefa;
    @FXML private Button btnBuscarInstrutor;
    @FXML private Button btnExcluirTarefa;

    @FXML private TableView<Tarefa> tabelaInstrutores;
    @FXML private TableColumn<Tarefa, String> colInstrutor;
    @FXML private TableColumn<Tarefa, String> colCurso;
    @FXML private TableColumn<Tarefa, String> colAgente;
    @FXML private TableColumn<Tarefa, String> colTurno;
    @FXML private TableColumn<Tarefa, String> colData;
    @FXML private TableColumn<Tarefa, String> colDataFinal;
    @FXML private TableColumn<Tarefa, String> colDisponibilidade;
    @FXML private TableColumn<Tarefa, Tarefa> colTarefaObjeto; 

    @FXML private ComboBox<Instrutores> comboInstrutor;
    @FXML private ComboBox<Agente> comboAgente;
    @FXML private ComboBox<Curso> comboCurso;
    @FXML private ComboBox<String> comboTurno;
    @FXML private DatePicker dateInicial;
    @FXML private DatePicker dateFinal;
    @FXML private TextField txtBuscaInstrutor;

    @FXML private Label lblNomeAgenteLogado;

    private TarefaDAO tarefaDAO;
    private ObservableList<Tarefa> listaDeTarefas;
    private Agente agenteLogado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarComboBox(comboInstrutor, new InstrutoresDAO().buscarInstrutoresDoBanco());
        configurarComboBox(comboAgente, new AgenteDAO().buscarAgenteDoBanco());
        configurarComboBox(comboCurso, new CursoDAO().buscarCursosDoBanco());
        comboTurno.setItems(FXCollections.observableArrayList("Matutino", "Vespertino", "Noturno"));

        tarefaDAO = new TarefaDAO();
        carregarTarefas();
        configurarTabela();

        agenteLogado = Util.Sessao.getAgenteLogado();
        if (agenteLogado != null) {
            String primeiroNome = agenteLogado.getNome().trim().split("\\s+")[0];
            lblNomeAgenteLogado.setText(primeiroNome);
        }

        btnExcluirTarefa.setOnAction(this::ActionExcluirTarefa);

        tabelaInstrutores.setRowFactory(tv -> {
            TableRow<Tarefa> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Tarefa tarefaSelecionada = row.getItem();
                    abrirTelaDetalheTarefa(tarefaSelecionada);
                }
            });
            return row;
        });
    }

    private <T> void configurarComboBox(ComboBox<T> comboBox, List<T> items) {
        comboBox.setItems(FXCollections.observableArrayList(items));
        comboBox.setConverter(new StringConverter<T>() {
            @Override
            public String toString(T obj) {
                return obj != null ? obj.toString() : "";
            }

            @Override
            public T fromString(String s) {
                return null;
            }
        });
    }

    public void setAgenteLogado(Agente agente) {
        this.agenteLogado = agente;
        if (agente != null && agente.getNome() != null && !agente.getNome().isBlank()) {
            String primeiroNome = agente.getNome().trim().split("\\s+")[0];
            lblNomeAgenteLogado.setText(primeiroNome);
        }
        carregarTarefas();
        tabelaInstrutores.setItems(listaDeTarefas);
        tabelaInstrutores.refresh();
    }

    private void carregarTarefas() {
        List<Tarefa> tarefas = tarefaDAO.listarTarefasComDisponibilidade();
        listaDeTarefas = FXCollections.observableArrayList(tarefas);
    }

    private void configurarTabela() {
        colInstrutor.setCellValueFactory(new PropertyValueFactory<>("nomeInstrutor"));
        colCurso.setCellValueFactory(new PropertyValueFactory<>("nomeCurso"));
        colAgente.setCellValueFactory(new PropertyValueFactory<>("nomeAgente"));
        colTurno.setCellValueFactory(new PropertyValueFactory<>("turno"));
        colData.setCellValueFactory(new PropertyValueFactory<>("dataInicial"));
        colDataFinal.setCellValueFactory(new PropertyValueFactory<>("dataFinal"));
        colDisponibilidade.setCellValueFactory(new PropertyValueFactory<>("disponibilidade"));

        colTarefaObjeto = new TableColumn<>("Tarefa");
        colTarefaObjeto.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        colTarefaObjeto.setVisible(false);
        tabelaInstrutores.getColumns().add(colTarefaObjeto); 

        tabelaInstrutores.setItems(listaDeTarefas);
    }

    @FXML
    void ActionBuscarTarefa(ActionEvent event) {
        String termo = txtBuscaInstrutor.getText().trim();
        List<Tarefa> resultados = tarefaDAO.buscarTarefasPorInstrutor(termo);
        listaDeTarefas.setAll(resultados);
        tabelaInstrutores.refresh();
    }

    @FXML
    void ActionAdicionarTarefa(ActionEvent event) {
        Instrutores ins = comboInstrutor.getValue();
        Agente ag = comboAgente.getValue();
        Curso cr = comboCurso.getValue();
        LocalDate di = dateInicial.getValue();
        LocalDate df = dateFinal.getValue();
        String t = comboTurno.getValue();

        if (ins == null || ag == null || cr == null || di == null || df == null || t == null) {
            Alerts.showAlert("Atenção", "Preencha todos os campos", "", AlertType.WARNING);
            return;
        }

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Tarefa nt = new Tarefa();
        nt.setCodInstrutor(String.valueOf(ins.getIdInstrutor()));
        nt.setCodAgente(String.valueOf(ag.getIdAgente()));
        nt.setCodCurso(String.valueOf(cr.getIdCurso()));
        nt.setDataInicial(di.format(fmt));
        nt.setDataFinal(df.format(fmt));
        nt.setTurno(t);

        tarefaDAO.adicionarTarefa(nt);

        carregarTarefas();
        tabelaInstrutores.setItems(listaDeTarefas); 
        tabelaInstrutores.refresh();
        Alerts.showAlert("Sucesso", "Tarefa adicionada com sucesso!", "", AlertType.INFORMATION);
    }

    @FXML
    void ActionExcluirTarefa(ActionEvent event) {
        Tarefa sel = tabelaInstrutores.getSelectionModel().getSelectedItem();

        if (sel == null) {
            Alerts.showAlert("Atenção", "Selecione uma tarefa", "", AlertType.INFORMATION);
            return;
        }

        if (agenteLogado == null) {
            abrirTelaAutenticacao(sel.getIdTarefa(), -1); 
        } else {
            abrirTelaAutenticacao(sel.getIdTarefa(), agenteLogado.getIdAgente());
        }
    }

    private void abrirTelaAutenticacao(int idT, int idA) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/TelaAutenticacaoAgente.fxml"));
            Parent root = loader.load();
            ControllerAutenticacaoAgente ca = loader.getController();
            ca.setDadosExclusao(idT, idA, this);
            Stage st = new Stage();
            st.setTitle("Autenticação");
            st.setScene(new Scene(root));
            st.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void confirmarExclusao(int idTarefa) {
        if (Alerts.showConfirmacaoExcluirTarefa("a tarefa") && tarefaDAO.excluirTarefa(idTarefa)) {
            listaDeTarefas.removeIf(x -> x.getIdTarefa() == idTarefa);
            tabelaInstrutores.refresh();
        }
    }

    @FXML void onactionConsultarInstrutor(ActionEvent e) throws IOException {
        abrirNovaTela("/View/ViewInstrutor.fxml", "Consulta Instrutores");
    }

    @FXML void onactionConsultarCurso(ActionEvent e) throws IOException {
        abrirNovaTela("/View/ViewCurso.fxml", "Consulta Cursos");
    }

    @FXML void onactionInstrutor(ActionEvent e) throws IOException {
        abrirNovaTela("/View/ViewRegistroInstrutor.fxml", "Cadastro Instrutor");
    }

    @FXML void onactionCurso(ActionEvent e) throws IOException {
        abrirNovaTela("/View/ViewRegistroCurso.fxml", "Cadastro Curso");
    }

    private void abrirNovaTela(String caminhoFXML, String titulo) throws IOException {
        Stage s = (Stage) btnBuscarInstrutor.getScene().getWindow();
        s.close();
        Parent root = FXMLLoader.load(getClass().getResource(caminhoFXML));
        Stage novaStage = new Stage();
        novaStage.setTitle(titulo);
        novaStage.setScene(new Scene(root));
        novaStage.show();
    }

    private void abrirTelaDetalheTarefa(Tarefa tarefa) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/TelaDetalheTarefa.fxml"));
            Parent root = loader.load();

            ControllerDetalheTarefa controller = loader.getController();
            controller.setTarefa(tarefa);

            Stage stage = new Stage();
            stage.setTitle("Detalhes da Tarefa");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
