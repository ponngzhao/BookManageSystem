package SystemGui;

import DataBase.DataBaseOperate;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class ChangeBorrowGui extends JFrame {

    private DataBaseOperate operateTest = new DataBaseOperate();
    private JPanel contentPane;
    private JTextField bookNumberField;
    private JTextField userIDField;
    private JTextField borrowDateField;
    private JTextField deadLineField;
    private JTextField returnDateField;

    private int borrowNumber;

    /**
     * Create the frame.
     */
    public ChangeBorrowGui(int borrowNumber) throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 378, 409);
        this.borrowNumber = borrowNumber;
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel bookNumberLabel = new JLabel("图书编号：");
        bookNumberLabel.setBounds(62, 10, 70, 15);
        contentPane.add(bookNumberLabel);

        ResultSet rs = operateTest.getBorrowNumberInformation(borrowNumber);
        try {
            rs.next();
        }catch (Exception e){
            e.printStackTrace();
        }

        bookNumberField = new JTextField();
        bookNumberField.setBounds(168, 7, 94, 21);
        contentPane.add(bookNumberField);
        bookNumberField.setText(rs.getString("B_ID"));
        bookNumberField.setColumns(10);

        JLabel userIDLabel = new JLabel("用户编号：");
        userIDLabel.setBounds(62, 51, 70, 15);
        contentPane.add(userIDLabel);

        userIDField = new JTextField();
        userIDField.setBounds(168, 48, 94, 21);
        contentPane.add(userIDField);
        userIDField.setText(rs.getString("User_ID"));
        userIDField.setColumns(10);

        JLabel borrowDateLabel = new JLabel("借阅日期：");
        borrowDateLabel.setBounds(62, 98, 70, 15);
        contentPane.add(borrowDateLabel);

        borrowDateField = new JTextField();
        borrowDateField.setBounds(168, 95, 94, 21);
        contentPane.add(borrowDateField);
        borrowDateField.setText(rs.getString("B_BorrowedDate"));
        borrowDateField.setColumns(10);

        JLabel deadLineLabel = new JLabel("截止日期：");
        deadLineLabel.setBounds(62, 151, 70, 15);
        contentPane.add(deadLineLabel);

        deadLineField = new JTextField();
        deadLineField.setBounds(168, 148, 94, 21);
        contentPane.add(deadLineField);
        deadLineField.setText(rs.getString("B_DeadLine"));
        deadLineField.setColumns(10);

        JLabel isReturnLabel = new JLabel("是否归还：");
        isReturnLabel.setBounds(62, 198, 70, 15);
        contentPane.add(isReturnLabel);

        JComboBox isReturnField = new JComboBox();
        isReturnField.setModel(new DefaultComboBoxModel(new String[] {"是", "否"}));
        isReturnField.setSelectedIndex(0);
        isReturnField.setBounds(168, 195, 94, 21);
        contentPane.add(isReturnField);

        JLabel returnDateLabel = new JLabel("归还日期");
        returnDateLabel.setBounds(62, 246, 70, 15);
        contentPane.add(returnDateLabel);

        returnDateField = new JTextField();
        returnDateField.setBounds(168, 243, 94, 21);
        contentPane.add(returnDateField);
        returnDateField.setText(rs.getString("B_ReturnDate"));
        returnDateField.setColumns(10);

        JButton ensureButton = new JButton("确定");
        ensureButton.setBounds(56, 311, 93, 23);
        contentPane.add(ensureButton);
        ensureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int bookNumber = Integer.parseInt(bookNumberField.getText());
                int userID = Integer.parseInt(userIDField.getText());
                String borrowDate = borrowDateField.getText();
                String deadLine = deadLineField.getText();
                boolean isReturn = false;
                switch (isReturnField.getSelectedIndex()){
                    case 0:
                        isReturn = true;
                        break;
                    case 1:
                        isReturn = false;
                        break;
                }
                String returnDate = returnDateField.getText();
//                if (returnDate.length() == 0) returnDate = " ";
//                System.out.println(returnDate.length());
                operateTest.changeBorrowInformation(borrowNumber,userID,bookNumber,borrowDate,deadLine,isReturn,returnDate);
                dispose();
            }
        });

        JButton canelButton = new JButton("取消");
        canelButton.setBounds(185, 310, 93, 23);
        canelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        contentPane.add(canelButton);
    }

}
