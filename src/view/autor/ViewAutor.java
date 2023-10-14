package view.autor;

import model.Autor;

import java.awt.event.ActionListener;
import java.util.List;

public interface ViewAutor {
  void init();

  void mostrarAutores(List<Autor> autores);

  void actionBuscaAutorByNomeListener(ActionListener al);
  void actionAdcionarAutorListener(ActionListener al);
  String getNomeAutor();
  String getSobrenomeAutor();

  void close();
}
