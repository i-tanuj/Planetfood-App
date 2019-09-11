
package planetfood.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.Emp;
import planetfood.pojo.Product;


public class EmpDao {
    public static boolean addEmployees(Emp e)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into employees values(?,?,?,?)");
        ps.setString(1,e.getEmpid());
        ps.setString(2,e.getEname());
        ps.setString(3,e.getJob());
        ps.setDouble(4,e.getSal());
        int result=ps.executeUpdate();
        return (result==1);
    }
    public static boolean addUser(Emp e)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into employees values(?,?,?)");
        ps.setString(1,e.getEmpid());
        ps.setString(2,e.getEname());
        ps.setString(3,e.getJob());
        int result=ps.executeUpdate();
        return (result==1);
    }
    public static HashMap<String,String> getAllCategoryId()throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st= conn.createStatement();
        ResultSet rs=st.executeQuery("Select empid,ename,job from employees");
        HashMap<String,String> employees=new HashMap<>();
        while(rs.next())
        {
            String empid=rs.getString(1);
            String empname=rs.getString(2);
            String Job=rs.getString(3);
            employees.put(empid,empname);
        }
        return employees;
    }
    public static HashMap<String,Emp>getEmployeesById(String ename)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry="select * from employees where ename=?";
        PreparedStatement ps=conn.prepareStatement(qry);
        HashMap<String,Emp> empList=new HashMap<String,Emp>();
        ps.setString(1, ename);
        ResultSet rs=ps.executeQuery();
        
        while(rs.next())
        {
            Emp e=new Emp();
            e.setEname(ename);
            e.setJob(rs.getString("job"));
            empList.put(e.getEname(),e);
        }
        return empList;
    }
    public static String getNewiD()throws SQLException {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select count(*) from employees");
        int id=101;
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            id=id+rs.getInt(1);
        }
         System.out.println(id);
        return "E"+id;
       
}
    public static HashMap<String,Emp>getProductByCategory(String empId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry="select * from employees where empid=?";
        PreparedStatement ps=conn.prepareStatement(qry);
        HashMap<String,Emp> emplist=new HashMap<String,Emp>();
        ps.setString(1, empId);
        ResultSet rs=ps.executeQuery();
        
        while(rs.next())
        {
            Emp e=new Emp();
            e.setEmpid(empId);
            e.setEmpid(rs.getString("empId"));
            e.setEname(rs.getString("ename"));
            e.setJob(rs.getString("job"));
            e.setSal(rs.getDouble("sal"));
            emplist.put(e.getEmpid(),e);
        }
        return emplist;
    }
    public static boolean updateproduct(Emp e)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry="update employees set empid=?,ename=?,job=?,sal=? where empid=?";
        PreparedStatement ps=conn.prepareStatement(qry);
        ps.setString(1,e.getEmpid());
        ps.setString(2,e.getEname());
        ps.setString(3,e.getJob());
        ps.setDouble(4,e.getSal());
        ps.setString(5,e.getEmpid());
        int x=ps.executeUpdate();
        return(x>0);
    }
    public static boolean RemoveEmp(String empId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Delete from employees where empid=?");
        ps.setString(1, empId);
        int x=ps.executeUpdate();
        return x>0;
    }
    public static ArrayList<Emp> getEmp(String empId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from employees where empid=?");
        ps.setString(1, empId);
        ResultSet rs=ps.executeQuery();
        ArrayList<Emp> empList=new ArrayList<Emp>();
        while(rs.next())
        {
            Emp e=new Emp();
            e.setEmpid(rs.getString("empid"));
            e.setEname(rs.getString("ename"));
            e.setJob(rs.getString("job"));
            e.setSal(rs.getDouble("sal"));
            empList.add(e);
        }
        return empList;
    }
    public static ArrayList<Emp> getAllData()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry ="select * from employees";
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(qry);
        ArrayList<Emp> productList=new ArrayList<Emp>();
        while(rs.next())
        {
            Emp e=new Emp();
            e.setEmpid(rs.getString("empid"));
            e.setEname(rs.getString("ename"));
            e.setJob(rs.getString("job"));
            e.setSal(rs.getDouble("sal"));
            productList.add(e);
        }
        return productList;
    }
}