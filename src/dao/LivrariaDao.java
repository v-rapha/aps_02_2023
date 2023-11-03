package dao;

import jdk.jshell.spi.SPIResolutionException;
import model.Autor;
import model.Editora;
import model.Livraria;
import model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivrariaDao implements Dao<Livraria> {
  private final String SELECT_ALL = """
           SELECT A.name, A.fname, B.isbn, B.title, B.price, P.name AS publisher_name
           FROM BooksAuthors BA
           INNER JOIN Authors A ON BA.author_id = A.author_id
           INNER JOIN Books B ON BA.isbn = B.isbn
           INNER JOIN Publishers P ON B.publisher_id = P.publisher_id;
          """;
  private final String SELECT_BY_NAME = """
           SELECT A.name, A.fname, B.isbn, B.title, B.price, P.name AS publisher_name
           FROM BooksAuthors BA
           INNER JOIN Authors A ON BA.author_id = A.author_id
           INNER JOIN Books B ON BA.isbn = B.isbn
           INNER JOIN Publishers P ON B.publisher_id = P.publisher_id
           WHERE B.title LIKE ?;
          """;
  @Override
  public boolean create(Livraria entity) {
    return false;
  }

  @Override
  public List<Livraria> findAll() {
    Connection con = FabricaConexao.getConnection();
    PreparedStatement stnt = null;
    ResultSet rs = null;

    List<Livraria> livrariaList = new ArrayList<>();

    try {
      stnt = con.prepareStatement(SELECT_ALL);
      rs = stnt.executeQuery();

      while (rs.next()) {
        String name = rs.getString("name");
        String fname = rs.getString("fname");
        String title = rs.getString("title");
        String isbn = rs.getString("isbn");
        double price = rs.getDouble("price");
        String publisher_name = rs.getString("publisher_name");

        Autor autor = new Autor(name, fname);
        Livro livro = new Livro(title, isbn, price);
        Editora editora = new Editora();
        editora.setNome(publisher_name);

        Livraria livraria = new Livraria(livro, autor, editora);

        livrariaList.add(livraria);
      }

    } catch (SQLException e) {
      return null;
    } finally {
      FabricaConexao.closeConnection(con, stnt, rs);
    }

    return livrariaList;
  }

  @Override
  public List<Livraria> findByName(String s, String s2) {
    Connection con = FabricaConexao.getConnection();
    PreparedStatement stnt = null;
    ResultSet rs = null;

    List<Livraria> livros = new ArrayList<>();

    try {
      stnt = con.prepareStatement(SELECT_BY_NAME);
      stnt.setString(1, s + "%");
      rs = stnt.executeQuery();

      while (rs.next()) {
        String name = rs.getString("name");
        String fname = rs.getString("fname");
        String title = rs.getString("title");
        String isbn = rs.getString("isbn");
        double price = rs.getDouble("price");
        String publisher_name = rs.getString("publisher_name");

        Autor autor = new Autor(name, fname);
        Livro livro = new Livro(title, isbn, price);
        Editora editora = new Editora();
        editora.setNome(publisher_name);

        Livraria livraria = new Livraria(livro, autor, editora);

        livros.add(livraria);
      }

    } catch (SQLException e) {
      return null;
    } finally {
      FabricaConexao.closeConnection(con, stnt, rs);
    }

    return livros;
  }

  @Override
  public boolean update(Livraria entity) {
    return false;
  }

  @Override
  public boolean delete(Livraria entity) {
    return false;
  }
}
