package view.livro;

import controller.LivroController;
import dao.LivroDao;
import model.LivroCompleto;
import model.Livro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class ViewLivro extends JPanel implements IViewLivro {
  private JLabel jLabelTituloP = new JLabel("Cadastro de Livro");
  private JLabel jLabelTitulo = new JLabel("Titulo:");
  private JTextField jTextFieldTitulo = new JTextField();
  private JLabel jLabelIsbn = new JLabel("ISBN:");
  private JTextField jTextFieldIsbn = new JTextField();
  private JLabel jLabelPreco = new JLabel("Preço:");
  private JTextField jTextFieldPreco = new JTextField();
  private JLabel jLabelEditoras = new JLabel("Editoras:");
  private JLabel jLabelAutores = new JLabel("Autores:");
  private JButton jButtonSalvar = new JButton("Salvar");
  private JButton jButtonAtualizar = new JButton("Atualizar");
  private JButton jButtonExcluir = new JButton("Excluir");
  private JButton jButtonBuscar = new JButton("Buscar");
  private JTable tabela;
  private JScrollPane jScroll;
  private JScrollPane jScrollJL;
  private JList<String> jListAutores;
  private DefaultListModel<String> listModel;
  private List<String> listaAutor = new ArrayList<>();
  private List<LivroCompleto> listaLivros = new ArrayList<>();
  private JComboBox<String> jComboBoxEditoras;
  private List<String> listaEditora = new ArrayList<>();

  private void initComponents() {
    jLabelTituloP.setFont(new Font("JetBrains Mono", Font.BOLD, 20));

    jLabelTitulo.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jTextFieldTitulo.setFont(new Font("JetBrains Mono", Font.PLAIN, 11));
    jTextFieldTitulo.setToolTipText("Insira o titulo do livro");

    jLabelIsbn.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jTextFieldIsbn.setFont(new Font("JetBrains Mono", Font.PLAIN, 11));
    jTextFieldIsbn.setToolTipText("Insira o ISBN do livro");

    jLabelPreco.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jTextFieldPreco.setFont(new Font("JetBrains Mono", Font.PLAIN, 11));
    jTextFieldPreco.setToolTipText("Insira o preço do livro");

    jLabelEditoras.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));

    jLabelAutores.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));

    jButtonSalvar.setFont(new java.awt.Font("JetBrains Mono", Font.PLAIN, 12));
    jButtonAtualizar.setFont(new java.awt.Font("JetBrains Mono", Font.PLAIN, 12));
    //jButtonAtualizar.setEnabled(false);
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
          jTextFieldTitulo.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
          jTextFieldPreco.setText(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
          jTextFieldIsbn.setText(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());
          jComboBoxEditoras.setSelectedItem(tabela.getValueAt(tabela.getSelectedRow(), 4).toString());
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
        jTextFieldTitulo.setText("");
        jTextFieldIsbn.setText("");
        jTextFieldPreco.setText("");
      }
    });

    jScroll = new JScrollPane(tabela);
    listModel = new DefaultListModel<>();

    jListAutores = new JList<>(listModel);
    jListAutores.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    jScrollJL = new JScrollPane(jListAutores);

    jComboBoxEditoras = new JComboBox<>();

    setLayout(null);
    jLabelTituloP.setBounds(400, 15, 250, 20);
    jLabelTitulo.setBounds(40, 50, 80, 20);
    jTextFieldTitulo.setBounds(95, 50, 210, 20);
    jLabelIsbn.setBounds(320, 50, 80, 20);
    jTextFieldIsbn.setBounds(360, 50, 100, 20);
    jLabelPreco.setBounds(480, 50, 80, 20);
    jTextFieldPreco.setBounds(530, 50, 65, 20);
    jButtonSalvar.setBounds(180, 350, 100, 20);
    jButtonBuscar.setBounds(300, 350, 100, 20);
    jButtonAtualizar.setBounds(420, 350, 100, 20);
    jButtonExcluir.setBounds(540, 350, 110, 20);
    jScroll.setBounds(150, 120, 550, 200);
    jLabelEditoras.setBounds(610, 50, 80, 20);
    jLabelAutores.setBounds(800, 50, 80, 20);
    jComboBoxEditoras.setBounds( 680, 50, 100, 20);
    jScrollJL.setBounds(800, 75, 130, 200);

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
    add(jLabelEditoras);
    add(jLabelAutores);
    add(jScrollJL);

    setBounds(0, 0, 1020, 500);
    setVisible(true);
  }

  @Override
  public void init() {
    initComponents();
    new LivroController(new LivroDao(), this).init();
  }

  @Override
  public void mostrarLivro(List<LivroCompleto> list) {
    this.atualizaTabela(list);
  }

  @Override
  public void mostraEditora(List<String> list) {
    this.atualizaComboBox(list);
  }

  public void mostrarAutor(List<String> list) {
    this.atualizaJList(list);
  }

  public void atualizaTabela(List<LivroCompleto> lsLivros) {
    DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
    listaLivros.clear();

    listaLivros.addAll(lsLivros);

    while (modelo.getRowCount() > 0) {
      modelo.removeRow(0);
    }

    for (LivroCompleto l : listaLivros) {
      String nomeAutor = l.getAutor().getNome();
      String sobrenomeAutor = l.getAutor().getSobrenome();
      String tituloLivro = l.getLivro().getTitulo();
      double precoLivro = l.getLivro().getPreco();
      String isbnLivro = l.getLivro().getIsbn();
      String nomeEditora = l.getEditora().getNome();

      modelo.setColumnIdentifiers(new Object[] {"Autor", "Titulo", "Preço", "ISBN", "Editora"});
      modelo.addRow(new Object[] {nomeAutor + " " + sobrenomeAutor, tituloLivro, precoLivro, isbnLivro, nomeEditora});
    }
  }

  public void atualizaComboBox(List<String> lsEditNames) {
    listaEditora.clear();

    listaEditora.addAll(lsEditNames);

    if (jComboBoxEditoras.getItemCount() > 0) {
      jComboBoxEditoras.removeAllItems();
    }

    for (String s: listaEditora) {
      jComboBoxEditoras.addItem(s);
    }
  }

  public void atualizaJList(List<String> lsAutorNames) {
    DefaultListModel<String> model = (DefaultListModel<String>) jListAutores.getModel();
    listaAutor.clear();

    listaAutor.addAll(lsAutorNames);

    for (String s: listaAutor) {
      model.addElement(s);
    }

  }

  public Livro selecionaLinhaTabela() {
    if (tabela.getSelectedRow() != -1) {
        String isbn = getIsbnLivro();
        String titulo = getTituloLivro();
        double preco = getPrecoLivro();

        Livro l = new Livro();
        l.setIsbn(isbn);
        l.setTitulo(titulo);
        l.setPreco(preco);
        return l;
      }
    return null;
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
    if (titulo.trim().isEmpty()) {
      return null;
    }
    return titulo;
  }

  @Override
  public String getIsbnLivro() {
    String isbn = this.jTextFieldIsbn.getText();
    if (isbn.trim().isEmpty()) {
      return null;
    }
    return isbn;
  }

  @Override
  public double getPrecoLivro() {
    String precoRaw = this.jTextFieldPreco.getText();
    if (!precoRaw.isEmpty()) {
      try {
        double preco = Double.parseDouble(precoRaw);
        if (preco >= 0) {
          return preco;
        } else {
          return -1;
        }
      } catch (NumberFormatException e) {
        return -2;
      }
    }
    return -1;
  }


  @Override
  public String getIdEditora() {
    //int idEditora = (int) tabela.getValueAt(tabela.getSelectedRow(), 3);
    String editora = (String) jComboBoxEditoras.getSelectedItem();
    return editora;
  }

  public String[] getNomesAutores() {
    return jListAutores.getSelectedValuesList().toArray(new String[0]);
  }

  @Override
  public void limparCampos() {
    jTextFieldTitulo.setText("");
    jTextFieldIsbn.setText("");
    jTextFieldPreco.setText("");
  }

  @Override
  public void close() {

  }

}
