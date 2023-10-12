import java.util.List;

public interface Dao {
  boolean create();
  List<?> list();
  List<?> listByName();
  boolean delete();
}
