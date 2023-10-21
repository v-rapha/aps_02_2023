package view.autor;

import model.Autor;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.List;

public interface ViewAutor {
  void init();

  void mostrarAutores(List<Autor> list);

  void actionBuscaAutorByNomeListener(ActionListener al);
  void actionAdcionarAutorListener(ActionListener al);
  String getNomeAutor();
  String getSobrenomeAutor();

  void close();
}
