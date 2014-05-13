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
public class Kota {
    private String  id_kota;
    private double id_stasiun;
    private String nama;

    /**
     * @return the id_kota
     */
    public String getId_kota() {
        return id_kota;
    }

    /**
     * @param id_kota the id_kota to set
     */
    public void setId_kota(String id_kota) {
        this.id_kota = id_kota;
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
    public void setKota(){
         Database db=new Database(); 
         String s = "insert into kota values ('"+this.id_kota+"','"+this.nama+"')";
         db.query(s);
    }
    public ResultSet getKota(){
          ResultSet result=null; 
          Database db=new Database(); 
          String s = "select * from kota";
          result = db.getData(s);
          return result;
    }
}
