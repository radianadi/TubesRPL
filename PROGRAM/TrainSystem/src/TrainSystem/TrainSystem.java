/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainSystem;


import java.util.Random;


/**
 *
 * @author DIDIL
 */
public class TrainSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Random rd = new Random();
        int randoms;
        String asda ;
        
        //this.id_tiket = rd.nextDouble(999999999);
        /*for (int i = 0 ; i<=10;i++){
        randoms = (int) rd.nextInt(9000) * 999;
        asda = Integer.toString(randoms);
            System.out.println(asda+" length "+asda.length());
        if (asda.length()>6){
            i=11;
        }
        }*/
        FormPemilihan fp = new FormPemilihan();
        fp.show();

    }

    static class ColumnsPriv {

        public ColumnsPriv() {
        }
    }

    static class Kota {

        public Kota() {
        }
    }
}
