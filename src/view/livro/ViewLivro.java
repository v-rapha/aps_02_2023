package view.livro;

import model.Editora;
import model.Livro;

import java.awt.event.ActionListener;
import java.util.List;

public interface ViewLivro {
  void init();
  void mostrarLivro(List<Livro> list);
  void mostraEditora(List<String> list);
  void addBuscaLivroByNomeListener(ActionListener al);
  void addAdcionarLivroListener(ActionListener al);
  void addAtualizarLivroListener(ActionListener al);
  void addDeletarLivroListener(ActionListener al);
  String getTituloLivro();
  String getIsbnLivro();
  double getPrecoLivro();
  String getIdEditora();

  void close();
}
