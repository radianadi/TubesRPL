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
public class Petugas extends Orang{
    private double id_petugas;
    private double id_jenis;
    private double id_stasiun;

    /**
     * @return the id_petugas
     */
    public double getId_petugas() {
        return id_petugas;
    }

    /**
     * @param id_petugas the id_petugas to set
     */
    public void setId_petugas(double id_petugas) {
        this.id_petugas = id_petugas;
    }

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
     * @return the id_stasiun
     */
    public double getId_stasiun() {
        return id_stasiun;
    }

    /**
     * @param id_stasiun the id_stasiun to set
     */
    public void setId_stasiun(double id_stasiun) {
        this.id_stasiun = id_stasiun;
    }
    public void setPetugas(){
         Database db=new Database(); 
         String s = "insert into Jadwal values ("+this.id_petugas+",'"+this.id_jenis+"',"+this.id_stasiun+"');";
         db.query(s);
    }
    public ResultSet getPetugas(){
         ResultSet result=null; 
          Database db=new Database(); 
          String s = "select * from Kereta";
          result = db.getData(s);
          return result;
    }
}
