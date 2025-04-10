package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ConnectionFactory.ConnectionDatabase;
import Model.Curso;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CursoDAO {

    public void inserirCurso(Curso curso) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO CURSO (nome) VALUES (?)");
            stmt.setString(1, curso.getNome());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Curso cadastrado com sucesso!");
            } else {
                System.out.println("Nenhum curso foi cadastrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar curso: " + e.getMessage());
            throw new RuntimeException("Erro ao cadastrar curso", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
    public ObservableList<String> buscarCursosDoBanco() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ObservableList<String> Cursos = FXCollections.observableArrayList();
        try {
            stmt = con.prepareStatement("select nome from CURSO"); 
            rs = stmt.executeQuery();

            while (rs.next()) {
            	String f = rs.getString(1);
            	Cursos.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return Cursos;
    }
}
