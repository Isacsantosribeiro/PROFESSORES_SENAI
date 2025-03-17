package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ConectionFactory.ConnectionDatabase;
import Model.Produto;

public class ProdutoDAO {

    
    public void create(Produto produto) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO Produto VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
            stmt.setString(1, produto.getIdfornecedor());
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getCodBarra());
            stmt.setString(4, produto.getLote());
            stmt.setString(5, produto.getDataFab());
            stmt.setString(6, produto.getDataVal());
            stmt.setString(7, produto.getMarca());
            stmt.setString(8, produto.getCategoria());
            stmt.setString(9, produto.getUnidadeDeMedida());
            stmt.setString(10, produto.getPrecoUnitario());
            stmt.setString(11, produto.getEstoque());
            
            stmt.executeUpdate();
            System.out.println("Produto cadastrado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar o produto!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    
    public ArrayList<Produto> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Produto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Produto");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getString(1));
                produto.setCodBarra(rs.getString(2));
                produto.setIdfornecedor(rs.getString(3));
                produto.setNome(rs.getString(4));
                produto.setLote(rs.getString(5));
                produto.setDataFab(rs.getString(6));
                produto.setDataVal(rs.getString(7));
                produto.setMarca(rs.getString(8));
                produto.setCategoria(rs.getString(9));
                produto.setUnidadeDeMedida(rs.getString(10));
                produto.setPrecoUnitario(rs.getString(11));
                produto.setEstoque(rs.getString(12));
                
                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler os produtos!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        
        return produtos;
    }
    
    // Metodo de auto completar
    
    public ArrayList<String> readProdutoByNome(){
    	Connection con = ConnectionDatabase.getConnection();
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	ArrayList<String> produtos = new ArrayList<>();
 
			try {
				stmt = con.prepareStatement("Select nome from Produto");
				rs = stmt.executeQuery();
				
				while (rs.next()) {
					String nome;
					nome = rs.getString(1);
					produtos.add(nome);
				}
			} catch (SQLException e) {
			throw new RuntimeException("Erro ao ler os produtos", e);
			}finally {
				ConnectionDatabase.closeConnection(con, stmt, rs);
			}
    		return produtos;
    }
    
  
    public void update(Produto produto) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        
        try {
        	stmt = con.prepareStatement("UPDATE Produto SET idFornecedorProduto = ?, nomeProduto = ?, codBarraProduto = ?, loteProduto = ?, dataFabProduto = ?, dataValProduto = ?, marcaProduto = ?, categoriaProduto = ?, unidadeDeMedidaProduto = ?, precoUnitarioProduto = ?, estoqueProduto = ? WHERE idProduto = ?");

        	stmt.setString(1, produto.getIdfornecedor());  
        	stmt.setString(2, produto.getNome());
        	stmt.setString(3, produto.getCodBarra());  
        	stmt.setString(4, produto.getLote());
        	stmt.setString(5, produto.getDataFab());  
        	stmt.setString(6, produto.getDataVal());  
        	stmt.setString(7, produto.getMarca());
        	stmt.setString(8, produto.getCategoria());
        	stmt.setString(9, produto.getUnidadeDeMedida());
        	stmt.setString(10, produto.getPrecoUnitario());  
        	stmt.setString(11, produto.getEstoque());  
        	stmt.setString(12, produto.getIdProduto());  

        	stmt.executeUpdate();

            System.out.println("Produto atualizado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o produto!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    
    public void delete(Produto produto) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM Produto WHERE idProduto = ?");
            stmt.setString(1, produto.getIdProduto());
            
            stmt.executeUpdate();
            System.out.println("Produto excluído com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir o produto!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        
        }
        
    }
    
    
    
    public ArrayList<Produto> search(Produto produto1){
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Produto> produtos = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("select * from Produto where idProduto like ? or nome like ?");
			stmt.setString(1, "%"+produto1.getIdProduto()+"%");
			stmt.setString(2, "%"+produto1.getNome()+"%");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Produto produto = new Produto();
				
				produto.setIdProduto(rs.getString(1));
				produto.setIdfornecedor(rs.getString(2));
				produto.setNome(rs.getString(3));
				produto.setCodBarra(rs.getString(4));
				produto.setLote(rs.getString(5));
				produto.setDataFab(rs.getString(6));
				produto.setDataVal(rs.getString(7));
				produto.setMarca(rs.getString(8));
				produto.setCategoria(rs.getString(9));
				produto.setUnidadeDeMedida(rs.getString(10));
				produto.setPrecoUnitario(rs.getString(11));
				produto.setEstoque(rs.getString(12));
			
				produtos.add(produto);
			
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		return produtos;
	}

    public ArrayList<Produto> getByEstoque() {
    Connection con = ConnectionDatabase.getConnection();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    ArrayList<Produto> produtos = new ArrayList<>();
    
    try {
        stmt = con.prepareStatement("select * from  Produto where estoque <15;");
        rs = stmt.executeQuery();
        
        int i = 1;
        
        while (rs.next()) {
            Produto produto = new Produto();
            produto.setIdProduto(""+1);
            produto.setCodBarra(rs.getString(4));
            produto.setIdfornecedor(rs.getString(2));
            produto.setNome(rs.getString(3));
            produto.setLote(rs.getString(5));
            produto.setDataFab(rs.getString(6));
            produto.setDataVal(rs.getString(7));
            produto.setMarca(rs.getString(8));
            produto.setCategoria(rs.getString(9));
            produto.setUnidadeDeMedida(rs.getString(10));
            produto.setPrecoUnitario(rs.getString(11));
            produto.setEstoque(rs.getString(12));
            i++;
            
            produtos.add(produto);
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao ler os produtos!", e);
    } finally {
        ConnectionDatabase.closeConnection(con, stmt, rs);
    }
    
    return produtos;
    
}
    
        


public ArrayList<Produto> getByDataVal() {
    Connection con = ConnectionDatabase.getConnection();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    ArrayList<Produto> produtos = new ArrayList<>();
    
    try {
        stmt = con.prepareStatement("select * from Produto where DATEDIFF (DD, GETDATE(), dataVal) <60 order by dataVal");
        rs = stmt.executeQuery();
        
        int i = 1;
        
        while (rs.next()) {
            Produto produto = new Produto();
            produto.setIdProduto(""+1);
            produto.setCodBarra(rs.getString(4));
            produto.setIdfornecedor(rs.getString(2));
            produto.setNome(rs.getString(3));
            produto.setLote(rs.getString(5));
            produto.setDataFab(rs.getString(6));
            produto.setDataVal(rs.getString(7));
            produto.setMarca(rs.getString(8));
            produto.setCategoria(rs.getString(9));
            produto.setUnidadeDeMedida(rs.getString(10));
            produto.setPrecoUnitario(rs.getString(11));
            produto.setEstoque(rs.getString(12));
            i++;
            
            produtos.add(produto);
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao ler os produtos!", e);
    } finally {
        ConnectionDatabase.closeConnection(con, stmt, rs);
    }
    
    return produtos;
    
    
}
}
