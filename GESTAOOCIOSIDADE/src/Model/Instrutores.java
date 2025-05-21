package Model;

public class Instrutores {

    private int idInstrutor;
    private String nome;
    private String cpf;

    public Instrutores() {
        super();
    }

    public Instrutores(int idInstrutor, String nome, String cpf) { 
        super();
        this.idInstrutor = idInstrutor; 
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getIdInstrutor() { 
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
        return nome; 
    }
}