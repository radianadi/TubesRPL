/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainSystem.connect;

/**
 *
 * @author DIDIL
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Database {

    private Statement stmt = null;
    private Connection con = null;
    private ResultSet rs = null;
    public String DBURL = "jdbc:mysql://localhost/trainsystem";
    
    public String DBUSER = "root";
    public String DBPASS = "";

    public Database() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public ResultSet getData(String SQLString) {
        ResultSet rs1 = null;
        try {
            rs1 = stmt.executeQuery(SQLString);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error :"
                    + e.getMessage(), "Syntax Error", JOptionPane.WARNING_MESSAGE);
        }

        return rs1;
    }

    public void query(String SQLString) {
        try {
            stmt.executeUpdate(SQLString);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error:"
                    + e.getMessage(), "Syntax Error", JOptionPane.WARNING_MESSAGE);
        }
         finally {
        if (stmt != null) {try {
                     stmt.close();
                 } catch (SQLException ex) {
                     Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                 }
 }
        }
    }
}
