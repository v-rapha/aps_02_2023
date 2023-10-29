package controller;

import dao.EditoraDao;
import dao.LivroDao;
import model.Editora;
import model.Livro;
import view.editora.TelaCadastroEditora;
import view.livro.TelaCadastroLivro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LivroController {
  private LivroDao livroDao;
  private TelaCadastroLivro livroView;

  public LivroController(LivroDao aLivroDao, TelaCadastroLivro aLivroView) {
    this.livroDao = aLivroDao;
    this.livroView = aLivroView;
  }

  public void init() {
    livroView.addAdcionarLivroListener(new LivroController.AcaoInserirLivro());
    livroView.addBuscaLivroByNomeListener(new LivroController.AcaoBuscarLivro());
    livroView.addAtualizarLivroListener(new LivroController.AcaoAtualizarLivro());
    livroView.addDeletarLivroListener(new LivroController.AcaoDeletarLivro());
    livroView.mostrarLivro(getLivro());
    livroView.mostraEditora(getEditora());
  }

  private List<Livro> getLivro() {
    return livroDao.findAll();
  }

  private List<String> getEditora() {
    return livroDao.getPublishersName();
  }

  class AcaoInserirLivro implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String titulo = livroView.getTituloLivro();
      String isbn = livroView.getIsbnLivro();
      double preco = livroView.getPrecoLivro();

      if (titulo == null) {
        JOptionPane.showMessageDialog(null, "Preencha o titulo");
        return;
      } else if (isbn == null) {
        JOptionPane.showMessageDialog(null, "Preencha o ISBN");
        return;
      } else if (preco == -1) {
        JOptionPane.showMessageDialog(null, "Insira um preço maior ou igual a zero");
        return;
      } else if (preco == -2) {
        JOptionPane.showMessageDialog(null, "Insira um preço válido");
        return;
      }

      String editoraNome = livroView.getIdEditora();
      int idEditora = livroDao.getPublisherId(editoraNome);

      boolean criado = livroDao.create(new Livro(titulo, isbn, preco, idEditora));

      if (criado) {
        livroView.limparCampos();
        List<Livro> livros = livroDao.findAll();
        livroView.atualizaTabela(livros);
      } else {
        JOptionPane.showMessageDialog(null, "Erro ao inserir o livro");
      }
    }
  }

  class AcaoBuscarLivro implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String titulo = livroView.getTituloLivro();
      if (titulo == null) {
        List<Livro> livros = livroDao.findAll();
        if (livros == null) {
          JOptionPane.showMessageDialog(null, "Erro ao listar");
          return;
        }

        livroView.atualizaTabela(livros);
        return;
      }

      List<Livro> livros = livroDao.findByName(titulo);
      if (livros == null) {
        JOptionPane.showMessageDialog(null, "Erro ao listar");
        return;
      }
      livroView.atualizaTabela(livros);
    }
  }

  class AcaoAtualizarLivro implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (livroView.selecionaLinhaTabela() != null) {
        int i = JOptionPane.showConfirmDialog(null, "Deseja continuar?", "Excluir",
                JOptionPane.OK_CANCEL_OPTION);
        if (i == JOptionPane.OK_OPTION) {
          Livro livro = livroView.selecionaLinhaTabela();
          System.out.println(livro);
          livroDao.update(livro);

          List<Livro> livros = livroDao.findAll();
          livroView.atualizaTabela(livros);
        }
      }
    }
  }

  class AcaoDeletarLivro implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (livroView.selecionaLinhaTabela() != null) {
        int i = JOptionPane.showConfirmDialog(null, "Deseja continuar?", "Excluir",
                JOptionPane.OK_CANCEL_OPTION);
        if (i == JOptionPane.OK_OPTION) {
          Livro livro = livroView.selecionaLinhaTabela();
          boolean excluido =  livroDao.delete(livro);

          if (excluido) {
            livroView.limparCampos();
            List<Livro> livros = livroDao.findAll();
            livroView.atualizaTabela(livros);
          } else {
            JOptionPane.showMessageDialog(null, "Erro ao excluir");
          }
        }
        livroView.limparCampos();
      } else {
        JOptionPane.showMessageDialog(null, "Selecione uma linha da tabela para excluir");
      }
    }
  }
}

