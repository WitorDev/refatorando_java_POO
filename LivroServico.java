import java.io.*;
import java.util.*;

public class LivroServico {

    private static final String ARQUIVO = "livros.txt";

    public List<Livro> carregar() {
        List<Livro> livros = new ArrayList<>();

        File file = new File(ARQUIVO);
        if (!file.exists()) {
            return livros;
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                // Adicionado quantidade de livros para cada livro
                String[] dados = sc.nextLine().split(";");
                livros.add(new Livro(dados[0], Integer.parseInt(dados[1])));
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar livros");
        }

        return livros;
    }

    public void salvar(List<Livro> livros) {
        // Adicionado o charset UTF-8 para evitar problemas de acentuação, caso haja
        try (PrintWriter pw = new PrintWriter(ARQUIVO, java.nio.charset.StandardCharsets.UTF_8)) {
            for (Livro livro : livros) {
                pw.println(livro.getTitulo() + ";" + livro.getQuantidade());
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar livros");
        }
    }
}