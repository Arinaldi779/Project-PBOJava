/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pboproject;

import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static pboproject.Config.writeLog;
import pboproject.Kategori;
import java.time.Instant;
import javax.swing.Timer;

/**
 *
 * @author Kelompok 5
 */
public class FMain extends javax.swing.JFrame {

    /**
     * Creates new form FMain
     */
    ArrayList<Kategori> arrKategori = new ArrayList<>();

    private final FTransit formTransit;

    private void load_table() {
// membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NO");
        model.addColumn("ID Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Type");
        model.addColumn("Stok");
        model.addColumn("Harga");
        model.addColumn("Tanggal Masuk");

        //menampilkan data database kedalam tabel
        try {
            int no = 1;
            String sql = "select * from v_barangtype";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{no++, res.getString(1), res.getString(2),
                    res.getString(3), res.getString(4), res.getString(5), res.getString(6)});
            }
            tblBarang.setModel(model);
            writeLog("Tampilkan data ke Frame " + getClass().getSimpleName());
        } catch (Exception e) {
            writeLog("Data tidak dapat ditampilkan : " + e.getMessage());
        }
    }

    public FMain() {
        this.formTransit = new FTransit();
        setUndecorated(true);  // Menghilangkan dekorasi jendela (termasuk tombol close, minimize, dan maximize)
        initComponents();
        setLocationRelativeTo(null); // Agar jendela muncul di tengah layar
        load_table(); // masukan code berikut
        loadComboBox();

//        showData(null);
    }

//    private void showData(String cari) {
//        String sql;
//        if (cari == null) {
//            sql = "select * from v_barangtype";
//        } else {
//            sql = "select * from v_barangtype where "
//                    + "idBarang like '%" + cari +"%'"
//                    + "namaBarang like '%" + cari + "%'"
//                    + "Kategori like '%" + cari + "%'";
//        }
//        
//        try {
////            java.sql.Connection conn = (Connection) Config.configDB();
////            java.sql.Statement stm = conn.createStatement();
////            java.sql.ResultSet res = stm.executeQuery(sql);
//            
//            ResultSet res = DB.read(sql);
//            
//        } catch (Exception e) {
//        }
//    }
    private void bersihkan() {
        txtIdBarang.setText(null);
        txtNamaBarang.setText(null);
        txtStok.setText(null);
        txtHarga.setText(null);
        cbType.setSelectedIndex(0);
        txtIdBarang.requestFocus();
    }

//    private void smoothTransition() {
//        // Buat Timer untuk Fade Out
//        Timer fadeOutTimer = new Timer(50, null); // Delay setiap frame 50ms
//        fadeOutTimer.addActionListener(e -> {
//            float opacity = getOpacity(); // Ambil nilai transparansi saat ini
//            if (opacity > 0.1f) {
//                setOpacity(opacity - 0.1f); // Kurangi transparansi
//            } else {
//                fadeOutTimer.stop(); // Hentikan fade out
//                goToTransit(); // Pindah ke halaman berikutnya
//            }
//        });
//        fadeOutTimer.start(); // Mulai fade out
//    }

    private void goToTransit() {
        // Panggil frame baru
        FTransit tabTransit = new FTransit();
        tabTransit.setVisible(true);

        // Tutup halaman saat ini
        dispose();
    }
    
    private void goToAbout() {
        // Panggil frame baru
        FAbout tabAbout = new FAbout();
        tabAbout.setVisible(true);

        // Tutup halaman saat ini
        dispose();
    }

    // Metode untuk mendapatkan ID kategori berdasarkan nama kategori
    private int getIdKategoriByNama(String namaKategori) {
        for (Kategori kategori : arrKategori) {
            if (kategori.getKategori().equals(namaKategori)) {
                return kategori.getId_type(); // Kembalikan ID jika nama cocok
            }
        }
        return 0; // Jika tidak ditemukan, kembalikan nilai default (0)
    }

    private void loadComboBox() {
        try {
            int no = 1;
            String sql = "select * from type";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            // Bersihkan ComboBox sebelum menambahkan data
            cbType.removeAllItems();
            cbType.addItem("-- Pilih Type --"); // Placeholder awal

            while (res.next()) {

                int idType = res.getInt("id_type");
                String kategori = res.getString("kategori");
                arrKategori.add(new Kategori(idType, kategori)); // Tambahkan ke array kategori
                cbType.addItem(kategori); // Tambahkan nama kategori ke ComboBox
            }
            Config.writeLog("ComboBox berhasil dimuat dengan data.");
        } catch (Exception e) {
            Config.writeLog("Error saat memuat ComboBox: " + e.getMessage());
        }
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
        cbType = new javax.swing.JComboBox<>();
        txtIdBarang = new javax.swing.JTextField();
        txtNamaBarang = new javax.swing.JTextField();
        txtStok = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBarang = new javax.swing.JTable();
        lblTransit = new javax.swing.JLabel();
        lblAbout = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btLogout = new javax.swing.JButton();
        btSubmit = new javax.swing.JButton();
        btHapus = new javax.swing.JButton();
        btEdit = new javax.swing.JButton();
        btClear = new javax.swing.JButton();
        btTambahKategori = new javax.swing.JButton();
        txtKirim = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cbType.setBackground(new java.awt.Color(255, 255, 102));
        cbType.setForeground(new java.awt.Color(0, 0, 0));
        cbType.setBorder(null);
        jPanel1.add(cbType, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 147, 202, 25));

        txtIdBarang.setBackground(new java.awt.Color(255, 255, 204));
        txtIdBarang.setBorder(null);
        txtIdBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdBarangActionPerformed(evt);
            }
        });
        jPanel1.add(txtIdBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 60, 202, 25));

        txtNamaBarang.setBackground(new java.awt.Color(255, 255, 204));
        txtNamaBarang.setBorder(null);
        jPanel1.add(txtNamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 105, 202, 25));

        txtStok.setBackground(new java.awt.Color(255, 255, 204));
        txtStok.setBorder(null);
        jPanel1.add(txtStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 187, 202, 25));

        txtHarga.setBackground(new java.awt.Color(255, 255, 204));
        txtHarga.setBorder(null);
        jPanel1.add(txtHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 228, 202, 25));

        tblBarang.setBackground(new java.awt.Color(255, 255, 204));
        tblBarang.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBarang);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, 560, 160));

        lblTransit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblTransit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTransitMouseClicked(evt);
            }
        });
        jPanel1.add(lblTransit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 130, 80));

        lblAbout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAboutMouseClicked(evt);
            }
        });
        jPanel1.add(lblAbout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 130, 80));

        jLabel5.setBackground(new java.awt.Color(255, 255, 204));
        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 1, 3)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/computer.png"))); // NOI18N
        jLabel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 204), 10, true));
        jLabel5.setOpaque(true);
        jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 130, 80));

        btLogout.setBackground(new java.awt.Color(0, 102, 153));
        btLogout.setFont(new java.awt.Font("Source Code Pro", 1, 14)); // NOI18N
        btLogout.setForeground(new java.awt.Color(255, 255, 255));
        btLogout.setText("Logout");
        btLogout.setBorder(null);
        btLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogoutActionPerformed(evt);
            }
        });
        jPanel1.add(btLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 445, 110, 37));

        btSubmit.setBackground(new java.awt.Color(255, 255, 204));
        btSubmit.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        btSubmit.setForeground(new java.awt.Color(0, 0, 0));
        btSubmit.setText("Submit");
        btSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSubmitActionPerformed(evt);
            }
        });
        jPanel1.add(btSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 290, -1, -1));

        btHapus.setBackground(new java.awt.Color(255, 255, 204));
        btHapus.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        btHapus.setForeground(new java.awt.Color(0, 0, 0));
        btHapus.setText("Hapus");
        btHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusActionPerformed(evt);
            }
        });
        jPanel1.add(btHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 290, -1, -1));

        btEdit.setBackground(new java.awt.Color(255, 255, 204));
        btEdit.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        btEdit.setForeground(new java.awt.Color(0, 0, 0));
        btEdit.setText("Edit");
        btEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditActionPerformed(evt);
            }
        });
        jPanel1.add(btEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 290, -1, -1));

        btClear.setBackground(new java.awt.Color(255, 255, 204));
        btClear.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        btClear.setForeground(new java.awt.Color(0, 0, 0));
        btClear.setText("Clear");
        btClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearActionPerformed(evt);
            }
        });
        jPanel1.add(btClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 290, -1, -1));

        btTambahKategori.setBackground(new java.awt.Color(255, 255, 204));
        btTambahKategori.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/1564491_add_create_new_plus_icon (1) (1) (1).png"))); // NOI18N
        btTambahKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTambahKategoriActionPerformed(evt);
            }
        });
        jPanel1.add(btTambahKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, 40, 40));

        txtKirim.setBackground(new java.awt.Color(51, 255, 0));
        txtKirim.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        txtKirim.setForeground(new java.awt.Color(0, 0, 0));
        txtKirim.setText("Kirim");
        txtKirim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKirimActionPerformed(evt);
            }
        });
        jPanel1.add(txtKirim, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 290, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Form Barang1.png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(1920, 1080));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 512));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdBarangActionPerformed

    private void btLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogoutActionPerformed
        // TODO add your handling code here:
        int response = JOptionPane.showConfirmDialog(this,
                "Apakah anda yakin ingin Logout",
                "Konfirmasi Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            FLogin formLogin = new FLogin();  // Pastikan FLogin adalah kelas lain yang valid
            formLogin.setVisible(true);
            dispose(); // Tutup form FMain
        }
    }//GEN-LAST:event_btLogoutActionPerformed

    private void btClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearActionPerformed
        // TODO add your handling code here:
        bersihkan();
    }//GEN-LAST:event_btClearActionPerformed

    private void btSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSubmitActionPerformed
        // TODO add your handling code here:
        String namaKategori = cbType.getSelectedItem().toString(); // Ambil nama kategori dari ComboBox
        int idKategori = getIdKategoriByNama(namaKategori); // Ambil ID kategori dari nama

        Instant timestamp = Instant.now();
        try {
            String sql = "INSERT INTO barang VALUES "
                    + "('" + txtIdBarang.getText()
                    + "','" + txtNamaBarang.getText()
                    + "','" + idKategori
                    + "','" + txtStok.getText()
                    + "','" + txtHarga.getText()
                    + "','" + timestamp + "')";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
            writeLog("Penyimpanan Data Berhasil dengan NIM " + txtIdBarang.getText());
            load_table();
            bersihkan();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            writeLog("Data gagal disimpan : " + e.getMessage());
        }
    }//GEN-LAST:event_btSubmitActionPerformed

    private void btTambahKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTambahKategoriActionPerformed
        // TODO add your handling code here:
        FType formType = new FType();
        formType.setVisible(true);
    }//GEN-LAST:event_btTambahKategoriActionPerformed

    private void btEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditActionPerformed
        // TODO add your handling code here:
        String namaKategori = cbType.getSelectedItem().toString(); // Ambil nama kategori dari ComboBox
        int idKategori = getIdKategoriByNama(namaKategori); // Ambil ID kategori dari nama

        Instant timestamp = Instant.now();
        try {
            if ("".equals(txtIdBarang.getText())) {
                JOptionPane.showMessageDialog(this, "Isikan Id Barang terlebih dahulu");
            } else {
                String sql = "UPDATE barang SET "
                        + "id_barang = '" + txtIdBarang.getText() + "', "
                        + "nama_barang = '" + txtNamaBarang.getText() + "', "
                        + "id_type = '" + idKategori + "', "
                        + "stok = '" + txtStok.getText() + "', "
                        + "harga = '" + txtHarga.getText() + "', "
                        + "tgl_masuk = '" + timestamp + "' "
                        + "WHERE id_barang = '" + txtIdBarang.getText() + "'";
                java.sql.Connection conn = (Connection) Config.configDB();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Data Berhasil Diperbaharui");
                writeLog("Data Berhasil Diperbaharui dengan Id Barang " + txtIdBarang.getText());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            writeLog("Data gagal disimpan : " + e.getMessage());
        }
        load_table();
        bersihkan();

    }//GEN-LAST:event_btEditActionPerformed

    private void tblBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBarangMouseClicked
        // TODO add your handling code here:

        int baris = tblBarang.rowAtPoint(evt.getPoint());
        String idBarang = tblBarang.getValueAt(baris, 1).toString();
        String namaBarang = tblBarang.getValueAt(baris, 2).toString();
        String kategori = tblBarang.getValueAt(baris, 3).toString();
        String stok = tblBarang.getValueAt(baris, 4).toString();
        String harga = tblBarang.getValueAt(baris, 5).toString();
        String tglMasuk = tblBarang.getValueAt(baris, 6).toString();
        txtIdBarang.setText(idBarang);
        txtNamaBarang.setText(namaBarang);
        cbType.setSelectedItem(kategori);
        txtStok.setText(stok);
        txtHarga.setText(harga);
        txtNamaBarang.requestFocus();
    }//GEN-LAST:event_tblBarangMouseClicked

    private void btHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusActionPerformed
        // TODO add your handling code here:
        try {
            if ("".equals(txtIdBarang.getText())) {
                JOptionPane.showMessageDialog(this, "Isikan Id Barang terlebih dahulu");
            } else {
                int response = JOptionPane.showConfirmDialog(this,
                        "Apakah anda yakin ingin Menghapus data",
                        "Konfirmasi Hapus",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    String sql = "DELETE FROM barang WHERE id_barang='" + txtIdBarang.getText() + "'";
                    java.sql.Connection conn = (Connection) Config.configDB();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    pst.execute();
                    JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus dengan id Barang " + txtIdBarang.getText());
                    writeLog("Data Berhasil Dihapus dengan Id Barang " + txtIdBarang.getText());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            writeLog("Data gagal dihapus : " + e.getMessage());
        }
        load_table();
        bersihkan();
    }//GEN-LAST:event_btHapusActionPerformed

    private void txtKirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKirimActionPerformed
        // TODO add your handling code here:

        try {
            if ("".equals(txtIdBarang.getText())) {
                JOptionPane.showMessageDialog(this, "Isikan Id Barang terlebih dahulu");
            } else {
                int response = JOptionPane.showConfirmDialog(this,
                        "Apakah anda yakin ingin Mengirim data",
                        "Konfirmasi Kirim",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    formTransit.setDataId(txtIdBarang);
                    formTransit.setDataNama(txtNamaBarang);
                    formTransit.setDataStok(txtStok);
                    JOptionPane.showMessageDialog(this, "Data Berhasil Dikirim dengan id Barang " + txtIdBarang.getText());
                    writeLog("Data Berhasil Dikirim dengan Id Barang " + txtIdBarang.getText());
                    formTransit.setVisible(true);
                    dispose();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            writeLog("Data gagal Dikirim : " + e.getMessage());
        }

    }//GEN-LAST:event_txtKirimActionPerformed

    private void lblTransitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTransitMouseClicked
        // TODO add your handling code here:
        try {
            Thread.sleep(300); // Delay 2 detik (2000 ms)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        goToTransit();
    }//GEN-LAST:event_lblTransitMouseClicked

    private void lblAboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAboutMouseClicked
        // TODO add your handling code here:
        try {
            Thread.sleep(300); // Delay 2 detik (2000 ms)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        goToAbout();
    }//GEN-LAST:event_lblAboutMouseClicked

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
            java.util.logging.Logger.getLogger(FMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btClear;
    private javax.swing.JButton btEdit;
    private javax.swing.JButton btHapus;
    private javax.swing.JButton btLogout;
    private javax.swing.JButton btSubmit;
    private javax.swing.JButton btTambahKategori;
    private javax.swing.JComboBox<String> cbType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAbout;
    private javax.swing.JLabel lblTransit;
    private javax.swing.JTable tblBarang;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtIdBarang;
    private javax.swing.JButton txtKirim;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtStok;
    // End of variables declaration//GEN-END:variables
}
