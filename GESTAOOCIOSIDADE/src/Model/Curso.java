package Model;

public class Curso {

    private int idCurso; 
    private String nome;

    public Curso() {
        super();
    }

    public Curso(int idCurso, String nome) { 
        super();
        this.idCurso = idCurso; 
        this.nome = nome;
    }

    public int getIdCurso() { 
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome; 
    }
}