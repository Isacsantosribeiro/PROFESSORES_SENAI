package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tarefa {
    private int idTarefa;
    private String codInstrutor;
    private String nomeInstrutor;
    private String codAgente;
    private String nomeAgente;
    private String codCurso;
    private String nomeCurso;
    private String dataInicial;
    private String dataFinal;
    private LocalDate dataInicialLocalDate;
    private LocalDate dataFinalLocalDate;
    private String descricao;
    private String disponibilidade;
    private String turno;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Tarefa() {
        super();
    }

    public Tarefa(int idTarefa, String codInstrutor, String nomeInstrutor, String codAgente, String nomeAgente, String codCurso, String nomeCurso, String dataInicial, String dataFinal, String descricao, String disponibilidade, String turno) {
        super();
        this.idTarefa = idTarefa;
        this.codInstrutor = codInstrutor;
        this.nomeInstrutor = nomeInstrutor;
        this.codAgente = codAgente;
        this.nomeAgente = nomeAgente;
        this.codCurso = codCurso;
        this.nomeCurso = nomeCurso;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.descricao = descricao;
        this.disponibilidade = disponibilidade;
        this.turno = turno;
        try {
            this.dataInicialLocalDate = LocalDate.parse(dataInicial, DATE_FORMATTER);
            this.dataFinalLocalDate = LocalDate.parse(dataFinal, DATE_FORMATTER);
        } catch (Exception e) {
            this.dataInicialLocalDate = null;
            this.dataFinalLocalDate = null;
            System.err.println("Erro ao converter datas para LocalDate na tarefa " + idTarefa + ": " + e.getMessage());
        }
    }

    public int getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(int idTarefa) {
        this.idTarefa = idTarefa;
    }

    public String getCodInstrutor() {
        return codInstrutor;
    }

    public void setCodInstrutor(String codInstrutor) {
        this.codInstrutor = codInstrutor;
    }

    public String getNomeInstrutor() {
        return nomeInstrutor;
    }

    public void setNomeInstrutor(String nomeInstrutor) {
        this.nomeInstrutor = nomeInstrutor;
    }

    public String getCodAgente() {
        return codAgente;
    }

    public void setCodAgente(String codAgente) {
        this.codAgente = codAgente;
    }

    public String getNomeAgente() {
        return nomeAgente;
    }

    public void setNomeAgente(String nomeAgente) {
        this.nomeAgente = nomeAgente;
    }

    public String getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(String codCurso) {
        this.codCurso = codCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
        try {
            this.dataInicialLocalDate = LocalDate.parse(dataInicial, DATE_FORMATTER);
        } catch (Exception e) {
            this.dataInicialLocalDate = null;
            System.err.println("Erro ao converter data inicial para LocalDate na tarefa " + idTarefa + ": " + e.getMessage());
        }
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
        try {
            this.dataFinalLocalDate = LocalDate.parse(dataFinal, DATE_FORMATTER);
        } catch (Exception e) {
            this.dataFinalLocalDate = null;
            System.err.println("Erro ao converter data final para LocalDate na tarefa " + idTarefa + ": " + e.getMessage());
        }
    }

    public LocalDate getDataInicialLocalDate() {
        return dataInicialLocalDate;
    }

    public void setDataInicialLocalDate(LocalDate dataInicialLocalDate) {
        this.dataInicialLocalDate = dataInicialLocalDate;
        this.dataInicial = dataInicialLocalDate != null ? DATE_FORMATTER.format(dataInicialLocalDate) : null;
    }

    public LocalDate getDataFinalLocalDate() {
        return dataFinalLocalDate;
    }

    public void setDataFinalLocalDate(LocalDate dataFinalLocalDate) {
        this.dataFinalLocalDate = dataFinalLocalDate;
        this.dataFinal = dataFinalLocalDate != null ? DATE_FORMATTER.format(dataFinalLocalDate) : null;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}