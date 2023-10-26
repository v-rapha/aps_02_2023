package controller;

import dao.AutorDao;
import model.Autor;
import view.autor.TelaCadastroAutor;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AutorController {
  private AutorDao autorDao;
  private TelaCadastroAutor autorView;

  public AutorController(AutorDao aAutorModel, TelaCadastroAutor aAutorView) {
    this.autorDao = aAutorModel;
    this.autorView = aAutorView;
  }

  public void init() {
    autorView.addAdcionarAutorListener(new AcaoInserirAutor());
    autorView.addBuscaAutorByNomeListener(new AcaoBuscarAutor());
    autorView.addAtualizarAutorListener(new AcaoAtualizarAutor());
    autorView.addDeletarAutorListener(new AcaoDeletarAutor());
    autorView.mostrarAutores(getAutores());
  }

  private List<Autor> getAutores() {
    return autorDao.findAll();
  }

  class AcaoInserirAutor implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String nome = autorView.getNomeAutor();
      String sobrenome = autorView.getSobrenomeAutor();

      autorDao.create(new Autor(nome, sobrenome));
    }
  }

  class AcaoBuscarAutor implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String nome = autorView.getNomeAutor();

      List<Autor> autores = autorDao.findByName(nome);
      autorView.atualizaTabela(autores);
    }
  }

  class AcaoAtualizarAutor implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      Autor a = autorView.selecionaLinhaTabela();
      System.out.println("AcaoAtualizarAutor " + a);
      autorDao.update(a);

      List<Autor> autores =  autorDao.findAll();
      autorView.atualizaTabela(autores);
    }
  }

  class AcaoDeletarAutor implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      Autor a = autorView.selecionaLinhaTabela();
      autorDao.delete(a);

      List<Autor> autores = autorDao.findAll();
      autorView.atualizaTabela(autores);
    }
  }
}
