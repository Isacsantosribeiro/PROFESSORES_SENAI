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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
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
    @FXML private TableColumn<Tarefa, Tarefa> colTarefaObjeto; // Adicione esta coluna

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
        // Comboboxes (seu código existente)
        InstrutoresDAO instrutoresDAO = new InstrutoresDAO();
        comboInstrutor.setItems(instrutoresDAO.buscarInstrutoresDoBanco());
        comboInstrutor.setConverter(new StringConverter<Instrutores>() {
            @Override public String toString(Instrutores ins) { return ins!=null?ins.getNome():""; }
            @Override public Instrutores fromString(String s) { return null; }
        });

        AgenteDAO agenteDAO = new AgenteDAO();
        comboAgente.setItems(agenteDAO.buscarAgenteDoBanco());
        comboAgente.setConverter(new StringConverter<Agente>() {
            @Override public String toString(Agente ag) { return ag!=null?ag.getNome():""; }
            @Override public Agente fromString(String s) { return null; }
        });

        CursoDAO cursoDAO = new CursoDAO();
        comboCurso.setItems(cursoDAO.buscarCursosDoBanco());
        comboCurso.setConverter(new StringConverter<Curso>() {
            @Override public String toString(Curso cr) { return cr!=null?cr.getNome():""; }
            @Override public Curso fromString(String s) { return null; }
        });

        comboTurno.setItems(FXCollections.observableArrayList("Matutino", "Vespertino", "Noturno"));

        // Prepara tabela
        tarefaDAO = new TarefaDAO();
        carregarTarefas();
        configurarTabela();
        btnExcluirTarefa.setOnAction(this::ActionExcluirTarefa);

        // Adiciona o listener para o duplo clique na tabela
        tabelaInstrutores.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
                Tarefa tarefaSelecionada = tabelaInstrutores.getSelectionModel().getSelectedItem();
                if (tarefaSelecionada != null) {
                    abrirTelaDetalheTarefa(tarefaSelecionada);
                }
            }
        });
    }

    /** Recebe o agente logado, exibe o nome e recarrega a tabela de tarefas */
    public void setAgenteLogado(Agente agente) {
        this.agenteLogado = agente;
        if (agente != null && agente.getNome() != null && !agente.getNome().isBlank()) {
            String primeiroNome = agente.getNome().trim().split("\\s+")[0];
            lblNomeAgenteLogado.setText(primeiroNome);
        }
        // Recarrega tarefas sempre que o agente é definido
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

        // Configura a coluna para armazenar o objeto Tarefa (invisível)
        colTarefaObjeto = new TableColumn<>("Tarefa");
        colTarefaObjeto.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue()));
        colTarefaObjeto.setVisible(false);
        tabelaInstrutores.getColumns().add(colTarefaObjeto); // Adiciona a coluna à tabela

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

        System.out.println("Tentando adicionar tarefa: " + nt); // Debugging log
        tarefaDAO.adicionarTarefa(nt);

        carregarTarefas();
        System.out.println("Número de tarefas carregadas: " + listaDeTarefas.size()); // Debugging log
        tabelaInstrutores.setItems(listaDeTarefas); // Ensure setItems is called after reloading
        tabelaInstrutores.refresh();
        System.out.println("Tabela de tarefas foi atualizada."); // Debugging log
    }
    @FXML
    void ActionExcluirTarefa(ActionEvent event) {
        Tarefa sel = tabelaInstrutores.getSelectionModel().getSelectedItem();
        if (sel!=null) abrirTelaAutenticacao(sel.getIdTarefa(), agenteLogado.getIdAgente());
        else Alerts.showAlert("Atenção","Selecione uma tarefa","",AlertType.INFORMATION);
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
        } catch(IOException e) { e.printStackTrace(); }
    }

    public void confirmarExclusao(int idTarefa) {
        if (Alerts.showConfirmacaoExcluirTarefa("a tarefa") && tarefaDAO.excluirTarefa(idTarefa)) {
            listaDeTarefas.removeIf(x->x.getIdTarefa()==idTarefa);
            tabelaInstrutores.refresh();
        }
    }

    @FXML void onactionConsultarInstrutor(ActionEvent e) throws IOException {
        Stage s=(Stage)btnBuscarInstrutor.getScene().getWindow(); s.close();
        Parent r=FXMLLoader.load(getClass().getResource("/View/ViewInstrutor.fxml"));
        Stage n=new Stage(); n.setTitle("Consulta Instrutores"); n.setScene(new Scene(r)); n.show();
    }
    @FXML void onactionConsultarCurso(ActionEvent e) throws IOException {
        Stage s=(Stage)btnBuscarInstrutor.getScene().getWindow(); s.close();
        Parent r=FXMLLoader.load(getClass().getResource("/View/ViewCurso.fxml"));
        Stage n=new Stage(); n.setTitle("Consulta Cursos"); n.setScene(new Scene(r)); n.show();
    }
    @FXML void onactionInstrutor(ActionEvent e) throws IOException {
        Stage s=(Stage)btInstrutor.getScene().getWindow(); s.close();
        Parent r=FXMLLoader.load(getClass().getResource("/View/ViewRegistroInstrutor.fxml"));
        Stage n=new Stage(); n.setTitle("Cadastro Instrutor"); n.setResizable(false); n.setScene(new Scene(r)); n.show();
    }
    @FXML void onactionCurso(ActionEvent e) throws IOException {
        Stage s=(Stage)((Node)e.getSource()).getScene().getWindow(); s.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/ViewRegistroCurso.fxml")))); s.show();
    }

    private void abrirTelaDetalheTarefa(Tarefa tarefa) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/TelaDetalheTarefa.fxml"));
            Parent root = loader.load();

            ControllerDetalheTarefa controller = loader.getController();
            controller.setTarefa(tarefa); // Passa a tarefa para o controlador da tela de detalhes

            Stage stage = new Stage();
            stage.setTitle("Detalhes da Tarefa");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}