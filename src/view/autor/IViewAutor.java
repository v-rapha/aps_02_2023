package view.autor;

import model.Autor;

import java.awt.event.ActionListener;
import java.util.List;

public interface IViewAutor {
  void init();
  void mostrarAutores(List<Autor> list);
  void addBuscaAutorByNomeListener(ActionListener al);
  void addAdcionarAutorListener(ActionListener al);
  void addAtualizarAutorListener(ActionListener al);
  void addDeletarAutorListener(ActionListener al);
  String getNomeAutor();
  String getSobrenomeAutor();
  void limparCampos();

  void close();

}
