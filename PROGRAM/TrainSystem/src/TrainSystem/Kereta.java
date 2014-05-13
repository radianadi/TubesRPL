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
public class Kereta {
    private String nama,id_kereta,id_jenis,id_rute;
    private int JmlKursi,JmlGerbong;
    private double harga;

    /**
     * @return the id_kereta
     */
    public String getId_kereta() {
        return id_kereta;
    }

    /**
     * @param id_kereta the id_kereta to set
     */
    public void setId_kereta(String id_kereta) {
        this.setId_kereta(id_kereta);
    }

    /**
     * @return the JmlGerbong
     */
    public double getJmlGerbong() {
        return JmlGerbong;
    }

    /**
     * @param JmlGerbong the JmlGerbong to set
     */
    public void setJmlGerbong(double JmlGerbong) {
        this.setJmlGerbong(JmlGerbong);
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

    /**
     * @return the JmlKursi
     */
    public int getJmlKursi() {
        return JmlKursi;
    }

    /**
     * @param JmlKursi the JmlKursi to set
     */
    public void setJmlKursi(int JmlKursi) {
        this.JmlKursi = JmlKursi;
    }

    /**
     * @return the id_stasiun
     */


    /**
     * @return the id_jenis
     */
    public String getId_jenis() {
        return id_jenis;
    }

    /**
     * @param id_jenis the id_jenis to set
     */
    public void setId_jenis(double id_jenis) {
        this.setId_jenis(id_jenis);
    }
    public void setKereta(){
         Database db=new Database(); 
         String s = "insert into Kereta values ('"+this.getId_kereta()+"','"+this.getNama()+"','"+this.getJmlKursi()+"','"+this.getJmlGerbong()+"','"+this.getId_jenis()+"','"+this.getHarga()+"','"+getHarga()+"')";
         db.query(s);
    }
    public ResultSet getKereta(){
         ResultSet result=null; 
          Database db=new Database(); 
          String s = "select * from kereta";
          result = db.getData(s);
          return result;
    }

    /**
     * @param id_kereta the id_kereta to set
     */
   
    /**
     * @param id_jenis the id_jenis to set
     */
    public void setId_jenis(String id_jenis) {
        this.id_jenis = id_jenis;
    }

    /**
     * @return the id_rute
     */
    public String getId_rute() {
        return id_rute;
    }

    /**
     * @param id_rute the id_rute to set
     */
    public void setId_rute(String id_rute) {
        this.id_rute = id_rute;
    }

    /**
     * @param JmlGerbong the JmlGerbong to set
     */
    public void setJmlGerbong(int JmlGerbong) {
        this.JmlGerbong = JmlGerbong;
    }

    /**
     * @return the harga
     */
    public double getHarga() {
        return harga;
    }

    /**
     * @param harga the harga to set
     */
    public void setHarga(double harga) {
        this.harga = harga;
    }
}