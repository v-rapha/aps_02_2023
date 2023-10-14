package view.autor;

import model.Autor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaCadastroAutor extends JFrame implements ViewAutor {
  JLabel jLabelTituloP = new JLabel("Cadastro de Autor");
  JLabel jLabelNome = new JLabel("Nome:");
  JLabel jLabelSobrenome = new JLabel("Sobrenome:");
  JPanel jPanelCadastroAutor = new JPanel();
  JTextField jTextFieldNome = new JTextField();
  JTextField jTextFieldSobrenome = new JTextField();
  JButton jButtonSalvar = new JButton("Salvar");
  JButton jButtonLimpar = new JButton("Limpar");
  JButton jButtonCancelar = new JButton("Cancelar");
  JButton jButtonBuscar = new JButton("Buscar");

//  public TelaCadastroAutor() {
//    initComponents();
//  }

  private void initComponents() {
    jLabelTituloP.setFont(new Font("JetBrains Mono", Font.BOLD, 24));

    jPanelCadastroAutor.setBackground(new Color(204, 204, 204));
    jPanelCadastroAutor.setPreferredSize(new Dimension(780, 400));

    jLabelNome.setFont(new Font("JetBrains Mono", Font.PLAIN, 18));

    jTextFieldNome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jTextFieldNome.setToolTipText("Insira o nome do autor");

    jLabelSobrenome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jTextFieldSobrenome.setToolTipText("Insira o sobrenome do autor");

    jButtonSalvar.setFont(new java.awt.Font("JetBrains Mono", Font.PLAIN, 18));
    jButtonLimpar.setFont(new java.awt.Font("JetBrains Mono", Font.PLAIN, 18));
    jButtonCancelar.setFont(new java.awt.Font("JetBrains Mono", Font.PLAIN, 18));
    jButtonBuscar.setFont(new java.awt.Font("JetBrains Mono", Font.PLAIN, 18));

    GroupLayout jPanelCadastroAutorLayout = new GroupLayout(jPanelCadastroAutor);
    jPanelCadastroAutor.setLayout(jPanelCadastroAutorLayout);
    jPanelCadastroAutorLayout.setHorizontalGroup(
            jPanelCadastroAutorLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCadastroAutorLayout.createSequentialGroup()
                            .addGap(73, 73, 73)
                            .addGroup(jPanelCadastroAutorLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelCadastroAutorLayout.createSequentialGroup()
                                            .addComponent(jButtonSalvar)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jButtonLimpar)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButtonCancelar)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButtonBuscar))
                                    .addGroup(jPanelCadastroAutorLayout.createSequentialGroup()
                                            .addGroup(jPanelCadastroAutorLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabelSobrenome)
                                                    .addComponent(jLabelNome))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(jPanelCadastroAutorLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jTextFieldNome, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                                                    .addComponent(jTextFieldSobrenome))))
                            .addContainerGap(137, Short.MAX_VALUE))
    );
    jPanelCadastroAutorLayout.setVerticalGroup(
            jPanelCadastroAutorLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCadastroAutorLayout.createSequentialGroup()
                            .addGap(83, 83, 83)
                            .addGroup(jPanelCadastroAutorLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelNome)
                                    .addComponent(jTextFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanelCadastroAutorLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelSobrenome)
                                    .addComponent(jTextFieldSobrenome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                            .addGroup(jPanelCadastroAutorLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonSalvar)
                                    .addComponent(jButtonLimpar)
                                    .addComponent(jButtonCancelar)
                                    .addComponent(jButtonBuscar))
                            .addGap(57, 57, 57))
    );

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(jPanelCadastroAutor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                            .addGap(268, 268, 268)
                                            .addComponent(jLabelTituloP)))
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(jLabelTituloP)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanelCadastroAutor, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                            .addContainerGap())
    );

    pack();
    setTitle("Livraria - Cadastro de Livros");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
    setVisible(true);
  }

  @Override
  public void init() {
    initComponents();
  }

  @Override
  public void mostrarAutores(List<Autor> autores) {

  }

  @Override
  public void actionBuscaAutorByNomeListener(ActionListener al) {
    this.jButtonBuscar.addActionListener(al);
    this.jTextFieldNome.addActionListener(al);
  }

  @Override
  public void actionAdcionarAutorListener(ActionListener al) {
    this.jButtonSalvar.addActionListener(al);
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
