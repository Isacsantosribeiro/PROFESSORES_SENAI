package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConectionFactory.ConnectionDatabase;
import Model.Cliente;

public class ClienteDAO {
    
    public void create(Cliente cliente) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO CLIENTE VALUES(?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getDataNasc());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getEndereco());
            stmt.setString(6, cliente.getTelefone());
            stmt.setString(7, cliente.getGenero());
            
            stmt.executeUpdate();
            System.out.println("Cadastrado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar o cliente!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
    
    public ArrayList<Cliente> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Cliente");
            rs = stmt.executeQuery();
     

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getString(1));
                cliente.setNome(rs.getString(2));
                cliente.setCpf(rs.getString(3));
                cliente.setDataNasc(rs.getString(4));
                cliente.setEmail(rs.getString(5));
                cliente.setGenero(rs.getString(6));
                cliente.setEndereco(rs.getString(7));
                cliente.setTelefone(rs.getString(8));
                
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler os clientes!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        
        return clientes;
    }
    // metodo surce para pesquisa 
    public ArrayList<Cliente> search(Cliente cliente1) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("select * from Cliente where cpfCliente like ? or nomeCliente like ?");
            stmt.setString(1, "%"+cliente1.getCpf()+"%");
            stmt.setString(2, "%"+cliente1.getNome()+"%");
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getString(1));
                cliente.setNome(rs.getString(2));
                cliente.setCpf(rs.getString(3));
                cliente.setDataNasc(rs.getString(4));
                cliente.setEmail(rs.getString(5));
                cliente.setGenero(rs.getString(6));
                cliente.setEndereco(rs.getString(7));
                cliente.setTelefone(rs.getString(8));
                
                clientes.add(cliente); 
                }
        
        }catch (SQLException e) {
            throw new RuntimeException("Erro ao ler os clientes!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        
        return clientes;
        }
    public void update(Cliente cliente) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE Cliente SET nomeCliente = ?, cpfCliente = ?, dataDeNascimento = ?, emailCliente = ?, enderecoCliente = ?, telefoneCliente = ?, generoCliente = ? WHERE idCliente = ? OR cpfCliente = ?");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getDataNasc());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getEndereco());
            stmt.setString(6, cliente.getTelefone());
            stmt.setString(7, cliente.getGenero());
            stmt.setString(8, cliente.getId());
            stmt.setString(9, cliente.getCpf());
            
            stmt.executeUpdate();
            System.out.println("Cliente atualizado!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
    
    public void delete(Cliente cliente) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        try {    
            stmt = con.prepareStatement("DELETE FROM Cliente WHERE idCliente = ? OR cpfCliente = ?");
            stmt.setString(1, cliente.getId());
            stmt.setString(2, cliente.getCpf());
            
            stmt.executeUpdate();
            System.out.println("Excluído com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
}
