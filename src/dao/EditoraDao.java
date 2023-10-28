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
      throw new RuntimeException(e);
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
      JOptionPane.showMessageDialog(null, "Erro ao listar " + e.getMessage());
    } finally {
      FabricaConexao.closeConnection(con, stnt, rs);
    }

    return editoras;
  }

  @Override
  public List<Editora> findByName(String s) {
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
      JOptionPane.showMessageDialog(null, "Erro ao listar " + e.getMessage());
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
      stnt = con.prepareStatement(DELETE);
      stnt.setInt(1, editora.getId());

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