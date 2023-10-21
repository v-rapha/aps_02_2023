package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaPrincipal extends JFrame {
  private Container contentPane;
  private JMenuBar jMenuBarTelaPrincipal;
  private JMenu jMenuCadastro, jMenuSair;
  private JMenuItem jMenuItemLivro, jMenuItemAutor, jMenuItemEditora;
  private DefaultTableModel modelo = new DefaultTableModel();
  private JTable tabela = new JTable(modelo);
  private JComboBox<String> jComboBox;
  private JScrollPane jScrollPane;

  public TelaPrincipal() {
    initComponents();
  }

  private void initComponents() {
    jMenuBarTelaPrincipal = new JMenuBar();

    jMenuCadastro = new JMenu("Cadastro");
    jMenuCadastro.setFont(new Font("JetBrains Mono", Font.PLAIN, 16));

    jMenuSair = new JMenu("Sair");
    jMenuSair.setFont(new Font("JetBrains Mono", Font.PLAIN, 16));

    jMenuItemLivro = new JMenuItem("Livro");
    jMenuItemLivro.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));

    jMenuItemAutor = new JMenuItem("Autor");
    jMenuItemAutor.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));

    jMenuItemEditora = new JMenuItem("Editora");
    jMenuItemEditora.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));

    jMenuCadastro.add(jMenuItemLivro);
    jMenuCadastro.add(jMenuItemAutor);
    jMenuCadastro.add(jMenuItemEditora);

    jMenuBarTelaPrincipal.add(jMenuCadastro);
    jMenuBarTelaPrincipal.add(jMenuSair);

    setJMenuBar(jMenuBarTelaPrincipal);

    //combobox
    jComboBox = new JComboBox<>();
    jComboBox.addItem("Autores");
    jComboBox.addItem("Livros");
    jComboBox.addItem("Editoras");
    jComboBox.setBounds(650, 50, 100, 20);
    add(jComboBox);

    // Adicionando colunas e linhas à tabela
    // gerar uma nova table para cada entidade (autor, livros, editora)
    modelo.addColumn("author_id");
    modelo.addColumn("name");
    modelo.addColumn("fname");
    tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
    tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
    tabela.getColumnModel().getColumn(2).setPreferredWidth(80);

    jScrollPane = new JScrollPane(tabela);
    jScrollPane.setBounds(120, 120, 550, 200);
    add(jScrollPane);


    pack();
    setLayout(null);
    setBounds(0, 0, 800, 500);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setTitle("Livraria");
    setResizable(false);
  }
}