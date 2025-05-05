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

    public boolean cursoExiste(String nomeCurso) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            stmt = con.prepareStatement("SELECT 1 FROM CURSO WHERE nome = ?");
            stmt.setString(1, nomeCurso);
            rs = stmt.executeQuery();

            if (rs.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar se o curso já existe", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }

        return existe;
    }

    public boolean inserirCurso(Curso curso) {
        if (cursoExiste(curso.getNome())) {
            System.out.println("Curso já cadastrado.");
            return false; 
        }

        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO CURSO (nome) VALUES (?)");
            stmt.setString(1, curso.getNome());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Curso cadastrado com sucesso!");
                return true; 
            } else {
                System.out.println("Nenhum curso foi cadastrado.");
                return false; 
            }

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar curso: " + e.getMessage());
            throw new RuntimeException("Erro ao cadastrar curso", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }

    public ObservableList<Curso> buscarCursosDoBanco() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ObservableList<Curso> listaDeCursos = FXCollections.observableArrayList();
        try {
            stmt = con.prepareStatement("SELECT idCurso, nome FROM CURSO");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(rs.getInt("idCurso"));
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
                curso.setIdCurso(rs.getInt("idCurso"));
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

    public boolean delete(Curso curso) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM CURSO WHERE idCurso = ?");
            stmt.setInt(1, curso.getIdCurso());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Curso excluído com sucesso!");
                return true; 
            } else {
                System.out.println("Nenhum curso foi excluído.");
                return false;
            }
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
            stmt = con.prepareStatement("SELECT * FROM CURSO WHERE idCurso LIKE ? OR nome LIKE ?");
            stmt.setString(1, "%" + cursoBusca.getIdCurso() + "%");
            stmt.setString(2, "%" + cursoBusca.getNome() + "%");

            rs = stmt.executeQuery();

            while (rs.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(rs.getInt("idCurso"));
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