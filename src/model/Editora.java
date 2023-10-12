package model;

public class Editora {
  private int id;
  private String nome;
  private String url;

  public Editora(String aNome, String aUrl) {
    this.nome = aNome;
    this.url = aUrl;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public String toString() {
    return "Editora{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            ", url='" + url + '\'' +
            '}';
  }
}
