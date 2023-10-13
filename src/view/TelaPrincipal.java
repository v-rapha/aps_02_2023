package view;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

  public TelaPrincipal() {
    initComponents();
  }

  private void initComponents() {
    JMenuBar jMenuBarTelaPrincipal = new JMenuBar();
    JMenu jMenuCadastro = new JMenu();
    JMenuItem jMenuItemLivro = new JMenuItem();
    JMenuItem jMenuItemAutor = new JMenuItem();
    JMenuItem jMenuItemEditora = new JMenuItem();
    JMenu jMenuSair = new JMenu();

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setTitle("Livraria");
    setResizable(false);

    jMenuCadastro.setText("Cadastro");
    jMenuCadastro.setToolTipText("");
    jMenuCadastro.setFont(new Font("JetBrains Mono", Font.PLAIN, 16));

    jMenuSair.setText("Sair");
    jMenuSair.setFont(new Font("JetBrains Mono", Font.PLAIN, 16));

    jMenuItemLivro.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jMenuItemLivro.setText("Livro");
    jMenuItemLivro.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    jMenuCadastro.add(jMenuItemLivro);

    jMenuItemAutor.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jMenuItemAutor.setText("Autor");
    jMenuItemAutor.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    jMenuCadastro.add(jMenuItemAutor);

    jMenuItemEditora.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jMenuItemEditora.setText("Editora");
    jMenuItemEditora.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    jMenuCadastro.add(jMenuItemEditora);

    jMenuBarTelaPrincipal.add(jMenuCadastro);
    jMenuBarTelaPrincipal.add(jMenuSair);

    setJMenuBar(jMenuBarTelaPrincipal);

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 800, Short.MAX_VALUE));
    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 328, Short.MAX_VALUE));

    pack();
    setLocationRelativeTo(null);
  }
}
