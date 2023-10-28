package controller;

import dao.EditoraDao;
import model.Editora;
import view.editora.TelaCadastroEditora;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditoraController {
  private EditoraDao editoraDao;
  private TelaCadastroEditora editoraView;

  public EditoraController(EditoraDao aEditoraDao, TelaCadastroEditora aEditoraView) {
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

      editoraDao.create(new Editora(nome, url));
    }
  }

  class AcaoBuscarEditora implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String nome = editoraView.getNomeEditora();

      List<Editora> editoras = editoraDao.findByName(nome);
      editoraView.atualizaTabela(editoras);
    }
  }

  class AcaoAtualizarEditora implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      Editora editora = editoraView.selecionaLinhaTabela();
      editoraDao.update(editora);

      List<Editora> editoras =  editoraDao.findAll();
      editoraView.atualizaTabela(editoras);
    }
  }

  class AcaoDeletarEditora implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      Editora editora = editoraView.selecionaLinhaTabela();
      editoraDao.delete(editora);

      List<Editora> editoras = editoraDao.findAll();
      editoraView.atualizaTabela(editoras);
    }
  }
}
