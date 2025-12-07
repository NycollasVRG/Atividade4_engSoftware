package aplicacao;

import entidades.Pessoa;
import entidades.RepositorioPessoa;
import java.util.Scanner;
import entidades.Funcionario;
import entidades.RepositorioFuncionario;

public class SistemaCadastro {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RepositorioPessoa repositorio = new RepositorioPessoa(); // Instancia o repositório
        RepositorioFuncionario repositorioFuncionario = new RepositorioFuncionario(); // Instancia o repositório de funcionário
        int opcao = 0;

        while (opcao != 6) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Cadastrar | 2. Funcionário | 3. Editar | 4. Buscar | 5. Excluir | 6. Sair");
            System.out.print("Opção: ");

            // Tratamento simples para evitar erro se digitar letra
            if(scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa buffer
            } else {
                scanner.nextLine(); // Limpa a entrada inválida
                opcao = 0; // Força cair no default
            }

            switch (opcao) {
                case 1: // CADASTRAR
                    System.out.println("\n--- Cadastrar ---");
                    // Chama nosso método auxiliar lá de baixo
                    String cpf = lerCpfValido(scanner);

                    if (repositorio.buscarPorCpf(cpf) != null) {
                        System.out.println("Erro: CPF já existe!");
                    } else {
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Telefone: ");
                        String tel = scanner.nextLine();
                        System.out.print("Endereço: ");
                        String end = scanner.nextLine();

                        repositorio.adicionar(new Pessoa(cpf, nome, tel, end));
                        System.out.println("Cadastrado com sucesso!");
                    }
                    break;

                case 2: // FUNCIONARIO
                    int opcaoFunc = 0;

                    while (opcaoFunc != 5) {
                        System.out.println("\n--- Funcionário ---");
                        System.out.println("1. Cadastrar | 2. Editar | 3. Buscar | 4. Excluir | 5. Voltar");
                        System.out.print("Opção: ");

                        if (scanner.hasNextInt()) {
                            opcaoFunc = scanner.nextInt();
                            scanner.nextLine();
                        } else {
                            scanner.nextLine();
                            opcaoFunc = 0;
                        }

                        switch (opcaoFunc) {
                            case 1:
                                System.out.println("\n--- Cadastrar Funcionário ---");
                                String cpfF = lerCpfValido(scanner);

                                if (repositorioFuncionario.buscarPorCpf(cpfF) != null) {
                                    System.out.println("Erro: CPF de Funcionário já existe!");
                                } else {
                                    // Dados da classe Pessoa
                                    System.out.print("Nome: ");
                                    String nomeF = scanner.nextLine();
                                    System.out.print("Telefone: ");
                                    String telF = scanner.nextLine();
                                    System.out.print("Endereço: ");
                                    String endF = scanner.nextLine();

                                    // Dados da classe Funcionário
                                    System.out.print("Matrícula: ");
                                    String matricula = scanner.nextLine();
                                    System.out.print("Cargo: ");
                                    String cargo = scanner.nextLine();
                                    System.out.print("Salário: ");

                                    double salario = 0;
                                    try {
                                        salario = Double.parseDouble(scanner.nextLine());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Salário inválido. Usando 0.0.");
                                    }

                                    // Regra de Negócio: Salário positivo
                                    if (salario <= 0) {
                                        System.out.println("Erro: Salário deve ser positivo. Cadastro cancelado.");
                                        break;
                                    }

                                    Funcionario novoFuncionario = new Funcionario(cpfF, nomeF, telF, endF, matricula, cargo, salario);
                                    repositorioFuncionario.adicionar(novoFuncionario);
                                    System.out.println("Funcionário cadastrado com sucesso!");
                                }
                                break;

                            case 2:  // EDITAR FUNCIONARIO
                                System.out.println("\n--- Editar Funcionário ---");
                                System.out.print("CPF para editar: ");
                                String cpfEdit = scanner.nextLine();
                                Funcionario fEdit = repositorioFuncionario.buscarPorCpf(cpfEdit);

                                if (fEdit != null) {
                                    System.out.print("Novo nome: ");
                                    fEdit.setNome(scanner.nextLine());
                                    System.out.print("Novo telefone: ");
                                    fEdit.setTelefone(scanner.nextLine());
                                    System.out.print("Novo endereco: ");
                                    fEdit.setEndereco(scanner.nextLine());

                                    System.out.print("Novo cargo: ");
                                    fEdit.setCargo(scanner.nextLine());

                                    System.out.print("Novo salario: ");
                                    String novoSalarioStr = scanner.nextLine();
                                    if (!novoSalarioStr.isEmpty()) {
                                        try {
                                            double novoSalario = Double.parseDouble(novoSalarioStr);
                                            if (novoSalario > 0) {
                                                fEdit.setSalario(novoSalario);
                                            } else {
                                                System.out.println("Formato de Salário inválido ou não positivo. Mantendo o anterior.");
                                            }
                                        } catch (NumberFormatException e) {
                                            System.out.println("Formato de Salário inválido. Mantendo o anterior.");
                                        }
                                    }
                                    System.out.println("Atualizado");
                                } else {
                                    System.out.println("Funcionário não encontrado.");
                                }
                                break;

                            case 3: // BUSCAR FUNCIONÁRIO
                                System.out.println("\n--- Buscar Funcionário ---");
                                System.out.print("CPF: ");
                                String cpfBuscaF = scanner.nextLine();
                                Funcionario fBusca = repositorioFuncionario.buscarPorCpf(cpfBuscaF);

                                if (fBusca != null) {
                                    System.out.println(fBusca);
                                } else {
                                    System.out.println("Não encontrado.");
                                }
                                break;

                            case 4: // EXCLUIR FUNCIONÁRIO
                                System.out.println("\n--- Excluir Funcionário ---");
                                System.out.print("CPF para excluir: ");
                                String cpfExcluirF = scanner.nextLine();
                                Funcionario fExcluir = repositorioFuncionario.buscarPorCpf(cpfExcluirF);

                                if (fExcluir != null) {
                                    repositorioFuncionario.remover(fExcluir);
                                    System.out.println("Removido com sucesso.");
                                } else {
                                    System.out.println("Funcionário não encontrado.");
                                }
                                break;

                            case 5:
                                System.out.println("Voltando ao menu principal...");
                                break;

                            default:
                                System.out.println("Opção inválida!");
                        }
                    }
                    break;

                case 3: // EDITAR
                    System.out.println("\n--- Editar ---");
                    System.out.print("CPF para editar: ");
                    String cpfEdit = scanner.nextLine();
                    Pessoa pEdit = repositorio.buscarPorCpf(cpfEdit);

                    if(pEdit != null) {
                        System.out.print("Novo Nome: ");
                        pEdit.setNome(scanner.nextLine());
                        System.out.print("Novo Telefone: ");
                        pEdit.setTelefone(scanner.nextLine());
                        System.out.print("Novo Endereço: ");
                        pEdit.setEndereco(scanner.nextLine());
                        System.out.println("Atualizado!");
                    } else {
                        System.out.println("Não encontrado.");
                    }
                    break;

                case 4: // BUSCAR
                    System.out.println("\n--- Buscar ---");
                    System.out.print("CPF: ");
                    String cpfBusca = scanner.nextLine();
                    Pessoa pBusca = repositorio.buscarPorCpf(cpfBusca);
                    if(pBusca != null) System.out.println(pBusca);
                    else System.out.println("Não encontrado.");
                    break;

                case 5: // EXCLUIR (CORRIGIDO)
                    System.out.println("\n--- Excluir ---");
                    System.out.print("CPF para excluir: ");
                    String cpfExcluir = scanner.nextLine();

                    // 1. Busca usando o repositório
                    Pessoa pExcluir = repositorio.buscarPorCpf(cpfExcluir);

                    if (pExcluir != null) {
                        // 2. Remove usando o repositório
                        repositorio.remover(pExcluir);
                        System.out.println("Removido com sucesso.");
                    } else {
                        System.out.println("Pessoa não encontrada.");
                    }
                    break;

                case 6:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }

    private static String lerCpfValido(Scanner scanner) {
        while (true) {
            System.out.print("Digite o CPF (11 dígitos numéricos): ");
            String lido = scanner.nextLine();
            if (lido.length() == 11 && lido.matches("[0-9]+")) {
                return lido;
            }
            System.out.println("CPF inválido! Tente novamente.");
        }
    }

}