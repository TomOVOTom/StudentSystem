package student_management.ui.layout;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractInputPanel {
    protected JPanel panel;
    protected GridBagConstraints gbc;

    public AbstractInputPanel() {
        panel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        initComponents();
    }

    protected abstract void initComponents();

    protected void addLabelAndField(String labelText, JComponent field, int gridy) {
        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.weightx = 0;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(field, gbc);
    }

    public JPanel getPanel() {
        return panel;
    }
}