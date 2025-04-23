package Model;

public class Curso {

    private int idCurso; // Alterado para int e renomeado para clareza
    private String nome;

    public Curso() {
        super();
    }

    public Curso(int idCurso, String nome) { // Alterado o tipo do parâmetro id
        super();
        this.idCurso = idCurso; // Usando o nome mais claro
        this.nome = nome;
    }

    public int getIdCurso() { // Getter para o ID
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
        return nome; // Para exibir o nome no ComboBox
    }
}