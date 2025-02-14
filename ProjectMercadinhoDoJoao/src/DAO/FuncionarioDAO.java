package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConectionFactory.ConnectionDatabase;
import Model.Funcionario;
import Util.Alerts;
import javafx.scene.control.Alert.AlertType;

public class FuncionarioDAO {

    public void create(Funcionario funcionario) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO Funcionario (nomeFuncionario, cpfFuncionario, dataDeNascimento, emailFuncionario, generoFuncionario, enderecoFuncionario, telefoneFuncionario, cargo, salario, dataDeAdmissao, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getDataDeNascimento());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getGenero());
            stmt.setString(6, funcionario.getEndereco());
            stmt.setString(7, funcionario.getTelefone());
            stmt.setString(8, funcionario.getCargo());
            stmt.setString(9, funcionario.getSalario());
            stmt.setString(10, funcionario.getDataDeAdimissao());
            stmt.setString(11, funcionario.getSenha());

            stmt.executeUpdate();
            System.out.println("Funcionário cadastrado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar o funcionário!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public ArrayList<Funcionario> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Funcionario");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setNome(rs.getString(1));
                funcionario.setCpf(rs.getString(2));
                funcionario.setDataDeNascimento(rs.getString(3));
                funcionario.setEmail(rs.getString(4));
                funcionario.setGenero(rs.getString(5));
                funcionario.setEndereco(rs.getString(6));
                funcionario.setTelefone(rs.getString(7));
                funcionario.setCargo(rs.getString(8));
                funcionario.setSalario(rs.getString(9));
                funcionario.setDataDeAdimissao(rs.getString(10));
                funcionario.setSenha(rs.getString(11));

                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler os funcionários!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return funcionarios;
    }

    public void update(Funcionario funcionario) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE Funcionario SET nomeFuncionario = ?, cpfFuncionario = ?, dataDeNascimento = ?, emailFuncionario = ?, generoFuncionario = ?, enderecoFuncionario = ?, telefoneFuncionario = ?, cargo = ?, salario = ?, dataDeAdmissao = ?, senha = ? WHERE idFuncionario = ? OR cpfFuncionario = ?");

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCpf());
            stmt.setString(3, funcionario.getDataDeNascimento());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getGenero());
            stmt.setString(6, funcionario.getEndereco());
            stmt.setString(7, funcionario.getTelefone());
            stmt.setString(8, funcionario.getCargo());
            stmt.setString(9, funcionario.getSalario());
            stmt.setString(10, funcionario.getDataDeAdimissao());
            stmt.setString(11, funcionario.getSenha());
            stmt.setString(12, funcionario.getId());
            stmt.setString(13, funcionario.getCpf());

            stmt.executeUpdate();
            System.out.println("Funcionário atualizado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o funcionário!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public void delete(Funcionario funcionario) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM Funcionario WHERE idFuncionario = ? OR cpfFuncionario = ?");

            stmt.setString(1, funcionario.getId());
            stmt.setString(2, funcionario.getCpf());

            stmt.executeUpdate();
            System.out.println("Funcionário excluído com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir o funcionário!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        
        }
    }
    
	
	public Funcionario autenticarUser(String cpf, String senha) {
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Funcionario funcionario = new Funcionario ();
		
		try {
			stmt = con.prepareStatement("select * from Funcionario where cpfFuncionario = ? and senha = ?");
			stmt.setString(1, cpf);
			stmt.setString(2, senha);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
			funcionario.setId(rs.getString(1));						
			funcionario.setNome(rs.getString(2));
			funcionario.setCpf(rs.getString(3));
			funcionario.setDataDeNascimento(rs.getString(4));
			funcionario.setEmail(rs.getString(5));
			funcionario.setGenero(rs.getString(6));
			funcionario.setEndereco(rs.getString(7));
			funcionario.setTelefone(rs.getString(8));
			funcionario.setCargo(rs.getString(9));
			funcionario.setSalario(rs.getString(10));
			funcionario.setDataDeAdimissao(rs.getString(11));
			funcionario.setSenha(rs.getString(12));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Alerts.showAlert("Erro", "Erro de conexao", "Falha ao consultar informações no banco de dados", AlertType.ERROR);
			throw new RuntimeException("Erro de autenticação", e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		
		return funcionario;
	}
}

