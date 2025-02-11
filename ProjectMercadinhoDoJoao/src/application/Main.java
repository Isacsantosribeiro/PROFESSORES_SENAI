package application;
	
import java.sql.Connection;
import java.util.ArrayList;

import ConectionFactory.ConnectionDatabase;
import DAO.ClienteDAO;
import DAO.FornecedorDAO;
import DAO.ProdutoDAO;
import DAO.ProdutoVendaDAO;
import DAO.VendaDAO;
import Model.Cliente;
import Model.Fornecedor;
import Model.Produto;
import Model.ProdutoVenda;
import Model.Venda;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Connection con = ConnectionDatabase.getConnection();
		ConnectionDatabase.closeConnection(con);
		
		
//   metodode pesquisa 
		
	Cliente cliente = new Cliente();
	ClienteDAO clienteDAO = new ClienteDAO();
	ArrayList <Cliente> clientes = new ArrayList<>();
	cliente.setCpf("12345678901");
	clientes.addAll(clienteDAO.search(cliente));
	
		
	for (int i = 0 ; i < clientes.size(); i++) {
		cliente = clientes.get(i);
		System.out.println("");
		System.out.println(cliente.getId()+ "|");
		System.out.println(cliente.getNome()+ "|");
		System.out.println(cliente.getDataNasc()+ "|");
		System.out.println(cliente.getEmail()+ "|");
		System.out.println(cliente.getEndereco()+ "|");
		System.out.println(cliente.getGenero()+ "|");
		System.out.println(cliente.getTelefone()+ "|");
	}
		
		
		
		
		
		
//		Cliente cliente = new Cliente();
//		cliente.setNome("Junin 244");
//		cliente.setCpf("05296385725");
//		cliente.setDataNasc("2003-04-25");
//		cliente.setEmail("junin898grau@gmail.com");
//		cliente.setEndereco("Rua das flores, numero 12");
//		cliente.setTelefone("634585859");
//		cliente.setGenero("masculino");
//		
//		ClienteDAO clienteDAO = new ClienteDAO();
//		clienteDAO.delete(cliente);
//	
		
		

//		    Funcionario funcionario = new Funcionario();
//		    funcionario.setNome("Carlos Silva");
//		    funcionario.setCpf("12345678900");
//		    funcionario.setDataDeNascimento("1990-05-15");
//		    funcionario.setEmail("carlos@empresa.com");
//		    funcionario.setGenero("Masculino");
//		    funcionario.setEndereco("Rua Central, 123");
//		    funcionario.setTelefone("11987654321");
//		    funcionario.setCargo("Gerente");
//		    funcionario.setSalario("4500.00");
//		    funcionario.setDataDeAdimissao("2023-01-10");
//		    funcionario.setSenha("senha123");
//
//		    FuncionarioDAO funcionarioDAO = new FuncionarioDAO(); 
//		    funcionarioDAO.delete(funcionario); 
////		
	  //Produto produto = new Produto();
	       // produto.setIdProduto("49");
	     //   produto.setIdfornecedor("4");
	    //    produto.setNome("Produto Exemplo");
	    //    produto.setCodBarra("2456678");
	   //     produto.setLote("Lote123");
	   //     produto.setDataFab("2024-01-03");
	   //     produto.setDataVal("2025-01-21");
	 //       produto.setMarca("MarcaX");
//	        produto.setCategoria("CategoriaA");
////	        produto.setUnidadeDeMedida("Un");
////	        produto.setPrecoUnitario("100");
////	        produto.setEstoque("50");
////	        
       // ProdutoDAO produtoDAO = new ProdutoDAO();
        //produtoDAO.delete(produto);
//				

		
		
//		Venda venda = new Venda();
//		venda.setIdvenda("1");
//		venda.setIdcliente("6");
//		venda.setIdfuncionario("1");
//		venda.setFormaDePagamento("1");
//		venda.setDesconto("10.00");
//		venda.setDataVenda("");
//		venda.setPrecoTotal("200.00");
//
//		VendaDAO vendaDAO = new VendaDAO();
//		vendaDAO.delete(venda);
		
	
		
		
		//ProdutoVenda produtoVenda = new ProdutoVenda();
		//produtoVenda.setIdprodutovenda("41");
		//produtoVenda.setIdvenda("31");
		//produtoVenda.setIdproduto("3");
		//produtoVenda.setQuantidade("5");

		//ProdutoVendaDAO produtoVendaDAO = new ProdutoVendaDAO();
		//produtoVendaDAO.delete(produtoVenda);
			
  launch(args);
	}
}


