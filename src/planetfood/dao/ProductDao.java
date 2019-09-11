package planetfood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.Product;


public class ProductDao {
    public static String getNewID()throws SQLException {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select count(*) from products");
        int id=101;
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            id=id+rs.getInt(1);
        }
        return "p"+id;   
    }
    public static boolean AddProduct(Product p)throws SQLException {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Insert into products values(?,?,?,?,?)");
        ps.setString(1,p.getProdid());
        ps.setString(2,p.getCatid());
        ps.setString(3,p.getProdName());
        ps.setDouble(4,p.getProdPrice());
        ps.setString(5,p.getIsActive());
        int x=ps.executeUpdate();
        return (x>0);
    }
    public static HashMap<String,Product>getProductByCategory(String catId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry="select * from products where CAT_ID=?";
        PreparedStatement ps=conn.prepareStatement(qry);
        HashMap<String,Product> productList=new HashMap<String,Product>();
        ps.setString(1, catId);
        ResultSet rs=ps.executeQuery();
        
        while(rs.next())
        {
            Product p=new Product();
            p.setCatid(catId);
            p.setProdid(rs.getString("product_id"));
            p.setProdName(rs.getString("product_name"));
            p.setProdPrice(rs.getDouble("prod_price"));
            p.setIsActive(rs.getString("active"));
            productList.put(p.getProdid(),p);
        }
        return productList;
    }
    public static ArrayList<Product> getAllData()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry ="select * from Products";
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(qry);
        ArrayList<Product> productList=new ArrayList<Product>();
        while(rs.next())
        {
            Product p=new Product();
            p.setCatid(rs.getString("cat_id"));
            p.setProdid(rs.getString("product_id"));
            p.setProdName(rs.getString("product_name"));
            p.setProdPrice(rs.getDouble("prod_price"));
            p.setIsActive(rs.getString("active"));
            productList.add(p);
        }
        return productList;
    }
    public static boolean updateproduct(Product p)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry="update Products set cat_id=?,product_name=?,prod_price=?,active=? where product_id=?";
        PreparedStatement ps=conn.prepareStatement(qry);
        ps.setString(1,p.getCatid());
        ps.setString(2,p.getProdName());
        ps.setDouble(3,p.getProdPrice());
        ps.setString(4,p.getIsActive());
        ps.setString(5,p.getProdid());
        int x=ps.executeUpdate();
        return(x>0);
    }
    public static boolean removeProduct(String prodId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update Products set active='N' where product_id=?");
        ps.setString(1, prodId);
        int x=ps.executeUpdate();
        return x>0;
    }
    public static HashMap<String,String> getActiveProductsByCategory(String catId)throws SQLException{
       Connection conn=DBConnection.getConnection();
       String qry="Select product_name,product_id from Products where cat_id=? and active='Y'";
       PreparedStatement ps=conn.prepareStatement(qry);
       ps.setString(1, catId);
       ResultSet rs=ps.executeQuery();
       HashMap<String,String> productList=new HashMap<>();
       while(rs.next()){
               String prodName=rs.getString("product_name");
               String prodId=rs.getString("product_id");
               productList.put(prodName,prodId);
          }
       return productList;
         
        }
    public static ArrayList<Product> getPrd(String cat_id)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from products where cat_id =?");
        ps.setString(1, cat_id);
        ResultSet rs=ps.executeQuery();
        ArrayList<Product> prdList=new ArrayList<Product>();
        while(rs.next()){
        Product obj=new Product();
        obj.setCatid(rs.getString("cat_id"));
        obj.setProdid(rs.getString("product_Id"));
        obj.setProdName(rs.getString("product_Name"));
        
        
        prdList.add(obj);
        
    }
return prdList;
    }
    
}