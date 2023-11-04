package view.livro;

import model.LivroCompleto;

import java.awt.event.ActionListener;
import java.util.List;

public interface IViewLivro {
  void init();
  void mostrarLivro(List<LivroCompleto> list);
  void mostraEditora(List<String> list);
  void addBuscaLivroByNomeListener(ActionListener al);
  void addAdcionarLivroListener(ActionListener al);
  void addAtualizarLivroListener(ActionListener al);
  void addDeletarLivroListener(ActionListener al);
  String getTituloLivro();
  String getIsbnLivro();
  double getPrecoLivro();
  String getIdEditora();
  void limparCampos();

  void close();

}
