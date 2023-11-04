package dao;

import model.Editora;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditoraDao implements Dao<Editora> {
  private final String INSERT = "INSERT INTO Publishers (name, url) VALUES (?, ?)";
  private final String LIST = "SELECT * FROM Publishers";
  private final String LIKE = "SELECT * FROM Publishers WHERE name LIKE ?";
  private final String UPDATE = "UPDATE Publishers SET name = ?, url = ? WHERE publisher_id = ?";
  private static final String DELETE = "DELETE FROM Publishers WHERE publisher_id = ?";

  @Override
  public boolean create(Editora editora) {
    Connection con = FabricaConexao.getConnection();
    PreparedStatement st = null;

    try {
      st = con.prepareStatement(INSERT);
      st.setString(1, editora.getNome());
      st.setString(2, editora.getUrl());

      if (st.executeUpdate() != 0) {
        return true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    } finally {
      FabricaConexao.closeConnection(con, st);
    }

    return false;
  }

  @Override
  public List<Editora> findAll() {
    Connection con = FabricaConexao.getConnection();
    PreparedStatement stnt = null;
    ResultSet rs = null;

    List<Editora> editoras = new ArrayList<>();

    try {
      stnt = con.prepareStatement(LIST);
      rs = stnt.executeQuery();

      while (rs.next()) {
        Editora editora = new Editora();

        editora.setId(rs.getInt("publisher_id"));
        editora.setNome(rs.getString("name"));
        editora.setUrl(rs.getString("url"));

        editoras.add(editora);
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    } finally {
      FabricaConexao.closeConnection(con, stnt, rs);
    }

    return editoras;
  }

  @Override
  public List<Editora> findByName(String s, String s2) {
    Connection con = FabricaConexao.getConnection();
    PreparedStatement stnt = null;
    ResultSet rs = null;

    List<Editora> editoras = new ArrayList<>();

    try {
      stnt = con.prepareStatement(LIKE);
      stnt.setString(1, s + "%");
      rs = stnt.executeQuery();

      while (rs.next()) {
        Editora editora = new Editora();

        editora.setId(rs.getInt("publisher_id"));
        editora.setNome(rs.getString("name"));
        editora.setUrl(rs.getString("url"));

        editoras.add(editora);
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    } finally {
      FabricaConexao.closeConnection(con, stnt, rs);
    }

    return editoras;
  }

  @Override
  public boolean update(Editora editora) {
    Connection con = FabricaConexao.getConnection();
    PreparedStatement stnt = null;

    try {
      stnt = con.prepareStatement(UPDATE);
      stnt.setString(1, editora.getNome());
      stnt.setString(2, editora.getUrl());
      stnt.setInt(3, editora.getId());

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

  @Override
  public boolean delete(Editora editora) {
    Connection con = FabricaConexao.getConnection();
    PreparedStatement stnt = null;

    try {
      // Verifica se há registros relacionados na tabela Books
      String checkRelatedBooksQuery = "SELECT * FROM Books WHERE publisher_id = ?";
      PreparedStatement checkBooksStnt = con.prepareStatement(checkRelatedBooksQuery);
      checkBooksStnt.setInt(1, editora.getId());
      ResultSet booksResult = checkBooksStnt.executeQuery();

      if (booksResult.next()) {
        // Existem livros relacionados a esta editora.

        // Exclui os livros relacionados:
        String isbn = booksResult.getString("isbn");
        String deleteBooksAuthorsQuery = "DELETE FROM BooksAuthors WHERE isbn = ?";
        PreparedStatement deleteBooksAuthorsStnt = con.prepareStatement(deleteBooksAuthorsQuery);
        deleteBooksAuthorsStnt.setString(1, isbn);
        deleteBooksAuthorsStnt.executeUpdate();

        // Exclui o livro da tabela Books.
        String deleteBooksQuery = "DELETE FROM Books WHERE isbn = ?";
        PreparedStatement deleteBooksStnt = con.prepareStatement(deleteBooksQuery);
        deleteBooksStnt.setString(1, isbn);
        deleteBooksStnt.executeUpdate();
      }

      stnt = con.prepareStatement(DELETE);
      stnt.setInt(1, editora.getId());

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

  public int getIdEditora(String nomeEditora) {
    Connection con = FabricaConexao.getConnection();
    PreparedStatement stnt = null;
    ResultSet rs = null;

    int idEditora = 0;

    try {
      stnt = con.prepareStatement("SELECT publisher_id from Publishers WHERE name LIKE ?");
      stnt.setString(1, nomeEditora);
      rs = stnt.executeQuery();

      if (rs.next()) {
        idEditora = rs.getInt("publisher_id");
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    } finally {
      FabricaConexao.closeConnection(con, stnt, rs);
    }

    return idEditora;
  }

  public List<String> getNomesEditoras() {
    Connection con = FabricaConexao.getConnection();
    PreparedStatement stnt = null;
    ResultSet rs = null;

    List<String> nomesEditoras = new ArrayList<>();

    try {
      stnt = con.prepareStatement("SELECT name from Publishers");
      rs = stnt.executeQuery();

      while (rs.next()) {
        String name = rs.getString("name");
        nomesEditoras.add(name);
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    } finally {
      FabricaConexao.closeConnection(con, stnt, rs);
    }

    return nomesEditoras;
  }
}
