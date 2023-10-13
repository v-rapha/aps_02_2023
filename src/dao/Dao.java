package dao;

import java.util.List;

public interface Dao {
  boolean create();
  List<?> findAll(Object obj);
  List<?> findByName(String s);
  boolean update(String s);
  boolean delete();
}
