package SystemGui;

import DataBase.BarCode;
import DataBase.DataBaseOperate;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import org.omg.CORBA.TRANSACTION_UNAVAILABLE;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.spec.ECField;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class ReaderGui extends JFrame {

    private int userID;
    private DataBaseOperate operateTest = new DataBaseOperate();
    private JPanel contentPane;
    private Object[][] borrowInformation;
    private Object[][] bookInformation;
    private JTextField searchTextField;
    private JTable reaultTable;
    public JPanel readerInformationPanel = new JPanel();
    private JPanel searchBookPanel = new JPanel();
    private JPanel reserveInformationPanel = new JPanel();
    private JPanel internetPanel = new JPanel();
    private int resultRow;
    private JTable borrowInformationTable = new JTable();
    public JPanel imgPanel = new JPanel();

    /**
     * Create the frame.
     */
    public ReaderGui(int userID) {
        super("图书管理系统");
        this.userID = userID;


        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        readerInformationPanel.setBounds(0, 0, 745, 413);
        readerInformationPanel.setLayout(null);
        contentPane.add(readerInformationPanel);
        readerInformationPanel.setVisible(false);



        imgPanel.setBounds(0, 0, 745, 413);
        imgPanel.setLayout(null);
        contentPane.add(imgPanel);
        imgPanel.setVisible(true);
        JLabel imgLabel = new JLabel(new ImageIcon("src/2.jpg"));
        imgLabel.setVisible(true);
        imgPanel.add(imgLabel);
        imgLabel.setBounds(0,0,755,493);

        internetPanel.setBounds(0, 0, 745, 413);
        internetPanel.setLayout(null);
        contentPane.add(readerInformationPanel);
        internetPanel.setVisible(false);
//        setInternetPanel();

        setReaderInformationPanel();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 771, 483);



        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        setReaderInformationPanel();
        setReserveInformationPanel();
        setSearchBookPanel();

        JMenu readerInformationMenu = new JMenu("读者信息");
        menuBar.add(readerInformationMenu);

        JMenuItem informationMenuItem = new JMenuItem("个人信息");

        readerInformationMenu.add(informationMenuItem);

        JMenuItem reserveMenuItem = new JMenuItem("借阅信息");
        readerInformationMenu.add(reserveMenuItem);

        JMenuItem changePasswprdItem = new JMenuItem("修改密码");
        readerInformationMenu.add(changePasswprdItem);

        JMenu bookInformationMenu = new JMenu("图书信息");
        menuBar.add(bookInformationMenu);

        JMenuItem searchBookMenuItem = new JMenuItem("本地查找书籍");
        bookInformationMenu.add(searchBookMenuItem);

        JMenuItem internetMenuItem = new JMenuItem("网络查找书籍");
        bookInformationMenu.add(internetMenuItem);

        JMenu aboutMenu = new JMenu("关于");
        menuBar.add(aboutMenu);

        JMenuItem exitMenuItem = new JMenuItem("安全退出");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login gui = new Login();
                gui.setVisible(true);
            }
        });
        aboutMenu.add(exitMenuItem);

        JMenuItem aboutAuthor = new JMenuItem("关于作者");
        aboutAuthor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"作者\n姓名：赵宏鹏\n班级：计科1403\n学号：1030414313","作者",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        aboutMenu.add(aboutAuthor);

        informationMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                setReaderInformationPanel();
//                readerInformationPanel.repaint();
//                searchBookPanel.setVisible(false);
//                reserveInformationPanel.setVisible(false);
//                readerInformationPanel.setVisible(true);
                ReaderGui gui = new ReaderGui(userID);
                gui.readerInformationPanel.setVisible(true);
                gui.imgPanel.setVisible(false);
                gui.setVisible(true);
                dispose();
            }
        });

        reserveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                internetPanel.setVisible(false);
                searchBookPanel.setVisible(false);
                imgPanel.setVisible(false);
                readerInformationPanel.setVisible(false);
                reserveInformationPanel.setVisible(true);
            }
        });

        searchBookMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBookPanel.setVisible(true);
                internetPanel.setVisible(false);
                imgPanel.setVisible(false);
                readerInformationPanel.setVisible(false);
                reserveInformationPanel.setVisible(false);

            }
        });

        internetMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                searchBookPanel.setVisible(false);
//                internetPanel.setVisible(true);
//                readerInformationPanel.setVisible(false);
//                reserveInformationPanel.setVisible(false);
                try{
                    UIUtils.setPreferredLookAndFeel();
                    NativeInterface.open();
                    JFrame frame = new JFrame("网络查询");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.getContentPane().add(new internetGui(), BorderLayout.CENTER);
                    frame.setSize(800, 600);
                    frame.setLocationByPlatform(true);
                    frame.setVisible(true);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.setLocationRelativeTo(null);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        changePasswprdItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangePasswardGui changePasswardGui = new ChangePasswardGui(userID);
                changePasswardGui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                changePasswardGui.setVisible(true);
                changePasswardGui.setLocationRelativeTo(null);
            }
        });
        setLocationRelativeTo(null);
    }

    public void getBorrowInformation(int userID){
        ResultSet rs = operateTest.getBorrowInformation(userID);
        int row = -1;
        try{
            rs.last();
            row = rs.getRow();
            rs.first();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.borrowInformation = new Object[row][7];
        int i = 0;
        try {
            while (i<row) {
                int borrowNumber = rs.getInt("B_BorrowNumber");
                int bookID = rs.getInt("B_ID");
                String borrowedDate = rs.getString("B_BorrowedDate");
                String deadLine = rs.getString("B_DeadLine");
                boolean isReturn = rs.getBoolean("B_IsReturn");
                String returnDate = rs.getString("B_ReturnDate");
                Object[] information = {borrowNumber,userID,bookID,borrowedDate,deadLine,isReturn,returnDate};
                this.borrowInformation[i] = information;
                i++;
                rs.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setReaderInformationPanel(){


        JLabel nameLabel = new JLabel("姓名：");
        nameLabel.setBounds(67, 24, 50, 15);
        readerInformationPanel.add(nameLabel);

        JLabel nameLabel_1 = new JLabel();
        nameLabel_1.setBounds(113, 24, 71, 15);
        try {
            ResultSet rs = operateTest.getReaderInformation(userID);
            rs.next();
            nameLabel_1.setText(rs.getString("R_Name"));
        }catch (Exception e){
            e.printStackTrace();
        }
        readerInformationPanel.add(nameLabel_1);

        JLabel sexLabel = new JLabel("性别：");
        sexLabel.setBounds(212, 24, 62, 15);
        readerInformationPanel.add(sexLabel);

        JLabel sexLabel_1 = new JLabel();
        sexLabel_1.setBounds(265, 24, 73, 15);
        try {
            ResultSet rs = operateTest.getReaderInformation(userID);
            rs.next();
            sexLabel_1.setText(rs.getString("R_Sex"));
        }catch (Exception e){
            e.printStackTrace();
        }
        readerInformationPanel.add(sexLabel_1);

        JLabel typeLabel = new JLabel("类别：");
        typeLabel.setBounds(364, 24, 54, 15);
        readerInformationPanel.add(typeLabel);

        JLabel typeLabel_1 = new JLabel();
        typeLabel_1.setBounds(415, 24, 82, 15);
        typeLabel_1.setText(getType(userID));
        readerInformationPanel.add(typeLabel_1);

        JLabel bornDateLabel = new JLabel("出生日期：");
        bornDateLabel.setBounds(512, 24, 84, 15);
        readerInformationPanel.add(bornDateLabel);

        JLabel bornDateLabel_1 = new JLabel();
        bornDateLabel_1.setBounds(581, 24, 111, 15);
        try {
            ResultSet rs = operateTest.getReaderInformation(userID);
            rs.next();
            bornDateLabel_1.setText(rs.getString("R_BornDate"));
        }catch (Exception e){
            e.printStackTrace();
        }
        readerInformationPanel.add(bornDateLabel_1);

        JLabel credentialsLabel = new JLabel("证件类型：");
        credentialsLabel.setBounds(67, 92, 70, 15);
        readerInformationPanel.add(credentialsLabel);

        JLabel credentialsLabel_1 = new JLabel();
        credentialsLabel_1.setBounds(137, 92, 69, 15);
        try {
            ResultSet rs = operateTest.getReaderInformation(userID);
            rs.next();
            credentialsLabel_1.setText(rs.getString("R_Credentials"));
        }catch (Exception e){
            e.printStackTrace();
        }
        readerInformationPanel.add(credentialsLabel_1);

        JLabel credentialsIDLabel = new JLabel("证件号码：");
        credentialsIDLabel.setBounds(208, 91, 78, 15);
        readerInformationPanel.add(credentialsIDLabel);

        JLabel credentialsIDLabel_1 = new JLabel();
        credentialsIDLabel_1.setBounds(288, 92, 181, 15);
        try {
            ResultSet rs = operateTest.getReaderInformation(userID);
            rs.next();
            credentialsIDLabel_1.setText(rs.getString("R_CredentialsID"));
        }catch (Exception e){
            e.printStackTrace();
        }
        readerInformationPanel.add(credentialsIDLabel_1);

        JLabel phoneNumberLabel = new JLabel("电话号码：");
        phoneNumberLabel.setBounds(512, 92, 65, 15);
        readerInformationPanel.add(phoneNumberLabel);

        JLabel phoneNumber_1 = new JLabel();
        phoneNumber_1.setBounds(587, 92, 113, 15);
        try {
            ResultSet rs = operateTest.getReaderInformation(userID);
            rs.next();
            phoneNumber_1.setText(rs.getString("R_PhoneNumber"));
        }catch (Exception e){
            e.printStackTrace();
        }
        readerInformationPanel.add(phoneNumber_1);

        JLabel eamilLabel = new JLabel("Email：");
        eamilLabel.setBounds(67, 167, 54, 15);
        readerInformationPanel.add(eamilLabel);

        JLabel emailLabel_1 = new JLabel();
        emailLabel_1.setBounds(113, 167, 114, 15);
        try {
            ResultSet rs = operateTest.getReaderInformation(userID);
            rs.next();
            emailLabel_1.setText(rs.getString("R_Email"));
        }catch (Exception e){
            e.printStackTrace();
        }
        readerInformationPanel.add(emailLabel_1);

        JLabel maxNumberLabel = new JLabel("最大借书数量：");
        maxNumberLabel.setBounds(245, 167, 103, 15);
        readerInformationPanel.add(maxNumberLabel);

        JLabel maxNumberLabel_1 = new JLabel();
        maxNumberLabel_1.setBounds(331, 168, 54, 15);
        try {
            ResultSet rs = operateTest.getReaderInformation(userID);
            rs.next();
            maxNumberLabel_1.setText(rs.getString("R_MaxBookNumber"));
        }catch (Exception e){
            e.printStackTrace();
        }
        readerInformationPanel.add(maxNumberLabel_1);

        JLabel borrowedNumberLabel = new JLabel("已借阅数量：");
        borrowedNumberLabel.setBounds(364, 167, 88, 15);
        readerInformationPanel.add(borrowedNumberLabel);

        JLabel borrowedNumberLabel_1 = new JLabel();
        borrowedNumberLabel_1.setBounds(452, 167, 54, 15);
        try {
            ResultSet rs = operateTest.getReaderInformation(userID);
            rs.next();
            borrowedNumberLabel_1.setText(rs.getString("R_BorrowedNumber"));
        }catch (Exception e){
            e.printStackTrace();
        }
        readerInformationPanel.add(borrowedNumberLabel_1);

        JLabel barcodeLabel = new JLabel();
        barcodeLabel.setBounds(330, 230, 309, 73);
        try {
            ResultSet rs = operateTest.getReaderInformation(userID);
            rs.next();
            BarCode barcode = new BarCode(rs.getInt("R_BarCode"));
            barcodeLabel.setIcon(new ImageIcon("BarCode/"+userID+".jpg"));
        }catch (Exception e){
            e.printStackTrace();
        }
        readerInformationPanel.add(barcodeLabel);

        JButton changeInformationButton = new JButton("修改个人信息");
        changeInformationButton.setBounds(294, 349, 158, 23);
        changeInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeReaderInformation change = new ChangeReaderInformation(userID);
                dispose();
            }
        });
        readerInformationPanel.add(changeInformationButton);
    }

    public void setSearchBookPanel(){
        searchBookPanel.setBounds(0, 0, 755, 418);
        contentPane.add(searchBookPanel);
        searchBookPanel.setLayout(null);
        searchBookPanel.setVisible(false);

        JComboBox searchComboBox = new JComboBox();
        searchComboBox.setModel(new DefaultComboBoxModel(new String[] {"书名", "作者", "译者"}));
        searchComboBox.setBounds(107, 7, 54, 21);
        searchBookPanel.add(searchComboBox);

        searchTextField = new JTextField();
        searchTextField.setBounds(161, 7, 380, 21);
        searchBookPanel.add(searchTextField);
        searchTextField.setColumns(10);


        JLabel resultLabel = new JLabel("查到"+resultRow+"条结果：");
        resultLabel.setBounds(10, 44, 131, 15);
        resultLabel.setVisible(false);
        searchBookPanel.add(resultLabel);

        JButton searchButton = new JButton("查找");
        searchButton.setBounds(569, 6, 93, 23);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchType = "";
                switch (searchComboBox.getSelectedIndex()){
                    case 0:
                        searchType = "B_Name";
                        break;
                    case 1:
                        searchType = "B_Author";
                        break;
                    case 2:
                        searchType = "B_Translator";
                        break;
                }
                String content = searchTextField.getText();
                String searchSql = "SELECT * FROM BookInformation WHERE "+searchType+" LIKE '%"+content+"%'";
                ResultSet rs = operateTest.getKeyWordsBook(searchSql);
                resultRow = getBookInformation(rs);
                resultLabel.setText("已查到"+resultRow+"条结果：");
                resultLabel.setVisible(true);
                String[] columnNames = {"图书编号","书名","作者","译者","最大借阅时间","图书类别","出版时间","出版社","页数"};
                reaultTable.setModel(new DefaultTableModel(getBookInformation(),columnNames));
            }
        });

        searchTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchButton.doClick();
            }
        });

        searchBookPanel.add(searchButton);

        reaultTable = new JTable();
//        reaultTable.setBounds(10, 75, 735, 95);
        String[] columnNames = {"图书编号","书名","作者","译者","最大借阅时间","图书类别","出版时间","出版社","页数"};
        reaultTable.setModel(new DefaultTableModel(getBookInformation(),columnNames));
        JScrollPane table2ScrollPane = new JScrollPane(reaultTable);
        table2ScrollPane.setVisible(true);
        table2ScrollPane.setBounds(10, 75, 735, 95);
        searchBookPanel.add(table2ScrollPane);
//        searchBookPanel.add(reaultTable);



        JLabel lblResultcover = new JLabel("resultCover");
        lblResultcover.setBounds(10, 202, 151, 206);
        lblResultcover.setVisible(false);
        searchBookPanel.add(lblResultcover);

        JLabel resultName = new JLabel("书名：");
        resultName.setBounds(184, 202, 54, 15);
        resultName.setVisible(false);
        searchBookPanel.add(resultName);

        JLabel resultName_1 = new JLabel("New label");
        resultName_1.setBounds(233, 203, 104, 15);
        resultName_1.setVisible(false);
        searchBookPanel.add(resultName_1);

        JLabel resultAuthor = new JLabel("作者：");
        resultAuthor.setBounds(184, 227, 54, 15);
        resultAuthor.setVisible(false);
        searchBookPanel.add(resultAuthor);

        JLabel resultAuthor_1 = new JLabel("New label");
        resultAuthor_1.setBounds(233, 227, 154, 15);
        resultAuthor_1.setVisible(false);
        searchBookPanel.add(resultAuthor_1);

        JLabel resultTranslator = new JLabel("译者：");
        resultTranslator.setBounds(184, 252, 54, 15);
        resultTranslator.setVisible(false);
        searchBookPanel.add(resultTranslator);

        JLabel resultTranslator_1 = new JLabel("New label");
        resultTranslator_1.setBounds(233, 252, 154, 15);
        resultTranslator_1.setVisible(false);
        searchBookPanel.add(resultTranslator_1);

        JLabel resultPress = new JLabel("出版社：");
        resultPress.setBounds(184, 277, 54, 15);
        resultPress.setVisible(false);
        searchBookPanel.add(resultPress);

        JLabel resultPress_1 = new JLabel("New label");
        resultPress_1.setBounds(233, 277, 104, 15);
        resultPress_1.setVisible(false);
        searchBookPanel.add(resultPress_1);

        JLabel resultDate = new JLabel("出版日期：");
        resultDate.setBounds(184, 298, 77, 15);
        resultDate.setVisible(false);
        searchBookPanel.add(resultDate);

        JLabel resultDate_1 = new JLabel("New label");
        resultDate_1.setBounds(243, 298, 104, 15);
        resultDate_1.setVisible(false);
        searchBookPanel.add(resultDate_1);

        JLabel resultPrice = new JLabel("价格：");
        resultPrice.setBounds(184, 323, 54, 15);
        resultPrice.setVisible(false);
        searchBookPanel.add(resultPrice);

        JLabel resultPrice_1 = new JLabel("New label");
        resultPrice_1.setBounds(233, 323, 84, 15);
        resultPrice_1.setVisible(false);
        searchBookPanel.add(resultPrice_1);

        JLabel resultPages = new JLabel("页数：");
        resultPages.setBounds(184, 348, 54, 15);
        resultPages.setVisible(false);
        searchBookPanel.add(resultPages);

        JLabel resultPages_1 = new JLabel("New label");
        resultPages_1.setBounds(233, 348, 54, 15);
        resultPages_1.setVisible(false);
        searchBookPanel.add(resultPages_1);

        JLabel resultBookSum = new JLabel("剩余本数：");
        resultBookSum.setBounds(184, 373, 77, 15);
        resultBookSum.setVisible(false);
        searchBookPanel.add(resultBookSum);

        JLabel resultBookSum_1 = new JLabel("New label");
        resultBookSum_1.setBounds(243, 373, 54, 15);
        resultBookSum_1.setVisible(false);
        searchBookPanel.add(resultBookSum_1);

        JLabel resultBrief = new JLabel("作品简介：");
        resultBrief.setBounds(330, 202, 77, 15);
        resultBrief.setVisible(false);
        searchBookPanel.add(resultBrief);

        JTextArea resultBrief_1 = new JTextArea();
//        resultBrief_1.setBounds(403, 202, 233, 83);
//        resultBrief_1.setVisible(false);
        JScrollPane table3ScrollPane = new JScrollPane(resultBrief_1);
        table3ScrollPane.setVisible(false);
        table3ScrollPane.setBounds(403, 202, 233, 83);
        searchBookPanel.add(table3ScrollPane);

        JLabel resultIntroduction = new JLabel("作者简介：");
        resultIntroduction.setBounds(330, 298, 77, 15);
        resultIntroduction.setVisible(false);
        searchBookPanel.add(resultIntroduction);

        JTextArea resultInstroduction_1 = new JTextArea();
//        resultInstroduction_1.setBounds(403, 294, 233, 83);
//        resultInstroduction_1.setVisible(false);
        JScrollPane table4ScrollPane = new JScrollPane(resultInstroduction_1);
        table4ScrollPane.setVisible(false);
        table4ScrollPane.setBounds(403, 294, 233, 83);
        searchBookPanel.add(table4ScrollPane);
//        searchBookPanel.add(resultInstroduction_1);

        JButton resultBorrowButton = new JButton("借书");
        resultBorrowButton.setBounds(641, 273, 93, 23);
        resultBorrowButton.setVisible(false);
        searchBookPanel.add(resultBorrowButton);
        JLabel bookIDLabel = new JLabel();
        reaultTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if(reaultTable.getValueAt(reaultTable.getSelectedRow(),0)!=null)
                {
                    int bookNumber = (int)reaultTable.getValueAt(reaultTable.getSelectedRow(),0); //获取所选中的行的第一个位置的内容，当然你也可以指定具体的该行第几格
//                    System.out.println(s);
                    ResultSet bookInformation = operateTest.getBookInformation(bookNumber);
                    try{
                        bookInformation.next();
                        bookIDLabel.setText(bookInformation.getInt("B_ID")+"");
                        String coverPath = bookInformation.getString("B_Cover");
                        lblResultcover.setIcon(new ImageIcon(coverPath));
                        String name = bookInformation.getString("B_Name");
                        resultName_1.setText(name);
                        String author = bookInformation.getString("B_Author");
                        resultAuthor_1.setText(author);
                        String press = bookInformation.getString("B_Press");
                        resultPress_1.setText(press);
                        String date = bookInformation.getString("B_PublicationDate");
                        resultDate_1.setText(date);
                        String price = bookInformation.getString("B_Price");
                        resultPrice_1.setText(price);
                        String pages = bookInformation.getString("B_Pages");
                        resultPages_1.setText(pages);
                        String brief = bookInformation.getString("B_BriefIntroduction");
                        resultBrief_1.setText(brief);
                        resultBrief_1.setEditable(false);
                        String instroduction = bookInformation.getString("B_AuthorIntroduction");
                        resultInstroduction_1.setText(instroduction);
                        resultInstroduction_1.setEditable(false);
                        resultBookSum_1.setText(operateTest.getBookSum(bookNumber)+"");
                        lblResultcover.setVisible(true);
                        resultName.setVisible(true);
                        resultName_1.setVisible(true);
                        resultAuthor.setVisible(true);
                        resultAuthor_1.setVisible(true);
                        resultTranslator.setVisible(true);
                        resultTranslator_1.setVisible(true);
                        resultPress.setVisible(true);
                        resultPress_1.setVisible(true);
                        resultDate.setVisible(true);
                        resultDate_1.setVisible(true);
                        resultPrice.setVisible(true);
                        resultPrice_1.setVisible(true);
                        resultPages.setVisible(true);
                        resultPages_1.setVisible(true);
                        resultBookSum.setVisible(true);
                        resultBookSum_1.setVisible(true);
                        resultBrief.setVisible(true);
                        table3ScrollPane.setVisible(true);
                        resultIntroduction.setVisible(true);
                        table4ScrollPane.setVisible(true);
                        resultBorrowButton.setVisible(true);
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
        resultBorrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Integer.parseInt(resultBookSum_1.getText()) <= 0){
                    JOptionPane.showMessageDialog(null,"该书库存不足！","提示",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    operateTest.borrowBook(userID,Integer.parseInt(bookIDLabel.getText()));
                    JOptionPane.showMessageDialog(null,"借书成功！","提示",JOptionPane.INFORMATION_MESSAGE);
                    getBorrowInformation(userID);
//                    borrowInformationTable.repaint();
                    initTable();
//                    setReserveInformationPanel();
                }
            }
        });
    }

    public void setReserveInformationPanel(){
        reserveInformationPanel.setBounds(0, 0, 745, 413);
        reserveInformationPanel.setLayout(null);
        contentPane.add(reserveInformationPanel);
        reserveInformationPanel.setVisible(false);



        JLabel bookCoverLabel = new JLabel();
        bookCoverLabel.setBounds(10, 191, 156, 221);
        bookCoverLabel.setVisible(false);
        reserveInformationPanel.add(bookCoverLabel);

        JLabel bookName = new JLabel("书名：");
        bookName.setBounds(203, 190, 54, 15);
        bookName.setVisible(false);
        reserveInformationPanel.add(bookName);

        JLabel bookNameLabel_1 = new JLabel("New label");
        bookNameLabel_1.setBounds(257, 190, 104, 15);
        bookNameLabel_1.setVisible(false);
        reserveInformationPanel.add(bookNameLabel_1);

        JLabel authorLabel = new JLabel("作者：");
        authorLabel.setBounds(203, 215, 54, 15);
        authorLabel.setVisible(false);
        reserveInformationPanel.add(authorLabel);

        JLabel authorLabel_1 = new JLabel("New label");
        authorLabel_1.setBounds(257, 215, 129, 15);
        authorLabel_1.setVisible(false);
        reserveInformationPanel.add(authorLabel_1);

        JLabel translatorLabel = new JLabel("译者：");
        translatorLabel.setBounds(203, 240, 54, 15);
        translatorLabel.setVisible(false);
        reserveInformationPanel.add(translatorLabel);

        JLabel translatorLabel_1 = new JLabel("New label");
        translatorLabel_1.setBounds(257, 240, 129, 15);
        translatorLabel_1.setVisible(false);
        reserveInformationPanel.add(translatorLabel_1);

        JLabel bookTypeLabel = new JLabel("类别：");
        bookTypeLabel.setBounds(203, 265, 71, 15);
        bookTypeLabel.setVisible(false);
        reserveInformationPanel.add(bookTypeLabel);

        JLabel bookTypeLabel_1 = new JLabel("New label");
        bookTypeLabel_1.setBounds(267, 265, 84, 15);
        bookTypeLabel_1.setVisible(false);
        reserveInformationPanel.add(bookTypeLabel_1);

        JLabel priceLabel = new JLabel("价格：");
        priceLabel.setBounds(203, 290, 54, 15);
        priceLabel.setVisible(false);
        reserveInformationPanel.add(priceLabel);

        JLabel priceLabel_1 = new JLabel("New label");
        priceLabel_1.setBounds(257, 290, 84, 15);
        priceLabel_1.setVisible(false);
        reserveInformationPanel.add(priceLabel_1);

        JLabel pagesLabel = new JLabel("页数：");
        pagesLabel.setBounds(203, 315, 54, 15);
        pagesLabel.setVisible(false);
        reserveInformationPanel.add(pagesLabel);

        JLabel pagesLabel_1 = new JLabel("New label");
        pagesLabel_1.setBounds(257, 315, 84, 15);
        pagesLabel_1.setVisible(false);
        reserveInformationPanel.add(pagesLabel_1);

        JLabel ISBNLabel = new JLabel("ISBN：");
        ISBNLabel.setBounds(203, 340, 54, 15);
        ISBNLabel.setVisible(false);
        reserveInformationPanel.add(ISBNLabel);

        JLabel ISBNLabel_1 = new JLabel("New label");
        ISBNLabel_1.setBounds(257, 340, 114, 15);
        ISBNLabel_1.setVisible(false);
        reserveInformationPanel.add(ISBNLabel_1);

        JLabel borrowDateLabel = new JLabel("借阅日期：");
        borrowDateLabel.setBounds(382, 191, 84, 15);
        borrowDateLabel.setVisible(false);
        reserveInformationPanel.add(borrowDateLabel);

        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(460, 190, 84, 15);
        lblNewLabel.setVisible(false);
        reserveInformationPanel.add(lblNewLabel);

        JLabel deadLineLabel = new JLabel("截止日期：");
        deadLineLabel.setBounds(382, 215, 71, 15);
        deadLineLabel.setVisible(false);
        reserveInformationPanel.add(deadLineLabel);

        JLabel deadLine_1 = new JLabel("New label");
        deadLine_1.setBounds(460, 215, 84, 15);
        deadLine_1.setVisible(false);
        reserveInformationPanel.add(deadLine_1);

        JLabel isReturnLabel = new JLabel("是否归还：");
        isReturnLabel.setBounds(382, 240, 71, 15);
        isReturnLabel.setVisible(false);
        reserveInformationPanel.add(isReturnLabel);

        JLabel isReturnLabel_1 = new JLabel("New label");
        isReturnLabel_1.setBounds(460, 240, 84, 15);
        isReturnLabel_1.setVisible(false);
        reserveInformationPanel.add(isReturnLabel_1);

        JLabel returnDateLabel = new JLabel("归还日期：");
        returnDateLabel.setBounds(382, 265, 76, 15);
        returnDateLabel.setVisible(false);
        reserveInformationPanel.add(returnDateLabel);

        JLabel returnDateLabel_1 = new JLabel("New label");
        returnDateLabel_1.setBounds(459, 265, 84, 15);
        returnDateLabel_1.setVisible(false);
        reserveInformationPanel.add(returnDateLabel_1);

        JButton returnButton = new JButton("还书");
        returnButton.setBounds(384, 312, 125, 35);
        returnButton.setVisible(false);
        reserveInformationPanel.add(returnButton);

        String[] columnNames = {"借阅记录编号","用户编号","书籍编号","借阅时间","应还时间","是否归还","还书时间"};
        Vector column = new Vector();
        column.addElement(columnNames);
        getBorrowInformation(this.userID);
        borrowInformationTable.setModel(new DefaultTableModel(this.borrowInformation,columnNames));
        JScrollPane tableScrollPane = new JScrollPane(borrowInformationTable);
        tableScrollPane.setVisible(true);
        tableScrollPane.setBounds(0,10,readerInformationPanel.getWidth(),readerInformationPanel.getHeight()/3);
        reserveInformationPanel.add(tableScrollPane);


        JLabel borrowLabel = new JLabel();
        JLabel bookIDLabel = new JLabel();
        borrowInformationTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if(borrowInformationTable.getValueAt(borrowInformationTable.getSelectedRow(),0)!=null)
                {
                    int borrowNumber = (int)borrowInformationTable.getValueAt(borrowInformationTable.getSelectedRow(),0); //获取所选中的行的第一个位置的内容，当然你也可以指定具体的该行第几格
//                    System.out.println(s);
                    borrowLabel.setText(""+borrowNumber);
                    ResultSet borrowInformation = operateTest.getBorrowNumberInformation(borrowNumber);
                    try {
                        borrowInformation.next();
                        int bookID = borrowInformation.getInt("B_ID");
                        bookIDLabel.setText(""+bookID);
                        ResultSet bookInformation = operateTest.getBookInformation(bookID);
                        bookInformation.next();
                        String coverPath = bookInformation.getString("B_Cover");
                        bookCoverLabel.setIcon(new ImageIcon(coverPath));
                        String bookName = bookInformation.getString("B_Name");
                        bookNameLabel_1.setText(bookName);
                        String author = bookInformation.getString("B_Author");
                        authorLabel_1.setText(author);
                        int bookTypeNumber = bookInformation.getInt("B_BookTypeNumber");
//                        String translator = bookInformation.getString("B_Type");
                        bookTypeLabel_1.setText(operateTest.getBookTypeName(bookTypeNumber));
                        String price = bookInformation.getString("B_Price");
                        priceLabel_1.setText(price);
                        String pages = bookInformation.getString("B_Pages");
                        pagesLabel_1.setText(pages);
                        String ISBN = bookInformation.getString("B_ISBN");
                        ISBNLabel_1.setText(ISBN);
                        String borrowDate = borrowInformation.getString("B_BorrowedDate");
                        lblNewLabel.setText(borrowDate);
                        String deadLine = borrowInformation.getString("B_DeadLine");
                        deadLine_1.setText(deadLine);
                        boolean isReturn = borrowInformation.getBoolean("B_IsReturn");
                        isReturnLabel_1.setText(""+isReturn);
                        String returnDate = borrowInformation.getString("B_ReturnDate");
                        returnDateLabel_1.setText(returnDate);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                    bookCoverLabel.setVisible(true);
                    bookName.setVisible(true);
                    bookNameLabel_1.setVisible(true);
                    authorLabel.setVisible(true);
                    authorLabel_1.setVisible(true);
                    translatorLabel.setVisible(true);
                    translatorLabel_1.setVisible(true);
                    bookTypeLabel.setVisible(true);
                    bookTypeLabel_1.setVisible(true);
                    priceLabel.setVisible(true);
                    priceLabel_1.setVisible(true);
                    pagesLabel.setVisible(true);
                    pagesLabel_1.setVisible(true);
                    ISBNLabel.setVisible(true);
                    ISBNLabel_1.setVisible(true);
                    borrowDateLabel.setVisible(true);
                    lblNewLabel.setVisible(true);
                    deadLineLabel.setVisible(true);
                    deadLine_1.setVisible(true);
                    isReturnLabel.setVisible(true);
                    isReturnLabel_1.setVisible(true);
                    returnDateLabel.setVisible(true);
                    returnDateLabel_1.setVisible(true);
                    returnButton.setVisible(true);
                }
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Boolean.parseBoolean(isReturnLabel_1.getText())){
                    JOptionPane.showMessageDialog(null,"图书已还！","提示",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    operateTest.returnBook(Integer.parseInt(borrowLabel.getText()),userID,Integer.parseInt(bookIDLabel.getText()));
                    JOptionPane.showMessageDialog(null,"还书成功！","提示",JOptionPane.INFORMATION_MESSAGE);
                    getBorrowInformation(userID);
                    borrowInformationTable.repaint();
//                    setReserveInformationPanel();
//                    borrowInformationTable.
                    initTable();
//                    borrowInformationTable.setModel(new DefaultTableModel(getBorrowInformation(),columnNames));
                }
            }
        });



    }

    public Object[][] getBorrowInformation(){
        return this.borrowInformation;
    }

    public String getType(int userID){
        String type = "";
        try {
            ResultSet rs = operateTest.getReaderInformation(userID);
            rs.next();
            type = rs.getString("R_Type");
        }catch (Exception e){
            e.printStackTrace();
        }
        return type;
    }

    public int getBookInformation(ResultSet rs){
        int row = -1;
        try{
            rs.last();
            row = rs.getRow();
            rs.first();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.bookInformation = new Object[row][9];
//        System.out.println(row);
        int i = 0;
        String[] columnNames = {"图书编号","书名","作者","译者","最大借阅时间","图书类别","出版时间","出版社","页数"};
        try {
            while (i<row) {
                int bookID = rs.getInt("B_ID");
                String bookName = rs.getString("B_Name");
                String author = rs.getString("B_Author");
                String translator = rs.getString("B_Translator");
                int maxBorrowDays = rs.getInt("B_MaxDays");
                int typeNumber = rs.getInt("B_BookTypeNumber");
                String type = operateTest.getBookTypeName(typeNumber);
                String date = rs.getString("B_PublicationDate");
                String press = rs.getString("B_Press");
                String pages = rs.getString("B_Pages");
                Object[] information = {bookID,bookName,author,translator,maxBorrowDays,type,date,press,pages};
                this.bookInformation[i] = information;
//                System.out.println(bookID);
                i++;
                rs.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return row;
    }
    public Object[][] getBookInformation(){
        return this.bookInformation;
    }

    public  void initTable(){
        String[] columnNames = {"借阅记录编号","用户编号","书籍编号","借阅时间","应还时间","是否归还","还书时间"};
        getBorrowInformation(this.userID);
        borrowInformationTable.setModel(new DefaultTableModel(this.borrowInformation,columnNames));
    }

//    public void setInternetPanel()throws Exception{
//        UIUtils.setPreferredLookAndFeel();
//        NativeInterface.open();
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    JFrame frame = new JFrame("Ives");
//                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                    frame.getContentPane().add(new internetGui(), BorderLayout.CENTER);
//                    frame.setSize(800, 600);
//                    frame.setLocationByPlatform(true);
//                    frame.setVisible(true);
//                } catch (HeadlessException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (URISyntaxException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        });
//        NativeInterface.runEventPump();
//    }
}
