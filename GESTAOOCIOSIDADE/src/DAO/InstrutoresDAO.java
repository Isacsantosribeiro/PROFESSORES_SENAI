package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionFactory.ConnectionDatabase;
import Model.Instrutores;
import Util.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InstrutoresDAO {

	public boolean inserirInstrutor(Instrutores instrutor) {
	    if (cpfExiste(instrutor.getCpf())) {
	        mostrarAlerta("CPF já cadastrado!", AlertType.WARNING);
	        return false;
	    }

	    Connection con = ConnectionDatabase.getConnection();
	    PreparedStatement stmt = null;

	    try {
	        String sql = "INSERT INTO INSTRUTORES (nome, CPF) VALUES (?, ?)";
	        stmt = con.prepareStatement(sql);
	        stmt.setString(1, instrutor.getNome());
	        stmt.setString(2, instrutor.getCpf());

	        int linhasAfetadas = stmt.executeUpdate();

	        if (linhasAfetadas > 0) {
	            mostrarAlerta("Instrutor cadastrado com sucesso!", AlertType.INFORMATION);
	            return true;
	        } else {
	            mostrarAlerta("Não foi possível cadastrar o instrutor.", AlertType.ERROR);
	            return false;
	        }

	    } catch (SQLException e) {
	        mostrarAlerta("Erro ao cadastrar instrutor: " + e.getMessage(), AlertType.ERROR);
	        return false;
	    } finally {
	        ConnectionDatabase.closeConnection(con, stmt);
	    }
	}
	
	private void mostrarAlerta(String mensagem, AlertType tipo) {
	    Alert alert = new Alert(tipo);
	    alert.setTitle("Cadastro de Instrutor");
	    alert.setHeaderText(null);
	    alert.setContentText(mensagem);
	    alert.showAndWait();
	}

	public boolean cpfExiste(String cpf) {
	    Connection con = ConnectionDatabase.getConnection();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        String sql = "SELECT 1 FROM INSTRUTORES WHERE CPF = ?";
	        stmt = con.prepareStatement(sql);
	        stmt.setString(1, cpf);
	        rs = stmt.executeQuery();

	        return rs.next(); // Se houver resultado, o CPF já existe

	    } catch (SQLException e) {
	        System.out.println("Erro ao verificar CPF: " + e.getMessage());
	        return false;
	    } finally {
	        ConnectionDatabase.closeConnection(con, stmt, rs);
	    }
	    
	}
	public ArrayList<Instrutores> search(Instrutores instrutorBusca) {
	    Connection con = ConnectionDatabase.getConnection();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    ArrayList<Instrutores> instrutores = new ArrayList<>();

	    try {
	        String sql = "SELECT * FROM instrutores WHERE 1=1";
	        if (!instrutorBusca.getCpf().isEmpty()) {
	            sql += " AND cpf LIKE ?";
	        }
	        if (!instrutorBusca.getNome().isEmpty()) {
	            sql += " AND nome LIKE ?";
	        }

	        stmt = con.prepareStatement(sql);

	        int index = 1;
	        if (!instrutorBusca.getCpf().isEmpty()) {
	            stmt.setString(index++, "%" + instrutorBusca.getCpf() + "%");
	        }
	        if (!instrutorBusca.getNome().isEmpty()) {
	            stmt.setString(index++, "%" + instrutorBusca.getNome() + "%");
	        }

	        rs = stmt.executeQuery();

	        while (rs.next()) {
	            Instrutores instrutor = new Instrutores();
	            instrutor.setId(rs.getString("idInstrutor")); 
	            instrutor.setNome(rs.getString("nome"));
	            instrutor.setCpf(rs.getString("cpf"));

	            instrutores.add(instrutor);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        ConnectionDatabase.closeConnection(con, stmt, rs);
	    }

	    return instrutores;
	}

	

//    public ArrayList<String> listarInstrutores() {
//        Connection con = ConnectionDatabase.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//
//        ArrayList<String> instrutores = new ArrayList<>();
//
//        try {
//            stmt = con.prepareStatement("SELECT * FROM INSTRUTORES");
//            rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                String instrutor = new String();
//                instrutor.setId(rs.getString("idInstrutor"));
//                instrutor.setNome(rs.getString("nome"));
//                instrutor.setCpf(rs.getString("CPF"));
//                instrutores.add(instrutor);
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException("Erro ao ler instrutores!", e);
//        } finally {
//            ConnectionDatabase.closeConnection(con, stmt, rs);
//        }
//
//        return instrutores;
//    }

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

    public void delete(Instrutores instrutores) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM INSTRUTORES WHERE idInstrutor = ? OR CPF = ?");
            stmt.setString(1, instrutores.getId());   
            stmt.setString(2, instrutores.getCpf());

            stmt.executeUpdate();
            System.out.println("Instrutor excluído com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir instrutor!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

//    public ArrayList<String> buscarInstrutores(String busca) {
//        Connection con = ConnectionDatabase.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//
//        ArrayList<String> instrutores = new ArrayList<>();
//
//        try {
//            stmt = con.prepareStatement("SELECT * FROM INSTRUTORES WHERE nome LIKE ? OR CPF LIKE ?");
//            stmt.setString(1, "%" + busca + "%");
//            stmt.setString(2, "%" + busca + "%");
//
//            rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                String instrutor = new String();
//                instrutor.setId(rs.getString("idInstrutor"));
//                instrutor.setNome(rs.getString("nome"));
//                instrutor.setCpf(rs.getString("CPF"));
//                instrutores.add(instrutor);
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException("Erro ao buscar instrutores!", e);
//        } finally {
//            ConnectionDatabase.closeConnection(con, stmt, rs);
//       }
//
//        return instrutores;
//    }
    public ObservableList<String> buscarInstrutoresDoBanco() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ObservableList<String> Instrutores = FXCollections.observableArrayList();
        try {
            stmt = con.prepareStatement("select nome from INSTRUTORES"); 
            rs = stmt.executeQuery();

            while (rs.next()) {
            	String f = rs.getString(1);
            	Instrutores.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return Instrutores;
    }
    public ArrayList<Instrutores> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Instrutores> listaDeInstrutores = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM INSTRUTORES");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Instrutores instrutor = new Instrutores();
                instrutor.setId(rs.getString("idInstrutor"));
                instrutor.setNome(rs.getString("nome"));
                instrutor.setCpf(rs.getString("cpf"));
                listaDeInstrutores.add(instrutor);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler os instrutores!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return listaDeInstrutores;
    }
}
