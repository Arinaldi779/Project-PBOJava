/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pboproject;

import java.sql.Connection;
import java.time.Instant;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import static pboproject.Config.writeLog;

;

/**
 *
 * @author Kelompok 5
 */
public class FTransit extends javax.swing.JFrame {

    /**
     * Creates new form FTransit
     */
    public FTransit() {
        setUndecorated(true);  // Menghilangkan dekorasi jendela (termasuk tombol close, minimize, dan maximize)
        initComponents();
        setLocationRelativeTo(null); // Agar jendela muncul di tengah layar
        load_table();
    }

    private void bersihkan() {
        txtIdTransit.setText(null);
        txtNamaBarang.setText(null);
        txtStok.setText(null);
        txtTujuan.setText(null);
        txtIdTransit.requestFocus();
    }

    public void updateStok() {
        try {
             // Ambil id_barang dan stok yang diinput user
        String idBarang = txtIdBarang.getText();
        int stokInput = Integer.parseInt(txtStok.getText());
        Instant timestamp = Instant.now();

        // Query untuk mengambil stok dari tabel barang
         String sqlCheck = "SELECT stok FROM barang WHERE id_barang = ?";
        java.sql.Connection conn = Config.configDB();
        java.sql.PreparedStatement pstCheck = conn.prepareStatement(sqlCheck);
        pstCheck.setString(1, idBarang); // Gunakan placeholder dan set parameter
        java.sql.ResultSet rs = pstCheck.executeQuery();

        // Periksa apakah barang ditemukan
        if (rs.next()) {
            int stokDB = rs.getInt("stok"); // Ambil stok dari database

            // Bandingkan stok database dengan stok input
            if (stokDB < stokInput) {
                // Stok tidak mencukupi
                JOptionPane.showMessageDialog(null, "Stok tidak mencukupi! Stok tersedia: " + stokDB);
            } else {
                // Stok mencukupi, lanjutkan pengurangan stok
                String sqlUpdate = "UPDATE barang SET stok = stok - ? WHERE id_barang = ?";
                java.sql.PreparedStatement pstUpdate = conn.prepareStatement(sqlUpdate);
                // Set parameter ke dalam query
                pstUpdate.setInt(1, stokInput);  // Placeholder pertama untuk stok
                pstUpdate.setString(2, idBarang); // Placeholder kedua untuk id_barang

                pstUpdate.executeUpdate(); // Gunakan executeUpdate untuk UPDATE query

                JOptionPane.showMessageDialog(null, "Stok berhasil diperbarui.");

                    JOptionPane.showMessageDialog(null, "Stok berhasil diperbarui.");
                    String sql = "INSERT INTO transaksi VALUES "
                            + "('" + txtIdTransit.getText()
                            + "','" + txtIdBarang.getText()
                            + "','" + timestamp
                            + "','" + txtTujuan.getText()
                            + "','" + txtStok.getText() + "')";
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
                    writeLog("Penyimpanan Data Berhasil dengan NIM " + txtIdTransit.getText());
                    load_table();
                    bersihkan();
                }
            } else {
                // Barang tidak ditemukan
                JOptionPane.showMessageDialog(null, "Barang dengan ID " + idBarang + " tidak ditemukan!");
            }

            // Tutup koneksi
            rs.close();
            pstCheck.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private void load_table() {
// membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NO");
        model.addColumn("ID Transit");
        model.addColumn("Nama Barang");
        model.addColumn("Kategori");
        model.addColumn("Tujuan");
        model.addColumn("Tanggal Transit");
        model.addColumn("Stok");

        //menampilkan data database kedalam tabel
        try {
            int no = 1;
            String sql = "select * from v_transaksi_barang";
            java.sql.Connection conn = (Connection) Config.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{no++, res.getString(1), res.getString(2),
                    res.getString(3), res.getString(4), res.getString(5), res.getString(6)});
            }
            tblTransit.setModel(model);
            writeLog("Tampilkan data ke Frame " + getClass().getSimpleName());
        } catch (Exception e) {
            writeLog("Data tidak dapat ditampilkan : " + e.getMessage());
        }
    }

    public void setDataId(JTextField data) {
        txtIdBarang.setText(data.getText());
    }

    public void setDataNama(JTextField data) {
        txtNamaBarang.setText(data.getText());
    }

    public void setDataStok(JTextField data) {
        txtStok.setText(data.getText());
    }

//     private void smoothTransition() {
//        // Buat Timer untuk Fade Out
//        Timer fadeOutTimer = new Timer(50, null); // Delay setiap frame 50ms
//        fadeOutTimer.addActionListener(e -> {
//            float opacity = getOpacity(); // Ambil nilai transparansi saat ini
//            if (opacity > 0.1f) {
//                setOpacity(opacity - 0.1f); // Kurangi transparansi
//            } else {
//                fadeOutTimer.stop(); // Hentikan fade out
//                goToMain(); // Pindah ke halaman berikutnya
//            }
//        });
//        fadeOutTimer.start(); // Mulai fade out
//    }
    private void goToMain() {
        // Panggil frame baru
        FMain tabMain = new FMain();
        tabMain.setVisible(true);

        // Tutup halaman saat ini
        dispose();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransit = new javax.swing.JTable();
        btLogout = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtIdTransit = new javax.swing.JTextField();
        txtNamaBarang = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtTujuan = new javax.swing.JTextArea();
        txtStok = new javax.swing.JTextField();
        btTransit = new javax.swing.JButton();
        btHapusTransit = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtIdBarang = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblTransit.setBackground(new java.awt.Color(255, 255, 204));
        tblTransit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        tblTransit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTransitMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTransit);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 340, 560, 160));

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

        jLabel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 130, 80));

        jLabel3.setBackground(new java.awt.Color(255, 255, 204));
        jLabel3.setForeground(new java.awt.Color(255, 255, 204));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/transit.png"))); // NOI18N
        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel3.setOpaque(true);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 130, 80));

        jLabel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 130, 80));

        txtIdTransit.setBackground(new java.awt.Color(255, 255, 204));
        txtIdTransit.setBorder(null);
        txtIdTransit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdTransitActionPerformed(evt);
            }
        });
        jPanel1.add(txtIdTransit, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 60, 202, 25));

        txtNamaBarang.setEditable(false);
        txtNamaBarang.setBackground(new java.awt.Color(255, 255, 204));
        txtNamaBarang.setBorder(null);
        txtNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaBarangActionPerformed(evt);
            }
        });
        jPanel1.add(txtNamaBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 106, 202, 25));

        txtTujuan.setBackground(new java.awt.Color(255, 255, 204));
        txtTujuan.setColumns(20);
        txtTujuan.setRows(5);
        txtTujuan.setWrapStyleWord(true);
        txtTujuan.setBorder(null);
        jScrollPane2.setViewportView(txtTujuan);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 200, 60));

        txtStok.setBackground(new java.awt.Color(255, 255, 204));
        txtStok.setBorder(null);
        jPanel1.add(txtStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(286, 244, 202, 25));

        btTransit.setBackground(new java.awt.Color(51, 255, 51));
        btTransit.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        btTransit.setForeground(new java.awt.Color(0, 0, 0));
        btTransit.setText("Transit");
        btTransit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTransitActionPerformed(evt);
            }
        });
        jPanel1.add(btTransit, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 290, -1, -1));

        btHapusTransit.setBackground(new java.awt.Color(255, 0, 0));
        btHapusTransit.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        btHapusTransit.setForeground(new java.awt.Color(0, 0, 0));
        btHapusTransit.setText("Hapus");
        btHapusTransit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHapusTransitActionPerformed(evt);
            }
        });
        jPanel1.add(btHapusTransit, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 290, -1, -1));

        jButton2.setBackground(new java.awt.Color(255, 255, 204));
        jButton2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 290, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Form Transit.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 512));

        txtIdBarang.setEditable(false);
        txtIdBarang.setOpaque(true);
        jPanel1.add(txtIdBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 100, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void tblTransitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransitMouseClicked
        // TODO add your handling code here:

        int baris = tblTransit.rowAtPoint(evt.getPoint());
        String idTransit = tblTransit.getValueAt(baris, 1).toString();
        String namaBarang = tblTransit.getValueAt(baris, 2).toString();
        String tujuan = tblTransit.getValueAt(baris, 5).toString();
        String stok = tblTransit.getValueAt(baris, 6).toString();
        txtIdTransit.setText(idTransit);
        txtNamaBarang.setText(namaBarang);
        txtTujuan.setText(tujuan);
        txtStok.setText(stok);
        txtNamaBarang.requestFocus();
    }//GEN-LAST:event_tblTransitMouseClicked

    private void txtIdTransitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdTransitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdTransitActionPerformed

    private void txtNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaBarangActionPerformed

    private void btTransitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTransitActionPerformed
        // TODO add your handling code here:
        try {
            updateStok();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            writeLog("Data gagal Dikirim : " + e.getMessage());
        }
    }//GEN-LAST:event_btTransitActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        bersihkan();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btHapusTransitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHapusTransitActionPerformed
        // TODO add your handling code here:

        try {
            if ("".equals(txtIdTransit.getText())) {
                JOptionPane.showMessageDialog(this, "Isikan Id Transit terlebih dahulu");
            } else {
                int response = JOptionPane.showConfirmDialog(this,
                        "Apakah anda yakin ingin Menghapus data",
                        "Konfirmasi Hapus",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    String sql = "DELETE FROM transaksi WHERE id_transaksi='" + txtIdTransit.getText() + "'";
                    java.sql.Connection conn = (Connection) Config.configDB();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    pst.execute();
                    JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus dengan id Transit " + txtIdTransit.getText());
                    writeLog("Data Berhasil Dihapus dengan Id Transit " + txtIdTransit.getText());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            writeLog("Data gagal dihapus : " + e.getMessage());
        }
        load_table();
        bersihkan();
    }//GEN-LAST:event_btHapusTransitActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        try {
            Thread.sleep(300); // Delay 2 detik (2000 ms)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        goToMain();
    }//GEN-LAST:event_jLabel5MouseClicked

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
            java.util.logging.Logger.getLogger(FTransit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FTransit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FTransit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FTransit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FTransit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btHapusTransit;
    private javax.swing.JButton btLogout;
    private javax.swing.JButton btTransit;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblTransit;
    private javax.swing.JTextField txtIdBarang;
    private javax.swing.JTextField txtIdTransit;
    private javax.swing.JTextField txtNamaBarang;
    private javax.swing.JTextField txtStok;
    private javax.swing.JTextArea txtTujuan;
    // End of variables declaration//GEN-END:variables
}
