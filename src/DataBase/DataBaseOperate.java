package DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class DataBaseOperate {
    public DataBaseLink dataBaseLink = new DataBaseLink();

    //查询登录信息，并且返回账户状态：-1表示没有此用户，0代表密码错误，1代表账号密码正确且其为管理员，2代表账号密码正确且其为读者
    public  int getLoginInformation(int userID,String password){
        int loginInformationFlag = -1;
        try{
            String sql = "SELECT User_ID,U_Character FROM UserCharacter WHERE  User_ID = "+userID;
            ResultSet rs = this.dataBaseLink.executeQuery(sql);
            if(rs.next()) {
                if (userID == rs.getInt("User_ID")) {
                    int character = rs.getInt("U_Character");
                    switch (character) {
                        case 1:
//                            System.out.println("该账户是管理员");
                            String sqlAdmin = "SELECT User_ID,A_PassWord FROM AdminInformation WHERE User_ID=" + userID;
                            ResultSet rsAdmin = this.dataBaseLink.executeQuery(sqlAdmin);
                            rsAdmin.next();
                            if (password.equals(rsAdmin.getString("A_PassWord"))) {
                                loginInformationFlag = 1;
//                                System.out.println("管理员登录成功");
                            } else {
                                loginInformationFlag = 0;
//                                System.out.println("管理员登录失败");
                            }
                            break;
                        case 2:
//                            System.out.println("该账户是读者");
                            String sqlReader = "SELECT User_ID,R_PassWord FROM ReaderInformation WHERE User_ID=" + userID;
                            ResultSet rsReader = this.dataBaseLink.executeQuery(sqlReader);
                            rsReader.next();
                            if (password.equals(rsReader.getString("R_PassWord"))) {
                                loginInformationFlag = 2;
//                                System.out.println("读者登录成功");
                            } else {
                                loginInformationFlag = 0;
//                                System.out.println("读者登录失败");
                            }
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        if(loginInformationFlag == -1) System.out.println("无该账户");
        return loginInformationFlag;
    }
    //填写个人信息，注册读者账号,并且返回User_ID
    public int signUp(String name,String sex,String type,String bornDate, String credentials,String credentialsID,
                      String phoneNumber, String email,String password){
        int userID = -1;
        try{
            ResultSet userIDList;
            String getUserIDSql = "SELECT User_ID FROM UserCharacter ORDER BY User_ID DESC";
            userIDList = this.dataBaseLink.executeQuery(getUserIDSql);
            userIDList.next();
            userID = userIDList.getInt("User_ID")+1;
            String insertUserCharacterSql = "INSERT INTO UserCharacter VALUES ("+userID+",2)";
            this.dataBaseLink.executeUpdate(insertUserCharacterSql);
            int maxBookNumber;
            if(type.equals("本科生")){
                maxBookNumber = 5;
            }
            else if(type.equals("研究生")){
                maxBookNumber = 10;
            }
            else maxBookNumber = 20;
            String insertReaderInformationSql = "INSERT INTO ReaderInformation(User_ID,R_Name,R_Sex,R_Type,R_BornDate," +
                    "R_Credentials,R_CredentialsID,R_PhoneNumber,R_Email,R_PassWord,R_MaxBookNumber,R_BorrowedNumber," +
                    "R_Barcode) VALUES ("+userID+",'"+name+"','"+sex+"','"+type+"','"+bornDate+"','"+credentials+"','"+credentialsID
                    +"','"+phoneNumber+"','"+email+"','"+password+"','"+maxBookNumber+"',0,'"+userID+"')";
            this.dataBaseLink.executeUpdate(insertReaderInformationSql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return userID;
    }
    //添加图书种类，返回图书种类编号
    public int addBookType(String bookTypeName){
        int bookTypeNumber = -1;
        try {
            ResultSet typeNumberList;
            String getTypeNumberSql = "SELECT B_BookTypeNumber FROM BookTypeInformation ORDER BY B_BookTypeNumber DESC";
            typeNumberList = this.dataBaseLink.executeQuery(getTypeNumberSql);
            typeNumberList.next();
            bookTypeNumber = typeNumberList.getInt("B_BookTypeNumber") + 1;
            String insertUserCharacterSql = "INSERT INTO BookTypeInformation VALUES (" + bookTypeNumber+ ","+bookTypeName+")";
            this.dataBaseLink.executeUpdate(insertUserCharacterSql);
        }catch (Exception e){
            e.printStackTrace();
    }
        return bookTypeNumber;
    }

    //添加图书信息，返回书籍编号
    public int addBookInformation(String name,String cover,String author,String translator,int maxDays,int bookTypeNumber,
                                  String publicationData,String press,String pages,String authorIntroduction,String briefIntroduction,
                                  String ISBN,String price){
        int bookID = -1;
        try {
            ResultSet bookIDList;
            String getBookIDSql = "SELECT B_ID FROM BookInformation ORDER BY B_ID DESC";
            bookIDList = this.dataBaseLink.executeQuery(getBookIDSql);
            bookIDList.next();
            bookID = bookIDList.getInt("B_ID") + 1;
            String insertBookInformationSql = "INSERT INTO BookInformation(B_ID,B_Name,B_Cover,B_Author,B_Translator," +
                    "B_MaxDays,B_BookTypeNumber,B_PublicationDate,B_Press,B_Pages,B_AuthorIntroduction," +
                    "B_BriefIntroduction,B_ISBN,B_Price) VALUES (" + bookID+",'"+name+"','"+cover+"','"+author+"','"+translator
                    +"',"+maxDays+","+bookTypeNumber+",'"+publicationData+"','"+press+"','"+pages+"','"+authorIntroduction
                    +"','"+briefIntroduction+"','"+ISBN+"','"+price+"')";
//            System.out.println(insertBookInformationSql);
            this.dataBaseLink.executeUpdate(insertBookInformationSql);
        }catch (Exception e){
            e.printStackTrace();
        }
        addBookReserveInformation(bookID,5);
        return bookID;
    }

    //更新图书信息
    public void updateBookInformation(int bookID,String name,String cover,String author,String translator,int maxDays,int bookTypeNumber,
                                      String publicationData,String press,String pages,String authorIntroduction,String briefIntroduction,
                                      String ISBN,String price){
        String updateBookInformationSql = "UPDATE BookInformation SET B_Name = '"+name+"',B_Cover = '"+cover+"',B_Author = '"+
                author+"',B_Translator = '"+translator+"',B_MaxDays = "+maxDays+",B_BookTypeNumber = "+bookTypeNumber+"," +
                "B_PublicationDate = '"+publicationData+"',B_Press = '"+press+"',B_Pages = '"+pages+"',B_BriefIntroduction = '"+
                briefIntroduction+"',B_AuthorIntroduction='"+authorIntroduction+"',B_ISBN='"+ISBN+"',B_Price = '"+price+"'" +
                "WHERE B_ID = "+bookID;
//        System.out.println(updateBookInformationSql);
        dataBaseLink.executeUpdate(updateBookInformationSql);
    }
    //添加图书库存信息,返回图书总本书
    public int addBookReserveInformation(int bookID,int bookSum){
        int sum = -1;
        try{
            String sql1 = "SELECT B_ID,B_Sum FROM BookReserveInformation WHERE B_ID = "+bookID;
            ResultSet rs = this.dataBaseLink.executeQuery(sql1);
            rs.next();
            if (rs.next()){
                String sql2 = "UPDATE BookReserveInformation SET B_Sum = B_Sum+" + bookSum+"WHERE B_ID = bookID";
                this.dataBaseLink.executeUpdate(sql2);
                sum = rs.getInt("B_Sum") + bookSum;
            }
            else {
                String sql3 = "INSERT INTO BookReserveInformation(B_ID,B_Sum,B_BorrowedNumber) VALUES ("+bookID+","+bookSum+","+0+")";
                this.dataBaseLink.executeUpdate(sql3);
                sum = bookSum;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return sum;
    }

    //添加借阅信息，返回借阅编号
    public int addBorrowInformation(int userID,int bookID){
        int B_BorrowNumber = -1;
        try {
            ResultSet borrowNumberList;
            String getBorrowNumberSql = "SELECT B_BorrowNumber FROM BorrowInformation ORDER BY B_BorrowNumber DESC";
            borrowNumberList = this.dataBaseLink.executeQuery(getBorrowNumberSql);
            borrowNumberList.next();
            B_BorrowNumber = borrowNumberList.getInt("B_BorrowNumber") + 1;
            String borrowedDate = getNowDate();
            int maxDays;
            ResultSet maxDaysList;
            String getMaxDaysSql = "SELECT B_MaxDays FROM BookInformation WHERE B_ID = "+bookID;
            maxDaysList = dataBaseLink.executeQuery(getMaxDaysSql);
            maxDaysList.next();
            maxDays = maxDaysList.getInt("B_MaxDays");
            String insertBorrowInformationSql = "INSERT BorrowInformation(B_BorrowNumber,User_ID,B_ID,B_BorrowedDate" +
                    ",B_DeadLine,B_IsReturn) VALUES("+B_BorrowNumber+","+userID+","+bookID+
                    ",'"+borrowedDate+"','"+getDeadLine(maxDays)+"',"+0+")";
//            System.out.println(insertBorrowInformationSql);
            dataBaseLink.executeUpdate(insertBorrowInformationSql);
        }catch(Exception e){
            e.printStackTrace();
        }
        return B_BorrowNumber;
    }
    //添加借阅信息，返回借阅编号
    public int addBorrowInformation(int userID,int bookID,String borrowDate,String deadLine,boolean isReturn,String returnDate){
        int B_BorrowNumber = -1;
        try {
            ResultSet borrowNumberList;
            String getBorrowNumberSql = "SELECT B_BorrowNumber FROM BorrowInformation ORDER BY B_BorrowNumber DESC";
            borrowNumberList = this.dataBaseLink.executeQuery(getBorrowNumberSql);
            borrowNumberList.next();
            B_BorrowNumber = borrowNumberList.getInt("B_BorrowNumber") + 1;
            String insertBorrowInformationSql = "INSERT BorrowInformation(B_BorrowNumber,User_ID,B_ID,B_BorrowedDate,B_DeadLine,B_IsReturn,B_ReturnDate" +
                    ") VALUES("+B_BorrowNumber+","+userID+","+bookID+ ",'"+borrowDate+"','"+deadLine+"','"+isReturn+"','"+returnDate+"')";
//            System.out.println(insertBorrowInformationSql);
            dataBaseLink.executeUpdate(insertBorrowInformationSql);
        }catch(Exception e){
            e.printStackTrace();
        }
        return B_BorrowNumber;
    }

    //修改借阅信息
    public void changeBorrowInformation(int borrowID,int userID,int bookID,String borrowDate,String deadLine,boolean isReturn,String returnDate){
//        int B_BorrowNumber = -1;
        try {
            String sql = "UPDATE BorrowInformation SET User_ID = "+userID+",B_ID = "+bookID+",B_BorrowedDate = '"+borrowDate+"'," +
                    "B_DeadLine = '"+deadLine+"',B_IsReturn = '"+isReturn+"',B_ReturnDate = '"+returnDate+"'WHERE B_BorrowNumber = "+borrowID;
//            String insertBorrowInformationSql = "INSERT BorrowInformation(B_BorrowNumber,User_ID,B_ID,B_BorrowedDate,B_DeadLine,B_IsReturn,B_ReturnDate" +
//                    ") VALUES("+B_BorrowNumber+","+userID+","+bookID+ ",'"+borrowDate+"','"+deadLine+"','"+isReturn+"','"+returnDate+"')";
//            System.out.println(sql);
            dataBaseLink.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
//        return B_BorrowNumber;
    }

    //获取当前系统的年月日
    public String getNowDate(){
        Calendar now = Calendar.getInstance();
        String date;
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH)+1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        date = year+"-"+month+"-"+day;
        return date;
    }

    //获取还书最晚时间
    public String getDeadLine(int days){
        Calendar now = Calendar.getInstance();
        now.set(Calendar.DATE,now.get(Calendar.DATE)+days);
        String dateLine;
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH)+1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        dateLine = year+"-"+month+"-"+day;
        return dateLine;
    }

//    public String get

    //获取借阅信息
    public ResultSet getBorrowInformation(int userID){
        String getInformationSql = "SELECT B_BorrowNumber,User_ID,B_ID,B_BorrowedDate,B_DeadLine,B_IsReturn," +
                "B_ReturnDate FROM BorrowInformation WHERE User_ID = "+userID;
        return dataBaseLink.executeQuery(getInformationSql);
    }

    //获取借阅信息
    public ResultSet getBorrowInformationBN(int bookID){
        String getInformationSql = "SELECT B_BorrowNumber,User_ID,B_ID,B_BorrowedDate,B_DeadLine,B_IsReturn," +
                "B_ReturnDate FROM BorrowInformation WHERE B_ID = "+bookID;
        return dataBaseLink.executeQuery(getInformationSql);
    }

    //获取读者信息
    public ResultSet getReaderInformation(int userID){
        String getReaderInformationSql = "SELECT R_Name,R_Sex,R_Type,R_BornDate,R_Credentials,R_CredentialsID," +
                "R_PhoneNumber,R_Email,R_PassWord,R_MaxBookNumber,R_BorrowedNumber,R_Barcode,R_Remark FROM " +
                "ReaderInformation WHERE User_ID = "+userID;
        return dataBaseLink.executeQuery(getReaderInformationSql);
    }
    //获取管理员信息
    public ResultSet getAdminInformation(int userID){
        String getAdminInformationSql = "SELECT A_Name,A_Sex,A_Type,A_BornDate,A_Credentials,A_CredentialsID," +
                "A_PhoneNumber,A_Email,A_PassWord,A_MaxBookNumber,A_BorrowedNumber,A_Barcode,A_Remark FROM " +
                "AdminInformation WHERE User_ID = "+userID;
        return dataBaseLink.executeQuery(getAdminInformationSql);
    }

    //修改读者信息
    public boolean changeReaderInformationUp(int userID,String name,String sex,String type,String bornDate,
                                         String credentials,String credentialsID, String phoneNumber, String email){
        String updateSql = "UPDATE ReaderInformation SET R_Name = '"+name+"',R_Sex = '"+sex+"',R_Type = '"+type+"',R_BornDate " +
                "= '"+bornDate+"',R_Credentials = '"+credentials+"',R_CredentialsID = '"+credentialsID+"',R_PhoneNumber = '"+
                phoneNumber+"',R_Email = '"+email+"' WHERE User_ID = "+userID;
//        System.out.println(updateSql);
        this.dataBaseLink.executeUpdate(updateSql);
        return true;
    }
    //修改管理员信息
    public boolean changeAdminInformationUp(int userID,String name,String sex,String type,String bornDate,
                                             String credentials,String credentialsID, String phoneNumber, String email){
        String updateSql = "UPDATE AdminInformation SET A_Name = '"+name+"',A_Sex = '"+sex+"',A_Type = '"+type+"',A_BornDate " +
                "= '"+bornDate+"',A_Credentials = '"+credentials+"',A_CredentialsID = '"+credentialsID+"',A_PhoneNumber = '"+
                phoneNumber+"',A_Email = '"+email+"' WHERE User_ID = "+userID;
//        System.out.println(updateSql);
        this.dataBaseLink.executeUpdate(updateSql);
        return true;
    }
    //修改用户密码
    public boolean changeReaderPassword(int userID,String password){
        String changePasswordSql = "UPDATE ReaderInformation SET R_PassWord = "+password+"WHERE User_ID = "+userID;
        this.dataBaseLink.executeUpdate(changePasswordSql);
        return true;
    }
    //修改管理员密码
    public boolean changeAdminPassword(int userID,String password){
        String changePasswordSql = "UPDATE AdminInformation SET A_PassWord = "+password+"WHERE User_ID = "+userID;
        this.dataBaseLink.executeUpdate(changePasswordSql);
        return true;
    }

    //获取某一条借阅信息
    public ResultSet getBorrowNumberInformation(int borrowNumber){
        String sql = "SELECT * FROM BorrowInformation WHERE B_BorrowNumber = "+borrowNumber;
        return dataBaseLink.executeQuery(sql);
    }

    //获取图书信息
    public ResultSet getBookInformation(int bookID){
        String sql = "SELECT * FROM BookInformation WHERE B_ID = "+bookID;
        return dataBaseLink.executeQuery(sql);
    }

    //获取图书信息
    public ResultSet getBookInformationName(String name){
        String sql = "SELECT * FROM BookInformation WHERE B_Name LIKE '%"+name+"%'";
        return dataBaseLink.executeQuery(sql);
    }
    //获取图书信息
    public ResultSet getBookInformationAuthor(String author){
        String sql = "SELECT * FROM BookInformation WHERE B_Author LIKE '%"+author+"%'";
//        System.out.println(sql);
        return dataBaseLink.executeQuery(sql);
    }
    //获取图书类别名称
    public String getBookTypeName(int typeNumber){
        String sql = "SELECT B_BookTypeName FROM BookTypeInformation WHERE B_BookTypeNumber = "+typeNumber;
        ResultSet rs = dataBaseLink.executeQuery(sql);
        String name = "";
        try {
            rs.next();
            name = rs.getString("B_BookTypeName");
        }catch (Exception e){
            e.printStackTrace();
        }
        return name;
    }

    //还书
    public boolean returnBook(int borrowNumber,int userID,int bookID) {
        String date = getNowDate();
        String sql = "UPDATE BorrowInformation SET B_IsReturn =" + 1 + ",B_ReturnDate = '" + date + "' WHERE B_BorrowNumber = " + borrowNumber;
//        System.out.println(sql);
        String sql2 = "UPDATE ReaderInformation SET R_BorrowedNumber = R_BorrowedNumber -"+1+"WHERE User_ID = "+userID;
        String sql3 = "UPDATE BookReserveInformation SET B_BorrowedNumber = B_BorrowedNumber-"+1+"WHERE B_ID = " +bookID;
        dataBaseLink.executeUpdate(sql);
        dataBaseLink.executeUpdate(sql2);
        dataBaseLink.executeUpdate(sql3);
        return true;
    }

    //查找关键字的书籍
    public ResultSet getKeyWordsBook(String sql){
        return dataBaseLink.executeQuery(sql);
    }

    //查询图书剩余信息
    public int getBookSum(int bookID){
        String sql = "SELECT * FROM BookReserveInformation WHERE B_ID = "+bookID;
        ResultSet rs = dataBaseLink.executeQuery(sql);
        int num = -1;
        try{
            rs.next();
            num = rs.getInt("B_Sum")-rs.getInt("B_BorrowedNumber");
        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }

    //借书,返回借阅记录编号
    public int borrowBook(int userID,int bookID){
        String sql = "UPDATE ReaderInformation SET R_BorrowedNumber = R_BorrowedNumber +"+1+"WHERE User_ID = "+userID;
        String sql2 = "UPDATE BookReserveInformation SET B_BorrowedNumber = B_BorrowedNumber+"+1+"WHERE B_ID = " +bookID;
        dataBaseLink.executeUpdate(sql);
        dataBaseLink.executeUpdate(sql2);
        return addBorrowInformation(userID, bookID);
    }

    //通过userID返回个人信息
    public ResultSet getUserInformation(int userID){
        ResultSet rs = null;
        String getCharacterSql = "SELECT U_Character FROM UserCharacter WHERE User_ID = "+userID;
        ResultSet characterRs = dataBaseLink.executeQuery(getCharacterSql);
        int character = -1;
        try {
            characterRs.next();
            character = characterRs.getInt("U_Character");
        }catch (Exception e){
            e.printStackTrace();
        }
        switch (character){
            case 1:
                String sql = "SELECT * FROM AdminInformation WHERE User_ID = "+userID;
                rs = dataBaseLink.executeQuery(sql);
                break;
            case 2:
                String sql2 = "SELECT * FROM ReaderInformation WHERE User_ID = "+userID;
                rs = dataBaseLink.executeQuery(sql2);
                break;
        }
        return rs;
    }

    //通过用户名称返回个人信息
    public ResultSet getUserInformationNameA(String name){
        String sql1 = "SELECT * FROM AdminInformation WHERE A_Name LIKE '%"+name+"%'";
        String sql2 = "SELECT * FROM ReaderInformation WHERE A_Name LIKE '%"+name+"%'";
        return dataBaseLink.executeQuery(sql1);
    }
    //通过用户名称返回个人信息
    public ResultSet getUserInformationNameR(String name){
//        String sql1 = "SELECT * FROM AdminInformation WHERE A_Name LIKE '%"+name+"%'";
        String sql2 = "SELECT * FROM ReaderInformation WHERE R_Name LIKE '%"+name+"%'";
        return dataBaseLink.executeQuery(sql2);
    }
    //通过证件号码返回个人信息
    public ResultSet getUserInformationIDA(String id){
        String sql1 = "SELECT * FROM AdminInformation WHERE A_CredentialsID ="+id;
        return dataBaseLink.executeQuery(sql1);
    }
    //通过证件号码返回个人信息
    public ResultSet getUserInformationIDR(String id){
        String sql2 = "SELECT * FROM ReaderInformation WHERE R_CredentialsID ="+id;
        return dataBaseLink.executeQuery(sql2);
    }
    //删除借阅记录
    public void deleteBorrowInformation(int borrowID){
        String sql = "DELETE BorrowInformation WHERE B_BorrowNumber = " +borrowID;
        dataBaseLink.executeUpdate(sql);
    }
    //获取角色信息
    public ResultSet getCharacter(int userID){
        String sql = "SELECT * FROM UserCharacter WHERE User_ID = "+userID;
        return dataBaseLink.executeQuery(sql);
    }
    //将用户设置为管理员
    public void setAdmin(int userID){
        String sql1 = "UPDATE UserCharacter SET U_Character = "+1+"WHERE User_ID = "+userID;
        String sql2 = "SELECT * FROM ReaderInformation WHERE User_ID = "+userID;
        dataBaseLink.executeUpdate(sql1);
        ResultSet rrs = dataBaseLink.executeQuery(sql2);
        try {
            rrs.next();
            String name = rrs.getString(2);
            String sex = rrs.getString(3);
            String type = rrs.getString(4);
            String bornDate = rrs.getString(5);
            String credentials = rrs.getString(6);
            String credentialsID = rrs.getString(7);
            String phoneNumber = rrs.getString(8);
            String email = rrs.getString(9);
            String password = rrs.getString(10);
            int maxBookNumber = rrs.getInt(11);
            int borrowedNumber = rrs.getInt(12);
            String barCode = rrs.getString(13);
            String remark = rrs.getString(14);
            String sql3 =  "INSERT INTO AdminInformation(User_ID,A_Name,A_Sex,A_Type,A_BornDate," +
                    "A_Credentials,A_CredentialsID,A_PhoneNumber,A_Email,A_PassWord,A_MaxBookNumber,A_BorrowedNumber," +
                    "A_Barcode,A_Remark) VALUES ("+userID+",'"+name+"','"+sex+"','"+type+"','"+bornDate+"','"+credentials+"','"+credentialsID
                    +"','"+phoneNumber+"','"+email+"','"+password+"','"+maxBookNumber+"',"+borrowedNumber+","+barCode+",'"+remark+"')";
            dataBaseLink.executeUpdate(sql3);
            String sql4 = "DELETE ReaderInformation WHERE User_ID = "+userID;
            dataBaseLink.executeUpdate(sql4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //将用户设置为读者
    public void setReader(int userID){
        String sql1 = "UPDATE UserCharacter SET U_Character = "+2+"WHERE User_ID = "+userID;
        String sql2 = "SELECT * FROM AdminInformation WHERE User_ID = "+userID;
        dataBaseLink.executeUpdate(sql1);
        ResultSet rrs = dataBaseLink.executeQuery(sql2);
        try {
            rrs.next();
            String name = rrs.getString(2);
            String sex = rrs.getString(3);
            String type = rrs.getString(4);
            String bornDate = rrs.getString(5);
            String credentials = rrs.getString(6);
            String credentialsID = rrs.getString(7);
            String phoneNumber = rrs.getString(8);
            String email = rrs.getString(9);
            String password = rrs.getString(10);
            int maxBookNumber = rrs.getInt(11);
            int borrowedNumber = rrs.getInt(12);
            String barCode = rrs.getString(13);
            String remark = rrs.getString(14);
            String sql3 =  "INSERT INTO ReaderInformation(User_ID,R_Name,R_Sex,R_Type,R_BornDate," +
                    "R_Credentials,R_CredentialsID,R_PhoneNumber,R_Email,R_PassWord,R_MaxBookNumber,R_BorrowedNumber," +
                    "R_Barcode,R_Remark) VALUES ("+userID+",'"+name+"','"+sex+"','"+type+"','"+bornDate+"','"+credentials+"','"+credentialsID
                    +"','"+phoneNumber+"','"+email+"','"+password+"','"+maxBookNumber+"',"+borrowedNumber+","+barCode+",'"+remark+"')";
            dataBaseLink.executeUpdate(sql3);
            String sql4 = "DELETE AdminInformation WHERE User_ID = "+userID;
            dataBaseLink.executeUpdate(sql4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
