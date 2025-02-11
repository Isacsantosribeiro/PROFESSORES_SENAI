package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ConectionFactory.ConnectionDatabase;
import Model.Fornecedor;

public class FornecedorDAO {

    // Método para inserir um novo fornecedor no banco de dados
    public void create(Fornecedor fornecedor) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        
        try {
         
            stmt = con.prepareStatement("INSERT INTO Fornecedor VALUES(?, ?, ?, ?)");
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.setString(4, fornecedor.getEndereco());
            
            stmt.executeUpdate();
            System.out.println("Fornecedor cadastrado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar o fornecedor!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    // Método para ler todos os fornecedores do banco de dados
    public ArrayList<Fornecedor> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Fornecedor");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getString(1));     
                fornecedor.setNome(rs.getString(2));  
                fornecedor.setCnpj(rs.getString(3));                     
                fornecedor.setTelefone(rs.getString(4)); 
                fornecedor.setEndereco(rs.getString(5)); 
                
                fornecedores.add(fornecedor);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler os fornecedores!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        
        return fornecedores;
    }
    
    // metodo de pesquisa 
//    public ArrayList<Fornecedor> search(Fornecedor fornecedor) {
//        Connection con = ConnectionDatabase.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        ArrayList<Fornecedor> fornecedores = new ArrayList<>();
//        
//        try {
//            // Consulta que busca fornecedores cujo CNPJ ou nome contenha o valor pesquisado
//            stmt = con.prepareStatement("SELECT * FROM Fornecedor WHERE cnpjFornecedor LIKE ? OR nomeFornecedor LIKE ?");
//            stmt.setString(1, "%" + fornecedor.getCnpj() + "%");
//            stmt.setString(2, "%" + fornecedor.getNome() + "%");
//            
//            rs = stmt.executeQuery();
//            
//            while (rs.next()) {
//                Fornecedor f = new Fornecedor();
//                fornecedor.setId(rs.getString(1));
//                fornecedor.setNome(null);
//                fornecedor.setId(rs.getString(1));
//                fornecedor.setId(rs.getString(1));
//                fornecedor.setId(rs.getString(1));
//                
//                fornecedores.add(f);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//           
//            ConnectionDatabase.closeConnection(con, stmt, rs);
//        }
//        
//        return fornecedores;
//    }
//
//    
//    
    
    
    

    // Método para atualizar os dados de um fornecedor
    public void update(Fornecedor fornecedor) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE Fornecedor SET nomeFornecedor = ?, cnpj = ?, telefoneFornecedor = ?, enderecoFornecedor = ? WHERE idFornecedor = ?");
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.setString(4, fornecedor.getEndereco());
            stmt.setString(5, fornecedor.getId());
            
            stmt.executeUpdate();
            System.out.println("Fornecedor atualizado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o fornecedor!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    // Método para excluir um fornecedor do banco de dados
    public void delete(Fornecedor fornecedor) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM Fornecedor WHERE idFornecedor = ? OR cnpj = ?");
            stmt.setString(1, fornecedor.getId());
            stmt.setString(2, fornecedor.getCnpj());
            
            stmt.executeUpdate();
            System.out.println("Fornecedor excluído com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir o fornecedor!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
}
