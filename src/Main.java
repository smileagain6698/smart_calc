import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SmartCalculator extends JFrame implements ActionListener {

    private JTextField display;
    private String currentInput = "";
    private String operator = "";
    private double num1 = 0;
    private boolean operatorClicked = false;

    public SmartCalculator() {
        setTitle("Smart Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            if (operatorClicked) {
                currentInput = "";
                operatorClicked = false;
            }
            currentInput += command;
            display.setText(currentInput);
        } else if (command.equals("C")) {
            display.setText("");
            currentInput = "";
            operator = "";
            num1 = 0;
        } else if (command.equals("=")) {
            double num2 = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }

            display.setText(String.valueOf(result));
            currentInput = String.valueOf(result);
            operator = "";
        } else {
            operatorClicked = true;
            operator = command;
            num1 = Double.parseDouble(currentInput);
        }
    }

    public static void main(String[] args) {
        new SmartCalculator();
    }
}
