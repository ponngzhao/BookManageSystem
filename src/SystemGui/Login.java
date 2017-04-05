package SystemGui;

import DataBase.DataBaseOperate;
import DataBase.DataBaseLink;
import java.awt.BorderLayout;
import java.awt.EventQueue;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

    private DataBaseOperate dataBaseOperate= new DataBaseOperate();
    private JPanel contentPane;
    private JTextField accountField;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 337, 306);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel imgLabel = new JLabel(new ImageIcon("src/1.png"));
        imgLabel.setVisible(true);
        imgLabel.setBounds(0,0,337,110);
        contentPane.add(imgLabel);

        JLabel AccountLabel = new JLabel("账号：");
        AccountLabel.setBounds(65, 116, 40, 26);
        contentPane.add(AccountLabel);

        JLabel passwordLabel = new JLabel("密码：");
        passwordLabel.setBounds(65, 152, 40, 26);
        contentPane.add(passwordLabel);

        accountField = new JTextField();
        accountField.setBounds(123, 119, 127, 21);
        contentPane.add(accountField);
        accountField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(123, 155, 127, 21);
        contentPane.add(passwordField);

        JButton loginButton = new JButton("登录");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String account = accountField.getText();
                int userID = Integer.parseInt(account);
                String password = passwordField.getText();
                int flag = dataBaseOperate.getLoginInformation(userID,password);
                switch (flag){
                    case -1:
                        JOptionPane.showMessageDialog(null,"该账号不存在！","提示",JOptionPane.ERROR_MESSAGE);
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(null, "密码错误！", "提示", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 1:
                        dispose();
                        AdminGui homePage1 = new AdminGui();
                        homePage1.setVisible(true);
                        homePage1.setLocationRelativeTo(null);
                        break;
                    case 2:
                        dispose();
                        ReaderGui homePage = new ReaderGui(userID);
                        homePage.setVisible(true);
                        homePage.setLocationRelativeTo(null);
                        break;
                }
            }
        });
        loginButton.setBounds(173, 191, 93, 23);
        contentPane.add(loginButton);

        passwordField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginButton.doClick();
            }
        });

        JButton signUpButton = new JButton("注册");
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                SignUp signUpGui = new SignUp();
                signUpGui.setVisible(true);
                signUpGui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                signUpGui.setLocationRelativeTo(null);
            }
        });
        signUpButton.setBounds(56, 190, 93, 23);
        contentPane.add(signUpButton);
        setLocationRelativeTo(null);
    }
}
