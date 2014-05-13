/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainSystem;

import java.sql.ResultSet;
import java.util.Date;
import TrainSystem.connect.Database;

/**
 *
 * @author DIDIL
 */
public class Jadwal {
    private double  id_kereta;
    private double id_tiket;
    private Date JamDatang;
    private Date JamBerangkat;
    private String kotaTujuan;

    /**
     * @return the id_kereta
     */
    public double getId_kereta() {
        return id_kereta;
    }

    /**
     * @param id_kereta the id_kereta to set
     */
    public void setId_kereta(double id_kereta) {
        this.id_kereta = id_kereta;
    }

    /**
     * @return the id_tiket
     */
    public double getId_tiket() {
        return id_tiket;
    }

    /**
     * @param id_tiket the id_tiket to set
     */
    public void setId_tiket(double id_tiket) {
        this.id_tiket = id_tiket;
    }

    /**
     * @return the JamDatang
     */
    public Date getJamDatang() {
        return JamDatang;
    }

    /**
     * @param JamDatang the JamDatang to set
     */
    public void setJamDatang(Date JamDatang) {
        this.JamDatang = JamDatang;
    }

    /**
     * @return the JamBerangkat
     */
    public Date getJamBerangkat() {
        return JamBerangkat;
    }

    /**
     * @param JamBerangkat the JamBerangkat to set
     */
    public void setJamBerangkat(Date JamBerangkat) {
        this.JamBerangkat = JamBerangkat;
    }

    /**
     * @return the kotaTujuan
     */
    public String getKotaTujuan() {
        return kotaTujuan;
    }

    /**
     * @param kotaTujuan the kotaTujuan to set
     */
    public void setKotaTujuan(String kotaTujuan) {
        this.kotaTujuan = kotaTujuan;
    }
    public void setJadwal(){
         Database db=new Database(); 
         String s = "insert into Jadwal values ("+this.id_kereta+",'"+this.id_tiket+"','"+this.JamDatang+"','"+this.JamBerangkat+"','"+this.kotaTujuan+"'";
         db.query(s);
    }
    public ResultSet getJadwal(){
         ResultSet result=null; 
          Database db=new Database(); 
          String s = "select * from jadwal";
          result = db.getData(s);
          return result;
    }
}
