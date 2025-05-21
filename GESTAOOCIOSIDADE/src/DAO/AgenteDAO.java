package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionFactory.ConnectionDatabase;
import Model.Agente;
import Util.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;

public class AgenteDAO {

    public void create(Agente agente) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
        	stmt = con.prepareStatement("INSERT INTO AGENTES (nome, cpf, senha) VALUES (?, ?, ?)");
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
        public boolean cpfExiste(String cpf) {
            Connection con = ConnectionDatabase.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                String sql = "SELECT 1 FROM AGENTES WHERE CPF = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, cpf);
                rs = stmt.executeQuery();

                return rs.next(); 
            } catch (SQLException e) {
                System.out.println("Erro ao verificar CPF: " + e.getMessage());
                return false;
            } finally {
                ConnectionDatabase.closeConnection(con, stmt, rs);
            }
        

    }

    public ArrayList<Agente> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Agente> agentes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM AGENTES");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Agente agente = new Agente();
                agente.setIdAgente(rs.getInt("idAgente")); 
                agente.setNome(rs.getString("nome"));
                agente.setCpf(rs.getString("CPF"));
                agente.setSenha(rs.getString("senha"));
                agentes.add(agente);
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
            stmt = con.prepareStatement("UPDATE AGENTES SET nome = ?, CPF = ?, senha = ? WHERE idAgente = ?");
            stmt.setString(1, agente.getNome());
            stmt.setString(2, agente.getCpf());
            stmt.setString(3, agente.getSenha());
            stmt.setInt(4, agente.getIdAgente()); 
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
            stmt = con.prepareStatement("DELETE FROM AGENTES WHERE idAgente = ?");
            stmt.setInt(1, agente.getIdAgente()); 

            stmt.executeUpdate();
            System.out.println("Excluido com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir! ", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public ArrayList<Agente> search(Agente agenteBusca) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Agente> agentes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM AGENTES WHERE CPF LIKE ? OR nome LIKE ? OR idAgente LIKE ?");
            stmt.setString(1, "%" + agenteBusca.getCpf() + "%");
            stmt.setString(2, "%" + agenteBusca.getNome() + "%");
            stmt.setString(3, "%" + agenteBusca.getIdAgente() + "%"); 
            rs = stmt.executeQuery();

            while (rs.next()) {
                Agente agente = new Agente();
                agente.setIdAgente(rs.getInt("idAgente")); 
                agente.setNome(rs.getString("nome"));
                agente.setCpf(rs.getString("CPF"));
                agentes.add(agente);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler informações!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return agentes;
    }

    public void inserirAgente(Agente agente) throws SQLException {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
        	String sql = "INSERT INTO AGENTES (nome, CPF, senha) VALUES (?, ?, ?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, agente.getNome());
            stmt.setString(2, agente.getCpf());
            stmt.setString(3, agente.getSenha());

            int linhasAfetadas = stmt.executeUpdate();
            if(linhasAfetadas > 0) {
            	System.out.println("Agente cadastro com sucesso!");
            } else {
            	System.out.println("Nenhum agente foi cadastrado: ");
            }
        } catch (SQLException e){
        	System.out.println("Erro ao cadastrar agente:" + e.getMessage());
        } finally {
        	ConnectionDatabase.closeConnection(con,stmt);
        }
    }

    public Agente autenticarUser(String nome, String senha) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Agente agenteAutenticado = null;

        try {
            stmt = con.prepareStatement("SELECT idAgente, nome, senha FROM AGENTES WHERE nome = ? AND senha = ?");
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            while (rs.next()) {
                agenteAutenticado = new Agente();
                agenteAutenticado.setIdAgente(rs.getInt("idAgente")); 
                agenteAutenticado.setNome(rs.getString("nome"));
                agenteAutenticado.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            Alerts.showAlert("Erro", "Erro de conexão", "Falha ao consultar informações no banco de dados", AlertType.ERROR);
            throw new RuntimeException("Erro de autenticação", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return agenteAutenticado;
    }

    public ObservableList<Agente> buscarAgenteDoBanco() { 
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ObservableList<Agente> agentes = FXCollections.observableArrayList();
        try {
            stmt = con.prepareStatement("SELECT idAgente, nome FROM AGENTES"); 
            rs = stmt.executeQuery();

            while (rs.next()) {
                Agente agente = new Agente();
                agente.setIdAgente(rs.getInt("idAgente")); 
                agente.setNome(rs.getString("nome"));
                agentes.add(agente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return agentes;
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
}