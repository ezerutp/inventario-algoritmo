package appinventario.views.Suministro;

import appinventario.controllers.SuministroController;
import appinventario.models.Suministro;
import appinventario.tablas.SuministroTableModel;
import appinventario.views.BaseMover;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class frmSuministroView extends BaseMover {
    
    private final SuministroController controlador;
    public static frmSuministroView instancia;

    public frmSuministroView() {
        initComponents();
        this.controlador = new SuministroController();
        actualizarTabla();
    }

    // Funcion para la vista nuevo
    private void frmNewView() {
        frmSuministroAddView addview = new frmSuministroAddView(this, true, this.controlador);
        addview.setVisible(true);
        actualizarTabla();
    }
    
    // Funcion para la vista eliminar
    private void frmEliminarView() {
        if (this.txtID.getText() != null) {
            try {
                int id = Integer.parseInt(this.txtID.getText());
                Suministro sum = controlador.obtenerSuministro(id);
                if (sum.getId() > 0) {
                    int opcion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el suministro : [ " + sum.getCantidad() + " de " + sum.getProducto().getNombre() + " ] ?", "CUIDADO!", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION) {
                        boolean rs = controlador.eliminarSuministro(id);
                        if (rs) {
                            JOptionPane.showMessageDialog(null, "SUMINISTRO ELIMINADO!!","CORRECTO",JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "NO SE PUDO ELIMINAR EL SUMINISTRO","ERROR",JOptionPane.ERROR_MESSAGE);
                        }
                        actualizarTabla();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No existe ningun suministro con esa ID","ERROR",JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    // Funcion para refrescar la tabla y visualizar los cambios
    private void actualizarTabla() {
        List<Suministro> sum = controlador.obtenerTodosSuministros();
        SuministroTableModel modelo = new SuministroTableModel(sum);
        this.tablaSuministros.setModel(modelo);
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaSuministros = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 224, 246), 4));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 36)); // NOI18N
        jLabel1.setText("SUMINISTRO");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        tablaSuministros.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablaSuministros);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 860, 300));

        btnAgregar.setBackground(new java.awt.Color(51, 255, 51));
        btnAgregar.setText("AGREGAR SUMINISTRO");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 110, 180, 30));

        btnEliminar.setBackground(new java.awt.Color(255, 51, 0));
        btnEliminar.setText("ELIMINAR SUMINISTRO");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, 180, 30));
        jPanel1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, 90, 30));

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel2.setText("ID");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, -1, -1));

        lblClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/x.png"))); // NOI18N
        lblClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCloseMouseClicked(evt);
            }
        });
        jPanel1.add(lblClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(865, 15, 20, 20));

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bgadmin.png"))); // NOI18N
        lblBackground.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 4, 892, 472));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        frmNewView();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        frmEliminarView();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void lblCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseClicked
        dispose();
    }//GEN-LAST:event_lblCloseMouseClicked

    public static frmSuministroView getInstancia() {
        if (instancia == null) {
            instancia = new frmSuministroView();
            instancia.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            return instancia;
        } else {
            return instancia;
        }
    }
    
    public void visible(boolean visible) {
        instancia.setVisible(visible);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblClose;
    private javax.swing.JTable tablaSuministros;
    private javax.swing.JTextField txtID;
    // End of variables declaration//GEN-END:variables
}
