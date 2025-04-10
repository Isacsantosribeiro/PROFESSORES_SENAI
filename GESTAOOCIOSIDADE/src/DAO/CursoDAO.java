package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ConnectionFactory.ConnectionDatabase;
import Model.Curso;

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
}
