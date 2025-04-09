package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionFactory.ConnectionDatabase;
import Model.Instrutores;

public class InstrutoresDAO {

    public boolean inserirInstrutor(Instrutores instrutor) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "INSERT INTO INSTRUTORES (nome, CPF) VALUES (?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, instrutor.getNome());
            stmt.setString(2, instrutor.getCpf());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar instrutor: " + e.getMessage());
            return false;
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public ArrayList<Instrutores> listarInstrutores() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Instrutores> instrutores = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM INSTRUTORES");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Instrutores instrutor = new Instrutores();
                instrutor.setId(rs.getString("idInstrutor"));
                instrutor.setNome(rs.getString("nome"));
                instrutor.setCpf(rs.getString("CPF"));
                instrutores.add(instrutor);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler instrutores!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return instrutores;
    }

    public boolean atualizarInstrutor(Instrutores instrutor) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            String sql = "UPDATE INSTRUTORES SET nome = ?, CPF = ? WHERE idInstrutor = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, instrutor.getNome());
            stmt.setString(2, instrutor.getCpf());
            stmt.setString(3, instrutor.getId());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar instrutor: " + e.getMessage());
            return false;
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public boolean deletarInstrutor(String id) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM INSTRUTORES WHERE idInstrutor = ?");
            stmt.setString(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao excluir instrutor: " + e.getMessage());
            return false;
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public ArrayList<Instrutores> buscarInstrutores(String busca) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Instrutores> instrutores = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM INSTRUTORES WHERE nome LIKE ? OR CPF LIKE ?");
            stmt.setString(1, "%" + busca + "%");
            stmt.setString(2, "%" + busca + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                Instrutores instrutor = new Instrutores();
                instrutor.setId(rs.getString("idInstrutor"));
                instrutor.setNome(rs.getString("nome"));
                instrutor.setCpf(rs.getString("CPF"));
                instrutores.add(instrutor);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar instrutores!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return instrutores;
    }
}
