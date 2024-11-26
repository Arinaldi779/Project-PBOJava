/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pboproject;

/**
 *
 * @author Kelompok 5
 */
public class FSplash extends javax.swing.JFrame {

    /**
     * Creates new form FSplash
     */
    public FSplash() {
        setUndecorated(true);  // Menghilangkan dekorasi jendela (termasuk tombol close, minimize, dan maximize)
        initComponents();
        setLocationRelativeTo(null);
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
        progres = new javax.swing.JLabel();
        bar = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        progres.setForeground(new java.awt.Color(0, 0, 0));
        progres.setText("0 %");
        jPanel1.add(progres, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 370, 30, -1));
        jPanel1.add(bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 460, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Splash Screen.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FSplash sp = new FSplash(); // Perbaikan tanda titik koma
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                sp.setVisible(true);
            }
        });
        try {
            for (int i = 0; i <= 100; i++) { // Perbaikan kondisi loop
                Thread.sleep(10); // Delay 90 ms
                sp.bar.setValue(i); // Progress bar dinamis
                sp.progres.setText(Integer.toString(i) + " %");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Opsional untuk debugging
        }
        sp.setVisible(false); // Sembunyikan splash screen
        FLogin formLogin = new FLogin();
        formLogin.setVisible(true);
        sp.dispose(); // Hapus splash screen dari memori
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar bar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel progres;
    // End of variables declaration//GEN-END:variables
}
