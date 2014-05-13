/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainSystem;

import java.sql.ResultSet;
import TrainSystem.connect.Database;

/**
 *
 * @author DIDIL
 */
public class JenisKereta {
    private double id_jenis;
    private double id_kereta;
    private String nama;

    /**
     * @return the id_jenis
     */
    public double getId_jenis() {
        return id_jenis;
    }

    /**
     * @param id_jenis the id_jenis to set
     */
    public void setId_jenis(double id_jenis) {
        this.id_jenis = id_jenis;
    }

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
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setJenisKereta(){
         Database db=new Database(); 
         String s = "insert into JenisKereta values ("+this.id_jenis+",'"+this.id_kereta+"'"+this.nama+"'";
         db.query(s);
    }
    public ResultSet getJadwal(){
         ResultSet result=null; 
          Database db=new Database(); 
          String s = "select * from JenisKereta";
          result = db.getData(s);
          return result;
    }
}
