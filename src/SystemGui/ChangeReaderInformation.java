package SystemGui;

import DataBase.DataBaseOperate;
import jdk.nashorn.internal.runtime.regexp.joni.Warnings;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Reader;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ChangeReaderInformation extends JFrame {

    private DataBaseOperate dataBaseOperate = new DataBaseOperate();
    private JPanel contentPane;
    private JTextField nameField;
    private JTextField yearField;
    private JTextField monthField;
    private JTextField dayField;
    private JTextField credentialsIDTextField;
    private JTextField phoneNumberTextField;
    private JTextField emailTextField;
    private int userID;


    public ChangeReaderInformation(int userID) {
        super("更改个人信息");
        this.userID = userID;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 453, 431);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        this.setVisible(true);

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
        try {
            ResultSet rs = dataBaseOperate.getReaderInformation(userID);
            rs.next();
            nameField.setText(rs.getString("R_Name"));
        }catch (Exception e){
            e.printStackTrace();
        }
        nameField.setColumns(10);

        JComboBox sexComboBox = new JComboBox();
        sexComboBox.setModel(new DefaultComboBoxModel(new String[] {"男", "女"}));
        String sex = "男";
        int sexFlag = 0;
        try {
            ResultSet rs = dataBaseOperate.getReaderInformation(userID);
            rs.next();
            sex = rs.getString("R_Sex");
        }catch (Exception e){
            e.printStackTrace();
        }
        if(sex.equals("男")){
            sexFlag = 0;
        }
        else if (sex.equals("女")){
            sexFlag = 1;
        }
        sexComboBox.setSelectedIndex(sexFlag);
        sexComboBox.setMaximumRowCount(2);
        sexComboBox.setBounds(190, 49, 102, 21);
        contentPane.add(sexComboBox);

        JComboBox typeComboBox = new JComboBox();
        typeComboBox.setModel(new DefaultComboBoxModel(new String[] {"本科生", "研究生", "教师"}));
        String type = "本科生";
        int typeFlag = 0;
        try {
            ResultSet rs = dataBaseOperate.getReaderInformation(userID);
            rs.next();
            sex = rs.getString("R_Type");
        }catch (Exception e){
            e.printStackTrace();
        }
        if(type.equals("本科生")){
            typeFlag = 0;
        }
        else if (type.equals("研究生")){
            typeFlag = 1;
        }
        else if (type.equals("教师")){
            typeFlag = 2;
        }
        typeComboBox.setSelectedIndex(typeFlag);
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
        String credentials = "身份证";
        int credentialsFlag = 0;
        try {
            ResultSet rs = dataBaseOperate.getReaderInformation(userID);
            rs.next();
            credentials = rs.getString("R_Credentials");
        }catch (Exception e){
            e.printStackTrace();
        }
        if(credentials.equals("身份证")){
            credentialsFlag = 0;
        }
        else if (credentials.equals("护照")){
            credentialsFlag = 1;
        }
        credentialsComboBox.setSelectedIndex(credentialsFlag);
        credentialsComboBox.setBounds(190, 139, 105, 21);
        contentPane.add(credentialsComboBox);

        JLabel credentialsIDLable = new JLabel("证件号码：");
        credentialsIDLable.setBounds(111, 169, 67, 15);
        contentPane.add(credentialsIDLable);

        credentialsIDTextField = new JTextField();
        credentialsIDTextField.setBounds(190, 169, 132, 21);
        try {
            ResultSet rs = dataBaseOperate.getReaderInformation(userID);
            rs.next();
            credentialsIDTextField.setText(rs.getString("R_CredentialsID"));
        }catch (Exception e){
            e.printStackTrace();
        }
        contentPane.add(credentialsIDTextField);
        credentialsIDTextField.setColumns(10);
        JLabel phoneNumberLable = new JLabel("电话号码：");
        phoneNumberLable.setBounds(111, 199, 77, 15);
        contentPane.add(phoneNumberLable);

        phoneNumberTextField = new JTextField();
        phoneNumberTextField.setBounds(190, 199, 132, 21);
        try {
            ResultSet rs = dataBaseOperate.getReaderInformation(userID);
            rs.next();
            phoneNumberTextField.setText(rs.getString("R_PhoneNumber"));
        }catch (Exception e){
            e.printStackTrace();
        }
        contentPane.add(phoneNumberTextField);
        phoneNumberTextField.setColumns(10);

        JLabel emailLable = new JLabel("Email：");
        emailLable.setBounds(111, 227, 54, 15);
        contentPane.add(emailLable);

        emailTextField = new JTextField();
        emailTextField.setBounds(190, 227, 132, 21);
        try {
            ResultSet rs = dataBaseOperate.getReaderInformation(userID);
            rs.next();
            emailTextField.setText(rs.getString("R_Email"));
        }catch (Exception e){
            e.printStackTrace();
        }
        contentPane.add(emailTextField);
        emailTextField.setColumns(10);;

        JButton yesButton = new JButton("修改");
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
                dataBaseOperate.changeReaderInformationUp(userID,name,sex,type,year+"-"+month+"-"+day,credentials,
                        credentialsID,phoneNumber,email);
                dispose();
                JOptionPane.showMessageDialog(null,"修改成功","成功",JOptionPane.INFORMATION_MESSAGE);
                ReaderGui gui1 = new ReaderGui(userID);
                gui1.setVisible(true);
                gui1.readerInformationPanel.setVisible(true);
                return;
            }

        });
        yesButton.setBounds(91, 347, 93, 23);
        contentPane.add(yesButton);

        JButton cancelButton = new JButton("取消");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ReaderGui gui2 = new ReaderGui(userID);
                gui2.setVisible(true);
                dispose();
            }
        });
        cancelButton.setBounds(226, 347, 93, 23);
        contentPane.add(cancelButton);
    }
}
