package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionFactory.ConnectionDatabase;
import Model.Agente;

public class AgenteDAO {

    public void create(Agente agente) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO AGENTES (nome, cpf, senha) VALUES (?, ?, ?)"); // CORREÇÃO: AGENTES
            stmt.setString(1, agente.getNome());
            stmt.setString(2, agente.getCpf());
            stmt.setString(3, agente.getSenha());

            stmt.executeUpdate();
            System.out.println("Cadastro com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar! ", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public ArrayList<Agente> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Agente> agentes = new ArrayList<>();
        int i = 1;

        try {
            stmt = con.prepareStatement("SELECT * FROM AGENTES");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Agente agente = new Agente();

                agente.setId("" + i);
                agente.setNome(rs.getString(2));
                agente.setCpf(rs.getString(3));
                agente.setSenha(rs.getString(4));

                agentes.add(agente);
                i++;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler informações!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return agentes;
    }

    public void update(Agente agente) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE AGENTES SET nome = ?, CPF = ?, senha = ? WHERE idAgente = ? OR CPF = ?");
            stmt.setString(1, agente.getNome());
            stmt.setString(2, agente.getCpf());
            stmt.setString(3, agente.getSenha());
            stmt.setString(4, agente.getId()); // Adicionado o ID para a cláusula WHERE
            stmt.setString(5, agente.getCpf()); // Adicionado o CPF para a cláusula WHERE

            stmt.executeUpdate();
            System.out.println("Atualizado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar! ", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public void delete(Agente agente) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM AGENTES WHERE idAgente = ? OR CPF = ?");

            stmt.setString(1, agente.getId());
            stmt.setString(2, agente.getCpf());

            stmt.executeUpdate();
            System.out.println("Excluido com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir! ", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public ArrayList<Agente> search(Agente agente1) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Agente> agentes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM AGENTES WHERE CPF LIKE ? OR nome LIKE ?");
            stmt.setString(1, "%" + agente1.getCpf() + "%");
            stmt.setString(2, "%" + agente1.getNome() + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                Agente agente = new Agente();

                agente.setId(rs.getString(1));
                agente.setNome(rs.getString(2));
                agente.setCpf(rs.getString(3));

                agentes.add(agente);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler informações!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return agentes;
    }

    // Método para inserir um novo agente (adaptado para o seu código)
    public void inserirAgente(Agente agente) throws SQLException {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO AGENTES (nome, cpf, senha) VALUES (?, ?, ?)"); // CORREÇÃO: AGENTES
            stmt.setString(1, agente.getNome());
            stmt.setString(2, agente.getCpf());
            stmt.setString(3, agente.getSenha());
            stmt.executeUpdate();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public Agente autenticarUser(String usuario, String senha) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Agente agenteAutenticado = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM AGENTES WHERE nome = ?"); // Supondo que o login seja feito com o nome
            stmt.setString(1, usuario);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Agente agente = new Agente();
                agente.setId(rs.getString("idAgente")); // Use o nome correto da coluna
                agente.setNome(rs.getString("nome"));
                agente.setCpf(rs.getString("cpf"));
                agente.setSenha(rs.getString("senha")); // A senha no banco DEVE estar criptografada

                // *** IMPORTANTE: A senha armazenada no banco de dados DEVE estar criptografada. ***
                // *** Você precisará comparar a senha fornecida (criptografada) com a senha do banco. ***
                // *** Para fins de exemplo SIMPLES (NÃO recomendado para produção): ***
                if (agente.getSenha().equals(senha)) {
                    agenteAutenticado = agente;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao autenticar usuário!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return agenteAutenticado;
    }
	}
