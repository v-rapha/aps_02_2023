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

public class TelaCadastroAutor extends JFrame implements ViewAutor {
  JLabel jLabelTituloP = new JLabel("Cadastro de Autor");
  JLabel jLabelNome = new JLabel("Nome:");
  JLabel jLabelSobrenome = new JLabel("Sobrenome:");
  //JPanel jPanelCadastroAutor = new JPanel();
  JTextField jTextFieldNome = new JTextField();
  JTextField jTextFieldSobrenome = new JTextField();
  JButton jButtonSalvar = new JButton("Salvar");
  JButton jButtonAtualizar = new JButton("Atualizar");
  JButton jButtonCancelar = new JButton("Cancelar");
  JButton jButtonBuscar = new JButton("Buscar");
  private JTable tabela;
  private JScrollPane jScroll;
  private DefaultTableModel modelo;
  private List<Autor> listaAutores = new ArrayList<>();

//  public TelaCadastroAutor() {
//    initComponents();
//  }

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
    jButtonCancelar.setFont(new java.awt.Font("JetBrains Mono", Font.PLAIN, 12));
    jButtonBuscar.setFont(new java.awt.Font("JetBrains Mono", Font.PLAIN, 12));

    DefaultTableModel tableModel = new DefaultTableModel() {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    tableModel.addColumn("ID");
    tableModel.addColumn("Nome");
    tableModel.addColumn("Idade");
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
        //txID.setText("");
        jTextFieldNome.setText("");
        jTextFieldSobrenome.setText("");
      }
    });

    //tabela.setModel(modelo);

    // Adicionando colunas e linhas a tabela
    //modelo.addColumn("Id");
    //modelo.addColumn("Nome");
    //modelo.addColumn("Sobrenome");


    jScroll = new JScrollPane(tabela);

    setLayout(null);
    jLabelTituloP.setBounds(300, 15, 250, 20);
    jLabelNome.setBounds(100, 50, 80, 20);
    jTextFieldNome.setBounds(150, 50, 150, 20);
    jLabelSobrenome.setBounds(320, 50, 80, 20);
    jTextFieldSobrenome.setBounds(395, 50, 100, 20);
    jButtonSalvar.setBounds(180, 350, 100, 20);
    jButtonBuscar.setBounds(300, 350, 100, 20);
    jButtonAtualizar.setBounds(420, 350, 100, 20);
    jButtonCancelar.setBounds(540, 350, 110, 20);
    jScroll.setBounds(120, 120, 550, 200);

    add(jLabelTituloP);
    add(jLabelNome);
    add(jTextFieldNome);
    add(jLabelSobrenome);
    add(jTextFieldSobrenome);
    add(jButtonSalvar);
    add(jButtonBuscar);
    add(jButtonAtualizar);
    add(jButtonCancelar);
    add(jScroll);

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
    //int linha = tabela.getSelectedRow();
    String nome = jTextFieldNome.getText();
    String sobrenome = jTextFieldSobrenome.getText();
    Autor a = new Autor(nome, sobrenome);
    a.setId((int) tabela.getValueAt(tabela.getSelectedRow(), 0));

    //Autor a = listaAutores.get(linha);
    System.out.println(a);

    //tela.setVisible(true);
    //tela.setAutor(a);

    return a;
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
    this.jButtonCancelar.addActionListener(al);
  }

  @Override
  public String getNomeAutor() {
    String nome = this.jTextFieldNome.getText();
    return nome;
  }

  @Override
  public String getSobrenomeAutor() {
    String sobrenome = this.jTextFieldSobrenome.getText();
    return sobrenome;
  }

  @Override
  public void close() {

  }
}
