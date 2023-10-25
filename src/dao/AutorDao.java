package dao;

import model.Autor;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDao implements Dao<Autor> {
  private final String INSERT = "INSERT INTO Authors (name, fname) VALUES (?, ?)";
  private final String LIST = "SELECT * FROM Authors";
  private final String LIKE = "SELECT * FROM Authors WHERE name LIKE ?";
  private final String UPDATE = "UPDATE Authors SET name = ?, fname = ? WHERE author_id = ?";

  @Override
  public boolean create(Autor autor) {
    Connection con = FabricaConexao.getConnection();
    PreparedStatement st = null;

    try {
      st = con.prepareStatement(INSERT);
      st.setString(1, autor.getNome());
      st.setString(2, autor.getSobrenome());

      if (st.executeUpdate() != 0) {
        return true;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      FabricaConexao.closeConnection(con, st);
    }

    return false;
  }

  @Override
  public List<Autor> findAll() {
    Connection con = FabricaConexao.getConnection();
    PreparedStatement stnt = null;
    ResultSet rs = null;

    List<Autor> autores = new ArrayList<>();

    try {
      stnt = con.prepareStatement(LIST);
      rs = stnt.executeQuery();

      while (rs.next()) {
        Autor autor = new Autor();

        autor.setId(rs.getInt("author_id"));
        autor.setNome(rs.getString("name"));
        autor.setSobrenome(rs.getString("fname"));

        autores.add(autor);
      }

    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null, "Erro ao listar " + e.getMessage());
    } finally {
      FabricaConexao.closeConnection(con, stnt, rs);
    }

    return autores;
  }

  @Override
  public List<Autor> findByName(String s) {
    Connection con = FabricaConexao.getConnection();
    PreparedStatement stnt = null;
    ResultSet rs = null;

    List<Autor> autores = new ArrayList<>();

    try {
      stnt = con.prepareStatement(LIKE);
      stnt.setString(1, s + "%");
      rs = stnt.executeQuery();

      while (rs.next()) {
        Autor autor = new Autor();

        autor.setId(rs.getInt("author_id"));
        autor.setNome(rs.getString("name"));
        autor.setSobrenome(rs.getString("fname"));

        autores.add(autor);
      }

    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null, "Erro ao listar " + e.getMessage());
    } finally {
      FabricaConexao.closeConnection(con, stnt, rs);
    }

    return autores;
  }

  @Override
  public boolean update(Autor a) {
    Connection con = FabricaConexao.getConnection();
    PreparedStatement stnt = null;

    try {
      stnt = con.prepareStatement(UPDATE);
      stnt.setString(1, a.getNome());
      stnt.setString(2, a.getSobrenome());
      stnt.setInt(3, a.getId());

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
  public boolean delete() {
    return false;
  }
}
