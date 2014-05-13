/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainSystem;

import java.sql.ResultSet;
import java.util.Random;
import TrainSystem.connect.Database;

/**
 *
 * @author DIDIL
 */
public class Tiket {
    private String id_tiket,tgl_pemesanan,id_penumpang,id_penjadwalan,id_petugas,id_jadwal;
    private int gerbing,nokursi;

    /**
     * @return the id_penumpang
     */
    public String getId_penumpang() {
        return id_penumpang;
    }
    public void setIdPetugas(String id_petugas){
        this.id_petugas=id_petugas;
    }
    public void setKursi(){
        Random rd = new Random();
        int randoms = gerbing * 40;
        randoms +=rd.nextInt(10);
        nokursi = randoms;
    }
    public void setTgl(String tgl){
        this.tgl_pemesanan=tgl;
    }
    public void setGebong(int gerbing){
        this.gerbing=gerbing;
    }
    /**
     * @param id_penumpang the id_penumpang to set
     */
    public void setId_penumpang(String id_penumpang) {
        this.id_penumpang = id_penumpang;
    }

    /**
     * @return the id_tiket
     */
    public String getId_tiket() {
        return id_tiket;
    }

    /**
     * @param id_tiket the id_tiket to set
     */
    public void setId_tiket() {
        Random rd = new Random();
        String asda="";
        int randoms;
        for (int i  = 0;i <100 ; i++){
        randoms = (int) rd.nextInt(9000) * 999;
        asda = Integer.toString(randoms);
        if (asda.length()>=7){
            i=101;
        }
        }
        this.id_tiket=asda;
        setKursi();
        //this.id_tiket = rd.nextDouble(999999999);
        
    }

    /**
     * @return the id_kereta
     */


    /**
     * @return the harga
     */

    public void setTiket(){
         Database db=new Database(); 
         setId_tiket();
         java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
         String s = "insert into Tiket values ('"+getId_tiket()+"','"+this.gerbing+"','"+this.nokursi+"',TO_DATE('"+this.tgl_pemesanan+"','DD-MM-YYYY'),'"+this.id_penumpang+"','"+this.id_jadwal+"','"+this.id_petugas+"','"+this.id_penjadwalan+"')";
         System.out.println(""+s);
         db.query(s);
    }
    public ResultSet getTiket(){
          ResultSet result=null; 
          Database db=new Database(); 
          String s = "select * from Tiket";
          result = db.getData(s);
          return result;
    }

    /**
     * @param id_penjadwalan the id_penjadwalan to set
     */
    public void setId_penjadwalan(String id_penjadwalan) {
        this.id_penjadwalan = id_penjadwalan;
    }

    /**
     * @param id_jadwal the id_jadwal to set
     */
    public void setId_jadwal(String id_jadwal) {
        this.id_jadwal = id_jadwal;
    }
} 
