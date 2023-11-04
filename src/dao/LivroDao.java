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
  private final String LIKE = "SELECT * FROM Books WHERE title LIKE ?";

  @Override
  public boolean create(Livro livro){
    Connection con = FabricaConexao.getConnection();
    PreparedStatement stnt = null;

    try {
      stnt = con.prepareStatement("INSERT INTO Books (title, isbn, price, publisher_id) VALUES (?, ?, ?, ?)");
      stnt.setString(1, livro.getTitulo());
      stnt.setString(2, livro.getIsbn());
      stnt.setDouble(3, livro.getPreco());
      stnt.setInt(4, livro.getIdEditora());
      stnt.executeUpdate();

      String selectIsbn = "SELECT isbn FROM Books WHERE isbn = ?";
      PreparedStatement selectStatement = con.prepareStatement(selectIsbn);
      selectStatement.setString(1, livro.getIsbn());

      ResultSet resultSet = selectStatement.executeQuery();

      if (resultSet.next()) {
        String generatedISBN = resultSet.getString("isbn");

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

      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } finally {
      FabricaConexao.closeConnection(con, stnt);
    }
  }

  // Sem uso
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

  // Sem uso
  @Override
  public List<Livro> findByName(String s, String s2) {
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
      // Verifique se o publisher_id é válido
      if (isPublisherIdValid(con, livro.getIdEditora())) {
        stnt = con.prepareStatement("UPDATE Books SET title = ?, isbn = ?, price = ?, publisher_id = ? WHERE isbn = ?");
        stnt.setString(1, livro.getTitulo());
        stnt.setString(2, livro.getIsbn());
        stnt.setDouble(3, livro.getPreco());
        stnt.setInt(4, livro.getIdEditora());
        stnt.setString(5, livro.getIsbn());

        if (stnt.executeUpdate() != 0) {
          return true;
        }
      } else {
        // O publisher_id não é válido
        return false;
      }
    } catch (SQLException e) {
      e.printStackTrace();
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
      // Verifica se há registros relacionados na tabela BooksAuthors
      String checkRelatedAuthorsQuery = "SELECT * FROM BooksAuthors WHERE isbn = ?";
      PreparedStatement checkAuthorsStnt = con.prepareStatement(checkRelatedAuthorsQuery);
      checkAuthorsStnt.setString(1, livro.getIsbn());
      ResultSet authorsResult = checkAuthorsStnt.executeQuery();

      if (authorsResult.next()) {
        // Existem registros relacionados na tabela BooksAuthors.

        // Exclui registros relacionados na tabela BooksAuthors
        String deleteAuthorsQuery = "DELETE FROM BooksAuthors WHERE isbn = ?";
        PreparedStatement deleteAuthorsStnt = con.prepareStatement(deleteAuthorsQuery);
        deleteAuthorsStnt.setString(1, livro.getIsbn());
        deleteAuthorsStnt.executeUpdate();
      }

      stnt = con.prepareStatement("DELETE FROM Books WHERE isbn = ?");
      stnt.setString(1, livro.getIsbn());

      if (stnt.executeUpdate() != 0) {
        return true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } finally {
      FabricaConexao.closeConnection(con, stnt);
    }

    return false;
  }

  private boolean isPublisherIdValid(Connection con, int publisherId) throws SQLException {
    // Verifica se o publisher_id é válido consultando a tabela Publishers
    PreparedStatement stnt = con.prepareStatement("SELECT publisher_id FROM Publishers WHERE publisher_id = ?");
    stnt.setInt(1, publisherId);
    ResultSet resultSet = stnt.executeQuery();
    return resultSet.next(); // Se o resultado for vazio, o publisher_id não é válido
  }





//  public int getPublisherId(String nomeEditora) {
//    Connection con = FabricaConexao.getConnection();
//    PreparedStatement stnt = null;
//    ResultSet rs = null;
//
//    int idPublisher = 0;
//
//    try {
//      stnt = con.prepareStatement("SELECT publisher_id from Publishers WHERE name LIKE ?");
//      stnt.setString(1, nomeEditora);
//      rs = stnt.executeQuery();
//
//      if (rs.next()) {
//        idPublisher = rs.getInt("publisher_id");
//      }
//    } catch (SQLException e) {
//      return 0;
//    } finally {
//      FabricaConexao.closeConnection(con, stnt, rs);
//    }
//
//    return idPublisher;
//  }

//  public int getAutorId(String nome, String sobrenome) {
//    Connection con = FabricaConexao.getConnection();
//    PreparedStatement stnt = null;
//    ResultSet rs = null;
//
//    int autorId = 0;
//
//    try {
//      stnt = con.prepareStatement("SELECT author_id FROM Authors WHERE name = ? AND fname = ?");
//      stnt.setString(1, nome);
//      stnt.setString(2, sobrenome);
//      rs = stnt.executeQuery();
//
//      if (rs.next()) {
//        autorId = rs.getInt("author_id");
//      }
//    } catch (SQLException e) {
//      return 0;
//    } finally {
//      FabricaConexao.closeConnection(con, stnt, rs);
//    }
//
//    return autorId;
//  }
}
