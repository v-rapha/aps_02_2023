package controller;

import dao.AutorDao;
import model.Autor;
import model.Editora;
import view.autor.TelaCadastroAutor;

import javax.swing.*;
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

      if (nome == null) {
        JOptionPane.showMessageDialog(null, "Preencha o campo nome");
        return;
      } else if (sobrenome == null) {
        JOptionPane.showMessageDialog(null, "Preencha o campo sobrenome");
        return;
      }

      boolean criado = autorDao.create(new Autor(nome, sobrenome));

      if (criado) {
        autorView.limparCampos();
        List<Autor> autores = autorDao.findAll();
        autorView.atualizaTabela(autores);
      } else {
        JOptionPane.showMessageDialog(null, "Erro ao inserir o autor");
      }
    }
  }

  class AcaoBuscarAutor implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String nome = autorView.getNomeAutor();
      String sobrenome = autorView.getSobrenomeAutor();
      List<Autor> autores;

      if (nome == null && sobrenome == null) {
        autores = autorDao.findAll();
        if (autores == null) {
          JOptionPane.showMessageDialog(null, "Erro ao listar");
          return;
        }
        autorView.atualizaTabela(autores);
        return;
      }

      // sql issue: procura apenas pelo nome
      autores = autorDao.findByName(nome);
      if (autores == null) {
        JOptionPane.showMessageDialog(null, "Erro ao listar");
        return;
      }
      autorView.atualizaTabela(autores);
    }
  }

  class AcaoAtualizarAutor implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (autorView.selecionaLinhaTabela() != null) {
        int i = JOptionPane.showConfirmDialog(null, "Deseja continuar com a edição?", "Excluir",
                JOptionPane.OK_CANCEL_OPTION);
        if (i == JOptionPane.OK_OPTION) {
          Autor a = autorView.selecionaLinhaTabela();
          boolean editado = autorDao.update(a);

          if (editado) {
            autorView.limparCampos();
            List<Autor> autores = autorDao.findAll();
            autorView.atualizaTabela(autores);
          } else {
            JOptionPane.showMessageDialog(null, "Erro ao editar");
          }
        }
        autorView.limparCampos();
      } else {
        JOptionPane.showMessageDialog(null, "Selecione uma linha da tabela para editar");
      }
    }
  }

  class AcaoDeletarAutor implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (autorView.selecionaLinhaTabela() != null) {
        int i = JOptionPane.showConfirmDialog(null, "Deseja continuar com a exclusão?", "Excluir",
                JOptionPane.OK_CANCEL_OPTION);
        if (i == JOptionPane.OK_OPTION) {
          Autor a = autorView.selecionaLinhaTabela();
          boolean excluido = autorDao.delete(a);

          if (excluido) {
            autorView.limparCampos();
            List<Autor> autores = autorDao.findAll();
            autorView.atualizaTabela(autores);
          } else {
            JOptionPane.showMessageDialog(null, "Erro ao excluir");
          }
        }
        autorView.limparCampos();
      } else {
        JOptionPane.showMessageDialog(null, "Selecione uma linha da tabela para excluir");
      }
    }
  }
}
