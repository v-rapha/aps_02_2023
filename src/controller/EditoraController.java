package controller;

import dao.EditoraDao;
import model.Editora;
import view.editora.ViewEditora;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditoraController {
  private EditoraDao editoraDao;
  private ViewEditora editoraView;

  public EditoraController(EditoraDao aEditoraDao, ViewEditora aEditoraView) {
    this.editoraDao = aEditoraDao;
    this.editoraView = aEditoraView;
  }

  public void init() {
    editoraView.addAdcionarEditoraListener(new EditoraController.AcaoInserirEditora());
    editoraView.addBuscaEditoraByNomeListener(new EditoraController.AcaoBuscarEditora());
    editoraView.addAtualizarEditoraListener(new EditoraController.AcaoAtualizarEditora());
    editoraView.addDeletarEditoraListener(new EditoraController.AcaoDeletarEditora());
    editoraView.mostrarEditoras(getEditoras());
  }

  private List<Editora> getEditoras() {
    return editoraDao.findAll();
  }

  class AcaoInserirEditora implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String nome = editoraView.getNomeEditora();
      String url = editoraView.getUrlEditora();

      if (nome == null) {
        JOptionPane.showMessageDialog(null, "Insira o nome da Editora");
        return;
      } else if (url == null) {
        JOptionPane.showMessageDialog(null, "Insira o site da Editora");
        return;
      }

      boolean criado = editoraDao.create(new Editora(nome, url));

      if (criado) {
        editoraView.limparCampos();
        List<Editora> editoras = editoraDao.findAll();
        editoraView.atualizaTabela(editoras);
      } else {
        JOptionPane.showMessageDialog(null, "Erro na inserção da editora");
      }
    }
  }

  class AcaoBuscarEditora implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String nome = editoraView.getNomeEditora();
      List<Editora> editoras;

      if (nome == null) {
        editoras = editoraDao.findAll();
        if (editoras == null) {
          JOptionPane.showMessageDialog(null, "Erro ao listar Editoras");
          return;
        }
        editoraView.atualizaTabela(editoras);
        return;
      }

      editoras = editoraDao.findByName(nome, null);
      if (editoras == null) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Editoras");
        return;
      }
      editoraView.atualizaTabela(editoras);
    }
  }

  class AcaoAtualizarEditora implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (editoraView.selecionaLinhaTabela() != null) {
        int i = JOptionPane.showConfirmDialog(null, "Deseja continuar com a edição da Editora?", "Editar",
                JOptionPane.OK_CANCEL_OPTION);
        if (i == JOptionPane.OK_OPTION) {
          Editora editora = editoraView.selecionaLinhaTabela();
          boolean editado = editoraDao.update(editora);

          if (editado) {
            editoraView.limparCampos();
            List<Editora> editoras = editoraDao.findAll();
            editoraView.atualizaTabela(editoras);
          } else {
            JOptionPane.showMessageDialog(null, "Erro na edição da Editora");
          }
        }
        editoraView.limparCampos();
      } else {
        JOptionPane.showMessageDialog(null, "Por favor, selecione uma linha da tabela para editar");
      }
    }
  }

  class AcaoDeletarEditora implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      if (editoraView.selecionaLinhaTabela() != null) {
        int i = JOptionPane.showConfirmDialog(null, "Deseja continuar com a exclusão? Isso excluirá os livros desta Editora", "Excluir",
                JOptionPane.OK_CANCEL_OPTION);
        if (i == JOptionPane.OK_OPTION) {
          Editora editora = editoraView.selecionaLinhaTabela();
          boolean excluido = editoraDao.delete(editora);

          if (excluido) {
            editoraView.limparCampos();
            List<Editora> editoras = editoraDao.findAll();
            editoraView.atualizaTabela(editoras);
          } else {
            JOptionPane.showMessageDialog(null, "Erro na exclusão da Editora");
          }
        }
        editoraView.limparCampos();
      } else {
        JOptionPane.showMessageDialog(null, "Por favor, selecione uma linha da tabela para excluir");
      }
    }
  }
}
