package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConectionFactory.ConnectionDatabase;
import Model.ProdutoVenda;

public class ProdutoVendaDAO {
    

    public void create(ProdutoVenda produtoVenda) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        try {
          
        	stmt = con.prepareStatement("INSERT INTO ProdutoVenda (FK_idVenda, FK_idProduto, quantidade) VALUES (?, ?, ?)");
        	stmt.setString(1, produtoVenda.getIdvenda());
        	stmt.setString(2, produtoVenda.getIdproduto());
        	stmt.setString(3, produtoVenda.getQuantidade());
            
            stmt.executeUpdate();
            System.out.println("ProdutoVenda cadastrado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar o ProdutoVenda!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
    
 
    public ArrayList<ProdutoVenda> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<ProdutoVenda> produtosVenda = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM ProdutoVenda");
            rs = stmt.executeQuery();
     
            while (rs.next()) {
                ProdutoVenda produtoVenda = new ProdutoVenda();
                produtoVenda.setIdprodutovenda(rs.getString(1));  
                produtoVenda.setIdvenda(rs.getString(2));       
                produtoVenda.setIdprodutovenda(rs.getString(3));         
                produtoVenda.setQuantidade(rs.getString(4));       
                
                produtosVenda.add(produtoVenda);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler os registros de ProdutoVenda!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        
        return produtosVenda;
    }
    
    
    public ArrayList<ProdutoVenda> search(ProdutoVenda produtoVendaFiltro) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<ProdutoVenda> produtosVenda = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM ProdutoVenda WHERE idVenda LIKE ? OR idProduto LIKE ?");
            stmt.setString(1, "%" + produtoVendaFiltro.getIdvenda() + "%");
            stmt.setString(2, "%" + produtoVendaFiltro.getIdprodutovenda() + "%");
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                ProdutoVenda produtoVenda = new ProdutoVenda();
                produtoVenda.setIdprodutovenda(rs.getString(1));
                produtoVenda.setIdvenda(rs.getString(2));
                produtoVenda.setIdprodutovenda(rs.getString(3));
                produtoVenda.setQuantidade(rs.getString(4));
                
                produtosVenda.add(produtoVenda);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar registros de ProdutoVenda!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        
        return produtosVenda;
    }
    
    
    public void update(ProdutoVenda produtoVenda) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
                "UPDATE ProdutoVenda SET idVenda = ?, idProduto = ?, quantidade = ? WHERE idProdutoVenda = ?"
            );
            stmt.setString(1, produtoVenda.getIdvenda());
            stmt.setString(2, produtoVenda.getIdprodutovenda());
            stmt.setString(3, produtoVenda.getQuantidade());
            stmt.setString(4, produtoVenda.getIdprodutovenda());
            
            stmt.executeUpdate();
            System.out.println("ProdutoVenda atualizado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar ProdutoVenda!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
    
  
    public void delete(ProdutoVenda produtoVenda) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        try {    
            stmt = con.prepareStatement("DELETE FROM ProdutoVenda WHERE idProdutoVenda = ?");
            stmt.setString(1, produtoVenda.getIdprodutovenda());
            
            stmt.executeUpdate();
            System.out.println("ProdutoVenda excluído com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir ProdutoVenda!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
}
