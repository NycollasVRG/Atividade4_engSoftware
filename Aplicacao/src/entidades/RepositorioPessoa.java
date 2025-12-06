package entidades;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPessoa {
    // A lista fica escondida aqui dentro
    private List<Pessoa> listaDePessoas;

    public RepositorioPessoa() {
        this.listaDePessoas = new ArrayList<>();
    }

    // Método para adicionar
    public void adicionar(Pessoa p) {
        listaDePessoas.add(p);
    }

    // Método para remover
    public void remover(Pessoa p) {
        listaDePessoas.remove(p);
    }

    public Pessoa buscarPorCpf(String cpf) {
        for (Pessoa p : listaDePessoas) {
            if (p.getCpf().equals(cpf)) {
                return p;
            }
        }
        return null; // Não achou
    }
}
