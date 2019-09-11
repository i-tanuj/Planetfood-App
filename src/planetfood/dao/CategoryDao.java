/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package planetfood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.Category;
import planetfood.pojo.Product;

/**
 *
 * @author hp
 */
public class CategoryDao {
    public static HashMap<String,String> getAllCategoryId()throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st= conn.createStatement();
        ResultSet rs=st.executeQuery("Select cat_name,cat_id from categories");
        HashMap<String,String> categories=new HashMap<>();
        while(rs.next())
        {
            String catName=rs.getString(1);
            String catId=rs.getString(2);
            categories.put(catName,catId);
        }
        return categories;
    }
    public static HashMap<String,String> getAllCategory()throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st= conn.createStatement();
        ResultSet rs=st.executeQuery("Select cat_name,cat_id from categories");
        HashMap<String,String> categories=new HashMap<>();
        while(rs.next())
        {
            String catName=rs.getString(1);
            String catId=rs.getString(2);
            categories.put(catName,catId);
        }
        return categories;
    }
    public static HashMap<String,Category>getProductByCategory(String catId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry="select * from Categories where CAT_ID=?";
        PreparedStatement ps=conn.prepareStatement(qry);
        HashMap<String,Category> productList=new HashMap<String,Category>();
        ps.setString(1, catId);
        ResultSet rs=ps.executeQuery();
        
        while(rs.next())
        {
            Category p=new Category();
            p.setCatid(catId);
            p.setCatname(rs.getString("cat_name"));
            productList.put(p.getCatid(),p);
        }
        return productList;
    }
    public static HashMap<String,String> getActiveProductsByCategory(String catId)throws SQLException{
       Connection conn=DBConnection.getConnection();
       String qry="Select cat_id,cat_name from categories where cat_id=?";
       PreparedStatement ps=conn.prepareStatement(qry);
       ps.setString(1, catId);
       ResultSet rs=ps.executeQuery();
       HashMap<String,String> productList=new HashMap<>();
       while(rs.next()){
               String Catid=rs.getString("cat_id");
               String Catname=rs.getString("cat_name");
               productList.put(Catid,Catname);
          }
       return productList;
         
        }
    public static HashMap<String,Category>getProductCategory(String catId)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry="select * from categories where CAT_ID=?";
        PreparedStatement ps=conn.prepareStatement(qry);
        HashMap<String,Category> categoryList=new HashMap<String,Category>();
        ps.setString(1, catId);
        ResultSet rs=ps.executeQuery();
        
        while(rs.next())
        {
            Category p=new Category();
            p.setCatid(catId);
            p.setCatid(rs.getString("cat_id"));
            p.setCatname(rs.getString("cat_name"));
            categoryList.put(p.getCatid(),p);
        }
        return categoryList;
    }
    public static boolean updateproduct(Category p)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry="update categories set cat_id=?,cat_name=?";
        PreparedStatement ps=conn.prepareStatement(qry);
        ps.setString(1,p.getCatid());
        ps.setString(2,p.getCatname());
        int x=ps.executeUpdate();
        return(x>0);
    }
    public static HashMap<String,String> getCategoryId()throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st= conn.createStatement();
        ResultSet rs=st.executeQuery("Select cat_name,cat_id from categories");
        HashMap<String,String> categories=new HashMap<>();
        while(rs.next())
        {
            String catName=rs.getString(1);
            String catId=rs.getString(2);
            categories.put(catName,catId);
        }
        return categories;
    }
    public static String getNewID()throws SQLException {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select count(*) from categories");
        int id=101;
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
            id=id+rs.getInt(1);
        }
        return "C"+id;   
    }
    public static boolean UpdateProduct(Category p)throws SQLException {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("update categories set cat_name=? where cat_id=?");
        ps.setString(1,p.getCatname());
        ps.setString(2,p.getCatid());
        int x=ps.executeUpdate();
        return (x>0);
    }
    public static boolean AddProduct(Category p)throws SQLException {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into categories values(?,?)");
        ps.setString(1,p.getCatid());
        ps.setString(2,p.getCatname());
        int x=ps.executeUpdate();
        return (x>0);
    }
    public static ArrayList<Category> getAllData()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        String qry ="select * from categories";
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(qry);
        ArrayList<Category> productList=new ArrayList<Category>();
        while(rs.next())
        {
            Category P=new Category();
            P.setCatid(rs.getString("cat_id"));
            P.setCatname(rs.getString("cat_name"));
            productList.add(P);
        }
        return productList;
    }
    

}
