/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainSystem;

import TrainSystem.connect.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DIDIL
 */
public class FrameUtama extends javax.swing.JFrame {

    /**
     * Creates new form FrameUtama
     */
    private final String[] columnNamesKota = {"ID KOTA", "NAMA KOTA"};
    private final String[] columnKereta = {"ID KERETA", "NAMA KERETA", "KURSI", "GERBONG", "JENIS", "HARGA", "ID RUTE"};
    private final String[] columnsStasiun = {"ID STASIUN", "NAMA STASIUN", "NAMA KOTA"};

    public FrameUtama() {
        initComponents();
        this.show();
        waktu wktu = new waktu(waktuu);
        wktu.start();
        setStasiun();
        panelAdmin.hide();
        panelAdmin1.hide();
        hapus.hide();
        perbarui.hide();
        panelKota.hide();
        //setKereta();

    }
    public void adminnMode(){
        panelAdmin.show();
        hapus.show();
        perbarui.show();
        panelKota.show();
    }
    public void setKereta() {
        ArrayList<ArrayList> list = getKereta();

        int i = 0;
        int j = 0;
        for (ArrayList listt : list) {
            j = 0;
            for (Object a : listt) {
                tbKereta.setValueAt(a, j, i);
                j++;
            }
            i++;

        }
        for (i = 0; i < 7; i++) {
            tbKereta.setValueAt("", j, i);
        }

    }

    public void setStasiun() {
        ArrayList<ArrayList> list = getStasiunLg();

        int i = 0;
        int j = 0;
        for (ArrayList listt : list) {
            j = 0;
            for (Object a : listt) {

                tbStasiun.setValueAt(a, j, i);
                j++;
            }
            i++;

        }
        for (i = 0; i < 3; i++) {
            tbStasiun.setValueAt("", j, i);
        }

    }
public DefaultTableModel buildTableModel(ResultSet rs)
        throws SQLException {

    ResultSetMetaData colum = rs.getMetaData();

    
    // names of columns
    Vector<String> columnNames = new Vector<String>();
    int columnCount = colum.getColumnCount();
    for (int column = 1; column <= columnCount; column++) {
        columnNames.add(colum.getColumnName(column));
    }

    // data of the table
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    while (rs.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            vector.add(rs.getObject(columnIndex));
        }
        data.add(vector);
    }

    return new DefaultTableModel(data, columnNames);

}
    public Object[][][] getStasiun() {
        ArrayList<String> arSt = new ArrayList<String>();
        ArrayList<String> arId = new ArrayList<String>();
        PreparedStatement stmt = null;
        ArrayList<String> arKota = new ArrayList<String>();
        Object[][][] data = null;
        Stasiun kt = new Stasiun();
        
        try {
            ResultSet rs = kt.getStasiun();
            //rs = stmt.executeQuery("insert into kota values('SMR','SEMARANG')");
            //rs.next();
            //System.out.println(""+rs.getString("nama_kota"));
            while (rs.next()) {
                arId.add(rs.getString(1));
                arKota.add(rs.getString(2));
                arSt.add(rs.getString(3));
            }
            int a = 0, b = 0, c = 0;
            data = new Object[arId.size() + 1][arKota.size() + 1][arSt.size() + 1];
            if (arId.size() > 0 && arKota.size() > 0 && arSt.size() > 0) {
                for (String temp : arId) {
                    data[a][0][0] = temp;

                    System.out.println("" + data[a][0][0]);
                    a++;
                }
                for (String temp : arSt) {
                    data[b][1][0] = temp;
                    System.out.println("" + data[b][1][0]);
                    b++;
                }
                for (String temp : arKota) {
                    data[c][2][0] = temp;
                    System.out.println("" + data[c][2][0]);
                    c++;
                }
            }
            //tbKota = new JTable(list,columnNames);
        } catch (SQLException ex) {
            Logger.getLogger(FrameUtama.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }


        return data;
    }

    public ArrayList getStasiunLg() {
        ArrayList<ArrayList> list = new ArrayList();
        ArrayList<String> arSt = new ArrayList<String>();
        ArrayList<String> arId = new ArrayList<String>();
        PreparedStatement stmt = null;
        ArrayList<String> arKota = new ArrayList<String>();
        Object[][][] data = null;
        Stasiun kt = new Stasiun();
        try {
            ResultSet rs = kt.getStasiun();
            //rs = stmt.executeQuery("insert into kota values('SMR','SEMARANG')");
            //rs.next();
            //System.out.println(""+rs.getString("nama_kota"));
            while (rs.next()) {
                arId.add(rs.getString(1));
                arKota.add(rs.getString(2));
                arSt.add(rs.getString(3));
            }
            int a = 0, b = 0, c = 0;
            data = new Object[arId.size() + 1][arKota.size() + 1][arSt.size() + 1];
            if (arId.size() > 0 && arKota.size() > 0 && arSt.size() > 0) {

                list.add(arId);
                list.add(arKota);
                list.add(arSt);

            }
            //tbKota = new JTable(list,columnNames);
        } catch (SQLException ex) {
            Logger.getLogger(FrameUtama.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }


        return list;
    }

    public Object[][] getKota() {

        ArrayList<Object> list = new ArrayList<Object>();
        ArrayList<String> arId = new ArrayList<String>();
        PreparedStatement stmt = null;
        ArrayList<String> arKota = new ArrayList<String>();
        Object[][] data = null;
        Kota kt = new Kota();
        try {
            ResultSet rs = kt.getKota();
            //rs = stmt.executeQuery("insert into kota values('SMR','SEMARANG')");
            //rs.next();
            //System.out.println(""+rs.getString("nama_kota"));
            while (rs.next()) {
                arId.add(rs.getString("ID_KOTA"));
                arKota.add(rs.getString("NAMA_KOTA"));
            }
            int a = 0, b = 0;
            data = new Object[arId.size() + 1][arKota.size() + 1];
            if (arId.size() > 0 && arKota.size() > 0) {
                for (String temp : arId) {
                    data[a][0] = temp;
                    a++;
                }
                for (String temp : arKota) {
                    data[b][1] = temp;
                    b++;
                }
            }
            //tbKota = new JTable(list,columnNames);
        } catch (SQLException ex) {
            Logger.getLogger(FrameUtama.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return data;
    }

    public Object[][][][][][][] getKeretaLg() {

        ArrayList<String> arId = new ArrayList<String>();
        Object data[][][][][][][] = null;
        Kereta kt = new Kereta();
        PreparedStatement stmt = null;
        try {
            ResultSet rs = kt.getKereta();
            //rs = stmt.executeQuery("insert into kota values('SMR','SEMARANG')");
            //rs.next();
            //System.out.println(""+rs.getString("nama_kota"));
            while (rs.next()) {
                arId.add(rs.getString(1));
            }
            data = new Object[arId.size() + 1][arId.size() + 1][arId.size() + 1][arId.size() + 1][arId.size() + 1][arId.size() + 1][arId.size() + 1];
           // data = new Object[15][15][15][15][15][15][15];
        } catch (SQLException ex) {
            Logger.getLogger(FrameUtama.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return data;
    }
    public String getDataStringTunggal(String Table,String kolom,String kondisi,String kolomKondisi){
        String kembalian = null;
        Database db = new Database();
        String sql = "select "+kolom+" from "+Table+" where "+kondisi+" = '"+kolomKondisi+"'";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            rs=db.getData(sql);
            while (rs.next()){
                kembalian=rs.getString(kolom);
            }
        }catch (Exception e){
            
        }
        return kembalian;
    } 
    public ArrayList getKereta() {
        ArrayList<ArrayList> list = new ArrayList();
        ArrayList<String> arId = new ArrayList<String>();
        ArrayList<String> arNm = new ArrayList<String>();
        ArrayList<String> arJenis = new ArrayList<String>();
        ArrayList<String> arRute = new ArrayList<String>();

        PreparedStatement stmt = null;
        ArrayList<Integer> arKursi = new ArrayList<Integer>();
        ArrayList<Integer> arHarga = new ArrayList<Integer>();
        ArrayList<Integer> arGerbong = new ArrayList<Integer>();
        Kereta kt = new Kereta();
        try {
            ResultSet rs = kt.getKereta();
            //rs = stmt.executeQuery("insert into kota values('SMR','SEMARANG')");
            //rs.next();
            //System.out.println(""+rs.getString("nama_kota"));
            while (rs.next()) {
                arId.add(rs.getString(1));
                arNm.add(rs.getString(2));
                arKursi.add(rs.getInt(3));
                arGerbong.add(rs.getInt(4));
                arJenis.add(rs.getString(5));
                arHarga.add(rs.getInt(6));
                arRute.add(rs.getString(7));

            }

            if (arId.size() > 0) {

                list.add(arId);
                list.add(arNm);
                list.add(arKursi);
                list.add(arGerbong);
                list.add(arJenis);
                list.add(arHarga);
                list.add(arRute);

            }
            //tbKota = new JTable(list,columnNames);
        } catch (SQLException ex) {
            Logger.getLogger(FrameUtama.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }

        return list;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
 @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbStasiun = new javax.swing.JTable();
        panelAdmin = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        nmSt = new javax.swing.JTextField();
        stIdKota = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        idSt = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        listSt = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbJadwal = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbKereta = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbKota = new javax.swing.JTable();
        panelKota = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        nmKota = new javax.swing.JTextField();
        nKota = new javax.swing.JLabel();
        iKota = new javax.swing.JLabel();
        idKota = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        scrPemesanan = new javax.swing.JScrollPane();
        tbPemesanan = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        perbarui = new javax.swing.JToggleButton();
        hapus = new javax.swing.JToggleButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        waktuu = new javax.swing.JLabel();
        panelAdmin1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(tbStasiun);

        jButton3.setText("Tambah Stasiun");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setText("ID Kota");

        jLabel2.setText("Nama Stasiun");

        jLabel1.setText("ID Stasiun");

        javax.swing.GroupLayout panelAdminLayout = new javax.swing.GroupLayout(panelAdmin);
        panelAdmin.setLayout(panelAdminLayout);
        panelAdminLayout.setHorizontalGroup(
            panelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAdminLayout.createSequentialGroup()
                        .addGroup(panelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(16, 16, 16))
                    .addGroup(panelAdminLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(32, 32, 32)))
                .addGroup(panelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(idSt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(stIdKota, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nmSt, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 348, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );
        panelAdminLayout.setVerticalGroup(
            panelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAdminLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(idSt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nmSt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(stIdKota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
                    .addComponent(panelAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(385, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Lihat Stasiun", jPanel2);

        Stasiun stas = new Stasiun();
        ArrayList listsss = new ArrayList();
        try {
            Database dbsts = new Database();

            String SQLString = "select nama_stasiun \"Nama Stasiun\" from stasiun";
            ResultSet rsstasiun =  dbsts.getData(SQLString);
            while (rsstasiun.next()) {
                listsss.add(rsstasiun.getString(1));

            }
            listSt.setModel(new javax.swing.DefaultComboBoxModel());
            for (Object asd:listsss){
                listSt.addItem(asd);
            }
        }
        catch (Exception e){

        }
        listSt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                listStItemStateChanged(evt);
            }
        });
        listSt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listStActionPerformed(evt);
            }
        });

        Jadwal jw = new Jadwal();

        ResultSet rss = null;
        try {
            Database db = new Database();
            rss = db.getData("select j.id_jadwal \"ID Jadwal\",k.id_kereta \"ID Kereta\",k.nama_kereta \"Nama Kereta\",k.jumlah_kursi as Kursi,k.jumlah_gerbong as Gerbong,j.jam_datang \"Jam Datang\",j.jam_berangkat \"Jam Berangkat\",st.nama_stasiun \"Stasiun Awal\",j.stasiun_tujuan \"Stasiun Tujuan\",k.harga "+
                " from kereta k , penjadwalan pj , jadwal j ,keretastasiun ks,stasiun st"+
                " where k.id_kereta=pj.id_kereta and pj.id_jadwal=j.id_jadwal and k.id_kereta = ks.id_kereta and ks.id_stasiun = st.id_stasiun and st.nama_stasiun = '"+listSt.getSelectedItem().toString()+"'");
            tbJadwal.setModel(buildTableModel(rss));
        } catch (Exception e){

        }
        jScrollPane2.setViewportView(tbJadwal);

        jButton4.setText("Beli Tiket");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Pilih Tanggal Keberangkatan");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel4.setText("Tanggal yang dipilih");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tanggalPil, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addGap(42, 42, 42))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addGap(0, 589, Short.MAX_VALUE))))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(listSt, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(listSt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jLabel4)
                    .addComponent(tanggalPil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 398, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Lihat Jadwal", jPanel4);

        Kereta kt = new Kereta();
        ResultSet rs = null;
        try {
            rs = kt.getKereta();
            tbKereta.setModel(buildTableModel(rs));
        } catch (Exception e){

        }
        jScrollPane3.setViewportView(tbKereta);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 402, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Lihat Kereta", jPanel6);

        tbKota.setModel(new javax.swing.table.DefaultTableModel(
            getKota(),
            columnNamesKota
        ));
        tbKota.setColumnSelectionAllowed(true);
        jScrollPane4.setViewportView(tbKota);
        tbKota.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jScrollPane5.setViewportView(jScrollPane4);

        jButton1.setText("Tambah Kota");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        nKota.setText("NAMA KOTA");

        iKota.setText("ID KOTA");

        javax.swing.GroupLayout panelKotaLayout = new javax.swing.GroupLayout(panelKota);
        panelKota.setLayout(panelKotaLayout);
        panelKotaLayout.setHorizontalGroup(
            panelKotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKotaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelKotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iKota)
                    .addComponent(nKota))
                .addGap(52, 52, 52)
                .addGroup(panelKotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(idKota)
                    .addComponent(nmKota, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1))
        );
        panelKotaLayout.setVerticalGroup(
            panelKotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKotaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelKotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKotaLayout.createSequentialGroup()
                        .addGroup(panelKotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(iKota)
                            .addComponent(idKota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelKotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nKota)
                            .addComponent(nmKota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelKota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelKota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 401, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Kota", jPanel8);

        String sqlPemesanan ="select p.nama_penumpang as NamaPenumpang,t.id_tiket as NomorTiket,s.nama_stasiun as StasiunAwal,k.nama_kota as KotaAsal ,j.stasiun_tujuan as StasiunTujuan,ke.nama_kereta as NamaKereta,t.gerbong as NoGerbong ,t.nokursi as NoKursi ,t.tgl_pemesanan as TglBerangkat,j.jam_berangkat as JamBerangkat from penumpang p,tiket t,kota k,jadwal j,penjadwalan pj,kereta ke,keretastasiun ks,stasiun s where p.id_penumpang=t.id_penumpang and t.id_jadwal=j.id_jadwal and pj.id_jadwal=j.id_jadwal and pj.id_kereta=ke.id_kereta and ks.id_kereta=ke.id_kereta and ks.id_stasiun=s.id_stasiun and s.id_kota=k.id_kota";
        //String sqlPemesanan ="select p.nama_penumpang \"Nama Penumpang\",t.id_tiket \"Nomor Tiket\",s.nama_stasiun \"Stasiun Awal\",k.nama_kota \"Kota Asal\" ,j.stasiun_tujuan \"Stasiun Tujuan\",ke.nama_kereta \"Nama Kereta\",t.gerbong\"No Gerbong\",t.nokursi\"No Kursi\",t.tgl_pemesanan \"Tgl Berangkat\",j.jam_berangkat \"Jam Berangkat\" from penumpang p,tiket t,kota k,jadwal j,penjadwalan pj,kereta ke,keretastasiun ks,stasiun s where p.id_penumpang=t.id_penumpang and t.id_jadwal=j.id_jadwal and pj.id_jadwal=j.id_jadwal and pj.id_kereta=ke.id_kereta and ks.id_kereta=ke.id_kereta and ks.id_stasiun=s.id_stasiun and s.id_kota=k.id_kota";
        Database dbPemesanan = new Database();

        try {ResultSet rsPemesanan = dbPemesanan.getData(sqlPemesanan);
            tbPemesanan.setModel(buildTableModel(rsPemesanan)
            );
        }
        catch (SQLException e){

        }
        scrPemesanan.setViewportView(tbPemesanan);

        jButton6.setText("Lihat Tiket");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        perbarui.setText("Perbarui Info Penumpang");
        perbarui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                perbaruiActionPerformed(evt);
            }
        });

        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrPemesanan, javax.swing.GroupLayout.DEFAULT_SIZE, 746, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(perbarui, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(hapus))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrPemesanan, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(hapus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(perbarui)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Daftar Pemesanan", jPanel10);

        waktuu.setText("jLabel1");

        javax.swing.GroupLayout panelAdmin1Layout = new javax.swing.GroupLayout(panelAdmin1);
        panelAdmin1.setLayout(panelAdmin1Layout);
        panelAdmin1Layout.setHorizontalGroup(
            panelAdmin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelAdmin1Layout.setVerticalGroup(
            panelAdmin1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(waktuu, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(panelAdmin1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(waktuu, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAdmin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Kota kt = new Kota();
        kt.setNama(nmKota.getText());
        kt.setId_kota(idKota.getText());
        kt.setKota();
        tbKota.setModel(new javax.swing.table.DefaultTableModel(getKota(), columnNamesKota));

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Stasiun st = new Stasiun();
        st.setId_stasiun(idSt.getText());
        st.setNama(nmSt.getText());
        st.setId_kota(stIdKota.getText());
        st.setStasiun();
        tbStasiun.setModel(new javax.swing.table.DefaultTableModel(
                getStasiun(),
                columnsStasiun));
        setStasiun();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (tbJadwal.getSelectedRow() < 0){
            JOptionPane.showMessageDialog(null, "Harap pilih jadwal terlebih dahulu", "PIlih Jadwal", JOptionPane.WARNING_MESSAGE);
        }
        else if (tanggalPil.getText().length()< 1){
            JOptionPane.showMessageDialog(null, "Harap pilih Tanggal Keberangkatan", "PIlih Tanggal", JOptionPane.WARNING_MESSAGE);
        
        }
        if (tbJadwal.getSelectedRow() >= 0 &&tanggalPil.getText().length() > 0){
           String penjadwalan=getDataStringTunggal("penjadwalan", "id_penjadwalan", "id_jadwal", tbJadwal.getValueAt(tbJadwal.getSelectedRow(),0).toString());
        int maxgerbong = Integer.parseInt(tbJadwal.getValueAt(tbJadwal.getSelectedRow(),4).toString());
        String tanggal = tanggalPil.getText();
        formPenumpang fpenumpang = new formPenumpang(tbPemesanan,this,maxgerbong,tanggal, tbJadwal.getValueAt(tbJadwal.getSelectedRow(),0).toString(), penjadwalan);
        fpenumpang.show(); 
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        tanggalPil.setText(new DatePicker(this).setPickedDate());
    }//GEN-LAST:event_jButton5ActionPerformed

    private void listStActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listStActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listStActionPerformed

    private void listStItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_listStItemStateChanged
        // TODO add your handling code here:
        ResultSet rss = null;
try {
Database db = new Database();
rss = db.getData("select j.id_jadwal \"ID Jadwal\",k.id_kereta \"ID Kereta\",k.nama_kereta \"Nama Kereta\",k.jumlah_kursi as Kursi,k.jumlah_gerbong as Gerbong,j.jam_datang \"Jam Datang\",j.jam_berangkat \"Jam Berangkat\",st.nama_stasiun \"Stasiun Awal\",j.stasiun_tujuan \"Stasiun Tujuan\",k.harga "+ 
" from kereta k , penjadwalan pj , jadwal j ,keretastasiun ks,stasiun st"+
" where k.id_kereta=pj.id_kereta and pj.id_jadwal=j.id_jadwal and k.id_kereta = ks.id_kereta and ks.id_stasiun = st.id_stasiun and st.nama_stasiun = '"+listSt.getSelectedItem().toString()+"'");
tbJadwal.setModel(buildTableModel(rss));
} catch (Exception e){
 
}
    }//GEN-LAST:event_listStItemStateChanged

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
      String nama,tiket,keberangkatan,jam,stasiun;
      nama = tbPemesanan.getValueAt(tbPemesanan.getSelectedRow(), 0).toString();
      tiket = tbPemesanan.getValueAt(tbPemesanan.getSelectedRow(), 1).toString();
      keberangkatan = tbPemesanan.getValueAt(tbPemesanan.getSelectedRow(), 8).toString();
      jam = tbPemesanan.getValueAt(tbPemesanan.getSelectedRow(), 9).toString();
      stasiun = tbPemesanan.getValueAt(tbPemesanan.getSelectedRow(), 4).toString();
      int gerbnong = Integer.parseInt(tbPemesanan.getValueAt(tbPemesanan.getSelectedRow(), 6).toString());
      int kursi = Integer.parseInt(tbPemesanan.getValueAt(tbPemesanan.getSelectedRow(), 7).toString());
      frameTiket ftiket = new frameTiket(nama, tiket, keberangkatan, jam, stasiun,kursi,gerbnong);  
      ftiket.show();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        // TODO add your handling code here:
        String tiket = tbPemesanan.getValueAt(tbPemesanan.getSelectedRow(), 1).toString();
        String s = "Delete tiket where id_tiket='"+tiket+"'";
        Database dbPemesanan = new Database();
        Database dbdelete = new Database();
        dbdelete.query(s);
        String sqlPemesanan = "select p.nama_penumpang \"Nama Penumpang\",t.id_tiket \"Nomor Tiket\",s.nama_stasiun \"Stasiun Awal\",k.nama_kota \"Kota Asal\" ,j.stasiun_tujuan \"Stasiun Tujuan\",ke.nama_kereta \"Nama Kereta\",j.jam_berangkat \"Jam Berangkat\" from penumpang p,tiket t,kota k,jadwal j,penjadwalan pj,kereta ke,keretastasiun ks,stasiun s where p.id_penumpang=t.id_penumpang and t.id_jadwal=j.id_jadwal and pj.id_jadwal=j.id_jadwal and pj.id_kereta=ke.id_kereta and ks.id_kereta=ke.id_kereta and ks.id_stasiun=s.id_stasiun and s.id_kota=k.id_kota";       
        try {
            ResultSet rsPemesanan = dbPemesanan.getData(sqlPemesanan);
            tbPemesanan.setModel(buildTableModel(rsPemesanan));
        } catch (SQLException e) {
        }
        
    }//GEN-LAST:event_hapusActionPerformed

    private void perbaruiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perbaruiActionPerformed
        // TODO add your handling code here:
        String idtiket = (tbPemesanan.getValueAt(tbPemesanan.getSelectedRow(),0).toString());
        Database db = new Database();   
        String nama = null,jenis = null,id = null,alamat = null;
        int umur = 0;
        System.out.println("select * from penumpang where nama_penumpang='"+idtiket+"'");
        ResultSet rs = db.getData("select * from penumpang where nama_penumpang='"+idtiket+"'");
        try {
            while (rs.next()) {
                    System.out.println("Gak ada");
                    id=(rs.getString(1));
                    nama=(rs.getString(2));
                    jenis=(rs.getString(3));
                    alamat=(rs.getString(5));
                    umur = rs.getInt(4);
                    
                }
        } catch (SQLException ex) {
            Logger.getLogger(FrameUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        formEditPenumpang editPenumpang = new formEditPenumpang(tbPemesanan,nama, umur, alamat, jenis, id);
    }//GEN-LAST:event_perbaruiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameUtama().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton hapus;
    private javax.swing.JLabel iKota;
    private javax.swing.JTextField idKota;
    private javax.swing.JTextField idSt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox listSt;
    private javax.swing.JLabel nKota;
    private javax.swing.JTextField nmKota;
    private javax.swing.JTextField nmSt;
    private javax.swing.JPanel panelAdmin;
    private javax.swing.JPanel panelAdmin1;
    private javax.swing.JPanel panelKota;
    private javax.swing.JToggleButton perbarui;
    private javax.swing.JScrollPane scrPemesanan;
    private javax.swing.JTextField stIdKota;
    private final javax.swing.JTextField tanggalPil = new javax.swing.JTextField();
    private javax.swing.JTable tbJadwal;
    private javax.swing.JTable tbKereta;
    private javax.swing.JTable tbKota;
    private javax.swing.JTable tbPemesanan;
    private javax.swing.JTable tbStasiun;
    private javax.swing.JLabel waktuu;
    // End of variables declaration//GEN-END:variables
}
