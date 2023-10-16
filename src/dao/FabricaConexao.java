package dao;

import java.sql.*;

public class FabricaConexao {
  private final static String DBNAME = "aps_livraria";
  private final static String URL = "jdbc:mysql://localhost:3306/" + DBNAME;
  private final static String LOGIN = "root";
  private final static String PASS = "123456";

  public static Connection getConnection() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      return DriverManager.getConnection(URL, LOGIN, PASS);
    } catch (SQLException ex) {
      throw new RuntimeException("Erro na conexão " + ex);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public static void closeConnection(Connection con) {
    try {
      if (con != null) {
        con.close();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  // Método para fechar a conexão com o banco de dados e PrepareStatement
  public static void closeConnection(Connection con, PreparedStatement stnt) {
    closeConnection(con);

    try {
      if (stnt != null) {
        stnt.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // Método para fechar a conexão com o banco de dados e PrepareStatement e ResultSet
  public static void closeConnection(Connection con, PreparedStatement stnt, ResultSet rs) {
    closeConnection(con, stnt);

    try {
      if (rs != null) {
        rs.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
