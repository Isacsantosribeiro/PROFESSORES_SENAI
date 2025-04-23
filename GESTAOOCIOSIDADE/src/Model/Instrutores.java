package Model;

public class Instrutores {

    private int idInstrutor; // Alterado para int e renomeado para clareza
    private String nome;
    private String cpf;

    public Instrutores() {
        super();
    }

    public Instrutores(int idInstrutor, String nome, String cpf) { // Alterado o tipo do parâmetro id
        super();
        this.idInstrutor = idInstrutor; // Usando o nome mais claro
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getIdInstrutor() { // Getter para o ID
        return idInstrutor;
    }

    public void setIdInstrutor(int idInstrutor) {
        this.idInstrutor = idInstrutor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return nome; // Para exibir o nome no ComboBox
    }
}