package entidades;

import java.util.ArrayList;
import java.util.List;

public class RepositorioFuncionario {

    private List<Funcionario> funcionarios = new ArrayList<>();

    // Metodo que adiciona um funcionário novo.
    public void adicionar(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    // Metodo para buscar funcionário por CPF
    public Funcionario buscarPorCpf (String cpf) {
        for (Funcionario f : funcionarios) {
            if (f.getCpf().equals(cpf)) {
                return f;
            }
        }
        return null;
    }

    // Metodo para remover funcionário
    public void remover (Funcionario funcionario) {
        funcionarios.remove(funcionario);
    }

    // Metodo para listar todos os funcionários
    public List<Funcionario> listarTodos() {
        return funcionarios;
    }
}
