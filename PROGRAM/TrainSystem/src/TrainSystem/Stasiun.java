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
public class Stasiun {
    private String id_stasiun;
    private String id_kota;
    private String nama_stasiun;

    /**
     * @return the id_stasiun
     */
    public String getId_stasiun() {
        return id_stasiun;
    }

    /**
     * @param id_stasiun the id_stasiun to set
     */
    public void setId_stasiun(String id_stasiun) {
        this.id_stasiun = id_stasiun;
    }

    /**
     * @return the id_petugas


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
     * @return the id_kereta
     */
    /**
     * @return the nama
     */
    public String getNama() {
        return getNama_stasiun();
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.setNama_stasiun(nama);
    }

    /**
     * @return the lokasi
     */

    public void setStasiun(){
         Database db=new Database(); 
         String s = "insert into Stasiun values ('"+getId_stasiun()+"','"+getNama_stasiun()+"','"+getId_kota()+"')";
         db.query(s);
    }
    public ResultSet getStasiun(){
          ResultSet result=null; 
          Database db=new Database(); 
          String s = "select s.id_stasiun ,s.nama_stasiun, k.nama_kota from stasiun s,kota k where s.id_kota = k.id_kota";
          result = db.getData(s);
          return result;
    }

    /**
     * @return the nama_stasiun
     */
    public String getNama_stasiun() {
        return nama_stasiun;
    }

         /**
     * @param nama_stasiun the nama_stasiun to set
     */
    public void setNama_stasiun(String nama_stasiun) {
        this.nama_stasiun = nama_stasiun;
    }
}
