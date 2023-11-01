package model;

import java.util.Arrays;
import java.util.List;

public class Livro {
  private String titulo;
  private String isbn;
  private double preco;
  private int idEditora;
  private List<Integer> autoresId;

  public Livro() {}

  public Livro(String aTitulo, String aIsbn, double aPreco, int aIdEditora, List<Integer> aAutoresId) {
    this.titulo = aTitulo;
    this.isbn = aIsbn;
    this.preco = aPreco;
    this.idEditora = aIdEditora;
    this.autoresId = aAutoresId;
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

  public List<Integer> getAutoresId() {
    return autoresId;
  }

  public void setAutoresId(List<Integer> autoresId) {
    this.autoresId = autoresId;
  }

  @Override
  public String toString() {
    return "Livro{" +
            "titulo='" + titulo + '\'' +
            ", isbn='" + isbn + '\'' +
            ", preco=" + preco +
            ", idEditora=" + idEditora +
            ", autoresId=" + autoresId +
            '}';
  }
}
