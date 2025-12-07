package entidades;

public class Funcionario extends Pessoa {

    private String matricula;
    private String cargo;
    private double salario;

    // Construtor completo, chamando o construtor da classe Pessoa (super)
    public Funcionario(String cpf, String nome, String telefone, String endereco, String matricula,
                       String cargo, double salario) {
        super(cpf, nome, telefone, endereco);
        this.matricula = matricula;
        this.cargo = cargo;
        this.salario = salario;
    }

    // Getters e Setters do Funcionário
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    // Metodo para imprimir os dados
    @Override
    public String toString() {
        return super.toString() +
                " | Matrícula: " + matricula +
                " | Cargo: " + cargo +
                " | Salário: R$" + String.format("%.2f", salario);
    }
}
