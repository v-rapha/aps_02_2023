package dao;

import model.Editora;
import model.Livro;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDao implements Dao<Livro> {
  private final String INSERT = "INSERT INTO Books (title, isbn, price, publisher_id) VALUES (?, ?, ?, ?)";
  private final String UPDATE = "UPDATE Books SET title = ?, isbn = ?, price = ?, publisher_id = ? WHERE isbn = ?";
  private static final String DELETE = "DELETE FROM Books WHERE isbn = ?";
  private final String LIST = "SELECT * FROM Books";
  private final String LIST_PUBLISHERS_NAME = "SELECT name from Publishers";
  private final String LIST_AUTHORS_NAME = "SELECT name, fname from Authors";
  private final String ID_PUBLISHER = "SELECT publisher_id from Publishers WHERE name LIKE ?";
//  private final String LIST = "SELECT Books.isbn, Books.title, Books.price, Publishers.name AS publisher " +
//                              "FROM Books " +
//                              "INNER JOIN Publishers ON Books.publisher_id = Publishers.publisher_id;";
//  private final String LIST = """
//        SELECT A.name, A.fname, B.isbn, B.title, B.price, P.name AS publisher_name
//        FROM BooksAuthors BA
//        INNER JOIN Authors A ON BA.author_id = A.author_id
//        INNER JOIN Books B ON BA.isbn = B.isbn
//        INNER JOIN Publishers P ON B.publisher_id = P.publisher_id;""";
  private final String LIKE = "SELECT * FROM Books WHERE title LIKE ?";

  @Override
  public boolean create(Livro livro){
    Connection con = FabricaConexao.getConnection();
    PreparedStatement stnt = null;

    try {
      stnt = con.prepareStatement("INSERT INTO Books (title, isbn, price, publisher_id) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
      stnt.setString(1, livro.getTitulo());
      stnt.setString(2, livro.getIsbn());
      stnt.setDouble(3, livro.getPreco());
      stnt.setInt(4, livro.getIdEditora());
      stnt.executeUpdate();

      ResultSet generatedKeys = stnt.getGeneratedKeys();
      //System.out.println(generatedKeys.toString());
      if (generatedKeys.next()) {
        String generatedISBN = generatedKeys.getString(1);

        int seq_no = 1;
        for (int autorId : livro.getAutoresId()) {
          String autorSql = "INSERT INTO BooksAuthors (isbn, author_id, seq_no) VALUES (?, ?, ?)";
          PreparedStatement autorStmt = con.prepareStatement(autorSql);
          autorStmt.setString(1, generatedISBN);
          autorStmt.setInt(2, autorId);
          autorStmt.setInt(3, seq_no);
          autorStmt.executeUpdate();
          seq_no++;
        }
      }

      //return stnt.executeUpdate() != 0;
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } finally {
      FabricaConexao.closeConnection(con, stnt);
    }
  }

  @Override
  public List<Livro> findAll() {
    Connection con = FabricaConexao.getConnection();
    PreparedStatement stnt = null;
    ResultSet rs = null;

    List<Livro> livros = new ArrayList<>();

    try {
      stnt = con.prepareStatement(LIST);
      rs = stnt.executeQuery();

      while (rs.next()) {
        Livro livro = new Livro();

        livro.setTitulo(rs.getString("title"));
        livro.setIsbn(rs.getString("isbn"));
        livro.setPreco(rs.getDouble("price"));
        livro.setIdEditora(rs.getInt("publisher_id"));

        livros.add(livro);
      }

    } catch (SQLException e) {
      return null;
    } finally {
      FabricaConexao.closeConnection(con, stnt, rs);
    }

    return livros;
  }

  @Override
  public List<Livro> findByName(String s) {
    Connection con = FabricaConexao.getConnection();
    PreparedStatement stnt = null;
    ResultSet rs = null;

    List<Livro> livros = new ArrayList<>();

    try {
      stnt = con.prepareStatement(LIKE);
      stnt.setString(1, s + "%");
      rs = stnt.executeQuery();

      while (rs.next()) {
        Livro livro = new Livro();

        livro.setTitulo(rs.getString("title"));
        livro.setIsbn(rs.getString("isbn"));
        livro.setPreco(rs.getDouble("price"));
        livro.setIdEditora(rs.getInt("publisher_id"));

        livros.add(livro);
      }

    } catch (SQLException e) {
      return null;
    } finally {
      FabricaConexao.closeConnection(con, stnt, rs);
    }

    return livros;
  }

  @Override
  public boolean update(Livro livro) {
    Connection con = FabricaConexao.getConnection();
    PreparedStatement stnt = null;

    try {
      System.out.println("DAO " + livro);
      stnt = con.prepareStatement(UPDATE);
      stnt.setString(1, livro.getTitulo());
      stnt.setString(2, livro.getIsbn());
      stnt.setDouble(3, livro.getPreco());
      stnt.setInt(4, livro.getIdEditora());
      stnt.setString(5, livro.getIsbn());

      if (stnt.executeUpdate() != 0) {
        return true;
      }
    } catch (SQLException e) {
      return false;
    } finally {
      FabricaConexao.closeConnection(con, stnt);
    }

    return false;
  }

  @Override
  public boolean delete(Livro livro) {
    Connection con = FabricaConexao.getConnection();
    PreparedStatement stnt = null;

    try {
      stnt = con.prepareStatement(DELETE);
      stnt.setString(1, livro.getIsbn());

      if (stnt.executeUpdate() != 0) {
        return true;
      }
    } catch (SQLException e) {
      return false;
    } finally {
      FabricaConexao.closeConnection(con, stnt);
    }

    return false;
  }

  public List<String> getPublishersName() {
    Connection con = FabricaConexao.getConnection();
    PreparedStatement stnt = null;
    ResultSet rs = null;

    List<String> editNames = new ArrayList<>();

    try {
      stnt = con.prepareStatement(LIST_PUBLISHERS_NAME);
      rs = stnt.executeQuery();

      while (rs.next()) {
        String name = rs.getString("name");
        editNames.add(name);
      }

    } catch (SQLException e) {
      return null;
    } finally {
      FabricaConexao.closeConnection(con, stnt, rs);
    }

    return editNames;
  }

  public List<String> getAuthorsName() {
    Connection con = FabricaConexao.getConnection();
    PreparedStatement stnt = null;
    ResultSet rs = null;

    List<String> editNames = new ArrayList<>();

    try {
      stnt = con.prepareStatement(LIST_AUTHORS_NAME);
      rs = stnt.executeQuery();

      while (rs.next()) {
        String name = rs.getString("name");
        String fname = rs.getString("fname");
        editNames.add(name + ", " + fname);
      }

    } catch (SQLException e) {
      return null;
    } finally {
      FabricaConexao.closeConnection(con, stnt, rs);
    }

    return editNames;
  }

  public int getPublisherId(String nomeEditora) {
    Connection con = FabricaConexao.getConnection();
    PreparedStatement stnt = null;
    ResultSet rs = null;

    int idPublisher = 0;

    try {
      stnt = con.prepareStatement(ID_PUBLISHER);
      stnt.setString(1, nomeEditora);
      rs = stnt.executeQuery();

      if (rs.next()) {
        idPublisher = rs.getInt("publisher_id");
      }
    } catch (SQLException e) {
      return 0;
    } finally {
      FabricaConexao.closeConnection(con, stnt, rs);
    }

    return idPublisher;
  }

  public int getAutorId(String nome, String sobrenome) {
    Connection con = FabricaConexao.getConnection();
    PreparedStatement stnt = null;
    ResultSet rs = null;

    int autorId = 0;

    try {
      stnt = con.prepareStatement("SELECT author_id FROM Authors WHERE name = ? AND fname = ?");
      stnt.setString(1, nome);
      stnt.setString(2, sobrenome);
      rs = stnt.executeQuery();

      if (rs.next()) {
        autorId = rs.getInt("author_id");
      }
    } catch (SQLException e) {
      return 0;
    } finally {
      FabricaConexao.closeConnection(con, stnt, rs);
    }

    return autorId;
  }
}
