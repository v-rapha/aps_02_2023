package controller;

import dao.AutorDao;
import dao.EditoraDao;
import dao.LivroCompletoDao;
import dao.LivroDao;
import model.LivroCompleto;
import model.Livro;
import view.livro.ViewLivro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LivroController {
  private LivroDao livroDao;
  private ViewLivro livroView;
  private LivroCompletoDao livroCompletoDao;
  private EditoraDao editoraDao;
  private AutorDao autorDao;

  public LivroController(LivroDao aLivroDao, ViewLivro aLivroView) {
    this.livroDao = aLivroDao;
    this.livroView = aLivroView;
    this.livroCompletoDao = new LivroCompletoDao();
    this.editoraDao = new EditoraDao();
    this.autorDao = new AutorDao();
  }

  public void init() {
    livroView.addAdcionarLivroListener(new LivroController.AcaoInserirLivro());
    livroView.addBuscaLivroByNomeListener(new LivroController.AcaoBuscarLivro());
    livroView.addAtualizarLivroListener(new LivroController.AcaoAtualizarLivro());
    livroView.addDeletarLivroListener(new LivroController.AcaoDeletarLivro());
    livroView.mostrarLivro(getLivro());
    livroView.mostraEditora(getEditora());
    livroView.mostrarAutor(getAutor());
  }

  private List<LivroCompleto> getLivro() {
    return livroCompletoDao.findAll();
  }

  private List<String> getEditora() {
    return editoraDao.getNomesEditoras();
  }

  private List<String> getAutor() {
    return autorDao.getNomesAutores();
  }

  class AcaoInserirLivro implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String titulo = livroView.getTituloLivro();
      String isbn = livroView.getIsbnLivro();
      double preco = livroView.getPrecoLivro();

      if (titulo == null) {
        JOptionPane.showMessageDialog(null, "Insira o titulo do Livro");
        return;
      } else if (isbn == null) {
        JOptionPane.showMessageDialog(null, "Insira o ISBN do Livro");
        return;
      } else if (preco == -1) {
        JOptionPane.showMessageDialog(null, "O Livro não pode ser cadastrado com um preço menor ou igual a zero");
        return;
      } else if (preco == -2) {
        JOptionPane.showMessageDialog(null, "Insira um preço válido para cadastrar o Livro");
        return;
      }

      String editoraNome = livroView.getIdEditora();
      int idEditora = editoraDao.getIdEditora(editoraNome);

      String[] nomesAutores = livroView.getNomesAutores();
      List<Integer> idsAutores = new ArrayList<>();

      if (nomesAutores.length == 0) {
        JOptionPane.showMessageDialog(null, "Por favor, selecione um autor");
        return;
      }
      for (String nomeAutor : nomesAutores) {
        String[] pdc;
        pdc = nomeAutor.trim().split(", ", 2);
        int autorId = autorDao.getIdAutor(pdc[0], pdc[1]);
        idsAutores.add(autorId);

        //System.out.println("id do autor: " + autorId);
      }

      //System.out.println(idsAutores);


      boolean criado = livroDao.create(new Livro(titulo, isbn, preco, idEditora, idsAutores));

      if (criado) {
        atualizaView();
      } else {
        JOptionPane.showMessageDialog(null, "Erro na inserção do livro");
      }
    }
  }

  class AcaoBuscarLivro implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String titulo = livroView.getTituloLivro();
      if (titulo == null) {
        List<LivroCompleto> livros = livroCompletoDao.findAll();
        if (livros == null) {
          JOptionPane.showMessageDialog(null, "Erro ao listar Livros");
          return;
        }

        livroView.atualizaTabela(livros);
        return;
      }

      List<LivroCompleto> livros = livroCompletoDao.findByName(titulo, null);
      if (livros == null) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Livros");
        return;
      }
      livroView.atualizaTabela(livros);
    }
  }

  class AcaoAtualizarLivro implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (livroView.selecionaLinhaTabela() != null) {
        int i = JOptionPane.showConfirmDialog(null, "Deseja continuar com a edição dos dados?", "Editar",
                JOptionPane.OK_CANCEL_OPTION);
        if (i == JOptionPane.OK_OPTION) {
          Livro livro = livroView.selecionaLinhaTabela();

          String editoraNome = livroView.getIdEditora();
          int idEditora = editoraDao.getIdEditora(editoraNome);
          livro.setIdEditora(idEditora);

          //System.out.println(livro);
          boolean editado = livroDao.update(livro);
          if (editado) {
           atualizaView();
          } else {
            JOptionPane.showMessageDialog(null, "Erro na edição do Livro");
          }
        }
        livroView.limparCampos();
      } else {
        JOptionPane.showMessageDialog(null, "Por favor, selecione uma linha da tabela para editar");
      }
    }
  }


  class AcaoDeletarLivro implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (livroView.selecionaLinhaTabela() != null) {
        int i = JOptionPane.showConfirmDialog(null, "Deseja continuar com a exclusão do Livro selecionado?", "Excluir",
                JOptionPane.OK_CANCEL_OPTION);
        if (i == JOptionPane.OK_OPTION) {
          Livro livro = livroView.selecionaLinhaTabela();
          boolean excluido = livroDao.delete(livro);

          if (excluido) {
            atualizaView();
          } else {
            JOptionPane.showMessageDialog(null, "Erro na exclusão do Livro");
          }
        }
        livroView.limparCampos();
      } else {
        JOptionPane.showMessageDialog(null, "Por favor, selecione uma linha da tabela para excluir");
      }
    }
  }

  private void atualizaView() {
    livroView.limparCampos();
    List<LivroCompleto> livros = livroCompletoDao.findAll();
    livroView.atualizaTabela(livros);
  }
}

