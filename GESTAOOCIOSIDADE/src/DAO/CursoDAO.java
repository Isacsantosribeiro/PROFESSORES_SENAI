package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    
    public ArrayList<Curso> read() { 
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Curso> listaDeCursos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM CURSO"); 
            rs = stmt.executeQuery();

            while (rs.next()) {
                Curso curso = new Curso();
                curso.setId(rs.getString("idCurso")); 
                curso.setNome(rs.getString("nome"));
                listaDeCursos.add(curso);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler os cursos!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        return listaDeCursos;
    }
    
    public void delete(Curso curso) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM CURSO WHERE idCurso = ? OR nome = ?");
            stmt.setString(1, curso.getId());   
            stmt.setString(2, curso.getNome());

            stmt.executeUpdate();
            System.out.println("Curso excluído com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir curso!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
    public ArrayList<Curso> search(Curso cursoBusca) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Curso> cursos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM curso WHERE idCurso LIKE ? OR nome LIKE ?");
            stmt.setString(1, "%" + cursoBusca.getId() + "%");
            stmt.setString(2, "%" + cursoBusca.getNome() + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                Curso curso = new Curso();
                curso.setId(rs.getString("idCurso"));   
                curso.setNome(rs.getString("nome"));

                cursos.add(curso);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cursos!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return cursos;
    }


}

