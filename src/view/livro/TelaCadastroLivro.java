package view.livro;

import controller.EditoraController;
import controller.LivroController;
import dao.EditoraDao;
import dao.LivroDao;
import model.Editora;
import model.Livro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class TelaCadastroLivro extends JFrame implements ViewLivro {
  JLabel jLabelTituloP = new JLabel("Cadastro de Livro");
  JLabel jLabelTitulo = new JLabel("Titulo:");
  JTextField jTextFieldTitulo = new JTextField();
  JLabel jLabelIsbn = new JLabel("ISBN:");
  JTextField jTextFieldIsbn = new JTextField();
  JLabel jLabelPreco = new JLabel("Preço:");
  JTextField jTextFieldPreco = new JTextField();
  JButton jButtonSalvar = new JButton("Salvar");
  JButton jButtonAtualizar = new JButton("Atualizar");
  JButton jButtonExcluir = new JButton("Excluir");
  JButton jButtonBuscar = new JButton("Buscar");
  private JTable tabela;
  private JScrollPane jScroll;
  private DefaultTableModel modelo;
  private List<Livro> listaLivros = new ArrayList<>();
  private JComboBox<String> jComboBoxEditoras;
  private List<Editora> listaEditora = new ArrayList<>();

  private void initComponents() {
    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Livraria - Cadastro de Livros");
    setResizable(false);

    jLabelTituloP.setFont(new Font("JetBrains Mono", Font.BOLD, 20));

    jLabelTitulo.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jTextFieldTitulo.setFont(new Font("JetBrains Mono", Font.PLAIN, 11));
    jTextFieldTitulo.setToolTipText("Insira o nome da editora");

    jLabelIsbn.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jTextFieldIsbn.setFont(new Font("JetBrains Mono", Font.PLAIN, 11));
    jTextFieldIsbn.setToolTipText("Insira o site da editora");

    jLabelPreco.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jTextFieldPreco.setFont(new Font("JetBrains Mono", Font.PLAIN, 11));
    jTextFieldPreco.setToolTipText("Insira o site da editora");

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
    tableModel.addColumn("Isbn");
    tableModel.addColumn("Titulo");
    tableModel.addColumn("Preco");
    tableModel.addColumn("ID Editora");
    tabela = new JTable(tableModel);
    tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
    tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
    tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
    tabela.getColumnModel().getColumn(2).setPreferredWidth(2);
    tabela.getColumnModel().getColumn(3).setPreferredWidth(2);

    tabela.addMouseListener(new MouseListener() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (tabela.getSelectedRow() != -1) {
          //txID.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
          jTextFieldIsbn.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
          jTextFieldTitulo.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
          jTextFieldPreco.setText(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
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
        jTextFieldTitulo.setText("");
        jTextFieldIsbn.setText("");
        jTextFieldPreco.setText("");
      }
    });

    jScroll = new JScrollPane(tabela);

    jComboBoxEditoras = new JComboBox<>();
    jComboBoxEditoras.addItem("Java R.R");

    setLayout(null);
    jLabelTituloP.setBounds(300, 15, 250, 20);
    jLabelTitulo.setBounds(100, 50, 80, 20);
    jTextFieldTitulo.setBounds(155, 50, 150, 20);
    jLabelIsbn.setBounds(320, 50, 80, 20);
    jTextFieldIsbn.setBounds(360, 50, 100, 20);
    jLabelPreco.setBounds(480, 50, 80, 20);
    jTextFieldPreco.setBounds(530, 50, 80, 20);
    jButtonSalvar.setBounds(180, 350, 100, 20);
    jButtonBuscar.setBounds(300, 350, 100, 20);
    jButtonAtualizar.setBounds(420, 350, 100, 20);
    jButtonExcluir.setBounds(540, 350, 110, 20);
    jScroll.setBounds(120, 120, 550, 200);
    jComboBoxEditoras.setBounds( 590, 90, 100, 20);

    add(jLabelTituloP);
    add(jLabelTitulo);
    add(jTextFieldTitulo);
    add(jLabelIsbn);
    add(jTextFieldIsbn);
    add(jLabelPreco);
    add(jTextFieldPreco);
    add(jButtonSalvar);
    add(jButtonBuscar);
    add(jButtonAtualizar);
    add(jButtonExcluir);
    add(jScroll);
    add(jComboBoxEditoras);

    pack();
    setBounds(0, 0, 800, 500);
    setTitle("Livraria - Cadastro de Livros");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
    setVisible(true);
  }

  @Override
  public void init() {
    initComponents();
    new LivroController(new LivroDao(), this).init();
  }

  @Override
  public void mostrarLivro(List<Livro> list) {
    this.atualizaTabela(list);
  }

  public void atualizaTabela(List<Livro> lsLivros) {
    DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
    listaLivros.clear();

    listaLivros.addAll(lsLivros);

    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }

    for (Livro l : listaLivros) {
      modelo.addRow(new Object[] {l.getIsbn(), l.getTitulo(), l.getPreco(), l.getIdEditora()});
    }
  }

//  public void atualizaComboBox(List<Editora> lsEdit) {
//    ComboBoxModel<String> model =  jComboBoxEditoras.getModel();
//    listaEditora.clear();
//
//    listaEditora.addAll(lsEdit);
//
//    while (jComboBoxEditoras.get > 0) {
//      jComboBoxEditoras.remove();
//    }
//  }

  public Livro selecionaLinhaTabela() {
    String isbn = jTextFieldIsbn.getText();
    String titulo = jTextFieldTitulo.getText();
    double preco = Double.parseDouble(jTextFieldPreco.getText());
    Livro l = new Livro();
    l.setIsbn(isbn);
    l.setTitulo(titulo);
    l.setPreco(preco);
    l.setIdEditora((int) tabela.getValueAt(tabela.getSelectedRow(), 3));

    return l;
  }

  @Override
  public void addBuscaLivroByNomeListener(ActionListener al) {
    this.jButtonBuscar.addActionListener(al);
  }

  @Override
  public void addAdcionarLivroListener(ActionListener al) {
    this.jButtonSalvar.addActionListener(al);
  }

  @Override
  public void addAtualizarLivroListener(ActionListener al) {
    this.jButtonAtualizar.addActionListener(al);
  }

  @Override
  public void addDeletarLivroListener(ActionListener al) {
    this.jButtonExcluir.addActionListener(al);
  }

  @Override
  public String getTituloLivro() {
    String titulo = this.jTextFieldTitulo.getText();
    return titulo;
  }

  @Override
  public String getIsbnLivro() {
    String isbn = this.jTextFieldIsbn.getText();
    return isbn;
  }

  @Override
  public double getPrecoLivro() {
    double preco = Double.parseDouble(this.jTextFieldPreco.getText());
    return preco;
  }

  @Override
  public int getIdEditora() {
    int idEditora = (int) tabela.getValueAt(tabela.getSelectedRow(), 3);
    return idEditora;
  }

  @Override
  public void close() {

  }
}