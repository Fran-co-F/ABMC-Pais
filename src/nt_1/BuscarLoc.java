package nt_1;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

//This class it's not finished yet

public class BuscarLoc extends javax.swing.JFrame {
    
    public Connection coneccion;
    public static Statement sentencia;
    public String url="jdbc:mysql://localhost/general";
    public String usuario="root";
    public String contraseña="123456";
    public ResultSet r;
    public static ResultSet res;
    public String a = "";
    public TableRowSorter trs;
    public String pcia;
    public int [] i = new int [1000];
    public static int [] prov = new int [40];
    public static String bandera = "inicio";
    
    public BuscarLoc() {
        initComponents();
        PrepararBaseDeDatos();
        CargarCombo();
        CargarConsulta();
        CargarDatos();
        bandera = "otro";
    }

    public void PrepararBaseDeDatos(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            coneccion = DriverManager.getConnection(url,usuario,contraseña);
            if (coneccion != null)
            {
                System.out.println("Conexión a base de datos  ... Ok");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al cargar el controlador");
        }
        
        try{
            sentencia = coneccion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al crear el obeto sentencia");
        }
    }

    public static void CargarCombo(){
        int i= 1;
        try {
            cboProvincia.removeAllItems();
            prov[0]=0;
            cboProvincia.addItem("Todas");
            res = sentencia.executeQuery("SELECT * FROM general.provincias ORDER BY IdProvincia"); 
            res.beforeFirst();
            while (res.next()) {
                cboProvincia.addItem(res.getString("NomProvincia"));
                prov [i]= res.getInt("IdProvincia");
                i++;
            }
        }
        catch (SQLException ex) {
            System.out.println("error en CargarCombo");      
        }
    }
        
    void CargarConsulta(){
        try{
            r = sentencia.executeQuery("SELECT IdLocalidad, NomLocalidad, CodPostal, NomProvincia, IdProvincia FROM general.provincias, general.localidades WHERE general.localidades.ProvinciaId = general.provincias.IdProvincia ORDER BY general.localidades.NomLocalidad");
        }catch(Exception e){
            System.out.println("error en CargarConsulta");}
    }
    
    void CargarConsultaCategoria(){
        try{
            DefaultTableModel tabla = (DefaultTableModel) tblLocalidades.getModel();
            int i=1,a=0;
            a = tabla.getRowCount();
            for (int q=1;q<=a;q++){
                tabla.removeRow(0);
            }
            if (cboProvincia.getSelectedIndex()==0){
                CargarConsulta();
            }else{
                while (i != cboProvincia.getSelectedIndex()){
                    i++;
                }
                r = sentencia.executeQuery("SELECT IdLocalidad, NomLocalidad, CodPostal, NomProvincia, IdProvincia FROM general.provincias, general.localidades WHERE general.localidades.ProvinciaId = general.provincias.IdProvincia AND general.localidades.ProvinciaId = " + prov[i] + " ORDER BY general.localidades.NomLocalidad");
            }
            CargarDatos();
        }catch(Exception e){
                System.out.println("error en CargarConsultaCategoria");
        }
    }
    
    void CargarDatos(){
        DefaultTableModel tabla = (DefaultTableModel) tblLocalidades.getModel();
        try{
            int f=0;
            r.beforeFirst();
            while (r.next()){
                tabla.addRow(new Object[]{"","","","",""});
                tabla.setValueAt(r.getString("IdLocalidad"),f ,0 );
                tabla.setValueAt(r.getString("NomLocalidad"),f ,1 );
                tabla.setValueAt(r.getString("CodPostal"),f ,2 );
                tabla.setValueAt(r.getString("NomProvincia"),f ,3 );
                tabla.setValueAt(r.getString("IdProvincia"),f ,4 );
                f++;
            }
        }catch(Exception e){
            System.out.println("error en CargarDatos");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLocalidades = new javax.swing.JTable();
        btnReporteLocalidades = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        btnSeleccionar = new javax.swing.JButton();
        cboProvincia = new javax.swing.JComboBox();
        btnQuitar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar Materiales");
        setName("frmBuscarLocalidad"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        tblLocalidades.setBorder(new javax.swing.border.MatteBorder(null));
        tblLocalidades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Código Postal", "Provincia", "IdProvincia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLocalidades.setColumnSelectionAllowed(true);
        tblLocalidades.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblLocalidades.setRowSelectionAllowed(false);
        tblLocalidades.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblLocalidades);
        tblLocalidades.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tblLocalidades.getColumnModel().getColumnCount() > 0) {
            tblLocalidades.getColumnModel().getColumn(0).setResizable(false);
            tblLocalidades.getColumnModel().getColumn(1).setResizable(false);
            tblLocalidades.getColumnModel().getColumn(2).setResizable(false);
            tblLocalidades.getColumnModel().getColumn(3).setResizable(false);
            tblLocalidades.getColumnModel().getColumn(4).setMinWidth(0);
            tblLocalidades.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        btnReporteLocalidades.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 14)); // NOI18N
        btnReporteLocalidades.setText("Reporte");
        btnReporteLocalidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteLocalidadesActionPerformed(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
        });

        btnSeleccionar.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 14)); // NOI18N
        btnSeleccionar.setLabel("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        cboProvincia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboProvinciaActionPerformed(evt);
            }
        });

        btnQuitar.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 14)); // NOI18N
        btnQuitar.setText("Quitar Filtro");

        jLabel1.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 14)); // NOI18N
        jLabel1.setText("Nombre:");

        jLabel2.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 14)); // NOI18N
        jLabel2.setText("Provincia:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboProvincia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 297, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSeleccionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQuitar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeleccionar)
                    .addComponent(jLabel1))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnQuitar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnReporteLocalidades, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(btnReporteLocalidades)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cualquiera(){
            txtNombre.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent ke){
                trs.setRowFilter(RowFilter.regexFilter("(?i)"+txtNombre.getText(),1));
            }
        });
        trs = new TableRowSorter(tblLocalidades.getModel());
        tblLocalidades.setRowSorter(trs);
    }
            
    
    
    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
//        Localidad bp=new Localidad();
//        try{
//            String Id = (String) tblLocalidades.getValueAt(tblLocalidades.getSelectedRow(), 0);
//            String Nombre = (String) tblLocalidades.getValueAt(tblLocalidades.getSelectedRow(), 1);
//            String CodigoPostal = (String) tblLocalidades.getValueAt(tblLocalidades.getSelectedRow(), 2);
//            String Provincia = (String) tblLocalidades.getValueAt(tblLocalidades.getSelectedRow(), 4);
//            Localidad.txtId.setText(Id);
//            Localidad.txtNombre.setText(Nombre);
//            Localidad.txtCodigoPostal.setText(CodigoPostal);
//            int i = Integer.parseInt(Provincia);
//            int aa=0;
//            while (i != Localidad.A[aa]){
//                aa++;
//            }
//            System.out.println(aa);
//            Localidad.cboProvincia.setSelectedIndex(aa);
//            this.setVisible(false);
//        }catch(Exception e){
//            System.out.println(e);
//        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void btnReporteLocalidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteLocalidadesActionPerformed
        /*String dir_current = System.getProperty("user.dir") ;
        String reportdirjrxml = dir_current+"/src/reportes/ReporteLocalidades.jrxml";        
        try {
            JasperReport reporte = JasperCompileManager.compileReport(reportdirjrxml);
            JasperPrint print = JasperFillManager.fillReport(reporte, null, this.coneccion);
            JasperViewer view = new JasperViewer(print, false);
            view.setVisible(true);
        }catch(JRException e){
            System.out.println(e);
            }*/
    }//GEN-LAST:event_btnReporteLocalidadesActionPerformed

    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        cualquiera();
    }//GEN-LAST:event_txtNombreKeyPressed

    private void cboProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboProvinciaActionPerformed
        if (bandera != "inicio"){
            CargarConsultaCategoria();
        }
    }//GEN-LAST:event_cboProvinciaActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        bandera="inicio";
    }//GEN-LAST:event_formWindowClosed


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
            java.util.logging.Logger.getLogger(BuscarLoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarLoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarLoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarLoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuscarLoc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnReporteLocalidades;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.ButtonGroup buttonGroup1;
    public static javax.swing.JComboBox cboProvincia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tblLocalidades;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
