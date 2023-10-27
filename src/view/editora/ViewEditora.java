package view.editora;

import model.Autor;
import model.Editora;

import java.awt.event.ActionListener;
import java.util.List;

public interface ViewEditora {
  void init();
  void mostrarEditoras(List<Editora> list);
  void addBuscaEditoraByNomeListener(ActionListener al);
  void addAdcionarEditoraListener(ActionListener al);
  void addAtualizarEditoraListener(ActionListener al);
  void addDeletarEditoraListener(ActionListener al);
  String getNomeEditora();
  String getUrlEditora();

  void close();
}
