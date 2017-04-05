package SystemGui;

import DataBase.DataBaseOperate;
import jdk.nashorn.internal.runtime.regexp.joni.Warnings;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SignUp extends JFrame {

    private DataBaseOperate dataBaseOperate = new DataBaseOperate();
    private JPanel contentPane;
    private JTextField nameField;
    private JTextField yearField;
    private JTextField monthField;
    private JTextField dayField;
    private JTextField credentialsIDTextField;
    private JTextField phoneNumberTextField;
    private JTextField emailTextField;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;


    public SignUp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 453, 431);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel nameLabel = new JLabel("姓名：");
        nameLabel.setBounds(111, 19, 54, 15);
        contentPane.add(nameLabel);

        JLabel sexLabel = new JLabel("性别：");
        sexLabel.setBounds(111, 49, 54, 15);
        contentPane.add(sexLabel);

        JLabel typeLable = new JLabel("类别：");
        typeLable.setBounds(111, 79, 54, 15);
        contentPane.add(typeLable);

        JLabel bornDateLable = new JLabel("出生日期：");
        bornDateLable.setBounds(111, 109, 67, 15);
        contentPane.add(bornDateLable);

        JLabel credentialsLable = new JLabel("证件：");
        credentialsLable.setBounds(111, 139, 54, 15);
        contentPane.add(credentialsLable);

        nameField = new JTextField();
        nameField.setBounds(190, 19, 102, 21);
        contentPane.add(nameField);
        nameField.setColumns(10);

        JComboBox sexComboBox = new JComboBox();
        sexComboBox.setModel(new DefaultComboBoxModel(new String[] {"男", "女"}));
        sexComboBox.setSelectedIndex(0);
        sexComboBox.setMaximumRowCount(2);
        sexComboBox.setBounds(190, 49, 102, 21);
        contentPane.add(sexComboBox);

        JComboBox typeComboBox = new JComboBox();
        typeComboBox.setModel(new DefaultComboBoxModel(new String[] {"本科生", "研究生", "教师"}));
        typeComboBox.setSelectedIndex(0);
        typeComboBox.setBounds(190, 79, 102, 21);
        contentPane.add(typeComboBox);

        yearField = new JTextField();
        yearField.setBounds(190, 109, 43, 21);
        contentPane.add(yearField);
        yearField.setColumns(10);

        JLabel yearLabel = new JLabel("年");
        yearLabel.setBounds(241, 109, 14, 15);
        contentPane.add(yearLabel);

        monthField = new JTextField();
        monthField.setBounds(261, 109, 30, 21);
        contentPane.add(monthField);
        monthField.setColumns(10);

        JLabel monthLabel = new JLabel("月");
        monthLabel.setBounds(303, 109, 16, 15);
        contentPane.add(monthLabel);

        dayField = new JTextField();
        dayField.setBounds(325, 109, 30, 21);
        contentPane.add(dayField);
        dayField.setColumns(10);

        JLabel dayLabel = new JLabel("日");
        dayLabel.setBounds(365, 109, 16, 15);
        contentPane.add(dayLabel);

        JComboBox credentialsComboBox = new JComboBox();
        credentialsComboBox.setModel(new DefaultComboBoxModel(new String[] {"身份证", "护照"}));
        credentialsComboBox.setSelectedIndex(0);
        credentialsComboBox.setBounds(190, 139, 105, 21);
        contentPane.add(credentialsComboBox);

        JLabel credentialsIDLable = new JLabel("证件号码：");
        credentialsIDLable.setBounds(111, 169, 67, 15);
        contentPane.add(credentialsIDLable);

        credentialsIDTextField = new JTextField();
        credentialsIDTextField.setBounds(190, 169, 102, 21);
        contentPane.add(credentialsIDTextField);
        credentialsIDTextField.setColumns(10);

        JLabel phoneNumberLable = new JLabel("电话号码：");
        phoneNumberLable.setBounds(111, 199, 67, 15);
        contentPane.add(phoneNumberLable);

        phoneNumberTextField = new JTextField();
        phoneNumberTextField.setBounds(190, 199, 102, 21);
        contentPane.add(phoneNumberTextField);
        phoneNumberTextField.setColumns(10);

        JLabel emailLable = new JLabel("Email：");
        emailLable.setBounds(111, 227, 54, 15);
        contentPane.add(emailLable);

        emailTextField = new JTextField();
        emailTextField.setBounds(190, 227, 102, 21);
        contentPane.add(emailTextField);
        emailTextField.setColumns(10);

        JLabel passwordLable = new JLabel("密码：");
        passwordLable.setBounds(111, 270, 54, 15);
        contentPane.add(passwordLable);

        passwordField = new JPasswordField();
        passwordField.setBounds(190, 270, 102, 21);
        contentPane.add(passwordField);

        JLabel ensurePasswordLable = new JLabel("确认密码：");
        ensurePasswordLable.setBounds(111, 300, 67, 15);
        contentPane.add(ensurePasswordLable);

        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(190, 300, 102, 21);
        contentPane.add(passwordField_1);

        JButton yesButton = new JButton("确定");
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                if(name.length() == 0){
                    JOptionPane.showMessageDialog(null,"请填写姓名！","提示",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String sex = null;
                switch (sexComboBox.getSelectedIndex()){
                    case 0:
                        sex = "男";
                        break;
                    case 1:
                        sex = "女";
                        break;
                }
                if (sex == null){
                    JOptionPane.showMessageDialog(null,"请选择性别！","提示",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String type = null;
                switch(typeComboBox.getSelectedIndex()){
                    case 0:
                        type = "本科生";
                        break;
                    case 1:
                        type = "研究生";
                        break;
                    case 2:
                        type = "教师";
                        break;
                }
                if (type == null){
                    JOptionPane.showMessageDialog(null,"请选择类别！","提示",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String year = yearField.getText();
                String month = monthField.getText();
                String day = dayField.getText();
                if (year.length() == 0){
                    JOptionPane.showMessageDialog(null,"请填写出生日期！","提示",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (month.length() == 0){
                    JOptionPane.showMessageDialog(null,"请填写出生日期！","提示",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (day.length() == 0){
                    JOptionPane.showMessageDialog(null,"请填写出生日期！","提示",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String credentials = null;
                switch (credentialsComboBox.getSelectedIndex()){
                    case 0:
                        credentials = "身份证";
                        break;
                    case 1:
                        credentials = "护照";
                        break;
                }
                if (credentials == null){
                    JOptionPane.showMessageDialog(null,"请选择证件类型！","提示",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String credentialsID = credentialsIDTextField.getText();
                if (credentialsID.length() == 0){
                    JOptionPane.showMessageDialog(null,"请填写证件号码！","提示",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String phoneNumber = phoneNumberTextField.getText();
                if (phoneNumber.length() == 0){
                    JOptionPane.showMessageDialog(null,"请填写出手机号码！","提示",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String email = emailTextField.getText();
                if (email.length() == 0) {
                    JOptionPane.showMessageDialog(null,"请填写出生Email！","提示",JOptionPane.ERROR_MESSAGE);
                    return;
                }
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
                int userID = dataBaseOperate.signUp(name,sex,type,year+"-"+month+"-"+day,credentials,credentialsID,phoneNumber,email,password1);
                dispose();
                JOptionPane.showMessageDialog(null,"注册成功！\n您的登录账号为"+userID,"成功",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        });
        yesButton.setBounds(91, 347, 93, 23);
        contentPane.add(yesButton);

        JButton cancelButton = new JButton("取消");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        cancelButton.setBounds(226, 347, 93, 23);
        contentPane.add(cancelButton);
    }
}
