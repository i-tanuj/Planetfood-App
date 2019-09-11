
package planetfood.dao;

import planetfood.pojo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.Emp;

/**
 *
 * @author hp
 */
public class UserDao {
    public static String validateUser( User user)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry="select username from Users where userid=? and password=? and usertype=?";
        PreparedStatement ps=conn.prepareStatement(qry);
        ps.setString(1,user.getUserId());
        ps.setString(2,user.getPassword());
        ps.setString(3,user.getUserType());
        ResultSet rs=ps.executeQuery();
        String username=null;
        if(rs.next())
        {
            username=rs.getString(1);
        }
        return username;
    }
    public static boolean addUser(User e)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into users values(?,?,?,?,?)");
        ps.setString(1,e.getUserId());
        ps.setString(2,e.getEname());
        ps.setString(3,e.getEmpId());
        ps.setString(4,e.getPassword());
        ps.setString(5,e.getUserType());
        int result=ps.executeUpdate();
        return (result==1);
    }
    public static HashMap<String,String> getAllCategory()throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st= conn.createStatement();
        ResultSet rs=st.executeQuery("Select empid,ename from employees");
        HashMap<String,String> Emp=new HashMap<>();
        while(rs.next())
        {
            String empid=rs.getString(1);
            String ename=rs.getString(2);
            Emp.put(empid,ename);
        }
        return Emp;
    }
    public static ArrayList<User> getCashier(String userId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from users where userid=?");
        ps.setString(1, userId);
        ResultSet rs=ps.executeQuery();
        ArrayList<User> List=new ArrayList<User>();
        while(rs.next())
        {
            User u=new User();
            u.setUserId(rs.getString("userid"));
            u.setUsername(rs.getString("username"));
            u.setEmpId(rs.getString("empid"));
            List.add(u);
        }
        return List;
    }
    public static boolean RemoveCashier(String userId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Delete from users where userid=?");
        ps.setString(1, userId);
        int x=ps.executeUpdate();
        return x>0;
    }
    public static boolean Adduser(User user)throws SQLException
    {
        String qry = "Select * from users where userid = ?";
        boolean status = true;
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(qry);
        ps.setString(1,user.getUserId());
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            status = false;
        else
        {
            qry = "Insert into users values(?,?,?,?,?)";
            ps = conn.prepareStatement(qry);
            ps.setString(1, user.getUserId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getEmpId());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getUserType());
            ps.executeUpdate();
        }
        return status;
    
}
}
