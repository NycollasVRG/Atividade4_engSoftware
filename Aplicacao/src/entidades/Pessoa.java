package entidades;

public class Pessoa {
    // Campos simples
    private String cpf;
    private String nome;
    private String telefone;
    private String endereco;

    // Construtor
    public Pessoa(String cpf, String nome, String telefone, String endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    // Getters e Setters (para podermos editar e buscar)
    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    // Método para imprimir os dados
    @Override
    public String toString() {
        return "CPF: " + cpf + " | Nome: " + nome +
                " | Tel: " + telefone + " | Endereço: " + endereco;
    }
}
