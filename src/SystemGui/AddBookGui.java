package SystemGui;

import DataBase.DataBaseOperate;
import DataBase.InsertBookIntoDataBase;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AddBookGui extends JFrame {

    private DataBaseOperate operateTest = new DataBaseOperate();

    private JPanel contentPane;
    private JTextField nameTextField;
    private JTextField authorTextField;
    private JTextField translatorTextField;
    private JTextField priceTextField;
    private JTextField pagesTextField;
    private JTextField pressTextField;
    private JTextField dateTextField;
    private JTextField ISBNTextField;
    private JTextField maxDaysTextField;
    private JTextField coverTextField;
    private JTextField typeTextField_;


    /**
     * Create the frame.
     */
    public AddBookGui() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 504, 484);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel nameLabel = new JLabel("书名：");
        nameLabel.setBounds(76, 11, 54, 15);
        contentPane.add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setBounds(165, 8, 66, 21);
        contentPane.add(nameTextField);
        nameTextField.setColumns(10);

        JLabel authorLabel = new JLabel("作者：");
        authorLabel.setBounds(76, 48, 54, 15);
        contentPane.add(authorLabel);

        authorTextField = new JTextField();
        authorTextField.setBounds(165, 45, 66, 21);
        contentPane.add(authorTextField);
        authorTextField.setColumns(10);

        JLabel translatorLabel = new JLabel("译者：");
        translatorLabel.setBounds(76, 86, 54, 15);
        contentPane.add(translatorLabel);

        translatorTextField = new JTextField();
        translatorTextField.setBounds(165, 83, 66, 21);
        contentPane.add(translatorTextField);
        translatorTextField.setColumns(10);

        JLabel priceLabel = new JLabel("价格：");
        priceLabel.setBounds(76, 125, 54, 15);
        contentPane.add(priceLabel);

        priceTextField = new JTextField();
        priceTextField.setBounds(165, 122, 66, 21);
        contentPane.add(priceTextField);
        priceTextField.setColumns(10);

        JLabel pagesLabel = new JLabel("页数：");
        pagesLabel.setBounds(76, 170, 54, 15);
        contentPane.add(pagesLabel);

        pagesTextField = new JTextField();
        pagesTextField.setBounds(165, 167, 66, 21);
        contentPane.add(pagesTextField);
        pagesTextField.setColumns(10);

        JLabel pressLabel = new JLabel("出版社：");
        pressLabel.setBounds(76, 215, 54, 15);
        contentPane.add(pressLabel);

        pressTextField = new JTextField();
        pressTextField.setBounds(165, 212, 66, 21);
        contentPane.add(pressTextField);
        pressTextField.setColumns(10);

        JLabel dateLabel = new JLabel("出版日期：");
        dateLabel.setBounds(76, 263, 66, 15);
        contentPane.add(dateLabel);

        dateTextField = new JTextField();
        dateTextField.setBounds(165, 260, 66, 21);
        contentPane.add(dateTextField);
        dateTextField.setColumns(10);

        JLabel ISBNLabel = new JLabel("ISBN：");
        ISBNLabel.setBounds(76, 312, 54, 15);
        contentPane.add(ISBNLabel);

        ISBNTextField = new JTextField();
        ISBNTextField.setBounds(165, 309, 66, 21);
        contentPane.add(ISBNTextField);
        ISBNTextField.setColumns(10);

        JLabel maxDaysLabel = new JLabel("最大借阅天数：");
        maxDaysLabel.setBounds(76, 360, 93, 15);
        contentPane.add(maxDaysLabel);

        maxDaysTextField = new JTextField();
        maxDaysTextField.setBounds(165, 357, 66, 21);
        contentPane.add(maxDaysTextField);
        maxDaysTextField.setColumns(10);

        JLabel coverLabel = new JLabel("封面地址：");
        coverLabel.setBounds(281, 11, 66, 15);
        contentPane.add(coverLabel);

        coverTextField = new JTextField();
        coverTextField.setBounds(357, 8, 66, 21);
        contentPane.add(coverTextField);
        coverTextField.setColumns(10);

        JLabel authorIntroLabel = new JLabel("作者简介：");
        authorIntroLabel.setBounds(280, 48, 66, 15);
        contentPane.add(authorIntroLabel);

        JLabel typeLabel = new JLabel("类别编号：");
        typeLabel.setBounds(76, 404, 66, 15);
        contentPane.add(typeLabel);

        typeTextField_ = new JTextField();
        typeTextField_.setBounds(165, 401, 66, 21);
        contentPane.add(typeTextField_);
        typeTextField_.setColumns(10);

        JTextArea authorTextArea = new JTextArea();
        authorTextArea.setBounds(281, 73, 167, 148);
        contentPane.add(authorTextArea);

        JLabel briefLabel = new JLabel("作品简介：");
        briefLabel.setBounds(281, 231, 81, 15);
        contentPane.add(briefLabel);

        JTextArea briefTextArea = new JTextArea();
        briefTextArea.setBounds(281, 259, 167, 127);
        contentPane.add(briefTextArea);

        JButton ensureButton = new JButton("确定");
        ensureButton.setBounds(281, 400, 66, 23);
        ensureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String author = authorTextField.getText();
                String translator = translatorTextField.getText();
                String price = priceTextField.getText();
                String pages = pagesTextField.getText();
                String press = pressTextField.getText();
                String date = dateTextField.getText();
                String ISBN = ISBNTextField.getText();
                int maxDays = Integer.parseInt(maxDaysTextField.getText());
                String cover = coverTextField.getText();
                int  type = Integer.parseInt(typeTextField_.getText());
                String brief = briefTextArea.getText();
                String authorIntro = authorTextArea.getText();
                int bookNumber = operateTest.addBookInformation(name,cover,author,translator,maxDays,type,date,press,pages,authorIntro,brief,ISBN,price);
                JOptionPane.showMessageDialog(null, "添加成功，图书编号：" + bookNumber, "成功", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });
        contentPane.add(ensureButton);

        JButton canelButton = new JButton("取消");
        canelButton.setBounds(378, 400, 70, 23);
        canelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        contentPane.add(canelButton);
    }
}
