package view.tInicio;

import java.awt.event.ActionListener;

public interface IViewPrincipal {
  void init();
  void irParaTelaAutor(ActionListener al);
  void irParaTelaEditora(ActionListener al);
  void irParaTelaLivro(ActionListener al);
  void sair(ActionListener al);
}
