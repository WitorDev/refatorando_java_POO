import java.util.*;
import java.io.*;

public class MainTudoEmUm {
    private static Scanner sc = new Scanner(System.in);
    private static int totalEmprestimos = 0;

    public static void main(String[] args) {
        System.out.println("=== BIBLIOTECA BAGUNCADA ===");
        List<String> livros = carregarLivros(); // Persistencia misturada

        // criar classe livro com quantidade

        boolean encerrarLoop = false; // adicionado para gerenciar encerramento do loop

        while (!encerrarLoop) {
            // Entrada misturada com UI
            System.out.println("\n1-Adicionar 2-Emprestar 3-Listar 4-Sair");
            int opcaoDeEntrada = sc.nextInt(); // mudamos para opcaoDeEntrada, mais legivel
            sc.nextLine();

            switch (opcaoDeEntrada) {
                case 1:
                    salvarLivro(livros);
                    break;
                case 2:
                    emprestarLivro(livros);
                    break;
                case 3:
                    // Listagem com formatacao inline
                    System.out.println("Livros (" + livros.size() + "):");
                    for (String l : livros) {
                        System.out.println("- " + l);
                    }
                    break;
                case 4:
                    salvarLivro(livros);
                    System.out.println("Saindo...");
                    encerrarLoop = true;
                    break;
                default:
                    System.out.println("opcaoDeEntradacao invalida!");
            }
        }
    }

    private static List<String> carregarLivros() {
        try {
            List<String> livros = new ArrayList<String>(); // Persistencia em memoria
            File arquivo = new File("livros.txt");
            if (arquivo.exists()) {
                Scanner scannerArquivo = new Scanner(arquivo);
                while (scannerArquivo.hasNextLine()) { // adiciona os livros do arquivo .txt na lista "livros"
                    livros.add(scannerArquivo.nextLine());
                }
                scannerArquivo.close();
                return livros;
            }
            return null;
        } catch (Exception e) {
            System.out.println("Erro carregando: " + e.getMessage()); // carregando? precisa de trycatch:?
            return null;
        }
    }

    private static void salvarLivro(List<String> livros) {

        // Entrada + Validacao misturada
        try {
            System.out.print("Titulo: ");
            String titulo = sc.nextLine();
            if (titulo.length() < 3) { // Validacao inline
                throw new Exception("O título deve ser maior que 3 caracteres");
            }

            // Regra de negocio + Persistencia
            if (livros.contains(titulo.toLowerCase())) {
                System.out.println("Livro ja existe!");
            } else {
                livros.add(titulo);
                salvarLivro(livros); // Salva arquivo toda vez!
                System.out.println("Adicionado!");
            }

            PrintWriter printWriter = new PrintWriter("livros.txt");
            for (String livro : livros) {
                printWriter.println(livro);
            }
            printWriter.close();
        } catch (Exception e) {
            System.out.println("Erro salvando: " + e.getMessage());
        }
    }

    private static void emprestarLivro(List<String> livros) {
        // Emprestimo com regra misturada
        System.out.print("Titulo para emprestimo: ");
        String tituloEmprestimo = sc.nextLine().toLowerCase();
        boolean encontrado = false;
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).toLowerCase().contains(tituloEmprestimo)) {
                totalEmprestimos++;
                System.out.println("Emprestado! Total: " + totalEmprestimos);
                encontrado = true;
                // Saida misturada com logica
                System.out.println("Livros disponiveis: " + (livros.size() - totalEmprestimos));
                break;
            }
        }
        if (!encontrado)
            System.out.println("Livro nao encontrado!");
    }
}
