package aplicacao;

import entidades.Pessoa;
import entidades.RepositorioPessoa;
import java.util.Scanner;

public class SistemaCadastro {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RepositorioPessoa repositorio = new RepositorioPessoa(); // Instancia o repositório
        int opcao = 0;

        while (opcao != 5) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Cadastrar | 2. Editar | 3. Buscar | 4. Excluir | 5. Sair");
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

                case 2: // EDITAR
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

                case 3: // BUSCAR
                    System.out.println("\n--- Buscar ---");
                    System.out.print("CPF: ");
                    String cpfBusca = scanner.nextLine();
                    Pessoa pBusca = repositorio.buscarPorCpf(cpfBusca);
                    if(pBusca != null) System.out.println(pBusca);
                    else System.out.println("Não encontrado.");
                    break;

                case 4: // EXCLUIR (CORRIGIDO)
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

                case 5:
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