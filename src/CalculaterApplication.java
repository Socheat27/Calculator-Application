import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculaterApplication extends JFrame {
    private JTextField firstNumberField;
    private JTextField secondNumberField;
    private JTextField resultField;

    public CalculaterApplication() {
        // Set up the frame
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Set custom font and colors
        Font font = new Font("Arial", Font.BOLD, 14);
        Color bgColor = new Color(50, 50, 50);
        Color fgColor = new Color(200, 200, 200);
        Color buttonColor = new Color(100, 100, 255);
        Color resultColor = new Color(255, 100, 100);

        // Create and add components with custom styles
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(createLabel("First Number:", font, fgColor), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        firstNumberField = createTextField(font, fgColor, bgColor);
        add(firstNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(createLabel("Second Number:", font, fgColor), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        secondNumberField = createTextField(font, fgColor, bgColor);
        add(secondNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(createLabel("Result:", font, fgColor), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        resultField = createTextField(font, resultColor, bgColor);
        resultField.setEditable(false);
        add(resultField, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(createButton("+", buttonColor, font), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(createButton("-", buttonColor, font), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(createButton("*", buttonColor, font), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(createButton("/", buttonColor, font), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(createButton("%", buttonColor, font), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        JButton clearButton = new JButton("Clear");
        styleButton(clearButton, buttonColor, font);
        clearButton.addActionListener(e -> {
            firstNumberField.setText("");
            secondNumberField.setText("");
            resultField.setText("");
        });
        add(clearButton, gbc);

        // Center the frame on the screen
        setLocationRelativeTo(null);
        getContentPane().setBackground(bgColor);
    }

    private JLabel createLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

    private JTextField createTextField(Font font, Color fgColor, Color bgColor) {
        JTextField textField = new JTextField();
        textField.setFont(font);
        textField.setForeground(fgColor);
        textField.setBackground(bgColor);
        textField.setBorder(BorderFactory.createLineBorder(fgColor));
        return textField;
    }

    private JButton createButton(String text, Color bgColor, Font font) {
        JButton button = new JButton(text);
        styleButton(button, bgColor, font);
        button.addActionListener(new OperationActionListener(text));
        return button;
    }

    private void styleButton(JButton button, Color bgColor, Font font) {
        button.setFont(font);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }

    private class OperationActionListener implements ActionListener {
        private String operation;

        public OperationActionListener(String operation) {
            this.operation = operation;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double firstNumber = Double.parseDouble(firstNumberField.getText());
                double secondNumber = Double.parseDouble(secondNumberField.getText());
                double result = 0;

                switch (operation) {
                    case "+":
                        result = firstNumber + secondNumber;
                        break;
                    case "-":
                        result = firstNumber - secondNumber;
                        break;
                    case "*":
                        result = firstNumber * secondNumber;
                        break;
                    case "/":
                        if (secondNumber != 0) {
                            result = firstNumber / secondNumber;
                        } else {
                            JOptionPane.showMessageDialog(null, "Cannot divide by zero", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        break;
                    case "%":
                        result = firstNumber % secondNumber;
                        break;
                }
                resultField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculaterApplication calculator = new CalculaterApplication();
            calculator.setVisible(true);
        });
    }
}
