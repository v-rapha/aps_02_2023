package view;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroEditora extends JFrame {

  public TelaCadastroEditora() {
    initComponents();
  }

  private void initComponents() {
    JLabel jLabelTituloP = new JLabel();
    JPanel jPanelCadastroEditora = new JPanel();
    JLabel jLabelNome = new JLabel();
    JTextField jTextFieldNome = new JTextField();
    JLabel jLabelUrl = new JLabel();
    JTextField jTextFieldUrl = new JTextField();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Livraria - Cadastro de Editora");
    setResizable(false);

    jLabelTituloP.setFont(new Font("JetBrains Mono", Font.BOLD, 24));
    jLabelTituloP.setText("Cadastro de Editora");

    jPanelCadastroEditora.setBackground(new Color(204, 204, 204));
    jPanelCadastroEditora.setPreferredSize(new Dimension(780, 400));

    jLabelNome.setFont(new Font("JetBrains Mono", Font.PLAIN, 18));
    jLabelNome.setText("Nome:");

    jTextFieldNome.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jTextFieldNome.setToolTipText("Insira o nome da editora");

    jLabelUrl.setFont(new Font("JetBrains Mono", Font.PLAIN, 18));
    jLabelUrl.setText("Site:");
    jLabelUrl.setToolTipText("");

    jTextFieldUrl.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
    jTextFieldUrl.setToolTipText("Insira o site da editora");

    GroupLayout jPanelCadastroEditoraLayout = new GroupLayout(jPanelCadastroEditora);
    jPanelCadastroEditora.setLayout(jPanelCadastroEditoraLayout);
    jPanelCadastroEditoraLayout.setHorizontalGroup(
            jPanelCadastroEditoraLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCadastroEditoraLayout.createSequentialGroup()
                            .addGap(97, 97, 97)
                            .addGroup(jPanelCadastroEditoraLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelCadastroEditoraLayout.createSequentialGroup()
                                            .addComponent(jLabelUrl)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jTextFieldUrl, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelCadastroEditoraLayout.createSequentialGroup()
                                            .addComponent(jLabelNome)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jTextFieldNome, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(118, Short.MAX_VALUE))
    );
    jPanelCadastroEditoraLayout.setVerticalGroup(
            jPanelCadastroEditoraLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCadastroEditoraLayout.createSequentialGroup()
                            .addGap(82, 82, 82)
                            .addGroup(jPanelCadastroEditoraLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelNome)
                                    .addComponent(jTextFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanelCadastroEditoraLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelUrl)
                                    .addComponent(jTextFieldUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(70, Short.MAX_VALUE))
    );

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(jPanelCadastroEditora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                            .addGap(251, 251, 251)
                                            .addComponent(jLabelTituloP)))
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jLabelTituloP)
                            .addGap(18, 18, 18)
                            .addComponent(jPanelCadastroEditora, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addContainerGap())
    );

    pack();
    setLocationRelativeTo(null);
  }
}
