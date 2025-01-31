package ConectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionDatabase {
private static final  String  Driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
private static final String URL = "jdbc:sqlserver://localhost:50054;encrypt=false;databaseName=MERCADO;user=sa;password=Senailab05";
private static final String user = "sa";
private static final String password = "Senailab05";
	
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

	
public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs ) {
	
	
	try {
		if (rs !=null);
		rs.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}



