package controller;

import view.autor.ViewAutor;
import view.editora.ViewEditora;
import view.livro.ViewLivro;
import view.tInicio.ViewPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewPrincipalController {
  private ViewPrincipal viewPrincipal;

  public ViewPrincipalController(ViewPrincipal aViewPrincipal) {
    this.viewPrincipal = aViewPrincipal;
  }

  public void init() {
    viewPrincipal.irParaTelaLivro(new ViewPrincipalController.AcaoIrParaTelaLivro());
    viewPrincipal.irParaTelaAutor(new ViewPrincipalController.AcaoIrParaTelaAutor());
    viewPrincipal.irParaTelaEditora(new ViewPrincipalController.AcaoIrParaTelaEditora());
    viewPrincipal.sair(new ViewPrincipalController.AcaoSair());
  }

  class AcaoIrParaTelaLivro implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      ViewLivro tcl = new ViewLivro();
      viewPrincipal.adicionarConteudo(tcl, "Cadastro de Livros");
      tcl.init();
    }
  }

  class AcaoIrParaTelaAutor implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      ViewAutor tca =  new ViewAutor();
      viewPrincipal.adicionarConteudo(tca, "Cadastro Autores");
      tca.init();
    }
  }

  class AcaoIrParaTelaEditora implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      ViewEditora tce = new ViewEditora();
      viewPrincipal.adicionarConteudo(tce, "Cadastro Editoras");
      tce.init();
    }
  }

  class AcaoSair implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      System.exit(0);
    }
  }
}
