import java.util.*;

public class MainTudoEmUm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BibliotecaServico bibliotecaServico = new BibliotecaServico(new LivroServico());

        boolean executar = true;

        while (executar) {
            System.out.println("\n1-Adicionar 2-Emprestar 3-Listar 4-Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcao) {
                    case 1:
                        System.out.print("Título: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Quantidade: ");
                        int qtd = scanner.nextInt();
                        scanner.nextLine();
                        bibliotecaServico.adicionarLivro(titulo, qtd);
                        System.out.println("Livro adicionado");
                        break;

                    case 2:
                        System.out.print("Título: ");
                        bibliotecaServico.emprestarLivro(scanner.nextLine());
                        System.out.println("Empréstimo realizado");
                        break;

                    case 3:
                        bibliotecaServico.listar().forEach(System.out::println);
                        break;

                    case 4:
                        executar = false;
                        System.out.println("Saindo...");
                        break;

                    default:
                        System.out.println("Opção inválida");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        scanner.close();
    }
}