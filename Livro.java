// Daria para utilizar notações com o LomBok, para não precisar de setter, getter e toString....
public class Livro {
    private String titulo;
    private int quantidade;

    public Livro(String titulo, int quantidade) {
        this.titulo = titulo;
        this.quantidade = quantidade;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void emprestar() {
        if (quantidade <= 0) {
            throw new IllegalStateException("Livro indisponível");
        }
        quantidade--;
    }

    @Override
    public String toString() {
        return titulo + " (qtd: " + quantidade + ")";
    }
}