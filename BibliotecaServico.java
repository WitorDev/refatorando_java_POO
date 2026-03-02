import java.util.*;

public class BibliotecaServico {

    private List<Livro> livros;
    private LivroServico livroServico;

    public BibliotecaServico(LivroServico livroServico) {
        this.livroServico = livroServico;
        this.livros = livroServico.carregar();
    }

    public void adicionarLivro(String titulo, int quantidade) {
        if (titulo == null || titulo.length() < 3) {
            throw new IllegalArgumentException("Título inválido");
        }

        if (buscarLivroPorTitulo(titulo).isPresent()) {
            throw new IllegalStateException("Livro já existe");
        }

        livros.add(new Livro(titulo, quantidade));
        livroServico.salvar(livros);
    }

    public void emprestarLivro(String titulo) {
        Livro livro = buscarLivroPorTitulo(titulo)
                .orElseThrow(() -> new IllegalStateException("Livro não encontrado"));

        livro.emprestar();
        livroServico.salvar(livros);
    }

    public List<Livro> listar() {
        return Collections.unmodifiableList(livros);
    }
    // Retorna um Optional<Entity> para evitar o uso de null e facilitar o tratamento de erros 
    private Optional<Livro> buscarLivroPorTitulo(String titulo) {
        return livros.stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                .findFirst();
    }
}