package view.editora;

import controller.AutorController;
import controller.EditoraController;
import dao.AutorDao;
import dao.EditoraDao;
import model.Autor;
import model.Editora;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class TelaCadastroEditora extends JPanel implements ViewEditora {
  JLabel jLabelTituloP = new JLabel("Cadastro de Editora");
  JLabel jLabelNome = new JLabel("Nome:");
  JTextField jTextFieldNome = new JTextField();
  JLabel jLabelUrl = new JLabel("Site:");
  JTextField jTextFieldUrl = new JTextField();
  JButton jButtonSalvar = new JButton("Salvar");
  JButton jButtonAtualizar = new JButton("Atualizar");
  JButton jButtonExcluir = new JButton("Excluir");
  JButton jButtonBuscar = new JButton("Buscar");
  private JTable tabela;
  private JScrollPane jScroll;
  private DefaultTableModel modelo;
  private List<Editora> listaEditoras = new ArrayList<>();

  private void initComponents() {
    //setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    //setTitle("Livraria - Cadastro de Editora");
    //setResizable(false);

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
          //txID.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
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
        //txID.setText("");
        jTextFieldNome.setText("");
        jTextFieldUrl.setText("");
      }
    });

    jScroll = new JScrollPane(tabela);

    setLayout(null);
    jLabelTituloP.setBounds(300, 15, 250, 20);
    jLabelNome.setBounds(120, 50, 80, 20);
    jTextFieldNome.setBounds(170, 50, 150, 20);
    jLabelUrl.setBounds(350, 50, 80, 20);
    jTextFieldUrl.setBounds(390, 50, 200, 20);
    jButtonSalvar.setBounds(180, 350, 100, 20);
    jButtonBuscar.setBounds(300, 350, 100, 20);
    jButtonAtualizar.setBounds(420, 350, 100, 20);
    jButtonExcluir.setBounds(540, 350, 110, 20);
    jScroll.setBounds(120, 120, 550, 200);

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

    //pack();
    setBounds(0, 0, 800, 500);
    //setTitle("Livraria - Cadastro de Editoras");
    //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    //setLocationRelativeTo(null);
    //setResizable(false);
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
  public void close() {

  }
}
