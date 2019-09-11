
package planetfood.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class DBConnection {
    private static Connection conn;
    static
            {
               try
               {
                   Class.forName("oracle.jdbc.OracleDriver");
                   conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-7QODKKDN:1521/XE","planetfood","student");
                   System.out.println("Connection opened");
               }
               catch(Exception ex)
               {
                   JOptionPane.showMessageDialog(null,"DB error in opning","Error!",JOptionPane.ERROR_MESSAGE);
                   ex.printStackTrace();
               }
                
            }
    public static Connection getConnection()
    {
        return conn;
    }
    public static void closeConnection()
    {
        try
        {
            conn.close();
            System.out.println("Connection closed");
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null,"connection Not Done","Error!",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
