package dao;

import model.Autor;

import java.util.List;

public class AutorDao implements Dao<Autor> {

  @Override
  public boolean create(Autor o) {
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
