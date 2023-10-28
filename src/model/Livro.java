package model;

public class Livro {
  private String titulo;
  private String isbn;
  private double preco;
  private int idEditora;

  public Livro() {}

  public Livro(String aTitulo, String aIsbn, double aPreco, int aIdEditora) {
    this.titulo = aTitulo;
    this.isbn = aIsbn;
    this.preco = aPreco;
    this.idEditora = aIdEditora;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public double getPreco() {
    return preco;
  }

  public void setPreco(double preco) {
    this.preco = preco;
  }

  public int getIdEditora() {
    return idEditora;
  }

  public void setIdEditora(int idEditora) {
    this.idEditora = idEditora;
  }

  @Override
  public String toString() {
    return "Livro{" +
            "titulo='" + titulo + '\'' +
            ", isbn='" + isbn + '\'' +
            ", preco=" + preco +
            ", idEditora=" + idEditora +
            '}';
  }
}
