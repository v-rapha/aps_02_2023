package dao;

import model.Editora;
import model.Livro;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDao implements Dao<Livro> {
  private final String INSERT = "INSERT INTO Books (title, isbn, price, publisher_id) VALUES (?, ?, ?, ?)";
  private final String UPDATE = "UPDATE Books SET title = ?, isbn = ?, price = ?, publisher_id = ? WHERE isbn = ?";
  private static final String DELETE = "DELETE FROM Books WHERE isbn = ?";
  private final String LIST = "SELECT * FROM Books";
//  private final String LIST = "SELECT Books.isbn, Books.title, Books.price, Publishers.name AS publisher " +
//                              "FROM Books " +
//                              "INNER JOIN Publishers ON Books.publisher_id = Publishers.publisher_id;";
  private final String LIKE = "SELECT * FROM Books WHERE title LIKE ?";

  @Override
  public boolean create(Livro livro) {
    // A conexão com o banco é aberta utilizando o método.getConnection() da classe
    // FabricaConexao
    Connection con = FabricaConexao.getConnection();
    // Uma variável do tipo PreparedStatement é declarada
    PreparedStatement stnt = null;

    try {
      stnt = con.prepareStatement(INSERT);
      stnt.setString(1, livro.getTitulo());
      stnt.setString(2, livro.getIsbn());
      stnt.setDouble(3, livro.getPreco());
      stnt.setInt(4, livro.getIdEditora());

      if (stnt.executeUpdate() != 0) {
        // Se alterou pelo menos 1 linha, então o comando deu certo
        return true;
      }
    } catch (SQLException e) {
      // Caso ocorra algum erro uma mensagem é mostrada ao usuário
      return false;
    } finally {
      // Caso o comando seja executado ou ocorra algum erro, a conexão com o banco é
      // fechada
      FabricaConexao.closeConnection(con, stnt);
    }
    return false;
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
      JOptionPane.showMessageDialog(null, "Erro ao listar " + e.getMessage());
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
}
