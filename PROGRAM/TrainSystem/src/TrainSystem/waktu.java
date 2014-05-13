/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainSystem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import TrainSystem.connect.Database;

/**
 *
 * @author DIDIL
 */
public class waktu extends Thread implements  Runnable{
    
    private JLabel jlabel;
    public waktu (JLabel label){
        this.jlabel=label;
    }
     @Override
    public void run() {
        while (1==1){
        try {
            Database db= new Database();
            ResultSet rs = db.getData("select to_char(sysdate, 'Dy DD-Mon-YYYY HH24:MI:SS') as \"Current Time\"from dual");
            if (rs.next()) {
                String currentDate = rs.getString(1);
                jlabel.setText(""+currentDate);
            }
                try {
                    rs.close();
                    sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(waktu.class.getName()).log(Level.SEVERE, null, ex);
                }
        } catch (SQLException ex) {
            Logger.getLogger(waktu.class.getName()).log(Level.SEVERE, null, ex);
        }

        
     }
    
}
}