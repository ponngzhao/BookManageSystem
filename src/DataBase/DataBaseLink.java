package DataBase;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataBaseLink {
    public static Connection cn = null;
    public static Statement st = null;
    public static Statement st2 = null;
    public static ResultSet rs = null;
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection
                    ("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=BookManageSystem", "sa", "zp358713420");
            if(cn != null){
                System.out.println("连接成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //执行查询SQL命令，返回记录集对象函数
    public static ResultSet executeQuery(String sql){
        ResultSet rs = null;
        try {
            st2 = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = st2.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    //执行更新类SQL命令的函数
    public static int executeUpdate(String sql){
        int i = 0;
        try {
            st2 = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            i = st2.executeUpdate(sql);
            cn.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }

    //执行查询查询SQL命令，返回是否成功的函数
    public static boolean query(String sql){
        try{
            rs = null;
            rs = st.executeQuery(sql);
        }catch (Exception e){
            System.out.println("sql exception:"+e);
            return false;
        }
        return true;
    }

    //执行更新类SQL命令，返回是否成功的函数
    public static boolean executeSQL(String sql){
        boolean executeFlag;
        try{
            st.execute(sql);
            executeFlag = true;
        }catch (Exception e){
            executeFlag = false;
            System.out.println("SQL Exception:"+e.getMessage());
        }
        return executeFlag;
    }



}
