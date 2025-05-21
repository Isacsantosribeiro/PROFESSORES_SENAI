package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import ConnectionFactory.ConnectionDatabase;
import Model.Agente;
import Model.Tarefa;

public class TarefaDAO {

    public void adicionarTarefa(Tarefa tarefa) {
        String sql = "INSERT INTO TAREFA (codInstrutor, codAgente, codCurso, dataInicial, dataFinal, turno) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = ConnectionDatabase.getConnection();
            stmt = con.prepareStatement(sql);

            stmt.setString(1, tarefa.getCodInstrutor());
            stmt.setString(2, tarefa.getCodAgente());
            stmt.setString(3, tarefa.getCodCurso());
            stmt.setString(4, tarefa.getDataInicial());
            stmt.setString(5, tarefa.getDataFinal());
            stmt.setString(6, tarefa.getTurno()); 

            stmt.executeUpdate();
            System.out.println("Tarefa inserida com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public List<Tarefa> listarTarefasComDisponibilidade() {
        List<Tarefa> lista = new ArrayList<>();

        String sql = """
            SELECT
                t.idTarefa,
                t.codInstrutor,
                i.nome AS nomeInstrutor,
                t.codAgente,
                a.nome AS nomeAgente,
                t.codCurso,
                c.nome AS nomeCurso,
                t.dataInicial,
                t.dataFinal,
                t.turno
            FROM TAREFA t
            INNER JOIN INSTRUTORES i ON t.codInstrutor = i.idInstrutor
            INNER JOIN AGENTES a ON t.codAgente = a.idAgente
            INNER JOIN CURSO c ON t.codCurso = c.idCurso
        """;

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionDatabase.getConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            LocalDate hoje = LocalDate.now();

            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setIdTarefa(rs.getInt("idTarefa"));
                tarefa.setCodInstrutor(rs.getString("codInstrutor"));
                tarefa.setNomeInstrutor(rs.getString("nomeInstrutor"));
                tarefa.setCodAgente(rs.getString("codAgente"));
                tarefa.setNomeAgente(rs.getString("nomeAgente"));
                tarefa.setCodCurso(rs.getString("codCurso"));
                tarefa.setNomeCurso(rs.getString("nomeCurso"));
                tarefa.setDataInicial(rs.getString("dataInicial"));
                tarefa.setDataFinal(rs.getString("dataFinal"));
                tarefa.setTurno(rs.getString("turno"));

                LocalDate dataFinalTarefa = LocalDate.parse(rs.getString("dataFinal"));
                long diasDisponiveis = ChronoUnit.DAYS.between(hoje, dataFinalTarefa);

                if (diasDisponiveis >= 0) {
                    tarefa.setDisponibilidade("Disponível em " + diasDisponiveis + " dias");
                } else {
                    tarefa.setDisponibilidade("Disponível");
                }

                lista.add(tarefa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public Agente buscarAgentePorId(String idAgente) {
        String sql = "SELECT idAgente, nome, senha FROM AGENTES WHERE idAgente = ?";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Agente agente = null;

        try {
            con = ConnectionDatabase.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, idAgente);
            rs = stmt.executeQuery();

            if (rs.next()) {
                agente = new Agente();
                agente.setIdAgente(rs.getInt("idAgente"));
                agente.setNome(rs.getString("nome"));
                agente.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return agente;
    }

    public boolean excluirTarefa(int idTarefa) {
        String sql = "DELETE FROM TAREFA WHERE idTarefa = ?";
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = ConnectionDatabase.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idTarefa);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public List<Tarefa> buscarTarefasPorInstrutor(String termoBusca) {
        List<Tarefa> lista = new ArrayList<>();

        String sql = """
            SELECT
                t.idTarefa,
                t.codInstrutor,
                i.nome AS nomeInstrutor,
                i.cpf AS cpfInstrutor,
                t.codAgente,
                a.nome AS nomeAgente,
                t.codCurso,
                c.nome AS nomeCurso,
                t.dataInicial,
                t.dataFinal,
                t.turno
            FROM TAREFA t
            INNER JOIN INSTRUTORES i ON t.codInstrutor = i.idInstrutor
            INNER JOIN AGENTES a ON t.codAgente = a.idAgente
            INNER JOIN CURSO c ON t.codCurso = c.idCurso
            WHERE i.nome LIKE ? OR i.cpf LIKE ?
        """;

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionDatabase.getConnection();
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + termoBusca + "%");
            stmt.setString(2, "%" + termoBusca + "%");
            rs = stmt.executeQuery();

            LocalDate hoje = LocalDate.now();

            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setIdTarefa(rs.getInt("idTarefa"));
                tarefa.setCodInstrutor(rs.getString("codInstrutor"));
                tarefa.setNomeInstrutor(rs.getString("nomeInstrutor"));
                tarefa.setCodAgente(rs.getString("codAgente"));
                tarefa.setNomeAgente(rs.getString("nomeAgente"));
                tarefa.setCodCurso(rs.getString("codCurso"));
                tarefa.setNomeCurso(rs.getString("nomeCurso"));
                tarefa.setDataInicial(rs.getString("dataInicial"));
                tarefa.setDataFinal(rs.getString("dataFinal"));
                tarefa.setTurno(rs.getString("turno"));

                LocalDate dataFinalTarefa = LocalDate.parse(rs.getString("dataFinal"));
                long diasDisponiveis = ChronoUnit.DAYS.between(hoje, dataFinalTarefa);

                if (diasDisponiveis >= 0) {
                    tarefa.setDisponibilidade("Disponível em " + diasDisponiveis + " dias");
                } else {
                    tarefa.setDisponibilidade("Disponível");
                }

                lista.add(tarefa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return lista;
    }
}