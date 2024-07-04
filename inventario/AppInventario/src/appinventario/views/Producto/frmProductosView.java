package appinventario.views.Producto;

import appinventario.tablas.ProductoTableModel;
import appinventario.controllers.ProductoController;
import appinventario.models.Producto;
import appinventario.views.BaseMover;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class frmProductosView extends BaseMover {
    
    private final ProductoController controlador;
    public static frmProductosView instancia;

    public frmProductosView() {
        initComponents();
        controlador = new ProductoController();
        actualizarTabla();
    }
    
    // Funcion para la vista update
    private void frmUpdateView(int id) {
        frmProductoUpdateView updateview = new frmProductoUpdateView(this, true, this.controlador, id);
        updateview.setVisible(true);
        actualizarTabla();
    }
    
    // Funcion para la vista nuevo
    private void frmNewView() {
        frmProductoAddView addview = new frmProductoAddView(this, true, this.controlador);
        addview.setVisible(true);
        actualizarTabla();
    }
    
    // Funcion para la vista eliminar
    private void frmEliminarView() {
        if (this.txtID.getText() != null) {
            try {
                int id = Integer.parseInt(this.txtID.getText());
                Producto producto = controlador.obtenerProducto(id);
                if (producto.getId() > 0) {
                    int opcion = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el producto " + producto.getNombre() + " ?", "CUIDADO!", JOptionPane.YES_NO_OPTION);
                    if (opcion == JOptionPane.YES_OPTION) {
                        boolean rs = controlador.eliminarProducto(id);
                        if (rs) {
                            JOptionPane.showMessageDialog(null, "PRODUCTO ELIMINADO!!","CORRECTO",JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "NO SE PUDO ELIMINAR EL PRODUCTO","ERROR",JOptionPane.ERROR_MESSAGE);
                        }
                        actualizarTabla();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No existe ningun producto con esa ID","ERROR",JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    // Funcion para refrescar la tabla y visualizar los cambios
    private void actualizarTabla() {
        List<Producto> productos = controlador.obtenerTodosProductos();
        ProductoTableModel modelo = new ProductoTableModel(productos);
        this.tablaProductos.setModel(modelo);
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
        btnModificar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        btnEliminar1 = new javax.swing.JButton();
        lblClose = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(224, 224, 252), 4));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setFont(new java.awt.Font("Consolas", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("PRODUCTOS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, 70));

        btnModificar.setBackground(new java.awt.Color(0, 153, 153));
        btnModificar.setText("MODIFICAR");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, 130, 30));

        btnRegistrar.setBackground(new java.awt.Color(0, 204, 0));
        btnRegistrar.setText("NUEVO");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, 130, 30));

        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaProductos);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 163, 780, 260));

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("ID");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, -1, -1));
        jPanel1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 33, 30));

        btnEliminar1.setBackground(new java.awt.Color(255, 51, 0));
        btnEliminar1.setText("ELIMINAR");
        btnEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, 130, 30));

        lblClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/x.png"))); // NOI18N
        lblClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCloseMouseClicked(evt);
            }
        });
        jPanel1.add(lblClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, 20, 20));

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/bgadmin.png"))); // NOI18N
        jPanel1.add(lblBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 4, 820, 443));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            if (!this.txtID.getText().isEmpty ()){
            int id = Integer.parseInt(this.txtID.getText());
            Producto user = controlador.obtenerProducto(id);
            if (user.getId()>0){
                frmUpdateView(id);     
            } else {
                JOptionPane.showMessageDialog(null, "No existe ningun pruduto con esa ID","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }
        }catch (Exception e)  {   
            JOptionPane.showMessageDialog(null,e.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        frmNewView();
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void tablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMouseClicked
        if (evt.getClickCount() == 2) {
            JTable target = (JTable) evt.getSource();
            int row = target.getSelectedRow();
            int id = (int) target.getValueAt(row, 0); // Es la id del elemento de la JTable.
            frmUpdateView(id); // Invocamos al metodo main modificado para que reciba el id del usuario
        }
    }//GEN-LAST:event_tablaProductosMouseClicked

    private void btnEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar1ActionPerformed
        frmEliminarView();
    }//GEN-LAST:event_btnEliminar1ActionPerformed

    private void lblCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseClicked
        dispose();
    }//GEN-LAST:event_lblCloseMouseClicked

    public static frmProductosView getInstancia() {
        if (instancia == null) {
            instancia = new frmProductosView();
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
    private javax.swing.JButton btnEliminar1;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblClose;
    private javax.swing.JTable tablaProductos;
    private javax.swing.JTextField txtID;
    // End of variables declaration//GEN-END:variables
}
