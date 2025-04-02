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
			stmt = con.prepareStatement("Insert into AGENTE values(? , ?)");
			stmt.setString(1, agente.getNome());
			stmt.setString(2, agente.getCpf());

			stmt.executeUpdate();
			System.out.println("Cadastro com sucesso!");


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao cadastrar! ", e );
		}finally {
			ConnectionDatabase.closeConnection(con, stmt);
		}

	}
	public ArrayList<Agente> read(){
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Agente> agentes = new ArrayList<>();
		int i =1;
		try {
			stmt = con.prepareStatement("select * from AGENTES");
			rs = stmt.executeQuery();

			while(rs.next()) {
				Agente agente = new Agente();

				agente.setId(""+i);
				agente.setNome(rs.getString(2));
				agente.setCpf(rs.getString(3));

				agentes.add(agente);
				i++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		return agentes;
	}
	public void update(Agente agente) {
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("Update AGENTES set nome = ? , CPF = ? where idAgente = ? or CPF = ?");
			stmt.setString(1, agente.getNome());
			stmt.setString(2, agente.getCpf());

			stmt.setString(3, agente.getId());





			stmt.executeUpdate();
			System.out.println("Atualizado com sucesso!");


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao atualizar! ", e );
		}finally {
			ConnectionDatabase.closeConnection(con, stmt);
		}
	}
	public void delete(Agente agente) {

		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("delete from AGENTES where idAgente = ? or CPF = ?");

			stmt.setString(1, agente.getId());
			stmt.setString(2, agente.getCpf());




			stmt.executeUpdate();
			System.out.println("Excluido com sucesso!");


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao excluir! ", e );
		}finally {
			ConnectionDatabase.closeConnection(con, stmt);
		}
	}
	public ArrayList<Agente> search(Agente agente1){
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Agente> agentes = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("select * from AGENTES where CPF like ? or nome like ?");
			stmt.setString(1, "%"+agente1.getCpf()+"%");
			stmt.setString(2, "%"+agente1.getNome()+"%");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Agente agente = new Agente();
				
				agente.setId(rs.getString(1));
				agente.setNome(rs.getString(2));
				agente.setCpf(rs.getString(3));
				
				
				agentes.add(agente);
			}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		return agentes;
	}
}