import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class CurrencyConverter extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JTextField fromAmount;
    private JTextField toAmount;
    private JLabel resultLabel;

    private static final String[] CURRENCIES = {
        "Choose One...", "US Dollar", "Indian Rupee", "Brazilian Real", "Canadian Dollar",
        "Kenyan Shilling", "Indonesian Rupiah", "Nigerian Naira", "Philippine Peso", "Pakistani Rupee"
    };
    private static final double[] RATES = {
        1.0, 1.24, 102.83, 6.20, 1.69, 173.56, 18671.21, 955.18, 69.47, 354.75
    };

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CurrencyConverter frame = new CurrencyConverter();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 350);
        setResizable(false);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 248, 255)); // Light blue background
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Currency Converter");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setBounds(10, 11, 464, 32);
        contentPane.add(lblTitle);

        fromCurrency = new JComboBox<>(CURRENCIES);
        fromCurrency.setBounds(20, 54, 200, 30);
        contentPane.add(fromCurrency);

        toCurrency = new JComboBox<>(CURRENCIES);
        toCurrency.setBounds(260, 54, 200, 30);
        contentPane.add(toCurrency);

        fromAmount = new JTextField();
        fromAmount.setBounds(20, 95, 200, 30);
        contentPane.add(fromAmount);

        toAmount = new JTextField();
        toAmount.setEditable(false);
        toAmount.setBounds(260, 95, 200, 30);
        contentPane.add(toAmount);

        JButton btnConvert = new JButton("Convert");
        btnConvert.setBackground(new Color(65, 105, 225)); // Royal blue
        btnConvert.setForeground(Color.WHITE);
        btnConvert.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnConvert.setBounds(190, 136, 100, 30);
        btnConvert.addActionListener(e -> convertCurrency());
        contentPane.add(btnConvert);

        resultLabel = new JLabel("");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        resultLabel.setBounds(10, 177, 464, 25);
        contentPane.add(resultLabel);

        JButton btnReset = new JButton("Reset");
        btnReset.setBackground(new Color(255, 69, 0)); // Orange-red
        btnReset.setForeground(Color.WHITE);
        btnReset.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnReset.setBounds(190, 213, 100, 30);
        btnReset.addActionListener(e -> resetFields());
        contentPane.add(btnReset);

        JButton btnExit = new JButton("Exit");
        btnExit.setBackground(new Color(220, 20, 60)); // Crimson
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnExit.setBounds(190, 253, 100, 30);
        btnExit.addActionListener(e -> System.exit(0));
        contentPane.add(btnExit);
    }

    private void convertCurrency() {
        try {
            int fromIndex = fromCurrency.getSelectedIndex();
            int toIndex = toCurrency.getSelectedIndex();
            
            if (fromIndex == 0 || toIndex == 0) {
                JOptionPane.showMessageDialog(this, "Please select both currencies.");
                return;
            }

            double amount = Double.parseDouble(fromAmount.getText());
            double result = amount * (RATES[toIndex] / RATES[fromIndex]);
            
            toAmount.setText(String.format("%.2f", result));
            resultLabel.setText(String.format("%.2f %s = %.2f %s", amount, CURRENCIES[fromIndex], result, CURRENCIES[toIndex]));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }
    }

    private void resetFields() {
        fromCurrency.setSelectedIndex(0);
        toCurrency.setSelectedIndex(0);
        fromAmount.setText("");
        toAmount.setText("");
        resultLabel.setText("");
    }
}