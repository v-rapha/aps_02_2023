package dao;

import model.Autor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AutorDao implements Dao<Autor> {
  private final String INSERT = "INSERT INTO Authors (name, fname) VALUES (?, ?)";

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
    return null;
  }

  @Override
  public List<Autor> findByName(String s) {
    return null;
  }

  @Override
  public boolean update(String s) {
    return false;
  }

  @Override
  public boolean delete() {
    return false;
  }
}
