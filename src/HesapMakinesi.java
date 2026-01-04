import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HesapMakinesi extends JFrame implements ActionListener {

    private JTextField ekran;
    private JButton[] rakamButonlari = new JButton[10];
    private JButton[] islemButonlari = new JButton[4];
    private JButton esittirButonu, temizleButonu;

    private double sayi1 = 0, sayi2 = 0, sonuc = 0;
    private char operator;

    public HesapMakinesi() {
        setTitle("IntelliJ Hesap Makinesi");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        ekran = new JTextField();
        ekran.setFont(new Font("Monospaced", Font.BOLD, 28));
        ekran.setHorizontalAlignment(JTextField.RIGHT);
        ekran.setEditable(false);
        ekran.setBackground(Color.WHITE);
        add(ekran, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 8, 8));

        for (int i = 0; i < 10; i++) {
            rakamButonlari[i] = new JButton(String.valueOf(i));
            rakamButonlari[i].setFont(new Font("Arial", Font.BOLD, 18));
            rakamButonlari[i].addActionListener(this);
        }

        String[] semboller = {"+", "-", "*", "/"};
        for (int i = 0; i < 4; i++) {
            islemButonlari[i] = new JButton(semboller[i]);
            islemButonlari[i].setFont(new Font("Arial", Font.BOLD, 18));
            islemButonlari[i].setBackground(new Color(230, 230, 230));
            islemButonlari[i].addActionListener(this);
        }

        esittirButonu = new JButton("=");
        esittirButonu.setBackground(Color.ORANGE);
        esittirButonu.addActionListener(this);

        temizleButonu = new JButton("C");
        temizleButonu.setBackground(Color.LIGHT_GRAY);
        temizleButonu.addActionListener(this);

        panel.add(rakamButonlari[7]); panel.add(rakamButonlari[8]); panel.add(rakamButonlari[9]); panel.add(islemButonlari[3]);
        panel.add(rakamButonlari[4]); panel.add(rakamButonlari[5]); panel.add(rakamButonlari[6]); panel.add(islemButonlari[2]);
        panel.add(rakamButonlari[1]); panel.add(rakamButonlari[2]); panel.add(rakamButonlari[3]); panel.add(islemButonlari[1]);
        panel.add(temizleButonu);      panel.add(rakamButonlari[0]); panel.add(esittirButonu);      panel.add(islemButonlari[0]);

        add(panel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {

            for (int i = 0; i < 10; i++) {
                if (e.getSource() == rakamButonlari[i]) {
                    ekran.setText(ekran.getText().concat(String.valueOf(i)));
                }
            }

            if (e.getSource() == temizleButonu) {
                ekran.setText("");
            }

            for (int i = 0; i < 4; i++) {
                if (e.getSource() == islemButonlari[i]) {
                    if (ekran.getText().isEmpty()) return;
                    sayi1 = Double.parseDouble(ekran.getText());
                    operator = islemButonlari[i].getText().charAt(0);
                    ekran.setText("");
                }
            }

            if (e.getSource() == esittirButonu) {
                if (ekran.getText().isEmpty()) return;
                sayi2 = Double.parseDouble(ekran.getText());

                switch (operator) {
                    case '+': sonuc = sayi1 + sayi2; break;
                    case '-': sonuc = sayi1 - sayi2; break;
                    case '*': sonuc = sayi1 * sayi2; break;
                    case '/':
                        if (sayi2 == 0) throw new ArithmeticException("Sıfıra bölünemez!");
                        sonuc = sayi1 / sayi2;
                        break;
                }

                if (sonuc % 1 == 0) {
                    ekran.setText(String.valueOf((int)sonuc));
                } else {
                    ekran.setText(String.valueOf(sonuc));
                }
            }
        } catch (ArithmeticException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Matematik Hatası", JOptionPane.ERROR_MESSAGE);
            ekran.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Hatalı giriş yaptınız!", "Hata", JOptionPane.ERROR_MESSAGE);
            ekran.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Beklenmedik bir hata oluştu.", "Sistem Hatası", JOptionPane.ERROR_MESSAGE);
            ekran.setText("");
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        new HesapMakinesi();
    }
}