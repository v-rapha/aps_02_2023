package model;

public class Livraria {
  Livro livro;
  Autor autor;
  Editora editora;

  public Livraria() {}

  public Livraria(Livro aLivro, Autor aAutor, Editora aEditora) {
    this.livro = aLivro;
    this.autor = aAutor;
    this.editora = aEditora;
  }

  public Livro getLivro() {
    return livro;
  }

  public void setLivro(Livro livro) {
    this.livro = livro;
  }

  public Autor getAutor() {
    return autor;
  }

  public void setAutor(Autor autor) {
    this.autor = autor;
  }

  public Editora getEditora() {
    return editora;
  }

  public void setEditora(Editora editora) {
    this.editora = editora;
  }

  @Override
  public String toString() {
    return "Livraria{" +
            "livro=" + livro +
            ", autor=" + autor +
            ", editora=" + editora +
            '}';
  }
}
