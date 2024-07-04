package appinventario.views.Administrador;

import javax.swing.JOptionPane;

import appinventario.controllers.AdminController;
import appinventario.models.Usuario;
import appinventario.utils.UserSession;

public class frmAdministradorPasswordView extends java.awt.Dialog {

    public frmAdministradorPasswordView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    private boolean validarDatos() {
        if (txtNewPass.getPassword().length == 0 || txtConfirmPass.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "Los campos de contraseña no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String snewpass = new String(this.txtNewPass.getPassword());
        String sconfirmpass = new String(this.txtConfirmPass.getPassword());
        if (!snewpass.equals(sconfirmpass)) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblConfirmPass = new javax.swing.JLabel();
        lblNewPass = new javax.swing.JLabel();
        txtConfirmPass = new javax.swing.JPasswordField();
        txtNewPass = new javax.swing.JPasswordField();
        btnUpdate = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(412, 172));
        setMinimumSize(new java.awt.Dimension(412, 172));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(412, 172));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 2));
        jPanel1.setMaximumSize(new java.awt.Dimension(412, 157));
        jPanel1.setMinimumSize(new java.awt.Dimension(412, 157));
        jPanel1.setPreferredSize(new java.awt.Dimension(412, 157));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jLabel2.setText("POR SEGURIDAD INGRESE UNA NUEVA CONTRASEÑA");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 15, -1, -1));

        lblConfirmPass.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        lblConfirmPass.setText("Confirmar contraseña :");
        jPanel1.add(lblConfirmPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 86, -1, -1));

        lblNewPass.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        lblNewPass.setText("Nueva contraseña :");
        jPanel1.add(lblNewPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 52, -1, -1));

        txtConfirmPass.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtConfirmPass.setSelectionColor(null);
        jPanel1.add(txtConfirmPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 190, 30));

        txtNewPass.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtNewPass.setSelectionColor(null);
        jPanel1.add(txtNewPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 45, 190, 30));

        btnUpdate.setText("ACTUALIZAR CONTRASEÑA");
        btnUpdate.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnUpdate.setBorderPainted(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 230, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bg.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 408, 168));

        add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (validarDatos()) {
            Usuario user = UserSession.getInstancia().getUser();
            String pass = new String(this.txtNewPass.getPassword());
            user.setPassword(pass);
            user.setforcePass(false);
            AdminController control = new AdminController();
            boolean rs = control.cambiarContraseñaAdministrador(user, pass);
            if (!rs) {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar su contraseña.", "ERROR", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña cambiada!", "CORRECTO!", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblConfirmPass;
    private javax.swing.JLabel lblNewPass;
    private javax.swing.JPasswordField txtConfirmPass;
    private javax.swing.JPasswordField txtNewPass;
    // End of variables declaration//GEN-END:variables
}
