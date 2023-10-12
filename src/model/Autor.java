package model;

public class Autor {
  private int id;
  private String nome;
  private String sobrenome;

  public Autor(String aNome, String aSobrenome) {
    this.nome = aNome;
    this.sobrenome = aSobrenome;
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

  public String getSobrenome() {
    return sobrenome;
  }

  public void setSobrenome(String sobrenome) {
    this.sobrenome = sobrenome;
  }

  @Override
  public String toString() {
    return "Autor{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            ", sobrenome='" + sobrenome + '\'' +
            '}';
  }
}
