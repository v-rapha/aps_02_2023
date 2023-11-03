package view.editora;

import model.Editora;

import java.awt.event.ActionListener;
import java.util.List;

public interface IViewEditora {
  void init();
  void mostrarEditoras(List<Editora> list);
  void addBuscaEditoraByNomeListener(ActionListener al);
  void addAdcionarEditoraListener(ActionListener al);
  void addAtualizarEditoraListener(ActionListener al);
  void addDeletarEditoraListener(ActionListener al);
  String getNomeEditora();
  String getUrlEditora();
  void limparCampos();

  void close();

}
