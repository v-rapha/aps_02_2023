package controller;

import dao.AutorDao;
import model.Autor;
import view.autor.ViewAutor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AutorController {
  private AutorDao autorDao;
  private ViewAutor autorView;

  public AutorController(AutorDao aAutorModel, ViewAutor aAutorView) {
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

      if (nome == null) {
        JOptionPane.showMessageDialog(null, "Insira o nome do Autor");
        return;
      } else if (sobrenome == null) {
        JOptionPane.showMessageDialog(null, "Insira o sobrenome do Autor");
        return;
      }

      boolean criado = autorDao.create(new Autor(nome, sobrenome));

      if (criado) {
        autorView.limparCampos();
        List<Autor> autores = autorDao.findAll();
        autorView.atualizaTabela(autores);
      } else {
        JOptionPane.showMessageDialog(null, "Erro na inserção do Autor");
      }
    }
  }

  class AcaoBuscarAutor implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String nome = autorView.getNomeAutor();
      String sobrenome = autorView.getSobrenomeAutor();
      List<Autor> autores = null;

      if (nome == null && sobrenome == null) {
        autores = autorDao.findAll();
        if (autores == null) {
          JOptionPane.showMessageDialog(null, "Erro ao listar Autores");
          return;
        }
        autorView.atualizaTabela(autores);
        return;
      } else if (nome != null) {
        autores = autorDao.findByName(nome, null);
      } else {
        autores = autorDao.findByName(null, sobrenome);
      }

      // sql issue: procura apenas pelo nome
      if (autores == null) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Autores");
        return;
      }
      autorView.atualizaTabela(autores);
    }
  }

  class AcaoAtualizarAutor implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (autorView.selecionaLinhaTabela() != null) {
        int i = JOptionPane.showConfirmDialog(null, "Deseja continuar com a edição do Autor?", "Editar",
                JOptionPane.OK_CANCEL_OPTION);
        if (i == JOptionPane.OK_OPTION) {
          Autor a = autorView.selecionaLinhaTabela();
          boolean editado = autorDao.update(a);

          if (editado) {
            autorView.limparCampos();
            List<Autor> autores = autorDao.findAll();
            autorView.atualizaTabela(autores);
          } else {
            JOptionPane.showMessageDialog(null, "Erro ao editar Autor");
          }
        }
        autorView.limparCampos();
      } else {
        JOptionPane.showMessageDialog(null, "Por favor, selecione uma linha da tabela para editar");
      }
    }
  }

  class AcaoDeletarAutor implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (autorView.selecionaLinhaTabela() != null) {
        int i = JOptionPane.showConfirmDialog(null, "Deseja continuar com a exclusão? Isso excluirá os livros deste autor", "Excluir",
                JOptionPane.OK_CANCEL_OPTION);
        if (i == JOptionPane.OK_OPTION) {
          Autor a = autorView.selecionaLinhaTabela();
          boolean excluido = autorDao.delete(a);

          if (excluido) {
            autorView.limparCampos();
            List<Autor> autores = autorDao.findAll();
            autorView.atualizaTabela(autores);
          } else {
            JOptionPane.showMessageDialog(null, "Erro na exclusão do Autor");
          }
        }
        autorView.limparCampos();
      } else {
        JOptionPane.showMessageDialog(null, "Por favor, selecione uma linha da tabela para excluir!");
      }
    }
  }
}
