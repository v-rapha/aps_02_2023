package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaPrincipal extends JFrame {
  private JMenuBar jMenuBarTelaPrincipal = new JMenuBar();
  private JMenu jMenuCadastro = new JMenu("Cadastro");
  private JMenuItem jMenuItemLivro = new JMenuItem("Livro");
  private JMenuItem jMenuItemAutor = new JMenuItem("Autor");
  private JMenuItem jMenuItemEditora = new JMenuItem("Editora");
  private JMenu jMenuSair = new JMenu("Sair");
  private DefaultTableModel modelo = new DefaultTableModel();
  private JTable tabela = new JTable(modelo);
  private JScrollPane jScroll;

  public TelaPrincipal() {
    initComponents();
  }

  private void initComponents() {
    jMenuCadastro.setFont(new Font("JetBrains Mono", Font.PLAIN, 16));
    jMenuSair.setFont(new Font("JetBrains Mono", Font.PLAIN, 16));

    jMenuItemLivro.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jMenuCadastro.add(jMenuItemLivro);

    jMenuItemAutor.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jMenuCadastro.add(jMenuItemAutor);

    jMenuItemEditora.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jMenuCadastro.add(jMenuItemEditora);

    jMenuBarTelaPrincipal.add(jMenuCadastro);
    jMenuBarTelaPrincipal.add(jMenuSair);

    // Adicionando colunas e linhas a tabela
    modelo.addColumn("author_id");
    modelo.addColumn("name");
    modelo.addColumn("fname");
    tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
    tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
    tabela.getColumnModel().getColumn(2).setPreferredWidth(80);
    //jScroll = new JScrollPane(tabela);

    setJMenuBar(jMenuBarTelaPrincipal);

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 800, Short.MAX_VALUE));
    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 328, Short.MAX_VALUE));
    //add(jScroll);

    pack();
    setLocationRelativeTo(null);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setTitle("Livraria");
    setResizable(false);
  }
}
