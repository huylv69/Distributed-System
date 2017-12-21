/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myftpstorage;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import static java.rmi.server.UnicastRemoteObject.unexportObject;
import java.util.Arrays;
import java.util.Enumeration;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author HuyLV
 */
public class ScreenServer extends javax.swing.JFrame {

    public ServerInterface server;
    private Registry rmiRegistry;
    private File serverFile;
    private boolean isStart;
    private int syncState;
    private JFileChooser fileChooser;

    /**
     * Creates new form ScreenServer
     */
    public ScreenServer() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_Stop = new javax.swing.JButton();
        btn_Start = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btn_ChooseFile = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_Stop.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_Stop.setText("Stop");
        btn_Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_StopActionPerformed(evt);
            }
        });

        btn_Start.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_Start.setText("Start");
        btn_Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_StartActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Choose File:");

        btn_ChooseFile.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_ChooseFile.setText("E:\\server");
        btn_ChooseFile.setName(""); // NOI18N
        btn_ChooseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ChooseFileActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MYFTP SEVER");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_ChooseFile, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_Start, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                        .addComponent(btn_Stop, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(69, 69, 69))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ChooseFile, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Start, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Stop, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_StopActionPerformed
        btn_Stop.addActionListener((java.awt.event.ActionEvent e) -> {
            try {
                if (btn_Stop.getText().equalsIgnoreCase("Stop")) {
                    stop();
                    btn_Start.setEnabled(true);
                    btn_Stop.setEnabled(false);
                    btn_Start.setText("Start");
                    btn_Stop.setText("Stopped");
                    btn_ChooseFile.setText("E:\\server");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
            }
        });
    }//GEN-LAST:event_btn_StopActionPerformed

    private void btn_StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_StartActionPerformed
        try {
            if (btn_ChooseFile.getText().equals("E:\\server")) {
                File defaultFile = new File("E:\\server");
                if (!defaultFile.exists()) {
                    // create file
                    if (defaultFile.mkdir()) {
                        btn_ChooseFile.setText("E:\\server");
                        // notification successful
                        JOptionPane.showMessageDialog(null, "Directory created!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Create directory failse");
                    }
                }
                // Nếu thư mục tồn tại, trả về địa chỉ ip của máy chủ RMI
                System.getProperty("java.rmi.server.hostname " + getIpServer());
                System.out.println("IP server: " + getIpServer());
                btn_ChooseFile.setText(defaultFile.getAbsolutePath());
                // Cài đặt quản lý an ninh mạng
                /*
                    RMISecurityManager implements a policy identical to the policy 
                    implemented by SecurityManager. 
                    RMI applications should use the SecurityManager class 
                    or another appropriate SecurityManager implementation instead of this class. 
                    RMI's class loader will download classes from remote locations only 
                    if a security manager has been set.
                 */
                if (System.getSecurityManager() == null) {
                    System.setSecurityManager(new SecurityManager());
                }
                server = new ServerImplement(defaultFile);
            } else {
                // Chọn folder
                // Kiểm tra địa chỉ
//                System.getProperty("java.rmi.server.hostname", getIpServer());
                if (System.getSecurityManager() == null) {
                    System.setSecurityManager(new SecurityManager());
                }
                System.out.println(fileChooser.getSelectedFile().getPath());
                server = new ServerImplement(fileChooser.getSelectedFile());
            }
            start();
            btn_Start.setText("Started");
            btn_Stop.setText("Stop");
            btn_Start.setEnabled(false);
            btn_Stop.setEnabled(true);
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }//GEN-LAST:event_btn_StartActionPerformed

    private void btn_ChooseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ChooseFileActionPerformed
        fileChooser = new JFileChooser();
        // Sets the string that goes in the JFileChooser window's title bar.
        fileChooser.setDialogTitle("Choose Server Folder");
        // Set default thÆ° má»¥c hiá»‡n táº¡i
        fileChooser.setCurrentDirectory(new java.io.File("E:\\server"));
        // Sets the JFileChooser to allow the user to just select only directories.
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        // Determines whether the AcceptAll FileFilter is used as an available choice in the choosable filter list.
        fileChooser.setAcceptAllFileFilterUsed(false);
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            btn_ChooseFile.setText("" + fileChooser.getSelectedFile().toString());
        } else {
            JOptionPane.showMessageDialog(null, "No selection");
        }
        System.out.println(Arrays.toString(fileChooser.getSelectedFiles()));
    }//GEN-LAST:event_btn_ChooseFileActionPerformed

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
            java.util.logging.Logger.getLogger(ScreenServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScreenServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScreenServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScreenServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ScreenServer().setVisible(true);
        });
    }

    public void start() throws Exception {
        // registry port 3000
        rmiRegistry = LocateRegistry.createRegistry(3000);
        //Thay thế các ràng buộc, rebind() để tránh lỗi trong trường hợp "server" đã tồn tại trong RMI Registry
        rmiRegistry.rebind("server", server);
        JOptionPane.showMessageDialog(null, "Server Started");
        isStart = true;
    }

    public void stop() throws Exception {
        int choice = JOptionPane.showConfirmDialog(null, " You are sure to stop application?");
        if (choice == JOptionPane.YES_OPTION) {
            isStart = false;
            syncState = 0;
            // Loại bỏ ràng buộc trong this registry
            rmiRegistry.unbind("server");
            unexportObject(server, true);
            // Removes the remote object, obj, from the RMI runtime.
            unexportObject(rmiRegistry, true);
        }
    }

    // Hàm xác định địa chỉ IP máy chủ địa phương
    public static String getIpServer() {
        String ipAddress = null;
        // net trả về danh sách địa chỉ ip trên 1 máy
        Enumeration<NetworkInterface> net = null;
        try {
            net = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        // Kiểm tra tất cả yếu tố của đối tượng net
        while (net.hasMoreElements()) {
            NetworkInterface element = net.nextElement();
            // Lấy địa chỉ của từng đối tượng net
            Enumeration<InetAddress> addresses = element.getInetAddresses();
            //Kiểm tra yế tố của đối tượng address
            while (addresses.hasMoreElements()) {
                InetAddress ip = addresses.nextElement();
                if (ip instanceof Inet4Address) {
                    // Kiểm tra có phải địa chỉ local hay không??? 10/8, 172.16/12 and 192.168/16.
                    if (ip.isSiteLocalAddress()) {
                        // Lấy ip của địa chỉ
                        ipAddress = ip.getHostAddress();
                    }
                }
            }
        }
        return ipAddress;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ChooseFile;
    private javax.swing.JButton btn_Start;
    private javax.swing.JButton btn_Stop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
