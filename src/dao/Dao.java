package dao;

import model.Autor;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
  boolean create(T entity);

  List<T> findAll();
  List<T> findByName(String s, String s2);
  boolean update(T entity);
  boolean delete(T entity);
}
