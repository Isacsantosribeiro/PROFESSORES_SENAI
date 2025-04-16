package ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionDatabase {
private static final  String  Driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
private static final String URL = "jdbc:sqlserver://LOCALHOST:50054;encrypt=false;databaseName=GESTAO;user=sa;password=Senailab05";
private static final String user = "sa";
private static final String password = "Senailab05";
/**
 * metodo responsavel por conectar ao banco de dados 
 * @return
 * Sem retorno
 */
	
public static Connection getConnection(){
	try {
		Class.forName(Driver);
		System.out.println("conexao bem sucedida!");
		return DriverManager.getConnection(URL,user,password);
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
throw new RuntimeException("Ero ao conectar!",e);
	}
		
}
/**
 * metodo responsavel por fechar a conexão com banco de dados 
 * @param con - objeto do tipo Connection que sera fechado 
 */

public static void closeConnection(Connection con) {

	try {
		if(con!= null) {
		con.close();
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		System.out.println("Conexao fechada!");
}
/**
 * Sobrecarga do metodo responsavel por fechar a conexão com bamco de dados 
 * @param con - con objeto do tipo connection que sera fechado 
 * @param stmt - objeto do tipo preparedstation que sera fechado.
 */
		public static void closeConnection(Connection con, PreparedStatement stmt) {
			closeConnection(con);
				try {
					if (stmt != null) {
					stmt.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		/**
		 * Sobrecarga do metodo responsavel por fechar a conexão com bamco de dados 
		 * @param con - con objeto do tipo connection que sera fechado
		 * @param stmt - objeto do tipo preparedstation que sera fechado.
		 * @param rs - objeto do topo resultset que sera fechado.
		 */

	
public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs ) {
	closeConnection(con,stmt);
	
	try {
		if (rs !=null);
		rs.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}



