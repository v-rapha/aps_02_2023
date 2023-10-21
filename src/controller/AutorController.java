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
    autorView.actionAdcionarAutorListener(new AcaoInserirAutor());
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
}
