package student_management.ui.layout;

import javax.swing.*;
import java.awt.*;

public class UIComponentFactory {
    public static JTextField createTextField() {
        JTextField field = new JTextField(20);
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, 40));
        return field;
    }


    public static JComboBox<String> createComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setPreferredSize(new Dimension(comboBox.getPreferredSize().width, 40));
        return comboBox;
    }

    public static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(180, 40));
        return button;
    }
}