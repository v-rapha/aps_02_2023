package view.autor;

import controller.AutorController;
import dao.AutorDao;
import model.Autor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class ViewAutor extends JPanel implements IViewAutor {
  private JLabel jLabelTituloP = new JLabel("Cadastro de Autor");
  private JLabel jLabelNome = new JLabel("Nome:");
  private JLabel jLabelSobrenome = new JLabel("Sobrenome:");
  private JTextField jTextFieldNome = new JTextField();
  private JTextField jTextFieldSobrenome = new JTextField();
  private JButton jButtonSalvar = new JButton("Salvar");
  private JButton jButtonAtualizar = new JButton("Atualizar");
  private JButton jButtonExcluir = new JButton("Excluir");
  private JButton jButtonBuscar = new JButton("Buscar");
  private JTable tabela;
  private JScrollPane jScroll;
  private List<Autor> listaAutores = new ArrayList<>();

  private void initComponents() {
    jLabelTituloP.setFont(new Font("JetBrains Mono", Font.BOLD, 20));

    jLabelNome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jTextFieldNome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jTextFieldNome.setToolTipText("Insira o nome do autor");

    jLabelSobrenome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jTextFieldSobrenome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jTextFieldSobrenome.setToolTipText("Insira o sobrenome do autor");

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
    tableModel.addColumn("Sobrenome");
    tabela = new JTable(tableModel);
    tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
    tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
    tabela.getColumnModel().getColumn(2).setPreferredWidth(80);
    tabela.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (tabela.getSelectedRow() != -1) {
          jTextFieldNome.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
          jTextFieldSobrenome.setText(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
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
        jTextFieldSobrenome.setText("");
      }
    });

    jScroll = new JScrollPane(tabela);

    setLayout(null);
    jLabelTituloP.setBounds(400, 15, 250, 20);
    jLabelNome.setBounds(240, 50, 80, 20);
    jTextFieldNome.setBounds(290, 50, 150, 20);
    jLabelSobrenome.setBounds(490, 50, 80, 20);
    jTextFieldSobrenome.setBounds(580, 50, 150, 20);
    jButtonSalvar.setBounds(270, 350, 100, 20);
    jButtonBuscar.setBounds(390, 350, 100, 20);
    jButtonAtualizar.setBounds(510, 350, 100, 20);
    jButtonExcluir.setBounds(630, 350, 110, 20);
    jScroll.setBounds(230, 120, 550, 200);

    add(jLabelTituloP);
    add(jLabelNome);
    add(jTextFieldNome);
    add(jLabelSobrenome);
    add(jTextFieldSobrenome);
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
    new AutorController(new AutorDao(), this).init();
  }

  @Override
  public void mostrarAutores(List<Autor> listaAutores) {
    this.atualizaTabela(listaAutores);
  }

  public void atualizaTabela(List<Autor> as) {
    DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
    listaAutores.clear();

    listaAutores.addAll(as);

    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }

    for (Autor a : listaAutores) {
      modelo.addRow(new Object[] {a.getId(), a.getNome(), a.getSobrenome()});
    }
  }

  public Autor selecionaLinhaTabela() {
    if (tabela.getSelectedRow() != -1) {
      String nome = jTextFieldNome.getText();
      String sobrenome = jTextFieldSobrenome.getText();
      Autor a = new Autor(nome, sobrenome);
      a.setId((int) tabela.getValueAt(tabela.getSelectedRow(), 0));
      return a;
    }
    return null;
  }

  @Override
  public void addBuscaAutorByNomeListener(ActionListener al) {
    this.jButtonBuscar.addActionListener(al);
  }

  @Override
  public void addAdcionarAutorListener(ActionListener al) {
    this.jButtonSalvar.addActionListener(al);
  }

  @Override
  public void addAtualizarAutorListener(ActionListener al) {
    this.jButtonAtualizar.addActionListener(al);
  }

  @Override
  public void addDeletarAutorListener(ActionListener al) {
    this.jButtonExcluir.addActionListener(al);
  }

  @Override
  public String getNomeAutor() {
    String nome = this.jTextFieldNome.getText();
    if (nome.trim().isEmpty()) {
      return null;
    }
    return nome;
  }

  @Override
  public String getSobrenomeAutor() {
    String sobrenome = this.jTextFieldSobrenome.getText();
    if (sobrenome.trim().isEmpty()) {
      return null;
    }
    return sobrenome;
  }

  @Override
  public void limparCampos() {
    this.jTextFieldNome.setText("");
    this.jTextFieldSobrenome.setText("");
  }

  @Override
  public void close() {}
}
