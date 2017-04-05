package SystemGui;

import DataBase.DataBaseOperate;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.ObjIntConsumer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AdminGui extends JFrame {

    private DataBaseOperate operateTest = new DataBaseOperate();
    private JPanel contentPane;
    private JTextField searchUserTextField;
    private JTextField IDTextField;
    private JTextField bookInformationTextField;
    private JTextField bookNumberTextField;
    private JTextField competenceIDField;
    JPanel competencePanel = new JPanel();
    JPanel bookBorrowPanel = new JPanel();
    JPanel userBorrowPanel = new JPanel();
    JPanel informationPanel = new JPanel();
    JPanel bookInformationPanel = new JPanel();
    private Object[][] userInformation;
    private Object[][] userBorrow;
    private Object[][] bookInformation;
    private Object[][] bookBorrow;
    private Object[][] competence;
    private int borrowNumber;
    private int bookNumber;
    private int userID;
    public JPanel imgPanel = new JPanel();

    /**
     * Launch the application.
     */

    /**
     * Create the frame.
     */
    public AdminGui() {
        setTitle("图书管理系统管理员界面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 771, 483);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu userInformationMenu = new JMenu("用户信息管理");
        menuBar.add(userInformationMenu);

        JMenuItem informationMenuItem = new JMenuItem("个人信息管理");
        userInformationMenu.add(informationMenuItem);
        informationMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imgPanel.setVisible(false);
                bookInformationPanel.setVisible(false);
                bookBorrowPanel.setVisible(false);
                informationPanel.setVisible(true);
                userBorrowPanel.setVisible(false);
                competencePanel.setVisible(false);
            }
        });

        JMenuItem userBorrowMenuItem = new JMenuItem("用户借阅记录管理");
        userInformationMenu.add(userBorrowMenuItem);
        userBorrowMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imgPanel.setVisible(false);
                bookInformationPanel.setVisible(false);
                bookBorrowPanel.setVisible(false);
                informationPanel.setVisible(false);
                userBorrowPanel.setVisible(true);
                competencePanel.setVisible(false);
            }
        });

        JMenu bookInformationMenu = new JMenu("图书信息管理");
        menuBar.add(bookInformationMenu);

        JMenuItem bookInformationMenuItem = new JMenuItem("图书信息管理");
        bookInformationMenu.add(bookInformationMenuItem);
        bookInformationMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imgPanel.setVisible(false);
                bookInformationPanel.setVisible(true);
                bookBorrowPanel.setVisible(false);
                informationPanel.setVisible(false);
                userBorrowPanel.setVisible(false);
                competencePanel.setVisible(false);
            }
        });

        JMenuItem bookBorrowMenuItem = new JMenuItem("图书借阅记录管理");
        bookInformationMenu.add(bookBorrowMenuItem);
        bookBorrowMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imgPanel.setVisible(false);
                bookInformationPanel.setVisible(false);
                bookBorrowPanel.setVisible(true);
                informationPanel.setVisible(false);
                userBorrowPanel.setVisible(false);
                competencePanel.setVisible(false);
            }
        });

        JMenu competenceMenu = new JMenu("角色权限管理");
        menuBar.add(competenceMenu);

        JMenuItem compentenceMenuItem = new JMenuItem("角色权限管理");
        competenceMenu.add(compentenceMenuItem);
        compentenceMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imgPanel.setVisible(false);
                bookInformationPanel.setVisible(false);
                bookBorrowPanel.setVisible(false);
                informationPanel.setVisible(false);
                userBorrowPanel.setVisible(false);
                competencePanel.setVisible(true);
            }
        });

        JMenu aboutMenu = new JMenu("关于");
        menuBar.add(aboutMenu);

        JMenuItem exitMenuItem = new JMenuItem("安全退出");
        aboutMenu.add(exitMenuItem);

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login gui = new Login();
                gui.setVisible(true);
            }
        });

        JMenuItem authorMenuItem = new JMenuItem("关于作者");
        aboutMenu.add(authorMenuItem);
        authorMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"作者\n姓名：赵宏鹏\n班级：计科1403\n学号：1030414313","作者",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        imgPanel.setBounds(0, 0, 745, 413);
        imgPanel.setLayout(null);
        contentPane.add(imgPanel);
        imgPanel.setVisible(true);
        JLabel imgLabel = new JLabel(new ImageIcon("src/2.jpg"));
        imgLabel.setVisible(true);
        imgPanel.add(imgLabel);
        imgLabel.setBounds(0,0,755,493);

        setBookInformationPanel();
        setBookBorrowPanel();
        setInformationPanel();
        setUserBorrowPanel();
        setCompetencePanel();

        bookInformationPanel.setVisible(false);
        bookBorrowPanel.setVisible(false);
        informationPanel.setVisible(false);
        userBorrowPanel.setVisible(false);
        competencePanel.setVisible(false);
    }

    public void setInformationPanel(){
        informationPanel.setBounds(0, 0, 745, 413);
        contentPane.add(informationPanel);
        informationPanel.setLayout(null);

        JComboBox searchUserComboBox = new JComboBox();
        searchUserComboBox.setModel(new DefaultComboBoxModel(new String[] {"用户ID", "用户名称", "证件号码"}));
        searchUserComboBox.setSelectedIndex(0);
        searchUserComboBox.setBounds(95, 10, 83, 21);
        informationPanel.add(searchUserComboBox);

        searchUserTextField = new JTextField();
        searchUserTextField.setBounds(177, 10, 366, 21);
        informationPanel.add(searchUserTextField);
        searchUserTextField.setColumns(10);

        JButton searchUserButton = new JButton("搜索");
        searchUserButton.setBounds(549, 8, 93, 23);

        searchUserTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchUserButton.doClick();
            }
        });

        JButton freshUserButton = new JButton("刷新");
        freshUserButton.setBounds(649, 8, 93, 23);
        informationPanel.add(freshUserButton);


        informationPanel.add(searchUserButton);
        JButton changeInformationButton = new JButton("修改用户信息");
        changeInformationButton.setBounds(317, 318, 120, 23);
        changeInformationButton.setVisible(false);
        informationPanel.add(changeInformationButton);

        JTable informationTable = new JTable();
//        reaultTable.setBounds(10, 75, 735, 95);
        String[] columnNames = {"用户编号","名称","性别","类别","出生日期","证件类别","证件号码","电话号码","EMail","密码",
                "最大借书数量","已借书数量"};
        informationTable.setModel(new DefaultTableModel(getUserInformation(),columnNames));
        JScrollPane table1ScrollPane = new JScrollPane(informationTable);
        table1ScrollPane.setVisible(true);
        table1ScrollPane.setBounds(10, 55, 735, 95);
        informationPanel.add(table1ScrollPane);

        freshUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                informationTable.setModel(new DefaultTableModel(getUserInformation(),columnNames));
                searchUserButton.doClick();
            }
        });

        informationTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if(informationTable.getValueAt(informationTable.getSelectedRow(),0)!=null)
                {
                    userID = (int)informationTable.getValueAt(informationTable.getSelectedRow(),0); //获取所选中的行的第一个位置的内容，当然你也可以指定具体的该行第几格
                }
            }
        });

        changeInformationButton.setVisible(true);
        changeInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeAdminInformation gui = new ChangeAdminInformation(userID);
                gui.setVisible(true);
                gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        searchUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (searchUserComboBox.getSelectedIndex()){
                    case 0:
                        setUserInformationID(Integer.parseInt(searchUserTextField.getText()));
                        break;
                    case 1:
                        setUserInformationName(searchUserTextField.getText());
                        break;
                    case 2:
                        setUserInformationCre(searchUserTextField.getText());
                        break;
                }
                informationTable.setModel(new DefaultTableModel(getUserInformation(),columnNames));
            }
        });
    }

    public void setUserBorrowPanel(){
        userBorrowPanel.setBounds(0, 0, 745, 412);
        contentPane.add(userBorrowPanel);
        userBorrowPanel.setLayout(null);

        JLabel IDLabel = new JLabel("用户ID：");
        IDLabel.setBounds(161, 17, 93, 15);
        userBorrowPanel.add(IDLabel);

        IDTextField = new JTextField();
        IDTextField.setBounds(233, 14, 194, 21);
        userBorrowPanel.add(IDTextField);
        IDTextField.setColumns(10);

        JButton searchButton = new JButton("查找");
        searchButton.setBounds(447, 13, 93, 23);
        userBorrowPanel.add(searchButton);

        JButton freshButton2 = new JButton("刷新");
        freshButton2.setBounds(616, 13, 93, 23);
        userBorrowPanel.add(freshButton2);

        IDTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchButton.doClick();
            }
        });

        freshButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchButton.doClick();
            }
        });

        JTable userBorrowTable = new JTable();
//        reaultTable.setBounds(10, 75, 735, 95);
        String[] columnNames = {"借阅编号","用户编号","图书编号","借阅时间","截止日期","是否归还","归还日期"};
        userBorrowTable.setModel(new DefaultTableModel(getUserBorrow(),columnNames));
        JScrollPane table2ScrollPane = new JScrollPane(userBorrowTable);
        table2ScrollPane.setVisible(true);
        table2ScrollPane.setBounds(10, 55, 585, 145);
        userBorrowPanel.add(table2ScrollPane);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(123);
                setUserBorrow(Integer.parseInt(IDTextField.getText()));
                userBorrowTable.setModel(new DefaultTableModel(getUserBorrow(),columnNames));
            }
        });
        JButton addButton = new JButton("添加记录");
        addButton.setBounds(616, 57, 93, 23);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBorrowGui gui = new AddBorrowGui();
                gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                gui.setVisible(true);
            }
        });
        userBorrowPanel.add(addButton);

        JButton deleteButton = new JButton("删除记录");
        deleteButton.setBounds(616, 109, 93, 23);
        userBorrowPanel.add(deleteButton);

        JButton changeButton = new JButton("修改记录");
        changeButton.setBounds(616, 163, 93, 23);
        userBorrowPanel.add(changeButton);

//        JLabel borrowNumberLabel = new JLabel("1");
        userBorrowTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if(userBorrowTable.getValueAt(userBorrowTable.getSelectedRow(),0)!=null)
                {
                    borrowNumber = (int)userBorrowTable.getValueAt(userBorrowTable.getSelectedRow(),0); //获取所选中的行的第一个位置的内容，当然你也可以指定具体的该行第几格
//                    borrowNumberLabel.setText(""+borrowNumber);
//                    int borrowNumber = Integer.parseInt(borrowNumberLabel.getText());
                }
                ;
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operateTest.deleteBorrowInformation(borrowNumber);
                JOptionPane.showMessageDialog(null,"删除成功","成功",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ChangeBorrowGui gui = new ChangeBorrowGui(borrowNumber);
                    gui.setVisible(true);
                    gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });

    }

    public void setBookInformationPanel(){
        bookInformationPanel.setBounds(0, 0, 745, 413);
        contentPane.add(bookInformationPanel);
        bookInformationPanel.setLayout(null);

        JComboBox bookInformawionComboBox = new JComboBox();
        bookInformawionComboBox.setModel(new DefaultComboBoxModel(new String[] {"书名", "作者", "图书编号"}));
        bookInformawionComboBox.setBounds(140, 10, 81, 21);
        bookInformationPanel.add(bookInformawionComboBox);

        bookInformationTextField = new JTextField();
        bookInformationTextField.setBounds(222, 10, 308, 21);
        bookInformationPanel.add(bookInformationTextField);
        bookInformationTextField.setColumns(10);

        JButton bookSearchButton = new JButton("查找");
        bookSearchButton.setBounds(540, 9, 93, 23);
        bookInformationPanel.add(bookSearchButton);

        JButton bookRefreshButton = new JButton("刷新");
        bookRefreshButton.setBounds(650, 9, 93, 23);
        bookInformationPanel.add(bookRefreshButton);

        bookRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookSearchButton.doClick();
            }
        });

        bookInformationTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookSearchButton.doClick();
            }
        });

        JButton addBookButton = new JButton("添加图书");
        addBookButton.setBounds(617, 200, 125, 23);
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBookGui gui = new AddBookGui();
                gui.setVisible(true);
                gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        bookInformationPanel.add(addBookButton);

        JButton deleteBookButton = new JButton("删除图书");
        deleteBookButton.setBounds(617, 260, 125, 23);
        bookInformationPanel.add(deleteBookButton);

        JButton changeBookButton = new JButton("修改图书信息");
        changeBookButton.setBounds(617, 322, 125, 23);
        changeBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeBookGui gui = new ChangeBookGui(bookNumber);
                gui.setVisible(true);
                gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        bookInformationPanel.add(changeBookButton);

        JTable bookInformationTable = new JTable();
//        reaultTable.setBounds(10, 75, 735, 95);
        String[] columnNames = {"图书编号","书名","作者","译者","最大借阅时间","图书类别","出版时间","出版社","页数"};
        bookInformationTable.setModel(new DefaultTableModel(getBookInformation(),columnNames));
        JScrollPane table3ScrollPane = new JScrollPane(bookInformationTable);
        table3ScrollPane.setVisible(true);
        table3ScrollPane.setBounds(10, 55, 735, 125);
        bookInformationPanel.add(table3ScrollPane);

        bookSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (bookInformawionComboBox.getSelectedIndex()){
                    case 0:
                        setBookInformationName(bookInformationTextField.getText());
                        break;
                    case 1:
                        setBookInformationAuthor(bookInformationTextField.getText());
                        break;
                    case 2:
                        setBookInformationID(Integer.parseInt(bookInformationTextField.getText()));
                        break;
                }
                bookInformationTable.setModel(new DefaultTableModel(getBookInformation(),columnNames));
            }
        });

        JLabel lblResultcover = new JLabel("resultCover");
        lblResultcover.setBounds(10, 202, 151, 206);
        lblResultcover.setVisible(false);
        bookInformationPanel.add(lblResultcover);

        JLabel resultName = new JLabel("书名：");
        resultName.setBounds(184, 202, 54, 15);
        resultName.setVisible(false);
        bookInformationPanel.add(resultName);

        JLabel resultName_1 = new JLabel("New label");
        resultName_1.setBounds(233, 203, 104, 15);
        resultName_1.setVisible(false);
        bookInformationPanel.add(resultName_1);

        JLabel resultAuthor = new JLabel("作者：");
        resultAuthor.setBounds(184, 227, 54, 15);
        resultAuthor.setVisible(false);
        bookInformationPanel.add(resultAuthor);

        JLabel resultAuthor_1 = new JLabel("New label");
        resultAuthor_1.setBounds(233, 227, 154, 15);
        resultAuthor_1.setVisible(false);
        bookInformationPanel.add(resultAuthor_1);

        JLabel resultTranslator = new JLabel("译者：");
        resultTranslator.setBounds(184, 252, 54, 15);
        resultTranslator.setVisible(false);
        bookInformationPanel.add(resultTranslator);

        JLabel resultTranslator_1 = new JLabel("New label");
        resultTranslator_1.setBounds(233, 252, 154, 15);
        resultTranslator_1.setVisible(false);
        bookInformationPanel.add(resultTranslator_1);

        JLabel resultPress = new JLabel("出版社：");
        resultPress.setBounds(184, 277, 54, 15);
        resultPress.setVisible(false);
        bookInformationPanel.add(resultPress);

        JLabel resultPress_1 = new JLabel("New label");
        resultPress_1.setBounds(233, 277, 104, 15);
        resultPress_1.setVisible(false);
        bookInformationPanel.add(resultPress_1);

        JLabel resultDate = new JLabel("出版日期：");
        resultDate.setBounds(184, 298, 77, 15);
        resultDate.setVisible(false);
        bookInformationPanel.add(resultDate);

        JLabel resultDate_1 = new JLabel("New label");
        resultDate_1.setBounds(243, 298, 104, 15);
        resultDate_1.setVisible(false);
        bookInformationPanel.add(resultDate_1);

        JLabel resultPrice = new JLabel("价格：");
        resultPrice.setBounds(184, 323, 54, 15);
        resultPrice.setVisible(false);
        bookInformationPanel.add(resultPrice);

        JLabel resultPrice_1 = new JLabel("New label");
        resultPrice_1.setBounds(233, 323, 84, 15);
        resultPrice_1.setVisible(false);
        bookInformationPanel.add(resultPrice_1);

        JLabel resultPages = new JLabel("页数：");
        resultPages.setBounds(184, 348, 54, 15);
        resultPages.setVisible(false);
        bookInformationPanel.add(resultPages);

        JLabel resultPages_1 = new JLabel("New label");
        resultPages_1.setBounds(233, 348, 54, 15);
        resultPages_1.setVisible(false);
        bookInformationPanel.add(resultPages_1);

        JLabel resultBookSum = new JLabel("剩余本数：");
        resultBookSum.setBounds(184, 373, 77, 15);
        resultBookSum.setVisible(false);
        bookInformationPanel.add(resultBookSum);

        JLabel resultBookSum_1 = new JLabel("New label");
        resultBookSum_1.setBounds(243, 373, 54, 15);
        resultBookSum_1.setVisible(false);
        bookInformationPanel.add(resultBookSum_1);

        JLabel resultBrief = new JLabel("作品简介：");
        resultBrief.setBounds(330, 202, 77, 15);
        resultBrief.setVisible(false);
        bookInformationPanel.add(resultBrief);

        JTextArea resultBrief_1 = new JTextArea();
//        resultBrief_1.setBounds(403, 202, 233, 83);
//        resultBrief_1.setVisible(false);
        JScrollPane table4ScrollPane = new JScrollPane(resultBrief_1);
        table4ScrollPane.setVisible(false);
        table4ScrollPane.setBounds(403, 202, 203, 83);
        bookInformationPanel.add(table4ScrollPane);

        JLabel resultIntroduction = new JLabel("作者简介：");
        resultIntroduction.setBounds(330, 298, 77, 15);
        resultIntroduction.setVisible(false);
        bookInformationPanel.add(resultIntroduction);

        JTextArea resultInstroduction_1 = new JTextArea();
//        resultInstroduction_1.setBounds(403, 294, 233, 83);
//        resultInstroduction_1.setVisible(false);
        JScrollPane table5ScrollPane = new JScrollPane(resultInstroduction_1);
        table5ScrollPane.setVisible(false);
        table5ScrollPane.setBounds(403, 294, 203, 83);
        bookInformationPanel.add(table5ScrollPane);

        bookInformationTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if(bookInformationTable.getValueAt(bookInformationTable.getSelectedRow(),0)!=null)
                {
                    bookNumber = (int)bookInformationTable.getValueAt(bookInformationTable.getSelectedRow(),0); //获取所选中的行的第一个位置的内容，当然你也可以指定具体的该行第几格
//                    System.out.println(s);
                    ResultSet bookInformation = operateTest.getBookInformation(bookNumber);
                    try{
                        bookInformation.next();
//                        bookInformationTable.setText(bookInformation.getInt("B_ID")+"");
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
                        table4ScrollPane.setVisible(true);
                        resultIntroduction.setVisible(true);
                        table5ScrollPane.setVisible(true);
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    public void setBookBorrowPanel(){
        bookBorrowPanel.setBounds(0, 0, 745, 413);
        contentPane.add(bookBorrowPanel);
        bookBorrowPanel.setLayout(null);

        JLabel bookNumberLabel = new JLabel("图书ID：");
        bookNumberLabel.setBounds(174, 21, 69, 15);
        bookBorrowPanel.add(bookNumberLabel);

        bookNumberTextField = new JTextField();
        bookNumberTextField.setBounds(231, 18, 259, 21);
        bookBorrowPanel.add(bookNumberTextField);
        bookNumberTextField.setColumns(10);

        JButton bookBorrowSearchButton = new JButton("查找");
        bookBorrowSearchButton.setBounds(514, 17, 93, 23);
        bookBorrowPanel.add(bookBorrowSearchButton);

        userBorrowPanel.setBounds(0, 0, 745, 412);
        contentPane.add(userBorrowPanel);
        bookBorrowPanel.setLayout(null);

        JButton freshButton2 = new JButton("刷新");
        freshButton2.setBounds(616, 17, 93, 23);
        bookBorrowPanel.add(freshButton2);

        bookNumberTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookBorrowSearchButton.doClick();
            }
        });

        freshButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookBorrowSearchButton.doClick();
            }
        });

        JTable bookBorrowTable = new JTable();
//        reaultTable.setBounds(10, 75, 735, 95);
        String[] columnNames = {"借阅编号","用户编号","图书编号","借阅时间","截止日期","是否归还","归还日期"};
        bookBorrowTable.setModel(new DefaultTableModel(getBookBorrow(),columnNames));
        JScrollPane table2ScrollPane = new JScrollPane(bookBorrowTable);
        table2ScrollPane.setVisible(true);
        table2ScrollPane.setBounds(10, 55, 585, 145);
        bookBorrowPanel.add(table2ScrollPane);

        bookBorrowSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(123);
                setBookBorrow(Integer.parseInt(bookNumberTextField.getText()));
                bookBorrowTable.setModel(new DefaultTableModel(getBookBorrow(),columnNames));
            }
        });
        JButton addButton = new JButton("添加记录");
        addButton.setBounds(616, 57, 93, 23);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBorrowGui gui = new AddBorrowGui();
                gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                gui.setVisible(true);
            }
        });
        bookBorrowPanel.add(addButton);

        JButton deleteButton = new JButton("删除记录");
        deleteButton.setBounds(616, 109, 93, 23);
        bookBorrowPanel.add(deleteButton);

        JButton changeButton = new JButton("修改记录");
        changeButton.setBounds(616, 163, 93, 23);
        bookBorrowPanel.add(changeButton);

//        JLabel borrowNumberLabel = new JLabel("1");
        bookBorrowTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if(bookBorrowTable.getValueAt(bookBorrowTable.getSelectedRow(),0)!=null)
                {
                    borrowNumber = (int)bookBorrowTable.getValueAt(bookBorrowTable.getSelectedRow(),0); //获取所选中的行的第一个位置的内容，当然你也可以指定具体的该行第几格
//                    borrowNumberLabel.setText(""+borrowNumber);
//                    int borrowNumber = Integer.parseInt(borrowNumberLabel.getText());
                }
                ;
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operateTest.deleteBorrowInformation(borrowNumber);
                JOptionPane.showMessageDialog(null,"删除成功","成功",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ChangeBorrowGui gui = new ChangeBorrowGui(borrowNumber);
                    gui.setVisible(true);
                    gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }

    public void setCompetencePanel(){
        competencePanel.setBounds(0, 0, 745, 413);
        contentPane.add(competencePanel);
        competencePanel.setLayout(null);

        JLabel competenceIDLabel = new JLabel("用户ID:");
        competenceIDLabel.setBounds(159, 10, 54, 15);
        competencePanel.add(competenceIDLabel);

        competenceIDField = new JTextField();
        competenceIDField.setBounds(208, 7, 293, 21);
        competencePanel.add(competenceIDField);
        competenceIDField.setColumns(10);

        JButton competenceSearchButton = new JButton("查找");
        competenceSearchButton.setBounds(526, 6, 93, 23);
        competencePanel.add(competenceSearchButton);

        JButton competenceRefreshButton = new JButton("刷新");
        competenceRefreshButton.setBounds(626, 6, 93, 23);
        competencePanel.add(competenceRefreshButton);

        competenceRefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                competenceSearchButton.doClick();
            }
        });

        competenceIDField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                competenceSearchButton.doClick();
            }
        });
        JButton setAdminButton = new JButton("设置为管理员");
        setAdminButton.setBounds(193, 299, 151, 23);
        setAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operateTest.setAdmin(userID);
                JOptionPane.showMessageDialog(null, "设置管理员成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        competencePanel.add(setAdminButton);

        JButton setReaderButton = new JButton("设置为读者");
        setReaderButton.setBounds(385, 299, 151, 23);
        competencePanel.add(setReaderButton);

        setReaderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operateTest.setReader(userID);
                JOptionPane.showMessageDialog(null, "设置读者成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });

//        JButton changeCompetenceButton = new JButton("修改权限");
//        changeCompetenceButton.setBounds(479, 299, 151, 23);
//        competencePanel.add(changeCompetenceButton);

        JTable competenceaTble = new JTable();
//        reaultTable.setBounds(10, 75, 735, 95);
        String[] columnNames = {"用户编号","用户角色"};
        competenceaTble.setModel(new DefaultTableModel(getCompetence(),columnNames));
        JScrollPane table6ScrollPane = new JScrollPane(competenceaTble);
        table6ScrollPane.setVisible(true);
        table6ScrollPane.setBounds(10, 55, 735, 145);
        competencePanel.add(table6ScrollPane);

        competenceSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCompetence(Integer.parseInt(competenceIDField.getText()));
                competenceaTble.setModel(new DefaultTableModel(getCompetence(),columnNames));
            }
        });

        competenceaTble.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if(competenceaTble.getValueAt(competenceaTble.getSelectedRow(),0)!=null)
                {
                    userID = (int)competenceaTble.getValueAt(competenceaTble.getSelectedRow(),0); //获取所选中的行的第一个位置的内容，当然你也可以指定具体的该行第几格
//                    borrowNumberLabel.setText(""+borrowNumber);
//                    int borrowNumber = Integer.parseInt(borrowNumberLabel.getText());
                }
                ;
            }
        });
    }

    public int setUserInformationID(int userID){
        ResultSet rs = operateTest.getUserInformation(userID);
        int row = -1;
        try{
            rs.last();
            row = rs.getRow();
            rs.first();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (row == 0||row == -1){
            JOptionPane.showMessageDialog(null,"没有该用户！","提示",JOptionPane.ERROR_MESSAGE);
            return row;
        }
        this.userInformation = new Object[row][12];
//        System.out.println(row);
        int i = 0;
        String[] columnNames = {"用户编号","名称","性别","类别","出生日期","证件类别","证件号码","电话号码","EMail","密码",
                "最大借书数量","已借书数量"};
        try {
            while (i<row) {
                int userID2 = rs.getInt(1);
                String name = rs.getString(2);
                String sex = rs.getString(3);
                String type = rs.getString(4);
//                int maxBorrowDays = rs.getInt("B_MaxDays");
//                int typeNumber = rs.getInt("B_BookTypeNumber");
//                String type = operateTest.getBookTypeName(typeNumber);
                String bornDate = rs.getString(5);
                String credentials = rs.getString(6);
                String credentialsID = rs.getString(7);
                String phoneNumber = rs.getString(8);
                String email= rs.getString(9);
                String password= rs.getString(10);
                int max = rs.getInt(11);
                int borrowed = rs.getInt(12);
                System.out.println(name);
                Object[] information = {userID2,name,sex,type,bornDate,credentials,credentialsID,phoneNumber,email,
                        password,max,borrowed};
                this.userInformation[i] = information;
//                System.out.println(bookID);
                i++;
                rs.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return row;
    }

    public int setUserInformationName(String name){
        ResultSet rsA = operateTest.getUserInformationNameA(name);
        ResultSet rsR = operateTest.getUserInformationNameR(name);
        int row = 0;
        int rowA = 0;
        int rowR = 0;
        try{
            rsA.last();
            rowA = rsA.getRow();
            rsA.first();
            rsR.last();
            rowR = rsR.getRow();
            rsR.first();
            row = rowA + rowR;
        }catch (Exception e){
            e.printStackTrace();
        }
        if (row == 0){
            JOptionPane.showMessageDialog(null,"没有该用户！","提示",JOptionPane.ERROR_MESSAGE);
            return row;
        }
        this.userInformation = new Object[row][12];
        int i = 0;
        String[] columnNames = {"用户编号","名称","性别","类别","出生日期","证件类别","证件号码","电话号码","EMail","密码",
                "最大借书数量","已借书数量"};
        try {
            while (i<rowA) {
                int userID = rsA.getInt(1);
                String name2 = rsA.getString(2);
                String sex = rsA.getString(3);
                String type = rsA.getString(4);
                String bornDate = rsA.getString(5);
                String credentials = rsA.getString(6);
                String credentialsID = rsA.getString(7);
                String phoneNumber = rsA.getString(8);
                String email= rsA.getString(9);
                String password= rsA.getString(10);
                int max = rsA.getInt(11);
                int borrowed = rsA.getInt(12);
//                System.out.println(name);
                Object[] information = {userID,name2,sex,type,bornDate,credentials,credentialsID,phoneNumber,email,
                        password,max,borrowed};
                this.userInformation[i] = information;
                i++;
                rsA.next();
            }
            while(i<row){
                int userID = rsR.getInt(1);
                String name2 = rsR.getString(2);
                String sex = rsR.getString(3);
                String type = rsR.getString(4);
                String bornDate = rsR.getString(5);
                String credentials = rsR.getString(6);
                String credentialsID = rsR.getString(7);
                String phoneNumber = rsR.getString(8);
                String email= rsR.getString(9);
                String password= rsR.getString(10);
                int max = rsR.getInt(11);
                int borrowed = rsR.getInt(12);
//                System.out.println(name);
                Object[] information = {userID,name2,sex,type,bornDate,credentials,credentialsID,phoneNumber,email,
                        password,max,borrowed};
                this.userInformation[i] = information;
                i++;
                rsR.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return row;
    }

    public int setUserInformationCre(String id){
        ResultSet rsA = operateTest.getUserInformationIDA(id);
        ResultSet rsR = operateTest.getUserInformationIDR(id);
        int row = 0;
        int rowA = 0;
        int rowR = 0;
        try{
            rsA.last();
            rowA = rsA.getRow();
            rsA.first();
            rsR.last();
            rowR = rsR.getRow();
            rsR.first();
            row = rowA + rowR;
        }catch (Exception e){
            e.printStackTrace();
        }
        if (row == 0){
            JOptionPane.showMessageDialog(null,"没有该用户！","提示",JOptionPane.ERROR_MESSAGE);
            return row;
        }
        this.userInformation = new Object[row][12];
        int i = 0;
        String[] columnNames = {"用户编号","名称","性别","类别","出生日期","证件类别","证件号码","电话号码","EMail","密码",
                "最大借书数量","已借书数量"};
        try {
            while (i<rowA) {
                int userID = rsA.getInt(1);
                String name2 = rsA.getString(2);
                String sex = rsA.getString(3);
                String type = rsA.getString(4);
                String bornDate = rsA.getString(5);
                String credentials = rsA.getString(6);
                String credentialsID = rsA.getString(7);
                String phoneNumber = rsA.getString(8);
                String email= rsA.getString(9);
                String password= rsA.getString(10);
                int max = rsA.getInt(11);
                int borrowed = rsA.getInt(12);
//                System.out.println(name);
                Object[] information = {userID,name2,sex,type,bornDate,credentials,credentialsID,phoneNumber,email,
                        password,max,borrowed};
                this.userInformation[i] = information;
                i++;
                rsA.next();
            }
            while(i<row){
                int userID = rsR.getInt(1);
                String name2 = rsR.getString(2);
                String sex = rsR.getString(3);
                String type = rsR.getString(4);
                String bornDate = rsR.getString(5);
                String credentials = rsR.getString(6);
                String credentialsID = rsR.getString(7);
                String phoneNumber = rsR.getString(8);
                String email= rsR.getString(9);
                String password= rsR.getString(10);
                int max = rsR.getInt(11);
                int borrowed = rsR.getInt(12);
//                System.out.println(name);
                Object[] information = {userID,name2,sex,type,bornDate,credentials,credentialsID,phoneNumber,email,
                        password,max,borrowed};
                this.userInformation[i] = information;
                i++;
                rsR.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return row;
    }
    public Object[][] getUserInformation(){
        return this.userInformation;
    }
    public int setUserBorrow(int userID){
        ResultSet rs = operateTest.getBorrowInformation(userID);
        int row = -1;
        try{
            rs.last();
            row = rs.getRow();
            rs.first();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (row == 0||row == -1){
            JOptionPane.showMessageDialog(null,"没有该用户！","提示",JOptionPane.ERROR_MESSAGE);
            return row;
        }
        this.userBorrow = new Object[row][7];
//        System.out.println(row);
        int i = 0;
        String[] columnNames = {"借阅编号","用户编号","图书编号","借阅时间","截止日期","是否归还","归还日期"};
        try {
            while (i<row) {
                int borrowID = rs.getInt(1);
                int bookID = rs.getInt(3);
                String date = rs.getString(4);
                String deadLine = rs.getString(5);
                Boolean isReturn = rs.getBoolean(6);
                String returnDate = rs.getString(7);
                Object[] information = {borrowID,userID,bookID,date,deadLine,isReturn,returnDate};
                this.userBorrow[i] = information;
                System.out.println(bookID);
                i++;
                rs.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return row;
    }
    public Object[][] getUserBorrow(){
        return this.userBorrow;
    }
    public int setBookInformationName(String name){
        ResultSet rs = operateTest.getBookInformationName(name);
        int row = -1;
        try{
            rs.last();
            row = rs.getRow();
            rs.first();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (row == 0||row == -1){
            JOptionPane.showMessageDialog(null,"没有图书！","提示",JOptionPane.ERROR_MESSAGE);
            return row;
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
                i++;
                rs.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return row;
    }
    public int setBookInformationAuthor(String author){
        ResultSet rs = operateTest.getBookInformationAuthor(author);
        int row = -1;
        try{
            rs.last();
            row = rs.getRow();
            rs.first();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (row == 0||row == -1){
            JOptionPane.showMessageDialog(null,"没有图书！","提示",JOptionPane.ERROR_MESSAGE);
            return row;
        }
        this.bookInformation = new Object[row][9];
//        System.out.println(row);
        int i = 0;
        String[] columnNames = {"图书编号","书名","作者","译者","最大借阅时间","图书类别","出版时间","出版社","页数"};
        try {
            while (i<row) {
                int bookID = rs.getInt("B_ID");
                String bookName = rs.getString("B_Name");
//                String author = rs.getString("B_Author");
                String translator = rs.getString("B_Translator");
                int maxBorrowDays = rs.getInt("B_MaxDays");
                int typeNumber = rs.getInt("B_BookTypeNumber");
                String type = operateTest.getBookTypeName(typeNumber);
                String date = rs.getString("B_PublicationDate");
                String press = rs.getString("B_Press");
                String pages = rs.getString("B_Pages");
                Object[] information = {bookID,bookName,author,translator,maxBorrowDays,type,date,press,pages};
                this.bookInformation[i] = information;
                i++;
                rs.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return row;
    }
    public int setBookInformationID(int id){
        ResultSet rs = operateTest.getBookInformation(id);
        int row = -1;
        try{
            rs.last();
            row = rs.getRow();
            rs.first();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (row == 0||row == -1){
            JOptionPane.showMessageDialog(null,"没有图书！","提示",JOptionPane.ERROR_MESSAGE);
            return row;
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
                i++;
                rs.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return row;
    }
    public Object[][] getBookInformation(){
        return bookInformation;
    }
    public Object[][] getBookBorrow(){
        return bookBorrow;
    }
    public int setBookBorrow(int bookNumber){
        ResultSet rs = operateTest.getBorrowInformationBN(bookNumber);
        int row = -1;
        try{
            rs.last();
            row = rs.getRow();
            rs.first();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (row == 0||row == -1){
            JOptionPane.showMessageDialog(null,"没有该图书！","提示",JOptionPane.ERROR_MESSAGE);
            return row;
        }
        this.bookBorrow = new Object[row][7];
//        System.out.println(row);
        int i = 0;
        String[] columnNames = {"借阅编号","用户编号","图书编号","借阅时间","截止日期","是否归还","归还日期"};
        try {
            while (i<row) {
                int borrowID = rs.getInt(1);
                int userID= rs.getInt(2);
                int bookID = rs.getInt(3);
                String date = rs.getString(4);
                String deadLine = rs.getString(5);
                Boolean isReturn = rs.getBoolean(6);
                String returnDate = rs.getString(7);
                Object[] information = {borrowID,userID,bookID,date,deadLine,isReturn,returnDate};
                this.bookBorrow[i] = information;
                System.out.println(bookID);
                i++;
                rs.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return row;
    }
    public Object[][] getCompetence(){
        return competence;
    }
    public int setCompetence(int userID){
        ResultSet rs = operateTest.getCharacter(userID);
        int row = -1;
        try{
            rs.last();
            row = rs.getRow();
            rs.first();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (row == 0||row == -1){
            JOptionPane.showMessageDialog(null,"没有该用户！","提示",JOptionPane.ERROR_MESSAGE);
            return row;
        }
        this.competence = new Object[row][2];
//        System.out.println(row);
        int i = 0;
        try {
            while (i<row) {
                int character = rs.getInt(2);
                String type = "";
                switch (character){
                    case 1:
                        type = "管理员";
                        break;
                    case 2:
                        type = "读者";
                        break;
                }
                Object[] information = {userID,type};
                this.competence[i] = information;
//                System.out.println(bookID);
                i++;
                rs.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return row;
    }
}
