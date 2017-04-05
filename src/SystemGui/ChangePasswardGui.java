package SystemGui;

import DataBase.DataBaseOperate;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;

/**
 * Created by hp on 2016/6/25.
 */
public class ChangePasswardGui extends JFrame{
    private DataBaseOperate operateTest = new DataBaseOperate();
    private JPanel contentPane;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;
    public ChangePasswardGui(int userID){
        super("修改密码");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        this.setVisible(true);

        JLabel passwordLabel = new JLabel("请输入新密码：");
        passwordLabel.setBounds(64, 45, 99, 26);
        contentPane.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(173, 46, 169, 26);
        contentPane.add(passwordField);

        JLabel passwordLabel2 = new JLabel("请确认新密码：");
        passwordLabel2.setBounds(64, 110, 99, 26);
        contentPane.add(passwordLabel2);

        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(173, 111, 169, 26);
        contentPane.add(passwordField_1);

        JButton changeButton = new JButton("修改");
        changeButton.setBounds(90, 190, 93, 23);
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password1 = passwordField.getText();
                String password2 = passwordField_1.getText();
                if (password1.length() == 0){
                    JOptionPane.showMessageDialog(null,"请填写密码！","提示",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (password2.length() == 0){
                    JOptionPane.showMessageDialog(null,"请确认密码！","提示",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!password1.equals(password2)){
                    JOptionPane.showMessageDialog(null,"两次密码不同！","提示",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                operateTest.changeReaderPassword(userID,password1);
                dispose();
                JOptionPane.showMessageDialog(null,"修改成功！","提示",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        });
        contentPane.add(changeButton);

        JButton canelButton = new JButton("取消");
        canelButton.setBounds(228, 190, 93, 23);
        canelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        contentPane.add(canelButton);
    }
}
