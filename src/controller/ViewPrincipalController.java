package controller;

import view.autor.TelaCadastroAutor;
import view.editora.TelaCadastroEditora;
import view.livro.TelaCadastroLivro;
import view.tInicio.TelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewPrincipalController {
  private TelaPrincipal viewPrincipal;

  public ViewPrincipalController(TelaPrincipal aViewPrincipal) {
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
      TelaCadastroLivro tcl = new TelaCadastroLivro();
      viewPrincipal.adicionarConteudo(tcl, "Cadastro de Livros");
      tcl.init();
    }
  }

  class AcaoIrParaTelaAutor implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      TelaCadastroAutor tca =  new TelaCadastroAutor();
      viewPrincipal.adicionarConteudo(tca, "Cadastro Autores");
      tca.init();
    }
  }

  class AcaoIrParaTelaEditora implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      TelaCadastroEditora tce = new TelaCadastroEditora();
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
