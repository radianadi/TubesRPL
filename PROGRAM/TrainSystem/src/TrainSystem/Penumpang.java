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
public class Penumpang extends Orang {

    
    private String Jenis_kelamin, Alamat, id_penumpang;

    /**
     * @return the id_tiket
     *
     *
     * /
     **
     * @return the Jenis_kelamin
     */
    public String getJenis_kelamin() {
        return Jenis_kelamin;
    }

    /**
     * @param Jenis_kelamin the Jenis_kelamin to set
     */
    public void setJenis_kelamin(String Jenis_kelamin) {
        this.Jenis_kelamin = Jenis_kelamin;
    }

    public void setPenumpang() {
        Database db = new Database();
        String s = "insert into Penumpang values ('" +getId_penumpang() + "','" + getNama() + "','" + getJenis_kelamin()+ "','"+getUmur()+"','"+getAlamat()+"')";
        db.query(s);
    }
    public void updatePenumpang(){
        Database db = new Database();
        String s = "update penumpang set nama_penumpang='"+getNama()+"',jenis_kelamin='"+getJenis_kelamin()+"',umur='"+getUmur()+"',alamat='"+getAlamat()+"' where id_penumpang='"+getId_penumpang()+"'";
        db.query(s);
    }
    public ResultSet getPenumpang() {
        ResultSet result = null;
        Database db = new Database();
        String s = "select * from Penumpang";
        result = db.getData(s);
        return result;
    }

    /**
     * @return the umur
     */
   

    /**
     * @return the Alamat
     */
    public String getAlamat() {
        return Alamat;
    }

    /**
     * @param Alamat the Alamat to set
     */
    public void setAlamat(String Alamat) {
        this.Alamat = Alamat;
    }

    /**
     * @return the Nama
     */
 

    /**
     * @return the id_penumpang
     */
    public String getId_penumpang() {
        return id_penumpang;
    }

    /**
     * @param id_penumpang the id_penumpang to set
     */
    public void setId_penumpang(String id_penumpang) {
        this.id_penumpang = id_penumpang;
    }
}
