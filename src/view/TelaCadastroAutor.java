package view;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroAutor extends JFrame {

  public TelaCadastroAutor() {
    initComponents();
  }

  private void initComponents() {
    JLabel jLabelTituloP = new JLabel();
    JPanel jPanelCadastroAutor = new JPanel();
    JLabel jLabelNome = new JLabel();
    JTextField jTextFieldNome = new JTextField();
    JLabel jLabelSobrenome = new JLabel();
    JTextField jTextFieldSobrenome = new JTextField();

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setTitle("Livraria - Cadastro de Livros");
    setResizable(false);

    jLabelTituloP.setFont(new Font("JetBrains Mono", Font.BOLD, 24));
    jLabelTituloP.setText("Cadastro de Autor");

    jPanelCadastroAutor.setBackground(new Color(204, 204, 204));
    jPanelCadastroAutor.setPreferredSize(new Dimension(780, 400));

    jLabelNome.setFont(new Font("JetBrains Mono", Font.PLAIN, 18));
    jLabelNome.setText("Nome:");

    jTextFieldNome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jTextFieldNome.setToolTipText("Insira o nome do autor");

    jLabelSobrenome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jTextFieldSobrenome.setToolTipText("Insira o sobrenome do autor");

    GroupLayout jPanelCadastroAutorLayout = new GroupLayout(jPanelCadastroAutor);
    jPanelCadastroAutor.setLayout(jPanelCadastroAutorLayout);
    jPanelCadastroAutorLayout.setHorizontalGroup(
            jPanelCadastroAutorLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCadastroAutorLayout.createSequentialGroup()
                            .addGap(73, 73, 73)
                            .addGroup(jPanelCadastroAutorLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelSobrenome)
                                    .addComponent(jLabelNome))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanelCadastroAutorLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldNome, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                                    .addComponent(jTextFieldSobrenome))
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
                            .addContainerGap(79, Short.MAX_VALUE))
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
                            .addComponent(jPanelCadastroAutor, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addContainerGap())
    );

    pack();
    setLocationRelativeTo(null);
  }
}
