package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionFactory.ConnectionDatabase;
import Model.Agente;
import Util.Alerts;
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
            stmt = con.prepareStatement("SELECT nome, senha FROM AGENTES WHERE nome = ? AND senha = ?");
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            while (rs.next()) { 
                agenteAutenticado = new Agente();
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
	}
