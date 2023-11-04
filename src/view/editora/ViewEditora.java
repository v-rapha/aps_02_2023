package view.editora;

import controller.EditoraController;
import dao.EditoraDao;
import model.Editora;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class ViewEditora extends JPanel implements IViewEditora {
  private JLabel jLabelTituloP = new JLabel("Cadastro de Editora");
  private JLabel jLabelNome = new JLabel("Nome:");
  private JTextField jTextFieldNome = new JTextField();
  private JLabel jLabelUrl = new JLabel("Site:");
  private JTextField jTextFieldUrl = new JTextField();
  private JButton jButtonSalvar = new JButton("Salvar");
  private JButton jButtonAtualizar = new JButton("Atualizar");
  private JButton jButtonExcluir = new JButton("Excluir");
  private JButton jButtonBuscar = new JButton("Buscar");
  private JTable tabela;
  private JScrollPane jScroll;
  private List<Editora> listaEditoras = new ArrayList<>();

  private void initComponents() {
    jLabelTituloP.setFont(new Font("JetBrains Mono", Font.BOLD, 20));

    jLabelNome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jTextFieldNome.setFont(new Font("JetBrains Mono", Font.PLAIN, 11));
    jTextFieldNome.setToolTipText("Insira o nome da editora");

    jLabelUrl.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jTextFieldUrl.setFont(new Font("JetBrains Mono", Font.PLAIN, 11));
    jTextFieldUrl.setToolTipText("Insira o site da editora");

    jButtonSalvar.setFont(new java.awt.Font("JetBrains Mono", Font.PLAIN, 12));
    jButtonAtualizar.setFont(new java.awt.Font("JetBrains Mono", Font.PLAIN, 12));
    jButtonExcluir.setFont(new java.awt.Font("JetBrains Mono", Font.PLAIN, 12));
    jButtonBuscar.setFont(new java.awt.Font("JetBrains Mono", Font.PLAIN, 12));

    DefaultTableModel tableModel = new DefaultTableModel() {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    tableModel.addColumn("ID");
    tableModel.addColumn("Nome");
    tableModel.addColumn("Url");
    tabela = new JTable(tableModel);
    tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
    tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
    tabela.getColumnModel().getColumn(2).setPreferredWidth(80);
    tabela.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (tabela.getSelectedRow() != -1) {
          jTextFieldNome.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
          jTextFieldUrl.setText(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
        } else {
          JOptionPane.showMessageDialog(null, "Selecione uma linha");
        }
      }

      @Override
      public void mouseEntered(MouseEvent e) {}
      @Override
      public void mouseExited(MouseEvent e) {}
      @Override
      public void mousePressed(MouseEvent e) {}

      @Override
      public void mouseReleased(MouseEvent e) {
        jTextFieldNome.setText("");
        jTextFieldUrl.setText("");
      }
    });

    jScroll = new JScrollPane(tabela);

    setLayout(null);
    jLabelTituloP.setBounds(400, 15, 250, 20);
    jLabelNome.setBounds(240, 50, 80, 20);
    jTextFieldNome.setBounds(290, 50, 150, 20);
    jLabelUrl.setBounds(530, 50, 80, 20);
    jTextFieldUrl.setBounds(570, 50, 200, 20);
    jButtonSalvar.setBounds(270, 350, 100, 20);
    jButtonBuscar.setBounds(390, 350, 100, 20);
    jButtonAtualizar.setBounds(510, 350, 100, 20);
    jButtonExcluir.setBounds(630, 350, 110, 20);
    jScroll.setBounds(230, 120, 550, 200);

    add(jLabelTituloP);
    add(jLabelNome);
    add(jTextFieldNome);
    add(jLabelUrl);
    add(jTextFieldUrl);
    add(jButtonSalvar);
    add(jButtonBuscar);
    add(jButtonAtualizar);
    add(jButtonExcluir);
    add(jScroll);

    setBounds(0, 0, 800, 500);
    setVisible(true);
  }

  @Override
  public void init() {
    initComponents();
    new EditoraController(new EditoraDao(), this).init();
  }

  @Override
  public void mostrarEditoras(List<Editora> list) {
    this.atualizaTabela(list);
  }

  public void atualizaTabela(List<Editora> lsEdit) {
    DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
    listaEditoras.clear();

    listaEditoras.addAll(lsEdit);

    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }

    for (Editora e : listaEditoras) {
      modelo.addRow(new Object[] {e.getId(), e.getNome(), e.getUrl()});
    }
  }

  public Editora selecionaLinhaTabela() {
    if (tabela.getSelectedRow() != -1) {
      String nome = jTextFieldNome.getText();
      String url = jTextFieldUrl.getText();
      Editora e = new Editora(nome, url);
      e.setId((int) tabela.getValueAt(tabela.getSelectedRow(), 0));
      return e;
    }
    return null;
  }

  @Override
  public void addBuscaEditoraByNomeListener(ActionListener al) {
    this.jButtonBuscar.addActionListener(al);
  }

  @Override
  public void addAdcionarEditoraListener(ActionListener al) {
    this.jButtonSalvar.addActionListener(al);
  }

  @Override
  public void addAtualizarEditoraListener(ActionListener al) {
    this.jButtonAtualizar.addActionListener(al);
  }

  @Override
  public void addDeletarEditoraListener(ActionListener al) {
    this.jButtonExcluir.addActionListener(al);
  }

  @Override
  public String getNomeEditora() {
    String nome = this.jTextFieldNome.getText();
    if (nome.trim().isEmpty()) {
      return null;
    }
    return nome;
  }

  @Override
  public String getUrlEditora() {
    String url = this.jTextFieldUrl.getText();
    if (url.trim().isEmpty()) {
      return null;
    }
    return url;
  }

  @Override
  public void limparCampos() {
    this.jTextFieldNome.setText("");
    this.jTextFieldUrl.setText("");
  }

  @Override
  public void close() {}
}
