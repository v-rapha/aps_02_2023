package view.tInicio;

import controller.ViewPrincipalController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame implements ViewPrincipal {
  private JMenuBar jMenuBarTelaPrincipal;
  private JMenu jMenuDrop;
  private JMenuItem jMenuItemLivro, jMenuItemAutor, jMenuItemEditora, jMenuSair;
  private Container painelConteudo;

  private void initComponents() {
    painelConteudo = getContentPane();
    jMenuBarTelaPrincipal = new JMenuBar();

    jMenuDrop = new JMenu("Menu");
    jMenuDrop.setFont(new Font("JetBrains Mono", Font.PLAIN, 16));

    jMenuSair = new JMenuItem("Sair");
    jMenuSair.setFont(new Font("JetBrains Mono", Font.PLAIN, 16));

    jMenuItemLivro = new JMenuItem("Livro");
    jMenuItemLivro.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));

    jMenuItemAutor = new JMenuItem("Autor");
    jMenuItemAutor.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));

    jMenuItemEditora = new JMenuItem("Editora");
    jMenuItemEditora.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));

    jMenuDrop.add(jMenuItemLivro);
    jMenuDrop.add(jMenuItemAutor);
    jMenuDrop.add(jMenuItemEditora);

    jMenuBarTelaPrincipal.add(jMenuDrop);
    jMenuBarTelaPrincipal.add(jMenuSair);

    setJMenuBar(jMenuBarTelaPrincipal);

    pack();
    setLayout(null);
    setBounds(0, 0, 1020, 500);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setTitle("Livraria");
    setResizable(false);
    setVisible(true);
  }

  public void adicionarConteudo(JPanel jPanel, String tituloJanela) {
    painelConteudo.removeAll();
    painelConteudo.add(jPanel);
    painelConteudo.validate();
    setTitle(tituloJanela);
  }

  @Override
  public void init() {
    initComponents();
    new ViewPrincipalController(this).init();
  }

  @Override
  public void irParaTelaAutor(ActionListener al) {
    this.jMenuItemAutor.addActionListener(al);
  }

  @Override
  public void irParaTelaEditora(ActionListener al) {
    this.jMenuItemEditora.addActionListener(al);
  }

  @Override
  public void irParaTelaLivro(ActionListener al) {
    this.jMenuItemLivro.addActionListener(al);
  }

  @Override
  public void sair(ActionListener al) {
    this.jMenuSair.addActionListener(al);
  }
}