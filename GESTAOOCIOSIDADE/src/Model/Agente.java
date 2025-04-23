package Model;

public class Agente {
    private int idAgente; // Alterado para int e renomeado para clareza
    private String nome;
    private String cpf;
    private String senha;

    public Agente() {
        super();
    }

    public Agente(int idAgente, String nome, String cpf, String senha) { // Alterado o tipo do parâmetro id
        super();
        this.idAgente = idAgente; // Usando o nome mais claro
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    public int getIdAgente() { // Getter para o ID
        return idAgente;
    }

    public void setIdAgente(int idAgente) {
        this.idAgente = idAgente;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return nome; // Para exibir o nome no ComboBox
    }
}